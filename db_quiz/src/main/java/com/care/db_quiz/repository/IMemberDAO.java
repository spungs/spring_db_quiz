package com.care.db_quiz.repository;

import java.util.ArrayList;

import org.springframework.stereotype.Repository;

import com.care.db_quiz.dto.MemberDTO;

@Repository
public interface IMemberDAO {
	public MemberDTO selectId(String id);
	public void insert(MemberDTO member);
	public ArrayList<MemberDTO> list();
	public void update(MemberDTO member);
	public void delete(String id);
}
