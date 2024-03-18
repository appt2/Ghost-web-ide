package io.github.rosemoe.sora.widget.tooltip;

import android.graphics.RectF;
import android.widget.TextView;
import io.github.rosemoe.sora.event.EventReceiver;
import io.github.rosemoe.sora.event.ScrollEvent;
import io.github.rosemoe.sora.event.HandleStateChangeEvent;
import io.github.rosemoe.sora.event.SelectionChangeEvent;
import io.github.rosemoe.sora.event.Unsubscribe;
import io.github.rosemoe.sora.widget.CodeEditor;
import io.github.rosemoe.sora.widget.EditorPopupWindow;
import io.github.rosemoe.sora.widget.EditorTouchEventHandler;

public class ToolTipHelper extends EditorPopupWindow
        implements EventReceiver<SelectionChangeEvent> {
    private static final long DELAY = 200;
    private final EditorTouchEventHandler mHandler;
    protected CodeEditor editor;
    protected TextView view;
    protected CodeEditor mEditor;
    private long mLastScroll;
    private int mLastPosition;
    private boolean isShow = true;

    public ToolTipHelper(CodeEditor editor) {
        super(editor, FEATURE_SHOW_OUTSIDE_VIEW_ALLOWED);
        this.mEditor = editor;

        mHandler = editor.getEventHandler();
        view = new TextView(editor.getContext());
        view.setText("Hello");
        setContentView(view);
        editor.subscribeEvent(SelectionChangeEvent.class, this);
        editor.subscribeEvent(
                ScrollEvent.class,
                ((event, unsubscribe) -> {
                    var last = mLastScroll;
                    mLastScroll = System.currentTimeMillis();
                    if (mLastScroll - last < DELAY) {
                        postDisplay();
                    }
                }));
        editor.subscribeEvent(
                HandleStateChangeEvent.class,
                ((event, unsubscribe) -> {
                    if (event.isHeld()) {
                        postDisplay();
                    }
                }));
    }

    private void postDisplay() {
        if (!isShowing()) {
            return;
        }
        dismiss();
        if (!mEditor.getCursor().isSelected()) {
            return;
        }
        mEditor.postDelayed(
                new Runnable() {
                    @Override
                    public void run() {
                        if (!mHandler.hasAnyHeldHandle()
                                && System.currentTimeMillis() - mLastScroll > DELAY
                                && mEditor.getScroller().isFinished()) {
                            displayWindow();
                        } else {
                            mEditor.postDelayed(this, DELAY);
                        }
                    }
                },
                DELAY);
    }

    public void displayWindow() {
        int top;

        var cursor = mEditor.getCursor();
        if (cursor.isSelected()) {
            var leftRect = mEditor.getLeftHandleDescriptor().position;

            var rightRect = mEditor.getRightHandleDescriptor().position;
            int top1 = selectTop(leftRect);
            int top2 = selectTop(rightRect);
            top = Math.min(top1, top2);
        } else {
            top = selectTop(mEditor.getInsertHandleDescriptor().position);
        }
        top = Math.max(0, Math.min(top, mEditor.getHeight() - getHeight() - 5));
        float handleLeftX =
                mEditor.getOffset(mEditor.getCursor().getLeftLine(), mEditor.getCursor().getLeftColumn());

        float handleRightX =
                mEditor.getOffset(mEditor.getCursor().getRightLine(), mEditor.getCursor().getRightColumn());
        int panelX = (int) ((handleLeftX + handleRightX) / 2f);
        setLocationAbsolutely(panelX, top);
        show();
    }

    private int selectTop(RectF rect) {
        int rowHeight = mEditor.getRowHeight();
        if (rect.top - rowHeight * 3 / 2F > getHeight()) {
            return (int) (rect.top - rowHeight * 3 / 2 - getHeight());
        } else {
            return (int) (rect.bottom + rowHeight / 2);
        }
    }

    @Override
    public void onReceive(SelectionChangeEvent event, Unsubscribe unsubscribe) {
        if (mHandler.hasAnyHeldHandle()) {
            return;
        }
        if (event.isSelected()) {
            if (!isShowing()) {
                mEditor.post(this::displayWindow);
            }
            mLastPosition = -1;
        } else {
            if (event.getLeft().index == mLastPosition && !isShowing()) {
                mEditor.post(this::displayWindow);
            } else {
                dismiss();
            }
            mLastPosition = event.getLeft().index;
        }
    }

    public TextView getView() {
        return this.view;
    }

    public void setView(TextView view) {
        this.view = view;
    }
}
