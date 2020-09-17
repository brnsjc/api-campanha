package br.com.api.teste.response;

import java.io.Serializable;
import java.util.Date;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CampanhaResponse implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 2730037385590409549L;
	
	
	private int id_campanha;
	
	@JsonProperty(value = "Nome Campanha")
	private String nomeCampanha;
	
	@JsonProperty(value = "ID do Time do Coração")
	private int idTime;
	
	@JsonProperty(value = "Data de Vigência Inicial")
	private Date vigenciaInicial;
	
	@JsonProperty(value = "Data de Vigência Final")
	private Date vigenciaFinal;
}
