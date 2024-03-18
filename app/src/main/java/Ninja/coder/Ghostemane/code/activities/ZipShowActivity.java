package Ninja.coder.Ghostemane.code.activities;

import Ninja.coder.Ghostemane.code.R;
import Ninja.coder.Ghostemane.code.glidecompat.GlideCompat;
import Ninja.coder.Ghostemane.code.model.LayoutPlus;
import Ninja.coder.Ghostemane.code.utils.FileUtil;
import Ninja.coder.Ghostemane.code.utils.SketchwareUtil;
import Ninja.coder.Ghostemane.code.utils.SvgShow;
import Ninja.coder.Ghostemane.code.widget.GhostWebMaterialDialog;
import android.Manifest;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.GradientDrawable;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.*;
import androidx.appcompat.widget.Toolbar;
import androidx.coordinatorlayout.widget.CoordinatorLayout;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import androidx.recyclerview.widget.RecyclerView;
import com.google.android.material.appbar.AppBarLayout;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.util.*;
import java.util.zip.ZipEntry;
import java.util.zip.ZipFile;

public class ZipShowActivity extends BaseCompat {

    com.balatong.zip.Unzipper unzipper;
    private Timer _timer = new Timer();
    private Toolbar _toolbar;
    private AppBarLayout _app_bar;
    private CoordinatorLayout _coordinator;
    private boolean groovie = false;
    private String path = "";
    private double number = 0;
    private String UpFolder = "";
    private ArrayList<String> Str = new ArrayList<>();
    private ArrayList<HashMap<String, Object>> Maps = new ArrayList<>();
    private RecyclerView rv_zip;
    private LinearLayout linear1;
    private LinearLayout linear2;
    private ListView listView;
    private TextView textview1;
    private ImageView imageview1;
    private LayoutPlus layoutPlus;
    private TimerTask tt;
    private ProgressDialog dialogProGress;
    private Ninja.coder.Ghostemane.code.adapter.ContentsAdapter zipContentsAdapter;
    private boolean isUnzipperBound = false;
    private File targetZipFile;
//  _install(getIntent().getStringExtra("zipview"));
//          unzipper.deleteZipEntries(zipContentsAdapter.getCheckedItems());

    @Override
    protected void onCreate(Bundle _savedInstanceState) {
        super.onCreate(_savedInstanceState);
        setContentView(R.layout.zipshow);
        initialize(_savedInstanceState);

        if (ContextCompat.checkSelfPermission(this, Manifest.permission.READ_EXTERNAL_STORAGE)
                == PackageManager.PERMISSION_DENIED) {
            ActivityCompat.requestPermissions(
                    this, new String[]{Manifest.permission.READ_EXTERNAL_STORAGE}, 1000);
        } else {
            initializeLogic();
        }
    }

    @Override
    public void onRequestPermissionsResult(
            int requestCode, String[] permissions, int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        if (requestCode == 1000) {
            initializeLogic();
        }
    }

