package io.chat.utils;

import io.chat.entity.User;
import io.chat.repository.UserRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Random;

@Component
public class BootstrapDataOnStartup implements CommandLineRunner {
    UserRepo userRepo;

    @Autowired
    public BootstrapDataOnStartup(UserRepo userRepo){
        this.userRepo = userRepo;
    }

    @Override
    public void run(String... args) throws Exception {

        // MALEs
        User male1 = new User();
        User male2 = new User();
        User male3 = new User();
        User male4 = new User();
        User male5 = new User();
        User male6 = new User();
        User male7 = new User();
        User male8 = new User();
        User male9 = new User();
        User male10 = new User();
        User male11 = new User();
        User male12 = new User();
        User male13 = new User();
        User male14 = new User();
        User male15 = new User();
        User male16 = new User();

        male1.setName("Ahmed Abdel-Rahman");
        male2.setName("Tarek Hassan");
        male3.setName("Omar Ali");
        male4.setName("Karim Mansour");
        male5.setName("Khaled Rizk");
        male6.setName("Hassan El-Masry");
        male7.setName("Mahmoud Sharif");
        male8.setName("Youssef Abdel-Aziz");
        male9.setName("Mohamed Khalil");
        male10.setName("Amr Abdel-Salam");
        male11.setName("Ibrahim Farag");
        male12.setName("Ali Kamal");
        male13.setName("Hesham Nasser");
        male14.setName("Adel Ashraf");
        male15.setName("Essam Abdel-Hadi");
        male16.setName("Sherif Mahmoud");

        male1.setEmail("ahmed.abdelrahman@example.com");
        male2.setEmail("tarek.hassan@example.com");
        male3.setEmail("omar.ali@example.com");
        male4.setEmail("karim.mansour@example.com");
        male5.setEmail("khaled.rizk@example.com");
        male6.setEmail("hassan.elmasry@example.com");
        male7.setEmail("mahmoud.sharif@example.com");
        male8.setEmail("youssef.abdelaziz@example.com");
        male9.setEmail("mohamed.khalil@example.com");
        male10.setEmail("amr.abdelsalam@example.com");
        male11.setEmail("ibrahim.farag@example.com");
        male12.setEmail("ali.kamal@example.com");
        male13.setEmail("hesham.nasser@example.com");
        male14.setEmail("adel.ashraf@example.com");
        male15.setEmail("essam.abdelhadi@example.com");
        male16.setEmail("sherif.mahmoud@example.com");

        ArrayList<User> maleList = new ArrayList<>();


        maleList.add(male1);
        maleList.add(male2);
        maleList.add(male3);
        maleList.add(male4);
        maleList.add(male5);
        maleList.add(male6);
        maleList.add(male7);
        maleList.add(male8);
        maleList.add(male9);
        maleList.add(male10);
        maleList.add(male11);
        maleList.add(male12);
        maleList.add(male13);
        maleList.add(male14);
        maleList.add(male15);
        maleList.add(male16);


        Random random = new Random();
        for (int i = 0; i < maleList.size(); i++) {
            String password = "";
            for (int j = 0; j < 4; j++) {
                password += String.valueOf(random.nextInt(10));
            }
            String maleImage = "man (" + (i + 1) + ").jpg";
            maleList.get(i).setImage(maleImage);
            maleList.get(i).setPassword(password);
        }


        ////////////////////             Females      ////////////////////////
        User female1 = new User();
        User female2 = new User();
        User female3 = new User();
        User female4 = new User();
        User female5 = new User();
        User female6 = new User();
        User female7 = new User();
        User female8 = new User();
        User female9 = new User();
        User female10 = new User();
        User female11 = new User();
        User female12 = new User();
        User female13 = new User();
        User female14 = new User();
        User female15 = new User();
        User female16 = new User();



        female1.setName("Fatima Abdel-Rahman");
        female2.setName("Nour Ali");
        female3.setName("Yasmin Mansour");
        female4.setName("Aisha Hassan");
        female5.setName("Salma Rizk");
        female6.setName("Mariam El-Masry");
        female7.setName("Sarah Sharif");
        female8.setName("Hana Abdel-Aziz");
        female9.setName("Mona Khalil");
        female10.setName("Amira Abdel-Salam");
        female11.setName("Rania Farag");
        female12.setName("Leila Kamal");
        female13.setName("Dalia Nasser");
        female14.setName("Nadia Ashraf");
        female15.setName("Laila Abdel-Hadi");
        female16.setName("Samira Mahmoud");

        female1.setEmail("fatima.abdelrahman@example.com");
        female2.setEmail("nour.ali@example.com");
        female3.setEmail("yasmin.mansour@example.com");
        female4.setEmail("aisha.hassan@example.com");
        female5.setEmail("salma.rizk@example.com");
        female6.setEmail("mariam.elmasry@example.com");
        female7.setEmail("sarah.sharif@example.com");
        female8.setEmail("hana.abdelaziz@example.com");
        female9.setEmail("mona.khalil@example.com");
        female10.setEmail("amira.abdelsalam@example.com");
        female11.setEmail("rania.farag@example.com");
        female12.setEmail("leila.kamal@example.com");
        female13.setEmail("dalia.nasser@example.com");
        female14.setEmail("nadia.ashraf@example.com");
        female15.setEmail("laila.abdelhadi@example.com");
        female16.setEmail("samira.mahmoud@example.com");

        ArrayList<User> femaleList = new ArrayList<>();

        femaleList.add(female1);
        femaleList.add(female2);
        femaleList.add(female3);
        femaleList.add(female4);
        femaleList.add(female5);
        femaleList.add(female6);
        femaleList.add(female7);
        femaleList.add(female8);
        femaleList.add(female9);
        femaleList.add(female10);
        femaleList.add(female11);
        femaleList.add(female12);
        femaleList.add(female13);
        femaleList.add(female14);
        femaleList.add(female15);
        femaleList.add(female16);

        for (int i = 0; i < femaleList.size(); i++) {
            String password = "";
            for (int j = 0; j < 4; j++) {
                password += String.valueOf(random.nextInt(10));
            }
            String femaleImage = "girl (" + (i + 1) + ").jpg";
            femaleList.get(i).setImage(femaleImage);
            femaleList.get(i).setPassword(password);
        }

        userRepo.saveAll(maleList);
        userRepo.saveAll(femaleList);

    }
}
