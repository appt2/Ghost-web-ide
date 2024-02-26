package Ninja.coder.Ghostemane.code;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.res.Resources;
import android.graphics.drawable.ColorDrawable;
import android.graphics.drawable.Drawable;
import android.graphics.Color;
import android.graphics.drawable.GradientDrawable;
import android.icu.text.Collator;
import android.os.Looper;
import android.os.Handler;
import android.os.Message;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ListView;
import androidx.core.content.res.ResourcesCompat;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.File;
import java.lang.reflect.Method;
import java.io.IOException;
import java.lang.reflect.Field;
import java.io.FileOutputStream;
import java.io.FileInputStream;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;

/*
All Code BY Ninjacoder
Use java 1.8 Up++
*/

public class GhostFile {
	public static Activity r;
	private static double length;
	private static double d;
	private static double count;
	
	
	
	public static void GetCopyFile(Activity oo, String source, String target) {
		GradientDrawable gr = new GradientDrawable();
		gr.setColor(Color.parseColor("#1F1B1C"));
		gr.setStroke(1,Color.parseColor("#FFB59C"));
		gr.setCornerRadius(14f);
		ProgressDialog progressDialog = new ProgressDialog(oo, ProgressDialog.THEME_DEVICE_DEFAULT_DARK);
		progressDialog.setProgressStyle(ProgressDialog.STYLE_HORIZONTAL);
		progressDialog.setTitle("CopyFile");
		progressDialog.setMessage("Copy File please whait...");
		progressDialog.getWindow().setBackgroundDrawable(gr);
		progressDialog.setCancelable(false);
		progressDialog.getWindow().getDecorView().setPadding(12, 12, 12, 12);
		progressDialog.show();
		
		Thread thread = new Thread(new Runnable() {
			
			@Override
			public void run() {
				
				oo.runOnUiThread(new Runnable() {
					@Override
					public void run() {
						progressDialog.show();
					}
				});
				
				File sourceFile = new File(source);
				File targetFile = new File(target);
				
				FileInputStream input = null;
				FileOutputStream output = null;
				try {
					input = new FileInputStream(sourceFile);
					output = new FileOutputStream(targetFile);
					
					length = sourceFile.length();
					count = 0;
					byte[] b = new byte[4 * 1024];
					int r;
					while ((r = input.read(b)) != -1) {
						count += r;
						oo.runOnUiThread(new Runnable() {
							@Override
							public void run() {
								progressDialog.setProgress((int) ((count * 100) / length));
							}
						});
						output.write(b, 0, r);
					}
					
				} catch (IOException e) {
					e.printStackTrace();
				} finally {
					try {
						if (input != null)
						input.close();
						if (output != null)
						output.close();
					} catch (IOException e) {
						e.printStackTrace();
					}
				}
				
				oo.runOnUiThread(new Runnable() {
					@Override
					public void run() {
						progressDialog.dismiss();
					}
				});
				
			}
		});
		thread.start();
		//GetCopyFile(this,"","");
		
	}
	
	public static void GetCutFile(Activity oo, String source, String target) {
		GradientDrawable gf = new GradientDrawable();
		gf.setColor(Color.parseColor("#1F1B1C"));
		gf.setStroke(1,Color.parseColor("#FFB59C"));
		gf.setCornerRadius(14f);
		ProgressDialog progressDialog = new ProgressDialog(oo, ProgressDialog.THEME_DEVICE_DEFAULT_DARK);
		progressDialog.setProgressStyle(ProgressDialog.STYLE_HORIZONTAL);
		progressDialog.setTitle("CutFile");
		progressDialog.setMessage("Move File please whait...");
		progressDialog.getWindow().setBackgroundDrawable(gf);
		progressDialog.setCancelable(false);
		progressDialog.getWindow().getDecorView().setPadding(12, 12, 12, 12);
		progressDialog.show();
		
		Thread thread = new Thread(new Runnable() {
			
			@Override
			public void run() {
				
				oo.runOnUiThread(new Runnable() {
					@Override
					public void run() {
						progressDialog.show();
					}
				});
				
				File sourceFile = new File(source);
				File targetFile = new File(target);
				
				FileInputStream input = null;
				FileOutputStream output = null;
				try {
					input = new FileInputStream(sourceFile);
					output = new FileOutputStream(targetFile);
					
					length = sourceFile.length();
					count = 0;
					byte[] b = new byte[4 * 1024];
					int r;
					while ((r = input.read(b)) != -1) {
						count += r;
						oo.runOnUiThread(new Runnable() {
							@Override
							public void run() {
								progressDialog.setProgress((int) ((count * 100) / length));
							}
						});
						output.write(b, 0, r);
					}
					
				} catch (IOException e) {
					e.printStackTrace();
				} finally {
					try {
						if (input != null)
						input.close();
						if (output != null)
						output.close();
					} catch (IOException e) {
						e.printStackTrace();
					}
				}
				
				oo.runOnUiThread(new Runnable() {
					@Override
					public void run() {
						sourceFile.delete();
						progressDialog.dismiss();
					}
				});
				
			}
		});
		thread.start();
		//GetCutFile(this,"","");
		
	}
	
