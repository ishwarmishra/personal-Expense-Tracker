import com.personalfinancetracker.model.ExpenseEntity;
import com.personalfinancetracker.repository.ExpenseRepository;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;

public class ExpenseControllerTest {

    private ExpenseRepository expenseRepository;

    @Before
    public void setUp() {
        expenseRepository = new ExpenseRepository();
    }

    @Test
    public void testSaveData() {
        ExpenseEntity expenseEntity1 = new ExpenseEntity(100, "Food", new BigDecimal("50"), LocalDate.now());

        expenseRepository.add(expenseEntity1);

        Assert.assertNotNull(expenseEntity1);
        //Assert.assertNull(expenseEntity1);
    }

    @Test
    public void deleteData() {
        ExpenseEntity expenseEntity = new ExpenseEntity(99, "Travel", new BigDecimal("200"), LocalDate.now());
        expenseRepository.add(expenseEntity);
        expenseRepository.delete(99);

        ExpenseEntity deletedEntity = expenseRepository.findById(99);
        Assert.assertNull(deletedEntity);
        //Assert.assertNotNull(deletedEntity);
    }

    @Test
    public void updateData() {
        ExpenseEntity updateEntity = new ExpenseEntity(22, "Rent", new BigDecimal("1000"), LocalDate.now());
        expenseRepository.add(updateEntity);

        ExpenseEntity updatedEntity = expenseRepository.update(updateEntity);

        Assert.assertEquals(updateEntity.getId(), updatedEntity.getId());
        Assert.assertEquals(updateEntity.getExpense(), updatedEntity.getExpense());
        Assert.assertEquals(updateEntity.getAmount(), updatedEntity.getAmount());
        Assert.assertEquals(updateEntity.getDate(), updatedEntity.getDate());

        //        Assert.assertNotEquals(updateEntity.getId(),updatedEntity.getId());
        //        Assert.assertNotEquals(updateEntity.getExpense(),updatedEntity.getAmount());
        //        Assert.assertNotEquals(updateEntity.getAmount(),updatedEntity.getAmount());
        //        Assert.assertNotEquals(updateEntity.getDate(),updatedEntity.getDate());
    }

    @Test
    public void findById() {
        ExpenseEntity expenseEntity1 = new ExpenseEntity(1000, "Shopping", new BigDecimal("200"), LocalDate.now());
        expenseRepository.add(expenseEntity1);

        ExpenseEntity retrieveEntity = expenseRepository.findById(100);

        Assert.assertNotNull(retrieveEntity);
//        Assert.assertNotEquals(expenseEntity1.getId(), retrieveEntity.getId());
//        Assert.assertNotEquals(expenseEntity1.getExpense(), retrieveEntity.getExpense());
//        Assert.assertNotEquals(expenseEntity1.getAmount(), retrieveEntity.getAmount());
//        Assert.assertNotEquals(expenseEntity1.getDate(), retrieveEntity.getDate());
    }

    @Test
    public void testFindAll() {
        ExpenseEntity expenseEntity1 = new ExpenseEntity(100, "Food", new BigDecimal("50"), LocalDate.now());
        ExpenseEntity expenseEntity2 = new ExpenseEntity(200, "Transportation", new BigDecimal("20"), LocalDate.now());
        expenseRepository.add(expenseEntity1);
        expenseRepository.add(expenseEntity2);

        List<ExpenseEntity> expenseEntities = expenseRepository.findAll();

        Assert.assertNotNull(expenseEntities);

//        Assert.assertEquals(2, expenseEntities.size());
//        expenseRepository.delete(expenseEntity1.getId());
//
//        expenseEntities = expenseRepository.findAll();
//
//        Assert.assertEquals(1, expenseEntities.size());
//        Assert.assertEquals(expenseEntity2, expenseEntities.get(0));
//        Assert.assertTrue(expenseEntities.get(0).getId() == 100);
    }

    @Test
    public void addTest() {
        Assert.assertEquals(2, add(1, 1));
        Assert.assertEquals(3, add(1, 2));
        Assert.assertEquals(4, add(1, 3));
        Assert.assertNotEquals(2, add(1, 4));
    }

    public int add(int a, int b) {
        return a + b;
    }

    private Object saveData(ExpenseEntity expenseEntity1) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }
}
