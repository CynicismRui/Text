package comm.android.win.music.base;

/**
 * Created by win on 2017/12/27.
 */

public class BasePresenter<V> {

    public V view;

    public V getView() {
        return view;
    }

    public void attachView(V view) {

        this.view = view;
    }

    public void detachView() {
        view = null;
    }

}
