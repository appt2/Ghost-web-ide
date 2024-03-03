package io.github.rosemoe.sora.langs.html;

import Ninja.coder.Ghostemane.code.FiledirActivity;
import android.content.SharedPreferences;
import android.net.Uri;
import io.github.rosemoe.sora.langs.IdentifierAutoComplete;
import io.github.rosemoe.sora.langs.php.PHPLanguage;
import io.github.rosemoe.sora.widget.CodeEditor;
import io.github.rosemoe.sora.widget.TextSummry.HTMLConstants;
import io.github.rosemoe.sora.widget.commentRule.AppConfig;
import java.io.File;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import io.github.rosemoe.sora.data.CompletionItem;
import io.github.rosemoe.sora.interfaces.AutoCompleteProvider;
import io.github.rosemoe.sora.text.TextAnalyzeResult;
import java.util.Random;
import lsp4custom.com.ninjacoder.customhtmllsp.ListKeyword;

public class HTMLAutoComplete implements AutoCompleteProvider {
  private List<CompletionItem> items;
  String Folder = "";
  String prfex = "";
  AppConfig config;
  private String[] mKeywords;
  private CompletionItem listItem;
  private boolean mKeywordsAreLowCase;
  private ArrayList<String> pathz;
  protected HTMLConstants htmlconfig;
  private CodeEditor editor;
  ListKeyword keyhtml;
  
  protected SharedPreferences shp;
  private IdentifierAutoComplete.Identifiers userIdentifiers;
  private TextAnalyzeResult analyzeResult;

  public HTMLAutoComplete() {
    config = new AppConfig();
    htmlconfig = new HTMLConstants();
    userIdentifiers = new IdentifierAutoComplete.Identifiers();
    pathz = new ArrayList<>();
    keyhtml = new ListKeyword();
    
  }

  @Override
  public List<CompletionItem> getAutoCompleteItems(
      String prefix, TextAnalyzeResult result, int line, int column) {
    prfex = prefix;
    analyzeResult = result;
    items = new ArrayList<>();
    setList(prefix);
    validTag();
    Collections.sort(items, CompletionItem.COMPARATOR_BY_NAME);
    
    List<CompletionItem> listasFiles = new ArrayList<>();
    File file = new File(FiledirActivity.POSNINJACODERMAIN);
    if (file.exists() && file.isDirectory()) {
      File[] listFile = file.listFiles();
      if (listFile != null) {
        for (File f : listFile) {
          if (f.isFile()) {
            CompletionItem item = new CompletionItem(f.getName(), "File Path");
            listasFiles.add(item);
          }
        }
        Collections.sort(listasFiles, CompletionItem.COMPARATOR_BY_NAME);
        items.addAll(listasFiles);
      }
    }

    // Test
    if ("lr".startsWith(prefix) && prefix.length() > 0) {
      try {
        items.add(CodeSampel("loram", "Loram", getLoramRandom(Integer.parseInt(prefix))));
      } catch (NumberFormatException exception) {
        items.add(CodeSampel("loram", "Loram", getLoramRandom(10)));
      }
    }
    return items;
  }

  private boolean isValidTag(String tag) {
    for (String validTag : keyhtml.TAGS) {
      if (validTag.equals(tag)) {
        return true;
      }
    }

    return false;
  }

  private CompletionItem attrAsCompletion(String attr, String desc) {
    final var item = new CompletionItem(attr, attr.concat("=\"\""), desc);
    item.cursorOffset(item.commit.length() - 1);
    return item;
  }

  private CompletionItem dddAsCompletion(String ddd, String desc) {
    final var item = new CompletionItem(ddd, desc);
    item.cursorOffset(item.commit.length());
    return item;
  }

  private CompletionItem mcssAsCompletion(String mcss, String desc) {
    final var item = new CompletionItem(mcss, desc);
    item.cursorOffset(item.commit.length());
    return item;
  }

