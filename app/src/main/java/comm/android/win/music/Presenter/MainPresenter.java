package comm.android.win.music.Presenter;

import comm.android.win.music.base.BasePresenter;
import comm.android.win.music.contract.MainContract;
import comm.android.win.music.model.MainModel;
import comm.android.win.music.model.bean.DataInfo;

/**
 * Created by win on 2017/12/27.
 */

public class MainPresenter extends BasePresenter<MainContract.View> implements MainContract.Presenter {

    private String type = "2";

    private int pageSize = 15;

    private int pageOffset;
    private boolean needClear=false;

    private final MainModel mainModel;

    public MainPresenter() {

        mainModel = new MainModel();
    }

    public void getData() {

        mainModel.getData(this, type, pageSize, pageOffset);


    }

    @Override
    public void showData(DataInfo dataInfo) {

        getView().showData(dataInfo);

        //获取到数据后停止刷新
        needClear=false;
    }

    public void refreshData() {

        needClear=true;
        pageSize=0;
        mainModel.getData(this, type, pageSize, pageSize*pageOffset);


    }


}
