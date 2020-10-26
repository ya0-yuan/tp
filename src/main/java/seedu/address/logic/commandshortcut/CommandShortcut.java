package seedu.address.logic.commandshortcut;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.stream.Collectors;

import seedu.address.logic.commandshortcut.exceptions.CommandWordException;

public class CommandShortcut {
    private CommandWord commandWord;
    private HashSet<String> shortcutSet = new HashSet<>();

    public CommandShortcut(CommandWord commandWord) {
        this.commandWord = commandWord;
    }

    /**
     * Constructs a new command Shortcut with a command and aliases
     * @param command Command to be associated with
     * @param aliases Aliases to be initialised with
     */
    public CommandShortcut(CommandWord command, List<String> aliases) {
        this.commandWord = command;
        shortcutSet.addAll(aliases.stream().filter(alias -> !alias.equals(commandWord.getDefaultWord()))
                .collect(Collectors.toList()));
    }

    /**
     * Add an shortcut
     * @param shortcut Alias to be added
     * @throws CommandWordException if shortcut already exists, empty or contains white spaces
     */
    public void addShortcut(String shortcut) throws CommandWordException {
        if (shortcutExist(shortcut)) {
            throw new CommandWordException(shortcut + " already exists!");
        }
        if (shortcut.equals("")) {
            throw new CommandWordException(shortcut + "cannot be empty!");
        }

        if (shortcut.matches(".*\\s.*")) {
            throw new CommandWordException("Shortcut cannot contain white spaces!");
        }

        shortcutSet.add(shortcut);
    }


    public boolean shortcutExist(String shortcut) {
        return shortcut.equals(commandWord.getDefaultWord()) || shortcutSet.contains(shortcut);
    }

    /**
     * Remove an shortcut
     * @param shortcut Alias to be removed
     * @throws CommandWordException
     */
    public void removeShortcut(String shortcut) throws CommandWordException {
        if (!shortcutExist(shortcut)) {
            throw new CommandWordException(shortcut + " does not exists!");
        }
        if (commandWord.getDefaultWord().equals(shortcut)) {
            throw new CommandWordException("Cannot remove default word!");
        }

        shortcutSet.remove(shortcut);
    }

    public CommandWord getCommandWord() {
        return commandWord;
    }

    public boolean checkCommandWord(CommandWord word) {
        return word == commandWord;
    }

    public ArrayList<String> getAllShortcuts() {
        return new ArrayList<>(shortcutSet);
    }

}
