
public class Main {
	public static void main(String[] args) {

		HorseRacingArena horseRace;	
		boolean isHealthyHorsesCountValid = false;	

		do {
			int numberOfHorses = Utility.getIntegerInput("Enter number of horses:", 1, 100);
			horseRace = new HorseRacingArena(numberOfHorses, 10, 10, 100);

			if (horseRace.getHealthyHorsesSize() >= 10) {
				isHealthyHorsesCountValid = true;
			} 
			else {
				System.out.println("Number of healthy horses is less than 10");
			}

		} while (!isHealthyHorsesCountValid);

		System.out.println("Horses: " + horseRace.getRaceState());
		horseRace.startRace();
	}	
}