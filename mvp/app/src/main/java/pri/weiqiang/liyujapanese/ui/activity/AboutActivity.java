package pri.weiqiang.liyujapanese.ui.activity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.annotation.Nullable;
import androidx.appcompat.widget.Toolbar;

import butterknife.BindView;
import pri.weiqaing.common.base.BaseActivity;
import pri.weiqaing.common.config.Constants;
import pri.weiqaing.common.utils.ActivityUtils;
import pri.weiqiang.liyujapanese.R;


public class AboutActivity extends BaseActivity {

    @BindView(R.id.toolbar)
    Toolbar mToolbar;

    @BindView(R.id.btn_author)
    Button mBtnAuthor;


    @Override
    protected int getViewId() {
        return R.layout.activity_about;
    }

    @Override
    protected void initVariable(@Nullable Bundle savedInstanceState) {

        initToolbar();
        initButton();
    }

    private void initToolbar() {

        mToolbar.setTitle(R.string.app_name);
        setSupportActionBar(mToolbar);
        mToolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

    }

    private void initButton() {

        mBtnAuthor.setOnClickListener(v -> ActivityUtils.openUrl(Constants.URL_AUTHOR));
        mBtnAuthor.setOnClickListener(v -> {

        });

    }


    @Override
    protected void doAction() {

    }
}
