package com.pianocat.lockscreen;

import android.app.Activity;
import android.app.admin.DevicePolicyManager;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;

public class MainActivity extends Activity {

	private DevicePolicyManager dpm = null;
	ComponentName cn = null;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		//获取设备管理服务
		dpm = (DevicePolicyManager) getSystemService(Context.DEVICE_POLICY_SERVICE);
		
		cn = new ComponentName(this, LockScreenAdmin.class);
		//如果有权限，直接锁屏
		if(dpm.isAdminActive(cn)){
			dpm.lockNow();
			onDestroy();
		}
		//如果没有权限，先获取权限，然后锁屏
		else{
			//启动设备管理(隐式Intent),在AndroidManifest.xml中设定相应过滤器
			Intent i = new Intent(DevicePolicyManager.ACTION_ADD_DEVICE_ADMIN);
			//权限列表,指定给哪个组件授权
			i.putExtra(DevicePolicyManager.EXTRA_DEVICE_ADMIN, cn);
			//添加相关描述
			i.putExtra(DevicePolicyManager.EXTRA_ADD_EXPLANATION, "注册组件后才能使用锁屏功能");
			startActivity(i);
		}
		
	}
	
}
