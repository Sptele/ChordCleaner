/**
 * Represents a Musical note, containing both a pitch (note name + accidental) and octave
 */
public class Note implements Comparable<Note> {
	private char note;
	private Accidental accidental;
	private int octave;
	private int scaleDegree; // # of semitones from root

	public final String[] INTERVALS = {
			"P1",  // Unison - 0
			"m2",  // Minor 2nd - 1
			"M2",  // Major 2nd - 2
			"m3",  // Minor 3rd - 3
			"M3",  // Major 3rd - 4
			"P4",  // Perfect 4th -5
			"A4/d5", // Augmented 4th / Diminished 5th (Tritone) - 6
			"P5",  // Perfect 5th - 7
			"m6",  // Minor 6th - 8
			"M6",  // Major 6th - 9
			"m7",  // Minor 7th - 10
			"M7",  // Major 7th - 11
			"P8"   // Octave - 12
	};


	/**
	 * Default Note is a tuning A4 (440hz)
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
		this.scaleDegree = -1; // undefined, updated by Chord
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

	public int getScaleDegree() {
		return scaleDegree;
	}

	public void setScaleDegree(int scaleDegree) {
		this.scaleDegree = scaleDegree;
	}

	public void setScaleDegree(Note root) {
		setScaleDegree(
				compareTo(root)
		);
	}

	public String getScaleDegreeToString() {
		int clampedToOctave = scaleDegree % 12;
		String octaveMark = "";

		if (scaleDegree > 12) octaveMark = "+" + scaleDegree/12;

		return INTERVALS[clampedToOctave] + octaveMark;
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
		return note + accidental.toString() + octave + (getScaleDegree() > 0 ? String.format("(%s)", getScaleDegreeToString()) : "");
	}

	public int toMIDI() {
		int adjustedNotePitch = note + ((note == 'A' || note == 'B') ? 7 : 0) -67 ; // ASCII puts A and B below the rest

		int pitchClass = 2 * adjustedNotePitch + accidental.pitchMod();

		if (pitchClass > 4) pitchClass--;

		return 12 * (getOctave() + 1) + pitchClass;
	}

}
