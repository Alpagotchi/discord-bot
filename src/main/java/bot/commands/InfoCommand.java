package bot.commands;

import bot.db.IDatabase;
import net.dv8tion.jda.api.events.interaction.command.SlashCommandInteractionEvent;

import java.util.Locale;

public abstract class InfoCommand implements ISlashCommand {
    @Override
    public void execute(final SlashCommandInteractionEvent event) {
        final var locale = Locale.ENGLISH;

        execute(event, locale);
    }

    protected abstract void execute(final SlashCommandInteractionEvent event, final Locale locale);
}