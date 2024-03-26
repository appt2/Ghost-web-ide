package ninja.coder.codecomment.rule;

import Ninja.coder.Ghostemane.code.PluginManager.Plugin;
import android.content.Context;
import android.os.Handler;
import android.os.Looper;
import android.util.Log;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import java.io.File;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class CommentRule {

  protected Plugin pl;
  private static String home = "/storage/emulated/0/ghostweb/comment/comment.json";
  private Gson gson;
  // from copy asster data
  protected Context context;
  private List<HashMap<String, String>> map = new ArrayList<>();

  public CommentRule(Context context) {
    this.context = context;
    pl = new Plugin("comment", true);
    var myfile = new File(home);
    pl.setFile(myfile);
    copyJson("comment.json", "/storage/emulated/0/ghostweb/comment/comment.json");
    synchronized (Gson.class) {
      try {
        gson = new Gson();
        map =
            gson.fromJson(
                readFile(home), new TypeToken<List<HashMap<String, String>>>() {}.getType());
      } catch (Exception err) {
        Log.e("Error", err.getLocalizedMessage());
      }
    }
  }

  private String getValueFromMap(String key) {
    String hsiValue = null;
    for (HashMap<String, String> entry : map) {
      hsiValue = entry.get(key);
      if (hsiValue != null) {
        break;
      }
    }
    return hsiValue;
  }

  public String getPythonEnd() {
    return getValueFromMap("pythoncommentstart");
  }

  public String getPythonStart() {
    return getValueFromMap("pythoncommentend");
  }

  public String getJavaNormal() {
    return getValueFromMap("javannormal");
  }

  public String getHtmlEnd() {
    return getValueFromMap("webhtmlend");
  }

  public String getHtmlStart() {
    return getValueFromMap("webhtmlstart");
  }

  public String getJavaEnd() {
    return getValueFromMap("javaend");
  }

  public String getJavaStart() {
    return getValueFromMap("javastart");
  }

  public String readFile(String path) {

    StringBuilder sb = new StringBuilder();
    FileReader fr = null;
    try {
      fr = new FileReader(new File(path));

      char[] buff = new char[1024];
      int length = 0;

      while ((length = fr.read(buff)) > 0) {
        sb.append(new String(buff, 0, length));
      }
    } catch (IOException e) {
      e.printStackTrace();
    } finally {
      if (fr != null) {
        try {
          fr.close();
        } catch (Exception e) {
          e.printStackTrace();
        }
      }
    }

    return sb.toString();
  }

  void copyJson(String assetFilename, String assetSavePath) {

    new Thread(
            () -> {
              try {
                int count;
                var input = context.getAssets().open(assetFilename);
                var output = new FileOutputStream(assetSavePath + "/" + assetFilename);
                byte data[] = new byte[1024];
                while ((count = input.read(data)) > 0) {
                  output.write(data, 0, count);
                }
                output.flush();
                output.close();
                input.close();
                run(
                    () -> {
                      Log.e("Done", "fileSaved in " + output);
                    });
              } catch (IOException err) {
                Log.e("TAG", err.getLocalizedMessage());
              }
            })
        .start();
  }

  void run(Runnable run) {
    var handler = new Handler(Looper.getMainLooper());
    handler.post(run);
  }
}
