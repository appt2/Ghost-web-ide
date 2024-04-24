package Ninja.coder.Ghostemane.code.navigator;

import Ninja.coder.Ghostemane.code.IdeEditor;
import Ninja.coder.Ghostemane.code.utils.ColorAndroid12;
import android.graphics.Color;
import com.allenliu.badgeview.BadgeView;
import io.github.rosemoe.sora.event.ContentChangeEvent;
import java.util.regex.Pattern;
import java.util.regex.Matcher;
import static androidx.core.graphics.ColorUtils.calculateLuminance;

public class EditorHelperColor {
  private IdeEditor editor;
  private BadgeView view;
  private static String currentWord = null;
  private static String currentWordPaser = null;
  private static int start = 0;
  private static int end = 0;

  public EditorHelperColor(IdeEditor editor, BadgeView view) {
    this.editor = editor;
    this.view = view;
    init();
  }

  public void init() {

    editor.subscribeEvent(
        ContentChangeEvent.class,
        (cc, dd) -> {
          try {
            String textSpan = editor.getText().toString();
            final int selection = editor.getCursor().getLeft();
            final Pattern pattern = Pattern.compile("(#?)(\\w+)");
            final Matcher matcher = pattern.matcher(textSpan);

            try {
              while (matcher.find()) {
                start = matcher.start();
                end = matcher.end();
                if (start <= selection && selection <= end) {
                  currentWordPaser = textSpan.substring(start, end).toString();
                  currentWord = currentWordPaser;
                }
              }
            } catch (Exception rr) {
              rr.printStackTrace();
            }
            if (!currentWord.isEmpty()) {
              if (currentWord.contains("#")) {
                try {
                  view.setBadgeBackground(Color.parseColor(currentWord));
                } catch (IllegalArgumentException iae) {
                  iae.printStackTrace();
                }
              } else {
                if (currentWord.toLowerCase().contains("0xff")) {
                  try {

                    currentWord = currentWord.replace("0xff", "#");
                    currentWord = currentWord.replace("0xFF", "#");
                    view.setBadgeBackground(Color.parseColor(currentWord));
                    if (calculateLuminance(Color.parseColor(currentWord)) < 0.5) {
                      view.setTextColor(Color.WHITE);

                    } else {
                      if (calculateLuminance(Color.parseColor(currentWord)) >= 0.5) {
                        view.setTextColor(Color.BLACK);
                      }
                    }
                  } catch (IllegalArgumentException iae) {

                  }
                } else {
                  editor.getSearcher().search(currentWord);
                }
              }
            }
          } catch (Exception e) {
            e.printStackTrace();
          }
        });
  }
}
