package pri.weiqiang.jet.liyujapanese.ui.fragment

import pri.weiqiang.jet.liyujapanese.ui.viewmodel.LessonFragmentViewModel
import android.view.LayoutInflater
import android.view.ViewGroup
import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.NavHostFragment
import pri.weiqiang.jet.liyujapanese.R
import androidx.lifecycle.ViewModelProvider
import pri.weiqiang.jet.liyujapanese.data.bean.Lesson
import pri.weiqiang.jet.liyujapanese.databinding.FragmentLessonBinding
import pri.weiqiang.jet.liyujapanese.ui.fragment.LessonFragment

class LessonFragment : Fragment() {
    private var binding: FragmentLessonBinding? = null
    private var model: LessonFragmentViewModel? = null
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentLessonBinding.inflate(inflater, container, false)
        return binding!!.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding!!.buttonSecond.setOnClickListener {
            NavHostFragment.findNavController(this@LessonFragment)
                .navigate(R.id.action_LessonFragment_to_WordFragment)
        }
        model = ViewModelProvider(this).get(LessonFragmentViewModel::class.java)
        model!!.lessonList.observe(viewLifecycleOwner) { lessons: List<Lesson>? ->
            if (lessons != null && lessons.size != 0) {
                Log.e(TAG, "lesson.get(0):" + lessons[0].toString())
            }
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        binding = null
    }

    companion object {
        private val TAG = LessonFragment::class.java.simpleName
    }
}