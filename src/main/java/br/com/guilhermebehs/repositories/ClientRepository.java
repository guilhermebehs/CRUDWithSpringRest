package br.com.guilhermebehs.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.com.guilhermebehs.data.models.ClientModel;

@Repository
public interface ClientRepository extends JpaRepository<ClientModel, Long>{

}
