package Ninja.coder.Ghostemane.code;

import Ninja.coder.Ghostemane.code.GlideUtilApi.GlideCompat;
import Ninja.coder.Ghostemane.code.adapter.DevAd;
import Ninja.coder.Ghostemane.code.model.DevModel;
import android.content.Intent;
import android.net.Uri;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import android.widget.Toast;
import androidx.activity.OnBackPressedCallback;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import com.bumptech.glide.Glide;
import java.util.ArrayList;
import java.util.HashMap;
import android.os.Bundle;
import java.util.List;
import org.eclipse.jdt.internal.compiler.batch.Main;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;

public class AboutActivity extends BaseCompat implements DevAd.OnItemClick {
  private RequestNetwork sazndeh;
  private RequestNetwork.RequestListener _sazndeh_request_listener;
  private ImageView appicon;
  protected TextView tv_about_name;
  protected RecyclerView rv_about_dev;
  protected DevAd devAd;

  protected List<DevModel> listModel = new ArrayList<>();

  @Override
  protected void onCreate(Bundle _savedInstanceState) {
    super.onCreate(_savedInstanceState);
    setContentView(R.layout.about);
    start();
  }

  private void start() {

    appicon = findViewById(R.id.icon_glide_about);
    tv_about_name = findViewById(R.id.tv_about_name);
    rv_about_dev = findViewById(R.id.rv_about_dev);

    listModel.add(new DevModel("Appt2 ", "main"));
    listModel.add(new DevModel("BlueWhaleYT", "main"));
    listModel.add(new DevModel("Mohammad Taha", "main"));
    listModel.add(new DevModel("Ban Doroid", "marco"));
    listModel.add(new DevModel("Mister Java", "macro"));
    listModel.add(new DevModel("Psi", "Help to adding python"));
    listModel.add(new DevModel("EUP", "marco"));
    GlideCompat.GlideNormal(appicon,R.mipmap.ghosticon);
    devAd = new DevAd(listModel, this);
    rv_about_dev.setAdapter(devAd);
    rv_about_dev.setLayoutManager(new LinearLayoutManager(this, RecyclerView.VERTICAL, false));
    sazndeh = new RequestNetwork(this);
    _sazndeh_request_listener =
        new RequestNetwork.RequestListener() {
          @Override
          public void onResponse(String _param1, String respanse, HashMap<String, Object> _param3) {
            getElementUser(respanse);
          }

          @Override
          public void onErrorResponse(String _param1, String _param2) {
             
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
    runview();
  }

  private void runview() {
    sazndeh.startRequestNetwork(
        RequestNetworkController.GET, "https://github.com/appt2", "b", _sazndeh_request_listener);
  }

  @Override
  public void onItemChange(View v, int pos) {
    switch (pos) {
      case 0:
        openUrl("https://github.com/appt2");
        break;
      case 1:
        openUrl("https://github.com/BlueWhaleYT");
        break;
      case 2:
        Toast.makeText(getApplicationContext(), "User not found Github page", 2).show();
        break;
      case 3:
        openUrl("https://github.com/BanDroid");
        break;
      case 4:
        Toast.makeText(getApplicationContext(), "User not found Github page", 2).show();
        break;
      case 5:
        openUrl("https://github.com/PsiCodes");
        break;
      case 6:
        Toast.makeText(getApplicationContext(), "User not found Github page", 2).show();
        break;
    }
  }

  void openUrl(String link) {
    var get = new Intent();
    get.setAction(Intent.ACTION_VIEW);
    get.setData(Uri.parse(link));
    startActivity(get);
  }

  void getElementUser(String input) {
    try {

      Document d = Jsoup.parse(input);
      Elements im = d.getElementsByClass("avatar avatar-user width-full border color-bg-default");
      Elements bv = d.getElementsByClass("p-nickname vcard-username d-block");
      Glide.with(getApplicationContext())
          .load(Uri.parse(im.attr("src")))
          .circleCrop()
          .into(appicon);
      tv_about_name.setText(bv.text());
    } catch (Exception e) {
      e.printStackTrace();
    }
  }
}
