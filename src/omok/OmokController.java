package omok;

import java.util.ArrayList;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.bind.support.SessionStatus;
import org.springframework.web.servlet.ModelAndView;


@Controller
@SessionAttributes({"sessionID","sessionGrade"})
public class OmokController {	
	@Autowired
	OBoardDAO boardDAO;
	@Autowired
	OBoardVO boardVO;
	@Autowired
	OInfoDAO infoDAO;
	@Autowired
	OInfoVO infoVO;
	
	/********************** 세션이 있을때 동작 *****************************/
	
	@RequestMapping(value="logout.omok",method=RequestMethod.GET)
	public ModelAndView logout(SessionStatus session){
		ModelAndView mav = new ModelAndView();
		session.setComplete();
		ArrayList<OBoardVO> freeBoard = boardDAO.getFreeList();
		ArrayList<OBoardVO> noticeBoard = boardDAO.getNoticeList();
		ArrayList<ORankVO> rankList = infoDAO.selectRank();
		
		mav.addObject("free", freeBoard);
		mav.addObject("notice", noticeBoard);
		mav.addObject("rank", rankList);	
		mav.addObject("page", "omoklogoutsuccess.jsp");
		mav.setViewName("index");
		return mav;			
	}
	@RequestMapping(value="rank.omok",method=RequestMethod.GET)
	public ModelAndView rank(){
		ModelAndView mav = new ModelAndView();
		ArrayList<ORankVO> list = infoDAO.selectRank();
		mav.addObject("list", list);
		mav.addObject("page", "omokrank.jsp");
		mav.setViewName("index");
		return mav;			
	}
	@RequestMapping(value="mypage.omok",method=RequestMethod.GET)
	public ModelAndView mypage(@ModelAttribute("sessionID") String sessionID){
		ModelAndView mav = new ModelAndView();
		infoVO = infoDAO.selectInfo(sessionID);
		mav.addObject("vo",infoVO);
		mav.addObject("page", "mypage.jsp");
		mav.setViewName("index");
		return mav;			
	}

	@RequestMapping(value="update.omok",method=RequestMethod.GET)
	public ModelAndView update(String id, @ModelAttribute("sessionID") String sessionID,@ModelAttribute("sessionGrade") String sessionGrade){
		ModelAndView mav = new ModelAndView();
		if(sessionGrade.equals("관리자")){
			infoVO = infoDAO.selectInfo(id);
		}else{
			infoVO = infoDAO.selectInfo(sessionID);
		}
		mav.addObject("vo", infoVO);
		mav.addObject("page", "omokupdateform.jsp");
		mav.setViewName("index");
		return mav;			
	}
	
	@RequestMapping(value="delete.omok",method=RequestMethod.GET)
	public ModelAndView delete(String id, SessionStatus session, @ModelAttribute("sessionID") String sessionID,@ModelAttribute("sessionGrade") String sessionGrade){
		ModelAndView mav = new ModelAndView();
		ArrayList<OBoardVO> freeBoard = boardDAO.getFreeList();
		ArrayList<OBoardVO> noticeBoard = boardDAO.getNoticeList();
		ArrayList<ORankVO> rankList = infoDAO.selectRank();
		
		mav.addObject("free", freeBoard);
		mav.addObject("notice", noticeBoard);
		mav.addObject("rank", rankList);
		
		if(sessionGrade.equals("관리자")){
			infoDAO.deleteInfo(id);
		}else{
			infoDAO.deleteInfo(sessionID);
			session.setComplete();
		}
		mav.addObject("page", "home.jsp");
		mav.setViewName("index");
		return mav;
	}
	
	@RequestMapping(value="infosearch.omok",method=RequestMethod.GET)
	public ModelAndView infosearch(){
		ModelAndView mav = new ModelAndView();
		mav.addObject("page", "infosearch.jsp");
		mav.setViewName("index");
		return mav;
	}
	
	@RequestMapping(value="infolist.omok",method=RequestMethod.GET)
	public ModelAndView infolist(String searchtype, String searchcontent){
		ModelAndView mav = new ModelAndView();
		if(searchtype.equals("id")){
			infoVO.setId(searchcontent);
		}
		else if(searchtype.equals("name")){
			infoVO.setName(searchcontent);
		}
		else if(searchtype.equals("grade")){
			infoVO.setGrade(searchcontent);
		}
		else if(searchtype.equals("email")){
			infoVO.setEmail(searchcontent);
		}
		else if(searchtype.equals("indate")){
			infoVO.setIndate(searchcontent);
		}
		ArrayList<OInfoVO> list = infoDAO.totalInfo(infoVO);
		mav.addObject("oinfolist", list);
		mav.addObject("page", "infosearch.jsp");
		mav.setViewName("index");
		return mav;
	}
	
