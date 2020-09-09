package com.example.obligatorio.Presentacion;

import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.BaseAdapter;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.obligatorio.Common.Publicidad;
import com.example.obligatorio.Dominio.Controladora;
import com.example.obligatorio.R;

import java.io.ByteArrayInputStream;
import java.util.ArrayList;

public class MyAdapterPublicidad extends BaseAdapter {

    Intent i;
    Context context;
    private Controladora unaControladora;
    ArrayList<Publicidad> publicidades;
    private static LayoutInflater inflater = null;
    //This is the constructor for MyAdapter: You can edit its second parameter a/c to your requirements
    //I used Array List of strings
        public MyAdapterPublicidad(Context context, ArrayList<Publicidad> publicidades){
        this.context=context;
        unaControladora = new Controladora(context);
        this.publicidades = publicidades;
        inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }


    @Override
    public int getCount() {
        return publicidades.size();
    }

    @Override
    public Publicidad getItem(int position) {
        return publicidades.get(position);
    }

    @Override
    public long getItemId(int position) {
        return publicidades.get(position).get_id();
    }

    @Override
    //Important method: You can write your own code in this function
    //You can set your textview/ button methods
    public View getView(final int position, View convertView, ViewGroup parent){
        //Get the data item for this position
        final Publicidad item = getItem(position);
        //Check if an existing view is being reused, otherwise inflate the view
        if(convertView == null){
            convertView = LayoutInflater.from(context).inflate(R.layout.listview_publicidades_estilo, parent, false);
        }

        final TextView list_txtTitulo=(TextView)convertView.findViewById(R.id.lstTxtTituloPublicidad);
        final TextView list_txtDescripcion=(TextView)convertView.findViewById(R.id.lstTxtDescripcionPublicidad);
        ImageButton list_but_baja = (ImageButton)convertView.findViewById(R.id.lstBtnBajaPublicidad);
        ImageButton list_but_modificar=(ImageButton)convertView.findViewById(R.id.lstBtnEditarPublicidad);
        ImageView list_img = (ImageView)convertView.findViewById(R.id.lstImgPublicidad);


        list_txtTitulo.setText(item.get_titulo());
        list_txtDescripcion.setText(item.get_descripcion());

       /* ByteArrayInputStream imageStream = new ByteArrayInputStream(item.get_imagen());
        Bitmap laImage= BitmapFactory.decodeStream(imageStream);*/

        byte[] imgBytes;
        Bitmap bm = BitmapFactory.decodeByteArray(item.get_imagen(), 0, item.get_imagen().length);
        list_img.setImageBitmap(bm);

        //list_img.setImageBitmap(laImage);


        list_but_baja.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                unaControladora.bajaPublicidad(item);
                i = new Intent(context,Administracion.class);
                ((Administracion)context).finish();
                context.startActivity(i);

            }
        });
        list_but_modificar.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                i = new Intent(context,Modificar_Publicidad.class);
                i.putExtra("keyPublicidadId",item.get_id());
                i.putExtra("keyPublicidadTitulo",list_txtTitulo.getText().toString());
                i.putExtra("keyPublicidadDescripcion",list_txtDescripcion.getText().toString());
                i.putExtra("keyPublicidadImagen",item.get_imagen());
                context.startActivity(i);
            }

        });
        return convertView;
    }
}
