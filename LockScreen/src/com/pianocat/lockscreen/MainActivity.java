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
		//��ȡ�豸�������
		dpm = (DevicePolicyManager) getSystemService(Context.DEVICE_POLICY_SERVICE);
		
		cn = new ComponentName(this, LockScreenAdmin.class);
		//�����Ȩ�ޣ�ֱ������
		if(dpm.isAdminActive(cn)){
			dpm.lockNow();
			onDestroy();
		}
		//���û��Ȩ�ޣ��Ȼ�ȡȨ�ޣ�Ȼ������
		else{
			//�����豸����(��ʽIntent),��AndroidManifest.xml���趨��Ӧ������
			Intent i = new Intent(DevicePolicyManager.ACTION_ADD_DEVICE_ADMIN);
			//Ȩ���б�,ָ�����ĸ������Ȩ
			i.putExtra(DevicePolicyManager.EXTRA_DEVICE_ADMIN, cn);
			//����������
			i.putExtra(DevicePolicyManager.EXTRA_ADD_EXPLANATION, "ע����������ʹ����������");
			startActivity(i);
		}
		
	}
	
}
