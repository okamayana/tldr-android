package ok.tldr.android.ui.activities;

import android.content.Intent;
import android.view.MotionEvent;

import org.junit.After;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.robolectric.Robolectric;
import org.robolectric.util.ActivityController;

import ok.tldr.android.TldrRobolectricTestRunner;

import static org.junit.Assert.fail;

@RunWith(TldrRobolectricTestRunner.class)
public class AbsSwipeToFinishActivityTest {

    TestSwipeToFinishActivity activity;
    ActivityController<TestSwipeToFinishActivity> activityController;

    @After
    public void teardown() {
        activityController.pause().stop().destroy();
        activityController = null;
        activity = null;
    }

    @Test(expected = IllegalArgumentException.class)
    public void onCreate_invalidSide_shouldThrowException() {
        setupActivity(null, 0.5f);
        fail();
    }

    @Test(expected = IllegalArgumentException.class)
    public void onCreate_invalidThreshold_shouldThrowException() {
        setupActivity(AbsSwipeToFinishActivity.Side.LEFT, -1.0f);
        fail();
    }

    @Test(expected = IllegalArgumentException.class)
    public void onCreate_invalidSideAndThreshold_shouldThrowException() {
        setupActivity(null, -1.0f);
        fail();
    }

    @Test(expected = IllegalArgumentException.class)
    public void setMembers_invalidSide_shouldThrowException() {
        setupActivity(AbsSwipeToFinishActivity.Side.LEFT, 5.0f);
        activity.setSide(null);
        fail();
    }

    @Test(expected = IllegalArgumentException.class)
    public void setMembers_invalidThreshold_shouldThrowException() {
        setupActivity(AbsSwipeToFinishActivity.Side.LEFT, 5.0f);
        activity.setThreshold(-1.0f);
        fail();
    }

    private void setupActivity(final AbsSwipeToFinishActivity.Side side, final float threshold) {
        final Intent intent = new Intent();
        intent.putExtra(AbsSwipeToFinishActivity.EXTRA_SIDE, side);
        intent.putExtra(AbsSwipeToFinishActivity.EXTRA_THRESHOLD, threshold);

        activityController = Robolectric.buildActivity(TestSwipeToFinishActivity.class).withIntent(intent);
        activity = activityController.create().start().resume().visible().get();
    }

    public static class TestSwipeToFinishActivity extends AbsSwipeToFinishActivity {

        @Override
        protected void onSwipeFromLeft(MotionEvent startEvent, MotionEvent endEvent, float distanceX, float distanceY) {
            super.onSwipeFromLeft(startEvent, endEvent, distanceX, distanceY);
        }

        @Override
        protected void onSwipeFromRight(MotionEvent startEvent, MotionEvent endEvent, float distanceX, float distanceY) {
            super.onSwipeFromRight(startEvent, endEvent, distanceX, distanceY);
        }

        @Override
        protected void onFlingFromLeft(MotionEvent startEvent, MotionEvent endEvent, float velocityX, float velocityY) {
            super.onFlingFromLeft(startEvent, endEvent, velocityX, velocityY);
        }

        @Override
        protected void onFlingFromRight(MotionEvent startEvent, MotionEvent endEvent, float velocityX, float velocityY) {
            super.onFlingFromRight(startEvent, endEvent, velocityX, velocityY);
        }
    }
}
