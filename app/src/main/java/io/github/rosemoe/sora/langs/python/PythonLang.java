package io.github.rosemoe.sora.langs.python;

import io.github.rosemoe.sora.interfaces.AutoCompleteProvider;
import io.github.rosemoe.sora.interfaces.CodeAnalyzer;
import io.github.rosemoe.sora.interfaces.EditorLanguage;
import io.github.rosemoe.sora.interfaces.NewlineHandler;
import io.github.rosemoe.sora.langs.internal.MyCharacter;
import io.github.rosemoe.sora.text.TextUtils;
import io.github.rosemoe.sora.widget.SymbolPairMatch;

public class PythonLang implements EditorLanguage {

    @Override
    public boolean useTab() {
        return true;
    }

    @Override
    public boolean isAutoCompleteChar(char ch) {
        return MyCharacter.isJavaIdentifierPart(ch) || ch == '.';
    }

    @Override
    public SymbolPairMatch getSymbolPairs() {
        return new SymbolPairMatch.DefaultSymbolPairs();
    }

    private NewlineHandler[] newlineHandlers = new NewlineHandler[] {new ColonHandler()};

    @Override
    public NewlineHandler[] getNewlineHandlers() {
        return newlineHandlers;
    }

    @Override
    public int getIndentAdvance(String content) {
        Tokens token;
        int advance = 0;
        PythonTextTokenizer t = new PythonTextTokenizer(content);
        boolean openBlock = false;
        try {
            while ((token = t.directNextToken()) != Tokens.EOF) {
                switch (token) {
                    case CLASS:
                    case DEF:
                    case IF:
                    case ELIF:
                    case FOR:
                    case WHILE:
                    case TRY:
                    case EXCEPT:
                        openBlock = !openBlock;
                        break;
                    case COLON:
                        advance++;
                        break;
                    case CONTINUE:
                    case BREAK:
                        openBlock = !openBlock;
                        advance--;
                        break;
                }
            }
            advance = Math.max(0, advance);
        } catch (Exception err) {
            err.printStackTrace();
        }

        return openBlock ? advance * 4 : 0;
    }

    @Override
    public AutoCompleteProvider getAutoCompleteProvider() {
        return new PythonAutoRun();
    }

    @Override
    public CodeAnalyzer getAnalyzer() {
        return new PythonCodeAnalyzer();
    }

    @Override
    public CharSequence format(CharSequence text) {
        return text;
    }

    class ColonHandler implements NewlineHandler {

        @Override
        public boolean matchesRequirement(String beforeText, String afterText) {
            return beforeText.endsWith(":");
        }

        @Override
        public HandleResult handleNewline(String beforeText, String afterText, int tabSize) {
            int count = TextUtils.countLeadingSpaceCount(beforeText, tabSize);
            int advanceBefore = getIndentAdvance(beforeText);
            int advanceAfter = getIndentAdvance(afterText);
            String text;
            StringBuilder sb =
                    new StringBuilder("\n")
                            .append(
                                    TextUtils.createIndent(
                                            count + advanceBefore, tabSize, useTab()))
                            .append('\n')
                            .append(
                                    text =
                                            TextUtils.createIndent(
                                                    count + advanceAfter, tabSize, useTab()));
            int shiftLeft = text.length() + 1;
            return new HandleResult(sb, shiftLeft);
        }
    }
}
