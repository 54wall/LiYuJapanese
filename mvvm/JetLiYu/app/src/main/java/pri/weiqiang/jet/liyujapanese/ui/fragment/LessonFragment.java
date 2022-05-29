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
import pri.weiqiang.jet.liyujapanese.R;
import pri.weiqiang.jet.liyujapanese.databinding.FragmentLessonBinding;
import pri.weiqiang.jet.liyujapanese.ui.viewmodel.LessonFragmentViewModel;

public class LessonFragment extends Fragment {

    private static final String TAG = LessonFragment.class.getSimpleName();
    private FragmentLessonBinding binding;
    private LessonFragmentViewModel model;

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

        binding.buttonSecond.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                NavHostFragment.findNavController(LessonFragment.this)
                        .navigate(R.id.action_LessonFragment_to_WordFragment);
            }
        });

        model = new ViewModelProvider(this).get(LessonFragmentViewModel.class);
        model.getLessonList().observe(getViewLifecycleOwner(), lessons -> {
            if (lessons != null && lessons.size() != 0) {
                Log.e(TAG, "lesson.get(0):" + lessons.get(0).toString());
            }
        });
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }

}