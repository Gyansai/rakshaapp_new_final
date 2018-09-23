package com.ksemin.raksha;

import android.Manifest;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.media.AudioManager;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Build;
import android.os.Bundle;
import android.provider.ContactsContract;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.telephony.SmsManager;
import android.text.TextUtils;
import android.util.Log;
import android.util.Patterns;
import android.view.KeyEvent;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.iid.FirebaseInstanceId;
import com.ksemin.raksha.data.model.remote.APIService_carertaker;
import com.ksemin.raksha.data.model.remote.APIService_falltest;
import com.ksemin.raksha.data.model.remote.APIUtils_caretaker;
import com.ksemin.raksha.data.model.remote.ApiUtils_falltest;
import com.ksemin.raksha.helpers.Informations;
import com.ksemin.raksha.data.model.remote.ApiUtils;


import com.flaviofaria.kenburnsview.KenBurnsView;
import com.goodiebag.pinview.Pinview;
import com.ksemin.raksha.helpers.BlurImage;
import com.ksemin.raksha.data.model.Post;
import com.ksemin.raksha.data.model.remote.APIService;

import com.ksemin.raksha.wearer.bluetooth.DeviceControlActivity;
import com.redbooth.WelcomeCoordinatorLayout;

import io.realm.Realm;
import io.realm.RealmConfiguration;
import io.realm.RealmResults;
import uk.co.deanwild.materialshowcaseview.MaterialShowcaseSequence;
import uk.co.deanwild.materialshowcaseview.ShowcaseConfig;

import static maes.tech.intentanim.CustomIntent.customType;

public class TypeActivity extends AppCompatActivity {


    private LinearLayout mainlyt;
    private LinearLayout lytWearer;
    private LinearLayout lytCareTaker;
    private KenBurnsView image;
    private APIService mAPIService;
    private APIService_carertaker mAPIService_caretaker;
    private APIService_falltest mAPIService_falltest;


    BlurImage mBlurImage;
    WelcomeCoordinatorLayout coordinatorLayout;
    private ImageView toOtp;

    //

    private int TYPE_LAYOUT = 0;
    private int WEARER_LAYOUT = 1;
    private int ADD_CARETAKER_LAYOUT = 2;
    private int OTP_LAYOUT = 3;
    private ImageView ivBackToType;
    private ImageView ivNext;
    public int randomPIN;
    public Pinview pin;
    private static final int RESULT_PICK_CONTACT = 8;
    public String enterotp;
    String otp;
    private ImageView ivBackWearerDetails;
    private ImageView genderTickM;
    public Toast backtoast;
    private String phone_number;
    private ImageView genderTickF;
    private ImageView male;
    private ImageView female;
    private EditText etCarePhone;
    private ImageView ivSendSms;
    private EditText otpEnter;
    private ImageView otpCheck;
    String TAG = "main";
    public Boolean maletrue = false;
    public String check;
    public Boolean femtrue = false;

    private EditText etName;
    private EditText etAge;
    private long backpresstime;
    private EditText etBlood;
    private TextView preview;
    public String name;
    public String age;
    public String bloodgrp;
    private Button emergency;
    private Button button;

    public String gender;
    public  String output;
    Splash msplash;


