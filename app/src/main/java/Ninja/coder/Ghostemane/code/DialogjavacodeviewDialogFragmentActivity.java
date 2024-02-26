package Ninja.coder.Ghostemane.code;

import Ninja.coder.Ghostemane.code.ExrtaFab;
import android.animation.ObjectAnimator;
import android.content.ClipData;
import android.content.ClipboardManager;
import android.view.MotionEvent;
import android.view.WindowManager;
import android.widget.LinearLayout;
import android.widget.FrameLayout;
import android.widget.TextView;
import android.widget.ImageView;
import android.widget.Toast;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.DialogFragment;
import io.github.rosemoe.sora.widget.CodeEditor;
import android.widget.ProgressBar;
import android.content.SharedPreferences;
import android.view.LayoutInflater;
import android.view.ViewGroup;
import android.os.Bundle;
import android.view.View;
import android.app.Activity;
import io.github.rosemoe.sora.widget.EditorColorScheme;
import android.graphics.Color;
import android.content.res.ColorStateList;
import io.github.rosemoe.sora.langs.java.JavaLanguage;
import android.graphics.drawable.ColorDrawable;
import android.view.animation.ScaleAnimation;
import android.view.animation.Animation;

public class DialogjavacodeviewDialogFragmentActivity extends DialogFragment {

	private ExrtaFab _fab;
	private LinearLayout toolbar;
	private FrameLayout linear1;
	private TextView textview1;
	private ImageView imageview1;
	private CodeEditor editor;
	private FrameLayout linear2;
	private ProgressBar pro;

	private SharedPreferences sp;

	@NonNull
	@Override
	public View onCreateView(@NonNull LayoutInflater _inflater, @Nullable ViewGroup _container,
			@Nullable Bundle _savedInstanceState) {
		View _view = _inflater.inflate(R.layout.dialogjavacodeview_dialog_fragment, _container, false);
		initialize(_savedInstanceState, _view);
		initializeLogic();
		return _view;
	}

