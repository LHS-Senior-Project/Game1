package Systems;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

import org.w3c.dom.Document;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

import Components.MobInfoComponent;
import Components.TowerInfoComponent;
import Main.ComponentName;
import Main.Game;
import Main.Level;
import Math.Path;
import Math.Vector2D;
import Types.ProjectileTypes;

public class LevelLoader {

	public Game game;
	public ArrayList<Level> levelList;
	public ArrayList<String> levelPaths;

	String PROGRAM_DIRECTORY;



	public static void main(String args[]) {
		LevelLoader ll = new LevelLoader(null);
	}

	public LevelLoader(Game g) {
		levelList = new ArrayList<Level>();
		levelPaths = new ArrayList<String>();
		this.game = g;
		getDirectory();
		detectLevels();		
	}

	public String getDirectory() {
		try {
			PROGRAM_DIRECTORY = getClass().getClassLoader().getResource("")
					.getPath(); // Gets the path of the class or jar.

			try {
				PROGRAM_DIRECTORY = PROGRAM_DIRECTORY.substring(0,
						PROGRAM_DIRECTORY.lastIndexOf('!'));
			} catch (Exception e) {
			}

			// Find the last / and cut it off at that location.
			PROGRAM_DIRECTORY = PROGRAM_DIRECTORY.substring(0,
					PROGRAM_DIRECTORY.lastIndexOf('/') + 1);
			// If it starts with /, cut it off.
			if (PROGRAM_DIRECTORY.startsWith("/"))
				PROGRAM_DIRECTORY = PROGRAM_DIRECTORY.substring(1,
						PROGRAM_DIRECTORY.length());
			// If it starts with file:/, cut that off, too.
			if (PROGRAM_DIRECTORY.startsWith("file:/"))
				PROGRAM_DIRECTORY = PROGRAM_DIRECTORY.substring(6,
						PROGRAM_DIRECTORY.length());
		} catch (Exception e) {
			PROGRAM_DIRECTORY = ""; // Current working directory instead.
		}
		return PROGRAM_DIRECTORY;
	}
	
