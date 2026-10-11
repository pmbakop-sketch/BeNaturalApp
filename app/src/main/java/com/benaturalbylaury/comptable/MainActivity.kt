package com.benaturalbylaury.comptable

import android.annotation.SuppressLint
import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.view.View
import android.webkit.*
import android.widget.ProgressBar
import androidx.activity.OnBackPressedCallback
import androidx.appcompat.app.AppCompatActivity
import androidx.core.splashscreen.SplashScreen.Companion.installSplashScreen

class MainActivity : AppCompatActivity() {

    private lateinit var webView: WebView
    private lateinit var progressBar: ProgressBar

    // ⚠️ REMPLACEZ CETTE URL PAR VOTRE URL GOOGLE APPS SCRIPT
    private val appUrl = "https://script.google.com/macros/s/VOTRE_ID_DE_DEPLOIEMENT/exec"

    @SuppressLint("SetJavaScriptEnabled")
    override fun onCreate(savedInstanceState: Bundle?) {
        // Splash screen
        installSplashScreen()

        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        // Initialisation des vues
        webView = findViewById(R.id.webView)
        progressBar = findViewById(R.id.progressBar)

        // Configuration WebView
        configureWebView()

        // Gestion du bouton retour
        onBackPressedDispatcher.addCallback(this, object : OnBackPressedCallback(true) {
            override fun handleOnBackPressed() {
                if (webView.canGoBack()) {
                    webView.goBack()
                } else {
                    finish()
                }
            }
        })

        // Charger l'application
        webView.loadUrl(appUrl)
    }

    @SuppressLint("SetJavaScriptEnabled")
    private fun configureWebView() {
        webView.settings.apply {
            // Activer JavaScript (obligatoire pour Google Apps Script)
            javaScriptEnabled = true

            // Activer le DOM Storage
            domStorageEnabled = true

            // Activer le cache
            cacheMode = WebSettings.LOAD_DEFAULT

            // Permettre le zoom
            builtInZoomControls = true
            displayZoomControls = false

            // Support du viewport
            useWideViewPort = true
            loadWithOverviewMode = true

            // Activer les bases de données
            databaseEnabled = true

            // User Agent personnalisé
            userAgentString = "$userAgentString BeNaturalApp/1.0"
        }

        // Client WebView personnalisé
        webView.webViewClient = object : WebViewClient() {

            override fun onPageStarted(view: WebView?, url: String?, favicon: android.graphics.Bitmap?) {
                super.onPageStarted(view, url, favicon)
                progressBar.visibility = View.VISIBLE
            }

            override fun onPageFinished(view: WebView?, url: String?) {
                super.onPageFinished(view, url)
                progressBar.visibility = View.GONE
            }

            override fun shouldOverrideUrlLoading(view: WebView?, request: WebResourceRequest?): Boolean {
                val url = request?.url?.toString() ?: return false

                // Ouvrir WhatsApp dans l'application externe
                if (url.startsWith("https://wa.me") || url.startsWith("whatsapp://")) {
                    val intent = Intent(Intent.ACTION_VIEW, Uri.parse(url))
                    startActivity(intent)
                    return true
                }

                // Ouvrir les liens mailto dans l'application email
                if (url.startsWith("mailto:")) {
                    val intent = Intent(Intent.ACTION_SENDTO, Uri.parse(url))
                    startActivity(intent)
                    return true
                }

                // Ouvrir les liens tel dans le téléphone
                if (url.startsWith("tel:")) {
                    val intent = Intent(Intent.ACTION_DIAL, Uri.parse(url))
                    startActivity(intent)
                    return true
                }

                // Garder les autres URLs dans la WebView
                return false
            }

            override fun onReceivedError(
                view: WebView?,
                request: WebResourceRequest?,
                error: WebResourceError?
            ) {
                super.onReceivedError(view, request, error)
                // Afficher une page d'erreur personnalisée
                if (request?.isForMainFrame == true) {
                    view?.loadData(
                        getErrorHtml(),
                        "text/html",
                        "UTF-8"
                    )
                }
            }
        }

        // Client Chrome personnalisé pour le support des alertes
        webView.webChromeClient = object : WebChromeClient() {
            override fun onProgressChanged(view: WebView?, newProgress: Int) {
                super.onProgressChanged(view, newProgress)
                progressBar.progress = newProgress
            }
        }
    }

    private fun getErrorHtml(): String {
        return """
            <!DOCTYPE html>
            <html>
            <head>
                <meta charset="UTF-8">
                <meta name="viewport" content="width=device-width, initial-scale=1.0">
                <style>
                    body {
                        font-family: 'Segoe UI', Arial, sans-serif;
                        display: flex;
                        flex-direction: column;
                        align-items: center;
                        justify-content: center;
                        height: 100vh;
                        margin: 0;
                        background: linear-gradient(135deg, #4CAF50 0%, #388E3C 100%);
                        color: white;
                        text-align: center;
                        padding: 20px;
                    }
                    .icon { font-size: 64px; margin-bottom: 20px; }
                    h1 { margin: 0 0 10px 0; font-size: 24px; }
                    p { margin: 0 0 30px 0; opacity: 0.9; }
                    button {
                        background: white;
                        color: #4CAF50;
                        border: none;
                        padding: 15px 40px;
                        border-radius: 25px;
                        font-size: 16px;
                        font-weight: bold;
                        cursor: pointer;
                    }
                </style>
            </head>
            <body>
                <div class="icon">📡</div>
                <h1>Connexion impossible</h1>
                <p>Vérifiez votre connexion internet et réessayez.</p>
                <button onclick="location.reload()">Réessayer</button>
            </body>
            </html>
        """.trimIndent()
    }

    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)
        webView.saveState(outState)
    }

    override fun onRestoreInstanceState(savedInstanceState: Bundle) {
        super.onRestoreInstanceState(savedInstanceState)
        webView.restoreState(savedInstanceState)
    }
}
