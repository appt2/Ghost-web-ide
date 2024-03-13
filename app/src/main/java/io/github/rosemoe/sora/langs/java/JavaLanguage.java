package io.github.rosemoe.sora.langs.java;

import android.os.Build;
import io.github.rosemoe.sora.interfaces.AutoCompleteProvider;
import io.github.rosemoe.sora.interfaces.CodeAnalyzer;
import io.github.rosemoe.sora.interfaces.EditorLanguage;
import io.github.rosemoe.sora.interfaces.NewlineHandler;
import io.github.rosemoe.sora.langs.internal.MyCharacter;
import io.github.rosemoe.sora.text.TextUtils;
import io.github.rosemoe.sora.widget.SymbolPairMatch;
import org.eclipse.jdt.internal.formatter.DefaultCodeFormatter;
import org.eclipse.jdt.internal.formatter.DefaultCodeFormatterOptions;
import org.eclipse.jface.text.Document;

public class JavaLanguage implements EditorLanguage {

  @Override
  public CodeAnalyzer getAnalyzer() {
    return new JavaCodeAnalyzer();
  }

  @Override
  public AutoCompleteProvider getAutoCompleteProvider() {
    return new JavaAutoComplete();
  }

  @Override
  public boolean isAutoCompleteChar(char ch) {
    return MyCharacter.isJavaIdentifierPart(ch);
  }

  @Override
  public int getIndentAdvance(String content) {
    JavaTextTokenizer t = new JavaTextTokenizer(content);
    Tokens token;
    int advance = 0;
    while ((token = t.directNextToken()) != Tokens.EOF) {
      switch (token) {
        case LBRACE:
          advance++;
          break;
          /*case RBRACE:
          advance--;
          break;*/
      }
    }
    advance = Math.max(0, advance);
    return advance * 4;
  }

  @Override
  public boolean useTab() {
    return true;
  }

  @Override
  public SymbolPairMatch getSymbolPairs() {
    return new SymbolPairMatch.DefaultSymbolPairs();
  }

  @Override
  public CharSequence format(CharSequence text) {
    GoogleJavaFormatter formater = new GoogleJavaFormatter(text.toString());
    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.UPSIDE_DOWN_CAKE) {
      return formater.format();
    }
    return text;
  }

  private final NewlineHandler[] newLineHandlers =
      new NewlineHandler[] {
        new BraceHandler(), new TwoIndentHandler(), new JavaDocStartHandler(), new JavaDocHandler()
      };

  @Override
  public NewlineHandler[] getNewlineHandlers() {
    return newLineHandlers;
  }

  class BraceHandler implements NewlineHandler {

    @Override
    public boolean matchesRequirement(String beforeText, String afterText) {
      return beforeText.endsWith("{") && afterText.startsWith("}");
    }

    @Override
    public HandleResult handleNewline(String beforeText, String afterText, int tabSize) {
      int count = TextUtils.countLeadingSpaceCount(beforeText, tabSize);
      int advanceBefore = getIndentAdvance(beforeText);
      int advanceAfter = getIndentAdvance(afterText);
      String text;
      StringBuilder sb =
          new StringBuilder("\n")
              .append(TextUtils.createIndent(count + advanceBefore, tabSize, useTab()))
              .append('\n')
              .append(text = TextUtils.createIndent(count + advanceAfter, tabSize, useTab()));
      int shiftLeft = text.length() + 1;
      return new HandleResult(sb, shiftLeft);
    }
  }

  class JavaDocStartHandler implements NewlineHandler {

    private boolean shouldCreateEnd = true;

    @Override
    public boolean matchesRequirement(String beforeText, String afterText) {
      return beforeText.trim().startsWith("/**");
    }

    @Override
    public HandleResult handleNewline(String beforeText, String afterText, int tabSize) {
      int count = TextUtils.countLeadingSpaceCount(beforeText, tabSize);
      int advanceAfter = getIndentAdvance(afterText);
      String text = "";
      StringBuilder sb =
          new StringBuilder()
              .append("\n")
              .append(TextUtils.createIndent(count + advanceAfter, tabSize, useTab()))
              .append(" * ");
      if (shouldCreateEnd) {
        sb.append("\n")
            .append(text = TextUtils.createIndent(count + advanceAfter, tabSize, useTab()))
            .append(" */");
      }
      return new HandleResult(sb, text.length() + 4);
    }
  }

  class JavaDocHandler implements NewlineHandler {

    @Override
    public boolean matchesRequirement(String beforeText, String afterText) {
      return beforeText.trim().startsWith("*") && !beforeText.trim().startsWith("*/");
    }

    @Override
    public HandleResult handleNewline(String beforeText, String afterText, int tabSize) {
      int count = TextUtils.countLeadingSpaceCount(beforeText, tabSize);
      int advanceAfter = getIndentAdvance(afterText);
      StringBuilder sb =
          new StringBuilder()
              .append("\n")
              .append(TextUtils.createIndent(count + advanceAfter, tabSize, useTab()))
              .append("* ");
      return new HandleResult(sb, 0);
    }
  }

  class TwoIndentHandler implements NewlineHandler {

    @Override
    public boolean matchesRequirement(String beforeText, String afterText) {
      //  Log.d("BeforeText", beforeText);
      if (beforeText.replace("\r", "").trim().startsWith(".")) {
        return false;
      }
      return beforeText.endsWith(")") && !afterText.startsWith(";");
    }

    @Override
    public HandleResult handleNewline(String beforeText, String afterText, int tabSize) {
      int count = TextUtils.countLeadingSpaceCount(beforeText, tabSize);
      int advanceAfter = getIndentAdvance(afterText) + (4 * 2);
      String text;
      StringBuilder sb =
          new StringBuilder()
              .append('\n')
              .append(text = TextUtils.createIndent(count + advanceAfter, tabSize, useTab()));
      int shiftLeft = 0;
      return new HandleResult(sb, shiftLeft);
    }
  }

  public class GoogleJavaFormatter {

    private String source;

    public GoogleJavaFormatter(String source) {
      this.source = source;
    }

    public String format() {
      var options = DefaultCodeFormatterOptions.getEclipseDefaultSettings();
      var codeFormatter = new DefaultCodeFormatter(options);
      var edit =
          codeFormatter.format(
              DefaultCodeFormatter.K_COMPILATION_UNIT,
              source,
              0, // starting index
              source.length(), // length
              0, // initial indentation
              System.lineSeparator() // line separator
              );

      var document = new Document(source);
      try {
        
        edit.apply(document);
      } catch (Exception e) {
        throw new IllegalStateException(e);
      }
      // return the formatted code
      return document.get();
    }
  }
}
