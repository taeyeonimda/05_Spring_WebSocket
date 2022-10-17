package kr.or.board.model.service;

import java.util.ArrayList;
import java.util.HashMap;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import kr.or.board.model.dao.BoardDao;
import kr.or.board.model.vo.Board;
import kr.or.board.model.vo.BoardPageData;
import kr.or.board.model.vo.FileVo;

@Service
public class BoardService {

	@Autowired
	private BoardDao dao;
	public BoardPageData selectBoardList(int reqPage) {
		//한페이지당 보여줄 게시물 수
		int numPerPage = 5;
		//reqPage에 게시물 번호 읽어오기
		int end = reqPage*numPerPage;
		int start = end-numPerPage +1;
		HashMap<String,Object> map = new HashMap<String,Object>();
		map.put("start",start);
		map.put("end",end);
		ArrayList<Board>list = dao.selectBoardList(map);
		//pageNavi 시작
		//전체페이지 수 계산필요
		int totalCount = dao.selectBoardCount();
		int totalPage = 0;
		if(totalCount%numPerPage==0) {
			totalPage = totalCount/numPerPage ;
		}else {
			totalPage = totalCount/numPerPage+1 ;
		}
		
		int pageNaviSize = 5;
//		int pageNo =((reqPage-1)/pageNaviSize)*pageNaviSize+1;
		int pageNo =1;
		if(reqPage>3) {
			pageNo = reqPage -2;
		}

		String pageNavi="<ul class='pagination circle-style'>";
		if(pageNo !=1) {
			 pageNavi +="<li>";
			 pageNavi +="<a class='page-item' href='/boardList.do?reqPage="+(pageNo-1)+"'>";
			 pageNavi +="<span class='material-icons'>chevron_left</span>";
			 pageNavi +="</a></li>";
		 }
		for(int i =0;i<pageNaviSize;i++) {
			 if(pageNo == reqPage) {
				 pageNavi +="<li>";
				 pageNavi +="<a class='page-item active-page' href='/boardList.do?reqPage="+pageNo+"'>";
				 pageNavi += pageNo;
				 //pageNavi +="<span class='material-icons'>chevron_left</span>";
				 pageNavi +="</a></li>";
			 }else {
				 pageNavi +="<li>";
				 pageNavi +="<a class='page-item' href='/boardList.do?reqPage="+pageNo+"'>";
				 pageNavi += pageNo;
				 //pageNavi +="<span class='material-icons'>chevron_left</span>";
				 pageNavi +="</a></li>";
			 }
			 pageNo++;
			 if(pageNo>totalPage) {
				break; 
			 }
		 }
		 //다음버튼
		 if(pageNo<=totalPage) {
			 pageNavi +="<li>";
			 pageNavi +="<a class='page-item' href='/boardList.do?reqPage="+pageNo+"'>";
			 pageNavi +="<span class='material-icons'>chevron_right</span>";
			 pageNavi +="</a></li>";
		 }
		 pageNavi += "</ul>";
		 BoardPageData bpd = new BoardPageData(list,pageNavi,reqPage,numPerPage);
		 return bpd;

	}
////	public BoardPageData selectBoardList(int reqPage) {
////		//한페이지당 보여줄 게시물 수
////		int numPerPage = 5;
////		//reqPage에 게시물 번호 읽어오기
////		int end = reqPage*numPerPage;
////		int start = end-numPerPage +1;
////		HashMap<String,Object> map = new HashMap<String,Object>();
////		map.put("start",start);
////		map.put("end",end);
////		ArrayList<Board>list = dao.selectBoardList(map);
////		//pageNavi 시작
////		//전체페이지 수 계산필요
////		int totalCount = dao.selectBoardCount();
////		int totalPage = 0;
////		if(totalCount%numPerPage==0) {
////			totalPage = totalCount/numPerPage ;
////		}else {
////			totalPage = totalCount/numPerPage+1 ;
////		}
////		
////		int pageNaviSize = 5;
//////		int pageNo =((reqPage-1)/pageNaviSize)*pageNaviSize+1;
////		int pageNo =1;
////		if(reqPage>3) {
////			pageNo = reqPage -2;
////		}
////
////		String pageNavi="<ul class='pagination circle-style'>";
////		if(pageNo !=1) {
////			 pageNavi +="<li>";
////			 pageNavi +="<a class='page-item' href='/boardList.do?reqPage="+(pageNo-1)+"'>";
////			 pageNavi +="<span class='material-icons'>chevron_left</span>";
////			 pageNavi +="</a></li>";
////		 }
////		for(int i =0;i<pageNaviSize;i++) {
////			 if(pageNo == reqPage) {
////				 pageNavi +="<li>";
////				 pageNavi +="<a class='page-item active-page' href='/boardList.do?reqPage="+pageNo+"'>";
////				 pageNavi += pageNo;
////				 //pageNavi +="<span class='material-icons'>chevron_left</span>";
////				 pageNavi +="</a></li>";
////			 }else {
////				 pageNavi +="<li>";
////				 pageNavi +="<a class='page-item' href='/boardList.do?reqPage="+pageNo+"'>";
////				 pageNavi += pageNo;
////				 //pageNavi +="<span class='material-icons'>chevron_left</span>";
////				 pageNavi +="</a></li>";
////			 }
////			 pageNo++;
////			 if(pageNo>totalPage) {
////				break; 
////			 }
////		 }
////		 //다음버튼
////		 if(pageNo<=totalPage) {
////			 pageNavi +="<li>";
////			 pageNavi +="<a class='page-item' href='/boardList.do?reqPage="+pageNo+"'>";
////			 pageNavi +="<span class='material-icons'>chevron_right</span>";
////			 pageNavi +="</a></li>";
////		 }
////		 pageNavi += "</ul>";
////		 BoardPageData bpd = new BoardPageData(list,pageNavi);
////		 return bpd;
//
//	}

	public Board selectOneBoard(Board board) {
//		 ArrayList<FileVo> fileList = dao.selectFileList(board);
		 Board b = dao.selectOneBoard(board);
//		 b.setFileList(fileList);
		 return b;
	}

	public int insertBoard(Board b) {
		int result = dao.insertBoard(b);
		if(result>0) {
//			int boardNo=0;		
			if(!b.getFileList().isEmpty()) {
//				boardNo=dao.selectBoardNo();
				
				for(FileVo fileVo:b.getFileList()) {
					fileVo.setBoardNo(b.getBoardNo());
					result += dao.insertFile(fileVo);
				}
			}
		}
		return result;
	}

	public FileVo getFileNo(int fileNo) {
		FileVo fv = dao.selectOneFileVo(fileNo);
		return fv;
	}
}
