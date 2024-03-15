package ninjacoder.ghostide.androidtools.r8.android;

import android.app.ProgressDialog;
import android.content.Context;
import android.os.Handler;
import android.os.Looper;
import com.android.tools.r8.CompilationMode;
import com.android.tools.r8.OutputMode;
import com.android.tools.r8.R8;
import com.android.tools.r8.R8Command;
import java.nio.file.Path;
import java.nio.file.Paths;

public class R8Tools {
  public void CompileR8Dex(String input, String output, int minapi) throws Exception {
    var path = Paths.get(input);
    var outputFile = Paths.get(output);
    if (path.endsWith(".dex")) {
      R8Command command =
          R8Command.builder()
              .setMinApiLevel(minapi)
              .setAndroidPlatformBuild(true)
              .addProgramFiles(path)
              .setOutput(outputFile, OutputMode.DexIndexed)
              .setMode(CompilationMode.RELEASE)
              .build();
      R8.run(command);
    }
  }

  public void onlyCompile(String input, String output, int api, Context context) {
    var dialog = new ProgressDialog(context, ProgressDialog.THEME_DEVICE_DEFAULT_DARK);
    dialog.setMessage("Compile jar to dex");
    dialog.show();
    new Thread(
            () -> {
              try {
                CompileR8Dex(input, output, api);
              } catch (Exception err) {
                runOnUiThread(
                    () -> {
                      dialog.setMessage(err.getLocalizedMessage());
                    });
              }
              runOnUiThread(
                  () -> {
                    if (dialog != null) {
                      dialog.setMessage("Done");
                      dialog.dismiss();
                    }
                  });
            })
        .start();
  } 

  private void runOnUiThread(Runnable d) {
    var handler = new Handler(Looper.getMainLooper());
    handler.post(d);
  }
}
