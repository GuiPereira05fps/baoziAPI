package br.com.baozistore.bscontrol.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


import br.com.baozistore.bscontrol.models.Cliente;

@Repository
public interface ClienteRepository extends JpaRepository<Cliente, Long>{

}