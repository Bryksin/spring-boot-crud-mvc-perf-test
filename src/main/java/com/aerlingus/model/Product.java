package com.aerlingus.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.*;

@Data
@ToString
@Builder
@Table
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class Product {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long productId;

    @NotNull
    @Size(min = 3, max = 100)
    String productName;

    @Size(min = 0, max = 1000)
    String productDescription;

    @NotNull
    float productPrice;

    int productStock;
}
