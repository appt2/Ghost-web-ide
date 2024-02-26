package Ninja.coder.Ghostemane.code;

import android.app.Activity;
import android.widget.Button;
import android.widget.LinearLayout;
import android.content.DialogInterface;
import android.widget.ProgressBar;
import android.widget.Toast;
import androidx.appcompat.app.AlertDialog;

import com.google.android.material.dialog.MaterialAlertDialogBuilder;
import java.util.logging.Logger;

public class DownloaderData {
	public static void androidjar(Activity a) {
		BindDialog(a, "download Androidjar", "https://raw.githubusercontent.com/moderGamer/data/main/android.jar",
		"/storage/emulated/0/GhostWebIDE/android/android.jar");
	}
	
	
	public static void css(Activity a) {
		BindDialog(a, "download css", "https://raw.githubusercontent.com/moderGamer/data/main/css.pdf",
		"/storage/emulated/0/GhostWebIDE/.lp/css.pdf");
	}
	
	public static void java(Activity a) {
		BindDialog(a, "download Java", "https://raw.githubusercontent.com/moderGamer/data/main/finaljavabook.pdf",
		"/storage/emulated/0/GhostWebIDE/.lp/finajavabook.pdf");
	}
	
	public static void android(Activity a) {
		BindDialog(a, "download Androidlearning", "https://raw.githubusercontent.com/moderGamer/data/main/Androidlearning.pdf",
		"/storage/emulated/0/GhostWebIDE/.lp/Androidlearning.pdf");
	}
	public static void inittheme(Activity a){
		BindDialog(a, "download Theme", "https://raw.githubusercontent.com/moderGamer/data/main/GhostThemeapp.ghost",
		"/storage/emulated/0/GhostWebIDE/theme/GhostThemeapp.ghost");
	}
	
	
	protected static void BindDialog(Activity activity, String data, String namefile, String dir) {
		androidx.appcompat.app.AlertDialog dialog = new com.google.android.material.dialog.MaterialAlertDialogBuilder(
		activity)
		
		.setView(R.layout.downloader)
		.setTitle("Downloader")
		.setMessage(data)
		.setCancelable(false)
		.create();
		dialog.setOnShowListener((var) -> {
			ProgressBar pro = dialog.findViewById(R.id.pro);
			Button positive = dialog.getButton(DialogInterface.BUTTON_POSITIVE);
			pro.setMax((int) 100);
			new DownloadFile(namefile, dir).setListener(new DownloadFile.onDownloadListener() {
				@Override
				public void onProgressListener(int progress, String lengthFiles) {
					pro.setProgress((int) progress);
				}
				
				@Override
				public void onCompleteListener() {
					dialog.dismiss();
				}
				
				@Override
				public void onErrorListener(String errorMessage) {
					Toast.makeText(activity, errorMessage, Toast.LENGTH_LONG).show();
				}
			}).execute(0);
		});
		dialog.show();
	}
}
