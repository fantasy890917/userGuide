package com.huaqin.userguide;
import android.app.Activity;  
import android.os.Bundle;  
import android.util.Log;  
import android.view.GestureDetector;  
import android.view.MotionEvent;  
import android.view.View;  
import android.view.GestureDetector.OnGestureListener;  
import android.view.ViewGroup.LayoutParams;  
import android.view.animation.AnimationUtils;  
import android.widget.Button;  
import android.widget.ExpandableListView;
import android.widget.TextView;
import android.widget.ViewFlipper;
import android.widget.ImageView;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import android.view.LayoutInflater;
import android.view.View.OnClickListener;
import android.view.Window;
import android.content.Context;

public class UserGuideDetail extends Activity implements OnGestureListener,OnClickListener {  
      
    private GestureDetector detector;  
    private ViewFlipper flipper;  
	private SharedPreferences setting;
	private int mcolumindex;
	private int mCurrentIndex;
	private int mViewMaxCount;
	private ImageView imageview1;
	private ImageView imageview2;
	private ImageView imageview3;
	private ImageView imageview4;
	private ImageView imageview5;
	private ImageView imageview6;
	private TextView  producetext;
	private ImageView readimage;


    /** Called when the activity is first created. */  
    @Override  
    public void onCreate(Bundle savedInstanceState) {  
        super.onCreate(savedInstanceState);
        this.requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.grid_flipper);

    	Intent mIntent = getIntent();
    	Bundle mBundle = mIntent.getBundleExtra("position_keyvalue");
    	int index = mBundle.getInt("position_key");
    	int[] data = ExplayInfoDetail.initimage[index];
    	mViewMaxCount = data.length;
    	mcolumindex = index;

    	//next for inproduce
    	producetext =(TextView)findViewById(R.id.produce_information);
    	producetext.setText(R.string.show_title_name);
    	//image for read
    	readimage =(ImageView)findViewById(R.id.read_item_preview);

    	// Fliperr
    	flipper = (ViewFlipper) this.findViewById(R.id.ViewFlipper01);
    	final LayoutInflater inflater = getLayoutInflater();
    	for (int i = 0; i < mViewMaxCount; i++)
    	{
    		View view = inflater.inflate(R.layout.theme_preview_item, flipper, false);
            ((ImageView) view.findViewById(R.id.theme_preview_item)).setImageResource(data[i]);
            ((TextView) view.findViewById(R.id.title_information)).setText(ExplayInfoDetail.initstring[mcolumindex][i]);

            flipper.addView(view); 		
    	}

       detector = new GestureDetector(this);
       mCurrentIndex = 0;
       
       //next for image index
       imageview1 =(ImageView)findViewById(R.id.image_view01);
       imageview1.setOnClickListener(this);
       imageview2 =(ImageView)findViewById(R.id.image_view02);
       imageview2.setOnClickListener(this);
       imageview3 =(ImageView)findViewById(R.id.image_view03);
       imageview3.setOnClickListener(this);
       imageview4 =(ImageView)findViewById(R.id.image_view04);
       imageview4.setOnClickListener(this);
       imageview5 =(ImageView)findViewById(R.id.image_view05);
       imageview5.setOnClickListener(this);
       imageview6 =(ImageView)findViewById(R.id.image_view06);
       imageview6.setOnClickListener(this);
       
