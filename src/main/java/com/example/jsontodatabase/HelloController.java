package com.example.jsontodatabase;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import org.json.simple.JSONObject;
import org.json.simple.JSONArray;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;



public class HelloController {

    Stage stage;
    @FXML
    private Label welcomeText;
    Connection connection = null;
    @FXML
    protected void onHelloButtonClick() {
        welcomeText.setText("Welcome to JavaFX Application!");
    }
    @FXML
    protected void downloadFile() throws IOException, ParseException, ClassNotFoundException, NoSuchMethodException, InvocationTargetException, InstantiationException, IllegalAccessException, SQLException {
        FileChooser fileChooser = new FileChooser();
        fileChooser.setInitialDirectory(new File("C:\\Users\\Nikita\\IdeaProjects\\JsonToDatabase\\target"));
        File fileText = fileChooser.showOpenDialog(stage);
        FileReader fr = new FileReader( fileText );

        JSONParser parser = new JSONParser();
        JSONArray parseFile = (JSONArray) parser.parse(fr);
        try {
            Class.forName("com.mysql.cj.jdbc.Driver").getDeclaredConstructor().newInstance();
            connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/desk", "root", "");
            System.out.println("Connection succesfull!");
        }
        catch(Exception ex){
            System.out.println("Connection failed...");
            System.out.println(ex);
        }
        List<Request> requestsList = new ArrayList<>();
        for (Object it:parseFile){
            JSONObject request = (JSONObject) it;
            String name =(String)request.get("name");
            String desc =(String)request.get("description");
            Request myRequest = new Request(1, name, desc);
            requestsList.add(myRequest);
            System.out.println("name: " + name + "\n desc: " + desc + "\n");
            String SQL = "INSERT INTO request (name,description) VALUES ('" + name + "','" + desc + "')";
            System.out.println(SQL);
            Statement stmt = connection.createStatement();
            stmt.executeUpdate(SQL);

        }

        fr.close();
    }
}