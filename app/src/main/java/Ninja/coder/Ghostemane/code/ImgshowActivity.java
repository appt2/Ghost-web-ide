package Ninja.coder.Ghostemane.code;


import Ninja.coder.Ghostemane.code.LayoutBlur.BlurImage;
import Ninja.coder.Ghostemane.code.marco.FileShareManager;
import android.Manifest;
import android.animation.*;
import android.app.*;
import android.content.*;
import android.content.pm.PackageManager;
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
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import androidx.recyclerview.*;
import androidx.recyclerview.selection.*;
import androidx.viewpager.widget.PagerAdapter;
import androidx.viewpager.widget.ViewPager;

import com.blankj.utilcode.util.ThreadUtils;
import com.google.android.material.*;
import com.google.android.material.appbar.MaterialToolbar;
import com.google.android.material.color.MaterialColors;
import com.google.android.material.dialog.MaterialAlertDialogBuilder;
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

import Ninja.coder.Ghostemane.code.GlideUtilApi.*;

public class ImgshowActivity extends BaseCompat {

  private Timer _timer = new Timer();

  
  private double Token = 0;
  private String post = "";
  private String IMAGE_VIEWER_PATH = "";
  private double imageFilesPosition = 0;
  private double imageViewPosition = 0;
  private String data = "";
  private String helper = "";
  private ArrayList<HashMap<String, Object>> viewerListMap = new ArrayList<>();
  private String pathSoft = "";
  private ViewPager viewpager1;
  private Viewpager1Adapter ad;
  private TimerTask time;
  private MaterialToolbar mainToolbar;
  private ImageView blurImage;
  private LinearLayout blur_layout;
  private int pos = 0;
  String datasend = "";

