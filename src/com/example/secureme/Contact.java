package com.example.secureme;

public class Contact {
	//private variables
		private int _id;
		private String _email;
		private String _phone_number;
		private String _sim;
		
		// Empty constructor
		public Contact(){
			
		}
		// constructor
		public Contact(int id, String email, String _phone_number,String sim){
			this._id = id;
			this._email =email;
			this._phone_number = _phone_number;
			this._sim=sim;
		}
		
		// constructor
		public Contact(String email, String _phone_number,String sim){
			this._email =email;
			this._phone_number = _phone_number;
			this._sim=sim;
		}
		// getting ID
		public int getID(){
			return this._id;
		}
		
		// setting id
		public void setID(int id){
			this._id = id;
		}
		
		// getting name
		public String getEmail(){
			return this._email;
		}
		
		// setting name
		public void setEmail(String email){
			this._email=email;
		}
		
		// getting phone number
		public String getPhoneNumber(){
			return this._phone_number;
		}
		
		// setting phone number
		public void setPhoneNumber(String phone_number){
			this._phone_number=phone_number;
		}
		public String getSim(){
			return this._sim;
		}
		
		// setting phone number
		public void setSim(String sim){
			this._sim=sim;
		}
		
	}


