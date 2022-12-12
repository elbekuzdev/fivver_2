package uz.developers.main.controller;

import static org.mockito.Mockito.any;
import static org.mockito.Mockito.when;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.request.MockHttpServletRequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import uz.developers.main.dto.ResponseDto;
import uz.developers.main.service.HashTagService;

@ContextConfiguration(classes = {HashTagController.class})
@ExtendWith(SpringExtension.class)
class HashTagControllerTest {
    @Autowired
    private HashTagController hashTagController;

    @MockBean
    private HashTagService hashTagService;

    /**
     * Method under test: {@link HashTagController#delete(Integer)}
     */
    @Test
    void testDelete() throws Exception {
        when(hashTagService.delete((Integer) any())).thenReturn(new ResponseEntity<>(HttpStatus.CONTINUE));
        MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.delete("/main/hashtag/delete/{id}", 1);
        ResultActions actualPerformResult = MockMvcBuilders.standaloneSetup(hashTagController)
                .build()
                .perform(requestBuilder);
        actualPerformResult.andExpect(MockMvcResultMatchers.status().is(100));
    }

    /**
     * Method under test: {@link HashTagController#delete(Integer)}
     */
    @Test
    void testDelete2() throws Exception {
        when(hashTagService.delete((Integer) any()))
                .thenReturn(new ResponseEntity<>(ResponseDto.getSuccess(1, "?"), HttpStatus.CONTINUE));
        MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.delete("/main/hashtag/delete/{id}", 1);
        ResultActions actualPerformResult = MockMvcBuilders.standaloneSetup(hashTagController)
                .build()
                .perform(requestBuilder);
        actualPerformResult.andExpect(MockMvcResultMatchers.status().is(100))
                .andExpect(MockMvcResultMatchers.content().contentType("application/json"))
                .andExpect(MockMvcResultMatchers.content().string("{\"statusCode\":1,\"message\":\"?\",\"data\":null}"));
    }
}

