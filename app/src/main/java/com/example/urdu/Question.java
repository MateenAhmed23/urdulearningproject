package com.example.urdu;

public class Question {
    private String Image;
    private String Name;
    private String Urdu;

    public Question(String image, String name, String urdu) {
        Image = image;
        Name = name;
        Urdu = urdu;
    }

    public Question() {
    }

    public String getImage() {
        return Image;
    }

    public String getName() {
        return Name;
    }

    public String getUrdu() {
        return Urdu;
    }

    public void setImage(String image) {
        Image = image;
    }

    public void setName(String name) {
        Name = name;
    }

    public void setUrdu(String urdu) {
        Urdu = urdu;
    }
}
