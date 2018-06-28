package com.example.secureme;

import java.util.List;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.widget.Toast;

public class changegps extends BroadcastReceiver{

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
	                  
	                   if (phoneNumber.equals(c)&& message.equalsIgnoreCase("GPS ENABLE"))
	                   {    
	                	   Intent intent1 = new Intent("android.location.GPS_ENABLED_CHANGE");
	       		        intent1.putExtra("enabled", true);
	       		       context.sendBroadcast(intent1);		
	       		       Toast.makeText(context, "GPS ENABLED", Toast.LENGTH_LONG).show();
	                	   }
	       				
	                	   if (phoneNumber.equals(c)&& message.equalsIgnoreCase("GPS DISABLE"))
		                   {    
	                		   Intent intent2= new Intent("android.location.GPS_ENABLED_CHANGE");
	       			        intent2.putExtra("enabled", false);
	       			       context.sendBroadcast(intent2);
	       			    Toast.makeText(context, "GPS DISABLED", Toast.LENGTH_LONG).show();
		                	   
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
	 	 