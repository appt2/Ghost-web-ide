package Ninja.coder.Ghostemane.code;

import Ninja.coder.Ghostemane.code.GlideUtilApi.GlideCompat;
import Ninja.coder.Ghostemane.code.tasks.AsyncTaskCompat;
import android.Manifest;
import android.animation.*;
import android.app.*;
import android.content.*;
import android.content.pm.PackageManager;
import android.content.res.*;
import android.graphics.*;
import android.graphics.Typeface;
import android.graphics.drawable.*;
import android.media.*;
import android.net.*;
import android.net.Uri;
import android.os.*;
import android.text.*;
import android.text.style.*;
import android.util.*;
import android.view.*;
import android.view.View;
import android.view.View.*;
import android.view.animation.*;
import android.webkit.*;
import android.widget.*;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import androidx.activity.*;
import androidx.annotation.*;
import androidx.appcompat.app.AlertDialog;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import androidx.recyclerview.*;
import androidx.recyclerview.selection.*;
import androidx.recyclerview.widget.*;
import androidx.recyclerview.widget.RecyclerView;
import androidx.recyclerview.widget.RecyclerView.ViewHolder;
import androidx.webkit.*;
import arabware.file.*;
import com.allenliu.badgeview.*;

import com.blankj.utilcode.*;
import com.bumptech.glide.*;
import com.bumptech.glide.integration.avif.*;
import com.bumptech.glide.integration.recyclerview.*;
import com.caverock.androidsvg.*;
import com.fasterxml.jackson.core.*;
import com.github.angads25.filepicker.*;
import com.github.junrar.*;
import com.google.android.material.*;
import com.google.android.material.color.MaterialColors;
import com.google.android.material.dialog.MaterialAlertDialogBuilder;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.search.SearchBar;
import com.google.android.material.search.SearchView;
import com.google.android.material.textfield.*;
import com.skydoves.powermenu.*;
import java.io.*;
import java.text.*;
import java.util.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.regex.*;
import Ninja.coder.Ghostemane.code.comprasor.*;

@Deprecated
public class IconmodActivity extends BaseCompat {

  private FloatingActionButton _fab;
  private double dos = 0;
  private String staticString = "";
  private String output = "";
  private String path = "";

  private double intpos = 0;
  private String search = "";

  private ArrayList<String> listDir = new ArrayList<>();
  private ArrayList<HashMap<String, Object>> ListMap = new ArrayList<>();
  private ArrayList<HashMap<String, Object>> FakeListMap = new ArrayList<>();
  private LinearLayout linear3;
  private ProgressBar progressbar1;
  private RecyclerView recyclerview1;
  private TextInputLayout input_iconmode;
  private TextInputEditText et_iconmode;

  private PowerMenu ent;

  @Override
  protected void onCreate(Bundle _savedInstanceState) {
    super.onCreate(_savedInstanceState);
    setContentView(R.layout.iconmod);
    initialize();

    if (ContextCompat.checkSelfPermission(this, Manifest.permission.READ_EXTERNAL_STORAGE)
            == PackageManager.PERMISSION_DENIED
        || ContextCompat.checkSelfPermission(this, Manifest.permission.WRITE_EXTERNAL_STORAGE)
            == PackageManager.PERMISSION_DENIED) {
      ActivityCompat.requestPermissions(
          this,
          new String[] {
            Manifest.permission.READ_EXTERNAL_STORAGE, Manifest.permission.WRITE_EXTERNAL_STORAGE
          },
          1000);
    } else {
      initializeLogic();
    }
  }

  @Override
  public void onRequestPermissionsResult(
      int requestCode, String[] permissions, int[] grantResults) {
    super.onRequestPermissionsResult(requestCode, permissions, grantResults);
    if (requestCode == 1000) {
      initializeLogic();
    }
  }