	public static void SortListFileandFolder(ArrayList<String> hsi) {
		
		final class FileComparator implements Comparator<String> {
			public int compare(String f1, String f2) {
				if (f1 == f2)
				return 0;
				if (FileUtil.isDirectory(f1) && FileUtil.isFile(f2))
				return -1;
				if (FileUtil.isFile(f1) && FileUtil.isDirectory(f2))
				return 1;
				return f1.compareToIgnoreCase(f2);
			}
		}
		Collections.sort(hsi, new FileComparator());
		//SortListFileToFolder(hsi);
	}
	
	public static void SortListFileToFolder(ArrayList<String> hsi) {
		class FileComparator implements Comparator<String> {
			public int compare(String f1, String f2) {
				if (f2 == f1)
				return 0;
				if (FileUtil.isDirectory(f2) && FileUtil.isFile(f1))
				return -1;
				if (FileUtil.isFile(f2) && FileUtil.isDirectory(f1))
				return 1;
				return f2.compareToIgnoreCase(f1);
			}
			
		}
		Collections.sort(hsi, new FileComparator());
		//	SortListFileToFolder(hsi);
	}
	
	public static void SortAToZ(ArrayList<String> hsi) {
		Collections.sort(hsi, Collator.getInstance());
		//	SortAToZ(hsi);
	}
	
	public static void SortToNumber(ArrayList<String> hsi) {
		
		try {
			Collections.sort(hsi);
		} catch (Exception e) {
			e.printStackTrace();
			
		}
		///How to use?
		//SortToNumber(hsi);
	}
	
	public static void RoadFastText(String input, EditText editText) {
		
		try {
			File file = new File(input);
			
			BufferedReader bufferedReader = new BufferedReader(new FileReader(file));
			StringBuilder result = new StringBuilder();
			String line = null;
			while ((line = bufferedReader.readLine()) != null) {
				result.append(line + "\n");
			}
			
			editText.setText(result.toString());
			
		} catch (IOException e) {
			e.printStackTrace();
		}
		//RoadFastText("",EditText1);
	}
	
	public static void SearchListMap(ArrayList<HashMap<String, Object>> map, String nameToSearch, String key) {
		
		double length = map.size();
		
		d = map.size() - 1;
		for (int i = 0; i < (int) (length); i++) {
			final String serching = map.get((int) d).get(key).toString();
			if (serching.toLowerCase().contains(nameToSearch.toLowerCase())) {
				
			} else {
				map.remove((int) (d));
			}
			d--;
		}
		///SearchListMap(map,"","");
	}
	
	
	public static void RomvedFile(Activity activity ,String input) {
		
		final ProgressDialog progressDialog = new ProgressDialog(activity);
		Thread copyingThread;
		GradientDrawable g = new GradientDrawable();
		g.setColor(Color.parseColor("#1F1B1C"));
		g.setStroke(1,Color.parseColor("#FFB59C"));
		g.setCornerRadius(15);
		
		
		final Handler mainHandler = new Handler(Looper.getMainLooper());
		progressDialog.setProgressStyle(ProgressDialog.STYLE_HORIZONTAL);
		progressDialog.setTitle("Deleting");
		progressDialog.setMessage("Deleting...");
		progressDialog.setIndeterminate(false);
		progressDialog.setCancelable(false);
		progressDialog.getWindow().getDecorView().setPadding(9,9,9,9);
		progressDialog.getWindow().setBackgroundDrawable(g);
		progressDialog.show();
		
		copyingThread = new Thread(new Runnable() {
			@Override
			public void run() {
				
				java.io.File sourceFile = new java.io.File(input);
				//  java.io.File destFile = new java.io.File("/storage/emulated/0/documents/test.mp4");
				java.io.FileInputStream fileInputStream;
				java.io.FileOutputStream fileOutputStream;
				long length = sourceFile.length();
				int bytesRead;
				int totalBytesRead = 0;
				byte[] buffer = new byte[4 * 1024]; // 4KB buffer
				try {
					fileInputStream = new java.io.FileInputStream(sourceFile);
					// fileOutputStream = new java.io.FileOutputStream(destFile);
					
					while (!Thread.currentThread().isInterrupted()
					&& (bytesRead = fileInputStream.read(buffer)) != -1) {
						
						//  fileOutputStream.write(buffer, 0, bytesRead);
						sourceFile.delete();
						
						totalBytesRead += bytesRead;
						progressDialog.setMax((int) sourceFile.length());
						final int fin = (int) (totalBytesRead);
						
						mainHandler.post(new Runnable() {
							@Override
							public void run() {
								progressDialog.setProgress(fin);
								
								
								
							}
							
						});
						if (fin == progressDialog.getMax()) {
							progressDialog.dismiss();
						}
					}
					
				} catch (Exception e) {
					System.out.println("foo");
					///	SketchwareUtil.showMessage(getApplicationContext(), e.toString());
				}
			}
			
		});
		copyingThread.start();
		
	}
	
}
