package mod.hey.studios.project.library;

import android.app.Activity;
import androidx.appcompat.app.AlertDialog;
import android.app.ProgressDialog;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Environment;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.view.WindowManager;
import android.widget.TextView;
import android.widget.Toast;
import android.content.Context;
import com.google.gson.Gson;
import com.google.gson.JsonParseException;
import com.sketchware.remod.Resources;
import Ninja.coder.Ghostemane.code.R;
import android.graphics.drawable.ColorDrawable;
import android.view.Window;
import android.graphics.PorterDuff;
import android.graphics.PorterDuff.Mode;
import android.graphics.Color;
import Ninja.coder.Ghostemane.code.LibraryDownloaderUtils;
import android.graphics.drawable.GradientDrawable;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.zip.ZipEntry;
import java.util.zip.ZipInputStream;
import com.google.android.material.dialog.MaterialAlertDialogBuilder;
import a.a.a.bB;
import mod.SketchwareUtil;
import mod.agus.jcoderz.dx.command.dexer.Main;
import mod.agus.jcoderz.lib.FileUtil;
import mod.hey.studios.lib.JarCheck;
import mod.hey.studios.lib.prdownloader.PRDownloader;
import mod.hey.studios.lib.prdownloader.PRDownloader.OnDownloadListener;
import mod.hey.studios.lib.prdownloader.PRDownloader.Status;
import mod.hey.studios.util.Helper;
import android.animation.ObjectAnimator;

//changed in 6.3.0

public class LibraryDownloader {
	
	public static final File CONFIGURED_REPOSITORIES_FILE = new File(Environment.getExternalStorageDirectory(),
	"GhostWebIDE" + File.separator + "ninjacoder" + File.separator + "repositories.json");
	private static final String DEFAULT_REPOSITORIES_FILE_CONTENT = "[{\"url\":\"https://repo.hortonworks.com/content/repositories/releases\",\"name\":\"HortanWorks\"},{\"url\":\"https://maven.atlassian.com/content/repositories/atlassian-public\",\"name\":\"Atlassian\"},{\"url\":\"https://jitpack.io\",\"name\":\"JitPack\"},{\"url\":\"https://jcenter.bintray.com\",\"name\":\"JCenter\"},{\"url\":\"https://oss.sonatype.org/content/repositories/releases\",\"name\":\"Sonatype\"},{\"url\":\"https://repo.spring.io/plugins-release\",\"name\":\"Spring Plugins\"},{\"url\":\"https://repo.spring.io/libs-milestone\",\"name\":\"Spring Milestone\"},{\"url\":\"https://repo.maven.apache.org/maven2\",\"name\":\"Apache Maven\"},{\"url\":\"https://dl.google.com/dl/android/maven2\",\"name\":\"Google Maven\"},{\"url\":\"https://repo1.maven.org/maven2\",\"name\":\"Maven Central\"}]";
	private final String downloadPath;
	private final ArrayList<String> repoUrls = new ArrayList<>();
	private final ArrayList<String> repoNames = new ArrayList<>();
	Context context;
	boolean use_d8;
	private OnCompleteListener listener;
	private AlertDialog dialog;
	private boolean isAarAvailable = false, isAarDownloaded = false;
	private int downloadId;
	private String libName = "";
	private String currentRepo = "";
	private int counter = 0;
	private ArrayList<HashMap<String, Object>> repoMap = new ArrayList<>();
	private ProgressDialog progressDialog;
	private String _AndroidJar;
	private String _ClassPathJar;
	private MaterialAlertDialogBuilder builder;
	
	public LibraryDownloader(Context context, boolean use_d8 , String where , String jar1 , String jar2) {
		this.context = context;
		this.use_d8 = use_d8;
		this._AndroidJar = jar1;
		this._ClassPathJar = jar2;
		
		downloadPath = where;
	}
	
	private static void mkdirs(File file, String str) {
		File file2 = new File(file, str);
		if (!file2.exists()) {
			file2.mkdirs();
		}
	}
	
