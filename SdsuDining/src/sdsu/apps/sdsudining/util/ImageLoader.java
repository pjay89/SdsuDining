package sdsu.apps.sdsudining.util;

import java.util.Collections;
import java.util.Map;
import java.util.WeakHashMap;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import sdsu.apps.sdsudining.R;

import android.content.Context;
import android.widget.ImageView;

public class ImageLoader {

	private MemoryCache memoryCache = new MemoryCache();
	private FileCache fileCache;
	private Map<ImageView, String> imageViews = Collections.synchronizedMap(new WeakHashMap<ImageView, String>());
	private ExecutorService executorService;
	
	public ImageLoader(Context context){
		fileCache = new FileCache(context);
		executorService = Executors.newFixedThreadPool(5);
	}
	
	
	
}
