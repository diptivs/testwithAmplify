package com.diptivs.android.testaws;

import android.os.AsyncTask;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.amazonaws.auth.CognitoCachingCredentialsProvider;
import com.amazonaws.mobileconnectors.apigateway.ApiClientFactory;
import com.amazonaws.mobileconnectors.cognitoidentityprovider.CognitoDevice;
import com.amazonaws.mobileconnectors.cognitoidentityprovider.CognitoUser;
import com.amazonaws.mobileconnectors.cognitoidentityprovider.CognitoUserAttributes;
import com.amazonaws.mobileconnectors.cognitoidentityprovider.CognitoUserCodeDeliveryDetails;
import com.amazonaws.mobileconnectors.cognitoidentityprovider.CognitoUserPool;
import com.amazonaws.mobileconnectors.cognitoidentityprovider.CognitoUserSession;
import com.amazonaws.mobileconnectors.cognitoidentityprovider.continuations.AuthenticationContinuation;
import com.amazonaws.mobileconnectors.cognitoidentityprovider.continuations.AuthenticationDetails;
import com.amazonaws.mobileconnectors.cognitoidentityprovider.continuations.ChallengeContinuation;
import com.amazonaws.mobileconnectors.cognitoidentityprovider.continuations.MultiFactorAuthenticationContinuation;
import com.amazonaws.mobileconnectors.cognitoidentityprovider.handlers.AuthenticationHandler;
import com.amazonaws.mobileconnectors.cognitoidentityprovider.handlers.GenericHandler;
import com.amazonaws.mobileconnectors.cognitoidentityprovider.handlers.SignUpHandler;
import com.amazonaws.regions.Regions;

import java.util.HashMap;
import java.util.Map;

import testapi1.TestapiClient;

public class MainActivity extends AppCompatActivity {
    THSensorAsyncTask task=null;
    CognitoUserPool userPool;
     TestapiClient client;
    private static CognitoUserSession currSession;
    private static CognitoDevice newDevice;
    private String userName = "";
    private String userPassword = "";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        final Regions cognitoRegion = Regions.DEFAULT_REGION;
        userPool = new CognitoUserPool(getApplicationContext(), "us-west-2_7a0XnEarW", "131og4q7fkhq42h26f2n60988j", "", cognitoRegion);





        Button register = (Button) findViewById(R.id.buttonRegister);

        Button btnConfCode = (Button) findViewById(R.id.buttonConfCode);

        Button btnSignIn = (Button) findViewById(R.id.buttonSignIn);

        btnSignIn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                signInUser();
            }
        });

        btnConfCode.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                EditText confCode = (EditText) findViewById(R.id.textViewConfCode);
                String confirmCode = confCode.getText().toString();
                userPool.getUser(userName).confirmSignUpInBackground(confirmCode, false, confirmationCallback);
                }
        });

        register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Create a CognitoUserAttributes object and add user attributes
                CognitoUserAttributes userAttributes = new CognitoUserAttributes();

// Add the user attributes. Attributes are added as key-value pairs
// Adding user's given name.
// Note that the key is "given_name" which is the OIDC claim for given name
                //userAttributes.addAttribute("given_name", "Dipti");

// Adding user's phone number
                //userAttributes.addAttribute("phone_number", "1111111111");

