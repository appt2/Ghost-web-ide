package Ninja.coder.Ghostemane.code;

import Ninja.coder.Ghostemane.code.FiledirActivity;
import android.app.Service;
import android.content.Intent;
import android.os.IBinder;
import io.reactivex.rxjava3.core.Observable;
import Ninja.coder.Ghostemane.code.databin.FileEvent;
import Ninja.coder.Ghostemane.code.databin.RxFileObserver;
import io.reactivex.rxjava3.schedulers.Schedulers;
import android.util.Log;
import android.widget.Toast;
import android.os.Handler;
import android.os.Looper;
import io.reactivex.rxjava3.disposables.Disposable;
import java.io.File;

public class FileEventUser extends Service {

  protected CallBack call;
  public String path = "";
  private Disposable disposable;

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
    //    File sdCard = new File(FiledirActivity.POSNINJACODERMAIN);
    //    Observable<FileEvent> sdCardFileEvents = RxFileObserver.create(sdCard);
    //
    //    disposable =
    //        sdCardFileEvents
    //            .observeOn(Schedulers.io())
    //            .subscribe(
    //                fileEvent -> {
    //                  runOnUiThread(
    //                      () -> {
    //                        if (fileEvent.isDelete()) {
    //                          if (sdCard.isDirectory()) dialog("Directory is Delete");
    //                          else dialog("File is Delete");
    //                        } else if (fileEvent.isCreate()) {
    //                          dialog("File is Create");
    //                        }
    //                        Log.i("TAG", fileEvent.toString());
    //                      });
    //                });
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

  public void dialog(String txt) {
    Toast.makeText(getApplicationContext(), txt, Toast.LENGTH_SHORT).show();
  }

  private void runOnUiThread(Runnable action) {
    Handler mainHandler = new Handler(Looper.getMainLooper());
    mainHandler.post(action);
  }
}
