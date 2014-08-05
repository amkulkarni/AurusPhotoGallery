package com.aurus.photogallery;

public class Constant {
	
	public static final String CLIENT_ID = "dac371f9d49a4314bcb73def9f295a30";
	public static final String CLIENT_SECRET = "721b6e4c567c4cbf9266ffa43021b5ec";
	
	//URL for authentication 
	public static final String AUTH_URL = "https://api.instagram.com/oauth/authorize/";
			
	// URL for getting the token and user details
	public static final String TOKEN_URL = "https://api.instagram.com/oauth/access_token";
	
	//API version which we are going to hit whenever we request for some information from Instagram
	public static final String API_URL = "https://api.instagram.com/v1";
	
	//URL is what registered as a Redirect UR
	public static String REDIRECT_URL = "https://uat4.whizpay.net/";
	
}
