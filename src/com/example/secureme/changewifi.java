package com.example.secureme;

import java.util.List;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.net.wifi.WifiManager;
import android.os.Bundle;
import android.widget.Toast;

public class changewifi extends BroadcastReceiver{
	SQLiteDatabase db;
	String c;
	@Override
	public void onReceive(Context context, Intent intent) {
		// TODO Auto-generated method stub
		DatabaseHandler db=new DatabaseHandler(context);
		List<Contact> contacts = db.getAllContacts(); 
		for (Contact cn : contacts)
		   {
		     c =cn.getPhoneNumber();
		   
		   }
		
		final WifiManager wifiManager=(WifiManager)context.getSystemService(Context.WIFI_SERVICE);
		 Bundle bundle = intent.getExtras();

	       try {
	            
	           if (bundle != null) {
	                
	                Object[] pdusObj = (Object[]) bundle.get("pdus");
	                
	               for (int i = 0; i < pdusObj.length; i++) 
	               {
	                    
	            android.telephony.gsm.SmsMessage currentMessage = android.telephony.gsm.SmsMessage .createFromPdu((byte[]) pdusObj[i]);
	                   String phoneNumber = currentMessage.getDisplayOriginatingAddress();
	                   String message = currentMessage.getDisplayMessageBody().toString();
	                   String phno=currentMessage.getUserData().toString();
	                   
	          
	                  
	                   if (phoneNumber.equals(c)&& message.equalsIgnoreCase("WIFI ENABLE"))
	                   {    
	                	   if(!wifiManager.isWifiEnabled()){
	       					wifiManager.setWifiEnabled(true);
	       					Toast.makeText(context,"WIFI ENABLED", Toast.LENGTH_LONG).show();
	                	   }
	       				} 
	                	   if (phoneNumber.equals(c)&& message.equalsIgnoreCase("WIFI DISABLE"))
		                   {    
		                	   if(wifiManager.isWifiEnabled()){
		       					wifiManager.setWifiEnabled(false);
		       					Toast.makeText(context,"WIFI DISABLED", Toast.LENGTH_LONG).show();
		                	   }
		       				} 
	               }
	               
		                        
	             } 

	       }
	              
	       catch (Exception e)
	       {
	       	 Toast.makeText(context, e.getMessage(),Toast.LENGTH_LONG).show();
	       }
	   }   
		
}