
import com.personalfinancetracker.model.IncomeEntity;
import com.personalfinancetracker.repository.IncomeRepository;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;

public class IncomeControllerTest {

    private IncomeRepository incomeRepository;

    @Before
    public void setUp() {
        incomeRepository = new IncomeRepository();
    }

    @Test
    public void testSaveData() {
        IncomeEntity incomeEntity1 = new IncomeEntity(100, "Salary", new BigDecimal("1000"), LocalDate.now());

        incomeRepository.add(incomeEntity1);
        IncomeEntity addEntity= incomeRepository.findById(100);
              
        Assert.assertEquals(incomeEntity1.getId(),addEntity.getId());
        Assert.assertEquals(incomeEntity1.getIncome(), addEntity.getIncome());
        Assert.assertEquals(incomeEntity1.getAmount(),addEntity.getAmount());
        Assert.assertEquals(incomeEntity1.getDate(), addEntity.getDate());

        
       //Assert.assertNull(incomeEntity1);
        
        
        

    }
    @Test
    public void deleteData(){
        IncomeEntity incomeEntity=new IncomeEntity(99, "Music", new BigDecimal("20000"), LocalDate.now()); 
       incomeRepository.add(incomeEntity);
       incomeRepository.delete(99);
       
       IncomeEntity deletedEntity= incomeRepository.findById(99);
       
       Assert.assertNull(deletedEntity);
       
    }
    
    @Test
    public void updateData(){
        IncomeEntity upEntity=new IncomeEntity(22, "Rent", new BigDecimal("30000"), LocalDate.now());
        incomeRepository.add(upEntity);
        
        IncomeEntity retrieveEntity = incomeRepository.findById(22);

        IncomeEntity updatedEntity =incomeRepository.update(upEntity);
        
        Assert.assertEquals(retrieveEntity.getId(), updatedEntity.getId());
        Assert.assertEquals(retrieveEntity.getIncome(),updatedEntity.getIncome());
        Assert.assertEquals(retrieveEntity.getAmount(), updatedEntity.getAmount());
        Assert.assertEquals(retrieveEntity.getDate(), updatedEntity.getDate());
        
        Assert.assertNotNull(updatedEntity);
        
        
    } 

    @Test
    public void findById() {
        IncomeEntity incomeEntity1 = new IncomeEntity(100, "Salary", new BigDecimal("1000"), LocalDate.now());
        incomeRepository.add(incomeEntity1);

        IncomeEntity retrieveEntity = incomeRepository.findById(100);


      
        Assert.assertEquals(incomeEntity1.getId(), retrieveEntity.getId());
        Assert.assertEquals(incomeEntity1.getIncome(), retrieveEntity.getIncome());
        Assert.assertEquals(incomeEntity1.getAmount(),retrieveEntity.getAmount());
        Assert.assertEquals(incomeEntity1.getDate(),retrieveEntity.getDate());

    }
    
    

    @Test
    public void testFindAll() {
        IncomeEntity incomeEntity1 = new IncomeEntity(100, "Salary", new BigDecimal("1000"), LocalDate.now());
        IncomeEntity incomeEntity2 = new IncomeEntity(200, "Bonus", new BigDecimal("500"), LocalDate.now());
        incomeRepository.add(incomeEntity1);
        incomeRepository.add(incomeEntity2);

        List<IncomeEntity> incomeEntities = incomeRepository.findAll();
        
        Assert.assertNotNull(incomeEntities);
        
//        Assert.assertEquals(2,incomeEntities.size() );
//        incomeRepository.delete(incomeEntity1.getId());
//            
//        incomeEntities = incomeRepository.findAll(); 
//        
//        Assert.assertEquals(1, incomeEntities.size());
//        Assert.assertEquals(incomeEntity2, incomeEntities.get(0));
//        Assert.assertTrue(incomeEntities.get(0).getId() == 100);
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

    private Object saveData(IncomeEntity incomeEntity1) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }
}
