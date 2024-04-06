package Ninja.coder.Ghostemane.code.activities;

import Ninja.coder.Ghostemane.code.R;
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
import com.google.android.material.appbar.MaterialToolbar;
import com.google.android.material.color.MaterialColors;
import com.google.android.material.floatingactionbutton.ExtendedFloatingActionButton;
import com.google.android.material.shape.CornerFamily;
import com.google.android.material.shape.ShapeAppearanceModel;

import java.io.IOException;

public class SetHomeWallpActivity extends BaseCompat {
    private ImageView imgcontext;
    private ExtendedFloatingActionButton fab;
    private MaterialToolbar toolbar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.setwalpapactivity);
        imgcontext = findViewById(R.id.imgwallpaper);
        fab = findViewById(R.id.fab);
        toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setHomeButtonEnabled(true);
        getSupportActionBar().hide();
        GlideCompat.GlideNormal(imgcontext, getIntent().getStringExtra("img"));
        fab.setText("Select to Walpaper?");
        var shap = new ShapeAppearanceModel.Builder();
        shap.setAllCorners(CornerFamily.CUT, 30f);
        fab.setShapeAppearanceModel(shap.build());
        toolbar.setBackgroundTintList(
                ColorStateList.valueOf(MaterialColors.getColor(toolbar, ColorAndroid12.Back)));
        toolbar.setTitleTextColor(MaterialColors.getColor(toolbar, ColorAndroid12.TvColor));
        fab.setOnClickListener(v -> setWallpaperImageView(imgcontext));
        ClickEffcat(fab);
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
