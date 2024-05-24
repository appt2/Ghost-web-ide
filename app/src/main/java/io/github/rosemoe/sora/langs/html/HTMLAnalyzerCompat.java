package io.github.rosemoe.sora.langs.html;

import android.graphics.Color;
import android.util.Log;
import io.github.rosemoe.sora.data.Span;
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

public class HTMLAnalyzerCompat implements CodeAnalyzer {

  @Override
  public void analyze(
      CharSequence content,
      TextAnalyzeResult result,
      TextAnalyzer.AnalyzeThread.Delegate delegate) {
    try {

      CodePointCharStream stream = CharStreams.fromReader(new StringReader(content.toString()));
      var lexer = new HTMLLexer(stream);
      var classNamePrevious = false;
      Token token, preToken = null, prePreToken = null;
      boolean first = true;
      Stack<BlockLine> stack = new Stack<>();
      int maxSwitch = 1, currSwitch = 0;
      int lastLine = 1;
      int line, column;
      var prevIsTagName = false;

      while (delegate.shouldAnalyze()) {
        token = lexer.nextToken();
        if (token == null) break;
        if (token.getType() == HTMLLexer.EOF) {
          lastLine = token.getLine() - 1;
          break;
        }
        line = token.getLine() - 1;
        column = token.getCharPositionInLine();
        lastLine = line;

        switch (token.getType()) {
          case HTMLLexer.Space:
            if (first) {
              result.addNormalIfNull();
            }
            break;
          case HTMLLexer.BlockStart:
          case HTMLLexer.BlockEnd:
            {
              result.addIfNeeded(
                  line,
                  column,
                  TextStyle.makeStyle(EditorColorScheme.ATTRIBUTE_VALUE, 0, true, false, false));
              break;
            }
          case HTMLLexer.Import:
          case HTMLLexer.Include:
          case HTMLLexer.Use:
          case HTMLLexer.Require:
          case HTMLLexer.Charset:
          case HTMLLexer.Mixin:
          case HTMLLexer.Function:
          case HTMLLexer.FontFace:
          case HTMLLexer.Forward:
          case HTMLLexer.Content:
          case HTMLLexer.Keyframes:
          case HTMLLexer.Return:
          case HTMLLexer.Media:
          case HTMLLexer.Extend:
          case HTMLLexer.Warn:
          case HTMLLexer.Error:
          case HTMLLexer.If:
          case HTMLLexer.AtIf:
          case HTMLLexer.AtFor:
          case HTMLLexer.AtElse:
          case HTMLLexer.JsKeyWord:
          case HTMLLexer.AtWhile:
          case HTMLLexer.AtEach:
            result.addIfNeeded(
                line,
                column,
                TextStyle.makeStyle(EditorColorScheme.ATTRIBUTE_VALUE, 0, true, false, false));
            break;

            // from px dp ....

          case HTMLLexer.FontRelative:
          case HTMLLexer.ViewportRelative:
          case HTMLLexer.Angle:
          case HTMLLexer.Resolution:
          case HTMLLexer.JsKeyWord1:
          case HTMLLexer.Freq:
          case HTMLLexer.Time:
          case HTMLLexer.Percentage:
            result.addIfNeeded(line, column, EditorColorScheme.BLOCK_LINE);
            break;
          case HTMLLexer.Uri:
          case HTMLLexer.Format:
            result.addIfNeeded(line, column, EditorColorScheme.KEYWORD);
            break;
          case HTMLLexer.From:
          case HTMLLexer.To:
          case HTMLLexer.JsKeyWord2:
          case HTMLLexer.Through:
          case HTMLLexer.Only:
          case HTMLLexer.Not:
          case HTMLLexer.And:
          case HTMLLexer.Using:
          case HTMLLexer.As:
          case HTMLLexer.With:
          case HTMLLexer.Or:
          case HTMLLexer.In:
          case HTMLLexer.Default:
          case HTMLLexer.Important:
            result.addIfNeeded(line, column, EditorColorScheme.AUTO_COMP_PANEL_CORNER);
            break;
            /// Syombl
          case HTMLLexer.Lparen:
          case HTMLLexer.Rparen:
          case HTMLLexer.Lbrack:
          case HTMLLexer.Rbrack:
          case HTMLLexer.JsKeyWord3:
          case HTMLLexer.Comma:
            /// case HTMLLexer.Colon:
          case HTMLLexer.Semi:
          case HTMLLexer.Tilde:
          case HTMLLexer.Under:
          case HTMLLexer.Dollar:
          case HTMLLexer.At:
          case HTMLLexer.Amp:
          case HTMLLexer.Hash:
          case HTMLLexer.True:
          case HTMLLexer.False:
          case HTMLLexer.Plus:
          case HTMLLexer.Minus:
          case HTMLLexer.Times:
          case HTMLLexer.Eq:
          case HTMLLexer.NotEq:
          case HTMLLexer.Less:
          case HTMLLexer.Includes:
          case HTMLLexer.DashMatch:
          case HTMLLexer.Pipe:
          case HTMLLexer.Cdo:
          case HTMLLexer.Cdc:
          case HTMLLexer.Greater:
          case HTMLLexer.PseudoNot:
          case HTMLLexer.Calc:
          case HTMLLexer.Rotate:
          case HTMLLexer.Var:
          case HTMLLexer.Rgba:
          case HTMLLexer.Repeat:
          case HTMLLexer.PrefixMatch:
          case HTMLLexer.SuffixMatch:
          case HTMLLexer.SubstringMatch:
            result.addIfNeeded(line, column, EditorColorScheme.BLOCK_LINE_CURRENT);
            break;
          case HTMLLexer.Number:
          case HTMLLexer.JsKeyWord4:
            result.addIfNeeded(line, column, EditorColorScheme.OPERATOR);
            break;
          case HTMLLexer.CSSCOLOR:
            result.addIfNeeded(
                line,
                column,
                TextStyle.makeStyle(EditorColorScheme.BLOCK_LINE_CURRENT, 0, false, false, false));
            break;
          case HTMLLexer.String_:
            {
              String text = token.getText();
              if (text.startsWith("\"#")) {
                try {
                  int color = Color.parseColor(text.substring(1, text.length() - 1));
                  result.addIfNeeded(line, column, EditorColorScheme.LITERAL);

                  Span span = Span.obtain(column + 1, EditorColorScheme.LITERAL);
                  span.setUnderlineColor(color);
                  result.add(line, span);

                  Span middle = Span.obtain(column + text.length() - 1, EditorColorScheme.LITERAL);
                  middle.setUnderlineColor(Color.TRANSPARENT);
                  result.add(line, middle);

                  Span end =
                      Span.obtain(
                          column + text.length(),
                          TextStyle.makeStyle(EditorColorScheme.TEXT_NORMAL));
                  end.setUnderlineColor(Color.TRANSPARENT);
                  result.add(line, end);
                  break;
                } catch (Exception ignore) {
                }
              }
              result.addIfNeeded(line, column, EditorColorScheme.LITERAL);
              break;
            }
          case HTMLLexer.Variable:
          case HTMLLexer.PHPSTARTENDS:
          case HTMLLexer.JsKeyWord5:
          case HTMLLexer.VendorPrefix:
          case HTMLLexer.CSSKEYWORD:
            result.addIfNeeded(line, column, EditorColorScheme.ATTRIBUTE_VALUE);
            break;
            // Comment
          case HTMLLexer.MultiLineComment:
          case HTMLLexer.COMMENTCSS:
          case HTMLLexer.LineComment:
            result.addIfNeeded(line, column, EditorColorScheme.COMMENT);
            break;

          case HTMLLexer.HtmlTags:
          case HTMLLexer.HtmlAttr:
          case HTMLLexer.HtmlTagOne:
          case HTMLLexer.JsKeyWord6:
            {
              result.addIfNeeded(line, column, EditorColorScheme.KEYWORD);
              break;
            }
          case HTMLLexer.Div:
            {
              result.addIfNeeded(line, column, EditorColorScheme.BLOCK_LINE_CURRENT);
              if (preToken != null && preToken.getType() == HTMLLexer.Less) {
                if (!stack.isEmpty()) {
                  BlockLine block = stack.pop();
                  block.endLine = preToken.getLine() - 1;
                  block.endColumn = preToken.getCharPositionInLine();
                  if (block.startLine != block.endLine) {
                    if (prePreToken.getLine() == preToken.getLine()) block.toBottomOfEndLine = true;
                    result.addBlockLine(block);
                  }
                }
              }
              break;
            }

          case HTMLLexer.SLASH_CLOSE:
            {
              result.addIfNeeded(line, column, EditorColorScheme.BLOCK_LINE_CURRENT);
              // set block line end position
              if (!stack.isEmpty()) {
                BlockLine block = stack.pop();
                block.endLine = line;
                block.endColumn = column;
                if (block.startLine != block.endLine) {
                  if (preToken.getLine() == token.getLine()) block.toBottomOfEndLine = true;
                  result.addBlockLine(block);
                }
              }
              break;
            }
          case HTMLLexer.Dot:
          case HTMLLexer.NameRule:
          case HTMLLexer.Colen:
            // from test to regex let aa = or class myapp() //
          case HTMLLexer.VAR_WS_EQUALS:
            {
              result.addIfNeeded(line, column, EditorColorScheme.Ninja);
              break;
            }
          case HTMLLexer.COLORUPPERCASE:
            {
              result.addIfNeeded(line, column, EditorColorScheme.COLOR_WARNING);
              break;
            }
          case HTMLLexer.REACTBRACET:
            {
              result.addIfNeeded(line, column, EditorColorScheme.COLOR_TIP);
              break;
            }
          case HTMLLexer.MYCLASS:
          case HTMLLexer.HASHSTYLE:
            {
              result.addIfNeeded(line, column, EditorColorScheme.COLOR_WARNING);
              break;
            }
          case HTMLLexer.GREENBOLD:
          //for test//
          case HTMLLexer.VOIDS:
          case HTMLLexer.LOGDIGI:
            {
              result.addIfNeeded(
                  line,
                  column,
                  TextStyle.makeStyle(EditorColorScheme.COLOR_WARNING, 0, true, false, false));
              break;
            }

          default:
            result.addIfNeeded(line, column, EditorColorScheme.TEXT_NORMAL);
            prevIsTagName = false;
            classNamePrevious = false;
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
}
