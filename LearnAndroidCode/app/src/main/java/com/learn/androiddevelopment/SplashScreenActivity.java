package com.learn.androiddevelopment;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.learn.androiddevelopment.model.MainTopic;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class SplashScreenActivity extends AppCompatActivity {

    private final int SPLASH_DISPLAY_LENGTH = 1000;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);

        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                /* Create an Intent that will start the Menu-Activity. */
                Intent mainIntent = new Intent(SplashScreenActivity.this, MainActivity.class);
                SplashScreenActivity.this.startActivity(mainIntent);
                SplashScreenActivity.this.finish();
            }
        }, SPLASH_DISPLAY_LENGTH);

        /*List<MainTopic> list = new ArrayList<>();
        list.add(new MainTopic(f16418a, f16419b, f16420c));
        list.add(new MainTopic(f16421d, f16422e, f16423f));
        list.add(new MainTopic(f16424g, f16425h, f16426i));
        list.add(new MainTopic(f16427j, f16428k, f16429l));
        list.add(new MainTopic(f16436s, f16437t, f16438u));
        list.add(new MainTopic(f16439v, f16440w, f16441x));

        try {
            for (int j = 0; j < list.size(); j++) {
                String[] topic = list.get(j).getName();
                String[] description = list.get(j).getDescription();
                String[] url = list.get(j).getUrl();
                JSONArray jsonArray = new JSONArray();
                for (int i = 0; i < topic.length; i++) {
                    JSONObject jsonObject = new JSONObject();
                    jsonObject.put("id", i);
                    jsonObject.put("name", topic[i]);
                    jsonObject.put("description", description[i]);
                    jsonObject.put("url", url[i]);
                    jsonArray.put(jsonObject);
                }
                int maxLogSize = 1000;
                for (int i = 0; i <= jsonArray.toString().length() / maxLogSize; i++) {
                    int start = i * maxLogSize;
                    int end = (i + 1) * maxLogSize;
                    end = end > jsonArray.toString().length() ? jsonArray.toString().length() : end;
                    Log.v("Splash", jsonArray.toString().substring(start, end));
                }
                Log.v("Splash", "\n\n NewTopic\n\n");
            }


        } catch (Exception exeception) {
            exeception.printStackTrace();
        }*/
    }
}
