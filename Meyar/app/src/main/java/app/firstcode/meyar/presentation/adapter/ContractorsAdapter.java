package app.firstcode.meyar.presentation.adapter;

import android.Manifest;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.support.annotation.NonNull;
import android.support.v4.app.ActivityCompat;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.afollestad.materialdialogs.DialogAction;
import com.afollestad.materialdialogs.MaterialDialog;
import com.bumptech.glide.Glide;
import com.google.gson.Gson;
import com.whinc.widget.ratingbar.RatingBar;

import java.util.ArrayList;

import app.firstcode.meyar.R;
import app.firstcode.meyar.data.service.model.ContractorSearchModel;
import app.firstcode.meyar.presentation.activities.ContractorsDetailsActivity;
import de.hdodenhof.circleimageview.CircleImageView;

/**
 * Created by Diaa on 6/5/2017.
 */

public class ContractorsAdapter extends RecyclerView.Adapter<ContractorsAdapter.ContractorViewHolder> {
    Context context;
    ArrayList<ContractorSearchModel> contractorSmallDatas;

    public ContractorsAdapter(Context context, ArrayList<ContractorSearchModel> stringArrayList) {
        this.context = context;
        this.contractorSmallDatas = stringArrayList;
    }


    @Override
    public ContractorViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(context).inflate(R.layout.item_row_contractors, parent, false);
        return new ContractorViewHolder(v);
    }

    @Override
    public void onBindViewHolder(ContractorViewHolder holder, int position) {
        holder.setData(contractorSmallDatas.get(position));
    }

    @Override
    public int getItemCount() {
        return contractorSmallDatas.size();
    }

    public class ContractorViewHolder extends RecyclerView.ViewHolder {

        CircleImageView circleImageView;
        TextView contractorName;
        TextView contractor_type;
        RatingBar ratingBar;
        ImageView call_button;

        public ContractorViewHolder(View itemView) {
            super(itemView);
            circleImageView = (CircleImageView) itemView.findViewById(R.id.contractor_image);
            contractorName = (TextView) itemView.findViewById(R.id.contractor_name);
            contractor_type = (TextView) itemView.findViewById(R.id.contractor_type);
            ratingBar = (RatingBar) itemView.findViewById(R.id.ratingBar);
            call_button = (ImageView) itemView.findViewById(R.id.call_button);
        }

        public void setData(final ContractorSearchModel data) {
            Glide.with(context).load(data.getProfilePicture()).placeholder(R.drawable.ic_user_place_holder).into(circleImageView);
            contractorName.setText(data.getName());
            contractor_type.setText(data.getCategory());
            ratingBar.setCount(data.getRate());

            call_button.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {

                    Intent intent = new Intent(Intent.ACTION_DIAL);
                    intent.setData(Uri.parse("tel:" + data.getMobile()));
                    context.startActivity(intent);
                }
            });

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent = new Intent(context, ContractorsDetailsActivity.class);
                    String object = new Gson().toJson(data);
                    intent.putExtra("contractor", object);
                    context.startActivity(intent);
                }
            });

        }
    }
}
