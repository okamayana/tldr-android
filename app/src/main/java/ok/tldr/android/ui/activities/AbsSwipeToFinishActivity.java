package ok.tldr.android.ui.activities;

import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.view.GestureDetector;
import android.view.MotionEvent;
import android.view.View;

import ok.tldr.android.util.ViewUtil;

public abstract class AbsSwipeToFinishActivity extends TldrActivity {

    public static final String EXTRA_SIDE = "extra_side";
    public static final String EXTRA_THRESHOLD = "extra_threshold";

    public enum Side {
        LEFT, RIGHT
    }

    private SwipeGestureListener gestureListener;
    private GestureDetector gestureDetector;

    public static void startActivity(final Context context, final Side side, final float threshold) {
        final Intent intent = new Intent(context, AbsSwipeToFinishActivity.class);

        intent.putExtra(EXTRA_SIDE, side);
        intent.putExtra(EXTRA_THRESHOLD, threshold);
        context.startActivity(intent);
    }

    @Override
    protected void onCreate(final Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        final Side side = (Side) getIntent().getSerializableExtra(EXTRA_SIDE);
        final float threshold = getIntent().getFloatExtra(EXTRA_THRESHOLD, -1.0f);

        gestureListener = new SwipeGestureListener(side, threshold);
        gestureDetector = new GestureDetector(AbsSwipeToFinishActivity.this, gestureListener);
    }

    @Override
    public boolean onTouchEvent(final MotionEvent event) {
        return gestureDetector.onTouchEvent(event);
    }

    void onSwipeFromLeft(final MotionEvent startEvent, final MotionEvent endEvent,
                                   final float distanceX, final float distanceY) { }

    void onSwipeFromRight(final MotionEvent startEvent, final MotionEvent endEvent,
                                    final float distanceX, final float distanceY) { }

    void onFlingFromLeft(final MotionEvent startEvent, final MotionEvent endEvent,
                                   final float velocityX, final float velocityY) { }

    void onFlingFromRight(final MotionEvent startEvent, final MotionEvent endEvent,
                                    final float velocityX, final float velocityY) { }

    private Bitmap getViewSnapshotBitmap() {
        final View decorView = getWindow().getDecorView();

        decorView.setDrawingCacheEnabled(true);
        final Bitmap bitmap = decorView.getDrawingCache();
        decorView.setDrawingCacheEnabled(false);

        return bitmap;
    }

    protected final void setSide(final Side side) {
        if (gestureListener != null) {
            gestureListener.setSide(side);
        }
    }

    protected final void setThreshold(final float threshold) {
        if (gestureListener != null) {
            gestureListener.setThreshold(threshold);
        }
    }

    private class SwipeGestureListener extends GestureDetector.SimpleOnGestureListener {

        private Side side;
        private float threshold;

        public SwipeGestureListener(final Side side, final float threshold) {
            validateArgs(side, threshold);

            this.side = side;

            if (side == Side.LEFT) {
                this.threshold = threshold;
            } else {
                final float displayWidth = ViewUtil.getDisplayWidth(AbsSwipeToFinishActivity.this);
                this.threshold = displayWidth - threshold;
            }
        }

        @Override
        public boolean onDown(MotionEvent startEvent) {
            return super.onDown(startEvent);
        }

        @Override
        public boolean onScroll(final MotionEvent startEvent, final MotionEvent endEvent,
                                final float distanceX, final float distanceY) {
            float startPoint = startEvent.getX();

            if (side == Side.LEFT && startPoint <= threshold) {
                onSwipeFromLeft(startEvent, endEvent, distanceX, distanceY);
            } else if (side == Side.RIGHT && startPoint >= threshold) {
                onSwipeFromRight(startEvent, endEvent, distanceX, distanceY);
            }

            return true;
        }

        @Override
        public boolean onFling(final MotionEvent startEvent, final MotionEvent endEvent,
                               final float velocityX, final float velocityY) {
            float startPoint = startEvent.getX();

            if (side == Side.LEFT && startPoint <= threshold) {
                onFlingFromLeft(startEvent, endEvent, velocityX, velocityY);
            } else if (side == Side.RIGHT && startPoint >= threshold) {
                onFlingFromRight(startEvent, endEvent, velocityX, velocityY);
            }

            return true;
        }

        public void setSide(final Side side) {
            if (side == null) {
                throw new IllegalArgumentException("Side cannot be null");
            }

            this.side = side;
        }

        public void setThreshold(final float threshold) {
            if (threshold <= 0.0f) {
                throw new IllegalArgumentException("Invalid threshold argument: " + threshold);
            }

            this.threshold = threshold;
        }

        private void validateArgs(final Side side, final float threshold) {
            if (side == null) {
                throw new IllegalArgumentException("Side cannot be null");
            }

            if (threshold <= 0.0f) {
                throw new IllegalArgumentException("Invalid threshold argument: " + threshold);
            }
        }
    }
}
