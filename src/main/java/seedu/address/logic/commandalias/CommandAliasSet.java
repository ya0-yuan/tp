package seedu.address.logic.commandalias;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import seedu.address.logic.commandalias.exceptions.CommandWordException;

public class CommandAliasSet {

    private static CommandAliasSet instance;

    private static List<CommandAlias> commandAliases;

    private CommandAliasSet() {
        this.commandAliases = Stream.of(CommandWord.values())
                .map(word -> new CommandAlias(word)).collect(Collectors.toList());
    }

    public static CommandAliasSet getInstance() {
        if (instance == null) {
            instance = new CommandAliasSet();
        }
        return instance;
    }

    /**
     * Insert a new alias
     * @param word The word associated with the alias
     * @param alias The alias to be added
     * @throws CommandWordException if alias already exists
     */
    public void insertNewAlias(CommandWord word, String alias) throws CommandWordException {

        if (commandAliases.stream().anyMatch(commandAlias -> commandAlias.aliasExist(alias))) {
            throw new CommandWordException("Alias already exists!");
        }
        commandAliases.stream().filter(commandAlias -> commandAlias.checkCommandWord(word))
                .findFirst().get().addAlias(alias);

    }

    /**
     * Insert a new alias
     * @param word The associated word
     * @param alias The new alias
     * @throws CommandWordException if the original command word does not exist
     */
    public void insertNewAlias(String word, String alias) throws CommandWordException {
        CommandWord commandWord = CommandWord.getCommandWord(word);
        if (commandWord == null) {
            throw new CommandWordException("Original Command Word does not exist!");
        }
        insertNewAlias(commandWord, alias);

    }

    public CommandWord getCommandWord(String alias) throws CommandWordException {
        Optional<CommandAlias> command = commandAliases.stream().filter(commandAlias -> commandAlias.aliasExist(alias))
                .findFirst();
        if (command.isEmpty()) {
            throw new CommandWordException("Command does not exists!");
        }

        return command.get().getCommandWord();
    }

    /**
     * Delete an alias
     * @param alias Alias to be deleted
     * @throws CommandWordException if the alias does not exist
     */
    public void deleteAlias(String alias) throws CommandWordException {
        Optional<CommandAlias> command = commandAliases.stream().filter(commandAlias -> commandAlias.aliasExist(alias))
                .findFirst();
        if (command.isEmpty()) {
            throw new CommandWordException("Alias does not exists!");
        }
        command.get().removeAlias(alias);
    }

    public List<CommandAlias> getListOfAliases() {
        return this.commandAliases;
    }

    public void setUpAliasSet(List<CommandAlias> aliasSet) {
        this.commandAliases = Stream.of(CommandWord.values())
                .map(word -> new CommandAlias(word)).collect(Collectors.toList());
        aliasSet.stream().forEach(set -> {
            set.getAllAliases().stream().forEach(alias -> {
                try {
                    insertNewAlias(set.getCommandWord(), alias);
                } catch (CommandWordException ex) {
                    System.out.println("skip");
                }
            });
        });
    }

    public void setUpAliasSet(CommandAliasSet aliasSet) {
        commandAliases = aliasSet.getListOfAliases();
    }
}

