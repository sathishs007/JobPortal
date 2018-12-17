package com.myjobkart.utils;

import java.awt.Graphics;
import java.awt.GraphicsConfiguration;
import java.awt.GraphicsDevice;
import java.awt.GraphicsEnvironment;
import java.awt.HeadlessException;
import java.awt.Image;
import java.awt.Transparency;
import java.awt.image.BufferedImage;

import javax.swing.ImageIcon;

public class ImageAndBarcodeHelper {

	public static BufferedImage createCode39(int width, int heigth,
			String az09, boolean showText, boolean checkSum) {
		// Image i = it.businesslogic.ireport.barcode.BcImage.getBarcodeImage(9,
		// az09, showText, checkSum, null, 1, heigth);
		final Image i = null;
		final BufferedImage ret = ImageAndBarcodeHelper.toBufferedImage(i);
		if (ret.getWidth() > width) {
			throw new IllegalArgumentException("Barcode to wide");
			// TODO: Scaling doesn't return b/w but gray shades
			// return ImageAndBarcodeHelper.scaleBarcodeMaxWidth(i, width,
			// heigth);
		}
		return ret;

	}

	public static BufferedImage create128A(int width, int heigth, String az09,
			boolean showText, boolean checkSum) {
		// Image i = it.businesslogic.ireport.barcode.BcImage.getBarcodeImage(6,
		// az09, showText, checkSum, null, 1, heigth);
		final Image i = null;
		final BufferedImage ret = ImageAndBarcodeHelper.toBufferedImage(i);
		// if (ret.getWidth() > width){
		// throw new IllegalArgumentException("Barcode to wide");
		// TODO: Scaling doesn't return b/w but gray shades
		// return ImageAndBarcodeHelper.scaleBarcodeMaxWidth(i, width, heigth);
		// }
		return ret;

	}

	/**
	 * Scales if Image is wider, Crops if image is higer. Suitable for barcode.
	 * 
	 * @param i
	 * @param width
	 * @param cropHeigth
	 * @return
	 */
	public static BufferedImage scaleBarcodeMaxWidth(Image i, int width,
			int cropHeigth) {
		BufferedImage ret;
		int newWidth;
		int newHeigth;
		boolean scale;
		boolean crop;

		final int iWidth = i.getWidth(null);
		final int iHeigth = i.getHeight(null);

		if (iWidth == -1 || iWidth > width) {
			newWidth = width;
			scale = true;
		} else {
			newWidth = iWidth;
			scale = false;
		}

		if (iHeigth == -1 || iHeigth > cropHeigth) {
			newHeigth = cropHeigth;
			crop = true;
		} else {
			newHeigth = iHeigth;
			crop = false;
		}

		if (scale) {
			ret = ImageAndBarcodeHelper.toBufferedImage(i.getScaledInstance(
					newWidth, newHeigth, Image.SCALE_SMOOTH));
		} else {
			ret = ImageAndBarcodeHelper.toBufferedImage(i);
		}

		if (crop) {
			final BufferedImage outImage = ImageAndBarcodeHelper
					.toBufferedImage(ret);
			ret = outImage.getSubimage(0, 0, outImage.getWidth(), cropHeigth);
		}

		return ret;

	}

	public static BufferedImage toBufferedImage(Image image) {
		if (image instanceof BufferedImage) {
			return (BufferedImage) image;
		}

		// This code ensures that all the pixels in the image are loaded
		image = new ImageIcon(image).getImage();

		// Determine if the image has transparent pixels; for this method's
		// implementation, see e661 Determining If an Image Has Transparent
		// Pixels
		final boolean hasAlpha = ImageAndBarcodeHelper.hasAlpha(image);

		// boolean hasAlpha = false;

		// Create a buffered image with a format that's compatible with the
		// screen
		BufferedImage bimage = null;
		final GraphicsEnvironment ge = GraphicsEnvironment
				.getLocalGraphicsEnvironment();
		try {
			// Determine the type of transparency of the new buffered image
			int transparency = Transparency.OPAQUE;
			if (hasAlpha) {
				transparency = Transparency.BITMASK;
			}

			// Create the buffered image
			final GraphicsDevice gs = ge.getDefaultScreenDevice();
			final GraphicsConfiguration gc = gs.getDefaultConfiguration();
			bimage = gc.createCompatibleImage(image.getWidth(null),
					image.getHeight(null), transparency);
		} catch (final HeadlessException e) {
			// The system does not have a screen
		}

		if (bimage == null) {
			// Create a buffered image using the default color model
			// int type = BufferedImage.TYPE_INT_RGB;
			int type = BufferedImage.TYPE_BYTE_BINARY;
			if (hasAlpha) {
				type = BufferedImage.TYPE_INT_ARGB;
			}
			bimage = new BufferedImage(image.getWidth(null),
					image.getHeight(null), type);
		}

		// Copy image to buffered image
		final Graphics g = bimage.createGraphics();

		// Paint the image onto the buffered image
		g.drawImage(image, 0, 0, null);
		g.dispose();

		return bimage;
	}

	// This method returns true if the specified image has transparent pixels
	public static boolean hasAlpha(Image image) {
		// If buffered image, the color model is readily available

		return false;

		// if (image instanceof BufferedImage) {
		// BufferedImage bimage = (BufferedImage) image;
		// return bimage.getColorModel().hasAlpha();
		// }
		//
		// // Use a pixel grabber to retrieve the image's color model;
		// // grabbing a single pixel is usually sufficient
		// PixelGrabber pg = new PixelGrabber(image, 0, 0, 1, 1, false);
		// try {
		// pg.grabPixels();
		// } catch (InterruptedException e) {
		// }
		//
		// // Get the image's color model
		// ColorModel cm = pg.getColorModel();
		// return cm.hasAlpha();
	}
}
