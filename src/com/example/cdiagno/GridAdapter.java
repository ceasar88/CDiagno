package com.example.cdiagno;

import java.util.ArrayList;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

public class GridAdapter extends BaseAdapter {
	private ArrayList<String> dates;
	Context c;
	
	public GridAdapter(Context cont,ArrayList<String> date){
		c = cont;
		this.dates = date;
	}

	@Override
	public int getCount() {
		// TODO Auto-generated method stub
		return dates.size();
	}

	@Override
	public Object getItem(int arg0) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public long getItemId(int arg0) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public View getView(int position, View convertView, ViewGroup arg2) {
		// TODO Auto-generated method stub
		 View grid;
         LayoutInflater inflater = (LayoutInflater) c
             .getSystemService(Context.LAYOUT_INFLATER_SERVICE);

         if (convertView == null) {

             grid = new View(c);
             grid = inflater.inflate(R.layout.saved_row, null);
             TextView textView = (TextView) grid.findViewById(R.id.date);
             
             //ImageView imageView = (ImageView)grid.findViewById(R.id.grid_image);
             textView.setText(dates.get(position));
             //imageView.setImageResource(Imageid[position]);
         } else {
             grid = (View) convertView;
         }
                //grid.setTranslationX()
         return grid;
	}

}
