package Components;

import java.awt.AlphaComposite;
import java.awt.Color;
import java.awt.Font;
import java.awt.FontFormatException;
import java.awt.Graphics2D;
import java.awt.GraphicsEnvironment;
import java.awt.image.BufferedImage;
import java.io.BufferedInputStream;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;

import javax.imageio.ImageIO;

import Main.BaseComponent;
import Main.ComponentName;

public class RenderableComponent extends BaseComponent {

	private BufferedImage image;
	public static Font pirate;

	public RenderableComponent(String imagePath) {
		this.name = ComponentName.RenderableComponent;
		loadFonts();
		if (!imagePath.isEmpty()) {
			try {
				this.image = ImageIO.read(getClass().getResource(imagePath));
				Graphics2D g = (Graphics2D) image.getGraphics();
				g.setComposite(AlphaComposite.Clear);
			} catch (IOException e) {
				initDefault();
				e.printStackTrace();
			}
		} else {
			initDefault();
		}

	}

	public RenderableComponent(boolean blank) {
		this.name = ComponentName.RenderableComponent;
		loadFonts();
		if (blank) {
			this.image = new BufferedImage(16, 16, BufferedImage.TYPE_INT_ARGB);
			Graphics2D g = (Graphics2D) image.getGraphics();
			g.setComposite(AlphaComposite.Clear);
		} else {
			initDefault();
		}
	}

	/***
	 * 
	 * @param blank
	 *            boolean to tell if the image should be blank
	 * @param width
	 *            int for image width
	 * @param height
	 *            int for image height
	 */
	public RenderableComponent(boolean blank, int width, int height) {
		this.name = ComponentName.RenderableComponent;
		loadFonts();
		if (blank) {
			this.image = new BufferedImage(width, height, BufferedImage.TYPE_INT_ARGB);
			Graphics2D g = (Graphics2D) image.getGraphics();
			g.setComposite(AlphaComposite.Clear);
		} else {
			initDefault();
		}
	}

	private void initDefault() {
		this.image = new BufferedImage(16, 16, BufferedImage.TYPE_INT_ARGB);
		Graphics2D g = (Graphics2D) image.getGraphics();
		g.setComposite(AlphaComposite.Clear);
		g.setColor(Color.RED);
		g.fillRect(0, 0, 16, 16);
		g.setColor(Color.GREEN);
		g.fillRect(0, 0, 4, 4);
		g.fillRect(4, 4, 4, 4);
		g.fillRect(0, 8, 4, 4);
		g.fillRect(8, 0, 4, 4);
		g.fillRect(8, 8, 4, 4);
		g.fillRect(12, 4, 4, 4);
		g.fillRect(4, 12, 4, 4);
		g.fillRect(12, 12, 4, 4);
	}

	public void loadFonts() {
		if (pirate == null) {
			try {
				System.out.println(this.getClass().getResource("/Images/vinque.ttf"));
				InputStream fileIn = new BufferedInputStream(
						this.getClass().getResource("/Images/vinque.ttf").openStream());
				pirate = Font.createFont(Font.TRUETYPE_FONT, fileIn);
				GraphicsEnvironment.getLocalGraphicsEnvironment().registerFont(pirate);
			} catch (Exception ex) {
				ex.printStackTrace();
				System.err.println("Font not loaded.");
			}
		}
	}

	public BufferedImage getImage() {
		return image;
	}

	public void updateText(String text){
		Graphics2D g = (Graphics2D) image.getGraphics();
		g.setComposite(AlphaComposite.Clear);
		g.fillRect(0, 0, image.getWidth(), image.getHeight());
		g.setComposite(AlphaComposite.Src);
		
		g.setFont(pirate.deriveFont(Font.PLAIN, 20));
		g.drawString(text, 0, 20);
	}

}
