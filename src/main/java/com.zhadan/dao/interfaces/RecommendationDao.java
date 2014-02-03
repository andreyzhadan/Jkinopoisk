package com.zhadan.dao.interfaces;

import com.zhadan.bean.Recommendation;
import com.zhadan.exceptions.DAOException;

import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * User: Andrew
 * Date: 20.01.14
 * Time: 1:06
 */
public interface RecommendationDao extends BasicDao<Recommendation> {
    public static final String HQL_SELECT_ALL = "from Movie";
    public static final String HQL_COUNT_RECOMMENDATIONS = "select count(*) from Recommendation";

    void batchInsert(List<Recommendation> recommendations) throws IllegalArgumentException, DAOException;
}
