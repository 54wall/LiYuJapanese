package pri.weiqiang.jet.liyujapanese.ui.fragment

import pri.weiqiang.jet.liyujapanese.ui.fragment.WordFragment
import pri.weiqiang.jet.liyujapanese.ui.viewmodel.WordFragmentViewModel
import pri.weiqiang.jet.liyujapanese.ui.adapter.WordAdapter
import android.view.LayoutInflater
import android.view.ViewGroup
import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.NavHostFragment
import pri.weiqiang.jet.liyujapanese.R
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.lifecycle.ViewModelProvider
import pri.weiqiang.jet.liyujapanese.data.bean.Word
import pri.weiqiang.jet.liyujapanese.databinding.FragmentWordBinding

class WordFragment : Fragment() {
    private val TAG = WordFragment::class.java.simpleName
    private var binding: FragmentWordBinding? = null
    private var model: WordFragmentViewModel? = null
    private var adapter: WordAdapter? = null
    private val isExpandable = false
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentWordBinding.inflate(inflater, container, false)
        return binding!!.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding!!.btnNext.setOnClickListener {
            NavHostFragment.findNavController(this@WordFragment)
                .navigate(R.id.action_WordFragment_to_LessonFragment)
            model!!.setQuery("新标日初级_02")
            //错误用法
//                model.getWordList("新标日初级_01").observe(getViewLifecycleOwner(),words->{
//                    if (words != null && words.size() != 0) {
//                        Log.e(TAG, "words.get(0):" + words.get(0).toString());
//                    }
//                });
        }
        binding!!.btnGet.setOnClickListener { model!!.setQuery("新标日初级_03") }
        adapter = WordAdapter(isExpandable)
        val linearLayoutManager = LinearLayoutManager(context)
        linearLayoutManager.orientation = RecyclerView.VERTICAL
        binding!!.rvWord.layoutManager = linearLayoutManager
        binding!!.rvWord.setHasFixedSize(true)
        binding!!.rvWord.adapter = adapter
        model = ViewModelProvider(this).get(WordFragmentViewModel::class.java)
        //ViewModel 绝不能引用视图、Lifecycle 或可能存储对 Activity 上下文的引用的任何类。
//        model.setContext(getActivity());
        model!!.wordList.observe(viewLifecycleOwner) { words: List<Word> ->
            if (words != null && words.size != 0) {
                Log.e(TAG, " words.get(0):" + words[0].toString())
            }
            adapter!!.submitList(words)
        }
        Log.e(TAG, "wordList:" + model!!.wordList)
    }

    override fun onDestroyView() {
        super.onDestroyView()
        binding = null
    }
}