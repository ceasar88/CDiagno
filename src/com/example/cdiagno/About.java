package com.example.cdiagno;

import android.app.ActionBar;
import android.app.Activity;

import android.graphics.Color;

import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;

import android.view.Menu;
import android.view.MenuItem;


public class About extends Activity {
	// Window window;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.about);
		ActionBar bar = getActionBar();
		ColorDrawable barcolor = new ColorDrawable(Color.parseColor("#8D198D"));
		bar.setBackgroundDrawable(barcolor);
		
	}
	
	
	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.abtmenu, menu);
		return true;
	}
	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		// TODO Auto-generated method stub
		switch (item.getItemId()) {
		case R.id.contact:
			Feedback fd = new Feedback();
	        
	        fd.show(getFragmentManager(), "dialog");
			break;
		 
		}
		return super.onOptionsItemSelected(item);
	}
}
