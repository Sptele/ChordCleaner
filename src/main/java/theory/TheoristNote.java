package theory;

import chords.Accidental;
import chords.Note;

public class TheoristNote extends Note {

	private int scaleDegree; // # of semitones from root

	public static final String[] INTERVALS = {
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

	public TheoristNote() {
		this.scaleDegree = -1;
	}

	public TheoristNote(int scaleDegree) {
		this.scaleDegree = scaleDegree;
	}

	public TheoristNote(char note, int octave, int scaleDegree) {
		super(note, octave);
		this.scaleDegree = scaleDegree;
	}

	public TheoristNote(char note, Accidental accidental, int octave, int scaleDegree) {
		super(note, accidental, octave);
		this.scaleDegree = scaleDegree;
	}

	public TheoristNote(Note note) {
		this(note.getNote(), note.getAccidental(), note.getOctave(), -1);
	}

	public int getScaleDegree() {
		return scaleDegree;
	}

	public void setScaleDegree(int scaleDegree) {
		this.scaleDegree = scaleDegree;
	}

	public void setScaleDegree(TheoristNote root) {
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


//	public boolean has(int interval) {
//		for (chords.Note note : notes) {
//			if (note.getScaleDegree() == interval) return true;
//		}
//
//		return false;
//	}
//
//	public List<chords.Note> ofIntervalRange(int max) {
//		return ofIntervalRange(1, max);
//	}
//
//	public List<chords.Note> ofIntervalRange(int min, int max) {
//		return notes.stream().filter((note -> min <= note.getScaleDegree() && note.getScaleDegree() < max)).collect(Collectors.toList());
//	}


	@Override
	public boolean equals(Object o) {
		if (this == o) return true;
		if (o == null || getClass() != o.getClass()) return false;
		if (!super.equals(o)) return false;

		TheoristNote that = (TheoristNote) o;

		return scaleDegree == that.scaleDegree;
	}

	@Override
	public int hashCode() {
		int result = super.hashCode();
		result = 31 * result + scaleDegree;
		return result;
	}

	@Override
	public String toString() {
		return super.toString() + (getScaleDegree() > 0 ? String.format("(%s)", getScaleDegreeToString()) : "");
	}
}
