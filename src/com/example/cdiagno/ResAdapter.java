package com.example.cdiagno;

import java.text.DecimalFormat;
import java.util.List;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;



import android.widget.ListView;
import android.widget.SeekBar;
import android.widget.TextView;


public class ResAdapter  extends ArrayAdapter<Illness>{
	ListView lvres;
	TextView name,fig;
	TextView description;
	SeekBar stat;
	
	 
	 Illness c;
	
	
	
	private List<Illness> itemList;
	 private Context context;

	 public ResAdapter(List<Illness> itemList, Context ctx) {
	 super (ctx, android.R.layout.simple_list_item_1, itemList);
	 this.itemList = itemList;
	 this.context = ctx;
	 }

	 public int getCount() {
	 if (itemList != null)
	 return itemList.size();
	 return 0;
	 }

	 public Illness getItem( int position) {
	 if (itemList != null)
	 return itemList.get(position);
	 return null;
	 }

	 public long getItemId( int position) {
	 if (itemList != null)
		return itemList.get(position).hashCode();
	 return 0;
	 }

		@Override
		public View getView(final int position, View convertView, ViewGroup parent) {
		       // TODO Auto-generated method stub
		       View vi=convertView;
		       if(convertView==null){
		    	   LayoutInflater inflater = (LayoutInflater) context.getSystemService
		    	   (Context.LAYOUT_INFLATER_SERVICE);
		    	    vi = inflater.inflate(R.layout.res_row, null);
		       }
		       c = itemList.get(position);
		       
		     
		   name = (TextView) vi.findViewById(R.id.name);
		   name.setText(c.getName());
		  
		 
		  //description = (TextView) vi.findViewById(R.id.des);
		  //description.setText(c.getDes());
		 stat = (SeekBar)vi.findViewById(R.id.stat);
		 double num = c.getChance();
		 int n = (int)num;
		        stat.setProgress(n);
		        stat.setEnabled(false);
		      fig = (TextView)vi.findViewById(R.id.fig);
		      DecimalFormat dt = new DecimalFormat("#.####");
		      fig.setText(dt.format(c.getChance()) + "%");
		       return vi;
		   }
		public List<Illness> getItemList() {
			 return itemList;
			 }

			 public void setItemList(List<Illness> itemList) {
				 System.out.println("yeaasss added!!");
				this.itemList = itemList;
			}
			
		}
	

		


