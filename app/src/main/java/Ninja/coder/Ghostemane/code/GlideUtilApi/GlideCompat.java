package Ninja.coder.Ghostemane.code.GlideUtilApi;

import Ninja.coder.Ghostemane.code.ApplicationLoader;
import Ninja.coder.Ghostemane.code.ColorAndroid12;
import Ninja.coder.Ghostemane.code.R;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.graphics.PorterDuff;
import android.graphics.drawable.Drawable;
import android.graphics.pdf.PdfRenderer;
import android.os.ParcelFileDescriptor;
import android.media.MediaMetadataRetriever;
import android.util.Log;
import android.widget.ImageView;
import android.widget.TextView;
import Ninja.coder.Ghostemane.code.*;
import androidx.swiperefreshlayout.widget.CircularProgressDrawable;
import com.blankj.utilcode.util.ThreadUtils;
import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.bumptech.glide.Priority;
import com.caverock.androidsvg.SVG;
import android.graphics.drawable.PictureDrawable;
import com.google.android.material.color.MaterialColors;
import com.sdsmdg.harjot.vectormaster.VectorMasterDrawable;
import java.io.IOException;
import java.io.FileInputStream;
import java.io.File;

public class GlideCompat {

  public static void GlideNormal(ImageView imageView, String path) {
    Glide.with(imageView.getContext())
        .load(new File(path))
        .placeholder(CircelPrograssBar())
        .diskCacheStrategy(DiskCacheStrategy.ALL)
        .priority(Priority.HIGH)
        .transform(new RoundedCornersTransformation(RenderSize()))
        .into(imageView);
  }

  public static void GlideNormal(ImageView imageView, int path) {
    Glide.with(imageView.getContext())
        .load(path)
        .placeholder(CircelPrograssBar())
        .diskCacheStrategy(DiskCacheStrategy.ALL)
        .priority(Priority.HIGH)
        .transform(new RoundedCornersTransformation(RenderSize()))
        .into(imageView);
  }

  public static void GlideNormals(ImageView imageView, String path) {
    Glide.with(imageView.getContext())
        .load(new File(path))
        .placeholder(CircelPrograssBar())
        .diskCacheStrategy(DiskCacheStrategy.ALL)
        .priority(Priority.HIGH)
        .transform(new RoundedCornersTransformation(0))
        .into(imageView);
  }

  public static void GlideLoadMp3(ImageView img, String path) {
    Log.d("AlbumPic", "Path: " + path);

    new Thread(
            new Runnable() {
              @Override
              public void run() {
                try {
                  MediaMetadataRetriever retriever = new MediaMetadataRetriever();
                  retriever.setDataSource(path);
                  final byte[] art = retriever.getEmbeddedPicture();

                  ThreadUtils.runOnUiThread(
                      new Runnable() {
                        @Override
                        public void run() {
                          Log.d("AlbumPic", "Path: " + path);

                          if (art != null && art.length > 0) {
                            BitmapFactory.Options options = new BitmapFactory.Options();
                            options.inSampleSize = 2; // Ajusta según sea necesario
                            Bitmap bitmap =
                                BitmapFactory.decodeByteArray(art, 0, art.length, options);
                            Glide.with(img.getContext())
                                .load(bitmap)
                                .placeholder(CircelPrograssBar())
                                .error(R.drawable.musico)
                                .diskCacheStrategy(DiskCacheStrategy.ALL)
                                .priority(Priority.HIGH)
                                .transform(new RoundedCornersTransformation(RenderSize()))
                                .into(img);
                          } else {
                            img.setImageResource(R.drawable.musico);
                          }
                        }
                      });
                } catch (final Exception e) {
                  ThreadUtils.runOnUiThread(
                      new Runnable() {
                        @Override
                        public void run() {
                          img.setImageResource(R.drawable.musico);
                        }
                      });
                }
              }
            })
        .start();
  }

  public static void GlideNormal(ImageView imageView, File path) {
    Glide.with(imageView.getContext())
        .load(path)
        .placeholder(CircelPrograssBar())
        .diskCacheStrategy(DiskCacheStrategy.ALL)
        .priority(Priority.HIGH)
        .error(R.drawable.keyboardlisnertalluserpost_4)
        .transform(new RoundedCornersTransformation(RenderSize()))
        .into(imageView);
  }

  public static void LoadMp4(ImageView imageView, String string) {
    Glide.with(imageView.getContext())
        .load(new File(string))
        .placeholder(CircelPrograssBar())
        .transform(new RoundedCornersTransformation(RenderSize()))
        .priority(Priority.HIGH)
        .error(R.drawable.keyboardlisnertalluserpost_4)
        .diskCacheStrategy(DiskCacheStrategy.ALL)
        .into(imageView);
  }

  public static void LoadImage(ImageView imageView, String string, TextView textView) {
    Glide.with(imageView.getContext())
        .load(new File(string))
        .placeholder(CircelPrograssBar())
        .transform(new RoundedCornersTransformation(RenderSize()))
        .priority(Priority.HIGH)
        .diskCacheStrategy(DiskCacheStrategy.ALL)
        .error(R.drawable.close)
        .into(imageView);
    getSizeImage(string, textView);
  }

