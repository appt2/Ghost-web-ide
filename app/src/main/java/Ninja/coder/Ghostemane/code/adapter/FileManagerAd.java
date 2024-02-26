package Ninja.coder.Ghostemane.code.adapter;

import Ninja.coder.Ghostemane.code.AnimUtils;
import Ninja.coder.Ghostemane.code.ColorAndroid12;
import Ninja.coder.Ghostemane.code.FileUtil;
import Ninja.coder.Ghostemane.code.R;
import Ninja.coder.Ghostemane.code.databin.FileMaker;
import Ninja.coder.Ghostemane.code.interfaces.FileCallBack;
import Ninja.coder.Ghostemane.code.marco.BindViewListMarger;
import Ninja.coder.Ghostemane.code.marco.binder.BinderRecyclerview1;
import android.content.Context;
import android.content.res.ColorStateList;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.graphics.drawable.RippleDrawable;
import android.net.Uri;
import android.text.TextUtils;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;
import androidx.recyclerview.widget.RecyclerView;
import com.google.android.material.color.MaterialColors;
import java.util.ArrayList;
import java.util.HashMap;

// fileListItem
public class FileManagerAd extends RecyclerView.Adapter<FileManagerAd.VH> {
  private ArrayList<HashMap<String, Object>> files = new ArrayList<>();
  protected Context context;
  protected onClick click;
  protected HashMap<String, Object> name = new HashMap<>();
  public static boolean Files = false;
  public static boolean Folder = false;

  public FileManagerAd(ArrayList<HashMap<String, Object>> files, Context context, onClick click) {
    this.context = context;
    this.files = files;
    this.click = click;
    registerAdapterDataObserver(
        new RecyclerView.AdapterDataObserver() {

          @Override
          public void onChanged() {
            super.onChanged();
            if (getItemCount() == 0) {
              Log.e("item reday", "getItemCount()");
            } else {
              Log.e("item not redy", "0");
            }
          }
        });
  }

  @Override
  public int getItemCount() {
    return files.size();
  }

  @Override
  public VH onCreateViewHolder(ViewGroup parnt, int pos) {
    View view =
        LayoutInflater.from(parnt.getContext()).inflate(R.layout.folder_remster, parnt, false);
    RecyclerView.LayoutParams _lp =
        new RecyclerView.LayoutParams(
            ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);
    view.setLayoutParams(_lp);
    return new VH(view);
  }

  @Override
  public void onBindViewHolder(VH viewHolder, int pos) {
    View view = viewHolder.itemView;
    var ripdr =
        new RippleDrawable(
            new ColorStateList(new int[][] {new int[] {}}, new int[] {Color.GREEN}),
            new ColorDrawable(0),
            null);
    view.setBackground(ripdr);
    RecyclerView.LayoutParams _lp =
        new RecyclerView.LayoutParams(
            ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);
    view.setLayoutParams(_lp);
    AnimUtils.Sacla(viewHolder.itemView);
    setSettingTextView(viewHolder.folderName);
    viewHolder.folderName.setText(
        Uri.parse(files.get(pos).get("path").toString()).getLastPathSegment());
    BinderRecyclerview1.bindz(files, pos, viewHolder.tvTools, viewHolder.icon,viewHolder.folderName);
    if (FileUtil.isDirectory(files.get(pos).get("path").toString())) {
      Folder = true;
      Files = false;
      viewHolder.icon.setPadding(9, 9, 9, 9);
      ColorAndroid12.shapeViews(viewHolder.icon);
      

    } else if (FileUtil.isExistFile(files.get(pos).get("path").toString())) {
      BindViewListMarger.runfromFile(viewHolder.folderName);
      viewHolder.icon.setPadding(0, 0, 0, 0);
      BinderRecyclerview1.off(viewHolder.icon);
      Files = true;
      Folder = false;
    }
//    File file = new File(files.get(pos).get("path").toString());
//    if (view != null) {
//      if (file.isDirectory()) view.setAlpha(file.isHidden() ? 0.5f : 1f);
//    }
//    

    viewHolder.itemView.setClickable(true);
//    viewHolder.folderName.setTextColor(
//        MaterialColors.getColor(viewHolder.folderName, ColorAndroid12.colorOnSurface));
//    viewHolder.tvTools.setTextColor(
//        MaterialColors.getColor(viewHolder.tvTools, ColorAndroid12.colorOnSurface));
  }

  public class VH extends RecyclerView.ViewHolder {
    protected TextView folderName, tvTools;
    protected LinearLayout roots;
    protected ImageView icon;
    View getPos;

    public VH(View view) {
      super(view);
      getPos = view;
      folderName = view.findViewById(R.id.folderName);
      tvTools = view.findViewById(R.id.tvTools);
      roots = view.findViewById(R.id.roots);
      icon = view.findViewById(R.id.icon);
      getPos.setOnClickListener(
          c -> {
            click.onClick(c, getPosition());
          });
      getPos.setOnLongClickListener(
          v -> {
            click.onLongClick(v, getPosition());
            return false;
          });
    }
  }

  public interface onClick {
    public void onClick(View view, int pos);

    public void onLongClick(View view, int pos);
  }

  public void setSettingTextView(TextView tv) {
    if (tv != null) {
      tv.setEllipsize(TextUtils.TruncateAt.MARQUEE);
      tv.setMarqueeRepeatLimit(-1);
      tv.setSelected(true);
      tv.setSingleLine(true);
    }
  }

  public void addItem(int Position) {
    notifyItemInserted(Position);
  }

  public void removedItem(int pos) {
    files.remove(pos);
    notifyItemRemoved(pos);
  }

  public void makeFile(String path) {
    var filemaker = new FileMaker(context);
    filemaker.setFolderName(path);
    filemaker.setCallBack(
        new FileCallBack() {

          @Override
          public void onError(String error) {
            Toast.makeText(context.getApplicationContext(), error, 2).show();
          }

          @Override
          public void onDoneMakeFile(String toast) {
            // notifyDataSetChanged();
          }
        });
  }
}
