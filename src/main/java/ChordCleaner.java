import chords.Accidental;
import chords.Chord;
import chords.Note;
import theory.Theorist;

public class ChordCleaner {
	public static void main(String[] args) {


		Chord c = new Chord();

		c.addNote(new Note('C', Accidental.NATURAL, 3));
		c.addNote(new Note('D', 3));
		c.addNote(new Note('E', 3));
		c.addNote(new Note('F', 3));
		c.addNote(new Note('G', 3));
		c.addNote(new Note('A', 3));
		c.addNote(new Note('B', 3));

		Theorist theory = new Theorist(c);

		System.out.println(theory.getChordValue());
		System.out.println(c);

	}


}
