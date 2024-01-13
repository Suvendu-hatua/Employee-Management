package com.springbootmainfiles;

import java.util.List;
import java.util.Map;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.ApplicationContext;
import org.springframework.context.MessageSource;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.support.ReloadableResourceBundleMessageSource;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.validation.beanvalidation.LocalValidatorFactoryBean;

import com.springbootbeanfiles.Bank;
import com.springbootbeanfiles.ConstructorInjectionDemo;
import com.springbootbeanfiles.FirstSpringBootBean;
import com.springbootbeanfiles.PropertiesBean;

/*
 * ***If you added @SpringBootApplication annotation to the class, you do not need to add
the @EnableAutoConfiguration, @ComponentScan and @SpringBootConfiguration
annotation. The @SpringBootApplication annotation includes all other annotations.
 */
@SpringBootApplication
@ComponentScan(basePackages = { "com" })

/*
 * Spring Data Jpa provides EnableJpaRepositories annotation which is used to scan the packages for 
 * configuration and repository class for Spring Data JPA.
 */
//SPRING DATA JPA+HIBERNATE
@EnableJpaRepositories("com.myrepository")//scanning all the repositories under repository package
@EntityScan("com.springbootbeanfiles")//scanning all the hibernate entities classes(@Table,@Entity)
public class SpringBootEntryDemo {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		/*
		 * The ApplicationContext is the central interface within a Spring application
		 * that is used for providing configuration information to the application.
		 * 
		 * ApplicationContext is a corner stone of a Spring Boot application. It
		 * represents the Spring IoC container and is responsible for instantiating,
		 * configuring, and assembling the beans. The container gets its instructions on
		 * what objects to instantiate, configure, and assemble by reading configuration
		 * metadata. The configuration metadata is represented in XML, Java annotations,
		 * or Java code.
		 */
		ApplicationContext con1 = SpringApplication.run(SpringBootEntryDemo.class, args);
		FirstSpringBootBean b1 = con1.getBean(FirstSpringBootBean.class);// NO XML FILE, NO BEAN ID!
		b1.display();

		// from application.properties file
		PropertiesBean object2 = con1.getBean(PropertiesBean.class);// NO XML FILE, NO BEAN ID!
		System.out.println("Name:: " + object2.getName());// bean getter method invoked

		Bank b11 = con1.getBean(Bank.class);
		System.out.println(b11.toString());
		b11.getV1().category();// mybike
		b11.getV1().parts();// mybike

		List<String> obj = b11.getTechnologies();
		for (String a : obj) {
			System.out.println(a);
		}
		Map<String, Integer> obj2 = b11.getRatings();
		System.out.println("Ratings::(Key&Value)::");

		for (Map.Entry<String, Integer> uobj : obj2.entrySet()) {
			System.out.println(uobj.getKey() + " " + uobj.getValue());
		}

		System.out.println("*********************************");

		// Constructor Injection
		ConstructorInjectionDemo c1 = con1.getBean(ConstructorInjectionDemo.class);
		System.out.println(c1.toString());

		List<String> obt = c1.technologies123;
		for (String a : obt) {
			System.out.println(a);
		}
		Map<String, Integer> obr = c1.ratings;
		System.out.println("Ratings::(Key&Value)::");

		for (Map.Entry<String, Integer> uobj : obr.entrySet()) {
			System.out.println(uobj.getKey() + " " + uobj.getValue());
		}

	}
	
	@Bean
	  public MessageSource messageSource() 
	 {
		 //ReloadableResourceBundleMessageSource::: resolve messages or properties file
		        ReloadableResourceBundleMessageSource messageSource1 = new ReloadableResourceBundleMessageSource();
	        messageSource1.setBasenames("classpath:myvalidationmessages","classpath:validationmessages2");//.properties file
	        messageSource1.setDefaultEncoding("UTF-8");//optional
	        return messageSource1;
	    }
	 /*
	  * To use custom name messages in a properties file like we need to define a 
	  * LocalValidatorFactoryBean and register the messageSource
	  */
	  @Bean
	    public LocalValidatorFactoryBean validator() 
	  {
	        LocalValidatorFactoryBean bean = new LocalValidatorFactoryBean();//search the propertiesfile which contains validation error messages!
	        bean.setValidationMessageSource(messageSource());//calling messageSource()
	        return bean;
	    }

}
