package mgo.service;

import java.util.List;

import mgo.model.Stage;

public class StageService {
    public final Stage dao = new Stage().dao();

    public List<Stage> findStagesByParentId(int parentId) {
        return dao.find(dao.getSql("findStagesByParentId"), parentId);
    }
}
