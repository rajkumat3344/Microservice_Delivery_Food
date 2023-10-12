package org.food.delivery.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.food.delivery.option.IdGenerator;
import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.NotFound;
import org.hibernate.annotations.NotFoundAction;


import java.util.Date;

@Data
@AllArgsConstructor
@Builder
@Entity
@Table(name = "CARD_TBL")
@NoArgsConstructor
public class CARD {
    @Id
    @GeneratedValue(generator = "generator")
    @GenericGenerator(name = "generator", strategy = "org.food.delivery.option.IdGenerator")
    @Column(unique = true, nullable = false, length = 36)
    private String id;
    private String cardNumber;
    private String cardholderName;
    private Date expirationDate;
    private Integer cvv;
    private String securityCode;

    @JsonIgnore
    private String user_Id;

    @Override
    public String toString() {
        return "CARD{" +
                "id='" + id + '\'' +
                ", cardNumber='" + cardNumber + '\'' +
                ", cardholderName='" + cardholderName + '\'' +
                ", expirationDate=" + expirationDate +
                ", cvv=" + cvv +
                ", securityCode='" + securityCode + '\'' +
                '}';
    }
}
