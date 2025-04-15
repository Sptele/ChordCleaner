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

		if (chord.density() == 1) return notes.first().toString();

		// Interval
		if (chord.density() == 2)
			return notes.last().getScaleDegree() != 6 ? notes.last().getScaleDegreeToString() : "Tritone";

		/* Logic */

		ChordStringBuilder chordStr = new ChordStringBuilder(chord.root().getNote() + "" + chord.root().getAccidental());
		TheoristPredicates predicates = new TheoristPredicates(intervals()); // Descriptive methods to check

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


		// Sevens
		if (predicates.hasSeven()) {
			boolean dom = predicates.hasDominantSeven();

			// Major takes priority
			if (dom && predicates.hasMajorSeven()) {
				dom = false;
			}

			// 7, 9, 11, 13 dom / maj chords
			if (predicates.hasNine()) {
				if (predicates.hasEleven()) {
					if (predicates.hasThirteen()) chordStr.append(SEVENTH.of(dom, 13));
					else chordStr.append(SEVENTH.of(dom, 11));
				} else chordStr.append(SEVENTH.of(dom, 9));
			} else chordStr.append(SEVENTH.of(dom, 7));
		}

		additions(predicates, chordStr);
		extensions(predicates, chordStr);

		return chordStr.toString();
	}

	private void extensions(TheoristPredicates predicates, ChordStringBuilder chordStr) {
		// Flats

		if (predicates.hasFlatNine() || predicates.hasMinorSecond()) chordStr.append(FLAT.of(9));
		if (predicates.hasFlatEleven()) chordStr.append(FLAT.of(11));
		if (predicates.hasFlatThirteen()) chordStr.append(FLAT.of(13));

		// Sharps

		if (predicates.hasSharpNine()) chordStr.append(SHARP.of(9));
		if (predicates.hasSharpEleven()) chordStr.append(SHARP.of(11));
		if (predicates.hasSharpThirteen()) chordStr.append(SHARP.of(13));
	}

	private void additions(TheoristPredicates predicates, ChordStringBuilder chordStr) {
		if (predicates.hasMajorSix()) chordStr.append(ADDITIONS.of(6)); // Six

		// Add/Sus 2/4
		ChordElementPriority priority = predicates.hasThird() ? ADDITIONS : SUSPENSION;

		if (predicates.hasMajorSecond()) {
			chordStr.append(priority.of(2));
		}

		if (predicates.hasFourth()) {
			chordStr.append(priority.of(4));
		}

		if (predicates.hasDominantSeven() && predicates.hasMajorSeven()) {
			chordStr.append(ADDITIONS.of(7));
		}


		// b7
		if (predicates.hasDominantSeven() && predicates.hasMajorSeven()) {
			chordStr.append(ADDITIONS.of("b7"));
		}

		// 9 (no 7), 11, 13

		if (predicates.hasNine() && !predicates.hasSeven()) {
			chordStr.append(ADDITIONS.of(9));
		}

		if (predicates.hasEleven() && !(predicates.hasSeven() && predicates.hasNine())) {
			chordStr.append(ADDITIONS.of(11));
		}

		if (predicates.hasThirteen() && !(predicates.hasSeven() && predicates.hasNine() && predicates.hasEleven())) {
			chordStr.append(ADDITIONS.of(13));
		}


	}

	private boolean[] intervals() {
		boolean[] has = new boolean[99];

		for (ScaleDegree note : notes) {
			has[note.getScaleDegree() % 24 /* clamp to two octaves */] = true;
		}

		return has;
	}

	public void clear() {
		chord.clear();
		notes.clear();
	}

}
