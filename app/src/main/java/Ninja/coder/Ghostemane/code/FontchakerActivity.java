package Ninja.coder.Ghostemane.code;

import android.Manifest;
import android.animation.*;
import android.app.*;
import android.app.Activity;
import android.content.*;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.PackageManager;
import android.content.res.*;
import android.graphics.*;
import android.graphics.drawable.*;
import android.media.*;
import android.net.*;
import android.net.Uri;
import android.os.*;
import android.text.*;
import android.text.style.*;
import android.util.*;
import android.view.*;
import android.view.View.*;
import android.view.animation.*;
import android.webkit.*;
import android.widget.*;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import androidx.activity.*;
import androidx.annotation.*;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.DialogFragment;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.recyclerview.*;
import androidx.recyclerview.selection.*;
import androidx.recyclerview.widget.*;
import androidx.recyclerview.widget.RecyclerView;
import androidx.recyclerview.widget.RecyclerView.Adapter;
import androidx.recyclerview.widget.RecyclerView.ViewHolder;
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
import java.util.ArrayList;
import java.util.HashMap;
import java.util.regex.*;
import org.benf.cfr.reader.*;
import org.beyka.tiffbitmapfactory.*;
import org.json.*;
import storage.sdcard.*;
import xyz.doikki.videoplayer.*;
import xyz.doikki.videoplayer.exo.*;
import xyz.doikki.videoplayer.ijk.*;

public class FontchakerActivity extends BaseCompat {
	
	private String Folder = "";
	private double index = 0;
	private String path = "";
	
	private ArrayList<String> list = new ArrayList<>();
	private ArrayList<String> folderList = new ArrayList<>();
	private ArrayList<String> fileList = new ArrayList<>();
	private ArrayList<HashMap<String, Object>> files = new ArrayList<>();
	
	private RecyclerView recyclerview1;
	private LinearLayout EmptyLayout;
	private ImageView imageview1;
	private TextView textview1;
	
	private SharedPreferences setfont;
	private Intent intent = new Intent();
	
	@Override
	protected void onCreate(Bundle _savedInstanceState) {
		super.onCreate(_savedInstanceState);
		setContentView(R.layout.fontchaker);
		initialize(_savedInstanceState);
		
		if (ContextCompat.checkSelfPermission(this, Manifest.permission.READ_EXTERNAL_STORAGE) == PackageManager.PERMISSION_DENIED) {
			ActivityCompat.requestPermissions(this, new String[] {Manifest.permission.READ_EXTERNAL_STORAGE}, 1000);
		} else {
			initializeLogic();
		}
	}
	
	@Override
	public void onRequestPermissionsResult(int requestCode, String[] permissions, int[] grantResults) {
		super.onRequestPermissionsResult(requestCode, permissions, grantResults);
		if (requestCode == 1000) {
			initializeLogic();
		}
	}
	
	private void initialize(Bundle _savedInstanceState) {
		recyclerview1 = findViewById(R.id.recyclerview1);
		EmptyLayout = findViewById(R.id.EmptyLayout);
		imageview1 = findViewById(R.id.imageview1);
		textview1 = findViewById(R.id.textview1);
		setfont = getSharedPreferences("setfont", Activity.MODE_PRIVATE);
	}
	
	private void initializeLogic() {
		Folder = "/storage/emulated/0/GhostWebIDE/font";
		_getFiles("");
		ColorAndroid12.setTextColor(textview1);
		ColorAndroid12.setColorFilter(imageview1);
	}
	
	public void _getFiles(final String _path) {
		list.clear();
		files.clear();
		folderList.clear();
		fileList.clear();
		FileUtil.listDir(Folder, list);
		Collections.sort(list, String.CASE_INSENSITIVE_ORDER);
		index = 0;
		for(int _repeat13 = 0; _repeat13 < (int)(list.size()); _repeat13++) {
			if (FileUtil.isDirectory(list.get((int)(index)))) {
				folderList.add(list.get((int)(index)));
			}
			else {
				fileList.add(list.get((int)(index)));
			}
			index++;
		}
		index = 0;
		for(int _repeat37 = 0; _repeat37 < (int)(folderList.size()); _repeat37++) {
			{
				HashMap<String, Object> _item = new HashMap<>();
				_item.put("path", folderList.get((int)(index)));
				files.add(_item);
			}
			
			index++;
		}
		index = 0;
		for(int _repeat54 = 0; _repeat54 < (int)(fileList.size()); _repeat54++) {
			{
				HashMap<String, Object> _item = new HashMap<>();
				_item.put("path", fileList.get((int)(index)));
				files.add(_item);
			}
			
			index++;
		}
		if (list.size() == 0) {
			recyclerview1.setVisibility(View.GONE);
			EmptyLayout.setVisibility(View.VISIBLE);
		}
		else {
			EmptyLayout.setVisibility(View.GONE);
			recyclerview1.setVisibility(View.VISIBLE);
		}
		recyclerview1.setAdapter(new Recyclerview1Adapter(files));
		recyclerview1.setLayoutManager(new LinearLayoutManager(this));
	}
	
	public class Recyclerview1Adapter extends RecyclerView.Adapter<Recyclerview1Adapter.ViewHolder> {
		
