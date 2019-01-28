package pri.weiqiang.liyujapanese.ui.fragment;

import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.Spinner;
import android.widget.TextView;

import java.util.List;

import androidx.annotation.Nullable;
import butterknife.BindView;
import pri.weiqiang.liyujapanese.MyApplication;
import pri.weiqiang.liyujapanese.R;
import pri.weiqiang.liyujapanese.mvp.bean.TranslateSpinnerItem;
import pri.weiqiang.liyujapanese.mvp.presenter.BasePresenter;
import pri.weiqiang.liyujapanese.mvp.presenter.TranslateFragmentPresenterImpl;
import pri.weiqiang.liyujapanese.mvp.view.BaseView;
import pri.weiqiang.liyujapanese.ui.adapter.TranslateSpinnerAdapter;

public class TranslateFragment extends BaseFragment implements BaseView.TranslateFragmentView, View.OnClickListener {


    @BindView(R.id.layout_root)
    LinearLayout mRootLayout;
    @BindView(R.id.spinner_from)
    Spinner mFromSpinner;
    @BindView(R.id.spinner_to)
    Spinner mToSpinner;
    @BindView(R.id.btn_translate)
    Button mTranslateButton;
    @BindView(R.id.et_src)
    EditText mSrcEditText;
    @BindView(R.id.tv_dst)
    TextView mDstTextView;

    @BindView(R.id.iv_src_copy)
    ImageView mSrcCopyImageView;
    @BindView(R.id.iv_src_paste)
    ImageView mSrcPasteImageView;
    @BindView(R.id.iv_src_clear)
    ImageView mSrcClearImageView;
    @BindView(R.id.iv_dst_copy)
    ImageView mDstCopyImageView;
    @BindView(R.id.iv_dst_clear)
    ImageView mDstClearImageView;

    BasePresenter.TranslateFragmentPresenter presenter;

    @Override
    protected int getViewId() {
        return R.layout.fragment_translate;
    }

    @Override
    protected void initVariable(@Nullable Bundle savedInstanceState) {

        presenter = new TranslateFragmentPresenterImpl(this);

        initSpinner();
        initButton();
        initImageView();

    }

    private void initSpinner() {

        mFromSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                presenter.checkFromLanguate(position);
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
            }
        });

        mToSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                presenter.checkToLanguage(position);
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
            }
        });

    }

    private void initButton() {

        mTranslateButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                presenter.doTranslate();
            }
        });

    }

    private void initImageView() {

        mSrcCopyImageView.setOnClickListener(this);
        mSrcPasteImageView.setOnClickListener(this);
        mSrcClearImageView.setOnClickListener(this);
        mDstCopyImageView.setOnClickListener(this);
        mDstClearImageView.setOnClickListener(this);

    }

    @Override
    protected void doAction() {
        presenter.initTranslateFragment();
    }

    @Override
    public void showMsg(String msg) {
        showSnackBar(mRootLayout, msg);
    }

    @Override
    public void showMsg(int msg) {
        showSnackBar(mRootLayout, msg);

    }

    @Override
    public String getSrcText() {
        return mSrcEditText.getText().toString();
    }

    @Override
    public void setSrcEditText(String text) {
        mSrcEditText.setText(text);
    }

    @Override
    public String getDstText() {
        return mDstTextView.getText().toString();
    }

    @Override
    public void setDstTextView(String text) {
        mDstTextView.setText(text);
    }


    @Override
    public void setFromSpinner(List<TranslateSpinnerItem> list) {

        mFromSpinner.setAdapter(new TranslateSpinnerAdapter(list, getContext()));
        mFromSpinner.setSelection(MyApplication.FROM_LAN);
    }

    @Override
    public void setToSpinner(List<TranslateSpinnerItem> list) {
        mToSpinner.setAdapter(new TranslateSpinnerAdapter(list, getContext()));
        mToSpinner.setSelection(MyApplication.TO_LAN);
    }


    @Override
    public void onClick(View v) {
        presenter.checkImageViewClick(v.getId());
    }


}
