package Ninja.coder.Ghostemane.code;

import android.Manifest;
import com.flask.colorpicker.ColorPickerView;
import android.view.MotionEvent;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.core.content.ContextCompat;
import android.content.pm.PackageManager;
import androidx.core.app.ActivityCompat;
import android.app.Activity;
import android.content.Context;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.media.AudioManager;
import android.view.inputmethod.EditorInfo;
import android.util.TypedValue;
import androidx.core.content.ContextCompat;
import android.content.pm.PackageManager;
import androidx.core.app.ActivityCompat;
import android.app.Activity;
import android.animation.*;
import android.app.*;
import android.content.*;
import android.content.res.*;
import android.graphics.*;
import android.graphics.drawable.*;
import android.media.*;
import android.net.*;
import android.os.*;
import android.text.*;
import android.text.style.*;
import android.util.*;
import android.view.*;
import android.view.View.*;
import android.view.animation.*;
import android.webkit.*;
import android.widget.*;
import androidx.annotation.*;
import android.content.Context;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.media.AudioManager;
import android.view.inputmethod.EditorInfo;
import android.util.TypedValue;
import io.github.rosemoe.sora.data.Span;
import io.github.rosemoe.sora.text.TextStyle;
import io.github.rosemoe.sora.widget.CodeEditor;
import Ninja.coder.Ghostemane.code.project.ProjectManager;
import io.github.rosemoe.sora.langs.html.HTMLLanguage;
import io.github.rosemoe.sora.widget.EditorAutoCompleteWindow;
import android.content.SharedPreferences;
import android.app.Activity;
import java.util.regex.Pattern;
import java.util.regex.Matcher;
import android.graphics.Color;
import Ninja.coder.Ghostemane.code.tasks.app.*;
import Ninja.coder.Ghostemane.code.LayoutBlur.BlurImage;
import android.graphics.drawable.GradientDrawable;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import android.content.res.ColorStateList;
import android.view.Window;
import io.github.rosemoe.sora.widget.EditorColorScheme;
import android.graphics.Typeface;
import java.io.File;
import com.flask.colorpicker.builder.ColorPickerDialogBuilder;
import com.flask.colorpicker.OnColorSelectedListener;
import com.flask.colorpicker.builder.ColorPickerClickListener;
import androidx.activity.EdgeToEdge;
import io.github.rosemoe.sora.text.Content;
import io.github.rosemoe.sora.text.Cursor;
import com.skydoves.powermenu.PowerMenuItem;
import com.skydoves.powermenu.MenuAnimation;
import android.graphics.drawable.ColorDrawable;
import com.skydoves.powermenu.OnMenuItemClickListener;
import android.view.animation.ScaleAnimation;
import androidx.recyclerview.widget.LinearLayoutManager;
import android.widget.EditText;
import android.text.TextWatcher;
import android.widget.Button;
import android.view.LayoutInflater;
import Ninja.coder.Ghostemane.code.marco.HtmlTagView;
import java.util.Arrays;
import Ninja.coder.Ghostemane.code.marco.NinjaMacroFileUtil;
import android.net.Uri;
import android.widget.Toast;
import android.text.SpannableString;
import android.text.style.ForegroundColorSpan;
import android.text.Spanned;
import android.text.style.StyleSpan;
import android.widget.PopupWindow;
import java.util.Collections;
import java.util.Comparator;
import android.graphics.drawable.BitmapDrawable;
import io.github.rosemoe.sora.widget.CodeEditor;
import Ninja.coder.Ghostemane.code.project.ProjectManager;
import io.github.rosemoe.sora.langs.html.HTMLLanguage;
import java.util.regex.Pattern;
import java.util.regex.Matcher;
import android.graphics.Color;
import Ninja.coder.Ghostemane.code.LayoutBlur.BlurImage;
import android.graphics.drawable.GradientDrawable;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import android.content.res.ColorStateList;
import android.view.Window;
import io.github.rosemoe.sora.widget.EditorColorScheme;
import android.graphics.Typeface;
import java.io.File;
import androidx.activity.EdgeToEdge;
import io.github.rosemoe.sora.text.Content;
import io.github.rosemoe.sora.text.Cursor;
import com.skydoves.powermenu.PowerMenuItem;
import com.skydoves.powermenu.MenuAnimation;
import android.graphics.drawable.ColorDrawable;
import com.skydoves.powermenu.OnMenuItemClickListener;
import android.view.animation.ScaleAnimation;
import androidx.recyclerview.widget.LinearLayoutManager;
import android.widget.EditText;
import android.text.TextWatcher;
import android.widget.Button;
import android.view.LayoutInflater;
import Ninja.coder.Ghostemane.code.marco.HtmlTagView;
import java.util.Arrays;
import Ninja.coder.Ghostemane.code.marco.NinjaMacroFileUtil;
import android.net.Uri;
import android.widget.Toast;
import com.google.android.material.color.MaterialColors;
import android.text.SpannableString;
import android.text.style.ForegroundColorSpan;
import android.text.Spanned;
import android.text.style.StyleSpan;
import android.widget.PopupWindow;
import java.util.Collections;
import java.util.Comparator;
import android.graphics.drawable.BitmapDrawable;
import java.util.Timer;
import Ninja.coder.Ghostemane.code.project.JavaCompilerBeta;
import io.github.rosemoe.sora.widget.EditorAutoCompleteWindow;
import Ninja.coder.Ghostemane.code.marco.WallpaperParallaxEffect;
import androidx.appcompat.widget.Toolbar;
import com.google.android.material.appbar.AppBarLayout;
import androidx.coordinatorlayout.widget.CoordinatorLayout;
import java.util.HashMap;
import java.util.ArrayList;
import android.widget.LinearLayout;
import android.widget.FrameLayout;
import android.widget.ProgressBar;
import android.widget.TextView;
import androidx.recyclerview.widget.RecyclerView;
import com.allenliu.badgeview.BadgeView;
import android.widget.ImageView;
import android.content.Intent;
import android.content.SharedPreferences;
import java.util.TimerTask;
import android.os.Vibrator;
import android.animation.ObjectAnimator;
import com.skydoves.powermenu.PowerMenu;
import android.media.SoundPool;
import android.os.Bundle;
import android.view.View;
import androidx.fragment.app.FragmentManager;
import android.widget.ListView;
import android.widget.BaseAdapter;
import com.skydoves.powermenu.*;
import android.view.ViewGroup;
import android.os.Handler;
import io.github.rosemoe.sora.widget.SymbolInputView;
import Ninja.coder.Ghostemane.code.adapter.*;
import Ninja.coder.Ghostemane.code.marco.*;
import androidx.appcompat.app.AppCompatActivity;
import Ninja.coder.Ghostemane.code.layoutmanager.DialogColorView;
import Ninja.coder.Ghostemane.code.layoutmanager.LogCatBottomSheet;

public class CodeeditorActivity extends AppCompatActivity {

  public final int REQ_CD_SETPASZAMINE = 101;

  private Timer _timer = new Timer();
  private SymbolInputView mSymbolInputView;
  protected Sound sound;
  protected EditorAutoCompleteWindow window;
  private static String EDITOR_LEFT_LINE_KEY = "line";
  private static String EDITOR_LEFT_COLUMN_KEY = "column";
  private static String EDITOR_RIGHT_LINE_KEY = "rightLine";
  private static String EDITOR_RIGHT_COLUMN_KEY = "rightColumn";
  public static String POSTMANPATH = "";
  private WallpaperParallaxEffect effect;
  private Toolbar _toolbar;
  private AppBarLayout _app_bar;
  private CoordinatorLayout _coordinator;
  protected ExrtaFab _fab; // /By ninja coder big man main

  private String currentWord = "";
  private HashMap<String, Object> imap = new HashMap<>();

  private double n = 0;

  private boolean home = false;
  private String code = "";
  private double click2var = 0;
  private double pos10 = 0;

  private double n1 = 0;
  private double n2 = 0;
  private double n3 = 0;
  private double n4 = 0;
  private String datas = "";
  private double itemPosRemoved = 0;
  private String GetTab = "";
  private double index = 0;
  private double ic = 0;
  private String vasteh = "";

  private ArrayList<HashMap<String, Object>> dexter = new ArrayList<>();
  private ArrayList<HashMap<String, Object>> tabs_listmap = new ArrayList<>();
  private ArrayList<String> indifier = new ArrayList<>();
  private ArrayList<HashMap<String, Object>> tags = new ArrayList<>();
  private ArrayList<HashMap<String, Object>> staticSymbiolPiare = new ArrayList<>();
  private ArrayList<String> string = new ArrayList<>();
  private ArrayList<HashMap<String, Object>> map = new ArrayList<>();
  private ArrayList<HashMap<String, Object>> treeviewmap = new ArrayList<>();
  private ArrayList<String> hsistr3000 = new ArrayList<>();
  private ArrayList<HashMap<String, Object>> hsimap3000 = new ArrayList<>();
  private ArrayList<String> list = new ArrayList<>();
  private ArrayList<String> folderList = new ArrayList<>();
  private ArrayList<String> fileList = new ArrayList<>();
  private ArrayList<HashMap<String, Object>> files = new ArrayList<>();

  private LinearLayout BlureView;
  private PraramnetLayoutNinja Mainlinear;
  private LinearLayout multytab;
  private FrameLayout FrameLayout01;
  private LinearLayout newLayoutSymbolBar;
  private LinearLayout CustomToolbar;
  private ProgressBar progressbar1;
  private TextView view;
  private RecyclerView recyclerview1;
  private RecyclerView dir;
  private LinearLayout divar;
  private LinearLayout stopIntarsenl;
  private LinearLayout fileinfo;
  private LinearLayout backgroundPressBack;
  private LinearLayout linear1;
  private LinearLayout linear2;
  private BadgeView badgeview3;
  private TextView tvtitle;
  private TextView typeVl;
  private TextView themenotfound;
  private ImageView helper;
  private ImageView image;
  private ImageView redo;
  private ImageView undo;
  private ImageView st;
  private ImageView menupopnew;
  private IDEEDITOR editor;
  private LinearLayout FrameLayout02;
  private LinearLayout linear3;
  private ProgressBar proanjctor;
  private LinearLayout linear4;
  private LinearLayout barSymoble;
  private LinearLayout linear5;
  private ImageView imageview1;
  private LinearLayout linear6;
  private ImageView down;
  private ImageView left;
  private ImageView up;
  private ImageView rh;
  private TextView tv;
  private LinearLayout divardown;
  private RecyclerView syspiar;

  private Intent intentaddFile = new Intent();
  private Intent htmlrus = new Intent();
  private SharedPreferences word;
  private SharedPreferences line;
  private SharedPreferences shp;
  private SharedPreferences qo;
  private RequestNetwork constreust;
  private RequestNetwork.RequestListener _constreust_request_listener;
  private AlertDialog.Builder myDialog;
  private Intent res = new Intent();
  private TimerTask ask;
  private AlertDialog.Builder di;
  private Intent jsonview = new Intent();
  private Intent getmd = new Intent();
  private Vibrator vb;
  private SharedPreferences getvb;
  private Intent further = new Intent();
  private SharedPreferences re;
  private SharedPreferences war;
  private SharedPreferences kos;
  private SharedPreferences tab100;
  private SharedPreferences setfont;
  private SharedPreferences atx;
  private SharedPreferences ru;
  private ObjectAnimator objectAnim = new ObjectAnimator();
  private SharedPreferences auto;
  private SharedPreferences tabimageview;
  private SharedPreferences moalaqfragment;
  private PowerMenu mmenucog;
  private PowerMenu mmenuitempos;
  private Intent icon = new Intent();
  private PowerMenu pvr;
  private Intent fontHelper = new Intent();
  private Intent setPaszamine = new Intent(Intent.ACTION_GET_CONTENT);
  private SharedPreferences pss;
  private SharedPreferences sve;

