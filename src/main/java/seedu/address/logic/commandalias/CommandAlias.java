package seedu.address.logic.commandalias;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.stream.Collectors;

import seedu.address.logic.commandalias.exceptions.CommandWordException;

public class CommandAlias {
    private static int MAX_LENGTH = 20;
    private CommandWord commandWord;
    private HashSet<String> aliasSet = new HashSet<>();

    public CommandAlias(CommandWord commandWord) {
        this.commandWord = commandWord;
    }

    /**
     * Constructs a new command Alias with a command and aliases
     * @param command Command to be associated with
     * @param aliases Aliases to be initialised with
     */
    public CommandAlias(CommandWord command, List<String> aliases) {
        this.commandWord = command;
        aliasSet.addAll(aliases.stream().filter(alias -> !alias.equals(commandWord.getDefaultWord()))
                .collect(Collectors.toList()));
    }

    /**
     * Add an alias
     * @param alias Alias to be added
     * @throws CommandWordException if alias already exists, empty or contains white spaces
     */
    public void addAlias(String alias) throws CommandWordException {
        if (aliasExist(alias)) {
            throw new CommandWordException(alias + " already exists!");
        }
        if (alias.equals("")) {
            throw new CommandWordException(alias + "cannot be empty!");
        }

        if (alias.matches(".*\\s.*")) {
            throw new CommandWordException("Alias cannot contain white spaces!");
        }

        if (alias.length() > MAX_LENGTH) {
            throw new CommandWordException("Alias cannot be more than 20 characters!");
        }

        aliasSet.add(alias);
    }


    public boolean aliasExist(String alias) {
        return alias.equals(commandWord.getDefaultWord()) || aliasSet.contains(alias);
    }

    /**
     * Remove an alias
     * @param alias Alias to be removed
     * @throws CommandWordException
     */
    public void removeAlias(String alias) throws CommandWordException {
        if (!aliasExist(alias)) {
            throw new CommandWordException(alias + " does not exists!");
        }
        if (commandWord.getDefaultWord().equals(alias)) {
            throw new CommandWordException("Cannot remove default word!");
        }

        aliasSet.remove(alias);
    }

    public CommandWord getCommandWord() {
        return commandWord;
    }

    public boolean checkCommandWord(CommandWord word) {
        return word == commandWord;
    }

    public ArrayList<String> getAllAliases() {
        return new ArrayList<>(aliasSet);
    }

}
