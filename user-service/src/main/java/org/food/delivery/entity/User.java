package org.food.delivery.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.food.delivery.option.PaymentMethod;
import org.food.delivery.option.Role;
import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.NotFound;
import org.hibernate.annotations.NotFoundAction;

import java.util.Date;
import java.util.LinkedList;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity
@Table(name = "USER_TBL")
public class User {
    @Id
    @GeneratedValue(generator = "generator")
    @GenericGenerator(name = "generator", strategy = "org.food.delivery.option.IdGenerator")
    @Column( unique = true, nullable = false, length = 36)
    private String userID;
    @NotNull
    private String firstname;
    @NotNull
    private String lastname;
    @NotNull
    private String phone;
    @NotNull
    private String password;
    @NotNull
    private String confirmPassword;
    @Enumerated(EnumType.STRING)
    private Role roles;
    private PaymentMethod paymentMethod;
    @OneToMany(
            fetch = FetchType.EAGER,
            targetEntity = CARD.class,
            cascade = CascadeType.ALL
    )
    @JoinColumn(name = "user_id")
    private List<CARD> cardDetails;
    private Date createdAt;
}
