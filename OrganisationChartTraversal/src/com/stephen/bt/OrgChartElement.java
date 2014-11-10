package com.stephen.bt;

public class OrgChartElement {

	private int mEmployeeID;
	private String mName;
	private int mManagerID;
	private char[] lineCharacters;
	
	private int line_index = 0;
	
	
	public OrgChartElement(String current_line) {
		
		this.lineCharacters = current_line.toCharArray();
		
		setEmployeeID();
		setName();
		setManagerID();
	}
	
	private String sortCell() {
		int line_start=line_index,  x=0;
		
		char[] element;
		
		while (true) {
			if ( lineCharacters[line_index] == '|' ) {
				x++;
				if(x == 2) break;
			}
			if (x == 0) line_start++;
			line_index++;
		}
		
		element = new char[1+line_index-line_start];
		
		
		boolean sEmpty = true;
		int eStart = 0;
		for( int m=line_start+1; m<line_index; m++) {
			if( !Character.isWhitespace(lineCharacters[m]) ) {
				eStart = m;
				sEmpty = false;
				break;
			}
		}
		
		int eEnd = 0;
		for( int l=line_index-1; l>line_start; l--) {
			if( !Character.isWhitespace(lineCharacters[l]) ) {
				eEnd = l;
				sEmpty = false;
				break;
			}
		}
		
		int g = 0;
		if (sEmpty) {
			element[g] = '0';
			
		} else {
			for( int f=eStart; f<eEnd+1; f++ ) {
				element[g] = lineCharacters[f];
				g++;
			}
		}
		
		String item = new String(element);
		return item;
		
	}
	
	private void setEmployeeID() {
		mEmployeeID = Integer.parseInt(sortCell().trim());
	}

	private void setName() {
		mName = sortCell().trim();
	}

	private void setManagerID() {
		mManagerID = Integer.parseInt(sortCell().trim());
	}
	
	public int getEmployeeID() {
		return mEmployeeID;
	}
	
	public String getName() {
		return mName;
	}
	
	public int getManagerID() {
		return mManagerID;
	}
	
}
