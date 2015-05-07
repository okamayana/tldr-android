package ok.tldr.android.ui.fragments;

import android.app.Fragment;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;

import io.pivotal.arca.dispatcher.*;
import io.pivotal.arca.dispatcher.Error;
import io.pivotal.arca.fragments.ArcaDispatcherFactory;
import io.pivotal.arca.monitor.ArcaDispatcher;
import io.pivotal.arca.monitor.RequestMonitor;
import ok.tldr.android.ui.managers.ViewStateManager;

public abstract class AbsTldrFragment<V extends View> extends Fragment
        implements ViewStateManager.ViewStateListener, QueryListener, ErrorListener {

    ViewStateManager viewStateManager;

    private V contentView;
    private View loadingView;
    private View emptyView;
    private View errorView;

    private ArcaDispatcher dispatcher;
    private ErrorReceiver errorReceiver;

    public AbsTldrFragment() { }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        dispatcher = getRequestDispatcher();
        errorReceiver = getErrorReceiver();
        viewStateManager = new ViewStateManager(AbsTldrFragment.this);
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        contentView = (V) view.findViewById(getContentViewId());
        loadingView = view.findViewById(getLoadingViewId());
        emptyView = view.findViewById(getEmptyViewId());
        errorView = view.findViewById(getErrorViewId());
    }

    @Override
    public void onStart() {
        super.onStart();

        if (errorReceiver != null) {
            errorReceiver.register(getContentUri());
        }
    }

    public void onStop() {
        if (errorReceiver != null) {
            errorReceiver.unregister();
        }

        super.onStop();
    }

    protected ErrorReceiver getErrorReceiver() {
        if (errorReceiver == null) {
            errorReceiver = new ErrorReceiver(AbsTldrFragment.this);
        }

        return errorReceiver;
    }

    protected abstract Uri getContentUri();

    protected ArcaDispatcher getRequestDispatcher() {
        if (dispatcher == null) {
            dispatcher = ArcaDispatcherFactory.generateDispatcher(AbsTldrFragment.this);
        }

        return dispatcher;
    }

    protected RequestMonitor getRequestMonitor() {
        if (dispatcher != null) {
            return dispatcher.getRequestMonitor();
        } else {
            return null;
        }
    }

    protected void setRequestMonitor(final RequestMonitor monitor) {
        final ArcaDispatcher dispatcher = getRequestDispatcher();
        if (dispatcher != null) {
            dispatcher.setRequestMonitor(monitor);
        }
    }

    protected void execute(final Query query) {
        final ArcaDispatcher dispatcher = getRequestDispatcher();
        if (dispatcher != null) {
            dispatcher.execute(query, AbsTldrFragment.this);
        }
    }

    @Override
    public void onRequestComplete(final QueryResult result) {
        final int resultCount = result.getResult().getCount();

        if (resultCount > 0) {
            onResultReady(result);
        } else {
            onResultEmpty();
        }
    }

    @Override
    public void onRequestReset() {
        onRequestRestart();
    }

    @Override
    public void onRequestError(Error error) {
        onResultError(error);
    }

    protected void onResultReady(final QueryResult result) {
        viewStateManager.moveToState(ViewStateManager.State.READY);
    }

    protected void onResultEmpty() {
        viewStateManager.moveToState(ViewStateManager.State.EMPTY);
    }

    protected void onResultError(final Error error) {
        viewStateManager.moveToState(ViewStateManager.State.ERROR);
    }

    protected void onRequestRestart() { }

    abstract int getContentViewId();

    abstract int getLoadingViewId();

    abstract int getEmptyViewId();

    abstract int getErrorViewId();

    V getContentView() {
        return contentView;
    }

    View getLoadingView() {
        return loadingView;
    }

    View getEmptyView() {
        return emptyView;
    }

    View getErrorView() {
        return errorView;
    }

    @Override
    public void onViewReady() {
        getContentView().setVisibility(View.VISIBLE);
        getLoadingView().setVisibility(View.GONE);
        getEmptyView().setVisibility(View.GONE);
        getErrorView().setVisibility(View.GONE);
    }

    @Override
    public void onViewLoading() {
        getContentView().setVisibility(View.GONE);
        getLoadingView().setVisibility(View.VISIBLE);
        getEmptyView().setVisibility(View.GONE);
        getErrorView().setVisibility(View.GONE);
    }

    @Override
    public void onViewEmpty() {
        getContentView().setVisibility(View.GONE);
        getLoadingView().setVisibility(View.GONE);
        getEmptyView().setVisibility(View.VISIBLE);
        getErrorView().setVisibility(View.GONE);
    }

    @Override
    public void onViewError() {
        getContentView().setVisibility(View.GONE);
        getLoadingView().setVisibility(View.GONE);
        getEmptyView().setVisibility(View.GONE);
        getErrorView().setVisibility(View.VISIBLE);
    }
}
