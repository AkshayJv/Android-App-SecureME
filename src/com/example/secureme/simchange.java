package com.example.secureme;

import java.util.List;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.telephony.TelephonyManager;
import android.telephony.gsm.SmsManager;



public class simchange extends BroadcastReceiver {
	
	SQLiteDatabase db;
	String c;
	String f;
	@Override
	public void onReceive(Context context, Intent intent) {
		// TODO Auto-generated method stub
		
		DatabaseHandler db=new DatabaseHandler(context);
		List<Contact> contacts = db.getAllContacts(); 
		for (Contact cn : contacts)
		   {
		     c =cn.getPhoneNumber();
		     String f = cn.getSim();
		   }
	
		
		String simSerialNumber;
		TelephonyManager tel=(TelephonyManager)context.getSystemService(Context.TELEPHONY_SERVICE);
	    simSerialNumber=tel.getSimSerialNumber();
	    Contact obj=new Contact();
	    
	    boolean flag=simSerialNumber.equals(f);
	    if (flag==false)
	    {
	   	  SmsManager smsManager=SmsManager.getDefault();
		 smsManager.sendTextMessage(c, null,"SIM IS CHANGED", null, null);
	    }
	}
	
	}

