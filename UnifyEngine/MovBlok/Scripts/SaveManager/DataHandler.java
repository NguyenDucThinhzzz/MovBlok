package MovBlok.Scripts.SaveManager;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.PrintWriter;
import java.util.Scanner;

import MovBlok.Objects.*;
import MovBlok.Scenes.GameScene;
import MovBlok.Scripts.Grid;
import UnifyEngine.Debug;
import UnifyEngine.GameObject;
import UnifyEngine.Vector2;

public class DataHandler {
	private File file = null;
	private GameObject[][] fileObjs;
	
	public DataHandler(String _filePath) {
		file = new File(_filePath);
	}
	
	public Vector2 ReadFileData(Grid grd){
		try {
			Vector2 plrPos = new Vector2();
			int x,y;
			Scanner scn = new Scanner(file);
			//Get x,y of the playing field
			x = scn.nextInt();
			y = scn.nextInt();
			fileObjs = new GameObject[y][x];
			//Read the file data
			for(int i=0;i<y;i++) {
				for(int j=0;j<x;j++) {
					switch(scn.nextInt()) {
					case 0:
						plrPos = new Vector2(i,j);
						break;
					case 1:
						grd.addObj(new Box(j,i));
						break;
					case 2:
						grd.addObj(new Ground(j,i));;
						break;
					case 3:
						grd.addObj(new Wall(j,i));;
						break;
					case 4:
						grd.addObj(new Portal(j,i));;
						break;
					}
				}
			}
			scn.close();
			return plrPos;
		} 
		catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			Debug.Log("The file you are trying to read is NULL!");
			return null;
		}	
	}
	
	public void WriteFileData(Grid grd) {
		
		try {
			PrintWriter prt = new PrintWriter(file);
			int x = grd.GetGridBoundX();
			int y = grd.GetGridBoundY();
			prt.println(x+" "+y);
			for(int i=0;i<y;i++) {
				for(int j=0;j<x;j++) {
					Debug.Log(i +" "+ j+"\n");
					prt.print(grd.getObj(j, i).GetID()+" ");
				}
				prt.println();
			}
			prt.close();
		}
		catch (FileNotFoundException e) {
			Debug.Log("The file you are trying to read is NULL!");
		}
		catch (ArrayIndexOutOfBoundsException e) {
			Debug.Log("The pointer you are trying to access is NULL!");
		}
	}
}
