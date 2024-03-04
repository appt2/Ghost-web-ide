package Ninja.coder.Ghostemane.code.adapter;

import Ninja.coder.Ghostemane.code.GlideUtilApi.GlideCompat;
import Ninja.coder.Ghostemane.code.R;
import android.net.Uri;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.recyclerview.widget.RecyclerView;
import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class IconShopAd extends RecyclerView.Adapter<IconShopAd.VH> {
  protected List<File> files;
  protected IconShopCallBack callback;

  public IconShopAd(List<File> files, IconShopCallBack callback) {
    this.files = files;
    this.callback = callback;
  }

  @Override
  public int getItemCount() {
    return files.size();
  }

  @Override
  public void onBindViewHolder(VH viewholder, int pos) {
    File mfile = files.get(pos);
    GlideCompat.LoadSvg(mfile.getName(),viewholder.icon);
    viewholder.nameIcon.setText(mfile.getName());
    View view = viewholder.itemView;
    viewholder.icon.setOnClickListener(v -> callback.OnItemClicket(pos,v));
    viewholder.icon.setOnLongClickListener(c ->{
        callback.OnItemLongClicket(pos,c);
      return false;
    });
    viewholder.nameIcon.setEllipsize(TextUtils.TruncateAt.MARQUEE);
    viewholder.nameIcon.setMarqueeRepeatLimit(-1);
    viewholder.nameIcon.setSingleLine(true);
    viewholder.nameIcon.setSelected(true);
//    var param =
//          new RecyclerView.LayoutParams(
//              ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT);
//      viewholder.itemView.setLayoutParams(param);
  }

  @Override
  public VH onCreateViewHolder(ViewGroup parnt, int viewType) {
    var view = LayoutInflater.from(parnt.getContext()).inflate(R.layout.icon,parnt,false);
    var param =
          new RecyclerView.LayoutParams(
              ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT);
      view.setLayoutParams(param);
    return new VH(view);
  }

  class VH extends RecyclerView.ViewHolder {
    private ImageView icon;
    private TextView nameIcon;
    
    public VH(View itemview) {
      super(itemview);
      
      icon = itemview.findViewById(R.id.icon_from_iconmode);
      nameIcon = itemview.findViewById(R.id.name_icon_fromiconmode);
    }
  }

  public interface IconShopCallBack {
    public void OnItemClicket(int pos, View view);

    public void OnItemLongClicket(int pos, View view);
  }
  
}
