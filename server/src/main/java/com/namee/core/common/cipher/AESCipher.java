package com.namee.core.common.cipher;

import org.apache.commons.codec.binary.Base64;

import javax.crypto.BadPaddingException;
import javax.crypto.Cipher;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;
import java.io.UnsupportedEncodingException;
import java.security.InvalidAlgorithmParameterException;
import java.security.InvalidKeyException;
import java.security.Key;
import java.security.NoSuchAlgorithmException;

/**
 * <pre>
 *	AES 128bit 암호화 클래스
 *	AESCipher aes = AESCipher.getInstance();
 *	aes.encode(str)
 *	aes.decode(str)
 * ------------------------------------------------------------------------------
 *	기본 JCE가 128비트만 지원해서 16바이트(128bit) 키만을 사용할 수 있는 상황.
 *	256비트 사용하기 위해선 패치를 통해 256비트까지 확장해서 32바이트 키를 사용할 수 있게 한다.
 *	- $JAVA_HOME/jre/lib/security 에 관련파일을 덮어쓴다.
 * JDK7 <http://www.oracle.com/technetwork/java/javase/downloads/jce-7-download-432124.html>
 * JDK8 <http://www.oracle.com/technetwork/java/javase/downloads/jce8-download-2133166.html>
 * </pre>
 * @class		AES256Cipher.java
 * @author	zzh
 * @date		2014. 12. 12. 2014
 */
public class AESCipher {
    
	final static String _SECRET_KEY  = "b3JwX21vYmlsZXNl"; //32bit
	final static String _CIPHER_TRANSFORMATION = "AES/CBC/PKCS5Padding";
	static Key keySpec;
 
    public AESCipher() {
        keySpec = new SecretKeySpec(_SECRET_KEY.getBytes(), "AES");
    }
 
    // 암호화
    public String encode(String str) {
        Cipher c = null;
        String enStr = null;
        try {
        	c = Cipher.getInstance(_CIPHER_TRANSFORMATION);
			c.init(Cipher.ENCRYPT_MODE, keySpec, new IvParameterSpec(_SECRET_KEY.getBytes()));
			
			byte[] encrypted = c.doFinal(str.getBytes("UTF-8"));
	        enStr = new String(Base64.encodeBase64(encrypted));
		} catch (InvalidKeyException | InvalidAlgorithmParameterException 
				| NoSuchAlgorithmException | NoSuchPaddingException 
				| IllegalBlockSizeException | BadPaddingException 
				| UnsupportedEncodingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
        return enStr;
    }
 
    //복호화
    public String decode(String str) {
    	Cipher c = null;
        String deStr = null;
        try {
			c = Cipher.getInstance(_CIPHER_TRANSFORMATION);
			c.init(Cipher.DECRYPT_MODE, keySpec, new IvParameterSpec(_SECRET_KEY.getBytes("UTF-8")));
			 
	        byte[] byteStr = Base64.decodeBase64(str.getBytes());
	        deStr = new String(c.doFinal(byteStr),"UTF-8");
		} catch (NoSuchAlgorithmException | NoSuchPaddingException 
				| InvalidKeyException | InvalidAlgorithmParameterException 
				| UnsupportedEncodingException | IllegalBlockSizeException 
				| BadPaddingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
        return deStr;
    }
}