package com.ksemin.raksha.welcomeFragments;

import android.os.Bundle;
import android.os.CountDownTimer;
import android.support.v7.app.AppCompatActivity;
import android.telephony.SmsManager;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.ksemin.raksha.R;

public class falldetected extends AppCompatActivity {
   ImageView imv;
   TextView mtextField;
   String count = "false";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_falldetected);
        mtextField=(TextView)findViewById(R.id.textView4);
        imv=(ImageView)findViewById(R.id.imageV);



        imv.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
               /* Intent intent = new Intent(falldetected.this, MainActivity.class);
                intent.putExtra("EXIT", false);
                intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                startActivity(intent);*/
               count ="true";
               finish();

            }
        });


            CountDownTimer countDownTimer = new CountDownTimer(15000, 1000) {

                public void onTick(long millisUntilFinished) {
                    mtextField.setText("sending message in  : " + millisUntilFinished / 1000);
                }

                public void onFinish() {
                    if (count.equals("false")) {
                        mtextField.setText("done!");
                        SmsManager smsManager = SmsManager.getDefault();
                        smsManager.sendTextMessage("+918179535894", null, "I need help  ", null, null);
                        Log.d("Wearer", "sent");
                    }
                }
            }.start();





    }



    private void sendSMS(String phoneNumber, String message) {
        SmsManager sms = SmsManager.getDefault();
        sms.sendTextMessage(phoneNumber, null, message, null, null);

    }

}