    private void initialize(Bundle _savedInstanceState) {
        _app_bar = findViewById(R.id._app_bar);
        _coordinator = findViewById(R.id._coordinator);
        _toolbar = findViewById(R.id._toolbar);
        layoutPlus = findViewById(R.id.layoutPlus);
        setSupportActionBar(_toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setHomeButtonEnabled(true);
        _toolbar.setNavigationOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View _v) {
                        onBackPressed();
                    }
                });
        linear1 = findViewById(R.id.linear1);
        linear2 = findViewById(R.id.linear2);
        listView = findViewById(R.id.listView);
        textview1 = findViewById(R.id.textview1);
        imageview1 = findViewById(R.id.imageview1);
        rv_zip = findViewById(R.id.rv_zip);
        layoutPlus.addItem("add File or folder", R.drawable.file);
        layoutPlus.addItem("Removed", R.drawable.delete);
        layoutPlus.addItem("UnZip", R.drawable.unzip);
        layoutPlus.setVisibility(View.GONE);
        imageview1.setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View _view) {
                        onBackPressed();
                    }
                });
    }

    private void initializeLogic() {
        dialogProGress =
                new ProgressDialog(ZipShowActivity.this, ProgressDialog.THEME_DEVICE_DEFAULT_DARK);
        /// result code By Ninja coder.ir my love java

        GradientDrawable sp = new GradientDrawable();
        sp.setColor(0xFF2B2121);
        sp.setCornerRadius(25);
        sp.setStroke(1, 0xFFFDA893);
        dialogProGress.getWindow().setBackgroundDrawable(sp);
        try {
            _install(getIntent().getStringExtra("zipview"));
        } catch (Exception e) {
            SketchwareUtil.showMessage(getApplicationContext(), e.toString());
        }
        /// AddNiceNameFab


        _toolbar.setVisibility(View.GONE);
        listView.setHorizontalScrollBarEnabled(false);
        listView.setVerticalScrollBarEnabled(false);
        listView.setOverScrollMode(ListView.OVER_SCROLL_NEVER);
    }

    public Bitmap extractIcon(String filePath) throws IOException {
        ZipFile zipFile = new ZipFile(filePath);
        ZipEntry iconEntry = zipFile.getEntry(filePath);
        InputStream inputStream = zipFile.getInputStream(iconEntry);
        Bitmap bitmapIcon = BitmapFactory.decodeStream(inputStream);
        return bitmapIcon;
    }

    public void _install(final String _input) {
        ProgressDialog dialog = new ProgressDialog(this);
        dialog.setTitle("Loading..");

        dialog.show();
        targetZipFile = new File(_input);
        unzipper = new com.balatong.zip.Unzipper(targetZipFile, getApplicationContext());

        unzipper.setUnzipListener(
                new com.balatong.zip.Unzipper.UnzipListener() {
                    @Override
                    public void onStart() {
                        dialog.setMessage("Loading...");
                    }

                    @Override
                    public void onProgress() {
                        dialog.setMessage("LoadingProgress...");
                    }

                    @Override
                    public void onFinish(Map<String, Object> parent) {
                        zipContentsAdapter = new Ninja.coder.Ghostemane.code.adapter.ContentsAdapter(getApplicationContext());
                        zipContentsAdapter.setSource(parent);
                        rv_zip.setAdapter(zipContentsAdapter);
                        if (dialog != null) {
                            dialog.dismiss();
                        }
                    }

                    @Override
                    public void onError(String errorMessage) {
                        dialog.setMessage(errorMessage);
                    }
                });
    }

    public void _Adptor(final ListView _lis) {
        Maps.clear();
        FileUtil.listDir(path, Str);
        final class FileComparator implements Comparator<String> {
            public int compare(String f1, String f2) {
                if (f1 == f2) return 0;
                if (FileUtil.isDirectory(f1) && FileUtil.isFile(f2)) return -1;
                if (FileUtil.isFile(f1) && FileUtil.isDirectory(f2)) return 1;
                return f1.compareToIgnoreCase(f2);
            }
        }
        Collections.sort(Str, new FileComparator());
        number = 0;
        for (int puls = 0; puls < (int) (Str.size()); puls++) {
            {
                HashMap<String, Object> _item = new HashMap<>();
                _item.put("path", Str.get((int) (number)));
                Maps.add(_item);
            }

            Maps.get((int) number).put("select", "false");
            number++;
        }
        _lis.setAdapter(new MyListViewListAdapter(Maps));

        ((BaseAdapter) _lis.getAdapter()).notifyDataSetChanged();
    }

    public void _pp() {
        var di = new GhostWebMaterialDialog(ZipShowActivity.this);
        ViewGroup viewGroup = findViewById(android.R.id.content);
        View dialogview = getLayoutInflater().inflate(R.layout.mylist, viewGroup, false);
        ListView mlist = dialogview.findViewById(R.id.mlist);
        Button btnback = dialogview.findViewById(R.id.btnback);
        Button btnhome = dialogview.findViewById(R.id.btnhome);
        btnback.setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View _view) {

                        if (path.equals(FileUtil.getExternalStorageDir())) {

                        } else {
                            UpFolder = path.substring((int) (0), (int) (path.lastIndexOf("/")));
                            path = UpFolder;
                            _Adptor(mlist);
                        }
                    }
                });
        btnhome.setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View _view) {
                        path = FileUtil.getExternalStorageDir();
                        _Adptor(mlist);
                    }
                });
        path = FileUtil.getExternalStorageDir();
        _Adptor(mlist);
        mlist.setOnItemLongClickListener(
                new AdapterView.OnItemLongClickListener() {
                    @Override
                    public boolean onItemLongClick(
                            AdapterView<?> _param1, View _param2, int _param3, long _param4) {
                        final int _position = _param3;

                        return true;
                    }
                });
        mlist.setOnItemClickListener(
                new AdapterView.OnItemClickListener() {
                    @Override
                    public void onItemClick(AdapterView<?> _param1, View _param2, int _param3, long _param4) {
                        final int _position = _param3;
                        if (FileUtil.isDirectory(Maps.get((int) _position).get("path").toString())) {
                            path = Maps.get((int) _position).get("path").toString();
                            _Adptor(mlist);
                        } else {
                            if (Maps.get((int) _position).get("path").toString().endsWith(".zip")) {
                            }
                        }
                    }
                });
        di.setTitle("Custom File Manager");
        di.setNegativeButton("Exit", (p3, d3) -> {
        });

        di.setPositiveButton(
                "select",
                (p1, d2) -> {
                    new AsyncTask<String, String, String>() {
                        @Override
                        protected void onPreExecute() {
                            dialogProGress.setTitle("Loading.....");
                            dialogProGress.show();
                        }

                        @Override
                        protected String doInBackground(String... params) {
                            String _param = params[0];
                            if (Maps.isEmpty()) {
                                SketchwareUtil.showMessage(getApplicationContext(), "please set item");
                            } else {
                                number = Maps.size() - 1;
                                for (int _repeat60 = 0; _repeat60 < (int) (Maps.size()); _repeat60++) {
                                    if (Maps.get((int) number).get("select").toString().equals("true")) {
                                        String extractPath = Maps.get((int) number).get("path").toString();
                                        unzipper.extractZipEntries(zipContentsAdapter.getCheckedItems(), extractPath);
                                        tt =
                                                new TimerTask() {
                                                    @Override
                                                    public void run() {
                                                        runOnUiThread(
                                                                new Runnable() {
                                                                    @Override
                                                                    public void run() {
                                                                        _install(getIntent().getStringExtra("zipview"));
                                                                    }
                                                                });
                                                    }
                                                };
                                        _timer.schedule(tt, (int) (100));
                                    }
                                    number--;
                                }
                                _Adptor(mlist);
                            }
                            return "";
                        }

                        @Override
                        protected void onPostExecute(String _result) {
                            dialogProGress.dismiss();
                        }
                    }.execute("");
                });
        di.setView(dialogview);
        di.show();
    }

    public void _cc() {

        var di = new GhostWebMaterialDialog(ZipShowActivity.this);
        ViewGroup viewGroup = findViewById(android.R.id.content);
        View dialogview = getLayoutInflater().inflate(R.layout.mylist, viewGroup, false);
        ListView mlist = dialogview.findViewById(R.id.mlist);
        Button btnback = dialogview.findViewById(R.id.btnback);
        Button btnhome = dialogview.findViewById(R.id.btnhome);
        btnback.setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View _view) {

                        if (path.equals(FileUtil.getExternalStorageDir())) {

                        } else {
                            UpFolder = path.substring((int) (0), (int) (path.lastIndexOf("/")));
                            path = UpFolder;
                            _Adptor(mlist);
                        }
                    }
                });
        btnhome.setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View _view) {
                        path = FileUtil.getExternalStorageDir();
                        _Adptor(mlist);
                    }
                });
        path = FileUtil.getExternalStorageDir();
        _Adptor(mlist);
        mlist.setOnItemLongClickListener(
                new AdapterView.OnItemLongClickListener() {
                    @Override
                    public boolean onItemLongClick(
                            AdapterView<?> _param1, View _param2, int _param3, long _param4) {
                        final int _position = _param3;

                        return true;
                    }
                });
        mlist.setOnItemClickListener(
                new AdapterView.OnItemClickListener() {
                    @Override
                    public void onItemClick(AdapterView<?> _param1, View _param2, int _param3, long _param4) {
                        final int _position = _param3;
                        if (FileUtil.isDirectory(Maps.get((int) _position).get("path").toString())) {
                            path = Maps.get((int) _position).get("path").toString();
                            _Adptor(mlist);
                        } else {
                            if (Maps.get((int) _position).get("path").toString().endsWith(".zip")) {
                            }
                        }
                    }
                });
        di.setTitle("Custom File Manager");
        di.setNegativeButton("Exit", (p3, d3) -> {
        });

        di.setPositiveButton(
                "select",
                (p1, d2) -> {
                    new AsyncTask<String, String, String>() {
                        @Override
                        protected void onPreExecute() {
                            dialogProGress.setTitle("Loading....");
                            dialogProGress.show();
                        }

                        @Override
                        protected String doInBackground(String... params) {
                            String _param = params[0];
                            if (Maps.isEmpty()) {
                                SketchwareUtil.showMessage(getApplicationContext(), "please set item");
                            } else {
                                number = Maps.size() - 1;
                                for (int _repeat60 = 0; _repeat60 < (int) (Maps.size()); _repeat60++) {
                                    if (Maps.get((int) number).get("select").toString().equals("true")) {
                                        String pickedF = Maps.get((int) number).get("path").toString();
                                        unzipper.addZipEntries(zipContentsAdapter.getCurrentDirectory(), pickedF);
                                        tt =
                                                new TimerTask() {
                                                    @Override
                                                    public void run() {
                                                        runOnUiThread(
                                                                new Runnable() {
                                                                    @Override
                                                                    public void run() {
                                                                        _install(getIntent().getStringExtra("zipview"));
                                                                    }
                                                                });
                                                    }
                                                };
                                        _timer.schedule(tt, (int) (100));
                                    }
                                    number--;
                                }
                                _Adptor(mlist);
                            }
                            return "";
                        }

                        @Override
                        protected void onPostExecute(String _result) {
                            dialogProGress.dismiss();
                        }
                    }.execute("");
                });
        di.setView(dialogview);
        di.show();
    }

    public void _hsi(final String _key, final ImageView _img) {
        if (_key.endsWith(".png")) {
            try {
                Bitmap bitmap = extractIcon(_key);
                _img.setImageBitmap(bitmap);
            } catch (IOException e) {
                e.printStackTrace();
            }

        } else {
            if (_key.endsWith(".java")) {
                _img.setImageResource(R.drawable.javanull);
            } else {
                if (_key.endsWith(".mp4")) {
                    _img.setImageResource(R.drawable.musico);
                } else {
                    if (_key.endsWith(".mp3")) {
                        _img.setImageResource(R.drawable.musico);
                    } else {
                        if (_key.endsWith(".rar")) {
                            _img.setImageResource(R.drawable.rartarget);
                        } else {
                            if (_key.endsWith(".zip")) {
                                _img.setImageResource(R.drawable.zipandtar);
                            } else {
                                if (_key.endsWith(".pdf")) {
                                    _img.setImageResource(R.drawable.rartarget);
                                } else {
                                    if (_key.endsWith(".go")) {
                                        _img.setImageResource(R.drawable.languagego);
                                    } else {
                                        if (_key.endsWith(".php")) {
                                            _img.setImageResource(R.drawable.languagephp);
                                        } else {
                                            if (_key.endsWith(".xml")) {
                                                _img.setImageResource(R.drawable.keyboardlisnertalluserpost_4);
                                            } else {
                                                if (_key.endsWith(".js")) {
                                                    SvgShow.GetAsster(getApplicationContext(), _img, "file_type_node.svg");
                                                } else {
                                                    if (_key.endsWith(".cpp")) {
                                                        SvgShow.GetAsster(getApplicationContext(), _img, "file_type_cpp3.svg");
                                                    } else {
                                                        if (_key.endsWith(".html")) {
                                                            SvgShow.GetAsster(
                                                                    getApplicationContext(), _img, "file_type_html.svg");
                                                        } else {
                                                            if (_key.endsWith(".html")) {
                                                                _img.setImageResource(R.drawable.javanull);
                                                            } else {
                                                                _img.setImageResource(R.drawable.file);
                                                            }
                                                        }
                                                    }
                                                }
                                            }
                                        }
                                    }
                                }
                            }
                        }
                    }
                }
            }
        }
    }

    public void showMessage(String _s) {
        Toast.makeText(getApplicationContext(), _s, Toast.LENGTH_SHORT).show();
    }

    class ContentsAdapter extends ArrayAdapter<String>
            implements AdapterView.OnItemClickListener, AdapterView.OnItemLongClickListener {

        private List<String> orderedList = new ArrayList<String>();
        private Map<String, Object> source;
        private ListView listView;
        private String currentDirectory = "";
        private List<Integer> checkedItems = new ArrayList<Integer>();
        private boolean lonClicked = false;

        public ContentsAdapter(Context context, ListView listView) {
            super(context, 0);
            this.listView = listView;
            this.listView.setOnItemClickListener(this);
            this.listView.setOnItemLongClickListener(this);
        }

        public Map<String, Object> getSource() {
            return source;
        }

        public void setSource(final Map<String, Object> source) {
            super.clear();
            this.source = source;
            checkedItems.clear();
            orderedList.clear();
            orderedList.addAll(source.keySet());
            Collections.sort(
                    orderedList,
                    new Comparator<String>() {
                        public int compare(String lhs, String rhs) {
                            if (lhs.equals("....")) return -1;
                            if (rhs.equals("....")) return +1;
                            if (source.get(lhs) instanceof Map && source.get(rhs) instanceof Map)
                                return lhs.compareTo(rhs);
                            if (source.get(lhs) instanceof Map && !(source.get(rhs) instanceof Map)) return -1;
                            if (!(source.get(lhs) instanceof Map) && source.get(rhs) instanceof Map) return +1;
                            if (!(source.get(lhs) instanceof Map) && !(source.get(rhs) instanceof Map))
                                return lhs.compareTo(rhs);

                            return 0;
                        }
                    });

            for (String key : orderedList) super.add(key);
        }

        @Override
        public View getView(final int position, View view, ViewGroup parent) {
            view = LayoutInflater.from(parent.getContext()).inflate(R.layout.views, parent, false);
            String key = orderedList.get(position);
            Object obj = source.get(key);
            ImageView image = (ImageView) view.findViewById(R.id.imageview1);
            TextView entry = (TextView) view.findViewById(R.id.textview1);
            TextView desc = (TextView) view.findViewById(R.id.textview2);
            CheckBox chkBox = (CheckBox) view.findViewById(R.id.checkbox1);
            chkBox.setFocusable(false);
            chkBox.setEnabled(true);
            chkBox.setOnCheckedChangeListener(
                    (button, isChecked) -> {
                        if (isChecked && !checkedItems.contains(position)) checkedItems.add(position);
                        else if (!isChecked && checkedItems.contains(position))
                            checkedItems.remove(Integer.valueOf(position));
                    });

            if (!lonClicked) {
                if (checkedItems.size() == 0) {
                    chkBox.setVisibility(View.GONE);
                } else {
                    chkBox.setVisibility(View.VISIBLE);
                }
            } else {
                chkBox.setVisibility(View.VISIBLE);
            }

            if (checkedItems.contains(position)) chkBox.setChecked(true);
            else chkBox.setChecked(false);
            if ("..".equals(key)) {
                chkBox.setEnabled(false);

            }
            if (obj instanceof ZipEntry) {
                java.util.zip.ZipEntry zipEntry = (ZipEntry) obj;
                _hsi(key, image);
                entry.setText(key);
            } else if (obj instanceof Map) {
                image.setImageResource(R.drawable.folder);
                entry.setText(key);
                desc.setText("");
            } else {
                IllegalStateException e =
                        new IllegalStateException(
                                "There should have been an object at position " + position + ".");
                //	logger.error(e.getMessage(), e);
            }

            return view;
        }

        @Override
        public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
            String key = orderedList.get(position);
            Object value = source.get(key);
            if (value instanceof Map) {
                source = (Map<String, Object>) value;
                if ("..".equals(key)) {
                    currentDirectory = currentDirectory.substring(0, currentDirectory.lastIndexOf("/"));
                } else {
                    currentDirectory = currentDirectory + "/" + key;
                }
                // statusView.setText(currentDirectory);
                this.setSource(source);
                listView.setAdapter(this);
            } else { // ZipEntry
                java.util.zip.ZipEntry zipEntry = (java.util.zip.ZipEntry) value;
                // extract
            }
        }

        @Override
        public boolean onItemLongClick(AdapterView<?> parent, View view, int position, long id) {
            String key = orderedList.get(position);
            Object value = source.get(key);
            //	logger.debug("LongClicked on " + key);
            if (!lonClicked) {
                lonClicked = true;
                layoutPlus.setVisibility(View.VISIBLE);
            } else {
                lonClicked = false;
                checkedItems.clear();
                layoutPlus.setVisibility(View.GONE);
            }
            notifyDataSetChanged();
            return true;
        }

        public Map<String, Object> getCheckedItems() {
            Map<String, Object> items = new HashMap<String, Object>();
            for (Integer position : checkedItems) {
                String key = orderedList.get(position);
                Object value = source.get(key);
                items.put(key, value);
            }
            if (items.size() == 0) {
                //	logger.debug("Unzipping all files in source.");
                items = source;
            }
            return items;
        }

        public void uncheckItems() {
            checkedItems.clear();
        }

        public String getCurrentDirectory() {
            return currentDirectory;
        }

        public void setCurrentDirectory(String currentDirectory) {
            this.currentDirectory = currentDirectory;
        }
    }

    public class MyListViewListAdapter extends BaseAdapter {

        ArrayList<HashMap<String, Object>> _data;

        public MyListViewListAdapter(ArrayList<HashMap<String, Object>> _arr) {
            _data = _arr;
        }

        @Override
        public int getCount() {
            return _data.size();
        }

        @Override
        public HashMap<String, Object> getItem(int _index) {
            return _data.get(_index);
        }

        @Override
        public long getItemId(int _index) {
            return _index;
        }

        @Override
        public View getView(final int _position, View _v, ViewGroup _container) {
            LayoutInflater _inflater = getLayoutInflater();
            View _view = _v;
            if (_view == null) {
                _view = _inflater.inflate(R.layout.view, null);
            }

            final LinearLayout l1 = (LinearLayout) _view.findViewById(R.id.l1);
            final TextView tv = (TextView) _view.findViewById(R.id.tv);
            final ImageView img = (ImageView) _view.findViewById(R.id.img);
            final CheckBox ch = (CheckBox) _view.findViewById(R.id.ch);
            tv.setText(Uri.parse(_data.get((int) _position).get("path").toString()).getLastPathSegment());
            if (FileUtil.isDirectory(_data.get((int) _position).get("path").toString())) {
                ch.setVisibility(View.VISIBLE);
                img.setImageResource(R.drawable.folder);
            } else {
                if (_data.get((int) _position).get("path").toString().endsWith(".jpeg")
                        || (_data.get((int) _position).get("path").toString().endsWith(".jpg")
                        || (_data.get((int) _position).get("path").toString().endsWith(".webp")
                        || (_data.get((int) _position).get("path").toString().endsWith(".bmp")
                        || _data.get((int) _position).get("path").toString().endsWith(".png"))))) {
                    GlideCompat.GlideNormal(img, _data.get(_position).get("path").toString());
                } else if (FileUtil.isExistFile(_data.get(_position).get("path").toString())) {
                    // img.setImageResource(R.drawable.file);
                    _hsi(_data.get(_position).get("path").toString(), img);
                } else {
                    img.setImageResource(R.drawable.file);
                }
                ch.setVisibility(View.GONE);
            }
            if (_data.get((int) _position).get("select").toString().equals("true")) {
                ch.setChecked(true);
            } else {
                ch.setChecked(false);
            }
            ch.setOnClickListener(
                    new View.OnClickListener() {
                        @Override
                        public void onClick(View _view) {
                            if (ch.isChecked()) {
                                _data.get((int) _position).put("select", "true");
                            } else {
                                _data.get((int) _position).put("select", "false");
                            }
                        }
                    });

            return _view;
        }
    }
}
