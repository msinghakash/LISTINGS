package DatabaseAndConnectors;

import com.google.firebase.database.Exclude;

public class Upload {

    private String mName;
    private String mImageUrl;
    private String mImageDescription;
    private String mSellingInformationEmail;
    private String mImageLocation;
    private String mKey;


    public Upload() {
        //Empty Constructor needed because we are working with Firebase
    }

    //Below constructor will take the name and URL
    public Upload(String name, String imageurl, String imagedescription, String informationemail, String imagelocation) {
        if (name.trim().equals("")) {
            name = "Name not provided";
        }
        if (imagedescription.trim().equals("")) {
            imagedescription = "Description/Price Tag Not Provided";
        }
        if (informationemail.trim().equals("")) {
            informationemail = "Phone Number/Email Not Provided";
        }
        if (imagelocation.trim().equals("")){
            imagelocation = "Location Not Provided";
        }
        mName = name;
        mImageUrl = imageurl;
        mImageDescription = imagedescription;
        mSellingInformationEmail = informationemail;
        mImageLocation = imagelocation;
    }

    //Putting Getter and Setter
    public String getName() {
        return mName;
    }

    public void setName(String name) {
        mName = name;
    }

    public String getImageUrl() {
        return mImageUrl;
    }

    public void setImageUrl(String imageUrl) {
        mImageUrl = imageUrl;
    }

    public String getImageDescription() {
        return mImageDescription;
    }

    public void setImageDescription(String imageDescription) {
        mImageDescription = imageDescription;
    }
    public String getSellingInformationEmail() {
        return mSellingInformationEmail;
    }

    public void setSellingInformationEmail(String informationEmail) {
        mSellingInformationEmail = informationEmail;
    }
    public String getImageLocation() {
        return mImageLocation;
    }

    public void setImageLocation(String imageLocation) {
        mImageLocation = imageLocation;
    }
    @Exclude
    public String getKey(){
        return mKey;}
    @Exclude
    public void setKey(String key){
        mKey = key;
    }
}
