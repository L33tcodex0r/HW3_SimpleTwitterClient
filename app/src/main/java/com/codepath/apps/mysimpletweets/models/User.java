package com.codepath.apps.mysimpletweets.models;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.Serializable;

public class User implements Serializable {
    //list attributes
    private String name;
    private long uid;
    private String screenName;
    private String profileImageUrl;
    private String tagline;

    public int getFollowersCount() {
        return followersCount;
    }

    public int getFriendsCount() {
        return followingsCount;
    }

    private int followersCount;
    private int followingsCount;

    public String getName() {
        return name;
    }

    public String getTagline() {
        return tagline;
    }

    public long getUid() {
        return uid;
    }

    public String getScreenName() {
        return screenName;
    }

    public String getProfileImageUrl() {
        return profileImageUrl;
    }

    public static User fromJSON(JSONObject json) {
        User u = new User();
        //Extract and fill the values
        try {
            u.name = json.getString("name");
            u.uid = json.getLong("id");
            u.screenName = json.getString("screen_name");
            u.profileImageUrl = json.getString("profile_image_url");
            u.tagline = json.getString("description");
            u.followersCount = json.getInt("followers_count");
            u.followingsCount = json.getInt("friends_count");


        } catch (JSONException e) {
            e.printStackTrace();
        }


        //Return a user
        return u;
    }
}
