package Ninja.coder.Ghostemane.code;

import Ninja.coder.Ghostemane.code.layoutmanager.WebViewCompatGhostWeb;
import android.content.res.ColorStateList;
import android.graphics.Bitmap;
import android.graphics.Typeface;
import android.view.LayoutInflater;
import android.view.WindowManager;
import android.webkit.ConsoleMessage;
import android.webkit.JavascriptInterface;
import android.webkit.JsResult;
import android.webkit.WebView;
import android.widget.LinearLayout;
import android.widget.TextView;
import com.google.android.material.appbar.AppBarLayout;
import androidx.coordinatorlayout.widget.CoordinatorLayout;
import com.google.android.material.bottomsheet.BottomSheetDialog;
import com.google.android.material.color.MaterialColors;
import com.google.android.material.floatingactionbutton.ExtendedFloatingActionButton;
import com.google.android.material.progressindicator.LinearProgressIndicator;

import android.content.SharedPreferences;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.app.Activity;
import android.webkit.WebChromeClient;
import android.webkit.WebViewClient;
import com.google.android.material.dialog.MaterialAlertDialogBuilder;
import android.net.Uri;
import androidx.appcompat.app.AlertDialog;
import android.view.View;
import android.animation.ObjectAnimator;
import androidx.webkit.WebSettingsCompat;
import android.widget.Toast;
import android.view.Gravity;
import android.widget.Button;
import android.os.Build;
import android.content.DialogInterface;
import android.widget.EditText;
import android.text.TextWatcher;
import com.google.android.material.appbar.MaterialToolbar;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import io.github.rosemoe.sora.langs.loglang.LogLang;
import io.github.rosemoe.sora.widget.CodeEditor;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.HashMap;

public class HtmlrunerActivity extends BaseCompat {
  protected ArrayList<HashMap<String, Object>> listLogJs = new ArrayList<>();
  protected ServerHost host;
  private MaterialToolbar _toolbar;
  private AppBarLayout _app_bar;
  private CoordinatorLayout _coordinator;
  private ExtendedFloatingActionButton _fab;
  private boolean boolean10 = false;
  private String param = "";
  private String Doc = "";
  private String Doc2 = "";
  private PraramnetLayoutNinja linear1;
  private LinearProgressIndicator progressbar1;
  private WebViewCompatGhostWeb web;
  private SharedPreferences qo;
  private Intent send = new Intent();
  private boolean isInspectEnabled = false;
  private boolean isShowingDialog = false;

  @Override
  protected void onCreate(Bundle _savedInstanceState) {
    super.onCreate(_savedInstanceState);
    setContentView(R.layout.htmlruner);
    findAndMatchIdInview();
    installToRun();
  }

