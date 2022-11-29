package visualizations;

import java.io.IOException;
import java.util.ArrayList;

import dataFetch.ParsedData;
import dataFetch.StoredData;

public class ViewerFactory {

    //ArrayList<StoredData> dataStorage, String title, String xLabel, String yLabel, String yLabel2, String seriesName,
    //String seriesName2, String seriesName3
    public Viewer CreateViewerFactory(
            String viewerType, 
            ArrayList<StoredData> dataStorage, 
            String xLabel,
            String yLabel, 
            String yLabel2,
            String title, 
            String seriesName, 
            String seriesName2, 
            String seriesName3) throws IOException {
        if (viewerType == null || viewerType.isEmpty())
            return null;    
        switch (viewerType) {
            case "Pie":
                return new ViewerPie(dataStorage, title);
            case "Report":
                return new ViewerReport(dataStorage, title);
            case "Line":
                return new ViewerLine(dataStorage, title, xLabel, yLabel, yLabel2, seriesName, seriesName2, seriesName3);
            case "Scatter":
                return new ViewerScatter(dataStorage, title, xLabel, yLabel, yLabel2, seriesName, seriesName2, seriesName3);
            case "Bar":
                return new ViewerBar(dataStorage, title, xLabel, yLabel, yLabel2, seriesName, seriesName2, seriesName3);
            default:
                throw new IllegalArgumentException("Unknown Graph " + viewerType);
        }

    }

}
