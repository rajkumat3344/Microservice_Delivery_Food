package org.food.delivery.option;

import org.hibernate.HibernateException;
import org.hibernate.engine.spi.SharedSessionContractImplementor;
import org.hibernate.id.IdentifierGenerator;

import java.io.Serializable;
import java.util.UUID;

public class IdGenerator implements IdentifierGenerator {

    @Override
    public Serializable generate(SharedSessionContractImplementor session, Object object) throws HibernateException {
        // Generate a random UUID
        UUID uuid = UUID.randomUUID();

        // Convert UUID to a hexadecimal string
        String hex = uuid.toString().replace("-", "");

        // Add 'a' to 'f' to the hexadecimal string
        StringBuilder alphanumericUUID = new StringBuilder();
        for (int i = 0; i < hex.length(); i++) {
            char c = hex.charAt(i);
            if (Character.isDigit(c)) {
                alphanumericUUID.append(c);
            } else {
                // Convert the character to lowercase and add to the alphanumericUUID string
                alphanumericUUID.append(Character.toLowerCase(c));
            }
        }

        // Return the alphanumeric UUID
        return alphanumericUUID.toString();
    }
}
