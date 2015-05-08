package ok.tldr.android.ui.activities;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.robolectric.Robolectric;
import org.robolectric.util.ActivityController;

import ok.tldr.android.R;
import ok.tldr.android.TldrRobolectricTestRunner;

import static org.fest.assertions.api.ANDROID.assertThat;

@RunWith(TldrRobolectricTestRunner.class)
public class SplashScreenActivityTest {

    SplashScreenActivity activity;
    ActivityController<SplashScreenActivity> activityController;

    @Before
    public void setup() {
        activityController = Robolectric.buildActivity(SplashScreenActivity.class);
        activity = activityController.create().start().resume().visible().get();
    }

    @After
    public void tearDown() {
        activityController.pause().stop().destroy();
        activityController = null;
        activity = null;
    }

    @Test
    public void onCreate_shouldShowSplashScreen() {
        assertThat(activity.findViewById(R.id.splash_logo)).isVisible();
    }
}
