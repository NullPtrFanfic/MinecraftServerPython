import java.util.ArrayList;
import net.minecraft.client.Minecraft;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.util.text.TextComponentString;
import net.minecraft.util.IChatComponent;
import net.minecraftforge.fml.common.Mod;
//import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
//import net.minecraftforge.fml.common.gameevent.PlayerEvent;
//import net.minecraftforge.fml.common.gameevent.TickEvent;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;
import net.minecraftforge.common.MinecraftForge;
import com.openai.api.models.CompletionRequest;
import com.openai.api.models.CompletionResponse;
import com.openai.api.OpenAI;

public class ChatGPTBot {
    private static final int MAX_MESSAGE_LENGTH = 2000;
    private static boolean gptEnabled = true;

    private static final OpenAI api = new OpenAI.Builder()
            .apiKey("YOUR_API_KEY")
            .build();
    
    @SubscribeEvent
    public static void onPlayerChat(ServerChatEvent event) {
        if (gptEnabled) {
            EntityPlayerMP player = event.getPlayer();
            String message = event.getMessage();

            if (message.length() > MAX_MESSAGE_LENGTH && message.startsWith("!gpt")) {
                sendLongMessage(player, message);
            } else if (message.startsWith("!gpt")) {
                String response = getResponse(event.getSenderName(), message);
                player.sendMessage(new TextComponentString(response));
            }
        }
    }

    private static String getResponse(String senderName, String message) {
        // Получение предыдущих сообщений пользователя
        ArrayList<String> inputs = new ArrayList<>();
        for (IChatComponent component : Minecraft.getMinecraft().ingameGUI.getChatGUI().getSentMessages()) {
            String input = component.getUnformattedText().trim();
            if (input.startsWith(senderName)) {
                inputs.add(input.replace(senderName, "") + "\n");
            }
        }
        
        // Добавление текущего сообщения пользователя в запрос
        inputs.add(message.replaceAll("!gpt", "") + "\n");

        // Формирование текста запроса
        String request = "";
        for (String input : inputs) {
            request += input;
        }
        
        // Получение ответа от OpenAI API
        try {CompletionRequest completionRequest = new CompletionRequest.Builder()
                    .engine("text-davinci-002")
                    .prompt(request)
                    .maxTokens(2048)
                    .temperature(0.7)
                    .build();

            CompletionResponse completionResponse = api.complete(completionRequest).get();
            String response = completionResponse.choices().get(0).text().trim();

            return response;
        } catch (Exception e) {
            return "An error has occurred while processing your request. Please try again later.";
        }
    }
    
    private static void sendLongMessage(EntityPlayerMP player, String message) {
        // Разделение сообщения на части
        ArrayList<String> chunks = new ArrayList<>();
        for (int i = 0; i < message.length(); i += MAX_MESSAGE_LENGTH) {
            chunks.add(message.substring(i, Math.min(i + MAX_MESSAGE_LENGTH, message.length())));
        }

        // Отправка каждой части сообщения по отдельности
        for (String chunk : chunks) {
            player.sendMessage(new TextComponentString(chunk));
        }
    }
}
