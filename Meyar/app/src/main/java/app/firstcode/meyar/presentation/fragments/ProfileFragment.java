package app.firstcode.meyar.presentation.fragments;

import android.support.annotation.Nullable;
import android.support.design.widget.FloatingActionButton;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.orhanobut.hawk.Hawk;

import app.firstcode.meyar.R;
import app.firstcode.meyar.base.BaseFragment;
import app.firstcode.meyar.data.service.model.Contractor;
import de.hdodenhof.circleimageview.CircleImageView;

public class ProfileFragment extends BaseFragment implements View.OnClickListener {


    private CircleImageView profile_image;
    private ImageView profileImagePlaceholder;
    private EditText contractorNameEdit;
    private EditText contractorEmailEdit;
    private EditText contractorNumberEdit;
    private EditText contractorBusinessName;
    private EditText ownerNameEdit;
    private EditText ownerNumberEdit;
    private EditText commissionerNameEdit;
    private EditText commissionerPhoneNumberEdit;
    private EditText commissionerNationalityEdit;
    private EditText commissionerEmployeesNumberEdit;
    private EditText companyAgeText;
    private EditText commercialRecordNumberEdit;
    private TextView textView;
    private CheckBox riyadhCheckBox;
    private CheckBox jeddahCheckBox;
    private CheckBox dammamCheckBox;
    //    private Button uploadLatter;
    private ImageView LetterPhoto;
    private ImageView firstImage;
    private ImageView secondImage;
    private ImageView thirdImage;
    private Button save;
    private FloatingActionButton edit_fab;
    public boolean enable = false;

    /**
     * Find the Views in the layout<br />
     * <br />
     * Auto-created on 2017-06-19 21:16:00 by Android Layout Finder
     * (http://www.buzzingandroid.com/tools/android-layout-finder)
     */
    private void findViews(View v) {
        profile_image = (CircleImageView) v.findViewById(R.id.profile_image);
        profileImagePlaceholder = (ImageView) v.findViewById(R.id.profile_image_placeholder);
        contractorNameEdit = (EditText) v.findViewById(R.id.contractor_name_edit);
        contractorEmailEdit = (EditText) v.findViewById(R.id.contractor_email_edit);
        contractorNumberEdit = (EditText) v.findViewById(R.id.contractor_number_edit);
        contractorBusinessName = (EditText) v.findViewById(R.id.contractor_business_name);
        ownerNameEdit = (EditText) v.findViewById(R.id.owner_name_edit);
        ownerNumberEdit = (EditText) v.findViewById(R.id.owner_number_edit);
        commissionerNameEdit = (EditText) v.findViewById(R.id.commissioner_name_edit);
        commissionerPhoneNumberEdit = (EditText) v.findViewById(R.id.commissioner_phone_number_edit);
        commissionerNationalityEdit = (EditText) v.findViewById(R.id.commissioner_nationality_edit);
        commissionerEmployeesNumberEdit = (EditText) v.findViewById(R.id.commissioner_employees_number_edit);
        companyAgeText = (EditText) v.findViewById(R.id.company_age_text);
        commercialRecordNumberEdit = (EditText) v.findViewById(R.id.commercial_record_number_edit);
        textView = (TextView) v.findViewById(R.id.textView);
        riyadhCheckBox = (CheckBox) v.findViewById(R.id.riyadh_check_box);
        jeddahCheckBox = (CheckBox) v.findViewById(R.id.jeddah_check_box);
        dammamCheckBox = (CheckBox) v.findViewById(R.id.dammam_check_box);
        LetterPhoto = (ImageView) v.findViewById(R.id.LetterPhoto);
        firstImage = (ImageView) v.findViewById(R.id.first_image);
        secondImage = (ImageView) v.findViewById(R.id.second_image);
        thirdImage = (ImageView) v.findViewById(R.id.third_image);
        save = (Button) v.findViewById(R.id.save);
        edit_fab = (FloatingActionButton) v.findViewById(R.id.edit_fab);

        riyadhCheckBox.setOnClickListener(this);
        jeddahCheckBox.setOnClickListener(this);
        dammamCheckBox.setOnClickListener(this);
//        uploadLatter.setOnClickListener(this);
        save.setOnClickListener(this);
        edit_fab.setOnClickListener(this);

        Contractor.User user = ((Contractor) Hawk.get("user")).getUser();
        fillData(user);
    }

