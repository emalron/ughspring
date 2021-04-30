package com.spring.pro30;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.PostConstruct;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
@Transactional(propagation = Propagation.REQUIRED)
public class GameService {
    @Autowired
    private GameDAO gameDAO;
    @Autowired
    private MemberDAO memberDAO;

    private Map<String, Integer> prices;
    @PostConstruct
    public void init() {
        prices = new HashMap<String, Integer>();
        prices.put("coin", 1000);
    }

    public List<ScoreVO> showAll() {
        List<ScoreVO> list = gameDAO.selectAll();
        return list;
    }
    public ScoreVO getScoreById(String id) {
        ScoreVO score = gameDAO.selectOne(id);
        return score;
    }
    public int setScore(ScoreVO scoreVO) {
        int result = gameDAO.insertScore(scoreVO);
        return result;
    }
    public int updateScore(ScoreVO scoreVO) {
        int result = gameDAO.updateScore(scoreVO);
        return result;
    }
    public int updateTransaction(String id, BillVO billVO) {
        ScoreVO scoreVO = gameDAO.selectOne(id);
        String item = billVO.getItem();
        int qty = billVO.getQty();
        int price = prices.get(item);
        int cost = qty * price;
        int score_player = scoreVO.getScore();
        boolean enough_score = score_player >= cost;
        System.out.println("money: " + score_player + " cost: " + cost);
        if(enough_score) {
            ScoreVO newScore = new ScoreVO(id, -cost);
            int update_result = gameDAO.updateScore(newScore);
            MemberVO member = memberDAO.selectMemberById(id);
            int coin = member.getCoin() + qty;
            member.setCoin(coin);
            update_result = memberDAO.updateMember(member);
            return update_result;
        }
        return 0;
    }
    public int insertCoin(MemberVO member) {
        int result = memberDAO.updateMember(member);
        return result;
    }
}
