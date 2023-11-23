package com.example.teacherspoint;

public class MyDataModel {

    private String imageUrl;
    private String name;
    private String address;
    private String contact;
    private String profileLink;

    public MyDataModel() {
        // Empty constructor needed for Firebase
    }

    public MyDataModel(String imageUrl, String name, String address, String contact, String profileLink) {
        this.imageUrl = imageUrl;
        this.name = name;
        this.address = address;
        this.contact = contact;
        this.profileLink = profileLink;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public String getName() {
        return name;
    }

    public String getAddress() {
        return address;
    }

    public String getContact() {
        return contact;
    }

    public String getProfileLink() {
        return profileLink;
    }
}

