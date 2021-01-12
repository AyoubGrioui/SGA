DROP DATABASE IF EXISTS Inheritance;
CREATE DATABASE Inheritance;
USE Inheritance;

CREATE TABLE T_PaypalPayments (
    idPayment           int          PRIMARY KEY,
    amount              double       NOT NULL,
    paymentDate         datetime     NOT NULL DEFAULT CURRENT_TIMESTAMP,
    accountNumber       varchar(30)
);

INSERT INTO T_PaypalPayments VALUES ( 1, 1295, now(), 'A fake paypal account' );

SELECT * FROM T_PaypalPayments;

CREATE TABLE T_CreditCardPayments (
    idPayment           int          PRIMARY KEY,
    amount              float        NOT NULL,
    paymentDate         datetime     NOT NULL DEFAULT CURRENT_TIMESTAMP,
    cardNumber          char(24),
    expirationDate      varchar(5)
);

INSERT INTO T_CreditCardPayments VALUES ( 2, 245, now(), '1234 5678 9012 3456', '06/19' );

SELECT * FROM T_CreditCardPayments;