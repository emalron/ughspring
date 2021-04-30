package com.spring.pro30;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class MemberService {
	@Autowired
	private MemberDAO memberDAO;
	
	public List<MemberVO> listMembers() {
		List<MemberVO> result = memberDAO.selectAllMembers();
		return result;
	}

	public MemberVO loginMember(MemberVO vo) {
		MemberVO vo_ = memberDAO.selectLoginMember(vo);
		return vo_;
	}

	public boolean signupMember(MemberVO vo) {
		MemberVO member = memberDAO.selectMemberById(vo.getId());
		boolean is_already_existing_member = member != null;
		if(is_already_existing_member) {
			return false;
		}
		int result = memberDAO.insertMember(vo);
		boolean success = result != 0;
		return success;
	}

	public MemberVO getMemberById(String id) {
		MemberVO member = memberDAO.selectMemberById(id);
		return member;
	}

	public int updateMember(MemberVO member) {
		int result = memberDAO.updateMember(member);
		return result;
	}
}
