package ru.malichenko.market;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.PropertySource;

// Домашнее задание:
// 1. Добавить выгрузку всех продуктов через SOAP
// 2. * Добавить выгрузку заказов пользователя по его имени (защиту не ставим пока)

// Планы на следующие занятия:
// - Вернуться к вопросу об изменении цены товара перед оформлением заказа

@SpringBootApplication
@PropertySource("classpath:secured.properties")
public class MarketApplication {

	public static void main(String[] args) {
		SpringApplication.run(MarketApplication.class, args);
	}

}