       UpdateIndicator();
    }
    
    public void UpdateIndicator()
    {
    	for(int i =0;i< ExplayInfoDetail.init[mcolumindex][ExplayInfoDetail.number_key];i++){
    		setImageResource(i, R.drawable.screen_indicator_normal);
    	}
    	if (mCurrentIndex >= mViewMaxCount)
    	{
    		mCurrentIndex = 0;
    	}else if (mCurrentIndex < 0){
    		mCurrentIndex = mViewMaxCount - 1;
    	}
    	
    	setting = getSharedPreferences(ExplayInfoDetail.key[mcolumindex],Context.MODE_PRIVATE);
    	
    	int alreadyReadCount = setting.getInt("read_number", 0);
    	Log.i("Fling", "alreadyReadCount:" + alreadyReadCount);
    	if(alreadyReadCount < mViewMaxCount) {
    		int iAleadyRead = setting.getInt("already_read_number", 0);
    		int read = iAleadyRead & getAlreadyReadNumber(mCurrentIndex);
    		Log.i("Fling", "read:" + read);
    		if (read == 0)
    		{
    			iAleadyRead =  iAleadyRead | getAlreadyReadNumber(mCurrentIndex);
    			Log.i("Fling", "iAleadyRead" + iAleadyRead);
        		Editor mEditor = setting.edit();
        		mEditor.clear();
        		mEditor.putInt("read_number", alreadyReadCount + 1);
        		mEditor.putInt("already_read_number", iAleadyRead);
        		mEditor.commit();  			
    		}
    		
    		if ((alreadyReadCount + 1) == mViewMaxCount)
    		{
        		readimage.setVisibility(View.VISIBLE);
        		readimage.setBackgroundResource(R.drawable.haveread);
    		}
    		else
    		{
    			readimage.setVisibility(View.GONE);
    		}
    	}else{
    		readimage.setVisibility(View.VISIBLE);
    		readimage.setBackgroundResource(R.drawable.haveread);
    	}

    	setImageResource(mCurrentIndex, R.drawable.screen_indicator_selected);
    }
    
    public int getAlreadyReadNumber(int index)
    {
    	int read = 0;
    	switch (index)
    	{
    	case 0:
    		read = 0x01;
    		break;
    	case 1:
    		read = 0x02;
    		break;
    	case 2:
    		read = 0x04;
    		break;
    	case 3:
    		read = 0x08;
    		break;
    	case 4:
    		break;
    	case 5:
    		read = 0x10;
    		break;
    	default:
    		read = 0;    			
    	}
    	
    	return read;
    }
    
    public void onClick(View v)
    {
    	Log.i("Fling", "OnCLick: "+ v.getId());
    	int index = -1;
    	switch(v.getId())
    	{
    	case R.id.image_view01:
    		index = 0;
    		break;
    	case R.id.image_view02:
    		index = 1;
    		break;
    	case R.id.image_view03:
    		index = 2;
    		break;
    	case R.id.image_view04:
    		index = 3;
    		break;
    	case R.id.image_view05:
    		index = 4;
    		break;
    	case R.id.image_view06:
    		index = 5;
    		break;    	  
    	}
    	
    	if (index != -1){
    		Log.i("Fling", "OnCLick index: "+index);
    		mCurrentIndex = index;
    		flipper.setDisplayedChild(mCurrentIndex);
    		UpdateIndicator();
    	}

    }

    @Override  
    public boolean onTouchEvent(MotionEvent event) {  
        Log.i("Fling", "Activity onTouchEvent!");
        return this.detector.onTouchEvent(event);  
    }

    @Override  
    public boolean onDown(MotionEvent e) {  
        // TODO Auto-generated method stub  
        return false;  
    }  

    /*** 监听滑动*/  
    @Override  
    public boolean onFling(MotionEvent e1, MotionEvent e2, float velocityX, float velocityY) {  
        // TODO Auto-generated method stub  
        Log.i("Fling", "Fling Happened!");  
        if (e1.getX() - e2.getX() > 120) {  
            this.flipper.setInAnimation(AnimationUtils.loadAnimation(this,R.anim.push_left_in));
            this.flipper.setOutAnimation(AnimationUtils.loadAnimation(this,R.anim.push_left_out));

            mCurrentIndex++;
            this.flipper.showNext();
            UpdateIndicator();
            return true;
        } else if (e1.getX() - e2.getX() < -120) {  
            this.flipper.setInAnimation(AnimationUtils.loadAnimation(this,R.anim.push_right_in));
            this.flipper.setOutAnimation(AnimationUtils.loadAnimation(this,R.anim.push_right_out));
            this.flipper.showPrevious();
            mCurrentIndex--;
            UpdateIndicator();
            return true;
        }
        return true;
    }

    @Override  
    public void onLongPress(MotionEvent e) {  
        // TODO Auto-generated method stub  
          
    }  
    @Override  
    public boolean onScroll(MotionEvent e1, MotionEvent e2, float distanceX,  
            float distanceY) {  
        // TODO Auto-generated method stub  
        return false;  
    }  
    @Override  
    public void onShowPress(MotionEvent e) {  
        // TODO Auto-generated method stub  
          
    }  
    @Override  
    public boolean onSingleTapUp(MotionEvent e) {  
        // TODO Auto-generated method stub  
        return false;  
    }  
public void setImageResource(int i, int resource){
	
	switch(i){
		case 0:
		   imageview1.setBackgroundResource(resource);
		   break;
	   case 1:
		   imageview2.setBackgroundResource(resource);
		   break;
	   case 2:
		   imageview3.setBackgroundResource(resource);
		   break;    	
	   case 3:
		   imageview4.setBackgroundResource(resource);
		   break; 
	   case 4:
		   imageview5.setBackgroundResource(resource);
		   break;    
	   case 5:
		   imageview6.setBackgroundResource(resource);
		   break;      			   
	}
}
} 
