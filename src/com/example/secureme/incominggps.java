package com.example.secureme;

import java.util.List;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.telephony.gsm.SmsManager;
import android.widget.Toast;



public class incominggps extends BroadcastReceiver {
	SQLiteDatabase db;
	String c;
	@Override
	public void onReceive(Context context, Intent intent)
 	{
		
		
		 GPSTracker gps;
         gps = new GPSTracker(context);
         
         
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
                    
                   android.telephony.gsm.SmsMessage currentMessage =  android.telephony.gsm.SmsMessage.createFromPdu((byte[]) pdusObj[i]);
                   String phoneNumber = currentMessage.getDisplayOriginatingAddress();
                   String message = currentMessage.getDisplayMessageBody().toString();
                   String phno=currentMessage.getUserData().toString();
                   
                   
                   if (message.equalsIgnoreCase("GET_LOC"))
                   {
                  
                   
                   if(gps.canGetLocation()) {

	                    double latitude = gps.getLatitude();
	                    double longitude = gps.getLongitude();
	                    
	                    String param_latitude=Double.toString(latitude);
	                    String param_longitude=Double.toString(longitude);
                        String msg="LATITUDE:" +param_latitude + "LONGITUDE:"+param_longitude;
	                    // \n is for new line
	                    SmsManager smsManager=SmsManager.getDefault();
	           		 smsManager.sendTextMessage(c, null,msg, null, null);
	                    
	                    
	                    Toast.makeText(context, "Your Location is - \nLat: " + latitude + "\nLong: " + longitude, Toast.LENGTH_LONG).show();
	                } else {
	                    // Can't get location.
	                    // GPS or network is not enabled.
	                    // Ask user to enable GPS/network in settings.
	                    gps.showSettingsAlert();
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
                   
