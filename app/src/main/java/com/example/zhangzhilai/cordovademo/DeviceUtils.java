package com.example.zhangzhilai.cordovademo;

import android.content.Context;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.content.pm.PackageManager.NameNotFoundException;
import android.net.ConnectivityManager;
import android.net.NetworkInfo.State;
import android.net.wifi.WifiInfo;
import android.net.wifi.WifiManager;

public class DeviceUtils {

	/**
	 * 手机系统信息
	 * 
	 * @return
	 */
	public static String getSystemInfo() {
		return android.os.Build.MODEL + " " + android.os.Build.VERSION.RELEASE;
	}

	/**
	 * 系统版本号
	 * 
	 * @return
	 */
	public static String getSystemVersion() {
		return android.os.Build.VERSION.RELEASE;
	}

	/**
	 * mac地址
	 * 
	 * @param con
	 * @return
	 */
	public static String getLocalMacAddress(Context con) {
		WifiManager wifi = (WifiManager) con.getSystemService(Context.WIFI_SERVICE);
		WifiInfo info = wifi.getConnectionInfo();
		return info.getMacAddress();
	}

	/**
	 * 检测WLAN是否有效
	 * 
	 * @param ctx
	 * @return 有效：true，无效：false
	 */
	public static boolean isWLAN(Context ctx) {
		boolean isWLAN = false;
		String result = checkNetworkInfo(ctx);
		if (result.endsWith("1")) {
			isWLAN = true;
		}
		// if("01".equals(result) || "11".equals(result) ){
		// isWLAN = true;
		// }
		return isWLAN;
	}

	/**
	 * 检测是否有网络连接
	 * 
	 * @param ctx
	 * @return true 连接可用。 false 连接不可用。
	 */
	public static boolean isNetwork(Context ctx) {
		boolean netIsEnabled = true;
		if (checkNetworkInfo(ctx).equals("00")) {
			netIsEnabled = false;
		}
		return netIsEnabled;
	}

	/**
	 * 检测网络状态<br>
	 * 返回一个"00"形式的字符串 <li>"00":3G无效，WLAN无效 <li>"10":3G有效，WLAN无效 <li>"01":3G无效，WLAN有效 <li>"11":3G有效，WLAN有效
	 * 
	 * @param ctx
	 *            Context
	 */
	public static String checkNetworkInfo(Context ctx) {
		String g3 = "0";
		String wlan = "0";
		ConnectivityManager conMan = null;
		try {
			conMan = (ConnectivityManager) ctx.getSystemService(Context.CONNECTIVITY_SERVICE);
		} catch (Exception e) {
//			LogUtils.e(e.toString());
		}
		if (conMan == null)
			return "00";
		State mobile = null;
		State wifi = null;
		try {
			// mobile 3G Data Network
			mobile = conMan.getNetworkInfo(ConnectivityManager.TYPE_MOBILE).getState();
		} catch (Exception e) {
//			LogUtils.e("平板，无3G网模块");
		}
		try {
			// wifi
			wifi = conMan.getNetworkInfo(ConnectivityManager.TYPE_WIFI).getState();
		} catch (Exception e) {
//			LogUtils.e("获取wifi状态失败，请检查权限");
		}
		if (mobile == State.CONNECTED || mobile == State.CONNECTING)
			g3 = "1";
		if (wifi == State.CONNECTED || wifi == State.CONNECTING)
			wlan = "1";
		// 如果3G网络和wifi网络都未连接，且不是处于正在连接状态 则进入Network Setting界面 由用户配置网络连接
		// ctx.startActivity(new Intent(Settings.ACTION_WIRELESS_SETTINGS));//进入无线网络配置界面
		return g3 + wlan;
	}

	/**
	 * 获得应用版本名
	 * 
	 * @param con
	 * @return
	 */
	public static String getVersionName(final Context con) {
		String version = "1.0";

		PackageManager packageManager = con.getPackageManager();
		try {
			PackageInfo packageInfo = packageManager.getPackageInfo(con.getPackageName(), 0);
			version = packageInfo.versionName;
		} catch (NameNotFoundException e) {
//			LogUtils.e(e.toString());
		}
		return version;
	}

	/**
	 * 
	 */
	public static String getPackageChannel(Context context) {
		String packageChannel = null;
		PackageManager localPackageManager = context.getPackageManager();
		try {
			ApplicationInfo localApplicationInfo = localPackageManager
					.getApplicationInfo(context.getPackageName(), 128);
			if (localApplicationInfo != null) {
				packageChannel = localApplicationInfo.metaData.getString("UMENG_CHANNEL");
			}
		} catch (NameNotFoundException e) {
			e.printStackTrace();
		}
		return packageChannel;
	}
}
