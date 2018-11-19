// (c) 2017 Ekkehard Gentz (ekke) @ekkescorner
#ifndef RSAUTILS_H
#define RSAUTILS_H

// #include "shareutils.hpp"
#include <QObject>

class RSAUtils : QObject
{
    Q_OBJECT

public:
    explicit RSAUtils(QObject *parent = nullptr);
    QString encrypt(const QString &text);


public slots:



};

#endif
