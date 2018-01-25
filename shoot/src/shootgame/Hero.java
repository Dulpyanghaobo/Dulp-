package shootgame;
/*
 * Ӣ�ۻ�Ҳ�Ƿ�����
 * */

import java.awt.image.BufferedImage;

public class Hero extends FlyingObject{
protected BufferedImage []images = {};
protected int index = 0;
private int doubleFire;
private int life;
public Hero() {
	life=3;
	doubleFire = 0;
	this.image=shootGame.hero0;
	images = new BufferedImage[]{shootGame.hero0,shootGame.hero1};
	width = image.getWidth();
	height = image.getHeight();
	x=150;
	y=500;
}
@Override
public void step() {
	if(images.length>0) {
		image=images[index++/10%images.length];
	}
	
}
//Ϊ�ɻ�����ӵ���λ��
public Bullet[] shoot() {
	int xStep = width/4;
	int yStep = 20;
	if(doubleFire>0) {
		Bullet[] bullets=new Bullet[2];
		bullets[0] = new Bullet(x+xStep,y-yStep);
		bullets[1] = new Bullet(x+3*xStep,y-yStep);
		doubleFire-=2;
		return bullets;
	}else {
		Bullet[] bullets=new Bullet[1];
		bullets[0] = new Bullet(x+2*xStep,y-yStep);
		return bullets;
	}
}
/*
 * �����ƶ�һ����Ծ���,��궯һ��x,y���λ���ƶ�
 * */
public void moveTo(int x,int y) {
	this.x = x-width / 2;
	this.y = y-height /2;
}
/*
 * ����������ӻ���
 * */
public void addDoubleFire() {
	doubleFire+=40;
}
//����
public void addLife() {
	life++;
}
public int getLife() {
	return life;
}
@Override
public boolean outOfBounds() {
	return false;
}
public void subtractLife() {
	life--;
}
public void setDoubleLife(int doubleFire) {
	this.doubleFire = doubleFire;
}
public boolean hit(FlyingObject other) {//��ײ�㷨
	int x1 =other.x-this.width/2;
	int x2 =other.x+other.width+this.width/2;
	int y1 =other.y-this.width/2;
	int y2 =other.y+other.width+this.width/2;
	return this.x+this.width/2>x1 && this.x +this.width/2<x2 && this.y +this.height/2> y1 && this.y+this.width/2<y2;
}
}
