package idv.tomazwang.app.pokedex;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;

/**
 * @authour TomazWang
 */
public class PokedexAdapter extends RecyclerView.Adapter<PokedexAdapter.ViewHolder>{


    private ArrayList<Pokemon> mDataSet = new ArrayList<>();

    public class ViewHolder extends  RecyclerView.ViewHolder{

        private final TextView txt_name;
        private final ImageView img_pic;


        public ViewHolder(View itemView) {
            super(itemView);

            img_pic = (ImageView) itemView.findViewById(R.id.img_pokemon_pic);
            txt_name = (TextView) itemView.findViewById(R.id.txt_pokemon_name);

        }


        public TextView getTxt_name() {
            return txt_name;
        }

        public ImageView getImg_pic() {
            return img_pic;
        }
    }


    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        // inflate viewHolder here.
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_pokemon,parent,false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {

        holder.getTxt_name().setText(mDataSet.get(position).getName_zh());
    }

    @Override
    public int getItemCount() {
        return mDataSet.size();
    }

    public PokedexAdapter(ArrayList<Pokemon> mDataSet) {
        this.mDataSet = mDataSet;
    }
}
