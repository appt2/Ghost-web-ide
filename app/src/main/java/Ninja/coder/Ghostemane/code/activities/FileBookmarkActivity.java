package Ninja.coder.Ghostemane.code.activities;

import Ninja.coder.Ghostemane.code.R;
import Ninja.coder.Ghostemane.code.databinding.FilebookmarkBinding;
import Ninja.coder.Ghostemane.code.folder.FileIconHelper;
import Ninja.coder.Ghostemane.code.glidecompat.GlideCompat;
import Ninja.coder.Ghostemane.code.marco.binder.BinderRecyclerview1;
import Ninja.coder.Ghostemane.code.utils.AnimUtils;
import Ninja.coder.Ghostemane.code.utils.ColorAndroid12;
import Ninja.coder.Ghostemane.code.utils.FileUtil;
import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.*;
import androidx.appcompat.widget.Toolbar;
import androidx.coordinatorlayout.widget.CoordinatorLayout;
import com.google.android.material.appbar.AppBarLayout;
import com.google.common.reflect.TypeToken;
import com.google.gson.Gson;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;

public class FileBookmarkActivity extends BaseCompat {
  private String subtitle = "";
  private String Folder = "";
  private double position = 0;
  private String UPFolder = "";
  private ArrayList<HashMap<String, Object>> map = new ArrayList<>();
  private SharedPreferences book, shp;
  protected FilebookmarkBinding bin;

  @Override
  protected void onCreate(Bundle _savedInstanceState) {
    super.onCreate(_savedInstanceState);
    bin = FilebookmarkBinding.inflate(getLayoutInflater());
    setContentView(bin.getRoot());
    initialize(_savedInstanceState);
  }

  private void initialize(Bundle _savedInstanceState) {
    
    setSupportActionBar(bin.Toolbar);
    getSupportActionBar().setDisplayHomeAsUpEnabled(true);
    getSupportActionBar().setHomeButtonEnabled(true);
    bin.Toolbar.setNavigationOnClickListener(
        new View.OnClickListener() {
          @Override
          public void onClick(View _v) {
            onBackPressed();
          }
        });

    
    
    book = getSharedPreferences("hsipsot4444", Activity.MODE_PRIVATE);
    shp = getSharedPreferences("path", Activity.MODE_PRIVATE);

    initializeLogic();
    bin.listviewBookmark.setVisibility(View.VISIBLE);
    bin.listviewBookmark.setEmptyView(bin.layoutBookmarkEmptyview);
    map =
        new Gson()
            .fromJson(
                book.getString("hsipsot4444", ""),
                new TypeToken<ArrayList<HashMap<String, Object>>>() {}.getType());
    bin.listviewBookmark.setAdapter(new FileBookMarkAdapter(map));
    ((BaseAdapter) bin.listviewBookmark.getAdapter()).notifyDataSetChanged();

    bin.listviewBookmark.setOnItemClickListener(
        new AdapterView.OnItemClickListener() {
          @Override
          public void onItemClick(AdapterView<?> _param1, View _param2, int _param3, long _param4) {
            final int _position = _param3;
            var utils = map.get(_position).get("list").toString();
            if (FileUtil.isDirectory(utils)) {
              Intent i = new Intent();
              i.putExtra("bookmarkDir", utils);
              i.setClass(getApplicationContext(), FileDirActivity.class);
              if (i != null) {
                startActivity(i);
              }
            } else {
              var file = new File(map.get(_position).get("list").toString());
              Intent i = new Intent();
               /**
                * using getParent from preview Path not files
                */
              i.putExtra("bookmarkDir", file.getParent());
              i.setClass(getApplicationContext(), FileDirActivity.class);
              if (i != null) {
                startActivity(i);
              }
            }
          }
        });
    bin.listviewBookmark.setDividerHeight(0);
    bin.listviewBookmark.setOnItemLongClickListener(
        new AdapterView.OnItemLongClickListener() {
          @Override
          public boolean onItemLongClick(
              AdapterView<?> _param1, View _param2, int _param3, long _param4) {
            final int _position = _param3;
            map.remove(_position);
            book.edit().putString("hsipsot4444", new Gson().toJson(map)).apply();
            ((BaseAdapter) bin.listviewBookmark.getAdapter()).notifyDataSetChanged();

            return true;
          }
        });
  }

  private void initializeLogic() {
    ColorAndroid12.setToolbarinit(bin.Toolbar);
    //  ColorAndroid12.setTextColor(textview1);
  }

  public void _RefreshData() {
    bin.listviewBookmark.setChoiceMode(ListView.CHOICE_MODE_MULTIPLE);
    bin.listviewBookmark.setItemsCanFocus(false);
  }

  @Override
  protected void onResume() {
    super.onResume();
    // TODO: Implement this method
    ((BaseAdapter)bin.listviewBookmark.getAdapter()).notifyDataSetChanged();
  }

  public class FileBookMarkAdapter extends BaseAdapter {

    ArrayList<HashMap<String, Object>> _data;

    public FileBookMarkAdapter(ArrayList<HashMap<String, Object>> _arr) {
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
      AnimUtils.Sacla(_view);
      ColorAndroid12.setTextColor(textview1);
      File file = new File(map.get((int) _position).get("list").toString());
      FileIconHelper helper = new FileIconHelper(file.toString());
      helper.setDynamicFolderEnabled(true);
      helper.setEnvironmentEnabled(true);
      imageview1.setImageResource(helper.getFileIcon());
      textview1.setText(file.getName());
      if (file.isDirectory()) {

      } else {
        if (BinderRecyclerview1.TaskVideo(file.toString())) {
          GlideCompat.GlideNormal(imageview1, file.toString());
        } else if (BinderRecyclerview1.PhotoView(file.toString())) {
          GlideCompat.GlideNormal(imageview1, file.toString());
        }
        if (file.toString().endsWith(".xml")) {
          GlideCompat.LoadVector(file.toString(), imageview1);
        } else if (file.toString().endsWith(".mp3")) {
          GlideCompat.GlideLoadMp3(imageview1, file.toString());
        } else if (file.toString().endsWith(".svg")) {
          GlideCompat.LoadSvg(file.toString(), imageview1);
        } else if (file.toString().endsWith(".pdf")) {
          try {
            GlideCompat.loadImgPdf(file, imageview1);
          } catch (IOException err) {
            imageview1.setImageResource(R.drawable.ic_material_pdf);
          }
        } else if (file.toString().endsWith(".apk")) {
          GlideCompat.LoadApkFile(file.toString(), imageview1);
        } else if (file.toString().endsWith(".swb")) {
          GlideCompat.LoadSwbIcon(file.toString(), imageview1);
        }else if(file.toString().endsWith(".vsix")){
          GlideCompat.LoadIconVsCode(file.toString(),imageview1);
        }
      }
      return _view;
    }
  }
}
