package Ninja.coder.Ghostemane.code;

import android.app.ProgressDialog;
import android.content.Context;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.graphics.drawable.GradientDrawable;
import android.os.AsyncTask;
import android.os.Handler;
import android.os.Looper;
import android.widget.EditText;
import android.widget.ProgressBar;
import io.github.rosemoe.sora.widget.CodeEditor;

public class JsonFormater {
	
	public static void install(String input, CodeEditor editor, Context context) {
		final var gradientDrawable = new GradientDrawable();
		gradientDrawable.setColor(0xFF1F1B1C);
		gradientDrawable.setStroke(1,0xFFFDB69A);
		gradientDrawable.setCornerRadius(15f);
		final var prog = new ProgressDialog(context, ProgressDialog.THEME_DEVICE_DEFAULT_DARK);
		prog.getWindow().setBackgroundDrawable(gradientDrawable);
		prog.setTitle("JsonFormater");
		prog.setMessage("Formating json....");
		prog.setProgressStyle(ProgressDialog.STYLE_HORIZONTAL);
		prog.show();
		
		input = editor.getText().toString();
		final String json_str = input;
		final int indent_width = 4;
		
		final char[] chars = json_str.toCharArray();
		final String newline = System.lineSeparator();
		
		final boolean[] begin_quotes = { false };
		
		final int[] progres = { 0 };
		
		final String[] ret = { "" };
		
		final Handler mHandler = new Handler();
		
		new Thread(new Runnable() {
			@Override
			public void run() {
				Looper.prepare();
				for (int i = 0, indent = 0; i < chars.length; i++) {
					char c = chars[i];
					
					prog.setProgress((int) i);
					prog.setMax(chars.length);
					if (c == '\"') {
						ret[0] += c;
						begin_quotes[0] = !begin_quotes[0];
						continue;
					}
					
					if (!begin_quotes[0]) {
						switch (c) {
							case '{':
							case '[':
							ret[0] += c + newline + String.format("%" + (indent += indent_width) + "s", "");
							continue;
							case '}':
							case ']':
							ret[0] += newline
							+ ((indent -= indent_width) > 0 ? String.format("%" + indent + "s", "") : "") + c;
							continue;
							case ':':
							ret[0] += c + " ";
							continue;
							case ',':
							ret[0] += c + newline + (indent > 0 ? String.format("%" + indent + "s", "") : "");
							continue;
							default:
							if (Character.isWhitespace(c))
							continue;
						}
					}
					
					ret[0] += c + (c == '\\' ? "" + chars[++i] : "");
				}
				
				mHandler.post(() -> {
					
					editor.setText(ret[0]);
					prog.dismiss();
					Looper.loop();
					
				});
			}
		}).start();
		
	}
}
