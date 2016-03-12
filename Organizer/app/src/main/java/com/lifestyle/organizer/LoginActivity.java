package com.lifestyle.organizer;

import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

/**
 * Created by Chandini on 2/29/2016.
 */
public class LoginActivity extends Activity
{
    public static final String REMEMBER_PASSWORD = "remember_password";
    public static final String USER_CREDENTIALS = "user_credentials";

    private SharedPreferences preferenceRememberPassword;
    private SharedPreferences preferenceCredentials;
    private SharedPreferences.Editor editor;
    private Animation animScale;
    private EditText userName;
    private EditText password;
    private Button signIn;
    private TextView signUp;
    private Intent intent;
    private CheckBox rememberPassword;

    private String m_userName;
    private String m_password;
    private String m_secureUserName;
    private String m_securePassword;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        userName = (EditText) findViewById(R.id.login);
        password = (EditText) findViewById(R.id.password);
        animScale = AnimationUtils.loadAnimation(this,R.anim.anim_scale);
        rememberPassword = (CheckBox) findViewById(R.id.checkBox);
        signIn = (Button) findViewById(R.id.button);
        signUp = (TextView) findViewById(R.id.signup);

        preferenceRememberPassword = getApplicationContext().getSharedPreferences(REMEMBER_PASSWORD, MODE_PRIVATE);
        preferenceCredentials = getApplicationContext().getSharedPreferences(USER_CREDENTIALS, MODE_PRIVATE);

        signIn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //Save the selection in the preference
                if(rememberPassword.isChecked())
                {
                    editor = preferenceRememberPassword.edit();
                    editor.putBoolean("rememberPassword", true);
                    editor.apply();
                }
                else
                {
                    editor = preferenceRememberPassword.edit();
                    editor.putBoolean("rememberPassword", false);
                    editor.apply();
                }

                m_secureUserName = preferenceCredentials.getString("login", "");
                m_securePassword = preferenceCredentials.getString("password", "");

                if(m_secureUserName.isEmpty() || m_securePassword.isEmpty())
                {
                    Toast.makeText(LoginActivity.this, R.string.error_no_account, Toast.LENGTH_LONG).show();
                }
                else
                {
                    m_userName = userName.getText().toString();
                    m_password = password.getText().toString();

                    if(m_userName.isEmpty() || m_password.isEmpty())
                    {
                        Toast.makeText(LoginActivity.this, R.string.error_creds_empty, Toast.LENGTH_LONG).show();
                    }
                    else
                    {
                        if(!m_secureUserName.equals(m_userName))
                        {
                            Toast.makeText(LoginActivity.this, R.string.error_invalid_username, Toast.LENGTH_LONG).show();
                        }
                        else if(!m_securePassword.equals(m_password))
                        {
                            Toast.makeText(LoginActivity.this, R.string.error_invalid_password, Toast.LENGTH_LONG).show();
                        }
                        else
                        {
                            view.startAnimation(animScale);
                            intent = new Intent(LoginActivity.this, MainActivity.class);
                            startActivity(intent);
                            finish();
                        }
                    }
                }
            }
        });

        signUp.setOnClickListener(
                new TextView.OnClickListener(){

                    @Override
                    public void onClick(View view) {
                        intent = new Intent(LoginActivity.this, RegisterActivity.class);
                        startActivity(intent);
                        finish();
                    }
                }
        );

    }
}
