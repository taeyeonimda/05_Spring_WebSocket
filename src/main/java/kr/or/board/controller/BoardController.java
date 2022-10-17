package kr.or.board.controller;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.multipart.MultipartFile;

import common.FileRename;
import kr.or.board.model.service.BoardService;
import kr.or.board.model.vo.Board;
import kr.or.board.model.vo.BoardPageData;
import kr.or.board.model.vo.FileVo;

@Controller
public class BoardController {
	@Autowired
	private BoardService service;
	@Autowired
	private FileRename fileRename;
	
	@RequestMapping(value="/boardList.do")
	public String boardList(int reqPage,Model model) {
		BoardPageData bpd = service.selectBoardList(reqPage);
		model.addAttribute("list",bpd.getList());
		model.addAttribute("pageNavi",bpd.getPageNavi());
		model.addAttribute("reqPage",bpd.getReqPage());
		model.addAttribute("numPerPage",bpd.getNumPerPage());
		return "board/boardList";	
	}
	
	@RequestMapping(value="/boardView.do")
	public String boardView(Board board,Model model){
		Board b = service.selectOneBoard(board);
		model.addAttribute("b",b);
		return "board/boardView";
	}
	
	@RequestMapping(value="/boardWriteFrm.do")
	public String boardWriterFrm() {
		return "board/boardWriteFrm";
	}
	
	@RequestMapping(value="/boardWrite.do")
	public String boardWriter(Board b,MultipartFile[] boardFile,HttpServletRequest request) {
		ArrayList<FileVo> list = new ArrayList<FileVo>();
		if(!boardFile[0].isEmpty()) {
			String savePath = request.getSession().getServletContext().getRealPath("/resources/upload/board/");
			for(MultipartFile file: boardFile) {
				String filename = file.getOriginalFilename();
				String filepath = fileRename.fileRename(savePath,filename);
				
				FileOutputStream fos;
				try {
					fos = new FileOutputStream(new File(savePath+filepath));
					BufferedOutputStream bos = new BufferedOutputStream(fos);
					byte[] bytes = file.getBytes();
					bos.write(bytes);
					bos.close();
				} catch (FileNotFoundException e) {
					e.printStackTrace();
				} catch (IOException e) {
					e.printStackTrace();
				}
				//파일업로드 끝(파일1개 업로드)
				FileVo fileVo = new FileVo();
				fileVo.setFilename(filename);
				fileVo.setFilepath(filepath);
				list.add(fileVo);

			}
		}
		b.setFileList(list);
		int result = service.insertBoard(b);
		if(result>0) {
			return "redirect:/boardList.do?reqPage=1";
		}else {
			return "board/boardWriteFrm";
		}	
	}
	
	
	@RequestMapping(value="/boardFileDown.do")
	public void boardFileDown(int fileNo,HttpServletRequest request,HttpServletResponse response) throws IOException,FileNotFoundException {
		FileVo fv = service.getFileNo(fileNo);
		String savePath = request.getSession().getServletContext().getRealPath("/resources/upload/board/");
		String downFile = savePath+fv.getFilepath();
		FileInputStream fis = new FileInputStream(downFile);
		BufferedInputStream bis = new BufferedInputStream(fis);
		
		ServletOutputStream sos = response.getOutputStream();
		BufferedOutputStream bos = new BufferedOutputStream(sos);
		String resFilename = new String(fv.getFilename().getBytes("UTF-8"),"ISO-8859-1");
		response.setContentType("application/octet-stream");
		response.setHeader("Content-disposition", 
				"attachment;filename="+resFilename);
		
		while(true) {
			int read = bis.read();
			//파일을 다읽어오면 -1이라서 -1이 아니면 break로 나가게함
			if(read != -1) {
				bos.write(read);
			}else {
				break;
			}
		}
		bos.close();
		bis.close();
	}
}

