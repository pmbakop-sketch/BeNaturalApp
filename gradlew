#!/bin/sh

#
# Gradle wrapper script for POSIX systems
#

# Resolve links: $0 may be a link
app_path=$0

# Add default JVM options here
DEFAULT_JVM_OPTS='"-Xmx64m" "-Xms64m"'

# Determine the Java command to use to start the JVM
if [ -n "$JAVA_HOME" ] ; then
    if [ -x "$JAVA_HOME/jre/sh/java" ] ; then
        JAVACMD=$JAVA_HOME/jre/sh/java
    else
        JAVACMD=$JAVA_HOME/bin/java
    fi
else
    JAVACMD=java
fi

# Use the maximum available, or set MAX_FD != -1 to use that value
MAX_FD=maximum

warn () {
    echo "$*"
} >&2

die () {
    echo
    echo "$*"
    echo
    exit 1
} >&2

# Determine the APP_HOME
APP_HOME=$(cd "$(dirname "$0")" && pwd -P)
APP_NAME="Gradle"
APP_BASE_NAME=$(basename "$0")

# Gradle distribution
CLASSPATH=$APP_HOME/gradle/wrapper/gradle-wrapper.jar

# Download and run Gradle wrapper
GRADLE_DOWNLOAD_URL="https://services.gradle.org/distributions/gradle-8.2-bin.zip"

if [ ! -f "$APP_HOME/gradle/wrapper/gradle-wrapper.jar" ]; then
    echo "Downloading Gradle wrapper..."
    mkdir -p "$APP_HOME/gradle/wrapper"
    if command -v curl > /dev/null 2>&1; then
        curl -L -o "$APP_HOME/gradle/wrapper/gradle-wrapper.jar" \
            "https://raw.githubusercontent.com/gradle/gradle/v8.2.0/gradle/wrapper/gradle-wrapper.jar"
    elif command -v wget > /dev/null 2>&1; then
        wget -O "$APP_HOME/gradle/wrapper/gradle-wrapper.jar" \
            "https://raw.githubusercontent.com/gradle/gradle/v8.2.0/gradle/wrapper/gradle-wrapper.jar"
    fi
fi

exec "$JAVACMD" $DEFAULT_JVM_OPTS \
    -classpath "$CLASSPATH" \
    org.gradle.wrapper.GradleWrapperMain "$@"
