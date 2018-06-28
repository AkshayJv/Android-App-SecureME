package com.example.secureme;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.*;


public class MainActivity extends Activity {
Button btn1,btn2;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		btn1=(Button)findViewById(R.id.button1);
		btn2=(Button)findViewById(R.id.button2);
	    btnclick();
	}
		
	
	private void btnclick() {
		// TODO Auto-generated method stub
btn1.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View arg0) {
				// TODO Auto-generated method stud
				{
				Intent next=new Intent(MainActivity.this,register.class);
				startActivity(next);
				}
			
			}
		});
	}


	
}