  @Override
  protected void onCreate(Bundle _savedInstanceState) {
    super.onCreate(_savedInstanceState);
    setContentView(R.layout.imgshow);
    initialize(_savedInstanceState);

    if (ContextCompat.checkSelfPermission(this, Manifest.permission.READ_EXTERNAL_STORAGE)
        == PackageManager.PERMISSION_DENIED) {
      ActivityCompat.requestPermissions(
          this, new String[] {Manifest.permission.READ_EXTERNAL_STORAGE}, 1000);
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
    
    viewpager1 = findViewById(R.id.viewpager1);
    mainToolbar = findViewById(R.id.toolbarImageShow);
    blurImage = findViewById(R.id.blur_img);
    ad = new Viewpager1Adapter(this, viewerListMap);
    setSupportActionBar(mainToolbar);
    blur_layout = findViewById(R.id.blur_layout);

    viewpager1.addOnPageChangeListener(
        new ViewPager.OnPageChangeListener() {
          @Override
          public void onPageScrolled(
              int _position, float _positionOffset, int _positionOffsetPixels) {}

          @Override
          public void onPageSelected(int _position) {
            pos = _position;
            datasend = viewerListMap.get(_position).get("path").toString();
            setTitle(
                Uri.parse(viewerListMap.get((int) _position).get("path").toString())
                    .getLastPathSegment());
            data =
                Uri.parse(viewerListMap.get((int) _position).get("path").toString())
                    .getLastPathSegment();
            helper = viewerListMap.get((int) _position).get("path").toString();
            ThreadUtils.runOnUiThread(
                () -> {
                  BlurImage.Start(getWindow().getDecorView(), ImgshowActivity.this, helper, 20);
                });
          }

          @Override
          public void onPageScrollStateChanged(int _scrollState) {}
        });

   // _fab.setOnClickListener(v -> ToolHelper(v));
  }

  private void initializeLogic() {
    _refreshViewer(getIntent().getStringExtra("imagePath"));
    imageViewPosition = 0;
    for (int _repeat33 = 0; _repeat33 < (int) (viewerListMap.size()); _repeat33++) {
      if (viewerListMap.get((int) imageViewPosition).containsKey("path")) {
        if (Uri.parse(viewerListMap.get((int) imageViewPosition).get("path").toString())
            .getLastPathSegment()
            .equals(getIntent().getStringExtra("imageName"))) {
          viewpager1.setCurrentItem((int) imageViewPosition);
          break;
        }
      }
      imageViewPosition++;
    }
    setTitle(getIntent().getStringExtra("imageName"));
    
  }

  public void _gogo(final String _input, final ImageView _img, final TextView _tv) {
    try {
      SvgShow.GetFileSvg(_input, _img, this, _tv);
    } catch (Exception e) {

    }
  }

  public boolean _imageFileType(final String _path) {
    return (_path.endsWith(".jpg")
        || (_path.endsWith(".jpeg")
            || (_path.endsWith(".svg")
                || (_path.endsWith(".tiff")
                    || (_path.endsWith(".png")
                        || (_path.endsWith(".tif")
                            || (_path.endsWith(".bmp")
                                || (_path.endsWith(".gif")
                                    || (_path.endsWith(".webp") || _path.endsWith(".bmp"))))))))));
  }

  public void _refreshViewer(final String _path) {

    viewerListMap.clear();
    IMAGE_VIEWER_PATH = _path;
    final File file = new File(IMAGE_VIEWER_PATH);
    final File[] files = file.listFiles();
    Arrays.sort(files, new FileComparator());
    imageFilesPosition = 0;
    for (int _repeat15 = 0; _repeat15 < (int) (files.length); _repeat15++) {
      if (_imageFileType(files[(int) imageFilesPosition].getAbsolutePath())) {
        {
          HashMap<String, Object> _item = new HashMap<>();
          _item.put("path", files[(int) imageFilesPosition].getAbsolutePath());
          viewerListMap.add(_item);
        }
      }
      imageFilesPosition++;
    }
    viewpager1.setAdapter(ad);
    ((PagerAdapter) viewpager1.getAdapter()).notifyDataSetChanged();
  }

  public void _sortFilesLibrary() {}

  public class FileComparator implements Comparator<File> {
    @Override
    public int compare(File lhs, File rhs) {

      if (lhs.isDirectory()
          == rhs.isDirectory()) { // Both files are directory OR file, compare by name
        return lhs.getName().toLowerCase().compareTo(rhs.getName().toLowerCase());
      } else if (lhs.isDirectory()) { // Directories before files
        return -1;
      } else { // rhs must be a directory
        return 1;
      }
    }
  }

  public class Viewpager1Adapter extends PagerAdapter {

    protected Context _context;
    protected ArrayList<HashMap<String, Object>> _data;
    protected ImageView castImage;

    public Viewpager1Adapter(Context _ctx, ArrayList<HashMap<String, Object>> _arr) {
      _context = _ctx;
      _data = _arr;
    }

    public Viewpager1Adapter(ArrayList<HashMap<String, Object>> _arr) {
      _context = getApplicationContext();
      _data = _arr;
    }

    @Override
    public int getCount() {
      return _data.size();
    }

    @Override
    public boolean isViewFromObject(View _view, Object _object) {
      return _view == _object;
    }

    @Override
    public void destroyItem(ViewGroup _container, int _position, Object _object) {
      _container.removeView((View) _object);
    }

    @Override
    public int getItemPosition(Object _object) {
      return super.getItemPosition(_object);
    }

    @Override
    public CharSequence getPageTitle(int pos) {
      // Use the Activity Event (onTabLayoutNewTabAdded) in order to use this method
      return "page " + String.valueOf(pos);
    }

    @Override
    public Object instantiateItem(ViewGroup _container, final int _position) {
      View _view = LayoutInflater.from(_context).inflate(R.layout.show, _container, false);

      final LinearLayout linear1 = _view.findViewById(R.id.linear1);
      final TextView textview1 = _view.findViewById(R.id.textview1);
      castImage = _view.findViewById(R.id.phimage);
      pathSoft = viewerListMap.get(_position).get("path").toString();
      if (viewerListMap.get((int) _position).containsKey("path")) {
        if (!FileUtil.isDirectory(viewerListMap.get((int) _position).get("path").toString())) {
          if (viewerListMap.get((int) _position).get("path").toString().endsWith(".png")
              || (viewerListMap.get((int) _position).get("path").toString().endsWith(".jpg")
                  || (viewerListMap.get((int) _position).get("path").toString().endsWith(".jpeg")
                      || (viewerListMap.get((int) _position).get("path").toString().endsWith(".gif")
                          || (viewerListMap
                                  .get((int) _position)
                                  .get("path")
                                  .toString()
                                  .endsWith(".mp4")
                              || (viewerListMap
                                      .get(_position)
                                      .get("path")
                                      .toString()
                                      .endsWith(".mkv")
                                  || viewerListMap
                                      .get(_position)
                                      .get("path")
                                      .toString()
                                      .endsWith(".webp"))))))) {

            GlideCompat.GlideNormals(
                castImage, viewerListMap.get(_position).get("path").toString());
            

          } else {
            castImage.setImageResource(R.drawable.file);
          }
        } else {
          castImage.setImageResource(R.drawable.file);
        }
      }

      _container.addView(_view);
      return _view;
    }

    public void setWallpaperImageView(ImageView selectedImage) {
      WallpaperManager wallpaperManager = WallpaperManager.getInstance(getApplicationContext());
      try {
        Drawable drawable = selectedImage.getDrawable();
        if (drawable instanceof BitmapDrawable) {
          Bitmap wallpaperBitmap = ((BitmapDrawable) drawable).getBitmap();
          wallpaperManager.setBitmap(wallpaperBitmap);
          SketchwareUtil.showMessage(getApplicationContext(), "Setting wallpaper completed 🖼️");
        } else {
          SketchwareUtil.showMessage(getApplicationContext(), "No image found in ImageView 🚫");
        }
      } catch (IOException e) {
        SketchwareUtil.showMessage(getApplicationContext(), "Error setting wallpaper ❌");
        e.printStackTrace();
      }
    }

    public ImageView getCastImage() {
      return this.castImage;
    }

    public void setCastImage(ImageView castImage) {
      this.castImage = castImage;
    }
  }

  public void ToolHelper(View v) {
    var dialog =
        new MaterialAlertDialogBuilder(this)
            .setTitle("Item Tools Image")
            .setMessage("This is an auxiliary menu for displaying app orphans.")
            .setPositiveButton("Helper", null)
            .setNegativeButton("dismiss", null)
            .create();
    dialog.setOnShowListener(
        c -> {
          Button btn = dialog.getButton(DialogInterface.BUTTON_POSITIVE);
          btn.setOnClickListener(
              s__s -> {
                PowerMenu menu =
                    new PowerMenu.Builder(this)
                        .addItem(new PowerMenuItem("set Wallpaper"))
                        .addItem(new PowerMenuItem("Share Image"))
                        .build();
                menu.setAutoDismiss(true);
                menu.setMenuColor(MaterialColors.getColor(this, ColorAndroid12.Back, 0));
                menu.setTextColor(MaterialColors.getColor(this, ColorAndroid12.TvColor, 0));
                menu.setMenuRadius(20f);
                menu.setCircularEffect(CircularEffect.BODY);
                menu.setAnimation(MenuAnimation.DROP_DOWN);
                menu.showAsDropDown(btn);
                menu.setSelectedEffect(true);
                menu.setShowBackground(false);

                menu.setOnMenuItemClickListener(
                    (pos, __) -> {
                      if (pos == 0) {
                        var in = new Intent();
                        in.putExtra("img", datasend);
                        in.setClass(getApplicationContext(), SetHomeWallpActivity.class);
                        startActivity(in);
                        dialog.dismiss();
                      } else if (pos == 1) {
                        FileShareManager share = new FileShareManager(this);
                        share.shareFile(new File(pathSoft));
                        dialog.dismiss();
                      }
                    });
              });
        });
    dialog.show();
  }
}
