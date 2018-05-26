import java.awt.GridLayout;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Stack;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
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
		panel.addKeyListener(this);
		frame.addKeyListener(this);
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
			int interval = (int)(Math.random()*500);
			while(file.hasNextLine() && index < x){
				words.add(file.nextLine());
				index ++;
				for (int i=0; i<interval; i++) {
					file.nextLine();
				}
			}
			//System.out.println(words.size());
			for (int i=0; i<x; i++) {
				int rand = (int) (Math.random()*words.size());
				guess.push(words.get(rand));
				words.remove(rand);
//				System.out.println(words.size());
//				System.out.println(i);
			}
		} catch (FileNotFoundException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
	}
	
	public void Play() {
		correct = guess.pop();
		//System.out.println(correct);
		for (int j=0; j<correct.length(); j++) {
			display += "_";
		}
		word_guess.setText(display);
	}

	@Override
	public void keyTyped(KeyEvent e) {
		// TODO Auto-generated method stub
	}

	@Override
	public void keyPressed(KeyEvent e) {
		// TODO Auto-generated method stub
		System.out.println("hello from keyPressed");
		int changes = 0;
		for (int i=0; i<correct.length(); i++) {
			if (correct.charAt(i) == (e.getKeyChar())) {
				display = display.substring(0, i) + correct.substring(i,i+1) + display.substring(i+1);
				word_guess.setText(display);
				changes ++;
			}
		}
		if (changes == 0) {
			live --;
			lives.setText("You have " + live + " lives left.");
		}
		if (display.equals(correct)) {
			JOptionPane.showMessageDialog(frame, "YOU GOT IT");
			solve ++;
			live = 9;
			display = "";
			Play();
			lives.setText("You have " + live + " lives left.");
			solved.setText("You have solved " + solve + " words.");
		}
		if (live == 0) {
			instructions.setText("You Lost");
			display = "";
			word_guess.setText(display);
		}

	}

	@Override
	public void keyReleased(KeyEvent e) {
		// TODO Auto-generated method stub
		
	}
}
