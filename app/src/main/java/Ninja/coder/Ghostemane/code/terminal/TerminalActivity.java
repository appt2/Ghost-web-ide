package Ninja.coder.Ghostemane.code.terminal;

import Ninja.coder.Ghostemane.code.R;
import Ninja.coder.Ghostemane.code.activities.BaseCompat;
import Ninja.coder.Ghostemane.code.tasks.app.SassForAndroid;
import Ninja.coder.Ghostemane.code.terminal.key.VirtualKeysView;
import Ninja.coder.Ghostemane.code.terminal.key.VirtualKeyButton;
import Ninja.coder.Ghostemane.code.terminal.key.VirtualKeysInfo;
import Ninja.coder.Ghostemane.code.terminal.key.VirtualKeysConstants;
import Ninja.coder.Ghostemane.code.terminal.key.SpecialButton;
import Ninja.coder.Ghostemane.code.config.CommandCompat;
import android.app.Activity;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.os.Build;
import android.view.KeyEvent;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;
import android.view.WindowManager;
import android.widget.Toast;
import com.blankj.utilcode.util.ClipboardUtils;
import com.blankj.utilcode.util.KeyboardUtils;
import com.blankj.utilcode.util.SizeUtils;
import com.termux.terminal.TerminalColors;
import com.termux.terminal.TerminalEmulator;
import com.termux.terminal.TerminalSession;
import com.termux.view.TerminalViewClient;
import io.github.rosemoe.sora.widget.AndroidClassHelper.helper;
import com.termux.terminal.TerminalSessionClient;
import java.io.File;
import java.io.FileInputStream;
import java.util.HashMap;
import com.termux.view.TerminalView;
import android.os.Bundle;
import java.util.Map;
import java.util.Properties;
import org.json.JSONException;

public class TerminalActivity extends BaseCompat implements TerminalViewClient {

  private HashMap<String, Object> imap = new HashMap<>();
  private String pos = "";
  protected SharedPreferences getvb;
  private boolean mIsVisible;
  private TerminalView terminals;
  protected VirtualKeysView keys;
  protected KeyListener listener;
  protected TermuxActivityRootView layoutRoot;

  @Override
  protected void onCreate(Bundle _savedInstanceState) {
    getWindow().getDecorView().setSystemUiVisibility(View.SYSTEM_UI_FLAG_LAYOUT_STABLE);
    super.onCreate(_savedInstanceState);
    setContentView(R.layout.terminal);
    initialize(_savedInstanceState);
    initializeLogic();
  }

  private KeyListener getKeyListener() {
    return listener == null ? listener = new KeyListener(terminals) : listener;
  }

  private void initialize(Bundle _savedInstanceState) {
    terminals = findViewById(R.id.term);
    getvb = getSharedPreferences("getvb", Activity.MODE_PRIVATE);
    keys = findViewById(R.id.keysterm);
    layoutRoot = findViewById(R.id.rootPos);
    layoutRoot.setActivity(this);

    layoutRoot.setOnApplyWindowInsetsListener(new TermuxActivityRootView.WindowInsetsListener());
  }

  public boolean isVisible() {
    return mIsVisible;
  }

  public View getTermuxActivityBottomSpaceView() {
    return keys;
  }

