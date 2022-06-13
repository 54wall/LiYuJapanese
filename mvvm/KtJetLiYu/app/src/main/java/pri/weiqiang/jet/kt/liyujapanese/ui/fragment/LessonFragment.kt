package pri.weiqiang.jet.kt.liyujapanese.ui.fragment

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import pri.weiqiang.jet.kt.liyujapanese.R
import pri.weiqiang.jet.kt.liyujapanese.data.bean.Lesson
import pri.weiqiang.jet.kt.liyujapanese.databinding.FragmentLessonBinding
import pri.weiqiang.jet.kt.liyujapanese.ui.viewmodel.LessonFragmentViewModel

/**
 * A simple [Fragment] subclass as the second destination in the navigation.
 */
class LessonFragment : Fragment() {
    private val TAG = LessonFragment::class.java.simpleName
    private var _binding: FragmentLessonBinding? = null
    private var model:LessonFragmentViewModel?=null
    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        _binding = FragmentLessonBinding.inflate(inflater, container, false)
        return binding.root

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.buttonSecond.setOnClickListener {
//            findNavController().navigate(R.id.action_LessonFragment_to_WordFragment)
            model!!.getUsers()!!.observe(viewLifecycleOwner){lessons:List<Lesson> ->
                if (lessons != null && lessons.isNotEmpty()) {
                    Log.e(TAG, " lessons.get(0):" + lessons[0].toString())
                }
            }
        }

        model = ViewModelProvider(this).get(LessonFragmentViewModel::class.java)


    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}