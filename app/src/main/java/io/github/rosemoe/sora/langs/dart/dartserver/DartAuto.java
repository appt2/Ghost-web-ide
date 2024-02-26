package io.github.rosemoe.sora.langs.dart.dartserver;

import io.github.rosemoe.sora.data.CompletionItem;
import io.github.rosemoe.sora.text.TextAnalyzeResult;
import java.util.ArrayList;
import java.util.List;
import io.github.rosemoe.sora.interfaces.AutoCompleteProvider;
import Ninja.coder.Ghostemane.code.project.FileReaderJsonSpinet;

public class DartAuto implements AutoCompleteProvider {
	private List<CompletionItem> list = new ArrayList<>();

	@Override
	public List<CompletionItem> getAutoCompleteItems(
		String prefix, TextAnalyzeResult analyzeResult, int line, int column) {
		for (var item : HotserverDart.keyword) {
			if (item.startsWith(prefix)) {
				list.add(dhelper(item, HotserverDart.KEYPATHLIST));
			}
		}
		FileReaderJsonSpinet reader = new FileReaderJsonSpinet();
		reader.Start(list, "dart3");
		return list;
	}

	protected CompletionItem dhelper(String dec, String word) {
		var mylist = new CompletionItem(dec, word);
		var u = mylist.commit;
		mylist.cursorOffset(u.length() - 1);
		return mylist;
	}
}
