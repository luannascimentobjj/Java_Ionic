package com.luannascimento.cursomc.resources.utils;

import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class URL {

	public static List<Integer> decodeIntList(String s) {

		try {
			return Arrays.asList(s.split(",")).stream().map(x -> Integer.parseInt(x)).collect(Collectors.toList());
		
		} catch (Exception e) {
			
			List<Integer> emptyList = new ArrayList<>();
			
			return emptyList;
		}
		
	}
		
		

	public static String decodeParam(String s) {

		try {
			return URLDecoder.decode(s, "UTF-8");
		} catch (UnsupportedEncodingException e) {
			return "";
		}

	}
}
