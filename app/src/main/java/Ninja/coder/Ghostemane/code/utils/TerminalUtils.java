package Ninja.coder.Ghostemane.code.utils;

import Ninja.coder.Ghostemane.code.ApplicationLoader;
import android.app.Activity;
import android.content.Context;
import android.content.res.ColorStateList;
import android.graphics.Color;
import android.view.KeyEvent;
import android.view.MotionEvent;
import com.blankj.utilcode.util.ClipboardUtils;
import com.blankj.utilcode.util.KeyboardUtils;
import com.google.android.material.dialog.MaterialAlertDialogBuilder;
import com.google.android.material.shape.CornerFamily;
import com.google.android.material.shape.MaterialShapeDrawable;
import com.google.android.material.shape.ShapeAppearanceModel;
import com.termux.terminal.TerminalEmulator;
import com.termux.terminal.TerminalSession;
import com.termux.terminal.TerminalSessionClient;
import com.termux.view.TerminalView;
import com.termux.view.TerminalViewClient;
import com.xiaoyv.ccompile.CCppEngine;
import Ninja.coder.Ghostemane.code.config.CommandCompat;
import com.xiaoyv.ccompile.compiler.listener.CompileCallback;
import com.xiaoyv.ccompile.compiler.listener.ExecuteListener;

