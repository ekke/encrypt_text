// (c) 2018 Ekkehard Gentz (ekke) @ekkescorner
// my blog about Qt for mobile: http://j.mp/qt-x
// see also /COPYRIGHT and /LICENSE

// Obj-C RSA from ideawu: https://github.com/ideawu/Objective-C-RSA
// see also http://www.ideawu.com/blog/post/132.html

#import "rsautils.hpp"

#import <ios/src/RSA.h>

#include <QDebug>


RSAUtils::RSAUtils(QObject *parent) : QObject(parent)
{
	//
}

QString RSAUtils::encrypt(const QString &text) {

    NSString *theText = text.toNSString();
    // public demo from http://www.ideawu.com/blog/post/132.html
    NSString *pubkey = @"-----BEGIN PUBLIC KEY-----\nMIGfMA0GCSqGSIb3DQEBAQUAA4GNADCBiQKBgQDLuwt30JLYFvKcFOUdjPuDRdqv\nSnDb5TSdA/w0ND/GwLExpT66DeRz9+6//G//Y0y3c/yWT14k/ab1vID4U6W3vOgr\nafC0RyuIgH8ooCTNQpU+LtIoZ6qCejnux7VZ5lwWeT/9DQjWOtf6TopeRdzmOX09\nwa7c5xGGUsmi29QxDQIDAQAB\n-----END PUBLIC KEY-----";
    NSString *ret = [RSA encryptString:theText publicKey:pubkey];
    // NSLog(@"encrypted: %@", ret);
    qDebug() << "TEST RSA Obj-C " << ret;
    return QString::fromNSString(ret);
}


