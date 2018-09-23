package com.ksemin.raksha;

import android.app.Notification;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;

import com.cuneytayyildiz.onboarder.OnboarderActivity;
import com.cuneytayyildiz.onboarder.OnboarderPage;
import com.cuneytayyildiz.onboarder.utils.OnboarderPageChangeListener;
import com.google.firebase.FirebaseApp;
import com.google.firebase.iid.FirebaseInstanceId;

import java.util.Arrays;
import java.util.List;

import br.com.goncalves.pugnotification.notification.PugNotification;


public class IntroActivity extends OnboarderActivity implements OnboarderPageChangeListener {

   @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
      // FirebaseApp.initializeApp(this);


//        PugNotification.with(this)
//                .load()
//                .title("Raksha")
//               .message("Welcome")
//                .bigTextStyle(" Raksha")
//                .ongoing(true)
//                .smallIcon(R.mipmap.applogo)
//                .largeIcon(R.mipmap.applogo)
//                .flags(Notification.DEFAULT_ALL)
//               .simple()
//                .build();

        List<OnboarderPage> pages = Arrays.asList(
                new OnboarderPage.Builder()
                        .title("why")
                        .description("we always would want to know that our loved ones are safe and sound wherever we are")
                     .imageResourceId( 0)
                       .backgroundColor(R.color.intro)
                        .titleColor(R.color.white)
                        .descriptionColor(R.color.white)
                        .multilineDescriptionCentered(true)
                        .build(),


                new OnboarderPage.Builder()
                        .title("how")
                        .description("you can now make sure that your loved ones are safe using raksha application")
                        .imageResourceId(0)
                        .backgroundColor(R.color.intro)
                        .titleColor(R.color.white)
                       .descriptionColor(R.color.white)
                        .multilineDescriptionCentered(true)
                        .build(),
                new OnboarderPage.Builder()
                  .title("what")
                .description("the rakha applicaiton monitors the saftey of your dear ones by ")
                .imageResourceId(0)
                .backgroundColor(R.color.intro)
                .titleColor(R.color.white)
                .descriptionColor(R.color.white)
                .multilineDescriptionCentered(true)
                .build()
        );
        setOnboarderPageChangeListener(this);
        initOnboardingPages(pages);


    }


    /**
     * onFinishbutton which call the Type_acctivity .
     * FLAG_ACTIVITY_CLEAR_TOP this clears the stack so that the app doesnt come back here when back pressed
     *
     */
    @Override
    public void onFinishButtonPressed() {
        Intent intent = new Intent(IntroActivity.this, TypeActivity.class);
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        startActivity(intent);
        finish();

    }



    @Override
    public void onPageChanged(int position) {

    }
}