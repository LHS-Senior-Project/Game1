package Main;

import java.util.ArrayList;

import Math.Path;
import Math.Vector2D;

public class Levels {

	private ArrayList<Level> levels;
	private int count;
	public Game game;
	
	public Levels(Game game){
		
		levels = new ArrayList<Level>();
		count = 0;
		
		Level level1 = new Level(game,new Path(new Vector2D(69, 106),new Vector2D(51, 219),new Vector2D(61, 338),new Vector2D(195, 529),new Vector2D(332, 340),new Vector2D(347, 116) ,new Vector2D(417, 12),new Vector2D(514, 27),new Vector2D(531, 120),new Vector2D(540, 232),new Vector2D(452, 314),new Vector2D(413, 546),new Vector2D(574, 559),new Vector2D(674, 125),new Vector2D(706, 211),new Vector2D(701, 321)));
		Level level2 = new Level(game,new Path(new Vector2D(69, 106),new Vector2D(51, 219),new Vector2D(61, 338),new Vector2D(195, 529),new Vector2D(332, 340),new Vector2D(347, 116) ,new Vector2D(417, 12),new Vector2D(514, 27),new Vector2D(531, 120),new Vector2D(540, 232),new Vector2D(452, 314),new Vector2D(413, 546),new Vector2D(574, 559),new Vector2D(674, 125),new Vector2D(706, 211),new Vector2D(701, 321)));
		Level level3 = new Level(game,new Path(new Vector2D(69, 106),new Vector2D(51, 219),new Vector2D(61, 338),new Vector2D(195, 529),new Vector2D(332, 340),new Vector2D(347, 116) ,new Vector2D(417, 12),new Vector2D(514, 27),new Vector2D(531, 120),new Vector2D(540, 232),new Vector2D(452, 314),new Vector2D(413, 546),new Vector2D(574, 559),new Vector2D(674, 125),new Vector2D(706, 211),new Vector2D(701, 321)));
		
		
		
		
	}
	
}
