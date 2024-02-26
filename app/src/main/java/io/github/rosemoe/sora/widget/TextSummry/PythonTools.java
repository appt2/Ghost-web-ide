package io.github.rosemoe.sora.widget.TextSummry;

import android.content.Context;
import android.view.View;
import io.github.rosemoe.sora.widget.CodeEditor;
import com.skydoves.powermenu.PowerMenu;
import com.skydoves.powermenu.PowerMenuItem;
import Ninja.coder.Ghostemane.code.ColorAndroid12;
import com.google.android.material.color.MaterialColors;
import com.skydoves.powermenu.MenuAnimation;
import Ninja.coder.Ghostemane.code.R;
import Ninja.coder.Ghostemane.code.marco.HtmlTagView;
import Ninja.coder.Ghostemane.code.project.FileReaderJsonSpinet;
import com.google.android.material.dialog.MaterialAlertDialogBuilder;
import android.view.LayoutInflater;
import com.google.android.material.textfield.TextInputEditText;
import com.blankj.utilcode.util.ThreadUtils;
import android.widget.Toast;
import io.github.rosemoe.sora.widget.Transilt;
import java.io.Serializable;

public class PythonTools implements Serializable {

  private ToolItem item;

  public PythonTools() {
    item = new ToolItem();
  }

  public void Tool(Context context, View view, CodeEditor editor) {
    PowerMenu menu =
        new PowerMenu.Builder(context)
            .addItem(new PowerMenuItem("Comment line"))
            .addItem(new PowerMenuItem("Python Tree view"))
            .addItem(new PowerMenuItem("Add to collection"))
            .addItem(new PowerMenuItem("Translate"))
            .setIsMaterial(true)
            .build();
    menu.setMenuRadius(20f);
    menu.setShowBackground(false);
    menu.setAutoDismiss(true);
    menu.setTextSize(14);
    menu.setMenuColor(MaterialColors.getColor(context, ColorAndroid12.Back, 0));
    menu.setTextColor(MaterialColors.getColor(context, ColorAndroid12.TvColor, 0));
    menu.showAsAnchorRightBottom(view);
    menu.setAnimation(MenuAnimation.ELASTIC_CENTER);
    menu.setHeaderView(item.getCustomHader("Python Helper", context));
    menu.setOnMenuItemClickListener(
        (pos, __) -> {
          if (pos == 0) {
            String pycomment = "\"\"\"";
            editor.getCommentHelper().CustomComment(pycomment, pycomment);
          } else if (pos == 1) {
            new HtmlTagView().PythonTreeView(editor.getText().toString(), context, editor);

          } else if (pos == 2) {
            FileReaderJsonSpinet sp = new FileReaderJsonSpinet();
            try {
              if (editor.getCursor().isSelected()) {
                MaterialAlertDialogBuilder dialog = new MaterialAlertDialogBuilder(context);
                View viewss = LayoutInflater.from(context).inflate(R.layout.listfilecol, null);
                TextInputEditText et1 = viewss.findViewById(R.id.et1);
                TextInputEditText et2 = viewss.findViewById(R.id.et2);
                TextInputEditText et3 = viewss.findViewById(R.id.et3);

                ThreadUtils.runOnUiThread(
                    () -> {
                      et1.setText(editor.getSelectedText());
                    });

                et1.setSingleLine(true);

                dialog.setView(viewss);
                dialog.setTitle("Collection code");
                dialog.setPositiveButton(
                    "ok",
                    (v, c) -> {
                      sp.addToJsonFile(
                          "python",
                          et2.getText().toString(),
                          et3.getText().toString(),
                          et1.getText().toString());
                    });
                dialog.show();
              } else {
                Toast.makeText(context, "Cursor not Selected", 2).show();
              }
            } catch (Exception e) {
              e.printStackTrace();
              Toast.makeText(context, e.toString(), Toast.LENGTH_SHORT).show();
            }
          } else if (pos == 3) {
            var mtools = new JavaTools();
            mtools.shareText(editor);
          } else if (pos == 4) {
            Transilt.Start(editor);
          }
        });
  }
}
