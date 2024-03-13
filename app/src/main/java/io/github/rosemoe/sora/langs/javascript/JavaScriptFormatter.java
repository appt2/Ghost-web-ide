package io.github.rosemoe.sora.langs.javascript;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import javax.script.Invocable;
import javax.script.ScriptEngine;
import javax.script.ScriptEngineManager;
import javax.script.ScriptException;
import java.io.InputStreamReader;

/** Class to format javascript code using beautify js */
public class JavaScriptFormatter {

  // replace with file path to your beautify js file
  private static final String PATH_TO_BEAUTIFY_JS_RESOURCE =
      "/storage/emulated/0/Download/beautify.js";

  // name of beautifier function
  private static final String BEAUTIFY_METHOD_NAME = "js_beautify";
  private final ScriptEngine engine;

  public JavaScriptFormatter() throws ScriptException, FileNotFoundException {
    engine = new ScriptEngineManager().getEngineByName("nashorn");

    // this is needed to make self invoking function modules work
    // otherwise you won't be able to invoke your function
    engine.eval("var global = this;");
    engine.eval(new InputStreamReader(new FileInputStream(new File(PATH_TO_BEAUTIFY_JS_RESOURCE))));
  }

  /** Calls this for format code. You should do this on async */
  public String format(String javascriptCode) throws ScriptException, NoSuchMethodException {
    return (String) ((Invocable) engine).invokeFunction(BEAUTIFY_METHOD_NAME, javascriptCode);
  }

  // Usage
  public static void main(String[] args)
      throws ScriptException, FileNotFoundException, NoSuchMethodException {
    String unformattedJs = "var a = 1; b = 2; var user = { name : \n \"Andrew\"}";

    JavaScriptFormatter javascriptBeautifierForJava = new JavaScriptFormatter();
    String formattedJs = javascriptBeautifierForJava.format(unformattedJs);

    System.out.println(formattedJs);
    // will print out:
    //        var a = 1;
    //        b = 2;
    //        var user = {
    //            name: "Andrew"
    //        }
  }
}