  private SoundPool soundPool;
  private SharedPreferences getinitdir;
  private SharedPreferences mthemepost;

  private SharedPreferences shSizePx;
  private PowerMenu jelper;
  private SharedPreferences t;
  private SharedPreferences thememanagersoft;
  private SharedPreferences sf;

  @Override
  protected void onCreate(Bundle _savedInstanceState) {
    super.onCreate(_savedInstanceState);
    setContentView(R.layout.codeeditor);
    initialize(_savedInstanceState);

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

  private boolean isJsonError(String string) {
    HashMap<String, Object> map = new HashMap<>();
    ArrayList<HashMap<String, Object>> listmap = new ArrayList<>();
    try {
      map = new Gson().fromJson(string, new TypeToken<HashMap<String, Object>>() {}.getType());
      return true;
    } catch (Exception e0) {
      try {
        listmap =
            new Gson()
                .fromJson(string, new TypeToken<ArrayList<HashMap<String, Object>>>() {}.getType());
        return true;
      } catch (Exception e1) {
        return false;
      }
    }
  }

  private void initialize(Bundle _savedInstanceState) {
    _app_bar = findViewById(R.id._app_bar);
    _coordinator = findViewById(R.id._coordinator);
    _toolbar = findViewById(R.id._toolbar);
    setSupportActionBar(_toolbar);
    getSupportActionBar().setDisplayHomeAsUpEnabled(true);
    getSupportActionBar().setHomeButtonEnabled(true);
    _toolbar.setNavigationOnClickListener(
        new View.OnClickListener() {
          @Override
          public void onClick(View _v) {
            onBackPressed();
          }
        });
    _fab = findViewById(R.id._fab);

    BlureView = findViewById(R.id.BlureView);
    _coordinator = findViewById(R.id._coordinator);
    Mainlinear = findViewById(R.id.Mainlinear);
    multytab = findViewById(R.id.multytab);
    FrameLayout01 = findViewById(R.id.FrameLayout01);
    newLayoutSymbolBar = findViewById(R.id.newLayoutSymbolBar);
    CustomToolbar = findViewById(R.id.CustomToolbar);
    progressbar1 = findViewById(R.id.progressbar1);
    view = findViewById(R.id.view);
    recyclerview1 = findViewById(R.id.recyclerview1);
    dir = findViewById(R.id.dir);
    divar = findViewById(R.id.divar);
    stopIntarsenl = findViewById(R.id.stopIntarsenl);
    fileinfo = findViewById(R.id.fileinfo);
    backgroundPressBack = findViewById(R.id.backgroundPressBack);
    linear1 = findViewById(R.id.linear1);
    linear2 = findViewById(R.id.linear2);
    badgeview3 = findViewById(R.id.badgeview3);
    tvtitle = findViewById(R.id.tvtitle);
    typeVl = findViewById(R.id.typeVl);
    themenotfound = findViewById(R.id.themenotfound);
    helper = findViewById(R.id.helper);
    image = findViewById(R.id.image);
    redo = findViewById(R.id.redo);
    undo = findViewById(R.id.undo);
    st = findViewById(R.id.st);
    menupopnew = findViewById(R.id.menupopnew);
    editor = findViewById(R.id.editor);
    FrameLayout02 = findViewById(R.id.FrameLayout02);
    linear3 = findViewById(R.id.linear3);
    proanjctor = findViewById(R.id.proanjctor);
    linear4 = findViewById(R.id.linear4);
    barSymoble = findViewById(R.id.barSymoble);
    linear5 = findViewById(R.id.linear5);
    imageview1 = findViewById(R.id.imageview1);
    linear6 = findViewById(R.id.linear6);
    down = findViewById(R.id.down);
    left = findViewById(R.id.left);
    up = findViewById(R.id.up);
    rh = findViewById(R.id.rh);
    tv = findViewById(R.id.tv);
    divardown = findViewById(R.id.divardown);
    syspiar = findViewById(R.id.syspiar);
    word = getSharedPreferences("word", Activity.MODE_PRIVATE);
    line = getSharedPreferences("line", Activity.MODE_PRIVATE);
    shp = getSharedPreferences("shp", Activity.MODE_PRIVATE);
    qo = getSharedPreferences("qo", Activity.MODE_PRIVATE);
    constreust = new RequestNetwork(this);
    myDialog = new AlertDialog.Builder(this);
    di = new AlertDialog.Builder(this);
    vb = (Vibrator) getSystemService(Context.VIBRATOR_SERVICE);
    getvb = getSharedPreferences("getvb", Activity.MODE_PRIVATE);
    re = getSharedPreferences("re", Activity.MODE_PRIVATE);
    war = getSharedPreferences("war", Activity.MODE_PRIVATE);
    kos = getSharedPreferences("kos", Activity.MODE_PRIVATE);
    tab100 = getSharedPreferences("tab100", Activity.MODE_PRIVATE);
    setfont = getSharedPreferences("setfont", Activity.MODE_PRIVATE);
    atx = getSharedPreferences("atx", Activity.MODE_PRIVATE);
    ru = getSharedPreferences("ru", Activity.MODE_PRIVATE);
    auto = getSharedPreferences("auto", Activity.MODE_PRIVATE);
    tabimageview = getSharedPreferences("tabimageview", Activity.MODE_PRIVATE);
    moalaqfragment = getSharedPreferences("moalaqfragment", Activity.MODE_PRIVATE);
    setPaszamine.setType("image/*");
    setPaszamine.putExtra(Intent.EXTRA_ALLOW_MULTIPLE, true);
    pss = getSharedPreferences("pss", Activity.MODE_PRIVATE);
    sve = getSharedPreferences("sve", Activity.MODE_PRIVATE);
    getinitdir = getSharedPreferences("getinitdir", Activity.MODE_PRIVATE);
    mthemepost = getSharedPreferences("mthemepost", Activity.MODE_PRIVATE);
    shSizePx = getSharedPreferences("shSizePx", Activity.MODE_PRIVATE);
    t = getSharedPreferences("t", Activity.MODE_PRIVATE);
    thememanagersoft = getSharedPreferences("thememanagersoft", Activity.MODE_PRIVATE);
    sf = getSharedPreferences("sf", Activity.MODE_PRIVATE);

    recyclerview1.addOnScrollListener(
        new RecyclerView.OnScrollListener() {
          @Override
          public void onScrollStateChanged(RecyclerView recyclerView, int _scrollState) {
            super.onScrollStateChanged(recyclerView, _scrollState);
          }

          @Override
          public void onScrolled(RecyclerView recyclerView, int _offsetX, int _offsetY) {
            super.onScrolled(recyclerView, _offsetX, _offsetY);
            if (_offsetX > (tabs_listmap.size() + 1)) {
              _clickAnimation(getWindow().getDecorView());
              _clickAnimation(editor);
              _clickAnimation(multytab);
              _clickAnimation(newLayoutSymbolBar);
              _clickAnimation(_fab);
            } else {
              _clickAnimation(editor);
              _clickAnimation(multytab);
              _clickAnimation(newLayoutSymbolBar);
              _clickAnimation(_fab);
              _clickAnimation(getWindow().getDecorView());
            }
          }
        });

    backgroundPressBack.setOnClickListener(
        new View.OnClickListener() {
          @Override
          public void onClick(View _view) {
            onBackPressed();
          }
        });

    image.setOnClickListener(
        new View.OnClickListener() {
          @Override
          public void onClick(View _view) {
            if (ic == 1) {
              image.setImageResource(R.drawable.noeye);
              editor.setEditable(false);
              ic--;
            } else {
              editor.setEditable(true);
              image.setImageResource(R.drawable.okeye);
              ic++;
            }
          }
        });

    redo.setOnClickListener(
        new View.OnClickListener() {
          @Override
          public void onClick(View _view) {
            editor.AutoRedo();
          }
        });

    undo.setOnClickListener(
        new View.OnClickListener() {
          @Override
          public void onClick(View _view) {
            editor.AutoUndo();
          }
        });

    st.setOnClickListener(
        new View.OnClickListener() {
          @Override
          public void onClick(View _view) {
            mmenucog =
                new PowerMenu.Builder(CodeeditorActivity.this)
                    .addItem(new PowerMenuItem("فونت منجر", false, R.drawable.fonthelper))
                    .addItem(
                        new PowerMenuItem(
                            "پس زمینه", false, R.drawable.keyboardlisnertalluserpost_3))
                    .build();
            mmenucog.setIconPadding(8);
            mmenucog.setIconSize(25);
            mmenucog.setAutoDismiss(true);
            mmenucog.setAnimation(MenuAnimation.ELASTIC_CENTER);
            mmenucog.setMenuRadius(30f);
            mmenucog.setSelectedEffect(true);
            mmenucog.showAsDropDown(st);
            mmenucog.setShowBackground(false);

            mmenucog.setDividerHeight(2);
            if (ru.contains("rup")) {
              mmenucog.setIconColor((int) 0xFFFDA893);
              mmenucog.setMenuColor(0xFF2B2121);
              mmenucog.setDivider(new ColorDrawable(0xFFFDA893));
              mmenucog.setTextColor(0xFFEEEEEE);
            } else {
              if (imap.containsKey("ImageColor")) {
                mmenucog.setIconColor((int) Color.parseColor(imap.get("ImageColor").toString()));
              } else {
                mmenucog.setDivider(new ColorDrawable(0xFFFDA893));
              }
              if (imap.containsKey("LINE_DIVIDER")) {
                mmenucog.setDivider(
                    new ColorDrawable(Color.parseColor(imap.get("LINE_DIVIDER").toString())));
              } else {
                mmenucog.setIconColor((int) 0xFFFDA893);
              }
              if (imap.containsKey("MenuBackground")) {
                mmenucog.setMenuColor(Color.parseColor(imap.get("MenuBackground").toString()));
              } else {
                mmenucog.setMenuColor(0xFF2B2121);
              }
              if (imap.containsKey("TEXT_NORMAL")) {
                mmenucog.setTextColor(Color.parseColor(imap.get("TEXT_NORMAL").toString()));
              } else {
                mmenucog.setTextColor(0xFFEEEEEE);
              }
            }
            mmenucog.setOnMenuItemClickListener(
                new OnMenuItemClickListener<PowerMenuItem>() {
                  @Override
                  public void onItemClick(int position, PowerMenuItem item) {
                    switch (position) {
                      case 0:
                        {
                          fontHelper.setClass(getApplicationContext(), FontchakerActivity.class);
                          fontHelper.setFlags(Intent.FLAG_ACTIVITY_SINGLE_TOP);
                          startActivity(fontHelper);
                          break;
                        }
                      case 1:
                        {
                          Intent myintent =
                              new Intent(
                                  Intent.ACTION_PICK,
                                  android.provider.MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
                          startActivityForResult(myintent, REQ_CD_SETPASZAMINE);

                          break;
                        }
                    }
                  }
                });
          }
        });

    menupopnew.setOnClickListener(
        new View.OnClickListener() {
          @Override
          public void onClick(View _view) {
            _managerpanel(menupopnew);
          }
        });

    imageview1.setOnClickListener(
        new View.OnClickListener() {
          @Override
          public void onClick(View _view) {
            barSymoble.setVisibility(View.GONE);
            _fab.setVisibility(View.VISIBLE);
          }
        });

    down.setOnClickListener(
        new View.OnClickListener() {
          @Override
          public void onClick(View _view) {
            editor.moveSelectionDown();
          }
        });

    left.setOnClickListener(
        new View.OnClickListener() {
          @Override
          public void onClick(View _view) {
            editor.moveSelectionLeft();
          }
        });

    up.setOnClickListener(
        new View.OnClickListener() {
          @Override
          public void onClick(View _view) {
            editor.moveSelectionUp();
          }
        });

    rh.setOnClickListener(
        new View.OnClickListener() {
          @Override
          public void onClick(View _view) {
            editor.moveSelectionRight();
          }
        });

    tv.setOnClickListener(
        new View.OnClickListener() {
          @Override
          public void onClick(View _view) {
            _sysba("    ");
            if (tab100.contains("mpcnullgogo")) {
              if (tab100.getString("mpcnullgogo", "").equals("4")) {
                _sysba("    ");
              } else {
                if (tab100.getString("mpcnullgogo", "").equals("6")) {
                  _sysba("      ");
                } else {
                  if (tab100.getString("mpcnullgogo", "").equals("8")) {
                    _sysba("        ");
                  } else {
                    if (tab100.getString("mpcnullgogo", "").equals("16")) {
                      _sysba("                  ");
                    } else {
                      _sysba("    ");
                    }
                  }
                }
              }
            }
          }
        });

    _fab.setOnClickListener(
        new View.OnClickListener() {
          @Override
          public void onClick(View _view) {
            _fabCl();
          }
        });

    _constreust_request_listener =
        new RequestNetwork.RequestListener() {
          @Override
          public void onResponse(String _param1, String _param2, HashMap<String, Object> _param3) {
            final String _tag = _param1;
            final String _response = _param2;
            final HashMap<String, Object> _responseHeaders = _param3;
          }

          @Override
          public void onErrorResponse(String _param1, String _param2) {
            final String _tag = _param1;
            final String _message = _param2;
          }
        };
  }

  private void initializeLogic() {
    proanjctor.setVisibility(View.GONE);
    barSymoble.setVisibility(View.GONE);
    _vi();
    POSTMANPATH = shp.getString("pos_path", "");
    _fab.shrink();
    Animation animation = AnimationUtils.loadAnimation(getApplicationContext(), R.anim.ab);
    animation.start();
    editor.setAnimation(animation);
    editor.setFadein();
    soundPool = new SoundPool((int) (3), AudioManager.STREAM_MUSIC, 0);
    n1 = soundPool.load(getApplicationContext(), R.raw.typenormal, 1);
    n2 = soundPool.load(getApplicationContext(), R.raw.typeremoved, 1);
    n3 = soundPool.load(getApplicationContext(), R.raw.ddoc, 1);
    n4 = soundPool.load(getApplicationContext(), R.raw.typespace, 1);
    editor.setLnTip("L->");
    editor.setFirstLineNumberAlwaysVisible(true);
    editor.setOverScrollEnabled(true);
    editor.setInputType(
        EditorInfo.TYPE_TEXT_FLAG_NO_SUGGESTIONS
            | EditorInfo.TYPE_CLASS_TEXT
            | EditorInfo.TYPE_TEXT_FLAG_MULTI_LINE
            | EditorInfo.TYPE_TEXT_VARIATION_VISIBLE_PASSWORD);
    editor.setImportantForAutofill(View.IMPORTANT_FOR_AUTOFILL_NO);
    var size =
        TypedValue.applyDimension(
            TypedValue.COMPLEX_UNIT_DIP,
            2,
            getApplicationContext().getResources().getDisplayMetrics());
    editor.setDividerWidth(size);

    editor.setKeyboardOperation(
        new CodeEditor.OnKeyboardOperation() {
          @Override
          public void Tab() {
            Toast.makeText(getApplicationContext(), "This Tab", 2).show();
          }

          @Override
          public void Space() {
            Toast.makeText(getApplicationContext(), "This Space", 2).show();
          }

          @Override
          public void Removed() {
            // Toast.makeText(getApplicationContext(),"This Key Del",2).show();
            n1 = soundPool.load(getApplicationContext(), R.raw.typenormal, 1);
          }

          @Override
          public void Enter() {
            n2 = soundPool.play((int) (2), 1.0f, 1.0f, 1, (int) (0), 1.0f);
            if (editor.getEditorLanguage() instanceof HTMLLanguage) {
              editor.formatCodeAsync();
            }

            //	Toast.makeText(getApplicationContext(),"This Key Enter",2).show();
          }
        });
    if (sf.contains("sd100")) {
      if (sf.getInt("sd100", 1) == 1) {
        editor.setCompletionWndPositionMode(CodeEditor.WINDOW_POS_MODE_AUTO);
      } else if (sf.getInt("sd100", 1) == 2) {
        editor.setCompletionWndPositionMode(CodeEditor.WINDOW_POS_MODE_FOLLOW_CURSOR_ALWAYS);
      } else if (sf.getInt("sd100", 1) == 3) {
        editor.setCompletionWndPositionMode(CodeEditor.WINDOW_POS_MODE_FULL_WIDTH_ALWAYS);
      }
    }

    editor.setLigatureEnabled(true);
    editor.setHighlightCurrentBlock(false);
    editor.setHighlightCurrentLine(false);
    editor.setAutoCompletionOnComposing(false);
    editor.setLineInfoTextSize(20f);
    editor.setBlockLineEnabled(true);

    var projectz = new ProjectManager();
    projectz.setProjectName(getIntent().getStringExtra("root"));

    editor.subscribeEvent(
        io.github.rosemoe.sora.event.ContentChangeEvent.class,
        (event, subscribe) -> {
          /// Code for saving file
          int line = event.getEditor().getCursor().getLeftLine();
          int column = event.getEditor().getCursor().getLeftColumn();

          if (event.getEditor().getText().toString().startsWith("\"#")) {
            try {
              int color =
                  Color.parseColor(
                      event
                          .getEditor()
                          .getText()
                          .toString()
                          .substring(1, event.getEditor().getText().length() - 1));
              event
                  .getEditor()
                  .getTextAnalyzeResult()
                  .addIfNeeded(line, column, EditorColorScheme.LITERAL);

              Span span = Span.obtain(column + 1, EditorColorScheme.LITERAL);
              span.setUnderlineColor(color);
              event.getEditor().getTextAnalyzeResult().add(line, span);
              Log.e("Error not Color set", span.toString());

              Span middle =
                  Span.obtain(
                      column + event.getEditor().getText().length() - 1, EditorColorScheme.LITERAL);
              middle.setUnderlineColor(Color.TRANSPARENT);
              event.getEditor().getTextAnalyzeResult().add(line, middle);

              Span end =
                  Span.obtain(
                      column + event.getEditor().getText().length(),
                      TextStyle.makeStyle(EditorColorScheme.TEXT_NORMAL));
              end.setUnderlineColor(Color.TRANSPARENT);
              event.getEditor().getTextAnalyzeResult().add(line, end);
              // Log.e("Color Error", result.toString());

            } catch (Exception ignore) {
              ignore.printStackTrace();
            }
          }
          final Handler handler = new Handler();
          proanjctor.setVisibility(View.VISIBLE);
          handler.postDelayed(
              () -> {
                proanjctor.setVisibility(View.GONE);
              },
              3000L);

          if (editor.getEditorLanguage() instanceof HTMLLanguage)
            if (editor.getText().toString().contains("<"))
              androidx.appcompat.widget.TooltipCompat.setTooltipText(editor, "FixBug");
          try {
            String textSpan = editor.getText().toString();
            final int selection = editor.getCursor().getLeft();
            final Pattern pattern = Pattern.compile("(#?)(\\w+)");
            final Matcher matcher = pattern.matcher(textSpan);
            int start = 0;
            int end = 0;

            String currentWordddddddd = "";
            try {
              while (matcher.find()) {
                start = matcher.start();
                end = matcher.end();
                if (start <= selection && selection <= end) {
                  currentWordddddddd = textSpan.substring(start, end).toString();
                  currentWord = currentWordddddddd;
                }
              }
            } catch (Exception rr) {
              rr.printStackTrace();
            }

            if (!currentWord.isEmpty()) {
              if (currentWord.contains("#")) {
                try {

                  badgeview3.setBadgeBackground(Color.parseColor(currentWord));
                  badgeview3.setBadgeCount("#");
                  badgeview3.setTextSize((int) 12);
                  badgeview3.setTextColor(0xFFFFFFFF);
                } catch (IllegalArgumentException iae) {

                }
              } else {
                if (currentWord.toLowerCase().contains("0xff")) {
                  try {

                    currentWord = currentWord.replace("0xff", "#");
                    currentWord = currentWord.replace("0xFF", "#");
                    badgeview3.setBadgeCount("0xff");
                    badgeview3.setVisibility(View.VISIBLE);
                    badgeview3.setBadgeBackground(Color.parseColor(currentWord));
                    badgeview3.setTextSize((int) 12);
                    if (androidx.core.graphics.ColorUtils.calculateLuminance(
                            Color.parseColor(currentWord))
                        < 0.5) {
                      badgeview3.setTextColor(0xFFFFFFFF);
                    } else {
                      if (androidx.core.graphics.ColorUtils.calculateLuminance(
                              Color.parseColor(currentWord))
                          >= 0.5) {
                        badgeview3.setTextColor(0xFF000000);
                      }
                    }
                  } catch (IllegalArgumentException iae) {

                  }
                } else {
                  badgeview3.setVisibility(View.GONE);
                  badgeview3.setBadgeBackground(Color.TRANSPARENT);
                }
              }
            }
          } catch (Exception e) {
            e.printStackTrace();
          }
        });
    if (sve.contains("getAutoSave")) {
      if (sve.getString("getAutoSave", "").equals("true")) {
        editor.subscribeEvent(
            io.github.rosemoe.sora.event.ContentChangeEvent.class,
            (event, subscribe) -> {
              /// Code for saving file
              FileUtil.writeFile(shp.getString("pos_path", ""), editor.getText().toString());
            });
      } else {

      }
    }
    if (FileUtil.isExistFile(pss.getString("getWallpaparSazen1000", ""))) {
      BlurImage.Start(
          _coordinator,
          CodeeditorActivity.this,
          pss.getString("getWallpaparSazen1000", ""),
          thememanagersoft.contains("br") ? thememanagersoft.getFloat("br", 2) : 3);
    } else {
      if (imap.containsKey("BackgroundColorLinear")) {
        _coordinator.setBackgroundColor(
            Color.parseColor(imap.get("BackgroundColorLinear").toString()));
      } else {
        _coordinator.setBackgroundColor(0xFF2B2120);
      }
    }
    _symbloinit();
    _poz();
    if (shp.contains("pos_path")) {
      if (!shp.getString("pos_path", "").equals("")) {
        _codeEditor(shp.getString("pos_path", ""));
      }
    }
    FileUtil.writeFile(
        "/storage/emulated/0/GhostWebIDE/ninjacoder/openFile.json", shp.getString("path", ""));
    stopIntarsenl.setBackground(
        new GradientDrawable() {
          public GradientDrawable getIns(int a, int b) {
            this.setCornerRadius(a);
            this.setColor(b);
            return this;
          }
        }.getIns((int) 50, 0xFFF44336));
    fileinfo.setBackground(
        new GradientDrawable() {
          public GradientDrawable getIns(int a, int b) {
            this.setCornerRadius(a);
            this.setColor(b);
            return this;
          }
        }.getIns((int) 50, 0xFFFFEB3B));
    backgroundPressBack.setBackground(
        new GradientDrawable() {
          public GradientDrawable getIns(int a, int b) {
            this.setCornerRadius(a);
            this.setColor(b);
            return this;
          }
        }.getIns((int) 50, 0xFFE91E63));

    progressbar1.setVisibility(View.GONE);

    badgeview3.setBadgeBackground(Color.TRANSPARENT);
    badgeview3.setBadgeCount("");
    _EditorSummery();
    imap = new HashMap<>();

    if (FileUtil.isExistFile(thememanagersoft.getString("themes", ""))) {
      if (thememanagersoft.contains("themes")) {
        imap =
            new Gson()
                .fromJson(
                    FileUtil.readFile(thememanagersoft.getString("themes", "")),
                    new TypeToken<HashMap<String, Object>>() {}.getType());
      } else {
        if (FileUtil.isExistFile("storage/emulated/0/GhostWebIDE/theme/GhostThemeapp.ghost")) {
          imap =
              new Gson()
                  .fromJson(
                      FileUtil.readFile("storage/emulated/0/GhostWebIDE/theme/GhostThemeapp.ghost"),
                      new TypeToken<HashMap<String, Object>>() {}.getType());
        } else {
          imap =
              new Gson()
                  .fromJson(
                      "{\n  \"ToolbarTextColor\": \"#ffff1750\",\n  \"BLOCK_LINE_CURRENT\": \"#ff28ffae\",\n  \"LINE_DIVIDER\": \"#1d000000\",\n  \"SyombolBarTextColor\": \"#ffff005b\",\n  \"HTML_TAG\": \"#ff1bffdd\",\n  \"FabColorStroker\": \"#ffc9ffd0\",\n  \"LINE_NUMBER\": \"#ff0dedff\",\n  \"KEYWORD\": \"#ff00d9ff\",\n  \"AUTO_COMP_PANEL_CORNER\": \"#ffffa1af\",\n  \"SELECTION_HANDLE\": \"#ff2a6373\",\n  \"TabImageColorFilter\": \"#ffbcffff\",\n  \"AUTO_COMP_PANEL_BG\": \"#ff0b1a32\",\n  \"COMMENT\": \"#626262\",\n  \"ToolbarColor\": \"#ff050d19\",\n  \"IDENTIFIER_NAME\": \"#fff0be4b\",\n  \"DisplayTextColorTab\": \"#fffad7ff\",\n  \"NON_PRINTABLE_CHAR\": \"#ff6b90ff\",\n  \"SELECTION_INSERT\": \"#ff2a6373\",\n  \"Ninja\": \"#ffddaeff\",\n  \"TEXTCOLORHDER\": \"#ff522012\",\n  \"TabTextColor\": \"#ffc9eaff\",\n  \"BLOCK_LINE\": \"#ff5effaa\",\n  \"MenuBackground\": \"#ff0a1320\",\n  \"LITERAL\": \"#ffbcf5ff\",\n  \"FabBackgroundColorColor\": \"#ff021020\",\n  \"ATTRIBUTE_VALUE\": \"#ffa6ffa1\",\n  \"TabBack\": \"#ff1e5e71\",\n  \"TEXTCOLORFORGRAND\": \"#424242\",\n  \"ImageColor\": \"#ffa1f1ff\",\n  \"TEXT_NORMAL\": \"#ffffffff\",\n  \"ATTRIBUTE_NAME\": \"#ffa1e3ff\",\n  \"print\": \"#ffecffa1\",\n  \"OPERATOR\": \"#ff43ffd5\",\n  \"CURRENT_LINE\": \"#20171717\",\n  \"WHOLE_BACKGROUND\": \"#02FFFFFF\",\n  \"BackgroundColorLinear\": \"#ff050d19\",\n  \"FabImageColor\": \"#ffbce1ff\",\n  \"LINE_NUMBER_BACKGROUND\": \"#00FFFFFF\",\n  \"TEXTCOLORIGOR\": \"#ff500001\",\n  \"TEXTCOLORINIER\": \"#ff402811\"\n}",
                      new TypeToken<HashMap<String, Object>>() {}.getType());
        }
      }
    } else {
      imap =
          new Gson()
              .fromJson(
                  FileUtil.readFile("storage/emulated/0/GhostWebIDE/theme/GhostThemeapp.ghost"),
                  new TypeToken<HashMap<String, Object>>() {}.getType());
    }

    getWindow()
        .setSoftInputMode(
            WindowManager.LayoutParams.SOFT_INPUT_STATE_VISIBLE
                | WindowManager.LayoutParams.SOFT_INPUT_ADJUST_RESIZE);
    _coderuner();
    tv.setOnTouchListener(
        new View.OnTouchListener() {
          @Override
          public boolean onTouch(View v, MotionEvent event) {
            switch (event.getAction()) {
              case MotionEvent.ACTION_DOWN:
                {
                  ObjectAnimator scaleX = new ObjectAnimator();
                  scaleX.setTarget(tv);
                  scaleX.setPropertyName("scaleX");
                  scaleX.setFloatValues(0.9f);
                  scaleX.setDuration((int) 5);
                  scaleX.start();

                  ObjectAnimator scaleY = new ObjectAnimator();
                  scaleY.setTarget(tv);
                  scaleY.setPropertyName("scaleY");
                  scaleY.setFloatValues(0.9f);
                  scaleY.setDuration((int) 5);
                  scaleY.start();
                  break;
                }
              case MotionEvent.ACTION_UP:
                {
                  ObjectAnimator scaleX = new ObjectAnimator();
                  scaleX.setTarget(tv);
                  scaleX.setPropertyName("scaleX");
                  scaleX.setFloatValues((float) 1);
                  scaleX.setDuration((int) 5);
                  scaleX.start();

                  ObjectAnimator scaleY = new ObjectAnimator();
                  scaleY.setTarget(tv);
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
    editor.setLineNumberAlign(Paint.Align.CENTER);

    SetThemeForJson themeForJson2 = new SetThemeForJson();
    themeForJson2.setThemeCodeEditor(editor, imap, false, this);
    themeForJson2.addTextColor(
        tvtitle, "SyombolBarTextColor", Color.parseColor("#FFFFA0FB"), this, imap);
    themeForJson2.addTextColor(
        typeVl, "SyombolBarTextColor", Color.parseColor("#FFFFA0FB"), this, imap);
    themeForJson2.addTextColor(
        tv, "SyombolBarTextColor", Color.parseColor("#FFFFA0FB"), this, imap);
    themeForJson2.addTextColor(
        view, "SyombolBarTextColor", Color.parseColor("#FFFFA0FB"), this, imap);
    themeForJson2.AddthemetoSattos(this, imap);
    themeForJson2.addBackground(this, imap, "ToolbarColor", CustomToolbar, 0xFF281B26);
    themeForJson2.addBackground(this, imap, "BackgroundColorLinear", Mainlinear, 0xFF281B26);
    themeForJson2.addBackground(this, imap, "TabImageColorFilter", divar, Color.RED);
    themeForJson2.addBackground(this, imap, "TabImageColorFilter", divardown, Color.RED);
    // divardown
    themeForJson2.addImageColor(undo, this, "ImageColor", imap, Color.parseColor("#ff94e7ff"));
    themeForJson2.addImageColor(redo, this, "ImageColor", imap, Color.parseColor("#ff94e7ff"));
    themeForJson2.addImageColor(image, this, "ImageColor", imap, Color.parseColor("#ff94e7ff"));
    themeForJson2.addImageColor(st, this, "ImageColor", imap, Color.parseColor("#ff94e7ff"));
    themeForJson2.addImageColor(
        menupopnew, this, "ImageColor", imap, Color.parseColor("#ff94e7ff"));
    themeForJson2.addImageColor(helper, this, "ImageColor", imap, Color.parseColor("#ff94e7ff"));
    themeForJson2.mfabcolor(this, _fab, imap);
    themeForJson2.mfab2(this, _fab, imap);
    AnimUtils.ClickAnimation(menupopnew);
    AnimUtils.ClickAnimation(st);
    AnimUtils.ClickAnimation(undo);
    AnimUtils.ClickAnimation(redo);
    AnimUtils.ClickAnimation(helper);

    if (ru.contains("rup")) {
      MapObjectData.setMatetialThemeCodeEditor(editor);
      CustomToolbar.setBackgroundColor(0xFF201B16);
      Mainlinear.setBackgroundColor(0xFF201B16);
      redo.setColorFilter(0xFFFFDCBD, PorterDuff.Mode.MULTIPLY);
      undo.setColorFilter(0xFFFFDCBD, PorterDuff.Mode.MULTIPLY);
      st.setColorFilter(0xFFEEEEEE, PorterDuff.Mode.MULTIPLY);
      menupopnew.setColorFilter(0xFFEEEEEE, PorterDuff.Mode.MULTIPLY);
      image.setColorFilter(0xFFFFB689, PorterDuff.Mode.MULTIPLY);
      tvtitle.setTextColor(0xFFFDA893);
      typeVl.setTextColor(0xFFFDA893);
      themenotfound.setTextColor(0xFFFDA893);
      tv.setTextColor(0xFFFDA893);
      helper.setColorFilter(0xFFFFDCBD, PorterDuff.Mode.MULTIPLY);
      _fab.setBackgroundTintList(ColorStateList.valueOf(0xFF2B2122));

      _fab.setStrokeColor(ColorStateList.valueOf(0xFFFDB69A));
      _fab.setStrokeWidth(1);
      _fab.setIconTint(ColorStateList.valueOf(0xFFFDB69A));
      if (Build.VERSION.SDK_INT > Build.VERSION_CODES.LOLLIPOP) {
        Window ninjacoder = this.getWindow();
        ninjacoder.clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
        ninjacoder.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);

        ninjacoder.setStatusBarColor(Color.parseColor("#FF2B2122"));
        ninjacoder.setNavigationBarColor(Color.parseColor("#FF2B2122"));
      }
      divardown.setBackgroundColor(0xFFC4A68A);
      divar.setBackgroundColor(0xFFC4A68A);
    } else {

    }
    _sttingpp();
    tvtitle.setText("GWI");
    _Animwork(_fab);
    _Anim01(editor);

    _toolbar.setVisibility(View.GONE);
    editor
        .getColorScheme()
        .setColor(EditorColorScheme.MATCHED_TEXT_BACKGROUND, Color.parseColor("#75800F31"));
    // editor.setColorScheme(editor.getColorScheme().setColor(EditorColorScheme.red,Color.RED));
    editor.getColorScheme().setColor(EditorColorScheme.red, Color.parseColor("#FF710000"));
    if (tab100.contains("mpcnullgogo")) {
      try {
        _tabsize(Double.parseDouble(tab100.getString("mpcnullgogo", "")));
      } catch (Exception e) {
        SketchwareUtil.showMessage(getApplicationContext(), "error");
      }
    }
    if (setfont.contains("mfont")) {
      if (!FileUtil.isFile(setfont.getString("mfont", ""))) {
        editor.setTypefaceText(Typeface.createFromAsset(getAssets(), "ghostfont.ttf"));
        editor.setTypefaceLineNumber(Typeface.createFromAsset(getAssets(), "ghostfont.ttf"));
        SketchwareUtil.showMessage(getApplicationContext(), "Custom Font Not Found");
        tvtitle.setTypeface(Typeface.createFromAsset(getAssets(), "fonts/ghostfont.ttf"), 0);
        typeVl.setTypeface(Typeface.createFromAsset(getAssets(), "fonts/ghostfont.ttf"), 0);
        themenotfound.setTypeface(Typeface.createFromAsset(getAssets(), "fonts/ghostfont.ttf"), 0);
        tv.setTypeface(Typeface.createFromAsset(getAssets(), "fonts/ghostfont.ttf"), 0);
      } else {
        _editorsetfontfromfile(setfont.getString("mfont", ""));
        tvtitle.setTypeface(Typeface.createFromFile(new File(setfont.getString("mfont", ""))));
        typeVl.setTypeface(Typeface.createFromFile(new File(setfont.getString("mfont", ""))));
        themenotfound.setTypeface(
            Typeface.createFromFile(new File(setfont.getString("mfont", ""))));
        tv.setTypeface(Typeface.createFromFile(new File(setfont.getString("mfont", ""))));
      }
    } else {
      editor.setTypefaceText(Typeface.createFromAsset(getAssets(), "ghostfont.ttf"));
      editor.setTypefaceLineNumber(Typeface.createFromAsset(getAssets(), "ghostfont.ttf"));
      tvtitle.setTypeface(Typeface.createFromAsset(getAssets(), "fonts/ghostfont.ttf"), 0);
      typeVl.setTypeface(Typeface.createFromAsset(getAssets(), "fonts/ghostfont.ttf"), 0);
      themenotfound.setTypeface(Typeface.createFromAsset(getAssets(), "fonts/ghostfont.ttf"), 0);
      tv.setTypeface(Typeface.createFromAsset(getAssets(), "fonts/ghostfont.ttf"), 0);
    }
    if (auto.contains("mauto")) {
      editor.setAutoCompletionEnabled(false);
    } else {
      editor.setAutoCompletionEnabled(true);
    }
    _fab.setIconResource(R.drawable.play);
    home = true;
    if (getinitdir.contains("mdir")) {
      if (getinitdir.getString("mdir", "").equals("true")) {
        dir.setVisibility(View.GONE);
      } else {
        dir.setVisibility(View.VISIBLE);
      }
    }
    if (imap.containsKey("TabBack")) {
      if (android.os.Build.VERSION.SDK_INT >= 21) {
        proanjctor
            .getIndeterminateDrawable()
            .setColorFilter(
                Color.parseColor(imap.get("TabBack").toString()), PorterDuff.Mode.SRC_IN);
      }
      if (android.os.Build.VERSION.SDK_INT >= 21) {
        progressbar1
            .getIndeterminateDrawable()
            .setColorFilter(
                Color.parseColor(imap.get("TabBack").toString()), PorterDuff.Mode.SRC_IN);
      }
    } else {
      if (android.os.Build.VERSION.SDK_INT >= 21) {
        proanjctor.getIndeterminateDrawable().setColorFilter(0xFFFFB689, PorterDuff.Mode.SRC_IN);
      }
      if (android.os.Build.VERSION.SDK_INT >= 21) {
        progressbar1.getIndeterminateDrawable().setColorFilter(0xFFFFB689, PorterDuff.Mode.SRC_IN);
      }
    }
    AnimUtils.ClickAnimation(image);
    if (mthemepost.getString("mytheme", "").equals("true")) {
      EdgeToEdge.enable(this);
    } else {
      if (imap.containsKey("BackgroundColorLinear")) {
        if (Build.VERSION.SDK_INT > Build.VERSION_CODES.LOLLIPOP) {
          Window ninjacoder = this.getWindow();
          ninjacoder.clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
          ninjacoder.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);

          ninjacoder.setStatusBarColor(
              Color.parseColor(imap.get("BackgroundColorLinear").toString()));
          ninjacoder.setNavigationBarColor(
              Color.parseColor(imap.get("BackgroundColorLinear").toString()));
        }
      } else {
        if (Build.VERSION.SDK_INT > Build.VERSION_CODES.LOLLIPOP) {
          Window ninjacoder = this.getWindow();
          ninjacoder.clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
          ninjacoder.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);

          ninjacoder.setStatusBarColor(Color.parseColor("#FF2B2122"));
          ninjacoder.setNavigationBarColor(Color.parseColor("#FF2B2122"));
        }
      }
    }
  }

  @Override
  protected void onActivityResult(int _requestCode, int _resultCode, Intent _data) {
    super.onActivityResult(_requestCode, _resultCode, _data);

    switch (_requestCode) {
      case REQ_CD_SETPASZAMINE:
        if (_resultCode == Activity.RESULT_OK) {
          ArrayList<String> _filePath = new ArrayList<>();
          if (_data != null) {
            if (_data.getClipData() != null) {
              for (int _index = 0; _index < _data.getClipData().getItemCount(); _index++) {
                ClipData.Item _item = _data.getClipData().getItemAt(_index);
                _filePath.add(
                    FileUtil.convertUriToFilePath(getApplicationContext(), _item.getUri()));
              }
            } else {
              _filePath.add(
                  FileUtil.convertUriToFilePath(getApplicationContext(), _data.getData()));
            }
          }
          if (_filePath.get((int) (0)).endsWith(".jpeg")
              || (_filePath.get((int) (0)).endsWith(".jpg")
                  || (_filePath.get((int) (0)).endsWith(".png")
                      || (_filePath.get((int) (0)).endsWith(".webp")
                          || _filePath.get((int) (0)).endsWith(".mp4"))))) {
            pss.edit().putString("getWallpaparSazen1000", _filePath.get((int) (0))).apply();
            BlurImage.Start(
                _coordinator,
                CodeeditorActivity.this,
                pss.getString("getWallpaparSazen1000", ""),
                thememanagersoft.contains("br") ? thememanagersoft.getFloat("br", 2) : 3);
          } else {
            var dialogpost =
                new com.google.android.material.dialog.MaterialAlertDialogBuilder(
                    CodeeditorActivity.this);
            dialogpost.setTitle("Error!");
            dialogpost.setMessage("Please Set in Format(png,jpeg,jpg)");
            dialogpost.setPositiveButton("ok", (mp1, md2) -> {});
            androidx.appcompat.app.AlertDialog dialogJanator = dialogpost.show();
            final View setview = dialogJanator.getWindow().getDecorView();
            setview.setScaleX(0f);
            setview.setScaleY(0f);
            final ObjectAnimator alertAnim = new ObjectAnimator();
            final ObjectAnimator alertAnim1 = new ObjectAnimator();
            alertAnim.setTarget(setview);
            alertAnim.setPropertyName("scaleX");
            alertAnim.setFloatValues((float) (1));
            alertAnim.setDuration((int) (250));
            alertAnim.start();
            alertAnim1.setTarget(setview);
            alertAnim1.setPropertyName("scaleY");
            alertAnim1.setFloatValues((float) (1));
            alertAnim1.setDuration((int) (250));
            alertAnim1.start();
            dialogJanator.show();
          }
        } else {
          var di = new GhostWebMaterialDialog(CodeeditorActivity.this);
          di.setTitle("Error");
          di.setMessage("Not Image Set From Wallpaper ");
          di.setNeutralButton("ok", (p, d) -> {});

          di.show();
        }
        break;
      default:
        break;
    }
  }

  private void restoreState(@NonNull Bundle savedInstanceState) {
    int leftLine = savedInstanceState.getInt(EDITOR_LEFT_LINE_KEY, 0);
    int leftColumn = savedInstanceState.getInt(EDITOR_LEFT_COLUMN_KEY, 0);
    int rightLine = savedInstanceState.getInt(EDITOR_RIGHT_LINE_KEY, 0);
    int rightColumn = savedInstanceState.getInt(EDITOR_RIGHT_COLUMN_KEY, 0);

    Content text = editor.getText();
    if (leftLine > text.getLineCount() || rightLine > text.getLineCount()) {
      return;
    }
    if (leftLine != rightLine && leftColumn != rightColumn) {
      editor.setSelectionRegion(leftLine, leftColumn, rightLine, rightColumn, true);
    } else {
      editor.setSelection(leftLine, leftColumn);
    }
  }

  @Override
  protected void onRestoreInstanceState(Bundle arg0) {
    super.onRestoreInstanceState(arg0);
    // TODO: Implement this method
    restoreState(arg0);
  }

  @Override
  public void onSaveInstanceState(@NonNull Bundle outState) {
    super.onSaveInstanceState(outState);

    Cursor cursor = editor.getCursor();
    outState.putInt(EDITOR_LEFT_LINE_KEY, cursor.getLeftLine());
    outState.putInt(EDITOR_LEFT_COLUMN_KEY, cursor.getLeftColumn());
    outState.putInt(EDITOR_RIGHT_LINE_KEY, cursor.getRightLine());
    outState.putInt(EDITOR_RIGHT_COLUMN_KEY, cursor.getRightColumn());
  }

  public void _coderuner() {
    editor.setColorScheme(new io.github.rosemoe.sora.widget.EditorColorScheme());

    if (shp.getString("path", "").contains(".html")) {
      _fab.show();
    } else {
      if (shp.getString("path", "").contains(".py")) {
        _fab.hide();
      } else {
        if (shp.getString("path", "").contains(".js")) {
          _fab.show();
        } else {
          if (shp.getString("path", "").contains(".css")) {
            _fab.hide();
          } else {

          }
        }
      }
    }
  }

  public void _managerpanel(final View _view) {
    pvr =
        new PowerMenu.Builder(CodeeditorActivity.this)
            .addItem(new PowerMenuItem("جستجو", false, R.drawable.textsearch))
            .addItem(new PowerMenuItem("رنگ", false, R.drawable.color))
            .addItem(new PowerMenuItem("Log cat", false, R.drawable.codeformat))
            .addItem(new PowerMenuItem("ذخیره", false, R.drawable.save))
            .addItem(new PowerMenuItem("ذخیره همه", false, R.drawable.setsavefileall))
            .build();
    pvr.setSelectedMenuColor(0xFFFDA893);
    pvr.setIconPadding(8);
    pvr.setIconSize(30);
    pvr.setAutoDismiss(true);
    pvr.showAsDropDown(_view);
    pvr.setAnimation(MenuAnimation.ELASTIC_CENTER);
    pvr.setMenuRadius(20f);
    pvr.setSelectedEffect(true);
    pvr.setShowBackground(false);
    pvr.setDividerHeight(2);
    pvr.setTextSize(14);
    if (ru.contains("rup")) {
      pvr.setIconColor((int) 0xFFFDA893);
      pvr.setDivider(new ColorDrawable(0xFFEEEEEE));
      pvr.setMenuColor(0xFF2B2121);
      pvr.setTextColor(0xFFEEEEEE);
    } else {
      if (imap.containsKey("ImageColor")) {
        pvr.setIconColor((int) Color.parseColor(imap.get("ImageColor").toString()));
        pvr.setDivider(new ColorDrawable(Color.parseColor(imap.get("ImageColor").toString())));
      } else {
        pvr.setDivider(new ColorDrawable(0xFFEEEEEE));
        pvr.setIconColor((int) 0xFFFDA893);
      }
      if (imap.containsKey("MenuBackground")) {
        pvr.setMenuColor(Color.parseColor(imap.get("MenuBackground").toString()));
      } else {
        pvr.setMenuColor(0xFF2B2121);
      }
      if (imap.containsKey("TEXT_NORMAL")) {
        pvr.setTextColor(Color.parseColor(imap.get("TEXT_NORMAL").toString()));
      } else {
        pvr.setTextColor(0xFFEEEEEE);
      }
    }
    pvr.setOnMenuItemClickListener(
        new OnMenuItemClickListener<PowerMenuItem>() {
          @Override
          public void onItemClick(int position, PowerMenuItem item) {
            switch (position) {
              case 0:
                {
                  EditorSearcherT.show(editor, FrameLayout01);
                  break;
                }

              case 1:
                {
                  ColorPickerDialogBuilder.with(CodeeditorActivity.this)
                      .setTitle("لطفا رنگ را انتخاب کنید")
                      // .initialColor(getColor(R.color.Ninja))
                      .wheelType(ColorPickerView.WHEEL_TYPE.FLOWER)
                      .showColorPreview(true)
                      .showColorEdit(true)
                      .density(22)
                      .showColorEdit(true)
                      .setOnColorSelectedListener(
                          new OnColorSelectedListener() {
                            @Override
                            public void onColorSelected(int selectedColor) {}
                          })
                      .setPositiveButton(
                          "انتخاب",
                          new ColorPickerClickListener() {
                            @Override
                            public void onClick(
                                DialogInterface dialog, int selectedColor, Integer[] allColors) {

                              String rgs = Integer.toHexString(selectedColor);

                              //	SketchwareUtil.CustomToast(getApplicationContext(), "رنگ کپی شد" +
                              // rgs, 0xFFFFFFFF, 15, 0xFF1F1B1C, 15, SketchwareUtil.CENTER);
                              try {

                                _sysba("#".concat(rgs.replace("#ff", "#")));
                              } catch (Exception exception) {
                                exception.printStackTrace();
                              }
                            }
                          })
                      .setNegativeButton(
                          "لغو",
                          new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {

                              SketchwareUtil.showMessage(getApplicationContext(), "لغو شد");
                            }
                          })
                      .build()
                      .show();

                  break;
                }
              case 2:
                {
                  var sheet = new LogCatBottomSheet(CodeeditorActivity.this);
                  sheet.run();
                  break;
                }
              case 3:
                {
                  if (editor.getText().toString().isEmpty()) {
                    SketchwareUtil.showMessage(
                        getApplicationContext(), "خطا متن خالی نمیشود ذخیره شود");
                  } else {
                    try {
                      if (shp.contains("pos_path")) {
                        if (!shp.getString("pos_path", "").equals("")) {
                          FileUtil.writeFile(
                              shp.getString("pos_path", ""), editor.getText().toString());
                          SketchwareUtil.showMessage(getApplicationContext(), "File saved");
                        } else {
                          SketchwareUtil.showMessage(
                              getApplicationContext(), "Error not File saved!");
                        }
                      }
                    } catch (Exception e) {
                      SketchwareUtil.showMessage(getApplicationContext(), "Error not File saved!");
                    }
                  }
                  break;
                }
              case 4:
                {
                  _AllSaveFile(_coordinator);
                  break;
                }
            }
          }
        });
  }

  public void _sttingpp() {
    //// get word app for my app lol
    if (line.getString("getline", "").equals("true")) {
      editor.setPinLineNumber(true);
    } else {
      if (line.getString("getline", "").equals("false")) {
        editor.setPinLineNumber(false);
      } else {

      }
    }
  }

  public void _Ripple_Drawable(final View _view, final String _c) {
    android.content.res.ColorStateList clr =
        new android.content.res.ColorStateList(
            new int[][] {new int[] {}}, new int[] {Color.parseColor(_c)});
    android.graphics.drawable.RippleDrawable ripdr =
        new android.graphics.drawable.RippleDrawable(clr, null, null);
    _view.setBackground(ripdr);
  }

  public void _codeEditor(final String _path) {
    Ninja.coder.Ghostemane.code.EditorManagerTask.EditorRoaderFile.RuningTask(
        editor, _fab, _path, proanjctor);
  }

  public void _close_tab(final double _position, final ArrayList<HashMap<String, Object>> _data) {
    if (FileUtil.isExistFile(_data.get((int) _position).get("path").toString())) {
      _data.remove((int) (_position));
      if (_data.isEmpty()) {
        editor.setText("");
        _data.clear();
        shp.edit().remove("pos_path").apply();
        shp.edit().remove("path").apply();
        n = 0;
        finish();
      } else {
        if ((_position == 0) && (_data.size() > 1)) {
          shp.edit()
              .putString("pos_path", _data.get((int) _position + 1).get("path").toString())
              .apply();
          shp.edit().putString("positionTabs", String.valueOf((long) (_position + 1))).apply();
          _codeEditor(_data.get((int) _position + 1).get("path").toString());
        } else {
          if ((_position == 0) && (_data.size() == 1)) {
            shp.edit().putString("pos_path", _data.get((int) 0).get("path").toString()).apply();
            _codeEditor(_data.get((int) 0).get("path").toString());
            editor.setText("");
          } else {
            shp.edit()
                .putString("pos_path", _data.get((int) _position - 1).get("path").toString())
                .apply();
            shp.edit().putString("positionTabs", String.valueOf((long) (_position - 1))).apply();
            _codeEditor(_data.get((int) _position - 1).get("path").toString());
          }
        }
      }
      shp.edit().putString("path", new Gson().toJson(_data)).apply();
    } else {
      _data.remove((int) (_position));
      if (_data.isEmpty()) {
        _data.clear();
        shp.edit().remove("pos_path").apply();
        shp.edit().remove("path").apply();
        finish();
      } else {
        if ((_position == 0) && (_data.size() > 1)) {
          shp.edit()
              .putString("pos_path", _data.get((int) _position + 1).get("path").toString())
              .apply();
          shp.edit().putString("positionTabs", String.valueOf((long) (_position + 1))).apply();
        } else {
          if ((_position == 0) && (_data.size() == 1)) {
            shp.edit().putString("pos_path", _data.get((int) 0).get("path").toString()).apply();
          } else {
            shp.edit()
                .putString("pos_path", _data.get((int) _position - 1).get("path").toString())
                .apply();
            shp.edit().putString("positionTabs", String.valueOf((long) (_position - 1))).apply();
          }
        }
      }
      shp.edit().putString("path", new Gson().toJson(_data)).apply();
    }
  }

  public void _Anim(final View _codeEditor) {
    android.view.animation.Animation animation = new android.view.animation.AlphaAnimation(0, 1);
    animation.setDuration(300);
    _codeEditor.setAnimation(animation);
  }

  public void _Bounce(final View _view) {
    AnimUtils.Bounce(_view);
  }

  public void _Anim01(final View _view) {
    AnimUtils.Amin01(_view);
  }

  public void _Animwork(final View _view) {
    AnimUtils.Worker(_view);
  }

  public void _Anim04(final View _view) {
    AnimUtils.Amin04(_view, this);
  }

  public void _viber() {
    if (getvb.getString("HsiGamer", "").equals("true")) {
      vb.vibrate((long) (59));
    } else {
      if (getvb.getString("HsiGamer", "").equals("false")) {

      } else {

      }
    }
  }

  public void _clickAnimation(final View _view) {
    ScaleAnimation fade_in =
        new ScaleAnimation(
            0.9f, 1f, 0.9f, 1f, Animation.RELATIVE_TO_SELF, 0.5f, Animation.RELATIVE_TO_SELF, 0.7f);
    fade_in.setDuration(300);
    fade_in.setFillAfter(true);
    _view.startAnimation(fade_in);
  }

  public void _EditorSummery() {

    if (re.getString("f380", "").equals("true")) {
      editor.setNonPrintablePaintingFlags(CodeEditor.FLAG_DRAW_LINE_SEPARATOR);
    }
  }

  public void _poz() {
    if (shp.contains("path")) {
      if (!shp.getString("path", "").equals("")) {
        tabs_listmap =
            new Gson()
                .fromJson(
                    shp.getString("path", ""),
                    new TypeToken<ArrayList<HashMap<String, Object>>>() {}.getType());
        recyclerview1.setAdapter(new Recyclerview1Adapter(tabs_listmap));
        recyclerview1.setLayoutManager(
            new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false));
      }
    }
    if (FileUtil.isExistFile(shp.getString("pos_path", ""))) {
      _codeEditor(shp.getString("pos_path", ""));
      ((LinearLayoutManager) recyclerview1.getLayoutManager())
          .scrollToPositionWithOffset(
              (int) Math.floor(Double.parseDouble(shp.getString("positionTabs", ""))), (int) 0);
    }
  }

  public void _sysba(final String _input) {
    io.github.rosemoe.sora.widget.SymbolChannel channel = editor.createNewSymbolChannel();
    channel.insertSymbol(_input, (int) _input.length());
  }

  public void _tabsize(final double _tab) {
    editor.setTabWidth((int) _tab);
  }

  public void _editorsetfontfromfile(final String _files) {
    editor.setTypefaceText(Typeface.createFromFile(new File(_files)));
    editor.setTypefaceLineNumber(Typeface.createFromFile(new File(_files)));
  }

  private LiveviewebDialogFragmentActivity LiveviewebDialogFragmentActivityN;
  private FragmentManager LiveviewebDialogFragmentActivityFM;

  public void _fragmentdatapost() {
    LiveviewebDialogFragmentActivityN = new LiveviewebDialogFragmentActivity();
    LiveviewebDialogFragmentActivityN.show(getSupportFragmentManager(), "1");
  }

  public void _symbloinit() {
    if (!FileUtil.isExistFile("/storage/emulated/0/GhostWebIDE/Symbols/symbol.json")) {
      try {
        java.io.InputStream inputstream5 = getAssets().open("symbol.json");
        staticSymbiolPiare =
            new Gson()
                .fromJson(
                    SketchwareUtil.copyFromInputStream(inputstream5),
                    new TypeToken<ArrayList<HashMap<String, Object>>>() {}.getType());
      } catch (Exception e) {
        SketchwareUtil.showMessage(getApplicationContext(), e.toString());
      }
    } else {
      try {
        staticSymbiolPiare =
            new Gson()
                .fromJson(
                    FileUtil.readFile("/storage/emulated/0/GhostWebIDE/Symbols/symbol.json"),
                    new TypeToken<ArrayList<HashMap<String, Object>>>() {}.getType());
      } catch (Exception e) {
        SketchwareUtil.showMessage(getApplicationContext(), e.toString());
      }
    }
    SyspiarAdapter syspiarAdapter =
        new SyspiarAdapter(
            staticSymbiolPiare,
            new SyspiarAdapter.OnTabView() {
              @Override
              public void TAB(String tab) {
                _sysba(tab);
              }

              @Override
              public void POST(String post) {
                _sysba(post);
              }
            });

    syspiar.setAdapter(syspiarAdapter);
    syspiar.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false));
  }

