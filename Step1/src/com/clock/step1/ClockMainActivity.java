package com.clock.step1;

import java.util.Calendar;


import android.os.Bundle;
import android.os.Handler;
import android.app.Activity;
import android.view.Menu;
import android.widget.TextView;
import android.view.ViewGroup;
import android.view.View;
import android.widget.Button;


public class ClockMainActivity extends Activity {

	private Button clearData;
	private Button topUp;
	private Button stopWatch;
	private Button upGrade;
	private Boolean fullLicense = false;
	private Boolean stopWatchOn = false;
	private long startTime = 0L;
	
	private Handler digitalclockHandler = new Handler();
	private Handler customHandler = new Handler();

	private StatusData statusData = new StatusData(this);	
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_clock_main);
			
		// show Points first time
		showPoints();
		
		// show the digital clock forever
	    digitalclockHandler.postDelayed(digitalclockTimerThread, 0);
		
	    // clear all local data
	    clearData = (Button) findViewById(R.id.clear_data); 
		clearData.setOnClickListener(new View.OnClickListener() {

			@Override
			public void onClick(View arg0) {
				statusData.clearAll();
			}
		}); 
	    
	    
	    // create the TopUp button and set up the event handler
	    topUp = (Button) findViewById(R.id.top_up); 
		topUp.setOnClickListener(new View.OnClickListener() {

			@Override
			public void onClick(View arg0) {
				statusData.topupPoints(5);
				showPoints();
			}
		});
	    
	    
		// create the stopWatch button and set up the event handler
		stopWatch = (Button) findViewById(R.id.stop_watch); 
		stopWatch.setOnClickListener(new View.OnClickListener() {

			@Override
			public void onClick(View arg0) {
				if (stopWatchOn)
				{ 	
					stopWatchOn = false;
					stopWatch.setText("Start Counting");
					customHandler.removeCallbacks(updateTimerThread);
				}
				else
				{	
					if (statusData.spendPoints(1)) {
						showPoints();
						stopWatchOn = true;
						stopWatch.setText("Stop Counting");    
						startTime = System.currentTimeMillis();
						customHandler.postDelayed(updateTimerThread, 0);
					}
				}  
			}
		});
		
		 
		fullLicense = statusData.isFullLicenseClock(); 
		if (fullLicense) {
			upGrade = (Button) findViewById(R.id.upgrade); 
			ViewGroup layout = (ViewGroup) upGrade.getParent();
			if(null!=layout) 
				layout.removeView(upGrade);	
		}	
		else
		{	
			// create the upGrade button and set up the event handler
			upGrade = (Button) findViewById(R.id.upgrade); 
			upGrade.setOnClickListener(new View.OnClickListener() {
 
				@Override
				public void onClick(View arg0) {
			  	
					fullLicense = true;	
					statusData.setFullLicenseClock();
							  				
					ViewGroup layout = (ViewGroup) upGrade.getParent();
					if(null!=layout)
						layout.removeView(upGrade);
				}
			});
		}	
	}

	private Runnable digitalclockTimerThread = new Runnable() {	 
		   public void run() {	 
			   
			   Calendar cal = Calendar.getInstance();
	           int hour = cal.get(Calendar.HOUR_OF_DAY);
	           int min = cal.get(Calendar.MINUTE);
	           int sec = cal.get(Calendar.SECOND);
	           TextView digitalclock = (TextView) findViewById(R.id.digital_clock_display);
	           if (fullLicense)
	           {	   
	        	   digitalclock.setText("Current Time Full Version:" + hour + ":" + min + ":" + sec);           
	           }
	           else
	           {	   
	        	   digitalclock.setText("Current Time Trial Version:" + hour + ":" + min + ":" + sec);        
	           }	   
	           digitalclockHandler.postDelayed(this, 1000);
	        }	 
	};
	
	private Runnable updateTimerThread = new Runnable() {	 
		   public void run() {	 
			   long  elapsed  = System.currentTimeMillis() - startTime;
	            String display = Long.toString(elapsed);
			    TextView secondCount = (TextView) findViewById(R.id.stop_watch_display); 
			    secondCount.setText("Counter:" + display);	           
	            customHandler.postDelayed(this, 0);
	        }	 
	};

	protected void showPoints() {
		
		  String pointsDisplay = "Current Points:" + statusData.currentPoints();
		  TextView topUp = (TextView) findViewById(R.id.top_up_display); 
		  topUp.setText(pointsDisplay);	
	}
			
	
	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.clock_main, menu);
		return true;
	}

}


