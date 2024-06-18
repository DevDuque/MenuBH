package com.example.menubh.components.utils;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.menubh.R;
import com.example.menubh.components.classes.RestaurantClass;

import java.util.ArrayList;

// Adapter para RecyclerView que exibe cartões de restaurantes
public class CardAdapter extends RecyclerView.Adapter<CardAdapter.CardViewHolder> {

    private ArrayList<RestaurantClass> restaurantList;

    // Construtor que recebe a lista de restaurantes a ser exibida
    public CardAdapter(ArrayList<RestaurantClass> restaurantList) {
        this.restaurantList = restaurantList;
    }

    @NonNull
    @Override
    public CardViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        // Popula o layout do cartão de restaurante (layout_card.xml)
        View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.layout_card, parent, false);

        // Retorna um novo ViewHolder com o layout completo
        return new CardViewHolder(itemView);
    }

    // Método chamado para exibir os dados de um restaurante em um ViewHolder específico
    @Override
    public void onBindViewHolder(@NonNull CardViewHolder holder, int position) {
        // Obtém o restaurante na posição atual
        RestaurantClass currentRestaurant = restaurantList.get(position);

        // Define os textos nos TextViews do ViewHolder com os dados do restaurante
        holder.textViewName.setText(currentRestaurant.getRestaurantName());
        holder.textViewAddress.setText(currentRestaurant.getRestaurantAddress());
        holder.textViewNumber.setText(currentRestaurant.getRestaurantNumber());

        // Define a imagem de especialidade com base no tipo de restaurante
        switch (currentRestaurant.getRestaurantSpecialty()) {
            case "Sushi":
                holder.specialtyView.setImageResource(R.drawable.img_sushi);
                break;
            case "Barbecue":
                holder.specialtyView.setImageResource(R.drawable.img_barbecue);
                break;
            case "Fish":
                holder.specialtyView.setImageResource(R.drawable.img_fish);
                break;
            case "Pizza":
                holder.specialtyView.setImageResource(R.drawable.img_pizza);
                break;
            default:
                holder.specialtyView.setImageResource(R.drawable.img_addbutton);
                break;
        }

        // Define as estrelas de avaliação com base na pontuação do restaurante
        int stars = currentRestaurant.getRestaurantRating();
        for (int i = 0; i < 5; i++) {
            if (i < stars) {
                holder.starViews[i].setImageResource(R.drawable.mini_activestar);
            } else {
                holder.starViews[i].setImageResource(R.drawable.mini_inactivestar);
            }
        }

        // Define a imagem do botão de favoritos com base no estado do restaurante
        if (currentRestaurant.getFavorite()) {
            holder.favoriteButton.setImageResource(R.drawable.img_activestar);
        } else {
            holder.favoriteButton.setImageResource(R.drawable.img_inactivestar);
        }

        // Configura o OnClickListener do botão de favoritos
        holder.favoriteButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Inverte o estado de favorito ao clicar no botão
                boolean isCurrentlyFavorite = currentRestaurant.getFavorite();
                currentRestaurant.setFavorite(!isCurrentlyFavorite);

                // Notifica o adaptador para atualizar a exibição do botão de favoritos
                notifyItemChanged(position);
            }
        });
    }

    // Retorna o número total de itens na lista de restaurantes
    @Override
    public int getItemCount() {
        return restaurantList.size();
    }

    // ViewHolder que contém referências aos elementos visuais do layout do cartão de restaurante
    public static class CardViewHolder extends RecyclerView.ViewHolder {
        public TextView textViewName;
        public TextView textViewAddress;
        public TextView textViewNumber;
        public ImageView specialtyView;
        public ImageView[] starViews;
        public ImageButton favoriteButton;

        // Construtor que inicializa as referências aos elementos visuais
        public CardViewHolder(View itemView) {
            super(itemView);
            textViewName = itemView.findViewById(R.id.textName);
            textViewAddress = itemView.findViewById(R.id.textAddress);
            textViewNumber = itemView.findViewById(R.id.textNumber);
            specialtyView = itemView.findViewById(R.id.specialty_view);

            starViews = new ImageView[5];  // Inicializa o array para as estrelas de avaliação

            // Preenche o array com as referências dos ImageView das estrelas
            starViews[0] = itemView.findViewById(R.id.star1);
            starViews[1] = itemView.findViewById(R.id.star2);
            starViews[2] = itemView.findViewById(R.id.star3);
            starViews[3] = itemView.findViewById(R.id.star4);
            starViews[4] = itemView.findViewById(R.id.star5);

            favoriteButton = itemView.findViewById(R.id.favoriteButton);
        }
    }
}
