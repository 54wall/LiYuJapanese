package pri.weiqaing.common.base;

import android.os.Bundle;
import android.view.View;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.material.snackbar.Snackbar;
import com.umeng.analytics.MobclickAgent;

import butterknife.ButterKnife;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.disposables.Disposable;
import pri.weiqaing.common.manager.ActivityManager;


public abstract class BaseActivity extends AppCompatActivity {

    CompositeDisposable mCompositeDisposable = new CompositeDisposable();

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        ActivityManager.getInstance().register(this);
        ActivityManager.setCurrent(this);

        setContentView(getViewId());

        ButterKnife.bind(this);

        initVariable(savedInstanceState);

        doAction();

    }

    public void addDisposable(Disposable disposable) {
        mCompositeDisposable.add(disposable);
    }

    protected abstract int getViewId();

    protected abstract void initVariable(@Nullable Bundle savedInstanceState);

    protected abstract void doAction();

    @Override
    protected void onDestroy() {
        super.onDestroy();
        mCompositeDisposable.clear();
        ActivityManager.getInstance().unregister(this);

    }

    public void showSnackBar(View view, String msg) {
        Snackbar.make(view, msg, Snackbar.LENGTH_SHORT).show();
    }

    public void showSnackBar(View view, int msg) {
        Snackbar.make(view, msg, Snackbar.LENGTH_SHORT).show();
    }

    @Override
    protected void onResume() {
        super.onResume();
        MobclickAgent.onResume(this);
        ActivityManager.setCurrent(this);

    }

    @Override
    protected void onPause() {
        super.onPause();
        MobclickAgent.onPause(this);
    }
}
