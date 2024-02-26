package Ninja.coder.Ghostemane.code;

import android.animation.*;
import android.app.*;
import android.app.Activity;
import android.content.*;
import android.content.SharedPreferences;
import android.content.res.*;
import android.graphics.*;
import android.graphics.drawable.*;
import android.media.*;
import android.net.*;
import android.os.*;
import android.text.*;
import android.text.style.*;
import android.util.*;
import android.view.*;
import android.view.View;
import android.view.View.*;
import android.view.animation.*;
import android.webkit.*;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.widget.*;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import androidx.activity.*;
import androidx.annotation.*;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.DialogFragment;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.recyclerview.*;
import androidx.recyclerview.selection.*;
import androidx.webkit.*;
import arabware.file.*;
import com.allenliu.badgeview.*;

import com.blankj.utilcode.*;
import com.bumptech.glide.*;
import com.bumptech.glide.integration.avif.*;
import com.bumptech.glide.integration.recyclerview.*;
import com.caverock.androidsvg.*;
import com.fasterxml.jackson.core.*;
import com.github.angads25.filepicker.*;
import com.github.junrar.*;
import com.google.android.material.*;
import com.hzy.lib7z.*;
import com.jsibbold.zoomage.*;
import com.lxj.xpopup.*;
import com.mukesh.*;
import com.neo.highlight.*;
import com.skydoves.powermenu.*;
import com.tapadoo.alerter.*;
import com.zip4j.*;
import fi.iki.elonen.*;
import java.io.*;
import java.text.*;
import java.util.*;
import java.util.regex.*;
import org.benf.cfr.reader.*;
import org.beyka.tiffbitmapfactory.*;
import org.json.*;
import storage.sdcard.*;
import xyz.doikki.videoplayer.*;
import xyz.doikki.videoplayer.exo.*;
import xyz.doikki.videoplayer.ijk.*;

public class LiveviewebDialogFragmentActivity extends DialogFragment {
	
	private PraramnetLayoutNinja param;
	private LinearLayout linear2;
	private WebView mweb;
	private ImageView imageview1;
	private TextView textview1;
	
	private SharedPreferences shp;
	
	@NonNull
	@Override
	public View onCreateView(@NonNull LayoutInflater _inflater, @Nullable ViewGroup _container, @Nullable Bundle _savedInstanceState) {
		View _view = _inflater.inflate(R.layout.livevieweb_dialog_fragment, _container, false);
		initialize(_savedInstanceState, _view);
		initializeLogic();
		return _view;
	}
	
	private void initialize(Bundle _savedInstanceState, View _view) {
		param = _view.findViewById(R.id.param);
		linear2 = _view.findViewById(R.id.linear2);
		mweb = _view.findViewById(R.id.mweb);
		mweb.getSettings().setJavaScriptEnabled(true);
		mweb.getSettings().setSupportZoom(true);
		imageview1 = _view.findViewById(R.id.imageview1);
		textview1 = _view.findViewById(R.id.textview1);
		shp = getContext().getSharedPreferences("shp", Activity.MODE_PRIVATE);
		
		//no listener code
		
		//no listener code
		
		//webviewOnProgressChanged
		mweb.setWebChromeClient(new WebChromeClient() {
				@Override public void onProgressChanged(WebView view, int _newProgress) {
					
				}
		});
		
		mweb.setWebViewClient(new WebViewClient() {
			@Override
			public void onPageStarted(WebView _param1, String _param2, Bitmap _param3) {
				final String _url = _param2;
				
				super.onPageStarted(_param1, _param2, _param3);
			}
			
			@Override
			public void onPageFinished(WebView _param1, String _param2) {
				final String _url = _param2;
				
				super.onPageFinished(_param1, _param2);
			}
		});
		
		imageview1.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View _view) {
				dismiss();
			}
		});
	}
	
	private void initializeLogic() {
		if (shp.contains("pos_path")) {
			mweb.loadUrl("file:///".concat(shp.getString("pos_path", "")));
		}
		mweb.getSettings().setJavaScriptEnabled(true);
		mweb.getSettings().setBuiltInZoomControls(true);
		mweb.getSettings().setDisplayZoomControls(false);
		Naitivae.FragmentStatusAndNativeView(this,"#FF2B2122");
		Naitivae.FragmentModeAnim(this,android.R.anim.fade_in);
	}
	
	@Override
	public void onStart() {
		super.onStart();
		if (getDialog() != null) { 
			int width = ViewGroup.LayoutParams.MATCH_PARENT;
			int height = ViewGroup.LayoutParams.MATCH_PARENT; 
			 getDialog().getWindow().setLayout(width, height);
			getDialog().getWindow().addFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);getDialog().getWindow().setBackgroundDrawable(new ColorDrawable(0xFF2B2122));
		}
	}
}
