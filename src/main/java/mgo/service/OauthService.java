package mgo.service;

import mgo.model.Oauth;

public class OauthService {
    public final Oauth dao = new Oauth().dao();

    public Oauth findOauthByType(long uid, String type) {
        if (type == null) {
            return null;
        }
        return dao.findFirst(dao.getSql("findOauthByType"), uid, type);
    }
}
