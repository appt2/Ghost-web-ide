package Ninja.coder.Ghostemane.code;

import Ninja.coder.Ghostemane.code.layoutmanager.DialogColorView;
import android.animation.*;
import android.app.*;
import android.content.*;
import android.content.Intent;
import android.content.res.*;
import android.graphics.*;
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
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import androidx.activity.*;
import androidx.annotation.*;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.DialogFragment;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.recyclerview.*;
import androidx.recyclerview.selection.*;
import androidx.recyclerview.widget.*;
import androidx.recyclerview.widget.RecyclerView;
import androidx.recyclerview.widget.RecyclerView.Adapter;
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
import com.hzy.lib7z.*;
import com.jsibbold.zoomage.*;
import com.lxj.xpopup.*;
import com.mukesh.*;
import com.neo.highlight.*;
import com.skydoves.powermenu.*;
import com.tapadoo.alerter.*;
import com.zip4j.*;
import fi.iki.elonen.*;
import java.io.*;
import java.text.*;
import java.util.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Timer;
import java.util.TimerTask;
import java.util.regex.*;
import org.benf.cfr.reader.*;
import org.beyka.tiffbitmapfactory.*;
import org.json.*;
import storage.sdcard.*;
import xyz.doikki.videoplayer.*;
import xyz.doikki.videoplayer.exo.*;
import xyz.doikki.videoplayer.ijk.*;
import com.google.android.material.shape.*;

public class StreamsoftappActivity extends BaseCompat {

  private Timer _timer = new Timer();

  protected SdCardUtil sd_stor;

  private ArrayList<HashMap<String, Object>> form = new ArrayList<>();
  private ArrayList<HashMap<String, Object>> apptools = new ArrayList<>();

  private LinearLayout linear1;
  private LinearLayout linear2;
  private LinearLayout linear4;
  private LinearLayout mainLayout;
  private ImageView imageview1;
  private LinearLayout linear3;
  private ImageView imageview2;
  private ImageView imageview3;
  private TextView textview1;
  private LinearLayout linear6;
  private LinearLayout layoutDir;
  private TextView textview4;
  private RecyclerView recyclerview2;
  private LinearLayout linear7;
  private LinearLayout lyhome;
  private LinearLayout getSd;
  private TextView textview5;
  private RecyclerView recyclerview4;
  private TextView textview3;
  private ImageView imageview5;
  private TextView textview2;
  private ImageView imageview4;

  private Intent sd = new Intent();
  private PowerMenu menu;
  private Intent myket = new Intent();
  private Intent other = new Intent();
  private Intent sum = new Intent();
  private TimerTask timer;

  @Override
  protected void onCreate(Bundle _savedInstanceState) {
    super.onCreate(_savedInstanceState);
    setContentView(R.layout.streamsoftapp);
    initialize(_savedInstanceState);
    initializeLogic();
  }