  private void initialize() {
    _fab = findViewById(R.id._fab);

    linear3 = findViewById(R.id.linear3);
    input_iconmode = findViewById(R.id.input_iconmode);
    et_iconmode = findViewById(R.id.et_iconmode);
    progressbar1 = findViewById(R.id.progressbar1);
    recyclerview1 = findViewById(R.id.recyclerview1);
    input_iconmode
        .getEditText()
        .setHintTextColor(
            MaterialColors.getColor(input_iconmode.getEditText(), ColorAndroid12.TvColor));
    input_iconmode.setHint("Search icon");

    runOnUiThread(
        () -> {
          input_iconmode
              .getEditText()
              .setOnEditorActionListener(
                  (v, actionId, event) -> {
                    et_iconmode.setText(et_iconmode.getText().toString());

                    if (input_iconmode.getEditText().getText().toString().length() > 0) {
                      search = input_iconmode.getEditText().toString();
                      new Thread(
                              new Runnable() {
                                @Override
                                public void run() {
                                  try {

                                    intpos = ListMap.size() - 1;
                                    FakeListMap.clear();
                                    for (int _repeat18 = 0;
                                        _repeat18 < ListMap.size();
                                        _repeat18++) {
                                      if (ListMap.get((int) intpos).containsKey("path")) {
                                        if (ListMap.get((int) intpos)
                                            .get("path")
                                            .toString()
                                            .toLowerCase()
                                            .contains(
                                                input_iconmode
                                                    .getEditText()
                                                    .getText()
                                                    .toString()
                                                    .toLowerCase())) {
                                          {
                                            HashMap<String, Object> _item = new HashMap<>();
                                            _item.put(
                                                "path",
                                                ListMap.get((int) intpos).get("path").toString());
                                            FakeListMap.add(_item);
                                          }
                                        }
                                      }
                                      intpos--;
                                    }

                                  } catch (Exception e) {

                                  }

                                  runOnUiThread(
                                      new Runnable() {
                                        @Override
                                        public void run() {
                                          recyclerview1.setLayoutAnimation(
                                              AnimationUtils.loadLayoutAnimation(
                                                  getApplicationContext(),
                                                  R.anim.layout_animation));
                                          recyclerview1.scheduleLayoutAnimation();
                                          recyclerview1.setAdapter(
                                              new Recyclerview1Adapter(FakeListMap));
                                          recyclerview1.getAdapter().notifyDataSetChanged();
                                        }
                                      });
                                }
                              })
                          .start();
                    } else {
                      recyclerview1.setLayoutAnimation(
                          AnimationUtils.loadLayoutAnimation(
                              getApplicationContext(), R.anim.layout_animation));
                      recyclerview1.scheduleLayoutAnimation();
                      //  recyclerview1.setAdapter(new Recyclerview1Adapter(ListMap));
                      recyclerview1.getAdapter().notifyDataSetChanged();
                    }
                    return false;
                  });
        });

    _fab.setImageResource(R.drawable.arrowr);

    recyclerview1.addOnScrollListener(
        new RecyclerView.OnScrollListener() {
          @Override
          public void onScrollStateChanged(RecyclerView recyclerView, int _scrollState) {
            super.onScrollStateChanged(recyclerView, _scrollState);
          }

          @Override
          public void onScrolled(RecyclerView recyclerView, int _offsetX, int _offsetY) {
            super.onScrolled(recyclerView, _offsetX, _offsetY);
            if (_offsetX > (ListMap.size() + 1)) {
              _fab.hide();
            } else {
              _fab.show();
            }
          }
        });

    _fab.setOnClickListener(
        new View.OnClickListener() {
          @Override
          public void onClick(View _view) {
            try {
              recyclerview1.smoothScrollToPosition(recyclerview1.getAdapter().getItemCount() - 1);
            } catch (Exception exception) {
              Toast.makeText(getApplication(), exception.getMessage(), 2).show();
            }
          }
        });
  }

  private void initializeLogic() {

    progressbar1.setVisibility(View.GONE);

    ColorAndroid12.setFabColor(_fab);
    itemList();
    ColorAndroid12.PrograssBarColor(progressbar1);
  }

  public void itemList() {
    listDir.clear();
    ListMap.clear();
    path = "/storage/emulated/0/GhostWebIDE/.icon";
    FileUtil.listDir(path, listDir);
    dos = 0;
    for (int post = 0; post < (int) (listDir.size()); post++) {
      {
        HashMap<String, Object> _item = new HashMap<>();
        _item.put("path", listDir.get((int) (dos)));
        ListMap.add(_item);
      }

      dos++;
    }
    //  recyclerview1.setAdapter(new Recyclerview1Adapter(ListMap));
    recyclerview1.setLayoutManager(new GridLayoutManager(IconmodActivity.this, (int) 3));
  }

  public void _clickAnimation(final View _view) {
    ScaleAnimation fade_in =
        new ScaleAnimation(
            0.9f, 1f, 0.9f, 1f, Animation.RELATIVE_TO_SELF, 0.5f, Animation.RELATIVE_TO_SELF, 0.7f);
    fade_in.setDuration(300);
    fade_in.setFillAfter(true);
    _view.startAnimation(fade_in);
  }

