#!/bin/sh

# Gradle start up script
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
    [ ! -x "$JAVACMD" ] && die "ERROR: JAVA_HOME is set to an invalid directory: $JAVA_HOME"
else
    JAVACMD=java
    which java >/dev/null 2>&1 || die "ERROR: No 'java' command found in PATH."
fi

if [ ! -f "$CLASSPATH" ] ; then
    echo "gradle-wrapper.jar not found at $CLASSPATH."
    echo "Please run 'gradle wrapper --gradle-version 8.11.1' locally and commit the files."
    exit 1
fi

exec "$JAVACMD" \
    -Xmx64m \
    -Xms64m \
    "-Dorg.gradle.appname=$APP_BASE_NAME" \
    -classpath "$CLASSPATH" \
    org.gradle.wrapper.GradleWrapperMain \
    "$@"
