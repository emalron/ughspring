package com.spring.pro30;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpSession;
import java.util.*;

@Controller
public class GameController {
    @Autowired
    private GameService gameService;
    @Autowired
    private MemberService memberService;

    @RequestMapping(value="/ugh", method= RequestMethod.GET)
    public String ughHome(HttpSession session) {
        MemberVO member = (MemberVO) session.getAttribute("login");
        if(member == null) {
            return "redirect:/lobby";
        }
        boolean no_coin_time = !coinTime(session);
        if(no_coin_time) {
            int coins = member.getCoin();
            boolean not_enough_coins = coins <= 0;
            if(not_enough_coins) {
                return "redirect:/lobby";
            }
            member.setCoin(coins-1);
            session.setAttribute("login", member);
            gameService.insertCoin(member);
            Calendar calendar = Calendar.getInstance();
            calendar.add(Calendar.MINUTE, 30);
            session.setAttribute("coin_time", calendar);
            System.out.println(calendar.getTimeInMillis()/1000);
        }
        String id = member.getId();
        ScoreVO scoreVO = gameService.getScoreById(id);
        if(scoreVO == null) {
            scoreVO = new ScoreVO(id, 0);
            gameService.setScore(scoreVO);
        }
        return "ugh";
    }

    private boolean coinTime(HttpSession session) {
        Calendar calendar = (Calendar) session.getAttribute("coin_time");
        if(calendar == null) {
            return false;
        }
        Calendar now = Calendar.getInstance();
        return now.before(calendar);
    }

    @RequestMapping(value="/cointime", method=RequestMethod.GET)
    public ModelAndView getCoin(HttpSession session) {
        ModelAndView mav = new ModelAndView("jsonView");
        Calendar coin_time = (Calendar) session.getAttribute("coin_time");
        Calendar now = Calendar.getInstance();
        boolean null_cointime = coin_time == null;
        if(null_cointime) {
            mav.addObject("msg", "no_cointime");
            return mav;
        }
        mav.addObject("msg", "cointime");
        mav.addObject("cointime", coin_time.getTimeInMillis()/1000);
        mav.addObject("now", now.getTimeInMillis()/1000);
        return mav;
    }

    @RequestMapping(value="/ugh", method=RequestMethod.POST)
    public void scoreUpdate(HttpSession session, @RequestBody Map<String, Object> data) {
        MemberVO member = (MemberVO) session.getAttribute("login");
        String id = member.getId();
        int score = Integer.parseInt(data.get("score").toString());
        boolean invalid_score = score < 0;
        if(invalid_score) {
            return;
        }
        ScoreVO scoreVO = new ScoreVO(id, score);
        gameService.updateScore(scoreVO);
    }

    @RequestMapping(value="/ugh/register", method=RequestMethod.POST)
    public String register(HttpSession session) {
        String nextPage = "redirect:/";
        MemberVO member = (MemberVO) session.getAttribute("login");
        boolean invalid_member = member == null;
        if(invalid_member) {
            System.out.println("/ugh/register error session has no id");
            return nextPage;
        }
        String id = member.getId();
        int score = 0;
        ScoreVO scoreVO = new ScoreVO(id, score);
        gameService.setScore(scoreVO);
        return nextPage;
    }

    @RequestMapping(value="/ranking", method=RequestMethod.GET)
    public String ranking(Model model) {
        List<ScoreVO> list = gameService.showAll();
        model.addAttribute("list", list);
        return "ranking";
    }

    @RequestMapping(value="/store", method=RequestMethod.GET)
    public String store(Model model, HttpSession session) {
        String id = ((MemberVO) session.getAttribute("login")).getId();
        ScoreVO score = gameService.getScoreById(id);
        model.addAttribute("score", score);
        return "store";
    }

    @RequestMapping(value="/lobby", method=RequestMethod.GET)
    public String lobby(HttpSession session) {
        boolean has_coin_time = coinTime(session);
        if(has_coin_time) {
            return "redirect:/ugh";
        }
        return "lobby";
    }

    @RequestMapping(value="/store/buy", method=RequestMethod.POST)
    public ModelAndView storeBuy(@RequestBody BillVO billVO, HttpSession session) {
        ModelAndView mav = new ModelAndView("jsonView");
        MemberVO member = (MemberVO) session.getAttribute("login");
        String id = member.getId();
        String debug_msg = String.format("%s: %s (%d)", id, billVO.getItem(), billVO.getQty());
        System.out.println(debug_msg);
        int result = gameService.updateTransaction(id, billVO);
        member = memberService.getMemberById(id);
        session.setAttribute("login", member);
        ScoreVO score = gameService.getScoreById(id);
        mav.addObject("coin", member.getCoin());
        mav.addObject("score", score.getScore());
        return mav;
    }
}