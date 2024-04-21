package Ninja.coder.Ghostemane.code.utils;

import android.view.View;
import android.widget.ProgressBar;
import com.blankj.utilcode.util.ThreadUtils;
import io.github.rosemoe.sora.widget.CodeEditor;


public class FileCompatApi28 {

  public static void readFile(String fileRead, ProgressBar bar, CodeEditor editText)
      throws Throwable {
    ShowBar(bar, true);

    ThreadUtils.executeByIo(
        new ThreadUtils.Task<String>() {

          @Override
          public void onSuccess(String result) {
            editText.setText(result);
            ShowBar(bar, false);
          }

          @Override
          public void onFail(Throwable throwable) {
            editText.setText(throwable.getLocalizedMessage());
          }

          @Override
          public String doInBackground() {

            return FileUtil.readFile(fileRead);
          }

          @Override
          public void onCancel() {}
        });
  }

  private static void ShowBar(ProgressBar bar, boolean show) {
    bar.setVisibility(show ? View.VISIBLE : View.GONE);
  }
}
