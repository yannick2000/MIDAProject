package controller;


import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.util.JRLoader;
import net.sf.jasperreports.view.JasperViewer;

import java.io.InputStream;
import java.sql.Connection;
import java.util.Map;

public abstract class JasperReport {
    private  static JasperReport jReport;
    private  static JasperViewer jview;
    private static JasperPrint jprint;
    public static  void createReport(Connection connect, Map<String,Object> map, InputStream by){
        try{
            jReport = (JasperReport) JRLoader.loadObject(by);
            jprint = JasperFillManager.fillReport(String.valueOf(jReport),map,connect);
        }catch (Exception e){
            e.printStackTrace();
        }
    }
    public static void showReport(){
        jview=new JasperViewer(jprint);
        jview.setVisible(true);
    }

}
