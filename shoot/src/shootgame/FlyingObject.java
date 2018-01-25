package shootgame;

import java.awt.image.BufferedImage;

public abstract class FlyingObject {
protected int x;
protected int y;
protected int width;
protected int height;
protected BufferedImage image;
public int getX() {
	return x;
}
public void setX(int x) {
	this.x = x;
}
public int getY() {
	return y;
}
public void setY(int y) {
	this.y = y;
}
public int getWidth() {
	return width;
}
public void setWidth(int width) {
	this.width = width;
}
public int getHeight() {
	return height;
}
public void setHeight(int height) {
	this.height = height;
}
public BufferedImage getImage() {
	return image;
}
public void setImage(BufferedImage image) {
	this.image = image;
}
/*
 * �������ƶ�
 * */
public abstract void step();
/*
 * ��鵱ǰ�������Ƿ��ӵ�����
 * */
public boolean shootBy(Bullet bullet) {
	int x = bullet.x;//�ӵ��ĺ�����
	int y = bullet.y;//�ӵ���������
	return this.x<x && x<this.x+width && this.y<y && y<this.y+height;
}
/*
 * ����Ƿ����
 * */
public abstract boolean outOfBounds();
}
