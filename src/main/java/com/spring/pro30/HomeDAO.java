package com.spring.pro30;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
public class HomeDAO {
    @Autowired
    SqlSession sqlSession;

    public String getDatabaseName() {
        String result = sqlSession.selectOne("mapper.score.dbname");
        return result;
    }
}
