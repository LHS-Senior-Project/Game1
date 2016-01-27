package Components;

import Main.BaseComponent;
import Main.ComponentName;

public class UIInfoComponent extends BaseComponent {

	public static int GOLD = 0;
	public static int HEARTS = 1;
	public static int TOWER = 2;
	public static int TOGGLE_PLAY = 3;
	public static int SETTINGS = 4;
	public static int TOWER_DISPLAY = 5;
	
	public boolean towerPlace;
	public TowerInfoComponent towerInfo;
	public boolean handleClick;
	public boolean update;
	public int updateValue;
		
	public UIInfoComponent(boolean towerPlacer, TowerInfoComponent tic) {
		this.name = ComponentName.UIInfoComponent;
		this.towerPlace = towerPlacer;
		this.towerInfo = tic;
		handleClick = true;
	}

	public boolean isTowerPlace() {
		return towerPlace;
	}

	public void setTowerPlace(boolean towerPlace) {
		this.towerPlace = towerPlace;
	}

	public TowerInfoComponent getTowerInfo() {
		return towerInfo;
	}

	public void setTowerInfo(TowerInfoComponent towerInfo) {
		this.towerInfo = towerInfo;
	}

	public boolean isHandleClick() {
		return handleClick;
	}

	public void setHandleClick(boolean handleClick) {
		this.handleClick = handleClick;
	}
	
	public boolean isUpdate() {
		return update;
	}

	public void setUpdate(boolean update, int value) {
		this.update = update;
		this.updateValue = value;
	}
		
}
