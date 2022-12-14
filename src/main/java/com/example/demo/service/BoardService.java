package com.example.demo.service;




import com.example.demo.dao.BoardMapper;
import com.example.demo.domain.Board;
import com.example.demo.domain.Reply;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BoardService {
	@Autowired
	private BoardMapper m;
	public boolean addBoard(Board b) {
		return m.addBoard(b);
	}

	public List<Board> getBoard(){
		return m.getBoard();
	}
	public Board getBoardOne(int idx) {
		return m.getBoardOne(idx);
	}
	public boolean addReply(Reply r) {
		return m.addReply(r);
	}
	public List<Reply> getReply(int boardIdx) {
		return m.getReply(boardIdx);
	}

	public void deleteMember(int idx) {
		if(m.selectById(idx)!=null){
			m.delete(idx);
		} else {
			throw new IllegalStateException("존재하지 않습니다.");
		}
	}
}
