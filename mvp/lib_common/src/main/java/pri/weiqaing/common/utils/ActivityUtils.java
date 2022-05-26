package pri.weiqaing.common.utils;

import android.content.Intent;
import android.net.Uri;

import pri.weiqaing.common.R;
import pri.weiqaing.common.manager.ActivityManager;

public class ActivityUtils {

    private ActivityUtils() {
        throw new RuntimeException("RuntimeException");
    }

    public static void share(String message) {
        Intent intent = new Intent(Intent.ACTION_SEND);
        intent.setType("text/plain");
        intent.putExtra(Intent.EXTRA_TEXT, message);
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);

        ActivityManager.getCurrent().startActivity(Intent.createChooser(intent, ResourceUtils.getString(ActivityManager.getCurrent(), R.string.share_to)));

    }

    public static void openUrl(String url) {

        Intent intent = new Intent(Intent.ACTION_VIEW);
        intent.setData(Uri.parse(url));

        ActivityManager.getCurrent().startActivity(intent);

    }
}
