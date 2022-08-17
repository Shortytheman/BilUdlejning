package com.example.biludlejning;
import com.example.biludlejning.model.LejeAftale;
import com.example.biludlejning.repository.LejeAftaleRepository;
import com.example.biludlejning.repository.SkadesrapportRepository;
import com.example.biludlejning.service.BilService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.kafka.KafkaProperties;
import org.springframework.boot.test.context.SpringBootTest;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;
//Skrevet af Johannes og Niklas og reviewet af hele gruppen

@SpringBootTest
class UC07 {

  @Autowired
  //Arrange
  private LejeAftaleRepository lr = new LejeAftaleRepository();

  @Test
  void slutAftaleAdvarselTest() {
    //Arrange
    LejeAftale nyla = new LejeAftale(1,1,1,1,"180822");
    //Act
    lr.opretLejeaftale(nyla);
    //Assert
    assertThat(lr.slutAftaleAdvarsel().size(),is(1));
    //Cleanup
    lr.sletLejeAftaleEfterKundeId(1);
  }

}