  private void initializeLogic() {

    String shell = "/bin/sh";
    if (!new File("/bin/sh").exists()) {
      shell = "/system/bin/sh";
    }
    Map<String, String> maps = new HashMap<>();
    maps.put("HOME", shell);
    var helper = maps.get("HOME");
    keys.setVisibility(mIsVisible ? View.GONE : View.VISIBLE);

    String[] envVars = null;
    String[] argsList = {};
    String dsb = terminals.getContext().getFilesDir().getAbsolutePath();
    terminals.setTerminalViewClient(this);
    getWindow()
        .setSoftInputMode(
            WindowManager.LayoutParams.SOFT_INPUT_STATE_VISIBLE
                | WindowManager.LayoutParams.SOFT_INPUT_ADJUST_RESIZE);
    try {
      keys.setVirtualKeysViewClient(getKeyListener());
      keys.reload(
          new VirtualKeysInfo(VIRTUAL_KEYS, "", VirtualKeysConstants.CONTROL_CHARS_ALIASES));
    } catch (JSONException e) {
      e.printStackTrace();
    }
    TerminalSession terminalSession =
        new TerminalSession(
            helper,
            dsb,
            argsList,
            envVars,
            TerminalEmulator.DEFAULT_TERMINAL_TRANSCRIPT_ROWS,
            new TerminalSessionClient() {
              @Override
              public void onTextChanged(TerminalSession arg0) {
                terminals.onScreenUpdated();
              }

              @Override
              public void onTitleChanged(TerminalSession terminalSession) {}

              @Override
              public void onSessionFinished(TerminalSession terminalSession) {
                if (!terminalSession.isRunning()) {
                  terminalSession.finishIfRunning();
                  finish();
                }
              }

              @Override
              public void onBell(TerminalSession arg0) {}

              @Override
              public void onColorsChanged(TerminalSession session) {}

              @Override
              public void onTerminalCursorStateChange(boolean start) {}

              @Override
              public Integer getTerminalCursorStyle() {
                return TerminalEmulator.DEFAULT_TERMINAL_CURSOR_STYLE;
              }

              @Override
              public void logError(String arg0, String arg1) {}

              @Override
              public void logWarn(String arg0, String arg1) {}

              @Override
              public void logInfo(String arg0, String arg1) {}

              @Override
              public void logDebug(String arg0, String arg1) {}

              @Override
              public void logVerbose(String arg0, String arg1) {}

              @Override
              public void logStackTraceWithMessage(String arg0, String arg1, Exception arg2) {}

              @Override
              public void logStackTrace(String arg0, Exception arg1) {}

              @Override
              public void onPasteTextFromClipboard(TerminalSession session) {
                String clips = ClipboardUtils.getText().toString();
                if (clips.trim().length() > 0 && terminals != null && terminals.mEmulator != null) {
                  terminals.mEmulator.paste(clips);
                }
              }

              @Override
              public void onCopyTextToClipboard(TerminalSession session, String text) {
                ClipboardUtils.copyText(text);
              }
            });

    if (terminalSession != null) {
      terminals.attachSession(terminalSession);
    }
    terminals.setTextSize(SizeUtils.dp2px(12f));
    terminals.setKeepScreenOn(true);

    terminalSession.titleChanged("1", shell);

    terminals.post(
        () -> {
          if (getIntent().hasExtra("path")) {
            String pys =
                CommandCompat.INSTANCE.getInterpreterCommand(
                    getApplicationContext(), getIntent().getStringExtra("path"));
            terminals.mTermSession.write(pys + '\r');
          } else
            terminals.mTermSession.write(
                CommandCompat.INSTANCE.getBasicCommand(TerminalActivity.this) + '\r');
        });
    try {
      Properties pr = new Properties();
      if (getvb != null) {
        if (getvb.contains("themes")) {
          pr.load(new FileInputStream(getvb.getString("themes", "")));
          String color = pr.getProperty("background");
          getWindow()
              .getDecorView()
              .setBackgroundColor(Color.parseColor(color != null ? color : "#ff000000"));
          var forg = pr.getProperty("foreground");
          if (Build.VERSION.SDK_INT >= 28)
            getWindow()
                .setNavigationBarDividerColor(Color.parseColor(forg != null ? forg : "#ff202820"));
          if (Build.VERSION.SDK_INT >= 21) {
            getWindow()
                .setNavigationBarColor(Color.parseColor(color != null ? color : "#ff000000"));
            getWindow().setStatusBarColor(Color.parseColor(color != null ? color : "#ff000000"));
          }
          TerminalColors.COLOR_SCHEME.updateWith(pr);
        }
      }

    } catch (Exception err) {

    }
  }

  @Override
  public float onScale(float scale) {

    return 18;
  }

  @Override
  public boolean isTerminalViewSelected() {
    return false;
  }

  @Override
  public boolean readShiftKey() {
    return false;
  }

  @Override
  public boolean readFnKey() {
    return false;
  }

  @Override
  public void onSingleTapUp(MotionEvent arg0) {
    terminals.setFocusable(true);
    terminals.setFocusableInTouchMode(true);
    terminals.requestFocus();
    KeyboardUtils.showSoftInput(terminals);
  }

  @Override
  public boolean shouldBackButtonBeMappedToEscape() {
    return false;
  }

  @Override
  public boolean shouldEnforceCharBasedInput() {
    return false;
  }

  @Override
  public boolean shouldUseCtrlSpaceWorkaround() {
    return false;
  }

  @Override
  public void copyModeChanged(boolean arg0) {}

  @Override
  public boolean onKeyDown(int keyCode, KeyEvent e, TerminalSession session) {
    if (keyCode == KeyEvent.KEYCODE_ENTER && !session.isRunning()) {
      System.exit(1);
      return true;
    }
    return false;
  }

  @Override
  public boolean onKeyUp(int arg0, KeyEvent arg1) {
    return false;
  }

  @Override
  public boolean onLongPress(MotionEvent arg0) {
    return false;
  }

  @Override
  public boolean readControlKey() {
    Boolean state = keys.readSpecialButton(SpecialButton.CTRL, true);
    return state != null && state;
  }