  public void _msvgtopng(final String _svg, final String _pngss) {
    AlertDialog dialog =
        new GhostWebMaterialDialog(IconmodActivity.this)
            .setView(R.layout.layout_svg_size_filedir)
            .setTitle("Svg to Png")
            .setCancelable(true)
            .setPositiveButton("convert", null)
            .setNegativeButton(android.R.string.cancel, null)
            .create();
    dialog.setOnShowListener(
        (var) -> {
          Button positive = dialog.getButton(DialogInterface.BUTTON_POSITIVE);
          EditText mwidth = dialog.findViewById(R.id.mwidth);
          EditText mheight = dialog.findViewById(R.id.mheight);
          EditText lastpng = dialog.findViewById(R.id.lastpng);
          EditText lastsvg = dialog.findViewById(R.id.lastsvg);
          ImageView img = dialog.findViewById(R.id.img);
          SvgShow.GetFileSvg(_svg, img, this);
          lastsvg.setText(Uri.parse(_svg).getLastPathSegment());
          lastpng.setText(Uri.parse(_svg.replace(".svg", ".png")).getLastPathSegment());
          positive.setOnClickListener(
              (vftrororocjj) -> {
                SvgToPng png =
                    new SvgToPng(
                        this,
                        new File(_svg),
                        new File(_pngss),
                        new SvgToPng.OnConversionListener() {
                          @Override
                          public void onConversionSuccess() {}

                          @Override
                          public void onConversionError(String error) {
                            throw new RuntimeException(error);
                          }
                        });
                png.setHeight(Float.parseFloat(mwidth.getText().toString()));
                png.setWidth(Float.parseFloat(mwidth.getText().toString()));
                png.execute();

                dialog.dismiss();
              });
        });
    dialog.show();
  }

  public class Recyclerview1Adapter extends RecyclerView.Adapter<Recyclerview1Adapter.ViewHolder> {

    ArrayList<HashMap<String, Object>> _data;

    public Recyclerview1Adapter(ArrayList<HashMap<String, Object>> _arr) {
      _data = _arr;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
      LayoutInflater _inflater = getLayoutInflater();
      View _v = _inflater.inflate(R.layout.icon, null);
      RecyclerView.LayoutParams _lp =
          new RecyclerView.LayoutParams(
              ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);
      _v.setLayoutParams(_lp);
      return new ViewHolder(_v);
    }

