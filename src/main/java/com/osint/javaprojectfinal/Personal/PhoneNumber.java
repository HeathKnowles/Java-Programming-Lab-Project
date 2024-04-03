package com.osint.javaprojectfinal.Personal;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import kong.unirest.HttpResponse;
import kong.unirest.Unirest;


public class PhoneNumber {
    private String phoneNumber;

    // Setter method for phoneNumber
    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    // Method to validate the phone number using the API and return the JSON response as a string
    public String validatePhoneNumber() {
        try {
            // Make the API call
            HttpResponse<String> response = Unirest.get("https://whatsapp-osint.p.rapidapi.com/wspic/dck?phone="+phoneNumber)
	.header("X-RapidAPI-Key", "RAPID_API_KEY")
	.header("X-RapidAPI-Host", "whatsapp-osint.p.rapidapi.com")
	.asString();

            // Process the response
            if (response.isSuccess()) {
                // Parse JSON response into a formatted string
                Gson gson = new GsonBuilder().setPrettyPrinting().create();
                String formattedJson = gson.toJson(response.getBody());
                return formattedJson;
            } else {
                // Return error message if request fails
                return "Failed to validate phone number. Status: " + response.getStatus() + ", Error: " + response.getStatusText();
            }
        } catch (Exception e) {
            // Handle exceptions and return error message
            return "An error occurred: " + e.getMessage();
        }
    }
}
