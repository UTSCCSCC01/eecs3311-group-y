package analysis;

// Abstract class for Strategy - base class for the different types of analysis
public interface analysisStrategy<T>{

    // Each type of analysis should present itself
    T performAnalysis(analysisContext context);
}
