package com.example.asus.whatson;

import android.content.Intent;
import android.graphics.Typeface;
import android.os.Bundle;
import android.support.design.widget.TextInputLayout;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.AppCompatButton;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class LoginActivity extends AppCompatActivity{

    private TextInputLayout[] textInputLayouts = new TextInputLayout[2];
    private TextView[] textViews = new TextView[2];
    private EditText usernameEdit, passwordEdit;
    private TextView whatsOnText;
    private AppCompatButton loginButton;

    private SessionManager sessionManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        //Initialize the layout objects that use external font
        whatsOnText = (TextView)findViewById(R.id.whatsonText);
        textInputLayouts[0] = (TextInputLayout)findViewById(R.id.inputUsernameLayout);
        textInputLayouts[1] = (TextInputLayout)findViewById(R.id.inputPasswordLayout);
        textViews[0] = (TextView)findViewById(R.id.noAccountText);
        textViews[1] = (TextView)findViewById(R.id.signUpText);
        loginButton = (AppCompatButton) findViewById(R.id.btn_login);
        usernameEdit = (EditText)findViewById(R.id.input_username);
        passwordEdit = (EditText)findViewById(R.id.input_password);

        //Change the typeface of the layout objects
        Typeface typeface = Typeface.createFromAsset(getAssets(),"fonts/You're So Cool - TTF.ttf");
        for (TextInputLayout ed: textInputLayouts
             ) {ed.setTypeface(typeface);
        }
        for (TextView tx:textViews
             ) {tx.setTypeface(typeface);
        }
        loginButton.setTypeface(typeface);
        whatsOnText.setTypeface(typeface);

        //Checking the session whether user already logined or not. If yes, change the login activity to main activity
        sessionManager = new SessionManager(getApplicationContext());
        if (sessionManager.isLoggedIn()) goToMainActivity();

        loginButton.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                switch (v.getId()) {
                    case R.id.btn_login:
                        String usernameInput = usernameEdit.getText().toString().trim();
                        String passwordInput = passwordEdit.getText().toString().trim();
                        if (!usernameInput.isEmpty() && !passwordInput.isEmpty()) {
                            if (checkLogin(usernameInput,passwordInput)) {

                            }
                            else {

                            }
                        }
                        else {
                            Toast.makeText(getApplicationContext(),
                                    "Please input the credentials", Toast.LENGTH_SHORT).show();
                        }
                        goToMainActivity();
                    default:
                }
            }
        });

        textViews[1].setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                switch (v.getId()) {
                    case R.id.signUpText:
                        GoToRegisterActivity();
                }
            }
        });

    }

    private void goToMainActivity(){
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
    }

    private void GoToRegisterActivity() {
        Intent intent = new Intent(this, RegisterActivity.class);
        startActivity(intent);
    }

    private boolean checkLogin(final String username, final String password) {
        return false;
    }

}
