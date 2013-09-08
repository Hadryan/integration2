package com.clock.step2;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;


import com.fortumo.android.Fortumo;
import com.fortumo.android.PaymentActivity;
import com.fortumo.android.PaymentRequestBuilder;
import com.fortumo.android.PaymentResponse;

public class Pay4Upgrade extends PaymentActivity {
	
  @Override
  public void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);

    //Buy non-consumable billing product "Clock Upgrade", make one time payment to upgrade
    Fortumo.enablePaymentBroadcast(this, Manifest.permission.PAYMENT_BROADCAST_PERMISSION);

    PaymentRequestBuilder builder = new PaymentRequestBuilder();
    builder.setService("6da96723d7ae32868ed9fc3fdbea2d17", "5d0cc22324ec266056090cb66f671eef"); // in-app secret
    builder.setDisplayString("Clock Full License");      // shown on user receipt
    builder.setProductName("Clock Upgrade");    		 // non-consumable purchases are restored using this value
    builder.setConsumable(false);       				 // non-consumable items can be later restore
    builder.setIcon(R.drawable.ic_launcher);
    makePayment(builder.build());     
  } 
 
  @Override 
  protected void onResume() {
    super.onResume();
    	Log.d("Pay4Upgrade", "onResume");   	
  }
  
  @Override
  protected void onPaymentCanceled(PaymentResponse response) {
	  Log.d("Pay4Upgrade", "onCancel");
      finish();
  }

  @Override
  protected void onPaymentFailed(PaymentResponse response) {
	  Log.d("Pay4Upgrade", "onPaymentFailed");
      finish();
  }

  @Override
  protected void onPaymentPending(PaymentResponse response) {
	  Log.d("Pay4Upgrade", "onPaymentPending");	 
      finish();
  }

  @Override
  protected void onPaymentSuccess(PaymentResponse response) {
	  Log.d("Pay4Upgrade", "onPaymentSuccess");
      finish();
  }  
}

