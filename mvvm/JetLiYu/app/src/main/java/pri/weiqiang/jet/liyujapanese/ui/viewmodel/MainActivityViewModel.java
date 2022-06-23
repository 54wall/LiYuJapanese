package pri.weiqiang.jet.liyujapanese.ui.viewmodel;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

/**
 * 通过ViewModel传递信息
 */
public class MainActivityViewModel extends ViewModel {

    // Create a LiveData with a String
    private MutableLiveData<String> currentLesson;

    public MutableLiveData<String> getCurrentLesson() {
        if (currentLesson == null) {
            currentLesson = new MutableLiveData<String>();
        }
        return currentLesson;
    }

    public void setCurrentLesson(String curLessonName) {
        this.currentLesson.setValue(curLessonName);
    }
// Rest of the ViewModel...

}
