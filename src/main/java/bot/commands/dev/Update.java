package bot.commands.dev;

import bot.commands.CommandManager;
import bot.commands.InfoSlashCommand;
import bot.utils.CommandType;
import bot.utils.Responses;
import net.dv8tion.jda.api.events.interaction.command.SlashCommandInteractionEvent;
import net.dv8tion.jda.api.interactions.DiscordLocale;
import net.dv8tion.jda.api.interactions.commands.DefaultMemberPermissions;
import net.dv8tion.jda.api.interactions.commands.build.CommandData;
import net.dv8tion.jda.api.interactions.commands.build.Commands;

import java.text.MessageFormat;
import java.util.Locale;

public class Update extends InfoSlashCommand {
    private final CommandManager commandManager;

    public Update(final CommandManager commandManager) {
        this.commandManager = commandManager;
    }

    @Override
    public void execute(final SlashCommandInteractionEvent event, final Locale locale) {
        event.getJDA()
             .updateCommands()
             .addCommands(this.commandManager.getCommandDataByTypes(CommandType.USER, CommandType.INFO, CommandType.INIT))
             .queue();

        event.getGuild()
             .updateCommands()
             .addCommands(this.commandManager.getCommandDataByTypes(CommandType.DEV))
             .queue();

        final var format = new MessageFormat(Responses.getLocalizedResponse("updateCommands", locale));
        final var msg = format.format(new Object[]{ this.commandManager.getCommands().size() });

        event.reply(msg).queue();
    }

    @Override
    public CommandData getCommandData() {
        return Commands.slash("update", "Refreshes all slash commands")
                       .setDescriptionLocalization(DiscordLocale.GERMAN, "Aktualisiert alle Befehle")
                       .setGuildOnly(true)
                       .setDefaultPermissions(DefaultMemberPermissions.DISABLED);
    }

    @Override
    public CommandType getCommandType() {
        return CommandType.DEV;
    }
}