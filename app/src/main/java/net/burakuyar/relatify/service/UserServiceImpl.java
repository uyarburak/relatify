package net.burakuyar.relatify.service;

import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import net.burakuyar.relatify.dao.UserDao;
import net.burakuyar.relatify.model.Person;
import net.burakuyar.relatify.model.Relative;
import net.burakuyar.relatify.model.User;
import net.burakuyar.relatify.util.JSONExport;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.InputStream;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/**
 * Created by burak on 2/25/2018.
 */
@Service
public class UserServiceImpl implements UserService {
    @Autowired
    private UserDao userDao;

    @Override
    public User getUser(String email, InputStream pdfStream) throws Exception{
        JsonArray arr = new JSONExport().PDFToJSON(pdfStream);
        User user = new User();
        user.setEmail(email);
        user.setPdfJSON(arr.toString());
        List<Relative> relatives = new ArrayList<>();
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
                Relative relative = new Relative();
                relative.setPerson(person);
                relative.setRelation(innerArr.get(2).getAsString());
                relatives.add(relative);
            }else{
                user.setUser(person);
            }
        }
        user.setRelatives(relatives);
        return user;
    }

    @Override
    public int save(User user){
        return userDao.save(user);
    }
}
