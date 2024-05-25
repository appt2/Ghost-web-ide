package io.github.rosemoe.sora.langs.java;

import android.graphics.Color;
import android.util.Log;
import io.github.rosemoe.sora.text.TextStyle;
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

public class JavaCodeAnalyzer implements CodeAnalyzer {

  @Override
  public void analyze(
      CharSequence content,
      TextAnalyzeResult result,
      TextAnalyzer.AnalyzeThread.Delegate delegate) {
    try {
      StringBuilder text =
          content instanceof StringBuilder ? (StringBuilder) content : new StringBuilder(content);
      CodePointCharStream stream = CharStreams.fromReader(new StringReader(content.toString()));
      JavaLexer lexer = new JavaLexer(stream);
      var classNamePrevious = false;
      Token token, preToken = null, prePreToken = null;
      boolean first = true;
      Stack<BlockLine> stack = new Stack<>();
      int type, currSwitch = 1, maxSwitch = 0, previous = -1;
      int lastLine = 1;
      int line, column;
      var prevIsTagName = false;

      while (delegate.shouldAnalyze()) {
        token = lexer.nextToken();
        if (token == null) break;
        if (token.getType() == JavaLexer.EOF) {
          lastLine = token.getLine() - 1;
          break;
        }
        line = token.getLine() - 1;
        type = token.getType();
        column = token.getCharPositionInLine();
        if (type == JavaLexer.EOF) {
          lastLine = line;
          break;
        }

        switch (type) {
          case JavaLexer.WS:
            if (first) {
              result.addNormalIfNull();
            }
            break;
          case JavaLexer.ABSTRACT:
          case JavaLexer.ASSERT:
          case JavaLexer.BREAK:
          case JavaLexer.CASE:
          case JavaLexer.CATCH:
          case JavaLexer.CLASS:
          case JavaLexer.CONST:
          case JavaLexer.CONTINUE:
          case JavaLexer.DEFAULT:
          case JavaLexer.DO:
          case JavaLexer.ELSE:
          case JavaLexer.EXTENDS:
          case JavaLexer.FINAL:
          case JavaLexer.FINALLY:
          case JavaLexer.FOR:
          case JavaLexer.IF:
          case JavaLexer.GOTO:
          case JavaLexer.IMPLEMENTS:
          case JavaLexer.IMPORT:
          case JavaLexer.INSTANCEOF:
          case JavaLexer.INTERFACE:
          case JavaLexer.NATIVE:
          case JavaLexer.NEW:
          case JavaLexer.PACKAGE:
          case JavaLexer.PRIVATE:
          case JavaLexer.PROTECTED:
          case JavaLexer.PUBLIC:
          case JavaLexer.RETURN:
          case JavaLexer.STATIC:
          case JavaLexer.STRICTFP:
          case JavaLexer.SUPER:
          case JavaLexer.SWITCH:
          case JavaLexer.SYNCHRONIZED:
          case JavaLexer.THIS:
          case JavaLexer.THROW:
          case JavaLexer.THROWS:
          case JavaLexer.TRANSIENT:
          case JavaLexer.TRY:
          case JavaLexer.VOID:
          case JavaLexer.VOLATILE:
          case JavaLexer.WHILE:
          case JavaLexer.VAR:
            result.addIfNeeded(
                line,
                column,
                TextStyle.makeStyle(EditorColorScheme.KEYWORD, 0, true, false, false));
            break;
          case JavaLexer.DECIMAL_LITERAL:
          case JavaLexer.HEX_LITERAL:
          case JavaLexer.OCT_LITERAL:
          case JavaLexer.BINARY_LITERAL:
          case JavaLexer.FLOAT_LITERAL:
          case JavaLexer.HEX_FLOAT_LITERAL:
          case JavaLexer.BOOL_LITERAL:
          case JavaLexer.CHAR_LITERAL:
          case JavaLexer.NULL_LITERAL:
            result.addIfNeeded(line, column, EditorColorScheme.LITERAL);
            break;
          case JavaLexer.STRING_LITERAL:
            result.addIfNeeded(line, column, forString());
            break;
          case JavaLexer.LPAREN:
          case JavaLexer.RPAREN:
          case JavaLexer.LBRACK:
          case JavaLexer.RBRACK:
          case JavaLexer.SEMI:
          case JavaLexer.COMMA:
          case JavaLexer.ASSIGN:
          case JavaLexer.GT:
          case JavaLexer.LT:
          case JavaLexer.BANG:
          case JavaLexer.TILDE:
          case JavaLexer.QUESTION:
          case JavaLexer.COLON:
          case JavaLexer.EQUAL:
          case JavaLexer.GE:
          case JavaLexer.LE:
          case JavaLexer.NOTEQUAL:
          case JavaLexer.AND:
          case JavaLexer.OR:
          case JavaLexer.INC:
          case JavaLexer.DEC:
          case JavaLexer.ADD:
          case JavaLexer.SUB:
          case JavaLexer.MUL:
          case JavaLexer.DIV:
          case JavaLexer.BITAND:
          case JavaLexer.BITOR:
          case JavaLexer.CARET:
          case JavaLexer.MOD:
          case JavaLexer.ADD_ASSIGN:
          case JavaLexer.SUB_ASSIGN:
          case JavaLexer.MUL_ASSIGN:
          case JavaLexer.DIV_ASSIGN:
          case JavaLexer.AND_ASSIGN:
          case JavaLexer.OR_ASSIGN:
          case JavaLexer.XOR_ASSIGN:
          case JavaLexer.MOD_ASSIGN:
          case JavaLexer.LSHIFT_ASSIGN:
          case JavaLexer.RSHIFT_ASSIGN:
          case JavaLexer.URSHIFT_ASSIGN:
          case JavaLexer.ARROW:
          case JavaLexer.COLONCOLON:
          case JavaLexer.ELLIPSIS:
          case JavaLexer.DOT:
            result.addIfNeeded(line, column, EditorColorScheme.OPERATOR);
            break;
          case JavaLexer.BOOLEAN:
          case JavaLexer.BYTE:
          case JavaLexer.CHAR:
          case JavaLexer.DOUBLE:
          case JavaLexer.ENUM:
          case JavaLexer.FLOAT:
          case JavaLexer.INT:
          case JavaLexer.LONG:
          case JavaLexer.SHORT:
            result.addIfNeeded(line, column, EditorColorScheme.ATTRIBUTE_VALUE);
            break;
          case JavaLexer.BLOCK_COMMENT:
          case JavaLexer.LINE_COMMENT:
            result.addIfNeeded(line, column, EditorColorScheme.COMMENT);
            break;
          case JavaLexer.AT:
            result.addIfNeeded(line, column, EditorColorScheme.Ninja);
            break;
          case JavaLexer.IDENTIFIER:
            {
              int colorid = EditorColorScheme.TEXT_NORMAL;
              if (previous == JavaLexer.AT) {
                colorid = EditorColorScheme.Ninja;
              }
              if (previous == JavaLexer.CLASS) {
                colorid = EditorColorScheme.AUTO_COMP_PANEL_CORNER;
              }
              if (previous == JavaLexer.LBRACK
                  || previous == JavaLexer.DOT
                  || previous == JavaLexer.IMPORT) {
                colorid = EditorColorScheme.ATTRIBUTE_NAME;
              }
              if (previous == JavaLexer.VOID || previous == JavaLexer.EXTENDS) {
                colorid = EditorColorScheme.COLOR_WARNING;
              }
              if (previous == JavaLexer.RETURN || previous == JavaLexer.NEW) {
                colorid = EditorColorScheme.HTML_TAG;
              }
              if (token.getText().matches("h[0-6]")) {
                colorid = EditorColorScheme.COLOR_ERROR;
              }
              // test
              if (token.getText().matches("[A-Z_]*\\s{0}")) {
                colorid = EditorColorScheme.COLOR_TIP;
              }
              var regex = "static\\s+[a-z-A-S]{1,6}";
              if (token.getText().matches(regex)) {
                colorid = EditorColorScheme.KEYWORD;
              }
              if(previous == JavaLexer.INT) {
              	colorid = EditorColorScheme.LITERAL;
              } 
              if(previous == JavaLexer.CASE || previous == JavaLexer.FINAL) {
              	colorid = EditorColorScheme.ATTRIBUTE_NAME;
              }
              result.addIfNeeded(line, column, colorid);
              break;
            }

          case JavaLexer.LBRACE:
            result.addIfNeeded(line, column, EditorColorScheme.OPERATOR);
            if (stack.isEmpty()) {
              if (currSwitch > maxSwitch) {
                maxSwitch = currSwitch;
              }
              currSwitch = 0;
            }
            currSwitch++;
            BlockLine block = result.obtainNewBlock();
            block.startLine = line;
            block.startColumn = column;
            stack.push(block);
            break;
          case JavaLexer.RBRACE:
            result.addIfNeeded(line, column, EditorColorScheme.OPERATOR);
            if (!stack.isEmpty()) {
              BlockLine b = stack.pop();
              b.endLine = line;
              b.endColumn = column;
              if (b.startLine != b.endLine) {
                result.addBlockLine(b);
              }
            }
            break;

          default:
            result.addIfNeeded(line, column, EditorColorScheme.TEXT_NORMAL);
            break;
        }

        if (type != JavaLexer.WS) {
          previous = type;
        }

        first = false;
      }
      result.determine(lastLine);

    } catch (IOException e) {
      e.printStackTrace();
      Log.e("TAG", e.getMessage());
    }
  }

  public long withoutCompletion(int id) {
    return TextStyle.makeStyle(id, 0, true, false, false);
  }

  public long forString() {
    return TextStyle.makeStyle(EditorColorScheme.LITERAL, 0, true, true, false);
  }
}
