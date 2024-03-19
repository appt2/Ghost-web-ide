package io.github.rosemoe.sora.langs.python;

import android.util.Log;

import io.github.rosemoe.sora.langs.IdentifierAutoComplete;
import io.github.rosemoe.sora.text.TextStyle;

import java.util.List;

import io.github.rosemoe.sora.data.NavigationItem;

import java.util.ArrayList;
import java.util.Stack;

import io.github.rosemoe.sora.data.BlockLine;
import org.antlr.v4.runtime.CharStreams;
import org.antlr.v4.runtime.CodePointCharStream;
import org.antlr.v4.runtime.Token;

import java.io.IOException;
import java.io.StringReader;

import io.github.rosemoe.sora.interfaces.CodeAnalyzer;
import io.github.rosemoe.sora.text.TextAnalyzeResult;
import io.github.rosemoe.sora.text.TextAnalyzer;
import io.github.rosemoe.sora.widget.EditorColorScheme;

public class PythonCodeAnalyzer implements CodeAnalyzer {
    private int COMPLETE = 25;
    private int INCOMPLETE = 24;

    @Override
    public void analyze(
            CharSequence content,
            TextAnalyzeResult result,
            TextAnalyzer.AnalyzeThread.Delegate delegate) {
        try {

            CodePointCharStream stream = CharStreams.fromReader(new StringReader(content.toString()));
            var lexer = new PythonLexer(stream);

            var classNamePrevious = false;
            Token token, preToken = null, prePreToken = null;
            boolean first = true;
            Stack<BlockLine> stack = new Stack<>();
            IdentifierAutoComplete.Identifiers in = new IdentifierAutoComplete.Identifiers();
            in.begin();
            List<NavigationItem> labels = new ArrayList<>();
            int maxSwitch = 1, currSwitch = 0;
            int lastLine = 1;
            int line, column;
            var prevIsTagName = false;
            while (delegate.shouldAnalyze()) {
                token = lexer.nextToken();
                if (token == null) break;
                if (token.getType() == PythonLexer.EOF) {
                    lastLine = token.getLine() - 1;
                    break;
                }
                line = token.getLine() - 1;
                column = token.getCharPositionInLine();
                lastLine = line;

                switch (token.getType()) {
                    case PythonLexer.WS:
                        if (first) {
                            result.addNormalIfNull();
                        }
                        break;
                    case PythonLexer.INDENT:
                    case PythonLexer.DEDENT:
                    case PythonLexer.FSTRING_START:
                    case PythonLexer.FSTRING_MIDDLE:
                    case PythonLexer.FSTRING_END:
                    case PythonLexer.FALSE:
                    case PythonLexer.AWAIT:
                    case PythonLexer.ELSE:
                    case PythonLexer.IMPORT:
                    case PythonLexer.PASS:
                    case PythonLexer.NONE:
                        result.addIfNeeded(line, column, Italic());
                        break;

                    case PythonLexer.BREAK:
                    case PythonLexer.EXCEPT:
                    case PythonLexer.IN:
                    case PythonLexer.RAISE:
                    case PythonLexer.TRUE:
                    case PythonLexer.CLASS:
                    case PythonLexer.DEF:
                        result.addIfNeeded(
                                line,
                                column,
                                TextStyle.makeStyle(EditorColorScheme.LITERAL, 0, true, false, false));
                        break;
                    case PythonLexer.COLON: {
                        classNamePrevious = false;
                        result.addIfNeeded(line, column, EditorColorScheme.HTML_TAG);
                        if (!stack.isEmpty()) {
                            BlockLine block1 = stack.pop();
                            block1.endLine = line;
                            block1.endColumn = column;
                            if (block1.startLine != block1.endLine) {
                                result.addBlockLine(block1);
                            }
                        }

                        break;
                    }

                    case PythonLexer.FINALLY:
                    case PythonLexer.IS:
                    case PythonLexer.RETURN:
                    case PythonLexer.AND:
                    case PythonLexer.CONTINUE:
                    case PythonLexer.FOR:
                    case PythonLexer.LAMBDA:
                    case PythonLexer.TRY:
                    case PythonLexer.AS:
                        result.addIfNeeded(line, column, Bold());
                        break;
                    case PythonLexer.FROM:
                    case PythonLexer.NONLOCAL:
                    case PythonLexer.WHILE:
                    case PythonLexer.ASSERT:
                    case PythonLexer.DEL:
                    case PythonLexer.GLOBAL:
                    case PythonLexer.NOT:
                    case PythonLexer.WITH:
                    case PythonLexer.ASYNC:
                    case PythonLexer.ELIF:
                    case PythonLexer.IF:
                    case PythonLexer.OR:
                    case PythonLexer.YIELD:
                        result.addIfNeeded(
                                line,
                                column,
                                TextStyle.makeStyle(EditorColorScheme.NON_PRINTABLE_CHAR, 0, true, false, false));
                        break;

                    case PythonLexer.LPAR:
                    case PythonLexer.LSQB:
                    case PythonLexer.LBRACE:
                    case PythonLexer.RPAR:
                    case PythonLexer.RSQB:
                    case PythonLexer.RBRACE:
                    case PythonLexer.DOT:
                    case PythonLexer.COMMA:
                    case PythonLexer.SEMI:
                    case PythonLexer.PLUS:
                    case PythonLexer.MINUS:
                    case PythonLexer.STAR:
                    case PythonLexer.SLASH:
                    case PythonLexer.VBAR:
                    case PythonLexer.AMPER:
                    case PythonLexer.LESS:
                    case PythonLexer.GREATER:
                    case PythonLexer.EQUAL:
                    case PythonLexer.PERCENT:
                    case PythonLexer.EQEQUAL:
                    case PythonLexer.NOTEQUAL:
                    case PythonLexer.LESSEQUAL:
                    case PythonLexer.GREATEREQUAL:
                    case PythonLexer.TILDE:
                    case PythonLexer.CIRCUMFLEX:
                    case PythonLexer.LEFTSHIFT:
                    case PythonLexer.RIGHTSHIFT:
                    case PythonLexer.DOUBLESTAR:
                    case PythonLexer.PLUSEQUAL:
                    case PythonLexer.MINEQUAL:
                    case PythonLexer.STAREQUAL:
                    case PythonLexer.SLASHEQUAL:
                    case PythonLexer.PERCENTEQUAL:
                    case PythonLexer.AMPEREQUAL:
                    case PythonLexer.VBAREQUAL:
                    case PythonLexer.CIRCUMFLEXEQUAL:
                    case PythonLexer.LEFTSHIFTEQUAL:
                    case PythonLexer.RIGHTSHIFTEQUAL:
                    case PythonLexer.DOUBLESTAREQUAL:
                    case PythonLexer.DOUBLESLASH:
                    case PythonLexer.DOUBLESLASHEQUAL:
                    case PythonLexer.AT:
                        result.addIfNeeded(line, column, EditorColorScheme.ATTRIBUTE_VALUE);
                        break;
                    case PythonLexer.ATEQUAL:
                    case PythonLexer.RARROW:
                    case PythonLexer.ELLIPSIS:
                    case PythonLexer.COLONEQUAL:
                    case PythonLexer.EXCLAMATION:
                        result.addIfNeeded(
                                line,
                                column,
                                TextStyle.makeStyle(EditorColorScheme.ATTRIBUTE_NAME, 0, true, false, false));
                        break;

                    case PythonLexer.NAME:
                    case PythonLexer.NUMBER:
                    case PythonLexer.STRING:
                        result.addIfNeeded(
                                line,
                                column,
                                TextStyle.makeStyle(EditorColorScheme.BLOCK_LINE, 0, true, false, false));
                        break;
                    case PythonLexer.TYPE_COMMENT:
                    case PythonLexer.NEWLINE:
                    case PythonLexer.COMMENT:
                        if (classNamePrevious) {
                            classNamePrevious = true;
                            result.addIfNeeded(
                                    line,
                                    column,
                                    TextStyle.makeStyle(EditorColorScheme.COMMENT, 0, true, false, false));
                        } else {
                            result.addIfNeeded(
                                    line,
                                    column,
                                    TextStyle.makeStyle(EditorColorScheme.LITERAL, 0, true, true, false));
                            classNamePrevious = false;
                        }
                        break;
                    case PythonLexer.EXPLICIT_LINE_JOINING:
                    case PythonLexer.ERROR_TOKEN:
                        result.addIfNeeded(
                                line,
                                column,
                                TextStyle.makeStyle(EditorColorScheme.HTML_TAG, 0, true, false, false));
                        break;

                    default:
                        try {
                            in.addIdentifier(token.getText().substring(line, column));
                        } catch (Exception err) {
                            in.addIdentifier(token.getText());
                        }

                        result.addIfNeeded(line, column, EditorColorScheme.TEXT_NORMAL);
                        prevIsTagName = false;
                        classNamePrevious = false;
                        break;
                }

                first = false;
            }
            in.finish();
            result.determine(lastLine);
            result.setExtra(in);
            result.setSuppressSwitch(maxSwitch + 10);
            result.setNavigation(labels);
        } catch (IOException e) {
            e.printStackTrace();
            Log.e("TAG", e.getMessage());
        }
    }

    public long Bold() {
        return TextStyle.makeStyle(EditorColorScheme.ATTRIBUTE_VALUE, 0, true, false, false);
    }

    public long Italic() {
        return TextStyle.makeStyle(EditorColorScheme.ATTRIBUTE_NAME, 0, false, true, false);
    }
}
