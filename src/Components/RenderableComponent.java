package Components;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

import Main.BaseComponent;

public class RenderableComponent extends BaseComponent{
	
	private BufferedImage image;
	
	public RenderableComponent(String imagePath){
		this.name = "RenderableComponent";
		
		if(!imagePath.isEmpty()){
			try {
				this.image = ImageIO.read(getClass().getResource(imagePath));
				
			} catch (IOException e) {
				initDefault();
				e.printStackTrace();
			}
		}else{
			initDefault();
		}
			
	}
	
	private void initDefault() {
		image = new BufferedImage(16,16, BufferedImage.TYPE_INT_RGB);
		Graphics g = image.getGraphics();
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

	public BufferedImage getImage(){
		return image;
	}
	
}
