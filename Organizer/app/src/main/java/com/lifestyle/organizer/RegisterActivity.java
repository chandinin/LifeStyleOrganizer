package com.lifestyle.organizer;

import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

/**
 * Created by Chandini on 2/29/2016.
 */
public class RegisterActivity extends Activity
{
    public static final String USER_CREDENTIALS = "user_credentials";

    private SharedPreferences preferenceCredentials;
    private SharedPreferences.Editor editor;
    private Animation animScale;
    private EditText userName;
    private EditText password;
    private EditText confirmPassword;
    private Button register;
    private Intent intent;

    private String m_userName;
    private String m_password;
    private String m_confirmPassword;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        userName = (EditText) findViewById(R.id.userName);
        password = (EditText) findViewById(R.id.password);
        confirmPassword = (EditText) findViewById(R.id.confirm_password);
        animScale = AnimationUtils.loadAnimation(this, R.anim.anim_scale);
        register = (Button) findViewById(R.id.button);

        register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                m_userName = userName.getText().toString();
                m_password = password.getText().toString();
                m_confirmPassword = confirmPassword.getText().toString();

                if(m_userName.isEmpty() || m_password.isEmpty() || m_confirmPassword.isEmpty())
                {
                    Toast.makeText(RegisterActivity.this, R.string.error_empty_fields, Toast.LENGTH_LONG).show();
                }
                else if(!m_password.equals(m_confirmPassword))
                {
                    Toast.makeText(RegisterActivity.this, R.string.error_ummatched_password, Toast.LENGTH_LONG).show();
                }
                else
                {
                    preferenceCredentials = getApplicationContext().getSharedPreferences(USER_CREDENTIALS, MODE_PRIVATE);
                    editor = preferenceCredentials.edit();

                    editor.putString("login", m_userName);
                    editor.putString("password", m_password);
                    editor.apply();

                    view.startAnimation(animScale);
                    intent = new Intent(RegisterActivity.this, MainActivity.class);
                    startActivity(intent);
                    finish();
                }
            }
        });

    }

}

