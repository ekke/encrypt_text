// (c) 2018 ekke@ekkes-corner.org

package org.ekkescorner.rsa;

import android.util.Log;
import android.os.Build;
import android.util.Base64;
import java.security.KeyFactory;
import java.security.PublicKey;
import java.security.spec.X509EncodedKeySpec;

import javax.crypto.Cipher;


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
        byte[] encrypted = null;
        try {
              String publicKeyPEM = PUBLIC_KEY;
              // Remove the first and last lines
              publicKeyPEM = publicKeyPEM.replace("-----BEGIN PUBLIC KEY-----\n", "");
              publicKeyPEM = publicKeyPEM.replace("-----END PUBLIC KEY-----", "");
              // Base64 decode data
              Log.d("ekkescorner enccryptData", publicKeyPEM);
              try {
                  byte[] publicBytes = Base64.decode(publicKeyPEM, Base64.DEFAULT);
                  X509EncodedKeySpec keySpec = new X509EncodedKeySpec(publicBytes);
                  KeyFactory keyFactory = KeyFactory.getInstance("RSA");
                  PublicKey pubKey = keyFactory.generatePublic(keySpec);
                  Cipher cipher = Cipher.getInstance("RSA/ECB/PKCS1PADDING"); //or try with "RSA"
                  cipher.init(Cipher.ENCRYPT_MODE, pubKey);
                  encrypted = cipher.doFinal(txt.getBytes());
                  encodedString = Base64.encodeToString(encrypted, Base64.DEFAULT);
              } catch (Exception e) {
                  e.printStackTrace();
              }
              }
              finally {
                  return encodedString;
              }
    }
}
