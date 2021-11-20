package bot.commands.member;

import bot.commands.interfaces.IStaticUserCommand;
import bot.models.Entry;
import bot.utils.Resources;
import net.dv8tion.jda.api.events.interaction.SlashCommandEvent;
import net.dv8tion.jda.api.interactions.commands.build.CommandData;

import java.text.MessageFormat;

public class Balance implements IStaticUserCommand {
	@Override
	public void execute(SlashCommandEvent event, Entry user) {
		final MessageFormat msg = new MessageFormat(Resources.getPattern("balance"));
		event.reply(msg.format(new Object[]{ user.getCurrency() })).queue();
	}

	@Override
	public CommandData getCommandData() {
		return new CommandData("balance", "Shows your fluffy balance");
	}
}
