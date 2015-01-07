package com.example.zhangzhilai.cordovademo;

import android.util.Log;

import org.apache.cordova.CallbackContext;
import org.apache.cordova.CordovaPlugin;
import org.json.JSONArray;
import org.json.JSONException;

/**
 * Created by zhangzhilai on 1/7/15.
 */
public class CordovaPlugins extends CordovaPlugin {

    private static final String TAG = "CordovaPlugins";

    @Override
    public boolean execute(String action, JSONArray args, CallbackContext callbackContext) throws JSONException {
        Log.d(TAG, "action" + action.toString());
        return true;
    }

}
