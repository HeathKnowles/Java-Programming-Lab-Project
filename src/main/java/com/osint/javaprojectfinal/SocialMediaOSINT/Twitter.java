package com.osint.javaprojectfinal.SocialMediaOSINT;

import com.google.gson.Gson;
import kong.unirest.HttpResponse;
import kong.unirest.Unirest;

public class Twitter {

    private String twitterData;
    private static final String RAPID_API_HOST = "twitter-api45.p.rapidapi.com";

    public Twitter() {
        // Constructor
    }

    public void fetchTwitterData() {
        try {
            String url = "https://twitter-api45.p.rapidapi.com/usermedia.php?screenname=" + this.twitterData;
            this.twitterData = makeRequest(url);
        } catch (Exception e) {
            this.twitterData = "An error occurred: " + e.getMessage();
        }
    }

    public String getTwitterData() {
        return this.twitterData;
    }

    public void setTwitterData(String twitterData) {
        this.twitterData = twitterData;
    }

    public String getUserMedia() {
        String url = "https://twitter-api45.p.rapidapi.com/usermedia.php?screenname=" + this.twitterData;
        return makeRequest(url);
    }

    public String getTimeline() {
        String url = "https://twitter-api45.p.rapidapi.com/timeline.php?screenname=" + this.twitterData;
        return makeRequest(url);
    }

    public String getUserLikes() {
        String url = "https://twitter-api45.p.rapidapi.com/userlikes.php?screenname=" + this.twitterData;
        return makeRequest(url);
    }

    public String getReplies() {
        String url = "https://twitter-api45.p.rapidapi.com/replies.php?screenname=" + this.twitterData;
        return makeRequest(url);
    }

    private String makeRequest(String url) {
        try {
            HttpResponse<String> response = Unirest.get(url)
                    .header("X-RapidAPI-Key", "RAPID_API_KEY")
                    .header("X-RapidAPI-Host", RAPID_API_HOST)
                    .asString();

            if (response.isSuccess()) {
                // Format the JSON response using Gson
                Gson gson = new Gson();
                Object parsedResponse = gson.fromJson(response.getBody(), Object.class);
                return gson.toJson(parsedResponse);
            } else {
                return "Failed to retrieve data. Status: " + response.getStatus() + ", Error: " + response.getStatusText();
            }
        } catch (Exception e) {
            return "An error occurred: " + e.getMessage();
        }
    }
}
