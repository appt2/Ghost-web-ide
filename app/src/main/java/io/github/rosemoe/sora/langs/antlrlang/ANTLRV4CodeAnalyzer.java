package io.github.rosemoe.sora.langs.antlrlang;

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

public class ANTLRV4CodeAnalyzer implements CodeAnalyzer {

  @Override
  public void analyze(
      CharSequence content,
      TextAnalyzeResult result,
      TextAnalyzer.AnalyzeThread.Delegate delegate) {
    try {
      StringBuilder text =
          content instanceof StringBuilder ? (StringBuilder) content : new StringBuilder(content);
      CodePointCharStream stream = CharStreams.fromReader(new StringReader(content.toString()));
      ANTLRv4Lexer lexer = new ANTLRv4Lexer(stream);
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
        if (token.getType() == ANTLRv4Lexer.EOF) {
          lastLine = token.getLine() - 1;
          break;
        }
        line = token.getLine() - 1;
        column = token.getCharPositionInLine();
        lastLine = line;
        switch (token.getType()) {
          case ANTLRv4Lexer.WS:
            if (first) {
              result.addNormalIfNull();
            }
            break;
          case ANTLRv4Lexer.LPAREN:
          case ANTLRv4Lexer.RPAREN:
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

          case ANTLRv4Lexer.TOKEN_REF:
          case ANTLRv4Lexer.RULE_REF:
          case ANTLRv4Lexer.LEXER_CHAR_SET:
            {
              classNamePrevious = false;
              result.addIfNeeded(line, column, EditorColorScheme.OPERATOR);
              break;
            }

          case ANTLRv4Lexer.POUND:
          case ANTLRv4Lexer.NOT:
          case ANTLRv4Lexer.ID:
          case ANTLRv4Lexer.ERRCHAR:
          case ANTLRv4Lexer.END_ARGUMENT:
          case ANTLRv4Lexer.UNTERMINATED_ARGUMENT:
          case ANTLRv4Lexer.ARGUMENT_CONTENT:
          case ANTLRv4Lexer.END_ACTION:
          case ANTLRv4Lexer.UNTERMINATED_ACTION:
          case ANTLRv4Lexer.ACTION_CONTENT:
          case ANTLRv4Lexer.UNTERMINATED_CHAR_SET:
            classNamePrevious = false;
            result.addIfNeeded(
                line,
                column,
                TextStyle.makeStyle(EditorColorScheme.ATTRIBUTE_VALUE, 0, true, false, false));
            break;

          case ANTLRv4Lexer.PRIVATE:
          case ANTLRv4Lexer.PROTECTED:
          case ANTLRv4Lexer.PUBLIC:
            classNamePrevious = false;
            result.addIfNeeded(
                line,
                column,
                TextStyle.makeStyle(EditorColorScheme.ATTRIBUTE_NAME, 0, true, false, false));
            break;
          case ANTLRv4Lexer.MODE:
          case ANTLRv4Lexer.COLON:
          case ANTLRv4Lexer.COLONCOLON:
          case ANTLRv4Lexer.COMMA:
          case ANTLRv4Lexer.SEMI:
          case ANTLRv4Lexer.RARROW:
          case ANTLRv4Lexer.ASSIGN:
          case ANTLRv4Lexer.QUESTION:
          case ANTLRv4Lexer.STAR:
          case ANTLRv4Lexer.PLUS_ASSIGN:
          case ANTLRv4Lexer.PLUS:
          case ANTLRv4Lexer.OR:
          case ANTLRv4Lexer.DOLLAR:
          case ANTLRv4Lexer.RANGE:
          case ANTLRv4Lexer.DOT:
          case ANTLRv4Lexer.AT:
            result.addIfNeeded(
                line,
                column,
                TextStyle.makeStyle(
                    EditorColorScheme.AUTO_COMP_PANEL_CORNER, 0, true, false, false));
            break;
          case ANTLRv4Lexer.PARSER:
          case ANTLRv4Lexer.GRAMMAR:
            result.addIfNeeded(
                line,
                column,
                TextStyle.makeStyle(EditorColorScheme.NON_PRINTABLE_CHAR, 0, true, false, false));
            break;

          case ANTLRv4Lexer.DOC_COMMENT:
          case ANTLRv4Lexer.BLOCK_COMMENT:
          case ANTLRv4Lexer.LINE_COMMENT:
            result.addIfNeeded(line, column, EditorColorScheme.COMMENT);
            break;

          case ANTLRv4Lexer.STRING_LITERAL:
          case ANTLRv4Lexer.UNTERMINATED_STRING_LITERAL:
          case ANTLRv4Lexer.BEGIN_ARGUMENT:
          case ANTLRv4Lexer.BEGIN_ACTION:
            result.addIfNeeded(line, column, forString());
            break;
          case ANTLRv4Lexer.LBRACE:
          case ANTLRv4Lexer.RBRACE:
          case ANTLRv4Lexer.LT:
          case ANTLRv4Lexer.GT:
            {
              classNamePrevious = false;
              result.addIfNeeded(line, column, EditorColorScheme.OPERATOR);
              if (!stack.isEmpty()) {
                BlockLine block1 = stack.pop();
                block1.endLine = line;
                block1.endColumn = column;
                if (block1.startLine != block1.endLine) {
                  result.addBlockLine(block1);
                }
              }
              break;
            }

          case ANTLRv4Lexer.OPTIONS:
          case ANTLRv4Lexer.TOKENS:
          case ANTLRv4Lexer.CHANNELS:
          case ANTLRv4Lexer.FRAGMENT:
          case ANTLRv4Lexer.LEXER:
            classNamePrevious = false;
            result.addIfNeeded(
                line, column, TextStyle.makeStyle(EditorColorScheme.Ninja, 0, true, false, false));
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
    } catch (Exception err) {
      err.printStackTrace();
    }
  }

  public long withoutCompletion(int id) {
    return TextStyle.makeStyle(id, 0, true, false, false);
  }

  public long forString() {
    return TextStyle.makeStyle(EditorColorScheme.LITERAL, 0, true, true, false);
  }
}