// Adding user's email address
                //userAttributes.addAttribute("email", "dipti@edu");
                userPool.signUpInBackground(userName, userPassword, userAttributes, null, signupCallback);
            }
        });

        FloatingActionButton fab = findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // Invoke your apiConfigPost method
                //client.itemsOptions();

                if(!(task==null))
                    task.cancel(true);

                task = new THSensorAsyncTask();
                task.execute();
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });
    }

    private void signInUser() {
        Log.d("Dipti","calling signInUser");
        userPool.getUser(userName).getSessionInBackground(authenticationHandler);

    }

    // Callback handler for confirmSignUp API
    GenericHandler confirmationCallback = new GenericHandler() {

        @Override
        public void onSuccess() {
            Log.d("Dipti","confirmationCallback Success");
            // User was successfully confirmed
        }

        @Override
        public void onFailure(Exception exception) {
            Log.d("Dipti","confirmationCallback Failed");
            // User confirmation failed. Check exception for the cause.
        }
    };

    SignUpHandler signupCallback = new SignUpHandler() {

        @Override
        public void onSuccess(CognitoUser cognitoUser, boolean userConfirmed, CognitoUserCodeDeliveryDetails cognitoUserCodeDeliveryDetails) {
            // Sign-up was successful

            // Check if this user (cognitoUser) needs to be confirmed
            if(!userConfirmed) {
                Log.d("Dipti","signupCallback success: need conf code");
                // This user must be confirmed and a confirmation code was sent to the user
                // cognitoUserCodeDeliveryDetails will indicate where the confirmation code was sent
                // Get the confirmation code from user
            }
            else {
                Log.d("Dipti","signupCallback success");
                // The user has already been confirmed
            }
        }

        @Override
        public void onFailure(Exception exception) {
            Log.d("Dipti","signupCallback failure");
            // Sign-up failed, check exception for the cause
        }
    };





    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    public class ReturnObject {
        int temperature;
        int humidity;
        int activity;
        int count;
        ReturnObject( int t, int h, int a, int c)
        {
            temperature = t;
            humidity = h;
            activity = a;
            count = c;
        }
    }

    private class THSensorAsyncTask extends AsyncTask<Integer, ReturnObject, Void> {
    @Override
    protected void onPreExecute() {
        super.onPreExecute();
    }

    @Override
    protected Void doInBackground(Integer... number) {
        // Get id token from CognitoUserSession.
        //String idToken = currSession.getIdToken().getJWTToken();

        Log.d("Dipti","calling setLogins");
        // Create a credentials provider, or use the existing provider.
        CognitoCachingCredentialsProvider credentialsProvider = new CognitoCachingCredentialsProvider(getApplicationContext(), "us-west-2:e69e05ad-e41c-489c-92c9-feb1c7e03f33", Regions.DEFAULT_REGION);

        // Set up as a credentials provider.
        Map<String, String> logins = new HashMap<String, String>();
        logins.put("cognito-idp.us-east-1.amazonaws.com/us-east-1_123456678", currSession.getIdToken().getJWTToken());
        credentialsProvider.setLogins(logins);

        Log.d("Dipti","calling client");
        ApiClientFactory factory = new ApiClientFactory();
        factory.credentialsProvider(credentialsProvider);
// create a client
        client = factory.build(TestapiClient.class);

        client.apiOwnerGet();
        return null;
    }

    @Override
    protected void onPostExecute(Void aVoid) {
        super.onPostExecute(aVoid);
    }


    @Override
    protected void onCancelled()
    {
        super.onCancelled();
    }
    }

    private void getUserAuthentication(AuthenticationContinuation continuation, String username) {
        AuthenticationDetails authenticationDetails = new AuthenticationDetails(username, userPassword, null);

        continuation.setAuthenticationDetails(authenticationDetails);

        continuation.continueTask();
    }

    AuthenticationHandler authenticationHandler = new AuthenticationHandler() {

        @Override

        public void onSuccess(CognitoUserSession cognitoUserSession, CognitoDevice device) {

            Log.d("Dipti", " authenticationHandler -- Auth Success");

            currSession = cognitoUserSession;

            newDevice = device;
        }



        @Override

        public void getAuthenticationDetails(AuthenticationContinuation authenticationContinuation, String username) {
        Log.d("Dipti","getAuthenticationDetails");
            getUserAuthentication(authenticationContinuation, username);
        }



        @Override

        public void getMFACode(MultiFactorAuthenticationContinuation multiFactorAuthenticationContinuation) {
            Log.d("Dipti","getMFACode");

        }



        @Override

        public void onFailure(Exception e) {
            Log.d("Dipti","getMFACode");

        }



        @Override

        public void authenticationChallenge(ChallengeContinuation continuation) {
            Log.d("Dipti","authenticationChallenge");
        }
    };
}
