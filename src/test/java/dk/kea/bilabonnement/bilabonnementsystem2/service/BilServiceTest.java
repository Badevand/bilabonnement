

package dk.kea.bilabonnement.bilabonnementsystem2.service;

import dk.kea.bilabonnement.bilabonnementsystem2.model.Bil;
import dk.kea.bilabonnement.bilabonnementsystem2.repository.BilRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

public class BilServiceTest {

    @Mock
    private BilRepository bilRepository;

    @InjectMocks
    private BilService bilService;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    // happy
    @Test
    public void testGetAlleBiler_ReturnsListOfCars() {

        // arrange
        List<Bil> ledigeBiler = new ArrayList<>();
        ledigeBiler.add(new Bil("ABC123", "12345", "Toyota", "Camry",
                "Standard", 200000, 50000, 120, "ledig", 0));

        List<Bil> udlejedeBiler = new ArrayList<>();
        udlejedeBiler.add(new Bil("XYZ789", "67890", "Honda", "Civic",
                "Premium", 180000, 45000, 110, "udlejet", 0));


        when(bilRepository.findAllLedigeBiler()).thenReturn(ledigeBiler);
        when(bilRepository.findUdlejedeBiler()).thenReturn(udlejedeBiler);
        when(bilRepository.findBilerMedSkadeStatus()).thenReturn(new ArrayList<>());

        // act
        List<Bil> result = bilService.findAll();

        // assert
        assertNotNull(result);
        assertEquals(2, result.size());


        verify(bilRepository).findAllLedigeBiler();
        verify(bilRepository).findUdlejedeBiler();
        verify(bilRepository).findBilerMedSkadeStatus();
    }

    // exception
    @Test
    public void testGetBilByVognnummer_ThrowsExceptionWhenVognnummerIsNull() {

        // arrange
        when(bilRepository.findByVognnummer(null)).thenThrow(new IllegalArgumentException("Vognnummer kan ikke være null"));


        // act & assert (test at exception kastes)
        IllegalArgumentException exception = assertThrows(IllegalArgumentException.class, () -> {
            bilService.getBilByVognnummer(null);
        });

        assertEquals("Vognnummer kan ikke være null", exception.getMessage());

        verify(bilRepository).findByVognnummer(null);
    }

    // inputvalidering
    @Test
    public void testUpdateBilStatus_ValidInput() {

        // arrange
        String vognnummer = "ABC123";
        String nyStatus = "udlejet";

        // act
        bilService.updateBilStatus(vognnummer, nyStatus);

        // assert
        verify(bilRepository).updateBilStatus(vognnummer, nyStatus);
    }
}