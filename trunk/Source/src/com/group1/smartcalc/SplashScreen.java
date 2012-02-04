package com.group1.smartcalc;

import com.group1.smartcalc.R;
import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.MotionEvent;

public class SplashScreen extends Activity {
	protected boolean _active = true; // trạng thái hiện tại của activity
    protected int _splashTime = 5000; // thời gian hiển thị splash (ms)
    
    /** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.splashscreen);
        
        // thread để hiển thị SplashScreen
        Thread splashTread = new Thread() {
            @Override
            public void run() {
                try {
                    int waited = 0;
                    while(_active && (waited < _splashTime)) {
                        sleep(100);
                        if(_active) {
                            waited += 100;
                        }
                    }
                } catch(InterruptedException e) {
                    // không làm gì cả
                } finally {
                	// kết thúc activity SplashScreen, gọi ra MainActivity
                    finish();
                    Intent mainIntent = new Intent(SplashScreen.this, MainActivity.class); 
                    SplashScreen.this.startActivity(mainIntent); 
                    SplashScreen.this.finish(); 
                    stop();
                }
            }
        };
        splashTread.start();
    }
    
    @Override
    public boolean onTouchEvent(MotionEvent event) {
    	//Trong quá trình hiển thị Splash Screen
    	//Nếu nhận được sự kiện chạm vào màn hình thì sẽ dừng activity SplashScreen
        if (event.getAction() == MotionEvent.ACTION_DOWN) {
            _active = false;
        }
        return true;
    }
}