    @Override
    public void onBindViewHolder(ViewHolder _holder, final int _position) {
      View _view = _holder.itemView;

      final LinearLayout linear2 = _view.findViewById(R.id.linear2);
      final androidx.cardview.widget.CardView cardview1 = _view.findViewById(R.id.cardview1);
      final LinearLayout linear1 = _view.findViewById(R.id.linear1);
      final TextView textview1 = _view.findViewById(R.id.textview1);
      final ImageView imageview1 = _view.findViewById(R.id.imageview1);
      final TextView textview2 = _view.findViewById(R.id.textview2);

      textview2.setText(
          Uri.parse(_data.get((int) _position).get("path").toString()).getLastPathSegment());

      GlideCompat.LoadSvg(_data.get(_position).get("path").toString(), imageview1);
      textview2.setTypeface(Typeface.createFromAsset(getAssets(), "fonts/ghostfont.ttf"), 0);
      ColorAndroid12.setTextColor(textview2);
      ColorAndroid12.setTextColor(textview1);
      textview2.setEllipsize(TextUtils.TruncateAt.MARQUEE);
      textview2.setMarqueeRepeatLimit(-1);
      textview2.setSingleLine(true);
      textview2.setSelected(true);

      try {
        if (search.isEmpty()) {

        } else {
          {
            SpannableString spannableString = new SpannableString(textview2.getText().toString());
            Matcher matcher = Pattern.compile(search).matcher(textview2.getText().toString());

            int i2 = 0;
            int i = 0;

            while (matcher.find()) {

              spannableString.setSpan(
                  new ForegroundColorSpan(
                      MaterialColors.getColor(IconmodActivity.this, ColorAndroid12.ButtonBack, 0)),
                  matcher.start(),
                  matcher.end(),
                  Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
              int i3 = i2 + 1;
              i2 = i + 1;
              spannableString.setSpan(
                  new BackgroundColorSpan(
                      i2 % 2 == 0
                          ? MaterialColors.getColor(
                              IconmodActivity.this, ColorAndroid12.TvColorFab, 0)
                          : MaterialColors.getColor(
                              IconmodActivity.this, ColorAndroid12.ColorNormal, 0)),
                  matcher.start(),
                  matcher.end(),
                  33);
              spannableString.setSpan(new StyleSpan(1), matcher.start(), matcher.end(), 33);
            }

            textview2.setText(spannableString);
          }
        }
      } catch (Exception e) {

      }
      imageview1.setOnClickListener(
          new View.OnClickListener() {
            @Override
            public void onClick(View _view) {
              AlertDialog dialog =
                  new MaterialAlertDialogBuilder(IconmodActivity.this)
                      .setTitle("Convert View")
                      .setMessage("Set Object to png xml and svg")
                      .setPositiveButton("Convert", null)
                      .setNegativeButton(android.R.string.cancel, null)
                      .create();
              dialog.setOnShowListener(
                  (var) -> {
                    Button positive = dialog.getButton(DialogInterface.BUTTON_POSITIVE);
                    positive.setOnClickListener(
                        new View.OnClickListener() {
                          @Override
                          public void onClick(View _view) {
                            ent =
                                new PowerMenu.Builder(IconmodActivity.this)
                                    .addItem(new PowerMenuItem("Xml"))
                                    .addItem(new PowerMenuItem("Svg"))
                                    .addItem(new PowerMenuItem("Png"))
                                    .build();
                            ent.setAutoDismiss(true);
                            ent.setMenuRadius((float) 20);
                            ent.setMenuColor(
                                MaterialColors.getColor(
                                    IconmodActivity.this, ColorAndroid12.Back, 0));
                            ent.setTextColor(
                                MaterialColors.getColor(
                                    IconmodActivity.this, ColorAndroid12.TvColor, 0));
                            ent.showAsDropDown(positive);
                            ent.setShowBackground(false);
                            ent.setOnMenuItemClickListener(
                                new OnMenuItemClickListener<PowerMenuItem>() {
                                  @Override
                                  public void onItemClick(int position, PowerMenuItem item) {
                                    switch ((int) position) {
                                      case ((int) 0):
                                        {
                                          output = "/sdcard/ghostweb/icon/vector/";
                                          FileUtil.makeDir(output);
                                          staticString =
                                              _data.get((int) _position).get("path").toString();
                                          vb.iconPath = staticString;
                                          vb.projectResourceDirectory = output;
                                          vb.v(IconmodActivity.this, () -> {});

                                          dialog.dismiss();
                                          break;
                                        }
                                      case ((int) 1):
                                        {
                                          if (!FileUtil.isDirectory("/sdcard/ghostweb/icon/svg")) {
                                            FileUtil.makeDir("/sdcard/ghostweb/icon/svg");
                                          }
                                          new AsyncTaskCompat<String, String, String>() {
                                            @Override
                                            protected void onPreExecute() {
                                              progressbar1.setVisibility(View.VISIBLE);
                                            }

                                            @Override
                                            protected String doInBackground(String... params) {
                                              String _param = params[0];
                                              runOnUiThread(
                                                  new Runnable() {
                                                    @Override
                                                    public void run() {
                                                      FileUtil.copyFile(
                                                          _data
                                                              .get((int) _position)
                                                              .get("path")
                                                              .toString(),
                                                          "sdcard/ghostweb/icon/svg/"
                                                              .concat(
                                                                  Uri.parse(
                                                                          _data
                                                                              .get((int) _position)
                                                                              .get("path")
                                                                              .toString())
                                                                      .getLastPathSegment()));
                                                    }
                                                  });
                                              return "";
                                            }

                                            @Override
                                            protected void onPostExecute(String _result) {
                                              progressbar1.setVisibility(View.GONE);
                                              SketchwareUtil.showMessage(
                                                  getApplicationContext(),
                                                  "saved : "
                                                      .concat(
                                                          _data
                                                              .get((int) _position)
                                                              .get("path")
                                                              .toString()));
                                            }
                                          }.execute("");
                                          dialog.dismiss();
                                          break;
                                        }
                                      case ((int) 2):
                                        {
                                          if (!FileUtil.isDirectory("/sdcard/ghostweb/icon/png")) {
                                            FileUtil.makeDir("/sdcard/ghostweb/icon/png");
                                          }
                                          _msvgtopng(
                                              _data.get(_position).get("path").toString(),
                                              "sdcard/ghostweb/icon/svg/"
                                                  .concat(
                                                      Uri.parse(
                                                              _data
                                                                  .get(_position)
                                                                  .get("path")
                                                                  .toString())
                                                          .getLastPathSegment()));
                                          dialog.dismiss();
                                          break;
                                        }
                                    }
                                  }
                                });
                          }
                        });
                  });
              dialog.show();
            }
          });
      _clickAnimation(linear2);
      cardview1.setCardBackgroundColor(Color.TRANSPARENT);
      cardview1.setRadius((float) 10);
      cardview1.setCardElevation((float) 0);
      cardview1.setUseCompatPadding(true);
      ColorAndroid12.setColorFilter(imageview1);
    }

    @Override
    public int getItemCount() {
      return _data.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
      public ViewHolder(View v) {
        super(v);
      }
    }
  }

  public void showMessage(String a) {
    Toast.makeText(getApplicationContext(), a, 2).show();
  }
}
