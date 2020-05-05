package com.example.mercadoesclavojmp;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class ProductosAdapter extends RecyclerView.Adapter<ProductosAdapter.ViewHolderProducto> {


    private List<Productos> productosList;
    private ProductosAdapterListener productosAdapterListener;

    public ProductosAdapter(List<Productos> productosList, ProductosAdapterListener listener) {
        this.productosList = productosList;
        this.productosAdapterListener = listener;
    }

    @NonNull
    @Override
    public ViewHolderProducto onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        View view = layoutInflater.inflate(R.layout.celda_producto, parent, false);
        return new ViewHolderProducto(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolderProducto holder, int position) {
        Productos productos = this.productosList.get(position);
        holder.onBind(productos);

    }

    @Override
    public int getItemCount() {
        return productosList.size();
    }

    protected class ViewHolderProducto extends RecyclerView.ViewHolder{

        private ImageView imageViewProducto;
        private TextView textViewNombreProducto;
        private TextView textViewPrecioProducto;

        public ViewHolderProducto(@NonNull final View itemView) {
            super(itemView);
            imageViewProducto = itemView.findViewById(R.id.celdaImageViewProducto);
            textViewNombreProducto = itemView.findViewById(R.id.celdaTextViewNombreProducto);
            textViewPrecioProducto = itemView.findViewById(R.id.celdaTextViewPrecioProducto);

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {

                    Productos productos = productosList.get(getAdapterPosition());
                    productosAdapterListener.onClick(productos);
                }
            });


        }

        public void onBind(Productos productos) {
            imageViewProducto.setImageResource(productos.getImagen());
            textViewNombreProducto.setText(productos.getNombre());
            textViewPrecioProducto.setText(productos.getPrecio().toString());

        }


        }
        public interface ProductosAdapterListener{
        public void onClick(Productos productos);

    }
}
