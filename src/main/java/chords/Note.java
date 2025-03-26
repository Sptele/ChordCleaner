package chords;

/**
 * Represents a Musical note, containing both a pitch (note name + accidental) and octave
 */
public class Note implements Comparable<Note> {
	private char note;
	private Accidental accidental;
	private int octave;


	/**
	 * Default chords.Note is a tuning A4 (440hz)
	 */
	public Note() {
		this('A', 4);
	}

	public Note(char note, int octave) {
		this(note, Accidental.NATURAL, octave);
	}

	public Note(char note, Accidental accidental, int octave) {
		this.note = note;
		this.accidental = accidental;
		this.octave = octave;
	}

	public char getNote() {
		return note;
	}

	public void setNote(char note) {
		this.note = note;
	}

	public Accidental getAccidental() {
		return accidental;
	}

	public void setAccidental(Accidental accidental) {
		this.accidental = accidental;
	}

	public int getOctave() {
		return octave;
	}

	public void setOctave(int octave) {
		this.octave = octave;
	}


	// -1 if this object is less than
	// 0 if this object is equal
	// 1 if this object is greater than
	@Override
	public int compareTo(Note o) {
		return toMIDI() - o.toMIDI();
	}

	@Override
	public boolean equals(Object o) {
		if (this == o) return true;
		if (o == null || getClass() != o.getClass()) return false;

		Note note1 = (Note) o;

		if (note != note1.note) return false;
		if (octave != note1.octave) return false;
		return accidental == note1.accidental;
	}

	@Override
	public int hashCode() {
		int result = note;
		result = 31 * result + (accidental != null ? accidental.hashCode() : 0);
		result = 31 * result + octave;
		return result;
	}

	@Override
	public String toString() {
		return note + accidental.toString() + octave;
	}

	public int toMIDI() {
		int adjustedNotePitch = note + ((note == 'A' || note == 'B') ? 7 : 0) -67 ; // ASCII puts A and B below the rest

		int pitchClass = 2 * adjustedNotePitch + accidental.pitchMod();

		if (pitchClass > 4) pitchClass--;

		return 12 * (getOctave() + 1) + pitchClass;
	}

}
