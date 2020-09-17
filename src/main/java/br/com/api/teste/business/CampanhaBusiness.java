package br.com.api.teste.business;


import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.spi.CalendarDataProvider;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import br.com.api.teste.entity.CampanhaEntity;
import br.com.api.teste.repository.CampanhaRepository;
import br.com.api.teste.response.CampanhaResponse;

@Component
public class CampanhaBusiness {
	
	@Autowired
	CampanhaRepository campanhaRep;
	
	CampanhaResponse campanhaResponse ;
	
	List<CampanhaEntity> campanhas ;
	
	CampanhaEntity campanhaDataAtualiza = new CampanhaEntity();
	
	DateFormat dform = new SimpleDateFormat("yy/MM/dd");
	
	Date obj = new Date();
	
	Calendar c = Calendar.getInstance();
	
	ModelMapper modelMapper = new ModelMapper();
	
	public List<CampanhaResponse> consultarVigenciasNaoVencidas() {
		
		
		List<CampanhaResponse> campanhasResponse = new ArrayList<>();
	
		String diaAtual;
		
		c.setTime(new Date());
		int dia = c.get(Calendar.DAY_OF_MONTH);
		int mes = c.get(Calendar.MONTH);
		int ano = c.get(Calendar.YEAR);
		
		diaAtual = ano+"/"+mes+"/"+dia;				

		System.out.print(c.getTime());
		
		for(CampanhaEntity camp : campanhaRep.listByVigenciaNaoVencida(diaAtual)) {
			
			campanhasResponse.add(modelMapper.map(camp, CampanhaResponse.class));
				
		}
		
		return campanhasResponse;
	}

	public CampanhaResponse inserirCampanha(CampanhaResponse campanhaResponse) {
		
		CampanhaEntity campanha;
		
		campanha  = modelMapper.map(campanhaResponse, CampanhaEntity.class);
			
		campanhaRep.save(campanha);
		
		
		return campanhaResponse;
	}
	
	public CampanhaResponse adicionarDiaCampanhaSalvar(CampanhaResponse campanhaResponse) {
		
		Calendar c = Calendar.getInstance(); 
		boolean dataIgual = true;

		campanhaDataAtualiza = campanhaRep.findOneByVigenciaFinal(campanhaResponse.getVigenciaFinal());
		
		c.setTime(campanhaResponse.getVigenciaFinal());
		c.add(Calendar.DATE, 1);
		
		campanhaDataAtualiza.setVigenciaFinal(c.getTime());
		campanhaRep.saveAndFlush(campanhaDataAtualiza);
	
		do {
		
		if(campanhaRep.findUnique() != null) {
			
			campanhaDataAtualiza = campanhaRep.findOneByVigenciaFinal(campanhaDataAtualiza.getVigenciaFinal());
			
			c.setTime(campanhaDataAtualiza.getVigenciaFinal());
			c.add(Calendar.DATE, 1);
			
			campanhaDataAtualiza.setVigenciaFinal(c.getTime());
			
			campanhaRep.save(campanhaDataAtualiza);
			campanhaRep.flush();
			
			if(campanhaRep.findUnique() == null) {
				dataIgual = false;
			}
		}
		
		else {
			
			campanhaRep.save(modelMapper.map(campanhaResponse, CampanhaEntity.class));
			campanhaRep.flush();
			dataIgual = false;
		}
		
		
		}while(dataIgual);
		
			return campanhaResponse;
	}

	public CampanhaResponse atualizarCampanha(CampanhaResponse campanhaResponse) {
		
		Calendar c = Calendar.getInstance(); 
		boolean dataIgual = true;
		
		campanhaRep.saveAndFlush(modelMapper.map(campanhaResponse, CampanhaEntity.class));
	
		do {
		
		if(campanhaRep.findUnique() != null) {
			
			campanhaDataAtualiza = campanhaRep.buscarUltimaCampanha(campanhaResponse.getVigenciaFinal());
			
			c.setTime(campanhaDataAtualiza.getVigenciaFinal());
			c.add(Calendar.DATE, 1);
			
			campanhaDataAtualiza.setVigenciaFinal(c.getTime());
			
			campanhaRep.save(campanhaDataAtualiza);
			campanhaRep.flush();
			
			if(campanhaRep.findUnique() == null) {
				dataIgual = false;
			}
		}
		
		else {
			dataIgual = false;
		}
		
		
		}while(dataIgual);
		
		return campanhaResponse;
	}

	public Boolean verificarVigenciaFinalIgual(Date dataVigencia) {
		
		campanhas = campanhaRep.findAllByVigenciaFinal(dataVigencia);
		if(campanhas.isEmpty()) {
			
			return true;
		}
		else {
			return false;
		}
		
	}
	
	public String deletarCampanhaById(int id_campanha) {
		
		campanhaRep.deleteById(id_campanha);
		
		return "Campanha Deletada";
	}
	
}
