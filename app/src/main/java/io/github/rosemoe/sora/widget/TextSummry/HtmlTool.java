package io.github.rosemoe.sora.widget.TextSummry;

import Ninja.coder.Ghostemane.code.BrowserActivity;
import Ninja.coder.Ghostemane.code.ColorAndroid12;
import Ninja.coder.Ghostemane.code.IDEEDITOR;
import Ninja.coder.Ghostemane.code.marco.ColorView;
import Ninja.coder.Ghostemane.code.marco.HtmlTagView;
import Ninja.coder.Ghostemane.code.project.FileReaderJsonSpinet;
import android.content.Context;
import Ninja.coder.Ghostemane.code.R;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Toast;
import com.blankj.utilcode.util.ThreadUtils;
import com.google.android.material.dialog.MaterialAlertDialogBuilder;
import com.google.android.material.textfield.TextInputEditText;
import com.skydoves.powermenu.PowerMenu;
import com.skydoves.powermenu.PowerMenuItem;
import com.google.android.material.color.MaterialColors;
import com.skydoves.powermenu.MenuAnimation;
import io.github.rosemoe.sora.widget.CodeEditor;
import io.github.rosemoe.sora.widget.Transilt;
import java.io.Serializable;

public class HtmlTool implements Serializable {
  private ToolItem item;

  public HtmlTool() {
    item = new ToolItem();
  }

  public void Tool(Context context, View view, CodeEditor editor) {
    PowerMenu menu =
        new PowerMenu.Builder(context)
            .addItem(new PowerMenuItem("Comment line"))
            .addItem(new PowerMenuItem("Html Tag view"))
            .addItem(new PowerMenuItem("Add to collection"))
            .addItem(new PowerMenuItem("Share"))
            .addItem(new PowerMenuItem("Run Web site(beta)"))
            .addItem(new PowerMenuItem("Translate"))
            .addItem(new PowerMenuItem("Link viewer"))
            .addItem(new PowerMenuItem("Color preview "))
            .setIsMaterial(true)
            .build();
    menu.setMenuRadius(20f);
    menu.setTextColor(0);
    menu.setShowBackground(false);
    menu.setAutoDismiss(true);
    menu.setTextSize(14);

    menu.setMenuColor(MaterialColors.getColor(context, ColorAndroid12.Back, 0));
    menu.setTextColor(MaterialColors.getColor(context, ColorAndroid12.TvColor, 0));
    menu.showAsAnchorRightBottom(view);
    menu.setAnimation(MenuAnimation.ELASTIC_CENTER);
    menu.setHeaderView(item.getCustomHader("Html Helper", context));
    menu.setOnMenuItemClickListener(
        (pos, __) -> {
          if (pos == 0) {
            editor.getCommentHelper().CustomComment("<!--", "-->");
          } else if (pos == 1) {
            HtmlTagView views = new HtmlTagView();
            views.Start(editor.getText().toString(), context, (IDEEDITOR) editor);
          } else if (pos == 2) {
            FileReaderJsonSpinet sp = new FileReaderJsonSpinet();
            try {
              if (editor.getCursor().isSelected()) {
                MaterialAlertDialogBuilder dialog = new MaterialAlertDialogBuilder(context);
                View viewss = LayoutInflater.from(context).inflate(R.layout.listfilecol, null);
                TextInputEditText et1 = viewss.findViewById(R.id.et1);
                TextInputEditText et2 = viewss.findViewById(R.id.et2);
                TextInputEditText et3 = viewss.findViewById(R.id.et3);
                String clip =
                    editor
                        .getText()
                        .subContent(
                            editor.getCursor().getLeftLine(),
                            editor.getCursor().getLeftColumn(),
                            editor.getCursor().getRightLine(),
                            editor.getCursor().getRightColumn())
                        .toString();
                ThreadUtils.runOnUiThread(
                    () -> {
                      et1.setText(clip.toString());
                    });

                et1.setSingleLine(true);
                dialog.setView(viewss);
                dialog.setTitle("Adding code");
                dialog.setPositiveButton(
                    "ok",
                    (v, c) -> {
                      sp.addToJsonFile(
                          "html",
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
            var tooljava = new JavaTools();
            tooljava.shareText(editor);
          } else if (pos == 4) {
            item.ShareText(context, editor);
          } else if (pos == 5) {
            Transilt.Start(editor);
          } else if (pos == 6) {
            ColorView colorview = new ColorView();
            colorview.linkview(editor, context);
          } else if (pos == 7) {
            ColorView mview = new ColorView();
            mview.colorview(editor, context);
          }
        });
  }
}
