package pri.weiqiang.liyujapanese.ui.activity;

import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;
import android.widget.LinearLayout;

import androidx.annotation.Nullable;

import com.blankj.utilcode.util.FileUtils;
import com.bumptech.glide.Glide;
import com.bumptech.glide.request.target.Target;
import com.github.chrisbanes.photoview.PhotoView;

import java.io.File;
import java.util.concurrent.ExecutionException;

import butterknife.BindView;
import io.reactivex.Observable;
import io.reactivex.ObservableEmitter;
import io.reactivex.ObservableOnSubscribe;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Consumer;
import io.reactivex.schedulers.Schedulers;
import pri.weiqaing.common.base.BaseActivity;
import pri.weiqaing.common.config.Constants;
import pri.weiqiang.liyujapanese.R;

public class PhotoViewActivity extends BaseActivity {


    @BindView(R.id.layout_root)
    LinearLayout mRootLayout;
    @BindView(R.id.pv)
    PhotoView mPhotoView;

    String img_url;
    int img_id;

    @Override
    protected int getViewId() {
        return R.layout.activity_photoview;
    }

    @Override
    protected void initVariable(@Nullable Bundle savedInstanceState) {

        img_url = getIntent().getExtras().getString(Constants.IMG_URL);
        img_id = getIntent().getExtras().getInt(Constants.IMG_ID);

        initPhotoView();

    }

    private void initPhotoView() {

        Glide.with(this)/*.asBitmap()*/.load(img_url).into(mPhotoView);

        mPhotoView.setOnLongClickListener(new View.OnLongClickListener() {
            @Override

            public boolean onLongClick(View v) {

                Disposable disposable = Observable.create(new ObservableOnSubscribe<Integer>() {
                    @Override
                    public void subscribe(ObservableEmitter<Integer> emitter) throws Exception {
                        try {

                            File srcFile = Glide.with(PhotoViewActivity.this)
                                    .load(img_url)
                                    .downloadOnly(Target.SIZE_ORIGINAL, Target.SIZE_ORIGINAL)
                                    .get();


                            if (FileUtils.createOrExistsDir(Constants.FILEDIR_ROOT)) {

                                File dstFile = new File(Constants.FILEDIR_ROOT, String.valueOf(img_id) + Constants.FILETYPE_JPG);
                                FileUtils.copyFile(srcFile, dstFile, new Listener());

                            }
                            emitter.onNext(R.string.save_success);
                        } catch (InterruptedException | ExecutionException e) {
                            emitter.onError(e);
                            showSnackBar(mRootLayout, R.string.save_error);
                        }
                        emitter.onComplete();
                    }
                }).subscribeOn(Schedulers.io())
                        .observeOn(AndroidSchedulers.mainThread())
                        .subscribe(new Consumer<Integer>() {
                            @Override
                            public void accept(Integer integer) throws Exception {
                                showSnackBar(mRootLayout, integer);
                            }
                        });
                addDisposable(disposable);
                return false;
            }
        });


    }

    @Override
    protected void doAction() {
        hideState();
    }

    private void hideState() {

        if (Build.VERSION.SDK_INT < 16) {
            getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        } else {
            getWindow().getDecorView().setSystemUiVisibility(View.SYSTEM_UI_FLAG_FULLSCREEN);
        }

    }

    @Override
    public void onBackPressed() {
        finish();
        overridePendingTransition(android.R.anim.fade_in, android.R.anim.fade_out);
    }

    /**
     * 设置文件是否覆盖
     */
    public class Listener implements FileUtils.OnReplaceListener {

        @Override
        public boolean onReplace() {

            return false;
        }
    }
}
