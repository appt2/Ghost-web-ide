package io.github.rosemoe.sora.langs.python;

import io.github.rosemoe.sora.data.CompletionItem;
import io.github.rosemoe.sora.interfaces.AutoCompleteProvider;
import io.github.rosemoe.sora.text.TextAnalyzeResult;
import io.github.rosemoe.sora.util.ArrayList;
import java.util.List;

public class PythonAutoComplete implements AutoCompleteProvider {
	private List<CompletionItem> items;
	private PythonLang language;
	protected String prfex;

	public PythonAutoComplete() {
		language = new PythonLang();

	}

	@Override
	public List<CompletionItem> getAutoCompleteItems(String prefix, TextAnalyzeResult analyzeResult, int line,
			int column) {
		items = new ArrayList<>();
		prfex = prefix;
		for (var users : PythonTextTokenizer.sKeywords) {
			if (users.startsWith(prefix)) {
				items.add(normalKey(users, "Python KeyWord Normal"));
			}
		}
		CustomSinppnet("print", "print('')", "Python", "print");

		return items;
	}

	public void CustomSinppnet(String text, String dec, String type, String data) {
		if (text.startsWith(prfex) && prfex.length() > 0) {
			items.add(CodeSampel(data, "Sinppnet " + type, dec));
		}
	}

	private CompletionItem normalKey(String php, String desc) {
		final CompletionItem item = new CompletionItem(php + " ", desc);
		item.cursorOffset(item.commit.length() - 5);
		return item;
	}

	private CompletionItem CodeSampel(String em, String desc, String codeS) {
		final var open = codeS;
		final CompletionItem item = new CompletionItem(em, desc);
		item.commit = open;
		item.cursorOffset(item.commit.length() - 4);
		return item;
	}
}
