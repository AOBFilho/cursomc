package com.aobfilho.cursomc.resources.utils;

import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.util.ArrayList;
import java.util.List;

public class URL {

	public static String decodeParam(String s) {
		try {
			return URLDecoder.decode(s, "UTF-8");
		} catch (UnsupportedEncodingException e) {
			return "";
		}
	}
	public static List<Integer> decodeIntList(String s){
		List<Integer> lista = new ArrayList<Integer>();
		if (s.isEmpty()) {
			return lista; 
		}
		
		String[] vet = s.split(",");
		for (int i=0;i<vet.length;i++) {
			lista.add(Integer.parseInt(vet[i]));
		}	
		return lista;
	}
}
