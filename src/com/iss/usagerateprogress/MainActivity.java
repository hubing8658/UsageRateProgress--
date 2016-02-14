
package com.iss.usagerateprogress;

import android.app.Activity;
import android.os.Bundle;

public class MainActivity extends Activity {

    private boolean mQuit = false;
    private UsageRateProgress urp1;
    private UsageRateProgress urp2;
    
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        urp1 = (UsageRateProgress) findViewById(R.id.urp1);
        new Thread() {
            
            @Override
            public void run() {
                while(!mQuit) {
                    int progress = (int) (Math.random() * 100);
                    urp1.setPostProgress(progress);
                    try {
                        Thread.sleep(500);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }
            
        }.start();
        
        urp2 = (UsageRateProgress) findViewById(R.id.urp2);
        new Thread() {
            
            @Override
            public void run() {
                while(!mQuit) {
                    int progress = (int) (Math.random() * 100);
                    urp2.setPostProgress(progress);
                    try {
                        Thread.sleep(500);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }
            
        }.start();
    }
    
    @Override
    protected void onDestroy() {
        super.onDestroy();
        mQuit = true;
    }
    
}
