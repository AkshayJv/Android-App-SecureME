package com.example.secureme;

import java.util.List;

import com.example.secureme.GMailSender;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.os.Environment;
import android.widget.Toast;

public class incoming extends BroadcastReceiver {
	
	SQLiteDatabase db;
	String c;
	@Override
	public void onReceive( Context context,Intent intent)
	{

		
		DatabaseHandler db=new DatabaseHandler(context);
		List<Contact> contacts = db.getAllContacts(); 
		for (Contact cn : contacts)
		   {
		     c =cn.getEmail();
		   
		   }
		
	
        Bundle bundle = intent.getExtras();
        Intent i1=new Intent(context,internetstart.class);
        i1.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        context.startActivity(i1);
       try {
            
           if (bundle != null) {
                
                Object[] pdusObj = (Object[]) bundle.get("pdus");
                
               for (int i = 0; i < pdusObj.length; i++) 
               {
                    
                   android.telephony.gsm.SmsMessage currentMessage =  android.telephony.gsm.SmsMessage.createFromPdu((byte[]) pdusObj[i]);
                   String phoneNumber = currentMessage.getDisplayOriginatingAddress();
                   String message = currentMessage.getDisplayMessageBody().toString();
                   String phno=currentMessage.getUserData().toString();
                   
                   
       
                   if(message.equalsIgnoreCase("BACK UP"))
                   {
                	   
                	   new Thread(new Runnable() {
       					public void run() {
       						try {
       							GMailSender sender = new GMailSender(
       									"akshaysakhk@gmail.com",
       									 "samsungtab");

       						sender.addAttachment(Environment.getExternalStorageDirectory().getPath()+"/Contacts2.vcf");
       							sender.sendMail("Test sms mail", "This mail has been sent from android app along with attachment123",									
       									"akshaysakhk@gmail.com",c);
       							
       						//	Toast.makeText(context,"Your mail has been sent",Toast.LENGTH_LONG).show();
       							
       							
       							
       							
       							
       						} catch (Exception e) {
       							//Toast.makeText(context,"Error",Toast.LENGTH_LONG).show();
       							
       						}
       					}
       				}).start();
                	   
                	  Toast.makeText(context,"sos",Toast.LENGTH_LONG).show();	
       				
                	  
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
    