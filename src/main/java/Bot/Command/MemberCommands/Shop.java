package Bot.Command.MemberCommands;

import Bot.Command.CommandContext;
import Bot.Command.ICommand;
import Bot.Utils.PermissionLevel;
import Bot.Config;
import Bot.Shop.IShopItem;
import Bot.Shop.ShopItemManager;
import net.dv8tion.jda.api.EmbedBuilder;
import net.dv8tion.jda.api.Permission;
import net.dv8tion.jda.api.entities.TextChannel;
import net.dv8tion.jda.api.entities.User;

import java.time.Instant;
import java.util.Comparator;
import java.util.EnumSet;

public class Shop implements ICommand {
	private final ShopItemManager itemManager;

	public Shop(ShopItemManager itemManager) {
		this.itemManager = itemManager;
	}

	@Override
	public void execute(CommandContext ctx) {
		final TextChannel channel = ctx.getChannel();
		final User dev = ctx.getJDA().getUserById(Config.get("DEV_ID"));
		final EmbedBuilder embed = new EmbedBuilder();
		embed.setTitle("Shop")
			 .addField("Item", getItemsByCategory("hunger", "name"), true)
			 .addField("Price", getItemsByCategory("hunger", "price"), true)
			 .addField("Saturation", getItemsByCategory("hunger", "saturation"), true)
			 .addField("Item", getItemsByCategory("thirst", "name"), true)
			 .addField("Price", getItemsByCategory("thirst", "price"), true)
			 .addField("Saturation", getItemsByCategory("thirst", "saturation"), true)
			 .setFooter("Created by " + dev.getName(), dev.getEffectiveAvatarUrl())
			 .setTimestamp(Instant.now());

		channel.sendMessage(embed.build()).queue();
	}

	@Override
	public String getHelp(String prefix) {
		return "**Usage:** " + prefix + "shop\n**Aliases:** " + getAliases() + "\n**Example:** " + prefix + "shop";
	}

	@Override
	public String getName() {
		return "shop";
	}

	@Override
	public Enum<PermissionLevel> getPermissionLevel() {
		return PermissionLevel.MEMBER;
	}

	@Override
	public EnumSet<Permission> getRequiredPermissions() {
		return EnumSet.of(Permission.MESSAGE_WRITE, Permission.MESSAGE_EMBED_LINKS);
	}

	private String getItemsByCategory(String category, String filter) {
		String emoji = category.equals("hunger") ? ":meat_on_bone: " : ":beer: ";

		StringBuilder builder = new StringBuilder();
		itemManager.getShopItems()
				   .stream()
				   .sorted(Comparator.comparingInt(IShopItem::getPrice))
				   .filter((item) -> item.getCategory().equals(category))
				   .forEach((item) -> {
					   if (filter.equals("name")) {
						   builder.append(":package: ").append(item.getName()).append("\n");
					   }
					   else if (filter.equals("price")) {
						   builder.append(":coin: ").append(item.getPrice()).append("\n");
					   }
					   else {
						   builder.append(emoji).append(item.getSaturation()).append("\n");
					   }
				   });

		return builder.toString();
	}
}