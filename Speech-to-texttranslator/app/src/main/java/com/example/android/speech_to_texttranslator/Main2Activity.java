package com.example.android.speech_to_texttranslator;

import android.app.ActionBar;
import android.app.Activity;
import android.content.ActivityNotFoundException;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.speech.RecognizerIntent;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.Switch;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

public class Main2Activity extends Activity {
    int contact_request = 10;  //This integer is a "request code" that identifies your request. Can be any number
    TextView textbox;       //Creating an instance of Textview. However, we are not initializing it to anything atleast for now
    ImageView imgButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);


        setContentView(R.layout.activity_main2);



    }

    public void speechListener(View view) {
        textbox = (TextView) findViewById(R.id.textbox);         //We are now initialzing it.
        imgButton = (ImageView) findViewById(R.id.mic);         //We are now initialzing it.

        Intent intent = new Intent(RecognizerIntent.ACTION_RECOGNIZE_SPEECH);   //Intent to handle speech recognition
        intent.putExtra(RecognizerIntent.EXTRA_LANGUAGE_MODEL, "en-US");         //Supports only US english
        try         //Using try block to catch exception if startActivity returns any any exception
        {
            startActivityForResult(intent, contact_request);  //Start the "speech-to-text" activity and receives
            //the result back in reference to "contact_request"
        } catch (ActivityNotFoundException E)      //catches the exception if the activity is not found

        {
            Toast.makeText(getApplicationContext(), "Your device doesn't support this feature", Toast.LENGTH_SHORT);


        }
    }

    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == contact_request)  //if the returned id is same as the contact_request

        {

            if (resultCode == RESULT_OK && data != null) {   //make sure the request was successful.

                ArrayList<String> result = data.getStringArrayListExtra(RecognizerIntent.EXTRA_RESULTS);  //stores
                //the extended data in the array form based on the confidence level in descending order.

                textbox.setText(result.get(0));     //returns the string with the highest confidence level.
            }

        }

    }



    //	An alert dialog pops up if a user accidentally clicks on the back button.
    @Override
    public void onBackPressed() {
        new AlertDialog.Builder(this).setIcon(android.R.drawable.ic_dialog_alert).setTitle("Quit Application")
                .setMessage("Are you sure you want to exit?")
                .setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        finish();
                        System.exit(0);
                    }
                }).setNegativeButton("No", null).show();
    }

}






