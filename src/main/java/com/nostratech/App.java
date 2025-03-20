package com.nostratech;

import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * Hello world!
 *
 */
public class App 
{
    public static void main( String[] args )
    {
        //This call via XML
        ApplicationContext ctx = new ClassPathXmlApplicationContext("application-context.xml");
        Author author = (Author) ctx.getBean("author");
        System.out.println(author.getName());

        Book book = (Book) ctx.getBean("book");
        System.out.println(book.getTitle());
        System.out.println(book.getAuthor().getName());


        //This call via XML
        EmailService emailService = (EmailService) ctx.getBean("emailService");
        try {
            System.out.println("Sending email..using XML Configuration.");
            emailService.sendMail();
        } catch (Exception e) {
            e.printStackTrace();
        }

        //This call via Java Config
        ApplicationContext appContext = new AnnotationConfigApplicationContext(AppConfig.class);
        Author author1 = appContext.getBean(Author.class);
        System.out.println(author1.getName());

        Book book1 = appContext.getBean(Book.class);
        System.out.println(book1.getTitle());
        System.out.println(book1.getAuthor().getName());

        //This call via Java Config
        EmailService emailService1 = appContext.getBean(EmailService.class);
        try {
            System.out.println("Sending email..using Java Config or Annotation Configuration.");
            emailService1.sendMail();
        } catch (Exception e) {
            e.printStackTrace();
        }

    }
}
