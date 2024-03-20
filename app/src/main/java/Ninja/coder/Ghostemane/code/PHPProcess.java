package Ninja.coder.Ghostemane.code;

import android.app.Service;
import android.content.Intent;
import android.os.IBinder;
import android.os.Process;
import java.io.BufferedOutputStream;
import java.io.BufferedReader;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import kotlin.jvm.internal.Intrinsics;
import kotlin.text.StringsKt;

public final class PHPProcess extends Service {
  private boolean isStarted;

  @Override
  public IBinder onBind(Intent intent) {
    return null;
  }

  public final boolean isStarted() {
    return this.isStarted;
  }

  public final void setStarted(boolean started) {
    this.isStarted = started;
  }

  @Override
  public int onStartCommand(Intent intent, int flags, int startId) {
    Intrinsics.checkNotNullParameter(intent, "intent");
    if (this.isStarted) {
      return super.onStartCommand(intent, flags, startId);
    } else {
      this.isStarted = true;
      int port = intent.getIntExtra("port", -1);
      String projectPath = intent.getStringExtra("projectPath");
      File phpLibrary = new File(getApplicationInfo().nativeLibraryDir, "libphp-8.2.8.so");
      File libraryDir = new File(getFilesDir().getPath() + "/lib");
      File errorDir = new File(getFilesDir(), "php_error");
      File pidFile = new File(getFilesDir(), "php_pid");
      File iniFile = new File(getFilesDir(), "php.ini");

      errorDir.delete();
      try {
        pidFile.createNewFile();
      } catch (IOException err) {
        err.printStackTrace();
      }

      try {
        write(String.valueOf(Process.myPid()), pidFile);
      } catch (IOException err) {
        err.printStackTrace();
      }

      new Thread(
              () -> {
                startPHPProcess(libraryDir, phpLibrary, port, projectPath, iniFile, errorDir, this);
              })
          .start();

      return super.onStartCommand(intent, flags, startId);
    }
  }

  private static void startPHPProcess(
      File libraryDir,
      File phpLibrary,
      int port,
      String projectPath,
      File iniFile,
      File errorDir,
      PHPProcess phpProcess) {

    try {
      String processOutput =
          runCommand(
              new String[] {
                "/system/bin/sh",
                "-c",
                "export LD_LIBRARY_PATH=\""
                    + libraryDir.getPath()
                    + "\" && \""
                    + phpLibrary.getPath()
                    + "\" -S 0.0.0.0:"
                    + port
                    + " -t \""
                    + projectPath
                    + "\" -c \""
                    + iniFile.getPath()
                    + "\""
              });

      if (StringsKt.startsWith(processOutput, "Error : ", false)) {
        errorDir.createNewFile();
        write(processOutput, errorDir);
        phpProcess.stopSelf();
      }
    } catch (Exception e) {
      e.printStackTrace();
    }
  }

  public static void write(String str, File file) throws IOException {
    BufferedOutputStream bufferedOutputStream =
        new BufferedOutputStream(new FileOutputStream(file));
    bufferedOutputStream.write(str.getBytes(), 0, str.getBytes().length);
    bufferedOutputStream.flush();
    bufferedOutputStream.close();
  }

  public static void write(InputStream inputStream, File file) throws IOException {
    BufferedOutputStream bufferedOutputStream =
        new BufferedOutputStream(new FileOutputStream(file));
    byte[] readStream = readStream(inputStream);
    bufferedOutputStream.write(readStream, 0, readStream.length);
    bufferedOutputStream.flush();
    bufferedOutputStream.close();
  }

  public static void write(byte[] bArr, File file) throws IOException {
    BufferedOutputStream bufferedOutputStream =
        new BufferedOutputStream(new FileOutputStream(file));
    bufferedOutputStream.write(bArr, 0, bArr.length);
    bufferedOutputStream.flush();
    bufferedOutputStream.close();
  }

  public static byte[] readStream(InputStream inputStream) throws IOException {
    byte[] bArr = new byte[1024];
    ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
    while (true) {
      int read = inputStream.read(bArr);
      if (read != -1) {
        byteArrayOutputStream.write(bArr, 0, read);
      } else {
        inputStream.close();
        byteArrayOutputStream.close();
        return byteArrayOutputStream.toByteArray();
      }
    }
  }

  public static String runCommand(String[] strArr) throws Exception {
    java.lang.Process runCommandAndGetProcess = runCommandAndGetProcess(strArr);
    runCommandAndGetProcess.waitFor();
    BufferedReader bufferedReader =
        new BufferedReader(new InputStreamReader(runCommandAndGetProcess.getInputStream()));
    String str = "";
    while (true) {
      String readLine = bufferedReader.readLine();
      if (readLine == null) {
        break;
      }
      str = str + readLine + "\n";
    }
    if (!str.equals("")) {
      return str;
    }
    BufferedReader bufferedReader2 =
        new BufferedReader(new InputStreamReader(runCommandAndGetProcess.getErrorStream()));
    while (true) {
      String readLine2 = bufferedReader2.readLine();
      if (readLine2 == null) {
        break;
      }
      str = str + readLine2 + "\n";
    }
    return !str.equals("") ? "Error: " + str : str;
  }

  public static java.lang.Process runCommandAndGetProcess(String[] strArr) throws Exception {
    return Runtime.getRuntime().exec(strArr);
  }
}
