package com.huaqin.userguide;

import java.util.ArrayList;
import java.util.List;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.GridView;
import android.widget.ListAdapter;
import android.widget.ProgressBar;
import android.widget.TextView;

public class UserGuide extends Activity implements OnItemClickListener{
    /** Called when the activity is first created. */
	public ArrayList<Explayinfo> listarray;
	public Explayinfo info;
	private GridView explaygridview;
	private ExplayAdapter adapter;
	private int totalnumber;
	private int havereadnumber ;
	private TextView titletext;
	private ProgressBar firstpro;
	private TextView protitle;
	private int progressnumber;
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.requestWindowFeature(Window.FEATURE_NO_TITLE);   
        setContentView(R.layout.main);
               
    }
    
    
    @Override
	protected void onResume() {
		// TODO Auto-generated method stub
		super.onResume();
		Init();

	}


	public void Init(){
    	
    	listarray = new ArrayList<Explayinfo>();
    	totalnumber =0;
    	havereadnumber =0;
    	
    	for(int i =0;i< ExplayInfoDetail.init.length;i++){
    		info = new Explayinfo();
    		SharedPreferences readnumber = getSharedPreferences(ExplayInfoDetail.key[i],Context.MODE_PRIVATE);
    		int read_number = readnumber.getInt("read_number", 0);
    		info.setInfo_name(ExplayInfoDetail.init[i][ExplayInfoDetail.name_key]);
    		info.setInfo_image(ExplayInfoDetail.init[i][ExplayInfoDetail.image_key]);
    		info.setInfo_number(ExplayInfoDetail.init[i][ExplayInfoDetail.number_key]);
    		info.setInfo_read_number(read_number);
    		listarray.add(info);
    		totalnumber = totalnumber+ExplayInfoDetail.init[i][ExplayInfoDetail.number_key];
    		havereadnumber = havereadnumber + read_number;
    		info = null;
    	}
    	adapter = new ExplayAdapter(UserGuide.this, listarray);
    	
    	explaygridview =(GridView)findViewById(R.id.explay_grid_view);
    	explaygridview.setAdapter(adapter);
    	explaygridview.setOnItemClickListener(this);
    	
    	//fot title text
    	//titletext = (TextView)findViewById(R.id.main_title_information);
    	//titletext.setText(getString(R.string.app_name));
    	
    	//for progressbar
    	firstpro = (ProgressBar)findViewById(R.id.firstprogressbar);
    	firstpro.setVisibility(View.VISIBLE);
        /* max = 25 */
    	firstpro.setProgress(havereadnumber*25/totalnumber);
    	protitle =(TextView)findViewById(R.id.progress_information);
		progressnumber = (havereadnumber *100)/totalnumber;
    	protitle.setText(" " + String.valueOf(progressnumber) + "%");
    }


	@Override
	public void onItemClick(AdapterView<?> parent, View arg1, int position, long arg3) {
		// TODO Auto-generated method stub
		Explayinfo mExplayinfo =(Explayinfo)parent.getItemAtPosition(position);
		Intent mIntent = new Intent("android.intent.action.SHOWEXPLAY");
		Bundle mBundle = new Bundle();
		mBundle.putInt("position_key", position);
		mIntent.putExtra("position_keyvalue", mBundle);
		startActivity(mIntent);
		
	}
	
	
}
