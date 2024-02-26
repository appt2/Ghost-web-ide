package Ninja.coder.Ghostemane.code;

import Ninja.coder.Ghostemane.code.CodeHelper.JsonHelper;
import Ninja.coder.Ghostemane.code.SketchwareUtil;
import android.util.Log;
import androidx.activity.OnBackPressedCallback;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.HashMap;
import androidx.recyclerview.widget.RecyclerView;
import android.widget.LinearLayout;
import android.widget.ImageView;
import android.widget.TextView;
import de.hdodenhof.circleimageview.CircleImageView;
import android.content.Intent;
import android.os.Bundle;
import android.view.ViewGroup;
import android.view.View;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import androidx.recyclerview.widget.LinearLayoutManager;
import android.view.LayoutInflater;
import com.bumptech.glide.Glide;
import android.net.Uri;
import android.graphics.Typeface;
import android.widget.Toast;
import java.util.List;

public class AboutActivity extends BaseCompat {

  private double counter = 0;
  private double go = 0;

  private ArrayList<HashMap<String, Object>> mp = new ArrayList<>();
  private ArrayList<HashMap<String, Object>> moderApp = new ArrayList<>();

  private PraramnetLayoutNinja linear1;
  private RecyclerView recyclerview1;
  private LinearLayout linear3;
  private LinearLayout linear2;
  private RecyclerView recyclerview2;
  private ImageView imageview2;
  private TextView textview3;
  private TextView textview1;
  private CircleImageView uo;

  private Intent github = new Intent();
  private RequestNetwork sazndeh;
  private RequestNetwork.RequestListener _sazndeh_request_listener;

  @Override
  protected void onCreate(Bundle _savedInstanceState) {
    super.onCreate(_savedInstanceState);
    setContentView(R.layout.about);
    initialize(_savedInstanceState);
    initializeLogic();
  }

  private void initialize(Bundle _savedInstanceState) {
    linear1 = findViewById(R.id.linear1);
    recyclerview1 = findViewById(R.id.recyclerview1);
    linear3 = findViewById(R.id.linear3);
    linear2 = findViewById(R.id.linear2);
    recyclerview2 = findViewById(R.id.recyclerview2);
    imageview2 = findViewById(R.id.imageview2);
    textview3 = findViewById(R.id.textview3);
    textview1 = findViewById(R.id.textview1);
    uo = findViewById(R.id.uo);
    sazndeh = new RequestNetwork(this);

    _sazndeh_request_listener =
        new RequestNetwork.RequestListener() {
          @Override
          public void onResponse(String _param1, String _param2, HashMap<String, Object> _param3) {
            final String _tag = _param1;
            final String _response = _param2;
            final HashMap<String, Object> _responseHeaders = _param3;
            try {
              org.jsoup.nodes.Document d = org.jsoup.Jsoup.parse(_response);
              org.jsoup.select.Elements im =
                  d.getElementsByClass("avatar avatar-user width-full border color-bg-default");
              org.jsoup.select.Elements bv =
                  d.getElementsByClass("p-nickname vcard-username d-block");
              Glide.with(getApplicationContext()).load(Uri.parse(im.attr("src"))).into(uo);
              textview1.setText(bv.text());
            } catch (Exception e) {

            }
          }

          @Override
          public void onErrorResponse(String _param1, String _param2) {
            final String _tag = _param1;
            final String _message = _param2;
          }
        };
    getOnBackPressedDispatcher()
        .addCallback(
            this,
            new OnBackPressedCallback(true) {

              @Override
              public void handleOnBackPressed() {
                // TODO: ...
                finish();
                Log.i("Add call", "OnBackPressed");
              }
            });
  }

  private void initializeLogic() {
    moderApp =
        new Gson()
            .fromJson(
                "[\n{\n	\"main\": \"NinjaCoder\",\n	\"sub\": \"Moder Main\"\n},\n{\n	\"main\": \"Mohammad Taha\",\n	\"sub\": \"Moder Main\"\n},\n{\n	\"main\": \"Ban Doroid\",\n	\"sub\": \"Moder Main\"\n},\n{\n	\"main\": \"Psi\",\n	\"sub\": \"Moder Main (Help with To python runer)\"\n},\n{\n	\"main\": \"EUP\",\n	\"sub\": \"Moder Main (Help with To Git)\"\n},\n{\n	\"main\": \"BlueWhaleYT\",\n	\"sub\": \"Moder Main (Help with To Git clone)\"\n}\n]\n",
                new TypeToken<ArrayList<HashMap<String, Object>>>() {}.getType());
    recyclerview2.setAdapter(new Recyclerview2Adapter(moderApp));
    recyclerview2.setLayoutManager(new LinearLayoutManager(this));
    ColorAndroid12.setTextColor(textview3);
    ColorAndroid12.setTextColor(textview1);
    ColorAndroid12.setColorFilter(imageview2);
    sazndeh.startRequestNetwork(
        RequestNetworkController.GET,
        "https://github.com/Arashvscode",
        "b",
        _sazndeh_request_listener);
  }

