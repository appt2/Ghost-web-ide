package Ninja.coder.Ghostemane.code.keyboard;

import Ninja.coder.Ghostemane.code.R;
import android.content.Context;
import android.content.res.TypedArray;
import android.util.AttributeSet;
import android.view.View;
import android.widget.SeekBar;
import android.widget.TextView;

import androidx.preference.DialogPreference;
import androidx.preference.PreferenceViewHolder;
import com.google.android.material.dialog.MaterialAlertDialogBuilder;
import java.util.Locale;

public class SeekBarPreference extends DialogPreference {

  private TextView mMinText;
  private TextView mMaxText;
  private TextView mValText;
  private SeekBar mSeek;
  private float mMin;
  private float mMax;
  private float mVal;
  private float mPrevVal;
  private float mStep;
  private boolean mAsPercent;
  private boolean mLogScale;
  private String mDisplayFormat;

  public SeekBarPreference(Context context, AttributeSet attrs) {
    super(context, attrs);
    init(context, attrs);
  }

  protected void init(Context context, AttributeSet attrs) {
    setDialogLayoutResource(R.layout.seek_bar_dialog);

    TypedArray a = context.obtainStyledAttributes(attrs, R.styleable.SeekBarPreference);
    mMin = a.getFloat(R.styleable.SeekBarPreference_minValue, 0.0f);
    mMax = a.getFloat(R.styleable.SeekBarPreference_maxValue, 100.0f);
    mStep = a.getFloat(R.styleable.SeekBarPreference_step, 0.0f);
    mAsPercent = a.getBoolean(R.styleable.SeekBarPreference_asPercent, false);
    mLogScale = a.getBoolean(R.styleable.SeekBarPreference_logScale, false);
    mDisplayFormat = a.getString(R.styleable.SeekBarPreference_displayFormat);
    a.recycle();
    
  }

  @Override
  protected Float onGetDefaultValue(TypedArray a, int index) {
    return a.getFloat(index, 0.0f);
  }

  @Override
  protected void onSetInitialValue(Object defaultValue) {
    setVal(getPersistedFloat(0.0f));
    savePrevVal();
  }

  private String formatFloatDisplay(Float val) {
    if (mAsPercent) {
      return String.format("%d%%", (int) (val * 100));
    }

    if (mDisplayFormat != null) {
      return String.format(mDisplayFormat, val);
    } else {
      return Float.toString(val);
    }
  }

  private void showVal() {
    mValText.setText(formatFloatDisplay(mVal));
  }

  protected void setVal(Float val) {
    mVal = val;
  }

  protected void savePrevVal() {
    mPrevVal = mVal;
  }

  protected void restoreVal() {
    mVal = mPrevVal;
  }

  protected String getValString() {
    return Float.toString(mVal);
  }

  private float percentToSteppedVal(
      int percent, float min, float max, float step, boolean logScale) {
    float val;
    if (logScale) {
      val =
          (float)
              Math.exp(
                  percentToSteppedVal(
                      percent, (float) Math.log(min), (float) Math.log(max), step, false));
    } else {
      float delta = percent * (max - min) / 100;
      if (step != 0.0f) {
        delta = Math.round(delta / step) * step;
      }
      val = min + delta;
    }
    val = Float.valueOf(String.format(Locale.US, "%.2g", val));
    return val;
  }

  private int getPercent(float val, float min, float max) {
    return (int) (100 * (val - min) / (max - min));
  }

  private int getProgressVal() {
    if (mLogScale) {
      return getPercent((float) Math.log(mVal), (float) Math.log(mMin), (float) Math.log(mMax));
    } else {
      return getPercent(mVal, mMin, mMax);
    }
  }

  @Override
  public void onBindViewHolder(PreferenceViewHolder holder) {
    super.onBindViewHolder(holder);
    
    mSeek = (SeekBar) holder.findViewById(R.id.seekBarPref);
    mMinText = (TextView) holder.findViewById(R.id.seekMin);
    mMaxText = (TextView) holder.findViewById(R.id.seekMax);
    mValText = (TextView) holder.findViewById(R.id.seekVal);

    showVal();
    mMinText.setText(formatFloatDisplay(mMin));
    mMaxText.setText(formatFloatDisplay(mMax));
    mSeek.setProgress(getProgressVal());

    mSeek.setOnSeekBarChangeListener(
        new SeekBar.OnSeekBarChangeListener() {
          public void onStopTrackingTouch(SeekBar seekBar) {}

          public void onStartTrackingTouch(SeekBar seekBar) {}

          public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
            if (fromUser) {
              float newVal = percentToSteppedVal(progress, mMin, mMax, mStep, mLogScale);
              if (newVal != mVal) {
                onChange(newVal);
              }
              setVal(newVal);
              mSeek.setProgress(getProgressVal());
            }
            showVal();
          }
        });
  }

  public void onChange(float val) {}

  @Override
  public CharSequence getSummary() {
    return formatFloatDisplay(mVal);
  }

  
  protected void onDialogClosed(boolean positiveResult) {
    if (!positiveResult) {
      restoreVal();
      return;
    }
    if (shouldPersist()) {
      persistFloat(mVal);
      savePrevVal();
    }
    notifyChanged();
  }
}