	@RequestMapping(value="infodetail.omok",method=RequestMethod.GET)
	public ModelAndView infodetail(String id){
		ModelAndView mav = new ModelAndView();
		
		infoVO = infoDAO.selectInfo(id);
		
		mav.addObject("vo", infoVO);
		mav.addObject("page", "infodetail.jsp");
		mav.setViewName("index");
		return mav;
	}
	
	@RequestMapping(value="begin.omok",method=RequestMethod.GET)
	public ModelAndView begin(String id, @ModelAttribute("sessionID") String sessionID){
		ModelAndView mav = new ModelAndView();
		infoVO = infoDAO.selectInfo(sessionID);
		ArrayList<OBoardVO> freeBoard = boardDAO.getFreeList();
		ArrayList<OBoardVO> noticeBoard = boardDAO.getNoticeList();
		ArrayList<ORankVO> rankList = infoDAO.selectRank();
		
		mav.addObject("free", freeBoard);
		mav.addObject("notice", noticeBoard);
		mav.addObject("rank", rankList);
		mav.addObject("grade", infoVO.getGrade());
		
		mav.addObject("page", "home.jsp");
		mav.setViewName("index");
		return mav;
	}
	
	/********************** 세션이 있을때 동작 *****************************/
	


	/********************** 세션이 없을때 동작 *****************************/

	@RequestMapping(value="begin2.omok",method=RequestMethod.GET)
	public ModelAndView begin2(){
		ModelAndView mav = new ModelAndView();
		
		ArrayList<OBoardVO> freeBoard = boardDAO.getFreeList();
		ArrayList<OBoardVO> noticeBoard = boardDAO.getNoticeList();
		ArrayList<ORankVO> rankList = infoDAO.selectRank();
		
		mav.addObject("free", freeBoard);
		mav.addObject("notice", noticeBoard);
		mav.addObject("rank", rankList);
		
		mav.addObject("page", "home.jsp");
		mav.setViewName("index");
		return mav;
	}
	
	@RequestMapping(value="login.omok",method=RequestMethod.GET)
	public ModelAndView login(){
		ModelAndView mav = new ModelAndView();
		
		mav.addObject("page", "omoklogin.jsp");
		mav.setViewName("index");
		return mav;
	}

	@RequestMapping(value="join.omok",method=RequestMethod.GET)
	public ModelAndView join(){
		ModelAndView mav = new ModelAndView();
		mav.addObject("page", "omokjoin.jsp");
		mav.setViewName("index");
		return mav;
	}

	/********************** 세션이 없을때 동작 ****************************GET*/
	
	/********************** 세션이 있을때 동작 ****************************POST*/
	@RequestMapping(value="updatesuccess.omok",method=RequestMethod.POST)
	public ModelAndView updatesuccess(OInfoVO vo , @ModelAttribute("sessionID") String sessionID, @ModelAttribute("sessionGrade") String sessionGrade){
		ModelAndView mav = new ModelAndView();
		
		if(sessionID.equals("관리자")){
			infoDAO.updateInfo(vo);
			mav.addObject("id", vo.getId());
		}else{
			vo.setId(sessionID);
			infoDAO.updateInfo(vo);
		}
		mav.addObject("page", "omokupdatesuccess.jsp");
		mav.setViewName("index");
		return mav;
	}	

	/********************** 세션이 있을때 동작 *****************************/
	
	/********************** 세션이 없을때 동작 *****************************/
	
	@RequestMapping(value="loginsuccess.omok",method=RequestMethod.POST)
	public ModelAndView loginsuccess(String id, String pw, Model model){
		ModelAndView mav = new ModelAndView();
		int result = infoDAO.selectID(id, pw);
		if(result == 0){
			infoVO = infoDAO.selectInfo(id);
			model.addAttribute("sessionID", id);
			model.addAttribute("sessionGrade", infoVO.getGrade());
			mav.setViewName("omokmain");
		}else{
			mav.setViewName("omokloginfail");			
		}		
		return mav;
	}
	
	@RequestMapping(value="joinsuccess.omok",method=RequestMethod.POST)
	public ModelAndView joinsuccess(OInfoVO vo, Model model){
		ModelAndView mav = new ModelAndView();
		vo.setGrade("일반회원");
		int result = infoDAO.insertInfo(vo);
		if(result == 0){
			mav.setViewName("omokjoinfail");
		}else{
			model.addAttribute("sessionID", vo.getId());
			mav.setViewName("omokjoinsuccess");
		}
		return mav;
	}
}
