package com.example.appcrud;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

public class CadastroPessoaActivity extends AppCompatActivity {

    private EditText nome;
    private EditText cpf;
    private EditText telefone;
    private PessoaDAO dao;
    private Pessoa pessoa = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        nome = findViewById(R.id.editNome);
        cpf = findViewById(R.id.editCPF);
        telefone = findViewById(R.id.editTelefone);
        dao = new PessoaDAO(this);

        Intent it = getIntent();
        if(it.hasExtra("pessoa")){
            pessoa = (Pessoa) it.getSerializableExtra("pessoa");
            nome.setText(pessoa.getNome());
            cpf.setText(pessoa.getCpf());
            telefone.setText(pessoa.getTelefone());
        }
    }

    public void salvar(View view){

        if (pessoa == null) {
            pessoa = new Pessoa();
            pessoa.setNome(nome.getText().toString());
            pessoa.setCpf(cpf.getText().toString());
            pessoa.setTelefone(telefone.getText().toString());
            long id = dao.inserir(pessoa);
            Toast.makeText(this, "Pessoa cadastrada com o ID: " + id, Toast.LENGTH_SHORT).show();
        }else{
            pessoa.setNome(nome.getText().toString());
            pessoa.setCpf(cpf.getText().toString());
            pessoa.setTelefone(telefone.getText().toString());
            dao.editar(pessoa);
            Toast.makeText(this, "O registro foi atualizado", Toast.LENGTH_SHORT).show();
        }
    }

}