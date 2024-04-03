package com.osint.javaprojectfinal;

import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.HBox;
import javafx.scene.text.Font;
import com.osint.javaprojectfinal.SocialMediaOSINT.*;
import com.osint.javaprojectfinal.Personal.*;
import javafx.scene.control.Button; 
import com.osint.javaprojectfinal.Dashboard;
import java.util.List;

public class InformationFinder extends HBox {
    private TextField PhoneNumber;

    public InformationFinder() {
        AnchorPane anchorPane1 = new AnchorPane();
        anchorPane1.setPrefHeight(400.0);
        anchorPane1.setPrefWidth(280.0);
        anchorPane1.setStyle("-fx-background-color: #F4B142;");

        ImageView imageView = new ImageView();
        imageView.setFitHeight(145.0);
        imageView.setFitWidth(254.0);
        imageView.setLayoutX(86.0);
        imageView.setLayoutY(55.0);
        imageView.setPickOnBounds(true);
        imageView.setPreserveRatio(true);
        Image image = new Image(getClass().getResourceAsStream("/Designer.png"));
        imageView.setImage(image);

        Label label1 = new Label();
        label1.setAlignment(javafx.geometry.Pos.CENTER);
        label1.setContentDisplay(javafx.scene.control.ContentDisplay.CENTER);
        label1.setLayoutX(70.0);
        label1.setLayoutY(216.0);
        label1.setPrefHeight(20.0);
        label1.setPrefWidth(191.0);
        label1.setText("INFORMATION FINDER");
        label1.setTextAlignment(javafx.scene.text.TextAlignment.CENTER);
        label1.setFont(new Font("Algerian", 18.0));

        Label label2 = new Label();
        label2.setAlignment(javafx.geometry.Pos.CENTER);
        label2.setContentDisplay(javafx.scene.control.ContentDisplay.CENTER);
        label2.setLayoutX(-1.0);
        label2.setLayoutY(243.0);
        label2.setPrefHeight(17.0);
        label2.setPrefWidth(350.0);
        label2.setText("Unlock the Unknown: From Familiar to Extraordinary");
        label2.setTextAlignment(javafx.scene.text.TextAlignment.CENTER);
        label2.setFont(new Font("Comic Sans MS", 12.0));

        anchorPane1.getChildren().addAll(imageView, label1, label2);

        AnchorPane anchorPane2 = new AnchorPane();
        anchorPane2.setPrefHeight(400.0);
        anchorPane2.setPrefWidth(266.0);

        Label label3 = new Label();
        label3.setLayoutX(14.0);
        label3.setLayoutY(21.0);
        label3.setPrefHeight(18.0);
        label3.setPrefWidth(233.0);
        label3.setText("Enter Required Details:");
        label3.setFont(new Font("Algerian", 18.0));

        Label label4 = new Label();
        label4.setLayoutX(24.0);
        label4.setLayoutY(66.0);
        label4.setText("Name:");
        label4.setFont(new Font("Algerian", 14.0));

        TextField name = new TextField();
        name.setLayoutX(77.0);
        name.setLayoutY(61.0);

        Label label5 = new Label();
        label5.setLayoutX(24.0);
        label5.setLayoutY(94.0);
        label5.setText("Ph no:");
        label5.setFont(new Font("Algerian", 14.0));
        
        PhoneNumber = new TextField();
        PhoneNumber.setLayoutX(77.0);
        PhoneNumber.setLayoutY(89.0);

        Label label6 = new Label();
        label6.setLayoutX(24.0);
        label6.setLayoutY(121.0);
        label6.setText("Email:");
        label6.setFont(new Font("Algerian", 14.0));
        

        TextField email = new TextField();
        email.setLayoutX(77.0);
        email.setLayoutY(116.0);

        Label label7 = new Label();
        label7.setLayoutX(14.0);
        label7.setLayoutY(160.0);
        label7.setPrefHeight(18.0);
        label7.setPrefWidth(233.0);
        label7.setText("Enter Social Media:");
        label7.setFont(new Font("Algerian", 18.0));

        Label label8 = new Label();
        label8.setLayoutX(24.0);
        label8.setLayoutY(200.0);
        label8.setText("Insta:");
        label8.setFont(new Font("Algerian", 14.0));

        Label label9 = new Label();
        label9.setLayoutX(16.0);
        label9.setLayoutY(267.0);
        label9.setText("Facebook:");
        label9.setFont(new Font("Algerian", 14.0));

        TextField instagram = new TextField();
        instagram.setLayoutX(77.0);
        instagram.setLayoutY(195.0);

        TextField facebook = new TextField();
        facebook.setLayoutX(77.0);
        facebook.setLayoutY(222.0);

        TextField twitter = new TextField();
        twitter.setLayoutX(77.0);
        twitter.setLayoutY(254.0);

        Label label10 = new Label();
        label10.setLayoutX(14.0);
        label10.setLayoutY(239.0);
        label10.setText("Twitter:");
        label10.setFont(new Font("Algerian", 14.0));
        
        Button searchButton = new Button("Search");
        searchButton.setLayoutX(120); // Adjust the layout position as needed
        searchButton.setLayoutY(300);
        
        searchButton.setOnAction(e -> {
            DataToOtherClasses(name.getText(), PhoneNumber.getText(), email.getText(), instagram.getText(), facebook.getText(), twitter.getText());
            Dashboard dashboard = new Dashboard();

            // Collect JSON responses from various classes
            List<String> jsonResponses = dashboard.collectJSONResponses();


            // Write JSON responses to a text file
            dashboard.writeJSONToFile(jsonResponses, "json_responses.txt");
            App.closeWindow();
            
        });

        anchorPane2.getChildren().addAll(label3, label4, name, label5, label6, PhoneNumber, email, label7, label8, label9, instagram, facebook, twitter, label10, searchButton);

        this.getChildren().addAll(anchorPane1, anchorPane2);
    }

    public void DataToOtherClasses(String name, String phoneNumber, String email, String instagram, String facebook, String twitter) {
        PhoneNumber phoneInstance = new PhoneNumber();
        phoneInstance.setPhoneNumber(phoneNumber);

        Email emailInstance = new Email();
        emailInstance.setEmailData(email);

        Facebook facebookInstance = new Facebook();
        facebookInstance.setFacebookData(facebook);

        Instagram instagramInstance = new Instagram();
        instagramInstance.setInstagramData(instagram);

        Twitter twitterInstance = new Twitter();
        twitterInstance.setTwitterData(twitter);
    }
}