package com.example.cdiagno;

import android.app.AlertDialog;
import android.app.Dialog;
import android.app.DialogFragment;
import android.content.DialogInterface;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.LayoutInflater;

public class Feedback extends DialogFragment {
	@Override
	public Dialog onCreateDialog(Bundle savedInstanceState) {
	    AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
	    // Get the layout inflater
	    LayoutInflater inflater = getActivity().getLayoutInflater();

	    // Inflate and set the layout for the dialog
	    // Pass null as the parent view because its going in the dialog layout
	    builder.setView(inflater.inflate(R.layout.feed, null))
	    // Add action buttons
	           .setPositiveButton("CALL", new DialogInterface.OnClickListener() {
	               @Override
	               public void onClick(DialogInterface dialog, int id) {
	                 
	            	   Uri number = Uri.parse("tel:0701405038");
	                   Intent dial = new Intent(Intent.ACTION_CALL, number);
	                   startActivity(dial);
	                   System.out.println("callllinngg!!");
	                  // Intent intent = new Intent(Intent.ACTION_DIAL);
	                  // intent.setData(Uri.parse("tel:0701405038"));
	                  // startActivity(intent); 
	              }
	           })
	           .setNegativeButton("TEXT", new DialogInterface.OnClickListener() {
	               public void onClick(DialogInterface dialog, int id) {
	            	   Intent intentsms = new Intent( Intent.ACTION_VIEW, Uri.parse( "sms:0701405038" ) );
	                   intentsms.putExtra( "sms_body", "send me text..." );
	                   startActivity( intentsms );
	               }
	           });      
	    return builder.create();
	}
}