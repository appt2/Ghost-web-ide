package Ninja.coder.Ghostemane.code;

import android.app.Activity;
import android.content.Context;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.graphics.drawable.GradientDrawable;
import android.os.AsyncTask;
import android.os.Looper;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.Toast;
import java.io.File;
import java.util.Timer;
import java.util.TimerTask;
import android.app.ProgressDialog;
import net.lingala.zip4j.exception.ZipException;
import net.lingala.zip4j.ZipFile;
import net.lingala.zip4j.progress.ProgressMonitor;
import com.google.android.material.dialog.MaterialAlertDialogBuilder;

public class NinjaZipPost {
	/*
	*Code by Ninja coder and my bro AmirAli Help to Fixed Zip File 
	*How to use 
	* input = dir + zip file 
	* output = dir
	* unzipAll(this,"input","output");
	* zipFile
	* follow me to install Code 
	* input = set Dir not add zip format
	* output = set Dir and zip File
	* input = /sdcard/Android/
	* output = /sdcard/Android/Android.zip
	* ZipFile(this,"input","output");
	*/

	public static void unzipAll(Activity activity, String input, String output) {
		/*
		* @param This run in Progress Dialog
		* @param Select Activity
		* @param Select input dont Use Java.io.File 
		* @param Select OutPut dont Use Java.io.File 
		*/

		GradientDrawable u = new GradientDrawable();
		u.setColor(0xFF2B2122);
		u.setCornerRadius(25);
		u.setStroke(1, 0xFFF8B09A);
		final ProgressDialog dialog = new ProgressDialog(activity, ProgressDialog.THEME_DEVICE_DEFAULT_DARK);
		dialog.setTitle("Unzip.....");
		dialog.getWindow().setBackgroundDrawable(u);
		dialog.setProgressStyle(ProgressDialog.STYLE_HORIZONTAL);
		dialog.setMax((int) 100);
		dialog.show();
		Thread thread = new Thread(() -> {
			ZipFile zipFile = new ZipFile(input);
			zipFile.setRunInThread(true);
			try {
				zipFile.extractAll(output);
			} catch (ZipException e) {
				throw new RuntimeException(e);
			}
			ProgressMonitor progressMonitor = zipFile.getProgressMonitor();
			while (!progressMonitor.getState().equals(ProgressMonitor.State.READY)) {

				activity.runOnUiThread(() -> {
					dialog.setProgress(progressMonitor.getPercentDone());
				});

			}
			activity.runOnUiThread(() -> {
				if (!progressMonitor.getState().equals(ProgressMonitor.Result.SUCCESS)) {
					dialog.dismiss();
					Dialogok(activity, "Done");
				} else if (!progressMonitor.getState().equals(ProgressMonitor.Result.ERROR)) {
					dialog.dismiss();
					DialogError(activity, "Error");
				}
			});
		});
		thread.start();

	}

	public static void unzipAll(Activity activity, String input, String output, ProgressBar dialog) {
		/*
		* @param This run in Progress Dialog
		* @param Select Activity
		* @param Select input dont Use Java.io.File 
		* @param Select OutPut dont Use Java.io.File 
		*/
		dialog.setVisibility(View.VISIBLE);
		Thread thread = new Thread(() -> {
			ZipFile zipFile = new ZipFile(input);
			zipFile.setRunInThread(true);
			try {
				zipFile.extractAll(output);
			} catch (ZipException e) {
				throw new RuntimeException(e);
			}
			ProgressMonitor progressMonitor = zipFile.getProgressMonitor();
			while (!progressMonitor.getState().equals(ProgressMonitor.State.READY)) {

				activity.runOnUiThread(() -> {
					dialog.setProgress(progressMonitor.getPercentDone());
				});

			}
			activity.runOnUiThread(() -> {
				if (!progressMonitor.getState().equals(ProgressMonitor.Result.SUCCESS)) {
					dialog.setVisibility(View.GONE);
					Dialogok(activity, "Done");
				} else if (!progressMonitor.getState().equals(ProgressMonitor.Result.ERROR)) {
					dialog.setVisibility(View.GONE);
					DialogError(activity, "Error");
				}
			});
		});
		thread.start();

	}

	public static void ZipFile(Activity activity, String input, String output) {
		/*
		* @param This run in Progress Dialog
		* @param Select Activity
		* @param Select input dont Use Java.io.File 
		* @param Select OutPut dont Use Java.io.File 
		*/

		GradientDrawable u = new GradientDrawable();
		u.setColor(0xFF2B2122);
		u.setCornerRadius(25);
		u.setStroke(1, 0xFFF8B09A);

		final ProgressDialog dialog = new ProgressDialog(activity, ProgressDialog.THEME_DEVICE_DEFAULT_DARK);
		dialog.setTitle("Zip File..");
		dialog.getWindow().setBackgroundDrawable(u);
		dialog.setProgressStyle(ProgressDialog.STYLE_HORIZONTAL);
		dialog.setMax((int) 100);
		dialog.show();
		Thread thread = new Thread(() -> {
			ZipFile zipFile = new ZipFile(input);
			zipFile.setRunInThread(true);
			try {
				zipFile.addFile(new File(output));
			} catch (ZipException e) {
				throw new RuntimeException(e);
			}
			ProgressMonitor progressMonitor = zipFile.getProgressMonitor();
			while (!progressMonitor.getState().equals(ProgressMonitor.State.READY)) {

				activity.runOnUiThread(() -> {
					dialog.setProgress(progressMonitor.getPercentDone());
				});

			}
			activity.runOnUiThread(() -> {
				if (!progressMonitor.getState().equals(ProgressMonitor.Result.SUCCESS)) {
					dialog.dismiss();
					Dialogok(activity, "Done");
				} else if (!progressMonitor.getState().equals(ProgressMonitor.Result.ERROR)) {
					dialog.dismiss();
					DialogError(activity, "Error");
				}
			});

		});
		thread.start();

	}

