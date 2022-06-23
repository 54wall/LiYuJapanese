package pri.weiqiang.jet.liyujapanese.ui.fragment;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.navigation.fragment.NavHostFragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import pri.weiqiang.jet.liyujapanese.R;
import pri.weiqiang.jet.liyujapanese.databinding.FragmentWordBinding;
import pri.weiqiang.jet.liyujapanese.ui.activity.MainActivity;
import pri.weiqiang.jet.liyujapanese.ui.adapter.WordAdapter;
import pri.weiqiang.jet.liyujapanese.ui.viewmodel.MainActivityViewModel;
import pri.weiqiang.jet.liyujapanese.ui.viewmodel.WordFragmentViewModel;

public class WordFragment extends Fragment {
    private String TAG = WordFragment.class.getSimpleName();
    private FragmentWordBinding binding;
    private WordFragmentViewModel model;
    private MainActivityViewModel mainModel;
    private WordAdapter adapter;
    private boolean isExpandable = false;
    @Override
    public View onCreateView(
            LayoutInflater inflater, ViewGroup container,
            Bundle savedInstanceState
    ) {

        binding = FragmentWordBinding.inflate(inflater, container, false);
        return binding.getRoot();

    }

    public void onViewCreated(@NonNull View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        binding.btnNext.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                NavHostFragment.findNavController(WordFragment.this).navigate(R.id.action_WordFragment_to_LessonFragment);
                model.setQuery("新标日初级_02");
                //错误用法
//                model.getWordList("新标日初级_01").observe(getViewLifecycleOwner(),words->{
//                    if (words != null && words.size() != 0) {
//                        Log.e(TAG, "words.get(0):" + words.get(0).toString());
//                    }
//                });
            }
        });
        binding.btnGet.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                model.setQuery("新标日初级_03");
            }
        });
        adapter = new WordAdapter(isExpandable);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getContext());
        linearLayoutManager.setOrientation(RecyclerView.VERTICAL);
        binding.rvWord.setLayoutManager(linearLayoutManager);
        binding.rvWord.setHasFixedSize(true);
        binding.rvWord.setAdapter(adapter);
        mainModel = new ViewModelProvider(requireActivity()).get(MainActivityViewModel.class);
        mainModel.getCurrentLesson().observe(getViewLifecycleOwner(), currentLesson -> {
            Log.e(TAG,"currentLesson:"+currentLesson);
            model.setQuery(currentLesson);
            ((MainActivity)getActivity()).getSupportActionBar().setTitle(currentLesson);
        });
        model = new ViewModelProvider(this).get(WordFragmentViewModel.class);
        //ViewModel 绝不能引用视图、Lifecycle 或可能存储对 Activity 上下文的引用的任何类。
//        model.setContext(getActivity());
        model.getWordList().observe(getViewLifecycleOwner(), words -> {
            if (words != null && words.size() != 0) {
                Log.e(TAG, " words.get(0):" + words.get(0).toString());
            }
            adapter.submitList(words);

        });
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }

}