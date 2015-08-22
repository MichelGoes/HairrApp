package br.com.hair.activity;

import br.com.hair.vo.ClienteVO;
import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class LoginActivity extends Activity implements OnClickListener{
	
	Button btnLogin;
	EditText email, senha;
	TextView cadastroLink;
	ClienteVO cliente = new ClienteVO();
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.login);

		email = (EditText) findViewById(R.id.email);
		senha = (EditText) findViewById(R.id.senha);
		btnLogin = (Button)   findViewById(R.id.btnlogin); 
		cadastroLink = (TextView) findViewById(R.id.cadastroLink);
		
		btnLogin.setOnClickListener(this);
		cadastroLink.setOnClickListener(this);
		
		Intent intent = getIntent();
		if (intent != null) {
			Bundle bundle = intent.getExtras();
			if (bundle != null) {
				
				cliente.setId(bundle.getLong("id"));
				cliente.setEmail(bundle.getString("email"));
				cliente.setSenha(bundle.getString("senha"));
				
				email.setText(cliente.getEmail());
				senha.setVisibility(View.GONE);
				btnLogin.setVisibility(View.VISIBLE);
			}
		}
		
	}
	
	@Override
	public void onClick(View v) {
		
		switch (v.getId()) {
		case R.id.btnlogin:
			
			cliente.setEmail(email.getText().toString());
			cliente.setSenha(senha.getText().toString());
			
			if (cliente.getId() != null && !cliente.getEmail().isEmpty() && !cliente.getSenha().isEmpty()) {
				
				Intent inicio = new Intent(this, HomeActivity.class);
				startActivity(inicio);
				
			}
			
			else {
				
				Toast.makeText(this, "Usuário ou senha inválido", Toast.LENGTH_SHORT).show();
			}
			
			break;
		
		case R.id.cadastroLink:
			
			Intent irParaCadastro = new Intent(this, CadastroCliente.class);
			startActivity(irParaCadastro);

		default:
			break;
		}
		
	}
	

}
