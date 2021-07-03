QT += quick
CONFIG += c++11

# QT_NO_DEPRECATED_WARNINGS will show no deprecated warnings
# comment out and build-clean-project to see them all
# should be done from time to time
# while preparing src for Qt6
# DEFINES += QT_NO_DEPRECATED_WARNINGS

# The following define makes your compiler emit warnings if you use
# any feature of Qt which as been marked deprecated (the exact warnings
# depend on your compiler). Please consult the documentation of the
# deprecated API in order to know how to port your code away from it.
# DEFINES += QT_DEPRECATED_WARNINGS

# You can also make your code fail to compile if you use deprecated APIs.
# In order to do so, uncomment the following line.
# You can also select to disable deprecated APIs only up to a certain version of Qt.
#DEFINES += QT_DISABLE_DEPRECATED_BEFORE=0x060000    # disables all the APIs deprecated before Qt 6.0.0

SOURCES += \
    cpp/main.cpp \
    cpp/applicationui.cpp

# can be placed under android only, but I prefer to see them always
OTHER_FILES += android/src/org/ekkescorner/rsa/QAndroidRSAUtils.java

RESOURCES += qml.qrc \
        js.qrc

# Additional import path used to resolve QML modules in Qt Creator's code model
QML_IMPORT_PATH =

# Additional import path used to resolve QML modules just for Qt Quick Designer
QML_DESIGNER_IMPORT_PATH =

# Default rules for deployment.
qnx: target.path = /tmp/$${TARGET}/bin
else: unix:!android: target.path = /opt/$${TARGET}/bin
!isEmpty(target.path): INSTALLS += target

DISTFILES += \
    android/AndroidManifest.xml \
    android/gradle/wrapper/gradle-wrapper.jar \
    android/gradlew \
    android/res/values/libs.xml \
    android/build.gradle \
    android/gradle.properties \
    android/gradle/wrapper/gradle-wrapper.properties \
    android/gradlew.bat

android {
    QT += androidextras

    ANDROID_PACKAGE_SOURCE_DIR = $$PWD/android

    # deploying 32-bit and 64-bit APKs you need different VersionCode
    # here's my way to solve this - per ex. Version 1.2.3
    # aabcddeef aa: 21 (MY_MIN_API), b: 0 (32 Bit) or 1 (64 Bit)  c: 0 (unused)
    # dd: 01 (Major Release), ee: 02 (Minor Release), f:  3 (Patch Release)
    # VersionName 1.2.3
    # VersionCode 32 Bit: 210001023
    # VersionCode 64 Bit: 211001023
        # Version App Bundles: 212001023
    defineReplace(droidVersionCode) {
        segments = $$split(1, ".")
        for (segment, segments): vCode = "$$first(vCode)$$format_number($$segment, width=2 zeropad)"
        equals(ANDROID_ABIS, arm64-v8a): \
            prefix = 1
        else: equals(ANDROID_ABIS, armeabi-v7a): \
            prefix = 0
        else: prefix = 2
        # add more cases as needed
        return($$first(prefix)0$$first(vCode))
    }
    MY_VERSION = 1.0
    MY_PATCH_VERSION = 0
    MY_MIN_API = 21
    ANDROID_VERSION_NAME = $$MY_VERSION"."$$MY_PATCH_VERSION
    ANDROID_VERSION_CODE = $$MY_MIN_API$$droidVersionCode($$MY_VERSION)$$MY_PATCH_VERSION

    # find this in shadow build android-build gradle.properties
    ANDROID_MIN_SDK_VERSION = "21"
    ANDROID_TARGET_SDK_VERSION = "29"
}

ios {
    # framework needed for RSA classes
    LIBS += -framework Security

    SOURCES += ios/src/RSA.m \
        ios/src/rsautils.mm
    HEADERS += ios/src/RSA.h \
        ios/src/rsautils.hpp
}

HEADERS += \
    cpp/applicationui.hpp
