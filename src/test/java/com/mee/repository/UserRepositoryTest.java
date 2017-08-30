package com.mee.repository;

import com.mee.entity.User;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;


@RunWith(SpringRunner.class)
@SpringBootTest
public class UserRepositoryTest {

    @Autowired
    private UserRepository userRepository;

    @Test
    public void save() throws Exception {
        User user = new User();
        user.setId("3");
        user.setFirstName("Igar");
        user.setPassword("IgorekVershik");
        user.setLastName("Vershall");
        user.setFacebookId("g8ffg75blar654nza75");
        user.setPhotoUrl("http://gordonua.com/img/article/1880/74_tn.jpg");
        userRepository.save(user);
    }

    @Test
    public void findUserByUserName() {
        User user = userRepository.findByFirstName("user");
        System.out.println(user);
    }

    @Test
    public void update() {
        User user = new User();
        user.setId("21");
        user.setFirstName("some_other_name");
        user.setPassword("fffxxxx");
        userRepository.save(user);
    }

    @Test
    public void deleteById() {
        userRepository.delete("11");
    }

}
