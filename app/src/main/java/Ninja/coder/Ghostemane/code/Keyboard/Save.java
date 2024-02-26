package Ninja.coder.Ghostemane.code.Keyboard;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.widget.Toast;
import Ninja.coder.Ghostemane.code.R;

public class Save extends Activity {
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        PreferenceManager.getDefaultSharedPreferences(this).edit().apply();
        Toast.makeText(getBaseContext(), "تغییرات با موفقیت ذخیره شدند", 0).show();
        restart(getBaseContext());
    }
	public static boolean restart(Context context) {
		try {
			Intent i = context.getPackageManager().getLaunchIntentForPackage(context.getPackageName());
			i.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
			context.startActivity(i);
        
			return true;
		} catch (Exception ignored) {
			return false;
		}
	}
}

