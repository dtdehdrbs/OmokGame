package omok;

import java.util.ArrayList;

import javax.servlet.RequestDispatcher;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.servlet.ModelAndView;

@Controller
@SessionAttributes({"sessionID","sessionGrade"})
public class BoardController {
	@Autowired
	OBoardDAO boardDAO;
	@Autowired
	OBoardVO boardVO;
	@Autowired
	OInfoDAO infoDAO;
	@Autowired
	OInfoVO infoVO;
	@Autowired
	OReplyDAO repDAO;
	@Autowired
	OReplyVO repVO;
	
	@RequestMapping(value="free.board", method=RequestMethod.GET)
	public ModelAndView free(String page){
		int currentPage=1;
		if(page != null )
			currentPage = Integer.parseInt(page);
		int cntPerPage=5;
		int total = boardDAO.getTotalCount("free");
		if(total % cntPerPage == 0){
			total = total / cntPerPage;
		}
		else{
			total = total / cntPerPage+1;
		}
		
		ArrayList<OBoardVO> list = boardDAO.getFreeList(currentPage,cntPerPage);
		
		ArrayList<OReplyVO> replyList = repDAO.getReplyList("free");
		ModelAndView mav = new ModelAndView();
		mav.addObject("totalpage", total);
		mav.addObject("list", list);
		mav.addObject("replyList", replyList);
		mav.addObject("boardtype", "자유 게시판");
		mav.addObject("boardtype2", "free");
		mav.addObject("page", "/board/boardlist.jsp");
		mav.setViewName("/omok/index");
		return mav;
	}
	@RequestMapping(value="notice.board", method=RequestMethod.GET)
	public ModelAndView notice(String page){
		int currentPage=1;
		if(page != null )
			currentPage = Integer.parseInt(page);
		int cntPerPage=5;
		int total = boardDAO.getTotalCount("notice");
		if(total % cntPerPage == 0){
			total = total / cntPerPage;
		}
		else{
			total = total / cntPerPage+1;
		}
		
		ArrayList<OBoardVO> list = boardDAO.getNoticeList(currentPage,cntPerPage);
		
		ArrayList<OReplyVO> replyList = repDAO.getReplyList("notice");
		ModelAndView mav = new ModelAndView();
		mav.addObject("totalpage", total);
		mav.addObject("list", list);
		mav.addObject("replyList", replyList);
		mav.addObject("boardtype", "공지사항");
		mav.addObject("boardtype2", "notice");
		mav.addObject("page", "/board/boardlist.jsp");
		mav.setViewName("/omok/index");
		return mav;
	}
	@RequestMapping(value="qna.board", method=RequestMethod.GET)
	public ModelAndView qna(String page){
		int currentPage=1;
		if(page != null )
			currentPage = Integer.parseInt(page);
		int cntPerPage=5;
		int total = boardDAO.getTotalCount("qna");
		if(total % cntPerPage == 0){
			total = total / cntPerPage;
		}
		else{
			total = total / cntPerPage+1;
		}
		
		ArrayList<OBoardVO> list = boardDAO.getQandAList(currentPage,cntPerPage);
		
		ArrayList<OReplyVO> replyList = repDAO.getReplyList("qna");
		ModelAndView mav = new ModelAndView();
		mav.addObject("totalpage", total);
		mav.addObject("list", list);
		mav.addObject("replyList", replyList);
		mav.addObject("boardtype", "Q & A");
		mav.addObject("boardtype2", "qna");
		mav.addObject("page", "/board/boardlist.jsp");
		mav.setViewName("/omok/index");
		return mav;
	}
	@RequestMapping(value="boarddetail.board", method=RequestMethod.GET)
	public ModelAndView detail(String seq, String boardtype, String reply, @ModelAttribute("sessionGrade") String sessionGrade){
		ModelAndView mav = new ModelAndView();
		//글
		OBoardVO vo = boardDAO.getBoardDetail(Integer.parseInt(seq), boardtype);
		mav.addObject("vo", vo);
		//답글	
		OReplyVO repvo= repDAO.getBoardDetail(Integer.parseInt(seq), boardtype);

		mav.addObject("grade", sessionGrade);
		mav.addObject("reply", reply);
		mav.addObject("repvo", repvo);
		mav.addObject("page", "/board/boarddetail.jsp");
		mav.setViewName("/omok/index");
		return mav;
	}
	@RequestMapping(value="write.board", method=RequestMethod.GET)
	public ModelAndView write(String boardtype){
		ModelAndView mav = new ModelAndView();
		mav.addObject("boardtype", boardtype);
		mav.addObject("page", "/board/boardwrite.jsp");
		mav.setViewName("/omok/index");
		return mav;
	}
	@RequestMapping(value="write.board", method=RequestMethod.POST)
	public ModelAndView writeSuccess(@ModelAttribute("vo") OBoardVO vo, @ModelAttribute("sessionID") String sessionID){
		ModelAndView mav = new ModelAndView();
		vo.setBoardwriter(sessionID);
		int result = boardDAO.insertBoard(vo);
		mav.addObject("result", result);
		mav.addObject("page", "/board/boardwritesuccess.jsp");
		mav.setViewName("/omok/index");
		return mav;
	}
	@RequestMapping(value="update.board", method=RequestMethod.GET)
	public ModelAndView update(String seq, String boardtype){
		ModelAndView mav = new ModelAndView();
		boardVO = boardDAO.getBoardDetail(Integer.parseInt(seq), boardtype);		
		mav.addObject("vo", boardVO);
		mav.addObject("page", "/board/boardupdateform.jsp");
		mav.setViewName("/omok/index");
		return mav;
	}
	@RequestMapping(value="update.board", method=RequestMethod.POST)
	public ModelAndView update(@ModelAttribute("vo") OBoardVO vo, @ModelAttribute("sessionID") String sessionID){
		ModelAndView mav = new ModelAndView();
		vo.setBoardwriter(sessionID);
		int result=boardDAO.updateBoard(vo);
		mav.addObject("result", result);
		mav.addObject("page", "/board/boardupdatesuccess.jsp");
		mav.setViewName("/omok/index");
		return mav;
	}
	@RequestMapping(value="delete.board", method=RequestMethod.GET)
	public ModelAndView delete(String seq, String boardtype){
		ModelAndView mav = new ModelAndView();
		boardDAO.deleteBoard(Integer.parseInt(seq), boardtype);
		mav.addObject("boardtype",boardtype);
		mav.addObject("page", "/board/boarddeletesuccess.jsp");
		mav.setViewName("/omok/index");
		return mav;
	}
	@RequestMapping(value="reply.board", method=RequestMethod.GET)
	public ModelAndView reply(@ModelAttribute("seq") String seq, @ModelAttribute("boardtype") String boardtype){
		ModelAndView mav = new ModelAndView();
		mav.addObject("page", "/board/boardreply.jsp");
		mav.setViewName("/omok/index");
		return mav;
	}
	@RequestMapping(value="reply.board", method=RequestMethod.POST)
	public ModelAndView reply(@ModelAttribute("vo") OReplyVO repVO, @ModelAttribute("sessionID") String sessionID){
		ModelAndView mav = new ModelAndView();
		repVO.setReplywriter(sessionID);
		repVO.setReplyviewcount(0);
		int result  = repDAO.insertReply(repVO);
		mav.addObject("result", result);
		mav.addObject("page", "/board/boardreplysuccess.jsp");
		mav.setViewName("/omok/index");
		return mav;
	}
}
