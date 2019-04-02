package com.prototipo_danilo.tasks.views;

import android.os.Build;
import android.os.Bundle;
import android.support.annotation.RequiresApi;
import android.support.design.widget.TextInputLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.AppCompatEditText;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.prototipo_danilo.tasks.R;
import com.prototipo_danilo.tasks.adapter.ItemAdapter;
import com.prototipo_danilo.tasks.entities.BestInstallment;
import com.prototipo_danilo.tasks.entities.CardEntity;
import com.prototipo_danilo.tasks.entities.ImageT;
import com.prototipo_danilo.tasks.entities.ProductsT;
import com.prototipo_danilo.tasks.entities.SellersT;
import com.prototipo_danilo.tasks.infra.SecurityPreferences;
import com.prototipo_danilo.tasks.infra.operation.OperationCards;
import com.prototipo_danilo.tasks.repository.local.BestInstallmentRepository;
import com.prototipo_danilo.tasks.repository.local.ImageRepository;
import com.prototipo_danilo.tasks.repository.local.ProdutoRepository;
import com.prototipo_danilo.tasks.repository.local.SellerRepository;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collector;
import java.util.stream.Collectors;

public class MainActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {

     //views:
    private RecyclerView recyclerView;
    private ItemAdapter itemAdapter;
    private TextView textqtderegistros;
    private ImageView imgpesq;
    private AppCompatEditText textpesq;
    private TextView textresultadopesq;
    private ImageView imgretornapesq;
    //private TextInputLayout txtNome;
    //variaveis globais:
    private List<ProductsT> productsTList = new ArrayList<>();
    private List<CardEntity> listcards = new ArrayList<>();

    @RequiresApi(api = Build.VERSION_CODES.N)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //inicializando os elementos:
        recyclerView = (RecyclerView)findViewById(R.id.recyclerView);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        textqtderegistros = (TextView) findViewById(R.id.textqtderegistros);
        imgpesq = (ImageView) findViewById(R.id.imgpesq);
        textpesq = (AppCompatEditText) findViewById(R.id.textpesq);
        textresultadopesq = (TextView)findViewById(R.id.textresultadopesq);
        imgretornapesq = (ImageView) findViewById(R.id.imgretornapesq);

        imgpesq.setFocusable(true);
        textpesq.setFocusable(false);
        textpesq.setFocusableInTouchMode(true);

        //preenchendo a lista
        listcards = OperationCards.CarregaCardsByProd(this);
        if(listcards.size()>0)
            CarregaCards(listcards);


        imgpesq.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                List<CardEntity> cards = new ArrayList<>();
                cards = CardEntity.FiltraCard(OperationCards.CarregaCardsByProd(getApplicationContext()),
                        "Nome",textpesq.getText().toString());
                CarregaCards(cards);
                textresultadopesq.setText(textpesq.getText().toString());
                textpesq.setText("");
                imgpesq.setFocusable(true);
                textpesq.setFocusableInTouchMode(false);
            }
        });

        imgretornapesq.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(listcards.size()>0) {
                    CarregaCards(listcards);
                    textresultadopesq.setText(" ");
                }
            }
        });

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);


        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);
    }

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {

        return true;
    }

    private void CarregaCards(List<CardEntity> cards)
    {
        if(cards.size()>0) {
            itemAdapter = new ItemAdapter(cards);
            recyclerView.setAdapter(itemAdapter);
            textqtderegistros.setText(String.valueOf(cards.size()));
            itemAdapter.notifyDataSetChanged();
        }else{

            List<CardEntity> cardzero = new ArrayList<>();
            itemAdapter = new ItemAdapter(cardzero);
            recyclerView.setAdapter(itemAdapter);
            textqtderegistros.setText("0");
            itemAdapter.notifyDataSetChanged();

            Toast.makeText(this,"Pesquisa não retornou resultados",Toast.LENGTH_LONG).show();
        }
    }

}
