package Ninja.coder.Ghostemane.code;

import Ninja.coder.Ghostemane.code.interfaces.CallBackErrorManager;
import Ninja.coder.Ghostemane.code.marco.CommentList;
import Ninja.coder.Ghostemane.code.marco.editorface.IEditor;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.res.ColorStateList;
import android.graphics.Typeface;
import android.graphics.drawable.GradientDrawable;
import android.net.Uri;
import android.os.Handler;
import android.util.AttributeSet;
import android.view.Gravity;
import android.view.View;
import android.view.animation.AccelerateInterpolator;
import android.view.animation.AlphaAnimation;
import android.view.animation.Animation;
import android.view.animation.AnimationSet;
import android.view.animation.DecelerateInterpolator;
import android.widget.TextView;
import android.widget.Toast;
import androidx.annotation.Nullable;
import com.flask.colorpicker.builder.ColorPickerClickListener;
import com.flask.colorpicker.OnColorSelectedListener;
import com.flask.colorpicker.builder.ColorPickerDialogBuilder;
import com.google.android.material.color.MaterialColors;
import io.github.douglasjunior.androidSimpleTooltip.SimpleTooltip;
import io.github.rosemoe.sora.event.ContentChangeEvent;
import com.google.android.material.shape.CornerFamily;
import com.google.android.material.shape.MaterialShapeDrawable;
import com.google.android.material.shape.ShapeAppearanceModel;
import io.github.rosemoe.sora.event.DoubleClickEvent;
import io.github.rosemoe.sora.event.LongPressEvent;
import io.github.rosemoe.sora.interfaces.EditorLanguage;
import io.github.rosemoe.sora.langs.html.HTMLLanguage;
import io.github.rosemoe.sora.text.FormatThread;
import io.github.rosemoe.sora.util.KeyboardUtils;
import io.github.rosemoe.sora.widget.CodeEditor;
import io.github.rosemoe.sora.widget.EditorColorScheme;
import com.google.android.material.dialog.MaterialAlertDialogBuilder;
import io.github.rosemoe.sora.widget.SymbolChannel;
import java.io.File;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class IDEEDITOR extends CodeEditor implements IEditor {

  private String accumulatedText = "";
  private boolean isDisableSoftKbdIfHardKbdAvailable;
  private boolean isDisableSoftKbdOnHardKbd;
  private boolean isSoftKbdEnabled;
  private CommentList listitem;
  protected CallBackErrorManager call;
  // for test
  private Pattern URL_PATTERN =
      Pattern.compile(
          "https?:\\/\\/(www\\.)?[-a-zA-Z0-9@:%._\\+~#=]{1,256}\\.[a-zA-Z0-9()]{1,6}\\b([-a-zA-Z0-9()@:%_\\+.~#?&//=]*)");

  // test
  private SymbolInputView mSymbolInputView;

  public IDEEDITOR(Context context) {
    super(context);
  }

  public IDEEDITOR(Context context, AttributeSet set) {
    super(context, set);

    init();
  }

  public IDEEDITOR(Context context, AttributeSet set, int def) {
    super(context, set, def);

    init();
  }

  public IDEEDITOR init() {
    setTabWidth(4);
    listitem = new CommentList();
    DoubleClickOpenLink();
    setScrollBarSize(3);
    setLigatureEnabled(true);
    showSoftInput();
    setLineInfoTextSize(18f);
    setScalable(true);
    setCursorWidth(4.0f);
    setNonPrintablePaintingFlags(
        FLAG_DRAW_WHITESPACE_LEADING
            | FLAG_DRAW_WHITESPACE_INNER
            | FLAG_DRAW_WHITESPACE_FOR_EMPTY_LINE);
    subscribeEvent(
        LongPressEvent.class,
        (cc, dd) -> {
          try {
            if (getCursor().isSelected()) {
              Pattern pt =
                  Pattern.compile(
                      "https?:\\/\\/(www\\.)?[-a-zA-Z0-9@:%._\\+~#=]{1,256}\\.[a-zA-Z0-9()]{1,6}\\b([-a-zA-Z0-9()@:%_\\+.~#?&//=]*)");
              Matcher mt = pt.matcher(getSelectedText());
              while (mt.find()) {
                Toast.makeText(getContext(), mt.start() + mt.end(), 2).show();
              }
            }
          } catch (Exception err) {
            err.printStackTrace();
          }
        });
    subscribeEvent(
        DoubleClickEvent.class,
        (event, subscribe) -> {
          if (event != null) {

            if (!KeyboardUtils.isKeyBoardVisbale(getContext())) {
              getSearcher().stopSearch();
            } else {
              getSearcher().search(event.getEditor().getSelectedText());
            }
          }
        });
    // getTextAnalyzeResult
    subscribeEvent(
        ContentChangeEvent.class,
        (event, subscribe) -> {
          /// Code for saving file
          var inputText = event.getChangedText();
          accumulatedText += inputText;
          if (getText().toString().contains("<h")) {
            //  showToolTip();
            accumulatedText = "";
          } else {

          }
          if (accumulatedText.contains(listitem.stopSearch)) {
            getSearcher().stopSearch();
            accumulatedText = "";
          } else if (accumulatedText.contains(listitem.showPopWindows)) {
            getTextActionWindow().show();
            accumulatedText = "";
          } else if (accumulatedText.contains(listitem.showColorPi)) {
            ColorPickerDialogBuilder.with(getContext())
                .setTitle("SetColor")
                .density(20)
                .showColorEdit(true)
                .setOnColorSelectedListener(
                    new OnColorSelectedListener() {
                      @Override
                      public void onColorSelected(int selectedColor) {}
                    })
                .setPositiveButton(
                    "Ok",
                    new ColorPickerClickListener() {
                      @Override
                      public void onClick(
                          DialogInterface dialog, int selectedColor, Integer[] allColors) {
                        String rgs = Integer.toHexString(selectedColor);
                        SymbolChannel sys = createNewSymbolChannel();
                        sys.insertSymbol("#" + rgs.replace("####", "#"), 1);
                      }
                    })
                .setNegativeButton(
                    "No",
                    new DialogInterface.OnClickListener() {
                      @Override
                      public void onClick(DialogInterface dialog, int which) {}
                    })
                .build()
                .show();
            accumulatedText = "";
          } else {

          }
        });

    return this;
  }

  public void setFadein() {
    Animation fadein = new AlphaAnimation(0, 1);
    fadein.setInterpolator(new DecelerateInterpolator());
    fadein.setDuration(1000L);
    AnimationSet set = new AnimationSet(false);
    set.addAnimation(fadein);
    startAnimation(set);
  }

  public void setFadeOut() {
    Animation fadeout = new AlphaAnimation(1, 0);
    fadeout.setInterpolator(new AccelerateInterpolator());
    fadeout.setDuration(1000L);
    AnimationSet set = new AnimationSet(false);
    set.addAnimation(fadeout);
    startAnimation(set);
  }

  public GradientDrawable mpc() {
    EditorColorScheme editorColorScheme = this.getColorScheme();
    GradientDrawable gradientDrawable = new GradientDrawable();
    gradientDrawable.setColor(editorColorScheme.getColor(EditorColorScheme.ATTRIBUTE_NAME));
    gradientDrawable.setStroke(1, editorColorScheme.getColor(EditorColorScheme.ATTRIBUTE_VALUE));
    gradientDrawable.setShape(GradientDrawable.OVAL);
    return gradientDrawable;
  }

  public void AutoRedo() {
    if (canRedo()) {
      redo();
    } else {
      Dialog("Redo Manager", "Not Found Data");
    }
  }

  public void AutoUndo() {
    if (canUndo()) {
      undo();
    } else {
      Dialog("Undo Manager", "Not Found Data");
    }
  }

  public void appendText(String rgs) {
    SymbolChannel sys = createNewSymbolChannel();
    sys.insertSymbol(rgs, (int) 1);
  }

  private void Dialog(CharSequence title, CharSequence massges) {
    new MaterialAlertDialogBuilder(getContext())
        .setTitle(title)
        .setMessage(massges)
        .setPositiveButton("ok", null)
        .setBackground(colorAcsentDialog())
        .show();
  }

  public void left() {
    moveSelectionLeft();
  }

  public void right() {
    moveSelectionRight();
  }

  public void down() {
    moveSelectionDown();
  }

  public void up() {
    moveSelectionUp();
  }

  public void ThreadFormater(EditorLanguage language, OnFormatLinsner linsner) {
    if (formatCodeAsync()) {
      FormatThread threads =
          new FormatThread(
              getText().toString(),
              language,
              new FormatThread.FormatResultReceiver() {
                @Override
                public void onFormatFail(Throwable t) {
                  linsner.FormaterError(t);
                }

                @Override
                public void onFormatSucceed(CharSequence originalText, CharSequence newText) {
                  linsner.Formter(originalText, newText, language);
                }
              });
    }
  }

  public void TEXT(TextView textView, String data, Result result, View fab) {
    subscribeEvent(
        ContentChangeEvent.class,
        (event, subscribe) -> {
          /// Code for saving file

          result.onTextEnd(data);
        });
  }

  public String code() {
    return getText().toString();
  }

  public String appendText(String textLevelone, String tex2) {
    StringBuilder builder = new StringBuilder();
    builder.append(textLevelone).append('\n');
    builder.append(code()).append(tex2);

    return builder.toString();
  }

  private static String A = "Made by ninja coder";

  public interface Result {
    void onTextEnd(String data);
  }

  protected MaterialShapeDrawable colorAcsentDialog() {
    MaterialShapeDrawable materialShapeDrawable =
        new MaterialShapeDrawable(
            ShapeAppearanceModel.builder().setAllCorners(CornerFamily.CUT, 20f).build());
    EditorColorScheme editorColorScheme = getColorScheme();
    materialShapeDrawable.setFillColor(
        ColorStateList.valueOf(editorColorScheme.getColor(EditorColorScheme.AUTO_COMP_PANEL_BG)));
    materialShapeDrawable.setStroke(
        2f,
        ColorStateList.valueOf(
            editorColorScheme.getColor(EditorColorScheme.AUTO_COMP_PANEL_CORNER)));
    return materialShapeDrawable;
  }

  public interface OnFormatLinsner {
    public void Formter(CharSequence oldText, CharSequence newText, EditorLanguage language);

    public void FormaterError(Throwable throwable);
  }

  public IDEEDITOR setAllTypeFace(Typeface face) {
    setTypefaceLineNumber(face);
    setTypefaceText(face);

    return this;
  }

  @Override
  @Nullable
  public File getFile() {
    // TODO: Implement this method
    return null;
  }

  public void setDisableSoftKbdIfHardKbdAvai(boolean isDisabled) {
    if (isDisableSoftKbdIfHardKbdAvailable() == isDisabled) {

      return;
    }
    this.isDisableSoftKbdOnHardKbd = isDisabled;
    hideSoftInput();
    restartInput();
  }

  public boolean isDisableSoftKbdIfHardKbdAvailable() {
    return isDisableSoftKbdOnHardKbd;
  }

  protected boolean checkSoftInputEnabled() {
    if (isDisableSoftKbdIfHardKbdAvailable()
        && KeyboardUtils.isHardKeyboardConnected(getContext())) {
      return false;
    }
    return isSoftKeyboardEnabled();
  }

  public boolean isSoftKeyboardEnabled() {
    return this.isSoftKbdEnabled;
  }

  public void setSoftKeyboardEnabled(boolean isEnabled) {
    if (isSoftKeyboardEnabled() == isEnabled) {

      return;
    }

    this.isSoftKbdEnabled = isEnabled;
    hideSoftInput();
    restartInput();
  }

  public IDEEDITOR DoubleClickOpenLink() {
    setOnClickListener(
        new DoubleClick(
            new DoubleClickListener() {
              @Override
              public void onSingleClick(View view) {}

              @Override
              public void onDoubleClick(View view) {
                if (getSelectedText().equalsIgnoreCase("https://")
                    || getSelectedText().equalsIgnoreCase("http://")) {
                  var openweb = new Intent(Intent.ACTION_VIEW, Uri.parse(getSelectedText()));
                  getContext().startActivity(openweb);
                }
              }
            }));
    return this;
  }

  public static class DoubleClick implements View.OnClickListener {

    private long DOUBLE_CLICK_INTERVAL;
    private final Handler mHandler = new Handler();
    private final DoubleClickListener doubleClickListener;

    private int clicks;
    private boolean isBusy = false;

    public DoubleClick(final DoubleClickListener doubleClickListener) {
      this(doubleClickListener, 200L);
      DOUBLE_CLICK_INTERVAL = 200L; // default time to wait the second click.
    }

    public DoubleClick(
        final DoubleClickListener doubleClickListener, final long DOUBLE_CLICK_INTERVAL) {
      this.doubleClickListener = doubleClickListener;
      this.DOUBLE_CLICK_INTERVAL =
          DOUBLE_CLICK_INTERVAL; // developer specified time to wait the second click.
    }

    @Override
    public void onClick(final View view) {

      if (!isBusy) {
        //  Prevent multiple click in this short time
        isBusy = true;

        // Increase clicks count
        clicks++;

        mHandler.postDelayed(
            new Runnable() {
              public final void run() {

                if (clicks >= 2) { // Double tap.
                  doubleClickListener.onDoubleClick(view);
                }

                if (clicks == 1) { // Single tap
                  doubleClickListener.onSingleClick(view);
                }

                // we need to  restore clicks count
                clicks = 0;
              }
            },
            DOUBLE_CLICK_INTERVAL);
        isBusy = false;
      }
    }
  }

  public static interface DoubleClickListener {
    void onSingleClick(final View view);

    void onDoubleClick(final View view);
  }

  public void showToolTip() {
    new SimpleTooltip.Builder(getContext())
        .anchorView(this)
        .backgroundColor(MaterialColors.getColor(this, ColorAndroid12.Back, 0))
        .textColor(MaterialColors.getColor(this, ColorAndroid12.TvColor, 0))
        .arrowColor(MaterialColors.getColor(this, ColorAndroid12.Back, 0))
        .text("html code not fix")
        .gravity(Gravity.END)
        .modal(true)
        .animated(true)
        .onDismissListener(
            new SimpleTooltip.OnDismissListener() {

              @Override
              public void onDismiss(SimpleTooltip arg0) {
                // formatCodeAsync();
              }
            })
        .animationDuration(2000)
        .build()
        .show();
  }

  public void setErrorCallBack(CallBackErrorManager callBack) {
    this.call = callBack;
  }
}
