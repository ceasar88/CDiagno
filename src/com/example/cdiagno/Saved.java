package com.example.cdiagno;

import java.util.ArrayList;


import DBjazz.DBstuff;
import android.app.ActionBar;
import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;

import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;

import android.widget.GridView;
import android.widget.Spinner;
import android.widget.Toast;

public class Saved extends Activity implements OnItemClickListener{
GridView grid;
GridAdapter adapter;
Context c ;
Spinner spinner;
//String dates[] = {"1111","2222","3333","4444","5555"};
String dates[];
ArrayList<String> findates = new ArrayList<String>();
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.saved);
		c = this;
		ActionBar bar = getActionBar();
		ColorDrawable barcolor = new ColorDrawable(Color.parseColor("#8D198D"));
		bar.setBackgroundDrawable(barcolor);
		DBstuff getdet = new DBstuff(c);
		getdet.open();
		dates = getdet.getDates();
		getdet.close();
		if(dates==null){
			
			Toast.makeText(this, "NO SAVED RESULTS", Toast.LENGTH_SHORT).show();
		}
		else{
		int h = 0;
		findates.add(dates[0]);
		for(int i=1;i<dates.length;i++){
		 if(dates[i].equals(dates[i-1])){
			 
		 }
		 else{
			 findates.add(dates[i]);
			 h++;
		 }
			
		}
		System.out.println(h);
		
		grid = (GridView)findViewById(R.id.grid);
		adapter = new GridAdapter(c,findates);
		grid.setAdapter(adapter);
		grid.setOnItemClickListener(this);
		}
	}
	@Override
	public void onItemClick(AdapterView<?> arg0, View arg1, int position, long arg3) {
		// TODO Auto-generated method stub
		String ill[];
		String d;
		d = findates.get(position);
		DBstuff getdet = new DBstuff(c);
		getdet.open();
		ill= getdet.getIllness(d);
		if(ill==null){
			Toast.makeText(this, "FOLDER IS EMPTY", Toast.LENGTH_SHORT).show();
		}
		else{
		System.out.println("doonne");
		AlertDialog.Builder builder = new AlertDialog.Builder(c);
    builder.setTitle("TOP TEN")
           .setItems(ill, new DialogInterface.OnClickListener() {
               public void onClick(DialogInterface dialog, int which) {
               // The 'which' argument contains the index position
               // of the selected item
           }
    });
        builder.show();
        getdet.close();
		}
		   //Toast.makeText(Saved.this, "AM CLICCKEDD BROO",
              //     Toast.LENGTH_SHORT).show();
	           }
	  
	/*public class SpinnerActivity extends Activity implements OnItemSelectedListener {
	   
	    
	    public void onItemSelected(AdapterView<?> parent, View view, 
	            int pos, long id) {
	        // An item was selected. You can retrieve the selected item using
	        // parent.getItemAtPosition(pos)
	    	spinner.setVisibility(View.INVISIBLE);
	    }

	    public void onNothingSelected(AdapterView<?> parent) {
	        // Another interface callback
	    }*/
	}
   

