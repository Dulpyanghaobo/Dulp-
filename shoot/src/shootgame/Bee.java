package shootgame;

import java.util.Random;

public class Bee extends FlyingObject implements Award{
private int xSpeed = 1;//x���ƶ��ٶ�
private int ySpeed = 2;//y�������ٶ�
private int awardType;//��������
	@Override
	public int getType() {
		return awardType;
	}
public Bee() {
	this.image=shootGame.bee;
	width = image.getWidth();
	height = image.getHeight();
	y = -height;
	Random rand = new Random();
	x = rand.nextInt(shootGame.WIDTH-width);
	//x=100;
	//y=200;
	awardType = rand.nextInt(2);
}
@Override
public void step() {
	//����б��
	x+= xSpeed;
	y+= ySpeed;
	if(x>shootGame.WIDTH-width) {
		xSpeed = -1;
	}
	if(x < 0) {
		xSpeed = 1;
	}
}
@Override
public boolean outOfBounds() {

	return y>shootGame.HEIGHT;
}
}
