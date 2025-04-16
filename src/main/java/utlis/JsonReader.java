package utlis;


import org.apache.commons.io.FileUtils;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;

import java.io.File;
import java.io.IOException;
import java.text.ParseException;

public class JsonReader {

    public static String getdatafromjson(String path) throws IOException, ParseException, org.json.simple.parser.ParseException {

        String testdata;
        return testdata = (String) getJsonData().get(path);//

    }

    public static JSONObject getJsonData() throws IOException, ParseException, org.json.simple.parser.ParseException {
        //pass the path of the testdata.json file
        File filename = new File("resources//TestData//TestData.json");
        //convert json file into string
        String json = FileUtils.readFileToString(filename, "UTF-8");
        //parse the string into object
        Object obj = new JSONParser().parse(json);
        //give jsonobject o that I can return it to the function everytime it get called
        JSONObject jsonObject = (JSONObject) obj;
        return jsonObject;


    }

    public static JSONArray getJsonArray(String key) throws IOException, ParseException, org.json.simple.parser.ParseException {
        JSONObject jsonObject = getJsonData();
        JSONArray jsonarray = (JSONArray) jsonObject.get(key);
        return jsonarray;
    }


   public static Object getjsondataArray(String key,int index) throws IOException, ParseException, org.json.simple.parser.ParseException {
       JSONArray languages = getJsonArray(key);

       return languages.get(index);

   }
}
