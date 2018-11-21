// (c) 2018 ekke@ekkes-corner.org

package org.ekkescorner.rsa;

import android.util.Log;
import android.os.Build;
import java.lang.String;
import javax.crypto.Cipher;
import java.security.spec.X509EncodedKeySpec;
import java.security.PublicKey;
import java.security.interfaces.RSAPublicKey;
import java.security.KeyFactory;
import org.apache.commons.codec.binary.Base64;

import java.security.GeneralSecurityException;
import java.io.IOException;

// import org.apache.commons.codec.binary.BaseNCodec;


public class QAndroidRSAUtils 
{
	
    protected QAndroidRSAUtils()
    {
        //
    }

    public static String PUBLIC_KEY = "-----BEGIN PUBLIC KEY-----\nMIGfMA0GCSqGSIb3DQEBAQUAA4GNADCBiQKBgQDLuwt30JLYFvKcFOUdjPuDRdqv\nSnDb5TSdA/w0ND/GwLExpT66DeRz9+6//G//Y0y3c/yWT14k/ab1vID4U6W3vOgr\nafC0RyuIgH8ooCTNQpU+LtIoZ6qCejnux7VZ5lwWeT/9DQjWOtf6TopeRdzmOX09\nwa7c5xGGUsmi29QxDQIDAQAB\n-----END PUBLIC KEY-----";

    static String enccryptData(String txt)
    {
        Log.d("ekkescorner enccryptData", txt);
        String encodedString = "Encryption failed";
        try {
              String publicKeyPEM = PUBLIC_KEY;
              // Remove the first and last lines
              publicKeyPEM = publicKeyPEM.replace("-----BEGIN PUBLIC KEY-----\n", "");
              publicKeyPEM = publicKeyPEM.replace("-----END PUBLIC KEY-----", "");
              // Base64 decode data
              Log.d("ekkescorner enccryptData", publicKeyPEM);
              byte[] encoded = Base64.decodeBase64(publicKeyPEM);
              Log.d("ekkescorner enccryptData", "????");
              KeyFactory kf = KeyFactory.getInstance("RSA");
              RSAPublicKey pubKey = (RSAPublicKey) kf.generatePublic(new X509EncodedKeySpec(encoded));
              Cipher cipher = Cipher.getInstance("RSA");
              cipher.init(Cipher.ENCRYPT_MODE, pubKey);
              encodedString = Base64.encodeBase64String(cipher.doFinal(txt.getBytes("UTF-8")));
            }
            catch (GeneralSecurityException e) {
              e.printStackTrace();
            }
            catch (IOException e) {
              e.printStackTrace();
            }
            catch (Exception e) {
              e.printStackTrace();
            }
            finally {
              return encodedString;
            }
    }
}
