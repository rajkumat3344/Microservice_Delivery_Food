package org.food.delivery.config;

import jakarta.transaction.Transactional;
import org.food.delivery.entity.CARD;
import org.food.delivery.entity.User;
import org.food.delivery.option.IdGenerator;
import org.food.delivery.option.PaymentMethod;
import org.food.delivery.option.Role;
import org.food.delivery.repository.CardRepository;
import org.food.delivery.repository.UserRepository;
import org.food.delivery.service.impl.UserEventPublisher;
import org.food.delivery.service.impl.UserEventType;
import org.springframework.beans.factory.annotation.Autowired;

import java.time.Instant;
import java.util.Date;
import java.util.List;
import java.util.function.Consumer;

public class UserStatusUpdateHandler {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private CardRepository cardRepository;
    @Autowired
    private UserEventPublisher userEventPublisher;

    @Transactional
    public void updateUser(String userId, Consumer<User> consumer) {
        userRepository.findUserByUserID(userId).ifPresent(consumer.andThen(this::updateUser));
    }

    private void updateUser(User user) {
        // Determine the event type based on the update
        UserEventType eventType = UserEventType.USER_CREATED;

        // Publish a user event based on the update
        userEventPublisher.publishUserEvent(buildUserEntity(user), eventType);
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
