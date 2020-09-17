package br.com.api.teste.entity;



import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.Data;
import lombok.Getter;

@Data
@Entity
@Table(name = "campanha")
public class CampanhaEntity{
	
	/**
	 * 
	 */
	@Column(name = "id_campanha")
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id_campanha;
	
	@Column(name = "nomeCampanha")
	private String nomeCampanha;
	
	@Column(name ="idTime")
	private int idtime;
	
	@Column(name= "vigenciaInicial")
	private Date vigenciaInicial;
	
	@Column(name= "vigenciaFinal")
	private Date vigenciaFinal;
}