	public static void ZipFile(Activity activity, String input, String output, ProgressBar dialog) {
		/*
		* @param This run in Progress Dialog
		* @param Select Activity
		* @param Select input dont Use Java.io.File 
		* @param Select OutPut dont Use Java.io.File 
		*/
		dialog.setVisibility(View.VISIBLE);
		Thread thread = new Thread(() -> {
			ZipFile zipFile = new ZipFile(input);
			zipFile.setRunInThread(true);
			try {
				zipFile.addFile(new File(output));
			} catch (ZipException e) {
				throw new RuntimeException(e);
			}
			ProgressMonitor progressMonitor = zipFile.getProgressMonitor();
			while (!progressMonitor.getState().equals(ProgressMonitor.State.READY)) {

				activity.runOnUiThread(() -> {
					dialog.setProgress(progressMonitor.getPercentDone());
				});

			}
			activity.runOnUiThread(() -> {
				if (!progressMonitor.getState().equals(ProgressMonitor.Result.SUCCESS)) {
					dialog.setVisibility(View.GONE);
					Dialogok(activity, "Done");
				} else if (!progressMonitor.getState().equals(ProgressMonitor.Result.ERROR)) {
					dialog.setVisibility(View.GONE);
					DialogError(activity, "Error");
				}
			});

		});
		thread.start();

	}

	public static void ZipFolder(Activity activity, String input, String output) {
		/*
		* @param This run in Progress Dialog
		* @param Select Activity
		* @param Select input dont Use Java.io.File 
		* @param Select OutPut dont Use Java.io.File 
		*/
		GradientDrawable u = new GradientDrawable();
		u.setColor(0xFF2B2122);
		u.setCornerRadius(25);
		u.setStroke(1, 0xFFF8B09A);
		final ProgressDialog dialog = new ProgressDialog(activity, ProgressDialog.THEME_DEVICE_DEFAULT_DARK);
		dialog.setTitle("Zip File..");
		dialog.getWindow().setBackgroundDrawable(u);
		dialog.setProgressStyle(ProgressDialog.STYLE_HORIZONTAL);
		dialog.setMax((int) 100);

		Thread thread = new Thread(() -> {
			ZipFile zipFile = new ZipFile(input);
			zipFile.setRunInThread(true);
			try {
				zipFile.addFolder(new File(output));
			} catch (ZipException e) {
				throw new RuntimeException(e);
			}
			ProgressMonitor progressMonitor = zipFile.getProgressMonitor();
			while (!progressMonitor.getState().equals(ProgressMonitor.State.READY)) {

				activity.runOnUiThread(() -> {
					dialog.setProgress(progressMonitor.getPercentDone());

				});

			}
			activity.runOnUiThread(() -> {
				if (!progressMonitor.getState().equals(ProgressMonitor.Result.SUCCESS)) {
					dialog.dismiss();
					Dialogok(activity, "Done");
				} else if (!progressMonitor.getState().equals(ProgressMonitor.Result.ERROR)) {
					dialog.dismiss();
					DialogError(activity, "Error");
				}
			});

		});
		thread.start();
		dialog.show();

	}

	public static void ZipFolder(Activity activity, String input, String output, ProgressBar dialog) {
		/*
		* @param This run in Progress Dialog
		* @param Select Activity
		* @param Select input dont Use Java.io.File 
		* @param Select OutPut dont Use Java.io.File 
		*/

		Thread thread = new Thread(() -> {
			ZipFile zipFile = new ZipFile(input);
			zipFile.setRunInThread(true);
			try {
				zipFile.addFolder(new File(output));
			} catch (ZipException e) {
				throw new RuntimeException(e);
			}
			ProgressMonitor progressMonitor = zipFile.getProgressMonitor();
			while (!progressMonitor.getState().equals(ProgressMonitor.State.READY)) {

				activity.runOnUiThread(() -> {
					dialog.setProgress(progressMonitor.getPercentDone());

				});

			}
			activity.runOnUiThread(() -> {
				if (!progressMonitor.getState().equals(ProgressMonitor.Result.SUCCESS)) {
					dialog.setVisibility(View.GONE);
					Dialogok(activity, "Done");
				} else if (!progressMonitor.getState().equals(ProgressMonitor.Result.ERROR)) {
					dialog.setVisibility(View.GONE);
					DialogError(activity, "Error");
				}
			});

		});
		thread.start();

	}

	protected static void Dialogok(Activity app, String msg) {
		/*
		* @param Dialog Helper to Done Zip and UnZip
		* @param Dont Set Activity automatic install For java file
		* dont Edit code Tnks
		* #Ninja coder
		*/

		GradientDrawable u = new GradientDrawable();
		u.setColor(Color.GREEN);
		u.setCornerRadius(15);
		u.setStroke(1, 0xFFF8B09A);
		new MaterialAlertDialogBuilder(app).setTitle("Done").setMessage(msg).setBackground(u)
				.setPositiveButton("Done", null).show();
	}

	protected static void DialogError(Activity app, String msg) {
		/*
		* @param Dialog Helper to Done Zip and UnZip
		* @param Dont Set Activity automatic install For java file
		* dont Edit code Tnks
		* #Ninja coder
		*
		*/

		GradientDrawable u = new GradientDrawable();
		u.setColor(Color.RED);
		u.setCornerRadius(15);
		u.setStroke(1, 0xFFF8B09A);
		new MaterialAlertDialogBuilder(app).setTitle("Error").setMessage(msg).setBackground(u)
				.setPositiveButton("Done", null).show();
	}

}