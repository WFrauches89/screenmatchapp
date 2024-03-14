package br.com.alura.screenmatch.services;

import com.theokanning.openai.completion.CompletionRequest;
import com.theokanning.openai.service.OpenAiService;

public class ConsultaChatGPT {

        public static String obterTraducao(String texto) {
            OpenAiService service = new OpenAiService("chave aqui");

            CompletionRequest requisicao = CompletionRequest.builder()
                    .model("modelo")
                    .prompt("Traduza para o português o texto: " + texto)
                    .maxTokens(1000)
                    .temperature(0.7)
                    .build();

            var resposta = service.createCompletion(requisicao);
            return resposta.getChoices().get(0).getText();
        }


}
