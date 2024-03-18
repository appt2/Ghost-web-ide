package Ninja.coder.Ghostemane.code;

import android.content.Intent;
import android.util.Log;
import android.widget.AdapterView;
import android.widget.Toast;
import androidx.appcompat.widget.Toolbar;
import com.google.android.material.appbar.AppBarLayout;
import androidx.coordinatorlayout.widget.CoordinatorLayout;
import com.google.common.reflect.TypeToken;
import com.google.gson.Gson;
import java.util.ArrayList;
import java.util.HashMap;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.widget.BaseAdapter;
import android.view.View;
import android.view.ViewGroup;
import android.app.Activity;
import android.view.LayoutInflater;
import android.widget.ImageView;
import android.net.Uri;

public class FilebookmarkActivity extends BaseCompat {

  private Toolbar _toolbar;
  private AppBarLayout _app_bar;
  private CoordinatorLayout _coordinator;
  private String subtitle = "";
  private String Folder = "";
  private double position = 0;
  private String UPFolder = "";
  private ArrayList<HashMap<String, Object>> map = new ArrayList<>();
  private ListView listview1;
  private LinearLayout layout_bookmark_emptyview;

  private SharedPreferences book, shp;

  @Override
  protected void onCreate(Bundle _savedInstanceState) {
    super.onCreate(_savedInstanceState);
    setContentView(R.layout.filebookmark);
    initialize(_savedInstanceState);
  }

  private void initialize(Bundle _savedInstanceState) {
    _app_bar = findViewById(R.id._app_bar);
    _coordinator = findViewById(R.id._coordinator);
    _toolbar = findViewById(R.id._toolbar);
    setSupportActionBar(_toolbar);
    getSupportActionBar().setDisplayHomeAsUpEnabled(true);
    getSupportActionBar().setHomeButtonEnabled(true);
    _toolbar.setNavigationOnClickListener(
        new View.OnClickListener() {
          @Override
          public void onClick(View _v) {
            onBackPressed();
          }
        });

    listview1 = findViewById(R.id.listview_bookmark);
    layout_bookmark_emptyview = findViewById(R.id.layout_bookmark_emptyview);
    book = getSharedPreferences("hsipsot4444", Activity.MODE_PRIVATE);
    shp = getSharedPreferences("path", Activity.MODE_PRIVATE);

    initializeLogic();
    listview1.setVisibility(View.VISIBLE);
    listview1.setEmptyView(layout_bookmark_emptyview);
    map =
        new Gson()
            .fromJson(
                book.getString("hsipsot4444", ""),
                new TypeToken<ArrayList<HashMap<String, Object>>>() {}.getType());
    listview1.setAdapter(new Listview1Adapter(map));
    ((BaseAdapter) listview1.getAdapter()).notifyDataSetChanged();
    Toast.makeText(getApplication(), new Gson().toJson(book.getString("hsipsot4444", "")), 2)
        .show();

    listview1.setOnItemClickListener(
        new AdapterView.OnItemClickListener() {
          @Override
          public void onItemClick(AdapterView<?> _param1, View _param2, int _param3, long _param4) {
            final int _position = _param3;
            var utils = map.get(_position).get("list").toString();
            if (FileUtil.isDirectory(utils)) {
              Intent i = new Intent();
              i.putExtra("bookmarkDir", utils);
              i.setClass(getApplicationContext(), FiledirActivity.class);
              if (i != null) {
                startActivity(i);
              }
            } else {
              Log.e("TAG", "Not work file");
            }
          }
        });
    listview1.setDividerHeight(0);
    listview1.setOnItemLongClickListener(
        new AdapterView.OnItemLongClickListener() {
          @Override
          public boolean onItemLongClick(
              AdapterView<?> _param1, View _param2, int _param3, long _param4) {
            final int _position = _param3;
            map.remove(_position);
            book.edit().putString("hsipsot4444", new Gson().toJson(map)).apply();
            ((BaseAdapter) listview1.getAdapter()).notifyDataSetChanged();

            return true;
          }
        });
  }

  private void initializeLogic() {
    ColorAndroid12.setToolbarinit(_toolbar);
    //  ColorAndroid12.setTextColor(textview1);
  }

  public void _RefreshData() {
    listview1.setChoiceMode(ListView.CHOICE_MODE_MULTIPLE);
    listview1.setItemsCanFocus(false);
  }

  public class Listview1Adapter extends BaseAdapter {

    ArrayList<HashMap<String, Object>> _data;

    public Listview1Adapter(ArrayList<HashMap<String, Object>> _arr) {
      _data = _arr;
    }

    @Override
    public int getCount() {
      return _data.size();
    }

    @Override
    public HashMap<String, Object> getItem(int _index) {
      return _data.get(_index);
    }

    @Override
    public long getItemId(int _index) {
      return _index;
    }

    @Override
    public View getView(final int _position, View _v, ViewGroup _container) {
      LayoutInflater _inflater = getLayoutInflater();
      View _view = _v;
      if (_view == null) {
        _view = _inflater.inflate(R.layout.layout_filebookmark, null);
      }

      final LinearLayout linear1 = _view.findViewById(R.id.linear1);
      final LinearLayout linear2 = _view.findViewById(R.id.linear2);
      final ImageView imageview1 = _view.findViewById(R.id.imageview1);
      final TextView textview1 = _view.findViewById(R.id.textview1);

      ColorAndroid12.shap(imageview1);
      Toast.makeText(getApplicationContext(), _data.get((int) _position).get("list").toString(), 2)
          .show();
      ColorAndroid12.setTextColor(textview1);

      textview1.setText(
          Uri.parse(map.get((int) _position).get("list").toString()).getLastPathSegment());
      return _view;
    }
  }

  public boolean FileText(String foo) {
    return foo.endsWith(".java")
        || foo.endsWith(".js")
        || foo.endsWith(".css")
        || foo.endsWith(".scss")
        || foo.endsWith(".kt")
        || foo.endsWith(".ninja")
        || foo.endsWith(".ghost")
        || foo.endsWith(".c")
        || foo.endsWith(".cpp")
        || foo.endsWith(".cs");
  }

  public boolean MediaEnd(String foo) {
    return foo.endsWith(".mp3")
        || foo.endsWith(".mp4")
        || foo.endsWith(".gif")
        || foo.endsWith(".png")
        || foo.endsWith(".jpg")
        || foo.endsWith(".tiff")
        || foo.endsWith(".tif")
        || foo.endsWith(".mva");
  }

  @Override
  protected void onResume() {
    super.onResume();
    // TODO: Implement this method
  }
}
