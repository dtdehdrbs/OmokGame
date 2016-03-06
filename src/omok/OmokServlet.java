package omok;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import board.OBoardDAO;
import board.OBoardVO;

public class OmokServlet extends HttpServlet {
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session = request.getSession(true);
		request.setCharacterEncoding("utf-8");
		response.setContentType("text/html;charset=utf-8");
		String sessionID = (String) session.getAttribute("sessionID");
		String sessionGrade = (String) session.getAttribute("sessionGrade");
		String menu = request.getParameter("menu");
		if(sessionID != null){//������ ������ ����			
			if(menu != null){//�޴��Ķ���� �ְ� �α��� �����϶�(��������)
				if(menu.equals("logout")){//�α׾ƿ� ����(�α׾ƿ��� �α���ȭ������ �̵�)
					session.invalidate();
					OBoardDAO dao = new OBoardDAO();
					OInfoDAO dao2 = new OInfoDAO();
					ArrayList<OBoardVO> list1 = dao.getFreeList();
					ArrayList<OBoardVO> list2= dao.getNoticeList();
					ArrayList<ORankVO> list3= dao2.selectRank();
					
					request.setAttribute("free", list1);
					request.setAttribute("notice", list2);
					request.setAttribute("rank", list3);
					RequestDispatcher rd = request.getRequestDispatcher("/omok/index.jsp");
					request.setAttribute("page", "home.jsp");
					rd.forward(request, response);			
				}
				if(menu.equals("rank")){//�α׾ƿ� ����(�α׾ƿ��� �α���ȭ������ �̵�)
					OInfoDAO dao = new OInfoDAO();
					ArrayList<ORankVO> list = dao.selectRank();
					request.setAttribute("list", list);
					RequestDispatcher rd = request.getRequestDispatcher("/omok/index.jsp");
					request.setAttribute("page", "omokrank.jsp");
					rd.forward(request, response);			
				}
				if(menu.equals("mypage")){//ȸ������������(����������)
					OInfoDAO dao =new OInfoDAO();
					OInfoVO vo = dao.selectInfo(sessionID);
					request.setAttribute("vo", vo);
					RequestDispatcher rd = request.getRequestDispatcher("/omok/index.jsp");
					request.setAttribute("page", "mypage.jsp");
					rd.forward(request, response);			
				}
				if(menu.equals("update")){//ȸ������
					OInfoDAO dao =new OInfoDAO();
					OInfoVO vo = dao.selectInfo(sessionID);
					if(sessionGrade.equals("������")){
						String id = request.getParameter("id");
						OInfoVO vo1 = dao.selectInfo(id);
						request.setAttribute("vo", vo1);
					}else{
						request.setAttribute("vo", vo);
					}
					RequestDispatcher rd = request.getRequestDispatcher("/omok/index.jsp");
					request.setAttribute("page", "omokupdateform.jsp");
					rd.forward(request, response);			
				}
				
				if(menu.equals("delete")){//�α׾ƿ� ����(�α׾ƿ��� �α���ȭ������ �̵�)
					OBoardDAO dao = new OBoardDAO();
					OInfoDAO dao2 = new OInfoDAO();
					ArrayList<OBoardVO> list1 = dao.getFreeList();
					ArrayList<OBoardVO> list2= dao.getNoticeList();
					ArrayList<ORankVO> list3= dao2.selectRank();
					
					request.setAttribute("free", list1);
					request.setAttribute("notice", list2);
					request.setAttribute("rank", list3);
					if(sessionGrade.equals("������")){
						String id = request.getParameter("id");
						dao2.deleteInfo(id);
					}else{
						dao2.deleteInfo(sessionID);
						session.invalidate();
					}
					RequestDispatcher rd = request.getRequestDispatcher("/omok/index.jsp");
					request.setAttribute("page", "home.jsp");
					rd.forward(request, response);			
				}
				if(menu.equals("infosearch")){//ȸ������������(��ü ȸ��)
					RequestDispatcher rd = request.getRequestDispatcher("/omok/index.jsp");
					request.setAttribute("page", "infosearch.jsp");
					rd.forward(request, response);			
				}
				
				if(menu.equals("infolist")){
					OInfoDAO dao =new OInfoDAO();
					OInfoVO vo = new OInfoVO();
					String searchtype = request.getParameter("searchtype");
					String searchcontent = request.getParameter("searchcontent");
					if(searchtype.equals("id")){
						vo.setId(searchcontent);
					}
					if(searchtype.equals("name")){
						vo.setName(searchcontent);
					}
					if(searchtype.equals("grade")){
						vo.setGrade(searchcontent);
					}
					if(searchtype.equals("email")){
						vo.setEmail(searchcontent);
					}
					if(searchtype.equals("indate")){
						vo.setIndate(searchcontent);
					}
					ArrayList<OInfoVO> list = dao.totalInfo(vo);
					request.setAttribute("oinfolist", list);
					RequestDispatcher rd = request.getRequestDispatcher("/omok/index.jsp");
					request.setAttribute("page", "infolist.jsp");
					rd.forward(request, response);	
				}
				
				if(menu.equals("infodetail")){//ȸ������������(��ڿ�)
					OInfoDAO dao =new OInfoDAO();
					String id = request.getParameter("id");
					OInfoVO vo = dao.selectInfo(id);
					request.setAttribute("vo", vo);
					RequestDispatcher rd = request.getRequestDispatcher("/omok/index.jsp");
					request.setAttribute("page", "infodetail.jsp");
					rd.forward(request, response);			
				}
			}else{ //�޴� �Ķ���ʹ� ���� �α��� �����϶�(��������)
				OBoardDAO dao = new OBoardDAO();
				OInfoDAO dao2 = new OInfoDAO();
				OInfoVO vo1 = dao2.selectInfo(sessionID);
				ArrayList<OBoardVO> list1 = dao.getFreeList();
				ArrayList<OBoardVO> list2= dao.getNoticeList();
				ArrayList<ORankVO> list3= dao2.selectRank();
				
				request.setAttribute("free", list1);
				request.setAttribute("notice", list2);
				request.setAttribute("rank", list3);
				request.setAttribute("grade", vo1.getGrade());
				RequestDispatcher rd = request.getRequestDispatcher("/omok/index.jsp");
				request.setAttribute("page", "home.jsp");
				rd.forward(request, response);
			}
			
		}else{//������ ������ ����
			if(menu == null){//ù����ȭ��(�޴��Ķ���Ͱ� ������ )
				OBoardDAO dao = new OBoardDAO();
				OInfoDAO dao2 = new OInfoDAO();
				ArrayList<OBoardVO> list1 = dao.getFreeList();
				ArrayList<OBoardVO> list2= dao.getNoticeList();
				ArrayList<ORankVO> list3= dao2.selectRank();
				
				request.setAttribute("free", list1);
				request.setAttribute("notice", list2);
				request.setAttribute("rank", list3);
				
				RequestDispatcher rd = request.getRequestDispatcher("/omok/index.jsp");
				request.setAttribute("page", "home.jsp");
				rd.forward(request, response);
			}
			else if(menu.equals("login")){//�α���(�޴��Ķ���Ͱ� login �ϋ�)
				RequestDispatcher rd = request.getRequestDispatcher("/omok/index.jsp");
				request.setAttribute("page", "omoklogin.jsp");
				rd.forward(request, response);
			}
			else if(menu.equals("join")){//ȸ������(�޴��Ķ���Ͱ� join �ϋ�)
				RequestDispatcher rd = request.getRequestDispatcher("/omok/index.jsp");
				request.setAttribute("page", "omokjoin.jsp");
				rd.forward(request, response);
			}
			
		}
		
	}

	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session = request.getSession(true);
		String sessionID = (String) session.getAttribute("sessionID");
		String sessionGrade = (String) session.getAttribute("sessionGrade");
		String menu = request.getParameter("menu");
		request.setCharacterEncoding("utf-8");
		response.setContentType("text/html;charset=utf-8");
		if(sessionID != null){//������ ������ ����	
			if(menu.equals("updatesuccess")){//ȸ������
				OInfoDAO dao =new OInfoDAO();
				if(sessionGrade.equals("������")){
					String id = request.getParameter("id");
					String pw= request.getParameter("pw");
					String name= request.getParameter("name");
					String email= request.getParameter("email");
					String phone= request.getParameter("phone");
					String birth= request.getParameter("birth");
					OInfoVO vo = new OInfoVO(id, pw, name, email, phone, birth,"" );
					dao.updateInfo(vo);
					request.setAttribute("id",id);
				}else{
					String pw= request.getParameter("pw");
					String name= request.getParameter("name");
					String email= request.getParameter("email");
					String phone= request.getParameter("phone");
					String birth= request.getParameter("birth");
					OInfoVO vo = new OInfoVO(sessionID, pw, name, email, phone, birth, "");
					dao.updateInfo(vo);
				}
				RequestDispatcher rd = request.getRequestDispatcher("/omok/omokupdatesuccess.jsp");
				rd.forward(request, response);
			}
		}else{//������ ������ ����
			if(menu.equals("loginsuccess")){//�޴��Ķ���Ͱ� loginsuccess �϶� ����
				OInfoDAO dao1 = new OInfoDAO();
				String id = request.getParameter("id");
				String pw = request.getParameter("pw");				
				OInfoDAO dao = new OInfoDAO();
				int result = dao.selectID(id, pw);
				if(result == 0){//�α��� ����(���ǵ�� �� ������������ �̵�)
					session.setAttribute("sessionID", id);
					OInfoVO vo1 = dao1.selectInfo(id);
					session.setAttribute("sessionGrade", vo1.getGrade());
					RequestDispatcher rd = request.getRequestDispatcher("/omok/omokmain.jsp");
					rd.include(request, response);			
				}else{//�α��� ����(���� ��Ͼ��� �α��� ������������ �̵�) 
					RequestDispatcher rd = request.getRequestDispatcher("/omok/omokloginfail.jsp");
					rd.forward(request, response);	
				}
			}
			if(menu.equals("joinsuccess")){//�޴��Ķ���Ͱ� joinsuccess �϶� ����
				String id = request.getParameter("id");
				String pw = request.getParameter("pw");	
				String name = request.getParameter("name");
				String email = request.getParameter("email");
				String birth = request.getParameter("birth");
				String phone = request.getParameter("phone");
				OInfoVO vo = new OInfoVO(id, pw, name, email, phone, birth, "�Ϲ�ȸ��");
				OInfoDAO dao = new OInfoDAO();
				int result = dao.insertInfo(vo);				
				if(result == 1){//ȸ������ ����(���ǵ�� �� ������������ �̵�)
					session.setAttribute("sessionID", id);
					RequestDispatcher rd = request.getRequestDispatcher("/omok/omokjoinsuccess.jsp");
					rd.forward(request, response);		
				}else{//ȸ������ ����(���� ��Ͼ��� �α��� ������������ �̵�) 
					RequestDispatcher rd = request.getRequestDispatcher("/omok/omokjoinfail.jsp");
					rd.forward(request, response);	
				}
			}
			
		}
	}
	

}

