package pri.weiqiang.jet.liyujapanese.ui.fragment;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.List;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.navigation.fragment.NavHostFragment;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import pri.weiqiang.jet.liyujapanese.R;
import pri.weiqiang.jet.liyujapanese.config.Constants;
import pri.weiqiang.jet.liyujapanese.data.bean.Book;
import pri.weiqiang.jet.liyujapanese.data.bean.Lesson;
import pri.weiqiang.jet.liyujapanese.databinding.FragmentLessonBinding;
import pri.weiqiang.jet.liyujapanese.manager.SharedPreferenceManager;
import pri.weiqiang.jet.liyujapanese.ui.activity.MainActivity;
import pri.weiqiang.jet.liyujapanese.ui.adapter.LeftMenuAdapter;
import pri.weiqiang.jet.liyujapanese.ui.adapter.RightMenuAdapter;
import pri.weiqiang.jet.liyujapanese.ui.viewmodel.LessonFragmentViewModel;
import pri.weiqiang.jet.liyujapanese.ui.viewmodel.MainActivityViewModel;

public class LessonFragment extends Fragment implements LeftMenuAdapter.onItemSelectedListener{

    private static final String TAG = LessonFragment.class.getSimpleName();
    private FragmentLessonBinding binding;
    private LessonFragmentViewModel model;
    private MainActivityViewModel mainModel;
    private Book headMenu;
    private LeftMenuAdapter leftAdapter;
    private RightMenuAdapter rightAdapter;
    private List<Book> mBookList;//数据源
    private boolean leftClickType = false;//左侧菜单点击引发的右侧联动

    @Override
    public View onCreateView(
            LayoutInflater inflater, ViewGroup container,
            Bundle savedInstanceState
    ) {

        binding = FragmentLessonBinding.inflate(inflater, container, false);
        return binding.getRoot();

    }

    public void onViewCreated(@NonNull View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        initView();
        initViewModel();
    }

    private void initViewModel(){
        mainModel = new ViewModelProvider(requireActivity()).get(MainActivityViewModel.class);
        model = new ViewModelProvider(this).get(LessonFragmentViewModel.class);
        model.getBookList().observe(getViewLifecycleOwner(), books-> {
            if (books != null && books.size() != 0) {
                Log.e(TAG, "books.get(0):" + books.get(0).toString());
            }
            //不含刷新数据可以这样做，但应该使用adapter.submitList(words);但联动数据暂时没有找到好方法，需要时间
            setData(books);
        });
    }

