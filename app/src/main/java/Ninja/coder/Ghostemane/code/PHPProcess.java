package Ninja.coder.Ghostemane.code;

import Ninja.coder.Ghostemane.code.utils.PhpRun;
import android.app.Service;
import android.content.Intent;
import android.os.IBinder;

import java.io.File;

public final class PHPProcess extends Service {
    private boolean isStarted;

    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }

    public final boolean isStarted() {
        return this.isStarted;
    }

    public final void setStarted(boolean z) {
        this.isStarted = z;
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {

        if (this.isStarted) {
            return super.onStartCommand(intent, flags, startId);
        }
        this.isStarted = true;
        int port = intent.getIntExtra("port", -1);
        String projectPath = intent.getStringExtra("projectPath");
        File libFile = new File(getApplicationInfo().nativeLibraryDir, "libphp-8.2.8.so");
        File targetDir = new File(getFilesDir().getPath() + "/lib");
        File errorFile = new File(getFilesDir(), "php_error");
        File pidFile = new File(getFilesDir(), "php_pid");
        File iniFile = new File(getFilesDir(), "php.ini");
        PhpRun run = new PhpRun();
        run.runPHPProcess(libFile, targetDir, iniFile, new File(projectPath), 8080, projectPath);
        return super.onStartCommand(intent, flags, startId);
    }
}
