package edu.utexas.datafetcher.utils;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

public class FileUtil {
	public static String getFileExtension(File file) {
		String fileName = file.getName();
		String extension = "";
		if(fileName.lastIndexOf(".") != -1 && fileName.lastIndexOf(".") != 0) {
			extension = fileName.substring(fileName.lastIndexOf(".") + 1);
		}
		return extension;
	}

	public static String readFileToString(File file) {
		StringBuilder fileData = new StringBuilder(2048);
		try {
			BufferedReader reader = new BufferedReader(new FileReader(file));
			char[] buf = new char[2048];
			int numRead = 0;
			while ((numRead = reader.read(buf)) != -1) {
				String readData = String.valueOf(buf, 0, numRead);
				fileData.append(readData);
				buf = new char[2048];
			}
			reader.close();
		} catch (IOException e) {
			System.err.println("readFileToString IOException on: " + file.getPath());
			e.printStackTrace();
		}
		return fileData.toString();
	}

	public static void writeStringToFile(String path, String str) {
		try {
			File file = new File(path);
			File dir = file.getParentFile();
			if (!dir.exists()) {
				dir.mkdirs();
			}
			BufferedWriter writer = new BufferedWriter(new FileWriter(file));
			writer.write(str);
			writer.close();
		} catch (IOException e) {
			System.err.println("writeStringToFile IOException on: " + path);
			e.printStackTrace();
		} catch (NullPointerException e) {
			System.err.println("writeStringToFile NullPointerException on: " + path);
			e.printStackTrace();
		}
	}

	public static int getLines(String content) {
		return content.split("\\n").length;
	}

}
