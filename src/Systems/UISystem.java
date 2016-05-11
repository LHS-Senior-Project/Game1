package Systems;

import java.awt.Color;
import java.awt.Graphics2D;
import java.util.ArrayList;

import Components.MobInfoComponent;
import Components.RenderableComponentS;
import Components.TowerInfoComponent;
import Components.UIInfoComponent;
import Main.ComponentName;
import Main.Entity;
import Main.Game;
import Main.KeyInput;
import Main.MouseInput;
import Types.TowerTypes;

public class UISystem {

	public MouseInput mouse;
	public KeyInput key;
	public ArrayList<Entity> uiElements;
	public Color uiBackground = new Color(181, 112, 74);
	public Game game;
	public boolean placing;
	public TowerInfoComponent tic;
	public boolean normal;
	public Entity pointer;

	public UISystem(MouseInput mouse, KeyInput key, Game game) {
		uiElements = new ArrayList<Entity>();
		this.key = key;
		this.mouse = mouse;
		this.game = game;
		this.placing = false;
		this.tic = new TowerInfoComponent();
		this.normal = true;
		this.pointer = new Entity(0, 0, 0, 0);
	}

	public void addElement(Entity element) {
		uiElements.add(element);
	}

	public ArrayList<Entity> checkForClickOnElement() {
		ArrayList<Entity> toReturn = new ArrayList<Entity>();
		for (Entity element : uiElements) {
			if (mouse.isClick() && element.positionComponent.checkCollide(mouse.getPositionComponent())) {
				toReturn.add(element);
			}
		}

		if (toReturn.isEmpty()) {
			return null;
		}
		return toReturn;
		// return new Entity(mouse.getPositionComponent());
	}

