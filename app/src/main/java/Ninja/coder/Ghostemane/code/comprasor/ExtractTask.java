package Ninja.coder.Ghostemane.code.comprasor;

import Ninja.coder.Ghostemane.code.ColorAndroid12;
import Ninja.coder.Ghostemane.code.tasks.AsyncTaskCompat;
import Ninja.coder.Ghostemane.code.tasks.app.ProgressDialogCompat;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.res.ColorStateList;
import android.graphics.drawable.GradientDrawable;
import android.net.Uri;
import com.google.android.material.shape.CornerFamily;
import com.google.android.material.shape.MaterialShapeDrawable;
import com.google.android.material.shape.ShapeAppearanceModel;
import org.apache.commons.compress.archivers.sevenz.SevenZArchiveEntry;
import org.apache.commons.compress.archivers.sevenz.SevenZFile;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.util.ArrayList;
import java.util.List;

public class ExtractTask extends AsyncTaskCompat<String, Object, List<File>> {
    private final Context context;
    private ProgressDialogCompat progressDialog;
    private int totalSize = 0;
    private Result result;
    private String outputPath;
    private GradientDrawable backgroundDrawable;
    private String inputPath;

    public ExtractTask(Context context, Result result, String inputPath, String outputPath) {
        this.context = context;
        this.result = result;
        this.inputPath = inputPath;
        this.outputPath = outputPath;
    }

    @Override
    protected void onPreExecute() {
        super.onPreExecute();
        MaterialShapeDrawable shap =
                new MaterialShapeDrawable(
                        ShapeAppearanceModel.builder()
                                .setAllCorners(CornerFamily.CUT, 17f)
                                .build());
        shap.setStrokeColor(ColorStateList.valueOf(ColorAndroid12.Back));
        shap.setStrokeWidth(2f);
        shap.setFillColor(ColorStateList.valueOf(ColorAndroid12.FabBack));
        progressDialog = new ProgressDialogCompat(context, ProgressDialog.THEME_DEVICE_DEFAULT_DARK);
        progressDialog.setTitle("Extracting Files");
        progressDialog.setMessage("Extracting " + lastDir(inputPath));
        progressDialog.setCancelable(false);
        progressDialog.getWindow().getDecorView().setBackground(shap);
        progressDialog.setProgressStyle(ProgressDialog.STYLE_HORIZONTAL);
        progressDialog.show();
    }

    @Override
    protected List<File> doInBackground(String... strings) {
        List<File> extractedFiles = new ArrayList<>();

        try {
            SevenZFile sevenZFile = new SevenZFile(new File(inputPath));
            SevenZArchiveEntry entry;
            while ((entry = sevenZFile.getNextEntry()) != null) {
                totalSize += (int) entry.getSize();
            }
            sevenZFile.close();

            long bytesRead = 0L;
            sevenZFile = new SevenZFile(new File(inputPath));
            byte[] buffer = new byte[4096];
            while ((entry = sevenZFile.getNextEntry()) != null) {
                File file = new File(outputPath + "/" + entry.getName());

                if (entry.isDirectory()) {
                    if (!file.mkdirs()) {
                        throw new Exception(
                                "Could not create directory: " + file.getAbsolutePath());
                    }
                } else {
                    file.getParentFile().mkdirs();
                    BufferedOutputStream bufferedOutputStream =
                            new BufferedOutputStream(new FileOutputStream(file));
                    int count;
                    while ((count = sevenZFile.read(buffer)) > 0) {
                        bufferedOutputStream.write(buffer, 0, count);
                        bytesRead += count;
                        int RoadByte = (int) ((bytesRead * 100) / entry.getSize());
                        publishProgress(RoadByte);
                    }
                    bufferedOutputStream.close();
                    extractedFiles.add(file);
                }
            }
            sevenZFile.close();

        } catch (Exception e) {
            e.printStackTrace();
        }
        return extractedFiles;
    }

    @Override
    protected void onProgressUpdate(Object... values) {
        super.onProgressUpdate(values);
        int progress = (int) values[0];
        progressDialog.setProgress(progress);
    }

    @Override
    protected void onPostExecute(List<File> files) {
        super.onPostExecute(files);
        progressDialog.dismiss();
        result.OnEnd();
        // Do something with the extracted files...
    }

    public interface Result {
        void OnEnd();
    }

    public String lastDir(String last) {
        return Uri.parse(last).getLastPathSegment();
    }
}
