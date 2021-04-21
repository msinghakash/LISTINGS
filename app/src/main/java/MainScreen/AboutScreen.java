package MainScreen;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.TextView;

import com.example.listings.R;

public class AboutScreen extends AppCompatActivity {

    // Inititallizing the variables.
    TextView aboutInfo;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_about_screen);

        aboutInfo = findViewById(R.id.about_info);

        //Below is the instructions that user will be able to see on the screen.
        String about = "\nFind products, Upload product photos, Connect with sellers\n\n" +
                "Keeping up with the products to buy and sell is now easier than ever." +
                "Click on the Choose Photo, Upload it into the market and find an easy wasy to sell you product and grow your business\n" +
                "\n" +
                "Features on the Listing app include:\n\n" +
                "\t-Scroll through products.\n" +
                "\t-Get to know the price and easily compare them.\n" +
                "\t-Get to know what products are available near you.\n" +
                "\t-Platform to buy and sell from comfort of your home.\n" +
                "\t-Get to look at the info you have saved while signing up.\n\n" +
                "You will get the updated and the most recent listings of products in real time.\n" +
                "This will save you from hassle of going through the items in your marketplace in the current pandemic situation and " +
                "will also make it easy available for you to make decision whether or not to buy the product.\n " +
                "\n\nListings will also help you to easy place the product into the marketplace from comfort of your home" +
                "without having the need to deal with the sellers and the without having the need to spend money on the " +
                "middleman to sell or transport your product. Thus, helping you to bring down your overhead costs" +
                "with the minimal and efficient technique. Great thing about this app is that it makes it easy to sell the products" +
                "because all the deals can be done over the phone or email that you provide without even having the need to " +
                "meet the buyer in person.\n" +
                "Listings just not provides you with comfort to buy and sell but it also opens up the market for you to" +
                "buy the products from and also gives you a place to present your product into the world." +
                "\n\nIn case of any issues contact akashsinghprojects@gmail.com"+
        "\n\n\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t-----THANK YOU-----\n\n\n";

        // Displaying the above instructions on theto the view.
        aboutInfo.setText(about);

    }
}