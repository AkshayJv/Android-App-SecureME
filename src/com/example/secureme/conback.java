package com.example.secureme;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.util.ArrayList;

import android.app.Activity;
import android.content.Context;
import android.content.res.AssetFileDescriptor;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.provider.ContactsContract;
import android.util.Log;

public class conback extends Activity {
    Cursor cursor;
    ArrayList<String> vCard;
    String vfile;
    static Context mContext;

    /** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle savedInstanceState) {
          super.onCreate(savedInstanceState);
          setContentView(R.layout.activity_main);
          mContext = conback.this;
          getVCF();
    }

    public static void getVCF() {
          final String vfile = "Contacts2.vcf";
          Cursor phones = mContext.getContentResolver().query(
          ContactsContract.CommonDataKinds.Phone.CONTENT_URI, null,
                                null, null, null);
          phones.moveToFirst();
          for (int i = 0; i < phones.getCount(); i++) {
                 String lookupKey = phones.getString(phones
                         .getColumnIndex(ContactsContract.Contacts.LOOKUP_KEY));
                 Uri uri = Uri.withAppendedPath(
                 ContactsContract.Contacts.CONTENT_VCARD_URI,
                                               lookupKey);
                 AssetFileDescriptor fd;
                 try {
                         fd = mContext.getContentResolver().openAssetFileDescriptor(uri, "r");
                         FileInputStream fis = fd.createInputStream();
                         byte[] buf = new byte[(int) fd.getDeclaredLength()];
                         fis.read(buf);
                         String VCard = new String(buf);
                         String path = Environment.getExternalStorageDirectory()
                                  .toString() + File.separator + vfile;
                         FileOutputStream mFileOutputStream = new FileOutputStream(path,
                                   true);
                         mFileOutputStream.write(VCard.toString().getBytes());
                         phones.moveToNext();
                         Log.d("Vcard", VCard);
                 } catch (Exception e1) {
                         // TODO Auto-generated catch block
                         e1.printStackTrace();
                 }
          }
    }
}
