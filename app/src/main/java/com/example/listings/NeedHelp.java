package com.example.listings;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

public class NeedHelp extends AppCompatActivity {

    TextView needHelp;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_need_help);

        needHelp = findViewById(R.id.need_help);

        String how_to_operate = "\nBelow are the instructions to operate the the Listings application:\n\n" +
                "- To Sign Up for the application, user need to click on either SIGN UP FOR LISTINGS\n\n" +
                "- The user can also click on the SIGN UP to create an account for LISTINGS android application\n\n"+
                "- For SIGN UP the user need to enter/provide the following information:\n\n" +
                "\t\t-Full Name\n" +
                "\t\t-Phone Number\n" +
                "\t\t-Email ID\n" +
                "\t\t-Username\n" +
                "\t\t-Password\n" +
                "\t\t-Gender\n" +
                "\t\t-Date of Birth\n\n" +
                "- For LOGGING into the application the user has to select the Country and enter the Phone Number\n" +
                "- The user also needs to enter the Password to use the application\n\n" +
                "\n-----HOW TO BUY THE PRODUCTS-----\n\n" +
                "- Log into the application." +
                "- The application's main page shows all the listings that are uploaded by the sellers.\n" +
                "- The buy the product from the listings the user can contact the seller by calling or emailing" +
                " them on the information that they have provided." +
                "The price of the product is also shown to the user underneath the product image.\n\n" +
                "\n-----HOW TO SELL THE PRODUCTS-----\n\n" +
                "- Click on the three bar button on the top left side of the screen" +
                " or Slide from left to right to open the drawer option.\n" +
                "- Choose the Sell options from the menus provided.\n" +
                "- Select the button to upload the image of the product that is to be sold.\n" +
                "- Enter the product name.\n" +
                "- Enter the contact information.\n" +
                "- Enter the description of the product with the price of the product.\n" +
                "- Enter the location where the product is being sold\n" +
                "- Click on the UPLOAD button to show the product in the listings\n\n" +
                "\n-----HOW TO SEE THE USER'S INFORMATION-----\n\n" +
                "To check the information regarding themselves the user has to follow the setps as mentioned below:\n\n" +
                "- Log in into the application using your information.\n" +
                "- Click on the three bar button on the top left side of the screen" +
                " or Slide from left to right to open the drawer option.\n" +
                "- Choose the User option from the menus provided." +
                "- The screen will show the information regarding the user\n\n" +
                "\n-----HOW TO KNOW ABOUT THE APPLICATION-----\n\n" +
                "To know about the application, follow the steps below:\n" +
                "- Click on the three bar button on the top left side of the screen" +
                " or Slide from left to right to open the drawer option.\n" +
                "- Choose the About option from the menus provided.\n" +
                "- Read about the application\n\n" +
                "\n-----HOW TO LOGOUT OF THE APPLICATION-----\n\n" +
                "- Click on the three bar button on the top left side of the screen" +
                " or Slide from left to right to open the drawer option.\n" +
                "- Choose the LOGOUT button to exit from the application." +
                "\n\n\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t-----THANK YOU-----\n\n";

        needHelp.setText(how_to_operate);

    }

}