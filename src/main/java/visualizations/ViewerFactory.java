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
                return new Viewers_Pie(dataStorage, title);
            case "Report":
                return new Viewer_Report(dataStorage, title);
            case "Line":
                return new Viewers_Line(dataStorage, xLabel, yLabel, title);
            case "Scatter":
                return new Viewer_Scatter(dataStorage, xLabel, yLabel, title);
            case "Scatter2":
                return new Viewer_Scatter(dataStorage, xLabel, yLabel, yLabel2, title);
            case "Bar":
                return new Viewers_Bar(dataStorage, xLabel, yLabel, title);
            case "Bar2":
                return new Viewers_Bar(dataStorage, xLabel, yLabel, yLabel2, title);
            default:
                throw new IllegalArgumentException("Unknown Graph " + viewerType);
        }

    }

}
