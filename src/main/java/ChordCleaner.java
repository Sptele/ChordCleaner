import chords.Accidental;
import chords.Chord;
import chords.Note;
import theory.Theorist;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;

public class ChordCleaner {
	public static void main(String[] args) {


		Scanner scan = new Scanner(System.in);
		String inp = "";

		while (true) {
			System.out.println("Enter some chords. Type jazz to analyze the given chord. Type exit to leave.");

			Chord c = new Chord();

			inp = scan.nextLine();

			if (inp.equals("exit")) return;

			while (!inp.equals("jazz")) {
				c.addNote(interpret(inp));
				inp = scan.nextLine();
			}

			Theorist theory = new Theorist(c);

			System.out.println(theory.getChordValue());
			System.out.println(theory.getNotes().toString());

			System.out.println();

		}

	}

	/**
	 * Converts notes to Note object, following (note)(accidental)(octave) convention.
	 * @param note A string of the note following (note)(accidental)(octave) convention.
	 * @return A {@chords.Note} object
	 */
	public static Note interpret(String note) {
		char n = note.substring(0, 1).toUpperCase().charAt(0);
		Accidental a = Accidental.from(note.length() == 2 ? "" : note.substring(1, 2));
		int octave = Integer.parseInt(note.substring(note.length()-1));

		return new Note(n, a, octave);

	}


}
