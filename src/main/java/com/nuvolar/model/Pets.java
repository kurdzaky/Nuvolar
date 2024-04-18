package com.nuvolar.model;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.fasterxml.jackson.annotation.JsonFormat;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Builder
@NoArgsConstructor
@AllArgsConstructor
@Data
@JsonFormat(shape = JsonFormat.Shape.ARRAY)
public class Pets {
    public Pet[] pets;

    public Pet[] getPet() {
        return pets;
    }

    public static Pets[] fromJson(String json) {
        Gson gson = new GsonBuilder().create();
        return gson.fromJson(json, Pets[].class);
    }

    public String toJSON() {
        Gson gson = new GsonBuilder().serializeNulls()
                .create();
        return gson.toJson(this);
    }
}