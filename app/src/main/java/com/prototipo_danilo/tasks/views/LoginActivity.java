package com.prototipo_danilo.tasks.views;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ProgressBar;

import com.prototipo_danilo.tasks.R;
import com.prototipo_danilo.tasks.infra.SecurityPreferences;
import com.prototipo_danilo.tasks.infra.operation.OperationListener;
import com.prototipo_danilo.tasks.manager.PersonManager;

public class LoginActivity extends AppCompatActivity implements View.OnClickListener {

    private ViewHolder mViewHolder = new ViewHolder();
    private PersonManager mPersonManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        // Inicializa elementos
        this.mViewHolder.buttonLogin = (Button) this.findViewById(R.id.button_login);
        this.mViewHolder.progressBarActvitys = (ProgressBar)this.findViewById(R.id.prb_aguarde);
        this.mViewHolder.progressBarActvitys.setVisibility(View.GONE);
        this.mPersonManager = new PersonManager(this);

        // Inicializa eventos
        this.mViewHolder.buttonLogin.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        int id = view.getId();
        if (id == R.id.button_login) {
            this.mViewHolder.buttonLogin.setEnabled(false);
            this.mViewHolder.progressBarActvitys.setVisibility(View.VISIBLE);
            SecurityPreferences securityPreferences = new SecurityPreferences(this);

            if(securityPreferences.getStoredString("executouapi").contains("S"))
                CarregaIntent();
            else{
                ExecutaApi();
            }
        }
    }

    /**
     * ViewHolder
     */
    private static class ViewHolder {
        private Button buttonLogin;
        private ProgressBar progressBarActvitys;
    }

    private void CarregaIntent(){
        startActivity(new Intent(this, MainActivity.class));
    }

    private void ExecutaApi()  {
        this.mPersonManager.Create(registerListener());//criado o listener para receber o retorno
    }

    private OperationListener registerListener(){
        return new OperationListener<Boolean>(){
            @Override
            public void OnSucess(Boolean result){
                CarregaIntent();
            }

            @Override
            public void OnError(int errorCode, String errorMessage){

            }
        };
    }



}
