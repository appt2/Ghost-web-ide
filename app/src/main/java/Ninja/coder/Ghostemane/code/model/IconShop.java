package Ninja.coder.Ghostemane.code.model;

import Ninja.coder.Ghostemane.code.adapter.IconManagerAdapter;
import Ninja.coder.Ghostemane.code.model.Icons;
import android.view.View;
import androidx.recyclerview.widget.GridLayoutManager;
import com.blankj.utilcode.util.ThreadUtils;
import java.util.ArrayList;
import android.content.Context;
import android.view.ViewGroup;
import androidx.recyclerview.widget.RecyclerView;
import com.google.android.material.bottomsheet.BottomSheetDialog;
import java.io.File;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.concurrent.Executors;

public class IconShop {
  protected Context context;
  protected String path = "/storage/emulated/0/GhostWebIDE/.icon";
  protected RecyclerView re;
  private IconManagerAdapter mAdapter;
  private List<Icons> Icons = new ArrayList<>();

  public IconShop(Context context) {
    this.context = context;
    initList();
  }

  private void initList() {
    re = new RecyclerView(context);
    ViewGroup.LayoutParams param =
        new ViewGroup.LayoutParams(
            ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT);
    re.setLayoutParams(param);
    var sheet = new BottomSheetDialog(context);
    mAdapter = new IconManagerAdapter();
    mAdapter.setOnIconSelectedListener(
        new IconManagerAdapter.OnIconSelectedListener() {
          @Override
          public void onIconSelect(Icons icons,View v) {}
        });
    loadIcons();
    re.setAdapter(mAdapter);
    re.setLayoutManager(new GridLayoutManager(context,4));
    sheet.setContentView(re);
    sheet.show();
  }

  private void loadIcons() {

    Executors.newSingleThreadExecutor()
        .execute(
            () -> {
              File iconDir = new File(path);
              if (iconDir.exists()) {

              } else {
                iconDir.mkdirs();
              }
              File[] files = iconDir.listFiles(File::isFile);

              if (files != null) {
                Arrays.sort(files, Comparator.comparingLong(File::lastModified));
                for (File file : files) {

                  if (file.exists()) {
                    Icons icon = new Icons(new File(file.getAbsolutePath().replaceAll("%20", " ")));
                    if (icon.getRootFile().getName().endsWith(".svg")) {
                      Icons.add(icon);
                    }
                    if (icon.getRootFile().getName().endsWith(".xml")) {
                      Icons.add(icon);
                    }
                  }
                }
              }

              ThreadUtils.runOnUiThread(
                  () -> {
                    mAdapter.submitList(Icons);
                  });
            });
  }
}
