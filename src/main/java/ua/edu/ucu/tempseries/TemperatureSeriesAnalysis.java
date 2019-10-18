package ua.edu.ucu.tempseries;

import java.util.InputMismatchException;

public class TemperatureSeriesAnalysis {

    private double[] temperatureSeries;

    private void ifEmpty() throws InputMismatchException{
        if (this.temperatureSeries.length == 0)
        {
            throw new InputMismatchException();
        }
    }

    public TemperatureSeriesAnalysis() {

    }

    public TemperatureSeriesAnalysis(double[] temperatureSeries) {
        this.temperatureSeries = temperatureSeries;
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
            squareSum += Math.pow(temperature, 2);
        }

        return Math.sqrt(squareSum/this.temperatureSeries.length);
    }

    public double min() {
        ifEmpty();

        double min = Double.POSITIVE_INFINITY;;
        for (double temperature : this.temperatureSeries) {
            if (min > temperature) {
                min = temperature;
            }
        }

        return min;
    }

    public double max() {
        ifEmpty();

        double max = Double.NEGATIVE_INFINITY;;
        for (double temperature : this.temperatureSeries) {
            if (max < temperature) {
                max = temperature;
            }
        }

        return max;
    }

    public double findTempClosestToZero() {
        return findTempClosestToValue(0);
    }

    public double findTempClosestToValue(double tempValue) {
        ifEmpty();

        double closest = Double.NEGATIVE_INFINITY;
        for (double temperature : this.temperatureSeries) {
            if (Math.abs(closest - tempValue) > Math.abs(temperature - tempValue)) {
                closest = temperature;
            }
            if (Math.abs(closest - tempValue) == Math.abs(temperature - tempValue)) {
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
        return null;
    }

    public int addTemps(double... temps) {
        return 0;
    }
}
