package Ninja.coder.Ghostemane.code.EditorManagerTask;

import android.content.Context;
import android.os.ParcelFileDescriptor;
import android.graphics.Bitmap;
import android.graphics.pdf.PdfRenderer;
import android.widget.ImageView;
import androidx.core.content.ContextCompat;
import com.bumptech.glide.Glide;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.nio.channels.FileChannel;
import java.io.FileReader;
import java.nio.charset.StandardCharsets;
import org.apache.commons.io.IOUtils;

public class FileCompatApi26 {
   
	public static String roadFileApi26(String file) {
		try {
			FileInputStream inputStream = new FileInputStream(new File(file));
			try {
				return IOUtils.toString(inputStream, StandardCharsets.UTF_8);
			} finally {
				inputStream.close();
			}
		} catch (IOException e) {
			return e.toString();
		}
		
	}
	
	public static void loadPdfPageIntoImageView(String file, ImageView imageView) throws IOException {
		FileInputStream inputStream = new FileInputStream(new File(file));
		try {
			ParcelFileDescriptor fileDescriptor = ParcelFileDescriptor.dup(inputStream.getFD());
			Bitmap bitmap = pdfPageToBitmap(fileDescriptor);
			Glide.with(imageView.getContext()).load(bitmap).into(imageView);
		} finally {
			inputStream.close();
		}
	}
	
	private static Bitmap pdfPageToBitmap(ParcelFileDescriptor fileDescriptor) throws IOException {
		PdfRenderer renderer = new PdfRenderer(fileDescriptor);
		PdfRenderer.Page page = renderer.openPage(0);
		
		// create a new bitmap with the same dimensions as the page
		Bitmap bitmap = Bitmap.createBitmap(page.getWidth(), page.getHeight(), Bitmap.Config.ARGB_8888);
		
		// render the page to the bitmap
		page.render(bitmap, null, null, PdfRenderer.Page.RENDER_MODE_FOR_DISPLAY);
		
		// clean up resources
		page.close();
		renderer.close();
		
		return bitmap;
	}
	
	public static boolean createDirectoryFromApi26(Context context, File parentDir, String dirName) {
		try {
			File dir = new File(parentDir, dirName);
			return context.getApplicationContext().getFilesDir().mkdirs();
		} catch (Exception exception) {
			return false;
		}
	}
	
	public static boolean createFile(File parentDir, String fileName) throws IOException {
		File newFile = new File(parentDir, fileName);
		return newFile.createNewFile();
	}
	
	public static void copyFileFromApi26(String source, String destination) {
		try {
			FileInputStream inputStream = new FileInputStream(new File(source));
			FileOutputStream outputStream = new FileOutputStream(new File(destination));
			FileChannel sourceChannel = inputStream.getChannel();
			FileChannel destinationChannel = outputStream.getChannel();
			sourceChannel.transferTo(0, sourceChannel.size(), destinationChannel);
			sourceChannel.close();
			destinationChannel.close();
			inputStream.close();
			outputStream.close();
		} catch (IOException e) {
			throw new RuntimeException(e);
		}
	}
	
	private static boolean deleteFile(File file) {
		return file.delete();
	}
	
	private static boolean deleteDirectory(File dir) {
		if (dir.isDirectory()) {
			String[] children = dir.list();
			for (int i = 0; i < children.length; i++) {
				boolean success = deleteDirectory(new File(dir, children[i]));
				if (!success) {
					return false;
				}
			}
		}
		return dir.delete();
	}
	
	public static void RomvedFileApi26(File file) {
		if (file.isFile()) {
			deleteFile(file);
		} else if (file.isDirectory()) {
			deleteDirectory(file);
		}
		
	}
}
