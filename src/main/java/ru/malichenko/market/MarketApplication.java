package ru.malichenko.market;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

// Домашнее задание:
// 1. На странице корзины делаем кнопку оформить заказ, нас перебрасывает на страницу,
// где показываются товары из корзины, без возможности их менять
// 2. На странице оформления заказа указывается имя клиента, его номер телефона, и адрес
// доставки, по нажатию на кнопку оформить, заказ должен быть сохранен в базу данных


@SpringBootApplication
public class MarketApplication {

	public static void main(String[] args) {
		SpringApplication.run(MarketApplication.class, args);
	}

}
