package com.stephen.bt;

import java.io.IOException;
import java.util.ArrayList;

public class Main {

	/**
	 * @param args
	 */
	
	public static void main(String[] args) throws IOException {
		// TODO Auto-generated method stub
		
		String file_name = args[0];
		String a = args[1];
		String b = args[2];
		
		try {
			OrgChartTxtFileReader mRead = new OrgChartTxtFileReader(file_name);
			String[] lines = mRead.Openfile();
			ArrayList<OrgChartElement> mElements = new ArrayList<OrgChartElement>();
			
			for (int i=1; i<lines.length; i++ ) {
								
				mElements.add( new OrgChartElement(lines[i]) );
			}
			
			OrgChartUtil mBtwn = new OrgChartUtil(mElements, a, b);
			
			mBtwn.showChartTraversal();
			
			
		} catch (IOException e) {
			System.out.println(e.getMessage());
		}
	}

}
