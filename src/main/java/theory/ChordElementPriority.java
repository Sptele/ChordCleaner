package theory;

enum ChordElementPriority {
	NOTE, AUGMENT, SEVENTH {
		public ChordStringBuilder.ChordElement of(boolean dom, int el) {
			return dom ? of(el) : of("maj" + el);
		}
	}, FLAT {
		@Override
		public ChordStringBuilder.ChordElement of(int el) {
			return super.of("(b" + el + ")");
		}

		@Override
		public int compareTo(String a, String b) {
			int aNum = Integer.parseInt(a.substring(2).replace(")", ""));
			int bNum = Integer.parseInt(b.substring(2).replace(")", ""));


			return aNum - bNum;
		}
	},
	SHARP {
		@Override
		public ChordStringBuilder.ChordElement of(int el) {
			return super.of("(#" + el + ")");
		}

		@Override
		public int compareTo(String a, String b) {
			int aNum = Integer.parseInt(a.substring(2).replace(")", ""));
			int bNum = Integer.parseInt(b.substring(2).replace(")", ""));


			return aNum - bNum;
		}

	}, SUSPENSION {
		@Override
		public ChordStringBuilder.ChordElement of(int el) {
			return super.of(" sus" + el);
		}

		@Override
		public int compareTo(String a, String b) {
			int aNum = Integer.parseInt(a.substring(4));
			int bNum = Integer.parseInt(b.substring(4));

			return aNum - bNum;
		}
	}, ADDITIONS {
		@Override
		public ChordStringBuilder.ChordElement of(int el) {
			return super.of("(add" + el + ")");
		}

		@Override
		public ChordStringBuilder.ChordElement of(String el) {
			return super.of("(add(" + el + "))");
		}

		@Override
		public int compareTo(String a, String b) {
			int aNum = Integer.parseInt(a.substring(4).replace(")", ""));
			int bNum = Integer.parseInt(b.substring(4).replace(")", ""));

			return aNum - bNum;
		}
	};

	public ChordStringBuilder.ChordElement of(String el) {
		return new ChordStringBuilder.ChordElement(el, this);
	}

	public ChordStringBuilder.ChordElement of(int el) {
		return new ChordStringBuilder.ChordElement("" + el, this);
	}

	public ChordStringBuilder.ChordElement of(boolean bool, int el) { return of(el); }

	public int compareTo(String a, String b) {
		return a.compareTo(b);
	}
}
