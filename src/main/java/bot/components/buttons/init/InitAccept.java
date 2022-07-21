package bot.components.buttons.init;

import bot.components.buttons.MessageButton;
import bot.db.IDatabase;
import bot.utils.Responses;
import net.dv8tion.jda.api.events.interaction.component.ButtonInteractionEvent;

import java.text.MessageFormat;
import java.util.Collections;
import java.util.Locale;

public class InitAccept extends MessageButton {
    @Override
    public void execute(final ButtonInteractionEvent event, final Locale locale) {
        IDatabase.INSTANCE.createUserById(event.getUser().getIdLong());

        final var format = new MessageFormat(Responses.get("initSuccess", locale));
        final var msg = format.format(new Object[]{});

        event.editMessage(msg)
             .setActionRows(Collections.emptyList())
             .setEmbeds(Collections.emptyList())
             .queue();
    }
}