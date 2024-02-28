package io.github.rosemoe.sora.widget;

import Ninja.coder.Ghostemane.code.ColorAndroid12;
import com.google.android.material.color.MaterialColors;
import android.text.Spannable;
import android.app.*;
import android.content.*;
import android.content.res.*;
import android.graphics.*;
import android.graphics.drawable.*;
import android.os.*;
import android.view.animation.AnimationUtils;
import android.widget.*;
import android.text.TextUtils;
import android.text.style.ForegroundColorSpan;
import android.util.TypedValue;
import android.view.*;
import android.view.View.*;
import com.google.android.material.shape.CornerFamily;
import com.google.android.material.shape.MaterialShapeDrawable;
import com.google.android.material.shape.ShapeAppearanceModel;
import com.google.android.material.textview.MaterialTextView;
import io.github.douglasjunior.androidSimpleTooltip.SimpleTooltip;
import io.github.rosemoe.sora.widget.TextSummry.HTMLConstants;
import Ninja.coder.Ghostemane.code.R;
import io.github.rosemoe.sora.data.CompletionItem;
import java.util.*;

@SuppressWarnings("CanBeFinal")
public class CustomAdGhostWeb extends EditorCompletionAdapter {
  private io.github.rosemoe.sora.widget.TextSummry.TextUtils textUtils;
  protected HTMLConstants htmlconfig;

  public CustomAdGhostWeb() {
    htmlconfig = new HTMLConstants();
  }

  @Override
  public int getItemHeight() {
    // 45 dp

    return (int)
        TypedValue.applyDimension(
            TypedValue.COMPLEX_UNIT_DIP, 53, getContext().getResources().getDisplayMetrics());
  }

  @Override
  public View getView(int pos, View view, ViewGroup parent, boolean isCurrentCursorPosition) {
    if (view == null) {
      view = LayoutInflater.from(getContext()).inflate(R.layout.one_ui, parent, false);
    }
    CompletionItem item = getItem(pos);
    MaterialTextView tv = view.findViewById(R.id.res);
    MaterialTextView tv1 = view.findViewById(R.id.tv1);
    MaterialTextView data = view.findViewById(R.id.data);
    LinearLayout display = view.findViewById(R.id.display);
    MaterialTextView iogore = view.findViewById(R.id.iogore);
    LinearLayout mob = view.findViewById(R.id.mob);
    LinearLayout helpers = view.findViewById(R.id.helpers);
    Spannable label = Spannable.Factory.getInstance().newSpannable(item.label != null ? item.label : "None");
    String prefix = getPrefix();
    textUtils = new io.github.rosemoe.sora.widget.TextSummry.TextUtils(tv);
    int index = TextUtils.indexOf(item.label, prefix);
    if (index != -1) {
      label.setSpan(
          new ForegroundColorSpan(getThemeColor(EditorColorScheme.KEYWORD)),
          index,
          index + prefix.length(),
          Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
    }
    tv.setText(label);
    tv1.setTextColor(getThemeColor(EditorColorScheme.AUTO_COMP_PANEL_BG));
    data.setTextColor(getThemeColor(EditorColorScheme.ATTRIBUTE_VALUE));
    tv.setTextColor(getThemeColor(EditorColorScheme.BLOCK_LINE_CURRENT));
    iogore.setTextColor(getThemeColor(EditorColorScheme.NON_PRINTABLE_CHAR));
    display.setBackground(shapeDrawable(getThemeColor(EditorColorScheme.AUTO_COMP_PANEL_CORNER)));
    var anim = AnimationUtils.loadAnimation(view.getContext(), android.R.anim.slide_out_right);
    anim.setDuration(200);
    view.setAnimation(anim);
    tv.setText(label);
    tv.setSingleLine(true);
    iogore.setText(item.desc);
    tv1.setText(item.desc.subSequence(0, 1));
    iogore.setEllipsize(TextUtils.TruncateAt.END);
    iogore.setMarqueeRepeatLimit(-1);
    iogore.setSingleLine(true);
    String lastItemPost = tv1.getText().toString();
    String lastItemPost2 = tv.getText().toString();
    //  helpers.setBackgroundColor(Color.TRANSPARENT);
    var getDesc = item.desc;
    if (getDesc.contains(htmlconfig.HTMLTAG)) {
      textUtils.setKeyWordHtmlNotWork();
      iogore.setText(HtmlHelper.code(label.toString()));
      
      // helpers.setBackgroundColor(Color.TRANSPARENT);
    } else if (getDesc.contains(htmlconfig.Attribute)) {
      helpers.setBackgroundColor(Color.TRANSPARENT);
    } else if (getDesc.contains(htmlconfig.CssKey)) {
      // iogore.setText(HtmlHelper.getCssKeyWord(item.label));
    } else if (getDesc.contains(htmlconfig.CssAttractions)) {
    } else if (getDesc.contains(htmlconfig.CssColor)) {
      helpers.setBackgroundColor(HtmlHelper.cssColor(label.toString()));
    } else if (getDesc.contains(htmlconfig.TagOne)) {
    } else if (getDesc.contains(htmlconfig.JsKey)) {
    } else if (getDesc.contains(htmlconfig.EmmetTagFor) && getDesc.contains(htmlconfig.Emmet)) {
    } else if (getDesc.contains(htmlconfig.PhpKeys)) {
      iogore.setText(HtmlHelper.getHelpPhp(label.toString()));
      helpers.setBackgroundColor(Color.TRANSPARENT);
    }
    tv.setOnLongClickListener(v ->{
      showToolTip(v.getContext(),label.toString(),tv);
        return false;
    });

    view.setTag(pos);
    return view;
  }

  protected void SetBackgroundItem(int colors, View view) {
    MaterialShapeDrawable pos =
        new MaterialShapeDrawable(
            ShapeAppearanceModel.builder().setAllCorners(CornerFamily.ROUNDED, 9f).build());
    pos.setFillColor(ColorStateList.valueOf(colors));

    view.setBackground(pos);
  }

  protected MaterialShapeDrawable shapeDrawable(int colors) {
    MaterialShapeDrawable pos =
        new MaterialShapeDrawable(
            ShapeAppearanceModel.builder().setAllCorners(CornerFamily.ROUNDED, 31f).build());
    pos.setFillColor(ColorStateList.valueOf(colors));
    return pos;
  }

  public int getRandom() {
    Random r = new Random();
    return Color.rgb(r.nextInt(255), r.nextInt(255), r.nextInt(255));
  }
  public void showToolTip(Context ctx,String tv,View v) {
    new SimpleTooltip.Builder(ctx)
        .anchorView(v)
        .backgroundColor(MaterialColors.getColor(ctx, ColorAndroid12.Back, 0))
        .textColor(MaterialColors.getColor(ctx, ColorAndroid12.TvColor, 0))
        .arrowColor(MaterialColors.getColor(ctx, ColorAndroid12.Back, 0))
        .text(tv)
        .gravity(Gravity.END)
        .modal(true)
        .animated(true)
        .onDismissListener(
            new SimpleTooltip.OnDismissListener() {

              @Override
              public void onDismiss(SimpleTooltip arg0) {
                // formatCodeAsync();
              }
            })
        .animationDuration(2000)
        .build()
        .show();
  }
}
