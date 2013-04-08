package maze.gui;

import java.awt.Image;
import java.awt.image.BufferedImage;
import java.awt.image.RasterFormatException;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

public class ImageFetcher {
	BufferedImage imgX = null;
	BufferedImage imgD = null;
	BufferedImage imgF = null;
	BufferedImage imgf = null;
	BufferedImage imgd = null;
	BufferedImage imgH = null;
	BufferedImage imgA = null;
	BufferedImage imgE = null;
	BufferedImage imgW = null;
	BufferedImage imgw = null;
	BufferedImage imgB = null;
	BufferedImage imgN = null;

	public ImageFetcher() {
		try {
			imgX = ImageIO.read(new File("src/maze/gui/pics/wall.png"));
			imgD = ImageIO.read(new File("src/maze/gui/pics/bowser.png"));
			imgF = ImageIO.read(new File("src/maze/gui/pics/bowser_sword.png"));
			imgf = ImageIO.read(new File(
					"src/maze/gui/pics/bowser_asleep_sword.png"));
			imgd = ImageIO
					.read(new File("src/maze/gui/pics/bowser_asleep.png"));
			imgH = ImageIO.read(new File("src/maze/gui/pics/link.png"));
			imgA = ImageIO.read(new File("src/maze/gui/pics/link_sword.png"));
			imgE = ImageIO.read(new File("src/maze/gui/pics/sword.png"));
			imgB = ImageIO.read(new File("src/maze/gui/pics/eagle_flying.png"));
			imgw = ImageIO.read(new File(
					"src/maze/gui/pics/eagle_roost_sword.png"));
			imgW = ImageIO.read(new File(
					"src/maze/gui/pics/eagle_flying_sword.png"));
			imgN = ImageIO.read(new File("src/maze/gui/pics/intro.png"));
		} catch (IOException e) {
			System.out.println("Image not found");
			e.printStackTrace();
		}
	}

	public static ImageFetcher instance = null;

	public static ImageFetcher getInstance() {
		if (instance == null)
			instance = new ImageFetcher();
		return instance;
	}

	public BufferedImage fetchImage(char c) {

		BufferedImage img = null;

		switch (c) {
		// se for parede
		case 'X':
			img = imgX;
			break;
		// se for dragao
		case 'D':
			img = imgD;
			break;
		case 'F':
			img = imgF;
			break;
		case 'f':
			img = imgf;
			break;
		case 'd':
			img = imgd;
			break;
		// se for heroi
		case 'H':
			img = imgH;
			break;
		case 'A':
			img = imgA;
			break;
		case 'E':
			// se for a espada sozinha
			img = imgE;
			break;
		// se for a aguia
		case 'B':
			img = imgB;
			break;
		case 'w':
			img = imgw;
			break;
		case 'W':
			img = imgW;
			break;
		case 'N':
			img = imgN;
			break;
		default:
			break;
		}

		return img;
	}
}
