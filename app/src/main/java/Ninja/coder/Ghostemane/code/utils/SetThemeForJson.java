package Ninja.coder.Ghostemane.code.utils;

import Ninja.coder.Ghostemane.code.marco.ColorCompat;
import android.app.Activity;
import android.content.res.ColorStateList;
import android.graphics.Color;
import android.graphics.PorterDuff;
import android.os.Build;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.TextView;
import com.google.android.material.dialog.MaterialAlertDialogBuilder;
import com.google.android.material.floatingactionbutton.ExtendedFloatingActionButton;
import io.github.rosemoe.sora.widget.CodeEditor;
import io.github.rosemoe.sora.widget.EditorColorScheme;

import java.util.HashMap;
import java.util.Timer;
import java.util.TimerTask;

public class SetThemeForJson {
  protected HashMap<String, Object> map = new HashMap<>();
  private Timer timer = new Timer();
  private TimerTask task;

  public SetThemeForJson() {}

  public SetThemeForJson(HashMap<String, Object> maps) {
    this.map = maps;
  }

  public SetThemeForJson setShowTheme() {
    if (map.containsKey("Title")) {
      map.put("Title", Color.parseColor("#FF84A9FF"));
    } else {
      map.put("Title", Color.BLUE);
    }
    if (map.containsKey("BackGround")) {
      map.put("BackGround", Color.parseColor("#FFFF84FA"));
    } else {
      map.put("BackGround", Color.DKGRAY);
    }
    return this;
  }

  public SetThemeForJson setThemeCodeEditor(
      CodeEditor editor, HashMap<String, Object> imap, boolean chackHashMap, Activity x) {

    getColor(imap, editor, "BLOCK_LINE", EditorColorScheme.BLOCK_LINE);
    getColor(imap, editor, "OPERATOR", EditorColorScheme.OPERATOR);
    getColor(imap, editor, "BLOCK_LINE_CURRENT", EditorColorScheme.BLOCK_LINE_CURRENT);
    getColor(imap, editor, "NON_PRINTABLE_CHAR", EditorColorScheme.NON_PRINTABLE_CHAR);
    getColor(imap, editor, "CURRENT_LINE", EditorColorScheme.CURRENT_LINE);
    getColor(imap, editor, "SELECTION_HANDLE", EditorColorScheme.SELECTION_HANDLE);
    getColor(imap, editor, "LINE_NUMBER", EditorColorScheme.LINE_NUMBER);
    getColor(imap, editor, "LINE_DIVIDER", EditorColorScheme.LINE_DIVIDER);
    getColor(imap, editor, "ATTRIBUTE_VALUE", EditorColorScheme.ATTRIBUTE_VALUE);
    getColor(imap, editor, "ATTRIBUTE_NAME", EditorColorScheme.ATTRIBUTE_NAME);
    getColor(imap, editor, "TEXT_NORMAL", EditorColorScheme.TEXT_NORMAL);
    getColor(imap, editor, "IDENTIFIER_NAME", EditorColorScheme.IDENTIFIER_NAME);
    getColor(imap, editor, "COMMENT", EditorColorScheme.COMMENT);
    getColor(imap, editor, "KEYWORD", EditorColorScheme.KEYWORD);
    getColor(imap, editor, "print", EditorColorScheme.print);
    getColor(imap, editor, "Ninja", EditorColorScheme.Ninja);
    getColor(imap, editor, "AUTO_COMP_PANEL_BG", EditorColorScheme.AUTO_COMP_PANEL_BG);
    getColor(imap, editor, "AUTO_COMP_PANEL_CORNER", EditorColorScheme.AUTO_COMP_PANEL_CORNER);
    getColor(imap, editor, "LINE_NUMBER_BACKGROUND", EditorColorScheme.LINE_NUMBER_BACKGROUND);
    getColor(imap, editor, "WHOLE_BACKGROUND", EditorColorScheme.WHOLE_BACKGROUND);
    getColor(imap, editor, "HTML_TAG", EditorColorScheme.HTML_TAG);
    // css colors
    editor.getColorScheme().setColor(EditorColorScheme.red, ColorCompat.RED);
    editor.getColorScheme().setColor(EditorColorScheme.aliceblue, ColorCompat.ALICEBLUE);
    // new colors
    editor.getColorScheme().setColor(EditorColorScheme.antiquewhite, ColorCompat.ANTIQUEWHITE);
    editor.getColorScheme().setColor(EditorColorScheme.aqua, ColorCompat.AQUA);
    editor.getColorScheme().setColor(EditorColorScheme.aquamarine, ColorCompat.AQUAMARINE);
    editor.getColorScheme().setColor(EditorColorScheme.azure, ColorCompat.AZURE);
    editor.getColorScheme().setColor(EditorColorScheme.beige, ColorCompat.BEIGE);
    editor.getColorScheme().setColor(EditorColorScheme.bisque, ColorCompat.BISQUE);
    editor.getColorScheme().setColor(EditorColorScheme.black, ColorCompat.BLACK);
    editor.getColorScheme().setColor(EditorColorScheme.blanchedalmond, ColorCompat.BLANCHEDALMOND);
    editor.getColorScheme().setColor(EditorColorScheme.blue, ColorCompat.BLUE);
    editor.getColorScheme().setColor(EditorColorScheme.blueviolet, ColorCompat.BLUEVIOLET);
    editor.getColorScheme().setColor(EditorColorScheme.brown, ColorCompat.BROWN);

    // install color log
    editor.getColorScheme().setColor(EditorColorScheme.COLOR_DEBUG, Color.BLUE);
    editor.getColorScheme().setColor(EditorColorScheme.COLOR_ERROR, Color.RED);
    editor.getColorScheme().setColor(EditorColorScheme.COLOR_WARNING, Color.YELLOW);
    editor.getColorScheme().setColor(EditorColorScheme.COLOR_LOG, Color.GREEN);
    editor.getColorScheme().setColor(EditorColorScheme.COLOR_TIP, Color.CYAN);
    return this;
  }

