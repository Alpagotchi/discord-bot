package Bot.Command;

import net.dv8tion.jda.api.interactions.commands.build.CommandData;

public interface ISlashCommand {
    CommandData getCommandData();
}
