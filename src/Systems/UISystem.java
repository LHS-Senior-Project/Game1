package Systems;

import java.util.ArrayList;

import Components.RenderableComponent;
import Components.TowerInfoComponent;
import Components.UIInfoComponent;
import Main.ComponentName;
import Main.Entity;
import Main.Game;
import Main.KeyInput;
import Main.MouseInput;

public class UISystem {

	public MouseInput mouse;
	public KeyInput key;
	public ArrayList<Entity> uiElements;
	public Game game;
	public boolean placing;
	public TowerInfoComponent tic;
	public boolean normal;

	public UISystem(MouseInput mouse, KeyInput key, Game game) {
		uiElements = new ArrayList<Entity>();
		this.key = key;
		this.mouse = mouse;
		this.game = game;
		this.placing = true;
		this.tic = new TowerInfoComponent();
		this.normal = true;
	}

	public void addElemnt(Entity element) {
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

		game.getGraphicsSystem().setDebug(key.isPressed(key.space));
		
		for(Entity ui : uiElements){
			if(ui.hasCompoent(ComponentName.UIInfoComponent)){
				UIInfoComponent info = (UIInfoComponent) ui.getComponent(ComponentName.UIInfoComponent);
				if(info.update){
					if(ui.hasCompoent(ComponentName.RenderableComponent)){
						RenderableComponent uiRenderable = (RenderableComponent) ui.getComponent(ComponentName.RenderableComponent);
						uiRenderable.updateText("" + game.getGold());
					}
				}
				
			}
		}

		ArrayList<Entity> clickedEntities = checkForClickOnElement();
		// handle
		if (mouse.isClick()) {

			if (clickedEntities == null) {
				if (placing && game.getGold() >= tic.getCost()) {
					Entity newTower = game.getTowerHandlingSystem().buildTower(tic);
					newTower.positionComponent.setX(mouse.getPositionComponent().getX() - tic.xSize / 2);
					newTower.positionComponent.setY(mouse.getPositionComponent().getY() - tic.ySize / 2);
					for (Entity e : game.getEntities()) {
						if (e != newTower) {
							if (newTower.positionComponent.checkCollide(e.positionComponent)) {
								return;
							}
						}
					}
					newTower.addComponent(tic);
					uiElements.add(newTower);
					game.getPhysicsSystem().addToPhysics(newTower);
					game.getGraphicsSystem().addRenderable(newTower);
					game.addEntities(newTower);
					game.addGold(-1 * tic.cost);
					placing = false;
				}
			} else {
				for (Entity clicked : clickedEntities) {
					if (clicked.hasCompoent(ComponentName.UIInfoComponent)) {
						UIInfoComponent info = (UIInfoComponent) clicked.getComponent(ComponentName.UIInfoComponent);
						if (info.handleClick) {
							if (info.towerPlace) {
								placing = true;
								this.tic = info.towerInfo;
							}
						}
					}
					if (clicked.hasCompoent(ComponentName.TowerInfoComponent)) {
						TowerInfoComponent towerInfo = (TowerInfoComponent) clicked
								.getComponent(ComponentName.TowerInfoComponent);
						game.getGraphicsSystem().getCanvas().getGraphics().drawString(towerInfo.towerName, 0, 10);
						System.out.println(towerInfo.towerName);
						mouse.clickHandled();
						return;
					}

				}
			}

		}
	}

}
