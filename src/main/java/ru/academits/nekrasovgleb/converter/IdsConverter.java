package ru.academits.nekrasovgleb.converter;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.io.UnsupportedEncodingException;

public class IdsConverter {

    private Gson gson = new GsonBuilder().create();

    public int[] convertFromJson(String idsParams) throws UnsupportedEncodingException {
        return gson.fromJson(idsParams, int[].class);
    }
}