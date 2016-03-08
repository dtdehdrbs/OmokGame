package omok;

import java.util.ArrayList;

public class Play {
	public final static int checkWin(String id,int status, int row, int col){
		int arr[][] = new int[20][20];
		PlayDAO dao = new PlayDAO();
		ArrayList<PlayVO> list = dao.selectPlay(id);
		for(int i=0; i<arr.length; i++){//��� ��ġ�� 0���� �ʱ�ȭ
			for(int j=0; j<arr[i].length; j++){
				arr[i][j] = 0;				
			}
		}
		for(int i=0; i < list.size(); i++){
			PlayVO vo = list.get(i);//Ư����ġ�� ���� ���� �Է�
			arr[vo.getPosX()][vo.getPosY()]=vo.getStatus();
		}
		//row,col �˻��� ���� ��ġ
		int count = 0;//���� ���� ��ġ���� ������ �˻�
		for(int i=0; i<20; i++){
			if(arr[row][i] == status) count++;
			else count =0;
			if(count == 5) return 1;
		}
		count = 0;
		for(int i=0; i<20; i++){//���絹����ġ���� ������ �˻�
			if(arr[i][col] == status) count++;
			else count =0;
			if(count == 5) return 1;
		}
		int srow, scol;//���� �� �밢�� ���� ��ġ
		int erow, ecol;//������ �Ʒ� �밢�� �� ��ġ
		int tr, tc;
		tr= row; tc= col;
		//���� ���밢�� ������ġ ã��
		while(tr != 0 && tc != 0){
			tr--;
			tc--;
		}
		srow = tr; scol = tc;
		
		//������ �Ʒ��밢�� �� ��ġ ã��
		tr= row; tc= col;
		while(tr != 0 && tc != 0){
			tr--;
			tc--;
		}
		erow = tr; ecol = tc;
		//�������밢��-�����ʾƷ��밢�� �˻�
		count=0;
		while(scol <= ecol && srow <= erow){
			if(arr[srow][scol] == status) count++;
			else count =0;
			if(count == 5) return 1;
			srow++;
			scol++;
		}
		
		tr= row; tc= col;
		//���� �Ʒ��밢�� ������ġ ã��
		while(tr != 0 && tc != 19){
			tr--;
			tc++;
		}
		srow = tr; scol = tc;
		
		//������ ���밢�� �� ��ġ ã��
		tr= row; tc= col;
		while(tr != 19 && tc != 0){
			tr++;
			tc--;
		}
		erow = tr; ecol = tc;
		//���ʾƷ��밢��-���������밢�� �˻�
		count=0;
		while(scol >= ecol && srow <= erow){
			if(arr[srow][scol] == status) count++;
			else count =0;
			if(count == 5) return 1;
			srow++;
			scol--;
		}
		
		return 0;//������ �ƴѰ��
	}
}