	public void update() {

		if (mouse.isClick()) {
			System.out.print("new Vector2D(" + (int) mouse.getPositionComponent().getX() + ", " + (int) mouse.getPositionComponent().getY() + "),");
		}
			
		handleKeys();
		
		

		// check if the element needs to update
		for (Entity ui : uiElements) {
			if (ui.hasComponent(ComponentName.UIInfoComponent)) {
				UIInfoComponent info = (UIInfoComponent) ui.getComponent(ComponentName.UIInfoComponent);
				if (info.update) {
					if (info.updateValue == UIInfoComponent.GOLD) {
						if (ui.hasComponent(ComponentName.RenderableComponent)) {
							RenderableComponentS uiRenderable = (RenderableComponentS) ui.getComponent(ComponentName.RenderableComponent);
							uiRenderable.updateText("" + game.getGold());
						}
					} else if (info.updateValue == UIInfoComponent.HEARTS) {
						if (ui.hasComponent(ComponentName.RenderableComponent)) {
							RenderableComponentS uiRenderable = (RenderableComponentS) ui.getComponent(ComponentName.RenderableComponent);
							uiRenderable.updateText("" + game.getHeart());
						}
					} else if (info.updateValue == UIInfoComponent.TOWER_DISPLAY) {
						if (ui.hasComponent(ComponentName.RenderableComponent)) {
							RenderableComponentS uiRenderable = (RenderableComponentS) ui.getComponent(ComponentName.RenderableComponent);
							Graphics2D g = uiRenderable.getGraphics();
							g.setColor(uiBackground);
							g.fillRect(0, 0, 256, 256);
							GraphicsSystem.drawString(g, "Tower Name: " + tic.getTowerName(), 10, 10, 15, Color.black, uiBackground);
							GraphicsSystem.drawString(g, "Tower Cost: " + tic.getCost(), 10, 30, 15, Color.black, uiBackground);
							GraphicsSystem.drawString(g, "Tower Range: " + tic.getRange(), 10, 50, 15, Color.black, uiBackground);
							GraphicsSystem.drawString(g, "Tower Damage: " + tic.getDamage(), 10, 70, 15, Color.black, uiBackground);
							GraphicsSystem.drawString(g, "Tower Shots/Sec: " + tic.getSpeed(), 10, 90, 15, Color.black, uiBackground);
							GraphicsSystem.drawString(g, "Tower Description: \n" + tic.getTowerDescription(), 10, 110, 12, Color.black, uiBackground);
						}
					} else if (info.updateValue == UIInfoComponent.TOGGLE_PLAY) {
						if (ui.hasComponent(ComponentName.RenderableComponent)) {
							RenderableComponentS uiRenderable = (RenderableComponentS) ui.getComponent(ComponentName.RenderableComponent);
							if (game.getSoftPause()) {
								uiRenderable.setImage("/Buttons/play.png", (int) ui.positionComponent.getSizeX(), (int) ui.positionComponent.getSizeY(),true);
							} else {
								uiRenderable.setImage("/Buttons/pause.png", (int) ui.positionComponent.getSizeX(), (int) ui.positionComponent.getSizeY(),true);
							}
						}
					}
				}

			}
		}

		// System.out.println("x: " +mouse.getPositionComponent().getX() +
		// "  Y: " + mouse.getPositionComponent().getY());

		pointer.positionComponent.setX(mouse.getPositionComponent().getX() - pointer.positionComponent.getSizeX() / 2);
		pointer.positionComponent.setY(mouse.getPositionComponent().getY() - pointer.positionComponent.getSizeY() / 2);

		ArrayList<Entity> clickedEntities = checkForClickOnElement();
		// handle clicks
		if (mouse.isClick()) {
			// clicked on nothing
			if (clickedEntities == null) {
				if (game.getRunning()) {
					// if you're placing a tower and you can afford it
					if (placing && game.getGold() >= tic.getCost()) {
						Entity newTower = game.getTowerHandlingSystem().buildTower(tic);
						newTower.positionComponent.setX(mouse.getPositionComponent().getX() - tic.border.getCentroid().getX());
						newTower.positionComponent.setY(mouse.getPositionComponent().getY() - tic.border.getCentroid().getY());
						for (Entity e : game.getEntities()) {
							if (e != newTower) {
								if (newTower.positionComponent.checkCollide(e.positionComponent)) {
									return;
								}
							}
						}
						game.getTowerHandlingSystem().createTower(tic, newTower.positionComponent.getX(), newTower.positionComponent.getY());
						// tic.updatePos(newTower);
						// newTower.addComponent(tic);
						// uiElements.add(newTower);
						// game.getPhysicsSystem().addToPhysics(newTower);
						// game.getGraphicsSystem().addRenderable(newTower);
						// game.addEntities(newTower);
						// game.addGold(-1 * tic.cost);

						placing = false;
						RenderableComponentS rc = (RenderableComponentS) this.pointer.getComponent(ComponentName.RenderableComponent);
						rc.clearImage();
					}
					mouse.clickHandled();

					// spawn a mob when clicked
//					game.getMobSystem().createMob(game.getCurLevel().levelMobInfo.get(0), 50, 50);
					// Entity testMob = new Entity(50, 50, 16, 16);
					// MobInfoComponent mic = new MobInfoComponent();
					// testMob.addComponent(new
					// RenderableComponent("/Images/ok_16x16_2.gif"));
					// testMob.addComponent(mic);
					// game.getMobSystem().currentLevel.addMob(testMob);
					// game.addEntities(testMob);
					// game.getPhysicsSystem().addToPhysics(testMob);
					// game.getGraphicsSystem().addRenderable(testMob);
				}
			} else {
				for (Entity clicked : clickedEntities) {
					if (clicked.hasComponent(ComponentName.UIInfoComponent)) {
						UIInfoComponent info = (UIInfoComponent) clicked.getComponent(ComponentName.UIInfoComponent);
						if (info.handleClick) {
							if (info.towerPlace) {
								placing = true;
								this.tic = info.getTowerInfo();
								RenderableComponentS rc = (RenderableComponentS) this.pointer.getComponent(ComponentName.RenderableComponent);
								this.pointer.positionComponent.setBorder(tic.getBorder());
								rc.setImage(tic.imageLoc, (int) tic.getxSize(), (int) tic.getySize(),false);
							}
							if (info.updateValue == UIInfoComponent.TOGGLE_PLAY) {
								togglePlay();
							}
						}

					}
					if (clicked.hasComponent(ComponentName.TowerInfoComponent)) {
						TowerInfoComponent towerInfo = (TowerInfoComponent) clicked.getComponent(ComponentName.TowerInfoComponent);
						this.tic = towerInfo;
						placing = false;
						// RenderableComponent pointerRender =
						// (RenderableComponent)
						// pointer.getComponent(ComponentName.RenderableComponent);
						// this.pointer.positionComponent.setSizeX(towerInfo.getxSize());
						// this.pointer.positionComponent.setSizeY(towerInfo.getySize());
						// pointerRender.setImage(towerInfo.imageLoc, (int)
						// towerInfo.getxSize(), (int)towerInfo.getySize());
						mouse.clickHandled();
						return;
					}

				}
			}

		} else {// not clicking

		}
	}

