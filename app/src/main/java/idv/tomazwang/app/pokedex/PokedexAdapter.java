package idv.tomazwang.app.pokedex;

import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.resource.drawable.GlideDrawable;
import com.bumptech.glide.request.RequestListener;
import com.bumptech.glide.request.target.Target;

import java.util.ArrayList;

import static android.content.ContentValues.TAG;

public class PokedexAdapter extends RecyclerView.Adapter<PokedexAdapter.ViewHolder>{


    private ArrayList<Pokemon> mDataSet = new ArrayList<>();

    class ViewHolder extends  RecyclerView.ViewHolder{

        private final TextView txt_name;
        private final ImageView img_pic;


        ViewHolder(View itemView) {
            super(itemView);

            img_pic = (ImageView) itemView.findViewById(R.id.img_pokemon_pic);
            txt_name = (TextView) itemView.findViewById(R.id.txt_pokemon_name);

        }


        TextView getTxt_name() {
            return txt_name;
        }
        ImageView getImg_pic() {
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

        String pic_link = mDataSet.get(position).getPic_link();

        Log.d(TAG, "onBindViewHolder: showing "+pic_link);
        ImageView img_pic = holder.getImg_pic();


        Glide
                .with(img_pic.getContext())
                .load(pic_link)
                .error(android.R.drawable.arrow_down_float)
                .fitCenter()
                .thumbnail(0.1f)
                .listener(requestListener)
                .into(img_pic);
    }



    @Override
    public int getItemCount() {
        return mDataSet.size();
    }

    public PokedexAdapter(ArrayList<Pokemon> mDataSet) {
        this.mDataSet = mDataSet;
    }

    private RequestListener<? super String, GlideDrawable> requestListener = new RequestListener<String, GlideDrawable>() {
        @Override
        public boolean onException(Exception e, String model, Target<GlideDrawable> target, boolean isFirstResource) {

            Log.w(TAG, "onException: Request Exception",e);

            return false;
        }

        @Override
        public boolean onResourceReady(GlideDrawable resource, String model, Target<GlideDrawable> target, boolean isFromMemoryCache, boolean isFirstResource) {
            return false;
        }
    };

}
