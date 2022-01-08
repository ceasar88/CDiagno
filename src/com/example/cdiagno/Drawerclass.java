package com.example.cdiagno;


import android.app.ActionBar;
import android.app.Activity;
import android.app.Fragment;

import android.content.Intent;
import android.content.res.Configuration;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.ActionBarDrawerToggle;

import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;

import android.widget.ListView;

public class Drawerclass extends Activity implements OnItemClickListener {
    private String[] title=  {"C-DIAGNO","Saved results","About Us","feedback","Comment on blog"};
    private int[] icon = new int[] { R.drawable.ic_action_collapse,R.drawable.ic_action_save, R.drawable.action_about, R.drawable.ic_action_call,R.drawable.ic_action_web_site};
    private DrawerLayout mDrawerLayout;
    private ListView mDrawerList;
    ActionBarDrawerToggle mDrawerToggle;
	drawermenuadapter mMenuAdapter;
	Fragment fragment1 = new Mainfrag();
	//For drawer
	public CharSequence mDrawerTitle;
	public CharSequence mTitle;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.drawer_main);
        ActionBar bar = getActionBar();
		ColorDrawable barcolor = new ColorDrawable(Color.parseColor("#8D198D"));
		bar.setBackgroundDrawable(barcolor);
		 
        mDrawerLayout = (DrawerLayout) findViewById(R.id.drawer_layout);
        mDrawerList = (ListView) findViewById(R.id.left_drawer);
        mDrawerLayout.setDrawerShadow(R.drawable.drawer_shadow,	GravityCompat.START);
        // Set the adapter for the list view
     mDrawerLayout.setFitsSystemWindows(true);
        // Set the list's click listener
        mDrawerTitle = getTitle();

     // Pass string arrays to MenuListAdapter
     		mMenuAdapter = new drawermenuadapter(Drawerclass.this, title, icon);
     		mDrawerList.setAdapter(mMenuAdapter);
     		mDrawerList.setOnItemClickListener(this);
     		
    	//System.out.println("yoooooo");
    		mDrawerToggle = new ActionBarDrawerToggle(this, mDrawerLayout, R.drawable.ic_drawer, R.string.drawer_open, R.string.drawer_close) {
    			
    			public void onDrawerClosed(View view) {
    				super.onDrawerClosed(view);
    				System.out.println("brooooo closedd");
    				getActionBar().setTitle(mDrawerTitle);
    				//selectItem(0);
    				  invalidateOptionsMenu(); 
    			}
    			public void onDrawerOpened(View drawerView) {
    				// Set the title on the action when drawer open
    				super.onDrawerOpened(drawerView);
    				getActionBar().setTitle(mDrawerTitle);
    				System.out.println("brooooo open");
    				
    				  invalidateOptionsMenu(); 
    			}
    			
    		};
    		
    		mDrawerLayout.setDrawerListener(mDrawerToggle);
    		bar.setHomeButtonEnabled(true);
        	bar.setDisplayHomeAsUpEnabled(true);
    		if (savedInstanceState == null) {
    			selectItem(0);
    		}
    }
    private void selectItem(int position) {

		android.app.FragmentTransaction ft = getFragmentManager().beginTransaction();
		// Locate Position
		switch (position) {
		case 0:
			ft.replace(R.id.content_frame, fragment1);
			ft.commit();
			mDrawerList.setItemChecked(position, true);
			// Get the title followed by the position
			setTitle(title[position]);
			// Close drawer
			mDrawerLayout.closeDrawer(mDrawerList);
			
			System.out.println("111111");
			break;
		case 1:
			Intent saved = new Intent(this,Saved.class);
			startActivity(saved);
			mDrawerLayout.closeDrawer(mDrawerList);
			break;
		case 2:
			Intent abt = new Intent(this,About.class);
			startActivity(abt);
			mDrawerLayout.closeDrawer(mDrawerList);
			break;
			
		case 3:
			 
Feedback fd = new Feedback();
	        
	        fd.show(getFragmentManager(), "dialog");
			
	        mDrawerLayout.closeDrawer(mDrawerList);
			break;
			
		case 4:
			 Intent viewIntent =
	          new Intent("android.intent.action.VIEW",
	            Uri.parse("http://cdiagno.blogspot.com/"));
	          startActivity(viewIntent);
			break;
		
		}
		
		
	}
    @Override
	public void setTitle(CharSequence title) {
		mTitle = title;
		getActionBar().setTitle(mTitle);
	}

	@Override
	public void onItemClick(AdapterView<?> arg0, View arg1, int position, long arg3) {
		// TODO Auto-generated method stub
		if(position==0){
			mDrawerLayout.closeDrawer(mDrawerList);
		}else{
			selectItem(position);
		}
		
	}
	 @Override
	    public boolean onPrepareOptionsMenu(Menu menu) {
	        // If the nav drawer is open, hide action items related to the content view
	      //  boolean drawerOpen = mDrawerLayout.isDrawerOpen(mDrawerList);
	       // menu.findItem(R.id.abt).setVisible(!drawerOpen);
	       
	       // menu.findItem(R.id.contact).setVisible(!drawerOpen);
	        return super.onPrepareOptionsMenu(menu);
	    }
	@Override
	protected void onPostCreate(Bundle savedInstanceState) {
		super.onPostCreate(savedInstanceState);
		// Sync the toggle state after onRestoreInstanceState has occurred.
		mDrawerToggle.syncState();
	}

	
	public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
		// TODO Auto-generated method stub
		inflater.inflate(R.menu.main, menu);
		super.onCreateOptionsMenu(menu);
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		// TODO Auto-generated method stub
		if (mDrawerToggle.onOptionsItemSelected(item)) {
	          return true;
	        }
		else{
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
		}
		return super.onOptionsItemSelected(item);
	}
	@Override
	public void onConfigurationChanged(Configuration newConfig) {
		super.onConfigurationChanged(newConfig);
		// Pass any configuration change to the drawer toggles
		mDrawerToggle.onConfigurationChanged(newConfig);
	}
	/*
	@Override
	public void onBackPressed() {

		FragmentManager manager = getFragmentManager();
		if (manager.getBackStackEntryCount() > 0) {
			// If there are back-stack entries, leave the FragmentActivity
			// implementation take care of them.
			manager.popBackStack();

		} else {
			// Otherwise, ask user if he wants to leave :)
			super.onBackPressed();
		}
	}*/

}
