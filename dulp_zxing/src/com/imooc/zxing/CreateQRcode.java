package com.imooc.zxing;

import java.io.File;
import java.nio.file.Path;
import java.util.HashMap;

import com.google.zxing.BarcodeFormat;
import com.google.zxing.EncodeHintType;
import com.google.zxing.MultiFormatWriter;
import com.google.zxing.client.j2se.MatrixToImageWriter;
import com.google.zxing.common.BitMatrix;
import com.google.zxing.qrcode.decoder.ErrorCorrectionLevel;

public class CreateQRcode {

	public static void main(String[] args) {
		int width = 300;
		int height =300;
		String format = "png";
		String content = "www.imooc.com";
		//�����ά�����
		HashMap hash = new HashMap();
		hash.put(EncodeHintType.CHARACTER_SET, "utf-8");//�����ַ���
		hash.put(EncodeHintType.ERROR_CORRECTION,ErrorCorrectionLevel.L);//����ȼ�
		hash.put(EncodeHintType.MARGIN, 2);//Ĭ�ϱ߾�
		//���ɶ�ά��
		try {
			BitMatrix bitMatrix =new MultiFormatWriter().encode(content,BarcodeFormat.QR_CODE, width, height,hash);
			Path file = new File("D:/code/img.png").toPath();//����һ���ļ���ʽ
			MatrixToImageWriter.writeToPath(bitMatrix, format, file);//��ά�����ɵ�·��
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}
