package com.clock.step2;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;


import com.fortumo.android.Fortumo;
import com.fortumo.android.PaymentActivity;
import com.fortumo.android.PaymentRequestBuilder;
import com.fortumo.android.PaymentResponse;

public class Pay4Points extends PaymentActivity {
	
  @Override
  public void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);

    //Log.d("Pay4Points", "start");
    //Buy consumable billing product "Clock Points" to use counter
    Fortumo.enablePaymentBroadcast(this, Manifest.permission.PAYMENT_BROADCAST_PERMISSION);
    //Fortumo.enablePaymentBroadcast(this, com.clock.step2.PAYMENT_BROADCAST_PERMISSION);

    PaymentRequestBuilder builder = new PaymentRequestBuilder();
    builder.setService("feafee2c5c44970fcc304fbf8ab9765d", "4c3a51360dab9369672a0c096145e670"); // service id and in-app secret
    builder.setDisplayString("Points for using the Counter");      // shown on user receipt
    builder.setProductName("Clock Points");    		 // consumable purchases are restored using this value
    builder.setConsumable(true);       				 // consumable items can be later restore
    builder.setIcon(R.drawable.ic_launcher);
    makePayment(builder.build());     
  } 
 
  @Override 
  protected void onResume() {
    super.onResume();
    	Log.d("Pay4Points", "onResume");   	
  }
  
  @Override
  protected void onPaymentCanceled(PaymentResponse response) {
	  Log.d("Pay4Points", "onCancel");
      finish();
  }

  @Override
  protected void onPaymentFailed(PaymentResponse response) {
	  Log.d("Pay4Points", "onPaymentFailed");
      finish();
  }

  @Override
  protected void onPaymentPending(PaymentResponse response) {
	  Log.d("Pay4Points", "onPaymentPending");
      finish();
  }

  @Override
  protected void onPaymentSuccess(PaymentResponse response) {
	  Log.d("Pay4Points", "onPaymentSuccess");
      finish();
  }  
}

