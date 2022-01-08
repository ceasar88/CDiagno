package com.example.cdiagno;


import android.app.ActionBar;
import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;

public class Welcome extends Activity implements OnClickListener {
	Button start;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.welcome);
		ActionBar bar = getActionBar();
		bar.hide();
	//	DBstuff getdet = new DBstuff(c);
		//getdet.open();
		start = (Button)findViewById(R.id.start);
		start.setOnClickListener(this);
	}
	@Override
	public void onClick(View arg0) {
		// TODO Auto-generated method stub
		Intent main = new Intent(this,Drawerclass.class);
		startActivity(main);
		finish();
	}

}
