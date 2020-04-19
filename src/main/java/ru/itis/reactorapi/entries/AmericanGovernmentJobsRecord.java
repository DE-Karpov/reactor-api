package ru.itis.reactorapi.entries;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

@Data
public class AmericanGovernmentJobsRecord {

    @JsonProperty("uuid")
    private String uuid;

    @JsonProperty("title")
    private String title;

}