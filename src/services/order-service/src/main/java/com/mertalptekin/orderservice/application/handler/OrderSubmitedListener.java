package com.mertalptekin.orderservice.application.handler;


import com.mertalptekin.orderservice.application.event.OrderSummited;
import com.mertalptekin.orderservice.respository.ProductRepository;
import com.mertalptekin.orderservice.service.entity.Product;
import org.springframework.stereotype.Component;
import org.springframework.transaction.event.TransactionPhase;
import org.springframework.transaction.event.TransactionalEventListener;

import java.util.Optional;

@Component
public class OrderSubmitedListener {

    // order submit olduktan sonra order aggregate den çık, product aggregate girip, ürünün stoğunu güncelle.
    private final ProductRepository productRepository;

   public OrderSubmitedListener(final ProductRepository productRepository) {
       this.productRepository = productRepository;
   }

    // Not: TransactionalEventListener yapısı after commit çalışır. Eğer @Transactional anatasyonu kullanılarak kayıt atılıyorsa. Kayıt commit sonrası tetiklenir. Eğer kayıt başarısız ise rollback dönerse buradaki event tetiklenemez.
    // Senaryo genel olarak veri kaynağına yazıldıktan sonra event fırlat şeklinde POST COMMIT genelde olabilir.

    // Not: Domain Eventler bir bütün halinde single transaction scope da verikaynağına yazıldığından Pre Save çalışır
    // Ama-> bazen uygulama içerisinde POST SAVe çalışmamız gerek durumlar olabilir. Integration Event -> Kafka göndermek için verinin commit edilmesi lazım. @TransactionalEventListener(phase = TransactionPhase.AFTER_COMMIT)
    @TransactionalEventListener(phase = TransactionPhase.BEFORE_COMMIT)
    //@EventListener
    public void orderSubmitted(OrderSummited event) throws InterruptedException {
        // buradaki logic devam eder.
        // artık order submit olduktan sonraki süreç
        System.out.println("Order submitted");

        // kodu simüle ettiğimiz kısım
        Optional<Product> p = this.productRepository.findById(1);

        if(p.isEmpty()){
            throw new RuntimeException("Product not found");
        } else {
           Product entity =  p.get();
           entity.setStock(entity.getStock() - 5); // 5 adet sipariş verilen üründen düş. // event.quantity()
            productRepository.save(entity);
        }

        // Not: Eğer transaction Rollback olursa ne order oluşucak nede product güncellenecek.
        // Not: Microservices olarak Product ile Order Service ayrıldığında sadece EventListener kodunu güncellemek yeterli olucak.
        // Not: Bu kısımda ise Kafkaya streamBridge -> ile publish edicez.

    }

}
