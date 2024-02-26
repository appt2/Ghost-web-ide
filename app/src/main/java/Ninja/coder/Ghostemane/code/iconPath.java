package Ninja.coder.Ghostemane.code;


import android.content.res.ColorStateList;
import android.graphics.Color;
import android.graphics.PorterDuff;
import android.widget.EditText;
import com.google.android.material.textfield.TextInputLayout;

public class iconPath {
	public static void codeUifer(EditText editor, TextInputLayout top) {
		top.setEndIconTintMode(PorterDuff.Mode.SRC_IN);
		top.setEndIconTintList(ColorStateList.valueOf(Color.parseColor("#FFFCB07D")));
		
		if (editor.getText().toString().endsWith(".txt")) {
			top.setEndIconDrawable(R.drawable.textfile);
		} else if (editor.getText().toString().endsWith(".c")) {
			top.setEndIconDrawable(R.drawable.cfiles);
		} else if (editor.getText().toString().endsWith(".cpp")) {
			top.setEndIconDrawable(R.drawable.cppfile);
		} else if (editor.getText().toString().endsWith(".css")) {
			top.setEndIconDrawable(R.drawable.cssfile);
		} else if (editor.getText().toString().endsWith(".ghost")) {
			top.setEndIconDrawable(R.drawable.ghostfile);
		} else if (editor.getText().toString().endsWith(".go")) {
			top.setEndIconDrawable(R.drawable.gofile);
		} else if (editor.getText().toString().endsWith(".html")) {
			top.setEndIconDrawable(R.drawable.htmlfile);
		} else if (editor.getText().toString().endsWith(".java")) {
			top.setEndIconDrawable(R.drawable.javafile);
		} else if (editor.getText().toString().endsWith(".json")) {
			top.setEndIconDrawable(R.drawable.jsonfile);
		} else if (editor.getText().toString().endsWith(".md")) {
			top.setEndIconDrawable(R.drawable.markdownfile);
		} else if (editor.getText().toString().endsWith(".php")) {
			top.setEndIconDrawable(R.drawable.phpfile);
		} else if (editor.getText().toString().endsWith(".py")) {
			top.setEndIconDrawable(R.drawable.pythonfile);
		} else if (editor.getText().toString().endsWith(".rb") || editor.getText().toString().endsWith(".rbw")) {
			top.setEndIconDrawable(R.drawable.rubyfile);
		} else if (editor.getText().toString().endsWith(".scss")) {
			top.setEndIconDrawable(R.drawable.scssfile);
		} else if (editor.getText().toString().endsWith(".sh")) {
			top.setEndIconDrawable(R.drawable.shellfile);
		} else if (editor.getText().toString().endsWith(".swift")) {
			top.setEndIconDrawable(R.drawable.swiftfile);
		} else if (editor.getText().toString().endsWith(".xml")) {
			top.setEndIconDrawable(R.drawable.xmlfile);
			
		} else if (editor.getText().toString().endsWith(".cs")) {
			top.setEndIconDrawable(R.drawable.csharpfile);
		} else if(editor.getText().toString().endsWith(".groovy") || editor.getText().toString().endsWith(".gradle") ) {
			top.setEndIconDrawable(R.drawable.gradle);
		}else{
            top.setEndIconDrawable(R.drawable.hsiclose);
			top.setEndIconTintList(ColorStateList.valueOf(Color.RED));
			top.setEndIconOnClickListener(v -> {
				editor.setText("");
			});
			top.setEndIconTintMode(PorterDuff.Mode.MULTIPLY);
        }
		
	}
	
}
