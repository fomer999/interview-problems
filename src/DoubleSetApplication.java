import java.util.HashMap;
import java.util.Map;

/*
Implement the DoubleSet data structure described below.
A DoubleSet is a collection whose members are integers, and who can have one or two of each member. We express them below in
maplike notation, ie the DoubleSet that has two 1s, one -3, and one 0 is represented as
{{1: 2}, {-3: 1}, {0: 1}}

If a DoubleSet has two of member 1, adding a third would result in an identical DoubleSet. If a
DoubleSet has zero of member 3, subtracting a 3 would likewise result in an identical DoubleSet.

We add two DoubleSets by adding each of their members, each of whose count can be no greater than two:
doubleSetOne has members
{{1: 2}, {2: 1}}
and doubleSetTwo has members
{{1: 1}, {2: 1}, {-3: 1}}
their sum is
{{1: 2}, {2: 2}, {-3: 1}}

We subtract two DoubleSets by subtracting each of their counts,
where elements whose counts fall below one are omitted from the difference entirely:
doubleSetOne has members
 {{1: 2}, {2: 1}, {4: 1}}
and doubleSetTwo has members
{{1: 1}, {2: 2}, {-3: 1}}
their difference is
{{1: 1}, {4: 1}}
* */

// Using HashMap as the underlying data structure and a custom Integer class
// to restrict values to 1 and 2
class DoubleSet {
	private Map<Integer, RestrictedInteger> map;

	public DoubleSet() {
		map = new HashMap<Integer, RestrictedInteger>();
	}
	public Map<Integer, RestrictedInteger> getMap() {
		return map;
	}

	public void setMap(Map<Integer, RestrictedInteger> map) {
		this.map = map;
	}

	public DoubleSet addToAnotherMap(DoubleSet anotherDoubleSet) {
		anotherDoubleSet.map.forEach((k, v) -> this.map.merge(k, v, RestrictedInteger::sum));
		return this;
	}

	public DoubleSet subtractAnotherMap(DoubleSet anotherDoubleSet) {
		for (Map.Entry<Integer, RestrictedInteger> entry : anotherDoubleSet.getMap().entrySet()) {
			if (this.map.containsKey(entry.getKey())) {
				this.map.merge(entry.getKey(), entry.getValue(), RestrictedInteger::difference);
			}
		}
		return this;
	}

	@Override
	public String toString() {
		StringBuilder stringBuilder = new StringBuilder();
		stringBuilder.append('{');
		for (Map.Entry<Integer, RestrictedInteger> entry : map.entrySet()) {
			if (entry.getValue() != null) {
				stringBuilder.append("{" + entry.getKey() + ": " + entry.getValue() + "}" + " ");
			}
		}
		stringBuilder.append('}');
		return stringBuilder.toString();
	}

}

class RestrictedInteger {
	private int number;

	@Override
	public String toString() {
		return String.valueOf(number);
	}

	public RestrictedInteger(int number) {
		this.setNumber(number);
	}

	public int getNumber() {
		return number;
	}

	public void setNumber(int numberParam) {
		if (numberParam >= 1 && numberParam <= 2) {
			this.number = numberParam;
		} else {
			throw new IllegalArgumentException("Invalid number value");
		}
	}

	public static RestrictedInteger sum(RestrictedInteger num1, RestrictedInteger num2) {
		if (num1.getNumber() + num2.getNumber() >= 2) {
			return new RestrictedInteger(2);
		}
		return new RestrictedInteger(num1.getNumber() + num2.getNumber());
	}

	public static RestrictedInteger difference(RestrictedInteger num1, RestrictedInteger num2) {
		if (num1.getNumber() - num2.getNumber() < 1) {
			return null;
		}
		return new RestrictedInteger(num1.getNumber() - num2.getNumber());
	}
}

public class DoubleSetApplication {
	public static void main(String[] args) {
		DoubleSet doubleSet1 = new DoubleSet(); // {{1: 2}, {2: 1}}
		doubleSet1.getMap().put(1, new RestrictedInteger(2));
		doubleSet1.getMap().put(2, new RestrictedInteger(1));

		DoubleSet doubleSet2 = new DoubleSet(); // {{1: 1}, {2: 1}, {-3: 1}}
		doubleSet2.getMap().put(1, new RestrictedInteger(1));
		doubleSet2.getMap().put(2, new RestrictedInteger(1));
		doubleSet2.getMap().put(-3, new RestrictedInteger(1));

		DoubleSet sum = doubleSet1.addToAnotherMap(doubleSet2);
		System.out.println("ADD SAMPLE RESULT");
		System.out.println(sum.toString());

		DoubleSet doubleSet3 = new DoubleSet(); // {{1: 2}, {2: 1}, {4: 1}}
		doubleSet3.getMap().put(1, new RestrictedInteger(2));
		doubleSet3.getMap().put(2, new RestrictedInteger(1));
		doubleSet3.getMap().put(4, new RestrictedInteger(1));

		DoubleSet doubleSet4 = new DoubleSet(); // {{1: 1}, {2: 2}, {-3: 1}}
		doubleSet4.getMap().put(1, new RestrictedInteger(1));
		doubleSet4.getMap().put(2, new RestrictedInteger(1));
		doubleSet4.getMap().put(-3, new RestrictedInteger(1));

		DoubleSet difference = doubleSet3.subtractAnotherMap(doubleSet4);
		System.out.println("SUBTRACT SAMPLE RESULT");
		System.out.println(difference.toString());
	}
}