  public void _closeall() {
    tabs_listmap.clear();
    shp.edit().remove("pos_path").apply();
    shp.edit().remove("positionTabs").apply();
    recyclerview1.getAdapter().notifyDataSetChanged();
    shp.edit().putString("path", new Gson().toJson(tabs_listmap)).apply();
    FileUtil.deleteFile("/storage/emulated/0/GhostWebIDE/ninjacoder/openFile.json");
    finish();
  }

  public void _closeother() {
    tabs_listmap.clear();
    {
      HashMap<String, Object> _item = new HashMap<>();
      _item.put("path", shp.getString("pos_path", ""));
      tabs_listmap.add(_item);
    }

    shp.edit().putString("positionTabs", "0").apply();
    FileUtil.writeFile(
        "/storage/emulated/0/GhostWebIDE/ninjacoder/openFile.json", shp.getString("pos_path", ""));
    shp.edit().putString("path", new Gson().toJson(tabs_listmap)).apply();
    recyclerview1.getAdapter().notifyDataSetChanged();
  }

  public void _powerMenuLisner(
      final View _v, final ArrayList<HashMap<String, Object>> _map, final double _pos) {
    itemPosRemoved = _pos;
    mmenuitempos =
        new PowerMenu.Builder(CodeeditorActivity.this)
            .addItem(new PowerMenuItem("close this"))
            .addItem(new PowerMenuItem("close other"))
            .addItem(new PowerMenuItem("close all"))
            .build();
    mmenuitempos.setAnimation(MenuAnimation.ELASTIC_CENTER);
    mmenuitempos.setMenuRadius(30f);
    mmenuitempos.setShowBackground(false);
    mmenuitempos.setSelectedEffect(true);
    mmenuitempos.setAutoDismiss(false);
    mmenuitempos.showAsDropDown(_v);

    mmenuitempos.setTextTypeface(
        Typeface.create(
            Typeface.createFromAsset(getAssets(), "fonts/ghostfont.ttf"), Typeface.NORMAL));
    if (ru.contains("rup")) {
      mmenuitempos.setMenuColor(0xFF2B2121);
      mmenuitempos.setTextColor(0xFFEEEEEE);
      mmenuitempos.setDivider(new ColorDrawable(0xFFEEEEEE));
    } else {
      if (imap.containsKey("MenuBackground")) {
        mmenuitempos.setMenuColor(Color.parseColor(imap.get("MenuBackground").toString()));
      } else {
        mmenuitempos.setMenuColor(0xFF2B2121);
      }
      if (imap.containsKey("ImageColor")) {
        mmenuitempos.setDivider(
            new ColorDrawable(Color.parseColor(imap.get("ImageColor").toString())));
      } else {
        mmenuitempos.setDivider(new ColorDrawable(0xFFEEEEEE));
      }
      if (imap.containsKey("TEXT_NORMAL")) {
        mmenuitempos.setTextColor(Color.parseColor(imap.get("TEXT_NORMAL").toString()));
      } else {
        mmenuitempos.setTextColor(0xFFEEEEEE);
      }
    }
    mmenuitempos.setOnMenuItemClickListener(
        new OnMenuItemClickListener<PowerMenuItem>() {
          @Override
          public void onItemClick(int position, PowerMenuItem item) {
            switch ((int) position) {
              case ((int) 0):
                {
                  _close_tab(_pos, _map);
                  recyclerview1.getAdapter().notifyDataSetChanged();
                  click2var = 0;
                  n = 0;
                  _distreeview();
                  mmenuitempos.dismiss();
                  break;
                }
              case ((int) 1):
                {
                  _closeother();
                  recyclerview1.getAdapter().notifyItemRemoved((int) itemPosRemoved);
                  click2var = 0;
                  _distreeview();
                  mmenuitempos.dismiss();
                  break;
                }
              case ((int) 2):
                {
                  _closeall();
                  recyclerview1.getAdapter().notifyItemRemoved((int) itemPosRemoved);
                  click2var = 0;
                  _distreeview();
                  mmenuitempos.dismiss();
                  break;
                }
            }
          }
        });
  }