    private void fillData(Contractor.User user) {
        contractorNameEdit.setText(user.getName());
        contractorEmailEdit.setText(user.getEmail());
        contractorNumberEdit.setText(user.getMobile());
        contractorBusinessName.setText(user.getCommercialName());
        ownerNameEdit.setText(user.getOwnerName());
        ownerNumberEdit.setText(user.getOwnerMobile());
        commissionerNameEdit.setText(user.getAuthorizedPerson());
        commissionerPhoneNumberEdit.setText(user.getAuthorizedPersonMobile());
        commissionerNationalityEdit.setText(user.getNationality());
        commissionerEmployeesNumberEdit.setText(user.getNoOfEmployees() + "");
        companyAgeText.setText(user.getBusinessStart());
        commercialRecordNumberEdit.setText(user.getCommercialRegistrationNo());

        if (!user.getAuthorizationLetterPhoto().isEmpty()) {
            Glide.with(getActivity()).load(user.getAuthorizationLetterPhoto()).into(LetterPhoto);
        }
        if (!user.getProfilePicture().isEmpty()) {
            Glide.with(getActivity()).load(user.getProfilePicture()).into(profile_image);
        }
        if (!user.getImage().isEmpty()) {
            Glide.with(getActivity()).load(user.getImage()).into(firstImage);
        }
        if (!user.getImage2().isEmpty()) {
            Glide.with(getActivity()).load(user.getImage2()).into(secondImage);
        }
        if (!user.getImage3().isEmpty()) {
            Glide.with(getActivity()).load(user.getImage3()).into(thirdImage);
        }

        if (user.getCities() != null) {
            String cities = user.getCities();
            String[] strings = cities.split(",");
            for (int i = 0; i < strings.length; i++) {
                if (strings[i].equalsIgnoreCase("1")) {
                    riyadhCheckBox.setChecked(true);
                }
                if (strings[i].equalsIgnoreCase("2")) {
                    jeddahCheckBox.setChecked(true);
                }
                if (strings[i].equalsIgnoreCase("3")) {
                    dammamCheckBox.setChecked(true);
                }
            }
        }

    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_profile, null, false);
        toolbarSetTitle(R.string.nav_profile);
        setCheckedItem(R.id.nav_profile);
        findViews(v);

        disableViews();
        return v;
    }

    private void disableViews() {
        enable = false;
        profileImagePlaceholder.setEnabled(false);
        contractorNameEdit.setEnabled(false);
        contractorEmailEdit.setEnabled(false);
        contractorNumberEdit.setEnabled(false);
        contractorBusinessName.setEnabled(false);
        ownerNameEdit.setEnabled(false);
        ownerNumberEdit.setEnabled(false);
        commissionerNameEdit.setEnabled(false);
        commissionerPhoneNumberEdit.setEnabled(false);
        commissionerNationalityEdit.setEnabled(false);
        commissionerEmployeesNumberEdit.setEnabled(false);
        companyAgeText.setEnabled(false);
        commercialRecordNumberEdit.setEnabled(false);
        textView.setEnabled(false);
        riyadhCheckBox.setEnabled(false);
        jeddahCheckBox.setEnabled(false);
        dammamCheckBox.setEnabled(false);

        save.setVisibility(View.GONE);
    }

    private void enableViews() {
        enable = true;
        profileImagePlaceholder.setEnabled(true);
        contractorNameEdit.setEnabled(true);
        contractorEmailEdit.setEnabled(true);
        contractorNumberEdit.setEnabled(true);
        contractorBusinessName.setEnabled(true);
        ownerNameEdit.setEnabled(true);
        ownerNumberEdit.setEnabled(true);
        commissionerNameEdit.setEnabled(true);
        commissionerPhoneNumberEdit.setEnabled(true);
        commissionerNationalityEdit.setEnabled(true);
        commissionerEmployeesNumberEdit.setEnabled(true);
        companyAgeText.setEnabled(true);
        commercialRecordNumberEdit.setEnabled(true);
        textView.setEnabled(true);
        riyadhCheckBox.setEnabled(true);
        jeddahCheckBox.setEnabled(true);
        dammamCheckBox.setEnabled(true);

        save.setVisibility(View.VISIBLE);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.edit_fab:
                if (!enable) {
                    enableViews();
                } else {
                    disableViews();
                }
                break;
        }
    }
}
