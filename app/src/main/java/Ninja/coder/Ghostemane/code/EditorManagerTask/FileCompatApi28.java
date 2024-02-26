package Ninja.coder.Ghostemane.code.EditorManagerTask;

import android.util.Log;
import android.view.View;
import io.github.rosemoe.sora.widget.CodeEditor;
import android.widget.ProgressBar;
import java.io.*;
import java.util.concurrent.CompletableFuture;

public class FileCompatApi28 {
	
	public static void readFile(String fileRead, ProgressBar bar, CodeEditor editText) throws Throwable {
		ShowBar(bar, true);
		
		CompletableFuture.supplyAsync(() -> {
			try {
				return readFile(fileRead);
			} catch (Exception e) {
				return null;// return null to indicate error
			}
		}).thenAcceptAsync(result -> {
			if (result != null) {
				editText.post(() -> {
					editText.setText(result);
					ShowBar(bar, false);
				});
			}
			
		});
	}
	
	private static void ShowBar(ProgressBar bar, boolean show) {
		bar.setVisibility(show ? View.VISIBLE : View.GONE);
	}
	
	public static String readFile(String path) {
		return readFile(new File(path));
	}
	
	public static String readFile(File file) {
		StringBuilder sb = new StringBuilder();
		FileReader fr = null;
		try {
			fr = new FileReader(file);
			
			char[] buff = new char[2048 * 10];
			int length = 0;
			
			while ((length = fr.read(buff)) > 0) {
				sb.append(new String(buff, 0, length));
			}
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			if (fr != null) {
				try {
					fr.close();
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		}
		return sb.toString();
	}
	
}