  private void findAndMatchIdInview() {
    _app_bar = findViewById(R.id._app_bar);
    _coordinator = findViewById(R.id._coordinator);
    _toolbar = findViewById(R.id._toolbar);
    setSupportActionBar(_toolbar);

    getSupportActionBar().setDisplayHomeAsUpEnabled(true);
    getSupportActionBar().setHomeButtonEnabled(true);
    _toolbar.setNavigationOnClickListener(
        (v) -> {
          finish();
        });
    _fab = findViewById(R.id._fab);

    linear1 = findViewById(R.id.linear1);
    progressbar1 = findViewById(R.id.progressbar1);
    web = findViewById(R.id.web);
    qo = getSharedPreferences("qo", Activity.MODE_PRIVATE);
    
    web.setWebChromeClient(
        new WebChromeClient() {
          @Override
          public void onProgressChanged(WebView view, int progresss) {
            progressbar1.setVisibility(progresss == 100 ? View.GONE : View.VISIBLE);
            progressbar1.setProgressCompat(progresss, true);
          }

          @Override
          public boolean onJsTimeout() {
            return super.onJsTimeout();
          }

          @Override
          public boolean onJsBeforeUnload(
              WebView arg0, String arg1, String arg2, JsResult jsresult) {
            var dialog = new MaterialAlertDialogBuilder(HtmlrunerActivity.this);
            dialog.setTitle("Exit");
            dialog.setMessage("Confrim to exit ?");
            dialog.setPositiveButton(
                android.R.string.ok,
                (c, cs) -> {
                  jsresult.confirm();
                });
            dialog.setNegativeButton(
                android.R.string.cancel,
                (_gg, r) -> {
                  jsresult.cancel();
                });
            dialog.show();
            return true;
          }

          @Override
          public void onCloseWindow(WebView arg0) {
            super.onCloseWindow(arg0);
          }
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
            reloadConsoleJs();
            setTitle(web.getTitle() == "about:blank" ? "Error" : web.getTitle());

            getSupportActionBar()
                .setSubtitle(web.getUrl() == "about:blank" ? "Preview" : web.getUrl());
            if (isInspectEnabled) {
              startInspectMode(web);
            }
            progressbar1.setVisibility(View.GONE);
            web.addJavascriptInterface(
                new Object() {

                  @JavascriptInterface
                  public void setInspectEnabled(boolean enabled) {
                    isInspectEnabled = enabled;
                    runOnUiThread(
                        new Runnable() {
                          @Override
                          public void run() {
                            if (enabled) {
                              startInspectMode(web);
                            }
                          }
                        });
                  }

                  @JavascriptInterface
                  public void onElementClicked(String html) {

                    runOnUiThread(
                        new Runnable() {
                          @Override
                          public void run() {
                            if (!isShowingDialog) {
                              isShowingDialog = true;
                              MaterialAlertDialogBuilder dialog =
                                  new MaterialAlertDialogBuilder(HtmlrunerActivity.this);
                              dialog.setTitle("inspect");
                              // dialog.setMessage(html);
                              var tvs = new TextView(HtmlrunerActivity.this);
                              LinearLayout.LayoutParams layout =
                                  new LinearLayout.LayoutParams(
                                      LinearLayout.LayoutParams.MATCH_PARENT,
                                      LinearLayout.LayoutParams.MATCH_PARENT);
                              layout.leftMargin = 3;
                              layout.rightMargin = 3;
                              // tvs.setGravity(Gravity.CENTER_HORIZONTAL |
                              // Gravity.CENTER_HORIZONTAL);
                              tvs.setLayoutParams(layout);
                              tvs.setTextIsSelectable(true);
                              tvs.setText(html);
                              tvs.setTextColor(
                                  ColorStateList.valueOf(
                                      MaterialColors.getColor(tvs, ColorAndroid12.TvColor, 0)));
                              tvs.setPadding(8, 8, 8, 8);
                              dialog.setCancelable(false);
                              dialog.setView(tvs);
                              dialog.setPositiveButton(
                                  android.R.string.ok,
                                  new DialogInterface.OnClickListener() {
                                    @Override
                                    public void onClick(DialogInterface dialog, int id) {
                                      isShowingDialog = false;
                                    }
                                  });
                              dialog.show();
                            }
                          }
                        });
                  }
                },
                "android");
            super.onPageFinished(_param1, _param2);
          }
        });

    _toolbar.setOverflowIcon(getDrawable(R.drawable.border_color));
    _fab.setIconResource(R.drawable.cog);

