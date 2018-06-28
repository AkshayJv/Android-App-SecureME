package com.example.secureme;

import java.util.List;



import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;

public class display extends Activity {
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.display);
		final DatabaseHandler db = new DatabaseHandler(this);
	
		Button dis;
		final ListView value;
	
		dis=(Button) findViewById(R.id.button1);
		value=(ListView) findViewById(R.id.listView1);
		
		
	dis.setOnClickListener(new View.OnClickListener() {
		
		@Override
		public void onClick(View arg0) {
			// TODO Auto-generated method stub
	        	       
		
		}
	});
	List<Contact> contacts = db.getAllContacts();       
    
    String[] arr=new String[contacts.size()];
   int i=0;
   for (Contact cn : contacts)
   {
       String c = cn.getEmail()+" "+ cn.getPhoneNumber() + " " + cn.getSim();
       arr[i]=c;
       i++;
   
   }

    ArrayAdapter<String> adapter=new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, arr);
    value.setAdapter(adapter);
}
}