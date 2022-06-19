package com.enovation.assingmentsolution.controllers;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.restdocs.AutoConfigureRestDocs;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;

import static org.hamcrest.Matchers.*;
import static org.springframework.restdocs.mockmvc.MockMvcRestDocumentation.document;
import static org.springframework.restdocs.mockmvc.RestDocumentationRequestBuilders.*;
import static org.springframework.restdocs.payload.PayloadDocumentation.*;
import static org.springframework.restdocs.payload.PayloadDocumentation.fieldWithPath;
import static org.springframework.restdocs.request.RequestDocumentation.parameterWithName;
import static org.springframework.restdocs.request.RequestDocumentation.pathParameters;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
@AutoConfigureTestDatabase
@AutoConfigureRestDocs(outputDir = "target/snippets")
public class PostControllerDocumentationTest {

    @Autowired
    protected MockMvc mvc;

    @Test
    @WithMockUser(username = "user", password = "testpass")
    void createPost() throws Exception {
        mvc.perform(
                        post("/posts")
                                .contentType(MediaType.APPLICATION_JSON)
                                .content("{\"title\":\"new post\",\"content\":\"new content\",\"creationDate\":\"2022-05-25 13:32:43\"}")
                )
                .andDo(print())
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.id", greaterThan(0)))
                .andDo(document("post/createPost",
                        requestFields(
                                fieldWithPath("title").description("Title of the  Post"),
                                fieldWithPath("content").description("Content of the  Post"),
                                fieldWithPath("creationDate").description("Creation date of the  Post in format yyyy-MM-dd HH:mm:ss")
                        ),
                        responseFields(
                                fieldWithPath("id").description("Id of the new saved Post"),
                                fieldWithPath("title").description("Title of the  Post"),
                                fieldWithPath("content").description("Content of the  Post"),
                                fieldWithPath("creationDate").description("Creation date of the  Post in format yyyy-MM-dd HH:mm:ss")

                        )));
    }
    @Test
    @WithMockUser(username = "user", password = "testpass")
    void updatePost() throws Exception {
        mvc.perform(
                        put("/posts/2")
                                .contentType(MediaType.APPLICATION_JSON)
                                .content("{\"title\":\"updated post\",\"content\":\"updated content\",\"creationDate\":\"2022-05-25 13:32:43\"}")
                )
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.title", equalTo("updated post")))
                .andDo(document("post/updatePost",
                        requestFields(
                                fieldWithPath("title").description("Title of the  Post"),
                                fieldWithPath("content").description("Content of the  Post"),
                                fieldWithPath("creationDate").description("Creation date of the  Post in format yyyy-MM-dd HH:mm:ss")
                        ),
                        responseFields(
                                fieldWithPath("id").description("Id of the updated  Post"),
                                fieldWithPath("title").description("Title of the updated  Post"),
                                fieldWithPath("content").description("Content of the updated Post"),
                                fieldWithPath("creationDate").description("Creation date of the updated Post in format yyyy-MM-dd HH:mm:ss")

                        )));
    }
    @Test
    @WithMockUser(username = "user", password = "testpass")
    void getPostDetails() throws Exception {
        mvc.perform((get("/posts/{id}",1)))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.title", equalTo("Welcome")))
                .andDo(document("post/getPostById",pathParameters(
                        parameterWithName("id").description("Id of the Post")),
                        responseFields(
                                fieldWithPath("id").description("Id of the new saved Post"),
                                fieldWithPath("title").description("Title of the  Post"),
                                fieldWithPath("content").description("Content of the  Post"),
                                fieldWithPath("creationDate").description("Creation date of the  Post in format yyyy-MM-dd HH:mm:ss")

                        )));
    }
    @Test
    @WithMockUser(username = "user", password = "testpass")
    void deletePost() throws Exception {
        mvc.perform(
                        delete("/posts/{id}",2)
                )
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.response", equalTo(true)))
                .andDo(document("post/deletePost",pathParameters(
                                parameterWithName("id").description("Id of the Post")),
                        responseFields(
                                fieldWithPath("response").description("Return True when Post delete succesfully"),
                                fieldWithPath("message").description("Post deleted successfully message"),
                                fieldWithPath("httpStatus").description("httpStatus OK")

                        )));
    }
    @Test
    @WithMockUser(username = "user", password = "testpass")
    void listPosts() throws Exception {
        mvc.perform((get("/posts")))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.[*].title", hasItem("Welcome")))
                .andDo(document("post/listPosts"));
    }
}
