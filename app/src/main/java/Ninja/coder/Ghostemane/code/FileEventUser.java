package Ninja.coder.Ghostemane.code;

import android.app.Service;
import android.content.Intent;
import android.os.FileObserver;
import android.os.IBinder;
import java.io.File;

public class FileEventUser extends Service {
  protected FileObserver server;
  protected CallBack call;
  public String path = "";

  public FileEventUser() {}

  public void setCallBack(CallBack call) {
    this.call = call;
  }

  @Override
  public IBinder onBind(Intent arg0) {
    // TODO: Implement this method
    return null;
  }

  @Override
  public void onCreate() {
    super.onCreate();
    // TODO: Implement this method
    Start();
  }

  protected void Start() {
    server =
        new FileObserver(path, FileObserver.ALL_EVENTS) {
          @Override
          public void onEvent(int event, final String files) {
            File file = new File(files);
            if (file.isDirectory() && file.exists()) {
              if (event == FileObserver.DELETE) {
                call.onFileChange();
              }
            }
          }
        };
    server.startWatching();
  }

  public interface CallBack {
    public void onFileChange();
  }

  public String getPath() {
    return this.path;
  }

  public void setPath(String path) {
    this.path = path;
  }
}
