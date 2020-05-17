package ru.itis.reactorapi.parsers;

import lombok.SneakyThrows;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import org.springframework.stereotype.Component;
import ru.itis.reactorapi.entries.YandexRestaurantRecord;

import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@Component
public class JsonParser {

    @SneakyThrows
    public List<YandexRestaurantRecord> parse(String path) {
        JSONParser parser = new JSONParser();
        List<YandexRestaurantRecord> records = new ArrayList<>();
        Long id = 0L;
        try {
            JSONArray restaurantArray = (JSONArray) parser.parse(new FileReader(path));

            for (Object object : restaurantArray) {
                JSONObject restaurantRecord = (JSONObject) object;
                JSONObject properties = (JSONObject) restaurantRecord.get("properties");
                JSONObject metaData = (JSONObject) properties.get("CompanyMetaData");
                System.out.println(metaData);
                JSONArray phones = (JSONArray) metaData.get("Phones");
                System.out.println(phones);
                JSONObject phone = (JSONObject) phones.get(0);
                System.out.println(restaurantRecord);
                YandexRestaurantRecord record = YandexRestaurantRecord.builder()
                        .id(id++)
                        .name((String) properties.get("name"))
                        .description((String) properties.get("description"))
                        .phone((String) phone.get("formatted"))
                        .build();

                records.add(record);
            }
        } catch (IOException | ParseException e) {
            e.printStackTrace();
        }
        return records;
    }
}
