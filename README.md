# Chord Cleaner
### I need a better name for this

Based on inputted Notes, this program analyzes them to identify the chord. This program does NOT support
slash notation, and assumes that the lowest provided note is the bass. Still in progress.

## Run Instructions
Run ChordCleaner.java to get the user interface. In the interface, you must provide each given note in the format:
> (note)(accidental, or blank for natural)(octave)

For example:
> Bb7
> C#4
> A3
> Abb1
> Ax3

All notes should be entered on the same line, separated by a space. After pressing enter, the program will identify
and output the chord.

### This will NOT handle every type of chord, and will undoubtedly be wrong in some instances. Music theory, at a high level, is incredibly complex, and requires a neural network for more indepth insight.
