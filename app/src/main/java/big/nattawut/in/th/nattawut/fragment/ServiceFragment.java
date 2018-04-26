package big.nattawut.in.th.nattawut.fragment;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import org.json.JSONArray;
import org.json.JSONObject;

import big.nattawut.in.th.nattawut.R;
import big.nattawut.in.th.nattawut.utility.FoodAdapter;
import big.nattawut.in.th.nattawut.utility.GetAllData;
import big.nattawut.in.th.nattawut.utility.MyConstant;

public class ServiceFragment extends Fragment {
    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

//        Create LisView
        createLisView();
    }// Main method

    private void createLisView() {

        ListView listView = getView().findViewById(R.id.LisViewFood);
        MyConstant myConstant = new MyConstant();
        try {

            GetAllData getAllData = new GetAllData(getActivity());
            getAllData.execute(myConstant.getUrlGetAlluser());

            String jsonString = getAllData.get();

            JSONArray jsonArray = new JSONArray(jsonString);

            String[] foodString = new String[jsonArray.length()];
            String[] priceString = new String[jsonArray.length()];
            String[] detailString = new String[jsonArray.length()];
            String[] imageString = new String[jsonArray.length()];

            for (int i=0; i<jsonArray.length(); i+=1){

                JSONObject jsonObject = jsonArray.getJSONObject(i);

                foodString[i] = jsonObject.getString("NameFood");
                priceString[i] = jsonObject.getString("Price");
                detailString[i] = jsonObject.getString("Detail");
                imageString[i] = jsonObject.getString("ImagePath");

            }

            FoodAdapter foodAdapter = new FoodAdapter(getActivity(), imageString, foodString, priceString, detailString);
            listView.setAdapter(foodAdapter);

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_sevise, container, false);
        return view;
    }
}
