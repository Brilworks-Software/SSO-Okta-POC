package com.okta.Okta.Demo.service;

import com.okta.Okta.Demo.dto.Technology;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class TechService {

    public List<Technology> getTechnologies() {
        List<Technology> technologies = new ArrayList<>();
        technologies.add(Technology.builder().id("1").technology("Java").description("Explore the power of Java programming language with our comprehensive course. From the basics of syntax to advanced features, learn to develop efficient and scalable applications. Dive into the world of object-oriented programming and gain the skills needed to build robust software solutions. Whether you're a beginner or looking to enhance your Java expertise, this course provides a solid foundation for success in the dynamic field of software development.").build());
        technologies.add(Technology.builder().id("2").technology("React").description("Dive into the world of React, the JavaScript library for building user interfaces. Our course empowers you to harness the flexibility and efficiency of React to create interactive and dynamic web applications. From component-based architecture to state management, you'll gain hands-on experience in developing modern, responsive UIs. Whether you're a frontend developer seeking to upgrade your skills or a newcomer to web development, this course is your gateway to mastering React and building cutting-edge web experiences.").build());
        technologies.add(Technology.builder().id("3").technology("Angular").description("Master Angular and revolutionize your web development skills. Dive into component-based architecture, reactive programming, and build dynamic single-page applications effortlessly. Whether you're a seasoned developer or new to the field, unlock the potential of Angular for creating modern, responsive web applications.").build());
        technologies.add(Technology.builder().id("4").technology("Python").description("Unlock the versatility of Python programming with our comprehensive course. From fundamental syntax to advanced concepts, dive into the world of Python and unleash its power for web development, data science, automation, and more. Whether you're a beginner or looking to enhance your coding skills, this course provides a solid foundation for mastering one of the most popular and versatile programming languages in the world.").build());
        return technologies;
    }

}
