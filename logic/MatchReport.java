package logic;

import entities.Goal;
import entities.Suspension;

public class MatchReport {

	public MatchReport() {

		sortSideBySide(goalArray(), suspensionArray());

		System.out.println();

		sort(goalArray(), suspensionArray());
	}

	private Goal[] goalArray() {
		Goal goal1 = new Goal(1, 13, 60, 1);

		Goal goal2 = new Goal(2, 13, 120, 1);

		Goal goal3 = new Goal(3, 12, 160, 1);

		Goal goalArr[] = { goal1, goal2, goal3 };

		return goalArr;
	}

	private Suspension[] suspensionArray() {

		Suspension sus1 = new Suspension(1, 12, 55, 1);

		Suspension sus2 = new Suspension(2, 13, 125, 1);

		Suspension sus3 = new Suspension(3, 12, 158, 1);

		Suspension sus4 = new Suspension(4, 13, 180, 1);

		Suspension susArr[] = { sus1, sus2, sus3, sus4 };

		return susArr;
	}

	private void sortSideBySide(Goal[] goalArr, Suspension[] susArr) {
		int i = 0;
		int j = 0;

		while (i < susArr.length) {
			while (j < goalArr.length) {
				if (goalArr[j].getMatchTime() < susArr[i].getMatchTime()) {
					System.out.println("Hold " + goalArr[j].getScoringTeam() + " har scoret i det "
							+ goalArr[j].getMatchTime() + " sekund");
					j++;

					if (j == goalArr.length) {
						while (i < susArr.length) {
							System.out.println("Hold " + susArr[i].getSuspensionTeam() + " har fået suspension i det "
									+ susArr[i].getMatchTime() + " sekund");
							i++;
						}
					}

				} else {
					System.out.println("Hold " + susArr[i].getSuspensionTeam() + " har fået suspension i det "
							+ susArr[i].getMatchTime() + " sekund");
					i++;

					if (i == susArr.length) {
						while (j < goalArr.length) {
							System.out.println("Hold " + goalArr[j].getScoringTeam() + " har scoret i det "
									+ goalArr[j].getMatchTime() + " sekund");
							j++;
						}
					}

				}
			}

		}
	}

	private void sort(Goal[] goalArr, Suspension[] susArr) {

		int arr1[] = new int[goalArr.length + susArr.length];
		
		for (int i = 0; i < arr1.length;) {
			for (int j = 0; j < goalArr.length; j++) {
				arr1[i] = goalArr[j].getScoringTeam();
				i++;
			}
			for (int j = 0; j < susArr.length; j++) {
				arr1[i] = susArr[j].getSuspensionTeam();
				i++;
			}
		}
		
		int arr2[] = new int[goalArr.length + susArr.length];
		
		for (int i = 0; i < arr2.length;) {
			for (int j = 0; j < goalArr.length; j++) {
				arr2[i] = goalArr[j].getMatchTime();
				i++;
			}
			for (int j = 0; j < susArr.length; j++) {
				arr2[i] = susArr[j].getMatchTime();
				i++;
			}
		}
		
//		int arr3[] = new int[susArr[4].getSuspensionTeam()];
//		int h = 0;
//		
//		for (int i = 0; i < arr3.length; i++) {
//			h = arr2[i];
//		}
//		
//		System.out.println(h);
//		
//		
//		for (int i = 0; i < arr1.length; i++) {
//			System.out.println(arr1[i] + " " + arr2[i]);
//		}

	}

}
