package Ninja.coder.Ghostemane.code;

import Ninja.coder.Ghostemane.code.ColorAndroid12;
import android.content.res.ColorStateList;
import android.widget.LinearLayout;
import com.google.android.material.color.MaterialColors;
import com.mukesh.MarkdownView;
import android.os.Bundle;
import androidx.appcompat.app.AppCompatActivity;

public class MdcodeviewActivity extends AppCompatActivity {

  private LinearLayout linear1;
  private MarkdownView markdownview;

  @Override
  protected void onCreate(Bundle _savedInstanceState) {
    super.onCreate(_savedInstanceState);
    setContentView(R.layout.mdcodeview);
    linear1 = findViewById(R.id.linear1);
    markdownview = findViewById(R.id.M);
    markdownview.setOpenUrlInBrowser(true);
    markdownview.setMarkDownText(FileUtil.readFile(getIntent().getStringExtra("v")));
    markdownview.setBackgroundTintList(
        ColorStateList.valueOf(MaterialColors.getColor(markdownview, ColorAndroid12.Back)));
  }
}
