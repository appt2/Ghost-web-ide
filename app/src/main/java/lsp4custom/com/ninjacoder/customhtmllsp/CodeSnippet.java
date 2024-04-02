package lsp4custom.com.ninjacoder.customhtmllsp;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import com.google.gson.Gson;
import java.util.ArrayList;
import java.util.Map;
import java.util.Set;
import com.google.gson.JsonElement;
import io.github.rosemoe.sora.data.CompletionItem;
import java.util.List;

public class CodeSnippet {
  public String jsonPath;

  public CodeSnippet(String jsonPath) {
    this.jsonPath = jsonPath;
  }
  public void run(List<CompletionItem> list){
    JsonObject jsonObject = JsonParser.parseString(jsonPath).getAsJsonObject();
    Gson gson = new Gson();
    List<CompletionItem> codeSnippets = new ArrayList<>();
    Set<Map.Entry<String, JsonElement>> jsonEntries = jsonObject.entrySet();
    for (Map.Entry<String, JsonElement> entry : jsonEntries) {
      CompletionItem snippet = gson.fromJson(entry.getValue(), CompletionItem.class);
       codeSnippets.add(snippet);
    }
    
    list.addAll(codeSnippets);
  }
}
