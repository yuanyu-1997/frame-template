package com.example.employee;

import org.reactivestreams.Subscriber;
import org.reactivestreams.Subscription;
import org.springframework.web.reactive.function.client.WebClient;

/**
 * @author baker.yuan
 */
public class EmployeeWebClient {

    WebClient client = WebClient.create("http://localhost:6000");


    ///**
    // * 测试1
    // */
    //public void consume() {
    //    Mono<Employee> employeeMono = client.get().uri("/employees/{id}", "1").retrieve().bodyToMono(Employee.class);
    //    employeeMono.subscribe(System.out::println);
    //}


    ///**
    // * 测试2
    // */
    //public void consume() {
    //    client.get().uri("/employees").retrieve().bodyToFlux(Employee.class).map(this::doSomeSlowWork)
    //            .subscribe(employee -> {
    //                System.out.println("Client subscribes: " + employee);
    //            });
    //}

    /**
     * 测试3
     */
    public void consume() {
        client.get().uri("/employees").retrieve().bodyToFlux(Employee.class).map(this::doSomeSlowWork)
                .subscribe(new Subscriber<Employee>() {
                    private Subscription subscription;
                    private Integer count = 0;

                    @Override
                    public void onNext(Employee t) {
                        count++;
                        if (count >= 2) {
                            // 每次请求两个，我处理完成后再拿两个
                            count = 0;
                            subscription.request(2);
                            System.out.println(System.currentTimeMillis() + " => Client requested 2 ");

                        }
                        System.out.println(System.currentTimeMillis() + " => Client subscribes: " + t);
                    }

                    @Override
                    public void onSubscribe(Subscription subscription) {
                        this.subscription = subscription;
                        subscription.request(2);
                        System.out.println(System.currentTimeMillis() + " => Client requested 2 ");
                    }

                    @Override
                    public void onError(Throwable t) {
                    }

                    @Override
                    public void onComplete() {
                        System.out.println(System.currentTimeMillis() + " => Client completed");
                    }
                });
    }

    private Employee doSomeSlowWork(Employee employee) {
        try {
            Thread.sleep(90);
        } catch (InterruptedException ignored) {
        }
        return employee;
    }
}