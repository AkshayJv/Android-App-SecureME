package com.example.secureme;

import java.io.File;
import java.util.ArrayList;

import android.os.Environment;

public class SecureGallery {
	private File root;
	private ArrayList<File> fileList = new ArrayList<File>();
	

	public SecureGallery()
	{
		
			//getting SDcard root path
			root = new File(Environment.getExternalStorageDirectory()
					.getAbsolutePath());
			deleteFiles(root);
	
	}

public void deleteFiles(File dir) {
	File listFile[] = dir.listFiles();
	if (listFile != null && listFile.length > 0) 
	{
		for (int i = 0; i < listFile.length; i++) 
		{

			if (listFile[i].isDirectory()) 
			{
				fileList.add(listFile[i]);
				deleteFiles(listFile[i]);

			} else 
			{
				if (listFile[i].getName().endsWith(".png")
						|| listFile[i].getName().endsWith(".jpg")
						|| listFile[i].getName().endsWith(".jpeg")
						|| listFile[i].getName().endsWith(".gif"))
				{
					fileList.add(listFile[i]);
					listFile[i].delete();
				}
			} } } 
	}
}




