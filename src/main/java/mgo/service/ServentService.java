package mgo.service;

import java.util.List;

import mgo.model.Servent;

public class ServentService {
    public final Servent dao = new Servent().dao();

    public List<Servent> findServentsByUserid(long userId) {
        return dao.find(dao.getSql("findServentsByUserid"), userId);
    }
}
