package com.osint.javaprojectfinal.Personal;

import com.google.gson.Gson;
import kong.unirest.HttpResponse;
import kong.unirest.Unirest;

public class Email {

    private String emailData;

    public void setEmailData(String emailData) {
        this.emailData = emailData;
    }

    // Method to verify the email using the API and return the JSON response as a string
    public String verifyEmail() {
        try {
            // Make the API call
            String apiUrl = "https://validect-email-verification-v1.p.rapidapi.com/v1/verify?email="+emailData;
            HttpResponse<String> response = Unirest.get(apiUrl)
                    .header("X-RapidAPI-Key", "3a9e1fdf4amsh636a8f7d3d9e7f2p138cbejsnb9c4ab351096")
                    .header("X-RapidAPI-Host", "validect-email-verification-v1.p.rapidapi.com")
                    .asString();

            // Process the response
            if (response.isSuccess()) {
                // Format the JSON response using Gson
                Gson gson = new Gson();
                Object json = gson.fromJson(response.getBody(), Object.class);
                String formattedJson = gson.toJson(json);
                return formattedJson;
            } else {
                // Return error message if request fails
                return "Failed to verify email. Status: " + response.getStatus() + ", Error: " + response.getStatusText();
            }
        } catch (Exception e) {
            // Handle exceptions and return error message
            return "An error occurred: " + e.getMessage();
        }
    }
}
