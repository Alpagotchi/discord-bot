package bot.commands;

import bot.commands.ISlashCommand;
import bot.db.IDatabase;
import bot.models.User;
import bot.utils.Responses;
import net.dv8tion.jda.api.events.interaction.command.SlashCommandInteractionEvent;

import java.util.Locale;

public abstract class ImmutableUserCommand implements ISlashCommand {
    @Override
    public void execute(SlashCommandInteractionEvent event) {
        final var user = IDatabase.INSTANCE.getUserById(event.getUser().getIdLong());
        final var locale = Locale.ENGLISH;

        if (!event.getName().equals("init") && user == null) {
            final var msg = Responses.getLocalizedResponse("errorAlpacaNotOwned", locale);

            event.reply(msg).setEphemeral(true).queue();
            return;
        }

        execute(event, locale, user);
    }

    protected abstract void execute(final SlashCommandInteractionEvent event, final Locale locale, final User user);
}