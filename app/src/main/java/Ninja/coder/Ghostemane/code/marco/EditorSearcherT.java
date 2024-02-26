package Ninja.coder.Ghostemane.code.marco;

import Ninja.coder.Ghostemane.code.IDEEDITOR;
import Ninja.coder.Ghostemane.code.R;
import android.graphics.PointF;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.PopupWindow;

public class EditorSearcherT {
  
  private static float[] lastEvent = null;
    private static float d = 0f;
    private static float newRot = 0f;
    private static boolean isZoomAndRotate;
    private static boolean isOutSide;
    private static final int NONE = 0;
    private static final int DRAG = 1;
    private static final int ZOOM = 2;
    private static int mode = NONE;
    private static PointF start = new PointF();
    private static PointF mid = new PointF();
    private static float oldDist = 1f;
    private static float xCoOrdinate, yCoOrdinate;

  public static void show(IDEEDITOR editor, View views) {

    var popupView =
        LayoutInflater.from(views.getContext()).inflate(R.layout.new_layoutsearch, null, false);
    PopupWindow popup =
        new PopupWindow(
            popupView,
            ViewGroup.LayoutParams.WRAP_CONTENT,
            ViewGroup.LayoutParams.WRAP_CONTENT,
            true);
    popup.showAtLocation(views, Gravity.CENTER, 0, 0);
//    popupView.setOnTouchListener(
//        new View.OnTouchListener() {
//          @Override
//          public boolean onTouch(View v, MotionEvent event) {
//            View view = (View) v;
//            view.bringToFront();
//            viewTransformation(view, event);
//            return true;
//          }
//        });
  }

  private static void viewTransformation(View view, MotionEvent event) {
    switch (event.getAction() & MotionEvent.ACTION_MASK) {
      case MotionEvent.ACTION_DOWN:
        xCoOrdinate = view.getX() - event.getRawX();
        yCoOrdinate = view.getY() - event.getRawY();

        start.set(event.getX(), event.getY());
        isOutSide = false;
        mode = DRAG;
        lastEvent = null;
        break;
      case MotionEvent.ACTION_POINTER_DOWN:
        break;
      case MotionEvent.ACTION_UP:
        isZoomAndRotate = false;
        if (mode == DRAG) {
          float x = event.getX();
          float y = event.getY();
        }
      case MotionEvent.ACTION_OUTSIDE:
        isOutSide = true;
        mode = NONE;
        lastEvent = null;
      case MotionEvent.ACTION_POINTER_UP:
        mode = NONE;
        lastEvent = null;
        break;
      case MotionEvent.ACTION_MOVE:
        if (!isOutSide) {
          if (mode == DRAG) {
            isZoomAndRotate = false;
            view.animate()
                .x(event.getRawX() + xCoOrdinate)
                .y(event.getRawY() + yCoOrdinate)
                .setDuration(1000)
                .start();
          }
        }
        break;
    }
  }

  private static float spacing(MotionEvent event) {
    float x = event.getX(0) - event.getX(1);
    float y = event.getY(0) - event.getY(1);
    return (int) Math.sqrt(x * x + y * y);
  }

  private static void midPoint(PointF point, MotionEvent event) {
    float x = event.getX(0) + event.getX(1);
    float y = event.getY(0) + event.getY(1);
    point.set(x / 2, y / 2);
  }
}
