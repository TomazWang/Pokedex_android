package idv.tomazwang.app.pokedex;

import android.content.res.AssetManager;
import android.os.Bundle;
import android.support.v7.widget.RecyclerView;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.io.IOException;
import java.io.InputStream;
import java.lang.reflect.Type;
import java.util.ArrayList;


public class MainFragment extends RecyclerViewFragment<Pokemon> {

    private final static String TAG = MainFragment.class.getSimpleName();


    private static final String FILE_DIR_KEY = "mfileDir";
    private String mFileDir;

    public static MainFragment newInstance(String fileDir) {
        MainFragment fragment = new MainFragment();
        Bundle bundle = new Bundle();
        bundle.putString(FILE_DIR_KEY, fileDir);

        fragment.setArguments(bundle);

        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        Bundle arguments = this.getArguments();
        mFileDir = arguments.getString(FILE_DIR_KEY);

        super.onCreate(savedInstanceState);
    }


    @Override
    protected RecyclerView.Adapter createAdapter() {
        return new PokedexAdapter(getDataSet());
    }

    @Override
    protected void initDataSet(ArrayList<Pokemon> dataSet) {

        try {
            AssetManager assetManager = getActivity().getAssets();
            InputStream is = assetManager.open(mFileDir);

            int size = is.available();
            byte[] buffer = new byte[size];

            is.read(buffer);
            is.close();

            String bufferString = new String(buffer, "UTF-8");

            Gson gson = new Gson();

            Type listType = new TypeToken<ArrayList<Pokemon>>() {
            }.getType();

            ArrayList<Pokemon> pokemonList = gson.fromJson(bufferString, listType);

            dataSet.addAll(pokemonList);

        } catch (IOException e) {
            e.printStackTrace();
        }

    }
}


