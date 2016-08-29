package idv.tomazwang.app.pokedex;

import android.os.Bundle;
import android.support.v7.widget.RecyclerView;

import java.util.ArrayList;


public class MainFragment extends RecyclerViewFragment<String> {

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
    protected void initDataSet(ArrayList<String> dataSet) {

        // TODO: replace these fake data here!
        for (int i = 1; i <= 251; i++) {
            dataSet.add("Number " + i);
        }
    }
}
