package com.spring.pro30;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ArticleService {
	@Autowired
	private ArticleDAO articleDAO;
	
	public List<ArticleVO> listArticles() {
		List<ArticleVO> result = articleDAO.selectAllArticles();
		return result;
	}
	
	public ArticleVO getArticle(int articleNo) {
		ArticleVO article = articleDAO.selectArticleByArticleNo(articleNo);
		return article;
	}

	public int getTotalArticlesNumber() {
		int total = articleDAO.selectTotalArticles();
		return total;
	}

	public List<ArticleVO> listArticlesPage(int page) {
		List<ArticleVO> result = articleDAO.selectPages(page);
		return result;
	}
	
	public int writeArticle(ArticleVO vo) {
		int result = articleDAO.insertArticle(vo);
		return result;
	}
	
	public int deleteArticle(int articleNo) {
		int result = articleDAO.deleteArticle(articleNo);
		return result;
	}

	public int editArticle(ArticleVO vo) {
		int result = articleDAO.updateArticle(vo);
		return result;
	}
}
