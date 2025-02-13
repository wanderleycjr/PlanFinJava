package com.pcmoney.util;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

import org.apache.commons.codec.binary.Hex;

public class Criptografia {
	
	public static String converteParaMD5(String senha) throws NoSuchAlgorithmException {
		MessageDigest md = MessageDigest.getInstance("MD5");
		md.update(senha.getBytes());
		byte[] digest = md.digest();
		String resultado = new String(Hex.encodeHex(digest));
		return resultado;
	}

}