package org.example.guestbook2024.Repository;

import org.example.guestbook2024.Guestbook2024Application;
import org.example.guestbook2024.entity.GuestBook;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;
import org.springframework.data.querydsl.QuerydslPredicateExecutor;

@SpringBootApplication
@EnableJpaAuditing

public interface GuestbookRepository extends JpaRepository<GuestBook , Long>, QuerydslPredicateExecutor<GuestBook> {

    public static void main(String[] args) {
        SpringApplication.run(Guestbook2024Application.class ,args);


    }

    }

