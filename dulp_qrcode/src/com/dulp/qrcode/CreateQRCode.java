package com.dulp.qrcode;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.io.UnsupportedEncodingException;

import javax.imageio.ImageIO;

import com.swetake.util.Qrcode;

public class CreateQRCode {
public static void main(String[] args) throws Exception {
	Qrcode x = new Qrcode();
	x.setQrcodeEncodeMode('B');//N��������A����a-Z��B���������ַ�
	x.setQrcodeErrorCorrect('M');//������
	x.setQrcodeVersion(7);//�汾
	String qrData = "http://202.198.0.54/jwweb/default.aspx\r\n";//������������������ӵ�ַ
	int width = 67 +12 * (7 - 1);//����ͼƬ��
	int height = 67 +12 * (7 - 1);
	BufferedImage bufferedImage = new BufferedImage(width,height, BufferedImage.TYPE_INT_BGR);
	Graphics2D gs =bufferedImage.createGraphics();
	gs.setBackground(Color.WHITE);
	gs.setColor(Color.BLACK);
	gs.clearRect(0, 0, width, height);
	int pixoff = 2;//ƫ����
	byte[] d = qrData.getBytes("gb2312");//��ͼƬ��д���ֽ�
	if(d.length>0 && d.length<120) {
		boolean[][]s =x.calQrcode(d);
		for(int i=0;i<s.length;i++) {
			for(int j=0;j<s.length;j++) {
				if(s[j][i]) {
				gs.fillRect(j*3+pixoff, i*3+pixoff, 3, 3);	
				}
			}
		}
	}
	gs.dispose();
	bufferedImage.flush();
	ImageIO.write(bufferedImage, "png", new File("D:/code/Academic_System.png"));
}
}