		ArrayList<HashMap<String, Object>> _data;
		
		public Recyclerview1Adapter(ArrayList<HashMap<String, Object>> _arr) {
			_data = _arr;
		}
		
		@Override
		public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
			LayoutInflater _inflater = getLayoutInflater();
			View _v = _inflater.inflate(R.layout.fontview1, null);
			RecyclerView.LayoutParams _lp = new RecyclerView.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);
			_v.setLayoutParams(_lp);
			return new ViewHolder(_v);
		}
		
		@Override
		public void onBindViewHolder(ViewHolder _holder, final int _position) {
			View _view = _holder.itemView;
			
			final LinearLayout linear1 = _view.findViewById(R.id.linear1);
			final LinearLayout linear2 = _view.findViewById(R.id.linear2);
			final TextView textview1 = _view.findViewById(R.id.textview1);
			final TextView textview2 = _view.findViewById(R.id.textview2);
			
			path = _data.get((int)_position).get("path").toString();
			RecyclerView.LayoutParams _lp = new RecyclerView.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT,ViewGroup.LayoutParams.WRAP_CONTENT);
				_view.setLayoutParams(_lp);
			if (FileUtil.isDirectory(path)) {
				
			}
			else {
				try{
					if (path.endsWith(".ttf")) {
						pass();
						
						typefaceinFile(textview1,new File(path));
						typefaceinFile(textview2,new File(path));
						textview2.setText(textview1.getText().toString().substring((int)(0), (int)(1)));
						textview1.setText("This Text for Test | متن تستی");
					}
				}catch(Exception e){
					SketchwareUtil.showMessage(getApplicationContext(), "Error");
				}
			}
			textview1.setTextColor(0xFFFFDCBD);
			textview2.setTextColor(0xFFC4A68A);
			linear2.setBackground(new GradientDrawable() { public GradientDrawable getIns(int a, int b) { this.setCornerRadius(a); this.setColor(b); return this; } }.getIns((int)18, 0xFFFFDCBD));
			linear1.setOnClickListener(new View.OnClickListener() {
				@Override
				public void onClick(View _view) {
					setfont.edit().putString("mfont", path).commit();
					SketchwareUtil.showMessage(getApplicationContext(), "فونت اعمال شد");
					var di = new com.google.android.material.dialog.MaterialAlertDialogBuilder(FontchakerActivity.this);
					
					di.setTitle("انجام شد!");
					di.setMessage("فونت اعمال شد ریست برنامه برای اجرای شدن فونت؟");
					di.setNeutralButton("بله", (p, d) -> {
						
						         intent.setClass(getApplicationContext(), FiledirActivity.class);
						intent.setFlags(Intent.FLAG_ACTIVITY_SINGLE_TOP);
						startActivity(intent);
						
									});
					di.setCancelable(false);
					androidx.appcompat.app.AlertDialog dialog = di.show();
					final View view = dialog.getWindow().getDecorView();
					view.setScaleX(0f);
					view.setScaleY(0f);  dialog.getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_VISIBLE);
					final ObjectAnimator alertAnim = new ObjectAnimator();
					final ObjectAnimator alertAnim1 = new ObjectAnimator();
					alertAnim.setTarget(view);
					alertAnim.setPropertyName("scaleX");
					alertAnim.setFloatValues((float) (1));
					alertAnim.setDuration((int) (250));
					alertAnim.start();
					alertAnim1.setTarget(view);
					alertAnim1.setPropertyName("scaleY");
					alertAnim1.setFloatValues((float) (1));
					alertAnim1.setDuration((int) (250));
					alertAnim1.start();
					dialog.show();
					
					ClickEffcat(linear1);
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
	}
	
	@Deprecated
	public void showMessage(String _s) {
		Toast.makeText(getApplicationContext(), _s, Toast.LENGTH_SHORT).show();
	}
	
	@Deprecated
	public int getLocationX(View _v) {
		int _location[] = new int[2];
		_v.getLocationInWindow(_location);
		return _location[0];
	}
	
	@Deprecated
	public int getLocationY(View _v) {
		int _location[] = new int[2];
		_v.getLocationInWindow(_location);
		return _location[1];
	}
	
	@Deprecated
	public int getRandom(int _min, int _max) {
		Random random = new Random();
		return random.nextInt(_max - _min + 1) + _min;
	}
	
	@Deprecated
	public ArrayList<Double> getCheckedItemPositionsToArray(ListView _list) {
		ArrayList<Double> _result = new ArrayList<Double>();
		SparseBooleanArray _arr = _list.getCheckedItemPositions();
		for (int _iIdx = 0; _iIdx < _arr.size(); _iIdx++) {
			if (_arr.valueAt(_iIdx))
			_result.add((double)_arr.keyAt(_iIdx));
		}
		return _result;
	}
	
	@Deprecated
	public float getDip(int _input) {
		return TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, _input, getResources().getDisplayMetrics());
	}
	
	@Deprecated
	public int getDisplayWidthPixels() {
		return getResources().getDisplayMetrics().widthPixels;
	}
	
	@Deprecated
	public int getDisplayHeightPixels() {
		return getResources().getDisplayMetrics().heightPixels;
	}
}
