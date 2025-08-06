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

    // HAPPY FLOW TEST
    @Test
    public void testGetAlleBiler_ReturnsListOfCars() {
        // Arrange (forbered test data)
        List<Bil> ledigeBiler = new ArrayList<>();
        ledigeBiler.add(new Bil("ABC123", "12345", "Toyota", "Camry", "Standard", 200000, 50000, 120, "ledig", 0));

        List<Bil> udlejedeBiler = new ArrayList<>();
        udlejedeBiler.add(new Bil("XYZ789", "67890", "Honda", "Civic", "Premium", 180000, 45000, 110, "udlejet", 0));

        // Mock repository responses
        when(bilRepository.findAllLedigeBiler()).thenReturn(ledigeBiler);
        when(bilRepository.findUdlejedeBiler()).thenReturn(udlejedeBiler);
        when(bilRepository.findBilerMedSkadeStatus()).thenReturn(new ArrayList<>());

        // Act (udfør test)
        List<Bil> result = bilService.getAlleBiler();

        // Assert (tjek resultater)
        assertNotNull(result);
        assertEquals(2, result.size()); // 1 ledig + 1 udlejet + 0 skadede = 2

        // Verify at repository metoder blev kaldt
        verify(bilRepository).findAllLedigeBiler();
        verify(bilRepository).findUdlejedeBiler();
        verify(bilRepository).findBilerMedSkadeStatus();
    }

    // EXCEPTION FLOW TEST + INPUT VALIDERING
    @Test
    public void testGetBilByVognnummer_ThrowsExceptionWhenVognnummerIsNull() {
        // Arrange
        when(bilRepository.findByVognnummer(null)).thenThrow(new IllegalArgumentException("Vognnummer kan ikke være null"));

        // Act & Assert (test at exception kastes)
        IllegalArgumentException exception = assertThrows(IllegalArgumentException.class, () -> {
            bilService.getBilByVognnummer(null);
        });

        assertEquals("Vognnummer kan ikke være null", exception.getMessage());
        verify(bilRepository).findByVognnummer(null);
    }

    // BONUS: Test af input validering
    @Test
    public void testUpdateBilStatus_ValidInput() {
        // Arrange
        String vognnummer = "ABC123";
        String nyStatus = "udlejet";

        // Act
        bilService.updateBilStatus(vognnummer, nyStatus);

        // Assert
        verify(bilRepository).updateBilStatus(vognnummer, nyStatus);
    }
}