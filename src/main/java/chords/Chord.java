package chords;

import java.util.*;

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

	public void clear() {
		notes.clear();
	}

	@Override
	public String toString() {
		return notes.toString();
	}
}