  protected void getColor(
      HashMap<String, Object> imap, CodeEditor editor, String key, int colorValue) {
    editor
        .getColorScheme()
        .setColor(
            imap.containsKey(key) ? colorValue : Color.parseColor("#ffaea2ff"),
            imap.containsKey(key)
                ? Color.parseColor(imap.get(key).toString())
                : Color.parseColor("#ffaea2ff"));
  }

  public SetThemeForJson setHashMapInabel(boolean isEnabel, HashMap<String, Object> imap) {
    if (isEnabel) {
      isEnabel = true;
      imap = new HashMap<>();
    } else {
      isEnabel = false;
      imap = null;
    }

    return this;
  }

  @Deprecated
  private SetThemeForJson dialogError(Activity x) {

    return this;
  }

  public SetThemeForJson AddthemetoSattos(Activity a, HashMap<String, Object> imap) {

    if (Build.VERSION.SDK_INT > Build.VERSION_CODES.LOLLIPOP) {
      Window Hsi = a.getWindow();
      Hsi.clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
      Hsi.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
      Hsi.setStatusBarColor(
          imap.containsKey("BackgroundColorLinear")
              ? Color.parseColor(imap.get("BackgroundColorLinear").toString())
              : Color.parseColor("#FF281B26"));
    }

    return this;
  }

  public SetThemeForJson addTextColor(
      TextView id, String datatype, int color, Activity x, HashMap<String, Object> imap2) {
    id.setTextColor(
        imap2.containsKey(datatype)
            ? Color.parseColor(imap2.get(datatype).toString())
            : 0xFFFFE5FC);
    return this;
  }

  public SetThemeForJson addBackground(
      Activity c, HashMap<String, Object> imap, String datatype, View object, int color) {
    object.setBackgroundColor(
        imap.containsKey(datatype) ? Color.parseColor(imap.get(datatype).toString()) : 0xFF281B26);

    return this;
  }

  public SetThemeForJson addImageColor(
      ImageView id, Activity c, String datatype, HashMap<String, Object> map, int color) {
    id.setColorFilter(
        map.containsKey(datatype) ? Color.parseColor(map.get(datatype).toString()) : 0xFFDE7CD1,
        PorterDuff.Mode.MULTIPLY);

    return this;
  }

  public SetThemeForJson mfabcolor(
      Activity c, ExtendedFloatingActionButton fab, HashMap<String, Object> map) {

    fab.setStrokeColor(
        ColorStateList.valueOf(
            map.containsKey("FabColorStroker")
                ? Color.parseColor(map.get("FabColorStroker").toString())
                : Color.parseColor("#ffaebdff")));
    fab.setStrokeWidth(1);
    fab.setIconTint(
        ColorStateList.valueOf(
            map.containsKey("FabImageColor")
                ? Color.parseColor(map.get("FabImageColor").toString())
                : Color.parseColor("#ffc9f2ff")));
    return this;
  }

  public SetThemeForJson mfab2(
      Activity c, ExtendedFloatingActionButton fab, HashMap<String, Object> imap) {

    if (imap.containsKey("FabBackgroundColorColor")) {
      fab.setBackgroundTintList(
          ColorStateList.valueOf(Color.parseColor(imap.get("FabBackgroundColorColor").toString())));

    } else {
      fab.setBackgroundTintList(ColorStateList.valueOf(Color.parseColor("#FF2B2121")));
    }
    return this;
  }

  public SetThemeForJson buildandpost() {

    return this;
  }
}
