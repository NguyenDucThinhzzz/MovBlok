package MovBlok.Scripts;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.NoSuchElementException;
import java.util.Scanner;

import MovBlok.Objects.*;
import UnifyEngine.Debug;
import UnifyEngine.Grid;

public class DataHandler {
	private PrintWriter prt;
	private Scanner scn;
	private File file = null;
	
	public DataHandler() {

	}
	public DataHandler(String _filePath) {
		file = new File(_filePath);
	}
	
	public void setDataFile(String _filePath) {
		file = new File(_filePath);
	}
	
	public void openFileRead(){
		try {
			scn = new Scanner(file);
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			Debug.LogError("Your file directory is either NULL or wrong!");
		}
	}
	public void openFileWrite(){
		try {
			prt = new PrintWriter(file);
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			Debug.LogError("Your file directory is either NULL or wrong!");
		}
	}
	
	public void closeFile() {
		if(prt!=null)
			prt.close();
		if(scn!=null)
			scn.close();
	}
	
	public Grid ReadGridData(){
		try {
			int x,y;
			//Get x,y of the playing field
			x = scn.nextInt();
			y = scn.nextInt();
			Grid grd = new Grid(x,y);
			//Read the file data
			for(int i=0;i<y;i++) {
				for(int j=0;j<x;j++) {
					switch(scn.nextInt()) {
					case 0:
						
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
			return grd;
		} 
		catch (NullPointerException e) {
			Debug.LogError("You must use openFile() first!");
			e.printStackTrace();
			return null;
		}
		catch(NoSuchElementException e) {
			Debug.LogError("The File is coruppted!");
			e.printStackTrace();
			return null;
		}
	}
	public Player ReadPlayerData() {
		try {
			int x = scn.nextInt();
			int y = scn.nextInt();
			scn.close();
			return (new Player(x,y));
		} catch (NullPointerException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return null;
		}
		catch(NoSuchElementException e) {
			Debug.LogError("The File is coruppted!");
			e.printStackTrace();
			return null;
		}
	}
	
	public void WriteFileData(Grid grd, Player plr) {
		
		try {
			int x = grd.getBoundX();
			int y = grd.getBoundY();
			prt.println(x+" "+y);
			for(int i=0;i<y;i++) {
				for(int j=0;j<x;j++) {
					if(grd.getObj(j, i)== null) 
						prt.print(-1+" ");
					else 
						prt.print(grd.getObj(j, i).getID()+" ");
				}
				prt.println();
			}
			prt.print(plr.getPos().x+" "+plr.getPos().y);
		}
		catch (ArrayIndexOutOfBoundsException e) {
			Debug.LogError("The pointer you are trying to access is NULL!");
			e.printStackTrace();
		}
		catch (NullPointerException e) {
			Debug.LogError("You must use openFileWrite() first!");
			e.printStackTrace();
		}
	}
	
}
