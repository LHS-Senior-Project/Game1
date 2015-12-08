package Systems;

import Components.MobInfoComponent;
import Main.ComponentName;
import Main.Entity;
import Main.Game;
import Main.Level;
import Math.Vector2D;

public class MobSystem {

	public Level currentLevel;
	public Game game;
	
	public MobSystem(Game game){
		this.game = game;
	}
	
	public void update(){
		for(Entity mob : currentLevel.getMobs()){
			if(mob.hasCompoent(ComponentName.MobInfoComponent)){
				MobInfoComponent info = (MobInfoComponent) mob.getComponent(ComponentName.MobInfoComponent);
				float deltaX = 0;
				float deltaY = 0;
				if(currentLevel.path.hasNext(info.getLevelProgress())){
					deltaX =  currentLevel.path.getNext(info.getLevelProgress()).getX() - mob.positionComponent.getX();
					deltaY =  currentLevel.path.getNext(info.getLevelProgress()).getY() - mob.positionComponent.getY() ;
					Vector2D heading = new Vector2D(deltaX, deltaY);
					heading = heading.getUnit();
//					heading.multiplyScalar(info.speed);
					mob.positionComponent.setVelX(heading.getX());
					mob.positionComponent.setVelY(heading.getY());
					if(deltaX <= 5 && deltaY <= 5){
						info.levelProgress++;
					}
				}else{
					game.addGold(info.goldOnKill);
					currentLevel.removeMob(mob);
					
				}
			}
		}
	}
	
}