import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class TerminalUtils {
  private static String app = "";
  private static String line = "";

  public static void setTerminalinit(TerminalView terminal) {
    String shell = "/bin/sh";
    if (!new File("/bin/sh").exists()) {
      shell = "/system/bin/sh";
    }
    Map<String, String> maps = new HashMap<>();
    maps.put("HOME", shell);
    var helper = maps.get("HOME");

    String[] envVars = null;
    String[] argsList = {};
    String dsb = terminal.getContext().getFilesDir().getAbsolutePath();

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
                terminal.onScreenUpdated();
              }

              @Override
              public void onTitleChanged(TerminalSession terminalSession) {}

              @Override
              public void onSessionFinished(TerminalSession terminalSession) {
                if (!terminalSession.isRunning()) {
                  terminalSession.finishIfRunning();
                  DialogExit(terminal.getContext());
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
                if (clips.trim().length() > 0 && terminal != null && terminal.mEmulator != null) {
                  terminal.mEmulator.paste(clips);
                }
              }

              @Override
              public void onCopyTextToClipboard(TerminalSession session, String text) {
                ClipboardUtils.copyText(text);
              }
            });
    terminalSession.titleChanged("1", shell);
    terminal.setTerminalViewClient(
        new TerminalViewClient() {
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
            terminal.setFocusable(true);
            terminal.setFocusableInTouchMode(true);
            terminal.requestFocus();
            KeyboardUtils.showSoftInput(terminal);
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
            return false;
          }

          @Override
          public boolean readAltKey() {
            return false;
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
        });

    if (terminalSession != null) {
      terminal.attachSession(terminalSession);
    }

    terminal.setTextSize(30);

    String line = Commands.getBasicCommand(terminal.getContext());
    String rootHelper =
        "sed -i 's@:/data/user/0/Ninja\\.coder\\.Ghostemane\\.code/files@Root@g' \necho -e \"\\e[31mRoot\\e[0m\"";
    terminal.post(
        () -> {
          terminal.mTermSession.write(line);
        });
  }

  public static void setTerminalinit(TerminalView terminal, String data) {
    String executablePath = null;
    String[] envVars = null;
    String[] argsList = {};

    TerminalSession terminalSession =
        new TerminalSession(
            executablePath,
            data,
            argsList,
            envVars,
            TerminalEmulator.DEFAULT_TERMINAL_TRANSCRIPT_ROWS,
            new TerminalSessionClient() {
              @Override
              public void onTextChanged(TerminalSession arg0) {
                terminal.onScreenUpdated();
              }

              @Override
              public void onTitleChanged(TerminalSession terminalSession) {}

              @Override
              public void onSessionFinished(TerminalSession terminalSession) {
                if (!terminalSession.isRunning()) {
                  terminalSession.finishIfRunning();
                  DialogExit(terminal.getContext());
                }
              }

              @Override
              public void onBell(TerminalSession arg0) {}

              @Override
              public void onColorsChanged(TerminalSession session) {}

              @Override
              public void onTerminalCursorStateChange(boolean arg0) {}

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
                if (clips.trim().length() > 0 && terminal != null && terminal.mEmulator != null) {
                  terminal.mEmulator.paste(clips);
                }
              }

              @Override
              public void onCopyTextToClipboard(TerminalSession session, String text) {
                ClipboardUtils.copyText(text);
              }
            });

    terminal.setTerminalViewClient(
        new TerminalViewClient() {
          @Override
          public float onScale(float scale) {

            if (scale < 0.9f || scale > 1.1f) {
              boolean increase = scale > 1.f;
              changeFontSize(increase, terminal);
              return 1.0f;
            }
            return scale;
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
            terminal.setFocusable(true);
            terminal.setFocusableInTouchMode(true);
            terminal.requestFocus();
            KeyboardUtils.showSoftInput(terminal);
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
          public boolean onKeyDown(int arg0, KeyEvent arg1, TerminalSession arg2) {
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
            return false;
          }

          @Override
          public boolean readAltKey() {
            return false;
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
        });
    if (terminalSession != null) {
      terminal.attachSession(terminalSession);
    }

    terminal.setTextSize(30);
  }

  private static void DialogExit(Context context) {
    MaterialShapeDrawable shap =
        new MaterialShapeDrawable(
            ShapeAppearanceModel.builder().setAllCorners(CornerFamily.CUT, 20f).build());
    shap.setFillColor(ColorStateList.valueOf(Color.parseColor("#FF281D1B")));
    shap.setStroke(1, ColorStateList.valueOf(Color.parseColor("#FFFFAF7A")));
    new MaterialAlertDialogBuilder(context)
        .setTitle("Terminal is Finished")
        .setMessage("Code 0 exit is Tasking is end")
        .setPositiveButton(
            "exit",
            (c, d) -> {
              ((Activity) context).finish();
            })
        .setBackground(shap)
        .setCancelable(false)
        .setRecycleOnMeasureEnabled(true)
        .show();
  }

  private static void changeFontSize(final boolean increase, TerminalView terminal) {
    int fontSize = getFontSize();
    fontSize += (increase ? 1 : -1) * 2;
    fontSize = Math.max(0, Math.min(fontSize, 60));
    setFontSize(fontSize, true, terminal);
  }

  public static int getFontSize() {
    int fontSize;
    fontSize = 16;
    return Math.min(Math.max(fontSize, 0), 60);
  }

  public static void setFontSize(int value, boolean apply, TerminalView terminal) {
    if (apply) {
      terminal.setTextSize(getFontSize());
    } else {
      terminal.setTextSize(18);
    }
  }

  public static void RunPythonCode(TerminalView terminal, Context context, String code) {
    String shell = "/bin/sh";
    if (!new File("/bin/sh").exists()) {
      shell = "/system/bin/sh";
    }
    Map<String, String> maps = new HashMap<>();
    maps.put("HOME", shell);
    var helper = maps.get("HOME");

    String[] envVars = null;
    String[] argsList = {};
    String dsb = context.getFilesDir().getAbsolutePath();

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
                terminal.onScreenUpdated();
              }

              @Override
              public void onTitleChanged(TerminalSession terminalSession) {}

              @Override
              public void onSessionFinished(TerminalSession terminalSession) {
                if (!terminalSession.isRunning()) {
                  terminalSession.finishIfRunning();
                }
              }

              @Override
              public void onBell(TerminalSession arg0) {}

              @Override
              public void onColorsChanged(TerminalSession session) {}

              @Override
              public void onTerminalCursorStateChange(boolean arg0) {}

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
                if (clips.trim().length() > 0 && terminal != null && terminal.mEmulator != null) {
                  terminal.mEmulator.paste(clips);
                }
              }

              @Override
              public void onCopyTextToClipboard(TerminalSession session, String text) {
                ClipboardUtils.copyText(text);
              }
            });

    terminal.setTerminalViewClient(
        new TerminalViewClient() {
          @Override
          public float onScale(float scale) {

            if (scale < 0.9f || scale > 1.1f) {
              boolean increase = scale > 1.f;
              changeFontSize(increase, terminal);
              return 1.0f;
            }
            return scale;
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
            terminal.setFocusable(true);
            terminal.setFocusableInTouchMode(true);
            terminal.requestFocus();
            KeyboardUtils.showSoftInput(terminal);
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
          public boolean onKeyDown(int arg0, KeyEvent arg1, TerminalSession arg2) {
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
            return false;
          }

          @Override
          public boolean readAltKey() {
            return false;
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
        });
    if (terminalSession != null) {
      terminal.attachSession(terminalSession);
    }
    String line = CommandCompat.INSTANCE.getInterpreterCommand(context, code);

    terminal.post(
        () -> {
          terminal.mTermSession.write(line + "\r");
        });

    terminal.setTextSize(30);
  }

  public static void RunCppCode(TerminalView terminal, Context context, String code) {
    String shell = "/bin/sh";
    if (!new File("/bin/sh").exists()) {
      shell = "/system/bin/sh";
    }

    String[] envVars = null;
    String[] argsList = {};
    String dsb = context.getFilesDir().getAbsolutePath();

    TerminalSession terminalSession =
        new TerminalSession(
            shell,
            dsb,
            argsList,
            envVars,
            TerminalEmulator.DEFAULT_TERMINAL_TRANSCRIPT_ROWS,
            new TerminalSessionClient() {
              @Override
              public void onTextChanged(TerminalSession arg0) {
                terminal.onScreenUpdated();
              }

              @Override
              public void onTitleChanged(TerminalSession terminalSession) {}

              @Override
              public void onSessionFinished(TerminalSession terminalSession) {
                if (!terminalSession.isRunning()) {
                  terminalSession.finishIfRunning();
                  System.exit(0);
                }
              }

              @Override
              public void onBell(TerminalSession arg0) {}

              @Override
              public void onColorsChanged(TerminalSession session) {}

              @Override
              public void onTerminalCursorStateChange(boolean arg0) {}

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
                if (clips.trim().length() > 0 && terminal != null && terminal.mEmulator != null) {
                  terminal.mEmulator.paste(clips);
                }
              }

              @Override
              public void onCopyTextToClipboard(TerminalSession session, String text) {
                ClipboardUtils.copyText(text);
              }
            });

    terminal.setTerminalViewClient(
        new TerminalViewClient() {
          @Override
          public float onScale(float scale) {

            if (scale < 0.9f || scale > 1.1f) {
              boolean increase = scale > 1.f;
              changeFontSize(increase, terminal);
              return 1.0f;
            }
            return scale;
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
            terminal.setFocusable(true);
            terminal.setFocusableInTouchMode(true);
            terminal.requestFocus();
            KeyboardUtils.showSoftInput(terminal);
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
          public boolean onKeyDown(int arg0, KeyEvent arg1, TerminalSession arg2) {
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
            return false;
          }

          @Override
          public boolean readAltKey() {
            return false;
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
        });
    if (terminalSession != null) {
      terminal.attachSession(terminalSession);
    }

    // Compile gcc and run
    File cFile = new File(code);
    List<File> files = new ArrayList<>();
    files.add(cFile);
    CCppEngine.getCppCompiler()
        .compile(
            files,
            new CompileCallback() {
              @Override
              public void onSuccess(String outPath) {
                File file = new File(outPath);
                CCppEngine.getCExecutor()
                    .exec(
                        file,
                        new ExecuteListener() {
                          @Override
                          public void onExecuteStart(java.lang.Process process) {
                            line = process.toString();
                          }

                          @Override
                          public void printStderr(Throwable error) {

                            error.printStackTrace();
                            line = error.getMessage();
                          }

                          @Override
                          public void printStdout(CharSequence out) {
                            line = out.toString();
                          }

                          @Override
                          public void onExecuteFinish(int waitFor, int exitValue) {}
                        });
              }

              @Override
              public void onError(String error) {
                line = error;
              }
            });
    terminal.post(
        () -> {
          terminal.mTermSession.write(line);
        });

    terminal.setTextSize(30);
  }

  
}
