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

    // public static String PUBLIC_KEY = "-----BEGIN PUBLIC KEY-----\nMIGfMA0GCSqGSIb3DQEBAQUAA4GNADCBiQKBgQDLuwt30JLYFvKcFOUdjPuDRdqv\nSnDb5TSdA/w0ND/GwLExpT66DeRz9+6//G//Y0y3c/yWT14k/ab1vID4U6W3vOgr\nafC0RyuIgH8ooCTNQpU+LtIoZ6qCejnux7VZ5lwWeT/9DQjWOtf6TopeRdzmOX09\nwa7c5xGGUsmi29QxDQIDAQAB\n-----END PUBLIC KEY-----";
    public static String PUBLIC_KEY = "-----BEGIN PUBLIC KEY-----\nMIICIjANBgkqhkiG9w0BAQEFAAOCAg8AMIICCgKCAgEAgtaGkn1FfUmOb7nFK8DZ\nfU4JTtmuuLyJ+FRABhvvS+6rUQGiWyveJrLhMK4VI+zYBJrIKhKsDwQTbbZv/uV8\nW5ysCewUOsFt6clcZnc1IWAYOIw6D0B3TnodMwRBaR4kDsGRwMKy6S8T7WjhU6/0\nP8JLjtHQicO4pVqrnJiSJWsEXVuU4ClDRgok69wp7z/n6AHkllzGVgJMWM/JvQTn\ny30xrFxhlfDP3KjsLaiuUZwRxb7ZXDi9ovneulMwQyPSZi3Met0FpXfq5NfdsHqD\nPWZ1eSj8ghoOgbC9F1RMpgMz69tSF7lZYCgkNsl1N/k0E4Sw8uB6wAyLmgOeAn9G\n98/LdAHM7DFWJV2y18H7RXiUUtdtOSSvsp8PVbpnT8C/icHnXJbnvHZ81usUCo6x\nfUepmGEf5J3uA0+rmdMBOOlV/JfqjM04Ta+empRkyaOf3CF10Oc5Xm5fHUhWJ91v\nDS8G8EP/cKJe3wp1Ew2ItxbgDKTRAKq5dg83Mf3brgXppqTa4X6iNpUxm5fQmATi\nVk4+3/3x5k5rDky/iZQ1TtPPnQ4POiCvAfdq5i/cUkAnNKbeHadCB0wWzf7jsCXv\nF25mp+zNcgBUDcwLyAUXLzBVmxSSGLoLzqHSzln1hUUoRVZTvqU08g2eTfWknVY6\n7ZOsHxRHmBm0swykpTix5XcCAwEAAQ==\n-----END PUBLIC KEY-----";
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
