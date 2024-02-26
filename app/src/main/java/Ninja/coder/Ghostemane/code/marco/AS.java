package Ninja.coder.Ghostemane.code.marco;

import android.widget.TextView;
import com.google.android.material.color.MaterialColors;
import com.mcal.apksigner.ApkSigner;
import java.io.File;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.Files;
import android.graphics.Color;
import java.io.IOException;
import android.util.Log;
import com.google.android.material.R;


public class AS {
  
  public static final String THEMEPATH = "/storage/emulated/0/GhostWebIDE/theme/";
  public static void runs(String input, CallBack back) {

    var signer = new ApkSigner(new File(input), new File(getOutputFileName(input)));
    signer.setUseDefaultSignatureVersion(false);
    signer.setV1SigningEnabled(true);
    signer.setV2SigningEnabled(true);
    signer.setV3SigningEnabled(true);
    signer.setV4SigningEnabled(false);
    signer.signDebug();
    back.end();
  }

  private static String getOutputFileName(String inputFileName) {
    int extensionIndex = inputFileName.lastIndexOf(".");
    if (extensionIndex != -1) {
      return inputFileName.substring(0, extensionIndex) + "_signedbyGhostWeb.apk";
    } else {
      return inputFileName + "_signedbyGhostWeb.apk";
    }
  }

  public static interface CallBack {
    void end();
  }

  public static void main() {
    AS.runs(
        "",
        new AS.CallBack() {

          @Override
          public void end() {
            ///
          }
        });
  }
  
  
  @Deprecated
  public static void SAF(String directoryPath, String typeName ,TextView view) {
    
  }
  
}
