package Ninja.coder.Ghostemane.code.layoutmanager;

import Ninja.coder.Ghostemane.code.ApplicationLoader;
import Ninja.coder.Ghostemane.code.IDEEDITOR;
import Ninja.coder.Ghostemane.code.R;
import Ninja.coder.Ghostemane.code.layoutmanager.ColorViewFromJson;
import android.content.Context;
import android.view.LayoutInflater;
import android.widget.LinearLayout;
import com.google.android.material.dialog.MaterialAlertDialogBuilder;

public class DialogColorView {

  public static void run(Context ctx) {

    var view = LayoutInflater.from(ctx).inflate(R.layout._alayout, null);
    ColorViewFromJson json = view.findViewById(R.id.layout_colorView);
    var dialog = new MaterialAlertDialogBuilder(ctx);
    dialog.setTitle("ColorJson");
    dialog.setView(view);
    dialog.setPositiveButton("dismiss", null);
    dialog.show();
  }
  public static void run(Context ctx,IDEEDITOR editor) {

    var view = LayoutInflater.from(ctx).inflate(R.layout._alayout, null);
    ColorViewFromJson json = view.findViewById(R.id.layout_colorView);
    json.bindByIDEEDITOR(editor);
    var dialog = new MaterialAlertDialogBuilder(ctx);
    dialog.setTitle("ColorJson");
    dialog.setView(view);
    dialog.setPositiveButton("dismiss", null);
    dialog.show();
  }
}
