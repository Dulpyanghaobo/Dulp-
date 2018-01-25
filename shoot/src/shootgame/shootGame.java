package shootgame;
/*
 * ͼƬ����
 * 
 * */


import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.lang.reflect.Array;
import java.util.Arrays;
import java.util.Random;
import java.util.Timer;
import java.util.TimerTask;

import javax.imageio.ImageIO;
import javax.swing.JFrame;
import javax.swing.JPanel;


public class shootGame extends JPanel{
public static final int WIDTH = 400;//����
public static final int HEIGHT = 650;//����
public static BufferedImage background;
public static BufferedImage start;
public static BufferedImage airplane;
public static BufferedImage bee;
public static BufferedImage bullet;
public static BufferedImage hero0;
public static BufferedImage hero1;
public static BufferedImage pause;
public static BufferedImage gameover;

private FlyingObject[] flyings = {};//�л�����
private Bullet[] bullets = {};//�ӵ�����
private Hero hero = new Hero();//Ӣ�ۻ�
int flyEnteredIndex = 0;//�������볡����
private Timer timer; //��ʱ��
private int intervel =1000/100;//ʱ����
int shootIndex = 0;//�������
private int score =0;//�÷�
private int state;
public static final int START =0;
public static final int RUNNING =1;
public static final int PAUSE =2;
public static final int GAME_OVER =3;

public shootGame() {
	//��ʼ��һֻ�۷��һ�ܷɻ�
	flyings = new FlyingObject[2];
	flyings[0] =new AirPlane();
	flyings[1] =new Bee();
	//��ʼ��һ���ӵ�
	bullets = new Bullet[1];
	bullets[0] =new Bullet(200,350);
}
@Override
public void paint(Graphics g) {
	g.drawImage(background, 0, 0, null);//������ͼ
	paintHero(g);//��Ӣ�ۻ�
	paintBullets(g);//���ӵ�
	paintFlyingObjects(g);//��������
	paintScore(g);//������
	paintState(g);
}
//����ִ�д���
public void action() {
	timer = new Timer();
	timer.schedule(new TimerTask() {
		@Override
		public void run() {
			enterAction();//�������볡
			stepAction();//��һ��
			repaint();//�ػ����paint����
			shootAction();//�����ӵ�����
			bangAction();
			outOfBoundsAction();//ɾ��Խ���������ӵ�
			checkGameOverAction();//�����Ϸ״̬
			}
	},intervel,intervel);
	//�������¼�
	MouseAdapter ma = new MouseAdapter() {
		@Override
		public void mouseMoved(MouseEvent e) {//����ƶ�
			if(state == RUNNING) {//����ʱ�ƶ����л�
			int x = e.getX();
			int y = e.getY();
			hero.moveTo(x,y);
		}
			}
		@Override
		public void mouseEntered(MouseEvent e) {//������
			if(state == PAUSE) {
				state = RUNNING;
			}
		}
		@Override
		public void mouseExited(MouseEvent e) {//����˳�
			if(state != GAME_OVER) {
				state =PAUSE;//��Ϸδ������������Ϊ��ͣ
			}
			
		}
		@Override
		public void mouseClicked(MouseEvent e) {//�����
			switch(state) {
			case START:
				state = RUNNING;
				break;
			case GAME_OVER://��Ϸ���������ֳ�
				flyings =new FlyingObject[0];
				bullets = new Bullet[0];
				hero = new Hero();
				score = 0;
				state = START;
				break;
			}
		}
		
	};
	this.addMouseListener(ma);//�������������
	this.addMouseMotionListener(ma);//������껬���Ĳ���
}
//��������
private void paintFlyingObjects(Graphics g) {
	for(int i=0;i<flyings.length;i++) {
		FlyingObject fo = flyings[i];
		g.drawImage(fo.getImage(), fo.getX(), fo.getY(), null);
	}
}
//���ӵ�
private void paintBullets(Graphics g) {
	for(int i =0; i<bullets.length;i++) {
		Bullet b = bullets[i];
		g.drawImage(b.getImage(), b.getX(), b.getY(), null);
	}
}
//��Ӣ�ۻ�
private void paintHero(Graphics g) {
	g.drawImage(hero.getImage(), hero.getX(), hero.getY(), null);
}
/*������*/
public void paintScore(Graphics g) {
	int x = 10;
	int y = 25;
	Font font = new Font(Font.SANS_SERIF,Font.BOLD, 14);
	g.setColor(new Color(0x3A3B3B));//������ɫ
	g.setFont(font);//��������
	g.drawString("SCORE"+score, x, y);//������
	y+=20;
	g.drawString("LIFE:"+hero.getLife(), x, y);//����
}
/*������Ϸ״̬*/
public void paintState(Graphics g) {
	switch(state) {
	case START:
		g.drawImage(start, 0, 0, null);
		break;
	case PAUSE:
		g.drawImage(pause, 0, 0, null);
		break;
	case GAME_OVER:
		g.drawImage(gameover, 0, 0,null);
		break;
	}
}
/*
 * ������ɷ�����
 * */
public static FlyingObject nextOne() {
	Random rand = new Random();
	int type = rand.nextInt(20);//ֻ�е������Ϊ0��ʱ����������۷�
	if(type==0) {
		return new Bee();
	}else {
	return new AirPlane();
}
	}
/*�������볡*/
public void enterAction() {
	flyEnteredIndex++;
	if(flyEnteredIndex % 40 == 0) {//400����--10*40
		FlyingObject obj = nextOne();
		flyings = Arrays.copyOf(flyings, flyings.length+1);//����
		flyings[flyings.length-1] =obj;//�������һλ
	}
}
//ʵ�����еķ������ƶ�
public void stepAction() {
	//��������һ��
	for(int i =0;i<flyings.length;i++) {
		FlyingObject f = flyings[i];
		f.step();
	}
	//�ӵ���һ��
	for(int i =0;i<bullets.length;i++) {
		Bullet b = bullets[i];
		b.step();
	}
	hero.step();
}
//ÿ����30�η���һ���ӵ��ӵ�����bullet[]����
public void shootAction() {
	shootIndex++;
	if(shootIndex%30==0) {//ÿ30���뷢��һ���ӵ�
		Bullet[]bs=hero.shoot();
		bullets = Arrays.copyOf(bullets, bullets.length+bs.length);//��ӵ��ӵ�������
		System.arraycopy(bs, 0, bullets, bullets.length-bs.length, bs.length);//׷������
	}
}
/*
 * �ӵ��ͷ�������ײ���
 * */
public void bangAction() {
	for(int i =0;i<bullets.length;i++) {//�������е��ӵ�
		Bullet b =bullets[i];
		bang(b);
	}
}
/*
 * ɾ��Խ��ķ�������ӵ�
 * */
public void outOfBoundsAction() {
	int index = 0;
	//������ŵķ�����
	FlyingObject [] flyingLives = new FlyingObject[flyings.length];
	for(int i=0;i<flyings.length;i++) {
		FlyingObject f =flyings[i];
		if(!f.outOfBounds()) {
			flyingLives[index++]=f;//�����������
			
		}
	}
	flyings = Arrays.copyOf(flyingLives, index);//����Խ��ķ���������
	index = 0;//����Ϊ0
	Bullet[] bulletLives = new Bullet[bullets.length];
	for(int i =0;i<bullets.length;i++) {
		Bullet b =bullets[i];
		if(!b.outOfBounds()) {
			bulletLives[index++]=b;
		}
	}
	bullets = Arrays.copyOf(bulletLives, index);//����Խ����ӵ�����
}
/*
 * �ӵ��ͷ�����֮�����ײ���
 * */
public void bang(Bullet bullet) {
	int index =-1;//���з���������
	for(int i=0;i<flyings.length;i++) {
		FlyingObject fly =flyings[i];
		if(fly.shootBy(bullet)) {//�ж��Ƿ񱻻���
			index = i;
			break;
		}
	}
	if(index !=-1) {//�л��еķ�����
		FlyingObject one = flyings[index];//��¼���еķ�����
		FlyingObject temp = flyings[index];//�����еķ���������һ�������ｻ��
		flyings[index] =flyings[flyings.length-1];
		flyings[flyings.length-1] = temp;
		//ɾ�����һ��������(��������)
		flyings = Arrays.copyOf(flyings, flyings.length-1);
		//���one��������ǵ���,�����
		if(one instanceof Enemy) {//�����������ǵ��˼ӷ�
			Enemy e = (Enemy) one;//ǿ������ת��
			score += e.getScore();//�ӷ�
		}
		if(one instanceof Award) {//��Ϊ���������ý���
			Award a = (Award) one;
			int type = a.getType();
			switch(type) {
			case Award.DOUBLE_FIRE:
				hero.addDoubleFire();//��˫������
			case Award.LIFE:
				hero.addLife();//����
				break;
			}
		}
	}
}
/*
 * �ж���Ϸ�Ƿ����
 * */
public boolean isGameOver() {
	for(int i=0;i<flyings.length;i++) {
		int index =-1;
		FlyingObject obj =flyings[i];
		if(hero.hit(obj)) {//����Ƿ���ײ
			hero.subtractLife();
			hero.setDoubleLife(0);
		index = i;
		}
		if(index!=-1) {
			FlyingObject t =flyings[index];
			flyings[index] = flyings[flyings.length-1];
			flyings[flyings.length-1] =t;
			flyings = Arrays.copyOf(flyings, flyings.length-1);
		}
	}
	return hero.getLife() <=0;
}
//�����Ϸ�Ƿ����
public void checkGameOverAction() {
	if(isGameOver()) {
		state=GAME_OVER;
	}
}
//��̬�����
static {
	try {
		background=ImageIO.read(shootGame.class.getResource("background.png"));
		start=ImageIO.read(shootGame.class.getResource("start.png"));
		airplane=ImageIO.read(shootGame.class.getResource("airplane.png"));
		bee=ImageIO.read(shootGame.class.getResource("bee.png"));
		bullet=ImageIO.read(shootGame.class.getResource("bullet.png"));
		hero0=ImageIO.read(shootGame.class.getResource("hero0.png"));
		hero1=ImageIO.read(shootGame.class.getResource("hero1.png"));
		pause=ImageIO.read(shootGame.class.getResource("pause.png"));
		gameover=ImageIO.read(shootGame.class.getResource("gameover.png"));
	} catch (IOException e) {
		e.printStackTrace();
		throw new RuntimeException(" ����ͼƬ��Դʧ��",e);
	}
}
public static void main(String[] args) {
	JFrame frame = new JFrame();
	shootGame game = new shootGame();//������
	frame.add(game);
	frame.setSize(WIDTH, HEIGHT);//��С
	frame.setAlwaysOnTop(true);//����������
	frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);//�رմ���
	frame.setLocationRelativeTo(null);//���ô����ʼλ��
	frame.setVisible(true);//���ٴ�������
	game.action();//��ʼ����
}

}
