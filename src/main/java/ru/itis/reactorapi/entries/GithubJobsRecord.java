package ru.itis.reactorapi.entries;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

@Data
public class GithubJobsRecord {

    @JsonProperty("id")
    private String id;

    @JsonProperty("title")
    private String title;

}