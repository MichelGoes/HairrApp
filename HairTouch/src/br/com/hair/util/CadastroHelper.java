package br.com.hair.util;

import br.com.hair.activity.CadastroCliente;
import br.com.hair.activity.R;
import br.com.hair.vo.ClienteVO;
import android.widget.EditText;

public class CadastroHelper {

	private EditText campoEmail, campoSenha, campoNome, campoTelefone;
	private ClienteVO cliente;
	
	public CadastroHelper(CadastroCliente activity){
		
		cliente = new ClienteVO();
		campoNome = (EditText) activity.findViewById(R.id.cadNome);
		campoTelefone = (EditText) activity.findViewById(R.id.cadTelefone);
		campoEmail = (EditText) activity.findViewById(R.id.cadEmail);
		campoSenha = (EditText) activity.findViewById(R.id.cadSenha);
	}
	
	public ClienteVO pegaCliente(ClienteVO pegaCliente){
		
		String nome = campoNome.getText().toString();
		String telefone = campoTelefone.getText().toString();
		String email = campoEmail.getText().toString();
		String senha = campoSenha.getText().toString();
		
		cliente.setNome(nome);
		cliente.setTelefone(telefone);
		cliente.setEmail(email);
		cliente.setSenha(senha);
		
		return pegaCliente;
		
	}
	
	public ClienteVO guardaCliente(ClienteVO novoCliente){
		
		cliente = novoCliente;
		campoNome.setText(novoCliente.getNome());
		campoTelefone.setText(novoCliente.getTelefone());
		campoEmail.setText(novoCliente.getEmail());
		campoSenha.setText(novoCliente.getSenha());
		
		return novoCliente;
		
	}
}
