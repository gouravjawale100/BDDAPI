package utils;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

public class ConfigReader {
	
	
	public String readPropData(String propToBeRead) throws IOException
	{
		String path =  System.getProperty("user.dir");// fetch project path
		
		path = path + "\\src\\test\\resources\\config.properties";
		
		Properties prop = new Properties();
		
		File file = new File(path);
		
		FileInputStream fis = new FileInputStream(file);
		
		prop.load(fis);
		
		String data = prop.getProperty(propToBeRead);
		
		return data;
	}

	
	public static void main(String[] args) throws IOException {
//		String path =  System.getProperty("user.dir");
//		
//		System.out.println(path);
		
//		ConfigReader configReader = new ConfigReader();
//		
//		configReader.readPropData();
	}
}
