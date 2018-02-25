package net.burakuyar.relatify.service;

import net.burakuyar.relatify.model.User;
import java.io.InputStream;

/**
 * Created by burak on 2/25/2018.
 */
public interface UserService {

    User getUser(String email, InputStream pdfStream) throws Exception;

    int save(User user);
}