    public void initView() {
        ((MainActivity)getActivity()).getSupportActionBar().setTitle("目录");
        binding.leftMenu.setLayoutManager(new LinearLayoutManager(getActivity()));
        binding.rightMenu.setLayoutManager(new LinearLayoutManager(getActivity()));
        //如果可以确定每个item的高度是固定的，设置这个选项可以提高性能
        binding.leftMenu.setHasFixedSize(true);
        binding.rightMenu.setHasFixedSize(true);
        binding.rightMenu.addOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrollStateChanged(RecyclerView recyclerView, int newState) {
                super.onScrollStateChanged(recyclerView, newState);
            }

            @Override
            public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
                Log.e(TAG, "onScrolled start");
                super.onScrolled(recyclerView, dx, dy);
                if (recyclerView.canScrollVertically(1) == false) {//无法下滑
                    showHeadView();
                    return;
                }
                View underView;
                if (dy > 0) {
                    underView = binding.rightMenu.findChildViewUnder(binding.include.llRightMenuHead.getX(), binding.include.llRightMenuHead.getMeasuredHeight() + 1);
                } else {
                    underView = binding.rightMenu.findChildViewUnder(binding.include.llRightMenuHead.getX(), 0);
                }
                if (underView != null && underView.getContentDescription() != null) {
                    int position = Integer.parseInt(underView.getContentDescription().toString());
                    Book menu = rightAdapter.getMenuOfMenuByPosition(position);

                    if (leftClickType || ((menu!=null&&headMenu!=null)
                            &&!menu.getName().equals(headMenu.getName()))) {
                        if (dy > 0 && binding.include.llRightMenuHead.getTranslationY() <= 1 && binding.include.llRightMenuHead.getTranslationY() >= -1 * binding.include.llRightMenuHead.getMeasuredHeight() * 4 / 5 && !leftClickType) {// underView.getTop()>9
                            int dealtY = underView.getTop() - binding.include.llRightMenuHead.getMeasuredHeight();
                            binding.include.llRightMenuHead.setTranslationY(dealtY);
//                            Log.e(TAG, "onScrolled: "+binding.include.llRightMenuHead.getTranslationY()+"   "+binding.include.llRightMenuHead.getBottom()+"  -  "+binding.include.llRightMenuHead.getMeasuredHeight() );
                        } else if (dy < 0 && binding.include.llRightMenuHead.getTranslationY() <= 0 && !leftClickType) {
                            binding.include.tvBook.setText(menu.getName());
                            int dealtY = underView.getBottom() - binding.include.llRightMenuHead.getMeasuredHeight();
                            binding.include.llRightMenuHead.setTranslationY(dealtY);
//                            Log.e(TAG, "onScrolled: "+binding.include.llRightMenuHead.getTranslationY()+"   "+binding.include.llRightMenuHead.getBottom()+"  -  "+binding.include.llRightMenuHead.getMeasuredHeight() );
                        } else {
                            binding.include.llRightMenuHead.setTranslationY(0);
                            headMenu = menu;
                            binding.include.tvBook.setText(headMenu.getName());
                            for (int i = 0; i < mBookList.size(); i++) {
                                if (mBookList.get(i) == headMenu) {
                                    leftAdapter.setSelectedNum(i);
                                    break;
                                }
                            }
                            if (leftClickType) leftClickType = false;
                            Log.e(TAG, "onScrolled: " + menu.getName());
                        }
                    }
                }
                Log.e(TAG, "onScrolled end");
            }
        });

    }

    public void setData(List<Book> data) {
        //来自initAdapter
        mBookList = data;
        leftAdapter = new LeftMenuAdapter(data);
        rightAdapter = new RightMenuAdapter(data);

        rightAdapter.setOnItemClickListener(new RightMenuAdapter.OnItemClickListener() {
            @Override
            public void onClick(Lesson item) {
                Log.e(TAG, "lesson:" + item.getTitle());
                SharedPreferenceManager.getInstance().putString(Constants.CURRENT_LESSON, item.getTitle());
                SharedPreferenceManager.getInstance().putInt(Constants.CURRENT_LESSON_ID, item.getId());
                mainModel.setCurrentLesson(item.getTitle());
                NavHostFragment.findNavController(LessonFragment.this).navigate(R.id.action_LessonFragment_to_WordFragment);
            }
        });
        binding.rightMenu.setAdapter(rightAdapter);
        binding.rightMenu.addItemDecoration(new DividerItemDecoration(getActivity(), DividerItemDecoration.VERTICAL));
        binding.leftMenu.setAdapter(leftAdapter);
        leftAdapter.addItemSelectedListener(LessonFragment.this);
        //设置初始滚动位置
        Log.e(TAG, "CURRENT_LESSON_ID:" + SharedPreferenceManager.getInstance().getInt(Constants.CURRENT_LESSON_ID, Constants.DEFAULT_LESSON_ID));
        binding.rightMenu.scrollToPosition(SharedPreferenceManager.getInstance().getInt(Constants.CURRENT_LESSON_ID, Constants.DEFAULT_LESSON_ID));

        initHeadView();
    }

    private void initHeadView() {
        headMenu = rightAdapter.getMenuOfMenuByPosition(0);
        binding.include.llRightMenuHead.setContentDescription("0");
        binding.include.tvBook.setText(headMenu.getName());
    }

    private void showHeadView() {
        binding.include.llRightMenuHead.setTranslationY(0);
        View underView = binding.rightMenu.findChildViewUnder(binding.include.tvBook.getX(), 0);
        if (underView != null && underView.getContentDescription() != null) {
            int position = Integer.parseInt(underView.getContentDescription().toString());
            Book menu = rightAdapter.getMenuOfMenuByPosition(position + 1);
            headMenu = menu;
            binding.include.tvBook.setText(headMenu.getName());
            for (int i = 0; i < mBookList.size(); i++) {
                if (mBookList.get(i) == headMenu) {
                    leftAdapter.setSelectedNum(i);
                    break;
                }
            }
        }
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }


    @Override
    public void onLeftItemSelected(int position, Book menu) {
        int sum = 0;
        for (int i = 0; i < position; i++) {
            sum += mBookList.get(i).getLessonList().size() + 1;
        }
        LinearLayoutManager layoutManager = (LinearLayoutManager) binding.rightMenu.getLayoutManager();
        layoutManager.scrollToPositionWithOffset(sum, 0);
        leftClickType = true;
    }
}