package com.store;

import static org.junit.jupiter.api.Assertions.assertTrue;

import java.time.LocalDate;

import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.skyscreamer.jsonassert.JSONAssert;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.RequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import com.store.api.TradeController;
import com.store.entity.TradeEntity;
import com.store.exception.LowerVersionException;
import com.store.service.TradeService;
import com.store.validator.TradeValidator;

@WebMvcTest(TradeController.class)
@RunWith(SpringRunner.class)
class StoreApplicationTests {
	@Autowired
	private MockMvc mockMvc;

	@MockBean
	TradeValidator validatiorService;
	@MockBean
	TradeService service;
	

	@Test
	public void saveTrade() throws Exception {
	   Mockito.when(validatiorService.validation(Mockito.any())).thenReturn(true);
	   TradeEntity entity=new TradeEntity();
	   entity.setBookId("bk1");
	   entity.setCounterPartyId("CP1");
	   entity.setTradId("tst");
	   entity.setVersion(1L);
	   entity.setMatruirtyDate(LocalDate.now());
	   entity.setCreateDate(LocalDate.now());
	   entity.setExpired('Y');
       Mockito.when(service.saveTrade(Mockito.any())).thenReturn(entity);
       
       RequestBuilder requestBuilder = MockMvcRequestBuilders.post("/api/v1/trade")
    		   .accept(MediaType.APPLICATION_JSON_VALUE)
    		   .contentType(MediaType.APPLICATION_JSON_VALUE)
    		   .content("{\"tradId\":\"tst\",\"version\":1,\"counterPartyId\":\"CP1\",\"bookId\":\"bk1\",\"matruirtyDate\":\"2022-06-29\"}");

		MvcResult result = mockMvc.perform(requestBuilder).andReturn();
		String expected = "{\"tradId\":\"tst\",\"version\":1,\"counterPartyId\":\"CP1\",\"bookId\":\"bk1\",\"matruirtyDate\":\"2022-06-30\",\"createDate\":\"2022-06-30\",\"expired\":\"Y\"}";
		JSONAssert.assertEquals(expected, result.getResponse().getContentAsString(), false);
	}
	
	@Test
	public void saveTrade_exception() throws Exception {
	   Mockito.when(validatiorService.validation(Mockito.any())).thenThrow(new LowerVersionException("Lower version"));
	   TradeEntity entity=new TradeEntity();
       Mockito.when(service.saveTrade(Mockito.any())).thenReturn(entity);
       
       RequestBuilder requestBuilder = MockMvcRequestBuilders.post("/api/v1/trade")
    		   .accept(MediaType.APPLICATION_JSON_VALUE)
    		   .contentType(MediaType.APPLICATION_JSON_VALUE)
    		   .content("{\"tradId\":\"tst\",\"version\":0,\"counterPartyId\":\"CP1\",\"bookId\":\"bk1\",\"matruirtyDate\":\"2022-06-29\"}");

		MvcResult result = mockMvc.perform(requestBuilder).andReturn();
		assertTrue(result.getResponse().getContentAsString().contains("Lower version"));
		
	}

}
