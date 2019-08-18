package com.example.metromaps.Models;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class RowsModel {

@SerializedName("rows")
@Expose
private List<Row> rows = null;

public List<Row> getRows() {
return rows;
}

public void setRows(List<Row> rows) {
this.rows = rows;
}

}