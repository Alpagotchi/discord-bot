package bot.components.buttons.delete;

import bot.components.buttons.MessageButton;
import bot.db.IDatabase;
import bot.utils.Responses;
import net.dv8tion.jda.api.events.interaction.component.ButtonInteractionEvent;

import java.util.Collections;
import java.util.Locale;

public class DeleteAccept extends MessageButton {
    @Override
    public void execute(final ButtonInteractionEvent event, final Locale locale) {
        IDatabase.INSTANCE.deleteUserById(event.getUser().getIdLong());

        event.editMessage(Responses.getLocalizedResponse("deleteSuccessful", locale))
             .setComponents(Collections.emptyList())
             .setEmbeds(Collections.emptyList())
             .queue();
    }
}