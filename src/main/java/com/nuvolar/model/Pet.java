
package com.nuvolar.model;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;
@Builder
@NoArgsConstructor
@AllArgsConstructor
public @Data class Pet {
private int id;
private List<Category> category;
private String name;
private List<String> photoUrls;
private List<Tag> tags;
private String status;

    public static Pet fromJson(String json) {
        Gson gson = new GsonBuilder().create();
        return gson.fromJson(json, Pet.class);
    }

    public String toJSON() {
        Gson gson = new GsonBuilder().serializeNulls()
                .create();
        return gson.toJson(this);
    }
}
