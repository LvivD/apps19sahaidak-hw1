package ua.edu.ucu.tempseries;

import java.util.InputMismatchException;
import java.util.Objects;

public class TemperatureSeriesAnalysis {

    private double[] temperatureSeries;

    public TemperatureSeriesAnalysis() {

    }

    public TemperatureSeriesAnalysis(double[] temperatureSeries) {
        final int minTemperature = -273;
        for (double temperature: temperatureSeries) {
            if (temperature < minTemperature) {
                throw new InputMismatchException();
            }
        }
        this.temperatureSeries = temperatureSeries;
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
//            if (Math.abs(closest - tempValue) == Math.abs(temperature - tempValue)) {
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

        return lessThenSeries;
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

        return greaterThenSeries;
    }

    public TempSummaryStatistics summaryStatistics() {
        ifEmpty();
        TempSummaryStatistics summary = new TempSummaryStatistics(
                this.average(), this.deviation(), this.min(), this.max());
        return summary;
    }

    public int addTemps(double... temps) {
        return 0;
    }
}
