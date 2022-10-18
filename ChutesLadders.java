import java.util.*;
public class ChutesLadders{
	int winner;
	int diceRoll;
	int position;
	int players;
	int games;
	int turns = 0;
	boolean loop = true;
	int[] positions;
	int[][] gameBoard = {
		{0, 38, 2, 3, 14, 5, 6, 7, 8, 31},
		{10, 11, 12, 13, 14, 15, 6, 17, 18, 19},
		{20, 42, 22, 23, 24, 25, 26, 27, 84, 29},
		{30, 31, 32, 33, 34, 35, 44, 37, 38, 39},
		{40, 41, 42, 43, 44, 45, 46, 26, 48, 11},
		{50, 67, 52, 53, 54, 55, 53, 57, 58, 59},
		{60, 61, 19, 63, 60, 65, 66, 67, 68, 69},
		{70, 90, 72, 73, 74, 75, 76, 77, 78, 79},
		{100, 81, 82, 83, 84, 85, 86, 24, 88, 89},
		{90, 91, 92, 73, 94, 76, 96, 97, 98, 99, 100}
	};
	
	public ChutesLadders(){
		runGame();
	}

	public void runGame(){
		Scanner userInput = new Scanner(System.in);
		System.out.println("How many players would you like to have? ");
		try{
			players = userInput.nextInt();
		}
		catch(Exception e) {}
		if(players > 0){
			positions = new int[players];
			for(int i = 0; i < positions.length; i++){
				positions[i] = 0;
			}
			while(true){
				for(int i = 0; i < players; i++){
					runTurn(i);
					turns++;
					System.out.println();
					if(positions[i] == 100){
						winner = i + 1;
						break;
					}
				}
				if(winner != 0){
					System.out.println(String.format("P%d has won! Whoop Whoop! It took %d turns to complete", winner, turns));
					loop = false;
					break;
				}
			}
		}
		else if(players <= 0){
			System.out.println("QUALITY CONTROL");
		}
	}

	public void runTurn(int index){
		int tensPlace;
		int onesPlace;
		diceRoll = (int)(Math.random() * 6) + 1;
		System.out.println(String.format("P%d current positions %d", index + 1, positions[index]));
		System.out.println(String.format("P%d rolled a %d", index + 1, diceRoll));
		position = positions[index] + diceRoll;
		if(position > 100){
			System.out.println(String.format("P%d overshot the goal and didnt move", index + 1));
			return;
		}
		else if(position == 100){
			positions[index] = 100;
			return;
		}
		else if(position >= 10){
			tensPlace = position / 10;
			onesPlace = position - (tensPlace * 10);
		}
		else{
			tensPlace = 0;
			onesPlace = position;
		}
		positions[index] = gameBoard[tensPlace][onesPlace];
		if(positions[index] < position){
			System.out.println(String.format("P%d landed on a chute and moved down to %d", index + 1, positions[index]));
		}
		else if(positions[index] > position){
			System.out.println(String.format("P%d landed on a ladder and moved up to %d", index + 1, positions[index]));
		}
		else{
			System.out.println(String.format("P%d is now on %d", index + 1, positions[index]));
		}
	}
}