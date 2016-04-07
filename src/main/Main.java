package main;

import java.util.List;

import files.FileHandler;

public class Main 
{
	public static void main(String[] args) 
	{
		FileHandler fh = new FileHandler();
		
		List<String> result = fh.readFile("/file/productos.txt", ",");
		
		System.out.println(result);
	}
}