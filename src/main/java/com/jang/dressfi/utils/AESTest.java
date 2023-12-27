package com.jang.dressfi.utils;


public class AESTest {

    public static void main(String[] args) throws Exception {

        String key = "jangan-1182-sam!!";       // key?�� 16?�� ?��?��
        AES256Util aes256 = new AES256Util(key);

        String text = "1234";
        String encText = aes256.aesEncode(text);
        String decText = aes256.aesDecode(encText);

        System.out.println("?��?��?��?�� 문자 : " + text);
        System.out.println("?��?��?��?�� 문자 : " + encText);
        System.out.println("복호?��?�� 문자 : " + decText);
    }
}
