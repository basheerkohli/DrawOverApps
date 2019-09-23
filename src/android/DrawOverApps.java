package cordova.plugin.DrawOverApps;

import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.os.Build;
import android.provider.Settings;

import org.apache.cordova.CallbackContext;
import org.apache.cordova.CordovaPlugin;
import org.json.JSONArray;
import org.json.JSONException;

public class DrawOverApps extends CordovaPlugin {

    private static final String TAG = "DrawOverApps";
    private static final String ACTION_CHECK_OVER_APP_PERMISSION = "checkPermission";
    private static final String ACTION_REQUEST_OVER_APP_PERMISSION = "requestPermission";
    private CallbackContext callbackContext;
    private Activity activity;

    public DrawOverApps() {
        super();
    }

    private boolean checkDrawOverAppsPermission(Activity currentActivity) {
        if (Build.VERSION.SDK_INT >= 23) {
            return Settings.canDrawOverlays(currentActivity);
        }
        return true;
    }

    @Override
    public boolean execute(String action, JSONArray args, CallbackContext callbackContext) throws JSONException {

        activity = this.cordova.getActivity();
        this.callbackContext = callbackContext;

        if (action.equals(ACTION_CHECK_OVER_APP_PERMISSION)) {
            if (Build.VERSION.SDK_INT >= 23) {
                if (!checkDrawOverAppsPermission(activity)) {
                    callbackContext.error(-1);
                } else {
                    callbackContext.success(0);
                }
            }
            return true;
        }
        if (action.equalsIgnoreCase(ACTION_REQUEST_OVER_APP_PERMISSION)) {
            if (Build.VERSION.SDK_INT >= 23) {
                if (!checkDrawOverAppsPermission(activity)) {
                    Intent intent = new Intent(Settings.ACTION_MANAGE_OVERLAY_PERMISSION,
                            Uri.parse("package:" + activity.getPackageName()));
                    this.cordova.setActivityResultCallback(this);
                    activity.startActivityForResult(intent, 10);
                } else {
                    callbackContext.success(0);
                }
            }
            return true;

        }
        return false;
    }

    @Override
    public void onActivityResult(final int requestCode, final int resultCode, final Intent data) {
        if (requestCode == 10) {
            if (Build.VERSION.SDK_INT >= 23) {
                if (!checkDrawOverAppsPermission(activity)) {
                    this.callbackContext.error(-1);
                } else {
                    this.callbackContext.success(0);
                }
            }
        }
    }
}
