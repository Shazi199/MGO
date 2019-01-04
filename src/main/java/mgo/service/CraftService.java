package mgo.service;

import java.util.List;

import mgo.model.Craft;

public class CraftService {
    public final Craft dao = new Craft().dao();

    public List<Craft> findCraftsByUserid(long userId) {
        return dao.find(dao.getSql("findCraftsByUserid"), userId);
    }
}
