package Ninja.coder.Ghostemane.code;

import android.Manifest;
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

    if (FileUtil.isExistFile("/storage/emulated/0/GhostWebIDE/theme/GhostThemeapp.ghost")) {

    } else {
      bin =
          "{\"ToolbarTextColor\":\"#ffffe5ae\",\"BLOCK_LINE_CURRENT\":\"#ffffbda1\",\"LINE_DIVIDER\":\"#1d000000\",\"SyombolBarTextColor\":\"#ffffd9ae\",\"HTML_TAG\":\"#ffffdabc\",\"FabColorStroker\":\"#ff1bffff\",\"LINE_NUMBER\":\"#ffffdabc\",\"KEYWORD\":\"#ffffdabc\",\"AUTO_COMP_PANEL_CORNER\":\"#ffffe4bc\",\"SELECTION_HANDLE\":\"#ff533a1f\",\"TabImageColorFilter\":\"#ffffd9ae\",\"AUTO_COMP_PANEL_BG\":\"#ff201b16\",\"COMMENT\":\"#626262\",\"ToolbarColor\":\"#ff201b16\",\"IDENTIFIER_NAME\":\"#fff0be4b\",\"DisplayTextColorTab\":\"#ffe0d1a5\",\"NON_PRINTABLE_CHAR\":\"#ffffeebc\",\"SELECTION_INSERT\":\"#ff533a1f\",\"Ninja\":\"#ffffe4bc\",\"TEXTCOLORHDER\":\"#ff522012\",\"TabTextColor\":\"#ffffd9a1\",\"BLOCK_LINE\":\"#ffffe7c9\",\"MenuBackground\":\"#ff201b16\",\"LITERAL\":\"#ffffd9a1\",\"FabBackgroundColorColor\":\"#ff201b16\",\"ATTRIBUTE_VALUE\":\"#fffff8bc\",\"TabBack\":\"#ff714f2a\",\"TEXTCOLORFORGRAND\":\"#424242\",\"ImageColor\":\"#ffffdabc\",\"TEXT_NORMAL\":\"#ffffffff\",\"ATTRIBUTE_NAME\":\"#ffffdabc\",\"print\":\"#ffff176e\",\"OPERATOR\":\"#ffffdabc\",\"CURRENT_LINE\":\"#20171717\",\"WHOLE_BACKGROUND\":\"#02FFFFFF\",\"BackgroundColorLinear\":\"#ff201b16\",\"FabImageColor\":\"#ffffd9ae\",\"LINE_NUMBER_BACKGROUND\":\"#00FFFFFF\",\"TEXTCOLORIGOR\":\"#ff500001\",\"TEXTCOLORINIER\":\"#ff402811\"}";
      FileUtil.writeFile("/storage/emulated/0/GhostWebIDE/theme/GhostThemeapp.ghost", bin);
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
    FileUtil.makeDir("/storage/emulated/0/GhostWebIDE/.lp");
    FileUtil.makeDir("/storage/emulated/0/GhostWebIDE/android");
    FileUtil.makeDir("/storage/emulated/0/GhostWebIDE/theme");
    FileUtil.makeDir("/storage/emulated/0/GhostWebIDE/ninjacoder");
    FileUtil.makeDir("/storage/emulated/0/ghostweb/icon/vector");
    FileUtil.makeDir("/storage/emulated/0/ghostweb/icon/svg");
    FileUtil.makeDir("/storage/emulated/0/GhostWebIDE/font");
    FileUtil.makeDir("/storage/emulated/0/GhostWebIDE/codeHelper");

    ColorAndroid12.setTextColor(tv1);
    ColorAndroid12.setTextColor(tv2);
    ColorAndroid12.setTextColor(textview1);
    ColorAndroid12.PrograssBarColor(progressbar1);
    layout_config.setVisibility(View.GONE);
  }
}