  private void initialize(Bundle _savedInstanceState) {
    linear1 = findViewById(R.id.linear1);
    linear2 = findViewById(R.id.linear2);
    linear4 = findViewById(R.id.linear4);
    mainLayout = findViewById(R.id.mainLayout);
    imageview1 = findViewById(R.id.imageview1);
    linear3 = findViewById(R.id.linear3);
    imageview2 = findViewById(R.id.imageview2);
    imageview3 = findViewById(R.id.imageview3);
    textview1 = findViewById(R.id.textview1);
    linear6 = findViewById(R.id.linear6);
    layoutDir = findViewById(R.id.layoutDir);
    textview4 = findViewById(R.id.textview4);
    recyclerview2 = findViewById(R.id.recyclerview2);
    linear7 = findViewById(R.id.linear7);
    lyhome = findViewById(R.id.lyhome);
    getSd = findViewById(R.id.getSd);
    textview5 = findViewById(R.id.textview5);
    recyclerview4 = findViewById(R.id.recyclerview4);
    textview3 = findViewById(R.id.textview3);
    imageview5 = findViewById(R.id.imageview5);
    textview2 = findViewById(R.id.textview2);
    imageview4 = findViewById(R.id.imageview4);

    imageview1.setOnClickListener(
        new View.OnClickListener() {
          @Override
          public void onClick(View _view) {
            myket.setClass(getApplicationContext(), SettingappActivity.class);
            startActivity(myket);
          }
        });

    imageview2.setOnClickListener(
        new View.OnClickListener() {
          @Override
          public void onClick(View _view) {
            menu =
                new PowerMenu.Builder(StreamsoftappActivity.this)
                    .addItem(new PowerMenuItem("Add Star for App"))
                    .addItem(new PowerMenuItem("exit"))
                    .build(); //   my telegram channel sketchware95
            menu.setAnimation(MenuAnimation.ELASTIC_CENTER); //   my telegram channel sketchware95
            menu.setAutoDismiss(true);
            menu.setShowBackground(false);
            menu.setTextColor(0xFFFFB59C); //   my telegram channel sketchware95
            menu.setTextTypeface(
                Typeface.create(
                    Typeface.createFromAsset(getAssets(), "fonts/ghostfont.ttf"),
                    Typeface.NORMAL)); // my telegram channel sketchware95
            menu.showAsDropDown(imageview2);
            menu.setMenuRadius((float) 15);
            menu.setDividerHeight((int) 2);
            menu.setMenuColor(0xFF2B2120);
            menu.setDivider(new ColorDrawable(0xFFFDB69A));
            menu.setSelectedMenuColor(0xFFFDB69A);
            menu.setOnMenuItemClickListener(
                new OnMenuItemClickListener<PowerMenuItem>() {
                  @Override
                  public void onItemClick(int position, PowerMenuItem item) {
                    switch ((int) position) {
                      case ((int) 0):
                        {
                          myket.setAction(Intent.ACTION_VIEW);
                          myket.setData(
                              Uri.parse("myket://comment?id=Ninja.coder.Ghostemane.code"));
                          startActivity(myket);
                          break;
                        }
                      case ((int) 1):
                        {
                          finishAffinity();
                          break;
                        }
                    }
                  }
                }); //   my telegram channel sketchware95
          }
        });

    lyhome.setOnClickListener(
        new View.OnClickListener() {
          @Override
          public void onClick(View _view) {
            sd.setClass(getApplicationContext(), FiledirActivity.class);
            sd.putExtra("home", FileUtil.getExternalStorageDir());
            lyhome.setTransitionName("home");

            android.app.ActivityOptions optionsCompat =
                android.app.ActivityOptions.makeSceneTransitionAnimation(
                    StreamsoftappActivity.this, lyhome, "home");
            startActivity(sd, optionsCompat.toBundle());
          }
        });

    getSd.setOnClickListener(
        new View.OnClickListener() {
          @Override
          public void onClick(View _view) {
            sd.setClass(getApplicationContext(), FiledirActivity.class);
            sd.putExtra("sd", sd_stor.getExtSdPath());
            getSd.setTransitionName("sd");

            android.app.ActivityOptions optionsCompat =
                android.app.ActivityOptions.makeSceneTransitionAnimation(
                    StreamsoftappActivity.this, getSd, "sd");
            startActivity(sd, optionsCompat.toBundle());
          }
        });
  }

  private void initializeLogic() {

    sd_stor = new SdCardUtil(this);
    if (sd_stor.hasRealRemovableSdCarde()) {
      getSd.setVisibility(View.VISIBLE);
    } else {
      getSd.setVisibility(View.GONE);
    }
    for (int posintaishon = 0; posintaishon < (int) (4); posintaishon++) {
      {
        HashMap<String, Object> _item = new HashMap<>();
        _item.put("add", "");
        form.add(_item);
      }
    }
    recyclerview2.setAdapter(new Recyclerview2Adapter(form));
    recyclerview2.setLayoutManager(new LinearLayoutManager(this));
    recyclerview2.setLayoutManager(
        new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false));