	private static String dirpart(String str) {
		int lastIndexOf = str.lastIndexOf(File.separatorChar);
		if (lastIndexOf == -1) {
			return null;
		}
		
		return str.substring(0, lastIndexOf);
	}
	
	private static void extractFile(ZipInputStream zipInputStream, File file, String str) throws IOException {
		byte[] bArr = new byte[4096];
		BufferedOutputStream bufferedOutputStream = new BufferedOutputStream(new FileOutputStream(new File(file, str)));
		while (true) {
			int read = zipInputStream.read(bArr);
			
			if (read == -1) {
				bufferedOutputStream.close();
				return;
			}
			
			bufferedOutputStream.write(bArr, 0, read);
		}
	}
	
	public void showDialog(OnCompleteListener listener) {
		this.listener = listener;
		
		builder = new MaterialAlertDialogBuilder(context);
		LayoutInflater inflater = ((androidx.appcompat.app.AppCompatActivity)context).getLayoutInflater();
		View view = inflater.inflate(R.layout.githublibdownlodermain, null);
        builder.setTitle("Write lib dependency");
		final LinearLayout linear1 = view.findViewById(R.id.linear1);
		final LinearLayout progressBarContainer = view.findViewById(R.id.linear3);
		final ProgressBar progressbar1 = view.findViewById(R.id.progressbar1);
		final LinearLayout libraryContainer = view.findViewById(R.id.linear4);
		final TextView message = view.findViewById(R.id.textview3);
		final LinearLayout start = view.findViewById(R.id.linear8);
		final LinearLayout pause = view.findViewById(R.id.linear9);
		final LinearLayout resume = view.findViewById(R.id.linear10);
		final LinearLayout cancel = view.findViewById(R.id.linear11);
		final EditText library = view.findViewById(R.id.edittext1);
		linear1.removeView(progressBarContainer);
		builder.setView(view);
		dialog = builder.show();
		pause.setEnabled(false);
		pause.setVisibility(View.GONE);
		resume.setEnabled(false);
		resume.setVisibility(View.GONE);
		cancel.setEnabled(false);
		cancel.setVisibility(View.GONE);
        dialog.show();
		
		start.setOnClickListener(startView -> {
			String dependency = library.getText().toString();
			
			if (dependency.isEmpty()) {
				bB.a(context, "Dependency can't be empty", 1).show();
			} else if (!dependency.contains(":")) {
				bB.a(context, "Invalid dependency", 1).show();
			} else {
				libName = downloadPath + _getLibName(dependency);
				
				if (!FileUtil.isExistFile(libName)) {
					FileUtil.makeDir(libName);
				}
				
				isAarDownloaded = false;
				isAarAvailable = false;
				
				library.setEnabled(false);
				
				start.setEnabled(false);
				start.setVisibility(View.GONE);
				
				cancel.setEnabled(true);
				cancel.setVisibility(View.VISIBLE);
				
				_getRepository();
				counter = 0;
				currentRepo = repoUrls.get(counter);
				
				downloadId = _download(
				currentRepo.concat(_getAarDownloadLink(dependency)),
				downloadPath,
				_getLibName(dependency + ".zip"),
				library,
				message,
				progressBarContainer,
				libraryContainer,
				start,
				pause,
				resume,
				cancel,
				progressbar1
				);
			}
		});
		
		pause.setOnClickListener(pauseView -> {
			if (PRDownloader.getStatus(downloadId) == Status.RUNNING) {
				PRDownloader.pause(downloadId);
			}
		});
		
		resume.setOnClickListener(resumeView -> {
			if (PRDownloader.getStatus(downloadId) == Status.PAUSED) {
				PRDownloader.resume(downloadId);
			}
		});
		
		cancel.setOnClickListener(cancelView -> {
			PRDownloader.cancel(downloadId);
			library.setEnabled(false);
			dialog.dismiss();
		});
	}
	
	private String _getAarDownloadLink(String str) {
		String[] split = str.split(":");
		String str2 = "/";
		
		for (int i = 0; i < split.length - 1; i++) {
			str2 = str2.concat(split[i].replace(".", "/") + "/");
		}
		
		return str2.concat(split[split.length - 1]).concat("/").concat(_getAarName(str));
	}
	
