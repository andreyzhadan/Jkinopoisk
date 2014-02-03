package com.zhadan.editor;

import com.zhadan.bean.Movie;
import com.zhadan.dao.interfaces.MovieDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.beans.PropertyEditorSupport;

/**
 * Created with IntelliJ IDEA.
 * User: azhadan
 * Date: 1/20/14
 * Time: 11:29 AM
 */
@Component
public class MovieEditor extends PropertyEditorSupport {

    @Autowired
    private MovieDao movieDao;

    @Override
    public void setAsText(String text) throws IllegalArgumentException {
        Movie movie = movieDao.find(Integer.valueOf(text));
        this.setValue(movie);
    }
}
