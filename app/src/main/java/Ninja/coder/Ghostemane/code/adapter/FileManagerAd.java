package Ninja.coder.Ghostemane.code.adapter;

import Ninja.coder.Ghostemane.code.AnimUtils;
import Ninja.coder.Ghostemane.code.ColorAndroid12;
import Ninja.coder.Ghostemane.code.FileUtil;
import Ninja.coder.Ghostemane.code.GlideUtilApi.GlideCompat;
import Ninja.coder.Ghostemane.code.MFileClass;
import Ninja.coder.Ghostemane.code.R;
import Ninja.coder.Ghostemane.code.component.FastScrollCompat.PopupTextProvider;
import Ninja.coder.Ghostemane.code.databin.FileMaker;
import Ninja.coder.Ghostemane.code.folderBuilder.FileHelper;
import Ninja.coder.Ghostemane.code.folderBuilder.FileIconHelper;
import Ninja.coder.Ghostemane.code.interfaces.FileCallBack;
import Ninja.coder.Ghostemane.code.marco.FileCounter;
import Ninja.coder.Ghostemane.code.marco.binder.BinderRecyclerview1;
import android.content.Context;
import android.content.res.ColorStateList;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.graphics.drawable.RippleDrawable;
import android.text.TextUtils;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import androidx.annotation.NonNull;
import java.io.File;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;
import androidx.recyclerview.widget.RecyclerView;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;

// fileListItem
public class FileManagerAd extends RecyclerView.Adapter<FileManagerAd.VH>
    implements PopupTextProvider {
  private List<HashMap<String, Object>> files = new ArrayList<>();
  protected Context context;
  protected onClick click;
  protected List<HashMap<String, Object>> filteredFiles;
  protected HashMap<String, Object> name = new HashMap<>();
  public static boolean Files = false;
  public static boolean Folder = false;

  public FileManagerAd(List<HashMap<String, Object>> files, Context context, onClick click) {
    this.context = context;
    this.files = files;
    this.filteredFiles = files;
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
    return filteredFiles.size();
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
    var myfile = new File(filteredFiles.get(pos).get("path").toString());
    viewHolder.folderName.setText(myfile.getName());
    FileIconHelper fileIconHelper = new FileIconHelper(myfile.toString());
    viewHolder.icon.setImageResource(fileIconHelper.getFileIcon());
    fileIconHelper.setDynamicFolderEnabled(true);
    fileIconHelper.setEnvironmentEnabled(true);
    if (FileUtil.isDirectory(filteredFiles.get(pos).get("path").toString())) {
      Folder = true;
      Files = false;
      viewHolder.icon.setPadding(9, 9, 9, 9);
      fileIconHelper.bindIcon(viewHolder.icon);
      ColorAndroid12.shapeViews(viewHolder.icon);
      FileCounter mfileC = new FileCounter(viewHolder.tvTools);
      mfileC.execute(myfile.toString());

      viewHolder.tvTools.setText("");
    } else if (FileUtil.isExistFile(filteredFiles.get(pos).get("path").toString())) {
      viewHolder.icon.setPadding(0, 0, 0, 0);
      getTime(myfile.toString(), viewHolder.tvTools);
      viewHolder.icon.setBackgroundColor(0);
      if (BinderRecyclerview1.TaskVideo(myfile.toString())) {
        GlideCompat.GlideNormal(viewHolder.icon, myfile.toString());
      } else if (BinderRecyclerview1.PhotoView(myfile.toString())) {
        GlideCompat.GlideNormal(viewHolder.icon, myfile.toString());
      }
      if (myfile.toString().endsWith(".xml")) {
        GlideCompat.LoadVector(myfile.toString(), viewHolder.icon);
      } else if (myfile.toString().endsWith(".mp3")) {
        GlideCompat.GlideLoadMp3(viewHolder.icon, myfile.toString());
      } else if (myfile.toString().endsWith(".svg")) {
        GlideCompat.LoadSvg(myfile.toString(), viewHolder.icon);
      } else if (myfile.toString().endsWith(".pdf")) {
        try {
          GlideCompat.loadImgPdf(myfile, viewHolder.icon);
        } catch (IOException err) {
          viewHolder.icon.setImageResource(R.drawable.ic_material_pdf);
        }
      } else if (myfile.toString().endsWith(".apk")) {
        GlideCompat.LoadApkFile(myfile.toString(), viewHolder.icon);
      } else if (myfile.toString().endsWith(".swb")) {
        GlideCompat.LoadSwbIcon(myfile.toString(), viewHolder.icon);
      }
    }

    viewHolder.itemView.setClickable(true);
  }

  @NonNull
  private HashMap<String, Object> getitem(int position) {
    return filteredFiles.get(position);
  }

  @Override
  public long getItemId(int position) {
    return getitem(position).hashCode();
  }

  @Override
  @NonNull
  public CharSequence getPopupText(int position) {
    HashMap<String, Object> map = getitem(position);
    return map.get("path").toString().substring(0, 1).toUpperCase();
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
            Toast.makeText(context, error, 2).show();
          }

          @Override
          public void onDoneMakeFile(String toast) {
            // notifyDataSetChanged();
          }
        });
  }

  public void search(String query) {
    if (query.length() > 0) {
      List<HashMap<String, Object>> result = new ArrayList<>();
      for (HashMap<String, Object> file : this.files) {
        if (file.get("path").toString().toLowerCase().contains(query.toLowerCase())) {
          result.add(file);
        }
      }

      this.filteredFiles = result;
      notifyDataSetChanged();
    } else {
      this.filteredFiles = this.files;
      notifyDataSetChanged();
    }
  }

  private void getTime(String path, TextView view) {
    try {

      if (view != null) {
        view.setText(
            MFileClass.convertBytes(FileUtil.getFileLength(path))
                .concat(", ".concat(MFileClass.getLastModifiedOfFile(path, "HH:mm,dd/MM/yyyy"))));
      }
    } catch (Exception err) {
      err.printStackTrace();
    }
  }

  public String getMp3Format(String txt) {
    List<String> list = Arrays.asList(FileHelper.AUDIO_FILES);
    for (var item : list) {
      return item + txt;
    }
    return txt;
  }
}
