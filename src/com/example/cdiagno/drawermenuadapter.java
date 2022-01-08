package com.example.cdiagno;
 


import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

public class drawermenuadapter extends BaseAdapter {
	
	// Declare Variables
	Context mcontxt;
	String[] mtitle;

	int[] micon;
	LayoutInflater inflater;
	
	public drawermenuadapter(Context c, String[] t,
			int[] icon) {
		this.mcontxt = c;
		this.mtitle = t;
		
		this.micon = icon;
	}

	@Override
	public int getCount() {
		return mtitle.length;
	}

	@Override
	public Object getItem(int position) {
		return mtitle[position];
	}

	@Override
	public long getItemId(int position) {
		return position;
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		
		// Declare Variables
				TextView txtTitle;
				
				ImageView imgIcon;

				inflater = (LayoutInflater) mcontxt.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
				
				View itemView = inflater.inflate(R.layout.drawer_list_item, parent, false);

				// Locate the TextViews in drawer_list_item.xml
				txtTitle = (TextView) itemView.findViewById(R.id.title);
				

				// Locate the ImageView in drawer_list_item.xml
				imgIcon = (ImageView) itemView.findViewById(R.id.icon);

				// Set the results into TextViews
				txtTitle.setText(mtitle[position]);
				
				// Set the results into ImageView
				imgIcon.setImageResource(micon[position]);

				return itemView;
		
	}

}
