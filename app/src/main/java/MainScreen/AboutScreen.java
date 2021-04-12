package MainScreen;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.TextView;

import com.example.listings.R;

public class AboutScreen extends AppCompatActivity {

    TextView aboutInfo;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_about_screen);

        aboutInfo = findViewById(R.id.about_info);

        String about = "\nFind products, Upload product, photos, Contact with people\n" +
                "Keeping up with the products and their prices is now easier than ever." +
                "Click on the Choose Photo, Upload it into the market and find an easy wasy to sell you product and grow your business\n" +
                "\n" +
                "Features on the Listing app include:\n\n" +
                "*Scroll through products available in the market.\n" +
                "*Get to know the price and easily compare them with the prices of the other products.\n" +
                "*Get to know what products are available near you by looking at the location\n" +
                "*Place to buy and sell from comfort of your home.\n" +
                "*Get to look at the info you have saved while logging in to this app.\n\n" +
                "Lisitings helps you keep up with the products that are available in the market.\n" +
                "You will get the updated and the most recent listings of products in real time.\n" +
                "This will save you from hassle of going through the items in your marketplace in the current pandemic situation and " +
                "will also make it easy available for you to make decision whether or not to buy the product.\n " +
                "Listings will also help you to easy place the product into the marketplace from comfort of your home" +
                "without having to deal with the sellers and the without having the need to spend money on the " +
                "middleman to sell or transport your product. Thus, helping you to bring down your overhead costs" +
                "with the minimal and efficient technique. Great thing about this app is that it makes it easy to sell the products" +
                "because all the deals can be done over the phone or email that you provide without even havnig the need to " +
                "meet the buyer in person.\n" +
                "Listings just not provides you with comfort to buy and sell but it also opens up the market for you to" +
                "buy the products from and also gives you a place to present your product into the world.";
        aboutInfo.setText(about);

    }
}