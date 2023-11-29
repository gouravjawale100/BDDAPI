package com.spotify.api;

public class Example {
	
	
	public static String test1()
	{
		int rollnum = 10;
		
		String code = "Eder"+rollnum;
		
		System.out.println(code);
		
		return code;
	}
	
	public static void main(String[] args) {
		test1();
		
		String Collegename = "COEP";
		
		String updatedCode = Collegename+ test1();
		
		System.out.println(updatedCode);
		
	}

}
