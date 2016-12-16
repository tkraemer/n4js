/**
 * Copyright (c) 2016 NumberFour AG.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *   NumberFour AG - Initial API and implementation
 */
package eu.numberfour.n4js.generator.headless;

import java.util.ArrayList;
import java.util.List;

/**
 * Small util to track used memory and present it in ASCII table format.
 */
public class MemoryTracker {

	private static final Runtime RUNTIME = Runtime.getRuntime();
	private final List<List<Long>> usedMemory = new ArrayList<>();
	private final List<List<String>> labels = new ArrayList<>();
	private final boolean recordData;
	private final boolean verbose;
	private int counter = -1;
	private int longestLabel = 0;

	/** Default, tracker will record memory data. */
	public MemoryTracker() {
		this(true, false);
	}

	/** Flag decides if tracker will record data. Convenience approach, easier then conditionals calls by clients. */
	public MemoryTracker(boolean recordData, boolean verbose) {
		this.recordData = recordData;
		this.verbose = verbose;
	}

	/** Use provided label as new series of measurements. */
	public void startSeries(String label) {
		if (!recordData)
			return;

		usedMemory.add(new ArrayList<>());
		labels.add(new ArrayList<>());
		counter += 1;
		recordMemoryData(label);
	}

	/** Use provided label to mark made measurement. */
	public void addSeriesPoint(String label) {
		if (!recordData)
			return;

		recordMemoryData(label);
	}

	private void recordMemoryData(String label) {
		usedMemory.get(counter).add(RUNTIME.totalMemory() - RUNTIME.freeMemory());
		labels.get(counter).add(label);
		if (label.length() > longestLabel) {
			longestLabel = label.length();
		}

		if (verbose) {
			List<String> seriesLabels = labels.get(counter);
			List<Long> seriesUsed = usedMemory.get(counter);
			StringBuilder sb = new StringBuilder("\nrecording memory " + seriesLabels.get(0));

			int current = seriesUsed.size() - 1;
			int prevPoint = current > 1 ? current - 1 : 0;

			sb.append(String.format("\n| %s | %s | %s |",
					formatLabel(" mem diff"),
					formatMemory("used delta"),
					formatMemory("used total")));
			sb.append(String.format("\n| %s | %s | %s |",
					formatLabel(" " + label),
					formatMemory(bytesToHuman(seriesUsed.get(current) - seriesUsed.get(prevPoint))),
					formatMemory(bytesToHuman(seriesUsed.get(current)))));

			System.out.println(sb);
		}
	}

	/** Returns string with ASCII style report. */
	public String dataTable() {
		if (counter < 0) {
			return "no memory data recorded";
		}
		StringBuilder sb = new StringBuilder("\n");
		long pUsed = 0;
		int series = 0;
		int points = 0;
		int point = 0;
		while (series <= counter) {
			List<String> seriesLabels = labels.get(series);
			List<Long> seriesUsed = usedMemory.get(series);
			points = seriesLabels.size();
			sb.append(String.format("\n| %s | %s | %s |",
					formatLabel(seriesLabels.get(0)),
					formatMemory("used delta"),
					formatMemory("used total")));
			sb.append(String.format("\n| %s | %s | %s |",
					formatLabel(" mem diff"),
					formatMemory(bytesToHuman(seriesUsed.get(points - 1) - seriesUsed.get(0))),
					formatMemory(bytesToHuman(seriesUsed.get(points - 1)))));
			sb.append(String.format("\n    | %s | %s | %s |",
					formatLabel("details"),
					formatMemory("used delta"),
					formatMemory("used total")));
			while (point < points) {
				long used = seriesUsed.get(point);
				sb.append(String.format("\n    | %s | %s | %s |",
						formatLabel(seriesLabels.get(point)),
						formatMemory(bytesToHuman(used - pUsed)),
						formatMemory(bytesToHuman(used))));
				pUsed = used;
				point += 1;
			}
			sb.append("\n");
			point = 0;
			series += 1;
		}

		return sb.toString();
	}

	private String formatLabel(String label) {
		return String.format("%1$-" + longestLabel + "s", label);
	}

	private static String formatMemory(String mem) {
		return String.format("%1$10s", mem);
	}

	private static String bytesToHuman(long value) {
		long abs = Math.abs(value);
		if (abs < 1024) {
			return value + " B";
		}
		int z = (63 - Long.numberOfLeadingZeros(abs)) / 10;
		return String.format("%.1f %sB", (double) value / (1L << (z * 10)), " KMGTPE".charAt(z));
	}

}