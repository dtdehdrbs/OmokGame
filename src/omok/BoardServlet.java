package omok;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public class BoardServlet extends HttpServlet {
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session= request.getSession(true);
		String sessionID=(String)session.getAttribute("sessionID");
		String sessionGrade=(String)session.getAttribute("sessionGrade");
		request.setCharacterEncoding("utf-8");
		response.setContentType("text/html;charset=utf-8");
		String menu = request.getParameter("menu");
		OBoardDAO dao = new OBoardDAO();
		OBoardVO vo = new OBoardVO();
		
		if(menu.equalsIgnoreCase("free")){
			int currentPage=1;
			if(request.getParameter("page") != null )
				currentPage = Integer.parseInt(request.getParameter("page"));
			int cntPerPage=5;
			int total = dao.getTotalCount("free");
			if(total % cntPerPage == 0){
				total = total / cntPerPage;
			}
			else{
				total = total / cntPerPage+1;
			}
			
			ArrayList<OBoardVO> list = dao.getFreeList(currentPage,cntPerPage);
			OReplyDAO repdao = new OReplyDAO();
			ArrayList<OReplyVO> replyList = repdao.getReplyList(menu);
			request.setAttribute("totalpage", total);
			request.setAttribute("list", list);
			request.setAttribute("replyList", replyList);
			request.setAttribute("page", "/board/boardlist.jsp");
			request.setAttribute("boardtype", "자유 게시판");
			request.setAttribute("boardtype2", "free");
			RequestDispatcher rd = request.getRequestDispatcher("/omok/index.jsp");
			rd.forward(request, response);
		}
		else if(menu.equalsIgnoreCase("notice")){
			int currentPage=1;
			if(request.getParameter("page") != null )
				currentPage = Integer.parseInt(request.getParameter("page"));
			int cntPerPage=5;
			int total = dao.getTotalCount("notice");
			if(total % cntPerPage == 0){
				total = total / cntPerPage;
			}
			else{
				total = total / cntPerPage+1;
			}
			
			OReplyDAO repdao = new OReplyDAO();
			ArrayList<OReplyVO> replyList = repdao.getReplyList(menu);
			ArrayList<OBoardVO> list = dao.getNoticeList(currentPage,cntPerPage);
			request.setAttribute("replyList", replyList);
			request.setAttribute("grade", sessionGrade);
			request.setAttribute("totalpage", total);
			request.setAttribute("list", list);
			request.setAttribute("page", "/board/boardlist.jsp");
			request.setAttribute("boardtype", "공지사항");
			request.setAttribute("boardtype2", "notice");
			RequestDispatcher rd = request.getRequestDispatcher("/omok/index.jsp");
			rd.forward(request, response);
		}
		else if(menu.equalsIgnoreCase("qna")){
			int currentPage=1;
			if(request.getParameter("page") != null )
				currentPage = Integer.parseInt(request.getParameter("page"));
			int cntPerPage=5;
			int total = dao.getTotalCount("qna");
			if(total % cntPerPage == 0){
				total = total / cntPerPage;
			}
			else{
				total = total / cntPerPage+1;
			}
			OReplyDAO repdao = new OReplyDAO();
			ArrayList<OReplyVO> replyList = repdao.getReplyList(menu);
			ArrayList<OBoardVO> list = dao.getQandAList(currentPage,cntPerPage);
			request.setAttribute("replyList", replyList);
			request.setAttribute("totalpage", total);
			request.setAttribute("list", list);
			request.setAttribute("page", "/board/boardlist.jsp");
			request.setAttribute("boardtype", "Q&A");
			request.setAttribute("boardtype2", "qna");
			RequestDispatcher rd = request.getRequestDispatcher("/omok/index.jsp");
			rd.forward(request, response);
		}
		else if(menu.equalsIgnoreCase("write")){
			String boardtype = request.getParameter("boardtype");
			request.setAttribute("boardtype", boardtype);
			request.setAttribute("page", "/board/boardwrite.jsp");
			RequestDispatcher rd = request.getRequestDispatcher("/omok/index.jsp");
			rd.forward(request, response);
		}
		else if(menu.equalsIgnoreCase("writesuccess")){
			vo.setBoardtitle(request.getParameter("boardtitle"));
			vo.setBoardwriter(sessionID);
			vo.setBoardcontents(request.getParameter("boardcontents"));
			vo.setBoardtype(request.getParameter("boardtype"));
			request.setAttribute("vo", vo);
			int result = dao.insertBoard(vo);
			request.setAttribute("result", result);
			request.setAttribute("page", "/board/boardwritesuccess.jsp");
			RequestDispatcher rd = request.getRequestDispatcher("/omok/index.jsp");
			rd.forward(request, response);
			
		}else if(menu.equals("detail")){
			OReplyVO repvo = null;
			OReplyDAO repdao = new OReplyDAO();	
			OInfoDAO dao1 = new OInfoDAO();
			OInfoVO vo1 = dao1.selectInfo(sessionID);
			String seq = request.getParameter("seq");			
			String boardtype = request.getParameter("boardtype");
			String reply = request.getParameter("reply");
			//글
			vo = dao.getBoardDetail(Integer.parseInt(seq), boardtype);
			request.setAttribute("vo", vo);
			//답글	
			repvo = repdao.getBoardDetail(Integer.parseInt(seq), boardtype);

			request.setAttribute("grade", vo1.getGrade());
			request.setAttribute("reply", reply);
			request.setAttribute("repvo", repvo);
			request.setAttribute("page", "/board/boarddetail.jsp");
			RequestDispatcher rd = request.getRequestDispatcher("/omok/index.jsp");
			rd.forward(request, response);
			
		}else if(menu.equals("delete")){
			String seq =request.getParameter("seq");
			String boardtype = request.getParameter("boardtype");
			dao.deleteBoard(Integer.parseInt(seq), boardtype);
			request.setAttribute("page", "/omok/home.jsp");
			RequestDispatcher rd = request.getRequestDispatcher("/omok/index.jsp");
			rd.forward(request, response);
			
		}else if(menu.equals("update")){			
			String seq =request.getParameter("seq");
			String boardtype = request.getParameter("boardtype");
			vo = dao.getBoardDetail(Integer.parseInt(seq), boardtype);		
			request.setAttribute("vo", vo);
			request.setAttribute("page", "/board/boardupdateform.jsp");
			RequestDispatcher rd = request.getRequestDispatcher("/omok/index.jsp");
			rd.forward(request, response);
			
		}else if(menu.equals("updatesuccess")){
			
			String seq=request.getParameter("seq");
			String boardtitle = request.getParameter("boardtitle");
			String boardcontents = request.getParameter("boardcontents");
			String boardtype = request.getParameter("type");
			vo.setBoardtype(boardtype);
			vo.setBoardseq(Integer.parseInt(seq));
			vo.setBoardtitle(boardtitle);
			vo.setBoardcontents(boardcontents);
			int result=dao.updateBoard(vo);
			request.setAttribute("result", result);
			request.setAttribute("vo", vo);
			request.setAttribute("page", "/board/boardupdatesuccess.jsp");
			RequestDispatcher rd = request.getRequestDispatcher("/omok/index.jsp");
			rd.forward(request, response);
			
		}
		else if(menu.equals("reply")){
			String seq= request.getParameter("seq");
			String boardtype= request.getParameter("boardtype");
			request.setAttribute("boardseq", seq);
			request.setAttribute("boardtype", boardtype);			
			request.setAttribute("page", "/board/boardreply.jsp");
			RequestDispatcher rd = request.getRequestDispatcher("/omok/index.jsp");
			rd.forward(request, response);
			
		}
		else if(menu.equals("replysuccess")){
			int seq = Integer.parseInt(request.getParameter("replyseq"));
			String type = request.getParameter("replytype");
			String title = request.getParameter("replytitle");
			String contents = request.getParameter("replycontents");
			
			
			OReplyVO repvo = new OReplyVO(seq, title, contents, sessionID, 0, null, type);
			OReplyDAO repdao = new OReplyDAO();
			int result  = repdao.insertReply(repvo);
			request.setAttribute("result", result);
			request.setAttribute("vo", repvo);
			
			request.setAttribute("page", "/board/boardreplysuccess.jsp");
			RequestDispatcher rd = request.getRequestDispatcher("/omok/index.jsp");
			rd.forward(request, response);
			
		}
	}

}
