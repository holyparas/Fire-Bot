/**
 * info1103 - assignment 3
 * Paras Gathani
 * pgat6537
 */

import java.util.Scanner;

public class Firebot {

	//
	// TODO
	//

  private static Scanner scan;
  private static Simulation sim;
  private static int seed;
  private static int width;
  private static int height;

  public static void init(String[] args) {
    if(args.length != 3){
        System.out.println("Usage: java Firebot <seed> <width> <height>");
        System.exit(1);
    }
    try{
      seed = Integer.parseInt(args[0]);
    }catch(NumberFormatException e) {
      System.out.println("Usage: java Firebot <seed> <width> <height>");
      System.exit(1);
    }
    if(seed < 0) {
      System.out.println("Usage: java Firebot <seed> <width> <height>");
      System.exit(1);
    }
    try{
      width = Integer.parseInt(args[1]);
    }catch(NumberFormatException e) {
      System.out.println("Usage: java Firebot <seed> <width> <height>");
      System.exit(0);
    }
    if(width < 1) {
      System.out.println("Usage: java Firebot <seed> <width> <height>");
      System.exit(1);
    }
    try{
      height = Integer.parseInt(args[2]);
    }catch(NumberFormatException e) {
      System.out.println("Usage: java Firebot <seed> <width> <height>");
      System.exit(1);
    }
    if(height < 1) {
      System.out.println("Usage: java Firebot <seed> <width> <height>");
      System.exit(1);
    }
    System.out.println("Day: 1");
    System.out.println("Wind: none");
    System.out.println();

  }

  public static void byeCommand() {
    System.out.print("bye\n");
    System.exit(1);
  }

  public static void helpCommand() {
    System.out.println("BYE");
    System.out.println("HELP\n");

    System.out.println("DATA");
    System.out.println("STATUS\n");

    System.out.println("NEXT <days>");
    System.out.println("SHOW <attribute>\n");

    System.out.println("FIRE <region>");
    System.out.println("WIND <direction>");
    System.out.println("EXTINGUISH <region>");
  }



  public static void nextCommand(String line){
    int num = Integer.parseInt(line.split(" ")[1]);
    sim.nextCommand(num);
    sim.statusCommand();
  }

  public static void nextCommand(){
    sim.nextCommand();
    sim.statusCommand();
  }

  public static void windCommand(String direction){
    String[] directions = {"all", "none","north","south","east","west"};
    for(String a : directions){
      if(a.equalsIgnoreCase(direction)){
        String wind = direction.toLowerCase();
        System.out.println("Set wind to " + wind);
        sim.windCommand(wind);
        return;
      }
    }
    printInvalid();
  }
  public static void printInvalid(){
    System.out.println("Invalid command");
  }

  public static void computeEngine() {
    scan = new Scanner(System.in);
    sim = new Simulation(width, height, seed);

    System.out.printf("> ");

		// Read from standard input until EOF
		while (scan.hasNextLine()) {
			// Process line
			String input = scan.nextLine();
      String[] commands = input.split(" ");
      commands[0] = commands[0].toLowerCase();

			switch (commands[0]) {
        case "":
					break;
				case "bye"://done
					byeCommand();
					break;
				case "help"://done
					helpCommand();
					break;
				case "data":
					//dataCommand(line);
					break;
				case "show"://not done yet
          if(commands.length > 2){
            printInvalid();
            break;
          }
					sim.showCommand(input);
					break;
        case "next":
          if(commands.length == 1){
            nextCommand();
            break;
          }else{
            nextCommand(input);
            break;
          }
        case "wind":
          sim.windCommand(commands[1]);
          break;
        case "extinguish":
        if(commands.length == 2){
          if(commands[1].toLowerCase().equals("all")){
            sim.extinguishCommand();
            break;
          }else{
            printInvalid();
            break;
          }
        }
        if(commands.length == 3){

          int startRow = Integer.parseInt(commands[1]);
          int startColumn = Integer.parseInt(commands[2]);

          sim.extinguishCommand(startRow, startColumn);
          break;
        }
        else if(commands.length == 5){
          int startRow = Integer.parseInt(commands[1]);
          int startColumn = Integer.parseInt(commands[2]);
          int rowRegion = Integer.parseInt(commands[3]);
          int columnRegion = Integer.parseInt(commands[4]);

          sim.extinguishCommand(startRow, startColumn, rowRegion, columnRegion);
          break;
        }
        else{
          printInvalid();
          break;
        }
				case "status":
					sim.statusCommand();
					break;
        case "fire": //what if both width and height are not specified
          if(commands.length == 2){
            if(commands[1].toLowerCase().equals("all")){
              sim.fireCommand();
              break;
            }else{
              printInvalid();
              break;
            }
          }
          if(commands.length == 3){

            int startRow = Integer.parseInt(commands[1]);
            int startColumn = Integer.parseInt(commands[2]);

            sim.fireCommand(startRow, startColumn);
            break;
          }
          else if(commands.length == 5){
            int startRow = Integer.parseInt(commands[1]);
            int startColumn = Integer.parseInt(commands[2]);
            int rowRegion = Integer.parseInt(commands[3]);
            int columnRegion = Integer.parseInt(commands[4]);

            sim.fireCommand(startRow, startColumn, rowRegion, columnRegion);
            break;
          }
          else{
            printInvalid();
            break;
          }

				default:
					printInvalid();
			}

			System.out.printf("\n> ");
		}
		System.out.printf("\nbye\n");
  }

	public static void main(String[] args) {
    init(args);
    computeEngine();


	}
  /*public static void showCommand(String line){
    sim.showCommand(line);
  }*/
}

