package com.smdeveloper.hdwallpaper.Model;

/**
 * Created by sathish kumar on 24-10-2017.
 */

public class FeedBackModel {
    private String Name;
    //private String Password;

    public FeedBackModel() {
    }

    public FeedBackModel(String name) {
        Name = name;
     //   Password = password;
    }

    public String getName() {
        return Name;
    }

    public void setName(String name) {
        Name = name;
    }

    //public String getPassword() {
      //  return Password;
    //}

    //public void setPassword(String password) {
      //  Password = password;
    //}
}
