// Generated from ./HTMLLexer.g4 by ANTLR 4.13.1
package io.github.rosemoe.sora.langs.html;

import io.github.rosemoe.sora.langs.javascript.JavaScriptLexer;
import org.antlr.v4.runtime.Token;
import org.antlr.v4.runtime.*;
import org.antlr.v4.runtime.atn.ATN;
import org.antlr.v4.runtime.atn.ATNDeserializer;
import org.antlr.v4.runtime.atn.LexerATNSimulator;
import org.antlr.v4.runtime.atn.PredictionContextCache;
import org.antlr.v4.runtime.dfa.DFA;

import java.util.LinkedList;

@SuppressWarnings({"all", "warnings", "unchecked", "unused", "cast", "CheckReturnValue", "this-escape"})
public class HTMLLexer extends Lexer {
    public static final int
            SCRIPT_END = 1, STYLE_END = 2, HTML_COMMENT = 3, HTML_CONDITIONAL_COMMENT = 4,
            XML = 5, CDATA = 6, DTD = 7, SCRIPTLET = 8, SEA_WS = 9, TAG_OPEN = 10, HTML_TEXT = 11,
            TAG_CLOSE = 12, TAG_SLASH_CLOSE = 13, TAG_SLASH = 14, EMPTYTEXR = 15, TAG_EQUALS = 16,
            TAG_NAME = 17, TAG_WHITESPACE = 18, SCRIPT_BODY = 19, SCRIPT_SHORT_BODY = 20,
            STYLE_BODY = 21, STYLE_SHORT_BODY = 22, ATTVALUE_VALUE = 23, ATTRIBUTE = 24;
    public static final int
            TAG = 1, SCRIPT = 2, STYLE = 3, ATTVALUE = 4;
    public static final String[] ruleNames = makeRuleNames();
    /**
     * @deprecated Use {@link #VOCABULARY} instead.
     */
    @Deprecated
    public static final String[] tokenNames;
    public static final String _serializedATN =
            "\u0004\u0000\u0018\u0173\u0006\uffff\uffff\u0006\uffff\uffff\u0006\uffff" +
                    "\uffff\u0006\uffff\uffff\u0006\uffff\uffff\u0002\u0000\u0007\u0000\u0002" +
                    "\u0001\u0007\u0001\u0002\u0002\u0007\u0002\u0002\u0003\u0007\u0003\u0002" +
                    "\u0004\u0007\u0004\u0002\u0005\u0007\u0005\u0002\u0006\u0007\u0006\u0002" +
                    "\u0007\u0007\u0007\u0002\b\u0007\b\u0002\t\u0007\t\u0002\n\u0007\n\u0002" +
                    "\u000b\u0007\u000b\u0002\f\u0007\f\u0002\r\u0007\r\u0002\u000e\u0007\u000e" +
                    "\u0002\u000f\u0007\u000f\u0002\u0010\u0007\u0010\u0002\u0011\u0007\u0011" +
                    "\u0002\u0012\u0007\u0012\u0002\u0013\u0007\u0013\u0002\u0014\u0007\u0014" +
                    "\u0002\u0015\u0007\u0015\u0002\u0016\u0007\u0016\u0002\u0017\u0007\u0017" +
                    "\u0002\u0018\u0007\u0018\u0002\u0019\u0007\u0019\u0002\u001a\u0007\u001a" +
                    "\u0002\u001b\u0007\u001b\u0002\u001c\u0007\u001c\u0002\u001d\u0007\u001d" +
                    "\u0002\u001e\u0007\u001e\u0002\u001f\u0007\u001f\u0001\u0000\u0001\u0000" +
                    "\u0001\u0000\u0001\u0000\u0001\u0000\u0001\u0000\u0005\u0000L\b\u0000" +
                    "\n\u0000\f\u0000O\t\u0000\u0001\u0000\u0001\u0000\u0001\u0000\u0001\u0000" +
                    "\u0001\u0001\u0001\u0001\u0001\u0001\u0001\u0001\u0001\u0001\u0005\u0001" +
                    "Z\b\u0001\n\u0001\f\u0001]\t\u0001\u0001\u0001\u0001\u0001\u0001\u0001" +
                    "\u0001\u0002\u0001\u0002\u0001\u0002\u0001\u0002\u0001\u0002\u0001\u0002" +
                    "\u0001\u0002\u0005\u0002i\b\u0002\n\u0002\f\u0002l\t\u0002\u0001\u0002" +
                    "\u0001\u0002\u0001\u0003\u0001\u0003\u0001\u0003\u0001\u0003\u0001\u0003" +
                    "\u0001\u0003\u0001\u0003\u0001\u0003\u0001\u0003\u0001\u0003\u0001\u0003" +
                    "\u0005\u0003{\b\u0003\n\u0003\f\u0003~\t\u0003\u0001\u0003\u0001\u0003" +
                    "\u0001\u0003\u0001\u0003\u0001\u0004\u0001\u0004\u0001\u0004\u0001\u0004" +
                    "\u0005\u0004\u0088\b\u0004\n\u0004\f\u0004\u008b\t\u0004\u0001\u0004\u0001" +
                    "\u0004\u0001\u0005\u0001\u0005\u0001\u0005\u0001\u0005\u0005\u0005\u0093" +
                    "\b\u0005\n\u0005\f\u0005\u0096\t\u0005\u0001\u0005\u0001\u0005\u0001\u0005" +
                    "\u0001\u0005\u0001\u0005\u0001\u0005\u0005\u0005\u009e\b\u0005\n\u0005" +
                    "\f\u0005\u00a1\t\u0005\u0001\u0005\u0001\u0005\u0003\u0005\u00a5\b\u0005" +
                    "\u0001\u0006\u0001\u0006\u0003\u0006\u00a9\b\u0006\u0001\u0006\u0004\u0006" +
                    "\u00ac\b\u0006\u000b\u0006\f\u0006\u00ad\u0001\u0007\u0001\u0007\u0001" +
                    "\u0007\u0001\u0007\u0001\b\u0004\b\u00b5\b\b\u000b\b\f\b\u00b6\u0001\t" +
                    "\u0001\t\u0001\t\u0001\t\u0001\n\u0001\n\u0001\n\u0001\n\u0001\n\u0001" +
                    "\u000b\u0001\u000b\u0001\f\u0001\f\u0001\r\u0001\r\u0001\r\u0001\r\u0001" +
                    "\u000e\u0001\u000e\u0005\u000e\u00cc\b\u000e\n\u000e\f\u000e\u00cf\t\u000e" +
                    "\u0001\u000f\u0001\u000f\u0001\u000f\u0001\u000f\u0001\u0010\u0001\u0010" +
                    "\u0001\u0010\u0001\u0010\u0001\u0010\u0001\u0010\u0001\u0010\u0001\u0010" +
                    "\u0001\u0010\u0001\u0010\u0005\u0010\u00df\b\u0010\n\u0010\f\u0010\u00e2" +
                    "\t\u0010\u0001\u0010\u0001\u0010\u0001\u0010\u0001\u0010\u0001\u0010\u0001" +
                    "\u0010\u0001\u0010\u0001\u0010\u0001\u0010\u0001\u0010\u0001\u0010\u0001" +
                    "\u0010\u0001\u0010\u0001\u0010\u0001\u0010\u0001\u0011\u0001\u0011\u0001" +
                    "\u0012\u0001\u0012\u0001\u0013\u0001\u0013\u0001\u0013\u0001\u0013\u0003" +
                    "\u0013\u00fb\b\u0013\u0001\u0014\u0003\u0014\u00fe\b\u0014\u0001\u0015" +
                    "\u0005\u0015\u0101\b\u0015\n\u0015\f\u0015\u0104\t\u0015\u0001\u0015\u0001" +
                    "\u0015\u0001\u0015\u0001\u0015\u0001\u0015\u0001\u0015\u0001\u0015\u0001" +
                    "\u0015\u0001\u0015\u0001\u0016\u0001\u0016\u0001\u0016\u0001\u0016\u0001" +
                    "\u0016\u0001\u0016\u0001\u0016\u0005\u0016\u0116\b\u0016\n\u0016\f\u0016" +
                    "\u0119\t\u0016\u0001\u0016\u0001\u0016\u0001\u0016\u0001\u0016\u0001\u0016" +
                    "\u0001\u0016\u0001\u0016\u0001\u0016\u0001\u0016\u0001\u0016\u0001\u0016" +
                    "\u0001\u0016\u0001\u0016\u0001\u0016\u0001\u0017\u0005\u0017\u012a\b\u0017" +
                    "\n\u0017\f\u0017\u012d\t\u0017\u0001\u0017\u0001\u0017\u0001\u0017\u0001" +
                    "\u0017\u0001\u0017\u0001\u0017\u0001\u0017\u0001\u0017\u0001\u0017\u0001" +
                    "\u0018\u0005\u0018\u0139\b\u0018\n\u0018\f\u0018\u013c\t\u0018\u0001\u0018" +
                    "\u0001\u0018\u0001\u0018\u0001\u0018\u0001\u0019\u0001\u0019\u0001\u0019" +
                    "\u0001\u0019\u0001\u0019\u0003\u0019\u0147\b\u0019\u0001\u001a\u0004\u001a" +
                    "\u014a\b\u001a\u000b\u001a\f\u001a\u014b\u0001\u001a\u0003\u001a\u014f" +
                    "\b\u001a\u0001\u001b\u0003\u001b\u0152\b\u001b\u0001\u001c\u0001\u001c" +
                    "\u0004\u001c\u0156\b\u001c\u000b\u001c\f\u001c\u0157\u0001\u001d\u0004" +
                    "\u001d\u015b\b\u001d\u000b\u001d\f\u001d\u015c\u0001\u001d\u0003\u001d" +
                    "\u0160\b\u001d\u0001\u001e\u0001\u001e\u0005\u001e\u0164\b\u001e\n\u001e" +
                    "\f\u001e\u0167\t\u001e\u0001\u001e\u0001\u001e\u0001\u001f\u0001\u001f" +
                    "\u0005\u001f\u016d\b\u001f\n\u001f\f\u001f\u0170\t\u001f\u0001\u001f\u0001" +
                    "\u001f\u000bM[j|\u0089\u0094\u009f\u00e0\u0102\u0117\u012b\u0000 \u0005" +
                    "\u0003\u0007\u0004\t\u0005\u000b\u0006\r\u0007\u000f\b\u0011\t\u0013\n" +
                    "\u0015\u000b\u0017\f\u0019\r\u001b\u000e\u001d\u000f\u001f\u0010!\u0011" +
                    "#\u0012%\u0013\'\u0000)\u0000+\u0000-\u0000/\u00141\u00153\u00165\u0017" +
                    "7\u00189\u0000;\u0000=\u0000?\u0000A\u0000C\u0000\u0005\u0000\u0001\u0002" +
                    "\u0003\u0004\f\u0002\u0000\t\t  \u0001\u0000<<\u0004\u0000  09AZaz\u0003" +
                    "\u0000\t\n\r\r  \u0003\u000009AFaf\u0001\u000009\u0002\u0000-.__\u0003" +
                    "\u0000\u00b7\u00b7\u0300\u036f\u203f\u2040\b\u0000::AZaz\u2070\u218f\u2c00" +
                    "\u2fef\u3001\u8000\ud7ff\u8000\uf900\u8000\ufdcf\u8000\ufdf0\u8000\ufffd" +
                    "\u0007\u0000##+;==??AZ__az\u0002\u0000\"\"<<\u0002\u0000\'\'<<\u0184\u0000" +
                    "\u0005\u0001\u0000\u0000\u0000\u0000\u0007\u0001\u0000\u0000\u0000\u0000" +
                    "\t\u0001\u0000\u0000\u0000\u0000\u000b\u0001\u0000\u0000\u0000\u0000\r" +
                    "\u0001\u0000\u0000\u0000\u0000\u000f\u0001\u0000\u0000\u0000\u0000\u0011" +
                    "\u0001\u0000\u0000\u0000\u0000\u0013\u0001\u0000\u0000\u0000\u0000\u0015" +
                    "\u0001\u0000\u0000\u0000\u0001\u0017\u0001\u0000\u0000\u0000\u0001\u0019" +
                    "\u0001\u0000\u0000\u0000\u0001\u001b\u0001\u0000\u0000\u0000\u0001\u001d" +
                    "\u0001\u0000\u0000\u0000\u0001\u001f\u0001\u0000\u0000\u0000\u0001!\u0001" +
                    "\u0000\u0000\u0000\u0001#\u0001\u0000\u0000\u0000\u0001%\u0001\u0000\u0000" +
                    "\u0000\u0002/\u0001\u0000\u0000\u0000\u00031\u0001\u0000\u0000\u0000\u0003" +
                    "3\u0001\u0000\u0000\u0000\u00045\u0001\u0000\u0000\u0000\u00047\u0001" +
                    "\u0000\u0000\u0000\u0005E\u0001\u0000\u0000\u0000\u0007T\u0001\u0000\u0000" +
                    "\u0000\ta\u0001\u0000\u0000\u0000\u000bo\u0001\u0000\u0000\u0000\r\u0083" +
                    "\u0001\u0000\u0000\u0000\u000f\u00a4\u0001\u0000\u0000\u0000\u0011\u00ab" +
                    "\u0001\u0000\u0000\u0000\u0013\u00af\u0001\u0000\u0000\u0000\u0015\u00b4" +
                    "\u0001\u0000\u0000\u0000\u0017\u00b8\u0001\u0000\u0000\u0000\u0019\u00bc" +
                    "\u0001\u0000\u0000\u0000\u001b\u00c1\u0001\u0000\u0000\u0000\u001d\u00c3" +
                    "\u0001\u0000\u0000\u0000\u001f\u00c5\u0001\u0000\u0000\u0000!\u00c9\u0001" +
                    "\u0000\u0000\u0000#\u00d0\u0001\u0000\u0000\u0000%\u00d4\u0001\u0000\u0000" +
                    "\u0000\'\u00f2\u0001\u0000\u0000\u0000)\u00f4\u0001\u0000\u0000\u0000" +
                    "+\u00fa\u0001\u0000\u0000\u0000-\u00fd\u0001\u0000\u0000\u0000/\u0102" +
                    "\u0001\u0000\u0000\u00001\u0117\u0001\u0000\u0000\u00003\u012b\u0001\u0000" +
                    "\u0000\u00005\u013a\u0001\u0000\u0000\u00007\u0146\u0001\u0000\u0000\u0000" +
                    "9\u0149\u0001\u0000\u0000\u0000;\u0151\u0001\u0000\u0000\u0000=\u0153" +
                    "\u0001\u0000\u0000\u0000?\u015a\u0001\u0000\u0000\u0000A\u0161\u0001\u0000" +
                    "\u0000\u0000C\u016a\u0001\u0000\u0000\u0000EF\u0005<\u0000\u0000FG\u0005" +
                    "!\u0000\u0000GH\u0005-\u0000\u0000HI\u0005-\u0000\u0000IM\u0001\u0000" +
                    "\u0000\u0000JL\t\u0000\u0000\u0000KJ\u0001\u0000\u0000\u0000LO\u0001\u0000" +
                    "\u0000\u0000MN\u0001\u0000\u0000\u0000MK\u0001\u0000\u0000\u0000NP\u0001" +
                    "\u0000\u0000\u0000OM\u0001\u0000\u0000\u0000PQ\u0005-\u0000\u0000QR\u0005" +
                    "-\u0000\u0000RS\u0005>\u0000\u0000S\u0006\u0001\u0000\u0000\u0000TU\u0005" +
                    "<\u0000\u0000UV\u0005!\u0000\u0000VW\u0005[\u0000\u0000W[\u0001\u0000" +
                    "\u0000\u0000XZ\t\u0000\u0000\u0000YX\u0001\u0000\u0000\u0000Z]\u0001\u0000" +
                    "\u0000\u0000[\\\u0001\u0000\u0000\u0000[Y\u0001\u0000\u0000\u0000\\^\u0001" +
                    "\u0000\u0000\u0000][\u0001\u0000\u0000\u0000^_\u0005]\u0000\u0000_`\u0005" +
                    ">\u0000\u0000`\b\u0001\u0000\u0000\u0000ab\u0005<\u0000\u0000bc\u0005" +
                    "?\u0000\u0000cd\u0005x\u0000\u0000de\u0005m\u0000\u0000ef\u0005l\u0000" +
                    "\u0000fj\u0001\u0000\u0000\u0000gi\t\u0000\u0000\u0000hg\u0001\u0000\u0000" +
                    "\u0000il\u0001\u0000\u0000\u0000jk\u0001\u0000\u0000\u0000jh\u0001\u0000" +
                    "\u0000\u0000km\u0001\u0000\u0000\u0000lj\u0001\u0000\u0000\u0000mn\u0005" +
                    ">\u0000\u0000n\n\u0001\u0000\u0000\u0000op\u0005<\u0000\u0000pq\u0005" +
                    "!\u0000\u0000qr\u0005[\u0000\u0000rs\u0005C\u0000\u0000st\u0005D\u0000" +
                    "\u0000tu\u0005A\u0000\u0000uv\u0005T\u0000\u0000vw\u0005A\u0000\u0000" +
                    "wx\u0005[\u0000\u0000x|\u0001\u0000\u0000\u0000y{\t\u0000\u0000\u0000" +
                    "zy\u0001\u0000\u0000\u0000{~\u0001\u0000\u0000\u0000|}\u0001\u0000\u0000" +
                    "\u0000|z\u0001\u0000\u0000\u0000}\u007f\u0001\u0000\u0000\u0000~|\u0001" +
                    "\u0000\u0000\u0000\u007f\u0080\u0005]\u0000\u0000\u0080\u0081\u0005]\u0000" +
                    "\u0000\u0081\u0082\u0005>\u0000\u0000\u0082\f\u0001\u0000\u0000\u0000" +
                    "\u0083\u0084\u0005<\u0000\u0000\u0084\u0085\u0005!\u0000\u0000\u0085\u0089" +
                    "\u0001\u0000\u0000\u0000\u0086\u0088\t\u0000\u0000\u0000\u0087\u0086\u0001" +
                    "\u0000\u0000\u0000\u0088\u008b\u0001\u0000\u0000\u0000\u0089\u008a\u0001" +
                    "\u0000\u0000\u0000\u0089\u0087\u0001\u0000\u0000\u0000\u008a\u008c\u0001" +
                    "\u0000\u0000\u0000\u008b\u0089\u0001\u0000\u0000\u0000\u008c\u008d\u0005" +
                    ">\u0000\u0000\u008d\u000e\u0001\u0000\u0000\u0000\u008e\u008f\u0005<\u0000" +
                    "\u0000\u008f\u0090\u0005?\u0000\u0000\u0090\u0094\u0001\u0000\u0000\u0000" +
                    "\u0091\u0093\t\u0000\u0000\u0000\u0092\u0091\u0001\u0000\u0000\u0000\u0093" +
                    "\u0096\u0001\u0000\u0000\u0000\u0094\u0095\u0001\u0000\u0000\u0000\u0094" +
                    "\u0092\u0001\u0000\u0000\u0000\u0095\u0097\u0001\u0000\u0000\u0000\u0096" +
                    "\u0094\u0001\u0000\u0000\u0000\u0097\u0098\u0005?\u0000\u0000\u0098\u00a5" +
                    "\u0005>\u0000\u0000\u0099\u009a\u0005<\u0000\u0000\u009a\u009b\u0005%" +
                    "\u0000\u0000\u009b\u009f\u0001\u0000\u0000\u0000\u009c\u009e\t\u0000\u0000" +
                    "\u0000\u009d\u009c\u0001\u0000\u0000\u0000\u009e\u00a1\u0001\u0000\u0000" +
                    "\u0000\u009f\u00a0\u0001\u0000\u0000\u0000\u009f\u009d\u0001\u0000\u0000" +
                    "\u0000\u00a0\u00a2\u0001\u0000\u0000\u0000\u00a1\u009f\u0001\u0000\u0000" +
                    "\u0000\u00a2\u00a3\u0005%\u0000\u0000\u00a3\u00a5\u0005>\u0000\u0000\u00a4" +
                    "\u008e\u0001\u0000\u0000\u0000\u00a4\u0099\u0001\u0000\u0000\u0000\u00a5" +
                    "\u0010\u0001\u0000\u0000\u0000\u00a6\u00ac\u0007\u0000\u0000\u0000\u00a7" +
                    "\u00a9\u0005\r\u0000\u0000\u00a8\u00a7\u0001\u0000\u0000\u0000\u00a8\u00a9" +
                    "\u0001\u0000\u0000\u0000\u00a9\u00aa\u0001\u0000\u0000\u0000\u00aa\u00ac" +
                    "\u0005\n\u0000\u0000\u00ab\u00a6\u0001\u0000\u0000\u0000\u00ab\u00a8\u0001" +
                    "\u0000\u0000\u0000\u00ac\u00ad\u0001\u0000\u0000\u0000\u00ad\u00ab\u0001" +
                    "\u0000\u0000\u0000\u00ad\u00ae\u0001\u0000\u0000\u0000\u00ae\u0012\u0001" +
                    "\u0000\u0000\u0000\u00af\u00b0\u0005<\u0000\u0000\u00b0\u00b1\u0001\u0000" +
                    "\u0000\u0000\u00b1\u00b2\u0006\u0007\u0000\u0000\u00b2\u0014\u0001\u0000" +
                    "\u0000\u0000\u00b3\u00b5\b\u0001\u0000\u0000\u00b4\u00b3\u0001\u0000\u0000" +
                    "\u0000\u00b5\u00b6\u0001\u0000\u0000\u0000\u00b6\u00b4\u0001\u0000\u0000" +
                    "\u0000\u00b6\u00b7\u0001\u0000\u0000\u0000\u00b7\u0016\u0001\u0000\u0000" +
                    "\u0000\u00b8\u00b9\u0005>\u0000\u0000\u00b9\u00ba\u0001\u0000\u0000\u0000" +
                    "\u00ba\u00bb\u0006\t\u0001\u0000\u00bb\u0018\u0001\u0000\u0000\u0000\u00bc" +
                    "\u00bd\u0005/\u0000\u0000\u00bd\u00be\u0005>\u0000\u0000\u00be\u00bf\u0001" +
                    "\u0000\u0000\u0000\u00bf\u00c0\u0006\n\u0001\u0000\u00c0\u001a\u0001\u0000" +
                    "\u0000\u0000\u00c1\u00c2\u0005/\u0000\u0000\u00c2\u001c\u0001\u0000\u0000" +
                    "\u0000\u00c3\u00c4\u0007\u0002\u0000\u0000\u00c4\u001e\u0001\u0000\u0000" +
                    "\u0000\u00c5\u00c6\u0005=\u0000\u0000\u00c6\u00c7\u0001\u0000\u0000\u0000" +
                    "\u00c7\u00c8\u0006\r\u0002\u0000\u00c8 \u0001\u0000\u0000\u0000\u00c9" +
                    "\u00cd\u0003-\u0014\u0000\u00ca\u00cc\u0003+\u0013\u0000\u00cb\u00ca\u0001" +
                    "\u0000\u0000\u0000\u00cc\u00cf\u0001\u0000\u0000\u0000\u00cd\u00cb\u0001" +
                    "\u0000\u0000\u0000\u00cd\u00ce\u0001\u0000\u0000\u0000\u00ce\"\u0001\u0000" +
                    "\u0000\u0000\u00cf\u00cd\u0001\u0000\u0000\u0000\u00d0\u00d1\u0007\u0003" +
                    "\u0000\u0000\u00d1\u00d2\u0001\u0000\u0000\u0000\u00d2\u00d3\u0006\u000f" +
                    "\u0003\u0000\u00d3$\u0001\u0000\u0000\u0000\u00d4\u00d5\u0005<\u0000\u0000" +
                    "\u00d5\u00d6\u0005s\u0000\u0000\u00d6\u00d7\u0005c\u0000\u0000\u00d7\u00d8" +
                    "\u0005r\u0000\u0000\u00d8\u00d9\u0005i\u0000\u0000\u00d9\u00da\u0005p" +
                    "\u0000\u0000\u00da\u00db\u0005t\u0000\u0000\u00db\u00dc\u0005>\u0000\u0000" +
                    "\u00dc\u00e0\u0001\u0000\u0000\u0000\u00dd\u00df\t\u0000\u0000\u0000\u00de" +
                    "\u00dd\u0001\u0000\u0000\u0000\u00df\u00e2\u0001\u0000\u0000\u0000\u00e0" +
                    "\u00e1\u0001\u0000\u0000\u0000\u00e0\u00de\u0001\u0000\u0000\u0000\u00e1" +
                    "\u00e3\u0001\u0000\u0000\u0000\u00e2\u00e0\u0001\u0000\u0000\u0000\u00e3" +
                    "\u00e4\u0005<\u0000\u0000\u00e4\u00e5\u0005/\u0000\u0000\u00e5\u00e6\u0005" +
                    "s\u0000\u0000\u00e6\u00e7\u0005c\u0000\u0000\u00e7\u00e8\u0005r\u0000" +
                    "\u0000\u00e8\u00e9\u0005i\u0000\u0000\u00e9\u00ea\u0005p\u0000\u0000\u00ea" +
                    "\u00eb\u0005t\u0000\u0000\u00eb\u00ec\u0005>\u0000\u0000\u00ec\u00ed\u0001" +
                    "\u0000\u0000\u0000\u00ed\u00ee\u0006\u0010\u0004\u0000\u00ee\u00ef\u0001" +
                    "\u0000\u0000\u0000\u00ef\u00f0\u0006\u0010\u0005\u0000\u00f0\u00f1\u0006" +
                    "\u0010\u0001\u0000\u00f1&\u0001\u0000\u0000\u0000\u00f2\u00f3\u0007\u0004" +
                    "\u0000\u0000\u00f3(\u0001\u0000\u0000\u0000\u00f4\u00f5\u0007\u0005\u0000" +
                    "\u0000\u00f5*\u0001\u0000\u0000\u0000\u00f6\u00fb\u0003-\u0014\u0000\u00f7" +
                    "\u00fb\u0007\u0006\u0000\u0000\u00f8\u00fb\u0003)\u0012\u0000\u00f9\u00fb" +
                    "\u0007\u0007\u0000\u0000\u00fa\u00f6\u0001\u0000\u0000\u0000\u00fa\u00f7" +
                    "\u0001\u0000\u0000\u0000\u00fa\u00f8\u0001\u0000\u0000\u0000\u00fa\u00f9" +
                    "\u0001\u0000\u0000\u0000\u00fb,\u0001\u0000\u0000\u0000\u00fc\u00fe\u0007" +
                    "\b\u0000\u0000\u00fd\u00fc\u0001\u0000\u0000\u0000\u00fe.\u0001\u0000" +
                    "\u0000\u0000\u00ff\u0101\t\u0000\u0000\u0000\u0100\u00ff\u0001\u0000\u0000" +
                    "\u0000\u0101\u0104\u0001\u0000\u0000\u0000\u0102\u0103\u0001\u0000\u0000" +
                    "\u0000\u0102\u0100\u0001\u0000\u0000\u0000\u0103\u0105\u0001\u0000\u0000" +
                    "\u0000\u0104\u0102\u0001\u0000\u0000\u0000\u0105\u0106\u0005<\u0000\u0000" +
                    "\u0106\u0107\u0005/\u0000\u0000\u0107\u0108\u0005>\u0000\u0000\u0108\u0109" +
                    "\u0001\u0000\u0000\u0000\u0109\u010a\u0006\u0015\u0006\u0000\u010a\u010b" +
                    "\u0001\u0000\u0000\u0000\u010b\u010c\u0006\u0015\u0005\u0000\u010c\u010d" +
                    "\u0006\u0015\u0001\u0000\u010d0\u0001\u0000\u0000\u0000\u010e\u010f\u0005" +
                    "<\u0000\u0000\u010f\u0110\u0005s\u0000\u0000\u0110\u0111\u0005t\u0000" +
                    "\u0000\u0111\u0112\u0005y\u0000\u0000\u0112\u0113\u0005l\u0000\u0000\u0113" +
                    "\u0114\u0005e\u0000\u0000\u0114\u0116\u0005>\u0000\u0000\u0115\u010e\u0001" +
                    "\u0000\u0000\u0000\u0116\u0119\u0001\u0000\u0000\u0000\u0117\u0118\u0001" +
                    "\u0000\u0000\u0000\u0117\u0115\u0001\u0000\u0000\u0000\u0118\u011a\u0001" +
                    "\u0000\u0000\u0000\u0119\u0117\u0001\u0000\u0000\u0000\u011a\u011b\u0005" +
                    "<\u0000\u0000\u011b\u011c\u0005/\u0000\u0000\u011c\u011d\u0005s\u0000" +
                    "\u0000\u011d\u011e\u0005t\u0000\u0000\u011e\u011f\u0005y\u0000\u0000\u011f" +
                    "\u0120\u0005l\u0000\u0000\u0120\u0121\u0005e\u0000\u0000\u0121\u0122\u0005" +
                    ">\u0000\u0000\u0122\u0123\u0001\u0000\u0000\u0000\u0123\u0124\u0006\u0016" +
                    "\u0007\u0000\u0124\u0125\u0001\u0000\u0000\u0000\u0125\u0126\u0006\u0016" +
                    "\u0005\u0000\u0126\u0127\u0006\u0016\u0001\u0000\u01272\u0001\u0000\u0000" +
                    "\u0000\u0128\u012a\t\u0000\u0000\u0000\u0129\u0128\u0001\u0000\u0000\u0000" +
                    "\u012a\u012d\u0001\u0000\u0000\u0000\u012b\u012c\u0001\u0000\u0000\u0000" +
                    "\u012b\u0129\u0001\u0000\u0000\u0000\u012c\u012e\u0001\u0000\u0000\u0000" +
                    "\u012d\u012b\u0001\u0000\u0000\u0000\u012e\u012f\u0005<\u0000\u0000\u012f" +
                    "\u0130\u0005/\u0000\u0000\u0130\u0131\u0005>\u0000\u0000\u0131\u0132\u0001" +
                    "\u0000\u0000\u0000\u0132\u0133\u0006\u0017\b\u0000\u0133\u0134\u0001\u0000" +
                    "\u0000\u0000\u0134\u0135\u0006\u0017\u0005\u0000\u0135\u0136\u0006\u0017" +
                    "\u0001\u0000\u01364\u0001\u0000\u0000\u0000\u0137\u0139\u0005 \u0000\u0000" +
                    "\u0138\u0137\u0001\u0000\u0000\u0000\u0139\u013c\u0001\u0000\u0000\u0000" +
                    "\u013a\u0138\u0001\u0000\u0000\u0000\u013a\u013b\u0001\u0000\u0000\u0000" +
                    "\u013b\u013d\u0001\u0000\u0000\u0000\u013c\u013a\u0001\u0000\u0000\u0000" +
                    "\u013d\u013e\u00037\u0019\u0000\u013e\u013f\u0001\u0000\u0000\u0000\u013f" +
                    "\u0140\u0006\u0018\u0001\u0000\u01406\u0001\u0000\u0000\u0000\u0141\u0147" +
                    "\u0003A\u001e\u0000\u0142\u0147\u0003C\u001f\u0000\u0143\u0147\u00039" +
                    "\u001a\u0000\u0144\u0147\u0003=\u001c\u0000\u0145\u0147\u0003?\u001d\u0000" +
                    "\u0146\u0141\u0001\u0000\u0000\u0000\u0146\u0142\u0001\u0000\u0000\u0000" +
                    "\u0146\u0143\u0001\u0000\u0000\u0000\u0146\u0144\u0001\u0000\u0000\u0000" +
                    "\u0146\u0145\u0001\u0000\u0000\u0000\u01478\u0001\u0000\u0000\u0000\u0148" +
                    "\u014a\u0003;\u001b\u0000\u0149\u0148\u0001\u0000\u0000\u0000\u014a\u014b" +
                    "\u0001\u0000\u0000\u0000\u014b\u0149\u0001\u0000\u0000\u0000\u014b\u014c" +
                    "\u0001\u0000\u0000\u0000\u014c\u014e\u0001\u0000\u0000\u0000\u014d\u014f" +
                    "\u0005 \u0000\u0000\u014e\u014d\u0001\u0000\u0000\u0000\u014e\u014f\u0001" +
                    "\u0000\u0000\u0000\u014f:\u0001\u0000\u0000\u0000\u0150\u0152\u0007\t" +
                    "\u0000\u0000\u0151\u0150\u0001\u0000\u0000\u0000\u0152<\u0001\u0000\u0000" +
                    "\u0000\u0153\u0155\u0005#\u0000\u0000\u0154\u0156\u0007\u0004\u0000\u0000" +
                    "\u0155\u0154\u0001\u0000\u0000\u0000\u0156\u0157\u0001\u0000\u0000\u0000" +
                    "\u0157\u0155\u0001\u0000\u0000\u0000\u0157\u0158\u0001\u0000\u0000\u0000" +
                    "\u0158>\u0001\u0000\u0000\u0000\u0159\u015b\u0007\u0005\u0000\u0000\u015a" +
                    "\u0159\u0001\u0000\u0000\u0000\u015b\u015c\u0001\u0000\u0000\u0000\u015c" +
                    "\u015a\u0001\u0000\u0000\u0000\u015c\u015d\u0001\u0000\u0000\u0000\u015d" +
                    "\u015f\u0001\u0000\u0000\u0000\u015e\u0160\u0005%\u0000\u0000\u015f\u015e" +
                    "\u0001\u0000\u0000\u0000\u015f\u0160\u0001\u0000\u0000\u0000\u0160@\u0001" +
                    "\u0000\u0000\u0000\u0161\u0165\u0005\"\u0000\u0000\u0162\u0164\b\n\u0000" +
                    "\u0000\u0163\u0162\u0001\u0000\u0000\u0000\u0164\u0167\u0001\u0000\u0000" +
                    "\u0000\u0165\u0163\u0001\u0000\u0000\u0000\u0165\u0166\u0001\u0000\u0000" +
                    "\u0000\u0166\u0168\u0001\u0000\u0000\u0000\u0167\u0165\u0001\u0000\u0000" +
                    "\u0000\u0168\u0169\u0005\"\u0000\u0000\u0169B\u0001\u0000\u0000\u0000" +
                    "\u016a\u016e\u0005\'\u0000\u0000\u016b\u016d\b\u000b\u0000\u0000\u016c" +
                    "\u016b\u0001\u0000\u0000\u0000\u016d\u0170\u0001\u0000\u0000\u0000\u016e" +
                    "\u016c\u0001\u0000\u0000\u0000\u016e\u016f\u0001\u0000\u0000\u0000\u016f" +
                    "\u0171\u0001\u0000\u0000\u0000\u0170\u016e\u0001\u0000\u0000\u0000\u0171" +
                    "\u0172\u0005\'\u0000\u0000\u0172D\u0001\u0000\u0000\u0000\"\u0000\u0001" +
                    "\u0002\u0003\u0004M[j|\u0089\u0094\u009f\u00a4\u00a8\u00ab\u00ad\u00b6" +
                    "\u00cd\u00e0\u00fa\u00fd\u0102\u0117\u012b\u013a\u0146\u014b\u014e\u0151" +
                    "\u0157\u015c\u015f\u0165\u016e\t\u0005\u0001\u0000\u0004\u0000\u0000\u0005" +
                    "\u0004\u0000\u0000\u0001\u0000\u0001\u0010\u0000\u0006\u0000\u0000\u0001" +
                    "\u0015\u0001\u0001\u0016\u0002\u0001\u0017\u0003";
    public static final ATN _ATN =
            new ATNDeserializer().deserialize(_serializedATN.toCharArray());
    protected static final DFA[] _decisionToDFA;
    protected static final PredictionContextCache _sharedContextCache =
            new PredictionContextCache();
    private static final String[] _LITERAL_NAMES = makeLiteralNames();
    private static final String[] _SYMBOLIC_NAMES = makeSymbolicNames();
    public static final Vocabulary VOCABULARY = new VocabularyImpl(_LITERAL_NAMES, _SYMBOLIC_NAMES);
    public static String[] channelNames = {
            "DEFAULT_TOKEN_CHANNEL", "HIDDEN"
    };
    public static String[] modeNames = {
            "DEFAULT_MODE", "TAG", "SCRIPT", "STYLE", "ATTVALUE"
    };

