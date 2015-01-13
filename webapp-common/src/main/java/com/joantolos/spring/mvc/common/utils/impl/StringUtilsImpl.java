package com.joantolos.spring.mvc.common.utils.impl;

import com.joantolos.spring.mvc.common.enums.StringErrorCode;
import com.joantolos.spring.mvc.common.exception.StringManipulationException;
import com.joantolos.spring.mvc.common.utils.StringUtils;
import org.springframework.stereotype.Component;

import java.util.ArrayList;

@Component
public class StringUtilsImpl implements StringUtils {

    public String toCamelCase(String s, String delimiter){
        String[] parts = s.split(delimiter);
        String camelCaseString = "";
        for (String part : parts){
            camelCaseString = camelCaseString + toProperCase(part);
        }
        return firstLetterLowerCase(camelCaseString);
    }

    public String toProperCase(String s) {
        return s.substring(0, 1).toUpperCase() + s.substring(1).toLowerCase();
    }

    public String firstLetterLowerCase(String s){
        StringBuilder sb = new StringBuilder();
        sb.append(s.substring(0,1).toLowerCase());
        sb.append(s.substring(1));

        return sb.toString();
    }

    public ArrayList<String> stringArrayToArrayList(String[] array){
        ArrayList<String> arrayList = new ArrayList<>();
        for(String a : array){
            arrayList.add(a);
        }
        return arrayList;
    }

    public String replaceLastChars(String originalString, String replaceString, int endOffset) throws StringManipulationException {
        if (originalString.length() < endOffset) throw new StringManipulationException(StringErrorCode.STRING_LENGTH_OVERFLOW, String.valueOf(endOffset));
        return originalString.substring(0, originalString.length() - endOffset) + replaceString;
    }
}
