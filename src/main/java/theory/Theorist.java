package theory;

import chords.Chord;
import chords.Note;

import java.util.Iterator;
import java.util.TreeSet;


import static theory.ChordElementPriority.*;

public class Theorist {
	private Chord chord;
	private TreeSet<ScaleDegree> notes;

	public Theorist(Chord chord) {
		this.chord = chord;

		this.notes = getNotesWithScaleDegrees();
	}

	public Chord getChord() {
		return chord;
	}

	public TreeSet<ScaleDegree> getNotes() {
		return notes;
	}

	public TreeSet<ScaleDegree> getNotesWithScaleDegrees() {
		TreeSet<Note> notes = chord.getNotes();

		if (notes.isEmpty()) return new TreeSet<>();

		TreeSet<ScaleDegree> conv = new TreeSet<>();

		Iterator<Note> iter = notes.iterator();

		ScaleDegree root = new ScaleDegree(iter.next());
		root.setScaleDegree(0);

		conv.add(root);


		while (iter.hasNext()) {
			ScaleDegree curr = new ScaleDegree(iter.next());
			curr.setScaleDegree(root);

			conv.add(curr);
		}

		return conv;
	}

	public String getChordValue() {
		/* Base Cases */

		// Empty
		if (notes.isEmpty()) return "";

		// Interval
		if (chord.density() == 2)
			return notes.last().getScaleDegree() != 6 ? notes.last().getScaleDegreeToString() : "Tritone";

		/* Logic */

		ChordStringBuilder chordStr = new ChordStringBuilder(chord.root().getNote() + "" + chord.root().getAccidental());

		TheoristPredicates predicates = new TheoristPredicates(intervals()); // Descriptive methods to check

		if (predicates.hasFifth()) { // Has a P5 -> W chord

			// Minor Third
			if (predicates.hasMinorThird()) {
				chordStr.pollToLowerCase(); // Show Minor by lower case
			}

			// Augmented Fifth / b13
			if (predicates.hasAugmentedFifth()) { // Major + Augmented Fifth
				chordStr.append(
						!predicates.hasPerfectFifth() // No Perfect Fifth
								&& !predicates.hasFlatFive() // No Flat Five
								&& (!predicates.hasMinorThird() && predicates.hasMajorThird()) // Major Third
								? AUGMENT.of("+")
								: FLAT.of(13)
				);
			}

			// Flat 5 or Diminished Five based on Third
			if (predicates.hasFlatFive()) {
				ChordStringBuilder.ChordElement flatFive =
						predicates.hasMinorThird() && !predicates.hasPerfectFifth()
								? AUGMENT.of("°")
								: FLAT.of(5);

				chordStr.append(flatFive);
			}

			// Add/Sus 2/4
			ChordElementPriority priority = predicates.hasThird() ? ADDITIONS : SUSPENSION;

			if (predicates.getInterval(2)) {
				chordStr.append(priority.of(2));
			}

			if (predicates.getInterval(5)) {
				chordStr.append(priority.of(4));
			}

			// Sevens
			if (predicates.hasSeven()) {
				if (predicates.hasDominantSeven()) { // Takes priority --> auto added
					chordStr.append(SEVENTH.of(7));
				}

				if (predicates.hasMajorSeven()) { // becomes add7 if dominant seven exists
					ChordStringBuilder.ChordElement seven =
							!predicates.hasDominantSeven()
							? SEVENTH.of("maj7")
							: ADDITIONS.of(7);

					chordStr.append(seven);
				}
			}

			if (predicates.hasMinorSecond()) chordStr.append(FLAT.of(9)); // b9
			if (predicates.hasMajorSix()) chordStr.append(ADDITIONS.of(6)); // Six
		} else {
			// No Fifth --> All notes are add notes
			for (ScaleDegree note : notes) {
				// TODO: FIX | chordStr.append("add", 5).append(note.getScaleDegree(), 5);
			}
		}

		return chordStr.toString();
	}

	private boolean[] intervals() {
		boolean[] has = new boolean[12];

		for (ScaleDegree note : notes) {
			has[note.getScaleDegree() % 12] = true;
		}

		return has;
	}

	public void clear() {
		chord.clear();
		notes.clear();
	}

}