    static {
        RuntimeMetaData.checkVersion("4.13.1", RuntimeMetaData.VERSION);
    }

    static {
        tokenNames = new String[_SYMBOLIC_NAMES.length];
        for (int i = 0; i < tokenNames.length; i++) {
            tokenNames[i] = VOCABULARY.getLiteralName(i);
            if (tokenNames[i] == null) {
                tokenNames[i] = VOCABULARY.getSymbolicName(i);
            }

            if (tokenNames[i] == null) {
                tokenNames[i] = "<INVALID>";
            }
        }
    }

    static {
        _decisionToDFA = new DFA[_ATN.getNumberOfDecisions()];
        for (int i = 0; i < _ATN.getNumberOfDecisions(); i++) {
            _decisionToDFA[i] = new DFA(_ATN.getDecisionState(i), i);
        }
    }

    private LinkedList<Token> queue = new LinkedList<>();

    public HTMLLexer(CharStream input) {
        super(input);
        _interp = new LexerATNSimulator(this, _ATN, _decisionToDFA, _sharedContextCache);
    }

    private static String[] makeRuleNames() {
        return new String[]{
                "HTML_COMMENT", "HTML_CONDITIONAL_COMMENT", "XML", "CDATA", "DTD", "SCRIPTLET",
                "SEA_WS", "TAG_OPEN", "HTML_TEXT", "TAG_CLOSE", "TAG_SLASH_CLOSE", "TAG_SLASH",
                "EMPTYTEXR", "TAG_EQUALS", "TAG_NAME", "TAG_WHITESPACE", "SCRIPT_BODY",
                "HEXDIGIT", "DIGIT", "TAG_NameChar", "TAG_NameStartChar", "SCRIPT_SHORT_BODY",
                "STYLE_BODY", "STYLE_SHORT_BODY", "ATTVALUE_VALUE", "ATTRIBUTE", "ATTCHARS",
                "ATTCHAR", "HEXCHARS", "DECCHARS", "DOUBLE_QUOTE_STRING", "SINGLE_QUOTE_STRING"
        };
    }

