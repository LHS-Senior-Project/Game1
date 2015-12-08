package Components;

import Main.BaseComponent;
import Main.ComponentName;

public class UIInfoComponent extends BaseComponent {

	public boolean towerPlace;
	public TowerInfoComponent towerInfo;
	public boolean handleClick;
	public boolean update;
	
	public UIInfoComponent(boolean towerPlacer, TowerInfoComponent tic) {
		this.name = ComponentName.UIInfoComponent;
		towerPlace = towerPlacer;
		towerInfo = tic;
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
		
}
