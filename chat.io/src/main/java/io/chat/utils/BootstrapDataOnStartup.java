package io.chat.utils;

import io.chat.entity.User;
import io.chat.repository.UserRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
public class BootstrapDataOnStartup implements CommandLineRunner {
    UserRepo userRepo;

    @Autowired
    public BootstrapDataOnStartup(UserRepo userRepo){
        this.userRepo = userRepo;
    }

    @Override
    public void run(String... args) throws Exception {
        User user = new User();
        user.setEmail("farid@gmail.com");
        user.setName("Farid Faisal");
        user.setPassword("1234");

        User ahmed = new User();
        ahmed.setName("Ahmed Mohamed");
        ahmed.setEmail("ahmed@gmail.com");
        ahmed.setPassword("1234");

        User yaser = new User();
        yaser.setName("Yaser Mohamed");
        yaser.setEmail("yaser@gmail.com");
        yaser.setPassword("1234");

        User khalid = new User();
        khalid.setName("Khalid Negm");
        khalid.setEmail("khalid@gmail.com");
        khalid.setPassword("1234");

        User mamdouyaser7 = new User();
        mamdouyaser7.setName("Mamdou7 Younis");
        mamdouyaser7.setEmail("mamdou7@gmail.com");
        mamdouyaser7.setPassword("1234");

        User mazen = new User();
        mazen.setName("Mazen Refaat");
        mazen.setEmail("mazen@gmail.com");
        mazen.setPassword("1234");

        userRepo.save(user);
        userRepo.save(mazen);
        userRepo.save(mamdouyaser7);
        userRepo.save(khalid);
        userRepo.save(yaser);
        userRepo.save(ahmed);

    }
}
