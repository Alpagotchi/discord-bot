package bot.db;

import bot.models.guildsettings.GuildSettings;
import bot.models.user.User;

public interface IDatabase {
    IDatabase INSTANCE = new PostgresDB();

    void initDatabase();

    void shutdownDatabase();

    User getUserById(final long userId);

    void createUserById(final long memberID);

    void updateUser(final User user);

    void deleteUserById(final long memberID);

    GuildSettings getSettingsById(final long guildID);

    void updateSettings(final GuildSettings settings);

    long getUserCount();
}