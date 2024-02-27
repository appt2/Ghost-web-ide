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
     var pathToWatch = "/storage/emulated/0/GhostWebIDE/theme/";
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
                            "{\n  \"ToolbarTextColor\": \"#ffff1750\",\n  \"BLOCK_LINE_CURRENT\": \"#ff28ffae\",\n  \"LINE_DIVIDER\": \"#1d000000\",\n  \"SyombolBarTextColor\": \"#ffff005b\",\n  \"HTML_TAG\": \"#ff1bffdd\",\n  \"FabColorStroker\": \"#ffc9ffd0\",\n  \"LINE_NUMBER\": \"#ff0dedff\",\n  \"KEYWORD\": \"#ff00d9ff\",\n  \"AUTO_COMP_PANEL_CORNER\": \"#ffffa1af\",\n  \"SELECTION_HANDLE\": \"#ff2a6373\",\n  \"TabImageColorFilter\": \"#ffbcffff\",\n  \"AUTO_COMP_PANEL_BG\": \"#ff0b1a32\",\n  \"COMMENT\": \"#626262\",\n  \"ToolbarColor\": \"#ff050d19\",\n  \"IDENTIFIER_NAME\": \"#fff0be4b\",\n  \"DisplayTextColorTab\": \"#fffad7ff\",\n  \"NON_PRINTABLE_CHAR\": \"#ff6b90ff\",\n  \"SELECTION_INSERT\": \"#ff2a6373\",\n  \"Ninja\": \"#ffddaeff\",\n  \"TEXTCOLORHDER\": \"#ff522012\",\n  \"TabTextColor\": \"#ffc9eaff\",\n  \"BLOCK_LINE\": \"#ff5effaa\",\n  \"MenuBackground\": \"#ff0a1320\",\n  \"LITERAL\": \"#ffbcf5ff\",\n  \"FabBackgroundColorColor\": \"#ff021020\",\n  \"ATTRIBUTE_VALUE\": \"#ffa6ffa1\",\n  \"TabBack\": \"#ff1e5e71\",\n  \"TEXTCOLORFORGRAND\": \"#424242\",\n  \"ImageColor\": \"#ffa1f1ff\",\n  \"TEXT_NORMAL\": \"#ffffffff\",\n  \"ATTRIBUTE_NAME\": \"#ffa1e3ff\",\n  \"print\": \"#ffecffa1\",\n  \"OPERATOR\": \"#ff43ffd5\",\n  \"CURRENT_LINE\": \"#20171717\",\n  \"WHOLE_BACKGROUND\": \"#02FFFFFF\",\n  \"BackgroundColorLinear\": \"#ff050d19\",\n  \"FabImageColor\": \"#ffbce1ff\",\n  \"LINE_NUMBER_BACKGROUND\": \"#00FFFFFF\",\n  \"TEXTCOLORIGOR\": \"#ff500001\",\n  \"TEXTCOLORINIER\": \"#ff402811\"\n}";
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
