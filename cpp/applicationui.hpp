// ekke (Ekkehard Gentz) @ekkescorner
#ifndef APPLICATIONUI_HPP
#define APPLICATIONUI_HPP

#include <QObject>

#include <QtQml>

#if defined (Q_OS_IOS)
#include "ios/src/rsautils.hpp"
#endif

class ApplicationUI : public QObject
{
    Q_OBJECT

public:
     ApplicationUI(QObject *parent = nullptr);

     Q_INVOKABLE
     QString encryptText(const QString& theText);

signals:

public slots:


private:

#if defined (Q_OS_IOS)
    RSAUtils* mRSAUtils;
#endif

};

#endif // APPLICATIONUI_HPP
