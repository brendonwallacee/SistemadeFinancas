package repository.implementacao;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;

import dao.DaoUtil;
import model.Usuario;
import repository.interfaces.IUsuarioRepository;

public class UsuarioRepository implements IUsuarioRepository {
    private DaoUtil daoUtil;

    public UsuarioRepository(Context context) {
        this.daoUtil = new DaoUtil(context);
    }

    private ContentValues carregarValores(Usuario usuario){
        ContentValues values = new ContentValues();
        values.put("email", usuario.getEmail());
        values.put("nome", usuario.getNome());
        values.put("senha", usuario.getSenha());
        return values;
    }

    private Usuario carregaUsuario(Cursor cursor){
        Usuario usuario = new Usuario();

        int idIndex = cursor.getColumnIndex("id");
        if(idIndex >=0) usuario.setId(cursor.getInt(idIndex));

        int emailIndex = cursor.getColumnIndex("email");
        if(emailIndex>=0) usuario.setEmail(cursor.getString(emailIndex));

        int nomeIndex =  cursor.getColumnIndex("nome");
        if(nomeIndex >=0) usuario.setNome(cursor.getString(nomeIndex));

        int descIndex =  cursor.getColumnIndex("descricao");
        if(descIndex >=0) usuario.setSenha(cursor.getString(descIndex));
        return usuario;
    }

    @Override
    public void salvar(Usuario usuario) {
        daoUtil.getConexaoDataBase().insert("usuario",
                null,
                carregarValores(usuario));

    }

    @Override
    public Usuario getUsuarioPorId(int id) {
        Usuario usuario = new Usuario();
        String sql = "SELECT id, email, nome, senha FROM usuario WHERE id = " + id;

        Cursor cursor = daoUtil.getConexaoDataBase().rawQuery(sql, null);
        cursor.moveToFirst();
        usuario = carregaUsuario(cursor);
        cursor.close();
        return usuario;
    }
}
