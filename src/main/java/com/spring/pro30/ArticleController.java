package com.spring.pro30;

import java.io.File;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.commons.io.FilenameUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class ArticleController {
    private static final Logger logger = LoggerFactory.getLogger(ArticleController.class);
    @Autowired
    private ArticleService articleService;
    @Autowired
    private MemberDAO memberDAO;

    @RequestMapping(value="/board/listArticles.do", method=RequestMethod.GET)
    public String listArticles(@RequestParam(value = "page", defaultValue = "1") int page, Model model) {
        if(page < 1) page = 1;
        int total_articles = articleService.getTotalArticlesNumber();
        int articlesPerPage = 10;
        int pagesPerSection = 10;
        int pages = total_articles / articlesPerPage;
        int begin = ((page-1) / pagesPerSection) * pagesPerSection + 1;
        int end = (pages - begin)/pagesPerSection > 0 ? begin+9 : pages;
        PageVO pageVO = new PageVO(page, begin, end);
        model.addAttribute("page", pageVO);
        List<ArticleVO> result = articleService.listArticlesPage(page);
        model.addAttribute("articles", result);
        return "/board/articles";
    }

    @RequestMapping(value="/board/{id}", method=RequestMethod.GET)
    public String getArticle(HttpServletResponse response, Model model, @PathVariable("id") int id) {
        response.setContentType("text/http;charset=utf-8");
        ArticleVO article = articleService.getArticle(id);
        model.addAttribute("article", article);
        System.out.println("SYSTEM OUT PRINTLN");
        logger.info("LOGGER INFO");
        return "/board/article";
    }

    @RequestMapping(value="/board/write.do", method=RequestMethod.GET)
    public String writeForm() {
        return "/board/writeForm";
    }

    @RequestMapping(value="/board/write.do", method=RequestMethod.POST)
    public String write(HttpServletRequest request, @ModelAttribute("article") ArticleVO articleVO, HttpSession session) throws Exception {
        request.setCharacterEncoding("utf-8");
        MemberVO user = (MemberVO) session.getAttribute("login");
        articleVO.setId(user.getId());
        articleVO.setParentNo(0);
        int articleNo = articleService.writeArticle(articleVO);
        String nextPage = String.format("redirect:/board/%d", articleNo);
        return nextPage;
    }

    @RequestMapping(value="/board/delete/{id}", method=RequestMethod.GET)
    public String delete(@PathVariable("id") int articleNo, HttpServletRequest request,
                         HttpServletResponse response, HttpSession session) throws Exception {
        response.setContentType("text/html;charset=utf-8");
        //본인 확인
        MemberVO memberVO = (MemberVO) session.getAttribute("login");
        ArticleVO articleVO = articleService.getArticle(articleNo);
        boolean not_author = !memberVO.getId().equals(articleVO.getId());
        if(not_author) {
            String referer = request.getHeader("Referer");
            return String.format("redirect:%s", referer);
        }
        // 글 삭제
        int result = articleService.deleteArticle(articleNo);
        String contextPath = request.getContextPath();
        return String.format("redirect:%s/board/listArticles.do", contextPath);
    }

    @RequestMapping(value="/board/edit/{id}", method=RequestMethod.GET)
    public String edit(@PathVariable("id") int articleNo, HttpSession session) {
        ArticleVO vo = articleService.getArticle(articleNo);
        session.setAttribute("article", vo);
        return "/board/edit";
    }

    @RequestMapping(value="/board/edit/{id}", method=RequestMethod.POST)
    public String editProcess(@PathVariable("id") int articleNo, @ModelAttribute("article") ArticleVO articleVO, HttpSession session) {
        ArticleVO article = articleService.getArticle(articleNo);
        MemberVO user = (MemberVO) session.getAttribute("login");
        boolean is_he_right_author = user != null && article.getId().equals(user.getId());
        if(is_he_right_author) {
            // articleVO.setId(user.getId());
            articleVO.setArticleNo(articleNo);
            int result = articleService.editArticle(articleVO);
        }
        session.removeAttribute("article");
        String page = String.format("redirect:/board/%s", article.getArticleNo());
        return page;
    }

    @RequestMapping(value="/board/reply/{id}", method=RequestMethod.GET)
    public String reply(@PathVariable("id") int articleNo, HttpSession session, Model model) {
        ArticleVO vo = articleService.getArticle(articleNo);
        StringBuilder sb = new StringBuilder("&#10;&#10;&#10;---원문---&#10;");
        sb.append(vo.getContent());
        vo.setContent(sb.toString());
        session.setAttribute("article", vo);
        return "/board/reply";
    }

    @RequestMapping(value="/board/reply/{id}", method=RequestMethod.POST)
    public String replyProcess(@PathVariable("id") int articleNo, @ModelAttribute ArticleVO vo, HttpSession session) {
        MemberVO author = (MemberVO) session.getAttribute("login");
        vo.setId(author.getId());
        vo.setParentNo(articleNo);
        int num = articleService.writeArticle(vo);
        return String.format("redirect:/board/%d", num);
    }

    private static final ApplicationContext context = ApplicationContextProvider.getContext();
    @RequestMapping(value="/board/image.do", method=RequestMethod.POST)
    public ModelAndView image(HttpServletRequest request) throws Exception {
        ModelAndView mav = new ModelAndView("jsonView");
        List<MultipartFile> fileList = ((MultipartHttpServletRequest)request).getFiles("upload");
        String imgPath = null;
        for (MultipartFile file : fileList) {
            if(file.getSize() > 0) {
                String originalFilename = file.getOriginalFilename();
                String ext = FilenameUtils.getExtension(originalFilename);
                String newImageName = String.format("img_%s.%s", UUID.randomUUID(), ext);
                PathVO pathVO = (PathVO) context.getBean("pathData");
                String loadURL = pathVO.getLoadUrl();
                String savePath = pathVO.getSavePath();
                imgPath = String.format("%s%s", loadURL, newImageName);
                String realPath = String.format("%s\\%s", savePath, newImageName);
                File file_ = new File(realPath);
                file.transferTo(file_);
            }
        }
        mav.addObject("uploaded", true);
        mav.addObject("url", imgPath);
        return mav;
    }

    @RequestMapping(value="/board/image.do", method=RequestMethod.GET)
    public ModelAndView imageTest(@RequestParam Map<String, Object> map) {
        ModelAndView mav = new ModelAndView();
        System.out.println("GET TEST");
        return mav;
    }
}
