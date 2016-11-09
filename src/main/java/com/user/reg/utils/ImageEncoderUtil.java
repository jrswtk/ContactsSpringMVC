package com.user.reg.utils;

import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.xml.bind.DatatypeConverter;

public class ImageEncoderUtil {

	public static BufferedImage getImageFromEncode64(String encode64) {
		byte[] imageBytes = DatatypeConverter.parseBase64Binary(
				encode64);
		
		BufferedImage bufferedImage = null;
		
		try {
			bufferedImage = ImageIO.read(
					new ByteArrayInputStream(imageBytes));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return bufferedImage; 
	}
	
}
