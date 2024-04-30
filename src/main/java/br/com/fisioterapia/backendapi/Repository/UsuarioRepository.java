package br.com.fisioterapia.backendapi.Repository;

import br.com.fisioterapia.backendapi.Models.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UsuarioRepository extends JpaRepository<Usuario, Long> {
}

