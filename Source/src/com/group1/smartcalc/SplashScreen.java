package com.group1.smartcalc;


import com.group1.smartcalc.R;
import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.MotionEvent;
import android.view.Window;
import android.view.WindowManager;

public class SplashScreen extends Activity implements Runnable {
	protected boolean _active = true; // trạng thái hiện tại của activity
    protected int _splashTime = 3000; // thời gian hiển thị splash (ms)
    private Thread _splashThread;
    /** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.splashscreen);
        
        _active = true;
    }
    
	@Override
	protected void onResume() {
		super.onResume();
	}
    
	@Override
	public void onWindowFocusChanged(boolean hasFocus) {
		// neu hien tai ko co tuyen nao chay thi se khoi tao thread moi de chay qua trinh gioi thieu logo
		if (_splashThread == null) {
			_splashThread = new Thread(this);
			_splashThread.start();
		}
		super.onWindowFocusChanged(hasFocus);
	}
    
	
    public void run() {
    	try {
			while (_active && (_splashTime >= 0)) {
				_splashTime -= 100;
				Thread.sleep(100);
			}

    	} catch (InterruptedException e) {
			// TODO: handle exception
		} finally {
			startActivity(new Intent(getApplicationContext(),CalcActivity.class));
			finish();
		}
    }
    
    @Override
    public boolean onTouchEvent(MotionEvent event) {
    	//Trong quá trình hiển thị Splash Screen
    	//Nếu nhận được sự kiện chạm vào màn hình thì sẽ dừng activity SplashScreen
        if (event.getAction() == MotionEvent.ACTION_DOWN) {
            _active = false;
        }
        return super.onTouchEvent(event);
    }
}