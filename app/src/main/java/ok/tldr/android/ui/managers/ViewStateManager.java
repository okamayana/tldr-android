package ok.tldr.android.ui.managers;

public class ViewStateManager {

    private ViewStateListener listener;

    public static enum State {
        READY, LOADING, EMPTY, ERROR;
    }

    public ViewStateManager(ViewStateListener listener) {
        this.listener = listener;
    }

    public void moveToState(State state) {
        switch (state) {
            case READY:
                listener.onViewReady();
                break;
            case LOADING:
                listener.onViewLoading();
                break;
            case EMPTY:
                listener.onViewEmpty();
                break;
            case ERROR:
                listener.onViewError();
        }
    }

    public interface ViewStateListener {
        public void onViewReady();

        public void onViewLoading();

        public void onViewEmpty();

        public void onViewError();
    }
}
