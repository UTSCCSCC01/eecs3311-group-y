package visualizations;

import java.io.IOException;
import java.util.ArrayList;

import dataFetch.StoredData;

public class ViewerFactory {

    public Viewer CreateViewerFactory(String viewerType, ArrayList<StoredData> dataStorage, String xLabel,
            String yLabel, String yLabel2,
            String title) throws IOException {
        if (viewerType == null || viewerType.isEmpty())
            return null;
        switch (viewerType) {
            case "Pie":
                return new ViewerPie(dataStorage, title);
            case "Report":
                return new ViewerReport(dataStorage, title);
            case "Line":
                return new ViewerLine(dataStorage, xLabel, yLabel, title);
            case "Scatter":
                return new ViewerScatter(dataStorage, xLabel, yLabel, title);
            case "Scatter2":
                return new ViewerScatter(dataStorage, xLabel, yLabel, yLabel2, title);
            case "Bar":
                return new ViewerBar(dataStorage, xLabel, yLabel, title);
            case "Bar2":
                return new ViewerBar(dataStorage, xLabel, yLabel, yLabel2, title);
            default:
                throw new IllegalArgumentException("Unknown Graph " + viewerType);
        }

    }

}
