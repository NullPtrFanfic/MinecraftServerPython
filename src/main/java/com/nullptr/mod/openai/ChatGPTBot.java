package com.nullptr.mod.openai;

import java.util.ArrayList;
import net.minecraft.client.Minecraft;
import net.minecraft.util.text.TextComponentString;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;
import net.minecraftforge.common.MinecraftForge;
import com.theokanning.openai.completion.chat.ChatCompletionRequest;
import com.theokanning.openai.service.OpenAiService;
import com.theokanning.openai.completion.CompletionRequest;
import java.util.List;
import java.time.Duration;
import java.util.concurrent.CompletableFuture;

@Mod(modid = "openai_mod", version = "1.0")
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
            throwable.printStackTrace();
            return null;
        });
    }

    public static String getResponse(String message) {
        ArrayList<String> inputs = new ArrayList<>();

        List<String> chatmessages = Minecraft.getMinecraft().ingameGUI.getChatGUI().getSentMessages();
        if (chatmessages.size() >= 5) {
            int startIndex = Math.max(0, chatmessages.size() - 5);
            int endIndex = Math.min(chatmessages.size(), startIndex + 5);

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

        // Добавление текущего сообщения пользователя в запрос
        inputs.add(message + "\n");
        //final List<ChatMessage> messages = new ArrayList<>();
        //ChatMessage systemMessage;
        // Формирование текста запроса
        StringBuilder request = new StringBuilder();
        for (String input : inputs) {
            // messages.add(new ChatMessage(ChatMessageRole.SYSTEM.value(), input););
            request.append(input);
        }
        return CompletableFuture.supplyAsync(() -> {
            try {
                ChatCompletionRequest chatCompletionRequest = ChatCompletionRequest.builder()
                        .model("gpt-3.5-turbo")
                        .temperature(0.8)
                        .maxTokens(MAX_MESSAGE_LENGTH)
                        // .messages(messages)
                        .build();
                CompletionRequest completionRequest = CompletionRequest.builder()
                        .model("ada")
                        .prompt(request.toString())
                        .echo(true)
                        .user("testing")
                        .n(3)
                        .build();
                String response = api.createCompletion(completionRequest).getChoices().get(0).getText();

                return response;
            } catch (Exception e) {
                return "An error has occurred while processing your request. Please try again later.";
            }
        }).exceptionally(throwable -> {
            if (throwable.getCause() instanceof HttpException) {
                String reason = switch (((HttpException) throwable.getCause()).response().code()) {
                    case 401 -> "Invalid API key! Please check your configuration.";
                    case 429 -> "Too many requests! Please wait a few seconds and try again.";
                    case 500 -> "OpenAI service is currently unavailable. Please try again later.";
                    default -> "Unknown error! Please try again later. If this error persists, contact the plugin developer.";
                };
                throw new RuntimeException(reason, throwable);
            }
            throw new RuntimeException(throwable);
        }).join(); // Wait for the CompletableFuture to complete and return the result
    }
}
