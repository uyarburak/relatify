package com.okapi.relatives.service;

import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.okapi.relatives.model.Person;
import com.okapi.relatives.model.User;
import technology.tabula.MyMain;

import java.io.File;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/**
 * Created by burak on 2/24/2018.
 */
public class PDFService {
    private MyMain parser = new MyMain();
    public User serve(String pathPDF){
        JsonArray arr = parser.PDFToJSON(new File(pathPDF));
        User user = new User();
        List<Person> relatives = new ArrayList<>();
        Iterator<JsonElement> iter = arr.iterator();
        while (iter.hasNext()){
            JsonArray innerArr = iter.next().getAsJsonArray();
            Person person = new Person();
            person.setGender(innerArr.get(1).getAsString());
            person.setName(innerArr.get(3).getAsString());
            person.setSurname(innerArr.get(4).getAsString());
            person.setFatherName(innerArr.get(5).getAsString());
            person.setMotherName(innerArr.get(6).getAsString());
            person.setBirthPlaceAndDate(innerArr.get(7).getAsString());
            person.setAddress(innerArr.get(8).getAsString());
            person.setSerieNo(innerArr.get(9).getAsString());
            person.setMaritalStatus(innerArr.get(10).getAsString());
            person.setLiveStatus(innerArr.get(11).getAsString());
            if(iter.hasNext()){
                relatives.add(person);
            }else{
                user.setUser(person);
            }
        }
        user.setRelatives(relatives);
        return user;
    }
    public static void main(String args[]){
        PDFService service = new PDFService();
        User user = service.serve("C:\\Users\\burak\\Downloads\\alt_ust_soy_bilgi.pdf");
        int i = 1;
    }

}
