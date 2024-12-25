package com.zibada.zibadaCore.utils.parsing;

import org.bukkit.inventory.ItemStack;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.lang.reflect.Constructor;

public class YmlObjectParser {
    public static Object parse(Map<String, Object> data) throws Exception {
        String className = (String) data.get("Parent");
        if (className == null) {
            throw new IllegalArgumentException("Parent field is required to specify the object's class.");
        }

        Class<?> clazz = Class.forName(className);
        data.remove("Parent");

        for (Constructor<?> constructor : clazz.getDeclaredConstructors()) {
            Class<?>[] parameterTypes = constructor.getParameterTypes();
            if (parameterTypes.length == data.size()) {
                Object[] args = new Object[parameterTypes.length];
                String[] fieldNames = data.keySet().toArray(new String[0]);

                for (int i = 0; i < parameterTypes.length; i++) {
                    String fieldName = fieldNames[i];
                    Object fieldValue = data.get(fieldName);

                    if (fieldValue != null && !parameterTypes[i].isAssignableFrom(fieldValue.getClass())) {
                        throw new IllegalArgumentException("Type mismatch for field: " + fieldName);
                    }

                    args[i] = fieldValue;
                }

                return constructor.newInstance(args);
            }

        }
        throw new IllegalArgumentException("No suitable constructor was found for class: " + className);
    }


    public static Object parseWithIS(Map<String, Object> data) throws Exception {
        String className = (String) data.get("Parent");
        if (className == null) {
            throw new IllegalArgumentException("Parent field is required to specify the object's class.");
        }
        Class<?> clazz = Class.forName(className);
        System.out.println(clazz.getName());
        data.remove("Parent");

        for (Constructor<?> constructor : clazz.getDeclaredConstructors()) {
            Class<?>[] parameterTypes = constructor.getParameterTypes();
            if (parameterTypes.length == data.size()) {
                Object[] args = new Object[parameterTypes.length];
                String[] fieldNames = data.keySet().toArray(new String[0]);

                for (int i = 0; i < parameterTypes.length; i++) {
                    String fieldName = fieldNames[i];
                    Object fieldValue = data.get(fieldName);

                    if (parameterTypes[i].equals(ItemStack.class) && fieldValue instanceof Map) {
                        Map<String, Object> itemMap = (Map<String, Object>) fieldValue;
                        args[i] = ItemStackParser.parseItemStack(itemMap);

                    } else if (fieldValue != null && !parameterTypes[i].isAssignableFrom(fieldValue.getClass())) {
                        throw new IllegalArgumentException("Type mismatch for field: " + fieldName);
                    } else {

                        args[i] = fieldValue;
                    }
                }

                return constructor.newInstance(args);
            }

        }
        throw new IllegalArgumentException("No suitable constructor was found for class: " + className);
    }

    public static <T> List<T> parseList(List<Map<String,Object>> data,Class<T> clazz) throws Exception {
        List<T> list = new ArrayList<>();
        for(Map<String,Object> object : data){
            try {
                T parsedObject = clazz.cast(parse(object));
                list.add(parsedObject);
            } catch (Exception e){

            }
        }

        return list;
    }

    public static <T> List<T> parseListWithIS(List<Map<String,Object>> data,Class<T> clazz) throws Exception {
        List<T> list = new ArrayList<>();
        for(Map<String,Object> object : data){
            try {
                T parsedObject = clazz.cast(parseWithIS(object));
                list.add(parsedObject);
            } catch (Exception e){

            }
        }

        return list;
    }
}
