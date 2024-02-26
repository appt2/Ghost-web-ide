package io.github.rosemoe.sora.widget.tooltip;

import android.widget.TextView;
import io.github.rosemoe.sora.event.Unsubscribe;

import io.github.rosemoe.sora.langs.html.HTMLLanguage;
import io.github.rosemoe.sora.text.Content;
import io.github.rosemoe.sora.text.Cursor;
import io.github.rosemoe.sora.widget.CodeEditor;
import io.github.rosemoe.sora.widget.EditorPopupWindow;

public class ToolTipHelper extends EditorPopupWindow {
	protected CodeEditor editor;
	protected TextView view;
	
	public ToolTipHelper(CodeEditor editor) {
		super(editor, FEATURE_SHOW_OUTSIDE_VIEW_ALLOWED);
		view = new TextView(editor.getContext());
		setContentView(view);
	}
	
	public void init() {
		setErrorHtml();
	}
	
	public void setErrorHtml() {
		if (editor.getEditorLanguage() instanceof HTMLLanguage) {
			Content text = editor.getText();
			if (text.toString().contains("<") || text.toString().equalsIgnoreCase("<")) {
				view.setText("this Error ");
				show();
			}
		}
	}
	
}
