package com.se2.ebankingproject.Helper;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Component;

@Component
public class CheckProperty {
    public Boolean objectHasProperty(Object obj, String propertyName){
        List<Field> properties = getAllFields(obj);
        for(Field field : properties){
            if(field.getName().equalsIgnoreCase(propertyName)){
                return true;
            }
        }
        return false;
    }

    public  List<Field> getAllFields(Object obj){
        List<Field> fields = new ArrayList<Field>();
        getAllFieldsRecursive(fields, obj.getClass());
        return fields;
    }

    public  List<Field> getAllFieldsRecursive(List<Field> fields, Class<?> type) {
        for (Field field: type.getDeclaredFields()) {
            fields.add(field);
        }
        if (type.getSuperclass() != null) {

        }
        return fields;
    }
}
