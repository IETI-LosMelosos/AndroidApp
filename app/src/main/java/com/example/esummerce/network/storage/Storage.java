package com.example.esummerce.network.storage;

import java.util.List;

public interface Storage {

    void saveToken(String token);

    String getToken();

    void saveCart(String product);

    String getCart();

    void clearCart();
}
