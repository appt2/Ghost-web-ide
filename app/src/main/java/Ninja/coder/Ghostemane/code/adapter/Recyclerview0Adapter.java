package Ninja.coder.Ghostemane.code.adapter;

import Ninja.coder.Ghostemane.code.GhostWebMaterialDialog;
import Ninja.coder.Ghostemane.code.R;
import android.animation.ObjectAnimator;
import android.content.Context;
import android.view.MotionEvent;
import androidx.recyclerview.widget.RecyclerView;
import io.github.rosemoe.sora.widget.CodeEditor;
import java.util.ArrayList;
import java.util.HashMap;
import android.view.ViewGroup;
import android.view.View;
import android.view.LayoutInflater;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.graphics.drawable.GradientDrawable;

public class Recyclerview0Adapter extends RecyclerView.Adapter<Recyclerview0Adapter.ViewHolder> {

  ArrayList<HashMap<String, Object>> _data;
  CodeEditor editor;
  Context context;

  public Recyclerview0Adapter(
      ArrayList<HashMap<String, Object>> _arr, CodeEditor editor, Context context) {
    _data = _arr;
    editor = editor;
    context = context;
  }

  @Override
  public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
    View _v = LayoutInflater.from(parent.getContext()).inflate(R.layout.fogbind, parent, false);
    RecyclerView.LayoutParams _lp =
        new RecyclerView.LayoutParams(
            ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);
    _v.setLayoutParams(_lp);
    return new ViewHolder(_v);
  }

  @Override
  public void onBindViewHolder(ViewHolder _holder, final int _position) {
    View _view = _holder.itemView;
    Animation animation =
        AnimationUtils.loadAnimation(context.getApplicationContext(), android.R.anim.fade_in);
    _view.startAnimation(animation);

    RecyclerView.LayoutParams _lp =
        new RecyclerView.LayoutParams(
            ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);
    _view.setLayoutParams(_lp);
    final LinearLayout lib = _view.findViewById(R.id.lib);
    final LinearLayout main = _view.findViewById(R.id.main);
    final TextView tv3 = _view.findViewById(R.id.tv3);
    final TextView c1 = _view.findViewById(R.id.c1);
    tv3.setText(_Decrypt(_data.get((int) _position).get("post").toString().trim()));
    try {
      c1.setText(tv3.getText().toString().substring((int) (0), (int) (1)).trim());
    } catch (Exception e) {
      c1.setText("c");
    }
    main.setOnClickListener(
        new View.OnClickListener() {
          @Override
          public void onClick(View _view) {
            main.setOnTouchListener(
                new View.OnTouchListener() {
                  @Override
                  public boolean onTouch(View v, MotionEvent event) {
                    switch (event.getAction()) {
                      case MotionEvent.ACTION_DOWN:
                        {
                          ObjectAnimator scaleX = new ObjectAnimator();
                          scaleX.setTarget(main);
                          scaleX.setPropertyName("scaleX");
                          scaleX.setFloatValues(0.9f);
                          scaleX.setDuration((int) 5);
                          scaleX.start();

                          ObjectAnimator scaleY = new ObjectAnimator();
                          scaleY.setTarget(main);
                          scaleY.setPropertyName("scaleY");
                          scaleY.setFloatValues(0.9f);
                          scaleY.setDuration((int) 5);
                          scaleY.start();
                          break;
                        }
                      case MotionEvent.ACTION_UP:
                        {
                          ObjectAnimator scaleX = new ObjectAnimator();
                          scaleX.setTarget(main);
                          scaleX.setPropertyName("scaleX");
                          scaleX.setFloatValues((float) 1);
                          scaleX.setDuration((int) 5);
                          scaleX.start();

                          ObjectAnimator scaleY = new ObjectAnimator();
                          scaleY.setTarget(main);
                          scaleY.setPropertyName("scaleY");
                          scaleY.setFloatValues((float) 1);
                          scaleY.setDuration((int) 5);
                          scaleY.start();

                          break;
                        }
                    }
                    return false;
                  }
                });
            editor.getSearcher().search(_data.get((int) _position).get("post").toString().trim());
            try {
              editor.getSearcher().gotoNext();
            } catch (IllegalStateException e) {
              e.printStackTrace();
            }
          }
        });
    main.setOnLongClickListener(
        new View.OnLongClickListener() {
          @Override
          public boolean onLongClick(View _view) {
            var di = new GhostWebMaterialDialog(context);
            di.setTitle("Fog Decoder");
            di.setMessage(
                "replace or replaceAll "
                    .concat(
                        _data
                            .get((int) _position)
                            .get("post")
                            .toString()
                            .trim()
                            .concat(
                                "to"
                                    .concat(
                                        _Decrypt(
                                            _data
                                                .get((int) _position)
                                                .get("post")
                                                .toString()
                                                .trim())))));
            di.setNeutralButton(
                "rep",
                (p, d) -> {
                  try {
                    editor.getSearcher().replaceThis(tv3.getText().toString());
                  } catch (IllegalStateException e) {
                    e.printStackTrace();
                  }
                  try {
                    editor.getSearcher().gotoNext();
                  } catch (IllegalStateException e) {
                    e.printStackTrace();
                  }
                });
            di.setPositiveButton(
                "repAll",
                (p1, d2) -> {
                  try {
                    editor.getSearcher().replaceAll(tv3.getText().toString());
                  } catch (IllegalStateException e) {
                    e.printStackTrace();
                  }
                  try {
                    editor.getSearcher().gotoNext();
                  } catch (IllegalStateException e) {
                    e.printStackTrace();
                  }
                });
            di.show();

            return true;
          }
        });
  }

  @Override
  public int getItemCount() {
    return _data.size();
  }

  public class ViewHolder extends RecyclerView.ViewHolder {
    public ViewHolder(View v) {
      super(v);
    }
  }

  public String encrypt(String str, String str2) {
    String encrypted = "";
    try {
      encrypted = new String(android.util.Base64.encode(xor(str.getBytes("UTF-8"), str2), 2));
    } catch (java.io.UnsupportedEncodingException e) {
    }
    return encrypted;
  }

  public String decrypt(String str, String str2) {
    String decrypted = "";
    try {
      decrypted = new String(xor(android.util.Base64.decode(str, 2), str2), "UTF-8");
    } catch (java.io.UnsupportedEncodingException e) {
    }
    return decrypted;
  }

  private static byte[] xor(byte[] bArr, String str) {
    int length = bArr.length;
    int length2 = str.length();
    int i = 0;
    int i2 = 0;
    while (i2 < length) {
      if (i >= length2) {
        i = 0;
      }
      bArr[i2] = (byte) (bArr[i2] ^ str.charAt(i));
      i2++;
      i++;
    }
    return bArr;
  }

  public String _Decrypt(final String _text) {
    String decrypted = "";
    try {
      decrypted = decrypt(_text, "UTF-8");
    } catch (Exception e) {
      decrypted = "Error : " + e.toString();
    }
    return decrypted;
  }
}
