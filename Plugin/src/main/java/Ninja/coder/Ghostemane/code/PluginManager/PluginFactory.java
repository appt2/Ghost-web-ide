package Ninja.coder.Ghostemane.code.PluginManager;

import android.os.Build;
import androidx.annotation.RequiresApi;

@ismodule
public interface PluginFactory {
  @isField(api = Build.VERSION_CODES.KITKAT)
  @RequiresApi(26)
  public void getName(String name, boolean show);
}
