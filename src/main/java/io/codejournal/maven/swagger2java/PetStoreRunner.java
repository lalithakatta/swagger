package io.codejournal.maven.swagger2java;

import static io.codejournal.maven.swagger2java.model.Pet.StatusEnum.AVAILABLE;
import static java.util.Collections.singletonList;


import java.util.List;

import io.codejournal.maven.swagger2java.api.PetApi;
import io.codejournal.maven.swagger2java.model.Pet;
import org.testng.annotations.Test;

public class PetStoreRunner {

    @Test
    public static void main(final String[] args) {
        final PetApi petApi = new PetApi();
        final List<Pet> petsByStatus = petApi.findPetsByStatus(singletonList(AVAILABLE.getValue()));

        petsByStatus.forEach(System.out::println);
    }
}