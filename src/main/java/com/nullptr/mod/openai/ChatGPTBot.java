package com.nullptr.mod.openai;

import java.util.ArrayList;
import net.minecraft.client.Minecraft;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.util.text.TextComponentString;
//import net.minecraft.util.IChatComponent;
import net.minecraftforge.fml.common.Mod;
//import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
//import net.minecraftforge.fml.common.gameevent.PlayerEvent;
//import net.minecraftforge.fml.common.gameevent.TickEvent;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;
import net.minecraftforge.common.MinecraftForge;
import com.theokanning.openai.completion.chat.ChatCompletionRequest;
//import com.theokanning.openai.completion.CompletionResponse;
import com.theokanning.openai.service.OpenAiService;
import com.theokanning.openai.completion.chat.ChatMessage;
import com.theokanning.openai.completion.chat.ChatMessageRole;
import java.util.List;
import java.util.HashMap;
import java.util.ArrayList;
import java.time.Duration;

public class ChatGPTBot {
    public static final int MAX_MESSAGE_LENGTH = 2000;
    public static boolean gptEnabled = true;

    public static final OpenAiService api = new OpenAIService("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", Duration.ofSeconds(30));

    public static String getResponse(String senderName, String message) {
        // Получение предыдущих сообщений пользователя
        ArrayList<String> inputs = new ArrayList<>();
        for (TextComponentString component : Minecraft.getMinecraft().ingameGUI.getChatGUI().getSentMessages()) {
            String input = component.getUnformattedText().trim();
            if (input.startsWith("!gpt")) {
                inputs.add(input.replace("!gpt", "") + "\n");
            }
        }
        
        // Добавление текущего сообщения пользователя в запрос
        inputs.add(message + "\n");
        final List<ChatMessage> messages = new ArrayList<>();
        final ChatMessage systemMessage;
        // Формирование текста запроса
        String request = "";
        for (String input : inputs) {
            final ChatMessage systemMessage = new ChatMessage(ChatMessageRole.SYSTEM.value(), input);
            messages.add(systemMessage);
        }
        
        // Получение ответа от OpenAI API
        try {
            CompletionRequest completionRequest = new CompletionRequest.builder()
                    .model("text-davinci-002")
                    .message(request)
                    .maxTokens(MAX_MESSAGE_LENGTH)
                    .logitBias(new HashMap<>())
                    .build();

            //CompletionResponse completionResponse = api.complete(completionRequest).get();
            String response = completionRequest.getChoices().get(0).text().trim();

            return response;
        } catch (Exception e) {
            return "An error has occurred while processing your request. Please try again later.";
        }
    }
    
    public static void sendLongMessage(EntityPlayerMP player, String message) {
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
