package com.progresssoft.warehouse;

import static org.assertj.core.api.Assertions.assertThat;

import java.math.BigDecimal;
import java.util.Date;

import com.progresssoft.warehouse.exception.ProgressoftException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import com.progresssoft.warehouse.business.DealDetailsBusiness;
import com.progresssoft.warehouse.repository.entity.DealDetails;

@SpringBootTest
@RunWith(SpringRunner.class)
@WebAppConfiguration
class DealDetailsTests {

	@SuppressWarnings("unused")
	private MockMvc mvc;

	@Autowired
	private WebApplicationContext webApplicationContext;

	@MockBean
	private DealDetailsBusiness dealDetailsBusiness;

	@BeforeEach
	public void setUp() throws ProgressoftException {
		mvc = MockMvcBuilders.webAppContextSetup(webApplicationContext).build();

		// Mock calls
		DealDetails deal = new DealDetails();
		deal.setDealAmount(new BigDecimal(50));
		deal.setDealTime(new Date());
		deal.setFromCurrencyIsoCode("TES");
		deal.setToCurrencyIsoCode("AED");

		Mockito.when(dealDetailsBusiness.getDocument(1)).thenReturn(deal);
	}

	@Test
	public void testSaveNewDealDetails() throws ProgressoftException {

		DealDetails deal = new DealDetails();
		deal.setDealAmount(new BigDecimal(50));
		deal.setDealTime(new Date());
		deal.setFromCurrencyIsoCode("TES");
		deal.setToCurrencyIsoCode("AED");
		dealDetailsBusiness.saveDocument(deal);
		DealDetails dealAfter = dealDetailsBusiness.getDocument(1);
		assertThat(dealAfter.getFromCurrencyIsoCode()).isEqualTo("TES");
	}

}
