package com.example.cdiagno;


import java.util.ArrayList;
import java.util.Date;
import java.util.List;


import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import DBjazz.DBstuff;
import android.app.ActionBar;
import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ListView;
import android.widget.Toast;


public class Results extends Activity {
	List<Illness> ill = new ArrayList<Illness>();
	 ProgressDialog pd;
	 JSONArray jarr;
	 JSONObject jobj;
	//String des[]={"first","second","third","fourth","fifth","sixth","seventh","eigth","nineth","tenth","eleventh","tweleveth"};
	//String des2[]={"1","2","3","4","5","6","7","8","9","10","11","12"};
	//int ch[] = {10,20,30,40,50,60,70,80,85,90,95,100};
	ResAdapter ra;
	String Symptoms[] = new String[4] ;
	String sex,age;
	JSONArray apistuff;
	Context c;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.results);
		ActionBar bar = getActionBar();
		ColorDrawable barcolor = new ColorDrawable(Color.parseColor("#8D198D"));
		bar.setBackgroundDrawable(barcolor);
		Bundle stuff  = getIntent().getExtras();
		     sex = stuff.getString("sex");
		     age = stuff.getString("age");
		     if(stuff.getString("sym0")!=null){
			Symptoms[0] = stuff.getString("sym0");
		     }
		    if( stuff.getString("sym1")!=null){
			Symptoms[1] = stuff.getString("sym1");
		    }
		    if(stuff.getString("sym2")!=null){
			Symptoms[2] = stuff.getString("sym2");
		    }
		    if(stuff.getString("sym3")!=null){
			Symptoms[3] = stuff.getString("sym3");
		    }
		    c = this;
		/*for(int i=0;i<ch.length;i++){
			Illness l = new Illness();
			l.setName(des[i]);
			l.setDes(des2[i]);
			l.setChance(ch[i]);
			ill.add(l);
		}*/
		
		ra = new ResAdapter(ill,this);
		RetrieveData apireq = new RetrieveData();
	      apireq.execute();
		  ListView listView = (ListView)findViewById(R.id.reslist);
	       listView.setAdapter(ra);
		
	}
	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.resmenu, menu);
		return true;
	}
	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		// TODO Auto-generated method stub
		switch (item.getItemId()) {
		case R.id.save:
			RetrieveData h = new RetrieveData();
        JSONArray savedarr = h.getarr();
        DBstuff dbsave = new DBstuff(c);
        dbsave.open();
        Date g = new Date();
        String date = g.toString();
        System.out.println(date);
        for(int i=0;i<10;i++){
        	JSONObject jj;
			try {
				jj = savedarr.getJSONObject(i);
				String illness = jj.getString("name");
				 System.out.println(illness);
				dbsave.createEntry(date, illness);
				
			} catch (JSONException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
        	
        	
        }
        dbsave.close();
        Toast.makeText(c, "SAVED!", Toast.LENGTH_SHORT).show();
			break;
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
	public class RetrieveData extends AsyncTask<String, Void, List<Illness>>{
		 
		
		
		protected void onPostExecute(List<Illness> result) {
			// TODO Auto-generated method stub
			super.onPostExecute(result);
			if(result!=null){
			//System.out.println(result.toString());
			ra.setItemList(result);
			ra.notifyDataSetChanged();
			if (pd != null) {
				pd.dismiss();
				// b.setEnabled(true);
			}
			Toast.makeText(c, "FILL ALL SYMPTOMS FOR MORE ACCURATE RESULTS", Toast.LENGTH_SHORT).show();
			}
			else{
				Toast.makeText(c, "SERVER ERROR", Toast.LENGTH_SHORT).show();
			}
		}
		@Override
		protected void onPreExecute() {
			// TODO Auto-generated method stub
			super.onPreExecute();
			pd = new ProgressDialog(c);
			pd.setTitle("FINDING YOUR ILLNESS...");
			pd.setMessage("Please wait...");
			pd.setCancelable(false);
			pd.setIndeterminate(true);
			pd.show();
		}
		@Override
		protected List<Illness> doInBackground(String... params) {
			// TODO Auto-generated method stub
			List<Illness> result = new ArrayList<Illness>();
			ClientServerInterface apiresults = new ClientServerInterface();
			String ids[] = new String[4];
			
			double chn = 0;
			apistuff = new JSONArray();
			JSONObject item = new JSONObject();
			for(int i=0;i<Symptoms.length;i++){
				if(Symptoms[i]!=null){
				try {
					ids[i] = apiresults.getPhrase(Symptoms[i]);
				} catch (JSONException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				}
			}
			try {
				apistuff = apiresults.getdiag(sex, age, ids[0], ids[1], ids[2], ids[3]);
				if(apistuff!=null){
				for(int i=0;i<apistuff.length();i++){
					Illness j = new Illness();
					item = apistuff.getJSONObject(i);
					if(item.getDouble("probability")>=0.001){
					j.setName(item.getString("name"));
					chn = item.getDouble("probability");
					j.setChance(chn*100);
					
					result.add(j);
					}
					else{
						break;
					}
					
				}
				return result;
				}else{
					System.out.println("didnot fetch");
					return null;
				}
			} catch (JSONException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
				return null;
			}
			
			//return result;
			
			 
			
			
		}
		
		public JSONArray getarr(){
			return apistuff;
		}
		 }
			 
	}





