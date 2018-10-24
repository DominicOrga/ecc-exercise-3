
import java.util.List;
import java.util.Map;
import java.util.Comparator;
import java.util.Optional;
import java.util.function.Supplier;
import java.util.function.Predicate;
import java.util.stream.Stream;
import java.util.stream.Collectors;

public class HorseRacingArena {
	
	private List<Horse> horses;
	private List<Horse> healthyHorses;
	private Optional<Horse> winningHorse;

	public HorseRacingArena(int numberOfHorses, int numberOfHealthyHorses, 
		float initialDestinationRange, float maxDestination) {
		winningHorse = Optional.empty();

		Supplier<Horse> horseSupplier = horseSupplier(initialDestinationRange, maxDestination);

		horses = Stream.generate(horseSupplier)
					   .limit(numberOfHorses <= 0 ? 1 : numberOfHorses)
					   .collect(Collectors.toList());

	   	healthyHorses = horses.stream().filter(Horse::isHealthy)
	   								   .limit(numberOfHealthyHorses)
	   								   .collect(Collectors.toList());
	}

	private Supplier<Horse> horseSupplier(float initialDestinationRange, float maxDestination) {
		return () -> new Horse(
			Utility.getAlphaNumericString(5),
			(float) Math.random() * initialDestinationRange,
			maxDestination
		);
	}

	public int getHealthyHorsesSize() {
		return healthyHorses.size();
	}

	public String getRaceState() {
		return this.healthyHorses.stream()
								 .map(a -> String.format("%s = %.2f", a.getName(), a.getCurrentDestination()))
							     .collect(Collectors.joining(","));
	}

	public void startRace() {
		List<Horse> sortedUnfinishedHealthyHorses;

		while (!(sortedUnfinishedHealthyHorses = getSortedUnfinishedHealthyHorses()).isEmpty()) {
			final Horse lastHorse = sortedUnfinishedHealthyHorses.get(0);

			sortedUnfinishedHealthyHorses.parallelStream().forEach(
				(horse) -> {
					if (horse.equals(lastHorse)) {
						System.out.printf("Horse %s needs a boost. [%s]\n", horse.getName(), getRaceState());
						horse.run(true);
					}
					else {
						horse.run(false);
					}

					if (horse.isFinished() && !winningHorse.isPresent()) {
						horse.warCry();
						winningHorse = Optional.of(horse);
					}
				}
			);
		}
	}

	private List<Horse> getSortedUnfinishedHealthyHorses() {
		return this.healthyHorses.parallelStream()
								 .filter(horse -> !horse.isFinished())
							     .sorted(Comparator.comparing(Horse::getCurrentDestination))
								 .collect(Collectors.toList());
	}
}