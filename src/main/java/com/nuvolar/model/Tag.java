package com.nuvolar.model;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Builder
@NoArgsConstructor
@AllArgsConstructor
public @Data class Tag {

        private Integer id;
        private String name;

    public static Tag fromJson(String json) {
        Gson gson = new GsonBuilder().create();
        return gson.fromJson(json, Tag.class);
    }

    public String toJSON() {
        Gson gson = new GsonBuilder().serializeNulls()
                .create();
        return gson.toJson(this);
    }
}
