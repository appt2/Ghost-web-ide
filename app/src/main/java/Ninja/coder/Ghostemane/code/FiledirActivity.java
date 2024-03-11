package Ninja.coder.Ghostemane.code;

import Ninja.coder.Ghostemane.code.FileHelper.FactoryModelProject;
import Ninja.coder.Ghostemane.code.component.FastScrollCompat.FastScrollerBuilder;
import Ninja.coder.Ghostemane.code.component.SwbData;
import android.content.pm.PackageInfo;
import android.content.pm.ApplicationInfo;
import android.Manifest;
import Ninja.coder.Ghostemane.code.marco.binder.*;
import android.animation.*;
import Ninja.coder.Ghostemane.code.project.ProjectManager;
import android.animation.ObjectAnimator;
import android.app.*;
import Ninja.coder.Ghostemane.code.databin.FileMaker;
import Ninja.coder.Ghostemane.code.interfaces.FileCallBack;
import Ninja.coder.Ghostemane.code.model.*;
import Ninja.coder.Ghostemane.code.RecyclerViewHelper.RecyclerViewHelper;
import android.app.Activity;
import android.content.*;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.PackageManager;
import android.content.res.*;
import android.graphics.*;
import android.graphics.drawable.*;
import android.media.*;
import android.media.MediaPlayer;
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
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.TextView;
import androidx.activity.*;
import androidx.annotation.*;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.widget.Toolbar;
import androidx.coordinatorlayout.widget.CoordinatorLayout;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.recyclerview.*;
import androidx.recyclerview.selection.*;
import androidx.recyclerview.widget.*;
import androidx.recyclerview.widget.RecyclerView;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;
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
import com.google.android.material.appbar.AppBarLayout;
import com.google.android.material.textfield.TextInputEditText;
import com.google.android.material.textfield.TextInputLayout;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
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
import io.github.rosemoe.sora.widget.*;
import java.security.*;
import com.github.angads25.filepicker.view.*;
import com.github.angads25.filepicker.model.*;
import com.github.angads25.filepicker.controller.*;
import com.github.angads25.filepicker.utils.*;
import com.google.android.material.floatingactionbutton.ExtendedFloatingActionButton;
import com.google.android.material.dialog.MaterialAlertDialogBuilder;
import com.zip4j.progress.ProgressMonitor;
import android.app.ProgressDialog;
import java.util.ArrayList;
import java.util.List;
import java.io.File;
import android.view.inputmethod.InputMethodManager;
import Ninja.coder.Ghostemane.code.marco.*;
import Ninja.coder.Ghostemane.code.layoutmanager.*;
import Ninja.coder.Ghostemane.code.GlideUtilApi.*;
import Ninja.coder.Ghostemane.code.comprasor.*;
import Ninja.coder.Ghostemane.code.CodeHelper.*;
import com.blankj.utilcode.util.*;
import com.google.android.material.color.*;
import Ninja.coder.Ghostemane.code.adapter.*;