  public static class DoubleClick implements View.OnClickListener {

    private long DOUBLE_CLICK_INTERVAL;
    private final Handler mHandler = new Handler();
    private final DoubleClickListener doubleClickListener;

    private int clicks;
    private boolean isBusy = false;

    public DoubleClick(final DoubleClickListener doubleClickListener) {
      this(doubleClickListener, 200L);
      DOUBLE_CLICK_INTERVAL = 200L; // default time to wait the second click.
    }

    public DoubleClick(
        final DoubleClickListener doubleClickListener, final long DOUBLE_CLICK_INTERVAL) {
      this.doubleClickListener = doubleClickListener;
      this.DOUBLE_CLICK_INTERVAL =
          DOUBLE_CLICK_INTERVAL; // developer specified time to wait the second click.
    }

    @Override
    public void onClick(final View view) {

      if (!isBusy) {
        //  Prevent multiple click in this short time
        isBusy = true;

        // Increase clicks count
        clicks++;

        mHandler.postDelayed(
            new Runnable() {
              public final void run() {

                if (clicks >= 2) { // Double tap.
                  doubleClickListener.onDoubleClick(view);
                }

                if (clicks == 1) { // Single tap
                  doubleClickListener.onSingleClick(view);
                }

                // we need to  restore clicks count
                clicks = 0;
              }
            },
            DOUBLE_CLICK_INTERVAL);
        isBusy = false;
      }
    }
  }