    private static String[] makeLiteralNames() {
        return new String[]{
                null, null, null, null, null, null, null, null, null, null, "'<'", null,
                "'>'", "'/>'", "'/'", null, "'='"
        };
    }

    private static String[] makeSymbolicNames() {
        return new String[]{
                null, "SCRIPT_END", "STYLE_END", "HTML_COMMENT", "HTML_CONDITIONAL_COMMENT",
                "XML", "CDATA", "DTD", "SCRIPTLET", "SEA_WS", "TAG_OPEN", "HTML_TEXT",
                "TAG_CLOSE", "TAG_SLASH_CLOSE", "TAG_SLASH", "EMPTYTEXR", "TAG_EQUALS",
                "TAG_NAME", "TAG_WHITESPACE", "SCRIPT_BODY", "SCRIPT_SHORT_BODY", "STYLE_BODY",
                "STYLE_SHORT_BODY", "ATTVALUE_VALUE", "ATTRIBUTE"
        };
    }

    @Override
    @Deprecated
    public String[] getTokenNames() {
        return tokenNames;
    }

    @Override

    public Vocabulary getVocabulary() {
        return VOCABULARY;
    }

    @Override
    public Token nextToken() {
        if (!queue.isEmpty()) {
            return queue.poll();
        }
        Token next = super.nextToken();
        return next;
    }

