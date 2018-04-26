package big.nattawut.in.th.nattawut.fragment;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.Toast;

import big.nattawut.in.th.nattawut.MainActivity;
import big.nattawut.in.th.nattawut.R;
import big.nattawut.in.th.nattawut.utility.AddNewUserToSever;
import big.nattawut.in.th.nattawut.utility.MyAlert;
import big.nattawut.in.th.nattawut.utility.MyConstant;

public class RegisterFragment extends Fragment {

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        //  Create Toolbar
        createToolbar();

    }// Main menthod

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        if (item.getItemId() == R.id.itemupload) {

            uploadValueToSever();
            return true;

        }


        return super.onOptionsItemSelected(item);
    }

    private void uploadValueToSever() {

//        get value true EditText
        EditText nameEditText = getView().findViewById(R.id.edtname);
        EditText userEditText = getView().findViewById(R.id.edtuser);
        EditText passwordEditText = getView().findViewById(R.id.edtpaassword);

        //       Change Data Type From EditText to String
        String nameString = nameEditText.getText().toString().trim();
        String userString = userEditText.getText().toString().trim();
        String passwordString = passwordEditText.getText().toString().trim();

//        check Space
        if (nameString.isEmpty() || userString.isEmpty() || passwordString.isEmpty()) {
//            have space

            MyAlert myAlert = new MyAlert(getActivity());
            myAlert.normaIDialog("Have Space", "Plese Fill All Black");

        } else {
//            no space
            try {

                MyConstant myConstant = new MyConstant();
                AddNewUserToSever addNewUserToSever = new AddNewUserToSever(getActivity());
                addNewUserToSever.execute(nameString,userString,passwordString,
                        myConstant.getUrlAddUser());

                String result = addNewUserToSever.get();
                Log.d("26ApriV6", "result ==>" + result);

                if (Boolean.parseBoolean(result)) {
                    getActivity().getSupportFragmentManager().popBackStack();
                }else{
                    Toast.makeText(getActivity(), "Error connot Upload",Toast.LENGTH_LONG).show();
                }

            }catch (Exception e){
                e.printStackTrace();
            }


        }
    }


    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        super.onCreateOptionsMenu(menu, inflater);

        inflater.inflate(R.menu.menu_registerfile, menu);

    }

    private void createToolbar() {

        Toolbar toolbar = getActivity().findViewById(R.id.toolbarRegister);
        ((MainActivity) getActivity()).setSupportActionBar(toolbar);

//        Setup Title
        ((MainActivity) getActivity()).getSupportActionBar().setTitle("Register");
        ((MainActivity) getActivity()).getSupportActionBar().setSubtitle("Plesse Fill All Blank");

//        setup nafication
        ((MainActivity) getActivity()).getSupportActionBar().setHomeButtonEnabled(true);
        ((MainActivity) getActivity()).getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getActivity().getSupportFragmentManager().popBackStack();
            }
        });

        setHasOptionsMenu(true);

    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_register, container, false);
        return view;
    }
}
