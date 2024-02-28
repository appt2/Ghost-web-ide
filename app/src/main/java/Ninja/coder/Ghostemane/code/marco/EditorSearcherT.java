package Ninja.coder.Ghostemane.code.marco;

import Ninja.coder.Ghostemane.code.IDEEDITOR;
import Ninja.coder.Ghostemane.code.R;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.PopupWindow;
import com.google.android.material.dialog.MaterialAlertDialogBuilder;
import com.google.android.material.textfield.TextInputLayout;

public class EditorSearcherT {

  public static void show(IDEEDITOR editor, View views) {

    var popupView =
        LayoutInflater.from(views.getContext()).inflate(R.layout.new_layoutsearch, null, false);
    PopupWindow popup =
        new PopupWindow(
            popupView,
            ViewGroup.LayoutParams.MATCH_PARENT,
            ViewGroup.LayoutParams.WRAP_CONTENT,
            true);
    popup.setContentView(popupView);
    popup.showAtLocation(views, Gravity.CENTER, 0, 0);
    TextInputLayout input = popupView.findViewById(R.id.input_search);
    EditText etSearch = popupView.findViewById(R.id.et_searchText);
    ImageView next = popupView.findViewById(R.id.nextSearch);
    ImageView last = popupView.findViewById(R.id.lastSearch);
    ImageView replaceThis = popupView.findViewById(R.id.replacethis);

    next.setOnClickListener(c -> editor.getSearcher().gotoNext());
    last.setOnClickListener(d -> editor.getSearcher().gotoLast());
    replaceThis.setOnClickListener(
        __ -> {
         popup.dismiss();
          var dialog = new MaterialAlertDialogBuilder(editor.getContext());
          var mylayout =
              LayoutInflater.from(editor.getContext()).inflate(R.layout.makefolder, null, false);
          EditText et = mylayout.findViewById(R.id.editor);
          dialog.setView(mylayout);
          dialog.setTitle("Replace Item");
          dialog.setMessage("If a single text does not exist, you do not need to replace all");
          dialog.setPositiveButton(
              "Replace This",
              (c, v) -> {
                if (editor != null) {
                  editor.getSearcher().replaceThis(et.getText().toString());
                }
              });
          dialog.setNegativeButton(
              "Replace All",
              (f, v) -> {
                if (editor != null) {
                  editor.getSearcher().replaceAll(et.getText().toString());
                }
              });
          if (dialog != null) {
            dialog.show();
          }
        });
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
}
