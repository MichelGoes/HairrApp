package br.com.hair.activity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.TextView;

public class HomeActivity extends Activity implements OnClickListener{

	TextView login, mapa, saloes;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.home);
		
		login = (TextView) findViewById(R.id.linkLogin);
		mapa = (TextView) findViewById(R.id.mapa);
		saloes = (TextView) findViewById(R.id.listarSaloes);
		
		
		login.setOnClickListener(this);
		mapa.setOnClickListener(this);
		saloes.setOnClickListener(this);
	}

	@Override
	public void onClick(View v) {
		
		switch (v.getId()) {
		case R.id.linkLogin:
			
			Intent login = new Intent(this, LoginActivity.class);
			startActivity(login);
			break;

			
		default:
			break;
		}

		
	}
}
