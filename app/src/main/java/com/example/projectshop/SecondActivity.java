package com.example.projectshop;

import android.content.Intent;
import android.graphics.Color;
import android.net.Uri;
import android.os.Bundle;
import android.renderscript.ScriptGroup;
import android.view.View;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.example.projectshop.databinding.CoolmarketBinding;

public class SecondActivity extends AppCompatActivity {
    private String MAIL = "yandex@yandex.ru";
    private String NUMBER = "88005553535";

    @Override

    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        CoolmarketBinding binding = CoolmarketBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        binding.phone.setText(NUMBER);

        binding.phone.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Intent.ACTION_DIAL, Uri.parse("tel:"+NUMBER));
                startActivity(intent);
            }
        });
        binding.send.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String mailText = "You ordered:\n";
                if(binding.fruits.isChecked()) mailText += "Fruits ";
                if(binding.vegetables.isChecked()) mailText += "Vegetables ";
                if(binding.mushrooms.isChecked()) mailText += "Mushrooms ";
                if(binding.meat.isChecked()) mailText += "Meat ";
                if(binding.diary.isChecked()) mailText += "Diary ";
                if(binding.sweets.isChecked()) mailText += "Sweets ";
                mailText += "\n\n You have chosen:\n";
                if(binding.delivery.isActivated()) mailText += "Delivery";
                else mailText += "Pickup";
                mailText += "\n\n Details about your order:\n";
                mailText += binding.mailtext.getText().toString();

                Intent intent = new Intent(Intent.ACTION_SENDTO);
                intent.setData(Uri.parse("mailto:"));
                intent.putExtra(Intent.EXTRA_EMAIL,new String[]{MAIL});
                intent.putExtra(Intent.EXTRA_TEXT,mailText);
                intent.putExtra(Intent.EXTRA_SUBJECT, "Your Order");
                startActivity(intent);
            }
        });
    }
}
