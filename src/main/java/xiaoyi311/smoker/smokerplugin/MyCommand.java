package xiaoyi311.smoker.smokerplugin;

import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.command.TabCompleter;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.ArrayList;
import java.util.List;

/*
SP 命令监听
 */
public class MyCommand implements CommandExecutor, TabCompleter {
    //得命也
    @Override
    public boolean onCommand(@NotNull CommandSender sender, @NotNull Command command, @NotNull String label, @NotNull String[] args) {
        //曹参的数目不足
        if (args.length == 0){
            sender.sendMessage(ChatColor.translateAlternateColorCodes('&',
                    PluginMain.INSTANCE.config.getString("lang.Game.CommandError", "")
            ));
        }else {
            switch (args[0]){
                //左右列
                case "help":
                    sender.sendMessage(GetHelp());
                    break;
                //重插件
                case "reload":
                    //其权也
                    if (sender.hasPermission("smokerplugin.reload")){
                        PluginMain.INSTANCE.ReloadPlugin();
                        sender.sendMessage(ChatColor.translateAlternateColorCodes('&',
                                PluginMain.INSTANCE.config.getString("lang.Game.PluginReload", "")
                        ));
                    }else {
                        sender.sendMessage(ChatColor.translateAlternateColorCodes('&',
                                PluginMain.INSTANCE.config.getString("lang.Game.NoPermission", "")
                        ));
                    }
                    break;
                //监刑狱的功劳能
                case "jail":
                    PluginMain.INSTANCE.ModJail.onCommand(sender, args);
                    break;
                //没有这样的命令
                default:
                    sender.sendMessage(ChatColor.translateAlternateColorCodes('&',
                            PluginMain.INSTANCE.config.getString("lang.Game.CommandError", "")
                    ));
                    break;
            }
        }
        return true;
    }

    //取佐表
    private String GetHelp() {
        return
                """
                        -------------------- Smokerplugin --------------------
                        /sp help -> 获取帮助列表
                        /sp reload -> 重载此插件
                        /sp jail help -> 显示监狱系统帮助
                        -------------------- Smokerplugin --------------------""";
    }

    //命完补
    @Override
    public @Nullable List<String> onTabComplete(@NotNull CommandSender sender, @NotNull Command command, @NotNull String alias, @NotNull String[] args) {
        List<String> commands = new ArrayList<>();
        if (sender instanceof Player) {
            if (args.length == 1){
                commands.add("help");
                commands.add("reload");
                commands.add("jail");
                return commands;
            }else{
                switch (args[0]){
                    case "jail":
                        commands = PluginMain.INSTANCE.ModJail.onTabComplete(args);
                        return commands;
                }
            }
        }
        return null;
    }
}