	private void detectLevels(){

		System.out.println(this.PROGRAM_DIRECTORY);
		File levelsDir = new File(this.PROGRAM_DIRECTORY);
		File directory = new File(this.PROGRAM_DIRECTORY);
		for (File file : directory.listFiles()) {
//			System.out.println(file.getName());
			if(file.getName().equalsIgnoreCase("levels")){
				levelsDir = file;
			}
		}
		
		String fileNames = "";
		for(File f : levelsDir.listFiles()){
			fileNames += f.getName();
//			System.out.println(f.getName());
			if(f.getName().equalsIgnoreCase("test.xml")){
				DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
				DocumentBuilder db;
				try {
					db = dbf.newDocumentBuilder();
					Document doc = db.parse(f);
					Node levelsNode = doc.getFirstChild();
					System.out.println(levelsNode.getNodeName());
					NodeList levels = levelsNode.getChildNodes();
					int levelCount = 0;
					for(int i = 0; i < levels.getLength(); i ++){
						if(levels.item(i).getNodeName().equalsIgnoreCase("Level")){
							levelCount++;
							System.out.print(levels.item(i).getNodeName() + " " + levelCount);
							System.out.println(": " + levels.item(i).getAttributes().getNamedItem("name").getTextContent());
							Level nl = new Level(this.game, new Path(new Vector2D(69, 106),new Vector2D(51, 219),new Vector2D(61, 338),new Vector2D(195, 529),new Vector2D(332, 340),new Vector2D(347, 116) ,new Vector2D(417, 12),new Vector2D(514, 27),new Vector2D(531, 120),new Vector2D(540, 232),new Vector2D(452, 314),new Vector2D(413, 546),new Vector2D(574, 559),new Vector2D(674, 125),new Vector2D(706, 211),new Vector2D(701, 321)));
							NodeList nodeList = levels.item(i).getChildNodes();
							for(int q = 0; q < nodeList.getLength(); q++){
//								System.out.println(nodeList.item(q).getNodeName());
								if(nodeList.item(q).getNodeName().equalsIgnoreCase("Background")){
									nl.backgroundPath = PROGRAM_DIRECTORY + "levels/" + nodeList.item(q).getTextContent();
//									System.out.println(PROGRAM_DIRECTORY + "levels/" + nodeList.item(q).getTextContent());
								}
								if(nodeList.item(q).getNodeName().equalsIgnoreCase("Mobs")){
									NodeList mobs = nodeList.item(q).getChildNodes();
									for(int m = 0; m < mobs.getLength(); m++){
										if(mobs.item(m).getNodeName().equalsIgnoreCase("mob")){
											String name = ComponentName.MobInfoComponent;
											float health = 100;
											float speed = .15f;
											int goldOnKill = 5;
											int width = 0;
											int height = 0;
											String imageString = "/Images/ok_16x16.gif";
											NodeList mobInfo = mobs.item(m).getChildNodes();
											for(int attr = 0; attr < mobInfo.getLength(); attr++){
												if(!mobInfo.item(attr).getNodeName().equalsIgnoreCase("#text")){
													System.out.println(mobInfo.item(attr).getNodeName());
													if(mobInfo.item(attr).getNodeName().equalsIgnoreCase("Name")){
														
													}
													if(mobInfo.item(attr).getNodeName().equalsIgnoreCase("Health")){
														health = Float.parseFloat(mobInfo.item(attr).getTextContent());										
																										}
													if(mobInfo.item(attr).getNodeName().equalsIgnoreCase("Gold")){
														goldOnKill = Integer.parseInt(mobInfo.item(attr).getTextContent());
													}
													if(mobInfo.item(attr).getNodeName().equalsIgnoreCase("Speed")){
														speed = Float.parseFloat(mobInfo.item(attr).getTextContent());
													}
													if(mobInfo.item(attr).getNodeName().equalsIgnoreCase("MobID")){
														
													}
													if(mobInfo.item(attr).getNodeName().equalsIgnoreCase("Image")){
														imageString = PROGRAM_DIRECTORY + "Levels/" + mobInfo.item(attr).getTextContent();
														System.out.println(imageString);
														width = Integer.parseInt(mobInfo.item(attr).getAttributes().getNamedItem("width").getNodeValue());
														height = Integer.parseInt(mobInfo.item(attr).getAttributes().getNamedItem("height").getNodeValue());
													}
												}
											}
											//(int xSize, int ySize, float startHealth, int health, float speed, int goldOnKill, int levelProgress, String imageString)
											nl.addMobInfo(new MobInfoComponent(width,height,health, (int) health, speed,goldOnKill,0,imageString));
										}
									}
									
								}//Mobs End
								
								if(nodeList.item(q).getNodeName().equalsIgnoreCase("Towers")){
									NodeList towers = nodeList.item(q).getChildNodes();
									for(int t = 0; t < towers.getLength(); t++){
										if(towers.item(t).getNodeName().equalsIgnoreCase("tower")){
											String towerImagePath = "";
											String towerIconPath = "";
											int width = 0;
											int height = 0;
											
											String name = "";
											int cost = 0;
											float range = 0;
											float speed = 0;
											int damage = 0;
											NodeList towerInfo = towers.item(t).getChildNodes();
											for(int attr = 0; attr < towerInfo.getLength(); attr++){
												if(towerInfo.item(attr).getNodeName().equalsIgnoreCase("Image")){
													towerImagePath = PROGRAM_DIRECTORY + "Levels/" + towerInfo.item(attr).getTextContent();
													width = Integer.parseInt(towerInfo.item(attr).getAttributes().getNamedItem("width").getNodeValue());
													height = Integer.parseInt(towerInfo.item(attr).getAttributes().getNamedItem("height").getNodeValue());
												}
												if(towerInfo.item(attr).getNodeName().equalsIgnoreCase("Icon")){
													towerIconPath = PROGRAM_DIRECTORY + "Levels/" + towerInfo.item(attr).getTextContent();
												}
												if(towerInfo.item(attr).getNodeName().equalsIgnoreCase("cost")){
													cost = Integer.parseInt(towerInfo.item(attr).getTextContent());
												}
												if(towerInfo.item(attr).getNodeName().equalsIgnoreCase("name")){
													name = towerInfo.item(attr).getTextContent();
												}
												if(towerInfo.item(attr).getNodeName().equalsIgnoreCase("range")){
													range = Float.parseFloat(towerInfo.item(attr).getTextContent());
												}
												if(towerInfo.item(attr).getNodeName().equalsIgnoreCase("speed")){
													speed = Float.parseFloat(towerInfo.item(attr).getTextContent());
												}
												if(towerInfo.item(attr).getNodeName().equalsIgnoreCase("damage")){
													damage = Integer.parseInt(towerInfo.item(attr).getTextContent());
												}
												
												
											}	
											//(String towerName, String towerDescription, String imageLoc, String iconLoc, int cost, float range, float speed, int damage, float xPos, float yPos, float xSize, float ySize, ProjectileInfoComponent projectile)
											nl.addTowerInfo(new TowerInfoComponent(name, "ription", towerImagePath, towerIconPath, cost, range, speed, damage, 0, 0, width, height, ProjectileTypes.Cannonball));
										}
									}
								}
								
								if(nodeList.item(q).getNodeName().equalsIgnoreCase("Wave")){
									System.out.println(nodeList.item(q).getTextContent());
									String[] list = nodeList.item(q).getTextContent().split(",");
									for(String mob : list){
										System.out.println(mob);
										nl.mobsToSpawn.add(Integer.parseInt(mob));
									}
								}
								
								if(nodeList.item(q).getNodeName().equalsIgnoreCase("Path")){
									
								}
							}
							
							levelList.add(nl);
						}
					}
										
//					System.out.println(levels.item(3).getNodeName());
//					System.out.println(levels.item(3).getAttributes().getNamedItem("name"));
					
				} catch (ParserConfigurationException | SAXException | IOException e) {
					e.printStackTrace();
				} 
				
			}
		}

	}

	public Level loadLevel(int index) {
		return levelList.get(index);
	}

}
