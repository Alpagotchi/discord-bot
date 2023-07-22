package bot.components.buttons.delete;

import bot.components.buttons.MessageButton;
import bot.utils.Responses;
import net.dv8tion.jda.api.events.interaction.component.ButtonInteractionEvent;

import java.util.Collections;
import java.util.Locale;

public class DeleteCancel extends MessageButton {
    @Override
    public void execute(final ButtonInteractionEvent event, final Locale locale) {
        event.editMessage(Responses.getLocalizedResponse("delete.cancelled", locale))
             .setComponents(Collections.emptyList())
             .setEmbeds(Collections.emptyList())
             .queue();
    }
}