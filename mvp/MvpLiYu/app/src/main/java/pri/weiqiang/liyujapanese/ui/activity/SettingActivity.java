package pri.weiqiang.liyujapanese.ui.activity;

import android.os.Bundle;
import android.util.Log;
import android.view.View;

import androidx.annotation.Nullable;
import androidx.appcompat.widget.Toolbar;
import androidx.coordinatorlayout.widget.CoordinatorLayout;

import butterknife.BindView;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Consumer;
import pri.weiqaing.common.base.BaseActivity;
import pri.weiqaing.common.rxbus.RxBus;
import pri.weiqaing.common.rxbus.event.EventContainer;
import pri.weiqaing.common.rxbus.event.SettingEvent;
import pri.weiqiang.liyujapanese.R;
import pri.weiqiang.liyujapanese.ui.fragment.SettingFragment;


public class SettingActivity extends BaseActivity {


    @BindView(R.id.toolbar)
    Toolbar mToolbar;
    @BindView(R.id.layout_root)
    CoordinatorLayout mRootLayout;
    boolean registered = false;
    Disposable subscription;
    private String TAG = SettingActivity.class.getSimpleName();

    @Override
    protected int getViewId() {
        return R.layout.activity_setting;
    }

    @Override
    protected void initVariable(@Nullable Bundle savedInstanceState) {

        if (!registered) {
            //RxBus
            subscription = RxBus.getDefault().toObserverable(EventContainer.class)
                    .subscribe(new Consumer<EventContainer>() {
                        @Override
                        public void accept(EventContainer eventContainer) throws Exception {
                            if (eventContainer.getType() == EventContainer.TYPE_SETTING) {

                                SettingEvent event = (SettingEvent) eventContainer.getEvent();
                                showSnackBar(mRootLayout, event.getMsg());
                            }
                        }
                    }, new Consumer<Throwable>() {
                        @Override
                        public void accept(Throwable throwable) throws Exception {
                            Log.e(TAG, "Consumer<Throwable>:" + throwable.toString());
                        }
                    });

            registered = true;

        }


        initToolbar();
    }

    @Override
    protected void doAction() {

        getFragmentManager().beginTransaction().replace(R.id.content, new SettingFragment()).commit();

    }

    private void initToolbar() {

        mToolbar.setTitle(R.string.setting);
        setSupportActionBar(mToolbar);

        mToolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        subscription.dispose();
    }
}
