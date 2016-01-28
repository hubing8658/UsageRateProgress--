
package com.iss.usagerateprogress;

import android.app.Activity;
import android.os.Bundle;

public class MainActivity extends Activity {

    private boolean mQuit = false;
    private UsageRateProgress cp1;
    private UsageRateProgress cp2;
    
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        cp1 = (UsageRateProgress) findViewById(R.id.cp1);
        new Thread() {
            
            @Override
            public void run() {
                while(!mQuit) {
                    int progress = (int) (Math.random() * 100);
                    cp1.setPostProgress(progress);
                    try {
                        Thread.sleep(500);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }
            
        }.start();
        
        cp2 = (UsageRateProgress) findViewById(R.id.cp2);
        cp2.setName("内存");
        new Thread() {
            
            @Override
            public void run() {
                while(!mQuit) {
                    int progress = (int) (Math.random() * 100);
                    cp2.setPostProgress(progress);
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
