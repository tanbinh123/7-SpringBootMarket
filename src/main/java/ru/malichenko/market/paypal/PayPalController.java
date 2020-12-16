package ru.malichenko.market.paypal;

import com.paypal.api.payments.*;
import com.paypal.base.rest.APIContext;
import com.paypal.base.rest.PayPalRESTException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import ru.malichenko.market.entities.OrderEntity;
import ru.malichenko.market.services.OrderService;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

@Controller
@Configuration
@RequestMapping("/paypal")
public class PayPalController {
    @Value("${paypal.client.id}")
    private String clientId;
    @Value("${paypal.client.secret}")
    private String clientSecret;
    @Value("${paypal.mode}")
    private String mode;

    private APIContext apiContext = new APIContext(clientId, clientSecret, mode);

    private OrderService orderService;

    @Autowired
    public void setOrderService(OrderService orderService){
        this.orderService = orderService;
    }

    @GetMapping("/buy/{orderId}")
    public String buy(Model model, @PathVariable(name="orderId") Long orderId){
        try {
            OrderEntity orderEntity = orderService.findById(orderId);

            Payer payer =new Payer();
            payer.setPaymentMethod("paypal");
            RedirectUrls redirectUrls = new RedirectUrls();
            redirectUrls.setCancelUrl("http://localhost:8189/market/paypal/cancel");
            redirectUrls.setReturnUrl("http://localhost:8189/market/paypal/success" + orderEntity.getId());

            Amount amount = new Amount();
            amount.setCurrency("RUB");
            amount.setTotal(String.valueOf(orderEntity.getPrice()));

            Transaction transaction = new Transaction();
            transaction.setAmount(amount);
            transaction.setDescription("Покупка в Spring Market");

            List<Transaction> transactions = new ArrayList<>();
            transactions.add(transaction);

            Payment payment = new Payment();
            payment.setPayer(payer);
            payment.setRedirectUrls(redirectUrls);
            payment.setTransactions(transactions);
            payment.setIntent("sale");

            Payment doPayment = payment.create(apiContext);
            Iterator<Links> links = doPayment.getLinks().iterator();
            while (links.hasNext()){
                Links link = links.next();
                if (link.getRel().equalsIgnoreCase("approval_url")){
                    return "redirect:" +link.getHref();
                }
            }
        } catch (PayPalRESTException e) {
            e.printStackTrace();
        }
        model.addAttribute("message","Вы сюда не должныы были попасть...");
        return "paypal-result";
    }

    @GetMapping("/success/{orderId}")
    public String success(HttpServletRequest request, HttpServletResponse response, Model model, @PathVariable(name="orderId") Long orderId){
        try {
            String paymentId = request.getParameter("paymentId");
            String payerId = request.getParameter("PayerID");

            if (paymentId == null || paymentId.isEmpty() || payerId == null || payerId.isEmpty()){
                return "redirect:/";
            }

            Payment payment = new Payment();
            payment.setId(paymentId);

            PaymentExecution paymentExecution = new PaymentExecution();
            paymentExecution.setPayerId(payerId);

            Payment executedPayment = payment.execute(apiContext, paymentExecution);

            if (executedPayment.getState().equals("approved")){
                model.addAttribute("message", "Ваш заказ №" +orderId + " сформирован и оплачен");
            }else {
                model.addAttribute("message", "Что-то пошло не так при формировании заказа");
            }
        } catch (PayPalRESTException e) {
            e.printStackTrace();
        }
        return "paypal-result";
    }

    @GetMapping("/cancel")
    public String cancel (Model model){
        model.addAttribute("message", "Оплата заказа была отменена");
        return "paypal-result";
    }
}
