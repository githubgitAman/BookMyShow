package dev.aman.bookmyshow.Models;

import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
public class Payment extends BaseModel {
    private String refNumber;
    private double amount;
    @Enumerated(EnumType.ORDINAL)
    private PAYMENTSTATUS paymentStatus;
    @Enumerated(EnumType.ORDINAL)
    private PAYMENTPROVIDER paymentProvider;
}
