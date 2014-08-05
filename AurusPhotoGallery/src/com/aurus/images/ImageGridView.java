package com.aurus.images;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.GridView;
import android.widget.Toast;

import com.aurus.photogallery.R;


public class ImageGridView extends Activity {
	
	private GridView girGridView;
	
	
	@Override
	public void onCreate(Bundle savedInstanceState) {
	
		
		super.onCreate(savedInstanceState);
		setContentView(R.layout.gridview);

		
		girGridView=(GridView) findViewById(R.id.gridView1_bir);
	
		girGridView.setAdapter(new ImageAdapter(this));
	    girGridView.setOnItemClickListener(new AdapterView.OnItemClickListener() {

				@Override
				public void onItemClick(AdapterView<?> arg0, View view, int position,long arg3) {
					Toast.makeText(getApplicationContext(), GridViewConfig.getInstagramImageList().get(position), Toast.LENGTH_SHORT).show();
					
					Intent intent = new Intent(ImageGridView.this, ShowFullImage.class);
					intent.putExtra("imagefilename", GridViewConfig.getInstagramImageList().get(position));
			        startActivity(intent);   
					
				}
			});
	    }
}
