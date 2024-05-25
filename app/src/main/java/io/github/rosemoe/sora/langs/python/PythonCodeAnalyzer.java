package io.github.rosemoe.sora.langs.python;

import android.util.Log;

import io.github.rosemoe.sora.text.TextStyle;
import java.util.List;
import io.github.rosemoe.sora.data.NavigationItem;
import java.util.ArrayList;
import java.util.Stack;
import io.github.rosemoe.sora.data.BlockLine;
import org.antlr.v4.runtime.CharStreams;
import org.antlr.v4.runtime.CodePointCharStream;
import org.antlr.v4.runtime.Token;
import java.io.IOException;
import java.io.StringReader;
import io.github.rosemoe.sora.interfaces.CodeAnalyzer;
import io.github.rosemoe.sora.text.TextAnalyzeResult;
import io.github.rosemoe.sora.text.TextAnalyzer;
import io.github.rosemoe.sora.widget.EditorColorScheme;

public class PythonCodeAnalyzer implements CodeAnalyzer {

  @Override
  public void analyze(
      CharSequence content,
      TextAnalyzeResult result,
      TextAnalyzer.AnalyzeThread.Delegate delegate) {
    try {

      CodePointCharStream stream = CharStreams.fromReader(new StringReader(content.toString()));
      var lexer = new PythonLexer(stream);

      var classNamePrevious = false;
      Token token, preToken = null, prePreToken = null;
      boolean first = true;
      Stack<BlockLine> stack = new Stack<>();
      List<NavigationItem> labels = new ArrayList<>();
      int maxSwitch = 1, currSwitch = 0;
      int lastLine = 1;
      int line, column;
      var prevIsTagName = false;
      while (delegate.shouldAnalyze()) {
        token = lexer.nextToken();
        if (token == null) break;
        if (token.getType() == PythonLexer.EOF) {
          lastLine = token.getLine() - 1;
          break;
        }
        line = token.getLine() - 1;
        column = token.getCharPositionInLine();
        lastLine = line;

        switch (token.getType()) {
          case PythonLexer.NEWLINE:
            if (first) {
              result.addNormalIfNull();
            }
            break;
          case PythonLexer.FALSE:
          case PythonLexer.AWAIT:
          case PythonLexer.ELSE:
          case PythonLexer.IMPORT:
          case PythonLexer.PASS:
          case PythonLexer.NONE:
            result.addIfNeeded(line, column, Italic());
            break;

          case PythonLexer.BREAK:
          case PythonLexer.EXCEPT:
          case PythonLexer.IN:
          case PythonLexer.RAISE:
          case PythonLexer.TRUE:
          case PythonLexer.CLASS:
          case PythonLexer.DEF:
            result.addIfNeeded(
                line,
                column,
                TextStyle.makeStyle(EditorColorScheme.LITERAL, 0, true, false, false));
            break;
          case PythonLexer.COLON:
            classNamePrevious = false;
            result.addIfNeeded(line, column, EditorColorScheme.HTML_TAG);
            break;
          case PythonLexer.FINALLY:
          case PythonLexer.IS:
          case PythonLexer.RETURN:
          case PythonLexer.AND:
          case PythonLexer.CONTINUE:
          case PythonLexer.FOR:
          case PythonLexer.LAMBDA:
          case PythonLexer.TRY:
          case PythonLexer.AS:
            result.addIfNeeded(line, column, Bold());
            break;
          case PythonLexer.FROM:
          case PythonLexer.NONLOCAL:
          case PythonLexer.WHILE:
          case PythonLexer.ASSERT:
          case PythonLexer.DEL:
            result.addIfNeeded(
                line, column, TextStyle.makeStyle(EditorColorScheme.Ninja, 0, true, false, false));
            break;
          case PythonLexer.GLOBAL:
          case PythonLexer.NOT:
          case PythonLexer.WITH:
          case PythonLexer.ASYNC:
          case PythonLexer.ELIF:
          case PythonLexer.IF:
          case PythonLexer.OR:
          case PythonLexer.YIELD:
            result.addIfNeeded(
                line,
                column,
                TextStyle.makeStyle(EditorColorScheme.ATTRIBUTE_NAME, 0, true, false, false));
            break;
          case PythonLexer.DOT:
          case PythonLexer.ELLIPSIS:
          case PythonLexer.STAR:
          case PythonLexer.OPEN_PAREN:
          case PythonLexer.CLOSE_PAREN:
          case PythonLexer.COMMA:
          case PythonLexer.SEMI_COLON:
          case PythonLexer.POWER:
          case PythonLexer.ASSIGN:
          case PythonLexer.OPEN_BRACK:
          case PythonLexer.CLOSE_BRACK:
          case PythonLexer.OR_OP:
          case PythonLexer.XOR:
          case PythonLexer.AND_OP:
          case PythonLexer.LEFT_SHIFT:
          case PythonLexer.BRAKECTMODEL:
          case PythonLexer.RIGHT_SHIFT:
          case PythonLexer.ADD:
            result.addIfNeeded(
                line,
                column,
                TextStyle.makeStyle(EditorColorScheme.ATTRIBUTE_NAME, 0, true, false, false));
            break;

          case PythonLexer.NUMBER:
          case PythonLexer.STRING:
          case PythonLexer.MINUS:
          case PythonLexer.DIV:
          case PythonLexer.MOD:
          case PythonLexer.IDIV:
          case PythonLexer.NOT_OP:
          case PythonLexer.OPEN_BRACE:
          case PythonLexer.CLOSE_BRACE:
          case PythonLexer.LESS_THAN:
          case PythonLexer.GREATER_THAN:
          case PythonLexer.EQUALS:
          case PythonLexer.GT_EQ:
          case PythonLexer.LT_EQ:
          case PythonLexer.NOT_EQ_1:
          case PythonLexer.NOT_EQ_2:
          case PythonLexer.AT:
          case PythonLexer.ARROW:
          case PythonLexer.ADD_ASSIGN:
          case PythonLexer.SUB_ASSIGN:
          case PythonLexer.MULT_ASSIGN:
          case PythonLexer.AT_ASSIGN:
            result.addIfNeeded(
                line,
                column,
                TextStyle.makeStyle(EditorColorScheme.BLOCK_LINE, 0, true, false, false));
            break;
          case PythonLexer.DIV_ASSIGN:
          case PythonLexer.MOD_ASSIGN:
          case PythonLexer.AND_ASSIGN:
          case PythonLexer.OR_ASSIGN:
          case PythonLexer.XOR_ASSIGN:
          case PythonLexer.LEFT_SHIFT_ASSIGN:
          case PythonLexer.RIGHT_SHIFT_ASSIGN:
          case PythonLexer.POWER_ASSIGN:
          case PythonLexer.IDIV_ASSIGN:
            result.addIfNeeded(
                line, column, TextStyle.makeStyle(EditorColorScheme.LITERAL, 0, true, true, false));
            classNamePrevious = false;

            break;

          case PythonLexer.UNKNOWN_CHAR:
            result.addIfNeeded(
                line, column, TextStyle.makeStyle(EditorColorScheme.red, 0, true, false, false));
            break;
          case PythonLexer.SKIP_:
            result.addIfNeeded(line, column, EditorColorScheme.COMMENT);
            break;
          case PythonLexer.NAME:
            result.addIfNeeded(line, column, EditorColorScheme.AUTO_COMP_PANEL_CORNER);
            break;
          case PythonLexer.STRING_LITERAL:
          case PythonLexer.BYTES_LITERAL:
          case PythonLexer.DECIMAL_INTEGER:
          case PythonLexer.OCT_INTEGER:
          case PythonLexer.HEX_INTEGER:
          case PythonLexer.BIN_INTEGER:
          case PythonLexer.FLOAT_NUMBER:
          case PythonLexer.IMAG_NUMBER:
          case PythonLexer.INITMODEL:
            result.addIfNeeded(line, column, EditorColorScheme.COLOR_TIP);
            break;
          case PythonLexer.COMPATPARN:
            result.addIfNeeded(line, column, EditorColorScheme.COLOR_WARNING);
            break;
          default:
            result.addIfNeeded(line, column, EditorColorScheme.TEXT_NORMAL);
            prevIsTagName = false;
            classNamePrevious = false;
            break;
        }

        first = false;
      }

      result.determine(lastLine);

      result.setSuppressSwitch(maxSwitch + 10);
      result.setNavigation(labels);
    } catch (IOException e) {
      e.printStackTrace();
      Log.e("TAG", e.getMessage());
    }
  }

  public long Bold() {
    return TextStyle.makeStyle(EditorColorScheme.ATTRIBUTE_VALUE, 0, true, false, false);
  }

  public long Italic() {
    return TextStyle.makeStyle(EditorColorScheme.ATTRIBUTE_NAME, 0, false, true, false);
  }
}
