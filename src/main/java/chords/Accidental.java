package chords;

import javax.accessibility.AccessibleIcon;

public enum Accidental {
	DOUBLE_FLAT {
		@Override
		public String toString() {
			return "♭♭";
		}

		@Override
		public int pitchMod() {
			return -2;
		}
	}, FLAT {
		@Override
		public String toString() {
			return "♭";
		}

		@Override
		public int pitchMod() {
			return -1;
		}
	}, NATURAL {
		@Override
		public String toString() {
			return "♮";
		}

		@Override
		public int pitchMod() {
			return 0;
		}

	}, SHARP {
		@Override
		public String toString() {
			return "#";
		}

		@Override
		public int pitchMod() {
			return 1;
		}
	}, DOUBLE_SHARP {
		@Override
		public String toString() {
			return "x";
		}

		@Override
		public int pitchMod() {
			return 2;
		}
	};

	public int pitchMod() {
		return 0;
	}

	public String toString() {
		return super.toString();
	}

	public static Accidental from(String aStr) {
		return switch (aStr) {
			case "♭♭", "bb" -> DOUBLE_FLAT;
			case "♭", "b" -> FLAT;
			case "#" -> SHARP;
			case "##" -> DOUBLE_SHARP;
			default -> NATURAL;
		};
	}
}