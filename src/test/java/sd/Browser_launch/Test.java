/*@author: Pranawa Mishra, Date: 18-05-2018*/
package sd.Browser_launch;

import java.io.FileReader;
import java.lang.reflect.Array;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.lang.reflect.Type;

import com.google.common.base.Equivalence;
import com.google.common.reflect.TypeToken;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.JSONValue;
import org.json.simple.parser.*;
import org.testng.collections.Lists;

import java.io.*;

public class Test {

    public static void main(String[] args) throws IOException, ParseException {

        deseri();

    }

    public static void deseri() throws IOException, ParseException {
        //JSON parser object to parse read file
        JSONParser jsonParser = new JSONParser();

        try (FileReader reader = new FileReader("d:\\data.json")) {
            //Read JSON file
            Object obj = jsonParser.parse(reader);

            JSONArray employeeList = (JSONArray) obj;
            // System.out.println(employeeList);

            //Iterate over employee array
            employeeList.forEach(emp -> {
                try {
                    parseEmployeeObject((JSONObject) emp);
                } catch (ParseException e) {
                    e.printStackTrace();
                }
            });

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ParseException e) {
            e.printStackTrace();
        }
    }

    private static void parseEmployeeObject(JSONObject employee) throws ParseException {
        //Get employee object within list
        JSONObject employeeObject = (JSONObject) employee.get("output");
        registrant_contact(employeeObject);
        admincontact(employeeObject);
        technical_contact(employeeObject);

        //Get employee first name
        //String firstName = (String) employeeObject.get("updated_on");
        //	//System.out.println(firstName);
        //	System.out.println(employeeObject);

    }


    public static void registrant_contact(JSONObject employeeObject) throws ParseException {
        JSONObject employeeObject1 = (JSONObject) employeeObject.get("registrant_contact");
        //System.out.println("all: -" + employeeObject1);
        String registrant = (String) employeeObject1.get("email");
        System.out.println("registrant's Email : " + registrant);

        try {
            FileWriter fstream = new FileWriter("d:\\emails\\Registrant.txt", true);
            BufferedWriter out = new BufferedWriter(fstream);
            out.write(registrant + ",");
            out.newLine();
            out.close();
        }
        catch (Exception e) {

            System.err.println("Error while writing to file: " + e.getMessage());
        }

    }

    public static void admincontact(JSONObject employeeObject) throws ParseException {
        JSONObject employeeObject1 = (JSONObject) employeeObject.get("admin_contact");
        //System.out.println("all: -" + employeeObject1);
        String admin_email = (String) employeeObject1.get("email");
        System.out.println("Admin email: " + admin_email);
    }

        public static void technical_contact (JSONObject employeeObject) throws ParseException {
            JSONObject employeeObject1 = (JSONObject) employeeObject.get("technical_contact");
            //System.out.println("all: -" + employeeObject1);
            String technical_contact = (String) employeeObject1.get("email");
            System.out.println("Technical contact's email is: "+technical_contact);

        }
    }


