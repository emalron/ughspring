package com.spring.pro30;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class GameDAO {
    @Autowired
    private SqlSession sqlSession;

    public List<ScoreVO> selectAll() {
        List<ScoreVO> result = sqlSession.selectList("mapper.score.selectAllScores");
        return result;
    }
    public ScoreVO selectOne(String id) {
        ScoreVO result = sqlSession.selectOne("mapper.score.selectScore", id);
        return result;
    }
    public int updateScore(ScoreVO scoreVO) {
        int result = sqlSession.update("mapper.score.updateScore", scoreVO);
        return result;
    }
    public int insertScore(ScoreVO scoreVO) {
        int result = sqlSession.insert("mapper.score.insertScore", scoreVO);
        return result;
    }
    public int deleteScore(String id) {
        int result =sqlSession.delete("mapper.score.deleteScore", id);
        return result;
    }
}
