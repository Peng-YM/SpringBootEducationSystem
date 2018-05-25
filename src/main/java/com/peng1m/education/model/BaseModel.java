package com.peng1m.education.model;

import com.google.gson.Gson;

import java.io.Serializable;

public class BaseModel implements Serializable {
    @Override
    public String toString() {
        return new Gson().toJson(getClass());
    }
}
