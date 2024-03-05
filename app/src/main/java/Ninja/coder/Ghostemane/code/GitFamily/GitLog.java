package Ninja.coder.Ghostemane.code.GitFamily;

import Ninja.coder.Ghostemane.code.FileUtil;
import android.content.Context;
import android.text.Spannable;
import android.text.SpannableString;
import android.graphics.Color;
import Ninja.coder.Ghostemane.code.SketchwareUtil;
import android.text.style.BackgroundColorSpan;
import android.text.style.CharacterStyle;
import android.text.style.ForegroundColorSpan;
import android.view.ViewGroup;
import android.widget.TextView;
import com.google.android.material.dialog.MaterialAlertDialogBuilder;
import java.io.File;
import java.util.ArrayList;
import org.eclipse.jgit.revwalk.RevWalk;
import org.eclipse.jgit.revwalk.RevCommit;
import java.util.List;
import org.eclipse.jgit.api.Git;
import org.eclipse.jgit.lib.Repository;

public class GitLog {
  public Context context;

  public GitLog(Context context) {
    this.context = context;
  }

  public void test() {
    TextView view = new TextView(context);
    ViewGroup.LayoutParams param =
        new ViewGroup.LayoutParams(
            ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);
    view.setLayoutParams(param);
    view.setPadding(8, 8, 8, 8);
    new MaterialAlertDialogBuilder(context)
        .setTitle("DiffyView")
        .setMessage("Test")
        .setView(view)
        .setPositiveButton("ok", null)
        .show();
    read(view, "/storage/emulated/0/AndroidIDEProjects/GhostWeb IDE2/.git");
  }

  public void read(TextView view, String gitlog) {
    try {
      Git git = Git.open(new File(gitlog));
      Repository repository = git.getRepository();

      try (RevWalk walk = new RevWalk(repository)) {
        RevCommit commit = walk.parseCommit(repository.resolve("HEAD"));
        List<RevCommit> logs = new ArrayList<>();
        RevCommit parent = commit.getParent(0);
        while (parent != null) {
          logs.add(0, parent);
          parent = parent.getParent(0);
        }
        for (RevCommit log : logs) {
          System.out.println(log.getFullMessage());
          DiffyViewer(view, log.getFullMessage());
        }
      } catch (Exception err) {
        err.printStackTrace();
      }

    } catch (Exception e) {
      e.printStackTrace();
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
          spannable1.setSpan(CharacterStyle.wrap(fgSpan), n, n + lines[i].length(), 0);
          spannable1.setSpan(CharacterStyle.wrap(bgSpan), n, n + lines[i].length(), 0);
        } catch (Exception e) {
          e.printStackTrace();
        }
      } else if (lines[i].substring(0, 1).replaceAll("\\s", "").equals("-")) {
        int n = output.indexOf(lines[i]);
        x = n + 1;
        try {
          spannable1.setSpan(CharacterStyle.wrap(fgSpan), n, n + lines[i].length(), 0);
          spannable1.setSpan(CharacterStyle.wrap(RbgSpan), n, n + lines[i].length(), 0);
        } catch (Exception e) {
          e.printStackTrace();
        }
      }
    }
    tv.setText(spannable1);
  }
}
