package com.spring.pro30;

import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
public class ArticleDAO {
	@Autowired
	private SqlSession sqlSession;
	
	public List<ArticleVO> selectAllArticles() {
		List<ArticleVO> result = sqlSession.selectList("mapper.board.selectAllArticles");
		return result;
	}
	
	public ArticleVO selectArticleByArticleNo(int articleNo) {
		ArticleVO article = sqlSession.selectOne("mapper.board.selectArticleByArticleNo", articleNo);
		return article;
	}

	public int selectTotalArticles() {
		int total_articles = sqlSession.selectOne("selectTotalArticles");
		return total_articles;
	}

	public List<ArticleVO> selectPages(int page) {
		List<ArticleVO> articles = sqlSession.selectList("selectPage", page);
		return articles;
	}
	
	public int insertArticle(ArticleVO vo) {
		int result = sqlSession.insert("mapper.board.insertArticle2", vo);
		if(result == 0) {
			return -1;
		}
		return vo.getArticleNo();
	}
	
	public int deleteArticle(int articleNo) {
		int result = sqlSession.delete("deleteArticle", articleNo);
		return result;
	}

	public int updateArticle(ArticleVO vo) {
		System.out.println(vo.getArticleNo());
		int result = sqlSession.update("updateArticle", vo);
		return result;
	}
}
