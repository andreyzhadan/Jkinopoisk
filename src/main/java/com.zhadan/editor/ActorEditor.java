package com.zhadan.editor;

import com.zhadan.bean.Actor;
import com.zhadan.dao.interfaces.ActorDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.beans.PropertyEditorSupport;

/**
 * Created with IntelliJ IDEA.
 * User: azhadan
 * Date: 9/5/13
 * Time: 4:08 PM
 */
@Component
public class ActorEditor extends PropertyEditorSupport {

    @Autowired
    private ActorDao actorDao;

    @Override
    public void setAsText(String text) throws IllegalArgumentException {
        Actor actor = actorDao.find(Integer.valueOf(text));
        this.setValue(actor);
    }
}
