package server;

import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpHandler;
import com.sun.net.httpserver.HttpServer;
import service.AccountManager;
import model.Account;

import java.io.IOException;
import java.io.OutputStream;
import java.net.InetSocketAddress;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public class MoneyTransferHttpServer {

    public static void main(String[] args) throws IOException {
        AccountManager accountManager = new AccountManager(initlizeAccount());

        HttpServer server = HttpServer.create(new InetSocketAddress(8000), 0);

        server.createContext("/transfer", new HttpHandler() {
            @Override
            public void handle(HttpExchange exchange) throws IOException {
                if (!"POST".equals(exchange.getRequestMethod())) {
                    exchange.sendResponseHeaders(405, 0);
                    return;
                }

                String query = new String(exchange.getRequestBody().readAllBytes());
                Map<String, String> params = parseQuery(query);
                String from = params.get("from");
                String to = params.get("to");
                int amount = Integer.parseInt(params.get("amount"));

                String response = accountManager.transfer(from, to, amount);
                byte[] respBytes = response.getBytes();
                exchange.sendResponseHeaders(200, respBytes.length);
                try (OutputStream os = exchange.getResponseBody()) {
                    os.write(respBytes);
                }
            }
        });

        server.createContext("/balances", new HttpHandler() {
            @Override
            public void handle(HttpExchange exchange) throws IOException {
                String response = accountManager.getBalances();
                byte[] respBytes = response.getBytes();
                exchange.sendResponseHeaders(200, respBytes.length);
                try (OutputStream os = exchange.getResponseBody()) {
                    os.write(respBytes);
                }
            }
        });

        server.setExecutor(null);
        server.start();
        System.out.println("Server started on port 8000");
    }

    private static Map<String, String> parseQuery(String query) {
        Map<String, String> result = new ConcurrentHashMap<>();
        String[] pairs = query.split("&");
        for (String pair : pairs) {
            String[] keyValue = pair.split("=");
            if (keyValue.length == 2) {
                result.put(keyValue[0], keyValue[1]);
            }
        }
        return result;
    }

    private static Map<String, Account> initlizeAccount () {
       return Map.of(
            "Mark", new Account("Mark", 100),
            "Jane", new Account("Jane", 50),
            "Adam", new Account("Adam", 0)
        );
    }
}