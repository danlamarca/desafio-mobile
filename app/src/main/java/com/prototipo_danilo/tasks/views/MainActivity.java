package com.prototipo_danilo.tasks.views;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.widget.TextView;

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

public class MainActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {

     //views:
    private RecyclerView recyclerView;
    private ItemAdapter itemAdapter;
    private TextView textqtderegistros;
    //variaveis globais:
    private List<ProductsT> productsTList = new ArrayList<>();
    private List<CardEntity> listcards = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //inicializando os elementos:
        recyclerView = (RecyclerView)findViewById(R.id.recyclerView);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        textqtderegistros = (TextView) findViewById(R.id.textqtderegistros);

        //preenchendo a lista
        listcards = OperationCards.CarregaCardsByProd(this);
        if(listcards.size()>0)
            CarregaCards(listcards);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        //DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        //ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        //drawer.setDrawerListener(toggle);
        //toggle.syncState();

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
        itemAdapter = new ItemAdapter(listcards);
        recyclerView.setAdapter(itemAdapter);
        textqtderegistros.setText(String.valueOf(listcards.size()));
        itemAdapter.notifyDataSetChanged();
    }

}
