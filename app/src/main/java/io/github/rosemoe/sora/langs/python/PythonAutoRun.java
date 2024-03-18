package io.github.rosemoe.sora.langs.python;

import io.github.rosemoe.sora.data.CompletionItem;
import io.github.rosemoe.sora.interfaces.AutoCompleteProvider;
import io.github.rosemoe.sora.text.TextAnalyzeResult;
import io.github.rosemoe.sora.util.ArrayList;

import java.util.List;

public class PythonAutoRun implements AutoCompleteProvider {
    private List<CompletionItem> items;
    private PythonLang language;

    public PythonAutoRun() {
        language = new PythonLang();

    }

    @Override
    public List<CompletionItem> getAutoCompleteItems(String prefix, TextAnalyzeResult analyzeResult, int line,
                                                     int column) {
        items = new ArrayList<>();
        for (String users : PythonTextTokenizer.sKeywords) {
            if (users.startsWith(prefix)) {
                items.add(normalKey(users, "Python KeyWord Normal"));
            }
        }


        return items;
    }

    private CompletionItem normalKey(String php, String desc) {
        final CompletionItem item = new CompletionItem(php, desc);
        item.cursorOffset(item.commit.length());
        return item;
    }
}
