package Ninja.coder.Ghostemane.code.layoutmanager;

import android.content.Context;
import android.os.Build;
import Ninja.coder.Ghostemane.code.*;
import android.util.AttributeSet;
import android.widget.Toast;
import com.google.android.material.internal.NavigationMenuView;
import androidx.recyclerview.widget.LinearLayoutManager;
import com.google.android.material.navigation.NavigationView;
import com.google.android.material.shape.MaterialShapeDrawable;
import android.view.MenuItem;
import com.google.android.material.shape.CornerFamily;
import android.content.res.ColorStateList;
import com.google.android.material.color.MaterialColors;
import android.graphics.Color;
import com.google.android.material.shape.ShapeAppearanceModel;

public class NavigationViewCompnet extends NavigationView {
  protected int Cut = CornerFamily.CUT;

  public NavigationViewCompnet(Context context) {
    super(context);
  }

  public NavigationViewCompnet(Context context, AttributeSet set) {
    super(context, set);
    init();
  }

  public NavigationViewCompnet init() {

    setItemTextColor(ColorStateList.valueOf(MaterialColors.getColor(this, ColorAndroid12.TvColor)));
    setItemIconTintList(
        ColorStateList.valueOf(MaterialColors.getColor(this, ColorAndroid12.ColorFilter)));
    setItemMaxLines(1);
    setItemIconSize(50);
    setBackground(getBackground());

    setVerticalScrollBarEnabled(false);
    setHorizontalScrollBarEnabled(false);
    getHeaderView(0)
        .findViewById(R.id.mcardClick)
        .setOnClickListener(
            v -> {
              Toast.makeText(getContext(), R.string.app_name, 3).show();
            });
        disableNavScroll(this);
        

    return this;
  }

  public void setClick(OnClick clicks) {
    setNavigationItemSelectedListener(
        new OnNavigationItemSelectedListener() {
          @Override
          public boolean onNavigationItemSelected(MenuItem menuItem) {
            clicks.Click(menuItem);

            return false;
          }
        });
  }

  public interface OnClick {
    public boolean Click(MenuItem item);
  }

  public MaterialShapeDrawable getBackground() {
    MaterialShapeDrawable shapeDrawable =
        new MaterialShapeDrawable(
            ShapeAppearanceModel.builder()
                .setTopRightCorner(Cut, 13f)
                .setBottomRightCorner(Cut, 13f)
                .build());

    shapeDrawable.setFillColor(
        ColorStateList.valueOf(MaterialColors.getColor(getContext(), ColorAndroid12.Back, 0)));

    return shapeDrawable;
  }

  private void disableNavScroll(NavigationView navView) {
    NavigationMenuView navMenu = (NavigationMenuView) navView.getChildAt(0);
    navMenu.setLayoutManager(
        new LinearLayoutManager(getContext()) {
          @Override
          public boolean canScrollVertically() {
            return true;
          }
        });
        navMenu.setScrollBarSize(0);
        
  }
}
