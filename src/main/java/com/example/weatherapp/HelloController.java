package com.example.weatherapp;

import javafx.fxml.FXML;
import javafx.scene.control.Label;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

import javafx.scene.control.TextField;
import org.json.*;

public class HelloController {
    @FXML
    private Label welcomeText;

    @FXML
    private TextField textField;

    @FXML
    protected void onHelloButtonClick() throws Exception {
        String cityName = textField.getText();
        List<City> list = init();
        String lat = "";
        String lon = "";
        for (City c:list) {
            if (c.getName().equals(cityName)){
                lat = c.getLat();
                lon = c.getLon();
                break;
            }
        }

        URL url = new URL ("https://api.openweathermap.org/data/2.5/weather?lat="+lat+"&lon="+lon+"&appid=8214b5994813fe7379c043e827584008");
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(url.openStream()));
        String str = bufferedReader.readLine();

        JSONObject jsonObject = new JSONObject(str);
        JSONObject jsonObject1 = (JSONObject) jsonObject.get("main");
        String temp = String.valueOf(jsonObject1.get("temp"));
        String feelTemp = String.valueOf(jsonObject1.get("feels_like"));
        String minTemp = String.valueOf(jsonObject1.get("temp_min"));
        String maxTemp = String.valueOf(jsonObject1.get("temp_max"));
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("Current temp is " +(Math.round(Double.valueOf(temp)-273)) +"\n");
        stringBuilder.append("Temp feels like " + (Math.round(Double.valueOf(feelTemp))-273) +"\n");
        stringBuilder.append("Min temp is " + (Math.round(Double.valueOf(minTemp)-273)) +"\n");
        stringBuilder.append("Max temp is " + (Math.round(Double.valueOf(maxTemp)-273)) +"\n");
        welcomeText.setText(stringBuilder.toString());
    }
    private List<City> init(){
        List<City> list = new ArrayList<>();
        list.add(new City("Minsk","53.893009","27.567444"));
        list.add(new City("Grodno","53.669353","23.813131"));
        list.add(new City("Brest","52.097622","23.734051"));
        list.add(new City("Chausy","53.801753","30.953397"));
        list.add(new City("Vitebsk","55.187222","30.205116"));
        list.add(new City("New York","43.000000","-75.000000"));
        list.add(new City("Kiev","50.431759","30.517023"));
        list.add(new City("Berlin","52.531677","13.381777"));
        list.add(new City("Pekin","40.568459","-89.643028"));
        list.add(new City("Paris","48.864716","2.349014"));
        return list;
    }
}