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
		String inp;

		System.out.println("Welcome to ChordCleaner, a chord analysis program designed to analyze a given set of notes and return their chord!");
		System.out.println("To enter chords, enter each note on the same line, separated by a space. Hit enter to analyze the chord. To exit, type 'exit'");
		System.out.println("Each note should be entered as <pitch><accidental><octave>:");
		System.out.println("- Valid pitches include A, B, C, D, E, F, G");
		System.out.println("- Valid accidentals include ♭♭, bb, ♭, \"\", ♮, #, ##");
		System.out.println("- Valid octave ranges from 0-9");
		System.out.println();

		while (true) {
			Chord c = new Chord();

			System.out.print(">>> ");

			inp = scan.nextLine();

			if (inp.equals("exit")) return;

			for (String note : inp.split(" ")) {
				if (note.length() < 2 || note.length() > 4) {
					System.out.println("Each note should be entered as <pitch><accidental><octave>! Notes must have a length of 2-4 characters (inclusive).");
					continue;
				}

				if (!Character.isDigit(note.charAt(note.length()-1))) {
					System.out.println("Must contain an octave!");
					continue;
				}

				int o = Integer.parseInt(note.charAt(note.length()-1)+"");

				if (o < 0 || o > 10) {
					System.out.println("Octaves can only go from 0-10 (exclusive)!");
					continue;
				}

				if (lacksNote(note.charAt(0))) {
					System.out.println("Must be a valid musical pitch!");
					continue;
				}

				if (note.length() > 2 && Accidental.from(note.substring(1, note.length() == 3 ? 2 : 3)) == null) { // has accidental
					System.out.println("Must have a valid accidental (allows double flats/sharps)!");

					continue;
				}

				c.addNote(interpret(note));
			}

			Theorist theory = new Theorist(c);

			System.out.println(theory.getChordValue());
			System.out.println(theory.getNotes().toString());

			System.out.println();

		}

	}

	private static boolean lacksNote(char note) {
		boolean found = false;

		for (char c : Chord.VALID_NOTES) {
			if (note == c) {
				found = true;
				break;
			}
		}

		return !found;
	}

	/**
	 * Converts notes to Note object, following (note)(accidental)(octave) convention.
	 * @param note A string of the note following (note)(accidental)(octave) convention.
	 * @return A {@chords.Note} object
	 */
	public static Note interpret(String note) {
		char n = note.substring(0, 1).toUpperCase().charAt(0);

		Accidental a = Accidental.from(switch (note.length()) {
			case 2 -> "";
			case 3 -> note.substring(1, 2);
			case 4 -> note.substring(1, 3);
			default -> throw new IllegalStateException("Unexpected accidental: " + note.length());
		});

		int octave = Integer.parseInt(note.substring(note.length()-1));

		return new Note(n, a, octave);

	}


}
