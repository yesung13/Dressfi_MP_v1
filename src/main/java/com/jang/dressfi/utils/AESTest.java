package com.jang.dressfi.utils;



public class AESTest {

	public static void main(String[] args) throws Exception {
		
        String key = "jangan-1182-sam!!";       // key?Š” 16? ?´?ƒ
        AES256Util aes256 = new AES256Util(key);
         
        String text = "1234";
        String encText = aes256.aesEncode(text);
        String decText = aes256.aesDecode(encText);
         
        System.out.println("?•”?˜¸?™”?•  ë¬¸ì : " + text);
        System.out.println("?•”?˜¸?™”?œ ë¬¸ì : " + encText);
        System.out.println("ë³µí˜¸?™”?œ ë¬¸ì : " + decText);
    }
}
