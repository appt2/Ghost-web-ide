package Ninja.coder.Ghostemane.code;

import android.content.ContentResolver;
import android.content.ContentUris;
import android.content.Context;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.ColorFilter;
import android.graphics.ColorMatrix;
import android.graphics.ColorMatrixColorFilter;
import android.graphics.LightingColorFilter;
import android.graphics.Matrix;
import android.graphics.Paint;
import android.graphics.PorterDuff;
import android.graphics.PorterDuffXfermode;
import android.graphics.Rect;
import android.graphics.RectF;
import android.media.ExifInterface;
import android.net.Uri;
import android.os.Environment;
import android.provider.DocumentsContract;
import android.provider.MediaStore;
import android.text.TextUtils;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.net.URLDecoder;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

public class LibraryDownloaderUtils {

    private static void createFolder(String path) {
        if (!isExistFile(path)) {
    	    makeDir(path);
        }
    }

    public static void createLibraryDownloaderFolder() {
        createFolder("/storage/emulated/0/Library Downloader/");
        createFolder("/storage/emulated/0/Library Downloader/.d8_libs/");
    }

    public static String getLibraryDownloaderJarFolder() {
        return "/storage/emulated/0/Library Downloader/.d8_libs/";
    }

    public static String getLibraryDownloaderFolder() {
        return "/storage/emulated/0/Library Downloader/";
    }

    public static boolean isExistFile(String path) {
        File file = new File(path);
        return file.exists();
    }

    public static void makeDir(String path) {
        if (!isExistFile(path)) {
            File file = new File(path);
            file.mkdirs();
        }
    }
}