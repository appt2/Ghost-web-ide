package Ninja.coder.Ghostemane.code;

import org.antlr.v4.Tool;
import org.antlr.v4.tool.ANTLRMessage;
import org.antlr.v4.tool.ANTLRToolListener;
import java.io.IOException;
import org.antlr.v4.tool.ErrorType;

public class G4Compiler {
  public static void compile(String input, String output, String packages) {
    if (input.endsWith(".g4")) {
      String[] args = {input, "-package", packages, "-o", output};
      main(args);
    }
  }

  public static void main(String[] args) {
    Tool antlr = new Tool(args);
    StringBuilder b = new StringBuilder();

    if (args.length == 0) {
      antlr.help();
    }

    try {

      antlr.processGrammarsOnCommandLine();
      antlr.addListener(
          new ANTLRToolListener() {

            @Override
            public void info(String info) {
              b.append(info).append("\n");
            }

            @Override
            public void error(ANTLRMessage error) {
              b.append(error.fileName).append("\n");
              
            }

            @Override
            public void warning(ANTLRMessage war) {
              b.append(war.fileName).append("\n");
            }
          });
    } finally {
      if (antlr.log) {
        try {
          String logname = antlr.logMgr.save();
          System.out.println("wrote " + logname);

        } catch (IOException ioe) {
          antlr.errMgr.toolError(ErrorType.INTERNAL_ERROR, ioe);
        }
      }
    }
  }
}
