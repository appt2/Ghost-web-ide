package Ninja.coder.Ghostemane.code;

import Ninja.coder.Ghostemane.code.IDEEDITOR;
import Ninja.coder.Ghostemane.code.project.FileReaderJsonSpinet;
import android.app.AlarmManager;
import android.app.Activity;
import android.app.PendingIntent;
import android.app.Application;
import android.content.*;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.os.Build;
import android.os.Process;
import android.util.Log;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatDelegate;

import com.google.android.material.color.DynamicColors;
import de.larsgrefer.sass.embedded.SassCompiler;
import de.larsgrefer.sass.embedded.android.AndroidSassCompilerFactory;
import java.util.Calendar;

public class ApplicationLoader extends Application {
  private static Context mApplicationContext;
  private static Activity activity;
  protected SharedPreferences mt300;
  private static IDEEDITOR editor;
  private Thread.UncaughtExceptionHandler uncaughtExceptionHandler;
  protected static SharedPreferences materialYou, getvb, setfont, ru, save_path;
  private static ApplicationLoader loader;
  private StringBuilder softwareInfo = new StringBuilder();

  public static Context getContext() {
    return mApplicationContext;
  }

  public static Activity getActivity() {
    return activity;
  }

  @Override
  public void onCreate() {

    FileReaderJsonSpinet js = new FileReaderJsonSpinet();
    js.makeFileJson();
    materialYou = getSharedPreferences("materialYou", MODE_PRIVATE);
    getvb = getSharedPreferences("getvb", MODE_PRIVATE);
    setfont = getSharedPreferences("setfont", MODE_PRIVATE);
    save_path = getSharedPreferences("path", MODE_PRIVATE);
    ru = getSharedPreferences("ru", MODE_PRIVATE);
    try (SassCompiler compiler = AndroidSassCompilerFactory.bundled(this)) {
      Toast.makeText(getApplicationContext(), compiler.getVersion().toString(), 2).show();
    } catch (Exception err) {
      err.printStackTrace();
    }
    mApplicationContext = getApplicationContext();
    softwareInfo
        .append("SDK: ")
        .append(Build.VERSION.SDK_INT)
        .append("\n")
        .append("Android: ")
        .append(Build.VERSION.RELEASE)
        .append("\n")
        .append("Model: ")
        .append(Build.VERSION.INCREMENTAL)
        .append("\n");
    Thread.setDefaultUncaughtExceptionHandler(
        new Thread.UncaughtExceptionHandler() {
          @Override
          public void uncaughtException(Thread thread, Throwable throwable) {
            Intent intent = new Intent(getApplicationContext(), ErrormanagerActivity.class);
            intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK);
            intent.putExtra("Error", Log.getStackTraceString(throwable));
            intent.putExtra("Date", Calendar.getInstance().getTime() + "\n");
            intent.putExtra("Test", MobileInfo.getMobileInfo(getApplicationContext()));
            intent.putExtra("Software", softwareInfo.toString());
            PendingIntent pendingIntent =
                PendingIntent.getActivity(
                    getApplicationContext(), 11111, intent, PendingIntent.FLAG_MUTABLE);

            AlarmManager am = (AlarmManager) getSystemService(Context.ALARM_SERVICE);
            am.set(AlarmManager.ELAPSED_REALTIME_WAKEUP, 1000, pendingIntent);

            Process.killProcess(Process.myPid());
            System.exit(1);

            uncaughtExceptionHandler.uncaughtException(thread, throwable);
          }
        });
    if (materialYou.getString("materialYou", "").equals("true")) {
      if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.S
          && DynamicColors.isDynamicColorAvailable()) {
        DynamicColors.applyToActivitiesIfAvailable(this);
      }
    }

    try {
      PackageInfo info = getPackageManager().getPackageInfo(getPackageName(), 0);
      String v = info.versionName;
      Toast.makeText(getApplicationContext(), "Welcom to Ghost web ide " + v, 2).show();
    } catch (PackageManager.NameNotFoundException err) {

    }

    super.onCreate();
  }

  public static ApplicationLoader getInstance() {
    return loader;
  }

  public boolean isSdkS() {
    return Build.VERSION.SDK_INT >= Build.VERSION_CODES.S;
  }

  public boolean isSdkQ() {
    return Build.VERSION.SDK_INT >= Build.VERSION_CODES.Q;
  }

  public void updateTheme(int nightMode, Activity activity) {
    AppCompatDelegate.setDefaultNightMode(nightMode);
    activity.recreate();
  }

  public String getVersion() {
    try {
      PackageInfo info = getPackageManager().getPackageInfo(getPackageName(), 0);
      String v = info.versionName;
      return v;
    } catch (PackageManager.NameNotFoundException err) {
      return null;
    }
  }

  public static SharedPreferences getVb() {
    return getvb;
  }

  public static SharedPreferences getsetFont() {
    return setfont;
  }

  public static SharedPreferences getRu() {
    return ru;
  }

  public static SharedPreferences getPath() {
    return save_path;
  }

  public static IDEEDITOR getEditor() {
    return editor;
  }
}
