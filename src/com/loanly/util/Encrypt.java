package com.loanly.util;

import java.security.SecureRandom;

import javax.crypto.Cipher;
import javax.crypto.SecretKey;
import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.DESKeySpec;

public class Encrypt {
	final String ENKEY="moneybal";
    public byte[] desCrypto(byte[] datasource) {            
        try{
        	byte[] result =new byte[datasource.length];
        	for(int i=0;i<datasource.length;i++){
        		result[i] = (byte) (datasource[i] & (byte) i);
        	}
        	return result;
        	/*
        	byte[] b = new byte[]{(byte)1};
        	SecureRandom random = new SecureRandom(b);
        	
        	System.out.println("random == "+random.getAlgorithm());
        	DESKeySpec desKey = new DESKeySpec(ENKEY.getBytes());
        	
        	//创建一个密匙工厂，然后用它把DESKeySpec转换成
        	SecretKeyFactory keyFactory = SecretKeyFactory.getInstance("DES");
        	SecretKey securekey = keyFactory.generateSecret(desKey);
        	//Cipher对象实际完成加密操作
        	Cipher cipher = Cipher.getInstance("DES");
        	//用密匙初始化Cipher对象
        	cipher.init(Cipher.ENCRYPT_MODE, securekey, random);
        	//现在，获取数据并加密
        	//正式执行加密操作
        	return cipher.doFinal(datasource);
        	*/
        }catch(Throwable e){
                e.printStackTrace();
        }
        return null;
    }
    
    
    
    private byte[] Decrypt(byte[] src) throws Exception {
        // DES算法要求有一个可信任的随机数源
    	byte[] b = new byte[]{(byte)1};
        SecureRandom random = new SecureRandom(b);
        // 创建一个DESKeySpec对象
        DESKeySpec desKey = new DESKeySpec(ENKEY.getBytes());
        // 创建一个密匙工厂
        SecretKeyFactory keyFactory = SecretKeyFactory.getInstance("DES");
        // 将DESKeySpec对象转换成SecretKey对象
        SecretKey securekey = keyFactory.generateSecret(desKey);
        // Cipher对象实际完成解密操作
        Cipher cipher = Cipher.getInstance("DES");
        // 用密匙初始化Cipher对象
        cipher.init(Cipher.DECRYPT_MODE, securekey, random);
        // 真正开始解密操作
        return cipher.doFinal(src);
    }    
}
