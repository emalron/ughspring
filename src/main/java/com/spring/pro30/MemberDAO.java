package com.spring.pro30;

import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
public class MemberDAO {
	@Autowired
	private SqlSession sqlSession;
	
	public List<MemberVO> selectAllMembers() {
		List<MemberVO> result = sqlSession.selectList("selectAllMembers");
		return result;
	}

	public MemberVO selectLoginMember(MemberVO vo) {
		MemberVO vo_ = sqlSession.selectOne("selectLoginMember", vo);
		return vo_;
	}

	public int insertMember(MemberVO vo) {
		int result = sqlSession.insert("insertMember", vo);
		return result;
	}

	public MemberVO selectMemberById(String id) {
		MemberVO member = sqlSession.selectOne("selectMemberById", id);
		return member;
	}

	public int updateMember(MemberVO vo) {
		int result = sqlSession.update("updateMember", vo);
		return result;
	}
}
