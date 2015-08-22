package br.com.hair.dao;

import java.util.ArrayList;
import java.util.List;

import br.com.hair.vo.EmpresaVO;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class EmpresaDAO extends SQLiteOpenHelper {
	
	private static final String DATABASE = "CadastroEmpresa";
	private static final int VERSAO = 1;
	private static final String TABELA = "Empresa";
	ContentValues cv;

	public EmpresaDAO(Context ctx) {
		super(ctx, DATABASE, null, VERSAO);
		
	}

	@Override
	public void onCreate(SQLiteDatabase dataBase) {

		String sql = "CREATE TABLE " + TABELA + " (id INTEGER PRIMARY KEY, " +
                " nome TEXT UNIQUE NOT NULL, telefone TEXT, endereco TEXT, numero NUMBER " +
                " site TEXT, foto TEXT);";

		dataBase.execSQL(sql);
		
	}

	@Override
	public void onUpgrade(SQLiteDatabase dataBase, int oldVersion, int newVersion) {

		String sql = "DROP TABLE IF EXISTS " + TABELA;
		dataBase.execSQL(sql);
		onCreate(dataBase);
		
	}
	
	public void insere(EmpresaVO empresa) {
		
		cv= new ContentValues();
		cv.put("nome", empresa.getNome());
		cv.put("site", empresa.getSite());
		cv.put("endereco", empresa.getEndereco());
		cv.put("numero", empresa.getNumero());
		cv.put("telefone", empresa.getTelefone());
		cv.put("foto", empresa.getFoto());

		getWritableDatabase().insert(TABELA, null, cv);
	}

	
	public List<EmpresaVO> getLista() {
		
		List<EmpresaVO> empresas = new ArrayList<EmpresaVO>();
		
		String sql = "SELECT * FROM " + TABELA + ";";
		Cursor c = getReadableDatabase().rawQuery(sql, null);
		
		while (c.moveToNext()) {
			
			EmpresaVO empresa = new EmpresaVO();
			
			empresa.setId(c.getLong(c.getColumnIndex("id")));
			empresa.setNome(c.getString(c.getColumnIndex("nome")));
			empresa.setSite(c.getString(c.getColumnIndex("site")));
			empresa.setEndereco(c.getString(c.getColumnIndex("endereco")));
			empresa.setNumero(c.getInt(c.getColumnIndex("numero")));
			empresa.setTelefone(c.getString(c.getColumnIndex("telefone")));
			empresa.setFoto(c.getString(c.getColumnIndex("foto")));
			
			empresas.add(empresa);
		}
		
		return empresas;
	}
	
	public void deletar(EmpresaVO empresa) {
		
		String[] args = {empresa.getId().toString()};
		getWritableDatabase().delete("Empresa", "id = ?", args);
		
	}
	
	public void alterar(EmpresaVO empresa) {

		cv =new ContentValues();
		cv.put("nome", empresa.getNome());
		cv.put("site", empresa.getSite());
		cv.put("endereco", empresa.getEndereco());
		cv.put("telefone", empresa.getTelefone());
		cv.put("numero", empresa.getNumero());
		cv.put("foto", empresa.getFoto());

		String[] args = {empresa.getId().toString()};
		getWritableDatabase().update("Empresa", cv, "id=?", args);
		
	}
	

}
