package snake;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.Random;

import javax.swing.ImageIcon;
import javax.swing.JPanel;
import javax.swing.Timer;
//���廭��
public class snakepanel extends JPanel implements KeyListener,ActionListener{
	ImageIcon body = new ImageIcon("body.png");
	ImageIcon down = new ImageIcon("down.png");
	ImageIcon food = new ImageIcon("food.png");
	ImageIcon left = new ImageIcon("left.png");
	ImageIcon right = new ImageIcon("right.png");
	ImageIcon title = new ImageIcon("title.jpg");
	ImageIcon up = new ImageIcon("up.png");
	
	int [] snakex=new int[750];
	int [] snakey=new int[750];
	//���ʳ���λ��
	Random rm = new Random();
	//�ܹ�һ����34�����ӣ��ܹ���25Ϊһ�����Ӻ����25��ʾ��һ��
	int foodx = rm.nextInt(34)*25+25 ;
	int foody = rm.nextInt(24)*25+75;
	int len = 4;
	int score = 0;
	
	String fangxiang = "R";//R��L��U��D��
	//��ƿ�ʼ��ť
	boolean isStarted = false;
	boolean isFailed = false;
	//������
	Timer timer=new Timer(50,this);
	//��ȡ����
	public snakepanel() {
		this.setFocusable(true);
		//�����Լ��ļ����¼�
		this.addKeyListener(this);
		initSetup();
		//�߿�ʼ��
		timer.start();
	}
	//ͼ�ε���˼����
	public void paint(Graphics g) {
		this.setBackground(Color.BLUE);
		title.paintIcon(this, g, 25, 11);
		//������
		//����Բ��g.fillOval(25, 75, 850, 600);
		g.fillRect(25, 75, 850, 600);
		//����ͷ
		if(fangxiang.equals("R")) {
			right.paintIcon(this, g, snakex[0],snakey[0]);
		}else if(fangxiang.equals("L")) {
			left.paintIcon(this, g, snakex[0],snakey[0]);
		}else if(fangxiang.equals("U")) {
			up.paintIcon(this, g, snakex[0],snakey[0]);
		}else if(fangxiang.equals("D")) {
			down.paintIcon(this, g, snakex[0],snakey[0]);
		}
		//���ߵ�����
		for(int i=1;i<len;i++) {
			body.paintIcon(this, g, snakex[i], snakey[i]);
		}
		//��ʾ��ʼ��ͣ
		if(!isStarted) {
			g.setColor(Color.WHITE);
			g.setFont(new Font("arial",Font.BOLD,30));
			g.drawString("Please Space to start/Pause", 280, 350);
		}
		if(isFailed) {
			g.setColor(Color.RED);
			g.setFont(new Font("arial",Font.BOLD,30));
			g.drawString("Game Over,Please Space to Restart", 280, 350);
		}
		//����ʳ��
		food.paintIcon(this, g, foodx, foody);
		g.setColor(Color.WHITE);
		g.setFont(new Font("arial",Font.BOLD,20));
		g.drawString("score: "+score, 750, 45);
		g.drawString("Length: "+len, 600, 45);
	}
	public void initSetup() {
		isStarted = false;
		isFailed = false;
		len = 4;
		snakex[0] =100; 
		snakex[1] =75;
		snakex[2] =50;
		snakex[3] =25;
		snakey[0] =100;
		snakey[1] =100;
		snakey[2] =100;
		snakey[3] =100;
		fangxiang = "R";
	}
	//��������
	@Override
	public void keyPressed(KeyEvent e) {
		int keyCode = e.getKeyCode();
		if(keyCode == KeyEvent.VK_SPACE) {
			if(isFailed) {
				initSetup();
			}else {
				isStarted = !isStarted;
			}
			
			//���»���(�����л�)
			repaint();
		}
		//��ͷ�ƶ�������ʵ�ֹ���
		else if(keyCode == KeyEvent.VK_UP && fangxiang != "D") {
			fangxiang = "U";
		} else if(keyCode == KeyEvent.VK_DOWN && fangxiang != "U") {
			fangxiang = "D";
		} else if(keyCode == KeyEvent.VK_LEFT && fangxiang != "R") {
			fangxiang = "L";
		} else if(keyCode == KeyEvent.VK_RIGHT && fangxiang != "L") {
			fangxiang = "R";
		}
	}
	@Override
	public void keyReleased(KeyEvent e) {
		
	}
	@Override
	public void keyTyped(KeyEvent e) {
		
	}
	@Override
	public void actionPerformed(ActionEvent e) {
		//����ʱ��(�������ػ�һ��)
		timer.start();
		//1.�ƶ�����
		//�ƶ�����
		if(isStarted && !isFailed) {
			for(int i=len;i>0;i--) {
				snakex[i]=snakex[i-1];
				snakey[i]=snakey[i-1];
			}
			//�ƶ�ͷ
			if(fangxiang.equals("R")) {
				snakex[0] = snakex[0]+25;
				if(snakex[0]>850) snakex[0] = 25;
			}else if(fangxiang.equals("L")) {
				snakex[0] = snakex[0]-25;
				if(snakex[0]<25) snakex[0] = 850;
			}else if(fangxiang.equals("U")) {
				snakey[0] = snakey[0]-25;
				if(snakey[0]<75) snakey[0] = 650;
			}else if(fangxiang.equals("D")) {
				snakey[0] = snakey[0]+25;
				if(snakey[0]>650) snakey[0] = 75;
			}
			//�Ե�ʳ��ӳ��ȶ���ʳ�����³���
			if(snakex[0]==foodx && snakey[0]==foody) {
				len++;
				score++;
				foodx = rm.nextInt(34)*25+25;
				foody = rm.nextInt(25)*25+75;
			}
			//��ҧ�����Լ�
			for(int i = 1;i<len;i++) {
				if(snakex[0]==snakex[i] &&snakey[0]==snakey[i]) {
					isFailed = true;
				}
			}
		}
		//2.repaint()
		repaint();
	}
}
