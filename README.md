# QuidProQuo

## Usage

Big brain minecraft plugin that lets you sacrifice stuff to do things.
Things get sacrificed on an altar that consists of a flat 3x3 of iron blocks with a gold block on top of them in the middle.
Just throw things on the altar and right click the gold block.

## Development

New effects are really easy to add, just make a copy of `BlankRitual.java` inside of the `rituals` folder and then add in a combination of `addIngredient()`, `addSacrifice()` and other properties under `setup()`. Then write your own custom effect under `execute()`. Finally, go into the main class and register the ritual with the rest of the others.
