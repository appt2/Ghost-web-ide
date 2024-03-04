package Ninja.coder.Ghostemane.code.model;

import Ninja.coder.Ghostemane.code.adapter.IconShopAd;
import android.animation.AnimatorSet;
import android.animation.ObjectAnimator;
import android.content.Context;
import android.view.MotionEvent;
import android.view.ScaleGestureDetector;
import android.view.View;
import android.view.ViewGroup;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import com.google.android.material.bottomsheet.BottomSheetDialog;
import java.io.File;
import java.util.Arrays;
import java.util.List;

public class IconShop implements IconShopAd.IconShopCallBack  {
  protected Context context;
  protected String path = "/storage/emulated/0/GhostWebIDE/.icon";
  protected RecyclerView re;
  protected IconShopAd ads;
  private GridLayoutManager mGridLayoutManager1, mGridLayoutManager2, mGridLayoutManager3;
  private ScaleGestureDetector mScaleGestureDetector;
  private RecyclerView.LayoutManager mCurrentLayoutManager;
  
  protected File[] hsi;
  protected List<File> listCast;
  protected File currentFolder;

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
    currentFolder = new File(path);
    hsi = currentFolder.listFiles();
    listCast = Arrays.asList(hsi);
    var sheet = new BottomSheetDialog(context);
    ads = new IconShopAd(listCast, this,context);
    if (ads != null) {
      re.setAdapter(ads);
    }
    mGridLayoutManager1 = new GridLayoutManager(context, 6);
    mGridLayoutManager2 = new GridLayoutManager(context, 8);
    mGridLayoutManager3 = new GridLayoutManager(context, 12);

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

  private void listFile() {}

  @Override
  public void OnItemClicket(int pos, View view) {
    
  }

  @Override
  public void OnItemLongClicket(int pos, View view) {}

  


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
