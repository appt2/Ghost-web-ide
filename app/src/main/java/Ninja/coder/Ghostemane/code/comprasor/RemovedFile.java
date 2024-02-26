package Ninja.coder.Ghostemane.code.comprasor;

import Ninja.coder.Ghostemane.code.ColorAndroid12;
import Ninja.coder.Ghostemane.code.tasks.AsyncTaskCompat;
import Ninja.coder.Ghostemane.code.tasks.app.ProgressDialogCompat;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.res.ColorStateList;
import android.graphics.drawable.Drawable;
import com.google.android.material.shape.CornerFamily;
import com.google.android.material.shape.MaterialShapeDrawable;
import com.google.android.material.shape.ShapeAppearanceModel;
import java.io.File;

public class RemovedFile extends AsyncTaskCompat<File, File, Void> {

	private ProgressDialogCompat dialog;
	private Context context;

	private OnRemoveCompleteListener onCompleteListener;

	public RemovedFile(Context context, OnRemoveCompleteListener onCompleteListener) {
		this.context = context;
		this.onCompleteListener = onCompleteListener;
	}

	@Override
	protected void onPreExecute() {
		super.onPreExecute();
		dialog = new ProgressDialogCompat(context, ProgressDialog.THEME_DEVICE_DEFAULT_DARK);
		dialog.setTitle("Removing File");
		dialog.setMessage("Removed :");
		
		dialog.setProgressStyle(ProgressDialogCompat.STYLE_HORIZONTAL);
		dialog.setMax(100);
		dialog.setCanceledOnTouchOutside(false);
		dialog.setCancelable(false);
		MaterialShapeDrawable shap = new MaterialShapeDrawable(
				ShapeAppearanceModel.builder().setAllCorners(CornerFamily.CUT, 20f).build());
		shap.setStroke(1f, ColorAndroid12.Back);
		shap.setFillColor(ColorStateList.valueOf(ColorAndroid12.FabBack));
		dialog.getWindow().getDecorView().setBackground((Drawable) shap);
		
        
		dialog.show();

	}

	@Override
	protected Void doInBackground(File... files) {
		for (File file : files) {
			if (file.isDirectory()) {
				deleteRecursive(file);
			} else {
				file.delete();
			}
		}
		return null;
	}

	@Override
	protected void onProgressUpdate(File... values) {
		super.onProgressUpdate(values);
		File file = values[0];
		String path = file.getParent() + " " + file.getName();
		dialog.setMessage("Removed : " + path);
		for (int a = 0; a < (int) file.length(); a++) {
			int dataBase = a;
			dialog.setProgress(dataBase);
		}
	}

	@Override
	protected void onPostExecute(Void aVoid) {
		super.onPostExecute(aVoid);
		dialog.dismiss();
		onCompleteListener.onRemoveComplete();
	}

	private void deleteRecursive(File fileOrDirectory) {
		if (fileOrDirectory.isDirectory()) {
			for (File child : fileOrDirectory.listFiles()) {
				deleteRecursive(child);
			}
		}

		float parentLength = fileOrDirectory.getParentFile().length();
		int percent;
		if (parentLength != 0) {
			percent = (int) ((float) (fileOrDirectory.length() / parentLength) * 100);
		} else {
			percent = 0;
		}

		publishProgress(fileOrDirectory);
		fileOrDirectory.delete();

	}

	@Override
	protected void onCancelled() {
		super.onCancelled();
		dialog.setOnCancelListener(dialog -> {
			cancel(true);
			dialog.dismiss();
		});
	}

	public interface OnRemoveCompleteListener {
		void onRemoveComplete();
	}

}