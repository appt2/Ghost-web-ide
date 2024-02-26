package Ninja.coder.Ghostemane.code;

import android.animation.ObjectAnimator;
import android.app.Activity;
import android.content.DialogInterface;
import android.graphics.BitmapFactory;
import android.graphics.Bitmap;
import android.os.AsyncTask;
import android.os.Handler;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.TextView;
import androidx.appcompat.app.AlertDialog;
import com.google.android.material.dialog.MaterialAlertDialogBuilder;
import java.util.Arrays;
import android.util.Log;
import android.content.Context;
import android.widget.ImageView;
import Ninja.coder.Ghostemane.code.GlideUtilApi.*;
import java.util.Date;

public class ImageCompressUtil {

  public static void Start(String lastDir, Activity activity, End end) {
    AlertDialog dialog =
        new MaterialAlertDialogBuilder(activity)
            .setView(R.layout.imagecompresslayout)
            .setCancelable(true)
            .setPositiveButton("ok", null)
            .create();
    dialog.setOnShowListener(
        (var) -> {
          ImageView imageComponet = dialog.findViewById(R.id.imageComponet);
          ProgressBar bar = dialog.findViewById(R.id.bar);
          ProgressBar joni = dialog.findViewById(R.id.joni);
          TextView result = dialog.findViewById(R.id.result);
          Button positive = dialog.getButton(DialogInterface.BUTTON_POSITIVE);
          new AsyncTask<String, String, String>() {
            @Override
            protected void onPreExecute() {
              imageComponet.setVisibility(View.GONE);
              joni.setVisibility(View.VISIBLE);
              positive.setEnabled(false);
            }

            @Override
            protected String doInBackground(String... params) {
              String _param = params[0];
              Compress(imageComponet, lastDir, result, joni);
              return "";
            }

            @Override
            protected void onPostExecute(String _result) {
              imageComponet.setVisibility(View.VISIBLE);
              positive.setEnabled(true);
              bar.setVisibility(View.GONE);
              end.onEnd();
            }
          }.execute("");
        });
    final View view = dialog.getWindow().getDecorView();
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

  public static void Compress(ImageView imageView, String dir, TextView textView, ProgressBar bar) {
    Luban.Builder lb = Luban.with(imageView.getContext());
    lb.load(dir);
    lb.ignoreBy((int) 100);
    lb.setTargetDir(new java.io.File(dir).getParent());
    lb.filter(
        new CompressionPredicate() {
          @Override
          public boolean apply(String path) {
            return !(TextUtils.isEmpty(path) || path.toLowerCase().endsWith(".gif"));
          }
        });

    lb.setCompressListener(
        new OnCompressListener() {
          @Override
          public void onStart() {
            textView.setText("Start Task");
            bar.setVisibility(View.VISIBLE);
          }

          @Override
          public void onSuccess(java.io.File file) {
            imageView.post(
                () -> {
                  GlideCompat.GlideNormal(imageView, file);
                  textView.setText(file.toString());
                  Handler handler = new Handler();
                  handler.postDelayed(
                      () -> {
                        long fileLenge = file.length();
                        Date date = new Date();
                        long hours = date.getHours();
                        long min = date.getMinutes();
                        int secned = date.getSeconds();

                        textView.setText(
                            "Saved for in "
                                + hours
                                + ","
                                + min
                                + ","
                                + secned
                                + "in length "
                                + fileLenge);
                      },
                      1000);
                  bar.setVisibility(View.GONE);
                });
          }

          @Override
          public void onError(Throwable e) {
            throw new RuntimeException(e.toString());
          }
        });
    lb.launch();
  }

  private static enum Checker {
    SINGLE;

    private static final String TAG = "Luban";

    private static final String JPG = ".jpg";

    private final byte[] JPEG_SIGNATURE = new byte[] {(byte) 0xFF, (byte) 0xD8, (byte) 0xFF};

    /**
     * Determine if it is JPG.
     *
     * @param is image file input stream
     */
    boolean isJPG(java.io.InputStream is) {
      return isJPG(toByteArray(is));
    }

    /** Returns the degrees in clockwise. Values are 0, 90, 180, or 270. */
    int getOrientation(java.io.InputStream is) {
      return getOrientation(toByteArray(is));
    }

    private boolean isJPG(byte[] data) {
      if (data == null || data.length < 3) {
        return false;
      }
      byte[] signatureB = new byte[] {data[0], data[1], data[2]};
      return Arrays.equals(JPEG_SIGNATURE, signatureB);
    }

    private int getOrientation(byte[] jpeg) {
      if (jpeg == null) {
        return 0;
      }

      int offset = 0;
      int length = 0;

      // ISO/IEC 10918-1:1993(E)
      while (offset + 3 < jpeg.length && (jpeg[offset++] & 0xFF) == 0xFF) {
        int marker = jpeg[offset] & 0xFF;

        // Check if the marker is a padding.
        if (marker == 0xFF) {
          continue;
        }
        offset++;

        // Check if the marker is SOI or TEM.
        if (marker == 0xD8 || marker == 0x01) {
          continue;
        }
        // Check if the marker is EOI or SOS.
        if (marker == 0xD9 || marker == 0xDA) {
          break;
        }

        // Get the length and check if it is reasonable.
        length = pack(jpeg, offset, 2, false);
        if (length < 2 || offset + length > jpeg.length) {
          Log.e(TAG, "Invalid length");
          return 0;
        }

        // Break if the marker is EXIF in APP1.
        if (marker == 0xE1
            && length >= 8
            && pack(jpeg, offset + 2, 4, false) == 0x45786966
            && pack(jpeg, offset + 6, 2, false) == 0) {
          offset += 8;
          length -= 8;
          break;
        }

        // Skip other markers.
        offset += length;
        length = 0;
      }

      // JEITA CP-3451 Exif Version 2.2
      if (length > 8) {
        // Identify the byte order.
        int tag = pack(jpeg, offset, 4, false);
        if (tag != 0x49492A00 && tag != 0x4D4D002A) {
          Log.e(TAG, "Invalid byte order");
          return 0;
        }
        boolean littleEndian = (tag == 0x49492A00);

        // Get the offset and check if it is reasonable.
        int count = pack(jpeg, offset + 4, 4, littleEndian) + 2;
        if (count < 10 || count > length) {
          Log.e(TAG, "Invalid offset");
          return 0;
        }
        offset += count;
        length -= count;

        // Get the count and go through all the elements.
        count = pack(jpeg, offset - 2, 2, littleEndian);
        while (count-- > 0 && length >= 12) {
          // Get the tag and check if it is orientation.
          tag = pack(jpeg, offset, 2, littleEndian);
          if (tag == 0x0112) {
            int orientation = pack(jpeg, offset + 8, 2, littleEndian);
            switch (orientation) {
              case 1:
                return 0;
              case 3:
                return 180;
              case 6:
                return 90;
              case 8:
                return 270;
            }
            Log.e(TAG, "Unsupported orientation");
            return 0;
          }
          offset += 12;
          length -= 12;
        }
      }

      Log.e(TAG, "Orientation not found");
      return 0;
    }

    String extSuffix(InputStreamProvider input) {
      try {
        android.graphics.BitmapFactory.Options options =
            new android.graphics.BitmapFactory.Options();
        options.inJustDecodeBounds = true;
        android.graphics.BitmapFactory.decodeStream(input.open(), null, options);
        return options.outMimeType.replace("image/", ".");
      } catch (Exception e) {
        return JPG;
      }
    }

    boolean needCompress(int leastCompressSize, String path) {
      if (leastCompressSize > 0) {
        java.io.File source = new java.io.File(path);
        return source.exists() && source.length() > (leastCompressSize << 10);
      }
      return true;
    }

    private int pack(byte[] bytes, int offset, int length, boolean littleEndian) {
      int step = 1;
      if (littleEndian) {
        offset += length - 1;
        step = -1;
      }

      int value = 0;
      while (length-- > 0) {
        value = (value << 8) | (bytes[offset] & 0xFF);
        offset += step;
      }
      return value;
    }

    private byte[] toByteArray(java.io.InputStream is) {
      if (is == null) {
        return new byte[0];
      }

      java.io.ByteArrayOutputStream buffer = new java.io.ByteArrayOutputStream();

      int read;
      byte[] data = new byte[4096];

      try {
        while ((read = is.read(data, 0, data.length)) != -1) {
          buffer.write(data, 0, read);
        }
      } catch (Exception ignored) {
        return new byte[0];
      } finally {
        try {
          buffer.close();
        } catch (java.io.IOException ignored) {
        }
      }

      return buffer.toByteArray();
    }
  }

  private static interface CompressionPredicate {

    /**
     * Determine the given input path should be compressed and return a boolean.
     *
     * @param path input path
     * @return the boolean result
     */
    boolean apply(String path);
  }

  private static class Engine {
    private InputStreamProvider srcImg;
    private java.io.File tagImg;
    private int srcWidth;
    private int srcHeight;
    private boolean focusAlpha;

    Engine(InputStreamProvider srcImg, java.io.File tagImg, boolean focusAlpha)
        throws java.io.IOException {
      this.tagImg = tagImg;
      this.srcImg = srcImg;
      this.focusAlpha = focusAlpha;

      android.graphics.BitmapFactory.Options options = new android.graphics.BitmapFactory.Options();
      options.inJustDecodeBounds = true;
      options.inSampleSize = 1;

      android.graphics.BitmapFactory.decodeStream(srcImg.open(), null, options);
      this.srcWidth = options.outWidth;
      this.srcHeight = options.outHeight;
    }

    private int computeSize() {
      srcWidth = srcWidth % 2 == 1 ? srcWidth + 1 : srcWidth;
      srcHeight = srcHeight % 2 == 1 ? srcHeight + 1 : srcHeight;

      int longSide = Math.max(srcWidth, srcHeight);
      int shortSide = Math.min(srcWidth, srcHeight);

      float scale = ((float) shortSide / longSide);
      if (scale <= 1 && scale > 0.5625) {
        if (longSide < 1664) {
          return 1;
        } else if (longSide < 4990) {
          return 2;
        } else if (longSide > 4990 && longSide < 10240) {
          return 4;
        } else {
          return longSide / 1280 == 0 ? 1 : longSide / 1280;
        }
      } else if (scale <= 0.5625 && scale > 0.5) {
        return longSide / 1280 == 0 ? 1 : longSide / 1280;
      } else {
        return (int) Math.ceil(longSide / (1280.0 / scale));
      }
    }

    private android.graphics.Bitmap rotatingImage(android.graphics.Bitmap bitmap, int angle) {
      android.graphics.Matrix matrix = new android.graphics.Matrix();

      matrix.postRotate(angle);

      return android.graphics.Bitmap.createBitmap(
          bitmap, 0, 0, bitmap.getWidth(), bitmap.getHeight(), matrix, true);
    }

    java.io.File compress() throws java.io.IOException {
      android.graphics.BitmapFactory.Options options = new android.graphics.BitmapFactory.Options();
      options.inSampleSize = computeSize();

      android.graphics.Bitmap tagBitmap =
          android.graphics.BitmapFactory.decodeStream(srcImg.open(), null, options);
      java.io.ByteArrayOutputStream stream = new java.io.ByteArrayOutputStream();

      if (Checker.SINGLE.isJPG(srcImg.open())) {
        tagBitmap = rotatingImage(tagBitmap, Checker.SINGLE.getOrientation(srcImg.open()));
      }
      tagBitmap.compress(
          focusAlpha
              ? android.graphics.Bitmap.CompressFormat.PNG
              : android.graphics.Bitmap.CompressFormat.JPEG,
          60,
          stream);
      tagBitmap.recycle();

      java.io.FileOutputStream fos = new java.io.FileOutputStream(tagImg);
      fos.write(stream.toByteArray());
      fos.flush();
      fos.close();
      stream.close();

      return tagImg;
    }
  }

  public abstract static class InputStreamAdapter implements InputStreamProvider {

    private java.io.InputStream inputStream;

    @Override
    public java.io.InputStream open() throws java.io.IOException {
      close();
      inputStream = openInternal();
      return inputStream;
    }

    public abstract java.io.InputStream openInternal() throws java.io.IOException;

    @Override
    public void close() {
      if (inputStream != null) {
        try {
          inputStream.close();
        } catch (java.io.IOException ignore) {
        } finally {
          inputStream = null;
        }
      }
    }
  }

  public static interface InputStreamProvider {

    java.io.InputStream open() throws java.io.IOException;

    void close();

    String getPath();
  }

  public static class Luban implements android.os.Handler.Callback {
    private static final String TAG = "Luban";
    private static final String DEFAULT_DISK_CACHE_DIR = "luban_disk_cache";

    private static final int MSG_COMPRESS_SUCCESS = 0;
    private static final int MSG_COMPRESS_START = 1;
    private static final int MSG_COMPRESS_ERROR = 2;

    private String mTargetDir;
    private boolean focusAlpha;
    private int mLeastCompressSize;
    private OnRenameListener mRenameListener;
    private OnCompressListener mCompressListener;
    private CompressionPredicate mCompressionPredicate;
    private java.util.List<InputStreamProvider> mStreamProviders;

    private android.os.Handler mHandler;

    private Luban(Builder builder) {
      this.mTargetDir = builder.mTargetDir;
      this.mRenameListener = builder.mRenameListener;
      this.mStreamProviders = builder.mStreamProviders;
      this.mCompressListener = builder.mCompressListener;
      this.mLeastCompressSize = builder.mLeastCompressSize;
      this.mCompressionPredicate = builder.mCompressionPredicate;
      mHandler = new android.os.Handler(android.os.Looper.getMainLooper(), this);
    }

    public static Builder with(Context context) {
      return new Builder(context);
    }

    /**
     * Returns a file with a cache image name in the private cache directory.
     *
     * @param context A context.
     */
    private java.io.File getImageCacheFile(Context context, String suffix) {
      if (TextUtils.isEmpty(mTargetDir)) {
        mTargetDir = getImageCacheDir(context).getAbsolutePath();
      }

      String cacheBuilder =
          mTargetDir
              + "/"
              + System.currentTimeMillis()
              + (int) (Math.random() * 1000)
              + (TextUtils.isEmpty(suffix) ? ".jpg" : suffix);

      return new java.io.File(cacheBuilder);
    }

    private java.io.File getImageCustomFile(Context context, String filename) {
      if (TextUtils.isEmpty(mTargetDir)) {
        mTargetDir = getImageCacheDir(context).getAbsolutePath();
      }

      String cacheBuilder = mTargetDir + "/" + filename;

      return new java.io.File(cacheBuilder);
    }

    /**
     * Returns a directory with a default name in the private cache directory of the application to
     * use to store retrieved audio.
     *
     * @param context A context.
     * @see #getImageCacheDir(Context, String)
     */
    private java.io.File getImageCacheDir(Context context) {
      return getImageCacheDir(context, DEFAULT_DISK_CACHE_DIR);
    }

    /**
     * Returns a directory with the given name in the private cache directory of the application to
     * use to store retrieved media and thumbnails.
     *
     * @param context A context.
     * @param cacheName The name of the subdirectory in which to store the cache.
     * @see #getImageCacheDir(Context)
     */
    private static java.io.File getImageCacheDir(Context context, String cacheName) {
      java.io.File cacheDir = context.getExternalCacheDir();
      if (cacheDir != null) {
        java.io.File result = new java.io.File(cacheDir, cacheName);
        if (!result.mkdirs() && (!result.exists() || !result.isDirectory())) {
          // File wasn't able to create a directory, or the result exists but not a directory
          return null;
        }
        return result;
      }
      if (Log.isLoggable(TAG, Log.ERROR)) {
        Log.e(TAG, "default disk cache dir is null");
      }
      return null;
    }

    /** start asynchronous compress thread */
    private void launch(final Context context) {
      if (mStreamProviders == null || mStreamProviders.size() == 0 && mCompressListener != null) {
        mCompressListener.onError(new NullPointerException("image file cannot be null"));
      }

      java.util.Iterator<InputStreamProvider> iterator = mStreamProviders.iterator();

      while (iterator.hasNext()) {
        final InputStreamProvider path = iterator.next();

        android.os.AsyncTask.SERIAL_EXECUTOR.execute(
            new Runnable() {
              @Override
              public void run() {
                try {
                  mHandler.sendMessage(mHandler.obtainMessage(MSG_COMPRESS_START));

                  java.io.File result = compress(context, path);

                  mHandler.sendMessage(mHandler.obtainMessage(MSG_COMPRESS_SUCCESS, result));
                } catch (java.io.IOException e) {
                  mHandler.sendMessage(mHandler.obtainMessage(MSG_COMPRESS_ERROR, e));
                }
              }
            });

        iterator.remove();
      }
    }

    /** start compress and return the file */
    private java.io.File get(InputStreamProvider input, Context context)
        throws java.io.IOException {
      try {
        return new Engine(
                input, getImageCacheFile(context, Checker.SINGLE.extSuffix(input)), focusAlpha)
            .compress();
      } finally {
        input.close();
      }
    }

    private java.util.List<java.io.File> get(Context context) throws java.io.IOException {
      java.util.List<java.io.File> results = new java.util.ArrayList<>();
      java.util.Iterator<InputStreamProvider> iterator = mStreamProviders.iterator();

      while (iterator.hasNext()) {
        results.add(compress(context, iterator.next()));
        iterator.remove();
      }

      return results;
    }

    private java.io.File compress(Context context, InputStreamProvider path)
        throws java.io.IOException {
      try {
        return compressReal(context, path);
      } finally {
        path.close();
      }
    }

    private java.io.File compressReal(Context context, InputStreamProvider path)
        throws java.io.IOException {
      java.io.File result;

      java.io.File outFile = getImageCacheFile(context, Checker.SINGLE.extSuffix(path));

      if (mRenameListener != null) {
        String filename = mRenameListener.rename(path.getPath());
        outFile = getImageCustomFile(context, filename);
      }

      if (mCompressionPredicate != null) {
        if (mCompressionPredicate.apply(path.getPath())
            && Checker.SINGLE.needCompress(mLeastCompressSize, path.getPath())) {
          result = new Engine(path, outFile, focusAlpha).compress();
        } else {
          result = new java.io.File(path.getPath());
        }
      } else {
        result =
            Checker.SINGLE.needCompress(mLeastCompressSize, path.getPath())
                ? new Engine(path, outFile, focusAlpha).compress()
                : new java.io.File(path.getPath());
      }

      return result;
    }

    @Override
    public boolean handleMessage(android.os.Message msg) {
      if (mCompressListener == null) return false;

      switch (msg.what) {
        case MSG_COMPRESS_START:
          mCompressListener.onStart();
          break;
        case MSG_COMPRESS_SUCCESS:
          mCompressListener.onSuccess((java.io.File) msg.obj);
          break;
        case MSG_COMPRESS_ERROR:
          mCompressListener.onError((Throwable) msg.obj);
          break;
      }
      return false;
    }

    public static class Builder {
      private Context context;
      private String mTargetDir;
      private boolean focusAlpha;
      private int mLeastCompressSize = 100;
      private OnRenameListener mRenameListener;
      private OnCompressListener mCompressListener;
      private CompressionPredicate mCompressionPredicate;
      private java.util.List<InputStreamProvider> mStreamProviders;

      Builder(Context context) {
        this.context = context;
        this.mStreamProviders = new java.util.ArrayList<>();
      }

      private Luban build() {
        return new Luban(this);
      }

      public Builder load(InputStreamProvider inputStreamProvider) {
        mStreamProviders.add(inputStreamProvider);
        return this;
      }

      public Builder load(final java.io.File file) {
        mStreamProviders.add(
            new InputStreamAdapter() {
              @Override
              public java.io.InputStream openInternal() throws java.io.IOException {
                return new java.io.FileInputStream(file);
              }

              @Override
              public String getPath() {
                return file.getAbsolutePath();
              }
            });
        return this;
      }

      public Builder load(final String string) {
        mStreamProviders.add(
            new InputStreamAdapter() {
              @Override
              public java.io.InputStream openInternal() throws java.io.IOException {
                return new java.io.FileInputStream(string);
              }

              @Override
              public String getPath() {
                return string;
              }
            });
        return this;
      }

      public <T> Builder load(java.util.List<T> list) {
        for (T src : list) {
          if (src instanceof String) {
            load((String) src);
          } else if (src instanceof java.io.File) {
            load((java.io.File) src);
          } else if (src instanceof android.net.Uri) {
            load((android.net.Uri) src);
          } else {
            throw new IllegalArgumentException(
                "Incoming data type exception, it must be String, File, Uri or Bitmap");
          }
        }
        return this;
      }

      public Builder load(final android.net.Uri uri) {
        mStreamProviders.add(
            new InputStreamAdapter() {
              @Override
              public java.io.InputStream openInternal() throws java.io.IOException {
                return context.getContentResolver().openInputStream(uri);
              }

              @Override
              public String getPath() {
                return uri.getPath();
              }
            });
        return this;
      }

      public Builder putGear(int gear) {
        return this;
      }

      public Builder setRenameListener(OnRenameListener listener) {
        this.mRenameListener = listener;
        return this;
      }

      public Builder setCompressListener(OnCompressListener listener) {
        this.mCompressListener = listener;
        return this;
      }

      public Builder setTargetDir(String targetDir) {
        this.mTargetDir = targetDir;
        return this;
      }

      /**
       * Do I need to keep the image's alpha channel
       *
       * @param focusAlpha
       *     <p>true - to keep alpha channel, the compress speed will be slow.
       *     <p>false - don't keep alpha channel, it might have a black background.
       */
      public Builder setFocusAlpha(boolean focusAlpha) {
        this.focusAlpha = focusAlpha;
        return this;
      }

      /**
       * do not compress when the origin image file size less than one value
       *
       * @param size the value of file size, unit KB, default 100K
       */
      public Builder ignoreBy(int size) {
        this.mLeastCompressSize = size;
        return this;
      }

      /**
       * do compress image when return value was true, otherwise, do not compress the image file
       *
       * @param compressionPredicate A predicate callback that returns true or false for the given
       *     input path should be compressed.
       */
      public Builder filter(CompressionPredicate compressionPredicate) {
        this.mCompressionPredicate = compressionPredicate;
        return this;
      }

      /** begin compress image with asynchronous */
      public void launch() {
        build().launch(context);
      }

      public java.io.File get(final String path) throws java.io.IOException {
        return build()
            .get(
                new InputStreamAdapter() {
                  @Override
                  public java.io.InputStream openInternal() throws java.io.IOException {
                    return new java.io.FileInputStream(path);
                  }

                  @Override
                  public String getPath() {
                    return path;
                  }
                },
                context);
      }

      /**
       * begin compress image with synchronize
       *
       * @return the thumb image file list
       */
      public java.util.List<java.io.File> get() throws java.io.IOException {
        return build().get(context);
      }
    }
  }

  private static interface OnCompressListener {
    void onStart();

    void onSuccess(java.io.File file);

    void onError(Throwable e);
  }

  public static interface OnRenameListener {
    String rename(String filePath);
  }

  public static interface End {
    void onEnd();
  }
}
