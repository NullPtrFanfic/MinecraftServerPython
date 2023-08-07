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
import com.theokanning.openai.completion.CompletionResult;
//import com.theokanning.openai.completion.ChatCompletionResult;
import java.util.List;
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
            throwable.printStackTrace();
            return null;
        });
    }

    public static String getResponse(String message) {
        // Getting previous user messages
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
                // Handler for cases where there are fewer than 5 messages in the chat
                // You can add necessary logic or display an error message here
            }
        }

        // Continue processing messages

        // Adding the current user message to the request
        inputs.add(message + "\n");
        String requestMessages = "";
        // Forming the request text
       // String request = "";
        for (String input : inputs) {
            requestMessages += input;
        }
        final String request = requestMessages;
        CompletableFuture.supplyAsync(() -> {
            try {
                api.createChatCompletion(ChatCompletionRequest.builder()
                        .model("gpt-3.5-turbo")
                        .temperature(0.8)
                        .maxTokens(MAX_MESSAGE_LENGTH)
                        .build());

                CompletionResult completionRequest = api.createCompletion(CompletionRequest.builder()
                        .model("ada")
                        .prompt(request)
                        .echo(true)
                        .user("testing")
                        .n(3)
                        .build());

                String response = completionRequest.getChoices().get(0).getText();

                return response;
            } catch (Exception e) {
                return "An error has occurred while processing your request. Please try again later.";
            }
        }).exceptionally(throwable -> {
           /* if (throwable.getCause() instanceof HttpException) {
                String reason = switch (((HttpException) throwable.getCause())) {
                    case 401:
                        return "Invalid API key! Please check your configuration.";
                        break;
                    case 429:
                        return "Too many requests! Please wait a few seconds and try again.";
                        break;
                    case 500:
                        return "OpenAI service is currently unavailable. Please try again later.";
                        break;
                    default:
                        return "Unknown error! Please try again later. If this error persists, contact the plugin developer.";
                        break;
                };
            }*/
            throw new RuntimeException(throwable);
      });
   }
}
