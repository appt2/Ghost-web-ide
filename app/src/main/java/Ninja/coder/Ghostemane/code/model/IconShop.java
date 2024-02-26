package Ninja.coder.Ghostemane.code.model;

import Ninja.coder.Ghostemane.code.ColorAndroid12;
import Ninja.coder.Ghostemane.code.FileUtil;
import Ninja.coder.Ghostemane.code.adapter.IconShopAd;
import Ninja.coder.Ghostemane.code.vb;
import android.animation.AnimatorSet;
import android.animation.ObjectAnimator;
import android.app.Activity;
import android.content.Context;
import android.view.MotionEvent;
import android.view.ScaleGestureDetector;
import android.view.View;
import android.view.ViewGroup;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import com.google.android.material.bottomsheet.BottomSheetDialog;
import com.google.android.material.color.MaterialColors;
import com.skydoves.powermenu.PowerMenu;
import com.skydoves.powermenu.PowerMenuItem;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class IconShop implements IconShopAd.IconShopCallBack {
  protected Context context;
  List<HashMap<String, String>> file = new ArrayList<>();
  List<String> listdir = new ArrayList<>();
  protected String path = "/storage/emulated/0/GhostWebIDE/.icon";
  protected RecyclerView re;
  protected IconShopAd ads;
  private GridLayoutManager mGridLayoutManager1, mGridLayoutManager2, mGridLayoutManager3;
  private ScaleGestureDetector mScaleGestureDetector;
  private RecyclerView.LayoutManager mCurrentLayoutManager;

  public IconShop(Context context) {
    this.context = context;

    listFile();
    initList();
  }

  private void initList() {
    re = new RecyclerView(context);
    ViewGroup.LayoutParams param =
        new ViewGroup.LayoutParams(
            ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT);
    re.setLayoutParams(param);

    // Use a custom ItemAnimator
    re.setItemAnimator(
        new DefaultItemAnimator() {

          @Override
          public boolean animateChange(
              RecyclerView.ViewHolder oldHolder,
              RecyclerView.ViewHolder newHolder,
              int fromX,
              int fromY,
              int toX,
              int toY) {
            if (oldHolder == newHolder) {
              // Don't know how to run change animations when both holders are the same.
              // Run a change animation only on the newHolder.
              oldHolder.itemView.setAlpha(0f);
              oldHolder.itemView.setScaleX(0f);
              oldHolder.itemView.setScaleY(0f);

              AnimatorSet animatorSet = new AnimatorSet();
              animatorSet.playTogether(
                  ObjectAnimator.ofFloat(oldHolder.itemView, "alpha", 0f, 1f),
                  ObjectAnimator.ofFloat(oldHolder.itemView, "scaleX", 0f, 1f),
                  ObjectAnimator.ofFloat(oldHolder.itemView, "scaleY", 0f, 1f));
              animatorSet.setDuration(200);
              animatorSet.start();

              return false;
            }

            return super.animateChange(oldHolder, newHolder, fromX, fromY, toX, toY);
          }
        });
    var sheet = new BottomSheetDialog(context);
    ads = new IconShopAd(file, this);
    if (ads != null) {
      re.setAdapter(ads);
    }
    mGridLayoutManager1 = new GridLayoutManager(context, 3);
    mGridLayoutManager2 = new GridLayoutManager(context, 6);
    mGridLayoutManager3 = new GridLayoutManager(context, 8);

    mCurrentLayoutManager = mGridLayoutManager1;
    re.setLayoutManager(mGridLayoutManager1);
    sheet.setContentView(re);
    sheet.show();

    mScaleGestureDetector =
        new ScaleGestureDetector(
            context,
            new ScaleGestureDetector.SimpleOnScaleGestureListener() {
              @Override
              public boolean onScale(ScaleGestureDetector detector) {
                if (detector.getCurrentSpan() > 200 && detector.getTimeDelta() > 200) {
                  if (detector.getCurrentSpan() - detector.getPreviousSpan() < -1) {
                    if (mCurrentLayoutManager == mGridLayoutManager1) {
                      mCurrentLayoutManager = mGridLayoutManager2;
                      re.setLayoutManager(mGridLayoutManager2);
                      return true;
                    } else if (mCurrentLayoutManager == mGridLayoutManager2) {
                      mCurrentLayoutManager = mGridLayoutManager3;
                      re.setLayoutManager(mGridLayoutManager3);
                      return true;
                    }
                  } else if (detector.getCurrentSpan() - detector.getPreviousSpan() > 1) {
                    if (mCurrentLayoutManager == mGridLayoutManager3) {
                      mCurrentLayoutManager = mGridLayoutManager2;
                      re.setLayoutManager(mGridLayoutManager2);
                      return true;
                    } else if (mCurrentLayoutManager == mGridLayoutManager2) {
                      mCurrentLayoutManager = mGridLayoutManager1;
                      re.setLayoutManager(mGridLayoutManager1);
                      return true;
                    }
                  }
                }
                return false;
              }
            });

    // set touch listener on recycler view
    re.setOnTouchListener(
        new View.OnTouchListener() {
          @Override
          public boolean onTouch(View v, MotionEvent event) {
            mScaleGestureDetector.onTouchEvent(event);
            return false;
          }
        });
  }

  private void listFile() {
    file.clear();
    listdir.clear();
    FileUtil.listDir(path, listdir);
    var p = 0;
    for (int i = 0; i < listdir.size(); ++i) {
      {
        HashMap<String, String> mapz = new HashMap<>();
        mapz.put("path", listdir.get(i));
        file.add(mapz);
      }
      p++;
    }
  }

  @Override
  public void OnItemClicket(int pos, View view) {
    getPowerMenu(pos, view);
  }

  @Override
  public void OnItemLongClicket(int pos, View view) {}

  public void getPowerMenu(int itempos, View view) {
    var menu =
        new PowerMenu.Builder(context)
            .addItem(new PowerMenuItem("Case to Vector"))
            .addItem(new PowerMenuItem("Add svg to project"))
            .addItem(new PowerMenuItem("Case to png"))
            .setIsMaterial(true)
            .build();
    menu.setMenuColor(MaterialColors.getColor(context, ColorAndroid12.Back, 0));
    menu.setMenuRadius(20f);
    menu.setTextColor(MaterialColors.getColor(context, ColorAndroid12.colorOnSurface, 0));
    menu.setShowBackground(false);
    menu.setAutoDismiss(true);
    menu.setOnMenuItemClickListener(
        (pos, __) -> {
          switch (pos) {
            case 0 -> {
              var output = "/sdcard/ghostweb/icon/vector/";
              FileUtil.makeDir(output);
              vb.iconPath = file.get(itempos).get("path").toString();
              vb.projectResourceDirectory = output;
              vb.v((Activity) context, () -> {});
              break;
            }
            case 1 -> {
              break;
            }
            case 2 -> {
              break;
            }
          }
        });
    menu.showAsAnchorRightBottom(view);
  }

  public class AnimatedGridLayoutManager extends GridLayoutManager {

    public AnimatedGridLayoutManager(Context context, int spanCount) {
      super(context, spanCount);
    }

    @Override
    public void onMeasure(
        RecyclerView.Recycler recycler, RecyclerView.State state, int widthSpec, int heightSpec) {
      super.onMeasure(recycler, state, widthSpec, heightSpec);

      if (widthSpec == View.MeasureSpec.EXACTLY && heightSpec == View.MeasureSpec.EXACTLY) {
        requestLayout();
      }
    }
  }
}
