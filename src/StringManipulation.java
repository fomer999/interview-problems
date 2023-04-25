public class StringManipulation {

	/* Write a function that capitalizes *only* the nth alphanumeric character of a string so that if I hand you

	Inspiration.com

	and I ask you to capitalize every 3rd character, you hand me back

	inSpiRatIon.Com

	If I ask you to capitalize every 4th character, you hand me back

	insPiraTion.Com

	Notes:
	Characters other than each nth should be downcased
	Count and capitalize only alphanumeric characters: [a-z][A-Z][0-9] (ignore special characters)
	Return empty string if n = 0 */

	public static String capitalizeNthCharacter(String input, int n) {
		if (n <= 0 || input == null) {
			return "";
		}
		if (input.length() <= n - 1) {
			return input.toLowerCase();
		}
		// convert String to char array for easy manipulation
		char[] inputArray = input.toLowerCase().toCharArray();

		int j = 0;
		for (int i = 0; i < inputArray.length; i++) {
			if (Character.isLetterOrDigit(inputArray[i])) {
				j++;
				if (j % n == 0) {
					inputArray[i] = Character.toUpperCase(inputArray[i]);
				}
			}
		}
		return String.copyValueOf(inputArray);
	}

	public static void main(String[] args) {
		System.out.println("Test Case 1:" + capitalizeNthCharacter("Inspiration.com", 3));
		System.out.println("Test Case 2:" + capitalizeNthCharacter("Inspiration.com", 4));
		System.out.println("Test Case 3:" + capitalizeNthCharacter("INSPIRATION.COM", 3));
		System.out.println("Test Case 4:" + capitalizeNthCharacter("INSPIRATION.COM", 0));
		System.out.println("Test Case 3:" + capitalizeNthCharacter("INS", 4));
	}
}



