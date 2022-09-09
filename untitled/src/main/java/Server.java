import com.sun.net.httpserver.HttpContext;
import com.sun.net.httpserver.HttpServer;
import controller.HttpHandlerCode;
import controller.HttpHandlerJson;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.util.concurrent.Executors;
import java.util.concurrent.ThreadPoolExecutor;

public class Server {

    public static void main (String [] args) throws IOException {
        final HttpServer server = HttpServer.create(new InetSocketAddress("localhost", 4000), 0);
        final HttpContext contextCode = server.createContext("/json", new HttpHandlerJson());
        final HttpContext contextJson = server.createContext("/code", new HttpHandlerCode());
        final ThreadPoolExecutor threadPoolExecutor = (ThreadPoolExecutor) Executors.newFixedThreadPool(10);
        server.setExecutor(threadPoolExecutor);

        server.start();
    }
}
