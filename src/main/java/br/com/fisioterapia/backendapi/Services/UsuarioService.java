package br.com.fisioterapia.backendapi.Services;

import br.com.fisioterapia.backendapi.Models.Usuario;
import br.com.fisioterapia.backendapi.Repository.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.List;

@Service
public class UsuarioService {

    @Autowired
    private UsuarioRepository usuarioRepository;

    public List<Usuario> listarUsuarios() {
        return usuarioRepository.findAll();
    }

    public Usuario buscarUsuarioPorId(Long id) {
        return usuarioRepository.findById(id).orElse(null);
    }

    public Usuario cadastrarUsuario(Usuario usuario) {
        usuario.senha = _converterParaMD5(usuario.senha);
        return usuarioRepository.save(usuario);
    }

    public Usuario atualizarUsuario(Long id, Usuario usuario) {
        if (usuarioRepository.existsById(id)) {
            usuario.id = id;
            return usuarioRepository.save(usuario);
        } else {
            return null;
        }
    }

    public boolean excluirUsuario(Long id) {
        if (usuarioRepository.existsById(id)) {
            usuarioRepository.deleteById(id);
            return true;
        } else {
            return false;
        }
    }

    private String _converterParaMD5(String input) {
        try {
            // Obtém uma instância do MessageDigest para MD5
            MessageDigest md = MessageDigest.getInstance("MD5");

            // Converte a string de entrada para bytes e atualiza o MessageDigest
            md.update(input.getBytes());

            // Calcula o hash MD5
            byte[] digest = md.digest();

            // Converte o hash de bytes para uma representação hexadecimal
            StringBuilder hexString = new StringBuilder();
            for (byte b : digest) {
                hexString.append(String.format("%02x", b));
            }

            return hexString.toString();
        } catch (NoSuchAlgorithmException e) {
            // Trata a exceção caso o algoritmo MD5 não seja suportado
            e.printStackTrace();
            return null;
        }
    }
}
