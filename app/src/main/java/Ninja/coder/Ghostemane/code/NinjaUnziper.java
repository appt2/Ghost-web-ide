package Ninja.coder.Ghostemane.code;

import android.app.Activity;
import java.io.FileOutputStream;
import java.io.IOException;
import android.app.ProgressDialog;
import java.util.ArrayList;
import java.util.List;
import java.util.zip.ZipFile;
import java.util.zip.ZipEntry;
import java.io.File;
import java.util.zip.ZipInputStream;
import java.io.FileInputStream;
import android.graphics.drawable.GradientDrawable;
import java.util.zip.ZipOutputStream;

public class NinjaUnziper {
	private static ProgressDialog progressDialog;
	private static ProgressDialog hsi;
	
	public static void unziper(Activity activity, String title, String massges, String input, String output) {
		
		///result code By Ninja coder.ir my love java
		
		GradientDrawable back = new GradientDrawable();
		back.setColor(0xFF1F1B1C);
		back.setCornerRadius(25);
		back.setStroke(2, 0xFFFDA893);
		progressDialog = new ProgressDialog(activity, ProgressDialog.THEME_DEVICE_DEFAULT_DARK);
		progressDialog.setMessage(massges);
        progressDialog.setProgressStyle(ProgressDialog.STYLE_HORIZONTAL);
		progressDialog.setTitle(title);
		progressDialog.getWindow().setBackgroundDrawable(back);
		Thread thread = new Thread(() -> {
			
			FileInputStream fis = null;
			ZipInputStream zis = null;
			try {
				activity.runOnUiThread(() -> {
					
					progressDialog.show();
					
				});
				File source = new File(input);
				File target = new File(output);
				byte[] buffer = new byte[4 * 1024];
				target.mkdirs();
				fis = new FileInputStream(source);
				zis = new ZipInputStream(fis);
				ZipEntry ze = zis.getNextEntry();
				final int length = new ZipFile(source).size();
				int counter = 0;
				while (ze != null) {
					String fileName = ze.getName();
					File newFile = new File(target + File.separator + fileName);
					if (fileName.endsWith("/")) {
						newFile.mkdirs();
						zis.closeEntry();
						ze = zis.getNextEntry();
						continue;
					}
					
					File parent = newFile.getParentFile();
					if (parent != null)
					parent.mkdirs();
					
					FileOutputStream fos = new FileOutputStream(newFile);
					int len;
					while ((len = zis.read(buffer)) > 0)
					fos.write(buffer, 0, len);
					fos.close();
					zis.closeEntry();
					ze = zis.getNextEntry();
					counter++;
					
					final double fi = (double) counter;
					activity.runOnUiThread(() -> {
						
						progressDialog.setProgress((int) ((fi / length) * 100));
						
					});
				}
				
			} catch (IOException e) {
				e.printStackTrace();
			} finally {
				try {
					if (fis != null)
					fis.close();
					if (zis != null) {
						zis.closeEntry();
						zis.close();
					}
				} catch (IOException e) {
					e.printStackTrace();
				}
				
				activity.runOnUiThread(() -> {
					
					progressDialog.dismiss();
					
				});
			}
			
		});
		thread.start();
		
	}
	
}
