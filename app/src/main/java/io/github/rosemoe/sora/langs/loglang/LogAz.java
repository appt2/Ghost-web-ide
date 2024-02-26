package io.github.rosemoe.sora.langs.loglang;

import android.graphics.Color;
import io.github.rosemoe.sora.text.TextStyle;
import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

import io.github.rosemoe.sora.data.BlockLine;
import io.github.rosemoe.sora.data.NavigationItem;
import io.github.rosemoe.sora.interfaces.CodeAnalyzer;
import io.github.rosemoe.sora.langs.IdentifierAutoComplete;
import io.github.rosemoe.sora.langs.internal.TrieTree;
import io.github.rosemoe.sora.text.LineNumberCalculator;
import io.github.rosemoe.sora.text.TextAnalyzeResult;
import io.github.rosemoe.sora.text.TextAnalyzer;
import io.github.rosemoe.sora.widget.EditorColorScheme;

/**
 * @author NinjaCoder
 */
public class LogAz implements CodeAnalyzer {

  private static final Object OBJECT = new Object();

  @Override
  public void analyze(
      CharSequence content,
      TextAnalyzeResult result,
      TextAnalyzer.AnalyzeThread.Delegate delegate) {
    StringBuilder text =
        content instanceof StringBuilder ? (StringBuilder) content : new StringBuilder(content);
    var tokenizer = new LogTextTokenizer(text);
    tokenizer.setCalculateLineColumn(false);
    Token token, previous = Token.UNKNOWN;
    int line = 0, column = 0;
    LineNumberCalculator helper = new LineNumberCalculator(text);
    IdentifierAutoComplete.Identifiers identifiers = new IdentifierAutoComplete.Identifiers();
    identifiers.begin();
    Stack<BlockLine> stack = new Stack<>();
    List<NavigationItem> labels = new ArrayList<>();
    int maxSwitch = 1, currSwitch = 0;
    // Tree to save class names and query
    TrieTree<Object> classNames = new TrieTree<>();
    // Whether previous token is class name
    boolean classNamePrevious = false;
    // Add default class name
    classNames.put("String", OBJECT);
    classNames.put("Object", OBJECT);
    boolean first = true;
    while (delegate.shouldAnalyze()) {
      try {
        // directNextToken() does not skip any token
        token = tokenizer.directNextToken();
      } catch (RuntimeException e) {
        // When a spelling input is in process, this will happen because of format mismatch
        token = Token.CHARACTER_LITERAL;
      }
      if (token == Token.EOF) {
        break;
      }
      // Backup values because looking ahead in function name match will change them
      int thisIndex = tokenizer.getIndex();
      int thisLength = tokenizer.getTokenLength();
      switch (token) {
        case WHITESPACE:
        case NEWLINE:
          if (first) {
            result.addNormalIfNull();
          }
          break;
        case DEBUG:
          result.addIfNeeded(line, column, Color.BLUE);
          break;
        
        default:
          int colorId = EditorColorScheme.OPERATOR;

          classNamePrevious = false;
          result.addIfNeeded(line, column, TextStyle.makeStyle(colorId));
      }
      first = false;
      helper.update(thisLength);
      line = helper.getLine();
      column = helper.getColumn();
      if (token != Token.WHITESPACE && token != Token.NEWLINE) {
        previous = token;
      }
    }
    if (stack.isEmpty()) {
      if (currSwitch > maxSwitch) {
        maxSwitch = currSwitch;
      }
    }
    identifiers.finish();
    result.determine(line);
    result.setExtra(identifiers);
    result.setSuppressSwitch(maxSwitch + 10);
    result.setNavigation(labels);
  }
}
