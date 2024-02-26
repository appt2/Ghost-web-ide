package Ninja.coder.Ghostemane.code.comprasor;

import android.view.View;
import android.widget.ProgressBar;
import io.github.rosemoe.sora.widget.CodeEditor;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import android.os.AsyncTask;


public class HtmlCodeFormater extends AsyncTask<String, String, String> {
	private final ProgressBar progressBar;
	private final CodeEditor codeEditor;

	public HtmlCodeFormater(ProgressBar progressBar, CodeEditor codeEditor) {
		this.progressBar = progressBar;
		this.codeEditor = codeEditor;
	}

	@Override
	protected void onPreExecute() {
		super.onPreExecute();
		progressBar.setVisibility(View.VISIBLE);
	}

	@Override
	protected String doInBackground(String... fotmaterTry) {
		try {
			return formatCode(fotmaterTry[0]);
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}

	@Override
	protected void onPostExecute(String arg0) {
		super.onPostExecute(arg0);
		if (arg0 != null) {
			codeEditor.post(() -> {
				codeEditor.setText(arg0);
				progressBar.setVisibility(View.GONE);

			});
		}
	}

	private String formatCode(String code) {
		String html = code;
		Document doc = Jsoup.parse(html);
		Document.OutputSettings outputSettings = doc.outputSettings();
		outputSettings.indentAmount(2);
		outputSettings.prettyPrint(true);
		if (doc.toString().contains("<!doctype html>")) {
			return doc.html().replace("<!doctype html>", "<!DOCTYPE html>");
		}
		return doc.html();

	}
}
