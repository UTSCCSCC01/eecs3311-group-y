package analysis;

// Abstract class for Strategy - base class for the different types of analysis
public abstract class analysisStrategy {

    // Each type of analysis should present itself
    public abstract void performAnalysis(analysisContext context);
}
