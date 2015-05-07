package ok.tldr.android.ui.fragments;

import android.app.Activity;
import android.app.FragmentManager;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.robolectric.Robolectric;
import org.robolectric.util.ActivityController;

import ok.tldr.android.TldrRobolectricTestRunner;
import ok.tldr.android.test.R;
import ok.tldr.android.ui.managers.ViewStateManager;

import static org.fest.assertions.api.ANDROID.assertThat;

@RunWith(TldrRobolectricTestRunner.class)
public class AbsTldrFragmentTest {

    Activity activity;
    ActivityController activityController;
    AbsTldrFragment fragment;

    @Before
    public void setup() {
        activityController = Robolectric.buildActivity(Activity.class);
        activity = (Activity) activityController.create().start().resume().visible().get();
        fragment = new TestTldrFragment();

        final FrameLayout contentView = new FrameLayout(activity);
        contentView.setId(android.R.id.content);
        activity.addContentView(contentView, new ViewGroup.LayoutParams(
                ViewGroup.LayoutParams.MATCH_PARENT,
                ViewGroup.LayoutParams.MATCH_PARENT));

        final FragmentManager fragmentManager = activity.getFragmentManager();
        fragmentManager.beginTransaction().add(android.R.id.content, fragment).commit();
    }

    @Test
    public void viewStateManager_moveToStateReady_shouldShowContentView() {
        fragment.viewStateManager.moveToState(ViewStateManager.State.READY);
        assertThat(fragment.getContentView()).isVisible();
        assertThat(fragment.getLoadingView()).isNotVisible();
        assertThat(fragment.getEmptyView()).isNotVisible();
        assertThat(fragment.getErrorView()).isNotVisible();
    }

    @Test
    public void viewStateManager_moveToStateLoading_shouldShowLoadingView() {
        fragment.viewStateManager.moveToState(ViewStateManager.State.LOADING);
        assertThat(fragment.getContentView()).isNotVisible();
        assertThat(fragment.getLoadingView()).isVisible();
        assertThat(fragment.getEmptyView()).isNotVisible();
        assertThat(fragment.getErrorView()).isNotVisible();
    }

    @Test
    public void viewStateManager_moveToStateEmpty_shouldShowEmptyView() {
        fragment.viewStateManager.moveToState(ViewStateManager.State.EMPTY);
        assertThat(fragment.getContentView()).isNotVisible();
        assertThat(fragment.getLoadingView()).isNotVisible();
        assertThat(fragment.getEmptyView()).isVisible();
        assertThat(fragment.getErrorView()).isNotVisible();
    }

    @Test
    public void viewStateManager_moveToStateError_shouldShowErrorView() {
        fragment.viewStateManager.moveToState(ViewStateManager.State.ERROR);
        assertThat(fragment.getContentView()).isNotVisible();
        assertThat(fragment.getLoadingView()).isNotVisible();
        assertThat(fragment.getEmptyView()).isNotVisible();
        assertThat(fragment.getErrorView()).isVisible();
    }

    public static class TestTldrFragment extends AbsTldrFragment {

        public TestTldrFragment() {
            super();
        }

        @Nullable
        @Override
        public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
            return inflater.inflate(R.layout.activity_tldr, container, false);
        }

        @Override
        protected Uri getContentUri() {
            return null;
        }

        @Override
        int getContentViewId() {
            return R.id.content_view;
        }

        @Override
        int getLoadingViewId() {
            return R.id.loading_view;
        }

        @Override
        int getEmptyViewId() {
            return R.id.empty_view;
        }

        @Override
        int getErrorViewId() {
            return R.id.error_view;
        }
    }
}
