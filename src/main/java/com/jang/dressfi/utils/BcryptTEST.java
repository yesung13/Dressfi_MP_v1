package com.jang.dressfi.utils;

public class BcryptTEST {

    public static void main(String[] args) {
        // TODO Auto-generated method stub
        String pass = "hello";
        System.out.println("�׳� ���ڿ� : " + pass);
        String hashPass = BCrypt.hashpw(pass, BCrypt.gensalt(12));
        System.out.println("��ȣȭ�� String : " + hashPass);
        String pass2 = "hello2";
        System.out.println("�н����� �񱳰��(����): " + BCrypt.checkpw(pass, hashPass));
        System.out.println("�н����� �񱳰��(�ٸ�): " + BCrypt.checkpw(pass2, hashPass));
    }
}
