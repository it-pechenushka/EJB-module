package ru.ilyai.lab2.helper;

import lombok.SneakyThrows;
import org.json.JSONObject;

import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.core.GenericType;
import javax.ws.rs.core.MediaType;
import java.util.List;
import java.util.stream.Collectors;

public class HelperFactory {

    @SneakyThrows
    public static Client getClient() {
        return ClientBuilder.newBuilder().build();
    }

    public static List<JSONObject> getAvailableServices(){
        JSONObject services = new JSONObject(getClient()
                .target(getConsulUrl() + "/v1/agent/services")
                .request(MediaType.APPLICATION_JSON)
                .get(new GenericType<String>(){}));

        return services.keySet().stream().map(services::getJSONObject).collect(Collectors.toList());
    }

    public static String getConsulUrl() {
        return System.getProperty("CONSUL_URL");
    }

    public static String getServiceUrl(String host, int port){
        return String.format("http://%s:%d", host, port);
    }
}
