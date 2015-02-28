package com.codepath.apps.mysimpletweets;

import android.content.Context;

import com.codepath.oauth.OAuthBaseClient;
import com.loopj.android.http.AsyncHttpResponseHandler;
import com.loopj.android.http.RequestParams;

import org.scribe.builder.api.Api;
import org.scribe.builder.api.TwitterApi;

/*
 * 
 * This is the object responsible for communicating with a REST API. 
 * Specify the constants below to change the API being communicated with.
 * See a full list of supported API classes: 
 *   https://github.com/fernandezpablo85/scribe-java/tree/master/src/main/java/org/scribe/builder/api
 * Key and Secret are provided by the developer site for the given API i.e dev.twitter.com
 * Add methods for each relevant endpoint in the API.
 * 
 * NOTE: You may want to rename this object based on the service i.e TwitterClient or FlickrClient
 * 
 */
public class TwitterClient extends OAuthBaseClient {
    public static final Class<? extends Api> REST_API_CLASS = TwitterApi.class;
    public static final String REST_URL = "https://api.twitter.com/1.1";
    public static final String REST_CONSUMER_KEY = ApiKeys.apiKey;
    public static final String REST_CONSUMER_SECRET = ApiKeys.apiSecret;
    public static final String REST_CALLBACK_URL = "oauth://cpsimpletweets"; // Change this (here and in manifest)

    public TwitterClient(Context context) {
        super(context, REST_API_CLASS, REST_URL, REST_CONSUMER_KEY, REST_CONSUMER_SECRET, REST_CALLBACK_URL);
    }

    //METHOD == ENDPOINT

    public void getHomeTimeline(AsyncHttpResponseHandler handler) {
        String apiUrl = getApiUrl("statuses/home_timeline.json");
        //Specify the params
        RequestParams params = new RequestParams();
        params.put("count", 25);
        params.put("since_id", 1);
        //Execute the request
        getClient().get(apiUrl, params, handler);
    }

    public void getMoreTweets(String screenName, AsyncHttpResponseHandler handler, long max_id) {
        String apiUrl;
        RequestParams params = new RequestParams();

        if (screenName == "") { //Get own timeline
            apiUrl = getApiUrl("statuses/home_timeline.json");
        } else { //Get timeline of user.
            apiUrl = getApiUrl("statuses/user_timeline.json");
            params.put("screen_name", screenName);
        }

        params.put("count", 25);
        params.put("max_id", max_id);

        //Execute the request
        getClient().get(apiUrl, params, handler);
    }


    public void sendTweet(String body, AsyncHttpResponseHandler handler) {
        String apiUrl = getApiUrl("statuses/update.json");
        RequestParams params = new RequestParams();
        params.put("status", body);
        getClient().post(apiUrl, params, handler);

    }

    public void getMentionsTimeline(AsyncHttpResponseHandler handler) {
        String apiUrl = getApiUrl("statuses/mentions_timeline.json");
        //Specify the params
        RequestParams params = new RequestParams();
        params.put("count", 25);
        //Execute the request
        getClient().get(apiUrl, params, handler);
    }

    public void getMoreMentions(AsyncHttpResponseHandler handler, long max_id) {
        String apiUrl = getApiUrl("statuses/mentions_timeline.json");
        //Specify the params
        RequestParams params = new RequestParams();
        params.put("count", 25);
        params.put("max_id", max_id);
        //Execute the request
        getClient().get(apiUrl, params, handler);
    }

    public void getUserTimeline(String screenName, AsyncHttpResponseHandler handler) {
        String apiUrl = getApiUrl("statuses/user_timeline.json");
        //Specify the params
        RequestParams params = new RequestParams();
        params.put("count", 25);
        params.put("screen_name", screenName);
        //Execute the request
        getClient().get(apiUrl, params, handler);
    }

    public void getUserInfo(String screenName, AsyncHttpResponseHandler handler) {
        String apiUrl;
        if (screenName == "" || screenName == null) {
            apiUrl = getApiUrl("account/verify_credentials.json"); //Get the user's own info
        } else {
            apiUrl = getApiUrl("users/show.json"); //Get the info for the specified person.
        }
        RequestParams params = new RequestParams();
        params.put("screen_name", screenName);
        //Execute the request
        getClient().get(apiUrl, params, handler);
    }

    //COMPOSE TWEET

    //HomeTimeline - Gets us the timeline

	/* 1. Define the endpoint URL with getApiUrl and pass a relative path to the endpoint
     * 	  i.e getApiUrl("statuses/home_timeline.json");
	 * 2. Define the parameters to pass to the request (query or body)
	 *    i.e RequestParams params = new RequestParams("foo", "bar");
	 * 3. Define the request method and make a call to the client
	 *    i.e client.get(apiUrl, params, handler);
	 *    i.e client.post(apiUrl, params, handler);
	 */
}