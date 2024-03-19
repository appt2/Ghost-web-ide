package Ninja.coder.Ghostemane.code.model;

import Ninja.coder.Ghostemane.code.adapter.ZipListFileShowAd;
import android.content.Context;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import com.google.android.material.dialog.MaterialAlertDialogBuilder;
import net.lingala.zip4j.ZipFile;
import net.lingala.zip4j.model.FileHeader;
import net.lingala.zip4j.progress.ProgressMonitor;

import java.io.File;
import java.util.List;

public class ZipFileShow {

    public static void showAsDialog(Context context, String Folder) {
        var tv = new RecyclerView(context);

        var param =
                new RecyclerView.LayoutParams(
                        RecyclerView.LayoutParams.MATCH_PARENT, RecyclerView.LayoutParams.MATCH_PARENT);
        tv.setLayoutParams(param);
        try {
            List<FileHeader> listModel = new ZipFile(Folder).getFileHeaders();
            tv.setAdapter(new ZipListFileShowAd(listModel));
            tv.setLayoutManager(
                    new LinearLayoutManager(context, LinearLayoutManager.VERTICAL, false));
        } catch (Exception err) {
            err.printStackTrace();
        }
        var dialog = new MaterialAlertDialogBuilder(context);
        dialog.setTitle("ZipFile Show");
        dialog.setView(tv);
        dialog.setPositiveButton(android.R.string.ok, null);
        dialog.show();
    }

    public static void zip(String input, String output, CallBackZip callbackzip) throws Exception {
        File fileinput = new File(input);
        ZipFile zipFile = new ZipFile(output);
        ProgressMonitor progress = zipFile.getProgressMonitor();
        zipFile.setRunInThread(true);

        if (fileinput.isDirectory()) {
            zipFile.addFolder(fileinput);
        } else if (fileinput.isFile()) {
            zipFile.addFile(fileinput);
        }

        while (!progress.getState().equals(ProgressMonitor.State.READY)) {
            callbackzip.PercentDone(progress.getPercentDone());
            callbackzip.FileName(progress.getFileName());
            // Avoid using Thread.sleep() in production code
            Thread.sleep(3000);
        }

        if (progress.getResult().equals(ProgressMonitor.Result.SUCCESS)) {
            callbackzip.Done();
        } else if (progress.getResult().equals(ProgressMonitor.Result.ERROR)) {
            callbackzip.Error(progress.getException().getMessage());
        } else if (progress.getResult().equals(ProgressMonitor.Result.CANCELLED)) {
            callbackzip.Cancelled();
        }
    }

    public static void main(String[] args) {
        String inputFilePath = "input.txt";
        String outputZipFilePath = "output.zip";

        try {
            zip(
                    inputFilePath,
                    outputZipFilePath,
                    new CallBackZip() {
                        @Override
                        public void PercentDone(int percentDone) {
                            System.out.println("Percent Done: " + percentDone + "%");
                        }

                        @Override
                        public void FileName(String fileName) {
                            System.out.println("Current File: " + fileName);
                        }

                        @Override
                        public void Done() {
                            System.out.println("Compression Done!");
                        }

                        @Override
                        public void Error(String message) {
                            System.out.println("Error: " + message);
                        }

                        @Override
                        public void Cancelled() {
                            System.out.println("Compression Cancelled!");
                        }
                    });
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static interface CallBackZip {
        public void PercentDone(int percentDone);

        public void FileName(String fileName);

        public void Done();

        public void Error(String message);

        public void Cancelled();
    }
}
