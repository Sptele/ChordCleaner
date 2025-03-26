package theory;

import chords.Chord;
import chords.Note;

import java.util.Iterator;
import java.util.TreeSet;

public class Theorist {
	private Chord chord;
	private TreeSet<TheoristNote> notes;

	public Theorist(Chord chord) {
		this.chord = chord;

		this.notes = getNotesWithScaleDegrees();
	}

	public Chord getChord() {
		return chord;
	}

	public TreeSet<TheoristNote> getNotes() {
		return notes;
	}

	public TreeSet<TheoristNote> getNotesWithScaleDegrees() {
		TreeSet<Note> notes = chord.getNotes();

		if (notes.isEmpty()) return new TreeSet<>();

		TreeSet<TheoristNote> conv = new TreeSet<>();

		Iterator<Note> iter = notes.iterator();

		TheoristNote root = new TheoristNote(iter.next());
		root.setScaleDegree(0);

		conv.add(root);


		while (iter.hasNext()) {
			TheoristNote curr = new TheoristNote(iter.next());
			curr.setScaleDegree(root);

			conv.add(curr);
		}

		return conv;
	}

	public String getChordValue() {
		if (notes.isEmpty()) return ""; // empty

		// Interval
		if (chord.density() == 2) {
			if (notes.last().getScaleDegree() == 6) return "Tritone";

			return notes.last().getScaleDegreeToString();
		}

		StringBuilder chordStr = new StringBuilder(chord.root().getNote() + "" + chord.root().getAccidental());

		TheoristPredicates predicates = new TheoristPredicates(intervals());

		// Custom Ordering scheme for different elements

		if (predicates.hasFifth()) { // Has a P5 -> W chord
			if (predicates.hasThird()) {

				// Third
				if (predicates.hasMinorThird()) { // Minor
					chordStr = new StringBuilder(chordStr.toString().toLowerCase()); // Minor chord

					if (predicates.hasFlatFive()) chordStr.append("°");
				} else { // Major
					if (predicates.hasAugmentedFifth()) chordStr.append("+");
					if (predicates.hasFlatFive()) chordStr.append("(b5)");
				}


				if (predicates.getInterval(2)) {
					chordStr.append("(add2)");
				}

				if (predicates.getInterval(5)) {
					chordStr.append("(add4)");
				}


			} else {
				if (predicates.getInterval(2)) {
					chordStr.append(" sus2");
				}

				if (predicates.getInterval(5)) {
					chordStr.append(" sus4");
				}
			}


			if (predicates.getInterval(10)) chordStr.append("7");
			if (predicates.getInterval(11)) chordStr.append("maj7");

			if (predicates.getInterval(1)) chordStr.append("(b9)");
			if (predicates.getInterval(8)) chordStr.append("(b13)");
			if (predicates.getInterval(9)) chordStr.append("(add6)");
		} else {
			// No Fifth --> All notes are add noites
			for (Note note : notes) {
				chordStr.append("add").append(note.getAccidental());
			}
		}



		return chordStr.toString();
	}

	private boolean[] intervals() {
		boolean[] has = new boolean[12];

		for (TheoristNote note : notes) {
			has[note.getScaleDegree() % 12] = true;
		}

		return has;
	}

}
