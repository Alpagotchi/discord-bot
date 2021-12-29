package bot.commands.member;

import bot.commands.SlashCommand;
import bot.models.Entry;
import bot.shop.ItemManager;
import bot.utils.CommandType;
import bot.utils.Env;
import bot.utils.MessageService;
import net.dv8tion.jda.api.EmbedBuilder;
import net.dv8tion.jda.api.entities.User;
import net.dv8tion.jda.api.events.interaction.SlashCommandEvent;
import net.dv8tion.jda.api.interactions.commands.build.CommandData;

import java.time.Instant;
import java.util.Locale;
import java.util.function.Consumer;

public class Inventory extends SlashCommand {
    private final ItemManager items;

    public Inventory(final ItemManager items) {
        this.items = items;
    }

    @Override
    public void execute(final SlashCommandEvent event, final Locale locale, final Entry user) {
        event.getJDA().retrieveUserById(Env.get("DEV_ID")).queue(dev -> {
            final EmbedBuilder embed = new EmbedBuilder()
                    .setTitle("Inventory")
                    .setThumbnail("https://cdn.discordapp.com/attachments/795637300661977132/839074173459365908/inventory.png")
                    .addField("__**:meat_on_bone: Hunger items**__", "These items are used to fill up the hunger of your alpaca", false)
                    .setFooter("Created by " + dev.getName(), dev.getAvatarUrl())
                    .setTimestamp(Instant.now());

            this.items.getItemsByStat("hunger").forEach(item -> embed.addField(
                    ":package: " + item.getName(),
                    "Quantity: **" + user.getItem(item.getName()) + "**",
                    true)
            );

            embed.addBlankField(false).addField(
                    "__**:beer: Thirst items**__",
                    "Following items replenish the thirst of your alpaca",
                    false
            );

            this.items.getItemsByStat("thirst").forEach(item -> embed.addField(
                    ":package: " + item.getName(),
                    "Quantity: **" + user.getItem(item.getName()) + "**",
                    true)
            );

            MessageService.queueReply(event, embed.build(), false);
        });
    }

    @Override
    public CommandData getCommandData() {
        return new CommandData("inventory", "Shows your items for your alpaca");
    }

    @Override
    protected CommandType getCommandType() {
        return CommandType.INFO;
    }
}
