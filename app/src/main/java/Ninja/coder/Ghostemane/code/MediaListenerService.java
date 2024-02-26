package Ninja.coder.Ghostemane.code;

import android.app.Service;
import android.os.FileObserver;
import android.content.Intent;
import android.os.Handler;
import android.os.IBinder;
import android.os.Looper;
import android.util.Log;
import android.widget.Toast;
import com.google.android.material.dialog.MaterialAlertDialogBuilder;

public class MediaListenerService extends Service {

  public static FileObserver observer;

  public MediaListenerService() {}

  @Override
  public IBinder onBind(Intent intent) {
    return null;
  }

  @Override
  public void onCreate() {
    super.onCreate();
    startWatching();
  }

  private void startWatching() {
    final String pathToWatch = "/storage/emulated/0/GhostWebIDE/theme/";
    // Toast.makeText(this, "سرویس در حال اجرا است صبر کنید..." + pathToWatch, Toast.LENGTH_LONG)
    // .show();

    observer =
        new FileObserver(
            pathToWatch,
            FileObserver.ALL_EVENTS) { // set up a file observer to watch this directory
          @Override
          public void onEvent(int event, final String file) {
            String eventText = "";
            if (event == FileObserver.DELETE && file.endsWith("ghost")) {
              new Thread(
                      () -> {
                        var bin =
                            "{\"ToolbarTextColor\":\"#ffffe5ae\",\"BLOCK_LINE_CURRENT\":\"#ffffbda1\",\"LINE_DIVIDER\":\"#1d000000\",\"SyombolBarTextColor\":\"#ffffd9ae\",\"HTML_TAG\":\"#ffffdabc\",\"FabColorStroker\":\"#ff1bffff\",\"LINE_NUMBER\":\"#ffffdabc\",\"KEYWORD\":\"#ffffdabc\",\"AUTO_COMP_PANEL_CORNER\":\"#ffffe4bc\",\"SELECTION_HANDLE\":\"#ff533a1f\",\"TabImageColorFilter\":\"#ffffd9ae\",\"AUTO_COMP_PANEL_BG\":\"#ff201b16\",\"COMMENT\":\"#626262\",\"ToolbarColor\":\"#ff201b16\",\"IDENTIFIER_NAME\":\"#fff0be4b\",\"DisplayTextColorTab\":\"#ffe0d1a5\",\"NON_PRINTABLE_CHAR\":\"#ffffeebc\",\"SELECTION_INSERT\":\"#ff533a1f\",\"Ninja\":\"#ffffe4bc\",\"TEXTCOLORHDER\":\"#ff522012\",\"TabTextColor\":\"#ffffd9a1\",\"BLOCK_LINE\":\"#ffffe7c9\",\"MenuBackground\":\"#ff201b16\",\"LITERAL\":\"#ffffd9a1\",\"FabBackgroundColorColor\":\"#ff201b16\",\"ATTRIBUTE_VALUE\":\"#fffff8bc\",\"TabBack\":\"#ff714f2a\",\"TEXTCOLORFORGRAND\":\"#424242\",\"ImageColor\":\"#ffffdabc\",\"TEXT_NORMAL\":\"#ffffffff\",\"ATTRIBUTE_NAME\":\"#ffffdabc\",\"print\":\"#ffff176e\",\"OPERATOR\":\"#ffffdabc\",\"CURRENT_LINE\":\"#20171717\",\"WHOLE_BACKGROUND\":\"#02FFFFFF\",\"BackgroundColorLinear\":\"#ff201b16\",\"FabImageColor\":\"#ffffd9ae\",\"LINE_NUMBER_BACKGROUND\":\"#00FFFFFF\",\"TEXTCOLORIGOR\":\"#ff500001\",\"TEXTCOLORINIER\":\"#ff402811\"}";
                        FileUtil.writeFile(
                            "/storage/emulated/0/GhostWebIDE/theme/GhostThemeapp.ghost", bin);
                      })
                  .start();
            }
          }
        };
    observer.startWatching();
  }
}
