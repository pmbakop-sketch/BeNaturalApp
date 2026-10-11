# 📱 BeNatural Comptable - Application Android

Application mobile de gestion comptable pour BeNatural ByLaury.

## 🚀 Installation rapide

### Prérequis
- Android Studio (version Arctic Fox ou supérieure)
- JDK 17
- Un appareil Android ou un émulateur (API 24+)

### Étapes

1. **Ouvrir le projet dans Android Studio**
   ```
   File → Open → Sélectionner le dossier BeNaturalApp
   ```

2. **Configurer l'URL de l'application**
   
   Ouvrir `app/src/main/java/com/benaturalbylaury/comptable/MainActivity.kt`
   
   Remplacer la ligne :
   ```kotlin
   private val appUrl = "https://script.google.com/macros/s/AKfycbxzv4rvdHPUuZ6A2haWjbSGmxd3OsZMcw3V-CbAXNa3njkchFLWWGIZtxKi4H7LQWvx/exec"
   ```
   
   Par votre URL Google Apps Script réelle.

3. **Synchroniser Gradle**
   ```
   File → Sync Project with Gradle Files
   ```

4. **Générer l'APK de release**
   ```
   Build → Generate Signed Bundle / APK → APK
   ```
   
   Ou en ligne de commande :
   ```bash
   ./gradlew assembleRelease
   ```

5. **L'APK se trouve dans :**
   ```
   app/build/outputs/apk/release/app-release.apk
   ```

## 📋 Fonctionnalités

- ✅ Interface WebView optimisée
- ✅ Splash screen avec logo
- ✅ Barre de progression lors du chargement
- ✅ Gestion du bouton retour
- ✅ Ouverture automatique de WhatsApp
- ✅ Page d'erreur personnalisée (hors connexion)
- ✅ Support du thème sombre
- ✅ Icône adaptive (Android 8+)

## 🎨 Personnalisation

### Changer les couleurs
Modifier `app/src/main/res/values/colors.xml`

### Changer le nom de l'app
Modifier `app/src/main/res/values/strings.xml`

### Changer l'icône
Remplacer les fichiers dans `app/src/main/res/mipmap-*/`

## 📱 Configuration minimale

- Android 7.0 (API 24) ou supérieur
- Connexion Internet requise
- ~5 MB d'espace de stockage

## 🔧 Structure du projet

```
BeNaturalApp/
├── app/
│   ├── src/main/
│   │   ├── java/.../MainActivity.kt    # Activité principale
│   │   ├── res/
│   │   │   ├── layout/                 # Interfaces XML
│   │   │   ├── values/                 # Couleurs, thèmes, textes
│   │   │   ├── drawable/               # Icônes vectorielles
│   │   │   └── mipmap-*/               # Icônes de l'app
│   │   └── AndroidManifest.xml         # Configuration
│   ├── build.gradle.kts                # Dépendances app
│   └── proguard-rules.pro              # Règles ProGuard
├── build.gradle.kts                    # Config globale
├── settings.gradle.kts                 # Modules
└── gradle.properties                   # Propriétés Gradle
```

## 📞 Support

BeNatural ByLaury
- WhatsApp : +237 675 495 937
- Site web : benaturalbylaury.com

---
© 2026 BeNatural ByLaury - Tous droits réservés
