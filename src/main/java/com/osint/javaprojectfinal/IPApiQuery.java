package com.osint.javaprojectfinal;

import com.google.gson.Gson;
import kong.unirest.HttpResponse;
import kong.unirest.Unirest;

import java.net.InetAddress;
import java.net.UnknownHostException;

public class IPApiQuery {
    
    private static final String RAPID_API_HOST = "ip-iq.p.rapidapi.com";

    public static String fetchFormattedJson() {
        try {
            // Get the local IP address
            InetAddress localhost = InetAddress.getLocalHost();
            String ipAddress = localhost.getHostAddress();

            // Send the IP address to the API using Unirest
            HttpResponse<String> response = Unirest.get("https://ip-iq.p.rapidapi.com/ip?ip=" + ipAddress)
                    .header("X-RapidAPI-Key", "3a9e1fdf4amsh636a8f7d3d9e7f2p138cbejsnb9c4ab351096")
                    .header("X-RapidAPI-Host", RAPID_API_HOST)
                    .asString();

            // Process the response
            if (response.isSuccess()) {
                // Get the JSON response as a string
                String jsonResponse = response.getBody();
                
                // Return formatted JSON response
                return formatJson(jsonResponse);
            } else {
                // Return error message if request fails
                return "Failed to fetch IP information. Status: " + response.getStatus() + ", Error: " + response.getStatusText();
            }
        } catch (UnknownHostException e) {
            // Return error message in case of UnknownHostException
            return "An error occurred: " + e.getMessage();
        }
    }

    // Method to format JSON string using Gson
    private static String formatJson(String jsonString) {
        Gson gson = new Gson();
        Object json = gson.fromJson(jsonString, Object.class);
        return gson.toJson(json);
    }
}