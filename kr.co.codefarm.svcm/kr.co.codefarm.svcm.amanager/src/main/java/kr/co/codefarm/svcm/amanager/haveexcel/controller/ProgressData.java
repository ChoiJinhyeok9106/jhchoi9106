/**
 * 
 */
package kr.co.codefarm.svcm.amanager.haveexcel.controller;

public class ProgressData {
	
    private int currentRow;
    private int totalRows;
    private double progress;

    public ProgressData(int currentRow, int totalRows, double progress) {
        this.currentRow = currentRow;
        this.totalRows = totalRows;
        this.progress = progress;
    }

    // Getter, Setter 메서드는 필요에 따라 추가할 수 있음

    public int getCurrentRow() {
        return currentRow;
    }

    public void setCurrentRow(int currentRow) {
        this.currentRow = currentRow;
    }

    public int getTotalRows() {
        return totalRows;
    }

    public void setTotalRows(int totalRows) {
        this.totalRows = totalRows;
    }

    public double getProgress() {
        return progress;
    }

    public void setProgress(double progress) {
        this.progress = progress;
    }
}