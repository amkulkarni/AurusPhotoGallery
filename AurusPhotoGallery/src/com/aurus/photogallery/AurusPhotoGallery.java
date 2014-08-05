package com.aurus.photogallery;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.Toast;

import com.aurus.images.FetchImageAsync;
import com.aurus.images.FetchImageAsync.OnTaskCompleted;
import com.aurus.images.GridViewConfig;
import com.aurus.images.ImageGridView;
import com.aurus.instagram.InstagramApp;
import com.aurus.instagram.InstagramApp.OAuthAuthenticationListener;

public class AurusPhotoGallery extends Activity implements OnTaskCompleted {

	Context mContext;
	private InstagramApp instaObj;

	public static final String CLIENT_ID = "dac371f9d49a4314bcb73def9f295a30";
	public static final String CLIENT_SECRET = "721b6e4c567c4cbf9266ffa43021b5ec";
	public static final String CALLBACK_URL = "https://uat4.whizpay.net/";

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_aurusphotogallery);

		mContext = this;
		
		addListenerOnButton();
		
		// Instagram Implementation
		instaObj = new InstagramApp(this, CLIENT_ID, CLIENT_SECRET,
				CALLBACK_URL);
		instaObj.setListener(oathListener);

	}

	public void addListenerOnButton() {
		
		Button btn_userAuth = (Button) findViewById(R.id.btn1);
		Button btn_loadImages = (Button) findViewById(R.id.btn2);
		
		btn_userAuth.setOnClickListener(new OnClickListener() {
			 
			@Override
			public void onClick(View arg0) {
				//Toast.makeText(mContext, "button click 1", Toast.LENGTH_LONG).show();

				// add this in your button click or wherever you need to call the
				// instagram api
				instaObj.authorize();
			}
		});
		
		
		btn_loadImages.setOnClickListener(new OnClickListener() {
			 
			@Override
			public void onClick(View arg0) {	
				
				if(instaObj.getUserName()!=null && instaObj.getId()!=null) {
					//Toast.makeText(mContext, "button click 2", Toast.LENGTH_LONG).show();
					FetchImageAsync imgAsync = new FetchImageAsync(instaObj, AurusPhotoGallery.this);
					imgAsync.execute();
				} else {
					Toast.makeText(mContext, "User authentication is required....", Toast.LENGTH_LONG).show();
				}
			}
		});
		
	}

	OAuthAuthenticationListener oathListener = new OAuthAuthenticationListener() {

		@Override
		public void onSuccess() {

			Log.e("Userid", instaObj.getId());
			Log.e("Name", instaObj.getName());
			Log.e("UserName", instaObj.getUserName());
		}

		@Override
		public void onFail(String error) {
			Toast.makeText(AurusPhotoGallery.this, error, Toast.LENGTH_SHORT)
					.show();
		}
	};

	/**
	    * Method that returns String from the InputStream given by p_is 
	    * @param p_is The given InputStream
	    * @return The String from the InputStream
	    */
	   public String streamToString(InputStream p_is)
	   {
	        try
	        {
	             BufferedReader m_br;
	                  StringBuffer m_outString = new StringBuffer();
	                  m_br = new BufferedReader(new InputStreamReader(p_is));
	              String m_read = m_br.readLine();
	              while(m_read != null)
	              {
	                    m_outString.append(m_read);
	                    m_read =m_br.readLine();
	              }
	             return m_outString.toString();
	         }
	       catch (Exception p_ex)
	         {
	          Log.e("exception=",""+p_ex.getMessage());
	          return "";
	         }
	   }

	
	@Override
	public void onTaskCompleted(ArrayList<String> array_image) {
		// TODO Auto-generated method stub
		
		Log.e("process complete...",  "add here");
		
		//Use for loop to traverse through the JsonArray.
		for(int index=0;index<array_image.size();index++) {
			Log.e( "image string=",array_image.get(index));
		}
		
		GridViewConfig.setInstagramImageList(array_image);
		  
		Intent intent = new Intent(AurusPhotoGallery.this, ImageGridView.class);
        startActivity(intent);      
  
	}
	
}
