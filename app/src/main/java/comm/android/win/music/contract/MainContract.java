package comm.android.win.music.contract;


import comm.android.win.music.model.bean.DataInfo;

/**
 * Created by win on 2017/12/26.
 */

public interface MainContract extends BaseContract {

    interface Model {
        void getData(Presenter presenter, String type, int size, int offset);
    }

    interface View {

        void showData(DataInfo dataInfo);

    }

    interface Presenter {
        void showData(DataInfo dataInfo);
    }
}
