package Ninja.coder.Ghostemane.code.project;

import Ninja.coder.Ghostemane.code.CodeeditorActivity;
import Ninja.coder.Ghostemane.code.FileUtil;
import android.net.Uri;
import com.google.gson.GsonBuilder;
import java.io.File;
import java.io.IOException;
import java.io.BufferedReader;
import java.lang.reflect.Type;
import java.io.FileWriter;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.io.FileReader;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import android.util.Log;
import java.io.Serializable;
import java.util.List;
import io.github.rosemoe.sora.data.CompletionItem;

public class FileReaderJsonSpinet implements Serializable {
  private CompletionItem itemHelper;

  public FileReaderJsonSpinet() {
    makeFileJson();
  }

  private String getFileNameJson(String name) {
    var a =
        "/storage/emulated/0/GhostWebIDE/codeHelper/".concat("lang.").concat(name).concat(".json");
    Log.v("Json File Found -> ", a);
    Log.w("Json File Found -> ", a);
    return a;
  }

  public void Start(List<CompletionItem> items, String name) {
    try {
      final List<CompletionItem> jsonData = readItemsFromJson(new File(getFileNameJson(name)));
      if (!jsonData.isEmpty()) {
        for(CompletionItem it : jsonData) {
        	Log.e("JSONNAME",it.name);
          Log.e("JSONVALUE",it.value);
          if(it != null) {
          	items.add(new CompletionItem(it.name,it.value,it.sp));
          }
        }
        items.addAll(jsonData);
        
        Log.e("JSONDATA",jsonData.toString());
      } else {
        Log.e("List", jsonData.toString());
      }
    } catch (Exception err) {
      Log.e("List2", err.getMessage());
    }
  }

  private static class CompletionItemComparator implements Comparator<CompletionItem> {
    @Override
    public int compare(CompletionItem item1, CompletionItem item2) {
      return item1.name.compareTo(item2.name);
    }
  }

  private List<CompletionItem> readItemsFromJson(File file) throws IOException {
    return new Gson()
        .fromJson(readFileAsString(file), new TypeToken<List<CompletionItem>>() {}.getType());
  }

  public String readFileAsString(File file) throws IOException {
    final StringBuilder contentBuilder = new StringBuilder();
    try (final BufferedReader br = new BufferedReader(new FileReader(file))) {
      String currentLine;
      while ((currentLine = br.readLine()) != null) {
        contentBuilder.append(currentLine).append("\n");
      }
    }
    return contentBuilder.toString();
  }

  private boolean isJsonError(String string) {
    HashMap<String, Object> map = new HashMap<>();
    ArrayList<HashMap<String, Object>> listmap = new ArrayList<>();
    try {
      map = new Gson().fromJson(string, new TypeToken<HashMap<String, Object>>() {}.getType());
      return true;
    } catch (Exception e0) {
      try {
        listmap =
            new Gson()
                .fromJson(string, new TypeToken<ArrayList<HashMap<String, Object>>>() {}.getType());
        return true;
      } catch (Exception e1) {
        return false;
      }
    }
  }

  public void addToJsonFile(String name, String newNameCode, String newSp, String newCodeName) {
    try {
      File file = new File(getFileNameJson(name));
      if (file.exists()) {
        Gson gson = new GsonBuilder().setPrettyPrinting().create();
        Type listType = new TypeToken<ArrayList<HashMap<String, Object>>>() {}.getType();
        ArrayList<HashMap<String, Object>> map = gson.fromJson(new FileReader(file), listType);

        HashMap<String, Object> newItem = new HashMap<>();
        newItem.put("name", newNameCode);
        newItem.put("value", newCodeName);
        newItem.put("sp", newSp);

        map.add(newItem);
        Log.w("Map adding in item", newItem.toString());
        FileWriter writer = new FileWriter(file);
        gson.toJson(map, writer);
        writer.close();
      } else {
        Log.e("FileNot Found", "Error File not found in " + file.getName());
      }
    } catch (Exception e) {
      Log.e("Error", e.getMessage());
    }
  }

