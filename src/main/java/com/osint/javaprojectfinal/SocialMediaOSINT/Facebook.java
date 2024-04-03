package com.osint.javaprojectfinal.SocialMediaOSINT;

import com.google.gson.Gson;
import kong.unirest.HttpResponse;
import kong.unirest.Unirest;

public class Facebook {
    private String facebookData;

    // Method to set the Facebook data
    public void setFacebookData(String facebookData) {
        this.facebookData = facebookData;
    }

    // Method to scrape Facebook profile based on ID
    public String scrapeProfile() {
        try {
            // Make the API call
            HttpResponse<String> response = Unirest.post("https://facebook-profil-scraper.p.rapidapi.com/fba")
                .header("content-type", "application/x-www-form-urlencoded")
                .header("X-RapidAPI-Key", "RAPID_API_KEY")
                .header("X-RapidAPI-Host", "facebook-profil-scraper.p.rapidapi.com")
                .body("id=" + facebookData)
                .asString();

            // Process the response
            if (response.isSuccess()) {
                // Format JSON response using Gson
                Gson gson = new Gson();
                Object json = gson.fromJson(response.getBody(), Object.class);
                return gson.toJson(json);
            } else {
                // Return error message if request fails
                return "Failed to scrape Facebook profile. Status: " + response.getStatus() + ", Error: " + response.getStatusText();
            }
        } catch (Exception e) {
            // Return error message in case of exception
            return "An error occurred: " + e.getMessage();
        }
    }
}
