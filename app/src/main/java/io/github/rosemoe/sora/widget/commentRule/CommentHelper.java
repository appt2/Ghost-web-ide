package io.github.rosemoe.sora.widget.commentRule;

import io.github.rosemoe.sora.langs.css3.CSS3Language;
import io.github.rosemoe.sora.langs.cpp.CppLanguage;
import io.github.rosemoe.sora.langs.javascript.JavaScriptLanguage;
import io.github.rosemoe.sora.langs.kotlin.KotlinLanguage;
import io.github.rosemoe.sora.langs.antlrlang.ANTLRV4Lang;
import io.github.rosemoe.sora.langs.dart.DartLang;
import io.github.rosemoe.sora.langs.php.PHPLanguage;
import io.github.rosemoe.sora.langs.php.PhpLexerBase;
import io.github.rosemoe.sora.langs.python.PythonLanguage;
import io.github.rosemoe.sora.langs.html.HTMLLanguage;
import io.github.rosemoe.sora.langs.java.JavaLanguage;
import io.github.rosemoe.sora.text.Content;
import io.github.rosemoe.sora.text.Cursor;
import io.github.rosemoe.sora.widget.CodeEditor;
/*
 @see package
*/

public class CommentHelper {
    protected CodeEditor editor;
    String longCommentStart = "/*";
    String longCommentEnd = "*/";
    String longCommentHtmlStart = "<!--";
    String longCommentHtmlEnd = "-->";

    public CommentHelper(CodeEditor editor) {
        this.editor = editor;
    }

    public void MakeCommentJava() {
        try {
            int leftLine = editor.getCursor().getLeftLine();
            int leftColumn = editor.getCursor().getLeftColumn();
            editor.getText().insert(leftLine, leftColumn, longCommentStart);
            int rightLine = editor.getCursor().getRightLine();
            int rightColumn = editor.getCursor().getRightColumn();
            editor.getText().insert(rightLine, rightColumn, longCommentEnd);
            //editor.selectAll();
        } catch (Exception err) {
            err.printStackTrace();
        }
    }

    public void MakeCommentHtml() {
        try {
            int leftLine = editor.getCursor().getLeftLine();
            int leftColumn = editor.getCursor().getLeftColumn();
            editor.getText().insert(leftLine, leftColumn, longCommentHtmlStart);
            int rightLine = editor.getCursor().getRightLine();
            int rightColumn = editor.getCursor().getRightColumn();
            editor.getText().insert(rightLine, rightColumn, longCommentHtmlEnd);
            //	editor.selectAll();
        } catch (Exception err) {
            err.printStackTrace();
        }
    }

    public void CustomComment(String start, String end) {
        try {
            int leftLine = editor.getCursor().getLeftLine();
            int leftColumn = editor.getCursor().getLeftColumn();
            editor.getText().insert(leftLine, leftColumn, start);
            int rightLine = editor.getCursor().getRightLine();
            int rightColumn = editor.getCursor().getRightColumn();
            editor.getText().insert(rightLine, rightColumn, end);
            //editor.selectAll();
        } catch (Exception err) {
            err.printStackTrace();
        }
    }

    public void AutoRun() {
        if (editor.getEditorLanguage() instanceof HTMLLanguage) {
            MakeCommentHtml();
        } else if (editor.getEditorLanguage() instanceof JavaLanguage) {
            MakeCommentJava();
        } else if (editor.getEditorLanguage() instanceof CSS3Language) {
            MakeCommentJava();
        } else if (editor.getEditorLanguage() instanceof CppLanguage) {
            MakeCommentJava();
        } else if (editor.getEditorLanguage() instanceof JavaScriptLanguage) {
            MakeCommentJava();
        } else if (editor.getEditorLanguage() instanceof KotlinLanguage) {
            MakeCommentJava();
        } else if (editor.getEditorLanguage() instanceof ANTLRV4Lang) {
            MakeCommentJava();
        } else if (editor.getEditorLanguage() instanceof DartLang) {
            MakeCommentJava();
        } else if (editor.getEditorLanguage() instanceof PHPLanguage) {
            MakeCommentJava();
        } else if (editor.getEditorLanguage() instanceof PythonLanguage) {
            String start = "\"\"\"";
            String end = start;
            CustomComment(start, end);
        } else {

        }
    }

    public void unComment() {
        Content text = editor.getText();
        Cursor cursor = editor.getCursor();
        for (int line = cursor.getLeftLine(); line <= cursor.getRightLine(); line++) {
            String lb = text.getLineString(line);
            if (lb.trim().startsWith("//")) {
                int cd = lb.indexOf("//");
                text.delete(line, cd, line, cd + 2);
            }
        }
    }

    public void onComment(String start, String end) {
        Content text = editor.getText();
        Cursor cursor = editor.getCursor();
        for (int line = cursor.getLeftLine(); line <= cursor.getRightLine(); line++) {
            String string = text.getLineString(line);
            if (string.trim().startsWith(start) && string.trim().endsWith(end)) {
                int str = string.indexOf(start);
                int ends = string.indexOf(end);
                text.delete(line, str, line, ends);
            }
        }

    }
}
