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
//import com.theokanning.openai.completion.CompletionResult;
import com.theokanning.openai.completion.chat.ChatMessageRole;
import com.theokanning.openai.completion.chat.ChatMessage;
import java.util.List;
import java.time.Duration;
import java.util.Objects;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;
//import org.apache.http.HttpException;
import javax.xml.ws.http.HTTPException;
import java.util.function.Supplier; 
import java.lang.Thread;
import java.io.StringWriter;
import java.io.PrintWriter;
import java.util.Arrays;
import java.util.HashMap;
public class ChatGPTBot {
    public static final int MAX_MESSAGE_LENGTH = 200;
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
        /*CompletableFuture<String> future = CompletableFuture.supplyAsync(() -> {
          @Override
          public String get() {
            try {
                CompletionRequest completion = CompletionRequest.builder()
                        .model("text-davinci-003")
                        .prompt(request)
                        .echo(true)
                        //.user("testing")
                        .n(1)
                        .build();

                api.createChatCompletion(ChatCompletionRequest.builder()
                        .model("gpt-3.5-turbo")
                        .temperature(0.8)
                        .maxTokens(MAX_MESSAGE_LENGTH)
                        .build());

                String response = api.createCompletion(completion).getChoices().get(0).getText();

                return response;
            } catch (Exception e) {
                return "An error has occurred while processing your request. Please try again later.";
            }
         }
       }).exceptionally(throwable -> {
              if (throwable.getCause() instanceof HttpException) {
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
            }
            //throw new RuntimeException(throwable);
            return "Error!";
      });*/
CompletableFuture<String> future = CompletableFuture.supplyAsync(() -> {
    return new Supplier<String>() {
        @Override
        public String get() {
            try {
               /* ChatCompletionRequest completionRequest = ChatCompletionRequest.builder()
                        .model("gpt-3.5-turbo")
                        .temperature(0.8)
                        .messages(Arrays.asList(new ChatMessage(ChatMessageRole.SYSTEM.value(), request)))
                        .maxTokens(MAX_MESSAGE_LENGTH)
                        .build();

                CompletionRequest completion = CompletionRequest.builder()
                        .temperature(0.9) 
                        .maxTokens(50) 
                        .topP(1.0) 
                        .frequencyPenalty(0.0) 
                        .presencePenalty(0.6) 
                        //.prompt(request) 
                       // .echo(false) 
                        .model("text-davinci-003") 
                        .build();*/
                System.out.println("\nCreating completion...");
        CompletionRequest completionRequest = CompletionRequest.builder()
                .model("ada")
                .prompt("hi")
                .echo(true)
                .user("testing")
                .n(3)
                .build();
      //  api.createCompletion(completionRequest).getChoices().forEach(System.out::println);


        System.out.println("Streaming chat completion...");
        final List<ChatMessage> messages = new ArrayList<>();
        final ChatMessage systemMessage = new ChatMessage(ChatMessageRole.SYSTEM.value(), "hi");
        messages.add(systemMessage);
        ChatCompletionRequest chatCompletionRequest = ChatCompletionRequest
                .builder()
                .model("gpt-3.5-turbo")
                .messages(messages)
                .n(1)
                .maxTokens(50)
                .logitBias(new HashMap<>())
                .build();

       /* api.streamChatCompletion(chatCompletionRequest)
                //.doOnError(Throwable::printStackTrace)
                .blockingForEach(System.out::println);
*/
        //api.shutdownExecutor();
                String response = api.createCompletion(completionRequest).getChoices().get(0).getText();

                return response;
            } catch (Exception e) {
                //return "An error has occurred while processing your request. Please try again later.";
                StringWriter sw = new StringWriter();
                PrintWriter pw = new PrintWriter(sw);
                e.printStackTrace(pw);
                String stackTrace = sw.toString();
                return stackTrace;
            }
        }
    }.get();
}).exceptionally(throwable -> {
    if (throwable instanceof HTTPException) {
        HTTPException exception = (HTTPException) throwable;
        switch (exception.getStatusCode()) {
            case 401:
                return "Invalid API key! Please check your configuration.";
            case 429:
                return "Too many requests! Please wait a few seconds and try again.";
            case 500:
                return "OpenAI service is currently unavailable. Please try again later.";
            default:
                return "Unknown error! Please try again later. If this error persists, contact the plugin developer.";
        }
    }
    else {
      Exception exception = new Exception("err1", throwable);
    //if (exception) {
      StringWriter sw = new StringWriter();
      PrintWriter pw = new PrintWriter(sw);
      exception.printStackTrace(pw);
      String stackTrace = sw.toString();
      return stackTrace;
    }
    // Now you can handle or rethrow the 'exception'
    //return "Unhandled exception!";
});
      try {
         return future.get();
      } catch (InterruptedException e) {
        Thread.currentThread().interrupt();
        return "InterruptedException";
      } catch (ExecutionException e) {
        return "ExecutionException";
      }
     // return "None";
   }
}
