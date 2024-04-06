package Ninja.coder.Ghostemane.code.activities;

import Ninja.coder.Ghostemane.code.R;
import Ninja.coder.Ghostemane.code.glidecompat.GlideCompat;
import Ninja.coder.Ghostemane.code.marco.FileShareManager;
import Ninja.coder.Ghostemane.code.utils.ColorAndroid12;
import Ninja.coder.Ghostemane.code.utils.FileUtil;
import Ninja.coder.Ghostemane.code.utils.SketchwareUtil;
import Ninja.coder.Ghostemane.code.widget.BlurImage;
import android.Manifest;
import android.app.WallpaperManager;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import androidx.viewpager.widget.PagerAdapter;
import androidx.viewpager.widget.ViewPager;
import com.blankj.utilcode.util.ThreadUtils;
import com.google.android.material.appbar.MaterialToolbar;
import com.google.android.material.color.MaterialColors;
import com.google.android.material.dialog.MaterialAlertDialogBuilder;
import com.skydoves.powermenu.CircularEffect;
import com.skydoves.powermenu.MenuAnimation;
import com.skydoves.powermenu.PowerMenu;
import com.skydoves.powermenu.PowerMenuItem;

import java.io.File;
import java.io.IOException;
import java.util.*;

public class ImgShowActivity extends BaseCompat {

  String datasend = "";
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
    Toast.makeText(getApplicationContext(), "this item : " + viewpager1.getCurrentItem(), 1).show();

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
                  BlurImage.Start(getWindow().getDecorView(), ImgShowActivity.this, helper, 20);
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
                        var pathz =
                            viewerListMap.get(viewpager1.getCurrentItem()).get("path").toString();
                        if (pathz.endsWith(".png")
                            || pathz.endsWith(".jpg")
                            || pathz.endsWith(".jepg")) {
                          var in = new Intent();
                          in.putExtra("img", pathz);
                          in.setClass(getApplicationContext(), SetHomeWallpActivity.class);
                          startActivity(in);
                        } else
                          new MaterialAlertDialogBuilder(ImgShowActivity.this)
                              .setTitle("Error")
                              .setMessage("Please Select Png Jpg Jepg Image for set Wallpaper")
                              .setPositiveButton(android.R.string.ok, null)
                              .show();

                        dialog.dismiss();
                      } else if (pos == 1) {
                        FileShareManager share = new FileShareManager(this);
                        share.shareFile(new File(viewerListMap.get(viewpager1.getCurrentItem()).get("path").toString()));
                        dialog.dismiss();
                      }
                    });
              });
        });
    dialog.show();
  }

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

      final TextView textview1 = _view.findViewById(R.id.textview1);
      castImage = _view.findViewById(R.id.phimage);
      pathSoft = viewerListMap.get(_position).get("path").toString();
      if (viewerListMap.get((int) _position).containsKey("path")) {
        var path = viewerListMap.get(_position).get("path").toString();
        if (!FileUtil.isDirectory(viewerListMap.get((int) _position).get("path").toString())) {
          if (getImageData(path)) {

            GlideCompat.GlideNormals(castImage, path);

          } else if (getSvgLoader(path)) {
            GlideCompat.LoadSvg(path, castImage);
          }
        } else {
          castImage.setImageResource(R.drawable.file);
        }
      }
      castImage.setOnClickListener(__ -> ToolHelper(__));

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

    public boolean getImageData(String path) {
      return path.endsWith(".png") || path.endsWith(".jpg") || path.endsWith(".jepg");
    }

    public boolean getSvgLoader(String path) {
      return path.endsWith(".svg");
    }
  }
}