    _fab.setOnClickListener(
        vv -> {
          var di = new MaterialAlertDialogBuilder(HtmlrunerActivity.this);

          di.setTitle("Choose your appreciable");
          di.setMessage("Please select one of the following options");
          di.setPositiveButton(
              "Search",
              (p1, d2) -> {
                _searchData();
              });
          di.setNeutralButton(
              "host",
              (p, d) -> {
                String path = getIntent().getStringExtra("run");
                host =
                    new ServerHost(
                        8080,
                        (String) path.subSequence(0, path.lastIndexOf("/")),
                        path.substring(path.lastIndexOf("/") + 1));
                host.startServer();
                String initialUrl = host.getLocalIpAddress();
                Intent openInBrowser = new Intent(Intent.ACTION_VIEW, Uri.parse(initialUrl));
                startActivity(openInBrowser);
              });
          AlertDialog dialog = di.show();
          final View view = dialog.getWindow().getDecorView();
          view.setScaleX(0f);
          view.setScaleY(0f);
          dialog.getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_VISIBLE);
          final ObjectAnimator alertAnim = new ObjectAnimator();
          final ObjectAnimator alertAnim1 = new ObjectAnimator();
          alertAnim.setTarget(view);
          alertAnim.setPropertyName("scaleX");
          alertAnim.setFloatValues((float) (1));
          alertAnim.setDuration((250));
          alertAnim.start();
          alertAnim1.setTarget(view);
          alertAnim1.setPropertyName("scaleY");
          alertAnim1.setFloatValues((float) (1));
          alertAnim1.setDuration((250));
          alertAnim1.start();
          dialog.show();
        });
  }

  private void installToRun() {
    ColorAndroid12.setFab(_fab);
    //   web.setCustomDialog();
    _toolbar.setNavigationIcon(R.drawable.arrow_back);
    initWebView();
    ColorAndroid12.setToolbarinit(_toolbar);
    if (getIntent().hasExtra("run")) {
      web.loadUrl("file:///".concat(getIntent().getStringExtra("run")));
    }
  }

  @Override
  public boolean onCreateOptionsMenu(Menu menu) {
    getMenuInflater().inflate(R.menu.webview_menu, menu);

    return super.onCreateOptionsMenu(menu);
  }

  @Override
  public boolean onPrepareOptionsMenu(Menu menu) {
    menu.findItem(R.id.drakmod).setVisible(Build.VERSION.SDK_INT >= 29 ? true : false);
    return super.onPrepareOptionsMenu(menu);
  }

  @Override
  public boolean onOptionsItemSelected(MenuItem item) {
    int id = item.getItemId();

    if (id == R.id.back) {
      if (web.canGoBack()) {
        web.goBack();
      } else {
        Toasts("Can't go back...");
      }
    } else if (id == R.id.forward) {
      if (web.canGoForward()) {
        web.goForward();
      } else {
        Toasts("Can't go Forward");
      }
    } else if (id == R.id.zooming) {
      web.getSettings().setSupportZoom(!item.isChecked());
      web.getSettings().setBuiltInZoomControls(!item.isChecked());
      item.setChecked(!item.isChecked());
    } else if (id == R.id.desktop_mode) {
      setDesktopMode(!item.isChecked());
      item.setChecked(!item.isChecked());
    } else if (id == R.id.refresh) {
      web.reload();
    } else if (id == R.id.reloader) {
      reloadConsoleJs();
    } else if (id == R.id.drakmod) {
      web.getSettings().setAlgorithmicDarkeningAllowed(!item.isChecked());
      item.setChecked(!item.isChecked());
      WebSettingsCompat.setForceDark(
          web.getSettings(),
          item.isChecked() ? WebSettingsCompat.FORCE_DARK_ON : WebSettingsCompat.FORCE_DARK_OFF);
    } else if (id == R.id.exits) {
      finish();
    } else if (id == R.id.injector) {
      web.reload();
      if (!item.isChecked()) {
        isInspectEnabled = true;
      } else {
        isInspectEnabled = false;
      }
      item.setChecked(!item.isChecked());
    } else if (id == R.id.consolefromsheet) {
      JsConsoleBeta();
    }
    return true;
  }

  private void Toasts(String t) {

    Toast toast = Toast.makeText(getApplicationContext(), t, 2);
    toast.setDuration(1000);
    toast.setGravity(Gravity.BOTTOM, Gravity.CENTER_HORIZONTAL, Gravity.BOTTOM);
    toast.show();
  }

  public void setDesktopMode(boolean _isChecked) {
    web.loadUrl(
        "javascript:!function(e){var t,n,o;window.innerWidth>=window.innerHeight||(t=1024/innerWidth,(n=document.querySelector(\"meta[name=viewport]\"))||((n=document.createElement(\"meta\")).name=\"viewport\",document.head.appenChild(n)),e?(o=window.innerHeight*t,sessionStorage.setItem(\"__old_viewport_content\",n.content),n.content=\"width=1024, height=\"+o):(o=sessionStorage.__old_viewport_content)&&(n.content=o))}("
            + _isChecked
            + ");");
    var webSettings = web.getSettings();
    webSettings.setUseWideViewPort(_isChecked);
    webSettings.setLoadWithOverviewMode(_isChecked);
    webSettings.setSupportZoom(_isChecked);
    webSettings.setBuiltInZoomControls(_isChecked);
  }

  public void _searchData() {
    AlertDialog dialog =
        new GhostWebMaterialDialog(HtmlrunerActivity.this)
            .setView(R.layout.webview_search)
            .setTitle("Search KeyWord")
            .setCancelable(false)
            .setPositiveButton("Search Text", null)
            .setNegativeButton(android.R.string.cancel, null)
            .create();
    dialog.setOnShowListener(
        (var) -> {
          Button positive = dialog.getButton(DialogInterface.BUTTON_POSITIVE);

          EditText editor = dialog.findViewById(R.id.editor);
          if (editor.getText().toString().isEmpty()) {
            positive.setEnabled(false);
          } else {
            positive.setEnabled(true);
          }

          editor.addTextChangedListener(
              new TextWatcher() {
                @Override
                public void onTextChanged(
                    CharSequence _param1, int _param2, int _param3, int _param4) {
                  final String _charSeq = _param1.toString();

                  if (editor.getText().toString().isEmpty()) {
                    positive.setEnabled(false);
                  } else {
                    positive.setEnabled(true);
                  }
                }

                @Override
                public void beforeTextChanged(
                    CharSequence _param1, int _param2, int _param3, int _param4) {}

                @Override
                public void afterTextChanged(android.text.Editable _param1) {}
              });
          positive.setOnClickListener(
              (vftrororocjj) -> {
                web.findAll(editor.getText().toString());
                if (editor.getText().toString().isEmpty()) {
                  positive.setEnabled(false);
                } else {
                  positive.setEnabled(true);
                }
                dialog.dismiss();
              });
        });
    final View view = dialog.getWindow().getDecorView();
    dialog.getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_VISIBLE);
    view.setScaleX(0f);
    view.setScaleY(0f);
    final ObjectAnimator alertAnim = new ObjectAnimator();
    final ObjectAnimator alertAnim1 = new ObjectAnimator();
    alertAnim.setTarget(view);
    alertAnim.setPropertyName("scaleX");
    alertAnim.setFloatValues((float) (1));
    alertAnim.setDuration((250));
    alertAnim.start();
    alertAnim1.setTarget(view);
    alertAnim1.setPropertyName("scaleY");
    alertAnim1.setFloatValues((float) (1));
    alertAnim1.setDuration(250);
    alertAnim1.start();
    dialog.show();
  }

  public void showMessage(String _s) {
    Toast.makeText(getApplicationContext(), _s, Toast.LENGTH_SHORT).show();
  }

  public void reloadConsoleJs() {
    String erudaPath = "file:///android_asset/eruda.js";
    String js =
        "(function(){"
            + "var script = document.createElement('script');"
            + "script.src = '"
            + erudaPath
            + "';"
            + "document.body.appendChild(script);"
            + "script.onload = function () { eruda.add({name: 'My Theme', init: function () { var bgColor = '#333'; var textColor = '#fff'; document.querySelector('.eruda-container').style.backgroundColor = bgColor; var consoleEls = document.querySelectorAll('.eruda-console-item, .eruda-header'); for (var i = 0; i < consoleEls.length; i++) { consoleEls[i].style.color = textColor; } }}); eruda.init(); }"
            + "})();";
    web.post(() -> web.loadUrl("javascript:" + js));
  }

  public void initWebView() {
    web.getSettings().setAllowContentAccess(true);
    web.getSettings().setAllowFileAccess(true);
    web.getSettings().setJavaScriptEnabled(true);
    web.getSettings().setJavaScriptCanOpenWindowsAutomatically(true);
    web.getSettings().setSupportZoom(true);
    //    web.getSettings().setBuiltInZoomControls(true);

    WebSettingsCompat.setAlgorithmicDarkeningAllowed(web.getSettings(), true);
  }

  public void startInspectMode(WebView _webview) {
    _webview.loadUrl(
        "javascript:(function() { "
            + "function addClickListener(element) {"
            + "    element.addEventListener('click', function() {"
            + "        window.android.onElementClicked(element.outerHTML);"
            + "    });"
            + "}"
            + "let elements = document.getElementsByTagName('*');"
            + "for (let i = 0; i < elements.length; i++) {"
            + "    addClickListener(elements[i]);"
            + "}"
            + "})();");
  }

  public void JsConsoleBeta() {
    BottomSheetDialog sheet = new BottomSheetDialog(this);
    var view = LayoutInflater.from(this).inflate(R.layout.z_layout_rv, null, false);
    CodeEditor editor = view.findViewById(R.id.rv_layout);
    HashMap<String, Object> imap = new HashMap<>();
    imap =
        new Gson()
            .fromJson(
                FileUtil.readFile("storage/emulated/0/GhostWebIDE/theme/GhostThemeapp.ghost"),
                new TypeToken<HashMap<String, Object>>() {}.getType());
    SetThemeForJson themeForJson2 = new SetThemeForJson();
    themeForJson2.setThemeCodeEditor(editor, imap, false, this);
    sheet.setContentView(view);
    sheet.show();
    web.reload();
    try {
      web.setWebChromeClient(
          new WebChromeClient() {
            @Override
            public boolean onConsoleMessage(ConsoleMessage consoleMessage) {

              String str = "";
              String level = "";
              String src = consoleMessage.sourceId();

              switch (consoleMessage.messageLevel()) {
                case DEBUG:
                  level = "DEBUG";
                  break;
                case ERROR:
                  level = "ERROR";
                  break;
                case LOG:
                  level = "LOG";
                  break;
                case TIP:
                  level = "TIP";
                  break;
                case WARNING:
                  level = "WARNING";
                  break;
              }
              DateTimeFormatter dtf = DateTimeFormatter.ofPattern("HH:mm:ss");
              String time = dtf.format(LocalDateTime.now());

              str += level + " -> " + consoleMessage.message() + "(" + time + ")" + "\n";

              editor.setSelection(
                  editor.getText().getLineCount() - 1,
                  editor.getText().getColumnCount(editor.getText().getLineCount() - 1));
              // editor.setColorScheme(new SchemeDarcula());
              editor.setLineNumberEnabled(false);
              editor.setEditable(false);
              editor.insertText(str, 1);
              editor.setPadding(8, 8, 8, 8);
              editor.setEditorLanguage(new LogLang());
              editor.setTypefaceText(Typeface.createFromAsset(getAssets(), "fonts/ghostfont.ttf"));
              return super.onConsoleMessage(consoleMessage);
            }
          });

    } catch (Exception err) {

    }
  }
}
