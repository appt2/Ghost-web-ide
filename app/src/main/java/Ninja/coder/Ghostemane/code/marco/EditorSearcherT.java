package Ninja.coder.Ghostemane.code.marco;

import Ninja.coder.Ghostemane.code.ColorAndroid12;
import Ninja.coder.Ghostemane.code.IDEEDITOR;
import Ninja.coder.Ghostemane.code.R;
import android.content.Context;
import android.graphics.drawable.GradientDrawable;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.PopupWindow;
import com.google.android.material.bottomsheet.BottomSheetDialog;
import com.google.android.material.button.MaterialButton;
import com.google.android.material.color.MaterialColors;
import com.google.android.material.dialog.MaterialAlertDialogBuilder;
import com.google.android.material.textfield.TextInputLayout;
import coyamo.visualxml.lib.parser.AndroidXmlParser;
import coyamo.visualxml.lib.parser.ReadOnlyParser;
import coyamo.visualxml.lib.proxy.ProxyResources;
import coyamo.visualxml.lib.ui.OutlineView;
import coyamo.visualxml.lib.utils.MessageArray;
import io.github.rosemoe.sora.widget.CodeEditor;

public class EditorSearcherT {

  public static void show(IDEEDITOR editor, View views, String txt) {

    var popupView =
        LayoutInflater.from(views.getContext()).inflate(R.layout.new_layoutsearch, null, false);
    PopupWindow popup =
        new PopupWindow(
            popupView,
            ViewGroup.LayoutParams.MATCH_PARENT,
            ViewGroup.LayoutParams.WRAP_CONTENT,
            true);
    popupView.setPadding(3, 3, 3, 3);
    popup.setContentView(popupView);
    popup.showAtLocation(views, Gravity.CENTER, 0, 0);
    EditText etSearch = popupView.findViewById(R.id.search_text);
    MaterialButton close = popupView.findViewById(R.id.close);
    MaterialButton next = popupView.findViewById(R.id.goto_next);
    MaterialButton last = popupView.findViewById(R.id.goto_last);
    EditText replceEt = popupView.findViewById(R.id.replace_text);
    MaterialButton replaceThis = popupView.findViewById(R.id.replace);
    MaterialButton replaceAll = popupView.findViewById(R.id.replace_all);

    next.setOnClickListener(c -> editor.getSearcher().gotoNext());
    last.setOnClickListener(d -> editor.getSearcher().gotoLast());
    etSearch.setText(txt);
    replaceThis.setOnClickListener(
        __ -> {
          popup.dismiss();
          editor.getSearcher().replaceThis(replceEt.getText().toString());
        });
    replaceAll.setOnClickListener(
        __x -> {
          popup.dismiss();
          editor.getSearcher().replaceAll(replceEt.getText().toString());
        });
    close.setOnClickListener(z -> popup.dismiss());
    GradientDrawable db = new GradientDrawable();
    db.setColor(MaterialColors.getColor(popupView, ColorAndroid12.Back, 0));
    db.setStroke(1, MaterialColors.getColor(popupView, ColorAndroid12.colorOnSurface, 0));
    popupView.setBackground(db);
    etSearch.addTextChangedListener(
        new TextWatcher() {

          @Override
          public void afterTextChanged(Editable arg0) {}

          @Override
          public void beforeTextChanged(CharSequence e, int arg1, int arg2, int arg3) {}

          @Override
          public void onTextChanged(CharSequence arg0, int arg1, int arg2, int arg3) {
            if (editor != null) {
              if (etSearch.getText().toString().isEmpty()) {
                editor.getSearcher().stopSearch();
                return;
              } else editor.getSearcher().search(etSearch.getText().toString());
            }
          }
        });
  }

  public static void show(IDEEDITOR editor, View views) {
    show(editor, views, "");
  }

  public static void xmlparser(Context context, CodeEditor editor) {
    var sheet = new BottomSheetDialog(context);
    try {
      ProxyResources.getInstance().getViewIdMap().clear();
      MessageArray.getInstanse().clear();
    } catch (Exception err) {
      err.printStackTrace();
    }
    OutlineView outline = new OutlineView(context, null);
    LinearLayout.LayoutParams par =
        new LinearLayout.LayoutParams(
            LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.WRAP_CONTENT);
    outline.setLayoutParams(par);
    outline.setHoldOutline(false);
    outline.setDisplayType(OutlineView.DISPLAY_DESIGN);
    AndroidXmlParser.with(outline)
        .setOnParseListener(
            new AndroidXmlParser.OnParseListener() {

              @Override
              public void onAddChildView(View v, ReadOnlyParser parser) {}

              @Override
              public void onFinish() {}

              @Override
              public void onJoin(ViewGroup viewGroup, ReadOnlyParser parser) {}

              @Override
              public void onRevert(ViewGroup viewGroup, ReadOnlyParser parser) {}

              @Override
              public void onStart() {}
            })
        .parse(editor.getText().toString());
    sheet.setContentView(outline);
    if (sheet != null) {
      sheet.show();
    }
  }
}
