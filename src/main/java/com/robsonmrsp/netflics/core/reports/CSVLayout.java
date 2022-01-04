package com.robsonmrsp.netflics.core.reports;

import java.util.LinkedList;

/* generated by JSetup v0.95 :  at 3 de jan de 2022 23:41:20 */
public abstract class CSVLayout<T> {

	private LinkedList<CSVColumn<T>> columns = new LinkedList<CSVColumn<T>>();

	public void addColumn(CSVColumn<T> csvColumn) {
		getColumns().add(csvColumn);
	}

	public LinkedList<CSVColumn<T>> getColumns() {
		return columns;
	}

	public void setColumns(LinkedList<CSVColumn<T>> columns) {
		this.columns = columns;
	}
	
	public abstract void adicionarColunas();
}
