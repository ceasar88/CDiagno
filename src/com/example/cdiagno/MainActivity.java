package com.example.cdiagno;


import android.os.Bundle;
import android.renderscript.Allocation;
import android.renderscript.Element;
import android.renderscript.RenderScript;
import android.renderscript.ScriptIntrinsicBlur;
import android.app.ActionBar;
import android.app.Activity;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Point;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.ColorDrawable;
import android.view.Display;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.Toast;

public class MainActivity extends Activity implements OnClickListener {
Button submit;
LinearLayout l;
ImageView vi;
EditText sex,age;
EditText a,b,c,d;
String w,x,y,z;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		ActionBar bar = getActionBar();
		ColorDrawable barcolor = new ColorDrawable(Color.parseColor("#8D198D"));
		bar.setBackgroundDrawable(barcolor);
		submit = (Button)findViewById(R.id.sub);
		submit.setOnClickListener(this);
		sex = (EditText)findViewById(R.id.sex);
		age=(EditText)findViewById(R.id.age);
		a = (EditText)findViewById(R.id.second);
		b=(EditText)findViewById(R.id.first);
		c=(EditText)findViewById(R.id.third);
		d=(EditText)findViewById(R.id.last);
		vi = (ImageView)findViewById(R.id.imag);
		l = (LinearLayout)findViewById(R.id.lay);
		
		vi.buildDrawingCache();
		/*Bitmap bmap = vi.getDrawingCache();
		Display display = getWindowManager().getDefaultDisplay();
		Point size = new Point();
		display.getSize(size);
		int width = size.x;
		int height = size.y;*/
		//MainActivity m = new MainActivity();
		//blur(bmap,l,8,width,height);
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		
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
		 case R.id.abt:
          Intent abt = new Intent(this,About.class);
          startActivity(abt);
		 break;
		}
		return super.onOptionsItemSelected(item);
	}
	@Override
	public void onClick(View arg0) {
		// TODO Auto-generated method stub
		if(sex.getText().toString()==null||age.getText().toString()==null){
			
		}
		else{
			if(a.getText().toString()!=null||b.getText().toString() != null||c.getText().toString() != null||d.getText().toString() != null){
		Intent main = new Intent(this,Results.class);
		main.putExtra("sex", sex.getText().toString());
		main.putExtra("age", age.getText().toString());
		if(a.getText().toString() != null){
		main.putExtra("sym0", a.getText().toString());
		}
		if(b.getText().toString() != null){
		main.putExtra("sym1", b.getText().toString());
		}
		if(c.getText().toString() != null){
		main.putExtra("sym2", c.getText().toString());
		}
		if(d.getText().toString() != null){
		main.putExtra("sym3", d.getText().toString());
		}
		startActivity(main);
		}
			else{
				        Intent stay = new Intent(this, Mainfrag.class);
				Toast.makeText(getBaseContext(), "NO INTERNET CONNECTION", Toast.LENGTH_SHORT).show();
			}
		}
	}

/*
private  void blur(Bitmap bkg, View view, float radius,int x,int y) {
 Bitmap overlay = Bitmap.createBitmap( x,y,Bitmap.Config.ARGB_8888);
// view.getMeasuredWidth(), 
// view.getMeasuredHeight(), 

 
 Canvas canvas = new Canvas(overlay);
 
 canvas.drawBitmap(bkg, -view.getLeft(), 
 -view.getTop(), null);
 
 RenderScript rs = RenderScript.create(this);
 
 Allocation overlayAlloc = Allocation.createFromBitmap(
 rs, overlay);
 
 ScriptIntrinsicBlur blur = ScriptIntrinsicBlur.create(
 rs, overlayAlloc.getElement());
 
 blur.setInput(overlayAlloc);
 
 blur.setRadius(radius);
 
 blur.forEach(overlayAlloc);
 
 overlayAlloc.copyTo(overlay);
 
 view.setBackground(new BitmapDrawable(
 getResources(), overlay));
 
 rs.destroy();
}
*/
}
