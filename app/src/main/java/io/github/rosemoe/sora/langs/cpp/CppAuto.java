package io.github.rosemoe.sora.langs.cpp;

import io.github.rosemoe.sora.data.CompletionItem;
import io.github.rosemoe.sora.text.TextAnalyzeResult;
import java.io.File;
import java.util.HashMap;
import java.io.FileReader;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import android.util.Log;
import java.util.ArrayList;
import java.util.List;
import io.github.rosemoe.sora.interfaces.AutoCompleteProvider;
import Ninja.coder.Ghostemane.code.project.FileReaderJsonSpinet;

public class CppAuto implements AutoCompleteProvider {
  private List<CompletionItem> item;
	private FileReaderJsonSpinet reader;

  @Override
  public List<CompletionItem> getAutoCompleteItems(
      String prefix, TextAnalyzeResult analyzeResult, int line, int column) {
    item = new ArrayList<>();
   
	  reader = new FileReaderJsonSpinet();
	  reader.Start(item,"cpp");

    for (var ib : CppTextTokenizer.sKeywords) {
      if (ib.startsWith(prefix)) {
        item.add(CppAsCompletion(ib, "Cpp Keyword"));
      }
    }
    return item;
  }

  private CompletionItem CppAsCompletion(String php, String desc) {
    final CompletionItem item = new CompletionItem(php + " ", desc);
    item.cursorOffset(item.commit.length() - 1);
    return item;
  }

  private CompletionItem CodeSampel(String em, String desc, String codeS) {
    final var open = codeS;
    final CompletionItem item = new CompletionItem(em + " ", desc);
    item.commit = open;
    item.cursorOffset(item.commit.length() - 1);
    return item;
  }
}
