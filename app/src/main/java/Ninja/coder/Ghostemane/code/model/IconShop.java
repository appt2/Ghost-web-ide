package Ninja.coder.Ghostemane.code.model;

import Ninja.coder.Ghostemane.code.R;
import Ninja.coder.Ghostemane.code.adapter.IconManagerAdapter;
import Ninja.coder.Ghostemane.code.utils.ColorAndroid12;
import android.content.Context;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.LinearLayout;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import com.blankj.utilcode.util.ThreadUtils;
import com.google.android.material.bottomsheet.BottomSheetDialog;
import com.google.android.material.color.MaterialColors;
import com.google.android.material.shape.CornerFamily;
import com.google.android.material.shape.ShapeAppearanceModel;
import com.google.android.material.textfield.TextInputLayout;
import com.skydoves.powermenu.PowerMenu;
import com.skydoves.powermenu.PowerMenuItem;

import java.io.File;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.concurrent.Executors;

import static android.view.View.GONE;
import static android.view.View.VISIBLE;

// tnks for codeassist and tryon ...
// rewinter code by ninja coder and appt2

public class IconShop {
    protected Context context;
    protected String path = "/storage/emulated/0/GhostWebIDE/.icon";
    protected RecyclerView re;
    protected boolean endIconShow;
    protected LinearLayout nullview;
    protected TextInputLayout serachIcon;
    private IconManagerAdapter mAdapter;
    private List<Icons> Icons = new ArrayList<>();

    public IconShop(Context context) {
        this.context = context;
        initList();
    }

    private void initList() {
        var view = LayoutInflater.from(context).inflate(R.layout.iconview_layout, null, false);
        re = view.findViewById(R.id.rv_iconShop);
        serachIcon = view.findViewById(R.id.icon_shop_serach);
        nullview = view.findViewById(R.id.null_view);
        serachIcon.setHint("Search Icon");
        serachIcon.setEndIconVisible(endIconShow ? true : false);
        serachIcon.setEndIconDrawable(R.drawable.closecompat);
        var shap = new ShapeAppearanceModel.Builder();
        shap.setAllCorners(CornerFamily.CUT, 20f);
        serachIcon.setShapeAppearanceModel(shap.build());
        serachIcon.setEndIconOnClickListener(
                c -> {
                    Icons.clear();
                    loadIcons();
                    serachIcon.getEditText().setText("");
                });
        nullview.setVisibility(GONE);
        serachIcon
                .getEditText()
                .addTextChangedListener(
                        new TextWatcher() {

                            @Override
                            public void afterTextChanged(Editable s) {
                                endIconShow = false;
                                if (s.toString().isEmpty()) {
                                    Icons.clear();
                                    loadIcons();
                                }
                            }

                            @Override
                            public void beforeTextChanged(CharSequence arg0, int arg1, int arg2, int arg3) {
                            }

                            @Override
                            public void onTextChanged(CharSequence s, int arg1, int arg2, int arg3) {
                                if (s.toString().length() > 0) {
                                    endIconShow = true;
                                } else endIconShow = false;

                                mAdapter.getFilter().filter(s);
                                Icons.clear();
                            }
                        });
        var sheet = new BottomSheetDialog(context);
        mAdapter = new IconManagerAdapter();
        mAdapter.setOnIconSelectedListener(
                new IconManagerAdapter.OnIconSelectedListener() {
                    @Override
                    public void onIconSelect(Icons icons, View v) {
                        powerMenu(v, icons);
                    }
                });
        loadIcons();
        re.setAdapter(mAdapter);
        re.setLayoutManager(new GridLayoutManager(context, 4));
        sheet.setContentView(view);
        sheet.show();
    }

    private void loadIcons() {
        nullview.setVisibility(VISIBLE);
        re.setVisibility(GONE);
        Executors.newSingleThreadExecutor()
                .execute(
                        () -> {
                            File iconDir = new File(path);
                            if (iconDir.exists()) {

                            } else {
                                iconDir.mkdirs();
                            }
                            File[] files = iconDir.listFiles(File::isFile);
                            if (files != null) {
                                // sort by in lastModified
                                Arrays.sort(files, Comparator.comparingLong(File::lastModified));
                                for (File file : files) {

                                    if (file.exists()) {
                                        /// winter class #Icons model and select #File
                                        Icons icon = new Icons(new File(file.getAbsolutePath().replaceAll("%20", " ")));
                                        /// lock getRootFile == Path
                                        if (icon.getRootFile().getName().endsWith(".svg")) {
                                            Icons.add(icon);
                                        }
                                        // dont using xml mode library svg loader not render vector mode ...
                                        if (icon.getRootFile().getName().endsWith(".xml")) {
                                            Icons.add(icon);
                                        }
                                    }
                                }
                            }

                            ThreadUtils.runOnUiThread(
                                    () -> {
                                        mAdapter.submitList(Icons);
                                        nullview.setVisibility(GONE);
                                        re.setVisibility(VISIBLE);
                                    });
                        });
    }

    protected void powerMenu(View v, Icons icon) {
        var menu =
                new PowerMenu.Builder(context)
                        .setIsMaterial(true)
                        .addItem(new PowerMenuItem("Convert to Vector"))
                        .addItem(new PowerMenuItem("Convert to Image"))
                        .addItem(new PowerMenuItem("Copy in project"))
                        .setMenuRadius(22f)
                        .setMenuColor(MaterialColors.getColor(context, ColorAndroid12.Back, 0))
                        .setTextColor(MaterialColors.getColor(context, ColorAndroid12.colorOnSurface, 0))
                        .setAutoDismiss(true)
                        .setShowBackground(false)
                        .build();
        menu.setOnMenuItemClickListener(
                (pos, __) -> {
                    switch (pos) {
                        case 0:
                            break;
                    }
                });

        menu.showAsDropDown(v);
    }
}
