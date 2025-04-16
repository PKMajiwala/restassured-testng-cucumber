package mocks;

import com.github.tomakehurst.wiremock.WireMockServer;
import static com.github.tomakehurst.wiremock.client.WireMock.*;

public class WireMockServerInitializer {
    public static WireMockServer startMockServer() {
        WireMockServer wireMockServer = new WireMockServer(8089);
        wireMockServer.start();

        wireMockServer.stubFor(get(urlEqualTo("/api/mock-user"))
            .willReturn(aResponse()
                .withHeader("Content-Type", "application/json")
                .withBody("{\"id\": 999, \"name\": \"Mock User\"}")));

        return wireMockServer;
    }
}
