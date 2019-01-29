package priv.thinkam.rentx.test.mapstruct;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Car {
	private String make;
	private int numberOfSeats;
	private CarType type;

	enum CarType {
		SEDAN
	}
}

