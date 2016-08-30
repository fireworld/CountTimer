package cc.fireworld.counttimerdemo;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.Toast;

import java.util.Random;

import cc.fireworld.countimer.CountTimer;


/**
 * Created by cxx on 15/12/19.
 * xx.ch@outlook.com
 */

public class MainActivity extends Activity implements View.OnClickListener, CountTimer.OnCountDownListener<Button>, CountTimer.OnStateChangeListener<Button> {
    private LinearLayout mRootLL;
    private Button mCountBTN;
    private Button mPauseBTN;
    private Button mCancelBTN;
    private Button mRestartBTN;

    private CountTimer<Button> mTimer;
    private Random mRandom = new Random();
    private int[] mImgRes = {
            R.drawable.img1, R.drawable.img2, R.drawable.img3, R.drawable.img4, R.drawable.img5,
            R.drawable.img6, R.drawable.img7, R.drawable.img8, R.drawable.img9, R.drawable.img10
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        initView();
        mTimer = new CountTimer<>(mCountBTN, 30, 500);
        mTimer.setOnCountDownListener(this);
        mTimer.setOnStateChangeListener(this);
    }

    @Override
    protected void onPause() {
        super.onPause();
        mTimer.cancel();
    }

    private void initView() {
        mRootLL = (LinearLayout) findViewById(R.id.ll_root);

        Button toSimpleCountTimerBTN = (Button) findViewById(R.id.btn_to_simple_count_timer);
        toSimpleCountTimerBTN.setOnClickListener(this);

        mCountBTN = (Button) findViewById(R.id.btn_start);
        mCountBTN.setOnClickListener(this);

        mPauseBTN = (Button) findViewById(R.id.btn_pause);
        mPauseBTN.setOnClickListener(this);

        mCancelBTN = (Button) findViewById(R.id.btn_cancel);
        mCancelBTN.setOnClickListener(this);

        mRestartBTN = (Button) findViewById(R.id.btn_restart);
        mRestartBTN.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btn_start:
                mTimer.start();
                Toast.makeText(this, getString(R.string.btn_start), Toast.LENGTH_SHORT).show();
                break;
            case R.id.btn_pause:
                mTimer.pause();
                Toast.makeText(this, getString(R.string.btn_pause), Toast.LENGTH_SHORT).show();
                break;
            case R.id.btn_cancel:
                mTimer.cancel();
                Toast.makeText(this, getString(R.string.btn_cancel), Toast.LENGTH_SHORT).show();
                break;
            case R.id.btn_restart:
                mTimer.restart();
                Toast.makeText(this, getString(R.string.btn_restart), Toast.LENGTH_SHORT).show();
                break;
            case R.id.btn_to_simple_count_timer:
                startActivity(new Intent(this, SimpleCountTimerActivity.class));
                break;
        }
    }

    @Override
    public void onStart(@NonNull Button button, int currentCount) {
    }

    @Override
    public void onCountDown(@NonNull Button button, int currentCount) {
        if (currentCount % 5 == 0) {
            int res = mImgRes[mRandom.nextInt(mImgRes.length)];
            mRootLL.setBackgroundResource(res);
        }
    }

    @Override
    public void onEnd(@NonNull Button button, int currentCount) {
    }

    @Override
    public void onStateChange(@NonNull Button button, int state) {
        mCountBTN.setEnabled(state == CountTimer.STATE_CANCEL);
        mPauseBTN.setEnabled(state == CountTimer.STATE_GOING);
        mCancelBTN.setEnabled(state != CountTimer.STATE_CANCEL);
        mRestartBTN.setEnabled(state == CountTimer.STATE_PAUSE);
    }
}
