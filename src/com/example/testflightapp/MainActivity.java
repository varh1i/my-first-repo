package com.example.testflightapp;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;

import com.testflightapp.acra.annotation.ReportsCrashes;
import com.testflightapp.lib.TestFlight;

@ReportsCrashes(formKey = "")
public class MainActivity extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		 TestFlight.takeOff(getApplication(), "e7085c6b-9d63-481f-900e-dd900f730de7");
		 TestFlight.passCheckpoint("START");
		setContentView(R.layout.activity_main);
		
		Button btn = (Button) findViewById(R.id.button1);
		btn.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				
				AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(MainActivity.this);
		 
					// set title
					alertDialogBuilder.setTitle("Your Title");
		 
					// set dialog message
					alertDialogBuilder
						.setMessage("Click yes to exit!")
						.setCancelable(false)
						.setPositiveButton("Yes",new DialogInterface.OnClickListener() {
							public void onClick(DialogInterface dialog,int id) {
								// if this button is clicked, close
								// current activity
								TestFlight.log("App closed from dialog");
								MainActivity.this.finish();
							}
						  })
						.setNegativeButton("No",new DialogInterface.OnClickListener() {
							public void onClick(DialogInterface dialog,int id) {
								// if this button is clicked, just close
								// the dialog box and do nothing
								TestFlight.log("Dialog closed");
								dialog.cancel();
							}
						});
		 
						// create alert dialog
						AlertDialog alertDialog = alertDialogBuilder.create();
		 
						// show it
						alertDialog.show();
						TestFlight.log("ExitDialog appeared");
			}
		});
		
		
		Button btn2 = (Button) findViewById(R.id.button2);
		btn2.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				
				AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(MainActivity.this);
		 
					// set title
					alertDialogBuilder.setTitle("Your Title");
		 
					// set dialog message
					alertDialogBuilder
						.setMessage("Click yes to trigger a crash!")
						.setCancelable(false)
						.setPositiveButton("Yes",new DialogInterface.OnClickListener() {
							public void onClick(DialogInterface dialog,int id) {
								// if this button is clicked, close
								// current activity
								TestFlight.log("Crash triggered");
								double x = 2;
								if(x == 2){
									throw new RuntimeException("myExceptionMessage");
								}
							}
						  })
						.setNegativeButton("No",new DialogInterface.OnClickListener() {
							public void onClick(DialogInterface dialog,int id) {
								// if this button is clicked, just close
								// the dialog box and do nothing
								TestFlight.log("CrashDialog closed");
								dialog.cancel();
							}
						});
		 
						// create alert dialog
						AlertDialog alertDialog = alertDialogBuilder.create();
		 
						// show it
						alertDialog.show();
						TestFlight.log("CrashDialog appeared");
			}
		});
	}

	@Override
	protected void onStop() {
		TestFlight.passCheckpoint("STOP");
		TestFlight.endSession();
		super.onDestroy();
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}

}
