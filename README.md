# Spring Email Configuration Study

This project demonstrates different approaches to configure email services in Spring using XML configuration.

## Key Components

### 1. EmailService Configuration
We have two different approaches to configure email services:

#### Approach 1: Direct EmailService Bean
```xml
<bean id="emailService" class="com.nostratech.EmailService">
    <constructor-arg value="sandbox.mailtrap.io" />
    <constructor-arg value="25" />
    <constructor-arg value="d1268cdd35952e" />
    <constructor-arg value="34ff9375651117" />
</bean>
```
This approach uses constructor injection to directly configure the EmailService.

#### Approach 2: Mail Session with Method Injection
```xml
<bean id="mailSession" class="jakarta.mail.Session" factory-method="getInstance">
    <!-- Mail Properties -->
    <constructor-arg>
        <props>
            <prop key="mail.smtp.auth">true</prop>
            <prop key="mail.smtp.starttls.enable">true</prop>
            <prop key="mail.smtp.host">sandbox.mailtrap.io</prop>
            <prop key="mail.smtp.port">25</prop>
            <prop key="mail.smtp.ssl.trust">sandbox.mailtrap.io</prop>
        </props>
    </constructor-arg>
    <!-- Authenticator Configuration -->
    <constructor-arg>
        <bean class="jakarta.mail.Authenticator">
            <lookup-method name="getPasswordAuthentication" bean="passwordAuthentication" />
        </bean>
    </constructor-arg>
</bean>
```

### 2. Method Injection Example
The project demonstrates the use of `lookup-method` injection, which is a special type of method injection in Spring:

- Used to override/implement abstract methods
- Returns a bean when the method is called
- Particularly useful for authentication scenarios
- More flexible than hardcoding values in Java classes

Example:
```xml
<lookup-method name="getPasswordAuthentication" bean="passwordAuthentication" />
```
This is equivalent to:
```java
new Authenticator() {
    @Override
    protected PasswordAuthentication getPasswordAuthentication() {
        return passwordAuthenticationBean;
    }
}
```

### 3. Authentication Configuration
```xml
<bean id="passwordAuthentication" class="jakarta.mail.PasswordAuthentication">
    <constructor-arg value="d1268cdd35952e" />
    <constructor-arg value="34ff9375651117" />
</bean>
```

## Benefits of This Approach

1. **Separation of Concerns**: Email configuration is separated from business logic
2. **Flexibility**: Easy to change email settings without modifying code
3. **Environment Independence**: Can use different configurations for different environments
4. **Security**: Credentials can be externalized and managed separately
5. **Maintainability**: Central location for all email-related configurations

## Notes
- The project uses Mailtrap as SMTP server for testing
- Both constructor injection and method injection are demonstrated
- XML configuration allows for easy modification of settings 