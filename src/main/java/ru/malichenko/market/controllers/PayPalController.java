package ru.malichenko.market.controllers;

import com.paypal.api.payments.Links;
import com.paypal.api.payments.Payment;
import com.paypal.base.rest.PayPalRESTException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import ru.malichenko.market.entities.OrderEntity;
import ru.malichenko.market.services.OrderService;
import ru.malichenko.market.services.PayPalService;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@Controller
@RequestMapping("/pay")
public class PayPalController {

    private OrderService orderService;

    @Autowired
    public void setOrderService(OrderService orderService){
        this.orderService = orderService;
    }

    PayPalService service;

    @Autowired
    public void setService(PayPalService service) {
        this.service = service;
    }

    public static final String SUCCESS_URL = "success/";
    public static final String CANCEL_URL = "cancel";
//
//    @GetMapping("/")
//    public String home() {
//        return "home";
//    }
    @GetMapping("/buy/{orderId}")
    public String buy(Model model, @PathVariable(name="orderId") Long orderId){
//    @PostMapping("/pay")
//    public String payment(@ModelAttribute("order") OrderEntity order) {
        try {
            OrderEntity order = orderService.findById(orderId);
//todo проверить что заказ уже оплачен или тчо заказ существует?

            Payment payment = service.createPayment(
                    (double) order.getPrice(),
                    "RUB",
                    "paypal",
                    "sale",
                    "Покупка в Spring Market",
                    "http://localhost:8189/market/pay/" + CANCEL_URL,
                    "http://localhost:8189/market/pay/" + SUCCESS_URL+ order.getId());
            for(Links link:payment.getLinks()) {
                if(link.getRel().equals("approval_url")) {
                    return "redirect:"+link.getHref();
                }
            }
        } catch (PayPalRESTException e) {
            e.printStackTrace();
        }
        model.addAttribute("message","Вы сюда не должныы были попасть...");
        return "/cancel";
//        return "redirect:/";
    }

    @GetMapping("/cancel")//, value = CANCEL_URL name = "/cancel"
    public String cancelPay(Model model) {
        model.addAttribute("message", "Оплата заказа была отменена");
        return "/cancel";
    }

    @GetMapping("/success/{orderId}")//, value = SUCCESS_URL
    public String successPay(
//            @RequestParam("paymentId") String paymentId,
//            @RequestParam("PayerID") String payerId,

            HttpServletRequest request,
            HttpServletResponse response,
            Model model,
            @PathVariable(name="orderId") Long orderId
    ) {
        try {
            String paymentId = request.getParameter("paymentId");
            String payerId = request.getParameter("PayerID");

            if (paymentId == null || paymentId.isEmpty() || payerId == null || payerId.isEmpty()){
                model.addAttribute("message", "Что-то пошло не так при формировании заказа");
                return "cancel";
            }
            Payment payment = service.executePayment(paymentId, payerId);
            System.out.println(payment.toJSON());
            if (payment.getState().equals("approved")) {
                model.addAttribute("message", "Ваш заказ №" +orderId + " сформирован и оплачен");
            }else{
                model.addAttribute("message", "Что-то пошло не так при формировании заказа");
                return "cancel";
            }
        } catch (PayPalRESTException e) {
            System.out.println(e.getMessage());
        }
        return "/success";
    }
}
