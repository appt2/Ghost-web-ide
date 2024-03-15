package Ninja.coder.Ghostemane.code;

import android.Manifest;
import android.widget.Toast;
import com.hzy.lib7z.IExtractCallback;
import com.hzy.lib7z.Z7Extractor;
import java.util.Timer;
import android.widget.LinearLayout;
import androidx.cardview.widget.CardView;
import android.widget.TextView;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.content.Intent;
import java.util.TimerTask;
import android.os.Vibrator;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import androidx.core.content.ContextCompat;
import android.content.pm.PackageManager;
import androidx.core.app.ActivityCompat;
import android.content.Context;
import android.app.Activity;
import java.io.File;

public class MainActivity extends BaseCompat {

  private Timer _timer = new Timer();

  protected AssetsSoft soft;
  private String bin = "";

  private LinearLayout roo;
  private LinearLayout linear3;
  private LinearLayout linear5;
  private CardView cardview1;
  private TextView tv2;
  private LinearLayout layout_config;
  private LinearLayout linear7;
  private ImageView imageview2;
  private ProgressBar progressbar1;
  private TextView textview1;
  private TextView tv1;

  private Intent gotopage = new Intent();
  private TimerTask ask;
  private Vibrator vibrator01;
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
    roo = findViewById(R.id.roo);
    linear3 = findViewById(R.id.linear3);
    linear5 = findViewById(R.id.linear5);
    cardview1 = findViewById(R.id.cardview1);
    tv2 = findViewById(R.id.tv2);
    layout_config = findViewById(R.id.layout_config);
    linear7 = findViewById(R.id.linear7);
    imageview2 = findViewById(R.id.imageview2);
    progressbar1 = findViewById(R.id.progressbar1);
    textview1 = findViewById(R.id.textview1);
    tv1 = findViewById(R.id.tv1);
    vibrator01 = (Vibrator) getSystemService(Context.VIBRATOR_SERVICE);
    setac = getSharedPreferences("setac", Activity.MODE_PRIVATE);
  }

  private void initializeLogic() {

    if (!FileUtil.isExistFile("/storage/emulated/0/GhostWebIDE/theme/GhostThemeapp.ghost")) {
      bin =
          "{\n  \"ToolbarTextColor\": \"#ffff1750\",\n  \"BLOCK_LINE_CURRENT\": \"#ff28ffae\",\n  \"LINE_DIVIDER\": \"#1d000000\",\n  \"SyombolBarTextColor\": \"#ffff005b\",\n  \"HTML_TAG\": \"#ff1bffdd\",\n  \"FabColorStroker\": \"#ffc9ffd0\",\n  \"LINE_NUMBER\": \"#ff0dedff\",\n  \"KEYWORD\": \"#ff00d9ff\",\n  \"AUTO_COMP_PANEL_CORNER\": \"#ffffa1af\",\n  \"SELECTION_HANDLE\": \"#ff2a6373\",\n  \"TabImageColorFilter\": \"#ffbcffff\",\n  \"AUTO_COMP_PANEL_BG\": \"#ff0b1a32\",\n  \"COMMENT\": \"#626262\",\n  \"ToolbarColor\": \"#ff050d19\",\n  \"IDENTIFIER_NAME\": \"#fff0be4b\",\n  \"DisplayTextColorTab\": \"#fffad7ff\",\n  \"NON_PRINTABLE_CHAR\": \"#ff6b90ff\",\n  \"SELECTION_INSERT\": \"#ff2a6373\",\n  \"Ninja\": \"#ffddaeff\",\n  \"TEXTCOLORHDER\": \"#ff522012\",\n  \"TabTextColor\": \"#ffc9eaff\",\n  \"BLOCK_LINE\": \"#ff5effaa\",\n  \"MenuBackground\": \"#ff0a1320\",\n  \"LITERAL\": \"#ffbcf5ff\",\n  \"FabBackgroundColorColor\": \"#ff021020\",\n  \"ATTRIBUTE_VALUE\": \"#ffa6ffa1\",\n  \"TabBack\": \"#ff1e5e71\",\n  \"TEXTCOLORFORGRAND\": \"#424242\",\n  \"ImageColor\": \"#ffa1f1ff\",\n  \"TEXT_NORMAL\": \"#ffffffff\",\n  \"ATTRIBUTE_NAME\": \"#ffa1e3ff\",\n  \"print\": \"#ffecffa1\",\n  \"OPERATOR\": \"#ff43ffd5\",\n  \"CURRENT_LINE\": \"#20171717\",\n  \"WHOLE_BACKGROUND\": \"#02FFFFFF\",\n  \"BackgroundColorLinear\": \"#ff050d19\",\n  \"FabImageColor\": \"#ffbce1ff\",\n  \"LINE_NUMBER_BACKGROUND\": \"#00FFFFFF\",\n  \"TEXTCOLORIGOR\": \"#ff500001\",\n  \"TEXTCOLORINIER\": \"#ff402811\"\n}";
      FileUtil.writeFile("/storage/emulated/0/GhostWebIDE/theme/GhostThemeapp.ghost", bin);
    }

    if (!FileUtil.isExistFile(" /storage/emulated/0/GhostWebIDE/android/android.jar")) {

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
              Toast.makeText(MainActivity.this, "done", 2).show();
            }
          });
    }

    if (!FileUtil.isExistFile(
        getFilesDir().getAbsolutePath() + File.separator + "files" + File.separator + "env.sh")) {
      if (soft != null) {
        soft.z();
      }
    } else {
    }

    if (setac.getString("ac", "").equals("true")) {
      gotopage.setClass(getApplicationContext(), FiledirActivity.class);
      gotopage.setFlags(Intent.FLAG_ACTIVITY_SINGLE_TOP);
      startActivity(gotopage);
    } else {
      if (setac.getString("ac", "").equals("false")) {
        gotopage.setClass(getApplicationContext(), StreamsoftappActivity.class);
        gotopage.setFlags(Intent.FLAG_ACTIVITY_SINGLE_TOP);
        startActivity(gotopage);
      } else {
        gotopage.setClass(getApplicationContext(), StreamsoftappActivity.class);
        gotopage.setFlags(Intent.FLAG_ACTIVITY_SINGLE_TOP);
        startActivity(gotopage);
      }
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
    FileUtil.makeDir("/storage/emulated/0/GhostWebIDE/codeHelper");

    ColorAndroid12.setTextColor(tv1);
    ColorAndroid12.setTextColor(tv2);
    ColorAndroid12.setTextColor(textview1);
    ColorAndroid12.PrograssBarColor(progressbar1);
    layout_config.setVisibility(View.GONE);
  }
}
