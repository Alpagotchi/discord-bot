package bot.commands.owner;

import bot.commands.types.OwnerCommand;
import bot.db.IDatabase;
import bot.commands.types.CommandType;
import bot.localization.LocalizedResponse;
import net.dv8tion.jda.api.events.interaction.command.SlashCommandInteractionEvent;
import net.dv8tion.jda.api.interactions.DiscordLocale;
import net.dv8tion.jda.api.interactions.commands.DefaultMemberPermissions;
import net.dv8tion.jda.api.interactions.commands.build.CommandData;
import net.dv8tion.jda.api.interactions.commands.build.Commands;

import java.util.Locale;

public class Shutdown extends OwnerCommand {
    @Override
    public void execute(final SlashCommandInteractionEvent event, final Locale locale) {
        // Shutdown db
        IDatabase.INSTANCE.shutdownDatabase();

        // Shutdown bot
        event.reply(LocalizedResponse.get("shutdown.successful", locale, event.getJDA().getSelfUser().getName())).complete();
        event.getJDA().shutdown();
    }

    @Override
    public CommandData getCommandData() {
        return Commands.slash("shutdown", "Shutdowns Alpagotchi")
                       .setDescriptionLocalization(DiscordLocale.GERMAN, "Fährt Alpagotchi herunter")
                       .setDefaultPermissions(DefaultMemberPermissions.DISABLED);
    }

    @Override
    public CommandType getCommandType() {
        return CommandType.OWNER;
    }
}