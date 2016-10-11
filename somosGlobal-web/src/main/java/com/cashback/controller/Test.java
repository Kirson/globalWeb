package com.cashback.controller;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class Test {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Test t = new Test();
		t.stringToMd5();
	}

	public void stringToMd5() {
		String password = "123456";
		MessageDigest md;
		try {
			md = MessageDigest.getInstance("MD5");
			md.update(password.getBytes());
			byte byteData[] = md.digest();
			StringBuffer sb = new StringBuffer();
			for (int i = 0; i < byteData.length; i++)
				sb.append(Integer.toString((byteData[i] & 0xff) + 0x100, 16)
						.substring(1));

			System.out.println("Digest(in hex format):: " + sb.toString());
		} catch (NoSuchAlgorithmException e) {
			e.printStackTrace();
		}
	}
}
