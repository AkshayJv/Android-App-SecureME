package com.example.secureme;



import java.util.ArrayList;
import java.util.List;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class DatabaseHandler extends SQLiteOpenHelper {


	// All Static variables
	// Database Version
	private static final int DATABASE_VERSION = 1;

	// Database Name
	private static final String DATABASE_NAME = "SecureDB1";

	// Contacts table name
	private static final String TABLE_CONTACTS = "contacts";

	// Contacts Table Columns names
		private static final String key_id = "id";
		private static final String key_email= "email";
		private static final String key_phno= "phno";
		private static final String key_sim="sim";
		
	public DatabaseHandler(Context context) {
	super(context, DATABASE_NAME, null, DATABASE_VERSION);
}
	
	// Creating Tables
	@Override
	public void onCreate(SQLiteDatabase db)
 {
String CREATE_CONTACTS_TABLE = "CREATE TABLE " +TABLE_CONTACTS+ "("
				+ key_id + " INT PRIMARY KEY," + key_email + " TEXT,"
				+ key_phno +" TEXT,"+key_sim+" TEXT"+ ")";
		db.execSQL(CREATE_CONTACTS_TABLE);
	}

	@Override
	public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
		// TODO Auto-generated method stub
		// Drop older table if existed
				db.execSQL("DROP TABLE IF EXISTS " + TABLE_CONTACTS);

				// Create tables again
				onCreate(db);

	}

	// Adding new contact
	void addContact(Contact contact) {
		SQLiteDatabase db = this.getWritableDatabase();

		ContentValues values = new ContentValues();
		values.put(key_email,contact.getEmail());
		values.put(key_phno, contact.getPhoneNumber());
        values.put(key_sim,contact.getSim()); 
		// Inserting Row
		db.insert(TABLE_CONTACTS, null, values);
		db.close(); // Closing database connection
	}

	// Getting single contact
		Contact getContact(int id) {
			SQLiteDatabase db = this.getReadableDatabase();

			Cursor cursor = db.query(TABLE_CONTACTS, new String[] { key_id,
					key_email, key_phno }, key_id + "=?",
					new String[] { String.valueOf(id) }, null, null, null, null);
			if (cursor != null)
				cursor.moveToFirst();

			Contact contact = new Contact(Integer.parseInt(cursor.getString(0)),
					cursor.getString(1), cursor.getString(2), null);
			// return contact
			return contact;
		}
		
		// Getting All Contacts
		public List<Contact> getAllContacts() {
			List<Contact> contactList = new ArrayList<Contact>();
			// Select All Query
			String selectQuery = "SELECT email,phno,sim FROM "+TABLE_CONTACTS;

			SQLiteDatabase db = this.getWritableDatabase();
			Cursor cursor = db.rawQuery(selectQuery, null);

			// looping through all rows and adding to list
			if (cursor.moveToFirst())
			{
				do {
					Contact contact = new Contact();
					
					contact.setEmail(cursor.getString(0));
					contact.setPhoneNumber(cursor.getString(1));
					contact.setSim(cursor.getString(2));
					// Adding contact to list
					contactList.add(contact);
				} while (cursor.moveToNext());
			}

			// return contact list
			return contactList;
		}

		// Updating single contact
		public int updateContact(Contact contact) {
			SQLiteDatabase db = this.getWritableDatabase();

			ContentValues values = new ContentValues();
			values.put(key_email, contact.getEmail());
			values.put(key_phno, contact.getPhoneNumber());

			// updating row
			return db.update(TABLE_CONTACTS, values, key_id + " = ?",
					new String[] { String.valueOf(contact.getID()) });
		}

		// Deleting single contact
		public void deleteContact(Contact contact) {
			SQLiteDatabase db = this.getWritableDatabase();
			db.delete(TABLE_CONTACTS, key_id + " = ?",
					new String[] { String.valueOf(contact.getID()) });
			db.close();
		}


		// Getting contacts Count
		public int getContactsCount() {
			int c=0;
			String countQuery = "SELECT  count(*) FROM " + TABLE_CONTACTS;
			SQLiteDatabase db = this.getReadableDatabase();
			Cursor cursor = db.rawQuery(countQuery, null);
			c=cursor.getInt(1);
			cursor.close();

			// return count
			return c;
		}

	}
	

