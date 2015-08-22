package br.com.hair.activity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;
import br.com.hair.dao.ClienteDAO;
import br.com.hair.vo.ClienteVO;

public class CadastroCliente extends Activity implements OnClickListener{
	
	private EditText campoEmail, campoSenha, campoNome, campoTelefone;
	private ClienteVO cliente = new ClienteVO();
	private Button btncadastrar;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.cadastrocliente);
		
		campoNome = (EditText) findViewById(R.id.cadNome);
		campoTelefone = (EditText) findViewById(R.id.cadTelefone);
		campoEmail = (EditText) findViewById(R.id.cadEmail);
		campoSenha = (EditText) findViewById(R.id.cadSenha);
		btncadastrar = (Button) findViewById(R.id.btnCadastrar);
		
		btncadastrar.setOnClickListener(this);
		
		Intent intent = getIntent();
		if (intent != null) {
			Bundle bundle = intent.getExtras();
			if (bundle != null) {
				
				cliente.setId(bundle.getLong("id"));
				cliente.setNome(bundle.getString("nome"));
				cliente.setTelefone(bundle.getString("telefone"));
				cliente.setEmail(bundle.getString("email"));
				cliente.setSenha(bundle.getString("senha"));
				
				campoNome.setText(cliente.getNome());
				campoTelefone.setText(cliente.getTelefone());
				campoEmail.setText(cliente.getEmail());
				campoSenha.setVisibility(View.GONE);
				btncadastrar.setVisibility(View.VISIBLE);
			}
		}
		
		
	}

	@Override
	public void onClick(View v) {
	

		switch (v.getId()) {
		case R.id.btnCadastrar:
			
			ClienteDAO dao = new ClienteDAO(CadastroCliente.this);
			
			cliente.setNome(campoNome.getText().toString());
			cliente.setTelefone(campoTelefone.getText().toString());
			cliente.setEmail(campoEmail.getText().toString());
			cliente.setSenha(campoSenha.getText().toString());
			
			if (cliente.getId() == null) {
				
				cliente.setId(cliente.getId());
				dao.adiciona(cliente);
				dao.close();
				finish();
				
				Toast.makeText(this, "Dados Gravados Com Sucesso", Toast.LENGTH_SHORT).show();
				break;
				
				}else {
					
					dao.alterar(cliente);
					
					Toast.makeText(this, "Usuário já existente", Toast.LENGTH_SHORT).show();
				}
		}					

		
	}
	
	
}
