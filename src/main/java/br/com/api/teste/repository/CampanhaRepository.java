package br.com.api.teste.repository;

import java.util.Date;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import br.com.api.teste.entity.CampanhaEntity;

@Repository
public interface CampanhaRepository extends JpaRepository<CampanhaEntity, Integer> {

	@Query(value = "select * from campanha where vigencia_final > :data ;",nativeQuery = true)	
	List<CampanhaEntity> listByVigenciaNaoVencida(@Param (value = "data") String data);
	
	List<CampanhaEntity> findAllByVigenciaFinal(Date vigencia_final);
	
	@Query(value = "SELECT * \n" + 
			"FROM campanha \n" + 
			"WHERE vigencia_final = :vigencia_final\n" + 
			"ORDER BY id_campanha ASC LIMIT 1;", nativeQuery = true)
	CampanhaEntity findOneByVigenciaFinal(@Param(value = "vigencia_final") Date vigencia_final);
	
	@Query(value = "SELECT * \n" + 
			"FROM campanha \n" + 
			"WHERE vigencia_final = :vigencia_final\n" + 
			"ORDER BY id_campanha DESC LIMIT 1;", nativeQuery = true)
	CampanhaEntity buscarUltimaCampanha(@Param(value = "vigencia_final") Date vigencia_final);
	
	@Query(value = "SELECT COUNT(*) vigencia_final\n" + 
			"FROM campanha\n" + 
			"GROUP BY vigencia_final\n" + 
			"HAVING COUNT(*) > 1;", nativeQuery = true)
	String findUnique();
	
	@Query(value = "SELECT *\n" + 
			"FROM campanha\n" + 
			"WHERE vigencia_inicial >= CAST(:dataInicial AS DATE)\n" + 
			"AND vigencia_final <= CAST(:dataFinal AS DATE);",nativeQuery = true)	
	List<CampanhaEntity> listVigenciasAtuais(@Param (value = "dataInicial") String dataInicial,
			@Param(value ="dataFinal") String dataFinal);
	
	@Query(value = "select * from campanha where vigencia_final = :data ;",nativeQuery = true)	
	List<CampanhaEntity> listarVigenciasFinalIgual(@Param (value = "data") String data);
	
	@Query(value = "update campanha set vigencia_final = (\n" + 
			"SELECT DATE_ADD(DATE(vigencia_inicial), INTERVAL 1 DAY)) where id_campanha = :id_campanha;", nativeQuery = true)
	List<CampanhaEntity> atualizarUltimoDiaVigencia(@Param(value = "id_campanha") int id_campanha);
}