  public static void LoadVideo(ImageView imageView, String string, TextView textView) {
    Glide.with(imageView.getContext())
        .load(string)
        .transform(new RoundedCornersTransformation(RenderSize()))
        .error(R.drawable.close)
        .placeholder(CircelPrograssBar())
        .diskCacheStrategy(DiskCacheStrategy.ALL)
        .priority(Priority.HIGH)
        .into(imageView);
    textView.setText(getVideoWhich(string).concat(",".concat(getVideohighlights(string))));
  }

  public static Drawable CircelPrograssBar() {
    int[] apl = {
      Color.parseColor("#FFFFB584"),
      Color.parseColor("#FFFF8884"),
      Color.parseColor("#FFDAFF84"),
      Color.parseColor("#FF84FFB1"),
      Color.parseColor("#FF84FFD8"),
      Color.parseColor("#FF84FDFF"),
      Color.parseColor("#FF84D4FF"),
      Color.parseColor("#FF8A84FF"),
      Color.parseColor("#FFB584FF"),
      Color.parseColor("#FFF984FF"),
      Color.parseColor("#FFFF84D6"),
      Color.parseColor("#FFFF84B3")
    };
    CircularProgressDrawable app = new CircularProgressDrawable(ApplicationLoader.getContext());
    app.setStrokeWidth(10f);
    app.setCenterRadius(20f);
    app.setColorSchemeColors(apl);
    app.start();
    return app;
  }

  protected static String getVideoWhich(String d) {
    MediaMetadataRetriever mediaMetadataRetriever = new MediaMetadataRetriever();
    mediaMetadataRetriever.setDataSource(d);
    return mediaMetadataRetriever.extractMetadata(18);
  }

  protected static String getVideohighlights(String dataBase) {
    MediaMetadataRetriever mediaMetadataRetriever = new MediaMetadataRetriever();
    mediaMetadataRetriever.setDataSource(dataBase);
    return mediaMetadataRetriever.extractMetadata(19);
  }

  private static final void getSizeImage(String string, TextView textView) {
    BitmapFactory.Options options = new BitmapFactory.Options();
    options.inJustDecodeBounds = true;
    BitmapFactory.decodeFile(string, options);
    int imageHeight = options.outHeight;
    int imageWidth = options.outWidth;
    textView.setText(String.valueOf(imageWidth).concat("x".concat(String.valueOf(imageHeight))));
  }

  public static void loadImgPdf(File file, ImageView imageView) throws IOException {
    FileInputStream inputStream = new FileInputStream(file);
    try {
      ParcelFileDescriptor fileDescriptor = ParcelFileDescriptor.dup(inputStream.getFD());
      Bitmap bitmap = pdfPageToBitmap(fileDescriptor);
      Glide.with(imageView.getContext())
          .load(bitmap)
          .transform(new RoundedCornersTransformation(RenderSize()))
          .placeholder(CircelPrograssBar())
          .diskCacheStrategy(DiskCacheStrategy.ALL)
          .priority(Priority.HIGH)
          .error(android.R.drawable.gallery_thumb)
          .into(imageView);
    } finally {
      inputStream.close();
    }
  }

  private static Bitmap pdfPageToBitmap(ParcelFileDescriptor fileDescriptor) throws IOException {
    PdfRenderer renderer = new PdfRenderer(fileDescriptor);
    PdfRenderer.Page page = renderer.openPage(0);
    // create a new bitmap with the same dimensions as the page
    Bitmap bitmap = Bitmap.createBitmap(page.getWidth(), page.getHeight(), Bitmap.Config.ARGB_8888);
    page.render(bitmap, null, null, PdfRenderer.Page.RENDER_MODE_FOR_DISPLAY);
    page.close();
    renderer.close();
    return bitmap;
  }

  public static void LoadVector(String path, ImageView c) {
    var vx = new VectorMasterDrawable(ApplicationLoader.getContext(), new File(path));
    if (vx.isVector()) {
      vx.setColorFilter(MaterialColors.getColor(c, ColorAndroid12.TvColor), PorterDuff.Mode.SRC_IN);
      Glide.with(c.getContext())
          .load(vx)
          .error(R.drawable.errorxml)
          .transform(new RoundedCornersTransformation(RenderSize()))
          .placeholder(CircelPrograssBar())
          .diskCacheStrategy(DiskCacheStrategy.ALL)
          .priority(Priority.HIGH)
          .into(c);
    } else {
      c.setImageResource(R.drawable.errorxml);
    }
  }

  public static void LoadSvg(String path, ImageView c) {
    Glide.with(c.getContext())
        .load(loadSvg(path))
        .error(R.drawable.errorxml)
        //       .transform(new RoundedCornersTransformation(RenderSize()))
        .placeholder(CircelPrograssBar())
        .diskCacheStrategy(DiskCacheStrategy.ALL)
        .priority(Priority.NORMAL)
        .into(c);
  }

  protected static int RenderSize() {
    return 25;
  }

  protected static Drawable loadSvg(String path) {
    Drawable drawable = null;
    try {
      FileInputStream fileInputStream = new FileInputStream(new File(path));
      SVG svg = SVG.getFromInputStream(fileInputStream);
      drawable = new PictureDrawable(svg.renderToPicture());

    } catch (Exception e) {
      e.printStackTrace();

      //	Toast.makeText(getApplicationContext(), e.toString(), 3000).show();
    }
    return drawable;
  }
}
