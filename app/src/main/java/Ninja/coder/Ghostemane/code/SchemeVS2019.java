package Ninja.coder.Ghostemane.code;

import io.github.rosemoe.sora.widget.EditorColorScheme;

/**
 * ColorScheme for editor
 * picked from Visual Studio 2019
 * Thanks to liyujiang-gzu (GitHub @liyujiang-gzu)
 */
public class SchemeVS2019 extends EditorColorScheme {

    @Override
    public void applyDefault() {
        super.applyDefault();
        setColor(ANNOTATION, 0xFF00DC30);
        setColor(FUNCTION_NAME, 0xFF894FD1);
        setColor(IDENTIFIER_NAME, 0xFF209AD1);
        setColor(IDENTIFIER_VAR, 0xFF0DB59D);
        setColor(LITERAL, 0xFFFF5C5F);
        setColor(OPERATOR, 0xFFCC1E58);
        setColor(COMMENT, 0xff57a64a);
        setColor(KEYWORD, 0xFFFC2222);
        setColor(WHOLE_BACKGROUND, 0x02FFFFFF);
        setColor(TEXT_NORMAL, 0xFFFFFFFF);
        setColor(LINE_NUMBER_BACKGROUND, 0x02FFFFFF);
        setColor(LINE_NUMBER, 0xff2b9eaf);
        setColor(LINE_DIVIDER, 0x02FFFFFF);
        setColor(SCROLL_BAR_THUMB, 0xff3e3e42);
        setColor(SCROLL_BAR_THUMB_PRESSED, 0xff9e9e9e);
        setColor(SELECTED_TEXT_BACKGROUND, 0xff3676b8);
        setColor(MATCHED_TEXT_BACKGROUND, 0xff653306);
        setColor(CURRENT_LINE, 0x1AFFFFFF);
        setColor(SELECTION_INSERT, 0xffffffff);
        setColor(SELECTION_HANDLE, 0xffffffff);
        setColor(BLOCK_LINE, 0xff717171);
        setColor(Ninja , 0xFF00FF88);
        setColor(Pro , 0xFFE55959);
        setColor(BLOCK_LINE_CURRENT, 0);
        setColor(NON_PRINTABLE_CHAR, 0xffdddddd);
    }

}
