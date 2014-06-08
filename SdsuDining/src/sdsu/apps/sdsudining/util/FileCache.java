package sdsu.apps.sdsudining.util;

import java.io.File;
import java.net.URLEncoder;

import sdsu.apps.sdsudining.R;

import android.content.Context;

public class FileCache {
	private File cacheDirectory;

	public FileCache(Context context){
		if(android.os.Environment.getExternalStorageState().equals(android.os.Environment.MEDIA_MOUNTED)){
			cacheDirectory = new File(android.os.Environment.getExternalStorageDirectory(), context.getString(R.string.restaurantsImagesFolder));
		}
		else{
			cacheDirectory = context.getCacheDir();
		}

		if(!cacheDirectory.exists()){
			cacheDirectory.mkdirs();
		}
	}

	public File getFile(String filename){
		return new File(cacheDirectory, filename);
	}

	public void clear(){
		File[] files = cacheDirectory.listFiles();
		if(files == null){
			return;
		}
		for(File file: files){
			file.delete();
		}
	}
}
