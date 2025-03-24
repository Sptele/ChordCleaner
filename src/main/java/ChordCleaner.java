public class ChordCleaner {
	public static void main(String[] args) {


		Chord c = new Chord();

		c.addNote(new Note('C', Accidental.FLAT, 3));
		c.addNote(new Note('D', 3));
		c.addNote(new Note('E', 3));
		c.addNote(new Note('F', 3));
		c.addNote(new Note('G', 3));
		c.addNote(new Note('A', 3));
		c.addNote(new Note('B', 3));

		System.out.println(c.toString());

	}


}
