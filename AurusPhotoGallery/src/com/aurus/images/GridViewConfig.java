package com.aurus.images;

import java.util.ArrayList;

public class GridViewConfig {
	
	private static ArrayList<String> instagramImageList=new ArrayList<String>();

	/**
	 * @return the instagramImageList
	 */
	public static ArrayList<String> getInstagramImageList() {
		return instagramImageList;
	}



	/**
	 * @param instagramImageList the instagramImageList to set
	 */
	public static void setInstagramImageList(ArrayList<String> instagramImageList) {
		GridViewConfig.instagramImageList = instagramImageList;
	}
	
}
