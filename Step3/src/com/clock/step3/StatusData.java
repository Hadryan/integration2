package com.clock.step3;

import android.app.Activity;
import android.content.Context;
import android.content.SharedPreferences;

public class StatusData {

	private Activity theActivity;
	//private SharedPreferences sharedPref = theActivity.getPreferences(Context.MODE_PRIVATE);
	//private SharedPreferences sharedPref; 
	
	
    public StatusData(Activity fromActivity) {
    	theActivity = fromActivity;
    	//sharedPref = theActivity.getPreferences(Context.MODE_PRIVATE);
    }
    
    public void topupPoints(int p) {
    	// retrieve the current Points
    	SharedPreferences sharedPref1 = theActivity.getPreferences(Context.MODE_PRIVATE);
    	int points = sharedPref1.getInt("Points", 0);
    	// add p Points
    	points = points + p;
    	// save the new Points
    	//SharedPreferences sharedPref2 = theActivity.getPreferences(Context.MODE_PRIVATE);
    	SharedPreferences.Editor editor = sharedPref1.edit();
    	editor.putInt("Points", points);
    	editor.commit();
    }
    
    public int currentPoints() {
    	// get Points from application preferences and return
    	SharedPreferences sharedPref = theActivity.getPreferences(Context.MODE_PRIVATE);
    	int points = sharedPref.getInt("Points", 0);
    	return points;
    }

	// spend p Points
	// return True if success, return False if no enough Points
    public Boolean spendPoints(int p) {
    	
    	// retrieve the current Points
    	SharedPreferences sharedPref = theActivity.getPreferences(Context.MODE_PRIVATE);
    	int points = sharedPref.getInt("Points", 0);
    	if (p>points) {
    		return false;
    	}
    	else
    	{	
    		points = points - p;
    		// save the new Points
        	//SharedPreferences sharedPref2 = theActivity.getPreferences(Context.MODE_PRIVATE);
        	SharedPreferences.Editor editor = sharedPref.edit();
        	editor.putInt("Points", points);
        	editor.commit();
    	    return true;   
    	}    
    }  
    
    public void setFullLicenseClock() {
    	// store Full License flag to application preferences    
    	SharedPreferences sharedPref = theActivity.getPreferences(Context.MODE_PRIVATE);
    	SharedPreferences.Editor editor = sharedPref.edit();
    	editor.putBoolean("FullLicense", true);
    	editor.commit();
    }
    
    public Boolean isFullLicenseClock() {
    	// get Full License flag from application preferences
    	SharedPreferences sharedPref = theActivity.getPreferences(Context.MODE_PRIVATE);
    	Boolean isFullLicense = sharedPref.getBoolean("FullLicense", false);
    	return isFullLicense;	
    }  

    public void clearAll() {
    	// clear all data in application preferences    
    	SharedPreferences sharedPref = theActivity.getPreferences(Context.MODE_PRIVATE);
    	SharedPreferences.Editor editor = sharedPref.edit();
    	editor.clear(); 
    	editor.commit();
    }
    
}
