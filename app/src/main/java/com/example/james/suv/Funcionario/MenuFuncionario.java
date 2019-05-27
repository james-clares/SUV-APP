package com.example.james.suv.Funcionario;

import android.app.Fragment;
import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import com.example.james.suv.AcessoBD.Funcionario;
import com.example.james.suv.Funcionario.Vacinas.UltimasVacinasFunc;
import com.example.james.suv.LeitorQRCode;
import com.example.james.suv.R;
import com.example.james.suv.adapter.SlidingMenuAdapter;
import com.example.james.suv.model.ItemSlideMenu;

import java.util.ArrayList;
import java.util.List;

public class MenuFuncionario extends AppCompatActivity {

    private List<ItemSlideMenu> listSliding;
    private SlidingMenuAdapter adapter;
    private ListView listViewSliding;
    private DrawerLayout drawerLayout;
    private ActionBarDrawerToggle actionBarDrawerToggle;

    Funcionario func=new Funcionario();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu_funcionario);

        listViewSliding = (ListView) findViewById(R.id.lv_sliding_menu_func);
        drawerLayout = (DrawerLayout) findViewById(R.id.drawer_layout_func);
        listSliding = new ArrayList<>();
        //Add item for sliding list
        listSliding.add(new ItemSlideMenu(R.drawable.icon, "Bem- vindo "+func.getNome()));
        listSliding.add(new ItemSlideMenu(R.drawable.icon_historico, "Últimas Vacinas"));
        listSliding.add(new ItemSlideMenu(R.drawable.icon_editar, "Dados do Funcionário"));
        listSliding.add(new ItemSlideMenu(R.drawable.icon_conf, "Alterar Senha"));      ;
        listSliding.add(new ItemSlideMenu(R.drawable.icon_conf, "Ler QR Code"));
        listSliding.add(new ItemSlideMenu(R.drawable.icon_vacina, "Consultar Vacina"));
        listSliding.add(new ItemSlideMenu(R.drawable.icon_sair, "Sair"));
        adapter = new SlidingMenuAdapter(this, listSliding);
        listViewSliding.setAdapter(adapter);

        //Display icon to open/ close sliding list
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        //Set title
        setTitle(listSliding.get(0).getTitle());
        //item selected
        listViewSliding.setItemChecked(0, true);
        //Close menu
        drawerLayout.closeDrawer(listViewSliding);

        //Display fragment 1 when start
        replaceFragment(0);
        //Hanlde on item click

        listViewSliding.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                //Set title
                setTitle(listSliding.get(position).getTitle());
                //item selected
                listViewSliding.setItemChecked(position, true);
                //Replace fragment
                replaceFragment(position);
                //Close menu
                drawerLayout.closeDrawer(listViewSliding);
            }
        });

        actionBarDrawerToggle = new ActionBarDrawerToggle(this, drawerLayout, R.string.drawer_opened, R.string.drawer_closed){
            @Override
            public void onDrawerOpened(View drawerView) {
                super.onDrawerOpened(drawerView);
                invalidateOptionsMenu();
            }

            @Override
            public void onDrawerClosed(View drawerView) {
                super.onDrawerClosed(drawerView);
                invalidateOptionsMenu();
            }
        };
        drawerLayout.setDrawerListener(actionBarDrawerToggle);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.main_menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if(actionBarDrawerToggle.onOptionsItemSelected(item)) {
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    protected void onPostCreate(Bundle savedInstanceState) {
        super.onPostCreate(savedInstanceState);
        actionBarDrawerToggle.syncState();
    }
    //Create method replace fragment

    private void replaceFragment(int pos) {
        Fragment fragment = null;
        switch (pos) {
            case 0:
                break;
            case 1:
                //fragment = new UltimasVacinasFunc();
                break;
            case 2:
                fragment = new DadosFuncionario();
                break;
            case 3:
                fragment = new AlterarSenhaFuncionario();
                break;
            case 4:
                Intent m = new Intent(this, LeitorQRCode.class).setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                startActivity(m);
                break;
            case 5:
                fragment = new BuscaCliente();
                break;

            case 6:
                sair();
                break;

            default:
                fragment = new UltimasVacinasFunc();
                break;
        }

        if(null!=fragment) {
            FragmentManager fragmentManager = getFragmentManager();
            FragmentTransaction transaction = fragmentManager.beginTransaction();
            transaction.replace(R.id.main_content, fragment);
            transaction.addToBackStack(null);
            transaction.commit();
        }
    }

    private void sair() {
        AlertDialog.Builder alerta=new AlertDialog.Builder(MenuFuncionario.this);
        alerta.setMessage("Deseja realmente sair?")
                .setCancelable(false)
                .setNegativeButton("Cancelar", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {

                    }
                })
                .setPositiveButton("Sair", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        Intent m = new Intent(MenuFuncionario.this, LoginFuncionario.class).setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                        startActivity(m);
                    }
                });
        AlertDialog alertDialog=alerta.create();
        alertDialog.show();
    }
}