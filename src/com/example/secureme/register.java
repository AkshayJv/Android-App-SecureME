package com.example.secureme;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.telephony.TelephonyManager;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;

public class register extends Activity {
	
 Button insert,move;
 EditText edtsim,edtcontactnumber,edtemail;
 DatabaseHandler db = new DatabaseHandler(this);
	String simSerialNumber="";
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.register);
		
		insert=(Button)findViewById(R.id.buttonregister);
		edtcontactnumber=(EditText)findViewById(R.id.editTextcontactnumber);
		edtemail=(EditText)findViewById(R.id.editTextemailid);
	    edtsim=(EditText)findViewById(R.id.editTextsimnumber);
	    move=(Button) findViewById(R.id.button1);
		 TelephonyManager tel=(TelephonyManager)this.getSystemService(Context.TELEPHONY_SERVICE);
	     simSerialNumber=tel.getSimSerialNumber();
	     edtsim.setText(simSerialNumber);
	 	
		
	   click();
	   move();
	}

	public void click()
	{
	insert.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View arg0)
			{
				String email,phone;
				
				email=edtemail.getText().toString();
				phone=edtcontactnumber.getText().toString();
				
				Contact contact=new Contact(email,phone,simSerialNumber);
				db.addContact(contact);
				
				Log.d("Inserted: ", "Inserted record");
			}
		});
	}
	public void move()
	{
	
	move.setOnClickListener(new OnClickListener() {
		
		@Override
		public void onClick(View arg0) {
			// TODO Auto-generated method stub
			Intent next1=new Intent(getApplicationContext(),display.class);
			startActivity(next1);
		}
	});
	
		
	}
}