  public static interface DoubleClickListener {
    void onSingleClick(final View view);

    void onDoubleClick(final View view);
  }

  public void _distreeview() {
    treeviewmap.clear();
    String _path_ = shp.getString("pos_path", "");

    _path_ = _path_.replaceFirst("/", "");
    String[] _path_list_ = _path_.split("/");
    for (int _i_ = 0; _i_ < _path_list_.length; _i_++) {
      {
        HashMap<String, Object> _item = new HashMap<>();
        _item.put("mo", _path_list_[_i_]);
        treeviewmap.add(_item);
      }
    }

    dir.setAdapter(new DirAdapter(treeviewmap));
    dir.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false));
    dir.smoothScrollToPosition((int) treeviewmap.size());
  }

  public void _winterFileinpath(final String _path, final String _dir) {
    NinjaMacroFileUtil.createFile(
        _path,
        _dir,
        new NinjaMacroFileUtil.OnFileOperationListener() {
          @Override
          public void onSuccess(String content) {
            // محتوای فایل با موفقیت خوانده شد
            Toast.makeText(getApplicationContext(), content, 2).show();
          }

          @Override
          public void onError(String e) {
            // خطایی در هنگام خواندن فایل رخ داده است
            Toast.makeText(getApplicationContext(), e, 2).show();
          }
        });
  }

  public void _save() {
    if (shp.contains("pos_path")) {
      if (!shp.getString("pos_path", "").equals("")) {
        _winterFileinpath(shp.getString("pos_path", ""), editor.getText().toString());
      } else {

      }
    }
  }

  public void _fabCl() {
    try {
      try {
        if (shp.contains("pos_path")) {
          if (!shp.getString("pos_path", "").equals("")) {
            FileUtil.writeFile(shp.getString("pos_path", ""), editor.getText().toString());
          } else {

          }
        }
      } catch (Exception e) {
        SketchwareUtil.showMessage(getApplicationContext(), e.toString());
      }
      if (shp.getString("pos_path", "").contains(".html")) {
        if (moalaqfragment.contains("mpost")) {
          _fragmentdatapost();
        } else {

          htmlrus.setClass(getApplicationContext(), HtmlrunerActivity.class);
          htmlrus.putExtra("run", shp.getString("pos_path", ""));
          htmlrus.putExtra("runs", Uri.parse(shp.getString("pos_path", "")).getLastPathSegment());
          File file = new File(shp.getString("pos_path", ""));
          if (file.exists()) {
            String phpz = file.getParent();
            htmlrus.putExtra("root", phpz);
            Toast.makeText(getApplicationContext(), phpz, 2).show();
          }

          startActivity(htmlrus);
        }
      } else {
        if (shp.getString("pos_path", "").contains(".json")) {
          jsonview.setClass(getApplicationContext(), JsonviewerActivity.class);
          jsonview.putExtra("g", editor.getText().toString());
          startActivity(jsonview);
        } else {
          if (shp.getString("pos_path", "").contains(".js")) {
            getmd.setClass(getApplicationContext(), JsrunerActivity.class);
            getmd.putExtra("sendCode", editor.getText().toString().trim());
            startActivity(getmd);
          } else {
            if (shp.getString("pos_path", "").contains(".sh")) {
              res.setClass(getApplicationContext(), ShellcodeActivity.class);
              res.putExtra("sh", editor.getText().toString().trim());
              startActivity(res);
            } else {
              if (shp.getString("pos_path", "").contains(".svg")) {
                htmlrus.setClass(getApplicationContext(), HtmlrunerActivity.class);
                htmlrus.putExtra("run", shp.getString("pos_path", ""));
                startActivity(htmlrus);
              } else {
                if (shp.getString("pos_path", "").contains(".md")) {
                  getmd.setClass(getApplicationContext(), MdcodeviewActivity.class);
                  getmd.putExtra("v", shp.getString("pos_path", ""));
                  startActivity(getmd);
                } else {
                  if (shp.getString("pos_path", "").contains(".cpp")) {
                    getmd.setClass(getApplicationContext(), PytestActivity.class);
                    getmd.putExtra("pathCpp", shp.getString("pos_path", ""));
                    startActivity(getmd);
                  } else {
                    if (shp.getString("pos_path", "").contains(".py")) {
                      getmd.setClass(getApplicationContext(), PytestActivity.class);
                      getmd.putExtra("path", shp.getString("pos_path", ""));
                      startActivity(getmd);
                    } else {
                      if (shp.getString("pos_path", "").contains(".g4")) {
                        _g4compiler();

                      } else {
                        if (shp.getString("pos_path", "").contains(".php")) {
                          getmd.setClass(getApplicationContext(), PhpActivity.class);
                          getmd.putExtra("phpcode", shp.getString("pos_path", ""));
                          startActivity(getmd);
                        } else if (shp.getString("pos_path", "").contains(".scss")
                            || shp.getString("pos_path", "").contains(".sass")) {
                          SassForAndroid.run(
                              CodeeditorActivity.this,
                              shp.getString("pos_path", ""),
                              shp.getString("pos_path", ""));

                        } else if (shp.getString("pos_path", "").contains(".java")) {
                          JavaCompilerBeta.run(
                              CodeeditorActivity.this, editor.getText().toString());
                        }
                      }
                    }
                  }
                }
              }
            }
          }
        }
      }
    } catch (Exception ex) {
      final AlertDialog.Builder malterDialog = new AlertDialog.Builder(CodeeditorActivity.this);
      StringBuffer sb = new StringBuffer(500);
      StackTraceElement[] st = ex.getStackTrace();
      sb.append(ex.getClass().getName() + ": " + ex.getMessage() + "\n");
      for (int iii = 0; iii < st.length; iii++) {
        sb.append("\t at ⟶" + st[iii].toString() + "\n");
      }

      final LinearLayout lin = new LinearLayout(getApplicationContext());

      lin.setPadding(8, 8, 8, 8);

      final TextView txt = new TextView(getApplicationContext());

      txt.setPadding(8, 8, 8, 8);

      txt.setTextIsSelectable(true);

      lin.addView(txt);

      txt.setText(sb.toString());

      {
        SpannableString spannableString = new SpannableString(txt.getText().toString());
        Matcher matcher = Pattern.compile("(^(.*)|(?<=:)\\d+)").matcher(txt.getText().toString());

        while (matcher.find()) {

          spannableString.setSpan(
              new ForegroundColorSpan(0xFFB71C1C),
              matcher.start(),
              matcher.end(),
              Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
          spannableString.setSpan(new StyleSpan(1), matcher.start(), matcher.end(), 33);
        }

        txt.setText(spannableString);
      }
      malterDialog.setView(lin);
      malterDialog.show();
    }
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
        if ((_v instanceof CodeEditor)) {
          if (((CodeEditor) _v) != null) {
            try {
              if (shp.contains("pos_path")) {
                if (!shp.getString("pos_path", "").equals("")) {
                  FileUtil.writeFile(
                      shp.getString("pos_path", ""), ((CodeEditor) _v).getText().toString());
                  if (imap.containsKey("MenuBackground")) {
                    //	alert.setAlertBackgroundColor(Color.parseColor(imap.get("MenuBackground").toString()));
                  } else {
                    //	alert.setAlertBackgroundColor(Color.BLUE);
                  }
                  if (imap.containsKey("TabTextColor")) {
                    //	alert.getText().setTextColor(Color.parseColor(imap.get("TabTextColor").toString()));
                    // alert.getTitle().setTextColor(Color.parseColor(imap.get("TabTextColor").toString()));
                  } else {
                    // alert.getTitle().setTextColor(Color.YELLOW);
                    //	alert.getText().setTextColor(Color.RED);
                  }
                  //	alert.getTitle().setTypeface(Typeface.createFromAsset(getAssets(),"fonts/ghostfont.ttf"), 0);
                  //	alert.getText().setTypeface(Typeface.createFromAsset(getAssets(),"fonts/ghostfont.ttf"), 0);
                } else {
                  SketchwareUtil.showMessage(getApplicationContext(), "Error not File saved!");
                }
              }
            } catch (Exception e) {
              SketchwareUtil.showMessage(getApplicationContext(), "Error not File saved!");
            }
          }

        } else {
          throw new RuntimeException("CanYouAdd CodeEditor??");
        }
      }
    } catch (Exception e) {
      e.printStackTrace();
    }
  }

  public void _g4compiler() {
    final com.google.android.material.bottomsheet.BottomSheetDialog bottomSheetDialog =
        new com.google.android.material.bottomsheet.BottomSheetDialog(CodeeditorActivity.this);

    View bottomSheetView;
    bottomSheetView = getLayoutInflater().inflate(R.layout.antcomp8ler, null);
    bottomSheetDialog.setContentView(bottomSheetView);

    EditText edpath = (EditText) bottomSheetView.findViewById(R.id.edpath);
    EditText etpa = (EditText) bottomSheetView.findViewById(R.id.etpa);
    Button btnrun = (Button) bottomSheetView.findViewById(R.id.btnrun);
    TextView tvlog = (TextView) bottomSheetView.findViewById(R.id.tvlog);
    btnrun.setOnClickListener(
        new View.OnClickListener() {
          public void onClick(View v) {

            G4Compiler.compile(
                shp.getString("pos_path", ""),
                edpath.getText().toString(),
                etpa.getText().toString());
            bottomSheetDialog.dismiss();
          }
        });
    bottomSheetDialog.show();
  }

  public void _vi() {

    effect = new WallpaperParallaxEffect(this);
    effect.setCallback(
        new WallpaperParallaxEffect.Callback() {
          @Override
          public void onOffsetsChanged(int offsetX, int offsetY, float angle) {
            float progress = 1.0f;
            _coordinator.setTranslationX(offsetX * progress);
            _coordinator.setTranslationY(offsetY * progress);
          }
        });

    effect.setEnabled(thememanagersoft.contains("effect") ? true : false);
  }

  public class Recyclerview1Adapter extends RecyclerView.Adapter<Recyclerview1Adapter.ViewHolder> {

    ArrayList<HashMap<String, Object>> _data;

    public Recyclerview1Adapter(ArrayList<HashMap<String, Object>> _arr) {
      _data = _arr;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
      LayoutInflater _inflater = getLayoutInflater();
      View _v = _inflater.inflate(R.layout.newlist, null);
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
      final LinearLayout linear5 = _view.findViewById(R.id.linear5);
      final LinearLayout selector = _view.findViewById(R.id.selector);
      final ImageView imageview2 = _view.findViewById(R.id.imageview2);
      final TextView textview1 = _view.findViewById(R.id.textview1);

      pos10 = _position;
      RecyclerView.LayoutParams _lp =
          new RecyclerView.LayoutParams(
              ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT);
      _view.setLayoutParams(_lp);
      if (tabimageview.contains("mtab")) {
        imageview2.setVisibility(View.GONE);
      }

      if (_data.isEmpty()) {

      } else {
        if (ru.contains("rup")) {
          android.graphics.drawable.GradientDrawable SketchUi30 =
              new android.graphics.drawable.GradientDrawable();
          int d = (int) getApplicationContext().getResources().getDisplayMetrics().density;
          SketchUi30.setColor(0xFFFDA893);
          SketchUi30.setCornerRadii(
              new float[] {d * 360, d * 360, d * 360, d * 360, d * 0, d * 0, d * 0, d * 0});
          selector.setElevation(d * 4);
          selector.setBackground(SketchUi30);
        } else {
          if (imap.containsKey("TabBack")) {
            android.graphics.drawable.GradientDrawable SketchUi =
                new android.graphics.drawable.GradientDrawable();
            int d = (int) getApplicationContext().getResources().getDisplayMetrics().density;
            SketchUi.setColor(Color.parseColor(imap.get("TabBack").toString()));
            SketchUi.setCornerRadii(
                new float[] {d * 360, d * 360, d * 360, d * 360, d * 0, d * 0, d * 0, d * 0});
            selector.setElevation(d * 4);
            selector.setBackground(SketchUi);
          } else {
            android.graphics.drawable.GradientDrawable SketchUi =
                new android.graphics.drawable.GradientDrawable();
            int d = (int) getApplicationContext().getResources().getDisplayMetrics().density;
            SketchUi.setColor(Color.parseColor("#ffd1aeff"));
            SketchUi.setCornerRadii(
                new float[] {d * 360, d * 360, d * 360, d * 360, d * 0, d * 0, d * 0, d * 0});
            selector.setElevation(d * 4);
            selector.setBackground(SketchUi);
          }
        }
        datas =
            Uri.parse(_data.get((int) _position).get("path").toString())
                .getLastPathSegment()
                .toLowerCase();
        textview1.setText(
            Uri.parse(_data.get((int) _position).get("path").toString())
                .getLastPathSegment()
                .toLowerCase());

        if (FileUtil.isDirectory(_data.get((int) _position).get("path").toString())) {

          imageview2.setImageResource(R.drawable.file);
        } else if (FileUtil.isExistFile(_data.get((int) _position).get("path").toString())) {

        } else {
          textview1.setText(
              "FileNotFound*"
                  .concat(
                      Uri.parse(_data.get((int) _position).get("path").toString())
                          .getLastPathSegment())
                  .toLowerCase());
        }
        if (_data
            .get((int) _position)
            .get("path")
            .toString()
            .equals(shp.getString("pos_path", ""))) {

          selector.setVisibility(View.VISIBLE);
          _distreeview();
          _clickAnimation(editor);
          n = 0;
          if (imap.containsKey("TabTextColor")) {
            textview1.setTextColor(Color.parseColor(imap.get("TabTextColor").toString()));
          } else {
            textview1.setTextColor(Color.parseColor("#f4ffc6bc"));
          }
          if (ru.contains("rup")) {
            textview1.setTextColor(0xFFFDA893);
          } else {
            if (imap.containsKey("TabTextColor")) {
              textview1.setTextColor(Color.parseColor(imap.get("TabTextColor").toString()));
            } else {
              textview1.setTextColor(Color.parseColor("#f4ffc6bc"));
            }
          }
        } else {
          selector.setVisibility(View.GONE);
          n = 0;
          _distreeview();
          _clickAnimation(editor);
          if (imap.containsKey("DisplayTextColorTab")) {
            textview1.setTextColor(Color.parseColor(imap.get("DisplayTextColorTab").toString()));
          } else {
            textview1.setTextColor(Color.parseColor("#ffc9fff2"));
          }
          if (ru.contains("rup")) {
            textview1.setTextColor(0xFFEEEEEE);
          } else {
            if (imap.containsKey("DisplayTextColorTab")) {
              textview1.setTextColor(Color.parseColor(imap.get("DisplayTextColorTab").toString()));
            } else {
              textview1.setTextColor(Color.parseColor("#ffc9fff2"));
            }
          }
        }
        linear5.setOnClickListener(
            new DoubleClick(
                new DoubleClickListener() {
                  @Override
                  public void onSingleClick(View view) {

                    // Single tap here.
                    if (FileUtil.isExistFile(_data.get((int) _position).get("path").toString())) {
                      _codeEditor(_data.get((int) _position).get("path").toString());
                      shp.edit()
                          .putString("positionTabs", String.valueOf((long) (_position)))
                          .apply();
                      shp.edit()
                          .putString("pos_path", _data.get((int) _position).get("path").toString())
                          .apply();
                      _clickAnimation(editor);
                      _distreeview();
                      notifyDataSetChanged();
                      if (FileUtil.isExistFile(_data.get((int) _position).get("path").toString())) {
                        _codeEditor(_data.get((int) _position).get("path").toString());
                        if (_data
                            .get((int) _position)
                            .get("path")
                            .toString()
                            .equals(shp.getString("pos_path", ""))) {
                          if (imap.containsKey("TabTextColor")) {
                            textview1.setTextColor(
                                Color.parseColor(imap.get("TabTextColor").toString()));
                          } else {
                            textview1.setTextColor(Color.parseColor("#f4ffc6bc"));
                          }
                          if (ru.contains("rup")) {
                            textview1.setTextColor(0xFFFDA893);
                          } else {
                            if (imap.containsKey("TabTextColor")) {
                              textview1.setTextColor(
                                  Color.parseColor(imap.get("TabTextColor").toString()));
                            } else {
                              textview1.setTextColor(Color.parseColor("#f4ffc6bc"));
                            }
                          }
                          selector.setVisibility(View.VISIBLE);
                          n = 0;
                          _distreeview();
                        } else {
                          selector.setVisibility(View.GONE);
                          if (imap.containsKey("DisplayTextColorTab")) {
                            textview1.setTextColor(
                                Color.parseColor(imap.get("DisplayTextColorTab").toString()));
                          } else {
                            textview1.setTextColor(Color.parseColor("#ffc9fff2"));
                          }
                          if (ru.contains("rup")) {
                            textview1.setTextColor(0xFFEEEEEE);
                          } else {
                            if (imap.containsKey("DisplayTextColorTab")) {
                              textview1.setTextColor(
                                  Color.parseColor(imap.get("DisplayTextColorTab").toString()));
                            } else {
                              textview1.setTextColor(Color.parseColor("#ffc9fff2"));
                            }
                          }

                          _clickAnimation(editor);
                          _distreeview();
                          n = 0;
                        }
                      } else {
                        selector.setVisibility(View.GONE);
                      }
                    } else {

                      var di =
                          new com.google.android.material.dialog.MaterialAlertDialogBuilder(
                              CodeeditorActivity.this);

                      di.setTitle("File not Found!");
                      di.setCancelable(false);
                      di.setNeutralButton(
                          "Romved!",
                          (p, d) -> {
                            _close_tab(_position, _data);
                            _distreeview();
                            notifyItemRemoved(_position);
                          });
                      di.show();
                    }
                  }

                  @Override
                  public void onDoubleClick(View view) {

                    // Double tap here.
                    _powerMenuLisner(linear5, _data, _position);
                  }
                }));

        linear5.setOnLongClickListener(
            new View.OnLongClickListener() {
              @Override
              public boolean onLongClick(View _view) {
                _powerMenuLisner(linear5, _data, _position);
                return true;
              }
            });
        if (imap.containsKey("TabBack")) {
          _Ripple_Drawable(linear5, imap.get("TabBack").toString());
        } else {
          _Ripple_Drawable(linear5, "#ffd1aeff");
        }
        if (ru.contains("rup")) {
          _Ripple_Drawable(linear5, "#ffd1aeff");
        } else {
          if (imap.containsKey("TabBack")) {
            _Ripple_Drawable(linear5, imap.get("TabBack").toString());
          } else {
            _Ripple_Drawable(linear5, "#ffd1aeff");
          }
        }
        shp.edit().putString("path", new Gson().toJson(_data)).apply();
      }
      _Anim(selector);
      if (setfont.contains("mfont")) {
        if (!FileUtil.isFile(setfont.getString("mfont", ""))) {
          SketchwareUtil.showMessage(getApplicationContext(), "Custom Font Not Found");
          textview1.setTypeface(Typeface.createFromAsset(getAssets(), "fonts/ghostfont.ttf"), 0);
        } else {
          textview1.setTypeface(Typeface.createFromFile(new File(setfont.getString("mfont", ""))));
        }
      } else {
        textview1.setTypeface(Typeface.createFromAsset(getAssets(), "fonts/ghostfont.ttf"), 0);
      }
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
}
