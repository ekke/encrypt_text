// JS solution see here: // see http://www-cs-students.stanford.edu/~tjw/jsbn/rsa.html to test

// Obj-C RSA from ideawu: https://github.com/ideawu/Objective-C-RSA
// see also http://www.ideawu.com/blog/post/132.html


import QtQuick 2.15
import QtQuick.Controls 2.15

import "../js/rsbn/jsbn.js" as MyJsbn
import "../js/rsbn/rsa.js" as MyRsa
import "../js/rsbn/rng.js" as MyRng
import "../js/rsbn/prng4.js" as MyPrng4
import "../js/rsbn/base64.js" as MyBase64

ApplicationWindow {
    id: window
    visible: true
    width: 640
    height: 480
    title: qsTr("Encrypt")

    Page {
        id: thePage
        padding: 24

        Column {
            TextField {
                id: myLabel
                text: "test"
            } // text
            Button {
                text: "Encrypt"
                onClicked: {
                    doEncrypt()
                }
            } // button
            Label {
                id: myResult
                text: "??"
            } // result
            Button {
                visible: Qt.platform.os === "ios" || Qt.platform.os === "android"
                text: Qt.platform.os === "ios"? "Encrypt Obj-C" : "Encrypt JAVA"
                onClicked: {
                    doEncryptNative()
                }
            } // button obj-c
            Label {
                id: myResultNative
                text: "??"
            } // result obj-c
        } // col
    } // page

    // JAVASCRIPT
    function doEncrypt() {
        var rsa = new MyRsa.RSAKey();
        rsa.setPublic(modulusHex, "10001")
        // Qt 5.12 Beta 3 slows down rsa.encrypt()
        var before = new Date();
        var res = rsa.encrypt(myLabel.text)
        if(res) {
            var after = new Date();
            console.log("JS RSA EncryptionTime: " + (after - before) + "ms")
            myResult.text = MyRsa.linebrk(res, 64)
        } else {
            myResult.text = "encrypt failed"
        }
    } // encrypt JS

    // Obj-C
    // public key is stored at RSAUtils.mm
    // JAVA
    // public key is stored at QAndroidRSAUtils.java
    // feel free to set the public key from C++
    function doEncryptNative() {
        var before = new Date();
        myResultNative.text = MyRsa.linebrk(myApp.encryptText(myLabel.text), 64)
        var after = new Date();
        console.log("native RSA EncryptionTime: " + (after - before) + "ms")
    }

    // JS solution needs this:
    property string modulusHex:
       "a5261939975948bb7a58dffe5ff54e65f0498f9175f5a09288810b8975871e99
af3b5dd94057b0fc07535f5f97444504fa35169d461d0d30cf0192e307727c06
5168c788771c561a9400fb49175e9e6aa4e23fe11af69e9412dd23b0cb6684c4
c2429bce139e848ab26d0829073351f4acd36074eafd036a5eb83359d2a698d3"
} // app window
