package bot.commands.member;

import bot.commands.UserCommand;
import bot.models.Entry;
import bot.utils.CommandType;
import bot.utils.Env;
import bot.utils.Responses;
import net.dv8tion.jda.api.EmbedBuilder;
import net.dv8tion.jda.api.events.interaction.command.SlashCommandInteractionEvent;
import net.dv8tion.jda.api.interactions.DiscordLocale;
import net.dv8tion.jda.api.interactions.commands.build.CommandData;
import net.dv8tion.jda.api.interactions.commands.build.Commands;
import net.dv8tion.jda.api.interactions.components.buttons.Button;

import java.time.Instant;
import java.util.Locale;

public class Init extends UserCommand {
    @Override
    public void execute(final SlashCommandInteractionEvent event, final Locale locale, final Entry user) {
        if (user != null) {
            final var msg = Responses.getLocalizedResponse("initAlreadyOwned", locale);

            event.reply(msg).setEphemeral(true).queue();
            return;
        }

        final var userId = event.getUser().getId();

        final var btnAccept = Button.success(userId + ":initAccept", Responses.getLocalizedResponse("buttonAccept", locale));
        final var btnCancel = Button.danger(userId + ":initCancelled", Responses.getLocalizedResponse("buttonCancel", locale));

        event.getJDA().retrieveUserById(Env.get("DEV_ID")).queue(dev -> {
            final var embed = new EmbedBuilder()
                    .setTitle(Responses.getLocalizedResponse("initEmbedTitle", locale))
                    .addField(Responses.getLocalizedResponse("initEmbedStorageFieldTitle", locale), Responses.getLocalizedResponse("initEmbedStorageFieldBody", locale), false)
                    .addField(Responses.getLocalizedResponse("initEmbedDeletionFieldTitle", locale), Responses.getLocalizedResponse("initEmbedDeletionFieldBody", locale), false)
                    .setFooter(Responses.getLocalizedResponse("createdByNotice", locale), dev.getAvatarUrl())
                    .setTimestamp(Instant.now())
                    .build();

            event.replyEmbeds(embed).addActionRow(btnAccept, btnCancel).queue();
        });
    }

    @Override
    public CommandData getCommandData() {
        return Commands.slash("init", "Initializes a new alpaca")
                       .setDescriptionLocalization(DiscordLocale.GERMAN, "Initialisiert ein neues Alpaka");
    }

    @Override
    public CommandType getCommandType() {
        return CommandType.INIT;
    }
}