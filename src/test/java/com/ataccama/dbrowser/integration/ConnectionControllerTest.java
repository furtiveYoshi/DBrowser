package com.ataccama.dbrowser.integration;

import com.ataccama.dbrowser.controller.ConnectionController;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import java.nio.charset.StandardCharsets;

@RunWith(SpringRunner.class)
@AutoConfigureMockMvc
@SpringBootTest
@TestPropertySource(
        locations = "classpath:application-test.properties")
public class ConnectionControllerTest {
    private static final MediaType ERROR_MEDIA_TYPE = new MediaType(MediaType.TEXT_PLAIN, StandardCharsets.UTF_8);

    @Autowired
    private MockMvc mockMvc;

    @Test
    public void whenPostRequestToConnectionAndValidConnection_thenCorrectResponse() throws Exception {
        String connection = "{\n" +
                "    \"name\":\"localtest\",\n" +
                "    \"hostName\":\"localhost\",\n" +
                "    \"port\":3306,\n" +
                "    \"databaseName\":\"mysql\",\n" +
                "    \"username\":\"root\",\n" +
                "    \"password\":\"root\"\n" +
                "}";
        mockMvc.perform(MockMvcRequestBuilders.post("/api/dbrowser/v1/connection")
                .content(connection)
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().isCreated())
                .andExpect(MockMvcResultMatchers.content()
                        .contentType(MediaType.APPLICATION_JSON));
    }

    @Test
    public void whenPostRequestToConnectionAndInvalidConnection_thenCorrectResponse() throws Exception {
        String connection = "{\n" +
                "    \"name\":\"localtest\",\n" +
                "    \"port\":3306,\n" +
                "    \"databaseName\":\"mysql\",\n" +
                "    \"username\":\"root\",\n" +
                "    \"password\":\"root\"\n" +
                "}";
        mockMvc.perform(MockMvcRequestBuilders.post("/api/dbrowser/v1/connection")
                .content(connection)
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().isInternalServerError())
                .andExpect(MockMvcResultMatchers.content()
                        .contentType(ERROR_MEDIA_TYPE));
    }

    @Test
    public void whenPutRequestToConnectionAndValidConnection_thenCorrectResponse() throws Exception {
        String connection = "{\n" +
                "    \"id\":\"1\",\n" +
                "    \"name\":\"localtest\",\n" +
                "    \"hostName\":\"localhost\",\n" +
                "    \"port\":3306,\n" +
                "    \"databaseName\":\"mysql\",\n" +
                "    \"username\":\"root\",\n" +
                "    \"password\":\"root\"\n" +
                "}";
        mockMvc.perform(MockMvcRequestBuilders.put("/api/dbrowser/v1/connection")
                .content(connection)
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content()
                        .contentType(MediaType.APPLICATION_JSON));
    }

    @Test
    public void whenPutRequestToConnectionAndInvalidConnection_thenCorrectResponse() throws Exception {
        String connection = "{\n" +
                "    \"name\":\"localtest\",\n" +
                "    \"hostName\":\"localhost\",\n" +
                "    \"port\":3306,\n" +
                "    \"databaseName\":\"mysql\",\n" +
                "    \"username\":\"root\",\n" +
                "    \"password\":\"root\"\n" +
                "}";
        mockMvc.perform(MockMvcRequestBuilders.put("/api/dbrowser/v1/connection")
                .content(connection)
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().isBadRequest())
                .andExpect(MockMvcResultMatchers.content()
                        .contentType(ERROR_MEDIA_TYPE));
    }

    @Test
    public void whenDeleteRequestToConnectionAndExistingConnection_thenCorrectResponse() throws Exception {
        String connection = "{\n" +
                "    \"id\":\"1\",\n" +
                "    \"name\":\"localtest\",\n" +
                "    \"hostName\":\"localhost\",\n" +
                "    \"port\":3306,\n" +
                "    \"databaseName\":\"mysql\",\n" +
                "    \"username\":\"root\",\n" +
                "    \"password\":\"root\"\n" +
                "}";
        mockMvc.perform(MockMvcRequestBuilders.put("/api/dbrowser/v1/connection")
                .content(connection)
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content()
                        .contentType(MediaType.APPLICATION_JSON));

        mockMvc.perform(MockMvcRequestBuilders.delete("/api/dbrowser/v1/connection")
                .param("id", "1")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().isOk());
    }

    @Test
    public void whenDeleteRequestToConnectionAndNotExistingConnection_thenCorrectResponse() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.delete("/api/dbrowser/v1/connection")
                .param("id", "1")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().isInternalServerError())
                .andExpect(MockMvcResultMatchers.content()
                        .contentType(ERROR_MEDIA_TYPE));
    }

    @Test
    public void whenGetRequestToConnectionAndExistingConnection_thenCorrectResponse() throws Exception {
        String connection = "{\n" +
                "    \"id\":\"2\",\n" +
                "    \"name\":\"localtest\",\n" +
                "    \"hostName\":\"localhost\",\n" +
                "    \"port\":3306,\n" +
                "    \"databaseName\":\"mysql\",\n" +
                "    \"username\":\"root\",\n" +
                "    \"password\":\"root\"\n" +
                "}";
        mockMvc.perform(MockMvcRequestBuilders.put("/api/dbrowser/v1/connection")
                .content(connection)
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content()
                        .contentType(MediaType.APPLICATION_JSON));

        mockMvc.perform(MockMvcRequestBuilders.get("/api/dbrowser/v1/connection")
                .param("id", "2")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().isOk());
    }

    @Test
    public void whenGetRequestToConnectionAndNotExistingConnection_thenCorrectResponse() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.get("/api/dbrowser/v1/connection")
                .param("id", "1")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().isNotFound())
                .andExpect(MockMvcResultMatchers.content()
                        .contentType(ERROR_MEDIA_TYPE));
    }
}
