package Ninja.coder.Ghostemane.code.model;

import Ninja.coder.Ghostemane.code.R;
import Ninja.coder.Ghostemane.code.adapter.FileManagerAd;
import Ninja.coder.Ghostemane.code.model.SearchFileData;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;
import com.blankj.utilcode.util.ThreadUtils;
import com.google.android.material.dialog.MaterialAlertDialogBuilder;
import com.google.android.material.textfield.TextInputEditText;
import com.google.android.material.textfield.TextInputLayout;
import java.util.ArrayList;
import java.util.HashMap;

public class SearchFileData {
  // R.layout.reminderlist
  protected AppCompatActivity compat;
  protected CallBack call;
  protected TextInputEditText et;
  protected TextInputLayout layout;
  private double d;
  protected RecyclerView recyclerview;
  protected ArrayList<HashMap<String, Object>> map;

  public SearchFileData(
      AppCompatActivity compat,
      CallBack call,
      ArrayList<HashMap<String, Object>> map,
      RecyclerView recyclerview) {
    this.compat = compat;
    this.call = call;
    this.map = map;
    this.recyclerview = recyclerview;
  }

  public void filterFile() {
    var view = LayoutInflater.from(compat).inflate(R.layout.reminderlist, null, false);
    var dialog = new MaterialAlertDialogBuilder(compat);
    dialog.setTitle("Filter List");
    dialog.setView(view);
    if (dialog != null) {
      dialog.show();
    }

    et = view.findViewById(R.id.ed_filter);
    layout = view.findViewById(R.id.input);
    layout.setEndIconVisible(true);
    layout.setEndIconMinSize(10);
    layout.setEndIconScaleType(ImageView.ScaleType.CENTER_INSIDE);
    layout.setEndIconDrawable(R.drawable.deletear);
    layout.setEndIconOnClickListener(v -> et.getText().clear());

    et.addTextChangedListener(
        new TextWatcher() {

          @Override
          public void onTextChanged(CharSequence ser, int arg1, int arg2, int arg3) {

            final double length = map.size();

            d = map.size() - 1;
            for (int i = 0; i < (int) (length); i++) {
              final String serching = map.get((int) d).get("path").toString();
              if (serching.toLowerCase().contains(et.getText().toString().toLowerCase())) {

              } else {
                map.remove((int) (d));
               recyclerview.getAdapter().notifyDataSetChanged();
              }
              d--;
            }

            ThreadUtils.runOnUiThread(
                () -> {
                  FileManagerAd ads =
                      new FileManagerAd(
                          map,
                          compat,
                          new FileManagerAd.onClick() {

                            @Override
                            public void onLongClick(View view, int pos) {}

                            @Override
                            public void onClick(View view, int pos) {}
                          });
                  recyclerview.setAdapter(ads);
                  recyclerview.getAdapter().notifyDataSetChanged();
                });
          }

          @Override
          public void beforeTextChanged(CharSequence arg0, int arg1, int arg2, int arg3) {}

          @Override
          public void afterTextChanged(Editable arg0) {}
        });
  }

  public interface CallBack {
    public void Reast();
  }
}
