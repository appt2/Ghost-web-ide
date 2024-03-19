package Ninja.coder.Ghostemane.code.marco;

import Ninja.coder.Ghostemane.code.IdeEditor;
import Ninja.coder.Ghostemane.code.R;
import Ninja.coder.Ghostemane.code.utils.ColorAndroid12;
import android.graphics.drawable.GradientDrawable;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.PopupWindow;
import com.google.android.material.button.MaterialButton;
import com.google.android.material.color.MaterialColors;

public class EditorSearcherT {

    public static void show(IdeEditor editor, View views, String txt) {

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
                    public void afterTextChanged(Editable arg0) {
                    }

                    @Override
                    public void beforeTextChanged(CharSequence e, int arg1, int arg2, int arg3) {
                    }

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

    public static void show(IdeEditor editor, View views) {
        show(editor, views, "");

    }
}
