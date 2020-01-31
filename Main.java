
class PrimeList {
	private final int[] primes;

	public PrimeList(final int[] primes) {
		this.primes = primes;
	}

	public int get(final int index) {
		return this.primes[index];
	}

	public int getLength() {
		return this.primes.length;
	}
}

class PrimeGenerator {
	private static final int ORDMAX = 30;

	public static PrimeList generate(final int limit) {
		int primes[] = new int[limit + 1];
		primes[1] = 2;
		int prime = 1;
		int index = 1;
		boolean isPrime;
		int order = 2;
		int square = 9;
		int n = 0;
		int mult[] = new int[ORDMAX + 1];
		while (index < limit) { 
			do {
				prime += 2;
				if (prime == square) {
					order++;
					square = primes[order] * primes[order];
					mult[order - 1] = prime;
				}
				n = 2;
				isPrime = true;
				while (n < order && isPrime) {
					while (mult[n] < prime) {
						mult[n] += 2*primes[n];
					}
					isPrime = mult[n] != prime;
					n++;
				}
			} while (!isPrime);
			index++;
			primes[index] = prime;
		}
		return new PrimeList(primes);
	}
}

class PrimePrinter {
	private static final String FORMAT_PAGE_TEXT = "The First %d Prime Numbers === Page %d\n";
	public static void print(final PrimeList primeList, final int limit, final int rows, final int columns) {
		int page = 1;
		int pageOffset = 1;
		int rowOffset;
		int column;
		while (pageOffset <= limit) {
			System.out.print(String.format(FORMAT_PAGE_TEXT, limit,page));
			for (rowOffset = pageOffset; rowOffset <= pageOffset + rows - 1; rowOffset++) {
				for (column = 0; column <= columns - 1; column++) {
					if (rowOffset + column * rows <= limit) {
						System.out.printf("%10d", primeList.get(rowOffset + column * rows));
					}
				}
				System.out.println();
			}
			System.out.println("\f");
			page++;
			pageOffset += rows * columns;
		}
	}
}

public class Main {
	public static void main(String[] args) {
		final int limit = 1000;
		final int rows = 50;
		final int columns = 4;
		PrimeList primeList = PrimeGenerator.generate(limit);
		PrimePrinter.print(primeList, limit, rows, columns);
	}
}
