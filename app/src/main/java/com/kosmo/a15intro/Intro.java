package com.kosmo.a15intro;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;

public class Intro extends AppCompatActivity {

    /*
        Manifest.xml 파일을 수정하여 Intro가 제일 먼저 실행되도록 수정한다.
     */
    //일정시간 이후에 실행하기 위해 Handler 객체를 생성한다.
    Handler handler = new Handler();
    Runnable runnable = new Runnable() {
        @Override
        public void run() {
            //인트로 화면 이후에 메인 액티비티를 실행하기 위해 인텐트 객체 생성
            Intent intent = new Intent(getApplicationContext(), MainActivity.class);
            startActivity(intent);

            /*
            액티비티가 실행되거나 종료될 때 애니메이션 효과를 부여한다.
            overridePendingTransition(새롭게 실행되는 액티비티 효과,
                                            종료되는 액티비티 효과);
             */
            //overridePendingTransition(R.anim.slide_in_bottom, R.anim.hold);
            overridePendingTransition(R.anim.fade_in, R.anim.hold);

            //Intro 액티비티 종료
            finish();
        }
    };

    //액티비티 실행 시 3번째(마지막)로 실행되는 수명주기 함수
   @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_intro);
    }
    @Override
    protected void onResume(){
       /*
       onCreate() 실행 후에 실행되는 함수로서 Intro 화면에 진입한 후
       2초 후에 runnable 객체를 실행하게 된다.
        */
        super.onResume();
        handler.postDelayed(runnable, 2000);
    }

    //액티비티 종료시 1번째로 실행되는 수명주기 함수
    @Override
    protected void onPause() {
       /*
       Intro 액티비티가 종료될 때 handler 에 예약해놓은 작업을 취소한다.
        */
        super.onPause();
        handler.removeCallbacks(runnable);
    }
}
