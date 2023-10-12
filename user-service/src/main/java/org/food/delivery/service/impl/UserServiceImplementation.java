package org.food.delivery.service.impl;

import jakarta.transaction.Transactional;
import org.food.delivery.config.event.UserEvent;
import org.food.delivery.entity.CARD;
import org.food.delivery.entity.User;
import org.food.delivery.option.IdGenerator;
import org.food.delivery.option.PaymentMethod;
import org.food.delivery.option.Role;
import org.food.delivery.repository.CardRepository;
import org.food.delivery.repository.UserRepository;
import org.food.delivery.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Sinks;

import java.time.Instant;

import java.util.Date;
import java.util.List;

@Service
public class UserServiceImplementation implements UserService {
    private final UserRepository userRepository;
    private final CardRepository cardRepository;

    @Autowired
    private UserEventPublisher userEventPublisher;

    private final Sinks.Many<UserEvent> userSinks = Sinks.many().multicast().onBackpressureBuffer();;




    @Autowired
    public UserServiceImplementation(UserRepository userRepository,
                                     CardRepository cardRepository) {

        this.userRepository = userRepository;
        this.cardRepository = cardRepository;

    }



    @Override
    @Transactional
    public User createUserWithCard(User user) {
       User users = buildUserEntity(user);

        // Create CARD entities and associate them with the user
        List<CARD> cardDetails = user.getCardDetails();
        if (cardDetails != null && !cardDetails.isEmpty()) {
            for (CARD card : cardDetails) {
                saveCard(card);
            }
        }

        users.setCardDetails(cardDetails);

        // Save user along with associated cards
        User savedUser = userRepository.save(users);

        // Save the associated cards
        cardRepository.saveAll(cardDetails);

        // Publish a Kafka event for user creation
        userEventPublisher.publishUserEvent(savedUser, UserEventType.USER_CREATED);


        return savedUser;
    }

    @Override
    @Transactional
    public List<User> getAllUsersWithCardDetails() {
        return userRepository.findAllWithCardDetails();
    }

    private User buildUserEntity(User user) {
        IdGenerator idGenerator = new IdGenerator();
         String _id = (String) idGenerator.generate(null, null);
        user.setUserID(_id);
        List<CARD> cardDetails;
        // Create CARD entities and set their IDs
        cardDetails = user.getCardDetails();
        if (cardDetails != null && !cardDetails.isEmpty()) {
            for (CARD card : cardDetails) {
                saveCard(card);

            }
        }
        return User.builder()
                .userID(user.getUserID())
                .firstname(user.getFirstname())
                .lastname(user.getLastname())
                .roles(Role.Administration)
                .password(user.getPassword())
                .confirmPassword(user.getConfirmPassword())
                .paymentMethod(PaymentMethod.CARD)
                .cardDetails(cardDetails)
                .phone(user.getPhone())
                .createdAt(Date.from(Instant.now()))
                .build();


    }

    public void saveCard(CARD card) {
        IdGenerator idGenerator = new IdGenerator();
        card.setId((String) idGenerator.generate(null, null));
        cardRepository.save(card);
    }

}
