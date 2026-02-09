package xikipedia.panagiotis.wikipedia;

import android.animation.*;
import android.app.*;
import android.app.Activity;
import android.app.DialogFragment;
import android.app.Fragment;
import android.app.FragmentManager;
import android.content.*;
import android.content.res.*;
import android.graphics.*;
import android.graphics.drawable.*;
import android.media.*;
import android.net.*;
import android.os.*;
import android.text.*;
import android.text.style.*;
import android.util.*;
import android.view.*;
import android.view.View;
import android.view.View.*;
import android.view.animation.*;
import android.webkit.*;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.widget.*;
import java.io.*;
import java.text.*;
import java.util.*;
import java.util.regex.*;
import org.json.*;
import xikipedia.panagiotis.wikipedia.databinding.*;

public class MainActivity extends Activity {
	
	private MainBinding binding;
	
	@Override
	protected void onCreate(Bundle _savedInstanceState) {
		super.onCreate(_savedInstanceState);
		binding = MainBinding.inflate(getLayoutInflater());
		setContentView(binding.getRoot());
		initialize(_savedInstanceState);
		initializeLogic();
	}
	
	private void initialize(Bundle _savedInstanceState) {
		
		binding.webview1.setWebViewClient(new WebViewClient() {
			@Override
			public void onPageStarted(WebView _param1, String _param2, Bitmap _param3) {
				final String _url = _param2;
				
				super.onPageStarted(_param1, _param2, _param3);
			}
			
			@Override
			public void onPageFinished(WebView _param1, String _param2) {
				final String _url = _param2;
				
				super.onPageFinished(_param1, _param2);
			}
		});
		
		binding.button3.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View _view) {
				binding.webview1.loadUrl("https://www.wikipedia.org");
			}
		});
		
		binding.button1.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View _view) {
				binding.webview1.loadUrl("https://xikipedia.org");
			}
		});
	}
	
	private void initializeLogic() {
		WebView webview1 = (WebView) findViewById(R.id.webview1);
		webview1.getSettings().setJavaScriptEnabled(true);
		webview1.getSettings().setAllowFileAccess(true);
		webview1.getSettings().setAllowContentAccess(true);
		webview1.getSettings().setDomStorageEnabled(true);
		
		webview1.setDownloadListener(new DownloadListener() {
			@Override
			public void onDownloadStart(
			String url,
			String userAgent,
			String contentDisposition,
			String mimeType,
			long contentLength) {
				
				DownloadManager.Request request =
				new DownloadManager.Request(Uri.parse(url));
				
				String fileName =
				URLUtil.guessFileName(url, contentDisposition, mimeType);
				
				request.setTitle(fileName);
				request.setDescription("Downloading...");
				request.setMimeType(mimeType);
				request.addRequestHeader("User-Agent", userAgent);
				
				request.setNotificationVisibility(
				DownloadManager.Request.VISIBILITY_VISIBLE_NOTIFY_COMPLETED);
				
				request.setDestinationInExternalPublicDir(
				Environment.DIRECTORY_DOWNLOADS,
				fileName
				);
				
				DownloadManager dm =
				(DownloadManager) getSystemService(DOWNLOAD_SERVICE);
				
				dm.enqueue(request);
				
				Toast.makeText(getApplicationContext(),
				"Λήψη αρχείου...", Toast.LENGTH_SHORT).show();
			}
		});
		binding.webview1.loadUrl("https://www.wikipedia.org");
	}
	
}