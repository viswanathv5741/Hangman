import java.util.Scanner;

public class Hangman_Tester {
	public static void main(String[] args) {
		Scanner keyboard = new Scanner(System.in);
		
		System.out.println("Give me a number");
		int elements = keyboard.nextInt();
		
		Hangman test = new Hangman(elements);
		test.Play();
	}
}
