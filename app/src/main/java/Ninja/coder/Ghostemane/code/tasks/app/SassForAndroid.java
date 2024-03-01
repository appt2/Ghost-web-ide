package Ninja.coder.Ghostemane.code.tasks.app;

import Ninja.coder.Ghostemane.code.ColorAndroid12;
import android.content.Context;
import android.widget.EditText;
import android.widget.LinearLayout;
import com.google.android.material.bottomsheet.BottomSheetDialog;
import com.google.android.material.color.MaterialColors;
import com.sass_lang.embedded_protocol.InboundMessage;
import de.larsgrefer.sass.embedded.CompileSuccess;
import de.larsgrefer.sass.embedded.SassCompiler;
import de.larsgrefer.sass.embedded.android.AndroidSassCompilerFactory;
import de.larsgrefer.sass.embedded.connection.CompilerConnection;
import de.larsgrefer.sass.embedded.connection.Packet;
import java.io.File;
import java.io.IOException;

public class SassForAndroid {
  
  public static String CompileForStringScss(String input,Context context) {
    try (var compiler = AndroidSassCompilerFactory.bundled(context)){
      CompileSuccess sb = compiler.compileScssString(input);
      return sb.getCss();
    } catch (Exception err) {
      return err.getLocalizedMessage();
    }
  }

  public static void CompilerForFile(File input, String output,Context context) {
    try(var compiler = AndroidSassCompilerFactory.bundled(context)) {
      CompileSuccess sb = compiler.compileFile(input);
      output = sb.getCss();
    } catch (Exception err) {
      err.printStackTrace();
    }
  }

  public static void CompileForStringSass(String input, String output,Context context) {
    try (var compiler = AndroidSassCompilerFactory.bundled(context)){
      CompileSuccess sb = compiler.compileSassString(input);
      output = sb.getCss();
    } catch (Exception err) {
      err.printStackTrace();
    }
  }

  public static void run(Context context, String input) {
    var sheet = new BottomSheetDialog(context);
    EditText tv = new EditText(context);
    LinearLayout.LayoutParams param =
        new LinearLayout.LayoutParams(
            LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.WRAP_CONTENT);
    tv.setLayoutParams(param);
    param.rightMargin = 3;
    param.leftMargin = 3;
    tv.setTextColor(MaterialColors.getColor(tv, ColorAndroid12.colorOnSurface, 0));
    tv.setPadding(9, 9, 9, 9);
    sheet.setContentView(tv);
    tv.setText(CompileForStringScss(input,context));
    sheet.show();
  }
}
