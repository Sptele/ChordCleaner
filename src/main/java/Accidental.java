
interface AccidentalInterface {
	int pitchMod();
	String toString();
}

public enum Accidental implements AccidentalInterface {
	DOUBLE_FLAT {
		@Override
		public String toString() {
			return "♭♭";
		}

		public int pitchMod() {
			return -2;
		}
	}, FLAT {
		@Override
		public String toString() {
			return "♭";
		}

		public int pitchMod() {
			return -1;
		}
	}, NATURAL {
		@Override
		public String toString() {
			return "♮";
		}

		public int pitchMod() {
			return 0;
		}

	}, SHARP {
		@Override
		public String toString() {
			return "#";
		}

		public int pitchMod() {
			return 1;
		}
	}, DOUBLE_SHARP {
		@Override
		public String toString() {
			return "x";
		}

		public int pitchMod() {
			return 2;
		}
	}
}