	private void handleKeys() {
		game.getGraphicsSystem().setDebug(key.isPressed(key.debug));
		
		if (key.isPressed(key.esc)) {
			placing = false;
			RenderableComponentS rc = (RenderableComponentS) this.pointer.getComponent(ComponentName.RenderableComponent);
			rc.clearImage();
			key.handled(key.esc);
		}
		if (key.isPressed(key.space)) {
			togglePlay();
			key.handled(key.space);
		}
		if(key.isPressed(key.k1)){
			game.loadLevel(0);
			key.handled(key.k1);
		}
		if(key.isPressed(key.k2)){
			game.loadLevel(1);
			key.handled(key.k2);
		}
		if(key.isPressed(key.k3)){
			game.loadLevel(2);
			key.handled(key.k3);
		}
		if(key.isPressed(key.q)){
			MobInfoComponent mic = game.getCurLevel().levelMobInfo.get(0);
			game.getMobSystem().createMob(mic, 100, 100, false);
			key.handled(key.q);
		}
	}

	public void togglePlay() {
		game.setSoftPause((!game.getSoftPause()));
		for(Entity ui : uiElements){
			if(ui.hasComponent(ComponentName.UIInfoComponent)){
				UIInfoComponent info = (UIInfoComponent) ui.getComponent(ComponentName.UIInfoComponent);
				if(info.updateValue == UIInfoComponent.TOGGLE_PLAY){
					if (game.getSoftPause()) {
						RenderableComponentS rc = (RenderableComponentS) ui.getComponent(ComponentName.RenderableComponent);
						rc.setImage("/Buttons/play.png", 16, 16,true);
					} else {
						RenderableComponentS rc = (RenderableComponentS) ui.getComponent(ComponentName.RenderableComponent);
						rc.setImage("/Buttons/pause.png", 16, 16,true);
					}
				}
			}
		}
	}

	public void setPointer(Entity pointer) {
		this.pointer = pointer;
	}

	public void updateTowerIcons(){
		int count = 0;
		int x = 0;
		int y = 0;
		for(TowerInfoComponent tic : game.getCurLevel().levelTowerInfo){
			switch (count){
				case 0:
					x = 1058;
					y = 211;
					break;
				case 1:
					x = 1160;
					y = 211;
					break;
				case 2:
					x = 1058;
					y = 315;
					break;
				case 3:
					x = 1160;
					y = 315;
					break;
				case 4:
					x = 1058;
					y = 419;
					break;
				case 5:
					x = 1160;
					y = 419;
					break;
				default:
					break;
			}
			
			Entity newTowerIcon = new Entity(x, y, 83, 83);
			newTowerIcon.addComponent(new RenderableComponentS(tic.iconLoc,83,83,false));
			newTowerIcon.addComponent(new UIInfoComponent(true, tic));
			game.getGraphicsSystem().addRenderable(newTowerIcon);
			game.addEntities(newTowerIcon);
			game.getUISystem().addElement(newTowerIcon);
			
			count++;
		}
	}
	
}
