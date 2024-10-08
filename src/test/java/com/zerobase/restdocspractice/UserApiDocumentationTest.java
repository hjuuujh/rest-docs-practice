package com.zerobase.restdocspractice;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.zerobase.restdocspractice.controller.UserApiController;
import com.zerobase.restdocspractice.model.User;
import com.zerobase.restdocspractice.service.UserService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.restdocs.RestDocumentationContextProvider;
import org.springframework.restdocs.RestDocumentationExtension;

import static com.epages.restdocs.apispec.MockMvcRestDocumentationWrapper.document;
import static com.epages.restdocs.apispec.ResourceDocumentation.resource;
import com.epages.restdocs.apispec.ResourceSnippetParameters;
import org.springframework.restdocs.mockmvc.RestDocumentationRequestBuilders;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import java.time.LocalDateTime;

import static org.mockito.BDDMockito.given;
import static org.springframework.restdocs.mockmvc.MockMvcRestDocumentation.documentationConfiguration;
import static org.springframework.restdocs.operation.preprocess.Preprocessors.*;
import static org.springframework.restdocs.payload.PayloadDocumentation.fieldWithPath;
import static org.springframework.restdocs.payload.PayloadDocumentation.responseFields;
import static org.springframework.restdocs.request.RequestDocumentation.parameterWithName;
import static org.springframework.restdocs.request.RequestDocumentation.pathParameters;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;

@WebMvcTest(UserApiController.class)
@ExtendWith(RestDocumentationExtension.class)
public class UserApiDocumentationTest {

    @MockBean
    private UserService userService;

    @Autowired
    private MockMvc mvc;

    @BeforeEach
    public void setUp(WebApplicationContext context,
                      RestDocumentationContextProvider restDocumentation) {
        this.mvc = MockMvcBuilders.webAppContextSetup(context)
                .apply(documentationConfiguration(restDocumentation))
                .build();
    }

    // pathvariable 사용지 MockMvcBuilders 보다 RestDocumentationRequestBuilders 사용
    @Test
    public void testRead() throws Exception {
        User user = User.builder()
                .id(1L)
                .account("account1")
                .email("user1@gmail.com")
                .phoneNumber("01012345678")
                .createdAt(LocalDateTime.now())
                .build();

        given(userService.read(1L)).willReturn(user);

        this.mvc.perform(RestDocumentationRequestBuilders.get("/api/user/{id}", 1)
                        .characterEncoding("utf-8"))
                .andDo(print())
                .andDo(document("user",
                        pathParameters(
                                parameterWithName("id").description("사용자 id")
                        ),
                        responseFields(
                                fieldWithPath("resultCode").description("응답코드"),
                                fieldWithPath("data.id").description("id"),
                                fieldWithPath("data.account").description("계정"),
                                fieldWithPath("data.email").description("이메일"),
                                fieldWithPath("data.phoneNumber").description("전화번호"),
                                fieldWithPath("data.createdAt").description("생성시간"),
                                fieldWithPath("data.updatedAt").description("수행시간")
                        )
                ));
        mvc.perform(RestDocumentationRequestBuilders.get("/api/user/{id}", 1))
                .andDo(document("user",
                        preprocessRequest(prettyPrint()),
                        preprocessResponse(prettyPrint()),
                        resource(
                                ResourceSnippetParameters.builder()
                                        .description("사용자의 정보를 생성/조회/수정/삭제 합니다.")
                                        .summary("사용자 정보")
                                        .pathParameters(
                                                parameterWithName("id").description("사용자 id")
                                        )
                                                .
                                responseFields(
                                        fieldWithPath("resultCode").description("응답코드"),
                                        fieldWithPath("data.id").description("id"),
                                        fieldWithPath("data.account").description("계정"),
                                        fieldWithPath("data.email").description("이메일"),
                                        fieldWithPath("data.phoneNumber").description("전화번호"),
                                        fieldWithPath("data.createdAt").description("생성시간"),
                                        fieldWithPath("data.updatedAt").description("수행시간")
                                ).build()
                        )
                ));
    }
}
