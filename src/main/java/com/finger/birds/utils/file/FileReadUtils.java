package com.finger.birds.utils.file;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;

public class FileReadUtils {

	public static String readAll(String fileName, String encoding) throws IOException {
		File file = new File(fileName);
		BufferedReader reader = null;
		StringBuffer content = new StringBuffer();
		try {
			InputStreamReader isr = new InputStreamReader(new FileInputStream(file), "UTF-8");
			reader = new BufferedReader(isr);
			String tempString = null;
			while ((tempString = reader.readLine()) != null) {
				content.append(tempString);
			}
		} catch (IOException e) {
			throw new IOException(e);
		} finally {
			if (reader != null) {
				reader.close();
			}
		}
		return content.toString();
	}
	
	public static void main(String[] args) throws IOException {
		//System.out.println(readAll("D:\\project\\conf\\email\\emailContent.html"));
	}
	
}
