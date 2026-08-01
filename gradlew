#!/bin/sh

APP_NAME="Gradle"
APP_BASE_NAME=`basename "$0"`

warn () { echo "$*"; } >&2
die () { echo; echo "$*"; echo; exit 1; } >&2

DIRNAME=`dirname "$0"`
APP_HOME=`cd "$DIRNAME" 2>/dev/null && pwd`
[ -z "$APP_HOME" ] && APP_HOME=`pwd`

CLASSPATH=$APP_HOME/gradle/wrapper/gradle-wrapper.jar

if [ -n "$JAVA_HOME" ] ; then
    [ -x "$JAVA_HOME/jre/sh/java" ] && JAVACMD="$JAVA_HOME/jre/sh/java" || JAVACMD="$JAVA_HOME/bin/java"
    [ ! -x "$JAVACMD" ] && die "ERROR: JAVA_HOME invalid: $JAVA_HOME"
else
    JAVACMD=java
    which java >/dev/null 2>&1 || die "ERROR: No java command found."
fi

if [ ! -f "$CLASSPATH" ] ; then
    echo "gradle-wrapper.jar not found. Downloading..."
    mkdir -p "$APP_HOME/gradle/wrapper"
    curl -fsSL "https://raw.githubusercontent.com/gradle/gradle/v8.11.1/gradle/wrapper/gradle-wrapper.jar" -o "$CLASSPATH" 2>/dev/null || \
    curl -fsSL "https://github.com/gradle/gradle/raw/v8.11.1/gradle/wrapper/gradle-wrapper.jar" -o "$CLASSPATH" 2>/dev/null || \
    wget -q "https://raw.githubusercontent.com/gradle/gradle/v8.11.1/gradle/wrapper/gradle-wrapper.jar" -O "$CLASSPATH" 2>/dev/null
    [ ! -f "$CLASSPATH" ] && die "Cannot download gradle-wrapper.jar. Run 'gradle wrapper' locally."
    echo "Downloaded."
fi

exec "$JAVACMD" \
    -Xmx64m -Xms64m \
    "-Dorg.gradle.appname=$APP_BASE_NAME" \
    -classpath "$CLASSPATH" \
    org.gradle.wrapper.GradleWrapperMain \
    "$@"
