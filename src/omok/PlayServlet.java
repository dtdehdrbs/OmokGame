package omok;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import omok.ORankDAO;
public class PlayServlet extends HttpServlet {

	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session = request.getSession(true);
		String sessionID = (String) session.getAttribute("sessionID");
		String menu = request.getParameter("menu");
		//���ӽ���
		if(menu.equals("play")){
			String userid = request.getParameter("userid");
			request.setAttribute("userid", userid);
			request.setAttribute("page", "/play/play.jsp");
			RequestDispatcher rd = request.getRequestDispatcher("/omok/index.jsp");
			rd.forward(request, response);
		}
		if(menu.equals("list")){
			RoomDAO dao = new RoomDAO();
			ArrayList<RoomVO> list = dao.selectRoom();
			
			request.setAttribute("list", list);
			request.setAttribute("page", "/play/list.jsp");
			RequestDispatcher rd = request.getRequestDispatcher("/omok/index.jsp");
			rd.forward(request, response);
		}
		if(menu.equals("create")){
			//create������ �̵��ϱ�
			request.setAttribute("page", "/play/create.jsp");
			RequestDispatcher rd = request.getRequestDispatcher("/omok/index.jsp");
			rd.forward(request, response);
		}
		if(menu.equals("createsuccess")){
			RoomDAO dao = new RoomDAO();
			//create������ �����޾ƿ���
			String userid = request.getParameter("userid");
			String prtitle = request.getParameter("prtitle");
			//DAO�� ó��
			RoomVO vo = new RoomVO();
			vo.setId(userid);
			vo.setPrtitle(prtitle);
			dao.insertRoom(vo);
			//�ٷ� ����ȣ���� ����Ʈ�̵�
			request.setAttribute("page", "/play/createsuccess.jsp");
			RequestDispatcher rd = request.getRequestDispatcher("/omok/index.jsp");
			rd.forward(request, response);
		}
		if(menu.equals("delete")){
			//�泪����
			RoomDAO room = new RoomDAO();
			PlayDAO dao = new PlayDAO();
			String userid = request.getParameter("userid");
			room.exitRoom(sessionID);
			room.exitRoom(userid);
			dao.deletePlay(sessionID);
			dao.deletePlay(userid);
			request.setAttribute("page", "/play/list.jsp");
			RequestDispatcher rd = request.getRequestDispatcher("/omok/index.jsp");
			rd.forward(request, response);
		}		
	}

	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session = request.getSession(true);
		response.setContentType("text/html;charset=utf-8");
		
		PrintWriter out = response.getWriter();
		
		String sessionID = (String) session.getAttribute("sessionID");
		String menu = request.getParameter("menu");
		
		if(menu.equals("sync")){
			PlayDAO dao = new PlayDAO();
			String userid = request.getParameter("userid");
			ArrayList<PlayVO> userlist = dao.selectPlay(userid);
			for(int i=0;i<userlist.size();i++){
				out.print(userlist.get(i).getPosX()+","+userlist.get(i).getPosY()+":");
			}
		}
		
		if(menu.equals("save")){
			PlayDAO dao = new PlayDAO();

			String userid = request.getParameter("userid");
			String order = request.getParameter("order");
			//����˰� DB�� ����
			String posX = request.getParameter("posX");
			String posY = request.getParameter("posY");
			String status = request.getParameter("status");			
			dao.insertPlay(sessionID,posX,posY,status);
			
			if(order.equals("checkBlack")){
				//����˰� �ҷ��ͼ� �������� üũ
				RoomDAO room = new RoomDAO();
				ORankDAO rank = new ORankDAO();
				PlayVO vo = dao.selectRecentPlay(sessionID, 1);
				int result = Play.checkWin(sessionID, 1 ,vo.getPosX(),vo.getPosY());
				if(result == 1){
					out.print("���� �¸��߽��ϴ�.");
					dao.deletePlay(sessionID);
					dao.deletePlay(userid);
					rank.winScore(sessionID);
					rank.loseScore(userid);
				}
			}
			if(order.equals("checkWhite")){
				//����˰� �ҷ��ͼ� �������� üũ
				RoomDAO room = new RoomDAO();
				ORankDAO rank = new ORankDAO();
				PlayVO vo = dao.selectRecentPlay(sessionID, 2);
				int result = Play.checkWin(sessionID, 2 ,vo.getPosX(),vo.getPosY());
				if(result == 1){
					out.print("���� �¸��߽��ϴ�.");
					dao.deletePlay(sessionID);
					dao.deletePlay(userid);
					rank.winScore(userid);
					rank.loseScore(sessionID);
				}
			}
		}
	}
	
	
	 
}
