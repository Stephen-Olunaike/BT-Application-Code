package com.stephen.bt;

import java.util.ArrayList;


public class OrgChartUtil {

	private ArrayList<OrgChartElement> allElements = new ArrayList<OrgChartElement>();
	private int indexX, indexY;
	private int curr_mID;
	private ArrayList<Integer> upFromX_eID = new ArrayList<Integer>();
	private ArrayList<Integer> upFromY_eID = new ArrayList<Integer>();
	
	private boolean sAboveX = false, sAboveY = false;
	
	public OrgChartUtil(ArrayList<OrgChartElement> elements, String x, String y) {
		this.allElements.addAll(elements);

		findElementsIndex(x, y);
	}
	
	public void showChartTraversal(){
		traverseChart();
		
		int index = 0;
		
		if (sAboveX) {
			for(int a=0; a<upFromX_eID.size(); a++) {
				index = findIndex(upFromX_eID.get(a));
				
				if(a != 0){
					System.out.print(" -> ");
				}
				
				System.out.print(allElements.get(index).getName());
				System.out.print(" (" + allElements.get(index).getEmployeeID() + ")");
			}
			
		} else if (sAboveY) {
			for(int a=upFromY_eID.size()-1; a>-1; a--) {
				index = findIndex(upFromY_eID.get(a));
				
				if(a != upFromY_eID.size()-1){
					System.out.print(" <- ");
				}
				
				System.out.print(allElements.get(index).getName());
				System.out.print(" (" + allElements.get(index).getEmployeeID() + ")");
			}
			
		} else {
			for(int a=0; a<upFromX_eID.size(); a++) {
				index = findIndex(upFromX_eID.get(a));
				
				if(a != 0){
					System.out.print(" -> ");
				}
				
				System.out.print(allElements.get(index).getName());
				System.out.print(" (" + allElements.get(index).getEmployeeID() + ")");
			}
			for(int a=upFromY_eID.size()-2; a>-1; a--) {
				index = findIndex(upFromY_eID.get(a));
				
				System.out.print(" <- ");
				System.out.print(allElements.get(index).getName());
				System.out.print(" (" + allElements.get(index).getEmployeeID() + ")");
			}
			
		}
		
		System.out.println();
	}
	
	private int findIndex(int p) {		
		for(int j=0; j<allElements.size(); j++) {
			if(allElements.get(j).getEmployeeID() == p) return j;
		}
		
		return -1;
	}
	
	private void traverseChart() {
		
		upFromX_eID.add(allElements.get(indexX).getEmployeeID());
		
		curr_mID = allElements.get(indexX).getManagerID();
		
		while( upFromX_eID.lastIndexOf(allElements.get(0).getEmployeeID()) == -1 ) {
			upFromX_eID.addAll(goUpTheChart());
			if( upFromX_eID.lastIndexOf(allElements.get(indexY).getEmployeeID()) != -1){
				sAboveX = true;
				break;
			}
		}
		
		upFromY_eID.add(allElements.get(indexY).getEmployeeID());
		
		curr_mID = allElements.get(indexY).getManagerID();
		
		while( upFromX_eID.indexOf(upFromY_eID.get(upFromY_eID.size()-1)) == -1 ) {
			upFromY_eID.addAll(goUpTheChart());
			if( upFromY_eID.lastIndexOf(allElements.get(indexX).getEmployeeID()) != -1){
				sAboveY = true;
				break;
			}
		}
	}
	
	private ArrayList<Integer> goUpTheChart() {
		ArrayList<Integer> employees = new ArrayList<Integer>();
		
		for(int o=0; o<allElements.size(); o++) {
			if ( allElements.get(o).getEmployeeID() == curr_mID ) {
				curr_mID = allElements.get(o).getManagerID();
				employees.add(allElements.get(o).getEmployeeID());
				
			}
		}
		
		return employees;
	}
	
	private void findElementsIndex(String name_x, String name_y) {		
		for (int g=0; g<allElements.size(); g++) {
			String s = allElements.get(g).getName();
			
			if ( s.equals(name_x) ) {
				indexX = g;
			}
			
			if ( s.equals(name_y) ) {
				indexY = g;
			}
		}
	}
}
