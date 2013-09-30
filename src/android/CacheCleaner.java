package org.felixtioh.phonegap.plugins;
 
import java.io.File;
 
import org.apache.cordova.CallbackContext;
import org.apache.cordova.CordovaPlugin;
import org.apache.cordova.PluginResult;
import org.json.JSONArray;
 
import android.util.Log;
import android.widget.Toast;
 
public class CacheCleaner extends CordovaPlugin {
 
  @Override
  public boolean execute(String action, final JSONArray args, final CallbackContext callbackContext) {
		if (action.equals("delete")) {
			cordova.getThreadPool().execute(new Runnable() {
				@Override
				public void run() {
					try {
						File dir = cordova.getActivity().getCacheDir();
						if (dir != null && dir.isDirectory()) {
							deleteDir(dir);
							showToast("App cache is deleted.","short");
						}
					} catch (Exception e) {
						e.printStackTrace();
						Log.e("PhoneGapLog", "CacheCleaner Plugin: Error: " + PluginResult.Status.ERROR);
						callbackContext.sendPluginResult(new PluginResult(PluginResult.Status.ERROR));
					}
				}
			});
			callbackContext.sendPluginResult(new PluginResult(PluginResult.Status.OK));
			return true;
		} else {
			Log.e("PhoneGapLog", "CacheCleaner Plugin: Error: " + PluginResult.Status.INVALID_ACTION);
			callbackContext.sendPluginResult(new PluginResult(PluginResult.Status.INVALID_ACTION));
			return false;
		}
	}
 
	public static boolean deleteDir(File dir) {
		if (dir != null && dir.isDirectory()) {
			String[] children = dir.list();
			for (int i = 0; i < children.length; i++) {
				boolean success = deleteDir(new File(dir, children[i]));
				if (!success) {
					return false;
				}
			}
			// dir.delete(); // delete the directory ITSELF. Grayed out, because it cripples the app until the app is fully restarted
		} else if (dir != null && dir.isFile()) {
			dir.delete(); // delete the file INSIDE the directory
		}
		return true;
	}
 
	private void showToast(final String message, final String duration) {
		cordova.getActivity().runOnUiThread(new Runnable() {
			@Override
			public void run() {
				Toast toast;
				if(duration.equals("long")) {
					toast = Toast.makeText(cordova.getActivity(), message, Toast.LENGTH_LONG);
				} else {
					toast = Toast.makeText(cordova.getActivity(), message, Toast.LENGTH_SHORT);
				}
				toast.show();
			}
		});
	}
}