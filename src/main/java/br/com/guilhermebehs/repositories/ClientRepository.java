package br.com.guilhermebehs.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import br.com.guilhermebehs.data.models.ClientModel;

@Repository
public interface ClientRepository extends JpaRepository<ClientModel, Long>{

	@Modifying
	@Query("UPDATE ClientModel SET enabled = FALSE WHERE id = :id")
	void disableClient(@Param("id") Long id);
}
