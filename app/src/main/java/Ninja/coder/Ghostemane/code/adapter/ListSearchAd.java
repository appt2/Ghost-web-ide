package Ninja.coder.Ghostemane.code.adapter;

import Ninja.coder.Ghostemane.code.R;
import android.widget.Adapter;
import android.widget.BaseAdapter;
import java.util.ArrayList;
import java.util.HashMap;
import android.view.View;
import android.view.ViewGroup;
import android.view.LayoutInflater;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.ImageView;
import android.net.Uri;
import Ninja.coder.Ghostemane.code.FileUtil;

public class ListSearchAd extends BaseAdapter implements Adapter {

  ArrayList<HashMap<String, Object>> _data;

  public ListSearchAd(ArrayList<HashMap<String, Object>> _arr) {
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
  public View getView( int position, View _v, ViewGroup parant) {
    
    View mview = LayoutInflater.from(parant.getContext()).inflate(R.layout.aaa, parant,false);

    final LinearLayout main = mview.findViewById(R.id.main);
    final TextView tv = mview.findViewById(R.id.tv);
    final TextView tvnull = mview.findViewById(R.id.tvnull);
    final ImageView img = mview.findViewById(R.id.img);
    tv.setText(Uri.parse(_data.get((int) position).get("path").toString()).getLastPathSegment());
    var sodocerListMapRemainder = _data.get((int) position).get("path").toString();
    if (FileUtil.isDirectory(sodocerListMapRemainder)) {
      img.setImageResource(R.drawable.folder);
    } else {
      img.setImageResource(R.drawable.file);
    }

    return mview;
  }
}
