package Ninja.coder.Ghostemane.code.FileHelper;

import Ninja.coder.Ghostemane.code.R;
import android.content.Context;
import android.content.DialogInterface;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import androidx.appcompat.app.AlertDialog;
import com.google.android.material.dialog.MaterialAlertDialogBuilder;
import com.google.android.material.shape.CornerFamily;
import com.google.android.material.shape.ShapeAppearanceModel;
import com.google.android.material.textfield.TextInputLayout;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.Files;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class CreatorModule {
  private Context context;
  private AlertDialog dialog;
  protected Spinner spinner;
  protected String folder;
  protected OnCallBack call;
  protected TextInputLayout inputLayout;
  protected List<String> str = new ArrayList<>();

  public CreatorModule(Context context, String folder
    ,OnCallBack call) {
    this.context = context;
    this.folder = folder;
    this.call = call;
    str.add("None");
    str.add("ModuleJava");
    str.add("ModuleAndroid");
    str.add("ModuleAndroidKts");
    dialog =
        new MaterialAlertDialogBuilder(context)
            .setTitle("CreatorModule")
            .setPositiveButton("ok", null)
            .setView(R.layout.__layout_java_module)
            .create();
    dialog.setOnShowListener(
        v -> {
          var btn = dialog.getButton(DialogInterface.BUTTON_POSITIVE);
          inputLayout = dialog.findViewById(R.id.input_ma);
          spinner = dialog.findViewById(R.id.sp1_main);
          spinner.setAdapter(
              new ArrayAdapter<String>(
                  context, android.R.layout.simple_spinner_dropdown_item, str));
          ((ArrayAdapter) spinner.getAdapter()).notifyDataSetChanged();
          var myShap = new ShapeAppearanceModel.Builder();
          myShap.setAllCorners(CornerFamily.CUT, 20f);
          inputLayout.setShapeAppearanceModel(myShap.build());
          inputLayout.setPadding(8, 8, 8, 8);
          inputLayout.setHintEnabled(true);
          inputLayout.setHint("CreatorModule");
          inputLayout
              .getEditText()
              .addTextChangedListener(
                  new TextWatcher() {

                    @Override
                    public void afterTextChanged(Editable arg0) {}

                    @Override
                    public void beforeTextChanged(
                        CharSequence arg0, int arg1, int arg2, int arg3) {}

                    @Override
                    public void onTextChanged(CharSequence s, int arg1, int arg2, int arg3) {
                      btn.setEnabled(s.toString().isEmpty() ? false : true);
                      spinner.setEnabled(s.toString().isEmpty() ? false : true);
                    }
                  });

          btn.setOnClickListener(
              vc -> {
                if(call != null) {
                	call.reload();
                }
                dialog.dismiss();
              });
          spinner.setOnItemSelectedListener(
              new AdapterView.OnItemSelectedListener() {
                @Override
                public void onItemSelected(AdapterView<?> ad, View m, int pp, long vv) {

                  switch (pp) {
                    case 0:
                      break;
                    case 1:
                        makeModuleJava();
                        break;
                    case 2:
                      makeModuleAndroid();
                      break;
                    case 3:
                      makeModuleAndroidKts();
                      break;
                  }
                }

                @Override
                public void onNothingSelected(AdapterView<?> arg0) {}
              });
        });
    dialog.show();
  }

  public void makeModuleJava() {
    Path moduleFolder = Paths.get(folder, inputLayout.getEditText().getText().toString());
    try {
      Files.createDirectories(moduleFolder);

      String buildGradleContent =
          "plugins {\n"
              + "    id 'java-library'\n"
              + "}\n\n"
              + "dependencies {\n"
              + "    //add your dependencies\n"
              + "}\n\n"
              + "java {\n"
              + "    sourceCompatibility = JavaVersion.VERSION_17\n"
              + "    targetCompatibility = JavaVersion.VERSION_17\n"
              + "}\n";

      String androidManifestContent =
          "<?xml version=\"1.0\" encoding=\"utf-8\"?>\n"
              + "<manifest xmlns:android=\"http://schemas.android.com/apk/res/android\">\n"
              + "</manifest>\n";
      String mainJavaContent =
          "public class Main {\n"
              + "    public static void main(String[] args) {\n"
              + "        // Your Java code here\n"
              + "    }\n"
              + "}\n";

      Files.write(moduleFolder.resolve("build.gradle"), buildGradleContent.getBytes());
      Files.write(moduleFolder.resolve(".gitignore"), "/build".getBytes());
      Files.createDirectories(moduleFolder.resolve("src/main/java"));
      Files.write(moduleFolder.resolve("src/main/java/Main.java"), mainJavaContent.getBytes());
      Files.write(
          moduleFolder.resolve("src/main/AndroidManifest.xml"), androidManifestContent.getBytes());
    } catch (IOException e) {
      e.printStackTrace();
    }
  }

  public void makeModuleAndroid() {
    Path moduleFolder = Paths.get(folder, inputLayout.getEditText().getText().toString());
    try {
      Files.createDirectories(moduleFolder);

      String buildGradleContent =
          "plugins {\n"
              + "    id 'com.android.library'\n"
              + "}\n\n"
              + "android {\n"
              + "    compileSdk 34\n"
              + "    defaultConfig {\n"
              + "        minSdkVersion 21\n"
              + "        targetSdkVersion 34\n"
              + "    }\n"
              + "    buildTypes {\n"
              + "        release {\n"
              + "            minifyEnabled false\n"
              + "            proguardFiles getDefaultProguardFile('proguard-android-optimize.txt'), 'proguard-rules.pro'\n"
              + "        }\n"
              + "    }\n"
              + "}\n"
              + "dependencies {\n"
              + "    //add your dependencies\n"
              + "}\n";

      String androidManifestContent =
          "<?xml version=\"1.0\" encoding=\"utf-8\"?>\n"
              + "<manifest xmlns:android=\"http://schemas.android.com/apk/res/android\"\n"
              + "</manifest>\n";
      String mainJavaContent =
          "package $YourApp;\n\n\nimport androidx.core.app.ActivityCompat;\n\npublic class MainActivity extends AppCompatActivity {\n\n  @Override\n  protected void onCreate(Bundle _savedInstanceState) {\n    super.onCreate(_savedInstanceState);\n    setContentView($YourApp);\n \n  }\n}\n";

      Files.write(moduleFolder.resolve("build.gradle"), buildGradleContent.getBytes());
      Files.write(moduleFolder.resolve(".gitignore"), "/build".getBytes());
      Files.createDirectories(moduleFolder.resolve("src/main/java/com/example/app"));
      Files.write(
          moduleFolder.resolve("src/main/java/com/example/app/MainActivity.java"), mainJavaContent.getBytes());
      Files.write(
          moduleFolder.resolve("src/main/AndroidManifest.xml"), androidManifestContent.getBytes());
    } catch (IOException e) {
      e.printStackTrace();
    }
  }

  public void makeModuleAndroidKts() {
    Path moduleFolder = Paths.get(folder, inputLayout.getEditText().getText().toString());
    try {
      Files.createDirectories(moduleFolder);

      String buildGradleContent =
          "plugins {\n"
              + "    id('com.android.library')\n"
              + "}\n\n"
              + "android {\n"
              + "    compileSdk = 34\n"
              + "    defaultConfig {\n"
              + "        minSdk = 21\n"
              + "        targetSdk = 34\n"
              + "    }\n"
              + "    buildTypes {\n"
              + "        getByName(\"release\") {\n"
              + "            isMinifyEnabled = false\n"
              + "            proguardFiles(getDefaultProguardFile('proguard-android-optimize.txt'), file('proguard-rules.pro'))\n"
              + "        }\n"
              + "    }\n"
              + "}\n"
              + "dependencies {\n"
              + "    //add your dependencies\n"
              + "}\n";

      String androidManifestContent =
          "<?xml version=\"1.0\" encoding=\"utf-8\"?>\n"
              + "<manifest xmlns:android=\"http://schemas.android.com/apk/res/android\"\n"
              + "</manifest>\n";
      String mainKotlinContent =
          "package $YourApp;\n\n\nimport androidx.core.app.ActivityCompat;\n\n class MainActivity : AppCompatActivity() {\n\n  override fun onCreate(savedInstanceState: Bundle?) {\n    super.onCreate(savedInstanceState)\n    setContentView($YourApp)\n \n val a = 20 \n println(a) }\n}\n";

      Files.write(moduleFolder.resolve("build.gradle.kts"), buildGradleContent.getBytes());
      Files.write(moduleFolder.resolve(".gitignore"), "/build".getBytes());
      Files.createDirectories(moduleFolder.resolve("src/main/java/com/example/app"));
      Files.write(
          moduleFolder.resolve("src/main/java/com/example/app/MainActivity.kt"), mainKotlinContent.getBytes());
      Files.write(
          moduleFolder.resolve("src/main/AndroidManifest.xml"), androidManifestContent.getBytes());
    } catch (IOException e) {
      e.printStackTrace();
    }
  }
  public interface OnCallBack{
    public void reload();
  }
}
