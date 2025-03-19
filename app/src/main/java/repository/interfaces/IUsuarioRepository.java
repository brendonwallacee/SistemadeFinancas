package repository.interfaces;

import model.Usuario;

public interface IUsuarioRepository {
    void salvar(Usuario usuario);
    Usuario getUsuarioPorId(int id);

}
