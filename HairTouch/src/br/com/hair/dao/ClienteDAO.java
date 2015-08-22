package br.com.hair.dao;

import java.util.ArrayList;
import java.util.List;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import br.com.hair.vo.ClienteVO;

public class ClienteDAO extends SQLiteOpenHelper {

	private static final String DATABASE = "CadastroCliente";
	private static final int VERSAO = 1;
	private static final String TABELA = "Cliente";
	ContentValues cv;
	
	public ClienteDAO(Context ctx) {
		super(ctx, DATABASE, null, VERSAO);
	}

	@Override
	public void onCreate(SQLiteDatabase dataBase) {
		
		String sql = "CREATE TABLE " + TABELA + " (id INTEGER PRIMARY KEY, " +
                " nome TEXT UNIQUE NOT NULL, telefone TEXT, email TEXT, senha TEXT, foto TEXT);"; 

		dataBase.execSQL(sql);
		
	}

	@Override
	public void onUpgrade(SQLiteDatabase database, int oldVersion, int newVersion) {

		String sql = "DROP TABLE IF EXISTS " + TABELA;
		database.execSQL(sql);
		onCreate(database);
		
	}
	
	public void adiciona (ClienteVO cliente){
		cv = new ContentValues();
		cv.put("nome", cliente.getNome());
		cv.put("telefone", cliente.getTelefone());
		cv.put("email", cliente.getEmail());
		cv.put("senha", cliente.getSenha());
		cv.put("foto", cliente.getFoto());
		
		getWritableDatabase().insert(TABELA, null, cv);
	}
	
	
	public void deletar(ClienteVO cliente) {
		
		String[] args = {cliente.getId().toString()};
		getWritableDatabase().delete("Cliente", "id = ?", args);
		
	}
	
	public void alterar(ClienteVO cliente) {

		cv = new ContentValues();
		cv.put("nome", cliente.getNome());
		cv.put("telefone", cliente.getTelefone());
		cv.put("email", cliente.getEmail());
		cv.put("senha", cliente.getSenha());
		cv.put("foto", cliente.getFoto());

		String[] args = {cliente.getId().toString()};
		getWritableDatabase().update("Cliente", cv, "id=?", args);
		
	}
	
	public List<ClienteVO> buscar() {
		
		List<ClienteVO> clientes = new ArrayList<ClienteVO>();
		
		String sql = "SELECT * FROM " + TABELA + ";";
		Cursor c = getReadableDatabase().rawQuery(sql, null);
		
		while (c.moveToNext()) {
			
			ClienteVO cliente = new ClienteVO();
			
			cliente.setId(c.getLong(c.getColumnIndex("id")));
			cliente.setNome(c.getString(c.getColumnIndex("nome")));
			cliente.setTelefone(c.getString(c.getColumnIndex("telefone")));
			cliente.setEmail(c.getString(c.getColumnIndex("email")));
			cliente.setSenha(c.getString(c.getColumnIndex("senha")));
			cliente.setFoto(c.getString(c.getColumnIndex("foto")));
			
			clientes.add(cliente);
		}
		
		return clientes;
	}

}
