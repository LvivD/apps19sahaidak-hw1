package ua.edu.ucu.tempseries;

import java.util.Arrays;
import java.util.InputMismatchException;
import java.util.Objects;

public class TemperatureSeriesAnalysis {

    private double[] temperatureSeries;
    final int MIN_TEMPERATURE = -273;

    public TemperatureSeriesAnalysis() {
        this.temperatureSeries = new double[0];
    }

    public TemperatureSeriesAnalysis(double[] temperatureSeries) {

        for (double temperature: temperatureSeries) {
            if (temperature < this.MIN_TEMPERATURE) {
                throw new InputMismatchException();
            }
        }
        this.temperatureSeries = temperatureSeries.clone();
    }

    private void ifEmpty() throws IllegalArgumentException {
        if (this.temperatureSeries.length == 0)
        {
            throw new IllegalArgumentException();
        }
    }

    public double average() {
        ifEmpty();

        double sum = 0;
        for (double temperature : this.temperatureSeries) {
            sum += temperature;
        }
        return sum/this.temperatureSeries.length;
    }

    public double deviation() {
        ifEmpty();

        double squareSum = 0;
        for (double temperature : this.temperatureSeries) {
            squareSum += temperature*temperature;
        }

        return Math.sqrt(squareSum/this.temperatureSeries.length);
    }

    public double min() {
        ifEmpty();

        double min = Double.POSITIVE_INFINITY;
        for (double temperature : this.temperatureSeries) {
            if (min > temperature) {
                min = temperature;
            }
        }

        return min;
    }

    public double max() {
        ifEmpty();

        double max = Double.NEGATIVE_INFINITY;
        for (double temperature : this.temperatureSeries) {
            if (max < temperature) {
                max = temperature;
            }
        }

        return max;
    }

    public double findTempClosestToZero() {
        return this.findTempClosestToValue(0);
    }

    public double findTempClosestToValue(double tempValue) {
        ifEmpty();

        double closest = Double.NEGATIVE_INFINITY;
        for (double temperature : this.temperatureSeries) {
            if (Math.abs(closest - tempValue)
                    > Math.abs(temperature - tempValue)) {
                closest = temperature;
            }
            if (Objects.equals(Math.abs(closest - tempValue),
                    Math.abs(temperature - tempValue))) {
                closest = Math.max(closest, temperature);
            }
        }

        return closest;
    }

    public double[] findTempsLessThen(double tempValue) {
        double[] lessThenSeries;
        lessThenSeries = new double[this.temperatureSeries.length];

        int i = 0;
        for (double temperature: this.temperatureSeries) {
            if (tempValue > temperature) {
                lessThenSeries[i] = temperature;
                i++;
            }
        }

        return Arrays.copyOf(lessThenSeries, i);
    }

    public double[] findTempsGreaterThen(double tempValue) {
        double[] greaterThenSeries = new double[this.temperatureSeries.length];

        int i = 0;
        for (double temperature: this.temperatureSeries) {
            if (tempValue < temperature) {
                greaterThenSeries[i] = temperature;
                i++;
            }
        }

        return Arrays.copyOf(greaterThenSeries, i);
    }

    public TempSummaryStatistics summaryStatistics() {
        ifEmpty();
        TempSummaryStatistics summary = new TempSummaryStatistics(
                this.average(), this.deviation(), this.min(), this.max());
        return summary;
    }

    public int addTemps(double... temps) {
        double[] newTemperatureSeries =
                new double[this.temperatureSeries.length + temps.length];
        int i;
        int j;
        for (i = 0; i < this.temperatureSeries.length; i++) {
            newTemperatureSeries[i] = this.temperatureSeries[i];
        }
        for (j = i; j < this.temperatureSeries.length + temps.length; j++) {
            if (temps[j-i] < this.MIN_TEMPERATURE) {
                throw new InputMismatchException();
            }
            newTemperatureSeries[j] = temps[j-i];
        }
            this.temperatureSeries = newTemperatureSeries;

        return j;
    }
}
