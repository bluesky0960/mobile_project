package com.kmu.mma_ver1.adapters;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.TextView;
import android.widget.Toast;

import com.kmu.mma_ver1.R;
import com.kmu.mma_ver1.models.Member;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;

import static android.media.CamcorderProfile.get;

public class MemberAdapter extends RecyclerView.Adapter<MemberAdapter.MemberHolder> {

    public static final int UNSELECTION_MODE = 1;
    public static final int SELECTION_MODE = 2;

    private int count = 0;

    private ArrayList<Member> memberList;

    public MemberAdapter(){
        memberList = new ArrayList<>();
    }

    public void addItem(Member member){
        memberList.add(member);
        notifyDataSetChanged();
    }
    public Member getItem(int position){
        return this.memberList.get(position);
    }

    @NonNull
    @Override
    public MemberHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.member_item, parent, false);
        MemberHolder memberHolder = new MemberHolder(view);


        return memberHolder;
    }

    @Override
    public void onBindViewHolder(MemberHolder holder, int position) {
        Member member = getItem(position);
        holder.mNameView.setText(member.getUsername());
        holder.mPhoneView.setText(member.getPhone());
    }

    @Override
    public int getItemCount() {
        return memberList.size();
    }

    public static class MemberHolder extends RecyclerView.ViewHolder{

        private TextView mNameView;
        private TextView mPhoneView;


        public MemberHolder(View v){
            super(v);
            mNameView = (TextView) v.findViewById(R.id.name);
            mPhoneView = (TextView) v.findViewById(R.id.phone);
        }
    }
}
