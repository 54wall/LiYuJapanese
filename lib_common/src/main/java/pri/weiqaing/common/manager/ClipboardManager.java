package pri.weiqaing.common.manager;

import android.content.ClipData;
import android.content.Context;

import pri.weiqaing.common.base.BaseApplication;

public class ClipboardManager {


    private volatile static ClipboardManager instance;
    private android.content.ClipboardManager clipboardManager;

    private ClipboardManager() {

        clipboardManager = (android.content.ClipboardManager) BaseApplication.getInstance().getSystemService(Context.CLIPBOARD_SERVICE);

    }


    public static ClipboardManager getInstance() {
        if (instance == null) {
            synchronized (ClipboardManager.class) {
                if (instance == null) {
                    instance = new ClipboardManager();
                }
            }
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
