package Bot.Command.AdminCommands;

import Bot.Command.CommandContext;
import Bot.Command.ICommand;
import Bot.Utils.Emote;
import Bot.Utils.Error;
import Bot.Utils.PermissionLevel;
import Bot.Database.IDatabase;
import net.dv8tion.jda.api.Permission;
import net.dv8tion.jda.api.entities.Guild;
import net.dv8tion.jda.api.entities.TextChannel;

import java.util.EnumSet;
import java.util.List;

public class SetPrefix implements ICommand {
	@Override
	public void execute(CommandContext ctx) {
		final TextChannel channel = ctx.getChannel();
		final List<String> args = ctx.getArgs();
		final Guild guild = ctx.getGuild();
		final String prefix = ctx.getPrefix();

		if (!PermissionLevel.ADMIN.hasPermission(ctx.getMember())) {
			channel.sendMessage(Error.ADMIN_ONLY.getMessage(prefix, getName())).queue();
			return;
		}

		if (args.isEmpty()) {
			channel.sendMessage(Error.MISSING_ARGS.getMessage(prefix, getName())).queue();
			return;
		}

		final String newPrefix = String.join("", args);
		if (newPrefix.equals(prefix)) {
			channel.sendMessage(Emote.REDCROSS + " The new prefix can't be the old one").queue();
			return;
		}

		IDatabase.INSTANCE.setPrefix(guild.getIdLong(), newPrefix);

		channel.sendMessage(Emote.GREENTICK + " The prefix of **" + guild.getName() + "** has been set to **" + newPrefix + "**").queue();
	}

	@Override
	public String getName() {
		return "setprefix";
	}

	@Override
	public PermissionLevel getPermissionLevel() {
		return PermissionLevel.ADMIN;
	}

	@Override
	public EnumSet<Permission> getRequiredPermissions() {
		return EnumSet.of(Permission.MESSAGE_WRITE);
	}

	@Override
	public String getSyntax() {
		return "setprefix [prefix]";
	}

	@Override
	public String getExample() {
		return "setprefix ?";
	}

	@Override
	public String getDescription() {
		return "Sets the prefix of the bot for the guild";
	}
}
