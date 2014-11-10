package com.stephen.bt;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class OrgChartTxtFileReader {

	private String file_path;
	
	public OrgChartTxtFileReader(String filePath) {
		
		this.file_path = filePath;
		
	}
	
	// externally sourced
	public String[] Openfile() throws IOException {
		
		FileReader fileReader = new FileReader(file_path);
		BufferedReader bufferedReader = new BufferedReader(fileReader);
		
		int numberOfLines = readLines();
		String[] textData = new String[numberOfLines];
		
		for (int i=0; i < numberOfLines; i++) {
			textData[ i ] = bufferedReader.readLine();
		}
		
		bufferedReader.close();
		return textData;
	}
	
	// externally sourced
	private int readLines() throws IOException {
		
		FileReader file = new FileReader(file_path);
		BufferedReader bReader = new BufferedReader(file);
		
		int lineCount = 0;
		
		while (bReader.readLine() != null) {
			lineCount++;
		}
		bReader.close();
		
		return lineCount;
	}

}
