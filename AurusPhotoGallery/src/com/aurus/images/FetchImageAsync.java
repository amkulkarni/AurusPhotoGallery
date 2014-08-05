package com.aurus.images;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.URL;
import java.util.ArrayList;

import org.json.JSONArray;
import org.json.JSONObject;
import org.json.JSONTokener;

import android.os.AsyncTask;
import android.util.Log;

import com.aurus.instagram.InstagramApp;
import com.aurus.photogallery.Constant;

public class FetchImageAsync extends AsyncTask<String, Void, String> {

	InstagramApp instaObj;
	OnTaskCompleted listener;
	
	ArrayList<String> array_image = new ArrayList<String>();
	
	public FetchImageAsync(InstagramApp instaObj, OnTaskCompleted listener){
        
		// TODO Auto-generated constructor stub
		this.instaObj = instaObj;
		 this.listener=listener;
	}

	@Override
	protected String doInBackground(String... urls) {
		try{ 
			String urlString = Constant.API_URL + "/users/"+ instaObj.getId()  +"/media/recent/?access_token=" + instaObj.getAccessToken() ;
			//String urlString = "https://api.instagram.com/v1/tags/selfie";
			URL url = new URL(urlString);

			InputStream inputStream = url.openConnection().getInputStream();
			String response = streamToString(inputStream);
			
			JSONObject jsonObject = (JSONObject) new JSONTokener(response).nextValue();
			JSONArray jsonArray = jsonObject.getJSONArray("data");

			
			//Use for loop to traverse through the JsonArray.
			for(int index=0;index<jsonArray.length();index++) {
				JSONObject mainImageJsonObject = jsonArray.getJSONObject(index).getJSONObject("images").getJSONObject("thumbnail");
				String imageUrlString = mainImageJsonObject.getString("url");
				array_image.add(imageUrlString);
			}
		}
		catch (Exception e) {
			Log.e("Exception==",e.getMessage());
		}
		return null;
	}

	@Override
	protected void onPostExecute(String result) {
		listener.onTaskCompleted(array_image);
	}
	
	
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
	   
	   public interface OnTaskCompleted{
		   void onTaskCompleted(ArrayList<String> array_image);	
	   }
}
