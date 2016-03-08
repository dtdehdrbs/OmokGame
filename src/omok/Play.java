package omok;

import java.util.ArrayList;

public class Play {
	public final static int checkWin(String id,int status, int row, int col){
		int arr[][] = new int[20][20];
		PlayDAO dao = new PlayDAO();
		ArrayList<PlayVO> list = dao.selectPlay(id);
		for(int i=0; i<arr.length; i++){//모든 위치에 0으로 초기화
			for(int j=0; j<arr[i].length; j++){
				arr[i][j] = 0;				
			}
		}
		for(int i=0; i < list.size(); i++){
			PlayVO vo = list.get(i);//특정위치에 돌의 상태 입력
			arr[vo.getPosX()][vo.getPosY()]=vo.getStatus();
		}
		//row,col 검사할 돌의 위치
		int count = 0;//현재 돌의 위치에서 세로줄 검사
		for(int i=0; i<20; i++){
			if(arr[row][i] == status) count++;
			else count =0;
			if(count == 5) return 1;
		}
		count = 0;
		for(int i=0; i<20; i++){//현재돌의위치에서 가로줄 검사
			if(arr[i][col] == status) count++;
			else count =0;
			if(count == 5) return 1;
		}
		int srow, scol;//왼쪽 위 대각선 시작 위치
		int erow, ecol;//오른쪽 아래 대각선 끝 위치
		int tr, tc;
		tr= row; tc= col;
		//왼쪽 위대각선 시작위치 찾기
		while(tr != 0 && tc != 0){
			tr--;
			tc--;
		}
		srow = tr; scol = tc;
		
		//오른쪽 아래대각선 끝 위치 찾기
		tr= row; tc= col;
		while(tr != 0 && tc != 0){
			tr--;
			tc--;
		}
		erow = tr; ecol = tc;
		//왼쪽위대각선-오른쪽아래대각선 검사
		count=0;
		while(scol <= ecol && srow <= erow){
			if(arr[srow][scol] == status) count++;
			else count =0;
			if(count == 5) return 1;
			srow++;
			scol++;
		}
		
		tr= row; tc= col;
		//왼쪽 아래대각선 시작위치 찾기
		while(tr != 0 && tc != 19){
			tr--;
			tc++;
		}
		srow = tr; scol = tc;
		
		//오른쪽 위대각선 끝 위치 찾기
		tr= row; tc= col;
		while(tr != 19 && tc != 0){
			tr++;
			tc--;
		}
		erow = tr; ecol = tc;
		//왼쪽아래대각선-오른쪽위대각선 검사
		count=0;
		while(scol >= ecol && srow <= erow){
			if(arr[srow][scol] == status) count++;
			else count =0;
			if(count == 5) return 1;
			srow++;
			scol--;
		}
		
		return 0;//오목이 아닌경우
	}
}
