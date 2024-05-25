package Ninja.coder.Ghostemane.code;

import Ninja.coder.Ghostemane.code.activities.ErrorManagerActivity;
import Ninja.coder.Ghostemane.code.utils.MobileInfo;
import android.app.Activity;
import android.app.AlarmManager;
import android.app.Application;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.os.Build;
import android.os.Process;
import android.util.Log;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatDelegate;
import androidx.preference.PreferenceManager;
import com.google.android.material.color.DynamicColors;
import de.larsgrefer.sass.embedded.SassCompiler;
import de.larsgrefer.sass.embedded.android.AndroidSassCompilerFactory;

import java.util.Calendar;

public class ApplicationLoader extends Application {
  // from terminal

  private static SharedPreferences prfns;
  protected static SharedPreferences materialYou, getvb, setfont, ru, save_path, thememanagersoft;
  private static Context mApplicationContext;
  private static Activity activity;
  private static IdeEditor editor;
  private static ApplicationLoader loader;
  protected SharedPreferences mt300;
  private Thread.UncaughtExceptionHandler uncaughtExceptionHandler;
  private StringBuilder softwareInfo = new StringBuilder();

  public static Context getContext() {
    return mApplicationContext;
  }

  public static Activity getActivity() {
    return activity;
  }

  public static ApplicationLoader getInstance() {
    return loader;
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

  public static SharedPreferences getThememanagersoft() {
    return thememanagersoft;
  }

  public static IdeEditor getEditor() {
    return editor;
  }

  @Override
  public void onCreate() {
    materialYou = getSharedPreferences("materialYou", MODE_PRIVATE);
    getvb = getSharedPreferences("getvb", MODE_PRIVATE);
    setfont = getSharedPreferences("setfont", MODE_PRIVATE);
    save_path = getSharedPreferences("path", MODE_PRIVATE);
    ru = getSharedPreferences("ru", MODE_PRIVATE);
    thememanagersoft = getSharedPreferences("thememanagersoft", MODE_PRIVATE);
    prfns =  PreferenceManager.getDefaultSharedPreferences(this);
    try (SassCompiler compiler = AndroidSassCompilerFactory.bundled(this)) {
      Toast.makeText(getApplicationContext(), compiler.getVersion().toString(), Toast.LENGTH_SHORT)
          .show();
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
            Intent intent = new Intent(getApplicationContext(), ErrorManagerActivity.class);
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
    } else
      try {
        PackageInfo info = getPackageManager().getPackageInfo(getPackageName(), 0);
        String v = info.versionName;
        Toast.makeText(getApplicationContext(), "Welcom to Ghost web ide " + v, Toast.LENGTH_SHORT)
            .show();
      } catch (PackageManager.NameNotFoundException err) {

      }

    super.onCreate();
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

  public static SharedPreferences getPrefManager() {
    return prfns;
  }
}
