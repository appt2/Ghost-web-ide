package Ninja.coder.Ghostemane.code;

import Ninja.coder.Ghostemane.code.activities.BaseCompat;
import Ninja.coder.Ghostemane.code.activities.FileDirActivity;
import Ninja.coder.Ghostemane.code.activities.StreamSoftAppActivity;
import Ninja.coder.Ghostemane.code.utils.AssetsSoft;
import Ninja.coder.Ghostemane.code.utils.ColorAndroid12;
import Ninja.coder.Ghostemane.code.utils.FileUtil;
import android.Manifest;
import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.PackageManager;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.widget.*;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import com.hzy.lib7z.IExtractCallback;
import com.hzy.lib7z.Z7Extractor;

import java.io.File;
import java.util.Timer;
import java.util.TimerTask;

public class MainActivity extends BaseCompat {

  protected AssetsSoft soft;
  private Timer _timer = new Timer();
  private String bin = "";
  private Intent gotopage = new Intent();
  private TimerTask ask;
  private SharedPreferences setac;

  @Override
  protected void onCreate(Bundle _savedInstanceState) {
    super.onCreate(_savedInstanceState);
    setContentView(R.layout.main);
    initialize(_savedInstanceState);

    if (ContextCompat.checkSelfPermission(this, Manifest.permission.READ_EXTERNAL_STORAGE)
            == PackageManager.PERMISSION_DENIED
        || ContextCompat.checkSelfPermission(this, Manifest.permission.WRITE_EXTERNAL_STORAGE)
            == PackageManager.PERMISSION_DENIED) {
      ActivityCompat.requestPermissions(
          this,
          new String[] {
            Manifest.permission.READ_EXTERNAL_STORAGE, Manifest.permission.WRITE_EXTERNAL_STORAGE
          },
          1000);
    } else {
      initializeLogic();
    }
    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.HONEYCOMB) {
      setTheme(R.style.Theme_Transparent);
    } else setTheme(R.style.AppTheme_Theme_Transparent);
  }

  @Override
  public void onRequestPermissionsResult(
      int requestCode, String[] permissions, int[] grantResults) {
    super.onRequestPermissionsResult(requestCode, permissions, grantResults);
    if (requestCode == 1000) {
      initializeLogic();
    }
  }

  private void initialize(Bundle _savedInstanceState) {
    setac = getSharedPreferences("setac", Activity.MODE_PRIVATE);
  }

  private void initializeLogic() {
    ask =
        new TimerTask() {
          @Override
          public void run() {
            runOnUiThread(
                () -> {
                  if (setac.getString("ac", "").equals("true")) {
                    gotopage.setClass(getApplicationContext(), FileDirActivity.class);
                    gotopage.setFlags(Intent.FLAG_ACTIVITY_SINGLE_TOP);
                    startActivity(gotopage);
                  } else {
                    if (setac.getString("ac", "").equals("false")) {
                      gotopage.setClass(getApplicationContext(), StreamSoftAppActivity.class);
                      gotopage.setFlags(Intent.FLAG_ACTIVITY_SINGLE_TOP);
                      startActivity(gotopage);
                    } else {
                      gotopage.setClass(getApplicationContext(), StreamSoftAppActivity.class);
                      gotopage.setFlags(Intent.FLAG_ACTIVITY_SINGLE_TOP);
                      startActivity(gotopage);
                    }
                  }
                });
          }
        };

    _timer.schedule(ask, 3000);

    if (!FileUtil.isExistFile("/storage/emulated/0/GhostWebIDE/theme/GhostThemeapp.ghost")) {
      bin =
          "{\n  \"ToolbarTextColor\": \"#ffff1750\",\n  \"BLOCK_LINE_CURRENT\": \"#ff28ffae\",\n  \"LINE_DIVIDER\": \"#1d000000\",\n  \"SyombolBarTextColor\": \"#ffff005b\",\n  \"HTML_TAG\": \"#ff1bffdd\",\n  \"FabColorStroker\": \"#ffc9ffd0\",\n  \"LINE_NUMBER\": \"#ff0dedff\",\n  \"KEYWORD\": \"#ff00d9ff\",\n  \"AUTO_COMP_PANEL_CORNER\": \"#ffffa1af\",\n  \"SELECTION_HANDLE\": \"#ff2a6373\",\n  \"TabImageColorFilter\": \"#ffbcffff\",\n  \"AUTO_COMP_PANEL_BG\": \"#ff0b1a32\",\n  \"COMMENT\": \"#626262\",\n  \"ToolbarColor\": \"#ff050d19\",\n  \"IDENTIFIER_NAME\": \"#fff0be4b\",\n  \"DisplayTextColorTab\": \"#fffad7ff\",\n  \"NON_PRINTABLE_CHAR\": \"#ff6b90ff\",\n  \"SELECTION_INSERT\": \"#ff2a6373\",\n  \"Ninja\": \"#ffddaeff\",\n  \"TEXTCOLORHDER\": \"#ff522012\",\n  \"TabTextColor\": \"#ffc9eaff\",\n  \"BLOCK_LINE\": \"#ff5effaa\",\n  \"MenuBackground\": \"#ff0a1320\",\n  \"LITERAL\": \"#ffbcf5ff\",\n  \"FabBackgroundColorColor\": \"#ff021020\",\n  \"ATTRIBUTE_VALUE\": \"#ffa6ffa1\",\n  \"TabBack\": \"#ff1e5e71\",\n  \"TEXTCOLORFORGRAND\": \"#424242\",\n  \"ImageColor\": \"#ffa1f1ff\",\n  \"TEXT_NORMAL\": \"#ffffffff\",\n  \"ATTRIBUTE_NAME\": \"#ffa1e3ff\",\n  \"print\": \"#ffecffa1\",\n  \"OPERATOR\": \"#ff43ffd5\",\n  \"CURRENT_LINE\": \"#20171717\",\n  \"WHOLE_BACKGROUND\": \"#02FFFFFF\",\n  \"BackgroundColorLinear\": \"#ff050d19\",\n  \"FabImageColor\": \"#ffbce1ff\",\n  \"LINE_NUMBER_BACKGROUND\": \"#00FFFFFF\",\n  \"TEXTCOLORIGOR\": \"#ff500001\",\n  \"TEXTCOLORINIER\": \"#ff402811\"\n}";
      FileUtil.writeFile("/storage/emulated/0/GhostWebIDE/theme/GhostThemeapp.ghost", bin);
    }

    if (!FileUtil.isExistFile("/storage/emulated/0/GhostWebIDE/android/android.jar")) {

      var asster = getAssets();
      Z7Extractor.extractAsset(
          asster,
          "android.7z",
          "/storage/emulated/0/GhostWebIDE/android/",
          new IExtractCallback() {

            @Override
            public void onError(int arg0, String arg1) {}

            @Override
            public void onGetFileNum(int arg0) {}

            @Override
            public void onProgress(String arg0, long arg1) {}

            @Override
            public void onStart() {}

            @Override
            public void onSucceed() {
              Toast.makeText(MainActivity.this, "done", Toast.LENGTH_SHORT).show();
            }
          });
    } else {

    }

    if (!FileUtil.isExistFile(
        getFilesDir().getAbsolutePath() + File.separator + "files" + File.separator + "env.sh")) {
      if (soft != null) {
        soft.z();
      }
    } else {
    }

    if (!FileUtil.isExistFile(
        getFilesDir().getAbsolutePath() + File.separator + "lib/libandroid-glob.so")) {
      var softApi = new AssetsSoft();
      softApi.unZipPython(this, "libs.zip");
    }
    if (!FileUtil.isExistFile(getFilesDir().getAbsolutePath() + File.separator + "php.ini")) {
      var softApi = new AssetsSoft();
      softApi.copyOneFileFromAssets("php.ini", getFilesDir().getAbsolutePath() + "/", this);
    }
    FileUtil.makeDir("/storage/emulated/0/GhostWebIDE/");
    FileUtil.makeDir("/storage/emulated/0/GhostWebIDE/.icon");
    FileUtil.makeDir("/storage/emulated/0/GhostWebIDE/android");
    FileUtil.makeDir("/storage/emulated/0/GhostWebIDE/theme");
    FileUtil.makeDir("/storage/emulated/0/GhostWebIDE/ninjacoder");
    FileUtil.makeDir("/storage/emulated/0/ghostweb/icon/vector");
    FileUtil.makeDir("/storage/emulated/0/ghostweb/icon/svg");
    FileUtil.makeDir("/storage/emulated/0/GhostWebIDE/font");
    FileUtil.makeDir("/storage/emulated/0/GhostWebIDE/apk");
  }
}
