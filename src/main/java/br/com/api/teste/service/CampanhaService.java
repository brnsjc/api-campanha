package br.com.api.teste.service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.api.teste.business.CampanhaBusiness;
import br.com.api.teste.response.CampanhaResponse;

@Service
public class CampanhaService {

	@Autowired
	CampanhaBusiness campanhaBusiness;
	
	
	CampanhaResponse campanhaResp = new CampanhaResponse();
	List<CampanhaResponse> responsesCampanha = new ArrayList<>();
	
	public List<CampanhaResponse> consultarCampanha() {
		
		return campanhaBusiness.consultarVigenciasNaoVencidas();
	}
	
	public CampanhaResponse inserirCampanha(CampanhaResponse campanhaResponse) {
		
		if(campanhaBusiness.verificarVigenciaFinalIgual(campanhaResponse.getVigenciaFinal()) == true){
			
			return campanhaBusiness.inserirCampanha(campanhaResponse);
		}
		else {
			
			campanhaBusiness.adicionarDiaCampanhaSalvar(campanhaResponse);		
		}
		return campanhaResponse;
	}
	
	public CampanhaResponse atualizarCampanha(CampanhaResponse campanhaResponse) {
		
		if(campanhaBusiness.verificarVigenciaFinalIgual(campanhaResponse.getVigenciaFinal()) == true){
			
			return campanhaBusiness.inserirCampanha(campanhaResponse);
		}
		else {
			
			campanhaBusiness.atualizarCampanha(campanhaResponse);		
		}
		
		return campanhaResponse;
	}

	public void deletarCampanhaById(int id_campanha) {
	
		
		campanhaBusiness.deletarCampanhaById(id_campanha);
		
	}
}
