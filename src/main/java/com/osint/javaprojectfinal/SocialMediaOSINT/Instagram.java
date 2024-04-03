package com.osint.javaprojectfinal.SocialMediaOSINT;

import com.google.gson.Gson;
import com.google.gson.JsonSyntaxException;
import kong.unirest.HttpResponse;
import kong.unirest.Unirest;

public class Instagram {

    private String instagramData;

    // Setter method for setting Instagram data
    public void setInstagramData(String instagramData) {
        this.instagramData = instagramData;
    }

    // Method to fetch Instagram data
    public String fetchInstagramData() {

        try {
            HttpResponse<String> response = Unirest.get("https://instagram-scraper-api2.p.rapidapi.com/v1/info")
                    .queryString("username_or_id_or_url", instagramData)
                    .header("X-RapidAPI-Key", "RAPID_API_KEY")
                    .header("X-RapidAPI-Host", "instagram-scraper-api2.p.rapidapi.com")
                    .asString();

            if (response.isSuccess()) {
                // Format the JSON response using Gson
                Gson gson = new Gson();
                String formattedJson = gson.toJson(gson.fromJson(response.getBody(), Object.class));
                return formattedJson;
            } else {
                return "Failed to retrieve data. Status: " + response.getStatus() + ", Error: " + response.getStatusText();
            }
        } catch (JsonSyntaxException e) {
            return "An error occurred: " + e.getMessage();
        }
    }
}
