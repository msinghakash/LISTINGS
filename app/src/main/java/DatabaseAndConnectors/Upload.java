package DatabaseAndConnectors;

public class Upload {

    private String mName;
    private String mImageUrl;
    private String mImageDescription;

    public Upload() {
        //Empty Constructor needed because we are working with Firebase
    }

    //Below constructor will take the name and URL
    public Upload(String name, String imageurl, String imagedescription) {
        if (name.trim().equals("")) {
            name = "No Name";
        }
        if (imagedescription.trim().equals("")) {
            imagedescription = "No Description Provided";
        }
        mName = name;
        mImageUrl = imageurl;
        mImageDescription = imagedescription;
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
}
