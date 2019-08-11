/**
 * info1103 - assignment 3
 * Paras Gathani
 * pgat6537
 */
import java.util.*;
public class Simulation {

	//
	// TODO
	//

	private int width;
	private int height;
	private Tree[][] trees;
	public int day = 1;
	private String wind = "none";

	public Simulation(int width, int height, int seed) {
		this.width = width;
		this.height = height;

		trees = new Tree[height][width];
		generateTerrain(seed);
	}

	public void statusCommand(){
    System.out.println("Day: " + day);
    System.out.println("Wind: " + wind);
  }

	public void nextCommand() {
		day++;
		if(wind.equals("none")){
			for(int i = 0; i < height; i++){
				for(int j = 0; j < width; j++){
					if(trees[i][j].getFire() > 0){
						trees[i][j].setFire(1);
					}
				}
			}
		}
		/*if(wind.equals("east")){
			windEast();
		}*/

	}

	//public void windEast()

	public void nextCommand(int num) {
		for(int i = 0; i < num; i++){
			nextCommand();
		}
	}


	public void showCommand(String line) {

		String command = line.split(" ")[1].toLowerCase();

		//Shows height
		if(command.equals("height")) {//SHOW HEIGHT
			System.out.print("+");
			for(int count = 1; count < width*2; count++)
				System.out.print("-");
			System.out.println("+");
			for(int i = 0; i < height; i++) {
				for(int j = 0; j < width; j++) {
					if(j == 0)
						System.out.print("|");
					if(trees[i][j].getHeight() == 0) {
						if(j == width-1) {
							System.out.print(" |");
							break;
						}else{
							System.out.print("  ");
						}
					}
					else if(j == width - 1){
						System.out.print(trees[i][j].getHeight());
					}else{
						System.out.print(trees[i][j].getHeight() + " ");
					}
					if(j == width-1)
						System.out.print("|");
				}
				System.out.println();
			}
			System.out.print("+");
			for(int count = 1; count < width*2; count++)
				System.out.print("-");
			System.out.println("+");
		}
		else if(command.equals("fire")){//showing fire
			System.out.print("+");
			for(int count = 1; count < width*2; count++)
				System.out.print("-");
			System.out.println("+");

			for(int i = 0; i < height; i++) {
				for(int j = 0; j < width; j++) {
					if(j == 0){
						System.out.print("|");
					}
					if(trees[i][j].getFire() >= 1){
						if(j == width - 1)
							System.out.print(trees[i][j].getFire());
						else{
							System.out.print(trees[i][j].getFire() + " ");
						}
						if(j == width - 1){
							System.out.print("|");
						}
						continue;
					}
					if(trees[i][j].getHeight() == 0) {
						if(j == width-1) {
							System.out.print(" |");
							break;
						}else{
							System.out.print("  ");
						}
					}
					else if(j == width - 1){
						if(trees[i][j].getFire() >= 1){
							if(j == width-1){
								System.out.print("|");
							}
							continue;
						}
						System.out.print(".");
					}else{
						System.out.print(". ");
					}
					if(j == width-1)
						System.out.print("|");
				}
				System.out.println();
			}
			System.out.print("+");
			for(int count = 1; count < width*2; count++)
				System.out.print("-");
			System.out.println("+");
		}
		else{
			System.out.println("Invalid command");
			return;
		}



	}
	public void fireCommand(){
		//if(tree)

		for(int i = 0; i < this.height; i++){
			for(int j = 0; j < this.width; j++){
				if(trees[i][j].getHeight() == 0 || trees[i][j].getFire() > 0){
					continue;
				}
				if(trees[i][j].getAllBurning()){
					System.out.println("No fires were started");
					return;
				}
				trees[i][j].setFire();
				trees[i][j].allBurning();
			}
		}
	}

	public void fireCommand(int horiz, int vert){
		try{
			if(trees[vert][horiz].getFire() > 0 || trees[vert][horiz].getHeight() == 0){
				System.out.println("No fires were started");
				return;
			}else{
				System.out.println("Started a fire");
				trees[vert][horiz].setFire();
			}
		}catch(ArrayIndexOutOfBoundsException e){
			System.out.print("Invalid command");
			return;
		}
	}

	public void fireCommand(int horiz, int vert, int horizReg, int vertReg){
		/*if(trees[vert][horiz].getFire() > 0 || trees[vert][horiz]])
			System.out.println("Started a fire");*/
		try{
			for(int i = 0; i < vertReg; i++){
				for(int j = 0; j < horizReg; j++){
						if(trees[vert + i][horiz + j].getHeight() == 0){
							continue;
						}
						trees[vert + i][horiz + j].setFire();
						trees[vert + i][horiz + j].fireReg();
				}
			}
		}catch(ArrayIndexOutOfBoundsException e){
			System.out.println("Invalid command");
			return;
		}
	}

	public void extinguishCommand(){//Extinguish all
		//if(tree)
		int count = 0;

		for(int i = 0; i < this.height; i++){
			for(int j = 0; j < this.width; j++){
				if(trees[i][j].getFire() > 0){
					trees[i][j].removeFire();
					count++;
				}
			}
		}
		if(count == 0){
			System.out.println("No fires to extinguish");
		}else{
			System.out.println("Extinguished fires");
		}
	}

	public void extinguishCommand(int horiz, int vert){
		if(trees[vert][horiz].getFire() == 0 ){
			System.out.println("No fires to extinguish");
			return;
		}else{
			System.out.println("Extinguished fires");
			trees[vert][horiz].removeFire();
		}
	}


	public void extinguishCommand(int horiz, int vert, int horizReg, int vertReg){
		/*if(trees[vert][horiz].getFire() > 0 || trees[vert][horiz]])
			System.out.println("Started a fire");*/
		try{
			for(int i = 0; i < vertReg; i++){
				for(int j = 0; j < horizReg; j++){
						if(trees[vert + i][horiz + j].getHeight() == 0){
							continue;
						}
						trees[vert + i][horiz + j].removeFire();
				}
			}
		}catch(ArrayIndexOutOfBoundsException e){
			System.out.println("Invalid command");
			return;
		}
	}

	public static void windCommand(String direction){
		/*ArrayList<Integer> coordinates = new ArrayList<Integer>();

		for(int i = 0; i < height; i++){
			for(int j = 0; j < width; j++){
				if(trees[i][j].getFire() > 0){
					coordinates.add(i);
					coordinates.add(j);
				}
			}
		}
		switch(direction){
			case "all":

		}*/
	}

	private void generateTerrain(int seed) {

		// ###################################
		// ### DO NOT MODIFY THIS FUNCTION ###
		// ###################################

		Perlin perlin = new Perlin(seed);
		double scale = 10.0 / Math.min(width, height);

		for (int y = 0; y < height; y++) {
			for (int x = 0; x < width; x++) {
				double height = perlin.noise(x * scale, y * scale, 0.5);
				height = Math.max(0, height * 1.7 - 0.7) * 10;
				trees[y][x] = new Tree((int) height);
			}
		}
	}

}

