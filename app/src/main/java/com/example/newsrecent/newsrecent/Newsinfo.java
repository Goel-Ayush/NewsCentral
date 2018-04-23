package com.example.newsrecent.newsrecent;

import java.net.URI;

public class Newsinfo {

    String nName;
    String nAuthorName;
    String nTitle;
    String nDescription;
   // URI urltoimage;

    public Newsinfo (String name, String aName, String title, String Description ){
        nName = name;
        nAuthorName = aName;
        nTitle = title;
        nDescription = Description;
    }

    public Newsinfo(){
        nName = "0";
        nAuthorName = "0";
        nTitle = "0";
        nDescription = "0";
    }
    public String getnAuthorName() {
        return nAuthorName;
    }

    public String getnDescription() {
        return nDescription;
    }

    public String getnName() {
        return nName;
    }

    public String getnTitle() {
        return nTitle;
    }
}
