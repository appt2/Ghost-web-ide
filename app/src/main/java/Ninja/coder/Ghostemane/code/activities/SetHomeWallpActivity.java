package Ninja.coder.Ghostemane.code.activities;

import Ninja.coder.Ghostemane.code.databinding.SetwalpapactivityBinding;
import Ninja.coder.Ghostemane.code.glidecompat.GlideCompat;
import Ninja.coder.Ghostemane.code.utils.ColorAndroid12;
import Ninja.coder.Ghostemane.code.utils.SketchwareUtil;
import android.app.WallpaperManager;
import android.content.res.ColorStateList;
import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.widget.ImageView;
import com.google.android.material.color.MaterialColors;
import com.google.android.material.shape.CornerFamily;
import com.google.android.material.shape.ShapeAppearanceModel;

import java.io.IOException;

public class SetHomeWallpActivity extends BaseCompat {
  private SetwalpapactivityBinding bin;

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    bin = SetwalpapactivityBinding.inflate(getLayoutInflater());
    setContentView(bin.getRoot());
    setSupportActionBar(bin.toolbar);
    GlideCompat.GlideNormal(bin.imgwallpaper, getIntent().getStringExtra("img"));
    bin.fab.setText("Select to Walpaper?");
    var shap = new ShapeAppearanceModel.Builder();
    shap.setAllCorners(CornerFamily.CUT, 30f);
    bin.fab.setShapeAppearanceModel(shap.build());
    bin.toolbar.setBackgroundTintList(
        ColorStateList.valueOf(MaterialColors.getColor(bin.toolbar, ColorAndroid12.Back)));
    bin.toolbar.setTitleTextColor(MaterialColors.getColor(bin.toolbar, ColorAndroid12.TvColor));
    bin.fab.setOnClickListener(v -> setWallpaperImageView(bin.imgwallpaper));
    ClickEffcat(bin.fab);
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
}
