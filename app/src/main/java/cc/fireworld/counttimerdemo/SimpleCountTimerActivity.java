package cc.fireworld.counttimerdemo;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import cc.fireworld.countimer.CountTimer;
import cc.fireworld.countimer.SimpleCountTimer;


/**
 * Created by cxx on 15/12/19.
 * xx.ch@outlook.com
 */
public class SimpleCountTimerActivity extends Activity {
    private Button mCountDownBTN;
    private CountTimer<Button> mTimer;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_simple_count_timer);

        mCountDownBTN = (Button) findViewById(R.id.btn_count_down);
    }

    @Override
    protected void onPause() {
        super.onPause();
        if (mTimer != null) {
            mTimer.cancel();
        }
    }

    public void onClick(View view) {
        startCountDown(mCountDownBTN, 30, 500);
    }

    private void startCountDown(Button btn, int totalCount, int interval) {
        if (mTimer == null) {
            mTimer = new SimpleCountTimer<>(btn, totalCount, interval);
        } else {
            mTimer.setView(btn);
            mTimer.setTotalCount(totalCount);
            mTimer.setInterval(interval);
        }
        mTimer.start();
    }
}
