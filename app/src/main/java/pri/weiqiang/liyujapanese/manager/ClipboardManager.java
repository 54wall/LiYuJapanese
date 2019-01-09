package pri.weiqiang.liyujapanese.manager;

import android.content.ClipData;
import android.content.Context;

import pri.weiqiang.liyujapanese.MyApplication;

public class ClipboardManager {


    private static ClipboardManager instance;
    private android.content.ClipboardManager clipboardManager;

    private ClipboardManager() {

        clipboardManager = (android.content.ClipboardManager) MyApplication.getInstance().getSystemService(Context.CLIPBOARD_SERVICE);

    }


    public static synchronized ClipboardManager getInstance() {

        if (instance == null) {
            instance = new ClipboardManager();
        }

        return instance;

    }

    public String getText() {
        return clipboardManager.getPrimaryClip().getItemAt(0).getText().toString();
    }

    public void setText(String label, String text) {
        clipboardManager.setPrimaryClip(ClipData.newPlainText(label, text));
    }

}
