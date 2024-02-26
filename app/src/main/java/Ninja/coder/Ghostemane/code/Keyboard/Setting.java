package Ninja.coder.Ghostemane.code.Keyboard;

import android.Manifest;
import android.os.Bundle;
import android.preference.PreferenceActivity;
import Ninja.coder.Ghostemane.code.R;
public class Setting extends PreferenceActivity {
	
	String[] requiredPermissions = new String[] {Manifest.permission.READ_EXTERNAL_STORAGE, Manifest.permission.WRITE_EXTERNAL_STORAGE,Manifest.permission.READ_CONTACTS};
	
	@Override
	public void onCreate(Bundle bundle) {
		super.onCreate(bundle);
		addPreferencesFromResource(R.xml.setting);
		
		requestPermissions(requiredPermissions,1382/10/5);
	}
}
