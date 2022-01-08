package com.example.cdiagno;


import android.app.Fragment;
import android.content.Intent;


import android.os.Bundle;
import android.view.LayoutInflater;

import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;

import android.widget.LinearLayout;
import android.widget.Toast;

public class Mainfrag extends Fragment implements OnClickListener {
	Button submit,clear;
	LinearLayout l;
	EditText sex,age;
	EditText a,b,c,d;

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		 View rootView = inflater.inflate(R.layout.activity_main, container, false);
		 submit = (Button)rootView.findViewById(R.id.sub);
		 clear = (Button)rootView.findViewById(R.id.clr);
			submit.setOnClickListener(this);
			
			sex = (EditText)rootView.findViewById(R.id.sex);
			age=(EditText)rootView.findViewById(R.id.age);
			a = (EditText)rootView.findViewById(R.id.second);
			b=(EditText)rootView.findViewById(R.id.first);
			c=(EditText)rootView.findViewById(R.id.third);
			d=(EditText)rootView.findViewById(R.id.last);
			
			l = (LinearLayout)rootView.findViewById(R.id.lay);
			clear.setOnClickListener(new OnClickListener(){

				@Override
				public void onClick(View arg0) {
					// TODO Auto-generated method stub
					sex.setText("");
					age.setText("");
					a.setText("");
					b.setText("");
					c.setText("");
					d.setText("");
				}
				
			});
			System.out.println("got to main");
	        return rootView;
	}

	

	@Override
	public void onDestroy() {
		// TODO Auto-generated method stub
		super.onDestroy();
		sex.setText("");
		age.setText("");
		a.setText("");
		b.setText("");
		c.setText("");
		d.setText("");
	}



	@Override
	public void onClick(View arg0) {
		// TODO Auto-generated method stub
if(sex.getText().toString().equals("")||age.getText().toString().equals("")){
	//Intent stay = new Intent();
	//stay.setClass(getActivity(), Drawerclass.class);
	//startActivity(stay);
	Toast.makeText(getActivity(), "AGE OR GENDER CANNOT BE EMPTY", Toast.LENGTH_SHORT).show();
	
		}
		else{
			if(sex.getText().toString().equalsIgnoreCase("male")||sex.getText().toString().equalsIgnoreCase("female")){
			if((a.getText().toString().equals("")&&b.getText().toString().equals("")&&c.getText().toString().equals("")&&d.getText().toString().equals(""))){

			//	Intent stay = new Intent();
				//stay.setClass(getActivity(), Drawerclass.class);
				//startActivity(stay);
				Toast.makeText(getActivity(), "ENTER ATLEAST ONE SYMPTOM", Toast.LENGTH_SHORT).show();
				
			}
			else{
				Intent main = new Intent();
		main.setClass(getActivity(), Results.class);
		main.putExtra("sex", sex.getText().toString());
		main.putExtra("age", age.getText().toString());
		if(!(a.getText().toString().equals(""))){
		main.putExtra("sym0", a.getText().toString());
		}
		if(!(b.getText().toString().equals(""))){
		main.putExtra("sym1", b.getText().toString());
		}
		if(!(c.getText().toString().equals(""))){
		main.putExtra("sym2", c.getText().toString());
		}
		if(!(d.getText().toString().equals(""))){
		main.putExtra("sym3", d.getText().toString());
		}
		startActivity(main);
		}
			
			}
			else{
				//Intent stay = new Intent();
			//	stay.setClass(getActivity(), Drawerclass.class);
				//startActivity(stay);
				Toast.makeText(getActivity(), "INVALID GENDER", Toast.LENGTH_SHORT).show();
				
			}
		}
	}

}
