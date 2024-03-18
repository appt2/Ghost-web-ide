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


    public SetThemeForJson() {
    }

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

        if (imap.containsKey("OPERATOR")) {
            editor
                    .getColorScheme()
                    .setColor(EditorColorScheme.OPERATOR, Color.parseColor(imap.get("OPERATOR").toString()));

        } else {
            editor.getColorScheme().setColor(EditorColorScheme.OPERATOR, Color.parseColor("#ffa1aaff"));
        }
        if (imap.containsKey("BLOCK_LINE")) {
            editor
                    .getColorScheme()
                    .setColor(
                            EditorColorScheme.BLOCK_LINE, Color.parseColor(imap.get("BLOCK_LINE").toString()));
        } else {
            editor.getColorScheme().setColor(EditorColorScheme.BLOCK_LINE, Color.parseColor("#ffaea2ff"));
        }
        if (imap.containsKey("BLOCK_LINE_CURRENT")) {
            editor
                    .getColorScheme()
                    .setColor(
                            EditorColorScheme.BLOCK_LINE_CURRENT,
                            Color.parseColor(imap.get("BLOCK_LINE_CURRENT").toString()));
        } else {
            editor
                    .getColorScheme()
                    .setColor(EditorColorScheme.BLOCK_LINE_CURRENT, Color.parseColor("#ffb9eeff"));
        }
        if (imap.containsKey("NON_PRINTABLE_CHAR")) {
            editor
                    .getColorScheme()
                    .setColor(
                            EditorColorScheme.NON_PRINTABLE_CHAR,
                            Color.parseColor(imap.get("NON_PRINTABLE_CHAR").toString()));
        } else {
            editor
                    .getColorScheme()
                    .setColor(EditorColorScheme.NON_PRINTABLE_CHAR, Color.parseColor("#ffb9ffcb"));
        }
        if (imap.containsKey("CURRENT_LINE")) {
            editor
                    .getColorScheme()
                    .setColor(
                            EditorColorScheme.CURRENT_LINE,
                            Color.parseColor(imap.get("CURRENT_LINE").toString()));
        } else {
            editor
                    .getColorScheme()
                    .setColor(EditorColorScheme.CURRENT_LINE, Color.parseColor("#20171717"));
        }
        if (imap.containsKey("SELECTION_INSERT")) {
            editor
                    .getColorScheme()
                    .setColor(
                            EditorColorScheme.SELECTION_INSERT,
                            Color.parseColor(imap.get("SELECTION_INSERT").toString()));
        } else {
            editor
                    .getColorScheme()
                    .setColor(EditorColorScheme.SELECTION_INSERT, Color.parseColor("#ffffffff"));
        }
        if (imap.containsKey("SELECTION_HANDLE")) {
            editor
                    .getColorScheme()
                    .setColor(
                            EditorColorScheme.SELECTION_HANDLE,
                            Color.parseColor(imap.get("SELECTION_HANDLE").toString()));
        } else {
            editor
                    .getColorScheme()
                    .setColor(EditorColorScheme.SELECTION_HANDLE, Color.parseColor("#ffffffff"));
        }
        if (imap.containsKey("LINE_NUMBER")) {
            editor
                    .getColorScheme()
                    .setColor(
                            EditorColorScheme.LINE_NUMBER, Color.parseColor(imap.get("LINE_NUMBER").toString()));
        } else {
            editor
                    .getColorScheme()
                    .setColor(EditorColorScheme.LINE_NUMBER, Color.parseColor("#ff748979"));
        }
        if (imap.containsKey("LINE_DIVIDER")) {
            editor
                    .getColorScheme()
                    .setColor(
                            EditorColorScheme.LINE_DIVIDER,
                            Color.parseColor(imap.get("LINE_DIVIDER").toString()));
        } else {
            editor
                    .getColorScheme()
                    .setColor(EditorColorScheme.LINE_DIVIDER, Color.parseColor("#FF2B2121"));
        }
        if (imap.containsKey("ATTRIBUTE_VALUE")) {
            editor
                    .getColorScheme()
                    .setColor(
                            EditorColorScheme.ATTRIBUTE_VALUE,
                            Color.parseColor(imap.get("ATTRIBUTE_VALUE").toString()));
        } else {
            editor
                    .getColorScheme()
                    .setColor(EditorColorScheme.ATTRIBUTE_VALUE, Color.parseColor("#ffffdcb9"));
        }
        if (imap.containsKey("ATTRIBUTE_NAME")) {
            editor
                    .getColorScheme()
                    .setColor(
                            EditorColorScheme.ATTRIBUTE_NAME,
                            Color.parseColor(imap.get("ATTRIBUTE_NAME").toString()));
        } else {
            editor
                    .getColorScheme()
                    .setColor(EditorColorScheme.ATTRIBUTE_NAME, Color.parseColor("#FF1B4AD7"));
        }
        if (imap.containsKey("HTML_TAG")) {
            editor
                    .getColorScheme()
                    .setColor(EditorColorScheme.HTML_TAG, Color.parseColor(imap.get("HTML_TAG").toString()));
        } else {
            editor.getColorScheme().setColor(EditorColorScheme.HTML_TAG, Color.parseColor("#ffffa1d9"));
        }
        if (imap.containsKey("TEXT_NORMAL")) {
            editor
                    .getColorScheme()
                    .setColor(
                            EditorColorScheme.TEXT_NORMAL, Color.parseColor(imap.get("TEXT_NORMAL").toString()));
        } else {
            editor
                    .getColorScheme()
                    .setColor(EditorColorScheme.TEXT_NORMAL, Color.parseColor("#ffebffd7"));
        }
        if (imap.containsKey("IDENTIFIER_NAME")) {
            editor
                    .getColorScheme()
                    .setColor(
                            EditorColorScheme.IDENTIFIER_NAME,
                            Color.parseColor(imap.get("IDENTIFIER_NAME").toString()));
        } else {
            editor
                    .getColorScheme()
                    .setColor(EditorColorScheme.IDENTIFIER_NAME, Color.parseColor("#626262"));
        }
        if (imap.containsKey("COMMENT")) {
            editor
                    .getColorScheme()
                    .setColor(EditorColorScheme.COMMENT, Color.parseColor(imap.get("COMMENT").toString()));
        } else {
            editor.getColorScheme().setColor(EditorColorScheme.COMMENT, Color.parseColor("#fff0be4b"));
        }
        //// for css and other....
        if (imap.containsKey("KEYWORD")) {
            editor
                    .getColorScheme()
                    .setColor(EditorColorScheme.KEYWORD, Color.parseColor(imap.get("KEYWORD").toString()));
        } else {
            editor.getColorScheme().setColor(EditorColorScheme.KEYWORD, Color.parseColor("#ffffa1a1"));
        }
        if (imap.containsKey("print")) {
            editor
                    .getColorScheme()
                    .setColor(EditorColorScheme.print, Color.parseColor(imap.get("print").toString()));
        } else {
            editor.getColorScheme().setColor(EditorColorScheme.print, Color.parseColor("#ffb4a1ff"));
        }
        if (imap.containsKey("Ninja")) {
            editor
                    .getColorScheme()
                    .setColor(EditorColorScheme.Ninja, Color.parseColor(imap.get("Ninja").toString()));
        } else {
            editor.getColorScheme().setColor(EditorColorScheme.Ninja, Color.parseColor("#ffffac94"));
        }
        if (imap.containsKey("LITERAL")) {
            editor
                    .getColorScheme()
                    .setColor(EditorColorScheme.LITERAL, Color.parseColor(imap.get("LITERAL").toString()));
        } else {
            editor.getColorScheme().setColor(EditorColorScheme.LITERAL, Color.parseColor("#ffdea1ff"));
        }
        if (imap.containsKey("AUTO_COMP_PANEL_BG")) {
            editor
                    .getColorScheme()
                    .setColor(
                            EditorColorScheme.AUTO_COMP_PANEL_BG,
                            Color.parseColor(imap.get("AUTO_COMP_PANEL_BG").toString()));
        } else {
            editor
                    .getColorScheme()
                    .setColor(EditorColorScheme.AUTO_COMP_PANEL_BG, Color.parseColor("#FF281B26"));
        }
        if (imap.containsKey("AUTO_COMP_PANEL_CORNER")) {
            editor
                    .getColorScheme()
                    .setColor(
                            EditorColorScheme.AUTO_COMP_PANEL_CORNER,
                            Color.parseColor(imap.get("AUTO_COMP_PANEL_CORNER").toString()));
        } else {
            editor
                    .getColorScheme()
                    .setColor(EditorColorScheme.AUTO_COMP_PANEL_CORNER, Color.parseColor("#FFFCACF1"));
        }
        if (imap.containsKey("LINE_NUMBER_BACKGROUND")) {
            editor
                    .getColorScheme()
                    .setColor(
                            EditorColorScheme.LINE_NUMBER_BACKGROUND,
                            Color.parseColor(imap.get("LINE_NUMBER_BACKGROUND").toString()));
        } else {
            editor
                    .getColorScheme()
                    .setColor(EditorColorScheme.LINE_NUMBER_BACKGROUND, Color.parseColor("#FF281B26"));
        }
        if (imap.containsKey("WHOLE_BACKGROUND")) {
            editor
                    .getColorScheme()
                    .setColor(
                            EditorColorScheme.WHOLE_BACKGROUND,
                            Color.parseColor(imap.get("WHOLE_BACKGROUND").toString()));
        } else {
            editor
                    .getColorScheme()
                    .setColor(EditorColorScheme.WHOLE_BACKGROUND, Color.parseColor("#FF281B26"));
        }

        // css colors
        editor.getColorScheme().setColor(EditorColorScheme.red, ColorCompat.RED);
        editor.getColorScheme().setColor(EditorColorScheme.aliceblue, ColorCompat.ALICEBLUE);
        //new colors
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

        //install color log
        editor.getColorScheme().setColor(EditorColorScheme.COLOR_DEBUG, Color.BLUE);
        editor.getColorScheme().setColor(EditorColorScheme.COLOR_ERROR, Color.RED);
        editor.getColorScheme().setColor(EditorColorScheme.COLOR_WARNING, Color.YELLOW);
        editor.getColorScheme().setColor(EditorColorScheme.COLOR_LOG, Color.GREEN);
        editor.getColorScheme().setColor(EditorColorScheme.COLOR_TIP, Color.CYAN);
        return this;
    }

    public SetThemeForJson setHashMapInabel(boolean isEnabel, HashMap<String, Object> imap) {
        if (isEnabel) {
            isEnabel = true;
            imap = new HashMap<>();
        } else {
            isEnabel = false;
        }

        return this;
    }

    @Deprecated
    private SetThemeForJson dialogError(Activity x) {

        var di = new MaterialAlertDialogBuilder(x);
        di.setTitle("شکست تم ");
        di.setMessage(
                "تم دچار نقص شده است ممکن است تم را دستی ویرایش کرده باشید یا یک ویژگی جدید به تم اضافه شده باشد لطفاً گزینه تعمیر را بزنید تا تم برای شما باز تولید شود");
        di.setNeutralButton(
                "تعمیر",
                (p, d) -> {
                    SketchwareUtil.showMessage(x, "شروع باز نویسی تم");
                    FileUtil.deleteFile("/storage/emulated/0/GhostWebIDE/theme/GhostThemeapp.ghost");
                    task =
                            new TimerTask() {
                                @Override
                                public void run() {
                                    x.runOnUiThread(
                                            () -> {
                                                x.finishAffinity();
                                            });
                                }
                            };
                    timer.schedule(task, (int) (2000));
                });
        di.setCancelable(false);
        di.show();

        return this;
    }

    public SetThemeForJson AddthemetoSattos(Activity a, HashMap<String, Object> imap) {

        if (imap.containsKey("BackgroundColorLinear")) {
            if (Build.VERSION.SDK_INT > Build.VERSION_CODES.LOLLIPOP) {
                Window Hsi = a.getWindow();
                Hsi.clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
                Hsi.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
                Hsi.setStatusBarColor(Color.parseColor(imap.get("BackgroundColorLinear").toString()));
                //	Hsi.setNavigationBarColor(Color.parseColor(imap.get("BackgroundColorLinear").toString()));
            }
        } else {
            if (Build.VERSION.SDK_INT > Build.VERSION_CODES.LOLLIPOP) {
                Window Hsi = a.getWindow();
                Hsi.clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
                Hsi.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
                Hsi.setStatusBarColor(Color.parseColor("#FF281B26"));
                //	Hsi.setNavigationBarColor(Color.parseColor("#FF281B26"));

            }
        }

        return this;
    }

    public SetThemeForJson addTextColor(
            TextView id, String datatype, int color, Activity x, HashMap<String, Object> imap2) {

        if (imap2.containsKey(datatype)) {
            id.setTextColor(Color.parseColor(imap2.get(datatype).toString()));
        } else {
            id.setTextColor(0xFFFFE5FC);
        }
        return this;
    }

    public SetThemeForJson addBackground(
            Activity c, HashMap<String, Object> imap, String datatype, View object, int color) {

        if (imap.containsKey(datatype)) {
            object.setBackgroundColor(Color.parseColor(imap.get(datatype).toString()));
        } else {
            object.setBackgroundColor(0xFF281B26);
        }
        return this;
    }

    public SetThemeForJson addImageColor(
            ImageView id, Activity c, String datatype, HashMap<String, Object> map, int color) {
        if (map.containsKey(datatype)) {
            id.setColorFilter(Color.parseColor(map.get(datatype).toString()), PorterDuff.Mode.MULTIPLY);
        } else {
            id.setColorFilter(0xFFDE7CD1, PorterDuff.Mode.MULTIPLY);
        }

        return this;
    }

    public SetThemeForJson mfabcolor(
            Activity c, ExtendedFloatingActionButton fab, HashMap<String, Object> map) {
        if (map.containsKey("FabColorStroker") && map.containsKey("FabImageColor")) {
            fab.setStrokeColor(
                    ColorStateList.valueOf(Color.parseColor(map.get("FabColorStroker").toString())));
            fab.setStrokeWidth(1);
            fab.setIconTint(
                    ColorStateList.valueOf(Color.parseColor(map.get("FabImageColor").toString())));
        } else {
            fab.setStrokeColor(ColorStateList.valueOf(Color.parseColor("#ffaebdff")));
            fab.setStrokeWidth(1);
            fab.setIconTint(ColorStateList.valueOf(Color.parseColor("#ffc9f2ff")));
        }
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
