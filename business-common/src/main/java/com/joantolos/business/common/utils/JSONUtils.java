package com.joantolos.business.common.utils;

import java.lang.reflect.Type;
import java.util.ArrayList;

/**
 *
 * Created by jtolos on 13/01/2015.
 */
public interface JSONUtils {

    public String objectToJson(Object o);

    public Object jsonToObject(String json, Class c);

    public Object jsonToObject(String json, Type t);

    public ArrayList<Object> objectListJsonToObjectList(String objectListJSON);

    public String objectListToObjectListJson(ArrayList<Object> objectList);
}