    private CommonToken createToken(int type, String text) {
        int stop = getCharIndex() - 1;
        int start = text.length() == 0 ? stop : stop - text.length() + 1;
        return new CommonToken(type, text);
    }

    @Override
    public String getGrammarFileName() {
        return "HTMLLexer.g4";
    }

    @Override
    public String[] getRuleNames() {
        return ruleNames;
    }

    @Override
    public String getSerializedATN() {
        return _serializedATN;
    }

    @Override
    public String[] getChannelNames() {
        return channelNames;
    }

    @Override
    public String[] getModeNames() {
        return modeNames;
    }

    @Override
    public ATN getATN() {
        return _ATN;
    }

    @Override
    public void action(RuleContext _localctx, int ruleIndex, int actionIndex) {
        switch (ruleIndex) {
            case 16:
                SCRIPT_BODY_action((RuleContext) _localctx, actionIndex);
                break;
            case 21:
                SCRIPT_SHORT_BODY_action((RuleContext) _localctx, actionIndex);
                break;
            case 22:
                STYLE_BODY_action((RuleContext) _localctx, actionIndex);
                break;
            case 23:
                STYLE_SHORT_BODY_action((RuleContext) _localctx, actionIndex);
                break;
        }
    }

    private void SCRIPT_BODY_action(RuleContext _localctx, int actionIndex) {
        switch (actionIndex) {
            case 0:

                var start = this._tokenStartCharIndex;
                var end = this.getCharIndex();
                var len = end - start;
                var text = this.getText().substring(this.getText().length() - len);
                text = text.substring(0, text.length() - "</script>".length());
                var str = CharStreams.fromString(text);
                var lexer = new JavaScriptLexer(str);
                for (int i = 0; ; ++i) {
                    var ro_token = lexer.nextToken();
                    var token = (CommonToken) ro_token;
                    token.setTokenIndex(i);
                    if (token.getType() == Token.EOF) {
                        token = this.createToken(SCRIPT_END, "</script>");
                        queue.addLast(token);
                        break;
                    }
                    token.setChannel(2);
                    queue.addLast(token);
                }

                break;
        }
    }

