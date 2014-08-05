package com.aurus.images;

import java.io.IOException;
import java.io.InputStream;
import java.net.MalformedURLException;
import java.net.URL;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.os.AsyncTask;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.ListAdapter;

public class ImageAdapter extends BaseAdapter implements ListAdapter {

	private Context context;

	public ImageAdapter(Context context) {
		super();
		this.context = context;
		Log.e("Image counter", "" + getCount());
	}


	/*
	 * Get total Image Count
	 */
	@Override
	public int getCount() {
		return GridViewConfig.getInstagramImageList().size();
	}

	/*
	 * Get Image at position
	 */
	@Override
	public Object getItem(int position) {

		return GridViewConfig.getInstagramImageList().get(position);
	}

	@Override
	public long getItemId(int position) {

		return position;
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		ImageView imageView;
		if (convertView == null) {
			imageView = new ImageView(context);
			imageView.setLayoutParams(new GridView.LayoutParams(100, 100));
			imageView.setScaleType(ImageView.ScaleType.CENTER_CROP);
			imageView.setPadding(5, 5, 5, 5);
		} else {
			imageView = (ImageView) convertView;
		}
		
		DownloadImageTask downloadImageTask = new DownloadImageTask(imageView);
        downloadImageTask.execute(GridViewConfig.getInstagramImageList().get(position));
        
		return imageView;
	}
	
	/*
	 * Download image in  background to avoid blocking of UI
	 */
	public class DownloadImageTask extends AsyncTask<String, Void, Drawable> {

	    private String TAG = DownloadImageTask.class.getSimpleName();
	    private ImageView mImageView;

	    DownloadImageTask(ImageView imageView) {
	        mImageView = imageView;
	    }

	    @Override
	    protected Drawable doInBackground(String... url) {
	        return fetchDrawable(url[0]);
	    }

	    @Override
	    protected void onPostExecute(Drawable result) {
	        if (result != null) {
	            mImageView.setImageDrawable(result);
	        } else {
	            Log.w(TAG, "Could download image!");
	        }
	    }

	    public Drawable fetchDrawable(String url) {
	        Log.v(TAG, "Downloading: " + url);
	        InputStream is;
	        try {
	            is = (InputStream) new URL(url).getContent();
	            return Drawable.createFromStream(is, null);
	        } catch (MalformedURLException e) {
	            Log.e(TAG, e.getMessage(), e);
	        } catch (IOException e) {
	            Log.e(TAG, e.getMessage(), e);
	        }

	        return null;
	    }
	}
}
