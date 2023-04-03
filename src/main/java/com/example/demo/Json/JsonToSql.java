package com.example.demo.Json;
import java.io.FileReader;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import org.apache.kafka.clients.producer.KafkaProducer;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;

public class JsonToSql {

    public static void main(String[] args) {

        // Specify the path to the JSON file
        String pathToJsonFile = "D:\\Json\\bets.txt";

        // Configure the MySQL connection
        String url = "jdbc:mysql://localhost:3306/Betting_Data";
        String user = "root";
        String password = "test";

        try (Connection connection = DriverManager.getConnection(url, user, password)) {

            // Read the JSON file using the JSON parser
            JSONParser parser = new JSONParser();
            Object obj = parser.parse(new FileReader(pathToJsonFile));
            JSONArray jsonArray = (JSONArray) obj;

            // Iterate over each JSON object in the array and insert it into the database
            for (Object json : jsonArray) {
                JSONObject jsonObject = (JSONObject) json;
               int Id = (int) jsonObject.get("Id");
               String numbets = (String) jsonObject.get("numbets");
                String game = (String) jsonObject.get("game");
                String stake = (String) jsonObject.get("stake");
                String returnAmount = (String) jsonObject.get("returns");
                String clientId = (String) jsonObject.get("clientid");
                String date = (String) jsonObject.get("date_coloumn");



                String query = "INSERT INTO bets (Id,numbets,game,stake,returns, client_id, date) "
                             + "VALUES (?, ?, ?, ?, ?)";

                PreparedStatement statement = connection.prepareStatement(query);
                statement.setInt(1, Id);
                statement.setString(2, numbets);
                statement.setString(1, game);
                statement.setString(1, stake);
                statement.setString(1, returnAmount);
                statement.setString(2, clientId);
                statement.setString(3, date);
                statement.executeUpdate();

                // Produce Kafka message after successful insert
              //  KafkaProducer.sendMessage("bet_detail", jsonObject.toJSONString());

            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
