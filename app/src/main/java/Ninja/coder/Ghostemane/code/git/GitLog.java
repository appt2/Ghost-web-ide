package Ninja.coder.Ghostemane.code.git;

import android.content.Context;
import android.graphics.Color;
import android.text.Spannable;
import android.text.SpannableString;
import android.text.style.BackgroundColorSpan;
import android.text.style.ForegroundColorSpan;
import android.view.ViewGroup;
import android.widget.TextView;
import com.google.android.material.dialog.MaterialAlertDialogBuilder;

import java.io.File;


public class GitLog {
  public Context context;
  protected GitUtils repository;

  public GitLog(Context context) {
    this.context = context;
    try {
      File file = new File("/storage/emulated/0/AndroidIDEProjects/GhostWeb IDE2/.git/");
      repository = new GitUtils(file);
      repository.init(file);
    } catch (Exception err) {

    }
  }

  public void test() {
    TextView view = new TextView(context);
    ViewGroup.LayoutParams param =
        new ViewGroup.LayoutParams(
            ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);
    view.setLayoutParams(param);
    view.setPadding(8, 8, 8, 8);
    new MaterialAlertDialogBuilder(context)
        .setTitle("GitHelper")
        .setMessage("View Item Change help")
        .setView(view)
        .setPositiveButton("ok", null)
        .show();
    StringBuilder b = new StringBuilder();
    try {
      b.append("Status ").append(repository.getStatusAsString()).append("\n");
      // b.append("BranchName ").append(repository.getCurrentBranchName()).append('\n');
      //  b.append("Diff").append(repository.getDiff()).append("\n");
      DiffyViewer(view, b.toString());
    } catch (Exception err) {
      view.setText(err.getLocalizedMessage());
    }
  }

  protected void DiffyViewer(TextView tv, String output) {
    String[] lines;
    Spannable spannable1 = new SpannableString(output);
    ForegroundColorSpan fgSpan = new ForegroundColorSpan(Color.parseColor("#ffffff"));
    BackgroundColorSpan bgSpan = new BackgroundColorSpan(Color.parseColor("#4caf50"));
    BackgroundColorSpan RbgSpan = new BackgroundColorSpan(Color.parseColor("#d81b69"));

    lines = output.split("\n");
    int x = 0;
    for (int i = 0; i < lines.length; i++) {
      if (lines[i].substring(0, 1).replaceAll("\\s", "").equals("+")) {
        int n = output.indexOf(lines[i]);
        x = n + 1;
        try {
          spannable1.setSpan(fgSpan, n, n + lines[i].length(), Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
          spannable1.setSpan(bgSpan, n, n + lines[i].length(), Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
        } catch (Exception e) {
          e.printStackTrace();
        }
      } else if (lines[i].substring(0, 1).replaceAll("\\s", "").equals("-")) {
        int n = output.indexOf(lines[i]);
        x = n + 1;
        try {
          spannable1.setSpan(fgSpan, n, n + lines[i].length(), Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
          spannable1.setSpan(bgSpan, n, n + lines[i].length(), Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
        } catch (Exception e) {
          e.printStackTrace();
        }
      }
    }
    tv.setText(spannable1);
  }
}
