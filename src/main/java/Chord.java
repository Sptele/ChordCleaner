import java.util.*;
import java.util.stream.Collectors;

public class Chord {
	private TreeSet<Note> notes;

	public Chord() {
		notes = new TreeSet<>();
	}

	public Chord(ArrayList<Note> notes) {
		this();

		this.notes.addAll(notes);
	}

	public Chord(TreeSet<Note> notes) {
		this.notes = notes;
	}

	public Chord(Chord c) {
		this(c.getNotes());
	}

	public TreeSet<Note> getNotes() {
		return notes;
	}

	public void addNote(Note n) {
		notes.add(n);
	}

	public String analyze() {
		if (notes.isEmpty()) return "";

		TreeSet<Note> copy = new TreeSet<>();

		Iterator<Note> iter = notes.iterator();

		Note root = iter.next();
		root.setScaleDegree(0);

		copy.add(root);


		while (iter.hasNext()) {
			Note curr = iter.next();
			curr.setScaleDegree(root);

			copy.add(curr);
		}

		notes = copy; // rewrite

		// Interval
		if (density() == 2) {
			if (notes.last().getScaleDegree() == 6) return "Tritone";

			return notes.last().getScaleDegreeToString();
		}

		StringBuilder chord = new StringBuilder(root().getNote() + "" + root().getAccidental());

		if (has(7) || has(8) || has(6)) { // Has a P5 -> W chord
			if (has(4) || has(3)) {
				if (has(3)) {
					chord = new StringBuilder(chord.toString().toLowerCase()); // Minor chord
				}

				if (has(6) && has(3)) chord.append("°"); // Diminished
				else {
					if (has(8)) chord.append("+");
				}


				if (has(10)) chord.append("7");
				if (has(11)) chord.append("maj7");

				if (has(2)) {
					chord.append("(add2)");
				}

				if (has(5)) {
					chord.append("(add4)");
				}

				if (has(6)) {
					if (has(4)) chord.append("(b5)"); // Flat Five
				}


			} else {
				if (has(10)) chord.append("7");
				if (has(11)) chord.append("maj7");

				if (has(2)) {
					chord.append(" sus2");
				}

				if (has(5)) {
					chord.append(" sus4");
				}

			}

			if (has(1)) chord.append("(b9)");
			if (has(8)) chord.append("(b13)");
			if (has(9)) chord.append("(add6)");

			return chord.toString();
		}

		for (Note note : notes) {
			chord.append("add").append(note.getAccidental());
		}


		return "fuck-ass chord";
	}

	public String insert(String s, int index, String a) {
		return s.substring(0, index) + a + s.substring(index);
	}

	@Override
	public boolean equals(Object o) {
		if (this == o) return true;
		if (o == null || getClass() != o.getClass()) return false;

		Chord chord = (Chord) o;

		return Objects.equals(notes, chord.notes);
	}

	@Override
	public int hashCode() {
		return notes != null ? notes.hashCode() : 0;
	}

	public int density() { return notes.size(); }
	public Note root() {
		return notes.first();
	}

	public boolean has(int interval) {
		for (Note note : notes) {
			if (note.getScaleDegree() == interval) return true;
		}

		return false;
	}

	public List<Note> ofIntervalRange(int max) {
		return ofIntervalRange(1, max);
	}

	public List<Note> ofIntervalRange(int min, int max) {
		return notes.stream().filter((note -> min <= note.getScaleDegree() && note.getScaleDegree() < max)).collect(Collectors.toList());
	}

	@Override
	public String toString() {
		return analyze() + notes.toString();
	}
}
