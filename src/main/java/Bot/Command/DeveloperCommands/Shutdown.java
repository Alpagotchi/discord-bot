package Bot.Command.DeveloperCommands;

import Bot.Command.CommandContext;
import Bot.Command.ICommand;
import Bot.Command.PermissionLevel;

public class Shutdown implements ICommand {

   @Override
   public void execute(CommandContext commandContext) {

      if (!PermissionLevel.DEVELOPER.hasPerms(commandContext.getMember())) {
         commandContext.getChannel().sendMessage("<:RedCross:782229279312314368> This is a developer-only command").queue();
         return;
      }

      commandContext.getChannel().sendMessage("<:GreenTick:782229268914372609> " + commandContext.getJDA().getSelfUser().getName() + " is shutting down...").complete();
      System.exit(0);
   }

   @Override
   public String getHelp(String prefix) {
      return "`Usage: " + prefix + "shutdown\n" + (this.getAliases().isEmpty() ? "`" : "Aliases: " + this.getAliases() + "`\n") + "Shutdowns the bot";
   }

   @Override
   public String getName() {
      return "shutdown";
   }

   @Override
   public Enum<PermissionLevel> getPermissionLevel() {
      return PermissionLevel.DEVELOPER;
   }
}