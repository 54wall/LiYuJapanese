package pri.weiqiang.jet.liyujapanese.ui.word;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import pri.weiqiang.jet.liyujapanese.databinding.FragmentWordBinding;
import pri.weiqiang.jet.liyujapanese.ui.adapter.WordsRecyclerAdapter;

public class WordFragment extends Fragment {
    private String TAG = WordFragment.class.getSimpleName();
    private FragmentWordBinding binding;
    private WordFragmentViewModel model;
    private RecyclerView rvWord;
    private WordsRecyclerAdapter adapter;
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

        binding.buttonFirst.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
//                NavHostFragment.findNavController(WordFragment.this).navigate(R.id.action_FirstFragment_to_SecondFragment);
                model.setQuery("新标日初级_02");
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
//                model.getWordList("新标日初级_01").observe(getViewLifecycleOwner(),words->{
//                    if (words != null && words.size() != 0) {
//                        Log.e(TAG, "words.get(0):" + words.get(0).toString());
//                    }
//                });
            }
        });

        model = new ViewModelProvider(this).get(WordFragmentViewModel.class);
        //ViewModel 绝不能引用视图、Lifecycle 或可能存储对 Activity 上下文的引用的任何类。
//        model.setContext(getActivity());
        model.getWordList().observe(getViewLifecycleOwner(), words -> {
            if (words != null && words.size() != 0) {
                Log.e(TAG, "words:"+words+" words.get(0):" + words.get(0).toString());
            }
            if (adapter==null){
                adapter = new WordsRecyclerAdapter(getActivity(),words,isExpandable);
                LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getContext());
                linearLayoutManager.setOrientation(RecyclerView.VERTICAL);
                binding.rvWord.setLayoutManager(linearLayoutManager);
                binding.rvWord.setAdapter(adapter);
                adapter.notifyDataSetChanged();
            }else {
                adapter.updateWordList(words);
                adapter.notifyDataSetChanged();
            }

        });
        Log.e(TAG, "wordList:" + model.getWordList());
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }

}