package Ninja.coder.Ghostemane.code.EditorSearch;

import android.app.Activity;
import android.content.Context;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.graphics.drawable.GradientDrawable;
import android.text.TextWatcher;
import android.text.Editable;
import android.view.Gravity;
import android.widget.Button;
import android.widget.EditText;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;
import Ninja.coder.Ghostemane.code.R;
import com.google.android.material.bottomsheet.BottomSheetDialog;
import com.google.android.material.dialog.MaterialAlertDialogBuilder;
import io.github.rosemoe.sora.widget.CodeEditor;

public class CodeEditorSearch {

	public static void Mp(Activity c, CharSequence cd) {
		Toast _toast = Toast.makeText(c, cd, Toast.LENGTH_SHORT);
		View _view = _toast.getView();
		TextView _textView = _view.findViewById(android.R.id.message);
		_textView.setTextSize(16);
		_textView.setTextColor(Color.BLACK);
		_textView.setGravity(Gravity.CENTER);
		GradientDrawable _gradientDrawable = new GradientDrawable();
		_gradientDrawable.setColor(Color.WHITE);
		_gradientDrawable.setCornerRadius(15);
		_view.setBackgroundDrawable(_gradientDrawable);
		_view.setPadding(15, 10, 15, 10);
		_view.setElevation(10);
        

	}

	public static void SearchDialogMod(Activity a, CodeEditor editor) {
		///result code By Ninja coder.ir my love java

		GradientDrawable aaa = new GradientDrawable();
		aaa.setColor(0xFF1F1B1C);
		aaa.setCornerRadius(15);
		aaa.setStroke(1, 0xFFFDA893);
		MaterialAlertDialogBuilder di = new MaterialAlertDialogBuilder(a);
		ViewGroup viewGroup = a.findViewById(android.R.id.content);
		View dialogview = a.getLayoutInflater().inflate(R.layout.serachviews, viewGroup, false);
		EditText editorrep = dialogview.findViewById(R.id.editorrep);
		EditText editorser = dialogview.findViewById(R.id.editorser);
		Button mis = dialogview.findViewById(R.id.mis);
		Button ser = dialogview.findViewById(R.id.ser);
		Button rep = dialogview.findViewById(R.id.rep);
		Button repall = dialogview.findViewById(R.id.repall);
		com.google.android.material.textfield.TextInputLayout input1 = dialogview.findViewById(R.id.input1);
		com.google.android.material.textfield.TextInputLayout input2 = dialogview.findViewById(R.id.input2);
		di.setTitle("جستجو");
		editorser.addTextChangedListener(new TextWatcher() {
			@Override
			public void onTextChanged(CharSequence _param1, int _param2, int _param3, int _param4) {
				final String _charSeq = _param1.toString();
				editor.getSearcher().search(_charSeq);
			}

			@Override
			public void beforeTextChanged(CharSequence _param1, int _param2, int _param3, int _param4) {

			}

			@Override
			public void afterTextChanged(Editable _param1) {

			}
		});

		mis.setOnClickListener((view) -> {

			try {
				editor.getSearcher().gotoLast();
			} catch (IllegalStateException e) {
				e.printStackTrace();
			}

		});
		ser.setOnClickListener((view) -> {

			try {
				editor.getSearcher().gotoNext();
			} catch (IllegalStateException e) {
				e.printStackTrace();
			}

		});
		rep.setOnClickListener((view) -> {

			if (editorrep.getText().toString().isEmpty()) {
				Mp(a, "متن نمیتواند خالی باشد");
				//SketchwareUtil.showMessage(getApplicationContext(), " متن خالی است");
			} else {
				try {
					editor.getSearcher().replaceThis(editorrep.getText().toString());
				} catch (IllegalStateException e) {
					e.printStackTrace();
				}
			}

		});
		repall.setOnClickListener((view) -> {

			if (editorrep.getText().toString().isEmpty()) {
				Mp(a, "متن نمیتواند خالی باشد");
				//SketchwareUtil.showMessage(getApplicationContext(), " متن خالی است");
			} else {
				try {
					editor.getSearcher().replaceAll(editorrep.getText().toString());
				} catch (IllegalStateException e) {
					e.printStackTrace();
				}
			}

		});
		di.setNeutralButton("بستن", (p, d) -> {

		});
		di.setBackground(aaa);
		di.setView(dialogview);
		di.show();

	}

	public static void FromSheet(Activity a, CodeEditor editor) {
		BottomSheetDialog bottomSheetDialog = new BottomSheetDialog(a);
        bottomSheetDialog.show();
		bottomSheetDialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
		View bottomSheetView;
		bottomSheetView = a.getLayoutInflater().inflate(R.layout.fromsheets, null);
		bottomSheetDialog.setContentView(bottomSheetView);
		ImageButton imageclose = bottomSheetView.findViewById(R.id.imageclose);
		TextView tani = bottomSheetView.findViewById(R.id.tani);
		EditText editorrep = bottomSheetView.findViewById(R.id.editorrep);
		EditText editorser = bottomSheetView.findViewById(R.id.editorser);
		Button mis = bottomSheetView.findViewById(R.id.mis);
		Button ser = bottomSheetView.findViewById(R.id.ser);
		Button rep = bottomSheetView.findViewById(R.id.rep);
		Button repall = bottomSheetView.findViewById(R.id.repall);
		com.google.android.material.textfield.TextInputLayout input1 = bottomSheetView.findViewById(R.id.input1);
		com.google.android.material.textfield.TextInputLayout input2 = bottomSheetView.findViewById(R.id.input2);
		editorser.addTextChangedListener(new TextWatcher() {
			@Override
			public void onTextChanged(CharSequence _param1, int _param2, int _param3, int _param4) {
				final String _charSeq = _param1.toString();
				editor.getSearcher().search(_charSeq);
			}

			@Override
			public void beforeTextChanged(CharSequence _param1, int _param2, int _param3, int _param4) {

			}

			@Override
			public void afterTextChanged(Editable _param1) {

			}
		});

		mis.setOnClickListener((view) -> {

			try {
				editor.getSearcher().gotoLast();
			} catch (IllegalStateException e) {
				e.printStackTrace();
			}

			imageclose.setOnClickListener(cd -> {
                
				bottomSheetDialog.dismiss();
                
			});
			tani.setOnClickListener(cd -> {
				Mp(a, "اذیت نکن عه");
			});

		});
		ser.setOnClickListener((view) -> {

			try {
				editor.getSearcher().gotoNext();
			} catch (IllegalStateException e) {
				e.printStackTrace();
			}

		});
		rep.setOnClickListener((view) -> {

			if (editorrep.getText().toString().isEmpty()) {
				Mp(a, "متن نمیتواند خالی باشد");
				//	SketchwareUtil.showMessage(getApplicationContext(), " متن خالی است");
			} else {
				try {
					editor.getSearcher().replaceThis(editorrep.getText().toString());
				} catch (IllegalStateException e) {
					e.printStackTrace();
				}
			}

		});
		repall.setOnClickListener((view) -> {

			if (editorrep.getText().toString().isEmpty()) {
				Mp(a, "متن نمیتواند خالی باشد");

				//	SketchwareUtil.showMessage(getApplicationContext(), " متن خالی است");
			} else {
				try {
					editor.getSearcher().replaceAll(editorrep.getText().toString());
				} catch (IllegalStateException e) {
					e.printStackTrace();
				}
			}

		});
	}

}