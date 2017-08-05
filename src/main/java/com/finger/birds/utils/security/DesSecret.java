package com.finger.birds.utils.security;

import java.security.SecureRandom;

import javax.crypto.Cipher;
import javax.crypto.SecretKey;
import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.DESKeySpec;

/**
 * @Description: DES加密解密 
 * @Date:2014-7-23
 * @author duanye.sz
 *
 */
public class DesSecret {

	/**
	 * 密钥，长度必须是8的倍数
	 */
	private static final String  SECRET_KEY = "ZUES_EDU";
	
	/**
	 * 算法
	 */
	private final static String ALGORITHM = "DES";	

	
	/**
	 * 
	 * 方法用途: 加密<br>
	 * 实现步骤: <br>
	 * @param src 数据源
	 * @param key 密钥，长度必须是8的倍数
	 * @return	  返回加密后的数据
	 * @throws Exception
	 */
	public static byte[] encrypt(byte[] src, byte[] key) throws Exception {
		// DES算法要求有一个可信任的随机数源
		SecureRandom sr = new SecureRandom();
		// 从原始密匙数据创建DESKeySpec对象
		DESKeySpec dks = new DESKeySpec(key);
		// 创建一个密匙工厂，然后用它把DESKeySpec转换成
		// 返回转换指定算法的秘密密钥的SecretKey对象
		SecretKeyFactory keyFactory = SecretKeyFactory.getInstance(ALGORITHM);
		SecretKey securekey = keyFactory.generateSecret(dks);
		// Cipher对象实际完成加密操作
		Cipher cipher = Cipher.getInstance(ALGORITHM);
		// 用密匙初始化Cipher对象
		cipher.init(Cipher.ENCRYPT_MODE, securekey, sr);
		// 现在，获取数据并加密
		// 正式执行加密操作
		return cipher.doFinal(src);
	}

	
	/**
	 * 
	 * 方法用途:解密 <br>
	 * 实现步骤: <br>
	 * @param src	数据源
	 * @param key	密钥，长度必须是8的倍数
	 * @return		返回解密后的原始数据
	 * @throws Exception
	 */
	public static byte[] decrypt(byte[] src, byte[] key) throws Exception {
		// DES算法要求有一个可信任的随机数源
		SecureRandom sr = new SecureRandom();
		// 从原始密匙数据创建一个DESKeySpec对象
		DESKeySpec dks = new DESKeySpec(key);
		// 创建一个密匙工厂，然后用它把DESKeySpec对象转换成
		// 返回转换指定算法的秘密密钥的SecretKey对象
		SecretKeyFactory keyFactory = SecretKeyFactory.getInstance(ALGORITHM);
		SecretKey securekey = keyFactory.generateSecret(dks);
		// Cipher对象实际完成解密操作
		Cipher cipher = Cipher.getInstance(ALGORITHM);
		// 用密匙初始化Cipher对象
		cipher.init(Cipher.DECRYPT_MODE, securekey, sr);
		// 现在，获取数据并解密
		// 正式执行解密操作
		return cipher.doFinal(src);
	}

	/**
	 * 
	 * 方法用途: 二行制转字符串<br>
	 * 实现步骤: <br>
	 * @param b
	 * @return
	 */
	public static String byte2hex(byte[] b) {
		String hs = "";
		String stmp = "";
		for (int n = 0; n < b.length; n++) {
			stmp = (java.lang.Integer.toHexString(b[n] & 0XFF));
			if (stmp.length() == 1)
				hs = hs + "0" + stmp;
			else
				hs = hs + stmp;
		}
		return hs.toUpperCase();
	}

	/**
	 * 
	 * 方法用途: <br>
	 * 实现步骤: <br>
	 * @param b
	 * @return
	 */
	public static byte[] hex2byte(byte[] b) {
		if ((b.length % 2) != 0)
			throw new IllegalArgumentException("长度不是偶数");
		byte[] b2 = new byte[b.length / 2];
		for (int n = 0; n < b.length; n += 2) {
			String item = new String(b, n, 2);
			b2[n / 2] = (byte) Integer.parseInt(item, 16);
		}
		return b2;
	}
	
	/**
	 * 
	 * 方法用途: 解密<br>
	 * 实现步骤: <br>
	 * @param cipherText 密文
	 * @return
	 */
	public final static String decrypt(String cipherText) {
		try {
			return new String(decrypt(hex2byte(cipherText.getBytes("UTF-8")), SECRET_KEY.getBytes()));
		} catch (Exception e) {
		}
		return null;
	}
	
	/**
	 * 
	 * 方法用途: 解密<br>
	 * 实现步骤: <br>
	 * @param cipherText	密文
	 * @param secretKey	密钥
	 * @return
	 */
	public final static String decrypt(String cipherText, String secretKey) {
		try {
			return new String(decrypt(hex2byte(cipherText.getBytes("UTF-8")), secretKey.getBytes()),"UTF-8");
		} catch (Exception e) {
		}
		return null;
	}

	
	/**
	 * 
	 * 方法用途:加密 <br>
	 * 实现步骤: <br>
	 * @param password	没有加密的文本
	 * @return
	 */
	public final static String encrypt(String plainText) {
		try {
			return byte2hex(encrypt(plainText.getBytes("UTF-8"), SECRET_KEY.getBytes()));
		} catch (Exception e) {
		}
		return null;
	}
	
	/**
	 * 
	 * 方法用途:加密 <br>
	 * 实现步骤: <br>
	 * @param plainText	没有加密的文本
	 * @param secretKey	密钥
	 * @return
	 */
	public final static String encrypt(String plainText, String secretKey) {
		try {
			return byte2hex(encrypt(plainText.getBytes("UTF-8"), secretKey.getBytes()));
		} catch (Exception e) {
			
		}
		return null;
	}

	public static void main(String[] args) {
		System.out.println(decrypt("D3FD4BCA59E78B98779594F6A8841681"));
	}
}