package Ninja.coder.Ghostemane.code;

import Ninja.coder.Ghostemane.code.GlideUtilApi.GlideCompat;
import android.widget.ImageView;

public class VectorCompatShow {
    public static void run(String input, ImageView imageView) {

        if (input.endsWith(".xml")) {
            GlideCompat.LoadVector(input,imageView);
        }
    }
}
