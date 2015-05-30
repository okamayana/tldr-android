package ok.tldr.android.util;

import android.content.Context;
import android.graphics.Point;
import android.view.Display;
import android.view.WindowManager;

public class ViewUtil {

    public static float getDisplayWidth(final Context context) {
        final WindowManager windowManager = (WindowManager) context.getSystemService(Context.WINDOW_SERVICE);
        final Display display = windowManager.getDefaultDisplay();

        final Point size = new Point();
        display.getSize(size);

        return size.x;
    }
}
