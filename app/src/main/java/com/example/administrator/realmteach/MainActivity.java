package com.example.administrator.realmteach;

import android.content.Context;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import io.realm.Realm;
import io.realm.RealmConfiguration;
import io.realm.RealmResults;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {
    private Context context;
    private Button btn;
    private Realm mRealm;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
       mRealm = Realm.getInstance(new RealmConfiguration.Builder(MainActivity.this)
                        .name("myRealm.realm")
                        .build()
        );
        //开启事务
        mRealm.beginTransaction();
        Country country1 = mRealm.createObject(Country.class);
        country1.setCity("SZ");
        country1.setPopulation(200000000);
        country1.setId(01);
        //提交事务
        //mRealm.commitTransaction();

        //方法二
        // Create the object
        Country country2 = new Country();
        country2.setCity("BeiJing");
        country2.setPopulation(146430430);
        country2.setId(03);

       // mRealm.beginTransaction();
        Country copyOfCountry2 = mRealm.copyToRealm(country2);

        Country country3 = mRealm.createObject(Country.class);
        country1.setCity("DG");
        country1.setPopulation(200000000);
        country1.setId(02);

        mRealm.commitTransaction();

        btn = (Button) this.findViewById(R.id.btn01);
        btn.setOnClickListener(this);
    }


    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.btn01:
                RealmResults<Country> results01=mRealm.where(Country.class).findAll();
              for(Country c:results01){
                  Log.e("result01:",c.getCity());
              }
        }
    }
}
