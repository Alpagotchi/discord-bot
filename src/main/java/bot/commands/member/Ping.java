package bot.commands.member;

import bot.commands.ISlashCommand;
import bot.models.Entry;
import bot.utils.CommandType;
import bot.utils.Responses;
import net.dv8tion.jda.api.events.interaction.command.SlashCommandInteractionEvent;
import net.dv8tion.jda.api.interactions.commands.build.CommandData;
import net.dv8tion.jda.api.interactions.commands.build.Commands;

import java.text.MessageFormat;
import java.util.Locale;

public class Ping implements ISlashCommand {
    @Override
    public void execute(final SlashCommandInteractionEvent event, final Locale locale, final Entry user) {
        event.getJDA().getRestPing().queue(ping -> {
            final var format = new MessageFormat(Responses.get("ping", locale));
            final var msg = format.format(new Object[]{ ping });

            event.reply(msg).queue();
        });
    }

    @Override
    public CommandData getCommandData() {
        return Commands.slash("ping", "Displays the current latency of Alpagotchi");
    }

    @Override
    public CommandType getCommandType() {
        return CommandType.INFO;
    }
}