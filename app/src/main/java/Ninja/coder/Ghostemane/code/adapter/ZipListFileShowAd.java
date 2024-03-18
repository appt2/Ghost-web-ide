package Ninja.coder.Ghostemane.code.adapter;

import Ninja.coder.Ghostemane.code.R;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import androidx.recyclerview.widget.RecyclerView;
import net.lingala.zip4j.model.FileHeader;

import java.io.File;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

public class ZipListFileShowAd extends RecyclerView.Adapter<ZipListFileShowAd.VH> {

    protected List<FileHeader> listModel;

    public ZipListFileShowAd(List<FileHeader> listModel) {
        this.listModel = listModel;
    }

    @Override
    public int getItemCount() {
        return listModel.size();
    }

    @Override
    public void onBindViewHolder(VH viewHolder, int pos) {
        View view = viewHolder.itemView;
        RecyclerView.LayoutParams layoutParams =
                new RecyclerView.LayoutParams(
                        ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        view.setLayoutParams(layoutParams);
        FileHeader fileHeader = listModel.get(pos);
        String filePath = fileHeader.getFileName();
        File file = new File(filePath);
        String displayName = file.getName();

        if (fileHeader.isDirectory()) {
            viewHolder.tvTools.setVisibility(View.GONE);
            viewHolder.icon.setImageResource(R.drawable.folder);
        } else {
            if (fileHeader.getFileName().endsWith(".jpg")) {
//        Glide.with(context).load(fileHeader.getFileName()).into(viewHolder.icon);
            } else {
                viewHolder.tvTools.setText(
                        "Size: "
                                + fileHeader.getFileCommentLength()
                                + " | Time: "
                                + fileHeader.getLastModifiedTimeEpoch());
            }
            viewHolder.icon.setImageResource(R.drawable.file);
        }

        viewHolder.folderName.setText(displayName);

        view.setOnLongClickListener(
                v -> {
                    if (fileHeader.isDirectory()) {
                        File parentFile = file.getParentFile();
                        if (parentFile != null) {
                            // do something with the parentFile.getAbsolutePath()
                            notifyDataSetChanged();
                        }
                    }
                    return false;
                });
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

    public void sortListByName() {
        Collections.sort(
                listModel,
                new Comparator<FileHeader>() {
                    @Override
                    public int compare(FileHeader file1, FileHeader file2) {
                        return file1.getFileName().compareTo(file2.getFileName());
                    }
                });
        notifyDataSetChanged();
    }

    public class VH extends RecyclerView.ViewHolder {
        protected TextView folderName, tvTools;
        protected LinearLayout roots;
        protected ImageView icon;

        public VH(View view) {
            super(view);
            folderName = view.findViewById(R.id.folderName);
            tvTools = view.findViewById(R.id.tvTools);
            roots = view.findViewById(R.id.roots);
            icon = view.findViewById(R.id.icon);
        }
    }
}
