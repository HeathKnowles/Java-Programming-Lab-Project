package com.osint.javaprojectfinal;

import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
    import java.util.List;

import com.osint.javaprojectfinal.Personal.*;
import com.osint.javaprojectfinal.SocialMediaOSINT.*;

public class Dashboard {

    public static void main(String[] args) {
        // Collect JSON responses from various classes
        List<String> jsonResponses = collectJSONResponses();

        // Generate HTML content based on the collected JSON response
        
        // Write JSON responses to a text file
        writeJSONToFile(jsonResponses, "json_responses.txt");
    }

    public static List<String> collectJSONResponses() {
        // Collect JSON responses from various classes and return them as a list
        List<String> jsonResponses = new ArrayList<>();
        
        IPApiQuery ipq = new IPApiQuery();
        String query = ipq.fetchFormattedJson();
        jsonResponses.add(query);
        

        // Example: Fetch Instagram data
        Instagram instagram = new Instagram();
        String iRes = instagram.fetchInstagramData();
        jsonResponses.add(iRes);

        Email email = new Email();
        String emailData = email.verifyEmail();
        jsonResponses.add(emailData);

        PhoneNumber phno = new PhoneNumber();
        String phdata = phno.validatePhoneNumber();
        jsonResponses.add(phdata);

        Facebook fb = new Facebook();
        String fbk = fb.scrapeProfile();
        jsonResponses.add(fbk);

        Twitter tx = new Twitter();
        String ttr = tx.getReplies();
        jsonResponses.add(ttr);

        // Add more JSON responses as needed
        return jsonResponses;
    }


    public static void writeJSONToFile(List<String> jsonResponses, String fileName) {
        try (FileWriter writer = new FileWriter(fileName)) {
            for (String jsonResponse : jsonResponses) {
                writer.write(jsonResponse);
                writer.write(System.lineSeparator()); // Add a new line after each JSON response
            }
            System.out.println("JSON responses written to " + fileName);
        } catch (IOException e) {
            System.err.println("Error writing JSON responses to file: " + e.getMessage());
        }
    }
}