// ekke (Ekkehard Gentz) @ekkescorner
#include "applicationui.hpp"

#include <QtQml>
#include <QGuiApplication>

#include <QDebug>

ApplicationUI::ApplicationUI(QObject *parent) : QObject(parent), mRSAUtils(new RSAUtils(this))
{
    //
}

QString ApplicationUI::encryptText(const QString& theText)
{
#if defined (Q_OS_IOS)
    QString encryptedStringBase64 = mRSAUtils->encrypt(theText);
    qDebug() << "ENCR STRING: " << encryptedStringBase64;
    QString asHex = encryptedStringBase64.toLatin1().toHex();
    qDebug() << "HEX: " << asHex;
    return asHex;
#endif

}


