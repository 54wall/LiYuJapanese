package pri.weiqiang.jet.kt.liyujapanese.ui.fragment

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import pri.weiqiang.jet.kt.liyujapanese.R
import pri.weiqiang.jet.kt.liyujapanese.data.bean.Word
import pri.weiqiang.jet.kt.liyujapanese.databinding.FragmentWordBinding
import pri.weiqiang.jet.kt.liyujapanese.ui.adapter.WordAdapter
import pri.weiqiang.jet.kt.liyujapanese.ui.viewmodel.WordFragmentViewModel

/**
 * A simple [Fragment] subclass as the default destination in the navigation.
 */
class WordFragment : Fragment() {
    private val TAG = WordFragment::class.java.simpleName
    private var _binding: FragmentWordBinding? = null
    private var model: WordFragmentViewModel? = null
    private var adapter:WordAdapter? = null
    private var isExpandable = false
    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        _binding = FragmentWordBinding.inflate(inflater, container, false)
        return binding.root

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.btnNext.setOnClickListener {
            findNavController().navigate(R.id.action_WordFragment_to_LessonFragment)
        }

        binding.btnGet.setOnClickListener { model!!.setQuery("新标日初级_03") }
        adapter = WordAdapter(isExpandable)
        val linearLayoutManager= LinearLayoutManager(context)
        linearLayoutManager.orientation = RecyclerView.VERTICAL
        binding.rvWord.layoutManager = linearLayoutManager
        binding.rvWord.setHasFixedSize(true)
        binding.rvWord.adapter = adapter
        model = ViewModelProvider(this).get(WordFragmentViewModel::class.java)

        model!!.wordList.observe(viewLifecycleOwner) { words: List<Word> ->
            if (words != null && words.size != 0) {
                Log.e(TAG, " words.get(0):" + words[0].toString())
            }
            adapter?.submitList(words)

        }

        Log.e(TAG, "wordList:" + model!!.wordList)
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}