	private String _getAarName(String str) {
		String[] split = str.split(":");
		return split[split.length - 2] + "-" + split[split.length - 1] + ".aar";
	}
	
	private String _getLibName(String str) {
		String[] split = str.split(":");
		return split[split.length - 2] + "_V_" + split[split.length - 1];
	}
	
	private void _jar2dex(String _path) throws Exception {
		// 6.3.0
		if (use_d8) {
			//File libs = new File(LibraryDownloaderUtils.getLibraryDownloaderJarFolder());
			
			ArrayList<String> cm = new ArrayList<>();
			cm.add("--release");
			cm.add("--intermediate");
			
			cm.add("--lib");
			cm.add(_AndroidJar);
			
			cm.add("--classpath");
			cm.add(_ClassPathJar);
			
			cm.add("--output");
			cm.add(new File(_path).getParentFile().getAbsolutePath());
			
			// Input
			cm.add(_path);
			
			
		} else {
			// 6.3.0 fix2
			Main.clearInternTables();
			
			// dx
			Main.main(new String[]{
				// 6.3.0 fix1
				// "--dex",
				"--debug",
				"--verbose",
				"--multi-dex",
				"--output=" + new File(_path).getParentFile().getAbsolutePath(),
				_path
			});
		}
	}
	
	private void _unZipFile(String str, String str2) {
		try {
			File file = new File(str2);
			ZipInputStream zipInputStream = new ZipInputStream(new FileInputStream(str));
			
			while (true) {
				ZipEntry nextEntry = zipInputStream.getNextEntry();
				
				if (nextEntry == null) {
					zipInputStream.close();
					return;
				}
				
				String name = nextEntry.getName();
				
				if (nextEntry.isDirectory()) {
					mkdirs(file, name);
				} else {
					String dirpart = dirpart(name);
					
					if (dirpart != null) {
						mkdirs(file, dirpart);
					}
					
					extractFile(zipInputStream, file, name);
				}
			}
			
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	private String getLastSegment(String path) {
		return Uri.parse(path).getLastPathSegment();
	}
	
	private String findPackageName(String path, String defaultValue) {
		ArrayList<String> files = new ArrayList<>();
		FileUtil.listDir(path, files);
		
		// Method 1: use manifest
		for (String f : files) {
			if (getLastSegment(f).equals("AndroidManifest.xml")) {
				String content = FileUtil.readFile(f);
				
				Pattern p = Pattern.compile("<manifest.*package=\"(.*?)\"", Pattern.DOTALL);
				Matcher m = p.matcher(content);
				
				if (m.find()) {
					return m.group(1);
				}
			}
		}
		
		// Method 2: screw manifest. use dependency
		if (defaultValue.contains(":")) {
			return defaultValue.split(":")[0];
		}
		
		// Method 3: nothing worked. return empty string (lmao) (yeah lmao)
		return "";
	}
	
	private void deleteUnnecessaryFiles(String path) {
		
		// 6.3.0
		String[] list = {
			"res",
			"classes.dex",
			"classes.jar",
			"config",
			"AndroidManifest.xml",
			"jni",
			"assets",
			"proguard.txt"
		};
		
		List<String> validFiles = new ArrayList<>(Arrays.asList(list));
		ArrayList<String> files = new ArrayList<>();
		FileUtil.listDir(path, files);
		
		for (String f : files) {
			// 6.3.0
			// Skip all dex files
			String p = getLastSegment(f);
			
			if (p.startsWith("classes") && p.endsWith(".dex")) continue;
			if (!validFiles.contains(p)) FileUtil.deleteFile(f);
		}
	}
	
	private int _download(
	final String url,
	final String path,
	final String name,
	
	final EditText library,
	final TextView message,
	final LinearLayout progressBarContainer,
	final LinearLayout libraryContainer,
	
	final LinearLayout start,
	final LinearLayout pause,
	final LinearLayout resume,
	final LinearLayout cancel,
	
	final ProgressBar progressbar1) {
		
		return PRDownloader
		.download(url, path, name)
		.build()
		.setOnStartOrResumeListener(() -> {
			message.setText("کتابخونه پیدا شد در حال دانلود.....");
			library.setEnabled(false);
			libraryContainer.removeAllViews();
			libraryContainer.addView(progressBarContainer);
			
			start.setEnabled(false);
			start.setVisibility(View.GONE);
			
			pause.setEnabled(true);
			pause.setVisibility(View.VISIBLE);
			
			resume.setEnabled(false);
			resume.setVisibility(View.GONE);
			
			cancel.setEnabled(true);
			cancel.setVisibility(View.VISIBLE);
		})
		.setOnPauseListener(() -> {
			message.setText("دانلود متوقف شد..");
			
			start.setEnabled(false);
			start.setVisibility(View.GONE);
			
			pause.setEnabled(false);
			pause.setVisibility(View.GONE);
			
			resume.setEnabled(true);
			resume.setVisibility(View.VISIBLE);
			
			cancel.setEnabled(true);
			cancel.setVisibility(View.VISIBLE);
		})
		.setOnCancelListener(() -> {
			library.setEnabled(true);
			
			start.setEnabled(true);
			start.setVisibility(View.VISIBLE);
			
			pause.setEnabled(false);
			pause.setVisibility(View.GONE);
			
			resume.setEnabled(false);
			resume.setVisibility(View.GONE);
			
			cancel.setEnabled(false);
			cancel.setVisibility(View.GONE);
		})
		.setOnProgressListener(progress -> {
			int progressPercent = (int) (progress.currentBytes * 100 / progress.totalBytes);
			progressbar1.setProgress(progressPercent);
		})
		.start(new OnDownloadListener() {
			@Override
			public void onDownloadComplete() {
				isAarAvailable = true;
				isAarDownloaded = true;
				
				StringBuilder path2 = new StringBuilder();
				path2.append(downloadPath);
				path2.append(_getLibName(library.getText().toString()).concat(".zip"));
				
				if (isAarDownloaded && isAarAvailable) {
					_unZipFile(path2.toString(), libName);
					if (FileUtil.isExistFile(libName.concat("/classes.jar"))) {
						if (use_d8 || JarCheck.checkJar(libName.concat("/classes.jar"), 44, 51)) {
							message.setText("پایان دانلود....");
							
							String[] test = new String[]{libName.concat("/classes.jar")};
							new BackTask().execute(test);
							FileUtil.deleteFile(path2.toString());
							
							FileUtil.writeFile(libName + "/config", findPackageName(libName + "/", library.getText().toString()));
							
							deleteUnnecessaryFiles(libName + "/");
							
						} else {
							message.setText("This jar is not supported by Dx since Dx only supports up to Java 1.7. In order to proceed, you need to switch to D8 (if your Android version is 8+)");
							FileUtil.deleteFile(path2.toString());
						}
					} else {
						message.setText("Library doesn't contain a jar file.");
						FileUtil.deleteFile(path2.toString());
					}
				}
				
				library.setEnabled(true);
				start.setEnabled(true);
				start.setVisibility(View.VISIBLE);
				
				pause.setEnabled(false);
				pause.setVisibility(View.GONE);
				
				resume.setEnabled(false);
				resume.setVisibility(View.GONE);
				
				cancel.setEnabled(false);
				cancel.setVisibility(View.GONE);
			}
			
			@Override
			public void onError(PRDownloader.Error e) {
				if (e.isServerError()) {
					if (!(isAarDownloaded || isAarAvailable)) {
						if (counter < repoUrls.size()) {
							currentRepo = repoUrls.get(counter);
							String name = repoNames.get(counter);
							
							counter++;
							message.setText("جستجو کردن... " + counter + "/" + repoUrls.size() + " [" + name + "]");
							
							downloadId = _download(
							currentRepo + _getAarDownloadLink(library.getText().toString()),
							downloadPath,
							_getLibName(library.getText().toString()) + ".zip",
							library,
							message,
							progressBarContainer,
							libraryContainer,
							start,
							pause,
							resume,
							cancel,
							progressbar1
							);
							
						} else {
							FileUtil.deleteFile(libName);
							message.setText("این کتابخانه در این ریپازانتوری پیدا  نشد");
							library.setEnabled(true);
							start.setEnabled(true);
							start.setVisibility(View.VISIBLE);
							
							pause.setEnabled(false);
							pause.setVisibility(View.GONE);
							
							resume.setEnabled(false);
							resume.setVisibility(View.GONE);
							
							cancel.setEnabled(false);
							cancel.setVisibility(View.GONE);
						}
					}
				} else {
					if (e.isConnectionError()) {
						message.setText("دانلود شکست خورد اینترنت را برسی کنید یا vpn");
						library.setEnabled(true);
						start.setEnabled(true);
						start.setVisibility(View.VISIBLE);
						
						pause.setEnabled(false);
						pause.setVisibility(View.GONE);
						
						resume.setEnabled(false);
						resume.setVisibility(View.GONE);
						
						cancel.setEnabled(false);
						cancel.setVisibility(View.GONE);
						
					}
				}
			}
		});
	}
	
	private void _getRepository() {
		repoUrls.clear();
		repoMap.clear();
		repoNames.clear();
		counter = 0;
		
		readRepositories:
		{
			String repositories;
			if (CONFIGURED_REPOSITORIES_FILE.exists() && !(repositories = FileUtil.readFile(CONFIGURED_REPOSITORIES_FILE.getAbsolutePath())).isEmpty()) {
				try {
					repoMap = new Gson().fromJson(repositories, Helper.TYPE_MAP_LIST);
					
					if (repoMap != null) {
						break readRepositories;
					}
				} catch (JsonParseException ignored) {
					// fall-through to shared error toast
				}
				
				bB.a(context, "Custom Repositories configuration file couldn't be read from. Using default repositories for now", 1).show();
			} else {
				FileUtil.writeFile(CONFIGURED_REPOSITORIES_FILE.getAbsolutePath(), DEFAULT_REPOSITORIES_FILE_CONTENT);
			}
			
			repoMap = new Gson().fromJson(DEFAULT_REPOSITORIES_FILE_CONTENT, Helper.TYPE_MAP_LIST);
		}
		
		for (HashMap<String, Object> configuration : repoMap) {
			Object repoUrl = configuration.get("url");
			
			if (repoUrl instanceof String) {
				Object repoName = configuration.get("name");
				
				if (repoName instanceof String) {
					repoUrls.add((String) repoUrl);
					repoNames.add((String) repoName);
				}
			}
			
			counter++;
		}
	}
	
	public interface OnCompleteListener {
		void onComplete();
	}
	
	private class BackTask extends AsyncTask<String, String, String> {
		boolean success = false;
		
		@Override
		protected void onPreExecute() {
			super.onPreExecute();
			GradientDrawable vors = new GradientDrawable();
			vors.setColor(0xFF1F1B1C);
			vors.setCornerRadius(15);
			vors.setStroke(1, 0xFFFDA893);
			progressDialog = new ProgressDialog(context ,ProgressDialog.THEME_DEVICE_DEFAULT_DARK);
			progressDialog.getWindow().setBackgroundDrawable(vors);
			progressDialog.setTitle("صبر کنید");
			progressDialog.setMessage((use_d8 ? "D8" : "Dx") + " is running...");
			progressDialog.setCancelable(false);
			progressDialog.show();
		}
		
		@Override
		protected String doInBackground(String... params) {
			try {
				_jar2dex(params[0]);
				success = true;
			} catch (Exception e) {
				success = false;
				return e.toString();
			}
			
			return "true";
		}
		
		@Override
		protected void onPostExecute(String s) {
			if (success) {
				bB.a(context, "The library has been downloaded and imported to local libraries successfully.", 1).show();
				listener.onComplete();
			} else {
				bB.a(context, "Dexing failed: " + s, 1).show();
			}
			
			if (dialog != null && dialog.isShowing()) {
				dialog.dismiss();
			}
			
			progressDialog.dismiss();
		}
	}
}