    private void SCRIPT_SHORT_BODY_action(RuleContext _localctx, int actionIndex) {
        switch (actionIndex) {
            case 1:

                var start = this._tokenStartCharIndex;
                var end = this.getCharIndex();
                var len = end - start;
                var text = this.getText().substring(this.getText().length() - len);
                text = text.substring(0, text.length() - "</>".length());
                var str = CharStreams.fromString(text);
                var lexer = new JavaScriptLexer(str);
                for (int i = 0; ; ++i) {
                    var ro_token = lexer.nextToken();
                    var token = (CommonToken) ro_token;
                    token.setTokenIndex(i);
                    if (token.getType() == Token.EOF) {
                        token = this.createToken(SCRIPT_END, "</>");
                        queue.addLast(token);
                        break;
                    }
                    token.setChannel(2);
                    queue.addLast(token);
                }

                break;
        }
    }

    private void STYLE_BODY_action(RuleContext _localctx, int actionIndex) {
        switch (actionIndex) {
            case 2:

                var start = this._tokenStartCharIndex;
                var end = this.getCharIndex();
                var len = end - start;
                var text = this.getText().substring(this.getText().length() - len);
                text = text.substring(0, text.length() - "</style>".length());
                var str = CharStreams.fromString(text);
                var lexer = new JavaScriptLexer(str);
                for (int i = 0; ; ++i) {
                    var ro_token = lexer.nextToken();
                    var token = (CommonToken) ro_token;
                    token.setTokenIndex(i);
                    if (token.getType() == Token.EOF) {
                        token = this.createToken(STYLE_END, "</style>");
                        queue.addLast(token);
                        break;
                    }
                    token.setChannel(3);
                    queue.addLast(token);
                }

                break;
        }
    }

    private void STYLE_SHORT_BODY_action(RuleContext _localctx, int actionIndex) {
        switch (actionIndex) {
            case 3:

                var start = this._tokenStartCharIndex;
                var end = this.getCharIndex();
                var len = end - start;
                var text = this.getText().substring(this.getText().length() - len);
                text = text.substring(0, text.length() - "</>".length());
                var str = CharStreams.fromString(text);
                var lexer = new JavaScriptLexer(str);
                for (int i = 0; ; ++i) {
                    var ro_token = lexer.nextToken();
                    var token = (CommonToken) ro_token;
                    token.setTokenIndex(i);
                    if (token.getType() == Token.EOF) {
                        token = this.createToken(STYLE_END, "</>");
                        queue.addLast(token);
                        break;
                    }
                    token.setChannel(3);
                    queue.addLast(token);
                }

                break;
        }
    }
}