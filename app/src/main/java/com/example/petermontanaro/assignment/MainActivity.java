package com.example.petermontanaro.assignment;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import Database.Database;

public class MainActivity extends AppCompatActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // When the app runs I stuffed up the opening screen and have built too far in to actually
        // fix this up, the mainMenu is what is meant to load when the app launches, but instead it goes
        // into a random screen with a back ground. So what needs to be done here is:
        /* First off, Have a splash Screen, like "Welcome to MAD Grill, please enter your table number:" etc...
        this should be the launching intent, then create an intent and pass that tablenumber into the main menu somehow.
        This number will be used later on , i'll have to refactor the code to allow an order to accept a tablenumber.
         */

        // intent to launch menu, should be changed to welcome screen.
        Intent menuScreen = new Intent(this, mainMenu.class);
        startActivity(menuScreen);

    }
}