    _AllSaveFile(linear1);
    for (int posintaishon = 0; posintaishon < (int) (5); posintaishon++) {
      {
        HashMap<String, Object> _item = new HashMap<>();
        _item.put("add", "");
        apptools.add(_item);
      }
    }
    recyclerview4.setAdapter(new Recyclerview4Adapter(form));
    recyclerview4.setLayoutManager(new LinearLayoutManager(this));
    recyclerview4.setLayoutManager(
        new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false));
    _AnimatoeUtils(imageview1);
    _AnimatoeUtils(imageview4);
    _AnimatoeUtils(imageview5);
    _AnimatoeUtils(imageview2);
    ColorAndroid12.setTextColor(textview1);
    ColorAndroid12.setTextColor(textview4);
    ColorAndroid12.setTextColor(textview5);
    ColorAndroid12.setTextColor(textview3);
    ColorAndroid12.setTextColor(textview2);
    ColorAndroid12.setColorFilter(imageview1);
    ColorAndroid12.setColorFilter(imageview2);
    ColorAndroid12.setColorFilter(imageview5);
    ColorAndroid12.setColorFilter(imageview4);
  }

  @Override
  public void onBackPressed() {
    new GhostWebMaterialDialog(StreamsoftappActivity.this)
        .setTitle("Exit App?")
        .setMessage("config to Exit App?")
        .setIcon(R.drawable.exit)
        .setCancelable(false)
        .setNeutralButton(
            "exit",
            (p, d) -> {
              SketchwareUtil.showMessage(getApplicationContext(), "Save Data Factory!");
              timer =
                  new TimerTask() {
                    @Override
                    public void run() {
                      runOnUiThread(
                          new Runnable() {
                            @Override
                            public void run() {
                              finishAffinity();
                            }
                          });
                    }
                  };
              _timer.schedule(timer, (int) (1000));
            })
        .setPositiveButton("dismiss", (p1, d2) -> {})
        .show();
  }

  public void _clickAnimation(final View _view) {
    ScaleAnimation fade_in =
        new ScaleAnimation(
            0.9f, 1f, 0.9f, 1f, Animation.RELATIVE_TO_SELF, 0.5f, Animation.RELATIVE_TO_SELF, 0.7f);
    fade_in.setDuration(300);
    fade_in.setFillAfter(true);
    _view.startAnimation(fade_in);
  }

  public void _AllSaveFile(final View _v) {
    try {
      if ((_v instanceof ViewGroup)) {
        ViewGroup viewGroup = (ViewGroup) _v;
        for (int i = 0; i < viewGroup.getChildCount(); i++) {
          View child = viewGroup.getChildAt(i);
          _AllSaveFile(child);
        }
      } else {
        if ((_v instanceof LinearLayout)) {

          ScaleAnimation fade_in =
              new ScaleAnimation(
                  0.9f,
                  1f,
                  0.9f,
                  1f,
                  Animation.RELATIVE_TO_SELF,
                  0.5f,
                  Animation.RELATIVE_TO_SELF,
                  0.7f);
          fade_in.setDuration(300);
          fade_in.setFillAfter(true);
          ((LinearLayout) _v).startAnimation(fade_in);
        } else if (_v instanceof ImageView) {
          ScaleAnimation fade_in =
              new ScaleAnimation(
                  0.9f,
                  1f,
                  0.9f,
                  1f,
                  Animation.RELATIVE_TO_SELF,
                  0.5f,
                  Animation.RELATIVE_TO_SELF,
                  0.7f);
          fade_in.setDuration(300);
          fade_in.setFillAfter(true);
          ((ImageView) _v).startAnimation(fade_in);
        } else if (_v instanceof TextView) {
          ScaleAnimation fade_in =
              new ScaleAnimation(
                  0.9f,
                  1f,
                  0.9f,
                  1f,
                  Animation.RELATIVE_TO_SELF,
                  0.5f,
                  Animation.RELATIVE_TO_SELF,
                  0.7f);
          fade_in.setDuration(300);
          fade_in.setFillAfter(true);
          ((TextView) _v).startAnimation(fade_in);
        } else if (_v instanceof Button) {

          ScaleAnimation fade_in =
              new ScaleAnimation(
                  0.9f,
                  1f,
                  0.9f,
                  1f,
                  Animation.RELATIVE_TO_SELF,
                  0.5f,
                  Animation.RELATIVE_TO_SELF,
                  0.7f);
          fade_in.setDuration(300);
          fade_in.setFillAfter(true);
          ((Button) _v).startAnimation(fade_in);
        }
      }
    } catch (Exception e) {

    }
  }

  public void _AnimatoeUtils(final View _v) {
    AnimUtils.ClickAnimation(_v);
  }

  public class Recyclerview2Adapter extends RecyclerView.Adapter<Recyclerview2Adapter.ViewHolder> {

    ArrayList<HashMap<String, Object>> _data;

    public Recyclerview2Adapter(ArrayList<HashMap<String, Object>> _arr) {
      _data = _arr;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
      LayoutInflater _inflater = getLayoutInflater();
      View _v = _inflater.inflate(R.layout.stream_layout_grid, null);
      //RecyclerView.LayoutParams _lp =
         // new RecyclerView.LayoutParams(
         //     ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);
     // _v.setLayoutParams(_lp);
      return new ViewHolder(_v);
    }

    @Override
    public void onBindViewHolder(ViewHolder _holder, final int _position) {
      View _view = _holder.itemView;

      final LinearLayout linear1 = _view.findViewById(R.id.linear1);
      final LinearLayout linear2 = _view.findViewById(R.id.linear2);
      final ImageView imageview1 = _view.findViewById(R.id.imageview1);
      final TextView textview1 = _view.findViewById(R.id.textview1);

      RecyclerView.LayoutParams _lp =
          new RecyclerView.LayoutParams(
              ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT);
      _view.setLayoutParams(_lp);
      ColorAndroid12.shp(_view);
      _view.setOnClickListener(
          new View.OnClickListener() {
            @Override
            public void onClick(View _view) {
              _view.setOnTouchListener(
                  new View.OnTouchListener() {
                    @Override
                    public boolean onTouch(View v, MotionEvent event) {
                      switch (event.getAction()) {
                        case MotionEvent.ACTION_DOWN:
                          {
                            ObjectAnimator scaleX = new ObjectAnimator();
                            scaleX.setTarget(_view);
                            scaleX.setPropertyName("scaleX");
                            scaleX.setFloatValues(0.9f);
                            scaleX.setDuration((int) 5);
                            scaleX.start();

                            ObjectAnimator scaleY = new ObjectAnimator();
                            scaleY.setTarget(_view);
                            scaleY.setPropertyName("scaleY");
                            scaleY.setFloatValues(0.9f);
                            scaleY.setDuration((int) 5);
                            scaleY.start();
                            break;
                          }
                        case MotionEvent.ACTION_UP:
                          {
                            ObjectAnimator scaleX = new ObjectAnimator();
                            scaleX.setTarget(_view);
                            scaleX.setPropertyName("scaleX");
                            scaleX.setFloatValues((float) 1);
                            scaleX.setDuration((int) 5);
                            scaleX.start();

                            ObjectAnimator scaleY = new ObjectAnimator();
                            scaleY.setTarget(_view);
                            scaleY.setPropertyName("scaleY");
                            scaleY.setFloatValues((float) 1);
                            scaleY.setDuration((int) 5);
                            scaleY.start();

                            break;
                          }
                      }
                      return false;
                    }
                  });
            }
          });
      if (_position == 0) {
        textview1.setText("Root Folder");
        imageview1.setImageResource(R.drawable.folders_0_3);
        linear2.setOnClickListener(
            new View.OnClickListener() {
              @Override
              public void onClick(View _view) {
                sum.setClass(getApplicationContext(), FiledirActivity.class);
                sum.putExtra("root", FileUtil.getPackageDataDir(getApplicationContext()));
                startActivity(sum);
              }
            });
      }
      if (_position == 1) {
        textview1.setText("Download Folder");
        imageview1.setImageResource(R.drawable.folders_0_6);
        linear2.setOnClickListener(
            new View.OnClickListener() {
              @Override
              public void onClick(View _view) {
                sum.setClass(getApplicationContext(), FiledirActivity.class);
                sum.putExtra("download", FileUtil.getPublicDir(Environment.DIRECTORY_DOWNLOADS));
                startActivity(sum);
              }
            });
      }
      if (_position == 2) {
        textview1.setText("Music Folder");
        imageview1.setImageResource(R.drawable.folders_0_4);
        linear2.setOnClickListener(
            new View.OnClickListener() {
              @Override
              public void onClick(View _view) {
                sum.setClass(getApplicationContext(), FiledirActivity.class);
                sum.putExtra("music", FileUtil.getPublicDir(Environment.DIRECTORY_MUSIC));
                startActivity(sum);
              }
            });
      }
      if (_position == 3) {
        textview1.setText("Theme Folder");
        imageview1.setImageResource(R.drawable.folders_0_2);
        linear2.setOnClickListener(
            new View.OnClickListener() {
              @Override
              public void onClick(View _view) {
                sum.setClass(getApplicationContext(), FiledirActivity.class);
                sum.putExtra("themes", "/storage/emulated/0/GhostWebIDE/theme");
                startActivity(sum);
              }
            });
      }

      // getSd.setBackground(shapeDrawable);
      textview1.setEllipsize(TextUtils.TruncateAt.MARQUEE);
      textview1.setMarqueeRepeatLimit(-1);
      textview1.setSingleLine(true);
      textview1.setSelected(true);
      ColorAndroid12.setTextColor(textview1);
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

  public class Recyclerview4Adapter extends RecyclerView.Adapter<Recyclerview4Adapter.ViewHolder> {

    ArrayList<HashMap<String, Object>> _data;

    public Recyclerview4Adapter(ArrayList<HashMap<String, Object>> _arr) {
      _data = _arr;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
      LayoutInflater _inflater = getLayoutInflater();
      View _v = _inflater.inflate(R.layout.layout_sream_grid_apptools, null);
      RecyclerView.LayoutParams _lp =
          new RecyclerView.LayoutParams(
              ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);
      _v.setLayoutParams(_lp);
      return new ViewHolder(_v);
    }

    @Override
    public void onBindViewHolder(ViewHolder _holder, final int _position) {
      View _view = _holder.itemView;

      final LinearLayout linear1 = _view.findViewById(R.id.linear1);
      final LinearLayout linear2 = _view.findViewById(R.id.linear2);
      final ImageView imageview1 = _view.findViewById(R.id.imageview1);
      final TextView textview1 = _view.findViewById(R.id.textview1);

      RecyclerView.LayoutParams _lp =
          new RecyclerView.LayoutParams(
              ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT);
      _view.setLayoutParams(_lp);
      ColorAndroid12.shp(_view);
      // getSd.setBackground(shapeDrawable);
      textview1.setEllipsize(TextUtils.TruncateAt.MARQUEE);
      textview1.setMarqueeRepeatLimit(-1);
      textview1.setSingleLine(true);
      textview1.setSelected(true);
      if (_position == 0) {
        textview1.setText("Theme Editor");
        imageview1.setImageResource(R.drawable.color);
        linear2.setOnClickListener(
            new View.OnClickListener() {
              @Override
              public void onClick(View _view) {
                sum.setClass(getApplicationContext(), AnjctorcolorActivity.class);
                startActivity(sum);
              }
            });
      }
      if (_position == 1) {
        textview1.setText("Java Codes");
        imageview1.setImageResource(R.drawable.javanull);
        linear2.setOnClickListener(
            new View.OnClickListener() {
              @Override
              public void onClick(View _view) {
                sum.setClass(getApplicationContext(), JavamanagerlayoutActivity.class);
                startActivity(sum);
              }
            });
      }
      if (_position == 2) {
        textview1.setText("Custom Color");
        imageview1.setImageResource(R.drawable.focused);
        linear2.setOnClickListener(
            new View.OnClickListener() {
              @Override
              public void onClick(View _view) {
                 DialogColorView.run(StreamsoftappActivity.this);
              }
            });
      }
      if (_position == 3) {
        textview1.setText("Leran Code");
        imageview1.setImageResource(R.drawable.school);
        linear2.setOnClickListener(
            new View.OnClickListener() {
              @Override
              public void onClick(View _view) {
                sum.setClass(getApplicationContext(), LeranActivity.class);
                startActivity(sum);
              }
            });
      }
      if (_position == 3) {
        textview1.setText("Terminal ");
        imageview1.setImageResource(R.drawable.console);
        linear2.setOnClickListener(
            new View.OnClickListener() {
              @Override
              public void onClick(View _view) {
                sum.setClass(getApplicationContext(), TerminalActivity.class);
                startActivity(sum);
              }
            });
      }
      if (_position == 4) {
        textview1.setText("LogCat");
        imageview1.setImageResource(R.drawable.log);
        linear2.setOnClickListener(
            new View.OnClickListener() {
              @Override
              public void onClick(View _view) {
                sum.setClass(getApplicationContext(), LogcatviewActivity.class);
                startActivity(sum);
              }
            });
      }
      _view.setOnClickListener(
          new View.OnClickListener() {
            @Override
            public void onClick(View _view) {
              _view.setOnTouchListener(
                  new View.OnTouchListener() {
                    @Override
                    public boolean onTouch(View v, MotionEvent event) {
                      switch (event.getAction()) {
                        case MotionEvent.ACTION_DOWN:
                          {
                            ObjectAnimator scaleX = new ObjectAnimator();
                            scaleX.setTarget(_view);
                            scaleX.setPropertyName("scaleX");
                            scaleX.setFloatValues(0.9f);
                            scaleX.setDuration((int) 5);
                            scaleX.start();

                            ObjectAnimator scaleY = new ObjectAnimator();
                            scaleY.setTarget(_view);
                            scaleY.setPropertyName("scaleY");
                            scaleY.setFloatValues(0.9f);
                            scaleY.setDuration((int) 5);
                            scaleY.start();
                            break;
                          }
                        case MotionEvent.ACTION_UP:
                          {
                            ObjectAnimator scaleX = new ObjectAnimator();
                            scaleX.setTarget(_view);
                            scaleX.setPropertyName("scaleX");
                            scaleX.setFloatValues((float) 1);
                            scaleX.setDuration((int) 5);
                            scaleX.start();

                            ObjectAnimator scaleY = new ObjectAnimator();
                            scaleY.setTarget(_view);
                            scaleY.setPropertyName("scaleY");
                            scaleY.setFloatValues((float) 1);
                            scaleY.setDuration((int) 5);
                            scaleY.start();

                            break;
                          }
                      }
                      return false;
                    }
                  });
            }
          });
      ColorAndroid12.setTextColor(textview1);
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

  @Deprecated
  public void showMessage(String _s) {
    Toast.makeText(getApplicationContext(), _s, Toast.LENGTH_SHORT).show();
  }
}
