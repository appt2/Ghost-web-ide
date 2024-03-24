package Ninja.coder.Ghostemane.code.activities;

import Ninja.coder.Ghostemane.code.R;
import Ninja.coder.Ghostemane.code.utils.ColorAndroid12;
import Ninja.coder.Ghostemane.code.utils.TerminalUtils;
import android.os.Bundle;
import androidx.appcompat.widget.Toolbar;
import androidx.coordinatorlayout.widget.CoordinatorLayout;
import com.google.android.material.appbar.AppBarLayout;
import com.termux.view.TerminalView;

public class PytestActivity extends BaseCompat {

    private Toolbar _toolbar;
    private AppBarLayout _app_bar;
    private CoordinatorLayout _coordinator;
    private String strin = "";

    private TerminalView trm;

    @Override
    protected void onCreate(Bundle _savedInstanceState) {
        super.onCreate(_savedInstanceState);
        setContentView(R.layout.pytest);
        initialize(_savedInstanceState);
        initializeLogic();
    }

    private void initialize(Bundle _savedInstanceState) {
        _app_bar = findViewById(R.id._app_bar);
        _coordinator = findViewById(R.id._coordinator);
        _toolbar = findViewById(R.id._toolbar);
        setSupportActionBar(_toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setHomeButtonEnabled(true);
        _toolbar.setNavigationOnClickListener(
                (v) -> {
                    finish();
                });
        trm = findViewById(R.id.trm);
    }

    private void initializeLogic() {
        ColorAndroid12.setToolbarinit(_toolbar);
        if (getIntent().hasExtra("path")) {
            String i = getIntent().getStringExtra("path");
            if (i != null) {
                TerminalUtils.RunPythonCode(trm, this, i);
            }
            setTitle("Python3 CodeRuner");
        } else {
            if (getIntent().hasExtra("pathCpp")) {
                String i2 = getIntent().getStringExtra("pathCpp");
                if (i2 != null) {
                    TerminalUtils.RunCppCode(trm, this, i2);
                }
                setTitle("Cpp CodeRuner");
            } else {
                
            }
        }
    }
}