  private CompletionItem ColorcssAsCompletion(String mcss, String desc) {
    final CompletionItem item = new CompletionItem(mcss + "  ", desc);
    item.cursorOffset(item.commit.length() - 1);
    return item;
  }

  private CompletionItem emmet(String em, String desc) {
    final var open =
        "<html lang=\"en\"> \n  <head> \n    <meta charset=\"UTF-8\"> \n    <meta name=\"viewport\" content=\"width=device-width, initial-scale=1.0\"> \n    <title>Document</title> \n  </head> \n  <body>  \n  </body>\n</html>";
    final CompletionItem item = new CompletionItem(em, desc);
    item.commit = open;
    item.cursorOffset(item.commit.length() - open.length());
    return item;
  }

  private CompletionItem getEmmetItemPost(String em, String doc) {
    final String open = "</".concat(em).concat(">");
    final var item = new CompletionItem(em, doc);
    item.commit = open;
    item.cursorOffset(item.commit.length() - open.length());
    return item;
  }

  private CompletionItem tagAsCompletion(String tag, String desc) {
    final String open = "<".concat(tag).concat(">");
    final String close = "</".concat(tag).concat(">");
    final var item = new CompletionItem(tag, desc);
    item.commit = open.concat(close);
    item.cursorOffset(item.commit.length() - close.length());
    return item;
  }

  private CompletionItem AutoCompletion(String tag, String desc, String items) {
    var userName = "class =".concat("\"".concat(ClassName("classTest").concat("\"")));
    String open =
        "<".concat(tag.replace(items, "".trim())).concat(" " + userName + " ").concat(">");
    String close = "</".concat(tag.replace(items, "".trim())).concat(">");
    var item = new CompletionItem(tag, desc);
    item.commit = open.concat(close);
    item.commit.endsWith(items.replace(items, "".trim()));
    item.cursorOffset(item.commit.length() - close.length());
    return item;
  }

  private CompletionItem getId(String tag, String desc, String items) {
    var userName = "id =".concat("\"".concat(ClassName("Id_Test").concat("\"")));
    String open =
        "<".concat(tag.replace(items, "".trim())).concat(" " + userName + " ").concat(">");
    String close = "</".concat(tag.replace(items, "".trim())).concat(">");
    CompletionItem item = new CompletionItem(tag, desc);
    item.commit = open.concat(close);
    boolean endsWith = item.commit.endsWith(items.replace(items, "".trim()));
    item.cursorOffset(item.commit.length() - close.length());
    return item;
  }

  private CompletionItem PhpAsCompletion(String php, String desc) {
    final CompletionItem item = new CompletionItem(php, desc);
    item.cursorOffset(item.commit.length());
    return item;
  }

  protected String ClassName(String base) {
    var random = new Random();
    int rd = random.nextInt(400282);
    return base + rd + " ";
  }

  private CompletionItem CodeSampel(String em, String desc, String codeS) {
    final String open = codeS;
    final CompletionItem item = new CompletionItem(em, desc);
    item.commit = open;
    item.cursorOffset(item.commit.length() - open.length());
    return item;
  }

  void Style(
      List<CompletionItem> items,
      String prefix,
      TextAnalyzeResult result,
      int line,
      int column,
      String mcss) {
    items.add(mcssAsCompletion(mcss + " : ", "Css3 keyword"));
    //	result.addIfNeeded(line,column,TextStyle.makeStyle(EditorColorScheme.ATTRIBUTE_VALUE,0,false,true,false));
  }

  void Style2(
      List<CompletionItem> items,
      String prefix,
      TextAnalyzeResult result,
      int line,
      int column,
      String mcss) {
    items.add(ColorcssAsCompletion(mcss, "Color Css "));
    //	result.addIfNeeded(line,column,TextStyle.makeStyle(EditorColorScheme.AUTO_COMP_PANEL_CORNER,CssColor(prefix),false,false,true));
  }

