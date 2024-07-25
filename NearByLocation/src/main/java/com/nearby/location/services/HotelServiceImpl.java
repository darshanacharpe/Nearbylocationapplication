package com.nearby.location.services;

import java.util.ArrayList;
import java.util.List;

import org.json.JSONArray;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

//import com.nearby.location.constant.AppConstants;
import com.nearby.location.entity.Hotel;

@Service
public class HotelServiceImpl {

    @Autowired
    private RestTemplate restTemplate;

    @Autowired
    private KafkaTemplate<String, String> kafkaTemplate;

    private static final String OVERPASS_URL = "https://overpass-api.de/api/interpreter";
    private static final String NOMINATIM_URL = "https://nominatim.openstreetmap.org/reverse";

    public List<Hotel> findNearbyHotels(double latitude, double longitude) {
        List<Hotel> hotels = new ArrayList<>();
        String query = String.format("[out:json];node[\"tourism\"=\"hotel\"](around:5000,%.6f,%.6f);out;", latitude, longitude);
        String response = restTemplate.postForObject(OVERPASS_URL, query, String.class);
        if (response != null) {
            JSONObject jsonResponse = new JSONObject(response);
            JSONArray elements = jsonResponse.getJSONArray("elements");

            for (int i = 0; i < elements.length(); i++) {
                JSONObject element = elements.getJSONObject(i);
                double lat = element.getDouble("lat");
                double lon = element.getDouble("lon");

                String name = element.has("tags") && element.getJSONObject("tags").has("name")
                        ? element.getJSONObject("tags").getString("name")
                        : "Unknown Hotel";

                String[] addressDetails = getDetailedAddress(lat, lon);
                String address = addressDetails[0];
                String city = addressDetails[1];
                String state = addressDetails[2];

                hotels.add(new Hotel(name, address, city, state));
                
              //this.kafkaTemplate.send(AppConstants.LOCATION_TOPIC_NAME, latitudenew, longitudenew  );
//				// this.kafkaTemplate.send

              //  sendLocationToKafka(AppConstants.LOCATION_TOPIC_NAME, lat, lon);
            }
        }
        return hotels;
    }

    private String[] getDetailedAddress(double lat, double lon) {
        String url = String.format("%s?format=json&lat=%.6f&lon=%.6f", NOMINATIM_URL, lat, lon);
        String response = restTemplate.getForObject(url, String.class);
        if (response != null) {
            JSONObject jsonResponse = new JSONObject(response);
            String road = jsonResponse.has("address") && jsonResponse.getJSONObject("address").has("road")
                    ? jsonResponse.getJSONObject("address").getString("road")
                    : "";
            String city = jsonResponse.has("address") && jsonResponse.getJSONObject("address").has("city")
                    ? jsonResponse.getJSONObject("address").getString("city")
                    : "";
            String state = jsonResponse.has("address") && jsonResponse.getJSONObject("address").has("state")
                    ? jsonResponse.getJSONObject("address").getString("state")
                    : "";
            String postcode = jsonResponse.has("address") && jsonResponse.getJSONObject("address").has("postcode")
                    ? jsonResponse.getJSONObject("address").getString("postcode")
                    : "";

            String address = String.format("%s, %s, %s, %s", road, city, state, postcode);
            return new String[] { address, city, state };
        }
        return new String[] { "Unknown Address", "Unknown City", "Unknown State" };
    }

//    private void sendLocationToKafka(String topic, double latitude, double longitude) {
//        String locationMessage = String.format("{\"latitude\": \"%.6f\", \"longitude\": \"%.6f\"}", latitude, longitude);
//        kafkaTemplate.send(topic, locationMessage);
//    }
}
