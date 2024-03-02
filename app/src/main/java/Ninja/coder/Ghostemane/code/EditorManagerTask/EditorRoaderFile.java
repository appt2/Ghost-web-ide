package Ninja.coder.Ghostemane.code.EditorManagerTask;

import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.drawable.Drawable;
import android.os.AsyncTask;
import android.os.Build;
import android.widget.ProgressBar;
import androidx.swiperefreshlayout.widget.CircularProgressDrawable;
import android.view.*;
import com.google.android.material.floatingactionbutton.ExtendedFloatingActionButton;
import io.github.rosemoe.sora.langs.desc.*;
import io.github.rosemoe.sora.langs.html.HTMLLanguage;
import io.github.rosemoe.sora.langs.java.*;
import io.github.rosemoe.sora.langs.python.PythonLang;
import io.github.rosemoe.sora.langs.universal.UniversalLanguage;
import io.github.rosemoe.sora.widget.CodeEditor;
import java.io.*;
import io.github.rosemoe.sora.langs.antlrlang.ANTLRV4Lang;
import io.github.rosemoe.sora.langs.ninjalang.NinjaLang;
import io.github.rosemoe.sora.langs.javascript.JavaScriptLanguage;
import io.github.rosemoe.sora.langs.ghostthemelang.ghostlang.ghostlangs;
import io.github.rosemoe.sora.langs.xml.XMLLanguage;
import java.nio.charset.StandardCharsets;
import io.github.rosemoe.sora.langs.smali.SMLang;
import java.util.concurrent.atomic.AtomicReference;
import java.util.concurrent.CompletableFuture;

public class EditorRoaderFile {

