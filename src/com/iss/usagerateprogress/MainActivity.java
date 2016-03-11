
package com.iss.usagerateprogress;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;

public class MainActivity extends Activity {

    private UsageRateProgress urp1;
    private UsageRateProgress urp2;
    
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        urp1 = (UsageRateProgress) findViewById(R.id.urp1);
        urp2 = (UsageRateProgress) findViewById(R.id.urp2);
    }
    
    public void onClick(View v) {
        int progress1 = (int) (Math.random() * 100);
        urp1.setProgress(progress1);
        int progress2 = (int) (Math.random() * 100);
        urp2.setProgress(progress2);
    }
    
}
