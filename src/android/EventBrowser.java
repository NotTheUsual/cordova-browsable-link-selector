package com.megaphone.cordova.events;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import android.content.Intent;
import android.app.Activity;
import org.apache.cordova.CordovaActivity;
import org.apache.cordova.CallbackContext;
import org.apache.cordova.CordovaPlugin;
import org.apache.cordova.CordovaResourceApi;
import org.apache.cordova.PluginResult;
import org.apache.cordova.CordovaWebView;
import org.apache.cordova.CordovaInterface;
import android.util.Log;


public class EventBrowser extends CordovaPlugin {

	private static final String ACTION_SHOW_BROWSER = "showBrowser";
	private static final String NOT_SUPPORTED_ERROR = "Plugin not supported on this platform";
	private CallbackContext callbackContext;

	@Override
	public boolean execute(String action, JSONArray args, final CallbackContext callbackContext) throws JSONException {
		this.callbackContext = callbackContext;

        if (android.os.Build.VERSION.RELEASE.startsWith("1.")) {
            callbackContext.sendPluginResult(new PluginResult(PluginResult.Status.ERROR, EventBrowser.NOT_SUPPORTED_ERROR));
            return true;
        }

		if (action.equals(ACTION_SHOW_BROWSER)) {
			Intent intent = new Intent(this.cordova.getActivity().getApplicationContext(), EventBrowserActivity.class);
			this.cordova.startActivityForResult((CordovaPlugin) this, intent, 1);
			PluginResult r = new PluginResult(PluginResult.Status.NO_RESULT);
			r.setKeepCallback(true);
			callbackContext.sendPluginResult(r);
			return true;
		}

		return false;
		
	}

	public void onActivityResult(int requestCode, int resultCode, Intent intent) {
		// If image available
		if (resultCode == Activity.RESULT_OK) {
			String url = intent.getStringExtra("url");
			this.callbackContext.sendPluginResult(new PluginResult(PluginResult.Status.OK, url));
		}
	}

}