    Realm realm;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_type);
        mAPIService = ApiUtils.getAPIService();
        mAPIService_caretaker=  APIUtils_caretaker.getAPIService();
        mAPIService_falltest= ApiUtils_falltest.getAPIService();
        Realm.init(this);
        try{
            realm = Realm.getDefaultInstance();

        }catch (Exception e){

            // Get a Realm instance for this thread
            RealmConfiguration config = new RealmConfiguration.Builder()
                    .deleteRealmIfMigrationNeeded()
                    .build();
            realm = Realm.getInstance(config);

        }
        /**left-to-right
         *right-to-left
         *bottom-to-up
         *up-to-bottom
         *fadein-to-fadeout
         *rotateout-to-rotatein*/


        customType(TypeActivity.this, "up-to-bottom");
        mBlurImage = new BlurImage();

        coordinatorLayout = (WelcomeCoordinatorLayout) findViewById(R.id.coordinator);
        coordinatorLayout.addPage(R.layout.select_type_layout, R.layout.wearer_details_layout, R.layout.add_caretaker_layout, R.layout.caretaker_login_layout);
        coordinatorLayout.setScrollingEnabled(false);
        coordinatorLayout.showIndicators(false);
        initView();
    }

    private void checkBTPermissions() {
        if (Build.VERSION.SDK_INT > Build.VERSION_CODES.LOLLIPOP) {
            int permissionCheck = this.checkSelfPermission("Manifest.permission.ACCESS_FINE_LOCATION");
            permissionCheck += this.checkSelfPermission("Manifest.permission.ACCESS_COARSE_LOCATION");
            permissionCheck += this.checkSelfPermission("android.permission.SEND_SMS");
            permissionCheck += this.checkSelfPermission("android.permission.CALL_PHONE");
            if (permissionCheck != 0) {
                this.requestPermissions(new String[]{Manifest.permission.SEND_SMS,Manifest.permission.ACCESS_FINE_LOCATION,Manifest.permission.BLUETOOTH,Manifest.permission.BLUETOOTH_ADMIN,Manifest.permission.CALL_PHONE,Manifest.permission.ACCESS_COARSE_LOCATION}, 1002);


            }
        } else {
            Log.d(TAG, "checkBTPermissions: No need to check permissions. SDK version < LOLLIPOP.");
        }
    }

    private void initView() {
        checkBTPermissions();
        //mPreferences = PreferenceManager.getDefaultSharedPreferences(getApplicationContext());
        mainlyt = (LinearLayout) findViewById(R.id.mainlyt);
        lytWearer = (LinearLayout) findViewById(R.id.lytWearer);
        lytCareTaker = (LinearLayout) findViewById(R.id.lytCareTaker);
        ImageView imbutton = (ImageView) findViewById(R.id.wearer_bt);
        ImageView imbutton1 = (ImageView) findViewById(R.id.caretakerbt);
         button = (Button)findViewById(R.id.falltestbt);
        Log.i("firebaseid",""+ FirebaseInstanceId.getInstance().getToken());

        ShowcaseConfig config = new ShowcaseConfig();
        config.setDelay(500); // half second between each showcase view
        boolean checknew=msplash.isfirstrun;

        if (checknew) {

            MaterialShowcaseSequence sequence = new MaterialShowcaseSequence(TypeActivity.this, "" + Math.random());

            sequence.setConfig(config);

            sequence.addSequenceItem(imbutton,
                    "If you are a raksha band user click here ", "GOT IT");

            sequence.addSequenceItem(imbutton1,
                    "If you want to be a caretaker click here ", "GOT IT");

            sequence.start();
        }


//        image = (KenBurnsView) findViewById(R.id.image);
//        image.setImageDrawable(getDrawable(R.drawable.background));
//       Bitmap icon = BitmapFactory.decodeResource(getResources(),R.drawable.background);
//       Bitmap mBitmap =  mBlurImage.fastblur(icon,1,10);
//       image.setImageBitmap(mBitmap);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.i("falltest","button pressed");
                ConnectivityManager cm = (ConnectivityManager)getSystemService(Context.CONNECTIVITY_SERVICE);
                NetworkInfo netInfo = cm.getActiveNetworkInfo();
                if (netInfo != null && netInfo.isConnectedOrConnecting()) {
                    new post_falltest().execute();

                } else {
                    SmsManager smsManager = SmsManager.getDefault();
                    smsManager.sendTextMessage("+917995530093", null, "I need help  ", null, null);
                    }
            }
        });
        imbutton1.setOnClickListener(new View.OnClickListener(){


            @Override
            public void onClick(View v) {
                Log.i("carebutton","pressed" );
                //post post_caretaker = new post_caretaker();
                new post_caretaker().execute();
            }
        });
        lytWearer.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                coordinatorLayout.setCurrentPage(WEARER_LAYOUT, true);
                Realm realm = Realm.getDefaultInstance(); //creating  database oject
                RealmResults<Informations> results = realm.where(Informations.class).findAllAsync();
                //fetching the data
                results.load();
                output="";
                for(Informations informations:results){
                    output+=informations.toString();
                }

                preview.setText(output);

            }
        });
        toOtp = (ImageView) findViewById(R.id.iv_caretaker_num);
        etName = (EditText) findViewById(R.id.et_nme);
        etAge = (EditText) findViewById(R.id.et_age);
        etBlood = (EditText) findViewById(R.id.et_blood);
        preview = (TextView) findViewById(R.id.preview);
        toOtp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                name = etName.getEditableText().toString();

                age=etAge.getText().toString();
                bloodgrp=etBlood.getText().toString();
                realm.beginTransaction();  //open the database
                //database operation
                Informations obj = realm.createObject(Informations.class);  //this will create a
                //   information object which will be inserted in dtabase

                obj.setName(name);
                obj.setAge(age);
                obj.setBlood_group(bloodgrp);
                obj.setGender(gender);   //inserted all data to database
                realm.commitTransaction(); //close the database
                Log.i("info",""+output);
               // Log.i("name",""+name);
                Toast.makeText(TypeActivity.this, "saving data", Toast.LENGTH_SHORT).show();
                coordinatorLayout.setCurrentPage(2, true);


                //public void onClick(View v) {}

            }
        });
        ivBackToType = (ImageView) findViewById(R.id.iv_back_to_type);
        ivBackToType.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                coordinatorLayout.setCurrentPage(TYPE_LAYOUT, true);
            }
        });

        ivBackWearerDetails = (ImageView) findViewById(R.id.iv_back_wearer_details);
        ivBackWearerDetails.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                coordinatorLayout.setCurrentPage(WEARER_LAYOUT, true);
            }
        });
        genderTickM = (ImageView) findViewById(R.id.gender_Tick_m);
        genderTickF = (ImageView) findViewById(R.id.gender_Tick_f);
        male = (ImageView) findViewById(R.id.male);
        male.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                genderTickM.setVisibility(View.VISIBLE);
                maletrue = true;
                femtrue = false;
                gender="male";

                genderTickF.setVisibility(View.INVISIBLE);

            }
        });
        female = (ImageView) findViewById(R.id.female);
        female.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                genderTickF.setVisibility(View.VISIBLE);
                femtrue = true;
                maletrue = false;
                gender="female";
                genderTickM.setVisibility(View.INVISIBLE);
            }
        });
        ivSendSms = (ImageView) findViewById(R.id.iv_send_sms);
        ivSendSms.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (ContextCompat.checkSelfPermission(TypeActivity.this, Manifest.permission.SEND_SMS)
                        != PackageManager.PERMISSION_GRANTED) {
                    Toast.makeText(getApplicationContext(), "please go to the settings and enable permissions ", Toast.LENGTH_SHORT).show();
                } else {

                    post posts = new post();
                    posts.execute();
                    pin = (Pinview) findViewById(R.id.pinview);
                    etCarePhone = (EditText) findViewById(R.id.et_care_phone);
                    phone_number = String.valueOf(etCarePhone.getText());
                    if (!TextUtils.isEmpty(phone_number)) {
                        coordinatorLayout.setCurrentPage(4, true);

                        boolean phone_num_check = isValidMobile(phone_number);
                        if (phone_num_check) {
                            SmsManager smsManager = SmsManager.getDefault();
                            smsManager.sendTextMessage("91" + phone_number, null, generatePIN(), null, null);
                            Toast.makeText(getApplication(), "OTP SENT ", Toast.LENGTH_SHORT).show();
                        }
                        if (!phone_num_check) {

                            //Toast.makeText(getApplication(), "the number you have entered is invalid  ", Toast.LENGTH_SHORT).show();
                        }
                    }

                }

            }
        });


        otpCheck = (ImageView) findViewById(R.id.next_ble);
        otpCheck.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                enterotp = String.valueOf(pin.getValue());
                Toast.makeText(getApplication(), "" + enterotp, Toast.LENGTH_SHORT).show();
                Log.i("TYPEACTIVITY", "" + enterotp);
                if (enterotp.equals(otp)) {
                    Toast.makeText(getApplication(), "OTP VERIFIED", Toast.LENGTH_SHORT).show();
                    Intent intent = new Intent(TypeActivity.this, DeviceControlActivity.class);
                    startActivity(intent);

                } else {
                    Log.i("TYPEACTIVITY", "" + enterotp);
                    Toast.makeText(getApplication(), "wrong " + enterotp, Toast.LENGTH_SHORT).show();
                }
            }
        });

        emergency = (Button) findViewById(R.id.emergency);
        emergency.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (ContextCompat.checkSelfPermission(TypeActivity.this, Manifest.permission.CALL_PHONE)
                        != PackageManager.PERMISSION_GRANTED) {
                    Toast.makeText(getApplicationContext(), "please go to the settings and enable calling permission", Toast.LENGTH_SHORT).show();
                } else {
                    String number = "+919652552367";
                    Intent callIntent = new Intent(Intent.ACTION_CALL);
                    callIntent.setData(Uri.parse("tel:" + number));
                    AudioManager audiomanager = (AudioManager) getSystemService(Context.AUDIO_SERVICE);
                    audiomanager.setMode(AudioManager.MODE_IN_CALL);
                    audiomanager.setSpeakerphoneOn(true);
                    audiomanager.setMode(AudioManager.MODE_NORMAL);
                    boolean var = audiomanager.isSpeakerphoneOn();
                    Log.d("Wearer", "" + var);
                    startActivity(callIntent);
                }
            }
        });
    }

    public String generatePIN() {

        //generate a 4 digit integer 1000 <10000
        randomPIN = (int) (Math.random() * 9000) + 1000;
        otp = String.valueOf(randomPIN);

        return otp;

    }

    private boolean isValidMobile(String phone) {
        return Patterns.PHONE.matcher(phone).matches();
    }

    public void pickContact() {
        Intent contactPickerIntent = new Intent(Intent.ACTION_PICK,
                ContactsContract.CommonDataKinds.Phone.CONTENT_URI);
        startActivityForResult(contactPickerIntent, RESULT_PICK_CONTACT);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        // check whether the result is ok
        if (resultCode == RESULT_OK) {
            // Check for the request code, we might be usign multiple startActivityForReslut
            switch (requestCode) {
                case RESULT_PICK_CONTACT:
                    contactPicked(data);
                    break;
            }
        } else {
            Log.e("TYPEACTIVITY", "Failed to pick contact");
        }
    }

    private void contactPicked(Intent data) {
        Cursor cursor = null;
        try {
            // getData() method will have the Content Uri of the selected contact
            Uri uri = data.getData();
            //Query the content uri
            cursor = getContentResolver().query(uri, null, null, null, null);
            cursor.moveToFirst();
            // column index of the phone number
            int phoneIndex = cursor.getColumnIndex(ContactsContract.CommonDataKinds.Phone.NUMBER);
            // column index of the contact name
            int nameIndex = cursor.getColumnIndex(ContactsContract.CommonDataKinds.Phone.DISPLAY_NAME);
            phone_number = cursor.getString(phoneIndex);
            etCarePhone.setText("" + phone_number, EditText.BufferType.EDITABLE);
            Log.i("daasd","fdsf");


        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void onBackPressed() {
        if (backpresstime + 2000 > System.currentTimeMillis()) {
            backtoast.cancel();
            /*unregisterReceiver(mBroadcastReceiver1);
            unregisterReceiver(mBroadcastReceiver1);
            unregisterReceiver(mBroadcastReceiver2);*/

            // super.onBackPressed();
            finish();
            return;
        }
        else {
            backtoast= Toast.makeText(getBaseContext(),"Press back again to go back to main menu",Toast.LENGTH_SHORT);
            backtoast.show();
        }
        backpresstime=System.currentTimeMillis();
        if (coordinatorLayout.getPageSelected() > 0) {
            coordinatorLayout.setCurrentPage(coordinatorLayout.getPageSelected() - 1, true);
        } else {
            Toast.makeText(TypeActivity.this, "Are you Sure want to exit!", Toast.LENGTH_SHORT).show();
            System.exit(1);
        }
    }
    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if ((keyCode == KeyEvent.KEYCODE_BACK)) {
            onBackPressed();
        }
        return false;
    }
    public class post extends AsyncTask<String, String, String>{

        @Override
        protected String doInBackground(String... strings) {
            //Realm.init(TypeActivity);
            //Realm realm = Realm.getDefaultInstance(); //creating  database oject
            //RealmResults<Informations> results = realm.where(Informations.class).findAllAsync();
            //fetching the data
            //results.load();
            //Log.i("hi","" +String.valueOf(results));
             //String name2 = Informations.toString();
          // Log.i("hi",""+name);
           String phone_numberw="8179535894" +
                   "";
           String wearer_fire_id= FirebaseInstanceId.getInstance().getToken();
           sendPost(name,age,gender, bloodgrp,phone_numberw,wearer_fire_id,phone_number );

            return name;
        }
    }
    public void sendPost(String name, String age,String gender,String bgrp,String user_id ,String wearer_fire_id,String caretaker_number) {
        //Log.i("hi",user_id);
        mAPIService.savePost(user_id,name, age, gender ,bgrp,wearer_fire_id,caretaker_number).enqueue(new Callback<Post>() {
        });

    }
    public class post_caretaker extends AsyncTask<String,String,String>{
        @Override
        protected String doInBackground(String... strings) {
            String caretaker_fire_id = FirebaseInstanceId.getInstance().getToken();
            String phone_numberc= "7995530093";
            String caretaker_name="Gyan sai";
            sendPost_caretaker(caretaker_name,phone_numberc,caretaker_fire_id);
        return phone_number;
        }
    }
    public void sendPost_caretaker(String name,String caretaker_number,String caretaker_fire_id){
        Log.i("called",caretaker_number);
        mAPIService_caretaker.savePost_caretaker(name,caretaker_number,caretaker_fire_id).enqueue(new Callback<Post>(){});
    }
    public class post_falltest extends AsyncTask<String,String,String> {
        @Override
        protected String doInBackground(String... strings) {
            String user_id= "8179535894";
            sendpost_falltest(user_id);
            return user_id;
        }
    }
    public void sendpost_falltest (String user_id){
        Log.i("called",user_id);
        mAPIService_falltest.savepost_falltest(user_id).enqueue(new Callback<Post>(){});
    }




}
