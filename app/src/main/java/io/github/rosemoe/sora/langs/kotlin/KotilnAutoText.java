package io.github.rosemoe.sora.langs.kotlin;

import io.github.rosemoe.sora.data.CompletionItem;
import java.util.ArrayList;
import io.github.rosemoe.sora.interfaces.AutoCompleteProvider;
import io.github.rosemoe.sora.text.TextAnalyzeResult;
import java.util.List;

public class KotilnAutoText implements AutoCompleteProvider {
    private List<CompletionItem> item;
    @Override
    public List<CompletionItem> getAutoCompleteItems(
            String prefix, TextAnalyzeResult analyzeResult, int line, int column) {
        
        item = new ArrayList<>();
        for(var items : KotilnKeys.ktKeyWord) {
        	if(items.startsWith(prefix)) {
        		item.add(kts(items,"KotlinKeyWords"));
        	}
        }
        for(var other : KotilnKeys.sampel()) {
        	if(other.contains("print")){
                if(other.startsWith(prefix)) {
                	item.add(CodeSampel(prefix,"print", "print(\"$\")"));
                }
            }else if(other.contains("main")){
                if(other.startsWith(prefix)){
                    item.add(CodeSampel(prefix,"main", "fun main(){\n    \n    print(\"Hello\")\n}"));
                }
            }else if(other.contains("ifnotNull")){
                if(other.startsWith(prefix)){
                    item.add(CodeSampel(prefix,"kts Utils",  "if($object != null){\n	/** your code **/\n}\n"));
                }
            }else if(other.contains("innerClass")){
                if(other.startsWith(prefix)){
                    item.add(CodeSampel(prefix,"ktsUtils","inner class app(){\n	fun hello(){\n		print(\"Hello\")\n	}\n}\n"));
                }
            }
            else{
                item.add(kts(other,"kts OtherKeyWord"));
            }
        }
        
        return item;
    }
    private CompletionItem kts(String des ,String doc){
        var keys = new CompletionItem(des ,doc);
        keys.cursorOffset(keys.commit.length() -1);
        return keys;
    }
    private CompletionItem CodeSampel(String em, String desc, String codeS) {
        final var open = codeS;
        final CompletionItem item = new CompletionItem(em, desc);
        item.commit = open;
        item.cursorOffset(item.commit.length() - 1);
        return item;
    }
}
