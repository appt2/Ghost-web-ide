package Ninja.coder.Ghostemane.code;

import Ninja.coder.Ghostemane.code.adapter.ApkListAdapter;
import Ninja.coder.Ghostemane.code.adapter.Extractor;
import android.Manifest;
import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.content.pm.PackageInfo;
import android.os.AsyncTask;
import android.view.Menu;
import android.app.SearchManager;
import android.content.Context;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.SearchView;
import androidx.appcompat.widget.Toolbar;
import androidx.core.view.MenuItemCompat;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.appbar.AppBarLayout;
import androidx.coordinatorlayout.widget.CoordinatorLayout;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import androidx.core.content.ContextCompat;
import android.content.pm.PackageManager;
import androidx.core.app.ActivityCompat;
import android.net.Uri;
import android.widget.Toast;
import com.google.android.material.dialog.MaterialAlertDialogBuilder;
import java.util.List;

public class ApkViewActivity extends AppCompatActivity {

  private Toolbar _toolbar;
  private AppBarLayout _app_bar;
  private CoordinatorLayout _coordinator;
  protected RecyclerView rv;
  protected ApkListAdapter adptor;

  @Override
  protected void onCreate(Bundle _savedInstanceState) {
    super.onCreate(_savedInstanceState);
    setContentView(R.layout.activity_apk_view);
    runbyid();

    if (ContextCompat.checkSelfPermission(this, Manifest.permission.READ_EXTERNAL_STORAGE)
            == PackageManager.PERMISSION_DENIED
        || ContextCompat.checkSelfPermission(this, Manifest.permission.WRITE_EXTERNAL_STORAGE)
            == PackageManager.PERMISSION_DENIED) {
      ActivityCompat.requestPermissions(
          this,
          new String[] {
            Manifest.permission.READ_EXTERNAL_STORAGE, Manifest.permission.WRITE_EXTERNAL_STORAGE
          },
          1000);
    } else {
      AppStartMain();
    }
  }

  @Override
  public void onRequestPermissionsResult(
      int requestCode, String[] permissions, int[] grantResults) {
    super.onRequestPermissionsResult(requestCode, permissions, grantResults);
    if (requestCode == 1000) {
      AppStartMain();
    }
  }

  private void runbyid() {
    _app_bar = findViewById(R.id.app_bar_apkview);
    _toolbar = findViewById(R.id.toolbar_apkview_main);
    setSupportActionBar(_toolbar);
    rv = findViewById(R.id.rv);
    _toolbar.setNavigationOnClickListener(
        new View.OnClickListener() {
          @Override
          public void onClick(View _v) {
            onBackPressed();
          }
        });
  }

  private void AppStartMain() {
    adptor = new ApkListAdapter(this);
    rv.setAdapter(adptor);
    rv.setLayoutManager(new LinearLayoutManager(this));
    new Loader().execute();
  }

  public void _shareApp(final String _finalPath) {
    Intent shareApp = new Intent(Intent.ACTION_SEND);
    shareApp.setType("application/vbd.android.package-archive");
    shareApp.putExtra(Intent.EXTRA_STREAM, Uri.fromFile(new java.io.File(_finalPath)));
    startActivity(Intent.createChooser(shareApp, "Share APK"));
  }

  public void showMessage(String _s) {
    Toast.makeText(getApplicationContext(), _s, Toast.LENGTH_SHORT).show();
  }

  public void doExctract(final PackageInfo info) {

    final Extractor extractor = new Extractor();
    try {
      String dst = extractor.extractWithoutRoot(info);
      Toast.makeText(this, String.format(dst), Toast.LENGTH_SHORT).show();
      return;
    } catch (Exception ex) {
      ex.printStackTrace();
    }
    new MaterialAlertDialogBuilder(ApkViewActivity.this)
        .setTitle("R.string.alert_root_title")
        .setMessage("R.string.alert_root_body")
        .setPositiveButton(
            "R.string.alert_root_yes",
            new DialogInterface.OnClickListener() {
              @Override
              public void onClick(DialogInterface dialog, int which) {
                try {
                  String dst = extractor.extractWithRoot(info);
                  Toast.makeText(ApkViewActivity.this, "Ereor", Toast.LENGTH_SHORT).show();
                } catch (Exception e) {
                  e.printStackTrace();
                  Toast.makeText(ApkViewActivity.this, "R.string.toast_failed", Toast.LENGTH_SHORT)
                      .show();
                }
              }
            })
        .setNegativeButton("R.string.alert_root_no", null)
        .show();
  }

  public void addItem(PackageInfo item) {
    adptor.addItem(item);
    
  }

  @Override
  public boolean onCreateOptionsMenu(Menu menu) {
    getMenuInflater().inflate(R.menu.main, menu);

    SearchManager searchManager = (SearchManager) getSystemService(Context.SEARCH_SERVICE);
    final SearchView searchView =
        (SearchView) MenuItemCompat.getActionView(menu.findItem(R.id.action_search));
    searchView.setSearchableInfo(searchManager.getSearchableInfo(getComponentName()));
    searchView.setOnQueryTextFocusChangeListener(
        new View.OnFocusChangeListener() {
          @Override
          public void onFocusChange(View view, boolean queryTextFocused) {
            if (!queryTextFocused && searchView.getQuery().length() < 1) {
              getSupportActionBar().collapseActionView();
            }
          }
        });
    searchView.setOnQueryTextListener(
        new SearchView.OnQueryTextListener() {
          @Override
          public boolean onQueryTextSubmit(String s) {
            return false;
          }

          @Override
          public boolean onQueryTextChange(String s) {
            adptor.setSearchPattern(s);
            return true;
          }
        });

    return super.onCreateOptionsMenu(menu);
  }

  class Loader extends AsyncTask<Void, PackageInfo, Void> {
    ProgressDialog dialog;

    public Loader() {
      dialog = ProgressDialog.show(ApkViewActivity.this, "Loadig..m", "Load...");
    }

    @Override
    protected Void doInBackground(Void... params) {
      List<PackageInfo> packages =
          getPackageManager().getInstalledPackages(PackageManager.GET_META_DATA);
      for (PackageInfo packageInfo : packages) {
        publishProgress(packageInfo);
      }
      return null;
    }

    @Override
    protected void onProgressUpdate(PackageInfo... values) {
      super.onProgressUpdate(values);
      addItem(values[0]);
    }

    @Override
    protected void onPostExecute(Void aVoid) {
      super.onPostExecute(aVoid);
      dialog.dismiss();
    }
  }
}
