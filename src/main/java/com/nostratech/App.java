package com.nostratech;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * Hello world!
 *
 */
public class App 
{
    public static void main( String[] args )
    {
        ApplicationContext ctx = new ClassPathXmlApplicationContext("application-context.xml");
        Author author = (Author) ctx.getBean("author");
        System.out.println(author.getName());

        Book book = (Book) ctx.getBean("book");
        System.out.println(book.getTitle());
        System.out.println(book.getAuthor().getName());

        EmailService emailService = (EmailService) ctx.getBean("emailService");
        try {
            emailService.sendMail();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
