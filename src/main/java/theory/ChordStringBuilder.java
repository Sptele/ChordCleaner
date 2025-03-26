package theory;

import java.util.PriorityQueue;

/**
 * A stringbuilder that sorts elements based on a given priority as it relates to the chord's final output:
 * (Note/Accidental:0)(Augment:1)(Sevens:2)(b#:3)(Suspensions:4)(Adds:5)
 */
public class ChordStringBuilder {

	// Basic Record to store element and priority
	static class ChordElement implements Comparable<ChordElement> {
		private final String el;
		private final ChordElementPriority priority;

		public ChordElement(String el, ChordElementPriority priority) {
			this.el = el;
			this.priority = priority;
		}

		@Override
		public int compareTo(ChordElement o) {
			int diff = priority.compareTo(o.priority);

			// Sort by priority, then lexographically
			return diff != 0 ? diff : priority.customComparator(el, o.el);
		}

		@Override
		public String toString() {
			return el;
		}
	}

	private final PriorityQueue<ChordElement> queue;

	public ChordStringBuilder() {
		queue = new PriorityQueue<>();
	}

	public ChordStringBuilder(String def) {
		this();

		append(def, ChordElementPriority.NOTE);
	}

	public ChordStringBuilder append(ChordElement el) {
		queue.add(el);

		return this;
	}

	public ChordStringBuilder append(String el, ChordElementPriority weight) {
		queue.add(new ChordElement(el, weight));

		return this;
	}

	public ChordStringBuilder append(int el, ChordElementPriority weight) {
		return append(""+el, weight);
	}

	public void pollToLowerCase() {
		if (queue.isEmpty()) return;

		ChordElement el = queue.poll();
		queue.add(new ChordElement(el.el.toLowerCase(), el.priority));
	}

	@Override
	public String toString() {
		StringBuilder bldr = new StringBuilder();

		while (!queue.isEmpty()) {
			bldr.append(queue.poll().toString());
		}

		return bldr.toString();
	}
}


