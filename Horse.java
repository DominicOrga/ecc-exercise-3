import java.util.Random;
import java.util.Optional;
import java.util.List;
import java.util.function.Supplier;
import java.util.stream.Stream;
import java.util.stream.Collectors;

public class Horse {

	private static List<Optional<String>> warCries;

	static {
		Supplier<Optional<String>> warCrySupplier = () -> {
			return (Math.random() < 0.5) ? 
				Optional.empty() : Optional.of(Utility.getAlphaNumericString(5));
		};

		warCries = Stream.generate(warCrySupplier)
						 .limit(10)
						 .collect(Collectors.toList());
	}

	private float currentDestination;
	private float targetDestination;

	private boolean isHealthy;
	private String name;

	public Horse(String name, float initialDestination, float targetDestination) {
		this.name = name;
		this.currentDestination = initialDestination;
		this.targetDestination = targetDestination;

		this.isHealthy = Math.random() > 0.5;
	}

	public String getName() {
		return this.name;
	}

	public float getCurrentDestination() {
		return this.currentDestination;
	}

	public float getTargetDestination() {
		return this.targetDestination;
	}

	public boolean isHealthy() {
		return this.isHealthy;
	}

	public void run(boolean isBoosted) {
		if (isFinished()) {
			return;
		}

		Random rnd = new Random();

		int min = 1;
		int max = (isBoosted) ? 20 : 10;

		float move = (float) Math.random() * (max - min) + min;
		this.currentDestination += move;

		if (isFinished()) {
			System.out.printf("%s has completed the race.\n", this.name);
		}
		else {
			System.out.printf("%s ran %.2f yards %s. Only %.2f yards left.\n", 
				this.name, move, (isBoosted) ? "(Boosted)" : Utility.EMPTY_STRING, 
				this.targetDestination - this.currentDestination);
		}
	}

	public boolean isFinished() {
		return this.currentDestination > this.targetDestination;
	}

	public void warCry() {
		Random rnd = new Random();
		Optional<String> warCry = warCries.get(rnd.nextInt(warCries.size()));
		System.out.printf("%s warcry: %s\n", this.name, warCry.orElse("No War Cry..."));
	}
}