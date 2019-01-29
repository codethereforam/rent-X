package priv.thinkam.rentx.test.mapstruct;

import org.junit.Test;

import static org.assertj.core.api.Assertions.assertThat;
public class MapstructTest {


	@Test
	public void shouldMapCarToDto() {
		//given
		Car car = new Car("Morris", 5, Car.CarType.SEDAN);

		//when
		CarDto carDto = CarMapper.INSTANCE.carToCarDto(car);
		System.out.println(carDto);

		//then
		assertThat(carDto).isNotNull();
		assertThat(carDto.getMake()).isEqualTo("Morris");
		assertThat(carDto.getSeatCount()).isEqualTo(5);
		assertThat(carDto.getType()).isEqualTo("SEDAN");
	}


}