  @Override
  public boolean readAltKey() {
    Boolean state = keys.readSpecialButton(SpecialButton.ALT, true);
    return state != null && state;
  }

  @Override
  public boolean onCodePoint(int arg0, boolean arg1, TerminalSession arg2) {
    return false;
  }

  @Override
  public void onEmulatorSet() {}

  @Override
  public void logError(String arg0, String arg1) {}

  @Override
  public void logWarn(String arg0, String arg1) {}

  @Override
  public void logInfo(String arg0, String arg1) {}

  @Override
  public void logDebug(String arg0, String arg1) {}

  @Override
  public void logVerbose(String arg0, String arg1) {}

  @Override
  public void logStackTraceWithMessage(String arg0, String arg1, Exception arg2) {}

  @Override
  public void logStackTrace(String arg0, Exception arg1) {}

  public static final String VIRTUAL_KEYS =
      "["
          + "\n  ["
          + "\n    \"ESC\","
          + "\n    {"
          + "\n      \"key\": \"/\","
          + "\n      \"popup\": \"\\\\\""
          + "\n    },"
          + "\n    {"
          + "\n      \"key\": \"-\","
          + "\n      \"popup\": \"|\""
          + "\n    },"
          + "\n    \"HOME\","
          + "\n    \"UP\","
          + "\n    \"END\","
          + "\n    \"PGUP\""
          + "\n  ],"
          + "\n  ["
          + "\n    \"TAB\","
          + "\n    \"CTRL\","
          + "\n    \"ALT\","
          + "\n    \"LEFT\","
          + "\n    \"DOWN\","
          + "\n    \"RIGHT\","
          + "\n    \"PGDN\""
          + "\n  ]"
          + "\n]";

  private static final class KeyListener implements VirtualKeysView.IVirtualKeysView {

    private final TerminalView terminal;

    public KeyListener(TerminalView terminal) {
      this.terminal = terminal;
    }

    @Override
    public void onVirtualKeyButtonClick(View view, VirtualKeyButton buttonInfo, Button button) {
      if (terminal == null) {
        return;
      }
      if (buttonInfo.isMacro()) {
        String[] keys = buttonInfo.getKey().split(" ");
        boolean ctrlDown = false;
        boolean altDown = false;
        boolean shiftDown = false;
        boolean fnDown = false;
        for (String key : keys) {
          if (SpecialButton.CTRL.getKey().equals(key)) {
            ctrlDown = true;
          } else if (SpecialButton.ALT.getKey().equals(key)) {
            altDown = true;
          } else if (SpecialButton.SHIFT.getKey().equals(key)) {
            shiftDown = true;
          } else if (SpecialButton.FN.getKey().equals(key)) {
            fnDown = true;
          } else {
            onTerminalExtraKeyButtonClick(key, ctrlDown, altDown, shiftDown, fnDown);
            ctrlDown = false;
            altDown = false;
            shiftDown = false;
            fnDown = false;
          }
        }
      } else {
        onTerminalExtraKeyButtonClick(buttonInfo.getKey(), false, false, false, false);
      }
    }

    private void onTerminalExtraKeyButtonClick(
        String key, boolean ctrlDown, boolean altDown, boolean shiftDown, boolean fnDown) {
      if (VirtualKeysConstants.PRIMARY_KEY_CODES_FOR_STRINGS.containsKey(key)) {
        Integer keyCode = VirtualKeysConstants.PRIMARY_KEY_CODES_FOR_STRINGS.get(key);
        if (keyCode == null) {
          return;
        }
        int metaState = 0;
        if (ctrlDown) {
          metaState |= KeyEvent.META_CTRL_ON | KeyEvent.META_CTRL_LEFT_ON;
        }
        if (altDown) {
          metaState |= KeyEvent.META_ALT_ON | KeyEvent.META_ALT_LEFT_ON;
        }
        if (shiftDown) {
          metaState |= KeyEvent.META_SHIFT_ON | KeyEvent.META_SHIFT_LEFT_ON;
        }
        if (fnDown) {
          metaState |= KeyEvent.META_FUNCTION_ON;
        }

        KeyEvent keyEvent = new KeyEvent(0, 0, KeyEvent.ACTION_UP, keyCode, 0, metaState);
        terminal.onKeyDown(keyCode, keyEvent);
      } else {
        // not a control char
        for (int off = 0; off < key.length(); ) {
          int codePoint = key.codePointAt(off);
          terminal.inputCodePoint(codePoint, ctrlDown, altDown);
          off += Character.charCount(codePoint);
        }
      }
    }

    @Override
    public boolean performVirtualKeyButtonHapticFeedback(
        View view, VirtualKeyButton buttonInfo, Button button) {
      // No need to handle this
      // VirtualKeysView will take care of performing haptic feedback
      return false;
    }
  }
}
