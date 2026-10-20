package org.example.model;

import lombok.EqualsAndHashCode;

import java.util.UUID;

@EqualsAndHashCode
public final class AnimalId {
    private final String value;
    public AnimalId() {
        this.value = UUID.randomUUID().toString();
    }

    public AnimalId(String value) {
        this.value = value;
    }

    @Override
    public String toString() {
        return value;
    }
}
