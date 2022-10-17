package kr.or.board.model.dao;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import kr.or.board.model.vo.Board;
import kr.or.board.model.vo.FileVo;

@Repository
public class BoardDao {

	@Autowired
	private SqlSessionTemplate sqlSession;

	public ArrayList<Board> selectBoardList(HashMap<String, Object> map) {
		List list = sqlSession.selectList("board.selectBoardList", map);
		return (ArrayList<Board>)list;
	}

	public int selectBoardCount() {
		int totalCount = sqlSession.selectOne("board.totalCount");
		return totalCount;
	}

	public Board selectOneBoard(Board board) {
		Board b = sqlSession.selectOne("board.selectOneBoard",board);
		return b;
	}

	public ArrayList<FileVo> selectFileList(Board board) {
		List list = sqlSession.selectList("board.selectFileList",board);
		return (ArrayList<FileVo>)list;
	}

	public int insertBoard(Board b) {
		int result = sqlSession.insert("board.insertBoard",b);
		return result;
	}

	public int selectBoardNo() {
		int result = sqlSession.selectOne("board.selectBoardNo");
		return result;
	}

	public int insertFile(FileVo fileVo) {
		int result = sqlSession.insert("board.insertFile",fileVo);
		return result;
	}

	public FileVo selectOneFileVo(int fileNo) {
		FileVo fv = sqlSession.selectOne("board.selectOneFileVo",fileNo);
		return fv;
	}
}
