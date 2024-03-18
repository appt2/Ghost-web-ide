package io.github.rosemoe.sora.langs.javascript;

import io.github.rosemoe.sora.data.CompletionItem;
import io.github.rosemoe.sora.text.TextAnalyzeResult;

import java.util.ArrayList;
import java.util.List;

import io.github.rosemoe.sora.interfaces.AutoCompleteProvider;

public class JsAutoComplete implements AutoCompleteProvider {

    @Override
    public List<CompletionItem> getAutoCompleteItems(
            String prefix, TextAnalyzeResult analyzeResult, int line, int column) {
        List<CompletionItem> list = new ArrayList<>();
        for (String itemLang : JavaScriptLanguage.keywords) {
            if (itemLang.startsWith(prefix)) {
                list.add(Normal(itemLang, "JavaScript Keywords"));
            }
        }

        return list;
    }

    private CompletionItem Sp(String em, String desc, String open) {
        final CompletionItem item = new CompletionItem(em + " ", desc);
        item.commit = open;
        item.cursorOffset(item.commit.length() - 2);
        return item;
    }

    private CompletionItem Normal(String mcss, String desc) {
        final var item = new CompletionItem(mcss + "  ", desc);
        item.cursorOffset(item.commit.length() - 1);
        return item;
    }
}
