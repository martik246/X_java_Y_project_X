package utils.json;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.ParseException;
import org.w3c.dom.ls.LSOutput;

import java.util.Objects;
import java.util.Set;

public class JSONParser {
    private final org.json.simple.parser.JSONParser JSON_PARSER;
    public JSONParser(){
        JSON_PARSER = new org.json.simple.parser.JSONParser();
    }
    public JSONObject deserializeObject(String object) {
        try {
            Object obj = this.JSON_PARSER.parse(object);
            return (JSONObject) obj;
        } catch (ParseException e) {
            throw new RuntimeException(e);
        }
    }

    public void showPrettyJSON(JSONObject object, String tab){
        Set keySet = object.keySet();
        for (Object key: keySet){
            Object value = object.get(key);
            if (value instanceof JSONObject) {
                System.out.println(key + ":");
                showPrettyJSON((JSONObject) value, " ");
                continue;
            } else if (value instanceof JSONArray jsonArray) {
                System.out.println(key + ":");
                for (Object jsonObject : jsonArray){
                    showPrettyJSON((JSONObject) jsonObject, "\t");
                }
                continue;
            }
            System.out.printf("%s: %s\n", key, value);
        }
    }
}
