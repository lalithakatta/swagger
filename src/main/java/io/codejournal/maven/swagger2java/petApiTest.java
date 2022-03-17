package io.codejournal.maven.swagger2java;
import io.codejournal.maven.swagger2java.api.PetApi;
import io.codejournal.maven.swagger2java.model.Category;
import io.codejournal.maven.swagger2java.model.Pet;
import io.codejournal.maven.swagger2java.model.Tag;
import org.springframework.web.client.RestClientException;
import org.testng.annotations.Test;
import java.util.Arrays;
import java.util.List;

import static io.codejournal.maven.swagger2java.model.Pet.StatusEnum.AVAILABLE;
import static java.util.Collections.singletonList;
import static org.junit.Assert.assertEquals;


public class petApiTest {
    private final PetApi api = new PetApi();
    private final long petId = 56484;
    private final String name = "jersey3 java8 pet";

    /**
     * Add a new pet to the store
     *
     * @return
     */
    @Test
    public void addPetTest() {
        Pet body = new Pet(); // Pet | Pet object that needs to be added to the store
        body.setId(petId);
        body.setName(name);
        Category category = new Category();
        category.setId(petId);
        category.setName("jersey3 java8 category");
        body.setCategory(category);
        body.setStatus(Pet.StatusEnum.AVAILABLE);
        //body.setPhotoUrls(new HashSet(Arrays.asList("A", "B", "C")));
        Tag tag = new Tag();
        tag.setId(petId);
        tag.setName("jersey3 java8 tag");
        body.setTags(Arrays.asList(tag));

        try {
            api.addPet(body);

            //get pet by ID
            Pet result = api.getPetById(petId);
            assertEquals(result.getId(), body.getId());
            assertEquals(result.getCategory(), category);
            assertEquals(result.getName(), body.getName());
            assertEquals(result.getPhotoUrls(), body.getPhotoUrls());
            assertEquals(result.getStatus(), body.getStatus());
            assertEquals(result.getTags(), body.getTags());

        } catch (RestClientException e) {
            System.out.println("Exception when calling PetApi->addPet: $e\n");
        }
    }

    /**
     * Find pet by findPetsByStatus
     * <p>
     * Returns a single pet
     *
     * @throws RestClientException if the Api call fails
     */

    @Test
    public void findPetsByStatusTest() {
        try {
            final List<Pet> petsByStatus = api.findPetsByStatus(singletonList(AVAILABLE.getValue()));
            petsByStatus.forEach(System.out::println);
        } catch (RestClientException e) {
            System.out.println("Exception when calling PetApi->findPetsByStatus: $e\n");
        }
    }


    /**
     * Updates a pet in the store with form data
     *
     * @throws RestClientException if the Api call fails
     */
    @Test
    public void updatePetWithFormTest() {
        // update pet
        api.updatePetWithForm(petId, "jersey2 java8 pet 2", "sold");
    }

    /**
     * Find pet by ID
     * <p>
     * Returns a single pet
     *
     * @throws RestClientException if the Api call fails
     */
    @Test
    public void getPetByIdTest() throws RestClientException {
        try {
            Pet result = api.getPetById(petId);
            System.out.println(result);
        } catch (RestClientException e) {
            System.err.println("Exception when calling PetApi#getPetById");
        }
    }


    /**
     * Delete Given Pet
     * <p>
     * Returns a single pet
     *
     * @throws RestClientException if the Api call fails
     */

    @Test
    public void deletePetTest() {
        try {
            api.deletePet(petId, "empty api key");
        } catch (RestClientException e) {
            System.out.println("Exception when calling PetApi->addPet: $e\n");
        }
    }

}