  public static void RuningTask(
      CodeEditor editor, ExtendedFloatingActionButton _fab, String _path, ProgressBar bar) {
    _fab.shrink();
    _fab.hide();
    if (_path.endsWith(".css")) {
      editor.setEditorLanguage(new io.github.rosemoe.sora.langs.css3.CSS3Language(editor));
      ReadFileCompat(editor, _path, bar);
    } else if (_path.endsWith(".py")) {
      editor.setEditorLanguage(new PythonLang());
      ReadFileCompat(editor, _path, bar);
      _fab.postDelayed(
          () -> {
            _fab.show();
          },
          400);
    } else if (_path.endsWith(".cpp")) {
      editor.setEditorLanguage(new io.github.rosemoe.sora.langs.cpp.CppLanguage());
      _fab.postDelayed(
          () -> {
            _fab.show();
          },
          400);
      ReadFileCompat(editor, _path, bar);
    } else if (_path.endsWith(".html")) {
      var htmllang = new HTMLLanguage(editor);
      editor.setEditorLanguage(htmllang);
      ReadFileCompat(editor, _path, bar);
      _fab.postDelayed(
          () -> {
            _fab.show();
          },
          400);
    } else if (_path.endsWith(".js")) {
      editor.setEditorLanguage(new JavaScriptLanguage());
      ReadFileCompat(editor, _path, bar);
      _fab.postDelayed(
          () -> {
            _fab.show();
          },
          400);
    } else if (_path.endsWith(".scss") || _path.endsWith(".sass")) {
      ReadFileCompat(editor, _path, bar);
      editor.setEditorLanguage(new UniversalLanguage(new SCSSDescription()));
      _fab.postDelayed(() -> _fab.show(), 300);
    } else if (_path.endsWith(".c")) {
      ReadFileCompat(editor, _path, bar);
      editor.setEditorLanguage(new UniversalLanguage(new CDescription()));
    } else if (_path.endsWith(".json")) {
      ReadFileCompat(editor, _path, bar);
      editor.setEditorLanguage(new io.github.rosemoe.sora.langs.json.JsonLanguage());
      _fab.postDelayed(
          () -> {
            _fab.show();
          },
          400);
    } else if (_path.endsWith(".java")) {
      ReadFileCompat(editor, _path, bar);
      editor.setEditorLanguage(new JavaLanguage());
    } else if (_path.endsWith(".cs")) {
      ReadFileCompat(editor, _path, bar);
      editor.setEditorLanguage(new UniversalLanguage(new sharpDescription()));
    } else if (_path.endsWith(".xml")) {
      ReadFileCompat(editor, _path, bar);
      //	editor.setEditorLanguage(new io.github.rosemoe.sora.langs.xml.XMLLanguage());
      XMLLanguage xmlLanguage = new XMLLanguage();
      xmlLanguage.setSyntaxCheckEnable(true);
      editor.setEditorLanguage(xmlLanguage);

    } else if (_path.endsWith(".ghost")) {
      ReadFileCompat(editor, _path, bar);
      editor.setEditorLanguage(new ghostlangs());
    } else if (_path.endsWith(".ninja")) {
      ReadFileCompat(editor, _path, bar);
      editor.setEditorLanguage(new NinjaLang());
    } else if (_path.endsWith(".sh")) {
      ReadFileCompat(editor, _path, bar);
      _fab.postDelayed(
          () -> {
            _fab.show();
          },
          400);
      editor.setEditorLanguage(new UniversalLanguage(new ShellDescription()));
    } else if (_path.endsWith(".svg")) {
      ReadFileCompat(editor, _path, bar);
      _fab.postDelayed(
          () -> {
            _fab.show();
          },
          400);
      var htmllang = new HTMLLanguage(editor);
      editor.setEditorLanguage(htmllang);
    } else if (_path.endsWith(".md")) {
      ReadFileCompat(editor, _path, bar);
      _fab.postDelayed(
          () -> {
            _fab.show();
          },
          400);
      var htmllang = new HTMLLanguage(editor);
      editor.setEditorLanguage(htmllang);
      editor.setAutoCompletionEnabled(false);
    } else if (_path.endsWith(".php")) {
      ReadFileCompat(editor, _path, bar);
      editor.setEditorLanguage(new io.github.rosemoe.sora.langs.php.PHPLanguage());
      _fab.postDelayed(
          () -> {
            _fab.show();
          },
          400);
    } else if (_path.endsWith(".go")) {
      ReadFileCompat(editor, _path, bar);
      editor.setEditorLanguage(new UniversalLanguage(new GoDescription()));
    } else if (_path.endsWith(".txt")) {
      ReadFileCompat(editor, _path, bar);
    } else if (_path.endsWith(".swift")) {
      ReadFileCompat(editor, _path, bar);
      editor.setEditorLanguage(new UniversalLanguage(new SwiftDescription()));
    } else if (_path.endsWith(".rb") || _path.endsWith(".rbw")) {
      ReadFileCompat(editor, _path, bar);
      editor.setEditorLanguage(new UniversalLanguage(new RubyDescription()));
    } else if (_path.endsWith(".dart")) {
      ReadFileCompat(editor, _path, bar);
      editor.setEditorLanguage(new io.github.rosemoe.sora.langs.dart.DartLang());
    } else if (_path.endsWith(".kt")) {
      ReadFileCompat(editor, _path, bar);
      _fab.postDelayed(
          () -> {
            _fab.show();
          },
          400);
      editor.setEditorLanguage(new io.github.rosemoe.sora.langs.kotlin.KotlinLanguage());
    } else if (_path.endsWith(".groovy") || _path.endsWith(".gradle")) {
      ReadFileCompat(editor, _path, bar);
      editor.setEditorLanguage(new io.github.rosemoe.sora.langs.groovy.lang.GroovyLanguage());
    } else if (_path.endsWith(".cpp")) {
      ReadFileCompat(editor, _path, bar);
      editor.setEditorLanguage(new UniversalLanguage(new CppDescription()));
    } else if (_path.endsWith(".smali")) {

      ReadFileCompat(editor, _path, bar);
      editor.setEditorLanguage(new SMLang());
    } else if (_path.endsWith(".g4")) {
      ReadFileCompat(editor, _path, bar);
      editor.setEditorLanguage(new ANTLRV4Lang());
      _fab.postDelayed(
          () -> {
            _fab.show();
          },
          400);
    } else if (_path.endsWith(".ts")) {
      ReadFileCompat(editor, _path, bar);
      editor.setEditorLanguage(new io.github.rosemoe.sora.langs.typescript.TsLang(editor));
    } else {
      _fab.hide();
    }
  }

  protected static void ReadFileCompat(CodeEditor editor, String path, ProgressBar bar) {
    try {
      FileCompatApi28.readFile(path, bar, editor);
    } catch (Throwable e) {
      editor.setText(e.toString());
    }
  }
}
