package Ninja.coder.Ghostemane.code;

import io.github.rosemoe.sora.widget.EditorColorScheme;

/**
 * ColorScheme for HTML Language for editor
 */
 
public class HTMLScheme extends EditorColorScheme {

    @Override
    public void applyDefault() {
        super.applyDefault();
        setColor(OPERATOR, 0xFFFF0000);
        setColor(BLOCK_LINE, 0xff717171);
        setColor(BLOCK_LINE_CURRENT, 0xFFDC1F9A);
        setColor(NON_PRINTABLE_CHAR, 0xffdddddd);
        setColor(CURRENT_LINE, 0xff464646);
        setColor(SELECTION_INSERT, 0xffffffff);
        setColor(SELECTION_HANDLE, 0xffffffff);
        setColor(LINE_NUMBER, 0xff2b9eaf);
        setColor(LINE_DIVIDER, 0x02FFFFFF);
        setColor(LINE_NUMBER_BACKGROUND, 0x02FFFFFF);
        setColor(WHOLE_BACKGROUND, 0x02FFFFFF);
        setColor(ATTRIBUTE_VALUE, 0xFF2658ED);
        setColor(ATTRIBUTE_NAME, 0xFF1B4AD7);
        setColor(HTML_TAG, 0xFFED2683);
        setColor(TEXT_NORMAL, 0xffffffff);
        setColor(IDENTIFIER_NAME, 0xfff0be4b);
        setColor(COMMENT, 0xFFFF8000);
        setColor(print, 0xFFFF8000 );
    }

}
