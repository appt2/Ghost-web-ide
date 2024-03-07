package io.github.rosemoe.sora.widget;

import Ninja.coder.Ghostemane.code.AnimUtils;
import android.text.Spannable;
import android.app.*;
import android.content.*;
import android.content.res.*;
import android.graphics.*;
import android.graphics.drawable.*;
import android.os.*;
import android.widget.*;
import android.text.TextUtils;
import android.text.style.ForegroundColorSpan;
import android.util.TypedValue;
import android.view.*;
import android.view.View.*;
import io.github.rosemoe.sora.widget.TextSummry.HTMLConstants;
import Ninja.coder.Ghostemane.code.R;
import io.github.rosemoe.sora.data.CompletionItem;
import java.util.*;

@SuppressWarnings("CanBeFinal")
public class CustomAdGhostWeb extends EditorCompletionAdapter {
  private io.github.rosemoe.sora.widget.TextSummry.TextUtils textUtils;
  protected HTMLConstants htmlconfig;
  protected TextView 
      completion_label,
      completion_detail,
      completion_api_info,
      completion_iconText;

  public CustomAdGhostWeb() {
    htmlconfig = new HTMLConstants();
  }

  @Override
  public int getItemHeight() {
    // 45 dp

    return (int)
        TypedValue.applyDimension(
            TypedValue.COMPLEX_UNIT_DIP, 55, getContext().getResources().getDisplayMetrics());
  }

  @Override
  public View getView(int pos, View view, ViewGroup parent, boolean isCurrentCursorPosition) {
    if (view == null) {
      view = LayoutInflater.from(getContext()).inflate(R.layout.one_ui, parent, false);
    }
    CompletionItem item = getItem(pos);
    
    completion_label = view.findViewById(R.id.completion_label);
    completion_detail = view.findViewById(R.id.completion_detail);
    completion_api_info = view.findViewById(R.id.completion_api_info);
    completion_iconText = view.findViewById(R.id.completion_iconText);
    Spannable label =
        Spannable.Factory.getInstance().newSpannable(item.label != null ? item.label : "None");
    String prefix = getPrefix();
    int index = TextUtils.indexOf(item.label, prefix);
    if (index != -1) {
      label.setSpan(
          new ForegroundColorSpan(getThemeColor(EditorColorScheme.KEYWORD)),
          index,
          index + prefix.length(),
          Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
    }
    completion_label.setText(label);
    completion_detail.setText(item.commit);
    completion_api_info.setText(item.desc);
    completion_iconText.setText(item.label.substring(0, 1));
    
    completion_label.setTextColor(getThemeColor(EditorColorScheme.ATTRIBUTE_VALUE));
    completion_detail.setTextColor(getThemeColor(EditorColorScheme.ATTRIBUTE_VALUE));
    completion_api_info.setTextColor(getThemeColor(EditorColorScheme.ATTRIBUTE_VALUE));
    completion_iconText.setTextColor(getThemeColor(EditorColorScheme.ATTRIBUTE_VALUE));
    AnimUtils.Sacla(view);
    if(item.desc.equals(htmlconfig.HTMLTAG)) {
    	completion_detail.setText(HtmlHelper.code(label.toString()));
    }else if(item.desc.equals(htmlconfig.CssColor)) {
    	completion_detail.setTextColor(HtmlHelper.cssColor(label.toString()));
    }else{
      completion_detail.setText(item.desc);
    }

    view.setTag(pos);
    return view;
  }
}
