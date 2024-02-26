package Ninja.coder.Ghostemane.code;

import android.animation.*;
import android.app.*;
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
import android.widget.*;
import android.widget.LinearLayout;
import android.widget.TextView;
import androidx.activity.*;
import androidx.annotation.*;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.coordinatorlayout.widget.CoordinatorLayout;
import androidx.fragment.app.DialogFragment;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.recyclerview.*;
import androidx.recyclerview.selection.*;
import androidx.webkit.*;
import arabware.file.*;
import com.allenliu.badgeview.*;

import com.blankj.utilcode.*;
import com.bumptech.glide.*;
import com.bumptech.glide.integration.avif.*;
import com.bumptech.glide.integration.recyclerview.*;
import com.caverock.androidsvg.*;
import com.fasterxml.jackson.core.*;
import com.github.angads25.filepicker.*;
import com.github.junrar.*;
import com.google.android.material.*;
import com.google.android.material.appbar.AppBarLayout;
import com.hzy.lib7z.*;
import com.jsibbold.zoomage.*;
import com.lxj.xpopup.*;
import com.mukesh.*;
import com.neo.highlight.*;
import com.skydoves.powermenu.*;
import com.tapadoo.alerter.*;
import com.zip4j.*;
import fi.iki.elonen.*;
import java.io.*;
import java.text.*;
import java.util.*;
import java.util.regex.*;
import org.benf.cfr.reader.*;
import org.beyka.tiffbitmapfactory.*;
import org.json.*;
import storage.sdcard.*;
import xyz.doikki.videoplayer.*;
import xyz.doikki.videoplayer.exo.*;
import xyz.doikki.videoplayer.ijk.*;
import Ninja.coder.Ghostemane.code.marco.*;
import com.hzy.lib7z.Z7Extractor;
import java.io.IOException;
import java.nio.file.Files;
import java.io.File;
import java.nio.file.StandardCopyOption;
import java.io.InputStream;
import com.google.android.material.dialog.MaterialAlertDialogBuilder;
import androidx.appcompat.app.AlertDialog;
import com.xiaoyv.ccompile.CCppEngine;

public class PluginManagerActivity extends BaseCompat {

  protected AssetsSoft soft;
  private Toolbar _toolbar;
  private AppBarLayout _app_bar;
  private CoordinatorLayout _coordinator;

  private LinearLayout linear1;
  private LinearLayout linear2;
  private LinearLayout linear4;
  private LinearLayout linear5;
  private TextView textview1;
  private LinearLayout fileChker;
  private TextView textview2;
  private LinearLayout fileChker2;
  private TextView textview3;
  private LinearLayout linear6;

  private ProgressDialog dialog;