  String[] comit = {"Comment", "Lorem1"};

  public String getLoramRandom(int count) {
    StringBuilder builder = new StringBuilder();
    String[] lorams = {"loram", "im", "to", "is"};
    Random random = new Random();
    for (int i = 0; i < count; i++) {
      int index = random.nextInt(lorams.length);
      builder.append(lorams[index]).append("");
      builder.append(" ");
    }
    return builder.toString();
  }

  public String last(String pa) {
    return Uri.parse(pa).getLastPathSegment();
  }

  public void setList(String prefix) {
    keyhtml.installFromSora(items, prefix);
    keyhtml.installHtmlAttr(items, prfex);
    keyhtml.intallCss3KeyWord(items, prfex);
    keyhtml.installCssAttr(items, prfex);
    for (String ddd : HTMLLanguage.JS)
      if (ddd.startsWith(prefix)) items.add(dddAsCompletion(ddd, htmlconfig.JsKey));
    for (String classapp : HTMLLanguage.EmtClass)
      if (classapp.startsWith(prefix))
        items.add(AutoCompletion(classapp, "Class adding", HTMLLanguage.getClass));
    for (String getingId : HTMLLanguage.EmtId)
      if (getingId.startsWith(prefix)) items.add(getId(getingId, "Id Tag", HTMLLanguage.getId));

    for (String php : HTMLLanguage.PHP)
      if (php.startsWith(prefix)) items.add(PhpAsCompletion(php, htmlconfig.PhpKeys));

    for (String post : HTMLLanguage.POSTEEMET)
      if (post.startsWith(prefix)) items.add(getEmmetItemPost(post, htmlconfig.Emmet));
    for (String getEmmt : HTMLLanguage.GETHTML5)
      if (getEmmt.startsWith(prefix)) items.add(emmet(getEmmt, htmlconfig.EmmetTagFor));

    for (String getTagNull : HTMLLanguage.TagNull)
      if (getTagNull.startsWith(prefix)) items.add(getEmmetItemPost(getTagNull, htmlconfig.TagOne));

    for (String post : HTMLLanguage.colorsCss)
      if (post.startsWith(prefix)) items.add(PhpAsCompletion(post, htmlconfig.CssColor));

    for (String span : comit) {
      if (span.equalsIgnoreCase("Comment")) {
        if (span.startsWith(prefix)) {
          items.add(CodeSampel(span, "Html 5 Comment", "<!-- Comment -->"));
        }
      }
    }
    for (String phpfuns : PHPLanguage.phpFuns) {
      if (phpfuns.startsWith(prefix)) {
        items.add(dddAsCompletion(phpfuns, htmlconfig.PhpFun));
      }
    }
  }

  public void validTag() {
    String api = "";
    List<String> validTags = new ArrayList<>();
    Collections.sort(validTags, String.CASE_INSENSITIVE_ORDER);
    String[] tags = prfex.split("\\+");
    for (String tag : tags) {
      String trimmedTag = tag.trim();
      if (isValidTag(trimmedTag)) {
        validTags.add(trimmedTag);
      }
    }

    if (!validTags.isEmpty()) {
      StringBuilder openingTags = new StringBuilder();
      StringBuilder closingTags = new StringBuilder();
      for (String tag : validTags) {
        api = tag;
        openingTags.append("<").append(tag).append(">");
        closingTags.insert(0, "</" + tag + ">");
      }
      String wrappedTags = openingTags.toString() + closingTags.toString();
      CompletionItem tagCompletion = new CompletionItem(api, wrappedTags, "Html Snippet Compat");
      tagCompletion.commit = wrappedTags;
      tagCompletion.cursorOffset = openingTags.length() - 2;
      //    Collections.sort(tagCompletion, CompletionItem.COMPARATOR_BY_NAME);
      items.add(tagCompletion);
    }
  }

  public void listfile() {}
}
