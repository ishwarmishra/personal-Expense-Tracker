

import com.personalfinancetracker.controller.IncomeController;
import com.personalfinancetracker.model.IncomeEntity;
import com.personalfinancetracker.repository.IncomeRepository;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.InputStream;
import java.io.PrintStream;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.Collections;
import java.util.List;

public class IncomeControllerTest {
    private IncomeRepository incomeRepository;
    private ByteArrayOutputStream outputStream;

    @Before
    public void setUp() {
        incomeRepository = new IncomeRepository();
        outputStream = new ByteArrayOutputStream();
        System.setOut(new PrintStream(outputStream));
    }

    @Test
    public void testSaveData() {
        String input = "Salary\n1000\n01/01/2023\n";
        InputStream inputStream = new ByteArrayInputStream(input.getBytes());
        System.setIn(inputStream);

        // Call the method
        IncomeController.saveData(incomeRepository);

        String expectedOutput = "Income added successfully.";
        Assert.assertEquals(expectedOutput, outputStream.toString().trim());

        // Verify the data in the repository
        List<IncomeEntity> incomeEntities = incomeRepository.findAll();
        Assert.assertEquals(1, incomeEntities.size());

        IncomeEntity savedIncome = incomeEntities.get(0);
        Assert.assertEquals("Salary", savedIncome.getIncome());
        Assert.assertEquals(new BigDecimal("1000"), savedIncome.getAmount());
        Assert.assertEquals(LocalDate.of(2023, 1, 1), savedIncome.getDate());
    }


    
    @Test
    public void testDeleteData() {
        // Prepare test data
        IncomeEntity incomeEntity = new IncomeEntity(1, "Salary", new BigDecimal("1000"), LocalDate.now());
        incomeRepository.add(incomeEntity);

        // Prepare input
        String input = "1\n";
        InputStream inputStream = new ByteArrayInputStream(input.getBytes());
        System.setIn(inputStream);

        // Call the method
        IncomeController.deleteData(incomeRepository);

        // Verify the output
        String expectedOutput = "Income with ID 1 deleted successfully.";
        Assert.assertEquals(expectedOutput, outputStream.toString().trim());

        // Verify the data in the repository
        List<IncomeEntity> incomeEntities = incomeRepository.findAll();
        Assert.assertEquals(Collections.emptyList(), incomeEntities);
    }
    
    @Test
public void testUpdateData() {
    // Prepare test data
    IncomeEntity incomeEntity = new IncomeEntity(1, "Salary", new BigDecimal("1000"), LocalDate.now());
    incomeRepository.add(incomeEntity);

    // Prepare input
    String input = "1\nNew Source\n2000\n01/01/2024\n";
    InputStream inputStream = new ByteArrayInputStream(input.getBytes());
    System.setIn(inputStream);

    // Call the method
    IncomeController.updateData(incomeRepository);

    // Verify the output
    String expectedOutput = "Income with ID 1 updated successfully.";
    Assert.assertEquals(expectedOutput, outputStream.toString().trim());

    // Verify the updated data in the repository
    IncomeEntity updatedIncome = incomeRepository.findById(1);
    Assert.assertEquals("New Source", updatedIncome.getIncome());
    Assert.assertEquals(new BigDecimal("2000"), updatedIncome.getAmount());
    Assert.assertEquals(LocalDate.of(2024, 1, 1), updatedIncome.getDate());
}

@Test
public void testFindByIdData() {
    // Prepare test data
    IncomeEntity incomeEntity = new IncomeEntity(1, "Salary", new BigDecimal("1000"), LocalDate.now());
    incomeRepository.add(incomeEntity);

    // Prepare input
    String input = "1\n";
    InputStream inputStream = new ByteArrayInputStream(input.getBytes());
    System.setIn(inputStream);

    // Call the method
    IncomeController.findByIdData(incomeRepository);

    // Verify the output
    String expectedOutput = "Income Details:\n" +
            "ID: 1\n" +
            "Source: Salary\n" +
            "Amount: 1000\n" +
            "Date: " + LocalDate.now().toString();
    Assert.assertEquals(expectedOutput, outputStream.toString().trim());
}

@Test
public void testFindAll() {
    // Prepare test data
    IncomeEntity incomeEntity1 = new IncomeEntity(1, "Salary", new BigDecimal("1000"), LocalDate.now());
    IncomeEntity incomeEntity2 = new IncomeEntity(2, "Bonus", new BigDecimal("500"), LocalDate.now());
    incomeRepository.add(incomeEntity1);
    incomeRepository.add(incomeEntity2);

    // Call the method
    IncomeController.findAll(incomeRepository);

    // Verify the output
    String expectedOutput = "All Income Data:\n" +
            "ID: 1\n" +
            "Source: Salary\n" +
            "Amount: 1000\n" +
            "Date: " + LocalDate.now().toString() + "\n\n" +
            "ID: 2\n" +
            "Source: Bonus\n" +
            "Amount: 500\n" +
            "Date: " + LocalDate.now().toString();
    Assert.assertEquals(expectedOutput, outputStream.toString().trim());
}

}