  public void makeFileJson() {

    /// Make Config file
    if (!FileUtil.isExistFile("/storage/emulated/0/GhostWebIDE/codeHelper/config.json")) {
      Log.v("File maket Done", "This file maket ");
      FileUtil.writeFile(
          "/storage/emulated/0/GhostWebIDE/codeHelper/config.json",
          "{\n  \"model\": [\n    {\n      \"App\": \"GhostWebide\",\n      \"id\": 160,\n      \"moder Ops\": \"ninjacoder\"\n    }\n  ],\n  \"lang\": [\n    \"langs\",\n    {\n      \"nameLang\": \"lang.html\"\n    },\n    {\n      \"nameLang\": \"lang.cpp\"\n    },\n    \"Json\",\n    {\n      \"Format\": \"Json\",\n      \"Acssent\": true\n    }\n  ]\n}");
    }
    /// Make Lang File
    if (!FileUtil.isExistFile("/storage/emulated/0/GhostWebIDE/codeHelper/lang.cpp.json")) {
      Log.v("File maket Done", "This file maket ");
      FileUtil.writeFile(
          "/storage/emulated/0/GhostWebIDE/codeHelper/lang.cpp.json",
          "[\n    {\n        \"name\": \"pys\",\n        \"value\": \"$YourCode\",\n        \"sp\": \"Your work code?\"\n    }\n]");
    }
    if (!FileUtil.isExistFile("/storage/emulated/0/GhostWebIDE/codeHelper/lang.html.json")) {
      Log.v("File maket Done", "This file maket ");
      FileUtil.writeFile(
          "/storage/emulated/0/GhostWebIDE/codeHelper/lang.html.json",
          "[\n    {\n        \"name\": \"ninja\",\n        \"value\": \"Test code\",\n        \"sp\": \"make test code\"\n    }\n]");
    }
    if (!FileUtil.isExistFile("/storage/emulated/0/GhostWebIDE/codeHelper/lang.python.json")) {
      Log.v("File maket Done", "This file maket ");
      FileUtil.writeFile(
          "/storage/emulated/0/GhostWebIDE/codeHelper/lang.python.json",
          "[\n    {\n        \"name\": \"ninja\",\n        \"value\": \"Test code\",\n        \"sp\": \"make test code\"\n    }\n]");
    }
    if (!FileUtil.isExistFile("/storage/emulated/0/GhostWebIDE/codeHelper/lang.js.json")) {
      Log.v("File maket Done", "This file maket ");
      FileUtil.writeFile(
          "/storage/emulated/0/GhostWebIDE/codeHelper/lang.js.json",
          "[\n    {\n        \"name\": \"ninja\",\n        \"value\": \"Test code\",\n        \"sp\": \"make test code\"\n    }\n]");
    }
    if (!FileUtil.isExistFile("/storage/emulated/0/GhostWebIDE/codeHelper/lang.css3.json")) {
      Log.v("File maket Done", "This file maket ");
      FileUtil.writeFile(
          "/storage/emulated/0/GhostWebIDE/codeHelper/lang.css3.json",
          "[\n    {\n        \"name\": \"ninja\",\n        \"value\": \"Test code\",\n        \"sp\": \"make test code\"\n    }\n]");
    }
    if (!FileUtil.isExistFile("/storage/emulated/0/GhostWebIDE/codeHelper/lang.php.json")) {
      Log.v("File maket Done", "This file maket ");
      FileUtil.writeFile(
          "/storage/emulated/0/GhostWebIDE/codeHelper/lang.php.json",
          "[\n    {\n        \"name\": \"ninja\",\n        \"value\": \"Test code\",\n        \"sp\": \"make test code\"\n    }\n]");
    }
    if (!FileUtil.isExistFile("/storage/emulated/0/GhostWebIDE/codeHelper/lang.ts.json")) {
      Log.v("File maket Done", "This file maket ");
      FileUtil.writeFile(
          "/storage/emulated/0/GhostWebIDE/codeHelper/lang.ts.json",
          "[\n    {\n        \"name\": \"ninja\",\n        \"value\": \"Test code\",\n        \"sp\": \"make test code\"\n    }\n]");
    }
    if (!FileUtil.isExistFile("/storage/emulated/0/GhostWebIDE/codeHelper/lang.dart3.json")) {
      Log.v("File maket Done", "This file maket ");
      FileUtil.writeFile(
          "/storage/emulated/0/GhostWebIDE/codeHelper/lang.dart3.json",
          "[\n    {\n        \"name\": \"ninja\",\n        \"value\": \"Test code\",\n        \"sp\": \"make test code\"\n    }\n]");
    }
    if (!FileUtil.isExistFile("/storage/emulated/0/GhostWebIDE/codeHelper/lang.java.json")) {
      Log.v("File maket Done", "This file maket ");
      FileUtil.writeFile(
          "/storage/emulated/0/GhostWebIDE/codeHelper/lang.java.json",
          "[\n    {\n        \"name\": \"ninja\",\n        \"value\": \"Test code\",\n        \"sp\": \"make test code\"\n    }\n]");
    }
    if (!FileUtil.isExistFile("/storage/emulated/0/GhostWebIDE/codeHelper/configAndroid.xml")) {
      Log.v("File maket Done", "This file maket ");
      FileUtil.writeFile(
          "/storage/emulated/0/GhostWebIDE/codeHelper/configAndroid.xml",
          "<?xml version=\"1.0\" encoding=\"UTF-8\"?>\n<root> \n  <model array=\"true\"> \n    <App>\n       GhostWebide \n    </App> \n    <id number=\"true\">\n       160 \n    </id> \n    <moder__EA__Ops>\n       ninjacoder \n    </moder__EA__Ops> \n  </model> \n  <lang>\n     langs \n  </lang> \n  <lang> \n    <nameLang>\n       lang.html \n    </nameLang> \n  </lang> \n  <lang> \n    <nameLang>\n       lang.cpp \n    </nameLang> \n  </lang> \n  <lang>\n     Json \n  </lang> \n  <lang> \n    <Format>\n       Json ,html,css,ts,js,dart3\n    </Format> \n    <Acssent boolean=\"true\">\n       true \n    </Acssent> \n  </lang> \n</root>");
    }
  }
  public void ListFile(List<CompletionItem> items){
  }
}
