package Ninja.coder.Ghostemane.code;

import android.graphics.Bitmap;
import android.view.View;
import androidx.appcompat.widget.Toolbar;
import com.google.android.material.appbar.AppBarLayout;
import androidx.coordinatorlayout.widget.CoordinatorLayout;
import android.webkit.WebView;
import android.os.Bundle;
import android.webkit.WebChromeClient;
import android.webkit.WebViewClient;
import java.io.File;
import android.content.Intent;
import android.widget.Toast;

public class PhpActivity extends BaseCompat {

  private Toolbar _toolbar;
  private AppBarLayout _app_bar;
  private CoordinatorLayout _coordinator;

  private WebView web;

  @Override
  protected void onCreate(Bundle _savedInstanceState) {
    super.onCreate(_savedInstanceState);
    setContentView(R.layout.php);
    initialize(_savedInstanceState);
    initializeLogic();
  }

  private void initialize(Bundle _savedInstanceState) {
    _app_bar = findViewById(R.id._app_bar);
    _coordinator = findViewById(R.id._coordinator);
    _toolbar = findViewById(R.id._toolbar);
    setSupportActionBar(_toolbar);
    getSupportActionBar().setDisplayHomeAsUpEnabled(true);
    getSupportActionBar().setHomeButtonEnabled(true);
    _toolbar.setNavigationOnClickListener(
        new View.OnClickListener() {
          @Override
          public void onClick(View _v) {
            onBackPressed();
          }
        });
    web = findViewById(R.id.web);
    web.getSettings().setJavaScriptEnabled(true);
    web.getSettings().setSupportZoom(true);
   // startService(new Intent(this, PHPProcess.class));

    // no listener code

    // no listener code

    // webviewOnProgressChanged
    web.setWebChromeClient(
        new WebChromeClient() {
          @Override
          public void onProgressChanged(WebView view, int _newProgress) {}
        });

    web.setWebViewClient(
        new WebViewClient() {
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
  }

  private void initializeLogic() {
    File file = new File(getIntent().getStringExtra("phpcode"));
    if (getIntent().hasExtra("phpcode")) {
      PhpRun run = new PhpRun();
      String path = run.runOffline(this, file);
      web.loadUrl(path);
    }
    Intent intent = new Intent(this, PHPProcess.class);
    intent.putExtra("port", 8080);
    intent.putExtra("projectPath", file.getAbsolutePath());
    startService(intent);

    web.getSettings().setJavaScriptCanOpenWindowsAutomatically(true);
    web.getSettings().setJavaScriptEnabled(true);
    web.getSettings().setUseWideViewPort(true);
    ColorAndroid12.setToolbarinit(_toolbar);
  }

  @Deprecated
  public void showMessage(String _s) {
    Toast.makeText(getApplicationContext(), _s, Toast.LENGTH_SHORT).show();
  }
}
