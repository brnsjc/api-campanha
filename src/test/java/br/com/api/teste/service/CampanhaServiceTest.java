package br.com.api.teste.service;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.atLeastOnce;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;

import org.assertj.core.util.Arrays;
import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.Spy;
import org.springframework.beans.factory.annotation.Autowired;

import br.com.api.teste.business.CampanhaBusiness;
import br.com.api.teste.response.CampanhaResponse;


public class CampanhaServiceTest {

	@InjectMocks
	@Autowired
	CampanhaService campanhaService;
	
	@Mock
	CampanhaResponse campanhaResponse;
	
	@Mock
	CampanhaBusiness campanhaBusiness;
	
	@Spy
	List<CampanhaResponse> campanhas = new ArrayList<CampanhaResponse>();
	
	@Before
	public void inciarMocks() {
		
		MockitoAnnotations.initMocks(this);
		
		campanhas.add(campanhaResponse);
		campanhas.add(campanhaResponse);
		campanhas.add(campanhaResponse);
		campanhas.add(campanhaResponse);
	}
	
	@Test
	public void getCampanhaTest() {
		
		when(campanhaResponse.getId_campanha()).thenReturn(1);
		
		System.out.print("ID: "+campanhaResponse.getId_campanha());
		System.out.print("\nVigencia: "+campanhaResponse.getVigenciaFinal());
		System.out.print("\nNome Campanha: "+campanhas.size());

		assertThat(campanhaResponse.getId_campanha()).isEqualTo(1);
	}
	
	@Test
	public void consultarCampanhaTest() {
		
		System.out.println("Get Campanhas : " + campanhas);
		
		when(campanhaService.consultarCampanha()).thenReturn(campanhas);
		
		assertThat(campanhaService.consultarCampanha()).isEqualTo(campanhas);
		
	}
	
	@Test
	public void atualizarCampanhaTest() {
		
		
		when(campanhaService.atualizarCampanha(campanhaResponse)).thenReturn(campanhaResponse);
		
		assertThat(campanhaService.atualizarCampanha(campanhaResponse)).isEqualTo(campanhaResponse);
		verify(campanhaService.atualizarCampanha(campanhaResponse),times(1));
		
	}
	
	@Test
	public void deletarCampanhaByIdTest() {
		
		when(campanhaService.deletarCampanhaById(0)).thenReturn("deletado");
		//assertThat(campanhaService.atualizarCampanha(campanhaResponse)).isEqualTo(campanhaResponse);
		assertThat(campanhaService.deletarCampanhaById(0)).isEqualTo("deletado");
		
	}
}
