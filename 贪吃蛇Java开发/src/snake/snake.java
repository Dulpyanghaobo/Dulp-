package snake;

import javax.swing.JFrame;

public class snake {
public static void main(String[] args) {
	/*
	 * ���屳����С
	 * */
	JFrame jfr = new JFrame();
	jfr.setBounds(10,10,920,700);
	//�����Ƿ��ܱ��ı�
	jfr.setResizable(false);
	//����رհ�ť��ʱ���˳���Ϸ
	jfr.setDefaultCloseOperation(jfr.EXIT_ON_CLOSE);

	
	snakepanel sp = new snakepanel();
	//��ӻ���
	jfr.add(sp);
	//��ʾ����
	jfr.setVisible(true);
}
}
