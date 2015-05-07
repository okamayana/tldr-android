package ok.tldr.android.util;

import android.app.Activity;
import android.app.Fragment;
import android.app.FragmentManager;
import android.view.ViewGroup;
import android.widget.FrameLayout;

public class TestActivityUtil {

    public static void addFragmentToActivity(final Fragment fragment, final Activity activity) {
        final FrameLayout contentView = new FrameLayout(activity);
        contentView.setId(android.R.id.content);
        activity.addContentView(contentView, new ViewGroup.LayoutParams(
                ViewGroup.LayoutParams.MATCH_PARENT,
                ViewGroup.LayoutParams.MATCH_PARENT));

        final FragmentManager fragmentManager = activity.getFragmentManager();
        fragmentManager.beginTransaction().add(android.R.id.content, fragment).commit();
    }
}