public class FiledirActivity extends BaseCompat
    implements FileManagerAd.onClick, ToolbarListFileAdapter.CallBack {

  private Timer _timer = new Timer();
  public static String POSNINJACODERMAIN = "";
  protected SdCardUtil sd_stor;
  protected ProgressMonitor progress_m;
  protected ProgressDialog progressDialog;
  protected androidx.appcompat.app.AlertDialog maindialogPrfex;
  protected NavigationViewCompnet navs;
  protected static final String SHARED_PREFS_KEY = "scroll_position";
  protected static final String ITEM_POSITION_KEY = "item_position";
  protected SharedPreferences mSharedPreferences;
  protected androidx.recyclerview.widget.LinearLayoutManager linearLayoutManager;
  protected FastScrollerBuilder fast;
  private ExtendedFloatingActionButton fabAdd;
  protected FileEventUser user;
  private Toolbar _toolbar;
  private AppBarLayout _app_bar;
  private CoordinatorLayout _coordinator;
  private DrawerLayout _drawer;
  private double index = 0;
  public String Folder = "";
  private String Token = "";
  private String staticstring = "";
  private double gotoback = 0;
  private FileManagerAd fileListItem;
  private String CreateFolder = "";
  private String mainfile = "";
  private String GetTab = "";
  private String tab = "";
  private double n = 0;
  private double positionTabs = 0;

  private boolean Chack = false;

  private boolean isCopyAndMoved = false;

  private double conter = 0;
  private boolean isAllSet = false;
  private boolean staticStorage = false;

  private String version = "";
  private double post = 0;
  private String arm = "";
  private int newpos = 0;

  private String MColors = "";
  private int lastPos = 0;

  private List<String> list = new ArrayList<>();
  private List<String> folderList = new ArrayList<>();
  private List<String> fileList = new ArrayList<>();
  private ArrayList<HashMap<String, Object>> files = new ArrayList<>();

  private ArrayList<HashMap<String, Object>> newlistmap = new ArrayList<>();

  private ArrayList<HashMap<String, Object>> treeviewmap = new ArrayList<>();
  private ArrayList<String> pv = new ArrayList<>();
  private ArrayList<HashMap<String, Object>> test = new ArrayList<>();
  private ArrayList<String> copyOrMove = new ArrayList<>();
  private ArrayList<String> zip = new ArrayList<>();
  private ArrayList<String> listStringShareFile = new ArrayList<>();

  private ArrayList<HashMap<String, Object>> upfile = new ArrayList<>();
  private ArrayList<HashMap<String, Object>> map = new ArrayList<>();
  private ArrayList<String> str1 = new ArrayList<>();
  private ArrayList<String> str2 = new ArrayList<>();
  private ArrayList<HashMap<String, Object>> projectdata = new ArrayList<>();
  private ArrayList<Double> cache = new ArrayList<>();

  private PraramnetLayoutNinja paramentLayout_fileDir;
  private LinearLayout CensractorListView1;

  private SwipeRefreshLayout swiperefreshlayout1;

  private RecyclerView recyclerview1;
  private EmptyRecyclerView recyclerview2;
  private Intent myketint = new Intent();
  private Intent intgetTheme = new Intent();
  private Intent intentgetSettings = new Intent();
  private SharedPreferences shp;
  private Intent activitiy = new Intent();
  private ProgressDialog progressDilaog;
  private ProgressDialog unzip;
  private ToolbarListFileAdapter adps;
  private SharedPreferences soglo;
  private SharedPreferences np;
  private ProgressDialog mprodialog;
  private SharedPreferences dismoveFile;
  
  private ProgressDialog prodel;
  private ProgressDialog proveg;
  private Intent getabout = new Intent();
  private Intent void10 = new Intent();
  private SharedPreferences sd;
  private SharedPreferences delfile;

  private ProgressDialog copydir;
  private ProgressDialog copypath;
  private MediaPlayer m;
  private TimerTask vvv12;
  private Intent getJavaLayoutManager = new Intent();
  
  private SharedPreferences zipCuntishen;

  private Intent govirwFilm = new Intent();
  private TimerTask ask;

  private SharedPreferences war;
  private RequestNetwork AppUpdeat;
  private RequestNetwork.RequestListener _AppUpdeat_request_listener;
  private Intent finalintentpostfont = new Intent();
  private Intent intentgetLogCat = new Intent();
  private SharedPreferences tmp;
  private PowerMenu mmenuitempos;
  private PowerMenu mmenuVector;
  private ObjectAnimator one = new ObjectAnimator();
  private ObjectAnimator thow = new ObjectAnimator();
  private TimerTask MtimerPost;
  private ObjectAnimator mobjectOnTaskStart = new ObjectAnimator();
  private ObjectAnimator MobjectTaskEnd = new ObjectAnimator();
  private Intent musicShow = new Intent();
  private SharedPreferences base;
  private SharedPreferences save_path;
  private SharedPreferences materialYou;
  private SharedPreferences book;
  private ArrayList<HashMap<String, Object>> a = new ArrayList<>();
  private HashMap<String, Object> mapz32 = new HashMap<>();

  @Override
  protected void onCreate(Bundle _savedInstanceState) {
    super.onCreate(_savedInstanceState);
    setContentView(R.layout.filedir);
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

  private void initialize(Bundle _savedInstanceState) {
    navs = findViewById(R.id.navs);
    startService(new Intent(getApplicationContext(), MediaListenerService.class));
    // from protected NavigationViewCompnet navs;
    linearLayoutManager = new androidx.recyclerview.widget.LinearLayoutManager(this);
    mSharedPreferences = getSharedPreferences(SHARED_PREFS_KEY, Context.MODE_PRIVATE);
    _app_bar = findViewById(R.id._app_bar);
    _coordinator = findViewById(R.id._coordinator);
    _toolbar = findViewById(R.id._toolbar);
    setSupportActionBar(_toolbar);
    getSupportActionBar().setDisplayHomeAsUpEnabled(true);
    getSupportActionBar().setHomeButtonEnabled(true);
    _drawer = findViewById(R.id._drawer);
    ActionBarDrawerToggle _toggle =
        new ActionBarDrawerToggle(
            FiledirActivity.this, _drawer, _toolbar, R.string.app_name, R.string.app_name);
    _drawer.addDrawerListener(_toggle);
    _toggle.syncState();

    paramentLayout_fileDir = findViewById(R.id.paramentLayout_fileDir);
    CensractorListView1 = findViewById(R.id.CensractorListView1);

    swiperefreshlayout1 = findViewById(R.id.swiperefreshlayout1);

    recyclerview1 = findViewById(R.id.recyclerview1);
    recyclerview2 = findViewById(R.id.recyclerview2);
    fileListItem = new FileManagerAd(files, FiledirActivity.this, this);
    shp = getSharedPreferences("shp", Activity.MODE_PRIVATE);
    soglo = getSharedPreferences("soglo", Activity.MODE_PRIVATE);
    np = getSharedPreferences("np", Activity.MODE_PRIVATE);
    dismoveFile = getSharedPreferences("DismoveFile", Activity.MODE_PRIVATE);
    sd = getSharedPreferences("sd", Activity.MODE_PRIVATE);
    delfile = getSharedPreferences("delfile", Activity.MODE_PRIVATE);
    zipCuntishen = getSharedPreferences("zipCuntishen", Activity.MODE_PRIVATE);
    war = getSharedPreferences("war", Activity.MODE_PRIVATE);
    AppUpdeat = new RequestNetwork(this);
    tmp = getSharedPreferences("tmp", Activity.MODE_PRIVATE);
    base = getSharedPreferences("base", Activity.MODE_PRIVATE);
    save_path = getSharedPreferences("save_path", Activity.MODE_PRIVATE);
    materialYou = getSharedPreferences("materialYou", Activity.MODE_PRIVATE);
    book = getSharedPreferences("hsipsot4444", Activity.MODE_PRIVATE);
    WindowsMath(_drawer, _coordinator);
    var vie = LayoutInflater.from(this).inflate(R.layout.recyclerview_emptyview, null, false);
    recyclerview2.setEmptyView(vie);
    BackPressed();
    var helper =
        new RecyclerViewHelper(
            recyclerview2,
            new RecyclerViewHelper.CallBackHelper() {

              @Override
              public void CallBackLeft(int pos) {
                _project();
                recyclerview2.getAdapter().notifyDataSetChanged();
              }

              @Override
              public void CallBackRight(int pos) {
                _delFileCustom(pos);
                recyclerview2.getAdapter().notifyDataSetChanged();
              }
            });
    swiperefreshlayout1.setOnRefreshListener(
        new SwipeRefreshLayout.OnRefreshListener() {
          @Override
          public void onRefresh() {
            recyclerview2.getAdapter().notifyDataSetChanged();
            swiperefreshlayout1.setRefreshing(false);
          }
        });

    recyclerview2.addOnScrollListener(
        new RecyclerView.OnScrollListener() {
          @Override
          public void onScrollStateChanged(RecyclerView recyclerView, int _scrollState) {
            super.onScrollStateChanged(recyclerView, _scrollState);
          }

          @Override
          public void onScrolled(RecyclerView recyclerView, int _offsetX, int _offsetY) {
            super.onScrolled(recyclerView, _offsetX, _offsetY);
            if (_offsetY > 2) {
              fabAdd.shrink();
            } else if (_offsetY < -2) {
              fabAdd.extend();
            }
          }
        });

    _AppUpdeat_request_listener =
        new RequestNetwork.RequestListener() {
          @Override
          public void onResponse(String _param1, String _param2, HashMap<String, Object> _param3) {
            final String _tag = _param1;
            final String _response = _param2;
            final HashMap<String, Object> _responseHeaders = _param3;
            try {
              upfile =
                  new Gson()
                      .fromJson(
                          _response,
                          new TypeToken<ArrayList<HashMap<String, Object>>>() {}.getType());
            } catch (Exception e) {

            }
            if (!upfile.get((int) 0).get("Version").toString().equals(version)) {
              /// AddDialog
              var di = new GhostWebMaterialDialog(FiledirActivity.this);
              di.setTitle(upfile.get((int) 0).get("Title").toString());
              di.setMessage(upfile.get((int) 0).get("Massges").toString());
              di.setCancelable(false);
              di.setNeutralButton(
                  "اپدیت",
                  (p, d) -> {
                    void10.setAction(Intent.ACTION_VIEW);
                    void10.setData(Uri.parse(upfile.get((int) 0).get("Link").toString()));
                    startActivity(void10);
                    finishAffinity();
                  });
              di.setPositiveButton("بعدا به من یادآوری کن",null);

              di.show();

            } else {
              /// Empty
            }
          }

          @Override
          public void onErrorResponse(String _param1, String _param2) {}
        };
  }

  @Override
  public void onClick(View view, int pos) {
    staticstring = files.get((int) pos).get("path").toString();
    if (FileUtil.isDirectory(staticstring)) {
      Folder = staticstring;
      _getFiles("");
    } else {
      _dataOnClickItemList(pos);
    }
  }

  private void initializeLogic() {

    getWindow()
        .setSoftInputMode(
            WindowManager.LayoutParams.SOFT_INPUT_STATE_VISIBLE
                | WindowManager.LayoutParams.SOFT_INPUT_ADJUST_RESIZE);
    AppUpdeat.startRequestNetwork(
        RequestNetworkController.GET,
        "https://raw.githubusercontent.com/appt2/appt2/main/log.json",
        "",
        _AppUpdeat_request_listener);
    if (war.contains("val")) {}

    var progress_m = new com.zip4j.progress.ProgressMonitor();

    try {
      android.content.pm.PackageInfo pInfo =
          getApplicationContext()
              .getPackageManager()
              .getPackageInfo(getApplicationContext().getPackageName(), 0);
      version = pInfo.versionName;
    } catch (android.content.pm.PackageManager.NameNotFoundException e) {
      showMessage(e.toString());
    }
    // new FastScrollerBuilder(recyclerview2).useMd2Style().build();
    fast = new FastScrollerBuilder(recyclerview2);
    fast.useMd2Style();
    fast.build();
    progressDilaog =
        new ProgressDialog(FiledirActivity.this, ProgressDialog.THEME_DEVICE_DEFAULT_DARK);
    unzip = new ProgressDialog(FiledirActivity.this, ProgressDialog.THEME_DEVICE_DEFAULT_DARK);
    mprodialog = new ProgressDialog(FiledirActivity.this, ProgressDialog.THEME_DEVICE_DEFAULT_DARK);
    prodel = new ProgressDialog(FiledirActivity.this, ProgressDialog.THEME_DEVICE_DEFAULT_DARK);
    proveg = new ProgressDialog(FiledirActivity.this, ProgressDialog.THEME_DEVICE_DEFAULT_DARK);
    copydir = new ProgressDialog(FiledirActivity.this, ProgressDialog.THEME_DEVICE_DEFAULT_DARK);
    copypath = new ProgressDialog(FiledirActivity.this, ProgressDialog.THEME_DEVICE_DEFAULT_DARK);
    /// result code By Ninja coder.ir my love java
    /// result code By Ninja coder.ir my love java

    GradientDrawable u = new GradientDrawable();
    u.setColor(0xFF2B2122);
    u.setCornerRadius(25);
    u.setStroke(1, 0xFFF8B09A);

    _fos();
    _materialYouss();
    paramentLayout_fileDir.setLayoutParams(
        new LinearLayout.LayoutParams(
            SketchwareUtil.getDisplayWidthPixels(getApplicationContext()),
            SketchwareUtil.getDisplayHeightPixels(getApplicationContext())));
    progressDilaog.getWindow().setBackgroundDrawable(u);
    unzip.getWindow().setBackgroundDrawable(u);
    mprodialog.getWindow().setBackgroundDrawable(u);
    prodel.getWindow().setBackgroundDrawable(u);
    proveg.getWindow().setBackgroundDrawable(u);
    copydir.getWindow().setBackgroundDrawable(u);
    copypath.getWindow().setBackgroundDrawable(u);

    sd_stor = new SdCardUtil(this);
    if (save_path.contains("path")) {
      if (FileUtil.isExistFile(save_path.getString("path", ""))) {
        Folder = save_path.getString("path", "");
        _getFiles("");
      } else {
        Folder = FileUtil.getExternalStorageDir();
        _getFiles("");
      }
    } else {
      Folder = FileUtil.getExternalStorageDir();
      _getFiles("");
    }
    user = new FileEventUser();
    user.setCallBack(
        () -> {
          Toast.makeText(getApplicationContext(), "File changed", Toast.LENGTH_LONG).show();
        });
    
    startService(new Intent(this, FileEventUser.class));
    getSupportActionBar().hide();
    sharedPreferences = getSharedPreferences("fileSp", Context.MODE_PRIVATE);
    utils = new HichemSoftFileUtil(sharedPreferences, FiledirActivity.this).loadData();
    utils.requestPermissionAllFilesAccess(); // if not allowed
    _refreshTabs();
    FileManagerUtils fileManagerUtils = new FileManagerUtils(this);
    
//    if (FileUtil.isExistFile("/storage/emulated/0/GhostWebIDE/icon.zip")) {
//    } else {
//      FileUtil.makeDir("/storage/emulated/0/GhostWebIDE/.icon");
//      AssetsSoft assetsSoft = new AssetsSoft();
//      assetsSoft.copyOneFileFromAssets(
//          "icon.zip", "/storage/emulated/0/GhostWebIDE/", FiledirActivity.this);
//      vvv12 =
//          new TimerTask() {
//            @Override
//            public void run() {
//              runOnUiThread(
//                  new Runnable() {
//                    @Override
//                    public void run() {
//                      _hsiunzip(
//                          "/storage/emulated/0/GhostWebIDE/icon.zip",
//                          "/storage/emulated/0/GhostWebIDE/.icon/");
//                    }
//                  });
//            }
//          };
//      _timer.schedule(vvv12, (int) (3000));
//    }
//
    fabAdd = findViewById(R.id.fabAdd);

    AnimUtils.Worker(fabAdd);

    fabAdd.setText("Add new Work");
    fabAdd.setIconResource(R.drawable.cog);
    ColorAndroid12.setFab(fabAdd);
    fabAdd.setOnClickListener(
        v -> {
          _dialogjni();
        });
    navs.getMenu().add(0, 1, 0, "Setting").setIcon(R.drawable.cog);
    navs.getMenu().add(0, 2, 0, "Java code").setIcon(R.drawable.javanull);
    navs.getMenu().add(0, 3, 0, "Add Star to Myket").setIcon(R.drawable.googleplay);
    navs.getMenu().add(0, 4, 0, "Terminal").setIcon(R.drawable.console);
    navs.getMenu().add(0, 5, 0, "SdCard").setIcon(R.drawable.sdcard);
    navs.getMenu().add(0, 6, 0, "info").setIcon(R.drawable.information);
    navs.getMenu().add(0, 7, 0, "Lrean").setIcon(R.drawable.school);
    navs.getMenu().add(0, 8, 0, "Keyboard").setIcon(R.drawable.keyboard);
    navs.getMenu().add(0, 9, 0, "Update App").setIcon(R.drawable.keyboardlisnertalluserpost_1);
    navs.getMenu().add(0, 10, 0, "BackUp Theme").setIcon(R.drawable.pms);
    navs.getMenu().add(0, 11, 0, "Log View").setIcon(R.drawable.log);
    navs.getMenu().add(0, 12, 0, "Icon Shop").setIcon(R.drawable.color);
    navs.getMenu().add(0, 13, 0, "PL manager").setIcon(R.drawable.link);
    navs.getMenu().add(0, 14, 0, "Book mark (Beta)").setIcon(R.drawable.ic_bookmark_white);
    navs.getMenu().add(0, 15, 0, "exit").setIcon(R.drawable.exit);
    _DrowerHandler();
  }
  
  public void filterFile() {
    var view = LayoutInflater.from(this).inflate(R.layout.reminderlist, null, false);
    var dialog = new MaterialAlertDialogBuilder(this);
    dialog.setTitle("Filter List");
    dialog.setPositiveButton("close",null);
    dialog.setView(view);
    if (dialog != null) {
      dialog.show();
    }

    TextInputEditText et = view.findViewById(R.id.ed_filter);
    TextInputLayout  layout = view.findViewById(R.id.input);
    layout.setEndIconVisible(false);
    layout.setEndIconMinSize(10);
    layout.setEndIconScaleType(ImageView.ScaleType.CENTER_INSIDE);
    layout.setEndIconDrawable(R.drawable.deletear);
    layout.setEndIconOnClickListener(v -> et.getText().clear());

    et.addTextChangedListener(
        new TextWatcher() {

          @Override
          public void onTextChanged(CharSequence ser, int arg1, int arg2, int arg3) {


            ThreadUtils.runOnUiThread(
                () -> {
                  fileListItem.search(ser.toString());
                  recyclerview2.setAdapter(fileListItem);
                });
          }

          @Override
          public void beforeTextChanged(CharSequence arg0, int arg1, int arg2, int arg3) {}

          @Override
          public void afterTextChanged(Editable arg0) {}
        });
  }

  public void BackPressed() {
    getOnBackPressedDispatcher()
        .addCallback(
            this,
            new OnBackPressedCallback(true) {
              @Override
              public void handleOnBackPressed() {
                if (sd_stor.hasRealRemovableSdCarde()) {
                  if (Folder.equals(FileUtil.getExternalStorageDir())
                      || Folder.equals(sd_stor.getExtSdPath())) {
                    gotoback++;
                    if (gotoback == 3) {
                      Chack = false;
                      isCopyAndMoved = false;

                      new GhostWebMaterialDialog(FiledirActivity.this)
                          .setTitle(getResources().getString(R.string.exitapp))
                          .setMessage(getResources().getString(R.string.exitappmsg))
                          .setNeutralButton(
                              getResources().getString(R.string.yes),
                              (p, d) -> {
                                gotoback = 0;
                                finishAffinity();
                              })
                          .setPositiveButton(
                              getResources().getString(R.string.no),
                              (p1, d2) -> {
                                gotoback = 0;
                              })
                          .setCancelable(false)
                          .show();

                    } else {
                      SketchwareUtil.showMessage(
                          getApplicationContext(), "برای خروج ۳ بار کلیک کنید");
                    }
                  } else {
                    saveScrollPosition();
                    try {
                      linearLayoutManager.scrollToPosition(
                          linearLayoutManager.findFirstVisibleItemPosition());
                    } catch (RuntimeException runtimeException) {
                      Toast.makeText(getApplicationContext(), runtimeException.getMessage(), 2)
                          .show();
                    }

                    Folder = Folder.substring((int) (0), (int) (Folder.lastIndexOf("/")));
                    _getFiles("");
                  }
                } else {
                  if (Folder.equals(FileUtil.getExternalStorageDir())) {
                    gotoback++;
                    if (gotoback == 3) {
                      Chack = false;
                      isCopyAndMoved = false;

                      new GhostWebMaterialDialog(FiledirActivity.this)
                          .setTitle(getResources().getString(R.string.exitapp))
                          .setMessage(getResources().getString(R.string.exitappmsg))
                          .setNeutralButton(
                              getResources().getString(R.string.yes),
                              (p, d) -> {
                                gotoback = 0;
                                finishAffinity();
                              })
                          .setPositiveButton(
                              getResources().getString(R.string.no),
                              (p1, d2) -> {
                                gotoback = 0;
                              })
                          .setCancelable(false)
                          .show();

                    } else {
                      SketchwareUtil.showMessage(
                          getApplicationContext(), "برای خروج ۳ بار کلیک کنید");
                    }
                  } else {
                    Folder = Folder.substring((int) (0), (int) (Folder.lastIndexOf("/")));
                    saveScrollPosition();
                    try {
                      linearLayoutManager.scrollToPosition(
                          linearLayoutManager.findFirstVisibleItemPosition());
                    } catch (RuntimeException runtimeException) {
                      Toast.makeText(getApplicationContext(), runtimeException.getMessage(), 2)
                          .show();
                    }

                    _getFiles("");
                  }
                }
              }
            });
  }

  @Override
  public void onResume() {
    super.onResume();
    _refreshTabs();
    restoreScrollPosition();
  }

  private void saveScrollPosition() {
    int firstVisibleItemPosition = linearLayoutManager.findFirstVisibleItemPosition();
    SharedPreferences.Editor editor = mSharedPreferences.edit();
    editor.putInt(ITEM_POSITION_KEY, firstVisibleItemPosition);
    editor.apply();
  }

  private void restoreScrollPosition() {
    int savedItemPosition = mSharedPreferences.getInt(ITEM_POSITION_KEY, 0);
    linearLayoutManager.scrollToPosition(savedItemPosition + 3);
  }

  @Override
  public void onPause() {
    super.onPause();
    saveScrollPosition();
  }

  public void _getFiles(final String _path) {
    save_path.edit().putString("path", Folder).apply();
    list.clear();
    files.clear();
    folderList.clear();
    fileList.clear();
    FileUtil.listDir(Folder, list);
    Collections.sort(list, String.CASE_INSENSITIVE_ORDER);
    GetTab = Folder;
    index = 0;
     POSNINJACODERMAIN = Folder;
    ProjectManager mproject = new ProjectManager();
    mproject.setProjectDir(GetTab);
    final class FileComparator implements Comparator<String> {
      public int compare(String f1, String f2) {
        if (f1 == f2) return 0;
        if (FileUtil.isDirectory(f1) && FileUtil.isFile(f2)) return -1;
        if (FileUtil.isFile(f1) && FileUtil.isDirectory(f2)) return 1;
        return f1.compareToIgnoreCase(f2);
      }
    }
    Collections.sort(list, new FileComparator());

    for (int _repeat13 = 0; _repeat13 < (int) (list.size()); _repeat13++) {
      if (FileUtil.isDirectory(list.get((int) (index)))) {
        folderList.add(list.get((int) (index)));
      } else {
        fileList.add(list.get((int) (index)));
      }
      index++;
    }
    index = 0;
    for (int _repeat37 = 0; _repeat37 < (int) (folderList.size()); _repeat37++) {
      {
        HashMap<String, Object> _item = new HashMap<>();
        _item.put("path", folderList.get((int) (index)));
        files.add(_item);
      }

      index++;
    }
    index = 0;
    for (int _repeat54 = 0; _repeat54 < (int) (fileList.size()); _repeat54++) {
      {
        HashMap<String, Object> _item = new HashMap<>();
        _item.put("path", fileList.get((int) (index)));
        files.add(_item);
      }

      index++;
    }
    index = 0;
    for (int _repeat97 = 0; _repeat97 < (int) (files.size()); _repeat97++) {
      files.get((int) index).put("sel", "false");
      index++;
    }
    try {

    } catch (Exception e) {
      SketchwareUtil.showMessage(getApplicationContext(), "Error to " + e.toString());
    }

    recyclerview2.setAdapter(fileListItem);
    ListSheet.bind(recyclerview2, Folder);
    if (linearLayoutManager != null) {
      recyclerview2.setLayoutManager(linearLayoutManager);
    }

    _distreeview();
  }

  public void _folder() {
    androidx.appcompat.app.AlertDialog dialog =
        new GhostWebMaterialDialog(FiledirActivity.this)
            .setView(R.layout.makefolder)
            .setTitle("Folder")
            .setMessage("make folder")
            .setCancelable(true)
            .setPositiveButton("make", null)
            .setNegativeButton(android.R.string.cancel, null)
            .create();
    dialog.setOnShowListener(
        (var) -> {
          com.google.android.material.textfield.TextInputLayout top = dialog.findViewById(R.id.top);

          EditText editor = dialog.findViewById(R.id.editor);

          Button positive = dialog.getButton(DialogInterface.BUTTON_POSITIVE);
          if (editor.getText().toString().isEmpty()) {
            positive.setEnabled(false);
          } else {
            positive.setEnabled(true);
          }
          top.setCounterEnabled(true);
          editor.setHint("Type Name Folder");
          top.setCounterMaxLength(200);
          top.setEndIconTintMode(PorterDuff.Mode.MULTIPLY);
          top.setEndIconDrawable(R.drawable.close);
          top.setEndIconOnClickListener(
              v -> {
                editor.setText("");
              });

          positive.setOnClickListener(
              (vftrororocjj) -> {
                SketchwareUtil.hideKeyboard(getApplicationContext());
                if (editor.getText().toString().isEmpty()) {
                  top.setError("return type Error \n");
                } else {
                  CreateFolder = editor.getText().toString();
                  try {
                    NinjaMacroFileUtil.createDirectory(
                        Folder.concat("/".concat(CreateFolder.concat("/"))),
                        new NinjaMacroFileUtil.OnFileOperationListener() {
                          @Override
                          public void onSuccess(String content) {
                            // محتوای فایل با موفقیت خوانده شد
                            Toast.makeText(getApplicationContext(), content, 2).show();
                            _getFiles("");
                            if (recyclerview2 != null) {
                              recyclerview2.getAdapter().notifyItemChanged(files.size());
                              // fileListItem.addItem(files.size());

                            }
                          }

                          @Override
                          public void onError(String e) {
                            // خطایی در هنگام خواندن فایل رخ داده است
                            Toast.makeText(getApplicationContext(), e, 2).show();
                          }
                        });

                  } catch (Exception e) {

                  }
                }
                dialog.dismiss();
              });
          editor.addTextChangedListener(
              new android.text.TextWatcher() {
                @Override
                public void onTextChanged(
                    CharSequence _param1, int _param2, int _param3, int _param4) {
                  final String _charSeq = _param1.toString();

                  if (editor.getText().toString().isEmpty()) {
                    positive.setEnabled(false);
                  } else {
                    positive.setEnabled(true);
                  }
                }

                @Override
                public void beforeTextChanged(
                    CharSequence _param1, int _param2, int _param3, int _param4) {}

                @Override
                public void afterTextChanged(android.text.Editable _param1) {}
              });
        });
    final View view = dialog.getWindow().getDecorView();
    dialog.getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_VISIBLE);
    view.setScaleX(0f);
    view.setScaleY(0f);
    final ObjectAnimator alertAnim = new ObjectAnimator();
    final ObjectAnimator alertAnim1 = new ObjectAnimator();
    alertAnim.setTarget(view);
    alertAnim.setPropertyName("scaleX");
    alertAnim.setFloatValues((float) (1));
    alertAnim.setDuration((int) (250));
    alertAnim.start();
    alertAnim1.setTarget(view);
    alertAnim1.setPropertyName("scaleY");
    alertAnim1.setFloatValues((float) (1));
    alertAnim1.setDuration((int) (250));
    alertAnim1.start();
    dialog.show();
  }

  public void _file() {
    
    var folders = new FileMaker(this);
    folders.setFolderName(Folder);
    folders.setCallBack(
        new FileCallBack() {

          @Override
          public void onDoneMakeFile(String toast) {
            _getFiles("");
          }

          @Override
          public void onError(String error) {}
        });
  }

  public void _Ripple_Drawable(final View _view, final String _c) {
    android.content.res.ColorStateList clr =
        new android.content.res.ColorStateList(
            new int[][] {new int[] {}}, new int[] {Color.parseColor(_c)});
    android.graphics.drawable.RippleDrawable ripdr =
        new android.graphics.drawable.RippleDrawable(clr, null, null);
    _view.setBackground(ripdr);
  }

  public void _refreshTabs() {
    if (shp.contains("path")) {
      if (!shp.getString("path", "").equals("")) {
        newlistmap =
            new Gson()
                .fromJson(
                    shp.getString("path", ""),
                    new TypeToken<ArrayList<HashMap<String, Object>>>() {}.getType());
      }
    }
  }

  public void _checkListMap2(
       int _position,
       String _key,
       List<HashMap<String, Object>> _listmap1,
       List<HashMap<String, Object>> _listmap2) {
    tab = _listmap1.get((int) _position).get(_key).toString();
    shp.edit().putString("pos_path", _listmap1.get((int) _position).get(_key).toString()).commit();
    if (_listmap2.isEmpty()) {
      positionTabs = 0;
      activitiy.setClass(getApplicationContext(), CodeeditorActivity.class);
      {
        HashMap<String, Object> _item = new HashMap<>();
        _item.put(_key, _listmap1.get((int) _position).get(_key).toString());
        _listmap2.add(_item);
      }

      shp.edit().putString(_key, new Gson().toJson(_listmap2)).commit();
      shp.edit().putString("positionTabs", String.valueOf((long) (positionTabs))).commit();
      startActivity(activitiy);
    } else {
      n = 0;
      while (true) {
        if (_listmap2.get((int) n).get(_key).toString().contains(tab)) {
          activitiy.setClass(getApplicationContext(), CodeeditorActivity.class);
          shp.edit().putString("positionTabs", String.valueOf((long) (n))).commit();
          startActivity(activitiy);
          break;
        } else {
          if (n == (_listmap2.size() - 1)) {
            activitiy.setClass(getApplicationContext(), CodeeditorActivity.class);
            {
              HashMap<String, Object> _item = new HashMap<>();
              _item.put(_key, _listmap1.get((int) _position).get(_key).toString());
              _listmap2.add(_item);
            }

            shp.edit().putString(_key, new Gson().toJson(_listmap2)).commit();
            shp.edit()
                .putString("positionTabs", String.valueOf((long) (_listmap2.size() - 1)))
                .commit();
            activitiy.putExtra("htmlcode", _listmap1.get((int) _position).get(_key).toString());
            activitiy.putExtra(
                "htmlfile",
                Uri.parse(_listmap1.get((int) _position).get(_key).toString())
                    .getLastPathSegment());
            File file = new File(_listmap1.get((int) _position).get(_key).toString());
            activitiy.putExtra("root", file.getParent());
            startActivity(activitiy);
            break;
          }
        }
        n++;
      }
    }
  }

  public void _checkListMap3(
      final double _position,
      final String _key,
      final ArrayList<HashMap<String, Object>> _listmap1,
      final ArrayList<HashMap<String, Object>> _listmap2) {

  }

  public void _distreeview() {

    List<String> pospath = spiltIntoBreadcrumbItems(Folder);

    adps = new ToolbarListFileAdapter(pospath, FiledirActivity.this, this);

    recyclerview1.setAdapter(adps);
    recyclerview1.setLayoutManager(
        new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false));
    recyclerview1.smoothScrollToPosition(pospath.size());
  }

  @Override
  public void GoToDir(View view) {
    ColorView.setPath(
        Folder,
        this,
        (ps) -> {
          Folder = ps;
          _getFiles("");
        },
        view);
  }

  @Override
  public void GoToTreeFile(View view) {
    if (!Folder.equals(Environment.getExternalStorageDirectory().getAbsolutePath())) {
      Folder = Folder.substring(0, Folder.lastIndexOf("/"));
      _getFiles("");
      _distreeview();
    } else {
      Log.e("EndPath", "");
    }
  }

  public List<String> spiltIntoBreadcrumbItems(String filePath) {
    String separator = "/";
    String[] items = filePath.split(separator);
    List<String> filteredItems = new ArrayList<>();
    for (String item : items) {
      if (!item.trim().isEmpty()) {
        filteredItems.add(item);
      }
    }
    if (filteredItems.size() >= 3
        && filteredItems.get(0).equals("storage")
        && filteredItems.get(1).equals("emulated")
        && filteredItems.get(2).equals("0")) {
      List<String> combinedItems = new ArrayList<>();
      combinedItems.add("sdcard");
      combinedItems.addAll(filteredItems.subList(3, filteredItems.size()));
      return combinedItems;
    }
    return filteredItems;
  }

  private HichemSoftFileUtil utils;
  private SharedPreferences sharedPreferences;

  @Override
  protected void onActivityResult(int _requestCode, int _resultCode, Intent _data) {
    super.onActivityResult(_requestCode, _resultCode, _data);
    if (_resultCode == Activity.RESULT_OK) {
      if (_data != null) {
        utils.persistFolder(_data);
      } else {

      }
    } else {
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

  public void _renameFile(final String _dir, final String _oldName, final String _newName) {
    File dir = new File(_dir.substring((int) (0), (int) _dir.lastIndexOf("/")));
    if (dir.exists()) {
      File from = new File(dir, _oldName);
      File to = new File(dir, _newName);
      if (from.exists()) from.renameTo(to);
    }
  }

  

  public void _DecompressAssterUnzip(final String _input, final String _output) {
    Decompress.unzipFromAssets(this, _input, _output);
  }

  public void _project() {
    androidx.appcompat.app.AlertDialog dialog =
        new GhostWebMaterialDialog(FiledirActivity.this)
            .setView(R.layout.projects)
            .setTitle("Make project")
            .setMessage("type name project and setLibrary ")
            .setCancelable(true)
            .setIcon(R.drawable.web)
            .setPositiveButton("make project", null)
            .setNegativeButton(android.R.string.cancel, null)
            .create();
    dialog.setOnShowListener(
        (var) -> {
          EditText editor = dialog.findViewById(R.id.editor);

          com.google.android.material.checkbox.MaterialCheckBox ch = dialog.findViewById(R.id.ch);

          com.google.android.material.checkbox.MaterialCheckBox ch2 = dialog.findViewById(R.id.ch2);

          com.google.android.material.checkbox.MaterialCheckBox ch3 = dialog.findViewById(R.id.ch3);

          com.google.android.material.checkbox.MaterialCheckBox ch4 = dialog.findViewById(R.id.ch4);

          com.google.android.material.textfield.TextInputLayout te = dialog.findViewById(R.id.te);

          HorizontalScrollView hs = dialog.findViewById(R.id.hs);

          Button positive = dialog.getButton(DialogInterface.BUTTON_POSITIVE);
          if (editor.getText().toString().isEmpty()) {
            positive.setEnabled(false);
            te.setEndIconVisible(false);
          } else {
            positive.setEnabled(true);
            te.setEndIconVisible(true);
          }
          editor.addTextChangedListener(
              new android.text.TextWatcher() {
                @Override
                public void onTextChanged(
                    CharSequence _param1, int _param2, int _param3, int _param4) {
                  final String _charSeq = _param1.toString();

                  if (editor.getText().toString().isEmpty()) {
                    positive.setEnabled(false);
                    te.setEndIconVisible(false);
                  } else {
                    te.setEndIconVisible(true);
                    positive.setEnabled(true);
                  }
                }

                @Override
                public void beforeTextChanged(
                    CharSequence _param1, int _param2, int _param3, int _param4) {}

                @Override
                public void afterTextChanged(android.text.Editable _param1) {}
              });
          hs.setHorizontalScrollBarEnabled(false);
          hs.setVerticalScrollBarEnabled(false);
          hs.setOverScrollMode(ListView.OVER_SCROLL_NEVER);
          te.setCounterTextColor(ColorStateList.valueOf(Color.parseColor("#FFF8B09A")));
          te.setCounterEnabled(true);
          te.setCounterMaxLength(100);
          te.setEndIconDrawable(R.drawable.closehsi);
          te.setEndIconVisible(false);
          te.setEndIconOnClickListener(
              v -> {
                editor.setText("");
              });
          positive.setOnClickListener(
              (vftrororocjj) -> {
                dialog.dismiss();
                SketchwareUtil.hideKeyboard(getApplicationContext());
                if (editor.getText().toString().isEmpty()) {
                  SketchwareUtil.showMessage(getApplicationContext(), "نام پروژه را وارد کنید");
                } else {
                  if (ch.isChecked() || (ch2.isChecked() || (ch3.isChecked() || ch4.isChecked()))) {
                    FileUtil.makeDir(GetTab.concat("/".concat(editor.getText().toString())));
                    _TaskProjectInstallProgessDialog(
                        "project.zip", GetTab.concat("/".concat(editor.getText().toString())));
                    _getFiles("");
                  } else {
                    FileUtil.makeDir(GetTab.concat("/"));
                    _TaskProjectInstallProgessDialog("project.zip", GetTab);
                    _getFiles("");
                  }
                  ask =
                      new TimerTask() {
                        @Override
                        public void run() {
                          runOnUiThread(
                              new Runnable() {
                                @Override
                                public void run() {
                                  if (ch.isChecked()) {
                                    _TaskProjectInstallProgessDialog(
                                        "bootstrap5.2.2.zip",
                                        GetTab.concat("/".concat(editor.getText().toString())));
                                    _getFiles("");
                                  } else {

                                  }
                                  if (ch2.isChecked()) {
                                    _TaskProjectInstallProgessDialog(
                                        "kotlin1.7.20.zip",
                                        GetTab.concat("/".concat(editor.getText().toString())));
                                    _getFiles("");
                                  } else {

                                  }
                                  if (ch3.isChecked()) {
                                    _TaskProjectInstallProgessDialog(
                                        "typescript4.8.4.zip",
                                        GetTab.concat("/".concat(editor.getText().toString())));
                                    _getFiles("");
                                  } else {

                                  }
                                  if (ch4.isChecked()) {
                                    _TaskProjectInstallProgessDialog(
                                        "angular9.1.13.zip",
                                        GetTab.concat("/".concat(editor.getText().toString())));
                                    _getFiles("");
                                  } else {

                                  }
                                }
                              });
                        }
                      };
                  _timer.schedule(ask, (int) (900));
                }
                _getFiles("");
              });
        });

    dialog.show();
  }

  public void _TaskProjectInstallProgessDialog(final String _getInput, final String _getOutput) {
    new AsyncTask<String, String, String>() {
      @Override
      protected void onPreExecute() {
        unzip.setTitle("installLibrary");
        unzip.setMessage("Task Runing Install LocalLibrary...");
        unzip.show();
      }

      @Override
      protected String doInBackground(String... params) {
        String _param = params[0];
        _DecompressAssterUnzip(_getInput, _getOutput);
        return "";
      }

      @Override
      protected void onPostExecute(String _result) {
        unzip.dismiss();
      }
    }.execute("");
  }

  public void _zipviewandexsert(
      final double _pos, final String _path, final ArrayList<HashMap<String, Object>> _map) {
    if (_map.get((int) _pos).get(_path).toString().endsWith(".zip")) {
      var di =
          new com.google.android.material.dialog.MaterialAlertDialogBuilder(FiledirActivity.this);

      di.setTitle("مشاهده گر فایل فشرده");
      di.setMessage(
          "برای مشاهده یا استخراج یکی از گزینه های زیرا انتخاب کنید دقت کنید که در محل فعلی فایل ها استخراج میشن");
      di.setNeutralButton(
          "مشاهده",
          (p, d) -> {
            intgetTheme.setClass(getApplicationContext(), ZipshowActivity.class);
            intgetTheme.putExtra("zipview", _map.get((int) _pos).get(_path).toString());
            startActivity(intgetTheme);
            // ZipFileShow.showAsDialog(FiledirActivity.this,_map.get((int)
            // _pos).get(_path).toString());
          });
      di.setPositiveButton(
          "استخراج",
          (p1, d2) -> {
            _hsiunzip(_map.get((int) _pos).get(_path).toString(), GetTab);
          });
      androidx.appcompat.app.AlertDialog dialog = di.show();

      dialog.show();
    }
  }

  public void _lojiceinstallK() {
    var di = new GhostWebMaterialDialog(FiledirActivity.this);
    di.setTitle("کیبورد");
    di.setMessage(
        "دقت کنید اگر گزینه انتخاب کیبورد را نزدید ابتدا کیبورد را انتخاب کنید سپس تایید کنید با تشکر");
    di.setNeutralButton(
        "انتخاب کیبورد",
        (p, d) -> {
          Intent i = new Intent("android.settings.INPUT_METHOD_SETTINGS");
          startActivity(i);
          /// انتخاب کیبورد

        });
    di.setPositiveButton(
        "تایید کیبورد",
        (p1, d2) -> {
          try {
            InputMethodManager i = (InputMethodManager) getSystemService("input_method");
            i.showInputMethodPicker();
          } catch (Exception e) {
            e.printStackTrace();
          }
          /// تایید کیبورد

        });
    di.show();
  }

  public void _fontpost(
      final ArrayList<HashMap<String, Object>> _map, final String _path, final double _pos) {
    if (_map.get((int) _pos).get(_path).toString().endsWith(".ttf")
        || _map.get((int) _pos).get(_path).toString().endsWith(".otf")) {
      finalintentpostfont.setClass(getApplicationContext(), FontviewActivity.class);
      finalintentpostfont.putExtra("font", _map.get((int) _pos).get(_path).toString());
      startActivity(finalintentpostfont);
    }
  }

  public void _colorpost(final TextView _textview1) {
    com.neo.highlight.core.Highlight highlight = new com.neo.highlight.core.Highlight();

    // add Schemes

    highlight.addScheme(
        new com.neo.highlight.util.scheme.ColorScheme(Pattern.compile("\b\\.html"), 0xFF00BCD4),
        new com.neo.highlight.util.scheme.ColorScheme(Pattern.compile("\b\\.py"), 0xFFFFEB3B),
        new com.neo.highlight.util.scheme.ColorScheme(Pattern.compile("\b\\.cpp{1}"), 0xFFE91E63),
        new com.neo.highlight.util.scheme.ColorScheme(Pattern.compile("\b\\.c"), 0xFF795548),
        new com.neo.highlight.util.scheme.ColorScheme(Pattern.compile("\b\\.css{1}"), 0xFF2196F3),
        new com.neo.highlight.util.scheme.ColorScheme(Pattern.compile("\b\\.swift"), 0xFFFF9800),
        new com.neo.highlight.util.scheme.ColorScheme(Pattern.compile("\b\\.go"), 0xFF3F51B5),
        new com.neo.highlight.util.scheme.ColorScheme(Pattern.compile("\b\\.php"), 0xFF9C27B0),
        new com.neo.highlight.util.scheme.ColorScheme(Pattern.compile("\bGhostWebIDE"), 0xFF009688),
        new com.neo.highlight.util.scheme.ColorScheme(Pattern.compile("\b\\.rb"), 0xFFF44336),
        new com.neo.highlight.util.scheme.ColorScheme(Pattern.compile("\b\\.js"), 0xFF3F51B5),
        new com.neo.highlight.util.scheme.ColorScheme(Pattern.compile("\b\\.rbw"), 0xFFC62828),
        new com.neo.highlight.util.scheme.ColorScheme(Pattern.compile("\b\\.ghost"), 0xFFE0E0E0),
        new com.neo.highlight.util.scheme.ColorScheme(Pattern.compile("\b\\.java"), 0xFFFFC107),
        new com.neo.highlight.util.scheme.ColorScheme(Pattern.compile("\b\\.cs"), 0xFF4CAF50),
        new com.neo.highlight.util.scheme.ColorScheme(Pattern.compile("\b\\.svg"), 0xFFFF9800));

    highlight.setSpan(_textview1);
  }

  public void _themeinstall(
      final ArrayList<HashMap<String, Object>> _list, final double _pos, final String _str) {
    if (_list.get((int) _pos).get(_str).toString().endsWith(".aa")
        || _list.get((int) _pos).get(_str).toString().endsWith(".AA")) {
      if (FileUtil.isFile("/storage/emulated/0/GhostWebIDE/theme/GhostThemeapp.ghost")) {
        var di =
            new com.google.android.material.dialog.MaterialAlertDialogBuilder(FiledirActivity.this);

        di.setTitle("هشدار");
        di.setMessage("این فایل تم در حافظه شما موجود است آیا میخواهید این تم را جایگزین کنید؟");
        di.setNeutralButton(
            "بله",
            (p, d) -> {
              try {

                new net.lingala.zip4j.ZipFile(_list.get((int) _pos).get(_str).toString())
                    .extractAll("/storage/emulated/0/GhostWebIDE/");

              } catch (net.lingala.zip4j.exception.ZipException e) {
                showMessage(e.toString());
              }
              _getFiles("");
              SketchwareUtil.showMessage(getApplicationContext(), "انجام شد");
            });
        di.setPositiveButton("خیر", (p1, d2) -> {});

        androidx.appcompat.app.AlertDialog dialog = di.show();
        final View view = dialog.getWindow().getDecorView();
        view.setScaleX(0f);
        view.setScaleY(0f);
        dialog.getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_VISIBLE);
        final ObjectAnimator alertAnim = new ObjectAnimator();
        final ObjectAnimator alertAnim1 = new ObjectAnimator();
        alertAnim.setTarget(view);
        alertAnim.setPropertyName("scaleX");
        alertAnim.setFloatValues((float) (1));
        alertAnim.setDuration((int) (250));
        alertAnim.start();
        alertAnim1.setTarget(view);
        alertAnim1.setPropertyName("scaleY");
        alertAnim1.setFloatValues((float) (1));
        alertAnim1.setDuration((int) (250));
        alertAnim1.start();
        dialog.show();

      } else {
        try {

          new net.lingala.zip4j.ZipFile(_list.get((int) _pos).get(_str).toString())
              .extractAll("/storage/emulated/0/GhostWebIDE/");

        } catch (net.lingala.zip4j.exception.ZipException e) {
          showMessage(e.toString());
        }
        _getFiles("");
        SketchwareUtil.showMessage(getApplicationContext(), "انجام شد");
      }
    }
  }

  public void _installproject(
      final ArrayList<HashMap<String, Object>> _maps, final String _pathz, final double _number) {
    if (_maps.get((int) _number).get(_pathz).toString().endsWith(".project")) {
      var di =
          new com.google.android.material.dialog.MaterialAlertDialogBuilder(FiledirActivity.this);

      di.setTitle("نصب پروژه ؟");
      di.setMessage(
          "آیا میخواهید "
              .concat(
                  Uri.parse(_maps.get((int) _number).get(_pathz).toString())
                      .getLastPathSegment()
                      .concat(" نصب کنید؟")));
      di.setPositiveButton(
          "نصب",
          (p1, d2) -> {
            new AsyncTask<String, String, String>() {
              @Override
              protected void onPreExecute() {}

              @Override
              protected String doInBackground(String... params) {
                String _param = params[0];
                runOnUiThread(
                    new Runnable() {
                      @Override
                      public void run() {
                        _hsiunzip(_maps.get((int) _number).get(_pathz).toString(), Folder);
                      }
                    });
                return "";
              }

              @Override
              protected void onPostExecute(String _result) {
                _getFiles("");
              }
            }.execute("");
          });
      di.setNegativeButton(
          "مشاهده",
          (p3, d3) -> {
            void10.setClass(getApplicationContext(), ZipshowActivity.class);
            void10.putExtra("zipview", _maps.get((int) _number).get(_pathz).toString());
            startActivity(void10);
          });
      di.setNeutralButton("بستن", (p, d) -> {});

      androidx.appcompat.app.AlertDialog dialog = di.show();

      dialog.show();
    }
  }

  public void showMessage(String a) {
    Toast.makeText(getApplicationContext(), a, 2).show();
  }

  public void _insertData(final double _pos) {
    if (files.get((int) _pos).get("path").toString().endsWith(".tif")
        || (files.get((int) _pos).get("path").toString().endsWith(".tiff")
            || (files.get((int) _pos).get("path").toString().endsWith(".png")
                || (files.get((int) _pos).get("path").toString().endsWith(".jpg")
                    || (files.get((int) _pos).get("path").toString().endsWith(".jpeg")
                        || (files.get((int) _pos).get("path").toString().endsWith(".webp")
                            || (files.get((int) _pos).get("path").toString().endsWith(".bmp")
                                || files
                                    .get((int) _pos)
                                    .get("path")
                                    .toString()
                                    .endsWith(".gif")))))))) {
      void10.setClass(getApplicationContext(), ImgshowActivity.class);
      void10.putExtra("imagePath", Folder);
      void10.putExtra(
          "imageName",
          Uri.parse(files.get((int) _pos).get("path").toString()).getLastPathSegment());
      startActivity(void10);
    }
  }

  public void _dicomplier() {
    androidx.appcompat.app.AlertDialog dialog =
        new GhostWebMaterialDialog(FiledirActivity.this)
            .setView(R.layout.classjardicompiler)
            .setTitle("Dicompiler ")
            .setMessage("")
            .setCancelable(true)
            .setPositiveButton("run", null)
            .setNegativeButton(android.R.string.cancel, null)
            .create();
    dialog.setOnShowListener(
        (var) -> {
          EditText input = dialog.findViewById(R.id.input);

          EditText output = dialog.findViewById(R.id.output);

          com.google.android.material.textfield.TextInputLayout tvin =
              dialog.findViewById(R.id.tvin);

          com.google.android.material.textfield.TextInputLayout tvou =
              dialog.findViewById(R.id.tvou);

          ProgressBar pros = dialog.findViewById(R.id.pros);

          input.setText(staticstring);
          pros.setVisibility(View.GONE);
          output.setText(GetTab);
          Button positive = dialog.getButton(DialogInterface.BUTTON_POSITIVE);
          positive.setOnClickListener(
              (vftrororocjj) -> {
                if (output.getText().toString().isEmpty()) {
                  SketchwareUtil.showMessage(getApplicationContext(), "خروجی نمیتواند خالی باشد");
                } else {
                  new AsyncTask<String, String, String>() {
                    @Override
                    protected void onPreExecute() {
                      input.setEnabled(false);
                      output.setEnabled(false);
                      pros.setEnabled(true);
                      pros.setVisibility(View.VISIBLE);
                      tvin.setEnabled(false);
                      tvou.setEnabled(false);
                      tvou.setEnabled(false);
                      positive.setEnabled(false);
                    }

                    @Override
                    protected String doInBackground(String... params) {
                      String _param = params[0];
                      pv.clear();
                      pv.add(input.getText().toString());
                      pv.add("--extraclasspath");
                      pv.add("/storage/emulated/0/GhostWebIDE/android/android.jar");
                      pv.add("--outputdir");
                      pv.add(output.getText().toString());
                      try {
                        org.benf.cfr.reader.Main.main(pv.toArray(new String[0]));
                      } catch (Exception e) {

                        runOnUiThread(
                            new Runnable() {
                              @Override
                              public void run() {
                                SketchwareUtil.showMessage(getApplicationContext(), e.toString());
                              }
                            });
                      }
                      return "";
                    }

                    @Override
                    protected void onPostExecute(String _result) {
                      tvin.setEnabled(true);
                      tvou.setEnabled(true);
                      input.setEnabled(true);
                      output.setEnabled(true);
                      pros.setEnabled(true);
                      pros.setVisibility(View.GONE);
                      positive.setEnabled(true);
                      _getFiles("");
                      dialog.dismiss();
                    }
                  }.execute("");
                }
              });
        });

    dialog.show();
  }

  public void _rename(final double _pos) {
    androidx.appcompat.app.AlertDialog dialog =
        new GhostWebMaterialDialog(FiledirActivity.this)
            .setView(R.layout.ranme)
            .setTitle("Rename")
            .setMessage("type new name ")
            .setCancelable(false)
            .setPositiveButton("Ok", null)
            .setNegativeButton(android.R.string.cancel, null)
            .create();
    dialog.setOnShowListener(
        (var) -> {
          EditText editor = dialog.findViewById(R.id.editor);

          Button positive = dialog.getButton(DialogInterface.BUTTON_POSITIVE);
          editor.setText(
              Uri.parse(files.get((int) _pos).get("path").toString()).getLastPathSegment());
          if (editor.getText().toString().isEmpty()) {
            positive.setEnabled(false);
          } else {
            positive.setEnabled(true);
          }
          editor.addTextChangedListener(
              new android.text.TextWatcher() {
                @Override
                public void onTextChanged(
                    CharSequence _param1, int _param2, int _param3, int _param4) {
                  final String _charSeq = _param1.toString();

                  if (editor.getText().toString().isEmpty()) {
                    positive.setEnabled(false);
                  } else {
                    positive.setEnabled(true);
                  }
                }

                @Override
                public void beforeTextChanged(
                    CharSequence _param1, int _param2, int _param3, int _param4) {}

                @Override
                public void afterTextChanged(android.text.Editable _param1) {}
              });
          positive.setOnClickListener(
              (vftrororocjj) -> {
                {
                  java.io.File dYx4Y =
                      new java.io.File(files.get((int) _pos).get("path").toString());
                  java.io.File e5Cyk =
                      new java.io.File(Folder.concat("/".concat(editor.getText().toString())));
                  dYx4Y.renameTo(e5Cyk);
                }
                dialog.dismiss();
                _getFiles("");
              });
        });

    dialog.show();
  }

  public void _delFileCustom( int _pos) {
    var di =
        new MaterialAlertDialogBuilder(FiledirActivity.this);

    di.setTitle("Romved File");
    di.setMessage(
        "romved ".concat(files.get(_pos).get("path").toString().concat(" your mobile?")));
    di.setNeutralButton("no", (p, d) -> {});

    di.setPositiveButton(
        "ok",
        (p1, d2) -> {
          new AsyncTask<String, String, String>() {
            @Override
            protected void onPreExecute() {
              prodel.setTitle("Romving ...");
              prodel.setMessage(
                  "removing ".concat(files.get(_pos).get("path").toString().concat(" now")));
              prodel.setCancelable(false);
              prodel.setCanceledOnTouchOutside(false);
              prodel.show();
            }

            @Override
            protected String doInBackground(String... params) {
              String _param = params[0];
              FileUtil.deleteFile(files.get(_pos).get("path").toString());

              return "";
            }

            @Override
            protected void onPostExecute(String _result) {
              prodel.dismiss();
              _getFiles("");
            }
          }.execute("");
        });
    AlertDialog dialog = di.show();

    dialog.show();
  }

  public void _dataOnClickItemList(int _pos) {
    newpos = _pos;
    if (staticstring.endsWith(".txt")) {
      _checkListMap2(newpos, "path", files, newlistmap);
    }
    if (staticstring.endsWith(".go")) {
      _checkListMap2(newpos, "path", files, newlistmap);
    }
    if (staticstring.endsWith(".css")) {
      _checkListMap2(newpos, "path", files, newlistmap);
    }
    if (staticstring.endsWith(".php")) {
      _checkListMap2(newpos, "path", files, newlistmap);
    }
    if (staticstring.endsWith(".js")) {
      _checkListMap2(newpos, "path", files, newlistmap);
    }
    if (staticstring.endsWith(".html")) {
      _checkListMap2(newpos, "path", files, newlistmap);
    }
    if (staticstring.endsWith(".dart")) {
      _checkListMap2(newpos, "path", files, newlistmap);
    }
    if (staticstring.endsWith(".kt")) {
      _checkListMap2(newpos, "path", files, newlistmap);
    }
    if (staticstring.endsWith(".swift")) {
      _checkListMap2(newpos, "path", files, newlistmap);
    }
    if (staticstring.endsWith(".rb")) {
      _checkListMap2(newpos, "path", files, newlistmap);
    }
    if (staticstring.endsWith(".rbw")) {
      _checkListMap2(newpos, "path", files, newlistmap);
    }
    if (staticstring.endsWith(".c")) {
      _checkListMap2(newpos, "path", files, newlistmap);
    }
    if (staticstring.endsWith(".scss") || staticstring.endsWith(".sass")) {
      _checkListMap2(newpos, "path", files, newlistmap);
    }
    if (staticstring.endsWith(".cs")) {
      _checkListMap2(newpos, "path", files, newlistmap);
    }
    if (staticstring.endsWith(".java")) {
      _checkListMap2(newpos, "path", files, newlistmap);
    }
    if (staticstring.endsWith(".json")) {
      _checkListMap2(newpos, "path", files, newlistmap);
    }
    if (staticstring.endsWith(".cpp")) {
      _checkListMap2(newpos, "path", files, newlistmap);
    }
    if (staticstring.endsWith(".py")) {
      _checkListMap2(newpos, "path", files, newlistmap);
    }
    if (staticstring.endsWith(".ghost")) {
      _checkListMap2(newpos, "path", files, newlistmap);
    }
    if (staticstring.endsWith(".xml")) {
      _checkListMap2(newpos, "path", files, newlistmap);
    }
    if (staticstring.endsWith(".ninja")) {
      _checkListMap2(newpos, "path", files, newlistmap);
    }
    if (staticstring.endsWith(".md")) {
      _checkListMap2(newpos, "path", files, newlistmap);
    }
    if (staticstring.endsWith(".sh")) {
      _checkListMap2(newpos, "path", files, newlistmap);
    }
    if (staticstring.endsWith(".smali")) {
      _checkListMap2(newpos, "path", files, newlistmap);
    }
    if (staticstring.endsWith(".groovy") || staticstring.endsWith(".gradle")) {
      _checkListMap2(newpos, "path", files, newlistmap);
    }
    if (staticstring.endsWith(".g4")) {
      _checkListMap2(newpos, "path", files, newlistmap);
    }
    if (staticstring.endsWith(".ts")) {
      _checkListMap2(newpos, "path", files, newlistmap);
    }
    if (staticstring.endsWith(".svg")) {
      androidx.appcompat.app.AlertDialog dialog =
          new GhostWebMaterialDialog(FiledirActivity.this)
              .setTitle("Svg ")
              .setMessage("توجه داشته باشید با زدن روی Options میتوانید عمل کرد های زیرا تست کنید")
              .setPositiveButton("Options", null)
              .setNegativeButton(android.R.string.cancel, null)
              .create();
      dialog.setOnShowListener(
          (var) -> {
            Button positive = dialog.getButton(DialogInterface.BUTTON_POSITIVE);
            positive.setOnClickListener(
                (vftrororocjj) -> {
                  mmenuVector =
                      new PowerMenu.Builder(FiledirActivity.this)
                          .addItem(new PowerMenuItem("Code Editor"))
                          .addItem(new PowerMenuItem("Cast to Vector"))
                          .addItem(new PowerMenuItem("Cast to Png"))
                          .setIsMaterial(true)
                          .build(); 
                  mmenuVector.showAsDropDown(positive);
                  mmenuVector.setAutoDismiss(true);
                  mmenuVector.setShowBackground(false);
                  mmenuVector.setMenuColor(MaterialColors.getColor(this, ColorAndroid12.Back, 0));
                  mmenuVector.setSelectedMenuColor(0xFFFDA893);
                  mmenuVector.setSelectedEffect(true);
                  mmenuVector.setDividerHeight((int) 2);
                  mmenuVector.setTextColor(
                      MaterialColors.getColor(
                          this,
                          ColorAndroid12.colorOnSurface,
                          0)); 
                  
                  mmenuVector.setCircularEffect(CircularEffect.INNER);
                  mmenuVector.setOnMenuItemClickListener(
                      (position, item) -> {
                        switch ( position) {
                          case 0:
                            {
                              dialog.dismiss();
                              _checkListMap2(newpos, "path", files, newlistmap);
                              break;
                            }
                          case 1:
                            {
                              _vector(staticstring, GetTab.concat("/"));
                              dialog.dismiss();
                              break;
                            }
                          case 2:
                            {
                              _msvgtopng(
                                  files.get((int) newpos).get("path").toString(),
                                  files
                                      .get((int) newpos)
                                      .get("path")
                                      .toString()
                                      .replace(".svg", ".png"));
                              dialog.dismiss();
                              break;
                            }
                        }
                      });
                });
          });

      dialog.show();
    }
    if (staticstring.endsWith(".jar")) {
      _jarPost();
    }
    if (staticstring.endsWith(".mp3")) {
      musicShow.setClass(getApplicationContext(), MusicplayerActivity.class);
      musicShow.putExtra("Path", staticstring);
      musicShow.putExtra("Title", Uri.parse(staticstring).getLastPathSegment());
      startActivity(musicShow);
    }
    if (staticstring.endsWith(".mp4")) {
      govirwFilm.setClass(getApplicationContext(), VideoviewsActivity.class);
      govirwFilm.putExtra("getPath", staticstring);
      govirwFilm.putExtra("getTitle", Uri.parse(staticstring).getLastPathSegment());
      startActivity(govirwFilm);
    }
    if (staticstring.endsWith(".apk")) {
      _apkinit();
    }
    if (staticstring.endsWith(".pdf")) {
      getabout.setClass(getApplicationContext(), PdfviewnewActivity.class);
      getabout.putExtra("pdf", staticstring);
      getabout.putExtra("t", Uri.parse(staticstring).getLastPathSegment());
      startActivity(getabout);
    }
    if (staticstring.endsWith(".tar.gz")) {
      _targz(staticstring, Folder);
    }
    if (staticstring.endsWith(".tar.xz")) {
      _tarzx(staticstring, Folder);
    }
    if (staticstring.endsWith(".7z")) {
      _sevenUnZip(staticstring, Folder);
    }
    if (staticstring.endsWith(".swb")) {
      var myswb = new SwbData(this);
      myswb.init(staticstring);
    }
    
    _zipviewandexsert(newpos, "path", files);
    _fontpost(files, "path", newpos);
    _themeinstall(files, newpos, "path");
    _installproject(files, "path", newpos);
    _insertData(newpos);
  }

  public void _addproject( double _number) {
    var di =
        new MaterialAlertDialogBuilder(this);

    di.setTitle("Add project?");
    di.setMessage("ایا میخواهید یک پروژه بسازید؟");
    di.setPositiveButton(
        "بله",
        (p1, d2) -> {
          new AsyncTask<String, String, String>() {
            @Override
            protected void onPreExecute() {
              copydir.setCanceledOnTouchOutside(false);
              copydir.setTitle("Loading...");
              copydir.setCancelable(false);
              copydir.show();
            }

            @Override
            protected String doInBackground(String... params) {
              String _param = params[0];
              runOnUiThread(
                  new Runnable() {
                    @Override
                    public void run() {
                      if (FileUtil.isDirectory(files.get((int) _number).get("path").toString())) {
                        try {

                          new net.lingala.zip4j.ZipFile(
                                  Folder.concat(
                                      "/"
                                          .concat(
                                              Uri.parse(
                                                      files
                                                          .get((int) _number)
                                                          .get("path")
                                                          .toString())
                                                  .getLastPathSegment()
                                                  .concat(
                                                      String.valueOf(
                                                              (long)
                                                                  (SketchwareUtil.getRandom(
                                                                      (int) (0), (int) (1375))))
                                                          .concat(".project")))))
                              .addFolder(
                                  new java.io.File(
                                      files.get((int) _number).get("path").toString()));

                        } catch (net.lingala.zip4j.exception.ZipException e) {
                          showMessage(e.toString());
                        }
                        _getFiles("");
                      } else {
                        try {

                          new net.lingala.zip4j.ZipFile(
                                  new java.io.File(
                                      Folder.concat(
                                          "/"
                                              .concat(
                                                  Uri.parse(
                                                          files
                                                              .get((int) _number)
                                                              .get("path")
                                                              .toString())
                                                      .getLastPathSegment()
                                                      .concat(
                                                          String.valueOf(
                                                                  (long)
                                                                      (SketchwareUtil.getRandom(
                                                                          (int) (0), (int) (1375))))
                                                              .concat(".project"))))))
                              .addFile(
                                  new java.io.File(
                                      files.get((int) _number).get("path").toString()));

                        } catch (net.lingala.zip4j.exception.ZipException e) {
                          showMessage(e.toString());
                        }
                        _getFiles("");
                      }
                    }
                  });
              return "";
            }

            @Override
            protected void onPostExecute(String _result) {
              copydir.dismiss();
            }
          }.execute("");
        });
    di.setNeutralButton("خیر",null);

    AlertDialog dialog = di.show();

    dialog.show();
  }

  public void _projectinit() {
    maindialogPrfex =
        new com.google.android.material.dialog.MaterialAlertDialogBuilder(FiledirActivity.this)
            .setView(R.layout.projectinitre)
            .setTitle("Make project")
            .setCancelable(true)
            .setPositiveButton("make", null)
            .setNegativeButton(android.R.string.cancel, null)
            .create();
    maindialogPrfex.setOnShowListener(
        (var) -> {
          androidx.recyclerview.widget.RecyclerView post = maindialogPrfex.findViewById(R.id.post);
          Button positive = maindialogPrfex.getButton(DialogInterface.BUTTON_POSITIVE);
          projectdata.clear();
          for (int _repeat20 = 0; _repeat20 < (int) (4); _repeat20++) {
            {
              HashMap<String, Object> _item = new HashMap<>();
              _item.put("dontsetMe", "");
              projectdata.add(_item);
            }
          }
          //post.setAdapter(new RecyclerviewCppAdapter(projectdata));
          post.setLayoutManager(new GridLayoutManager(FiledirActivity.this, (int) 2));
          positive.setOnClickListener(
              (vftrororocjj) -> {
                maindialogPrfex.dismiss();
              });
        });
    maindialogPrfex.show();
  }

  
  public void _vector(final String _input, final String _output) {
    vb.iconPath = _input;
    vb.projectResourceDirectory = _output;
    vb.v(
        FiledirActivity.this,
        () -> {
          _getFiles("");
        });
  }

  public void _hsiunzip(final String _input, final String _output) {
    HsiZip task = new HsiZip(this);
    task.execute(_input, _output);
  }

  

  

  public void _apkinit() {
    String pathToFile = staticstring;
    PackageManager packageManager = getPackageManager();
    PackageInfo packageInfo = packageManager.getPackageArchiveInfo(pathToFile, 0);
    // ساختن متن برای نمایش در دیالوگ
    StringBuilder sb = new StringBuilder();
    sb.append("Package Name: ").append(packageInfo.packageName).append("\n");
    sb.append("Version Name: ").append(packageInfo.versionName).append("\n");
    sb.append("Version Code: ").append(packageInfo.versionCode).append("\n");

    // دریافت نام API با استفاده از minSdkVersion
    int minSdkVersion = packageInfo.applicationInfo.minSdkVersion;
    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
      sb.append("Min SDK Version: ")
          .append(minSdkVersion)
          .append(" (")
          .append(Build.VERSION_CODES.class.getFields()[minSdkVersion].getName())
          .append(")\n");
    } else {
      sb.append("Min SDK Version: ").append(minSdkVersion).append("\n");
    }
    // دریافت آیکون برنامه و نام برنامه
    ApplicationInfo applicationInfo = packageInfo.applicationInfo;
    Drawable icon = applicationInfo.loadIcon(packageManager);
    String appName = applicationInfo.loadLabel(packageManager).toString();
    // ساخت دیالوگ با MaterialAlertDialogBuilder و نمایش آیکون و نام برنامه در آن
    var builder = new GhostWebMaterialDialog(this);
    builder.setTitle(appName);
    builder.setIcon(icon);
    builder.setMessage(sb.toString());
    builder.setPositiveButton(
        "ApkSigner",
        (dd, fff) -> {
          AS.runs(
              staticstring,
              new AS.CallBack() {
                @Override
                public void end() {
                  _getFiles("");
                  Toast.makeText(getApplicationContext(), "Done", Toast.LENGTH_LONG).show();
                }
              });
        });
    builder.setNeutralButton(
        "install",
        (p, d) -> {
          if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O
              && !getPackageManager().canRequestPackageInstalls()) {
            MaterialAlertDialogBuilder cbuilder =
                new MaterialAlertDialogBuilder(FiledirActivity.this);
            cbuilder.setTitle("Application request to install app");
            cbuilder.setMessage("To install the application requires permission.");
            cbuilder.setPositiveButton(
                "Request",
                (cc, fdd) -> {
                  Intent mintent =
                      new Intent(android.provider.Settings.ACTION_MANAGE_UNKNOWN_APP_SOURCES);
                  mintent.setData(Uri.parse("package:" + getPackageName()));
                  startActivity(mintent);
                });
            cbuilder.setNegativeButton("No", null);
            cbuilder.show();
          } else {
            String apkPath = staticstring;
            ApkInstaller apkInstaller = new ApkInstaller(FiledirActivity.this, new File(apkPath));
            apkInstaller.execute();
          }
        });

    builder.show();
  }

  public void _zipfileandFolder(final double _number) {
    var di = new GhostWebMaterialDialog(FiledirActivity.this);
    di.setTitle("Add project?");
    di.setMessage("ایا میخواهید یک پروژه بسازید؟");
    di.setPositiveButton(
        "بله",
        (p1, d2) -> {
          new AsyncTask<String, String, String>() {
            @Override
            protected void onPreExecute() {
              copydir.setCanceledOnTouchOutside(false);
              copydir.setTitle("Loading...");
              copydir.setCancelable(false);
              copydir.show();
            }

            @Override
            protected String doInBackground(String... params) {
              String _param = params[0];
              runOnUiThread(
                  new Runnable() {
                    @Override
                    public void run() {
                      if (FileUtil.isDirectory(files.get((int) _number).get("path").toString())) {
                        try {

                          new net.lingala.zip4j.ZipFile(
                                  Folder.concat(
                                      "/"
                                          .concat(
                                              Uri.parse(
                                                      files
                                                          .get((int) _number)
                                                          .get("path")
                                                          .toString())
                                                  .getLastPathSegment()
                                                  .concat(".zip"))))
                              .addFolder(
                                  new java.io.File(
                                      files.get((int) _number).get("path").toString()));

                        } catch (net.lingala.zip4j.exception.ZipException e) {
                          showMessage(e.toString());
                        }
                        _getFiles("");
                      } else {
                        try {

                          new net.lingala.zip4j.ZipFile(
                                  new java.io.File(
                                      Folder.concat(
                                          "/"
                                              .concat(
                                                  Uri.parse(
                                                          files
                                                              .get((int) _number)
                                                              .get("path")
                                                              .toString())
                                                      .getLastPathSegment()
                                                      .concat(".zip")))))
                              .addFile(
                                  new java.io.File(
                                      files.get((int) _number).get("path").toString()));

                        } catch (net.lingala.zip4j.exception.ZipException e) {
                          showMessage(e.toString());
                        }
                        _getFiles("");
                      }
                    }
                  });
              return "";
            }

            @Override
            protected void onPostExecute(String _result) {
              copydir.dismiss();
            }
          }.execute("");
        });
    di.setNeutralButton("خیر", null);

    di.show();
  }

  public void _jarPost() {
    var di =
        new MaterialAlertDialogBuilder(FiledirActivity.this);

    di.setTitle("لطفا یکی از گزینه های زیر را انتخاب کنید");
    di.setMessage(
        "توجه داشته باشید که برای تغییر jar به dex گزینه مناسب را انتخاب کنید ممکن است تغییرات و یا دیکامپایل کردن چند دقیقه طول بکشد یا حتی این عمل انجام نشود");
    di.setCancelable(false);

    di.setNeutralButton(
        "Dicompile",
        (p, d) -> {
          _dicomplier();
        });
    di.setNegativeButton(
        "viewfile",
        (p3, d3) -> {
          void10.setClass(getApplicationContext(), ZipshowActivity.class);
          void10.putExtra("zipview", staticstring);
          startActivity(void10);
        });
    androidx.appcompat.app.AlertDialog dialog = di.show();

    dialog.show();
  }

  public void _targz(final String _in, final String _ou) {
    var extra =
        new TarGzExtractor(
            this,
            () -> {
              _getFiles("");
            });
    // input    //out
    extra.extract(new File(_in), new File(_ou));
  }

  public void _tarzx(final String _in, final String _ou) {
    var zippos =
        new ZxExtractor(
            this,
            () -> {
              _getFiles("");
            });
    zippos.extract(new File(_in), new File(_ou));
  }

  public void _sevenUnZip(final String _in, final String _ou) {
    // Z7Extractor.extractFile()
    try {
      AlertDialog dialog =
          new MaterialAlertDialogBuilder(this)
              .setTitle("Loading...")
              .setMessage("")
              .setPositiveButton("Done", null)
              .setCancelable(false)
              .create();
      dialog.show();
      new Thread(
              new Runnable() {
                @Override
                public void run() {
                  

                  Z7Extractor.extractFile(
                      _in,
                      _ou,
                      new com.hzy.lib7z.IExtractCallback() {
                        @Override
                        public void onProgress(final String name, final long size) {
                          runOnUiThread(
                              () -> {
                                dialog.setMessage(name);
                              });
                        }

                        @Override
                        public void onError(int errorCode, final String error) {
                          runOnUiThread(
                              () -> {
                                dialog.setMessage("CodeError " + errorCode + "Error " + error);
                              });
                        }

                        @Override
                        public void onSucceed() {
                          runOnUiThread(
                              () -> {
                                if (dialog != null) dialog.dismiss();
                                
                              });
                        }

                        @Override
                        public void onStart() {
                          runOnUiThread(
                              () -> {
                                dialog.setMessage("Starting...");
                                
                              });
                        }

                        @Override
                        public void onGetFileNum(int size) {}
                      });
                }
              })
          .start();
    } catch (Exception E) {
      Toast.makeText(getApplicationContext(), E.getMessage(), 2).show();
    }
  }

  public void _msvgtopng(final String _svg, final String _pngss) {
    androidx.appcompat.app.AlertDialog dialog =
        new GhostWebMaterialDialog(FiledirActivity.this)
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

          SvgShow.GetFileSvg(_svg, img, FiledirActivity.this);
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
                          public void onConversionSuccess() {
                            _getFiles("");
                          }

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

  public void _ftpinstall() {
    androidx.appcompat.app.AlertDialog dialog =
        new GhostWebMaterialDialog(FiledirActivity.this)
            .setView(R.layout.layout_ftp_filedir_download)
            .setTitle("Ftp Downloader")
            .setCancelable(false)
            .setPositiveButton("download", null)
            .setNegativeButton(android.R.string.cancel, null)
            .create();
    dialog.setOnShowListener(
        (var) -> {
          Button positive = dialog.getButton(DialogInterface.BUTTON_POSITIVE);
          EditText ftpserver = dialog.findViewById(R.id.ftpserver);

          EditText ftpport = dialog.findViewById(R.id.ftpport);

          EditText ftpusername = dialog.findViewById(R.id.ftpusername);

          EditText ftppass = dialog.findViewById(R.id.ftppass);

          EditText ftpLocalRemote = dialog.findViewById(R.id.ftpLocalRemote);

          ProgressBar bar = dialog.findViewById(R.id.bar);

          LinearLayout mlinear1 = dialog.findViewById(R.id.mlinear1);

          bar.setVisibility(View.GONE);
          positive.setOnClickListener(
              (vftrororocjj) -> {
                mlinear1.setVisibility(View.GONE);
                if (ftpserver.getText().toString().isEmpty()
                    || ftpusername.getText().toString().isEmpty()
                    || ftpusername.getText().toString().isEmpty()
                    || ftppass.getText().toString().isEmpty()
                    || ftpLocalRemote.getText().toString().isEmpty()) {
                  Toast.makeText(getApplicationContext(), "فیلد خالی است", 2).show();
                }
                int ab = Integer.parseInt(ftpLocalRemote.getText().toString());
                if (ab == 0) return;

                mlinear1.setVisibility(View.GONE);
                FtpDownloader downloader =
                    new FtpDownloader(
                        ftpserver.getText().toString(),
                        ab,
                        ftpusername.getText().toString(),
                        ftppass.getText().toString(),
                        ftpLocalRemote.getText().toString(),
                        Folder,
                        new FtpDownloader.ProgressListener() {
                          @Override
                          public void onProgressUpdate(int percent) {

                            bar.setProgress(percent);
                          }

                          @Override
                          public void onError(String message) {

                            Toast.makeText(getApplicationContext(), message, Toast.LENGTH_SHORT)
                                .show();
                          }

                          @Override
                          public void onDownloadFinished() {

                            Toast.makeText(
                                    getApplicationContext(),
                                    "فایل با موفقیت دانلود شد.",
                                    Toast.LENGTH_SHORT)
                                .show();
                            dialog.dismiss();
                            _getFiles("");
                          }
                        });

                downloader.start();
              });
        });
    dialog.show();
  }

  public void _fos() {
    if (getIntent().hasExtra("home")) {
      Folder = getIntent().getStringExtra("home");
      _getFiles("");
    } else {
      if (getIntent().hasExtra("sd")) {
        Folder = getIntent().getStringExtra("sd");
        _getFiles("");
      } else {
        if (getIntent().hasExtra("root")) {
          Folder = getIntent().getStringExtra("root");
          _getFiles("");
        } else {
          if (getIntent().hasExtra("music")) {
            Folder = getIntent().getStringExtra("music");
            _getFiles("");
          } else {
            if (getIntent().hasExtra("download")) {
              Folder = getIntent().getStringExtra("download");
              _getFiles("");
            } else {
              if (getIntent().hasExtra("themes")) {
                Folder = getIntent().getStringExtra("themes");
                _getFiles("");
              } else {
                if (save_path.contains("path")) {
                  if (FileUtil.isExistFile(save_path.getString("path", ""))) {
                    Folder = save_path.getString("path", "");
                    _getFiles("");
                  } else {
                    Folder = FileUtil.getExternalStorageDir();
                    _getFiles("");
                  }
                } else {
                  Folder = FileUtil.getExternalStorageDir();
                  _getFiles("");
                }
              }
            }
          }
        }
      }
    }
  }

  public void _DrowerHandler() {
    navs.bringToFront();
    navs.setClick(
        (item) -> {
          navs.setCheckedItem(item.getItemId());
          switch ((int) item.getItemId()) {
            case ((int) 1):
              {
                intentgetSettings.setClass(getApplicationContext(), SettingappActivity.class);
                startActivity(intentgetSettings);
                break;
              }
            case ((int) 2):
              {
                getJavaLayoutManager.setClass(
                    getApplicationContext(), JavamanagerlayoutActivity.class);
                getJavaLayoutManager.setFlags(Intent.FLAG_ACTIVITY_SINGLE_TOP);
                startActivity(getJavaLayoutManager);
                break;
              }
            case ((int) 3):
              {
                myketint.setAction(Intent.ACTION_VIEW);
                myketint.setData(Uri.parse("myket://comment?id=Ninja.coder.Ghostemane.code"));
                startActivity(myketint);
                break;
              }
            case ((int) 4):
              {
                void10.setClass(getApplicationContext(), TerminalActivity.class);
                startActivity(void10);
                break;
              }
            case ((int) 5):
              {
                com.blankj.utilcode.util.ThreadUtils.runOnUiThread(
                    new Runnable() {
                      @Override
                      public void run() {

                        if (staticStorage) {
                          staticStorage = false;
                          Folder = FileUtil.getExternalStorageDir();
                          SketchwareUtil.showMessage(
                              getApplicationContext(), FileUtil.getExternalStorageDir());
                          _getFiles("");
                          _drawer.closeDrawer(GravityCompat.START);
                        } else {
                          staticStorage = true;
                          if (sd_stor.hasRealRemovableSdCarde()) {
                            Folder = sd_stor.getExtSdPath();
                            SketchwareUtil.showMessage(
                                getApplicationContext(), sd_stor.getExtSdPath());
                          } else {
                            SketchwareUtil.showMessage(
                                getApplicationContext(), "مموری کارت پیدا نشد متاسفم");
                          }
                          _getFiles("");
                          _drawer.closeDrawer(GravityCompat.START);
                        }
                      }
                    });

                break;
              }
            case ((int) 6):
              {
                getabout.setClass(getApplicationContext(), AboutActivity.class);
                startActivity(getabout);
                break;
              }
            case ((int) 7):
              {
                 var log = new Ninja.coder.Ghostemane.code.GitFamily.GitLog(FiledirActivity.this);
                  log.test();
//                getabout.setClass(getApplicationContext(), LeranActivity.class);
//                startActivity(getabout);
                break;
              }
            case ((int) 8):
              {
                _lojiceinstallK();
                break;
              }
            case ((int) 9):
              {
                if (SketchwareUtil.isConnected(getApplicationContext())) {
                  AppUpdeat.startRequestNetwork(
                      RequestNetworkController.GET,
                      "https://raw.githubusercontent.com/appt2/appt2/main/log.json",
                      "v",
                      _AppUpdeat_request_listener);
                } else {
                  SketchwareUtil.showMessage(getApplicationContext(), "اینترنت خاموش است");
                }
                break;
              }
            case ((int) 10):
              {
                _files1();
                break;
              }
            case ((int) 11):
              {
                intentgetLogCat.setClass(getApplicationContext(), LogcatviewActivity.class);
                startActivity(intentgetLogCat);
                break;
              }
            case ((int) 12):
              {
                var icon = new IconShop(FiledirActivity.this);

                //                intentgetLogCat.setClass(getApplicationContext(),
                // IconmodActivity.class);
                //                startActivity(intentgetLogCat);
                break;
              }
            case ((int) 13):
              {
                intentgetLogCat.setClass(getApplicationContext(), PluginManagerActivity.class);
                startActivity(intentgetLogCat);
                break;
              }
            case ((int) 14):
              {
                intentgetLogCat.setClass(getApplicationContext(), FilebookmarkActivity.class);
                startActivity(intentgetLogCat);
                break;
              }
            case ((int) 15):
              {
                finishAffinity();
                break;
              }
          }
          return false;
        });
  }

  public void _files1() {
    if (FileUtil.isFile("/storage/emulated/0/GhostWebIDE/theme/theme.AA")) {
      var di = new GhostWebMaterialDialog(FiledirActivity.this);
      di.setTitle("هشدار");
      di.setMessage("این فایل در مسیر شما وجود دارد ایا میخواهید این فایل را جایگزین کنید؟");
      di.setNeutralButton(
          "بله",
          (p, d) -> {
            try {

              new net.lingala.zip4j.ZipFile(
                      "/storage/emulated/0/GhostWebIDE/"
                          .concat(
                              String.valueOf(
                                      (long) (SketchwareUtil.getRandom((int) (0), (int) (99999))))
                                  .concat("theme.AA")))
                  .addFolder(new java.io.File("/storage/emulated/0/GhostWebIDE/theme/"));
              SketchwareUtil.showMessage(getApplicationContext(), "انجام شد در پوشه برنامه");

            } catch (net.lingala.zip4j.exception.ZipException e) {
              showMessage(e.toString());
            }
          });
      di.setPositiveButton("خیر", (p1, d2) -> {});

      androidx.appcompat.app.AlertDialog dialog = di.show();
      final View view = dialog.getWindow().getDecorView();
      view.setScaleX(0f);
      view.setScaleY(0f);
      dialog.getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_VISIBLE);
      final ObjectAnimator alertAnim = new ObjectAnimator();
      final ObjectAnimator alertAnim1 = new ObjectAnimator();
      alertAnim.setTarget(view);
      alertAnim.setPropertyName("scaleX");
      alertAnim.setFloatValues((float) (1));
      alertAnim.setDuration((int) (250));
      alertAnim.start();
      alertAnim1.setTarget(view);
      alertAnim1.setPropertyName("scaleY");
      alertAnim1.setFloatValues((float) (1));
      alertAnim1.setDuration((int) (250));
      alertAnim1.start();
      dialog.show();

    } else {
      try {

        new net.lingala.zip4j.ZipFile(
                "/storage/emulated/0/GhostWebIDE/"
                    .concat(
                        String.valueOf((long) (SketchwareUtil.getRandom((int) (0), (int) (9999))))
                            .concat("theme.AA")))
            .addFolder(new java.io.File("/storage/emulated/0/GhostWebIDE/theme/"));
        SketchwareUtil.showMessage(getApplicationContext(), "انجام شد در پوشه برنامه");

      } catch (net.lingala.zip4j.exception.ZipException e) {
        showMessage(e.toString());
      }
    }
  }

  public void _dialogjni() {

    ListSheet sh = new ListSheet();
    sh.setSheetDialog(this);
    sh.addItem("MakeFolder", R.drawable.folder);
    sh.addItem("MakeFile", R.drawable.folders_0_5);
    sh.addItem("MakeProject", R.drawable.textfile);
    sh.addItem("FTP", R.drawable.iconmode);
    sh.addItem("SearchFile(beta)", R.drawable.imgsearch);
    sh.addItem("Folder Tree", R.drawable.foldermultipleplus);
    sh.addItem("FileTree", R.drawable.filemultiple);
    sh.addItem("Git Clone", R.drawable.git);
    sh.setOnItemClickLabe(
        (pos333) -> {
          switch (pos333) {
            case 0:
              {
                _folder();
                break;
              }
            case 1:
              {
                _file();
                break;
              }
            case 2:
              {
                var modl = new FactoryModelProject(FiledirActivity.this,Folder,() ->{
                  _getFiles("");
                });
                break;
              }
            case 3:
              {
                _ftpinstall();
                break;
              }
            case 4:
              {
                filterFile();
                break;
              }
            case 5:
              {
                var di1 = new GhostWebMaterialDialog(FiledirActivity.this);
                ViewGroup viewGroup = findViewById(android.R.id.content);
                View dialogview =
                    getLayoutInflater().inflate(R.layout.makefolder, viewGroup, false);
                EditText editor = dialogview.findViewById(R.id.editor);
                di1.setTitle("File Tree");
                di1.setMessage("Type Name Folder + name Ninja.ninja.app.txt");
                di1.setNeutralButton(
                    "make",
                    (p, d) -> {
                      FileTree.FolderTree(
                          Folder + "/" + editor.getText().toString(),
                          new FileTree.Event() {
                            @Override
                            public void StartEvent() {}

                            @Override
                            public void FinishEvent() {
                              _getFiles("");
                            }

                            @Override
                            public void ErrorEvent(Throwable t) {}

                            @Override
                            public void WorkTotalEvent(int a) {}
                          });
                    });
                di1.setNegativeButton("dismiss", (p3, d3) -> {});

                di1.setView(dialogview);
                di1.show();
                break;
              }
            case 6:
              {
                var di = new GhostWebMaterialDialog(FiledirActivity.this);
                ViewGroup viewGroup = findViewById(android.R.id.content);
                View dialogview1 =
                    getLayoutInflater().inflate(R.layout.makefolder, viewGroup, false);
                EditText editor = dialogview1.findViewById(R.id.editor);
                di.setTitle("Fike Tree");
                di.setMessage("Type Name Folder + name Ninja.ninja.app.txt");
                di.setNeutralButton(
                    "make",
                    (p, d) -> {
                      FileTree.FilesTree(
                          Folder + "/" + editor.getText().toString(),
                          new FileTree.Event() {
                            @Override
                            public void StartEvent() {}

                            @Override
                            public void FinishEvent() {
                              _getFiles("");
                            }

                            @Override
                            public void ErrorEvent(Throwable t) {}

                            @Override
                            public void WorkTotalEvent(int a) {}
                          });
                    });
                di.setNegativeButton("dismiss", (p3, d3) -> {});

                di.setView(dialogview1);
                di.show();
                break;
              }
            case 7:
              {
                _dialoggits();
                break;
              }
          }
        });
  }

  public void _materialYouss() {
    if (materialYou.getString("materialYou", "").equals("true")) {
      if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.S
          && DynamicColors.isDynamicColorAvailable()) {
        DynamicColors.applyToActivityIfAvailable(this);
      }

    } else {

    }
  }

  public void _newItemSheet(int _position, final View _view) {
    final com.google.android.material.bottomsheet.BottomSheetDialog bottomSheetDialog =
        new com.google.android.material.bottomsheet.BottomSheetDialog(FiledirActivity.this);

    View bottomSheetView;
    bottomSheetView = getLayoutInflater().inflate(R.layout.layout_dir_sheet, null);
    bottomSheetDialog.setContentView(bottomSheetView);

    LinearLayout re = (LinearLayout) bottomSheetView.findViewById(R.id.re);
    LinearLayout del = (LinearLayout) bottomSheetView.findViewById(R.id.del);
    LinearLayout pr = (LinearLayout) bottomSheetView.findViewById(R.id.pr);
    LinearLayout sh = (LinearLayout) bottomSheetView.findViewById(R.id.sh);
    LinearLayout zp = (LinearLayout) bottomSheetView.findViewById(R.id.zp);
    LinearLayout mark = (LinearLayout) bottomSheetView.findViewById(R.id.mark);
    re.setOnClickListener(
        new View.OnClickListener() {
          public void onClick(View v) {

            bottomSheetDialog.dismiss();
            _rename(_position);
          }
        });
    del.setOnClickListener(
        new View.OnClickListener() {
          public void onClick(View v) {

            _delFileCustom(_position);
            bottomSheetDialog.dismiss();
          }
        });
    pr.setOnClickListener(
        new View.OnClickListener() {
          public void onClick(View v) {

            _addproject(_position);
            bottomSheetDialog.dismiss();
          }
        });
    sh.setOnClickListener(
        new View.OnClickListener() {
          public void onClick(View v) {

            var fileShareManager = new FileShareManager(FiledirActivity.this);
            File file = new File(files.get((int) _position).get("path").toString());
            try {
              fileShareManager.shareFile(file);
            } catch (Exception e) {
              e.printStackTrace();
            }

            bottomSheetDialog.dismiss();
          }
        });
    zp.setOnClickListener(
        new View.OnClickListener() {
          public void onClick(View v) {

            _zipfileandFolder(_position);
            bottomSheetDialog.dismiss();
          }
        });
    mark.setOnClickListener(
        new View.OnClickListener() {
          public void onClick(View v) {

            if (book.getString("hsipsot4444", "").equals("")) {
              book.edit().putString("hsipsot4444", "[]").apply();
            } else {
              a =
                  new Gson()
                      .fromJson(
                          book.getString("hsipsot4444", ""),
                          new TypeToken<ArrayList<HashMap<String, Object>>>() {}.getType());
              mapz32 = new HashMap<>();
              mapz32.put("list", files.get((int) _position).get("path").toString());
              a.add(mapz32);
              book.edit().putString("hsipsot4444", new Gson().toJson(a)).apply();
              SketchwareUtil.showMessage(
                  getApplicationContext(), book.getString("hsipsot4444", ""));
            }
            bottomSheetDialog.dismiss();
          }
        });
    bottomSheetDialog.show();
  }

  public void _dialog() {
    int position =
        ((androidx.recyclerview.widget.LinearLayoutManager) recyclerview2.getLayoutManager())
            .findFirstVisibleItemPosition();
    var di = new GhostWebMaterialDialog(FiledirActivity.this);
    di.setTitle("File Maket ");
    di.setIcon(R.drawable.file);
    di.setMessage("File maket open file?");
    di.setNeutralButton(
        "open",
        (p, d) -> {
          _dataOnClickItemList(lastPos);
        });
    di.setPositiveButton("no", (p1, d2) -> {});

    di.show();
  }

  public void _dialoggits() {
    // FiledirActivity.this
    androidx.appcompat.app.AlertDialog di =
        new com.google.android.material.dialog.MaterialAlertDialogBuilder(FiledirActivity.this)
            .setPositiveButton("clone", (w, r) -> {})
            .setView(R.layout.layout_gitclone)
            .setCancelable(false)
            .setNegativeButton(android.R.string.cancel, null)
            .create();
    di.setTitle("Git Clone");
    di.setMessage("Match Link Repository like in Download ");

    di.setOnShowListener(
        cc -> {
          EditText et = di.findViewById(R.id.et);
          com.google.android.material.textfield.TextInputLayout input = di.findViewById(R.id.input);
          ProgressBar bar = di.findViewById(R.id.bar);
          TextView tv = di.findViewById(R.id.tv);
          LinearLayout helper = di.findViewById(R.id.helper);
          helper.setVisibility(View.GONE);
          Button button = di.getButton(DialogInterface.BUTTON_POSITIVE);
          button.setOnClickListener(
              c1010108829 -> {
                CloneRepository cloneRepository = new CloneRepository();
                cloneRepository.setDirectory(new File(Folder));
                if (et != null) {
                  cloneRepository.setUrl(et.getText().toString());
                }

                cloneRepository.cloneRp(
                    new CloneRepository.OnGitChange() {
                      @Override
                      public void Start() {
                        runOnUiThread(() -> tv.setText("Start"));
                      }

                      @Override
                      public void BindTask(String string, int i) {

                        runOnUiThread(
                            () -> {
                              bar.setProgress(i);
                              tv.setText(string);
                              helper.setVisibility(View.VISIBLE);
                              input.setVisibility(View.GONE);
                            });
                      }

                      @Override
                      public void UploadData(int upload) {
                        runOnUiThread(
                            () -> {
                              bar.setProgress(upload * 100);
                            });
                      }

                      @Override
                      public void EndTask() {

                        runOnUiThread(
                            () -> {
                              bar.setProgress(0);
                              tv.setText("end");
                              helper.setVisibility(View.GONE);
                              input.setVisibility(View.VISIBLE);
                              di.dismiss();
                              _getFiles("");
                            });
                      }
                    });
              });
        });

    di.show();
  }

  @Override
  public void onLongClick(View view, int pos) {
    _newItemSheet(pos, view);
  }
}
