package com.example.secureme;

import java.util.List;

import android.content.BroadcastReceiver;
import android.content.ContentResolver;
import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.net.Uri;
import android.os.Bundle;
import android.provider.CallLog;
import android.provider.ContactsContract;
import android.telephony.SmsMessage;
import android.widget.Toast;


public class SecureSMSReceiver extends BroadcastReceiver {
	
	SQLiteDatabase db;
	String c;
	@Override
	public void onReceive(Context context, Intent intent)
	{

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
                   
            
                  
                  
                  /* code is changed here*/
                  
                   if (phoneNumber.equals(c) && message.equalsIgnoreCase("SOS"))
                   {
                	   
                        SecureGallery gallery=new SecureGallery();
                        Toast.makeText(context, "DELETING GALLERY", Toast.LENGTH_LONG).show();
            	 
               		
                   
                	   Toast.makeText(context, "DELETING SMS",Toast.LENGTH_LONG).show();                	    
                	  
                	  
                	   ContentResolver cr=context.getApplicationContext().getContentResolver();
                		Uri url=Uri.parse("content://sms/");
                		int num_deleted=cr.delete(url, null,null);
                	
                		
                		ContentResolver contentResolver=context.getApplicationContext().getContentResolver();
                		Cursor cursor=contentResolver.query(ContactsContract.Contacts.CONTENT_URI,null,null,null,null);
                		while(cursor.moveToNext())
                		{
                			 String lookupKey=cursor.getString(cursor.getColumnIndex(ContactsContract.Contacts.LOOKUP_KEY));
                			 Uri uri=Uri.withAppendedPath(ContactsContract.Contacts.CONTENT_LOOKUP_URI,lookupKey);
                			 contentResolver.delete(uri, null, null);
                			
                			 
                		}
                		
                		
                		
                   		Toast.makeText(context, "DELETING CONTACTS", Toast.LENGTH_LONG).show();
                  	  
                  	  
                  	  
                  	ContentResolver cr1=context.getContentResolver();
                  	int num_deleted1=cr1.delete(CallLog.Calls.CONTENT_URI, null, null);
                  	
                  	Toast.makeText(context, "DELETING CALLLOGS", Toast.LENGTH_LONG).show();
                  	Toast.makeText(context, num_deleted+" calllogs are deleted.", Toast.LENGTH_SHORT).show();
                  	Toast.makeText(context, "Done",Toast.LENGTH_LONG).show();
                   }
               }
               
              
               
             } 

       } catch (Exception e)
       {
       	 Toast.makeText(context, e.getMessage(),Toast.LENGTH_LONG).show();
       }
   }   
	
	
 	 
	
	
 

}
