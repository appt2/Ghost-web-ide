package io.github.rosemoe.sora.langs.html;

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

public class HTMLAnalyzerCompat implements CodeAnalyzer {

  @Override
  public void analyze(
      CharSequence content,
      TextAnalyzeResult result,
      TextAnalyzer.AnalyzeThread.Delegate delegate) {
    try {

      CodePointCharStream stream = CharStreams.fromReader(new StringReader(content.toString()));
      HtmlLexer lexer = new HtmlLexer(stream);

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
        if (token.getType() == HtmlLexer.EOF) {
          lastLine = token.getLine() - 1;
          break;
        }
        line = token.getLine() - 1;
        column = token.getCharPositionInLine();
        lastLine = line;

        switch (token.getType()) {
          case HtmlLexer.Space:
            if (first) {
              result.addNormalIfNull();
            }
            break;
          case HtmlLexer.BlockStart:
          case HtmlLexer.BlockEnd:
            {
              result.addIfNeeded(
                  line,
                  column,
                  TextStyle.makeStyle(EditorColorScheme.ATTRIBUTE_VALUE, 0, true, false, false));
              break;
            }
            // from @ using
          case HtmlLexer.Import:
          case HtmlLexer.Include:
          case HtmlLexer.Use:
          case HtmlLexer.Require:
          case HtmlLexer.Charset:
          case HtmlLexer.Mixin:
          case HtmlLexer.Function:
          case HtmlLexer.FontFace:
          case HtmlLexer.Forward:
          case HtmlLexer.Content:
          case HtmlLexer.Keyframes:
          case HtmlLexer.Return:
          case HtmlLexer.Media:
          case HtmlLexer.Extend:
          case HtmlLexer.Warn:
          case HtmlLexer.Error:
          case HtmlLexer.If:
          case HtmlLexer.AtIf:
          case HtmlLexer.AtFor:
          case HtmlLexer.AtElse:
          case HtmlLexer.JsKeyWord:
          case HtmlLexer.AtWhile:
          case HtmlLexer.AtEach:
            result.addIfNeeded(
                line,
                column,
                TextStyle.makeStyle(EditorColorScheme.ATTRIBUTE_VALUE, 0, true, false, false));
            break;

            // from px dp ....
          case HtmlLexer.AbsLength:
          case HtmlLexer.FontRelative:
          case HtmlLexer.ViewportRelative:
          case HtmlLexer.Angle:
          case HtmlLexer.Resolution:
          case HtmlLexer.JsKeyWord1:
          case HtmlLexer.Freq:
          case HtmlLexer.Time:
          case HtmlLexer.Percentage:
            result.addIfNeeded(line, column, EditorColorScheme.BLOCK_LINE);
            break;
          case HtmlLexer.Uri:
          case HtmlLexer.Format:
            result.addIfNeeded(line, column, EditorColorScheme.KEYWORD);
            break;
          case HtmlLexer.From:
          case HtmlLexer.To:
          case HtmlLexer.JsKeyWord2:
          case HtmlLexer.Through:
          case HtmlLexer.Only:
          case HtmlLexer.Not:
          case HtmlLexer.And:
          case HtmlLexer.Using:
          case HtmlLexer.As:
          case HtmlLexer.With:
          case HtmlLexer.Or:
          case HtmlLexer.In:
          case HtmlLexer.Default:
          case HtmlLexer.Important:
            result.addIfNeeded(line, column, EditorColorScheme.AUTO_COMP_PANEL_CORNER);
            break;
            /// Syombl
          case HtmlLexer.Lparen:
          case HtmlLexer.Rparen:
          case HtmlLexer.Lbrack:
          case HtmlLexer.Rbrack:
          case HtmlLexer.JsKeyWord3:
          case HtmlLexer.Dot:
          case HtmlLexer.Comma:
            /// case HtmlLexer.Colon:
          case HtmlLexer.Semi:
          case HtmlLexer.Tilde:
          case HtmlLexer.Under:
          case HtmlLexer.Dollar:
          case HtmlLexer.At:
          case HtmlLexer.Amp:
          case HtmlLexer.Hash:
          case HtmlLexer.True:
          case HtmlLexer.False:
          case HtmlLexer.Plus:

          case HtmlLexer.Minus:
          case HtmlLexer.Times:
          case HtmlLexer.Eq:
          case HtmlLexer.NotEq:
          case HtmlLexer.Less:
          case HtmlLexer.Includes:
          case HtmlLexer.DashMatch:
          case HtmlLexer.Pipe:
          case HtmlLexer.Cdo:
          case HtmlLexer.Cdc:
          case HtmlLexer.Greater:
          case HtmlLexer.PseudoNot:
          case HtmlLexer.Calc:
          case HtmlLexer.Rotate:
          case HtmlLexer.Var:
          case HtmlLexer.Rgba:
          case HtmlLexer.Repeat:
          case HtmlLexer.PrefixMatch:
          case HtmlLexer.SuffixMatch:
          case HtmlLexer.SubstringMatch:
            result.addIfNeeded(line, column, EditorColorScheme.BLOCK_LINE_CURRENT);
            break;
          case HtmlLexer.Number:
          case HtmlLexer.JsKeyWord4:
            result.addIfNeeded(line, column, EditorColorScheme.OPERATOR);
            break;
          case HtmlLexer.CSSCOLOR:
            result.addIfNeeded(
                line,
                column,
                TextStyle.makeStyle(
                    EditorColorScheme.BLOCK_LINE_CURRENT,
                    EditorColorScheme.red,
                    false,
                    false,
                    false));
            break;
          case HtmlLexer.String_:
          case HtmlLexer.Variable:
          case HtmlLexer.PHPSTARTENDS:
          case HtmlLexer.JsKeyWord5:
          case HtmlLexer.VendorPrefix:
          case HtmlLexer.CSSKEYWORD:
            result.addIfNeeded(line, column, EditorColorScheme.ATTRIBUTE_VALUE);
            break;
            // Comment
          case HtmlLexer.Comment:
          case HtmlLexer.MultiLineComment:
          case HtmlLexer.COMMENTCSS:
          case HtmlLexer.LineComment:
            result.addIfNeeded(line, column, EditorColorScheme.COMMENT);
            break;

          case HtmlLexer.HtmlTags:
          case HtmlLexer.HtmlAttr:
          case HtmlLexer.HtmlTagOne:
          case HtmlLexer.JsKeyWord6:
            {
              result.addIfNeeded(line, column, EditorColorScheme.KEYWORD);
              break;
            }
          case HtmlLexer.Div:
            {
              result.addIfNeeded(line, column, EditorColorScheme.BLOCK_LINE_CURRENT);
              if (preToken != null && preToken.getType() == HtmlLexer.Less) {
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
          
          case HtmlLexer.SLASH_CLOSE:
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
