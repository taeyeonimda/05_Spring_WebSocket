package kr.or.dm.model.dao;

import java.util.ArrayList;
import java.util.List;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import kr.or.dm.model.vo.DirectMessage;

@Repository
public class DirectMessageDao {

	@Autowired
	private SqlSessionTemplate sqlSession;

	public int sendDm(DirectMessage dm) {
		int result = sqlSession.insert("dm.sendDm",dm);
		return result;
	}

	public ArrayList<DirectMessage> getReceiveDm(DirectMessage dm) {
		List list = sqlSession.selectList("dm.getAllDm",dm);
		return (ArrayList<DirectMessage>)list;
	}

	public DirectMessage selectOne(int dmNo) {
		DirectMessage dm = sqlSession.selectOne("dm.selectOneDm",dmNo);
		return dm;
	}

	public void updateReadCheck(int dmNo) {
		sqlSession.update("dm.updateReadCheck",dmNo);
	}

	public int dmCount(String memberId) {
		
		return sqlSession.selectOne("dm.dmCount",memberId);
	}
}
