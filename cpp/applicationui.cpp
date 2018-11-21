// ekke (Ekkehard Gentz) @ekkescorner
#include "applicationui.hpp"

#include <QtQml>
#include <QGuiApplication>

#include <QDebug>
#include <QString>

#if defined (Q_OS_ANDROID)
#include <QtAndroidExtras/QAndroidJniObject>
#include <jni.h>
#endif

ApplicationUI::ApplicationUI(QObject *parent) : QObject(parent)
{
#if defined (Q_OS_IOS)
    mRSAUtils = new RSAUtils(this);
#endif
}

QString ApplicationUI::encryptText(const QString& theText)
{
    qDebug() << "try to encrypt: " << theText;
#if defined (Q_OS_IOS)
    QString encryptedStringBase64 = mRSAUtils->encrypt(theText);
    qDebug() << "OBJ-C ENCR STRING: " << encryptedStringBase64;
    QString asHex = encryptedStringBase64.toLatin1().toHex();
    qDebug() << "HEX: " << asHex;
    return asHex;
#elif defined (Q_OS_ANDROID)
    QAndroidJniObject myText = QAndroidJniObject::fromString(theText);
    QAndroidJniObject theString = QAndroidJniObject::callStaticObjectMethod("org/ekkescorner/rsa/QAndroidRSAUtils",
                                                                                     "enccryptData",
                                                                                     "(Ljava/lang/String;)Ljava/lang/String;",
                                                                                     myText.object<jstring>());
    QString encryptedStringBase64 = theString.toString();
    qDebug() << "ANDROID ENCR STRING: " << encryptedStringBase64;
    if(encryptedStringBase64 == "Encryption failed") {
        return encryptedStringBase64;
    }
    QString asHex = encryptedStringBase64.toLatin1().toHex();
    qDebug() << "HEX: " << asHex;
    return asHex;
#else
    QString fallBack = "not implemented for all platforms - use JS code";
    return fallBack;
#endif

}


