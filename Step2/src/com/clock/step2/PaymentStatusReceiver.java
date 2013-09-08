package com.clock.step2;
import android.content.BroadcastReceiver;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import com.fortumo.android.Fortumo;

public class PaymentStatusReceiver extends BroadcastReceiver {

 private static String TAG = "Clock_Step2";
 private static String CLOCK_UPGRADE = "6da96723d7ae32868ed9fc3fdbea2d17";
 private static String CLOCK_POINTS = "feafee2c5c44970fcc304fbf8ab9765d";

 
 @Override
 public void onReceive(Context context, Intent intent) 
 {		 
	Bundle extras = intent.getExtras();  
    Log.d(TAG, "- billing_status:  " + extras.getInt("billing_status"));
    Log.d(TAG, "- credit_amount:   " + extras.getString("credit_amount"));
    Log.d(TAG, "- credit_name:     " + extras.getString("credit_name"));
    Log.d(TAG, "- message_id:      " + extras.getString("message_id") );
    Log.d(TAG, "- payment_code:    " + extras.getString("payment_code"));
    Log.d(TAG, "- price_amount:    " + extras.getString("price_amount"));
    Log.d(TAG, "- price_currency:  " + extras.getString("price_currency"));
    Log.d(TAG, "- product_name:    " + extras.getString("product_name"));
    Log.d(TAG, "- service_id:      " + extras.getString("service_id"));
    Log.d(TAG, "- user_id:         " + extras.getString("user_id"));
	
    Activity mainActivity = ClockMainActivity.activity;
	StatusData statusData = new StatusData(mainActivity);	
	
	//error to connect to server, no checking on status code now
    //int billingStatus = extras.getInt("billing_status");
    
	//if ((billingStatus == Fortumo.MESSAGE_STATUS_BILLED) && (service_id == CLOCK_UPGRADE))
	String service_id = extras.getString("service_id");
    if (service_id.equals(CLOCK_UPGRADE))	
	{	
		statusData.setFullLicenseClock();
		Log.d(TAG, "assumed upgrade done!");
	}  

    //if ((billingStatus == Fortumo.MESSAGE_STATUS_BILLED) && (service_id == CLOCK_POINTS))
    if (service_id.equals(CLOCK_POINTS))	
	{		
		statusData.topupPoints(5);
		Log.d(TAG, "assumed topup done!");
	}  
  }
}



