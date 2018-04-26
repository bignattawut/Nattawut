package big.nattawut.in.th.nattawut.fragment;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import org.json.JSONArray;
import org.json.JSONObject;

import big.nattawut.in.th.nattawut.R;
import big.nattawut.in.th.nattawut.utility.GetAllData;
import big.nattawut.in.th.nattawut.utility.MyAlert;
import big.nattawut.in.th.nattawut.utility.MyConstant;

public class MainFragment extends Fragment {


    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

//         Register Controller
        registerController();

//        Login Controkker
        loginControkker();


    }//Main method

    private void loginControkker() {
        Button button = getView().findViewById(R.id.btnlogin);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                EditText userEditText = getView().findViewById(R.id.edtuser);
                EditText passwordEditText = getView().findViewById(R.id.edtpaassword);

                String userString = userEditText.getText().toString().trim();
                String passwordString = passwordEditText.getText().toString().trim();

                if (userString.isEmpty() || passwordString.isEmpty()) {

                    MyAlert myAlert = new MyAlert(getActivity());
                    myAlert.normaIDialog("Move space",
                            "Please Fill Every Blank");

                } else {
//                    No Space
                    MyConstant myConstant = new MyConstant();
                    Boolean b = true;
                    String truePass = null, nameuser = null;
                    MyAlert myAlert = new MyAlert(getActivity());

                    try {
                        GetAllData getAllData = new GetAllData(getActivity());
                        getAllData.execute(myConstant.getUrlGetAlluser());

                        String jsonString = getAllData.get();
                        Log.d("26/Aprilv1", "JSON ==>" + jsonString);

                        JSONArray jsonArray = new JSONArray(jsonString);
                        for (int i = 0; i < jsonArray.length(); i += 1) {
                            JSONObject jsonObject = jsonArray.getJSONObject(i);

                            if (userString.equals(jsonObject.getString("User"))) {

                                b = false;
                                truePass = jsonObject.getString("Password");
                                nameuser = jsonObject.getString("Name");
                            }

                        }
                        if (b) {
                            myAlert.normaIDialog("User False",
                                    "No User in my Databass");
                        } else if (passwordString.equals(truePass)) {
                            Toast.makeText(getActivity(), "Welcome" + nameuser,
                                    Toast.LENGTH_LONG).show();

                            getActivity().getSupportFragmentManager()
                                    .beginTransaction()
                                    .replace(R.id.contentMainfragement, new ServiceFragment())
                                    .commit();

                        } else {
                            myAlert.normaIDialog("Password False",
                                    "Please Try Agaies Password False");
                        }

                    } catch (Exception e) {
                        e.printStackTrace();
                    }

                } //if

            }
        });
    }

    private void registerController() {
        TextView textView = getView().findViewById(R.id.txtRegister);
        textView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

//                Replace Fragement
                getActivity().getSupportFragmentManager()
                        .beginTransaction()
                        .replace(R.id.contentMainfragement, new RegisterFragment())
                        .addToBackStack(null)
                        .commit();
            }
        });
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_main, container, false);
        return view;
    }
}//Main Class
