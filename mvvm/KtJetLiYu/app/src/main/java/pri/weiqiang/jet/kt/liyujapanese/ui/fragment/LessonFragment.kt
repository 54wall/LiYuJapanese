package pri.weiqiang.jet.kt.liyujapanese.ui.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import pri.weiqiang.jet.kt.liyujapanese.R
import pri.weiqiang.jet.kt.liyujapanese.databinding.FragmentLessonBinding

/**
 * A simple [Fragment] subclass as the second destination in the navigation.
 */
class LessonFragment : Fragment() {

    private var _binding: FragmentLessonBinding? = null

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
            findNavController().navigate(R.id.action_LessonFragment_to_WordFragment)
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}