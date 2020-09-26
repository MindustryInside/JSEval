package jseval;

import arc.util.*;
import mindustry.entities.type.*;
import mindustry.plugin.*;

import static mindustry.Vars.*;

public class JSEval extends Plugin {

    @Override
    public void registerClientCommands(CommandHandler handler) {
        handler.<Player>register("js", "<code...>", "Execute JavaScript code.", (args, player) -> {
            if (player.isAdmin) {
                 String output = mods.getScripts().runConsole(args[0]);
                 player.sendMessage("> " + (isError(output) ? "[#ff341c]" + output : output));
            }
        });
    }

    private boolean isError(String output) {
        try {
            String errorName = output.substring(0, output.indexOf(' ') - 1);
            Class.forName("org.mozilla.javascript." + errorName);
            return true;
        } catch (Throwable e) {
            return false;
        }
    }
}
