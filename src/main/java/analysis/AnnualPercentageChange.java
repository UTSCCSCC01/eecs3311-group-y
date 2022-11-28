package analysis;

import dataFetch.StoredData;

public class AnnualPercentageChange extends analysisStrategy{
    @Override
    public void performAnalysis(analysisContext context){
        for (StoredData sd: context.getData()){
            System.out.println(sd);
//            for (int i = 0; i < sd.size(); i++){
//                System.out.println(sd[i]);
//            }
        }
    }
    //
}
