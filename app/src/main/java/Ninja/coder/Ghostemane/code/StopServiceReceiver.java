package Ninja.coder.Ghostemane.code;

import android.app.Service;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.widget.Toast;

public class StopServiceReceiver extends BroadcastReceiver {
    @Override
    public void onReceive(Context context, Intent intent){
        if (Utils.isMyServiceRunning(context, DownloadService.class)){
            DownloadService.downloadAllowed = false;
            context.stopService(new Intent(context, DownloadService.class));
        }
    }
}
