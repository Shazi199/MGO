package mgo.service;

import mgo.model.User;

public class UserService {
    public final User dao = new User().dao();

    public User findByUsername(String username) {
        if (username == null) {
            return null;
        }
        return dao.findFirst(dao.getSql("findUserByUsername"), username);
    }

    public User findByOauth(String authid, String type) {
        if (authid == null || type == null) {
            return null;
        }
        return dao.findFirst(dao.getSql("findUserByOauth"), authid, type);
    }
}
