package japan.mori.commands;

import ca.tristan.easycommands.commands.EventData;
import ca.tristan.easycommands.commands.slash.SlashExecutor;
import net.dv8tion.jda.api.interactions.commands.OptionType;
import net.dv8tion.jda.api.interactions.commands.build.OptionData;

import java.util.List;

import static org.apache.http.client.methods.RequestBuilder.options;

public class sayCmd extends SlashExecutor {
    @Override
    public String getName() {
        return "botsay"; //super.getName();
    }

    @Override
    public List<OptionData> getOptions() {
        options.add(new OptionData(OptionType.STRING, "message", "this message will get sent by the bot."));
        return options;
    }

    @Override
    public String getDescription() {
        return "/say <messages> - bot send you back the message";
    }

    @Override
    public boolean isOwnerOnly() {
        return false;
    }

    @Override
    public void execute(EventData data) {
        data.deferReply();
        if(data.getCommand().getOptions().isEmpty()) {
            data.getEvent().reply("You need to specify a message").setEphemeral(true).queue();
            return;
        }
        data.getHook().sendMessage(data.getCommand().getOptions().get(0).getAsString());
    }
}
