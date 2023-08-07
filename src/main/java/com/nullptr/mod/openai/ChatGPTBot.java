package com.nullptr.mod.openai;

import java.util.ArrayList;
import net.minecraft.client.Minecraft;
//import net.minecraft.entity.player.EntityPlayerMP;
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
//import com.theokanning.openai.completion.chat.ChatMessage;
//import com.theokanning.openai.completion.chat.ChatMessageRole;
import com.theokanning.openai.completion.CompletionRequest;
import java.util.List;
import java.util.HashMap;
import java.util.ArrayList;
import java.time.Duration;
import java.util.concurrent.CompletableFuture;

public class ChatGPTBot {
    public static final int MAX_MESSAGE_LENGTH = 2000;
    public static boolean gptEnabled = true;

    private static OpenAiService api;

    public static CompletableFuture<Void> init() {
      return CompletableFuture.runAsync(() -> {
          try {
              api = new OpenAiService("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", Duration.ofSeconds(5));
          } catch (Exception e) {
              e.printStackTrace();
          }
        }).exceptionally(throwable -> {
          //getLogger().severe("Error while initializing OpenAI service! Is your API key valid?");
          throwable.printStackTrace();
          return null;
      });
   }

   public static String getResponse(String message) {
        // Получение предыдущих сообщений пользователя
        ArrayList<String> inputs = new ArrayList<>();

        List<String> chatmessages = Minecraft.getMinecraft().ingameGUI.getChatGUI().getSentMessages();
        if (chatmessages.size() >= 5)
        {
            int startIndex = Math.max(0, chatmessages.size() - 5); // Получаем индекс первого сообщения в списке
            int endIndex = Math.min(chatmessages.size(), startIndex + 5); // Получаем индекс последнего сообщения в списке
        
            if (endIndex > startIndex) {
                for (int i = startIndex; i < endIndex; i++) {
                     String oldmessage = chatmessages.get(i).trim();
                     if (oldmessage.startsWith("!gpt")) {
                         inputs.add(oldmessage.replace("!gpt", "") + "\n");
                     }
                }
            } else {
            // Обработчик случая, когда сообщений в чате меньше чем 5
            // Можно добавить нужную логику или вывод сообщения об ошибке
            }
        }
        
        // Далее можно продолжить обработку сообщений
        
        // Добавление текущего сообщения пользователя в запрос
        inputs.add(message + "\n");
        //final List<ChatMessage> messages = new ArrayList<>();
        //ChatMessage systemMessage;
        // Формирование текста запроса
        String request = "";
        for (String input : inputs) {
           // messages.add(new ChatMessage(ChatMessageRole.SYSTEM.value(), input););
            request+=input;
        }
        CompletableFuture.supplyAsync(() -> {
            try {
               ChatCompletionRequest chatCompletionRequest = api.createChatCompletion(ChatCompletionRequest.builder()
    .model("gpt-3.5-turbo")
    .temperature(0.8)
    .maxTokens(MAX_MESSAGE_LENGTH)
   // .messages(messages)
    .build();
               //CompletionResponse completionResponse = api.complete(completionRequest).get();
               String response = api.createCompletion(completionRequest).getChoices().get(0).getText();

               return response;
            } catch (Exception e) {
              return "An error has occurred while processing your request. Please try again later.";
            }
        }
        //service.createChatCompletion(ChatCompletionRequest.builder()
        CompletionRequest completionRequest = CompletionRequest.builder()
                .model("ada")
                .prompt(request)
                .echo(true)
                .user("testing")
                .n(3)
                .build();
        
        // Получение ответа от OpenAI API
    
    /*public static void sendLongMessage(String message) {
        // Разделение сообщения на части
        ArrayList<String> chunks = new ArrayList<>();
        for (int i = 0; i < message.length(); i += MAX_MESSAGE_LENGTH) {
            chunks.add(message.substring(i, Math.min(i + MAX_MESSAGE_LENGTH, message.length())));
        }

        // Отправка каждой части сообщения по отдельности
        for (String chunk : chunks) {
            Minecraft.getMinecraft().player.sendMessage(new TextComponentString(chunk));
        }
    }*/
}
