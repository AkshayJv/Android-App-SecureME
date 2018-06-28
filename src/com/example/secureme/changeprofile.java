package com.example.secureme;

import java.util.List;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.media.AudioManager;
import android.os.Bundle;
import android.widget.Toast;

public class changeprofile extends BroadcastReceiver {
	
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
		
		AudioManager audioManager=(AudioManager)context.getSystemService(Context.AUDIO_SERVICE);
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
	                   
	            
	
	              
	                  /* code is changed here*/
	                  
	                   if (phoneNumber.equals(c)&& message.equalsIgnoreCase("VIBRATE"))
	                   {    
	                	   audioManager.setRingerMode(AudioManager.RINGER_MODE_VIBRATE);
	                	   Toast.makeText(context,"PROFILE CHANGED TO VIBRATE ", Toast.LENGTH_LONG).show(); 
	                   }
	                   if(phoneNumber.equals(c)&&message.equalsIgnoreCase("SILENT"))
	                   {
	                	   audioManager.setRingerMode(AudioManager.RINGER_MODE_SILENT);
	                	   Toast.makeText(context,"PROFILE CHANGED TO SILENT",Toast.LENGTH_LONG).show();
	                   }
	                   if(phoneNumber.equals(c)&&message.equalsIgnoreCase("NORMAL"))
	                   {
	                	    audioManager.setRingerMode(AudioManager.RINGER_MODE_NORMAL);
	                	    Toast.makeText(context,"PROFILE CHANGED TO NORMAL",Toast.LENGTH_LONG).show();
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
