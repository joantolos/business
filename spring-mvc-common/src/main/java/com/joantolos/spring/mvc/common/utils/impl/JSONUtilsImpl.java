package com.joantolos.spring.mvc.common.utils.impl;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.joantolos.spring.mvc.common.utils.JSONUtils;
import org.springframework.stereotype.Component;

import java.lang.reflect.Type;
import java.util.ArrayList;

@Component("jsonUtils")
public class JSONUtilsImpl implements JSONUtils {

    public String objectToJson(Object o){
        return new Gson().toJson( o );
    }

    public Object jsonToObject(String json, Class c){
        return new Gson().fromJson( json, c );
    }

    public Object jsonToObject(String json, Type t){
        return new Gson().fromJson( json, t );
    }

    public ArrayList<Object> objectListJsonToObjectList(String objectListJSON){
        return new Gson().fromJson(objectListJSON, new TypeToken<ArrayList<Object>>() { }.getType());
    }

    public String objectListToObjectListJson(ArrayList<Object> objectList){
        return new Gson().toJson(objectList, new TypeToken<ArrayList<Object>>() { }.getType());
    }
}