  @Override
  protected void onCreate(Bundle _savedInstanceState) {
    super.onCreate(_savedInstanceState);
    setContentView(R.layout.plugin_manager);
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
            finish();
          }
        });
    linear1 = findViewById(R.id.linear1);
    linear2 = findViewById(R.id.linear2);
    linear4 = findViewById(R.id.linear4);
    linear5 = findViewById(R.id.linear5);
    textview1 = findViewById(R.id.textview1);
    fileChker = findViewById(R.id.fileChker);
    textview2 = findViewById(R.id.textview2);
    fileChker2 = findViewById(R.id.fileChker2);
    textview3 = findViewById(R.id.textview3);
    linear6 = findViewById(R.id.linear6);

    linear2.setOnClickListener(
        new View.OnClickListener() {
          @Override
          public void onClick(View _view) {
            if (!FileUtil.isExistFile(
                getFilesDir().getAbsolutePath()
                    + File.separator
                    + "files"
                    + File.separator
                    + "env.sh")) {
              fileChker.setBackground(
                  new GradientDrawable() {
                    public GradientDrawable getIns(int a, int b) {
                      this.setCornerRadius(a);
                      this.setColor(b);
                      return this;
                    }
                  }.getIns((int) 99, 0xFFF44336));

              try {
                AlertDialog dialog =
                    new MaterialAlertDialogBuilder(PluginManagerActivity.this)
                        .setTitle("Loading...")
                        .setMessage("")
                        .setPositiveButton("Done", null)
                        .setCancelable(false)
                        .create();
                dialog.show();
                new Thread(
                        new Runnable() {
                          @Override
                          public void run() {
                            AssetManager assetManager = getApplicationContext().getAssets();

                            Z7Extractor.extractAsset(
                                assetManager,
                                "python.7z",
                                getFilesDir().getAbsolutePath(),
                                new com.hzy.lib7z.IExtractCallback() {
                                  @Override
                                  public void onProgress(final String name, final long size) {
                                    runOnUiThread(
                                        () -> {
                                          dialog.setMessage(name);
                                        });
                                  }

                                  @Override
                                  public void onError(int errorCode, final String error) {
                                    runOnUiThread(
                                        () -> {
                                          dialog.setMessage(
                                              "CodeError " + errorCode + "Error " + error);
                                        });
                                  }

                                  @Override
                                  public void onSucceed() {
                                    runOnUiThread(
                                        () -> {
                                          if (dialog != null) dialog.dismiss();
                                          fileChker.setBackground(
                                              new GradientDrawable() {
                                                public GradientDrawable getIns(int a, int b) {
                                                  this.setCornerRadius(a);
                                                  this.setColor(b);
                                                  return this;
                                                }
                                              }.getIns((int) 99, 0xFF4CAF50));
                                        });
                                  }

                                  @Override
                                  public void onStart() {
                                    runOnUiThread(
                                        () -> {
                                          dialog.setMessage("Starting...");
                                          fileChker.setBackground(
                                              new GradientDrawable() {
                                                public GradientDrawable getIns(int a, int b) {
                                                  this.setCornerRadius(a);
                                                  this.setColor(b);
                                                  return this;
                                                }
                                              }.getIns((int) 99, 0xFFF44336));
                                        });
                                  }

                                  @Override
                                  public void onGetFileNum(int size) {}
                                });
                          }
                        })
                    .start();
              } catch (Exception E) {
                Toast.makeText(getApplicationContext(), E.getMessage(), 2).show();
              }

            } else {
              fileChker.setBackground(
                  new GradientDrawable() {
                    public GradientDrawable getIns(int a, int b) {
                      this.setCornerRadius(a);
                      this.setColor(b);
                      return this;
                    }
                  }.getIns((int) 99, 0xFF4CAF50));
            }
          }
        });

    linear4.setOnClickListener(
        new View.OnClickListener() {
          @Override
          public void onClick(View _view) {
            if (CCppEngine.checkGcc(PluginManagerActivity.this)) {}

            CCppEngine.installGcc(
                PluginManagerActivity.this,
                new File("/storage/emulated/0/GhostWebIDE/gcc.zip"),
                new CCppEngine.OnInstallListener() {
                  @Override
                  public void onSuccess() {
                    Toast.makeText(getApplicationContext(), "done", 2).show();
                  }

                  @Override
                  public void onError(String error) {
                    Toast.makeText(getApplicationContext(), "error " + error, 2).show();
                  }
                });
          }
        });

    linear5.setOnClickListener(
        new View.OnClickListener() {
          @Override
          public void onClick(View _view) {
            if (!FileUtil.isExistFile(
                getFilesDir().getAbsolutePath() + File.separator + "libaom.so")) {
              linear6.setBackground(
                  new GradientDrawable() {
                    public GradientDrawable getIns(int a, int b) {
                      this.setCornerRadius(a);
                      this.setColor(b);
                      return this;
                    }
                  }.getIns((int) 99, 0xFFF44336));

              try {
                AlertDialog dialog =
                    new MaterialAlertDialogBuilder(PluginManagerActivity.this)
                        .setTitle("Loading...")
                        .setMessage("")
                        .setPositiveButton("Done", null)
                        .setCancelable(false)
                        .create();
                dialog.show();
                new Thread(
                        new Runnable() {
                          @Override
                          public void run() {
                            AssetManager assetManager = getApplicationContext().getAssets();

                            Z7Extractor.extractAsset(
                                assetManager,
                                "lib.7z",
                                getFilesDir().getAbsolutePath(),
                                new com.hzy.lib7z.IExtractCallback() {
                                  @Override
                                  public void onProgress(final String name, final long size) {
                                    runOnUiThread(
                                        () -> {
                                          dialog.setMessage(name);
                                        });
                                  }

                                  @Override
                                  public void onError(int errorCode, final String error) {
                                    runOnUiThread(
                                        () -> {
                                          dialog.setMessage(
                                              "CodeError " + errorCode + "Error " + error);
                                        });
                                  }

                                  @Override
                                  public void onSucceed() {
                                    runOnUiThread(
                                        () -> {
                                          if (dialog != null) dialog.dismiss();
                                          linear6.setBackground(
                                              new GradientDrawable() {
                                                public GradientDrawable getIns(int a, int b) {
                                                  this.setCornerRadius(a);
                                                  this.setColor(b);
                                                  return this;
                                                }
                                              }.getIns((int) 99, 0xFF4CAF50));
                                        });
                                  }

                                  @Override
                                  public void onStart() {
                                    runOnUiThread(
                                        () -> {
                                          dialog.setMessage("Starting...");
                                          linear6.setBackground(
                                              new GradientDrawable() {
                                                public GradientDrawable getIns(int a, int b) {
                                                  this.setCornerRadius(a);
                                                  this.setColor(b);
                                                  return this;
                                                }
                                              }.getIns(99, 0xFFF44336));
                                        });
                                  }

                                  @Override
                                  public void onGetFileNum(int size) {}
                                });
                          }
                        })
                    .start();
              } catch (Exception E) {
                Toast.makeText(getApplicationContext(), E.getMessage(), 2).show();
              }

            } else {
              linear6.setBackground(
                  new GradientDrawable() {
                    public GradientDrawable getIns(int a, int b) {
                      this.setCornerRadius(a);
                      this.setColor(b);
                      return this;
                    }
                  }.getIns((int) 99, 0xFF4CAF50));
            }
          }
        });
  }

  private void initializeLogic() {

    soft = new AssetsSoft();

    // soft.z();
    dialog = new ProgressDialog(PluginManagerActivity.this);
    if (!FileUtil.isExistFile(
        getFilesDir().getAbsolutePath() + File.separator + "files" + File.separator + "env.sh")) {
      fileChker.setBackground(
          new GradientDrawable() {
            public GradientDrawable getIns(int a, int b) {
              this.setCornerRadius(a);
              this.setColor(b);
              return this;
            }
          }.getIns((int) 99, 0xFFF44336));
    } else {
      fileChker.setBackground(
          new GradientDrawable() {
            public GradientDrawable getIns(int a, int b) {
              this.setCornerRadius(a);
              this.setColor(b);
              return this;
            }
          }.getIns(99, 0xFF4CAF50));
    }
    if (!FileUtil.isExistFile(getFilesDir().getAbsolutePath().concat("gcc.zip"))) {
      fileChker2.setBackground(
          new GradientDrawable() {
            public GradientDrawable getIns(int a, int b) {
              this.setCornerRadius(a);
              this.setColor(b);
              return this;
            }
          }.getIns(99, 0xFF4CAF50));
    } else {
      fileChker2.setBackground(
          new GradientDrawable() {
            public GradientDrawable getIns(int a, int b) {
              this.setCornerRadius(a);
              this.setColor(b);
              return this;
            }
          }.getIns(99, 0xFFF44336));
    }
    //// data/user/0/Ninja.coder.Ghostemane.code/files/lib
    if (FileUtil.isExistFile(
        getFilesDir().getAbsolutePath()
            + File.separator
            + "files"
            + File.separator
            + "libs"
            + File.separator
            + "libaom.so")) {
      linear6.setBackground(
          new GradientDrawable() {
            public GradientDrawable getIns(int a, int b) {
              this.setCornerRadius(a);
              this.setColor(b);
              return this;
            }
          }.getIns(99, 0xFFF44336));
    } else {
      linear6.setBackground(
          new GradientDrawable() {
            public GradientDrawable getIns(int a, int b) {
              this.setCornerRadius(a);
              this.setColor(b);
              return this;
            }
          }.getIns((int) 99, 0xFF4CAF50));
    }
    ColorAndroid12.setToolbarinit(_toolbar);
    _clickAnimation(linear2);
    _clickAnimation(linear4);
    _clickAnimation(linear5);
    linear2.setElevation((float) 3);
    linear4.setElevation((float) 3);
    ColorAndroid12.setTextColor(textview1);
    ColorAndroid12.setTextColor(textview2);
    ColorAndroid12.setTextColor(textview3);
  }

  public void _clickAnimation(final View _view) {
    ScaleAnimation fade_in =
        new ScaleAnimation(
            0.9f, 1f, 0.9f, 1f, Animation.RELATIVE_TO_SELF, 0.5f, Animation.RELATIVE_TO_SELF, 0.7f);
    fade_in.setDuration(300);
    fade_in.setFillAfter(true);
    _view.startAnimation(fade_in);
  }

  public void _hsiunzip(final String _input, final String _output) {
    HsiZip task = new HsiZip(this);
    task.execute(_input, _output);
  }

  public void showMessage(String _s) {
    Toast.makeText(getApplicationContext(), _s, Toast.LENGTH_SHORT).show();
  }
}
