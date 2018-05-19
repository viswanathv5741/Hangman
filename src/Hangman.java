import java.awt.GridLayout;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Stack;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

import java.util.Scanner;

public class Hangman implements KeyListener {
	private ArrayList<String> words;
	private Stack<String> guess;
	private String correct;
	private String display;
	private int solve;
	private int live;
	private JFrame frame = new JFrame();
	private JPanel panel = new JPanel();
	private JLabel instructions = new JLabel("Enter a letter:");
	private JLabel word_guess = new JLabel("");
	private JLabel lives = new JLabel("You have 9 lives left.");
	private JLabel solved = new JLabel("You have solved 0 words.");
	
	public Hangman(int x) {
		panel.setLayout(new GridLayout(4, 1));
		panel.add(instructions);
		panel.add(word_guess);
		panel.add(lives);
		panel.add(solved);
		frame.add(panel);
		frame.setVisible(true);
		frame.pack();
		instructions.addKeyListener((KeyListener)this);
		words = new ArrayList<String>();
		guess = new Stack<String>();
		guess.setSize(x);
		solve = 0;
		live = 9;
		correct = "";
		display = "";
		try {
			Scanner file = new Scanner(new File("dictionary.txt"));
			int index = 0;
			while(file.hasNextLine() && index < x){
				words.add(file.nextLine());
				index ++;
			}
			System.out.println(words.size());
			for (int i=0; i<words.size(); i++) {
				int rand = (int) (Math.random()*words.size());
				guess.push(words.get(rand));
				i--;
			}
		} catch (FileNotFoundException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
	}
	
	public void Play() {
		correct = guess.pop();
		for (int j=0; j<correct.length(); j++) {
			display += "_";
		}
		word_guess.setText(display);
	}

	@Override
	public void keyTyped(KeyEvent e) {
		// TODO Auto-generated method stub
		int changes = 0;
		for (int i=0; i<correct.length(); i++) {
			if (correct.charAt(i) == (e.getKeyChar())) {
				display = display.substring(0, i) + correct.charAt(i) + display.substring(i+1);
				for (int x=0; x<display.length(); x++) {
					if (display.charAt(x) == '_'){
						changes ++;
					}
				}
			}
			else {
				live --;
			}
		}
		if (changes == 0) {
			solve ++;
			live = 9;
			Play();
		}
		if (live == 0) {
			panel.removeAll();
		}
	}

	@Override
	public void keyPressed(KeyEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void keyReleased(KeyEvent e) {
		// TODO Auto-generated method stub
		
	}
}
