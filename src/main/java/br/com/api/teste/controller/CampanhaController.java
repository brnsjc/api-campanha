package br.com.api.teste.controller;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.query.Param;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.api.teste.repository.CampanhaRepository;
import br.com.api.teste.response.CampanhaResponse;
import br.com.api.teste.service.CampanhaService;

@RestController
@RequestMapping(value = "/api")
public class CampanhaController {

	@Autowired
	CampanhaService campanhaService;
	
	
	@GetMapping(value = "/campanhas")
	public List<CampanhaResponse> consultar() {
		
		return campanhaService.consultarCampanha();
	}
	
	@PostMapping(value = "/atualizarCampanha")
	public CampanhaResponse atualizarCampanha(@RequestBody CampanhaResponse campanhaResponse) throws ParseException {
		
		
		DateFormat dform = new SimpleDateFormat("yy/MM/dd");
		
		Date inicioVigencia = campanhaResponse.getVigenciaInicial();
		Date finalVigencia = campanhaResponse.getVigenciaFinal();
		
		System.out.print(inicioVigencia);
		
		String dataFinal = "20/10/12";
		String dataInicial = "20/08/10";
		
		Date date1 = new SimpleDateFormat("yy/MM/dd").parse(dataFinal);
		Date date2 = new SimpleDateFormat("yy/MM/dd").parse(dataInicial);
		
		
		return campanhaService.atualizarCampanha(campanhaResponse);
	}
	
	@PostMapping(value = "/inserirCampanha")
	public CampanhaResponse inserirCampanha(@RequestBody CampanhaResponse campanhaResponse) throws ParseException {
		
		
		DateFormat dform = new SimpleDateFormat("yy/MM/dd");
		
		Date obj = new Date();
		
		String dataFinal = "20/10/12";
		String dataInicial = "20/08/10";
		
		Date date1 = new SimpleDateFormat("yy/MM/dd").parse(dataFinal);
		Date date2 = new SimpleDateFormat("yy/MM/dd").parse(dataInicial);
		
		
		return campanhaService.inserirCampanha(campanhaResponse);
	}
	
	public CampanhaResponse deletarCampanha(@RequestBody CampanhaResponse campanhaResponse) {
		
		campanhaService.deletarCampanhaById(campanhaResponse.getId_campanha());
		return campanhaResponse;
		
	}
}
