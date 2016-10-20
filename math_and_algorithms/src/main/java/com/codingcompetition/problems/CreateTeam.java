package com.codingcompetition.problems;

import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Scanner;

public class CreateTeam {

	static Scanner newInput() throws IOException {
		if (System.getProperty("JUDGE") != null) {
			return new Scanner(new File("team.in"));
		} else {
			return new Scanner(System.in);
		}
	}

	static PrintWriter newOutput() throws IOException {
		if (System.getProperty("JUDGE") != null) {
			return new PrintWriter("team.out");
		} else {
			return new PrintWriter(System.out);
		}
	}

	/**
	 * @param args
	 * @throws IOException
	 */
	public static void main(String[] args) throws IOException {

		try (Scanner in = newInput(); PrintWriter out = newOutput()) {

			String[] andrew = in.nextLine().split(" ");
			String[] peter = in.nextLine().split(" ");
			String[] paul = in.nextLine().split(" ");

			double result = 0;

			for (int i = 0; i < 3; i++) {
				for (int j = 0; j < 3; j++) {
					for (int n = 0; n < 3; n++) {

						if (i != j && i != n && j != n) {
							double calculatedEffiency = Math.sqrt(Math.pow(Double.valueOf(andrew[i]), 2)
									+ Math.pow(Double.valueOf(peter[j]), 2) + Math.pow(Double.valueOf(paul[n]), 2));
							if (result < calculatedEffiency) {
								result = calculatedEffiency;
							}
						}

					}
				}
			}
			out.println(result);
		}

	}

}