  public class Recyclerview1Adapter extends RecyclerView.Adapter<Recyclerview1Adapter.ViewHolder> {

    ArrayList<HashMap<String, Object>> _data;

    public Recyclerview1Adapter(ArrayList<HashMap<String, Object>> _arr) {
      _data = _arr;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
      LayoutInflater _inflater = getLayoutInflater();
      View _v = _inflater.inflate(R.layout.dravel, null);
      RecyclerView.LayoutParams _lp =
          new RecyclerView.LayoutParams(
              ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);
      _v.setLayoutParams(_lp);
      return new ViewHolder(_v);
    }

    @Override
    public void onBindViewHolder(ViewHolder _holder, final int _position) {
      View _view = _holder.itemView;

      final LinearLayout linear1 = _view.findViewById(R.id.linear1);
      final androidx.cardview.widget.CardView cardview1 = _view.findViewById(R.id.cardview1);
      final LinearLayout linear2 = _view.findViewById(R.id.linear2);
      final LinearLayout linear3 = _view.findViewById(R.id.linear3);
      final ImageView imageview2 = _view.findViewById(R.id.imageview2);
      final TextView textview1 = _view.findViewById(R.id.textview1);

      textview1.setText(mp.get((int) _position).get("user").toString());
      Glide.with(getApplicationContext())
          .load(Uri.parse(mp.get((int) _position).get("img").toString()))
          .into(imageview2);
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
  }

  public class Recyclerview2Adapter extends RecyclerView.Adapter<Recyclerview2Adapter.ViewHolder> {

    ArrayList<HashMap<String, Object>> _data;

    public Recyclerview2Adapter(ArrayList<HashMap<String, Object>> _arr) {
      _data = _arr;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
      LayoutInflater _inflater = getLayoutInflater();
      View _v = _inflater.inflate(R.layout.layout_help, null);
      RecyclerView.LayoutParams _lp =
          new RecyclerView.LayoutParams(
              ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);
      _v.setLayoutParams(_lp);
      return new ViewHolder(_v);
    }

    @Override
    public void onBindViewHolder(ViewHolder _holder, final int _position) {
      View _view = _holder.itemView;

      final LinearLayout linear1 = _view.findViewById(R.id.linear1);
      final LinearLayout diver = _view.findViewById(R.id.diver);
      final LinearLayout linear3 = _view.findViewById(R.id.linear3);
      final LinearLayout linear2 = _view.findViewById(R.id.linear2);
      final LinearLayout back = _view.findViewById(R.id.back);
      final ImageView imageview1 = _view.findViewById(R.id.imageview1);
      final TextView moderName = _view.findViewById(R.id.moderName);
      final TextView Doc = _view.findViewById(R.id.Doc);

      RecyclerView.LayoutParams _lp =
          new RecyclerView.LayoutParams(
              ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);
      _view.setLayoutParams(_lp);
      moderName.setTypeface(Typeface.createFromAsset(getAssets(), "fonts/ghostfont.ttf"), 0);
      Doc.setTypeface(Typeface.createFromAsset(getAssets(), "fonts/ghostfont.ttf"), 0);
      _view.setOnClickListener(
          new View.OnClickListener() {
            @Override
            public void onClick(View _view) {
              AnimUtils.ClickAnimation(_view);
            }
          });
      ColorAndroid12.setColorFilter(imageview1);
      ColorAndroid12.setColorBackground(diver);
      ColorAndroid12.shapeView(back);
      moderName.setText(_data.get(_position).get("main").toString());
      Doc.setText(_data.get(_position).get("sub").toString());
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
  }

  @Deprecated
  public void showMessage(String _s) {
    Toast.makeText(getApplicationContext(), _s, Toast.LENGTH_SHORT).show();
  }

  
}
