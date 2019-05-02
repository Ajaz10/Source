package im.delight.android.examples.webview;

import im.delight.android.webview.AdvancedWebView;

import android.app.DownloadManager;
import android.net.Uri;
import android.os.Environment;
import android.webkit.WebChromeClient;
import android.widget.Toast;
import android.webkit.WebView;
import android.view.View;
import android.webkit.WebViewClient;
import android.graphics.Bitmap;
import android.content.Intent;
import android.annotation.SuppressLint;
import android.os.Bundle;
import android.app.Activity;

public class MainActivity extends Activity implements AdvancedWebView.Listener {

	private static final String TEST_PAGE_URL = "https://console.firebase.google.com/project/trios-f8038/storage/trios-f8038.appspot.com/files";
	private AdvancedWebView mWebView;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);

		mWebView = (AdvancedWebView) findViewById(R.id.webview);
		mWebView.setListener(this, this);
		mWebView.setGeolocationEnabled(false);
		mWebView.setMixedContentAllowed(true);
		mWebView.setCookiesEnabled(true);
		mWebView.setThirdPartyCookiesEnabled(true);
		mWebView.setWebViewClient(new WebViewClient() {

			@Override
			public void onPageFinished(WebView view, String url) {
				Toast.makeText(MainActivity.this, "Finished loading", Toast.LENGTH_SHORT).show();
			}

		});
		mWebView.setWebChromeClient(new WebChromeClient() {

			@Override
			public void onReceivedTitle(WebView view, String title) {
				super.onReceivedTitle(view, title);
				Toast.makeText(MainActivity.this, title, Toast.LENGTH_SHORT).show();
			}

		});
		mWebView.addHttpHeader("X-Requested-With", "");
		mWebView.loadUrl(TEST_PAGE_URL);
	}
	/*mWebView.setDownloadListener(new DownloadListener() {

		@Override
		public void onDownloadStart(String url, String userAgent,
				String contentDisposition, String mimetype,
		long contentLength) {
			DownloadManager.Request request = new DownloadManager.Request(
					Uri.parse(url));

			request.allowScanningByMediaScanner();
			request.setNotificationVisibility(DownloadManager.Request.VISIBILITY_VISIBLE_NOTIFY_COMPLETED); //Notify client once download is completed!
			request.setDestinationInExternalPublicDir(Environment.DIRECTORY_DOWNLOADS, "Name of your downloadble file goes here, example: Mathematics II ");
			DownloadManager dm = (DownloadManager) getSystemService(DOWNLOAD_SERVICE);
			dm.enqueue(request);
			Toast.makeText(getApplicationContext(), "Downloading File", //To notify the Client that the file is being downloaded
					Toast.LENGTH_LONG).show();

		}
	});
	//az
*/
	@SuppressLint("NewApi")
	@Override
	protected void onResume() {
		super.onResume();
		mWebView.onResume();
		// ...
	}

	@SuppressLint("NewApi")
	@Override
	protected void onPause() {
		mWebView.onPause();
		// ...
		super.onPause();
	}

	@Override
	protected void onDestroy() {
		mWebView.onDestroy();
		// ...
		super.onDestroy();
	}

	@Override
	protected void onActivityResult(int requestCode, int resultCode, Intent intent) {
		super.onActivityResult(requestCode, resultCode, intent);
		mWebView.onActivityResult(requestCode, resultCode, intent);
		// ...
	}

	@Override
	public void onBackPressed() {
		if (!mWebView.onBackPressed()) { return; }
		// ...
		super.onBackPressed();
	}

	@Override
	public void onPageStarted(String url, Bitmap favicon) {
		mWebView.setVisibility(View.INVISIBLE);
	}

	@Override
	public void onPageFinished(String url) {
		mWebView.setVisibility(View.VISIBLE);
	}

	@Override
	public void onPageError(int errorCode, String description, String failingUrl) {
		Toast.makeText(MainActivity.this, "onPageError(errorCode = "+errorCode+",  description = "+description+",  failingUrl = "+failingUrl+")", Toast.LENGTH_SHORT).show();
	}

	@Override
	public void onDownloadRequested(String url, String suggestedFilename, String mimeType, long contentLength, String contentDisposition, String userAgent) {
		Toast.makeText(MainActivity.this, "onDownloadRequested(url = "+url+",  suggestedFilename = "+suggestedFilename+",  mimeType = "+mimeType+",  contentLength = "+contentLength+",  contentDisposition = "+contentDisposition+",  userAgent = "+userAgent+")", Toast.LENGTH_LONG).show();

		if (AdvancedWebView.handleDownload(this, url, suggestedFilename)) {
			// download successfully handled
		}
		else {
			// download couldn't be handled because user has disabled download manager app on the device
		}
	}

	//az



	@Override
	public void onExternalPageRequest(String url) {
		Toast.makeText(MainActivity.this, "onExternalPageRequest(url = "+url+")", Toast.LENGTH_SHORT).show();
	}

}
