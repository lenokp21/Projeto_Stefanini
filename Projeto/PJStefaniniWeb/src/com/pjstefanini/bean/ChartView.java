package com.pjstefanini.bean;

import javax.annotation.PostConstruct;
import java.io.Serializable;
import javax.faces.bean.ManagedBean;
 
import org.primefaces.model.chart.Axis;
import org.primefaces.model.chart.AxisType;
import org.primefaces.model.chart.BarChartModel;
import org.primefaces.model.chart.HorizontalBarChartModel;

import com.pjstefanini.dao.CargoDAO;
import com.pjstefanini.dao.FuncionarioDAO;
import com.pjstefanini.exception.SistemaException;

import org.primefaces.model.chart.ChartSeries;
 
@ManagedBean
public class ChartView implements Serializable {
 
    private BarChartModel barModel;
    
    private HorizontalBarChartModel horizontalBarModel;
 
    @PostConstruct
    public void init() {
        createBarModels();
    }
 
    public BarChartModel getBarModel() {
        return barModel;
    }
     
    public HorizontalBarChartModel getHorizontalBarModel() {
        return horizontalBarModel;
    }

	private BarChartModel initBarModel() {
        BarChartModel model = new BarChartModel();
 
        ChartSeries funcionarios = new ChartSeries();
        ChartSeries cargos = new ChartSeries();
        
        try {
			funcionarios = FuncionarioDAO.listarFuncionarioEmpresa();
			cargos = CargoDAO.listarEmpresaCargos();
		} catch (SistemaException e) {
			e.printStackTrace();
		}
 
        model.addSeries(funcionarios);
        model.addSeries(cargos);
         
        return model;
    }
     
    private void createBarModels() {
        createBarModel();
    }
     
    private void createBarModel() {
        barModel = initBarModel();
         
        barModel.setTitle("Quadro de Funcionarios/Cargos");
        barModel.setLegendPosition("ne");
         
        Axis xAxis = barModel.getAxis(AxisType.X);
        xAxis.setLabel("Empresa");
         
        Axis yAxis = barModel.getAxis(AxisType.Y);
        yAxis.setLabel("Funcionarios/Cargos");
        yAxis.setMin(0);
        yAxis.setMax(10);
    }
     
    
 
}