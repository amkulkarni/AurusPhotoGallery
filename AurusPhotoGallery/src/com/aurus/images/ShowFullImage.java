package com.aurus.images;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;

import com.aurus.photogallery.R;
import com.loopj.android.image.SmartImageView;


public class ShowFullImage extends Activity {

	@Override
	public void onCreate(Bundle savedInstanceState) {
	
		
		super.onCreate(savedInstanceState);
		setContentView(R.layout.showfullimage);

		Bundle bundle = getIntent().getExtras();

		String imageFileName;
        if(bundle.getString("imagefilename")!= null)
        {
        	imageFileName = bundle.getString("imagefilename");
        	
        	Log.e("image file name=", imageFileName);
        	
        	SmartImageView myImage = (SmartImageView) this.findViewById(R.id.my_image);
    		myImage.setImageUrl(imageFileName);
    
        } else {
        	Toast.makeText(this, "No image found", Toast.LENGTH_LONG).show();
        }
        
		
	}
}
