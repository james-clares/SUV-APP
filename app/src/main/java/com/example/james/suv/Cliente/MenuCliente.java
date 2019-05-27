package com.example.james.suv.Cliente;


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

import com.example.james.suv.AcessoBD.Cliente;
import com.example.james.suv.Cliente.Vacinas.VacinaCliente;
import com.example.james.suv.Cliente.Vacinas.VacinasRecentes;
import com.example.james.suv.GeradorQRCode;
import com.example.james.suv.GerarPDF;
import com.example.james.suv.R;
import com.example.james.suv.adapter.SlidingMenuAdapter;
import com.example.james.suv.model.ItemSlideMenu;

import java.util.ArrayList;
import java.util.List;

public class MenuCliente extends AppCompatActivity {
    private List<ItemSlideMenu> listSliding;
    private SlidingMenuAdapter adapter;
    private ListView listViewSliding;
    private DrawerLayout drawerLayout;
    private ActionBarDrawerToggle actionBarDrawerToggle;
    Cliente c = new Cliente();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu_cliente);

        listViewSliding = (ListView) findViewById(R.id.lv_sliding_menu);
        drawerLayout = (DrawerLayout) findViewById(R.id.drawer_layout);
        listSliding = new ArrayList<>();
        //Add item for sliding list
        listSliding.add(new ItemSlideMenu(R.drawable.icon, "Bem- vindo " + c.getNome()));
        listSliding.add(new ItemSlideMenu(R.drawable.icon_historico, "Vacinas Recentes"));
        listSliding.add(new ItemSlideMenu(R.drawable.icon_editar, "Dados do Cliente"));
        listSliding.add(new ItemSlideMenu(R.drawable.icon_editar, "Alterar Senha"));
        listSliding.add(new ItemSlideMenu(R.drawable.icon_vacina, "Vacinas"));
        listSliding.add(new ItemSlideMenu(R.drawable.icon_conf, "Gerar QR Code"));
        listSliding.add(new ItemSlideMenu(R.drawable.icon_conf, "Gerar PDF"));
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

        actionBarDrawerToggle = new ActionBarDrawerToggle(this, drawerLayout, R.string.drawer_opened, R.string.drawer_closed) {
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

        if (actionBarDrawerToggle.onOptionsItemSelected(item)) {
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
                fragment = new VacinasRecentes();
                break;
            case 1:
                fragment = new VacinasRecentes();
                break;
            case 2:
                fragment = new Dados_usuario();
                break;
            case 3:
                fragment = new AlterarSenhaUsuario();
                break;
            case 4:
                fragment = new VacinaCliente();
                break;
            case 5:
                fragment = new GeradorQRCode();
                break;
            case 6:
                Intent m = new Intent(MenuCliente.this, GerarPDF.class).setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                startActivity(m);
                break;
            case 7:
                sair();
                break;
            default:
                fragment = new VacinasRecentes();
                break;
        }

        if (null != fragment) {
            FragmentManager fragmentManager = getFragmentManager();
            FragmentTransaction transaction = fragmentManager.beginTransaction();
            transaction.replace(R.id.main_content, fragment);
            transaction.addToBackStack(null);
            transaction.commit();
        }
    }

    private void sair() {
        AlertDialog.Builder alerta = new AlertDialog.Builder(MenuCliente.this);
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
                        Intent m = new Intent(MenuCliente.this, MainActivity.class).setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                        startActivity(m);
                    }
                });
        AlertDialog alertDialog = alerta.create();
        alertDialog.show();
    }
}