	private void initialize(Bundle _savedInstanceState, View _view) {
		_fab = _view.findViewById(R.id._fab);

		toolbar = _view.findViewById(R.id.toolbar);
		linear1 = _view.findViewById(R.id.linear1);
		textview1 = _view.findViewById(R.id.textview1);
		imageview1 = _view.findViewById(R.id.imageview1);
		editor = _view.findViewById(R.id.editor);
		linear2 = _view.findViewById(R.id.linear2);
		pro = _view.findViewById(R.id.pro);
		sp = getContext().getSharedPreferences("sp", Activity.MODE_PRIVATE);

		imageview1.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View _view) {
				editor.formatCodeAsync();
			}
		});

		_fab.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View _view) {
				((ClipboardManager) getContext()
						.getSystemService(getContext().getApplicationContext().CLIPBOARD_SERVICE))
								.setPrimaryClip(ClipData.newPlainText("clipboard", editor.getText().toString()));
				Toast.makeText(requireActivity(),"done",2).show();
			}
		});
	}

	private void initializeLogic() {
		_clickAnimation(editor);
		getDialog().getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_VISIBLE
				| WindowManager.LayoutParams.SOFT_INPUT_ADJUST_RESIZE);
		if (sp.contains("bin")) {
			editor.setText(sp.getString("bin", ""));
		}

		editor.getColorScheme().setColor(EditorColorScheme.OPERATOR, Color.parseColor("#ff869e"));
		editor.getColorScheme().setColor(EditorColorScheme.BLOCK_LINE, Color.parseColor("#ff314d"));
		editor.getColorScheme().setColor(EditorColorScheme.BLOCK_LINE_CURRENT, Color.parseColor("#ff314d"));
		editor.getColorScheme().setColor(EditorColorScheme.NON_PRINTABLE_CHAR, Color.parseColor("#ffb9ffcb"));
		editor.getColorScheme().setColor(EditorColorScheme.CURRENT_LINE, Color.parseColor("#20171717"));
		editor.getColorScheme().setColor(EditorColorScheme.SELECTION_INSERT, Color.parseColor("#c06dff"));
		editor.getColorScheme().setColor(EditorColorScheme.SELECTION_HANDLE, Color.parseColor("#c06dff"));
		editor.getColorScheme().setColor(EditorColorScheme.LINE_NUMBER, Color.parseColor("#d9d9d9"));
		editor.getColorScheme().setColor(EditorColorScheme.LINE_DIVIDER, Color.parseColor("#FF2B2122"));
		editor.getColorScheme().setColor(EditorColorScheme.ATTRIBUTE_VALUE, Color.parseColor("#ffffdcb9"));
		editor.getColorScheme().setColor(EditorColorScheme.ATTRIBUTE_NAME, Color.parseColor("#FF1B4AD7"));
		editor.getColorScheme().setColor(EditorColorScheme.HTML_TAG, Color.parseColor("#ff92dc"));
		editor.getColorScheme().setColor(EditorColorScheme.TEXT_NORMAL, Color.parseColor("#ffebffd7"));
		editor.getColorScheme().setColor(EditorColorScheme.IDENTIFIER_NAME, Color.parseColor("#626262"));
		editor.getColorScheme().setColor(EditorColorScheme.COMMENT, Color.parseColor("#fff0be4b"));
		editor.getColorScheme().setColor(EditorColorScheme.KEYWORD, Color.parseColor("#ffffa1a1"));
		editor.getColorScheme().setColor(EditorColorScheme.print, Color.parseColor("#ffb4a1ff"));
		editor.getColorScheme().setColor(EditorColorScheme.Ninja, Color.parseColor("#ffffac94"));
		editor.getColorScheme().setColor(EditorColorScheme.LITERAL, Color.parseColor("#ffdea1ff"));
		editor.getColorScheme().setColor(EditorColorScheme.AUTO_COMP_PANEL_BG, Color.parseColor("#FF2B2122"));
		editor.getColorScheme().setColor(EditorColorScheme.AUTO_COMP_PANEL_CORNER, Color.parseColor("#ff94ffe7"));
		editor.getColorScheme().setColor(EditorColorScheme.LINE_NUMBER_BACKGROUND, Color.parseColor("#FF2B2122"));
		editor.getColorScheme().setColor(EditorColorScheme.WHOLE_BACKGROUND, Color.parseColor("#FF2B2122"));
		_fab.setBackgroundTintList(ColorStateList.valueOf(0xFF5C1900));
		pro.setVisibility(View.GONE);

		editor.setEditable(false);
		editor.setEditorLanguage(new JavaLanguage());
		_fab.setOnTouchListener(new View.OnTouchListener() {
			@Override
			public boolean onTouch(View v, MotionEvent event) {
				switch (event.getAction()) {
				case MotionEvent.ACTION_DOWN: {
					ObjectAnimator scaleX = new ObjectAnimator();
					scaleX.setTarget(_fab);
					scaleX.setPropertyName("scaleX");
					scaleX.setFloatValues(0.9f);
					scaleX.setDuration((int) 5);
					scaleX.start();

					ObjectAnimator scaleY = new ObjectAnimator();
					scaleY.setTarget(_fab);
					scaleY.setPropertyName("scaleY");
					scaleY.setFloatValues(0.9f);
					scaleY.setDuration((int) 5);
					scaleY.start();
					break;
				}
				case MotionEvent.ACTION_UP: {
					ObjectAnimator scaleX = new ObjectAnimator();
					scaleX.setTarget(_fab);
					scaleX.setPropertyName("scaleX");
					scaleX.setFloatValues((float) 1);
					scaleX.setDuration((int) 5);
					scaleX.start();

					ObjectAnimator scaleY = new ObjectAnimator();
					scaleY.setTarget(_fab);
					scaleY.setPropertyName("scaleY");
					scaleY.setFloatValues((float) 1);
					scaleY.setDuration((int) 5);
					scaleY.start();

					break;
				}
				}

				return false;
			}

		});
		ColorAndroid12.setColorFilter(imageview1);
	}

	@Override
	public void onStart() {
		super.onStart();
		if (getDialog() != null) {
			int width = ViewGroup.LayoutParams.MATCH_PARENT;
			int height = ViewGroup.LayoutParams.MATCH_PARENT;
			getDialog().getWindow().setLayout(width, height);
			getDialog().getWindow().setBackgroundDrawable(new ColorDrawable(0xFF2B2122));
		}
	}

	public void _clickAnimation(final View _view) {
		ScaleAnimation fade_in = new ScaleAnimation(0.9f, 1f, 0.9f, 1f, Animation.RELATIVE_TO_SELF, 0.5f,
				Animation.RELATIVE_TO_SELF, 0.7f);
		fade_in.setDuration(300);
		fade_in.setFillAfter(true);
		_view.startAnimation(fade_in);
	}
}
