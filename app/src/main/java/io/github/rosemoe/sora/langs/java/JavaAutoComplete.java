package io.github.rosemoe.sora.langs.java;

import Ninja.coder.Ghostemane.code.project.FileReaderJsonSpinet;
import io.github.rosemoe.sora.interfaces.AutoCompleteProvider;
import io.github.rosemoe.sora.text.TextAnalyzeResult;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import io.github.rosemoe.sora.data.CompletionItem;

public class JavaAutoComplete implements AutoCompleteProvider {
  List<String> forEach;
  private ArrayList<HashMap<String, Object>> javacode1 = new ArrayList<>();
  private String value = "";
  private ArrayList<HashMap<String, Object>> listas = new ArrayList<>();

  @Override
  public List<CompletionItem> getAutoCompleteItems(
      String prefix, TextAnalyzeResult analyzeResult, int line, int column) {

    List<CompletionItem> item = new ArrayList<>();
    for (var javakey : JavaTextTokenizer.sKeywords) {
      if (javakey.startsWith(prefix)) {
        item.add(PhpAsCompletion(javakey, "Java Normal KeyWord"));
      }
    }
    FileReaderJsonSpinet object = new FileReaderJsonSpinet();
    object.Start(item, "java");
    for (var itemimport : data) {
      if (itemimport.startsWith(prefix)) {
        item.add(PhpAsCompletion(itemimport, "Java import"));
      }
    }

    return item;
  }

  private CompletionItem PhpAsCompletion(String php, String desc) {
    final CompletionItem item = new CompletionItem(php + "  ", desc);
    item.cursorOffset(item.commit.length() - 1);
    return item;
  }

  private CompletionItem CodeSampel(String em, String desc, String codeS) {
    final var open = codeS;
    final CompletionItem item = new CompletionItem(em, desc);
    item.commit = open;
    item.cursorOffset(item.commit.length() - open.length());
    return item;
  }

  protected String[] data = {"android.Manifest$permission"};
}
