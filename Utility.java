
import java.util.Scanner;
import java.util.Random;
import java.util.List;

public class Utility {
	
	public static String EMPTY_STRING = "";

	public static int countOccurrence(String str, String substr) {

		int count = 0;
		int s = str.length() - substr.length();

		// substr length is longer than the string to be searched. Hence, return 0.
		if (s < 0) {
			return count;
		}

		for (int k = 0; k <= s; k++) {
			if (str.substring(k, k + substr.length()).equals(substr)) {
				count++;
			}
		}

		return count;
	}

	public static String getStringInput(String message, String... invalidSubstrings) {
		Scanner scanner = new Scanner(System.in);
		boolean isInputValidated = false;

		String input;

		loop:
		do {
			System.out.print(message + " ");
			input = scanner.nextLine();

			if (invalidSubstrings != null) {
				for (int i = 0, s = invalidSubstrings.length; i < s; i++) {
					if (input.contains(invalidSubstrings[i])) {
						System.out.printf(
							"Input contains invalid substring (%s)\n", invalidSubstrings[i]);
						continue loop;
					};
				}
			}

			isInputValidated = true;

		} while(!isInputValidated);

		return input;
	}

	public static int getIntegerInput(String message, int min, int max) {
		Scanner scanner = new Scanner(System.in);
		String onlyIntegerRegex = "-?\\d+";
		
		boolean isInputValidated = false;
		int intInput = 0;

		do {
			System.out.print(message + " ");
			String input = scanner.nextLine();

			if (input.matches(onlyIntegerRegex) && 
				(intInput = Integer.parseInt(input)) >= min && intInput <= max) {
				isInputValidated = true;
			}
			else {
				System.out.printf("Input should be an integer between %d (inclusive) and %d " + 
					"(inclusive)\n", min, max);				
			}

		} while (!isInputValidated);

		return intInput;
	}

	public static String getRandomString(int length, String symbols) {
		StringBuilder sb = new StringBuilder();

		Random rnd = new Random();

		for (int i = 0; i < length; i++) {
			int rndInt = rnd.nextInt(symbols.length());
			sb.append(symbols.charAt(rndInt));
		}

		return sb.toString();
	}

	public static String getAlphaNumericString(int length) {
		String symbols = "abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789";
		return getRandomString(length, symbols);
	}
}