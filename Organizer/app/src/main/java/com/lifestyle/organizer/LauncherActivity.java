package com.lifestyle.organizer;

import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;

/**
 * Created by Chandini on 3/12/2016.
 * This is the first activity to get started when the application is launched
 */
public class LauncherActivity extends Activity
{
    public static final String REMEMBER_PASSWORD = "remember_password";

    private SharedPreferences preferenceRememberPassword;
    private Boolean rememberPassword;
    private Intent intent;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_launcher);

        preferenceRememberPassword = getApplicationContext().getSharedPreferences(REMEMBER_PASSWORD, MODE_PRIVATE);
        rememberPassword = preferenceRememberPassword.getBoolean("rememberPassword",false);

        if(rememberPassword == true)
        {
            intent = new Intent(LauncherActivity.this, MainActivity.class);
            startActivity(intent);
            finish();
        }
        else
        {
            intent = new Intent(LauncherActivity.this, LoginActivity.class);
            startActivity(intent);
            finish();
        }

    }
}
