     package com.example.demo.entity;

     import javax.persistence.*;

     @Entity
     public class Task {
         @Id
         @GeneratedValue(strategy = GenerationType.IDENTITY)
         private Long id;

         private String title;
         private String description;

         @ManyToOne
         @JoinColumn(name = "user_id")
         private User user;

         // getters and setters
     }
