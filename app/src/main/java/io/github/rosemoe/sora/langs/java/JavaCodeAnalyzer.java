package io.github.rosemoe.sora.langs.java;

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
        column = token.getCharPositionInLine();
        lastLine = line;
        switch (token.getType()) {
          case JavaLexer.WS:
            if (first) {
              result.addNormalIfNull();
            }
            break;
          case JavaLexer.LPAREN:
          case JavaLexer.RPAREN:
          case JavaLexer.LBRACK:
          case JavaLexer.RBRACK:
            {
              classNamePrevious = false;
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
            }

          case JavaLexer.MODULE:
          case JavaLexer.NONSEALED:
          case JavaLexer.OACA:
          case JavaLexer.OPEN:
          case JavaLexer.OPENS:
          case JavaLexer.PROVIDES:
            {
              classNamePrevious = false;
              result.addIfNeeded(line, column, EditorColorScheme.OPERATOR);
              break;
            }

          case JavaLexer.ABSTRACT:
          case JavaLexer.ASSERT:
          case JavaLexer.BREAK:
            result.addIfNeeded(
                line, column, TextStyle.makeStyle(EditorColorScheme.red, 0, true, false, false));
            break;
          case JavaLexer.CASE:
          case JavaLexer.CATCH:
          case JavaLexer.CLASS:
            classNamePrevious = false;
            result.addIfNeeded(
                line,
                column,
                TextStyle.makeStyle(EditorColorScheme.LITERAL, 0, true, false, false));

            break;
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
            classNamePrevious = false;
            result.addIfNeeded(
                line,
                column,
                TextStyle.makeStyle(EditorColorScheme.ATTRIBUTE_VALUE, 0, true, false, false));
            break;
          case JavaLexer.IMPLEMENTS:
          case JavaLexer.IMPORT:
          case JavaLexer.INSTANCEOF:
          case JavaLexer.INTERFACE:
          case JavaLexer.NATIVE:
          case JavaLexer.NEW:
          case JavaLexer.PACKAGE:
            classNamePrevious = false;
            result.addIfNeeded(
                line,
                column,
                TextStyle.makeStyle(EditorColorScheme.HTML_TAG, 0, true, false, false));
            break;
          case JavaLexer.PRIVATE:
          case JavaLexer.PROTECTED:
          case JavaLexer.PUBLIC:
          case JavaLexer.RETURN:
          case JavaLexer.STATIC:
          case JavaLexer.STRICTFP:
          case JavaLexer.SUPER:
            classNamePrevious = false;
            result.addIfNeeded(
                line,
                column,
                TextStyle.makeStyle(EditorColorScheme.ATTRIBUTE_NAME, 0, true, false, false));
            break;
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
                TextStyle.makeStyle(
                    EditorColorScheme.AUTO_COMP_PANEL_CORNER, 0, true, false, false));
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
            result.addIfNeeded(
                line,
                column,
                TextStyle.makeStyle(EditorColorScheme.NON_PRINTABLE_CHAR, 0, true, false, false));
            break;
          case JavaLexer.LBRACE:
          case JavaLexer.ARROW:
          case JavaLexer.ASSIGN:
          case JavaLexer.GT:
          case JavaLexer.LT:
            result.addIfNeeded(line, column, EditorColorScheme.OPERATOR);
            if (stack.isEmpty()) {
              if (currSwitch > maxSwitch) {
                maxSwitch = currSwitch;
              }
              currSwitch = 0;
            }
            currSwitch++;
            var block = result.obtainNewBlock();
            block.startLine = line;
            block.startColumn = column;
            stack.push(block);
            break;
          case JavaLexer.RBRACE:
            result.addIfNeeded(line, column, EditorColorScheme.OPERATOR);
            if (!stack.isEmpty()) {
              var block2 = stack.pop();
              block2.endLine = line;
              block2.endColumn = column;
              if (block2.startLine != block2.endLine) {
                result.addBlockLine(block2);
              }
            }
            break;
          case JavaLexer.COMMENT:
            result.addIfNeeded(line, column, EditorColorScheme.COMMENT);
            break;
          case JavaLexer.LINE_COMMENT:
            var commentType = EditorColorScheme.COMMENT;
            result.addIfNeeded(line, column, commentType);

            break;
          case JavaLexer.AT:
            result.addIfNeeded(line, column, EditorColorScheme.AUTO_COMP_PANEL_CORNER);
            break;
          case JavaLexer.Identifier:
            {
              result.addIfNeeded(line, column, EditorColorScheme.OPERATOR);

              break;
            }

          case JavaLexer.StringLiteral:
            result.addIfNeeded(line, column, forString());
            break;
          case JavaLexer.SEMI:
          case JavaLexer.COMMA:

          case JavaLexer.EXPORTS:
          case JavaLexer.RECORD:
          case JavaLexer.REQUIRES:
          case JavaLexer.SEALED:
          case JavaLexer.TO:
          case JavaLexer.TRANSITIVE:
          case JavaLexer.USES:

          case JavaLexer.WITH:
          case JavaLexer.YIELD:

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
            classNamePrevious = false;
            result.addIfNeeded(
                line,
                column,
                TextStyle.makeStyle(EditorColorScheme.KEYWORD, 0, true, false, false));
            break;
          case JavaLexer.DEC:
          case JavaLexer.ADD:
          case JavaLexer.SUB:
          case JavaLexer.MUL:
          case JavaLexer.DIV:
          case JavaLexer.BITAND:
          case JavaLexer.BITOR:
          case JavaLexer.CARET:
          case JavaLexer.MOD:
          case JavaLexer.UNDER_SCORE:
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
            classNamePrevious = false;
            result.addIfNeeded(
                line, column, TextStyle.makeStyle(EditorColorScheme.Ninja, 0, true, false, false));
            break;
          case JavaLexer.COLONCOLON:
          case JavaLexer.ELLIPSIS:
          case JavaLexer.DOT:
            result.addIfNeeded(line, column, EditorColorScheme.LITERAL);
            break;
          default:
            result.addIfNeeded(line, column, EditorColorScheme.TEXT_NORMAL);
            prevIsTagName = false;
            classNamePrevious = false;

            if (previous == JavaLexer.IMPORT) {
              result.addIfNeeded(line, column, EditorColorScheme.AUTO_COMP_PANEL_CORNER);
              break;
            }
            if (previous == JavaLexer.COLON) {
              result.addIfNeeded(line, column, EditorColorScheme.ATTRIBUTE_VALUE);
              break;
            }
            break;
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
