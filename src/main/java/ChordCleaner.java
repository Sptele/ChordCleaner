import chords.Accidental;
import chords.Chord;
import chords.Note;
import theory.Theorist;

public class ChordCleaner {
	public static void main(String[] args) {


		Chord c = new Chord();

		c.addNote(new Note('C', Accidental.NATURAL, 3));
		c.addNote(new Note('D', Accidental.FLAT, 3));
		c.addNote(new Note('D', Accidental.NATURAL, 3));
		c.addNote(new Note('E', 3));
		c.addNote(new Note('F', Accidental.NATURAL, 3));
		c.addNote(new Note('G', Accidental.FLAT, 3));
		c.addNote(new Note('A', 3));
		c.addNote(new Note('B', Accidental.FLAT, 3));

		Theorist theory = new Theorist(c);

		System.out.println(theory.getChordValue());
		System.out.println(c);

		System.out.println();

		c.clear();

		String[] notes = { "Bb3", "Db4", "F4", "F#4", "A4", "Ab4" };
		for (String note : notes) {
			c.addNote(interpret(note));
		}

		theory = new Theorist(c);

		System.out.println(theory.getChordValue());
		System.out.println(c);

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
